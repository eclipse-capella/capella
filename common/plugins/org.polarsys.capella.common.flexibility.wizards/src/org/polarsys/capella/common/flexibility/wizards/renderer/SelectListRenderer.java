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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ColorRegistry;
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
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.flexibility.wizards.ui.FlexibilityColors;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ToolbarPopulator;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataViewerLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.ListData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaFilteredTree;

/**
 * A renderer for an elements list which content according to filter
 */
public class SelectListRenderer extends AbstractRenderer {

  private TreeAndListViewer viewer;

  protected ToolBarManager toolbarManager;
  private ToolbarPopulator toolbarPopulator = null;
  private ToolbarPopulator popupPopulator = null;
  protected MenuManager popupManager;
  protected IStructuredSelection selection;
  private ILabelProvider labelProvider;
  private DataViewerLabelProvider dataLabelProvider;


  /**
   * @return the viewer
   */
  protected TreeAndListViewer getViewer() {
    return viewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void performRender(Composite parent, IRendererContext rendererContext) {
    labelProvider = createLabelProvider(rendererContext);

    Composite prt = new Composite(parent, SWT.NONE);
    prt.setLayout(createMainLayout());
    prt.setLayoutData(createMainLayoutData());

    createTreeViewer(prt, rendererContext);

    // Populate toolbar and set selection to getViewer()
    IEclipseContext context = PlatformUI.getWorkbench().getService(IEclipseContext.class);
    if (!getToolbarLocation().isEmpty()) {
      toolbarPopulator =
          new ToolbarPopulator(toolbarManager, getToolbarLocation(), rendererContext, this, getViewer().getClientViewer(), PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow());
      ContextInjectionFactory.inject(toolbarPopulator, context);
      toolbarPopulator.populate();
    }

    // Populate contextMenu and set selection to getViewer()
    // As we use the TreeAndListViewer, it is registered in createTreeViewer()
    
    if (dataLabelProvider != null) {
      dataLabelProvider.setViewer(getViewer().getClientViewer());
    }
  }

  protected void initializeControls(final Composite parent, final IRendererContext context) {

    if (!getToolbarLocation().isEmpty()) {
      ToolBar toolbar = new ToolBar(parent, SWT.VERTICAL);
      toolbar.setLayout(createLayout());
      toolbar.setLayoutData(createToolbarLayoutData());
      toolbarManager = new ToolBarManager(toolbar);
    }

    if (!getPopupLocation().isEmpty()) {
      popupManager = new MenuManager();
    }
  }

  protected String getPopupLocation() {
    return "popup";
  }

  protected String getToolbarLocation() {
    return "";
  }

  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext) {
    dataLabelProvider = new DataViewerLabelProvider(rendererContext.getLabelProvider()) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean isValid(Object element) {
        return super.isValid(element) && isValidElement(element, rendererContext);
      }

    };

