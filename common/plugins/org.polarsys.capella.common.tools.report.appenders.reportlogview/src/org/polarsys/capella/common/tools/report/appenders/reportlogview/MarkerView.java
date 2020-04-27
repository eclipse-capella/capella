/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IStateListener;
import org.eclipse.core.commands.State;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.OpenAndLinkWithEditorHelper;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RadioState;
import org.eclipse.ui.part.IShowInSource;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.part.ViewPart;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * A temporary holder for the new Information view.
 * 
 *
 */
public class MarkerView extends ViewPart {

  /* FIXME this is only used by log view appender.. */
  public static final String MARKER_NUMBER = "idNumber"; //$NON-NLS-1$
  
  /* id for the 'group by' command */
  public static final String GROUP_COMMAND = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview.group"; //$NON-NLS-1$
  
  public static final String VIEW_ID = "org.polarsys.capella.common.tools.report.appenders.reportlogview.logview"; //$NON-NLS-1$
  
  /* FIXME this is only used by log view appender */
  public static final String MARKER_ID = VIEW_ID;

  private static final String CAPELLA_PROJECT_EXPLORER_ID = "capella.project.explorer";
  
  private MarkerViewHelper helper;
  private MarkerViewColumns columns;
  private AbstractMarkerViewContentProvider contentProvider;
  private IResourceChangeListener resourceListener;
  private TreeViewer viewer;
  private MarkerViewFilter filter;
  
  private IMarkerSource lightMarkers;
  private IMarkerSource resourceMarkers;
  
  /* updates selection on our filter */
  private ISelectionListener selectionListener;
  
  /* installs new flavours */
  private IStateListener flavourStateListener;
  
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void createPartControl(Composite parent) {
 
    
    
    // A custom tree to workaround https://bugs.eclipse.org/bugs/show_bug.cgi?id=259141
    Tree tree = new MarkerViewTree(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL | SWT.MULTI );
    
    tree.setHeaderVisible(true);
    tree.setLinesVisible(true);
    viewer = new TreeViewer(tree);
    viewer.setUseHashlookup(true);
    ColumnViewerToolTipSupport.enableFor(viewer);

    createContextMenu();
    hookSelectionListeners();
    addLinkWithEditorSupport();
    
    lightMarkers = LightMarkerRegistry.getInstance();
    helper = new MarkerViewHelper(lightMarkers, VIEW_ID);
    
    columns = new MarkerViewColumns(viewer);
    filter = createFilter(viewer);
    viewer.setFilters( new ViewerFilter[] { filter });
    
    // restore flavour, which is fancy way to say: set our content provider
    ICommandService commandService = (ICommandService) getSite().getService(ICommandService.class);
    Command flavourCommand = commandService.getCommand(GROUP_COMMAND);
    State flavourState = flavourCommand.getState(RadioState.STATE_ID);
    String value = flavourState.getValue().toString();
    Flavour flavour = Flavour.valueOf(value);
    setFlavour(flavour);

    hookFlavourStateListener(flavourState);

    
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    viewer.setInput(workspace.getRoot());
    
  }
  
  /**
   * Inspired from org.eclipse.ui.internal.views.markers.ExtendedMarkersView.addLinkWithEditorSupport
   */
  private void addLinkWithEditorSupport() {
    new OpenAndLinkWithEditorHelper(viewer) {
      @Override
      protected void activate(ISelection selection) {
        final int currentMode = OpenStrategy.getOpenMethod();
        try {
          OpenStrategy.setOpenMethod(OpenStrategy.DOUBLE_CLICK);
          openSelectedMarkers(selection);
        } finally {
          OpenStrategy.setOpenMethod(currentMode);
        }
      }

      @Override
      protected void linkToEditor(ISelection selection) {
        // Not supported by this part
      }

      @Override
      protected void open(ISelection selection, boolean activate) {
        openSelectedMarkers(selection);
      }
    };
  }

