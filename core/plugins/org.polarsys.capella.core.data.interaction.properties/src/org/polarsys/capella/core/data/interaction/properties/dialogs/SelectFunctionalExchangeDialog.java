/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.properties.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.TransferTreeListDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.ui.properties.providers.CapellaTransfertViewerLabelProvider;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

public class SelectFunctionalExchangeDialog extends SelectElementsDialog {
  public enum DataflowDialogCreationType {
    COMPONENT_EXCHANGE, FUNCTIONAL_EXCHANGE, FUNCTIONAL_EXCHANGE_SCENARIO
  }

  protected enum ListenerType {
    TARGET, SOURCE
  }

  public class ExchangeItemSelectionListener extends SelectionAdapter {

    CapellaElement element;

    public ExchangeItemSelectionListener(CapellaElement component) {
      this.element = component;
    }

    protected List<EObject> getAvailableElements() {
      List<EObject> returnedList = new ArrayList<>();
      EClass createdEClass = getCreatedEClass();
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(createdEClass, getExchangeItemReference(createdEClass));

      if (query != null) {
        returnedList.addAll(query.getAvailableElements(element));
        returnedList.removeAll(getCurrentElements());
      }
      return returnedList;
    }

    @SuppressWarnings({ "synthetic-access" })
    protected List<EObject> getCurrentElements() {
      return _allocatedExchangeItems;
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    @SuppressWarnings("synthetic-access")
    public void widgetSelected(SelectionEvent e) {
      List<? extends EObject> availableElements = getAvailableElements();
      TransferTreeListDialog dialog =
          new TransferTreeListDialog(getShell(), Messages.ExchangeDialog_ExchangeItemSelectionWizardTitle,
              Messages.ExchangeDialog_ExchangeItemSelectionWizardMessage);
      dialog.setLeftInput(availableElements, null);
      dialog.setRightInput(getCurrentElements(), null);

      if (Window.OK == dialog.open()) {
        Object[] results = dialog.getResult();
        List<EObject> resultObjects = new ArrayList<>();
        for (Object result : results) {
          if (result instanceof EObject) {
            resultObjects.add((EObject) result);
          }
        }
        updateExchangeItems(resultObjects);
      }
    }
  }

  /**
   * Clears the data set by the {@link SelectFunctionalExchangeDialog.FunctionSelectionListener}
   */
  public class FunctionClearListener extends SelectionAdapter {

    private Text _funtionToClear;
    private ListenerType _type;

    public FunctionClearListener(Text functionName, ListenerType type) {
      _funtionToClear = functionName;
      _type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
      _funtionToClear.setData(null);
      _funtionToClear.setText(""); //$NON-NLS-1$
      // clears the cache if any selected function was stored
      _cache.remove(_type);
    }

  }

  public class FunctionSelectionListener extends SelectionAdapter {
    private Component _component;
    private FunctionPkg _function;
    private Text _text;
    private ListenerType _type;

    /**
     * @param sourceFunctionName
     * @param function
     * @param listener_type
     */
    public FunctionSelectionListener(Text sourceText, FunctionPkg function, Component component, ListenerType listener_type) {
      _text = sourceText;
      _function = function;
      _component = component;
      _type = listener_type;
    }

    /**
     * @return the text
     */
    public Text getText() {
      return _text;
    }

    /**
     * @return the type
     */
    public ListenerType getType() {
      return _type;
    }

    /**
     * @return the component
     */
    public Component getComponent() {
      return _component;
    }

    /**
     * returns a list of available functions. These are the functions not yet implemented, further these implemented by _component
     * @param functionPkg
     * @return
     */
    private List<? extends EObject> getAccessibleFunctions(FunctionPkg functionPkg) {
      List<AbstractFunction> result = new ArrayList<>();
      TreeIterator<EObject> it = functionPkg.eAllContents();
      while (it.hasNext()) {
        EObject obj = it.next();
        if (obj instanceof AbstractFunction) {
          AbstractFunction af = (AbstractFunction) obj;
          boolean toAdd = true;
          for (ComponentFunctionalAllocation cfa : af.getComponentFunctionalAllocations()) {
            if ((cfa.getBlock() == _component) || ComponentExt.getSubUsedComponents(_component).contains(cfa.getBlock())) {
              toAdd = true;
              break;
            }
            toAdd = false;
          }

          if (toAdd && af.getSubFunctions().isEmpty()) {
            result.add(af);
          }
        }
      }
      // check that current function is not already selected in the other text field
      // if working on source function remove target and vice versa
      if (ListenerType.SOURCE.equals(_type)) {
        AbstractNamedElement targetFunc = _cache.get(ListenerType.TARGET);
        if (null != targetFunc) {
          result.remove(targetFunc);
        }
      } else if (ListenerType.TARGET.equals(_type)) {
        AbstractNamedElement srcFunc = _cache.get(ListenerType.SOURCE);
        if (null != srcFunc) {
          result.remove(srcFunc);
        }

      }
      return result;
    }

