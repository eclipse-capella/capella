/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.Activator;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ToolbarPopulator;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataViewerLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter;

/**
 * A renderer for an elements list which content according to filter
 */
public class SelectListRenderer extends AbstractRenderer {

  private TreeAndListViewer _viewer;

  protected ToolBarManager _toolbarManager;

  protected MenuManager _popupManager;

  protected IStructuredSelection _selection;

  /**
   * @return the viewer
   */
  protected TreeAndListViewer getViewer() {
    return _viewer;
  }

  private ILabelProvider _labelProvider;

  private DataViewerLabelProvider _dataLabelProvider;

  private ToolbarPopulator _toolbarPopulator = null;
  private ToolbarPopulator _popupPopulator = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public void performRender(Composite parent_p, IRendererContext rendererContext_p) {
    _labelProvider = createLabelProvider(rendererContext_p);

    Composite parent = new Composite(parent_p, SWT.NONE);
    parent.setLayout(createMainLayout());
    parent.setLayoutData(createMainLayoutData());

    createTreeViewer(parent, rendererContext_p);

    // Populate toolbar and set selection to getViewer()
    if (!getToolbarLocation().isEmpty()) {
      _toolbarPopulator =
          new ToolbarPopulator(_toolbarManager, getToolbarLocation(), rendererContext_p, this, getViewer().getClientViewer(), PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow());
      _toolbarPopulator.populate();
    }

    // Populate contextMenu and set selection to getViewer()
    if (!getPopupLocation().isEmpty()) {
      _popupPopulator =
          new ToolbarPopulator(_popupManager, getPopupLocation(), rendererContext_p, this, getViewer().getClientViewer(), PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow());
      _popupPopulator.populate();

      // Register the contextMenu on the view
      Menu menu = _popupManager.createContextMenu(getViewer().getClientViewer().getControl());
      getViewer().getClientViewer().getControl().setMenu(menu);
    }

    if (_dataLabelProvider != null) {
      _dataLabelProvider.setViewer(getViewer().getClientViewer());
    }

  }

  protected void initializeControls(final Composite parent_p, final IRendererContext context_p) {

    if (!getToolbarLocation().isEmpty()) {
      ToolBar toolbar = new ToolBar(parent_p, SWT.VERTICAL);
      toolbar.setLayout(createLayout());
      toolbar.setLayoutData(createToolbarLayoutData());
      _toolbarManager = new ToolBarManager(toolbar);
    }

    if (!getPopupLocation().isEmpty()) {
      _popupManager = new MenuManager();
    }

  }

  protected String getPopupLocation() {
    return "";
  }

