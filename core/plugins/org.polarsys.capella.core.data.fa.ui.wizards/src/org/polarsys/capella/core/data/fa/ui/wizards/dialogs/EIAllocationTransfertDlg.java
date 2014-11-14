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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.DeleteElementAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.MakeLinkAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.RemoveElementAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.SelectInProjectExplorerAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.SelectInSemanticBrowserAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.StartLinkAction;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.menu.dynamic.DynamicCreateChildAction;

/**
 */
public class EIAllocationTransfertDlg extends AbstractViewerDialog {

  protected Text _statusBarText;
  protected LinkManager _linkManager;
  protected EIAllocationTreeViewer _sepViewer;
  protected EIAllocationTreeViewer _tepViewer;
  protected List<ModelElement> _selectedElements;
  protected List<ModelElement> _transitionedElements;

  /** message pattern used for formatting the status bar tooltip */
  private static final String MSG_PATTERN = "[%s][%s] %s"; //$NON-NLS-1$

  /** separator used by the path shown in the status bar */
  private static final String PATH_SEPARATOR = "::"; //$NON-NLS-1$

  /**
   * @param parentShell_p
   * @param leftLabelProvider_p
   * @param rightLabelProvider_p
   * @param dialogTitle_p
   * @param dialogMessage_p
   */
  public EIAllocationTransfertDlg(Shell parentShell_p, String dialogTitle_p, String dialogMessage_p, String shellTitle_p) {
    super(parentShell_p, dialogTitle_p, dialogMessage_p, shellTitle_p);
    
    _linkManager = new LinkManager();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doCreateDialogArea(Composite dialogAreaComposite_p) {
    Composite treeComposite = new Composite(dialogAreaComposite_p, SWT.CENTER);
    treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
    GridData gdData = new GridData(GridData.FILL_BOTH);
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;
    gdData.heightHint = 400;
    gdData.widthHint = 600;
    treeComposite.setLayoutData(gdData);

    _sepViewer = new EIAllocationTreeViewer(treeComposite);
    _tepViewer = new EIAllocationTreeViewer(treeComposite);

    _sepViewer.setGroupLabel("Source Engineering Phase" + getArchitectureName(_selectedElements)); //$NON-NLS-1$
    _sepViewer.getTreeViewer().setContentProvider(new EIAllocationContentProvider());
    _sepViewer.getTreeViewer().setLabelProvider(new EIAllocationLabelProvider(_linkManager, _sepViewer, true));
    _sepViewer.getTreeViewer().setColumnProperties(new String[] { ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() });
    _sepViewer.getTreeViewer().setCellEditors(new CellEditor[] { new TextCellEditor(_sepViewer.getTreeViewer().getTree(), SWT.BORDER) });
    _sepViewer.getTreeViewer().setCellModifier(new EIAllocationTreeViewerCellModifier(_sepViewer.getTreeViewer().getTree()));
    _sepViewer.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event_p) {
        updateSelection((ITreeSelection) event_p.getSelection(), false, _tepViewer, _sepViewer);
      }
    });
    _sepViewer.getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event_p) {
        handleDbClick(event_p, _sepViewer.getTreeViewer());
      }
    });
    _sepViewer.getTreeViewer().getTree().addListener(SWT.MouseDown, new Listener() {
      @Override
      public void handleEvent(Event event) {
        handleNotification(event, _sepViewer);
      }
    });
    _sepViewer.setInput(_selectedElements);

    _tepViewer.setGroupLabel("Target Engineering Phase" + getArchitectureName(_transitionedElements)); //$NON-NLS-1$
    _tepViewer.getTreeViewer().setUseHashlookup(true);
    _tepViewer.getTreeViewer().setContentProvider(new EIAllocationContentProvider());
    _tepViewer.getTreeViewer().setLabelProvider(new EIAllocationLabelProvider(_linkManager, _tepViewer, false));
    _tepViewer.getTreeViewer().setColumnProperties(new String[] { ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() });
    _tepViewer.getTreeViewer().setCellEditors(new CellEditor[] { new TextCellEditor(_tepViewer.getTreeViewer().getTree(), SWT.BORDER) });
    _tepViewer.getTreeViewer().setCellModifier(new EIAllocationTreeViewerCellModifier(_tepViewer.getTreeViewer().getTree()));
    _tepViewer.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event_p) {
        updateSelection((ITreeSelection) event_p.getSelection(), true, _sepViewer, _tepViewer);
      }
    });
    _tepViewer.getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event_p) {
        handleDbClick(event_p, _tepViewer.getTreeViewer());
      }
    });
    _tepViewer.getTreeViewer().getTree().addListener(SWT.MouseDown, new Listener() {
      @Override
      public void handleEvent(Event event) {
        handleNotification(event, _tepViewer);
      }
    });
    _tepViewer.setInput(_transitionedElements);

    createStatusTextField(dialogAreaComposite_p);
    createDragDropSourceTargets();
  }

  /**
   *
   */
  protected void handleDbClick(DoubleClickEvent event_p, TreeViewer treeViewer_p) {
    ITreeSelection selection = (ITreeSelection) event_p.getSelection();
    if (!selection.isEmpty()) {
      Object data = selection.getFirstElement();
      ((EIAllocationTreeViewerCellModifier) treeViewer_p.getCellModifier()).setEnabled(true);
      treeViewer_p.editElement(data, 0);
    }
  }

  /**
   *
   */
  protected void handleNotification(Event event_p, EIAllocationTreeViewer treeViewer_p) {
    ((EIAllocationTreeViewerCellModifier) treeViewer_p.getTreeViewer().getCellModifier()).setEnabled(false);
    if (EIAllocationTreeViewerCellModifier.RENAMED_NOTIFICATION.equals(event_p.text)) {
      refreshViewers(true);
      updateStatusBar(event_p.data, treeViewer_p);
    }
  }

  /** */
  private boolean isAlreadyUpdatingSelection = false;
  
  /**
   *
   */
  protected void updateSelection(ITreeSelection selection, boolean isTEP_p, EIAllocationTreeViewer updatedViewer_p, EIAllocationTreeViewer menuViewer_p) {
    if (!isAlreadyUpdatingSelection) {
      isAlreadyUpdatingSelection = true;

      if (selection.size() == 1) {
        ModelElement selectedElement = (ModelElement) selection.getFirstElement();
        if (selectedElement instanceof ExchangeItem) {
          selectExchangeItem((ExchangeItem) selectedElement, updatedViewer_p);
        } else {
          List<ModelElement> elts = isTEP_p ?
           EIAllocationModelHelpers.getTransitionerElements(selectedElement) :
             EIAllocationModelHelpers.getTransitionedElements(selectedElement);
          updatedViewer_p.getTreeViewer().setSelection(new StructuredSelection(elts), true);
        }
        updateStatusBar(selectedElement, menuViewer_p);
      } else {
        updatedViewer_p.getTreeViewer().setSelection(null, true);
        updateStatusBar(null);
      }
      showContextualMenu(selection, menuViewer_p.getTreeViewer(), isTEP_p);

      isAlreadyUpdatingSelection = false;
    }
  }
  
  /**
   *
   */
  protected String getArchitectureName(List<ModelElement> elements_p) {
    if (!elements_p.isEmpty()) {
      ModelElement elt = elements_p.get(0);
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(elt);
      if (OaPackage.Literals.OPERATIONAL_ANALYSIS.equals(arch.eClass())) {
        return " [OA]"; //$NON-NLS-1$
      } else if (CtxPackage.Literals.SYSTEM_ANALYSIS.equals(arch.eClass())) {
        return " [SA]"; //$NON-NLS-1$
      } else if (LaPackage.Literals.LOGICAL_ARCHITECTURE.equals(arch.eClass())) {
        return " [LA]"; //$NON-NLS-1$
      } else if (PaPackage.Literals.PHYSICAL_ARCHITECTURE.equals(arch.eClass())) {
        return " [PA]"; //$NON-NLS-1$
      } else if (EpbsPackage.Literals.EPBS_ARCHITECTURE.equals(arch.eClass())) {
        return " [EPBS]"; //$NON-NLS-1$
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   *@param exchangeItem_p
   *@param treeViewer_p
   */
  protected void selectExchangeItem(ExchangeItem exchangeItem_p, EIAllocationTreeViewer treeViewer_p) {
    for (EObject obj : EIAllocationModelHelpers.getOwners(exchangeItem_p)) {
      treeViewer_p.getTreeViewer().expandToLevel(obj, 1);
    }
    Widget[] widgets = treeViewer_p.findItems(exchangeItem_p);
    TreeItem[] items = new TreeItem[widgets.length];
    for (int i = 0; i < widgets.length; i++) {
      items[i] = (TreeItem) widgets[i];
    }
    treeViewer_p.getTreeViewer().getTree().setSelection(items);
  }

  /**
   * @param selection_p
   * @param treeViewer_p
   * @param isTEP_p
   */
  protected void showContextualMenu(final IStructuredSelection selection_p, final TreeViewer treeViewer_p, final boolean isTEP_p) {
    MenuManager menuMgr = new MenuManager();
    if (isTEP_p) {
      if (selection_p.size() == 1) {
        for (Action action : getAddElementActions(selection_p, treeViewer_p)) {
          menuMgr.add(action);
        }
        menuMgr.add(new Separator());
      }
    }
    menuMgr.add(new DeleteElementAction(selection_p, treeViewer_p) {
      @Override
      protected void postRun() {
        refreshViewers(isTEP_p);
      }
    });
    menuMgr.add(new RemoveElementAction(selection_p, treeViewer_p) {
      @Override
      protected void postRun() {
        refreshViewers(isTEP_p);
      }
    });
    menuMgr.add(new Separator());
    menuMgr.add(new SelectInProjectExplorerAction(selection_p));
    menuMgr.add(new SelectInSemanticBrowserAction(selection_p));
    menuMgr.add(new Separator());
    if (isTEP_p) {
      menuMgr.add(new StartLinkAction(_linkManager, treeViewer_p) {
        @Override
        protected void postRun() {
          refreshViewers(true);
        }
      });
    } else {
      menuMgr.add(new MakeLinkAction(_linkManager, treeViewer_p) {
        @Override
        protected void postRun() {
          refreshViewers(true);
        }
      });
    }
    Menu menu = menuMgr.createContextMenu(treeViewer_p.getControl());
    treeViewer_p.getControl().setMenu(menu);
  }

  /**
   * @param isTEP_p
   */
  protected void refreshViewers(boolean isTEP_p) {
    _sepViewer.getTreeViewer().refresh(true);
    if (isTEP_p) {
      _tepViewer.getTreeViewer().refresh(true);
    }
  }

  /**
   * @param selection
   * @param treeViewer_p
   * @return
   */
  @SuppressWarnings("unchecked")
  protected List<Action> getAddElementActions(IStructuredSelection selection, final TreeViewer treeViewer_p) {
    List<Action> actions = new ArrayList<Action>();
    final Object selectedElement = selection.getFirstElement();
    if (selectedElement instanceof AbstractFunction) {
      TransactionalEditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
      for (final CommandParameter cmd : (Collection<CommandParameter>) editingDomain.getNewChildDescriptors(selectedElement, null)) {
        final EReference ref = cmd.getEReference();
        final EObject value = cmd.getEValue();
        if (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS.equals(ref)
            || ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS.equals(ref)
            || ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS.equals(ref))
        {
          actions.add(new DynamicCreateChildAction(editingDomain, selection, cmd) {
            @SuppressWarnings("rawtypes")
            @Override
            public void run() {
              EPackage pkg = value.eClass().getEPackage();
              EFactory factory = pkg.getEFactoryInstance();
              EObject obj = factory.create(value.eClass());

              ((Collection) ((EObject) selectedElement).eGet(ref)).add(obj);
              String name = EcoreUtil2.getUniqueName(obj, (EObject) selectedElement, ref, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, obj.eClass().getName());
              obj.eSet(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, name);

              treeViewer_p.refresh(true);
            }
            @Override
            public String getText() {
              ItemProviderAdapter itemProvider = (ItemProviderAdapter) ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory().adapt(cmd.getEValue(), IItemLabelProvider.class);
              String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(cmd.getEValue().eClass(), itemProvider);
              itemProvider.dispose();
              return metaclassLabel;
            }
          });
        }
      }
    }
    return actions;
  }

  /**
   * Creates the Drag and Drop Operations for the tree viewers.
   * @see EIAllocationDragAndDrop
   */
  protected void createDragDropSourceTargets() {
    int operations = DND.DROP_MOVE;
    Transfer[] transferTypes = new Transfer[] { LocalTransfer.getInstance() };
    _sepViewer.getTreeViewer().addDragSupport(operations, transferTypes, new EIAllocationDragListener(_sepViewer.getTreeViewer()));
    _tepViewer.getTreeViewer().addDropSupport(operations, transferTypes, new EIAllocationDropAdapter(_sepViewer.getTreeViewer(), _tepViewer.getTreeViewer()));
  }

  /**
   * @param parent_p
   */
  private void createStatusTextField(Composite parent_p) {
    _statusBarText = new Text(parent_p, SWT.READ_ONLY | SWT.BORDER);
    _statusBarText.setEditable(false);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = false;
    gdData.horizontalSpan = 2;
    _statusBarText.setLayoutData(gdData);
  }

  /**
   * @param selectedElements_p
   * @param transitionedElements_p
   */
  public void setSelection(List<ModelElement> selectedElements_p, List<ModelElement> transitionedElements_p) {
    _selectedElements = selectedElements_p;
    _transitionedElements = transitionedElements_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getResult() {
    return null;
  }

  /**
   * @param element_p
   */
  @SuppressWarnings("unchecked")
  protected void updateStatusBar(Object element_p) {
    updateStatusBar(element_p, Collections.EMPTY_LIST);
  }

  /**
   * @param element_p
   * @param treeViewer_p
   */
  protected void updateStatusBar(Object element_p, EIAllocationTreeViewer treeViewer_p) {
    updateStatusBar(element_p, treeViewer_p.findItem(element_p));
  }

  /**
   * @param element_p
   * @param widget_p
   */
  protected void updateStatusBar(Object element_p, Widget widget_p) {
    List<String> messages = new ArrayList<String>();
    if (null != widget_p) {
      Object data = widget_p.getData(EIAllocationLabelProvider.VALIDATION_KEY);
      if (data instanceof IStatus) {
        IStatus status = (IStatus) data;
        if (status.isMultiStatus()) {
          for (IStatus child : status.getChildren()) {
            messages.add(String.format(MSG_PATTERN, getSeverity(child), getRuleId(child), child.getMessage()));
          }
        } else {
          messages.add(String.format(MSG_PATTERN, getSeverity(status), getRuleId(status), status.getMessage()));
        }
      }
    }
    updateStatusBar(element_p, messages);
  }

  /**
   * @param status_p
   * @return
   */
  private String getRuleId(IStatus status_p) {
    if (status_p instanceof ConstraintStatus) {
      IConstraintDescriptor desc = ((ConstraintStatus) status_p).getConstraint().getDescriptor();
      return desc.getId().replace(desc.getPluginId() + ".", Util.ZERO_LENGTH_STRING); //$NON-NLS-1$
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * @param status_p
   * @return
   */
  private String getSeverity(IStatus status_p) {
    switch (status_p.getSeverity()) {
      case IStatus.INFO: return "INFO"; //$NON-NLS-1$
      case IStatus.WARNING: return "WARNING"; //$NON-NLS-1$
      case IStatus.ERROR: return "ERROR"; //$NON-NLS-1$
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * @param element_p
   * @param messages_p
   */
  protected void updateStatusBar(Object element_p, List<String> messages_p) {
    if (element_p instanceof AbstractNamedElement) {
      AbstractNamedElement element = (AbstractNamedElement) element_p;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
      String path = getPath(systemEngineering, element);
      _statusBarText.setText("[" + messages_p.size() + " warning/error(s)] " + ((path == null) ? Util.ZERO_LENGTH_STRING : path));  //$NON-NLS-1$//$NON-NLS-2$
      if (messages_p.size() > 0) {
        String tooltip = Util.ZERO_LENGTH_STRING;
        for (int i = 0; i < messages_p.size(); i++) {
          tooltip += messages_p.get(i);
          if (i < messages_p.size() - 1) {
            tooltip += "\n"; //$NON-NLS-1$
          }
        }
        _statusBarText.setToolTipText(tooltip);
      } else {
        _statusBarText.setToolTipText(null);
      }
    } else {
      _statusBarText.setText(Util.ZERO_LENGTH_STRING);
      _statusBarText.setToolTipText(null);
    }
  }

  /**
   * Gets the path.
   * @param sysEng_p tghe system engineering.
   * @param target_p The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng_p, AbstractNamedElement target_p) {
    String name = (target_p instanceof ExchangeItemAllocation) ? ((ExchangeItemAllocation) target_p).getAllocatedItem().getName() : target_p.getName();
    if (null == name) {
      name = Util.ZERO_LENGTH_STRING;
    }
    StringBuffer path = new StringBuffer(name);

    EObject container = target_p.eContainer();
    if (container instanceof AbstractNamedElement) {
      AbstractNamedElement parent = (AbstractNamedElement) target_p.eContainer();
      if (parent != sysEng_p) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng_p, parent) + PATH_SEPARATOR);
      } else {
        path.insert(path.indexOf(path.toString()), parent.getName() + PATH_SEPARATOR);
      }
    }
    return path.toString();
  }
}