    /**
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    @SuppressWarnings("synthetic-access")
    @Override
    public void widgetSelected(SelectionEvent e) {
      Collection<? extends EObject> functions = getAccessibleFunctions(_function);
      SelectElementsDialog selectFunctionDialog =
          new SelectElementsDialog(getParentShell(), Messages.SelectOperationDialog_SelectInterfaceDialog_Title, Messages.SelectOperationDialog_SelectInterfaceDialog_Message,
              functions);
      if (Window.OK == selectFunctionDialog.open()) {
        AbstractNamedElement selectedFunc = (AbstractNamedElement) selectFunctionDialog.getResult().get(0);
        _text.setText(selectedFunc.getName());
        _text.setData(selectedFunc);
        _cache.put(_type, selectedFunc);

      }
    }
  }

  private List<EObject> _allocatedExchangeItems;

  private Text _allocatedExchangeItemsText;
  private Button createNewExchangeButton;
  private Button _instanceLevelExchangeCheckBox;
  private static boolean _lastTypeLevelValue = false;

  private DataflowDialogCreationType _creationType;
  private List<? extends EObject> _displayedElements;
  private Text functionalExchangeText;
  /**
   * the sequence message this dialog will populate
   */
  @SuppressWarnings("unused")
  private SequenceMessage _sequenceMessage;
  private Button _sourceFunctionButton;
  private Button _sourceFunctionClearButton;

  // caches for each listener type (source or target) the user selected function
  public HashMap<ListenerType, AbstractNamedElement> _cache = new HashMap<>();

  private Text sourceFunctionName;
  private Button _targetFunctionButton;
  private Button _targetFunctionClearButton;

  private Text targetFunctionName;
  private InstanceRole _targetIR;
  private InstanceRole _sourceIR;

  public SelectFunctionalExchangeDialog(Shell parentShell, String dialogTitle,
      String dialogMessage, List<? extends EObject> displayedElements, SequenceMessage message, InstanceRole sourceIR, InstanceRole targetIR,
      DataflowDialogCreationType creationType) {
    super(parentShell, new CapellaTransfertViewerLabelProvider(), dialogTitle, dialogMessage, displayedElements, false, null,
          AbstractTreeViewer.ALL_LEVELS);
    _sequenceMessage = message;
    _displayedElements = displayedElements;
    _creationType = creationType;
    _allocatedExchangeItems = new ArrayList<>();
    _sourceIR = sourceIR;
    _targetIR = targetIR;
  }

  private void configureSelectExchangeButtonHandler() {
    SelectionAdapter listener = new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
        updateWindow();
      }
    };
    createNewExchangeButton.addSelectionListener(listener);

    if (_displayedElements.isEmpty()) {
      createNewExchangeButton.setSelection(true);
    } else {
      createNewExchangeButton.setSelection(false);
    }
  }

  @Override
  public void create() {
    super.create();
    if (sourceFunctionName != null) {
      getShell().setMinimumSize(661, 720);
    } else {
      getShell().setMinimumSize(661, 650);
    }
  }

