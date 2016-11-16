/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.appenders.reportlogview;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IStateListener;
import org.eclipse.core.commands.State;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RadioState;

/**
 */
public class MarkerViewFilter extends ViewerFilter {

  /* Toggle commands for severity filter */
  public static final String DEBUG_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.severityfilter.debug"; //$NON-NLS-1$
  public static final String INFO_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.severityfilter.info"; //$NON-NLS-1$
  public static final String WARNING_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.severityfilter.warning"; //$NON-NLS-1$
  public static final String ERROR_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.severityfilter.error"; //$NON-NLS-1$
  public static final String FATAL_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.severityfilter.fatal"; //$NON-NLS-1$
 
  /* Radio command for selectionfilter */
  public static final String SELECTIONFILTER_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.selectionfilter"; //$NON-NLS-1$
  
  private Map<String, SeverityLevel> severityLevelMap = new HashMap<String, SeverityLevel>(); 
  
  
  private SelectionFilter selectionFilter; // the active selection filter
  private Object selectedElement; // the first element of the current selection
  private IProject project;       // the project that owns the selectedElement
  
  private Set<SeverityLevel> activeLevels;
  private Viewer viewer;
  
  
  public MarkerViewFilter(Viewer viewer_p){
    viewer = viewer_p;
    hookSeverityFilter();
    hookSelectionFilter();
    
    
  }
  
  private void hookSelectionFilter() {
    
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    
    // restore current selection filter
    Command c = service.getCommand(SELECTIONFILTER_COMMAND);
    State s = c.getState(RadioState.STATE_ID);
    selectionFilter = SelectionFilter.valueOf(s.getValue().toString());
    
    // add listener to refresh after the filter is changed
    s.addListener(new IStateListener(){
      @SuppressWarnings("synthetic-access")
      public void handleStateChange(State state_p, Object oldValue_p) {
        SelectionFilter filter = SelectionFilter.valueOf(state_p.getValue().toString());
        if (filter != selectionFilter){
          selectionFilter = filter;
          viewer.refresh();
        }
      }
    });
  }

  /**
   * 
   */
  @SuppressWarnings("boxing")
  private void hookSeverityFilter() {
    severityLevelMap.put(DEBUG_COMMAND, SeverityLevel.DEBUG);
    severityLevelMap.put(INFO_COMMAND, SeverityLevel.INFO);
    severityLevelMap.put(WARNING_COMMAND, SeverityLevel.WARNING);
    severityLevelMap.put(ERROR_COMMAND, SeverityLevel.ERROR);
    severityLevelMap.put(FATAL_COMMAND, SeverityLevel.FATAL);
    
    ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);    
    activeLevels = EnumSet.noneOf(SeverityLevel.class);
    
