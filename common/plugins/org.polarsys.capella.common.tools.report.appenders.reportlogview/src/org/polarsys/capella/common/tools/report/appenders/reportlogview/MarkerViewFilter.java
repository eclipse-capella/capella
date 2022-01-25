/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
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
import org.osgi.framework.FrameworkUtil;

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
  
  
  public MarkerViewFilter(Viewer viewer){
    this.viewer = viewer;
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
      public void handleStateChange(State state, Object oldValue) {
        SelectionFilter filter = SelectionFilter.valueOf(state.getValue().toString());
        if (filter != selectionFilter){
          selectionFilter = filter;
          updateViewer(state, this, viewer);
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
      state.addListener(new IStateListener(){
        @SuppressWarnings("synthetic-access")
        public void handleStateChange(State state, Object oldValue) {
          SeverityLevel level = severityLevelMap.get(commandId);
          if ((Boolean) state.getValue()){
            if (activeLevels.add(level)){
              updateViewer(state, this, viewer);
            } 
          } else {
            if (activeLevels.remove(level)){
              updateViewer(state, this, viewer);
            }
          }
        }

      });
    }
    
  }

  /**
   * Update the viewer for the given state listener
   */
  private void updateViewer(State state, IStateListener listener, Viewer viewer) {
    if (!viewer.getControl().isDisposed()) {
      viewer.refresh();
    } else {
      state.removeListener(listener);
    }
  }
  
  public enum SelectionFilter {
    PROJECT,
    SELECTION,
    ALL;
  }
  
  void setSelectionFilter(SelectionFilter filter){
    if (filter != selectionFilter){
      selectionFilter = filter;
      viewer.refresh();
    }
  }
  
  /**
   * Update the selection. Usually you want to call viewer.refresh()
   * after setting the new selection via this method.
   * @param selection
   */
  void setSelection(ISelection selection){
    boolean refreshViewer = false;
    
    Object first = null;
    if (selection instanceof IStructuredSelection){
      first = ((IStructuredSelection) selection).getFirstElement();
    }
    
    if (first != selectedElement){ 
      if (selectionFilter == SelectionFilter.SELECTION){
        refreshViewer = true;
      }
      IProject newProject = getProject(selection);
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
   * @param selection
   * @return
   */
  protected IProject getProject(ISelection selection) {
    IProject result = null;
    if (selection instanceof IStructuredSelection){
      Object first = ((IStructuredSelection) selection).getFirstElement();
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
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    boolean result = true;
    if (element instanceof IMarker){

      // severity level
      SeverityLevel level = SeverityLevel.getLevel((IMarker) element);
      if (level != null){
        result = activeLevels.contains(level);
      }

      switch (selectionFilter){
        case ALL: break;
        case PROJECT:   result &= select(project,         viewer, parentElement, (IMarker) element);   break;
        case SELECTION: result &= select(selectedElement, viewer, parentElement, (IMarker) element); break;
      }
      
    } else {
      // if it's not a marker:
      // at least one child is visible => element visible
      // TODO this recurses, and possibly invokes the filter more than once for a single element => memorization
      TreeViewer client = (TreeViewer) viewer;
      ITreeContentProvider provider = (ITreeContentProvider) client.getContentProvider();
      Object[] children = provider.getChildren(element);
      result = false;
      for (Object o : children){
        if (select(viewer, element, o)){
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
   * @param selection the element that is selected.
   * @return whether the marker should show or not.
   */
  protected boolean select(Object selection, Viewer viewer, Object parent, IMarker marker) {
    boolean result = false;
    if (selection instanceof IResource){
      result = select((IResource) selection, viewer, parent, marker);
    } else if (selection instanceof EObject){
      List<EObject> affected = MarkerViewHelper.getModelElementsFromMarker(marker);
      if (affected.size() > 0){
        EObject firstAffected = affected.get(0);
        if (EcoreUtil.isAncestor((EObject) selection, firstAffected)){
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
   * @param resource the selected resource
   * @param viewer the viewer
   * @param parent element parent
   * @param marker the marker
   * @return whether the marker should show or not.
   * @see ViewerFilter.select()
   */
  private boolean select(IResource resource, Viewer viewer, Object parent, IMarker marker) {
    boolean result = false;
    
    // FIXME how would this work with CDO?
    
    if (resource == null)
      return result;
    
    IResource markerResource = marker.getResource();
    
    if (markerResource != null){
      // this would be the usual case if we created markers with
      // resource.createMarker(), but see LightMarkerRegistry.
      MarkerResourceVisitor vis = new MarkerResourceVisitor(markerResource);
      try {
        resource.accept(vis);
      } catch (CoreException e) {
        Platform.getLog(MarkerViewPlugin.class).log(new Status(IStatus.ERROR, FrameworkUtil.getBundle(MarkerViewPlugin.class).getSymbolicName(), e.getLocalizedMessage(), e));
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
//            String prefix = resource.getFullPath().toString();
//
//            // make sure to filter markers for project /hello, if project /helloworld is selected
//            if (!(resource instanceof IFile)){
//              prefix += "/"; //$NON-NLS-1$
//            }
//
//            // now check if the marker contains an EObject that's persisted in this EMF resource
//            if (rsURI.toPlatformString(true).startsWith(prefix)){
//              List<EObject> affected = MarkerViewHelper.getModelElementsFromMarker(marker);
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

    public MarkerResourceVisitor(IResource target){
      this.target = target;
    }
    
    public boolean visit(IResource resource) throws CoreException {
        if (resource == target){
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