  /**
   * Creates a group for the Exchange Creation with selection of source/target
   */
  private void createCompleteHeaderEditPart(Group treeViewerPartGroup) {
    InstanceRole ir;
    if (_sourceIR != null) {
      ir = _sourceIR;
    } else {
      ir = _targetIR;
    }

    FunctionPkg function = getFunctionPkgFromInstanceRole(ir);
    // default labels
    String treeViewerLabel = Messages.ExchangeDialog_CreateFunctionalExchangeGroup;
    String buttonLabel = Messages.ExchangeDialog_CreateFunctionalExchangeCheckbox;
    String exchangeLabel = Messages.ExchangeDialog_FunctionalExchangeLabel;
    String sourceLabel = Messages.ExchangeDialog_SourceFunctionLabel;
    String targetLabel = Messages.ExchangeDialog_TargetFunctionLabel;

    if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(ir)) {
      treeViewerLabel = Messages.ExchangeDialog_CreateInteractionGroup;
      buttonLabel = Messages.ExchangeDialog_CreateInteractionCheckbox;
      exchangeLabel = Messages.ExchangeDialog_InteractionLabel;
      sourceLabel = Messages.ExchangeDialog_SourceActivityLabel;
      targetLabel = Messages.ExchangeDialog_TargetActivityLabel;
    }

