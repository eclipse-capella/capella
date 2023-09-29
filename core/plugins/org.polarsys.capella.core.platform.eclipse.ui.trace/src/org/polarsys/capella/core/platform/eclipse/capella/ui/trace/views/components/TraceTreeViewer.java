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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components;

import java.util.EventObject;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.MDTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension.TraceExtensionManager;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceUtil;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.TraceTreeSelectionDialog;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.ViewEditPage;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelLabelProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelTreeContentProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.ElementLabelProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.IImageKeys;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.SourceElementContentProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.TargetElementContentProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.TypeElementFilter;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 * <code>TraceTreeViewer</code> render a composite containing a tree structure and a combo to filter
 */
public class TraceTreeViewer implements IDoubleClickListener {

  /** Type determining the content of the TraceTreeViewer */
  public static enum TraceType {
    SOURCE_ELEMENT, TARGET_ELEMENT
  }

  public static final String PATH_SEPARATOR = "::"; //$NON-NLS-1$
  // The menu.
  protected Menu _additionMenu;
  /** UI - ComboBox */
  private Combo _comboType;
  /** NamedElement selected by the user */
  public TraceableElement _currentNamedElement;

  /** View Filter */
  public TypeElementFilter _eltFilter ;
  /** List of listeners subscribers */
  private ListenerList _listeners = new ListenerList();

  private SelectionListener _menuSelectionListener;

  /** Button */
  ToolItem _removeItem, _addItem;

  /** Source Element content provider */
  public SourceElementContentProvider _srcEltContentProvider;
  /** Target Element content provider */
  public TargetElementContentProvider _targetEltContentProvider;

  /** Content type of the TraceTreeviewer */
  public TraceType _traceType;
  /** UI - Treeviewer */
  public TreeViewer _treeViewer;
  
  private ResourceSet context;

  public TreeViewer getClientViewer() {
    return _treeViewer;
  }

  private ViewEditPage _wizardPage;
  ToolBar toolBar;