  protected String getToolbarLocation() {
    return "";
  }

  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext_p) {
    _dataLabelProvider = new DataViewerLabelProvider(rendererContext_p.getLabelProvider()) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean isValid(Object element_p) {
        return super.isValid(element_p) && isValidElement(element_p, rendererContext_p);
      }

    };

    return new DefaultLabelProvider(_dataLabelProvider) {
      @Override
      public Color getBackground(Object element_p) {
        // Do nothing.
        IStatus status = getStatus(element_p, rendererContext_p);
        if ((status == null) || status.isOK()) {
          return null;

        } else if (status.matches(IStatus.INFO)) {
          return new Color(Display.getDefault(), 217, 255, 209);

        } else if (status.matches(IStatus.WARNING)) {
          return new Color(Display.getDefault(), 254, 241, 137);

        } else if (status.matches(IStatus.ERROR)) {
          return new Color(Display.getDefault(), 252, 222, 222);
        }
        return null;
      }
    };
  }

  /**
   * @param element_p
   * @return
   */
  protected boolean isValidElement(Object element_p, IRendererContext rendererContext_p) {
    return true;
  }

  /**
   * @param element_p
   * @param rendererContext_p
   * @return
   */
  protected IStatus getStatus(Object element_p, IRendererContext rendererContext_p) {
    return Status.OK_STATUS;
  }

  /**
   * @return
   */
  protected boolean isFilterBar() {
    return true;
  }

  protected boolean isMultipleSelection() {
    return true;
  }

  protected class CapellaFilteredTree extends FilteredTree {

    IRendererContext _context;

    /**
     * Constructor.
     * @param parent_p
     */
    protected CapellaFilteredTree(Composite parent_p) {
      super(parent_p);
    }

    /**
     * Constructor.
     * @param parent_p
     * @param treeStyle_p
     * @param filter_p
     */
    public CapellaFilteredTree(Composite parent_p, int treeStyle_p, PatternFilter filter_p, IRendererContext context_p) {
      super(parent_p);
      _context = context_p;
      init(treeStyle_p, filter_p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void clearText() {
      setFilterText(getInitialText());
      textChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createClearText(Composite parent_p) {
      filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
      filterToolBar.createControl(parent_p);
      createSearchDescriptionButton(parent_p);
      super.createClearText(parent_p);
    }

    @Override
    protected Composite createFilterGroup(Composite parent_p) {
      filterComposite = new Composite(parent_p, SWT.NONE);
      return filterComposite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createFilterControls(Composite parent_p) {
      return super.createFilterControls(parent_p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Label createMessageArea(Composite parent_p) {
      return null;
    }

    /**
     * Create the button that triggers search in description.
     * @param parent_p parent <code>Composite</code> of toolbar button
     */
    protected void createSearchDescriptionButton(Composite parent_p) {
      IAction searchInDescriptionAction = new Action(ICommonConstants.EMPTY_STRING, IAction.AS_PUSH_BUTTON) {
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void run() {
          String initText = getInitialText();
          String filterString = getFilterString();
          if ((initText == null) || !initText.equals(filterString)) {
            searchClicked(filterString, _context);
          }
          textChanged();
        }
      };
      searchInDescriptionAction.setToolTipText("Search");
      searchInDescriptionAction.setImageDescriptor(Activator.getDefault().getImageDescriptor("full/etool16/search.gif"));
      filterToolBar.add(searchInDescriptionAction);
    }

    @Override
    protected void createControl(Composite parent_p, int treeStyle_p) {
      showFilterControls = isFilterBar();
      super.createControl(parent, treeStyle_p);
    }

    @Override
    protected Control createTreeControl(Composite parent_p, int style) {
      Composite parent = new Composite(parent_p, SWT.NONE);
      GridLayout layout = (GridLayout) createLayout();
      layout.numColumns = 2;
      parent.setLayout(layout);
      parent.setLayoutData(createLayoutData());

      Control tree = super.createTreeControl(parent, style);
      initializeControls(parent, _context);
      return tree;
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#init(int, org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter)
     */
    @Override
    protected void init(int treeStyle_p, PatternFilter filter_p) {
      // Disable auto filtering for usability.
      setAutoFiltering(false);

      super.init(treeStyle_p, filter_p);
      showFilterControls = isFilterBar();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateToolbar(boolean visible_p) {
      // Do nothing as we want to always see the toolbar to access search in description.
      IContributionItem[] items = filterToolBar.getItems();
      items[1].setVisible(visible_p);
      filterToolBar.update(true);
    }
  }

  protected void createTreeViewer(Composite parent_p, final IRendererContext context_p) {
    int style_p = SWT.NONE;

    // Create a TreeAndListViewer.
    _viewer = new TreeAndListViewer(parent_p, isMultipleSelection(), style_p) {
      /**
       * Overridden to set the viewer in the label provider at creation time.
       * @see org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected TreeViewer doClientViewer(Composite parent_p) {

        // Create a filtered tree viewer that expands all systematically.
        FilteredTree filteredTree = new CapellaFilteredTree(parent_p, getTreeStyle(), getFilter(), context_p) {

          /**
           * {@inheritDoc}
           */
          @Override
          protected TreeViewer doCreateTreeViewer(Composite parent_p, int style_p) {
            return super.doCreateTreeViewer(parent_p, style_p);
          }

          /**
           * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#handleTreeViewerExpansionWhenNoFilter(java.lang.Object[])
           */
          @Override
          protected void handleTreeViewerExpansionWhenNoFilter(Object[] expandedElements_p) {
            treeViewer.expandAll();
          }
        };
        TreeViewer viewer = filteredTree.getViewer();
        setExpandLevel(viewer);
        return viewer;
      }

      protected void setExpandLevel(TreeViewer viewer_p) {
        viewer_p.setAutoExpandLevel(getExpandLevel());
      }

    };

    _viewer.getControl().setLayout(createLayout());
    _viewer.getControl().setLayoutData(createLayoutData());

    final TreeViewer clientViewer = _viewer.getClientViewer();
    // Add a selection listener to update widgets according to the selection.
    ISelectionChangedListener viewerSelectionChangedListener = new ISelectionChangedListener() {
      /**
       * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        // Handle the selection itself.
        IStructuredSelection selection = (IStructuredSelection) event_p.getSelection();
        _selection = selection;
        selectionChange(selection, context_p);
      }

    };

    clientViewer.addSelectionChangedListener(viewerSelectionChangedListener);
    // Add a double click listener to select and close the dialog.
    IDoubleClickListener viewerDoubleClickListener = new IDoubleClickListener() {
      /**
       * @see IDoubleClickListener#doubleClick(DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event_p) {
        ISelection selection = event_p.getSelection();
        if (isValidDoubleClick(event_p, selection, context_p)) {
          doubleClicked(selection, context_p);
        }
      }

    };
    clientViewer.addDoubleClickListener(viewerDoubleClickListener);

    clientViewer.setContentProvider(createContentProvider(context_p));
    clientViewer.setLabelProvider(_labelProvider);
  }

  /**
   * @param context_p
   * @return
   */
  protected ISelection getInitialSelection(IRendererContext context_p) {
    return new StructuredSelection();
  }

  /**
   * @return
   */
  protected int getExpandLevel() {
    return 2;
  }

  protected Object createToolbarLayoutData() {
    GridData layoutData = new GridData(SWT.BEGINNING, GridData.FILL, false, true);
    return layoutData;
  }

  /**
   * @return
   */
  protected Object createLayoutData() {
    GridData layoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    return layoutData;
  }

  /**
   * @return
   */
  protected Layout createMainLayout() {
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    return layout;
  }

  protected Object createMainLayoutData() {
    GridData layoutData = new GridData(GridData.FILL, GridData.FILL, true, true);
    layoutData.minimumHeight = 300;
    return layoutData;
  }

  /**
   * @return
   */
  protected Layout createLayout() {
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    return layout;
  }

  /**
   * @param property_p
   * @param propertyContext_p
   */
  protected void reloadInput(IProperty property_p, IRendererContext propertyContext_p) {
    Object input = createInput(property_p, propertyContext_p);

    if ((_viewer != null) && (_viewer.getClientViewer().getContentProvider() != null)) {
      if (reloadInputRequired(input, _viewer.getClientViewer().getInput())) {
        setInput(input, propertyContext_p);
      }
    }
  }

  /**
   * @param input_p
   */
  private void setInput(Object input_p, IRendererContext propertyContext_p) {
    _viewer.setInput(input_p);
    IContentProvider provider = _viewer.getClientViewer().getContentProvider();
    if (provider != null) {
      provider.inputChanged(_viewer.getClientViewer(), null, input_p);
    }
  }

  /**
   * @param input_p
   * @param input2_p
   * @return
   */
  public boolean reloadInputRequired(Object input_p, Object input2_p) {
    return true;
  }

  protected void searchClicked(String pattern_p, IRendererContext context_p) {
    IProperty property = context_p.getProperty(this);
    reloadInput(property, context_p);
  }

  protected boolean isValidDoubleClick(DoubleClickEvent event_p, ISelection selection_p, IRendererContext context_p) {
    if (!((null != selection_p) && !selection_p.isEmpty() && (selection_p instanceof IStructuredSelection))) {
      return false;
    }
    IStructuredSelection selection = (IStructuredSelection) selection_p;
    Collection<Object> value = isMultipleSelection() ? selection.toList() : Collections.singletonList(selection.getFirstElement());
    for (Object val : value) {
      Object input = event_p.getViewer().getInput();
      if ((input instanceof AbstractData) && !(((AbstractData) input).isValid(val))) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param doubleClickedElement_p
   * @param context_p
   */
  protected void doubleClicked(ISelection doubleClickedElement_p, IRendererContext context_p) {
    // Nothing here
  }

  protected IContentProvider createContentProvider(IRendererContext rendererContext_p) {
    return new DataContentProvider();
  }

  protected Object createInput(IProperty property_p, IRendererContext context_p) {
    if (property_p instanceof IRestraintProperty) {
      Object value = ((IRestraintProperty) property_p).getChoiceValues(context_p.getPropertyContext());
      if ((value != null) && (value instanceof Collection)) {
        Collection<Object> dataa = (Collection) value;
        TreeData data = new TreeData(dataa, null);
        return data;
      }
    }
    return new ListData(Collections.emptyList(), null);
  }

  public void initialize(IProperty property_p, IRendererContext propertyContext_p) {
    Object value = propertyContext_p.getPropertyContext().getDefaultValue(property_p);
    updatedValue(property_p, propertyContext_p, value);
    reloadInput(property_p, propertyContext_p);
    selectionChange(new StructuredSelection(), propertyContext_p);
  }

  public void selectionChange(IStructuredSelection selection_p, IRendererContext context_p) {
    Object value = isMultipleSelection() ? selection_p.toList() : selection_p.getFirstElement();
    changeValue(context_p.getProperty(this), context_p, value);
  }

  public IStructuredSelection getSelection() {
    return _selection;
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    // Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context_p) {
    super.dispose(context_p);

    if (_toolbarPopulator != null) {
      _toolbarPopulator.dispose();
      _toolbarPopulator = null;
    }
    if (_popupPopulator != null) {
      _popupPopulator.dispose();
      _popupPopulator = null;
    }
    if (_popupManager != null) {
      _popupManager.dispose();
      _popupManager = null;
    }
  }

}