    treeViewerPartGroup.setText(treeViewerLabel);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(4, false)); /*
                                                              * 3 columns one for the label, one the text and the last one for the button
                                                              */

    createNewExchangeButton = new Button(treeViewerPartGroup, SWT.CHECK);
    createNewExchangeButton.setText(buttonLabel);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 4;
    createNewExchangeButton.setLayoutData(layoutData);

    // Create a text field to host the functional exchange name.

    createLabel(treeViewerPartGroup, exchangeLabel);

    functionalExchangeText = createText(treeViewerPartGroup);
    ((GridData) functionalExchangeText.getLayoutData()).horizontalSpan = 3; // No
    // button
    // following.

    Component sourceComponent = _sourceIR == null ? null : (Component) _sourceIR.getRepresentedInstance().getAbstractType();
    Component targetComponent = _targetIR == null ? null : (Component) _targetIR.getRepresentedInstance().getAbstractType();

    // Create a text field to host the Source function name.
    createLabel(treeViewerPartGroup, sourceLabel);
    sourceFunctionName = createText(treeViewerPartGroup);
    _sourceFunctionButton = new Button(treeViewerPartGroup, SWT.PUSH);
    _sourceFunctionButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID));
    _sourceFunctionButton.addSelectionListener(new FunctionSelectionListener(sourceFunctionName, function, sourceComponent, ListenerType.SOURCE));
    _sourceFunctionClearButton = new Button(treeViewerPartGroup, SWT.PUSH);
    _sourceFunctionClearButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID));
    _sourceFunctionClearButton.addSelectionListener(new FunctionClearListener(sourceFunctionName, ListenerType.SOURCE));

    // same for target function
    createLabel(treeViewerPartGroup, targetLabel);
    targetFunctionName = createText(treeViewerPartGroup);
    _targetFunctionButton = new Button(treeViewerPartGroup, SWT.PUSH);
    _targetFunctionButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID));
    _targetFunctionButton.addSelectionListener(new FunctionSelectionListener(targetFunctionName, function, targetComponent, ListenerType.TARGET));
    _targetFunctionClearButton = new Button(treeViewerPartGroup, SWT.PUSH);
    _targetFunctionClearButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.REMOVE_IMAGE_ITEM_ID));
    _targetFunctionClearButton.addSelectionListener(new FunctionClearListener(targetFunctionName, ListenerType.TARGET));
    // Create a modify text adapter to update buttons.
    ModifyListener modifyListener = new ModifyListener() {
      /**
       * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
       */
      public void modifyText(ModifyEvent e) {
        updateButtons(null);
      }
    };
    sourceFunctionName.addModifyListener(modifyListener);
    targetFunctionName.addModifyListener(modifyListener);
    functionalExchangeText.addModifyListener(modifyListener);
  }

  /**
   * @param headerGroup
   */
  private void disableDirectCreationForNode(Group headerGroup) {
    Component sourceComponent = _sourceIR == null ? null : (Component) _sourceIR.getRepresentedInstance().getAbstractType();
    Component targetComponent = _targetIR == null ? null : (Component) _targetIR.getRepresentedInstance().getAbstractType();

    boolean isPhysicalComponentSorceOrTarget =
        (sourceComponent != null) && (targetComponent != null)
            && ((sourceComponent instanceof PhysicalComponent) && (targetComponent instanceof PhysicalComponent));
    BlockArchitecture sourceBlockArchitecture = ComponentExt.getRootBlockArchitecture(sourceComponent);
    BlockArchitecture targetBlockArchitecture = ComponentExt.getRootBlockArchitecture(targetComponent);
    boolean isPhysicalArchitecture =
        (sourceBlockArchitecture != null) && (targetBlockArchitecture != null) && (sourceBlockArchitecture instanceof PhysicalArchitecture)
            && (targetBlockArchitecture instanceof PhysicalArchitecture);
    if (isPhysicalComponentSorceOrTarget && isPhysicalArchitecture) {
      PhysicalComponent sourcePhysicalComponent = (PhysicalComponent) sourceComponent;
      PhysicalComponent targetPhysicalComponent = (PhysicalComponent) targetComponent;
      boolean isNodeSourceOrTarget = (sourcePhysicalComponent.getNature().equals(PhysicalComponentNature.NODE)
          && !ComponentExt.isActor(sourcePhysicalComponent))
          || (targetPhysicalComponent.getNature().equals(PhysicalComponentNature.NODE)
              && !ComponentExt.isActor(targetPhysicalComponent));
      disableInputText(headerGroup, !isNodeSourceOrTarget);

    } else {
      disableInputText(headerGroup, true);
    }
  }

  /**
   * @param isEditable
   * @param headerGroup
   */
  private void disableInputText(Group headerGroup, boolean isEditable) {
    Control[] controls = headerGroup.getChildren();
    for (Control control : controls) {
      if (control instanceof Text) {
        Text currentText = (Text) control;
        currentText.setEditable(isEditable);
      } else if (control instanceof Button) {
        Button currentControl = (Button) control;
        currentControl.setEnabled(isEditable);
      }
    }
  }

  private FunctionPkg getFunctionPkgFromInstanceRole(InstanceRole ir) {
    EObject obj = ir;
    while (!(obj instanceof BlockArchitecture)) {
      obj = obj.eContainer();
    }
    return ((BlockArchitecture) obj).getOwnedFunctionPkg();
  }

  /**
   * Creates a group for the Exchange Creation without selection of source/target
   */
  private void createSimpleHeaderEditPart(Group treeViewerPartGroup) {

    String treeViewerLabel;
    String buttonLabel;
    String exchangeLabel;

    // default labels
    if (_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE) {
      treeViewerLabel = Messages.ExchangeDialog_CreateComponentExchangeGroup;
      buttonLabel = Messages.ExchangeDialog_CreateComponentExchangeCheckbox;
      exchangeLabel = Messages.ExchangeDialog_ComponentExchangeLabel;
    } else {
      treeViewerLabel = Messages.ExchangeDialog_CreateFunctionalExchangeGroup;
      buttonLabel = Messages.ExchangeDialog_CreateFunctionalExchangeCheckbox;
      exchangeLabel = Messages.ExchangeDialog_FunctionalExchangeLabel;

      if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(_sourceIR)) {
        treeViewerLabel = Messages.ExchangeDialog_CreateInteractionGroup;
        buttonLabel = Messages.ExchangeDialog_CreateInteractionCheckbox;
        exchangeLabel = Messages.ExchangeDialog_InteractionLabel;
      }
    }

    treeViewerPartGroup.setText(treeViewerLabel);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(3, false)); /*
                                                              * 3 columns one for the label, one the text and the last one for the button
                                                              */

    createNewExchangeButton = new Button(treeViewerPartGroup, SWT.CHECK);
    createNewExchangeButton.setText(buttonLabel);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    createNewExchangeButton.setLayoutData(layoutData);
    createNewExchangeButton.setEnabled(true);

    // Create a text field to host the functional exchange name.
    createLabel(treeViewerPartGroup, exchangeLabel);

    functionalExchangeText = createText(treeViewerPartGroup);
    ((GridData) functionalExchangeText.getLayoutData()).horizontalSpan = 2; // No
    // button
    // following.

    // Create a modify text adapter to update buttons.
    ModifyListener modifyListener = new ModifyListener() {
      /**
       * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
       */
      public void modifyText(ModifyEvent e) {
        updateButtons(null);
      }
    };
    functionalExchangeText.addModifyListener(modifyListener);

    // Component Exchange creation in MultiPartLevel : instance or component CE ?
    TriStateBoolean isMultipart = CapellaProjectHelper.isReusableComponentsDriven(_sourceIR);
    if (isMultipart.equals(TriStateBoolean.True) && (_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE)
        && !CapellaLayerCheckingExt.isInOperationalAnalysisLayer(_sourceIR)) {
      createLabel(treeViewerPartGroup, "Component Exchange between types"); //$NON-NLS-1$
      _instanceLevelExchangeCheckBox = new Button(treeViewerPartGroup, SWT.CHECK);
      _instanceLevelExchangeCheckBox.setSelection(_lastTypeLevelValue);
    }

  }

  @Override
  protected void doCreateDialogArea(Composite parent) {
    if (!isLostFoundContext()) {
      final Group headerGroup = new Group(parent, SWT.NONE);
      if ((_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE) || (_creationType == DataflowDialogCreationType.FUNCTIONAL_EXCHANGE_SCENARIO)) {
        createSimpleHeaderEditPart(headerGroup);
      } else {
        createCompleteHeaderEditPart(headerGroup);
      }
      disableDirectCreationForNode(headerGroup);
    }
    super.doCreateDialogArea(parent);

    // Add a selection handler to all the selection of an existing
    // interface.
    if (!isLostFoundContext()) {
      configureSelectExchangeButtonHandler();
    }
    updateWindow();

  }

  private boolean isLostFoundContext() {
    return (_sourceIR == null) || (_targetIR == null);
  }

  /**
   * @param sourceFunctionName
   * @return
   */
  private AbstractFunction ensureFunction(Text sourceFunctionName, AbstractFunction parent) {
    if (sourceFunctionName.getData() != null) {
      return (AbstractFunction) sourceFunctionName.getData();
    }
    AbstractFunction result = null;

    if (parent instanceof SystemFunction) {
      result = CtxFactory.eINSTANCE.createSystemFunction();
    } else if (parent instanceof LogicalFunction) {
      result = LaFactory.eINSTANCE.createLogicalFunction();
    } else if (parent instanceof PhysicalFunction) {
      result = PaFactory.eINSTANCE.createPhysicalFunction();
    } else if (parent instanceof OperationalActivity) {
      result = OaFactory.eINSTANCE.createOperationalActivity();
    }
    if(result != null) {
    	parent.getOwnedFunctions().add(result);
    	CapellaElementExt.creationService(result);
    	result.setName(sourceFunctionName.getText());
    }
    return result;
  }

  /**
   * @param srcComponent
   * @param srcFunc
   */
  private void ensureFunctionRealization(Component component, AbstractFunction func) {
    boolean found = false;
    for (ComponentFunctionalAllocation alloc : component.getFunctionalAllocations()) {
      if (alloc.getFunction() == func) {
        found = true;
      }
    }
    if (!found) {
      ComponentFunctionalAllocation cfa = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
      component.getOwnedFunctionalAllocation().add(cfa);
      cfa.setTargetElement(func);
      cfa.setSourceElement(component);
      CapellaElementExt.creationService(cfa);
    }
  }

  /**
   * @param sourceFunctionName
   * @param targetFunctionName
   * @param functionalExchangeText
   * @return
   */
  private FunctionalExchange ensureFunctions(Text sourceFunctionName, Text targetFunctionName, Text functionalExchangeText) {
    AbstractFunction root = BlockArchitectureExt.getRootFunction(BlockArchitectureExt.getRootBlockArchitecture(_sourceIR != null ? _sourceIR : _targetIR));

    AbstractFunction srcFunc = ensureFunction(sourceFunctionName, root);
    AbstractFunction tgtFunc = ensureFunction(targetFunctionName, root);

    FunctionalExchange fe = FunctionalExchangeExt.createFunctionalExchange(srcFunc, tgtFunc);
    fe.setName(functionalExchangeText.getText());

    // do not forget the functionRealization on components.
    if (_sourceIR != null) {
      if (_sourceIR.getRepresentedInstance() instanceof Role) {
        Role srcRole = (Role) _sourceIR.getRepresentedInstance();
        ensureFunctionRealization(srcRole, srcFunc);
      } else {
        AbstractType srcComponent = _sourceIR.getRepresentedInstance().getAbstractType();
        ensureFunctionRealization((Component) srcComponent, srcFunc);
      }
    }
    if (_targetIR != null) {
      if (_targetIR.getRepresentedInstance() instanceof Role) {
        Role tgtRole = (Role) _targetIR.getRepresentedInstance();
        ensureFunctionRealization(tgtRole, tgtFunc);
      } else {
        AbstractType tgtComponent = _targetIR.getRepresentedInstance().getAbstractType();
        ensureFunctionRealization((Component) tgtComponent, tgtFunc);
      }
    }

    return fe;
  }

  private void ensureFunctionRealization(Role role, AbstractFunction func) {
    boolean found = false;
    for (ActivityAllocation alloc : role.getOwnedActivityAllocations()) {
      if (alloc.getActivity() == func) {
        found = true;
      }
    }
    if (!found) {
      ActivityAllocation cfa = OaFactory.eINSTANCE.createActivityAllocation();
      role.getOwnedActivityAllocations().add(cfa);
      cfa.setTargetElement(func);
      cfa.setSourceElement(role);
      CapellaElementExt.creationService(cfa);
    }

  }

  /**
   * Returns the created EClass according to the wizard mode
   */
  protected EClass getCreatedEClass() {
    if (_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE) {
      return FaPackage.Literals.COMPONENT_EXCHANGE;
    }
    return FaPackage.Literals.FUNCTIONAL_EXCHANGE;
  }

  /**
   * Returns the EReference used to access to exchange items for the given EClass
   */
  protected EReference getExchangeItemReference(EClass clazz) {
    if (FaPackage.Literals.FUNCTIONAL_EXCHANGE.equals(clazz)) {
      return FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS;
    }
    return ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS;
  }

  @Override
  protected List<? extends EObject> handleResult() {
    if ((createNewExchangeButton != null) && createNewExchangeButton.getSelection()) {
      // there are some elements to create
      if (_creationType == DataflowDialogCreationType.FUNCTIONAL_EXCHANGE) {
        FunctionalExchange fe = ensureFunctions(sourceFunctionName, targetFunctionName, functionalExchangeText);
        return Collections.singletonList(fe);

      } else if (_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE && _sourceIR != null && _targetIR !=  null) {
        ComponentExchange componentExchange = null;
        Component srcComponent = (Component) _sourceIR.getRepresentedInstance().getAbstractType();
        Component tgtComponent = (Component) _targetIR.getRepresentedInstance().getAbstractType();

        if (CapellaLayerCheckingExt.isInOperationalAnalysisLayer(_sourceIR)) {
          componentExchange = OaFactory.eINSTANCE.createCommunicationMean();
          componentExchange.setSource((Entity) srcComponent);
          componentExchange.setTarget((Entity) tgtComponent);
        } else {
          boolean isNotMultipart = true;
          if (_instanceLevelExchangeCheckBox != null) {
            isNotMultipart = _instanceLevelExchangeCheckBox.getSelection();
            _lastTypeLevelValue = isNotMultipart;
          }

          componentExchange =
              ComponentExchangeExt.createComponentExchange((Part) _sourceIR.getRepresentedInstance(), null, (Part) _targetIR.getRepresentedInstance(), null,
                  ComponentExchangeKind.FLOW, ComponentPortKind.FLOW, OrientationPortKind.OUT, ComponentPortKind.FLOW, OrientationPortKind.IN, !isNotMultipart);
        }
        ComponentExchangeExt.attachToDefaultContainer(componentExchange);
        CapellaElementExt.creationService(componentExchange);
        componentExchange.setName(functionalExchangeText.getText());

        saveExchangeItems(componentExchange);
        return Collections.singletonList(componentExchange);

      } else if (_creationType == DataflowDialogCreationType.FUNCTIONAL_EXCHANGE_SCENARIO && _sourceIR != null && _targetIR !=  null) {
        ActivityNode srcFunction = (ActivityNode) _sourceIR.getRepresentedInstance();
        ActivityNode dstFunction = (ActivityNode) _targetIR.getRepresentedInstance();

        FunctionalExchange fe = FunctionalExchangeExt.createFunctionalExchange(srcFunction, dstFunction);
        fe.setName(functionalExchangeText.getText());

        saveExchangeItems(fe);
        return Collections.singletonList(fe);
      }

    }

    return super.handleResult();
  }

  @Override
  protected boolean isOkToClose(ISelection selection) {
    if (createNewExchangeButton == null) {
      return (getViewer().getSelection() != null) && !getViewer().getSelection().isEmpty();
    }

    // If not checked, or no selection of exchangeItem in the list, the
    // end-user has selected an existing allocation.
    if ((!createNewExchangeButton.getSelection()) && (getViewer().getSelection() != null) && !getViewer().getSelection().isEmpty()) {
      return super.isOkToClose(selection);
    }
    if ((_creationType == DataflowDialogCreationType.COMPONENT_EXCHANGE) || (_creationType == DataflowDialogCreationType.FUNCTIONAL_EXCHANGE_SCENARIO)) {
      return !functionalExchangeText.getText().equals(ICommonConstants.EMPTY_STRING);
    }
    return !functionalExchangeText.getText().equals(ICommonConstants.EMPTY_STRING) && !sourceFunctionName.getText().equals(ICommonConstants.EMPTY_STRING)
           && !targetFunctionName.getText().equals(ICommonConstants.EMPTY_STRING);
  }

  @Override
  public int open() {
    return super.open();
  }

  /**
   * Save selected exchange items into the given object
   */
  @SuppressWarnings("unchecked")
  private void saveExchangeItems(EObject object) {
    EReference reference = getExchangeItemReference(object.eClass());

    if (_allocatedExchangeItems != null) {
      if ((reference != null) && reference.isMany()) {
        List<EObject> value = ((List<EObject>) object.eGet(reference));
        for (EObject item : new ArrayList<EObject>(value)) {
          if (!_allocatedExchangeItems.contains(item)) {
            value.remove(item);
          }
        }
        for (EObject item : _allocatedExchangeItems) {
          if (!value.contains(item)) {
            value.add(item);
          }
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateButtons(ISelection selection) {
    super.updateButtons(selection);

    // Update the exchange item list according to the selection
    List<EObject> exchangeItems = new ArrayList<>();
      if (selection instanceof TreeSelection) {
        Object element = ((TreeSelection) selection).getFirstElement();
        if (element instanceof EObject) {
          EReference reference = getExchangeItemReference(((EObject) element).eClass());
          if ((reference != null) && reference.isMany() && EcoreUtil2.isEqualOrSuperClass(reference.getEContainingClass(), ((EObject) element).eClass())) {
            exchangeItems.addAll((List<EObject>) ((EObject) element).eGet(reference));
          }
        }
      }
    updateExchangeItems(exchangeItems);
  }

  /**
   * Update temporary stored list of exchange item according to the given list and update the text of exchange item textbox
   */
  private void updateExchangeItems(List<EObject> resultObjects) {
    if (resultObjects != null) {
      _allocatedExchangeItems = resultObjects;

      String textDescription = ICommonConstants.EMPTY_STRING;
      for (int i = 0; i < (_allocatedExchangeItems.size() - 1); i++) {
        textDescription +=
            EObjectLabelProviderHelper.getText(_allocatedExchangeItems.get(i)) + ICommonConstants.COMMA_CHARACTER + ICommonConstants.WHITE_SPACE_CHARACTER;
      }
      if (!_allocatedExchangeItems.isEmpty()) {
        textDescription += EObjectLabelProviderHelper.getText(_allocatedExchangeItems.get(_allocatedExchangeItems.size() - 1));
      }
      if (_allocatedExchangeItemsText != null) {
        _allocatedExchangeItemsText.setText(textDescription);
      }
    }
  }

  void updateWindow() {
    if (createNewExchangeButton != null) {
      boolean newExchange = createNewExchangeButton.isEnabled() && createNewExchangeButton.getSelection();

      functionalExchangeText.setEnabled(newExchange);
      if (sourceFunctionName != null) {
        sourceFunctionName.setEnabled(newExchange);
        targetFunctionName.setEnabled(newExchange);
        _sourceFunctionButton.setEnabled(newExchange);
        _sourceFunctionClearButton.setEnabled(newExchange);
        _targetFunctionButton.setEnabled(newExchange);
        _targetFunctionClearButton.setEnabled(newExchange);
      }
      // Disable the main viewer to prevent from selection.
      TreeAndListViewer viewer = getViewer();
      ISelection selection = null;
      if (null != viewer) {
        viewer.setEnabled(!newExchange);
        // no selections since we create a new FE
        if (!newExchange) {
          selection = viewer.getSelection();
        }
      }
      if (_instanceLevelExchangeCheckBox != null) {
        _instanceLevelExchangeCheckBox.setEnabled(newExchange);
      }
      updateButtons(selection);
    }
  }
}
