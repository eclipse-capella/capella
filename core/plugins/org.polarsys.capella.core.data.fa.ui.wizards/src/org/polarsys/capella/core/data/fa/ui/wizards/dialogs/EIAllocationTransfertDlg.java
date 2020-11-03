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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
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
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.menu.dynamic.DynamicCreateChildAction;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ModalContextMenuExtender;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.DeleteElementAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.MakeLinkAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.RemoveElementAction;
import org.polarsys.capella.core.data.fa.ui.wizards.actions.StartLinkAction;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 */
public class EIAllocationTransfertDlg extends AbstractViewerDialog {

  protected Text statusBarText;
  protected LinkManager linkManager;
  protected EIAllocationTreeViewer sepViewer;
  protected EIAllocationTreeViewer tepViewer;
  protected List<ModelElement> selectedElements;
  protected List<ModelElement> transitionedElements;

  public static final String EIALLOCATION_SOURCE_VIEWER = "org.polarsys.capella.core.data.fa.ui.wizards.dialogs.eiAllocation.sourcePhase";
  
  public static final String EIALLOCATION_TARGET_VIEWER = "org.polarsys.capella.core.data.fa.ui.wizards.dialogs.eiAllocation.targetPhase";
  
  /** message pattern used for formatting the status bar tooltip */
  private static final String MSG_PATTERN = "[%s][%s] %s"; //$NON-NLS-1$

  /** separator used by the path shown in the status bar */
  private static final String PATH_SEPARATOR = "::"; //$NON-NLS-1$