    for (final String commandId : severityLevelMap.keySet()){
      
      // initialize active levels from command state
      Command command = service.getCommand(commandId);
      State state = command.getState(org.eclipse.ui.handlers.RegistryToggleState.STATE_ID);
      if ((Boolean) state.getValue()){
        activeLevels.add(severityLevelMap.get(commandId));
      }
      
      // listen to state change and refresh the viewer
      // FIXME should remove those listeners on dispose
      state.addListener(new IStateListener(){
        @SuppressWarnings("synthetic-access")
        public void handleStateChange(State state_p, Object oldValue_p) {
          SeverityLevel level = severityLevelMap.get(commandId);
          if ((Boolean) state_p.getValue()){
            if (activeLevels.add(level)){
              viewer.refresh();
            }
          } else {
            if (activeLevels.remove(level)){
              viewer.refresh();
            }
          }
        }
      });
    }
    
  }

  public enum SelectionFilter {
    PROJECT,
    SELECTION,
    ALL;
  }
  
  void setSelectionFilter(SelectionFilter filter_p){
    if (filter_p != selectionFilter){
      selectionFilter = filter_p;
      viewer.refresh();
    }
  }
  
  /**
   * Update the selection. Usually you want to call viewer.refresh()
   * after setting the new selection via this method.
   * @param selection_p
   */
  void setSelection(ISelection selection_p){
    boolean refreshViewer = false;
    
    Object first = null;
    if (selection_p instanceof IStructuredSelection){
      first = ((IStructuredSelection) selection_p).getFirstElement();
    }
    
    if (first != selectedElement){ 
      if (selectionFilter == SelectionFilter.SELECTION){
        refreshViewer = true;
      }
      IProject newProject = getProject(selection_p);
      if (newProject != project){
        project = newProject;
        if (selectionFilter == SelectionFilter.PROJECT){
          refreshViewer = true;
        }
      }
      selectedElement = first;
    }
    if (refreshViewer){
      viewer.refresh();
    }
  }
  
  /**
   * @param selection_p
   * @return
   */
  protected IProject getProject(ISelection selection_p) {
    IProject result = null;
    if (selection_p instanceof IStructuredSelection){
      Object first = ((IStructuredSelection) selection_p).getFirstElement();
      result = getProject(first);
    }
    return result;
    }
    
    protected IProject getProject(Object selection){
      IProject result = null;
      if (selection instanceof IResource){
        result = ((IResource) selection).getProject();
      } else if (selection instanceof EObject){
        Resource resource = ((EObject) selection).eResource();
        if (resource != null){
          URI uri = resource.getURI();
          if (uri != null && uri.isPlatformResource()){
            String platformResourceString = uri.toPlatformString(true);
            result = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString)).getProject();
          }
        }
      } else if (selection instanceof IAdaptable){
        IResource resource = (IResource) ((IAdaptable) selection).getAdapter(IResource.class);
        if (resource == null){
          Platform.getAdapterManager().getAdapter(selection, IResource.class);
        }
        if (resource != null){
          result = resource.getProject();
        }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    boolean result = true;
    if (element_p instanceof IMarker){

      // severity level
      SeverityLevel level = SeverityLevel.getLevel((IMarker) element_p);
      if (level != null){
        result = activeLevels.contains(level);
      }

      switch (selectionFilter){
        case ALL: break;
        case PROJECT:   result &= select(project,         viewer_p, parentElement_p, (IMarker) element_p);   break;
        case SELECTION: result &= select(selectedElement, viewer_p, parentElement_p, (IMarker) element_p); break;
      }
      
    } else {
      // if it's not a marker:
      // at least one child is visible => element visible
      // TODO this recurses, and possibly invokes the filter more than once for a single element => memorization
      TreeViewer client = (TreeViewer) viewer_p;
      ITreeContentProvider provider = (ITreeContentProvider) client.getContentProvider();
      Object[] children = provider.getChildren(element_p);
      result = false;
      for (Object o : children){
        if (select(viewer_p, element_p, o)){
          result = true;
          break;
        }
      }
    }
    return result;
  }
   
  /**
   * Is the given marker selected (a.k.a. shown, a.k.a. visible) under
   * the given selected object.
   * 
   * If the selection filter is active, the marker will be shown if this 
   * returns true, and hidden if it returns false.
   * 
   * The default implementation handles the case where the selection is an
   * IResource or an EObject. For the IResource case, we return true (show
   * the marker) if the markers resource is 'contained' in the selected
   * resource. For the EObject case, we return true (show the marker), 
   * if the selection contains (in terms of Ecore containment) the 
   * elements that are affected by the marker 
   * (@see MarkerViewHelper.getModelElementsFromMarker)
   * 
   * Subclasses may override to support more complex use cases.
   * @param selection_p the element that is selected.
   * @return whether the marker should show or not.
   */
  protected boolean select(Object selection_p, Viewer viewer_p, Object parent_p, IMarker marker_p) {
    boolean result = false;
    if (selection_p instanceof IResource){
      result = select((IResource) selection_p, viewer_p, parent_p, marker_p);
    } else if (selection_p instanceof EObject){
      List<EObject> affected = MarkerViewHelper.getModelElementsFromMarker(marker_p);
      if (affected.size() > 0){
        EObject firstAffected = affected.get(0);
        if (EcoreUtil.isAncestor((EObject) selection_p, firstAffected)){
          result = true;
        }
      }
    }
    return result;
  }
  
  /**
   * Is the given marker selected (a.k.a. shown, a.k.a. visible) under
   * the given selected resource.
   * 
   * If the selection filter is active, the marker will be shown if this 
   * returns true, and hidden if it returns false.
   * 
   * This implementation returns true (shows the marker) if the markers 
   * resource is 'contained' in the selected resource.
   * 
   * @param resource_p the selected resource
   * @param viewer_p the viewer
   * @param parent_p element parent
   * @param marker_p the marker
   * @return whether the marker should show or not.
   * @see ViewerFilter.select()
   */
  private boolean select(IResource resource_p, Viewer viewer_p, Object parent_p, IMarker marker_p) {
    boolean result = false;
    
    // FIXME how would this work with CDO?
    
    if (resource_p == null)
      return result;
    
    IResource markerResource = marker_p.getResource();
    
    if (markerResource != null){
      // this would be the usual case if we created markers with
      // resource.createMarker(), but see LightMarkerRegistry.
      MarkerResourceVisitor vis = new MarkerResourceVisitor(markerResource);
      try {
        resource_p.accept(vis);
      } catch (CoreException e) {
        MarkerViewPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, MarkerViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
      }
      result = vis.getResult();
    }
// FIXME is this code really necessary ? if it is, find another way to do that (not compliant with multiple editing domains)
//    else {
//      // ... but we had to roll our own 
//      // 
//      // the custom solution is to find all EMF Resource objects that have a platform resource uri 
//      // that shares a common prefix with the current selection path and return true if
//      // the marker's affected eobjects are contained in one of those resources
//      out: 
//        for (Resource res : LegacyMDEAdapterFactory.getEditingDomain().getResourceSet().getResources()){
//          URI rsURI = res.getURI();
//          if (rsURI.isPlatformResource()){
//
//            String prefix = resource_p.getFullPath().toString();
//
//            // make sure to filter markers for project /hello, if project /helloworld is selected
//            if (!(resource_p instanceof IFile)){
//              prefix += "/"; //$NON-NLS-1$
//            }
//
//            // now check if the marker contains an EObject that's persisted in this EMF resource
//            if (rsURI.toPlatformString(true).startsWith(prefix)){
//              List<EObject> affected = MarkerViewHelper.getModelElementsFromMarker(marker_p);
//              for (EObject affectedObject : affected){
//                if (affectedObject.eResource() == res){
//                  result = true;
//                  break out;
//                }
//              }
//            }
//          }
//        }
//    }
    return result;
  }
     
  
  // helper to find markers on active selection
  private class MarkerResourceVisitor implements IResourceVisitor {

    public IResource target; // find this resource
    private boolean found = false;

    public MarkerResourceVisitor(IResource target_p){
      target = target_p;
    }
    
    public boolean visit(IResource resource_p) throws CoreException {
        if (resource_p == target){
          found = true;
          return false;
        } 
        return true;
    }
    
    public boolean getResult(){
      return found;
    }
  }
  
}