  /**
   * 
   */
  public TraceTreeViewer(TraceableElement firstInput_p, TraceType traceType_p) {
    _traceType = traceType_p;
    _currentNamedElement = firstInput_p;
    context = firstInput_p.eResource().getResourceSet();
    _eltFilter = new TypeElementFilter(context);

    _menuSelectionListener = new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        if (e_p.widget instanceof MenuItem) {
          MenuItem item = (MenuItem) e_p.widget;
          showWizard(item.getText(), true);
        }

      }
    };
  }

  /**
   * Add a listener
   * 
   * @param listener_p
   */
  public void addListener(IDoubleClickListener listener_p) {
    _listeners.add(listener_p);// register a new subscriber
  }

  /**
   * add listeners for toobar item
   */
  private void addToolbarListeners() {

    _addItem.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e) {
        // get selection
        Object selectedElement = _treeViewer.getSelection();
        Object elem = null;
        if ((selectedElement instanceof IStructuredSelection)
            && (((IStructuredSelection) selectedElement).size() == 1)) {
          elem = ((IStructuredSelection) selectedElement).getFirstElement();
        }
        if (e.detail == SWT.ARROW) {
          if (!(elem instanceof Trace)) {
            Point point = new Point(e.x, e.y);
            point = Display.getCurrent().map(toolBar, null, point);
            _additionMenu.setLocation(point);
            _additionMenu.setVisible(true);
          }
        } else {
          boolean flag = false;
          if (elem instanceof Class) {
            flag = TraceExtensionManager.eINSTANCE.isAssignableFrom((Class<?>) elem, context)
                || GenericTrace.class.isAssignableFrom((Class<?>) elem);
          }
          if ((elem instanceof Trace) || ((elem instanceof Class) && flag)) {
            String traceType = null;
            boolean isNewTrace = false;
            LabelProvider labelProvider;
            IBaseLabelProvider baseProvider = _treeViewer.getLabelProvider();
            if (baseProvider instanceof LabelProvider) {
              labelProvider = (LabelProvider) baseProvider;
            } else {
              return;
            }
            if (elem instanceof Trace) {
              Object parent = ((ITreeContentProvider) _treeViewer.getContentProvider()).getParent(elem);
              traceType = labelProvider.getText(parent);
            } else {
              traceType = labelProvider.getText(elem);
              isNewTrace = true;
            }
            showWizard(traceType, isNewTrace);
          }
        }
      }
    });

    _removeItem.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e) {

        TraceUtil.removeTraces(_traceType, _treeViewer);
        if (TraceUtil.isIsVoidTrace()) {
          _wizardPage.setErrorMessage(Messages.getString("TraceTreeViewer.void_trace_error")); //$NON-NLS-1$
          _wizardPage.setPageComplete(false);
        } else {
          _wizardPage.setErrorMessage(null);
          _wizardPage.setPageComplete(true);
        }
        updateComponent(_currentNamedElement);
      }
    });
  }

  /**
   * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
   */
  public void doubleClick(DoubleClickEvent event_p) {
    ISelection selection = event_p.getSelection();
    if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
      Object elem = ((IStructuredSelection) selection).getFirstElement();
      if (elem instanceof CapellaElement) {
        updateComponent((CapellaElement) elem);
        notifyObservers(event_p);
      } else {
        if (_treeViewer.getExpandedState(elem)) {
          _treeViewer.collapseToLevel(elem, AbstractTreeViewer.ALL_LEVELS);
        } else {
          _treeViewer.expandToLevel(elem, 1);
        }
      }
    }
  }

  /**
   * <code>getControl</code> render a composite displaying src or target trace elements
   * 
   * @param parent_p
   *          parent composite
   * @return composite
   */
  public Composite getControl(Composite parent_p) {
    Composite compo = new Composite(parent_p, SWT.BORDER);

    // Create the desired layout for this wizard page
    GridLayout layout = new GridLayout();
    layout.numColumns = 1;
    layout.verticalSpacing = 2;
    layout.marginWidth = 0;
    layout.marginHeight = 2;
    compo.setLayout(layout);

    // toolbar
    toolBar = new ToolBar(compo, SWT.POP_UP);

    // Sets the tool bar layout data.
    GridData gData = new GridData();
    gData.horizontalAlignment = SWT.FILL;
    gData.grabExcessHorizontalSpace = true;
    toolBar.setLayoutData(gData);
    _additionMenu = new Menu(compo.getShell());

    _addItem = new ToolItem(toolBar, SWT.DROP_DOWN);
    _addItem.setToolTipText(Messages.getString("TraceTreeViewer.addTrace_tooltip")); //$NON-NLS-1$
    _addItem
        .setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MDTrace.PLUGIN_ID, IImageKeys.ACTION_ADD).createImage());
    prepareMenuItems();

    _removeItem = new ToolItem(toolBar, SWT.PUSH);
    _removeItem.setToolTipText(Messages.getString("TraceTreeViewer.removeTrace_tooltip")); //$NON-NLS-1$
    _removeItem.setImage(MDTrace.imageDescriptorFromPlugin(MDTrace.PLUGIN_ID, IImageKeys.ACTION_REMOVE).createImage());

    // disable the remove item
    _removeItem.setEnabled(false);

    addToolbarListeners();

    // Title
    Label label = new Label(compo, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
    if (_traceType.equals(TraceType.SOURCE_ELEMENT)) {
      label.setText(Messages.getString("TraceTreeViewer.source_elt_linked")); //$NON-NLS-1$
    } else {
      label.setText(Messages.getString("TraceTreeViewer.target_elt_linked")); //$NON-NLS-1$
    }
    GridData layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    label.setLayoutData(layoutData);

    _treeViewer = new TreeViewer(compo, SWT.LEFT | SWT.READ_ONLY | SWT.BORDER);
    _treeViewer.setUseHashlookup(true);

    // layout the tree viewer below the text field
    layoutData = new GridData();
    layoutData.grabExcessHorizontalSpace = true;
    layoutData.grabExcessVerticalSpace = true;
    layoutData.horizontalAlignment = GridData.FILL;
    layoutData.verticalAlignment = GridData.FILL;
    _treeViewer.getControl().setLayoutData(layoutData);

    if (_traceType.equals(TraceType.SOURCE_ELEMENT)) {
      _srcEltContentProvider = new SourceElementContentProvider();
      _treeViewer.setContentProvider(_srcEltContentProvider);
    } else {
      _targetEltContentProvider = new TargetElementContentProvider();
      _treeViewer.setContentProvider(_targetEltContentProvider);
    }

    _treeViewer.setLabelProvider(new ElementLabelProvider(context));
    _treeViewer.setInput(_currentNamedElement);
    _treeViewer.addDoubleClickListener(this);
    _treeViewer.expandAll();

    _treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection selection = event_p.getSelection();
        _addItem.setEnabled(true);
        _removeItem.setEnabled(!selection.isEmpty());

        if ((selection instanceof IStructuredSelection) && (((IStructuredSelection) selection).size() == 1)) {
          Object elem = ((IStructuredSelection) selection).getFirstElement();
          Object parent = null;
          if (elem instanceof CapellaElement) {
            parent = ((ITreeContentProvider) _treeViewer.getContentProvider()).getParent(elem);
          }
          boolean canEnableRemoveItem = TraceUtil.canAddRemoveItemsToTrace((null != parent) ? parent : elem, context);
          _removeItem.setEnabled(canEnableRemoveItem);

          boolean canEnableAddItem = TraceUtil.canEnableAddItem(elem);
          _addItem.setEnabled(canEnableAddItem);
        }
      }

    });

    // Combo filter
    Composite compositeComboBox = new Composite(compo, SWT.NONE);
    GridLayout gl2 = new GridLayout();
    gl2.numColumns = 2;
    compositeComboBox.setLayout(gl2);
    new Label(compositeComboBox, SWT.CENTER).setText(Messages.getString("TraceTreeViewer.trace_type")); //$NON-NLS-1$
    _comboType = new Combo(compositeComboBox, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
    _comboType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    setComboInput();

    _treeViewer.addFilter(_eltFilter);
    _comboType.addSelectionListener(new SelectionListener() {
      public void widgetDefaultSelected(SelectionEvent e_p) {
        //
      }

      public void widgetSelected(SelectionEvent e_p) {
        if (e_p.getSource() instanceof Combo) {
          Combo combo = (Combo) e_p.getSource();
          _eltFilter.setComboBoxValue(combo.getText());
          _treeViewer.refresh();
        }
      }
    });

    return compo;
  }

  /**
   * @return the wizardPage
   */
  public ViewEditPage getWizardPage() {
    return _wizardPage;
  }

  /**
   * Notify all listener of the current event
   * 
   * @param event_p
   */
  private void notifyObservers(EventObject event_p) {
    Object[] listeners = _listeners.getListeners();
    for (Object listener : listeners) {
      if (event_p instanceof DoubleClickEvent) {
        ((IDoubleClickListener) listener).doubleClick((DoubleClickEvent) event_p);
      }
    }
  }

  public void prepareMenuItems() {

    for (String traceName : TraceNameHelper.getManualTraceTypes(context)) {
      MenuItem item = new MenuItem(_additionMenu, SWT.PUSH);
      item.setText(traceName);
      item.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(MDTrace.PLUGIN_ID, IImageKeys.MENU_ITEM).createImage());
      item.addSelectionListener(_menuSelectionListener);
    }
  }

  /**
   * Remove a listener
   * 
   * @param listener_p
   */
  public void removeListener(IDoubleClickListener listener_p) {
    _listeners.remove(listener_p);// remove a subscriber
  }

  /**
   * <code>setComboInput</code> set Data contained by the combo
   */
  public void setComboInput() {
    List<Class<? extends AbstractTrace>> traceType;
    _comboType.removeAll();
    _comboType.add(Messages.getString("TraceTreeViewer.all_traces")); //$NON-NLS-1$
    _comboType.select(0);
    _eltFilter.setComboBoxValue(_comboType.getText());
    _treeViewer.refresh();
    if (_traceType.equals(TraceType.SOURCE_ELEMENT)) {
      traceType = _srcEltContentProvider.getTraceType();
      for (Class<? extends AbstractTrace> class1 : traceType) {
        _comboType.add(TraceNameHelper.getTraceNameFromClass(class1, context));
      }
    } else {
      traceType = _targetEltContentProvider.getTraceType();
      for (Class<? extends AbstractTrace> class1 : traceType) {
        _comboType.add(TraceNameHelper.getTraceNameFromClass(class1, context));
      }
    }
  }

  /**
   * @param wizardPage_p
   *          the wizardPage to set
   */
  public void setWizardPage(ViewEditPage wizardPage_p) {
    _wizardPage = wizardPage_p;
  }

  private void showWizard(String traceType_p, boolean isNewTrace_p) {

    EObject root = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(_currentNamedElement);
    if (root == null) {
      root = EcoreUtil2.getFirstContainer(_currentNamedElement, SharedmodelPackage.Literals.SHARED_PKG);
    }
    if (root == null) { // for project level properties
      root = CapellaQueries.getInstance().getRootQueries().getProject(_currentNamedElement);
    }
    CapellaModelTreeContentProvider contentProvider = new CapellaModelTreeContentProvider();
    contentProvider.setRootPkg((ModelElement) root);

    boolean expandViewer = CapellaUIPropertiesPlugin.getDefault().isAllowedExpandSingleViewerContent();
    int viewerExpandLevel = expandViewer ? AbstractTreeViewer.ALL_LEVELS : 0;

    TraceTreeSelectionDialog dlg = new TraceTreeSelectionDialog(_treeViewer.getControl().getShell(),
        new CapellaModelLabelProvider((CapellaElement) _currentNamedElement, isNewTrace_p), contentProvider,
        _currentNamedElement, isNewTrace_p);
    EObject container = root instanceof Project ? root : root.eContainer();
    dlg.setInput(container);
    dlg.setHelpAvailable(true);
    dlg.setTitle(Messages.getString("TraceTreeViewer.window_title")); //$NON-NLS-1$
    dlg.setAllowMultiple(false);
    dlg.setViewerExpandLevel(viewerExpandLevel);
    // dlg.setDoubleClickSelects(false);
    if (Window.OK == dlg.open()) {
      if (dlg.getResult().length > 0) {
        Object obj = dlg.getResult()[0];
        if (obj instanceof TraceableElement) {
          // get selection
          Object selectedElement = _treeViewer.getSelection();
          Object elem = null;
          if ((selectedElement instanceof IStructuredSelection)
              && (((IStructuredSelection) selectedElement).size() == 1)) {
            elem = ((IStructuredSelection) selectedElement).getFirstElement();
          }
          // perform add trace
          TraceUtil.addTraces(_currentNamedElement, (TraceableElement) obj, traceType_p, _traceType, elem);
          if (TraceUtil.isIsVoidTrace()) {
            _wizardPage.setErrorMessage(Messages.getString("TraceTreeViewer.void_trace_error")); //$NON-NLS-1$
            _wizardPage.setPageComplete(false);
          } else {
            _wizardPage.setErrorMessage(null);
            _wizardPage.setPageComplete(true);
          }
          _treeViewer.refresh();
          _treeViewer.expandAll();

          setComboInput();
        }
      }
    }
  }

  public void updateComponent(TraceableElement newNamedElement_p) {
    _currentNamedElement = newNamedElement_p;
    _treeViewer.setInput(newNamedElement_p);
    _treeViewer.refresh();
    _treeViewer.expandAll();
    setComboInput();
  }
}
