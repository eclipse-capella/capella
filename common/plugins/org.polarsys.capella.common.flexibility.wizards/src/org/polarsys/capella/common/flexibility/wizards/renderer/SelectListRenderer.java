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
  public void performRender(Composite parent, IRendererContext rendererContext) {
    _labelProvider = createLabelProvider(rendererContext);

    Composite prt = new Composite(parent, SWT.NONE);
    prt.setLayout(createMainLayout());
    prt.setLayoutData(createMainLayoutData());

    createTreeViewer(prt, rendererContext);

    // Populate toolbar and set selection to getViewer()
    if (!getToolbarLocation().isEmpty()) {
      _toolbarPopulator =
          new ToolbarPopulator(_toolbarManager, getToolbarLocation(), rendererContext, this, getViewer().getClientViewer(), PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow());
      _toolbarPopulator.populate();
    }

    // Populate contextMenu and set selection to getViewer()
    if (!getPopupLocation().isEmpty()) {
      _popupPopulator =
          new ToolbarPopulator(_popupManager, getPopupLocation(), rendererContext, this, getViewer().getClientViewer(), PlatformUI.getWorkbench()
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

  protected void initializeControls(final Composite parent, final IRendererContext context) {

    if (!getToolbarLocation().isEmpty()) {
      ToolBar toolbar = new ToolBar(parent, SWT.VERTICAL);
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

  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext) {
    _dataLabelProvider = new DataViewerLabelProvider(rendererContext.getLabelProvider()) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean isValid(Object element) {
        return super.isValid(element) && isValidElement(element, rendererContext);
      }

    };

    return new DefaultLabelProvider(_dataLabelProvider) {
      @Override
      public Color getBackground(Object element) {
        // Do nothing.
        IStatus status = getStatus(element, rendererContext);
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
   * @param element
   * @return
   */
  protected boolean isValidElement(Object element, IRendererContext rendererContext) {
    return true;
  }

  /**
   * @param element
   * @param rendererContext
   * @return
   */
  protected IStatus getStatus(Object element, IRendererContext rendererContext) {
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
     * @param parent
     */
    protected CapellaFilteredTree(Composite parent) {
      super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param treeStyle
     * @param filter
     */
    public CapellaFilteredTree(Composite parent, int treeStyle, PatternFilter filter, IRendererContext context) {
      super(parent);
      _context = context;
      init(treeStyle, filter);
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
    protected void createClearText(Composite parent) {
      filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
      filterToolBar.createControl(parent);
      createSearchDescriptionButton(parent);
      super.createClearText(parent);
    }

    @Override
    protected Composite createFilterGroup(Composite parent) {
      filterComposite = new Composite(parent, SWT.NONE);
      return filterComposite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createFilterControls(Composite parent) {
      return super.createFilterControls(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Label createMessageArea(Composite parent) {
      return null;
    }

    /**
     * Create the button that triggers search in description.
     * @param parent parent <code>Composite</code> of toolbar button
     */
    protected void createSearchDescriptionButton(Composite parent) {
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
    protected void createControl(Composite parent, int treeStyle) {
      showFilterControls = isFilterBar();
      super.createControl(parent, treeStyle);
    }

    @Override
    protected Control createTreeControl(Composite parent, int style) {
      Composite prt = new Composite(parent, SWT.NONE);
      GridLayout layout = (GridLayout) createLayout();
      layout.numColumns = 2;
      prt.setLayout(layout);
      prt.setLayoutData(createLayoutData());

      Control tree = super.createTreeControl(prt, style);
      initializeControls(prt, _context);
      return tree;
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#init(int, org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter)
     */
    @Override
    protected void init(int treeStyle, PatternFilter filter) {
      // Disable auto filtering for usability.
      setAutoFiltering(false);

      super.init(treeStyle, filter);
      showFilterControls = isFilterBar();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateToolbar(boolean visible) {
      // Do nothing as we want to always see the toolbar to access search in description.
      IContributionItem[] items = filterToolBar.getItems();
      items[1].setVisible(visible);
      filterToolBar.update(true);
    }
  }

  protected void createTreeViewer(Composite parent, final IRendererContext context) {
    int style = SWT.NONE;

    // Create a TreeAndListViewer.
    _viewer = new TreeAndListViewer(parent, isMultipleSelection(), style) {
      /**
       * Overridden to set the viewer in the label provider at creation time.
       * @see org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      protected TreeViewer doClientViewer(Composite parent) {

        // Create a filtered tree viewer that expands all systematically.
        FilteredTree filteredTree = new CapellaFilteredTree(parent, getTreeStyle(), getFilter(), context) {

          /**
           * {@inheritDoc}
           */
          @Override
          protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
            return super.doCreateTreeViewer(parent, style);
          }

          /**
           * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#handleTreeViewerExpansionWhenNoFilter(java.lang.Object[])
           */
          @Override
          protected void handleTreeViewerExpansionWhenNoFilter(Object[] expandedElements) {
            treeViewer.expandAll();
          }
        };
        TreeViewer viewer = filteredTree.getViewer();
        setExpandLevel(viewer);
        return viewer;
      }

      protected void setExpandLevel(TreeViewer viewer) {
        viewer.setAutoExpandLevel(getExpandLevel());
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
      public void selectionChanged(SelectionChangedEvent event) {
        // Handle the selection itself.
        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        _selection = selection;
        selectionChange(selection, context);
      }

    };

    clientViewer.addSelectionChangedListener(viewerSelectionChangedListener);
    // Add a double click listener to select and close the dialog.
    IDoubleClickListener viewerDoubleClickListener = new IDoubleClickListener() {
      /**
       * @see IDoubleClickListener#doubleClick(DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event) {
        ISelection selection = event.getSelection();
        if (isValidDoubleClick(event, selection, context)) {
          doubleClicked(selection, context);
        }
      }

    };
    clientViewer.addDoubleClickListener(viewerDoubleClickListener);

    clientViewer.setContentProvider(createContentProvider(context));
    clientViewer.setLabelProvider(_labelProvider);
  }

  /**
   * @param context
   * @return
   */
  protected ISelection getInitialSelection(IRendererContext context) {
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
   * @param property
   * @param propertyContext
   */
  protected void reloadInput(IProperty property, IRendererContext propertyContext) {
    Object input = createInput(property, propertyContext);

    if ((_viewer != null) && (_viewer.getClientViewer().getContentProvider() != null)) {
      if (reloadInputRequired(input, _viewer.getClientViewer().getInput())) {
        setInput(input, propertyContext);
      }
    }
  }

  /**
   * @param input
   */
  private void setInput(Object input, IRendererContext propertyContext) {
    _viewer.setInput(input);
    IContentProvider provider = _viewer.getClientViewer().getContentProvider();
    if (provider != null) {
      provider.inputChanged(_viewer.getClientViewer(), null, input);
    }
  }

  /**
   * @param input
   * @param input2
   * @return
   */
  public boolean reloadInputRequired(Object input, Object input2) {
    return true;
  }

  protected void searchClicked(String pattern, IRendererContext context) {
    IProperty property = context.getProperty(this);
    reloadInput(property, context);
  }

  protected boolean isValidDoubleClick(DoubleClickEvent event, ISelection selection, IRendererContext context) {
    if (!((null != selection) && !selection.isEmpty() && (selection instanceof IStructuredSelection))) {
      return false;
    }
    IStructuredSelection sel = (IStructuredSelection) selection;
    Collection<Object> value = isMultipleSelection() ? sel.toList() : Collections.singletonList(sel.getFirstElement());
    for (Object val : value) {
      Object input = event.getViewer().getInput();
      if ((input instanceof AbstractData) && !(((AbstractData) input).isValid(val))) {
        return false;
      }
    }

    return true;
  }

  /**
   * @param doubleClickedElement
   * @param context
   */
  protected void doubleClicked(ISelection doubleClickedElement, IRendererContext context) {
    // Nothing here
  }

  protected IContentProvider createContentProvider(IRendererContext rendererContext) {
    return new DataContentProvider();
  }

  protected Object createInput(IProperty property, IRendererContext context) {
    if (property instanceof IRestraintProperty) {
      Object value = ((IRestraintProperty) property).getChoiceValues(context.getPropertyContext());
      if (value != null) {
        Collection<Object> dataa = (Collection) value;
        TreeData data = new TreeData(dataa, null);
        return data;
      }
    }
    return new ListData(Collections.emptyList(), null);
  }

  public void initialize(IProperty property, IRendererContext propertyContext) {
    Object value = propertyContext.getPropertyContext().getDefaultValue(property);
    updatedValue(property, propertyContext, value);
    reloadInput(property, propertyContext);
    selectionChange(new StructuredSelection(), propertyContext);
  }

  public void selectionChange(IStructuredSelection selection, IRendererContext context) {
    Object value = isMultipleSelection() ? selection.toList() : selection.getFirstElement();
    changeValue(context.getProperty(this), context, value);
  }

  public IStructuredSelection getSelection() {
    return _selection;
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext propertyContext, Object newValue) {
    // Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);

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