    return new DefaultLabelProvider(dataLabelProvider) {
      @Override
      public Color getBackground(Object element) {
        // Do nothing.
        IStatus status = getStatus(element, rendererContext);
        if ((status == null) || status.isOK()) {
          return null;
        }

        ColorRegistry colors = FlexibilityColors.getColorRegistry();

        if (status.matches(IStatus.INFO)) {
          return colors.get(FlexibilityColors.BG_INFO);
        } else if (status.matches(IStatus.WARNING)) {
          return colors.get(FlexibilityColors.BG_WARNING);
        } else if (status.matches(IStatus.ERROR)) {
          return colors.get(FlexibilityColors.BG_ERROR);
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
  
  protected void createTreeViewer(Composite parent, final IRendererContext context) {
    int style = SWT.NONE;

    // Create a TreeAndListViewer.
    viewer = new TreeAndListViewer(parent, isMultipleSelection(), style) {
      
      @Override
      public String getContextMenuLocation() {
        return getPopupLocation();
      }
      
      /**
       * Overridden to set the viewer in the label provider at creation time.
       * @see org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer#doClientViewer(org.eclipse.swt.widgets.Composite)
       */
      @Override
      protected TreeViewer doClientViewer(Composite parent) {

        // Create a filtered tree viewer that expands all systematically.
        
        CapellaFilteredTree filteredTree = new CapellaFilteredTree(parent, getTreeStyle(), getFilter()) {
          @Override
          protected void createControl(Composite parent, int treeStyle) {
            // We need to override this method to provide room for the vertical toolbar on the right side
            // Make the main composite's layout having 2 columns.
            GridLayout layout = new GridLayout(2, false);
            layout.marginHeight = 0;
            layout.marginWidth = 0;
            setLayout(layout);
            setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

            // First column is for the filtered tree
            Composite filteredTreeComposite = new Composite(this, SWT.NONE);
            filteredTreeComposite.setLayout(new GridLayout());
            filteredTreeComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));

            if (showFilterControls) {
              filterComposite = new Composite(filteredTreeComposite, SWT.BORDER);
              filterComposite.setBackground(getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
              GridLayout filterLayout = new GridLayout(2, false);
              filterLayout.marginHeight = 0;
              filterLayout.marginWidth = 0;
              filterComposite.setLayout(filterLayout);
              filterComposite.setFont(parent.getFont());

              createFilterControls(filterComposite);
              filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
            }

            treeComposite = new Composite(filteredTreeComposite, SWT.NONE);
            GridLayout treeCompositeLayout = new GridLayout();
            treeCompositeLayout.marginHeight = 0;
            treeCompositeLayout.marginWidth = 0;
            treeComposite.setLayout(treeCompositeLayout);
            GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
            treeComposite.setLayoutData(data);
            createTreeControl(treeComposite, treeStyle);

            // The second column is for the vertical toolbar, which will be contributed by many actions, for instance in RecRpl dialog
            initializeControls(this, context);
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

    viewer.getControl().setLayout(createLayout());
    viewer.getControl().setLayoutData(createLayoutData());

    final TreeViewer clientViewer = viewer.getClientViewer();
    // Add a selection listener to update widgets according to the selection.
    ISelectionChangedListener viewerSelectionChangedListener = new ISelectionChangedListener() {
      /**
       * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
       */
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        // Handle the selection itself.
        selection = (IStructuredSelection) event.getSelection();
        selectionChange(selection, context);
      }
    };

    clientViewer.addSelectionChangedListener(viewerSelectionChangedListener);
    // Add a double click listener to select and close the dialog.
    IDoubleClickListener viewerDoubleClickListener = new IDoubleClickListener() {
      /**
       * @see IDoubleClickListener#doubleClick(DoubleClickEvent)
       */
      @Override
      public void doubleClick(DoubleClickEvent event) {
        ISelection selection = event.getSelection();
        if (isValidDoubleClick(event, selection, context)) {
          doubleClicked(selection, context);
        }
      }

    };
    clientViewer.addDoubleClickListener(viewerDoubleClickListener);

    clientViewer.setContentProvider(createContentProvider(context));
    clientViewer.setLabelProvider(labelProvider);
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

    if ((viewer != null) && (viewer.getClientViewer().getContentProvider() != null)) {
      if (reloadInputRequired(input, viewer.getClientViewer().getInput())) {
        setInput(input, propertyContext);
      }
    }
  }

  /**
   * @param input
   */
  private void setInput(Object input, IRendererContext propertyContext) {
    viewer.setInput(input);
    IContentProvider provider = viewer.getClientViewer().getContentProvider();
    if (provider != null) {
      provider.inputChanged(viewer.getClientViewer(), null, input);
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

  @Override
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
    return selection;
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

    if (toolbarPopulator != null) {
      toolbarPopulator.dispose();
      toolbarPopulator = null;
    }
    if (popupPopulator != null) {
      popupPopulator.dispose();
      popupPopulator = null;
    }
    if (popupManager != null) {
      popupManager.dispose();
      popupManager = null;
    }
  }
}