  /**
   * @param parentShell
   * @param leftLabelProvider
   * @param rightLabelProvider
   * @param dialogTitle
   * @param dialogMessage
   */
  public EIAllocationTransfertDlg(Shell parentShell, String dialogTitle, String dialogMessage, String shellTitle) {
    super(parentShell, dialogTitle, dialogMessage, shellTitle);
    
    linkManager = new LinkManager();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doCreateDialogArea(Composite dialogAreaComposite) {
    Composite treeComposite = new Composite(dialogAreaComposite, SWT.CENTER);
    treeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
    GridData gdData = new GridData(GridData.FILL_BOTH);
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = true;
    gdData.heightHint = 400;
    gdData.widthHint = 600;
    treeComposite.setLayoutData(gdData);

    sepViewer = new EIAllocationTreeViewer(treeComposite);
    tepViewer = new EIAllocationTreeViewer(treeComposite);

    sepViewer.setGroupLabel("Source Engineering Phase" + getArchitectureName(selectedElements)); //$NON-NLS-1$
    sepViewer.getTreeViewer().setContentProvider(new EIAllocationContentProvider());
    sepViewer.getTreeViewer().setLabelProvider(new EIAllocationLabelProvider(linkManager, sepViewer, true));
    sepViewer.getTreeViewer().setColumnProperties(new String[] { ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() });
    sepViewer.getTreeViewer().setCellEditors(new CellEditor[] { new TextCellEditor(sepViewer.getTreeViewer().getTree(), SWT.BORDER) });
    sepViewer.getTreeViewer().setCellModifier(new EIAllocationTreeViewerCellModifier(sepViewer.getTreeViewer().getTree()));
    sepViewer.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        updateSelection((ITreeSelection) event.getSelection(), false, tepViewer, sepViewer);
      }
    });
    sepViewer.getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event) {
        handleDbClick(event, sepViewer.getTreeViewer());
      }
    });
    sepViewer.getTreeViewer().getTree().addListener(SWT.MouseDown, new Listener() {
      @Override
      public void handleEvent(Event event) {
        handleNotification(event, sepViewer);
      }
    });
    sepViewer.setInput(selectedElements);

    tepViewer.setGroupLabel("Target Engineering Phase" + getArchitectureName(transitionedElements)); //$NON-NLS-1$
    tepViewer.getTreeViewer().setUseHashlookup(true);
    tepViewer.getTreeViewer().setContentProvider(new EIAllocationContentProvider());
    tepViewer.getTreeViewer().setLabelProvider(new EIAllocationLabelProvider(linkManager, tepViewer, false));
    tepViewer.getTreeViewer().setColumnProperties(new String[] { ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() });
    tepViewer.getTreeViewer().setCellEditors(new CellEditor[] { new TextCellEditor(tepViewer.getTreeViewer().getTree(), SWT.BORDER) });
    tepViewer.getTreeViewer().setCellModifier(new EIAllocationTreeViewerCellModifier(tepViewer.getTreeViewer().getTree()));
    tepViewer.getTreeViewer().addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        updateSelection((ITreeSelection) event.getSelection(), true, sepViewer, tepViewer);
      }
    });
    tepViewer.getTreeViewer().addDoubleClickListener(new IDoubleClickListener() {
      @Override
      public void doubleClick(DoubleClickEvent event) {
        handleDbClick(event, tepViewer.getTreeViewer());
      }
    });
    tepViewer.getTreeViewer().getTree().addListener(SWT.MouseDown, new Listener() {
      @Override
      public void handleEvent(Event event) {
        handleNotification(event, tepViewer);
      }
    });
    tepViewer.setInput(transitionedElements);

    registerContextualMenu(sepViewer.getTreeViewer(), false);
    registerContextualMenu(tepViewer.getTreeViewer(), true);
    
    createStatusTextField(dialogAreaComposite);
    createDragDropSourceTargets();
  }

  /**
   *
   */
  protected void handleDbClick(DoubleClickEvent event, TreeViewer treeViewer) {
    ITreeSelection selection = (ITreeSelection) event.getSelection();
    if (!selection.isEmpty()) {
      Object data = selection.getFirstElement();
      ((EIAllocationTreeViewerCellModifier) treeViewer.getCellModifier()).setEnabled(true);
      treeViewer.editElement(data, 0);
    }
  }

  /**
   *
   */
  protected void handleNotification(Event event, EIAllocationTreeViewer treeViewer) {
    ((EIAllocationTreeViewerCellModifier) treeViewer.getTreeViewer().getCellModifier()).setEnabled(false);
    if (EIAllocationTreeViewerCellModifier.RENAMED_NOTIFICATION.equals(event.text)) {
      refreshViewers(true);
      updateStatusBar(event.data, treeViewer);
    }
  }

  /** */
  private boolean isAlreadyUpdatingSelection = false;
  
  /**
   *
   */
  protected void updateSelection(ITreeSelection selection, boolean isTEP, EIAllocationTreeViewer updatedViewer, EIAllocationTreeViewer menuViewer) {
    if (!isAlreadyUpdatingSelection) {
      isAlreadyUpdatingSelection = true;

      if (selection.size() == 1) {
        ModelElement selectedElement = (ModelElement) selection.getFirstElement();
        if (selectedElement instanceof ExchangeItem) {
          selectExchangeItem((ExchangeItem) selectedElement, updatedViewer);
        } else {
          List<ModelElement> elts = isTEP ?
           EIAllocationModelHelpers.getTransitionerElements(selectedElement) :
             EIAllocationModelHelpers.getTransitionedElements(selectedElement);
          updatedViewer.getTreeViewer().setSelection(new StructuredSelection(elts), true);
        }
        updateStatusBar(selectedElement, menuViewer);
      } else {
        updatedViewer.getTreeViewer().setSelection(null, true);
        updateStatusBar(null);
      }
      isAlreadyUpdatingSelection = false;
    }
  }
  
  /**
   *
   */
  protected String getArchitectureName(List<ModelElement> elements) {
    if (!elements.isEmpty()) {
      ModelElement elt = elements.get(0);
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
   *@param exchangeItem
   *@param treeViewer
   */
  protected void selectExchangeItem(ExchangeItem exchangeItem, EIAllocationTreeViewer treeViewer) {
    for (EObject obj : EIAllocationModelHelpers.getOwners(exchangeItem)) {
      treeViewer.getTreeViewer().expandToLevel(obj, 1);
    }
    Widget[] widgets = treeViewer.findItems(exchangeItem);
    TreeItem[] items = new TreeItem[widgets.length];
    for (int i = 0; i < widgets.length; i++) {
      items[i] = (TreeItem) widgets[i];
    }
    treeViewer.getTreeViewer().getTree().setSelection(items);
  }

  /**
   * @param selection
   * @param treeViewer
   * @param isTEP
   */
  protected void registerContextualMenu(final TreeViewer treeViewer, final boolean isTEP) {
    MenuManager menuMgr = new MenuManager();

    if (isTEP) {
      menuMgr.add(new DynamicCreateContributionItem(treeViewer));
    }
    
    menuMgr.add(new ActionContributionItem(new DeleteElementAction(treeViewer) {
      @Override
      protected void postRun() {
        refreshViewers(isTEP);
      }
    }));
    
    menuMgr.add(new ActionContributionItem(new RemoveElementAction(treeViewer) {
      @Override
      protected void postRun() {
        refreshViewers(isTEP);
      }
    }));
    
    menuMgr.add(new Separator());
    
    if (isTEP) {
      menuMgr.add(new ActionContributionItem(new StartLinkAction(linkManager, treeViewer) {
        @Override
        protected void postRun() {
          refreshViewers(true);
        }
      }));
      
    } else {
      menuMgr.add(new ActionContributionItem(new MakeLinkAction(linkManager, treeViewer) {
        @Override
        protected void postRun() {
          refreshViewers(true);
        }
      }));
    }
    
    Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
    treeViewer.getControl().setMenu(menu);
    ModalContextMenuExtender.registerContextMenu(menuMgr, !isTEP ? EIALLOCATION_SOURCE_VIEWER : EIALLOCATION_TARGET_VIEWER, treeViewer);
  }

  /**
   * @param isTEP
   */
  protected void refreshViewers(boolean isTEP) {
    sepViewer.getTreeViewer().refresh(true);
    if (isTEP) {
      tepViewer.getTreeViewer().refresh(true);
    }
  }

  /**
   * @param selection
   * @param treeViewer
   * @return
   */
  @SuppressWarnings("unchecked")
  protected List<Action> getAddElementActions(IStructuredSelection selection, final TreeViewer treeViewer) {
    List<Action> actions = new ArrayList<Action>();
    final Object selectedElement = selection.getFirstElement();
    
    if (selectedElement instanceof AbstractFunction) {
      TransactionalEditingDomain editingDomain = TransactionHelper.getEditingDomain((EObject) selectedElement);
      
      for (final CommandParameter cmd : (Collection<CommandParameter>) editingDomain.getNewChildDescriptors(selectedElement, null)) {
        final EReference ref = cmd.getEReference();
        final EObject value = cmd.getEValue();
        if (FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS.equals(ref)
            || ActivityPackage.Literals.ABSTRACT_ACTION__INPUTS.equals(ref)
            || ActivityPackage.Literals.ABSTRACT_ACTION__OUTPUTS.equals(ref)) {
          
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

              treeViewer.refresh(true);
            }
            @Override
            public String getText() {
              ItemProviderAdapter itemProvider = EObjectLabelProviderHelper.getItemProvider(cmd.getEValue());
              String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(cmd.getEValue().eClass(), itemProvider);
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
    sepViewer.getTreeViewer().addDragSupport(operations, transferTypes, new EIAllocationDragListener(sepViewer.getTreeViewer()));
    tepViewer.getTreeViewer().addDropSupport(operations, transferTypes, new EIAllocationDropAdapter(sepViewer.getTreeViewer(), tepViewer.getTreeViewer()));
  }

  /**
   * @param parent
   */
  private void createStatusTextField(Composite parent) {
    statusBarText = new Text(parent, SWT.READ_ONLY | SWT.BORDER);
    statusBarText.setEditable(false);
    GridData gdData = new GridData();
    gdData.horizontalAlignment = SWT.FILL;
    gdData.grabExcessHorizontalSpace = true;
    gdData.grabExcessVerticalSpace = false;
    gdData.horizontalSpan = 2;
    statusBarText.setLayoutData(gdData);
  }

  /**
   * @param selectedElements
   * @param transitionedElements
   */
  public void setSelection(List<ModelElement> selectedElements, List<ModelElement> transitionedElements) {
    this.selectedElements = selectedElements;
    this.transitionedElements = transitionedElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getResult() {
    return null;
  }

  /**
   * @param element
   */
  @SuppressWarnings("unchecked")
  protected void updateStatusBar(Object element) {
    updateStatusBar(element, Collections.EMPTY_LIST);
  }

  /**
   * @param element
   * @param treeViewer
   */
  protected void updateStatusBar(Object element, EIAllocationTreeViewer treeViewer) {
    updateStatusBar(element, treeViewer.findItem(element));
  }

  /**
   * @param element
   * @param widget
   */
  protected void updateStatusBar(Object element, Widget widget) {
    List<String> messages = new ArrayList<String>();
    if (null != widget) {
      Object data = widget.getData(EIAllocationLabelProvider.VALIDATION_KEY);
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
    updateStatusBar(element, messages);
  }

  /**
   * @param status
   * @return
   */
  private String getRuleId(IStatus status) {
    if (status instanceof ConstraintStatus) {
      IConstraintDescriptor desc = ((ConstraintStatus) status).getConstraint().getDescriptor();
      return desc.getId().replace(desc.getPluginId() + ".", Util.ZERO_LENGTH_STRING); //$NON-NLS-1$
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * @param status
   * @return
   */
  private String getSeverity(IStatus status) {
    switch (status.getSeverity()) {
      case IStatus.INFO: return "INFO"; //$NON-NLS-1$
      case IStatus.WARNING: return "WARNING"; //$NON-NLS-1$
      case IStatus.ERROR: return "ERROR"; //$NON-NLS-1$
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * @param element
   * @param messages
   */
  protected void updateStatusBar(Object element, List<String> messages) {
    if (element instanceof AbstractNamedElement) {
      AbstractNamedElement elt = (AbstractNamedElement) element;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(elt);
      String path = getPath(systemEngineering, elt);
      statusBarText.setText("[" + messages.size() + " warning/error(s)] " + ((path == null) ? Util.ZERO_LENGTH_STRING : path));  //$NON-NLS-1$//$NON-NLS-2$
      if (messages.size() > 0) {
        String tooltip = Util.ZERO_LENGTH_STRING;
        for (int i = 0; i < messages.size(); i++) {
          tooltip += messages.get(i);
          if (i < messages.size() - 1) {
            tooltip += "\n"; //$NON-NLS-1$
          }
        }
        statusBarText.setToolTipText(tooltip);
      } else {
        statusBarText.setToolTipText(null);
      }
    } else {
      statusBarText.setText(Util.ZERO_LENGTH_STRING);
      statusBarText.setToolTipText(null);
    }
  }

  /**
   * Gets the path.
   * @param sysEng the system engineering.
   * @param target The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng, AbstractNamedElement target) {
    String name = (target instanceof ExchangeItemAllocation) ? ((ExchangeItemAllocation) target).getAllocatedItem().getName() : target.getName();
    if (null == name) {
      name = Util.ZERO_LENGTH_STRING;
    }
    StringBuffer path = new StringBuffer(name);

    EObject container = target.eContainer();
    if (container instanceof AbstractNamedElement) {
      AbstractNamedElement parent = (AbstractNamedElement) target.eContainer();
      if (parent != sysEng) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng, parent) + PATH_SEPARATOR);
      } else {
        path.insert(path.indexOf(path.toString()), parent.getName() + PATH_SEPARATOR);
      }
    }
    return path.toString();
  }
}