  protected void openSelectedMarkers(ISelection selection ) {
    IShowInSource source = getAdapter(IShowInSource.class);
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      IViewPart part = window.getActivePage().findView(CAPELLA_PROJECT_EXPLORER_ID); //$NON-NLS-1$
      if (part != null) {
        IShowInTarget showInTarget = (IShowInTarget) part.getAdapter(IShowInTarget.class);
        showInTarget.show(new ShowInContext(null, source.getShowInContext().getSelection()));
      }
    }
  }

  private void hookFlavourStateListener(State state) {
    // install a listener on the state to switch flavours when needed
    flavourStateListener = new IStateListener(){
      @SuppressWarnings("synthetic-access")
      public void handleStateChange(State state, Object oldValue) {
        setFlavour(Flavour.valueOf(state.getValue().toString()));
      }
    };
    state.addListener(flavourStateListener);
  }


  protected void hookSelectionListeners(){
    selectionListener = new ISelectionListener(){
      @SuppressWarnings("synthetic-access")
      public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part != MarkerView.this){
          filter.setSelection(selection);
        }
      }
    };
    getSite().getPage().addSelectionListener(selectionListener);
    getSite().setSelectionProvider(viewer);
  }
  
  protected void createContextMenu(){
      MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
      menuMgr.setRemoveAllWhenShown(true);
      menuMgr.addMenuListener(new IMenuListener() {
      public void menuAboutToShow(IMenuManager m) {
        MarkerView.this.fillContextMenu(m);
      }
      });
      Menu menu = menuMgr.createContextMenu(viewer.getControl());
      viewer.getControl().setMenu(menu);
      getSite().registerContextMenu(menuMgr, viewer);
  }
  
  
  protected void fillContextMenu(IMenuManager m){
    m.add(new Separator("navigation")); //$NON-NLS-1$
    m.add(new Separator("use")); //$NON-NLS-1$
    m.add(new Separator("settings")); //$NON-NLS-1$
    m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void setFocus() {
     viewer.getTree().setFocus();
  }

  @Override
  public void dispose(){
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    if (resourceListener != null && workspace != null){
      workspace.removeResourceChangeListener(resourceListener);
    }
    if (lightMarkers != null && contentProvider != null){
      lightMarkers.removeListener(contentProvider);
    }
    if (selectionListener != null){
      getSite().getPage().removeSelectionListener(selectionListener);
    }
    if (flavourStateListener != null){
      ICommandService commandService = (ICommandService) getSite().getService(ICommandService.class);
      Command command = commandService.getCommand(GROUP_COMMAND);
      State state = command.getState(RadioState.STATE_ID);
      state.removeListener(flavourStateListener);
    }
  }

  
  public TreeViewer getViewer(){
    return viewer;
  }
  
  // If the provider changes, also update the viewer columns.
  // Different providers favor different columns and alignment
  private void hookProvider(AbstractMarkerViewContentProvider newProvider){
    try {
      viewer.getTree().setRedraw(false);

      // cleanup old listeners
      if (contentProvider != null){
        lightMarkers.removeListener(contentProvider);
      } 

      if (resourceMarkers != null){
        ResourcesPlugin.getWorkspace().removeResourceChangeListener((IResourceChangeListener) resourceMarkers);
      }

      // cannot preserve selection when switching flavour
      viewer.setSelection(StructuredSelection.EMPTY);

      // each flavour can have a custom set of tree columns
      columns.update(newProvider);

      viewer.setContentProvider(newProvider);

      // hook into marker source
      lightMarkers.addListener(newProvider);

      // hide markers, show intermediate nodes
      expandToDefault();
      
      contentProvider = newProvider;
    } finally {
      viewer.getTree().setRedraw(true);      
    }
  }

  /** 
   * @param enabled
   */
  public void setAutomaticRefresh(boolean enabled){
    if (enabled){
      contentProvider.setViewerRefresh(getDefaultRefresh());
    } else {
      contentProvider.setViewerRefresh(null);
    }
  }
  
  /**
   * Whether we automatically refresh the viewer on content change.
   * @return
   */
  public boolean isAutomaticRefresh(){
    return contentProvider.getViewerRefresh() != null;
  }
  
  
  
  /**
   * Groups elements in the marker view according to the
   * group state passed as the parameter. Valid values
   * can be looked up in plugin.xml, under the 'group by'
   * menu contribution.
   * @param flavour
   */
  private void setFlavour(Flavour flavour) {
    if (flavour == Flavour.RULE){
      hookProvider(new RuleIdContentProvider(viewer, helper, getCurrentRefresh()));
    } else if (flavour == Flavour.CATEGORY){
      hookProvider(new CategoryContentProvider(viewer, helper, getCurrentRefresh()));
    } else if (flavour == Flavour.SEVERITY){
      hookProvider(new SeverityContentProvider(viewer, helper, getCurrentRefresh()));
    } else if (flavour == Flavour.NONE){
      hookProvider(new CanonicalContentProvider(viewer, helper, getCurrentRefresh()));
    }
  }

  public enum Flavour {
    CATEGORY,
    SEVERITY,
    NONE,
    RULE;
  }
  

  /**
   * Get the currently visible markers in form of a html document
   * @return the html value
   */
  public String getHtml() {
    
    StringBuilder res = new StringBuilder();
    res.append("<html> \n"); //$NON-NLS-1$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<title>Problems saved on " + new java.util.Date() + "</title> \n"); //$NON-NLS-1$ //$NON-NLS-2$
    res.append("<head> \n"); //$NON-NLS-1$
    res.append("<body> \n"); //$NON-NLS-1$
    res.append("<table border=\"1\"> \n"); //$NON-NLS-1$
    
    // headers
    res.append("<tr> \n"); //$NON-NLS-1$
    int[] columnOrder = viewer.getTree().getColumnOrder();
    for (int i = 0; i < columnOrder.length; i++){
      TreeColumn c = viewer.getTree().getColumn(columnOrder[i]);
      res.append("<th> \n"); //$NON-NLS-1$
      res.append(c.getText()); 
      res.append("</th> \n"); //$NON-NLS-1$
    }
    res.append("</tr> \n"); //$NON-NLS-1$
    
    // use our canonical content provider to get all markers
    // and the viewer label providers to fill the table
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    CanonicalContentProvider provider = new CanonicalContentProvider(viewer, helper, null);
    provider.inputChanged(null, null, root);
    Object[] elements = provider.getElements(root);
    viewer.getComparator().sort(null, elements);
    
    
    // elements
    for (Object e : elements){
      boolean filtered = false;
      for (ViewerFilter f : viewer.getFilters()){
        if (!f.select(viewer, root, e)){
          filtered = true;
        }
      }
      if (filtered){
        continue;
      }
      res.append("<tr> \n"); //$NON-NLS-1$
      for (int i = 0; i < columnOrder.length; i++){
        res.append("<td> \n"); //$NON-NLS-1$
        CellLabelProvider label = viewer.getLabelProvider(columnOrder[i]);
        if (label instanceof ColumnLabelProvider){
          String text = ((ColumnLabelProvider) label).getText(e);
          if (text == null){
            text = ICommonConstants.EMPTY_STRING;
          }
          res.append(StringEscapeUtils.escapeHtml(text)); 
          res.append("\n"); //$NON-NLS-1$
        }
        res.append("</td> \n"); //$NON-NLS-1$
      }
      res.append("</tr> \n"); //$NON-NLS-1$
  
    }
    return res.toString();
  }


  /**
   * The ViewerFilter to use in the viewer.
   * @return
   */
  protected MarkerViewFilter createFilter(Viewer v) {
    return new MarkerViewFilter(v);
  }


  /**
   * Delete all markers that our content provider knows about.
   */
  public void clear() {
    try {
      getViewer().getTree().setRedraw(false);
      getViewer().setSelection(StructuredSelection.EMPTY);
      IViewerRefresh oldRefresh = contentProvider.getViewerRefresh();
      contentProvider.setViewerRefresh(null);
      contentProvider.clear();
      getViewer().refresh();
      contentProvider.setViewerRefresh(oldRefresh);
    } finally {      
      viewer.getTree().setRedraw(true);
    }
  }

  /**
   * @return this views IMarkerSource marker sources
   */
  public List<IMarkerSource> getMarkerSources() {
    return Arrays.asList(new IMarkerSource[] {lightMarkers });
  }


  /**
   * @return the current selection represented as text
   */
  public String getSelectionAsText() {
    StringBuilder builder = new StringBuilder();
    String result = null;
    ISelection selection = getViewer().getSelection();
    if (selection instanceof IStructuredSelection){
      Object[] elements = ((IStructuredSelection) selection).toArray();
      int[] columnOrder = viewer.getTree().getColumnOrder();
      for (int ele = 0; ele < elements.length; ele++){
        Object element = elements[ele];
        for (int col = 0; col < columnOrder.length; col++){
          CellLabelProvider label = viewer.getLabelProvider(columnOrder[col]);
          if (label instanceof ColumnLabelProvider){
            builder.append(((ColumnLabelProvider) label).getText(element));
            if (col < columnOrder.length - 1){
              builder.append(" | "); //$NON-NLS-1$
            }
          }
          if (ele < elements.length - 1){
            builder.append(ICommonConstants.LINE_SEPARATOR);
          }
        }
      }
    }
    if (builder.length() > 0){
      result = builder.toString();
    } 
    return result;
  }
  
  private IViewerRefresh getDefaultRefresh(){
    return new ViewerRefresh(this, 100, TimeUnit.MILLISECONDS);
  }
  
  private IViewerRefresh getCurrentRefresh(){
    IViewerRefresh refresh = null;
    if (contentProvider != null){
      refresh = contentProvider.getViewerRefresh();
    } else {
      refresh = getDefaultRefresh();
    }
    return refresh;
  }

  /**
   * Configure the viewer tree, so that all nodes but the leafs are visible.
   * If the keepMarkers parameter is set to true, already expanded 
   * marker parents are not collapsed. Set the parameter to false to collapse
   * already expanded marker parents.
   * 
   * @param keepMarkers - whether already expanded marker parents should stay expanded
   */
  public void expandToDefault() {
    ITreeContentProvider provider = (ITreeContentProvider) viewer.getContentProvider();
    Set<Object> expanded = new HashSet<Object>();
    for (IMarkerSource source : getMarkerSources()){
      for (IMarker marker : source.getMarkers()){
        Object parent = provider.getParent(marker);
        if (parent != null && parent != viewer.getInput()){
          Object grandparent = provider.getParent(parent);
          while (grandparent != null && grandparent != viewer.getInput()){
            expanded.add(grandparent);
            grandparent = provider.getParent(grandparent); 
          }
        }
      }
    }
    
    Object input = viewer.getInput();
    if (input != null){
      expanded.add(viewer.getInput());
    }
    viewer.setExpandedElements(expanded.toArray());
  }
  
}
