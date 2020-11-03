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

package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.viewAndController;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.helpers.information.services.CommunicationLinkExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.controllers.InterfaceHelper;
import org.polarsys.capella.core.data.interaction.properties.dialogs.Messages;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.CreationAlgorithms;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.ui.toolkit.ToolkitPlugin;

/**
 * Dialog to select an operation (i.e service).<br>
 * The viewer is mono-selection.
 */
public class SelectOperationDialogForSharedDataAndEvent extends SelectElementsDialog {

  public enum ElementSupportedType {
    OPERATION, EXCHANGE
  }

  /**
   * Restricted tree viewer button.
   */
  private Button _restrictedTreeViewerButton;
  /**
   * Filter used to display or not restricted interface.
   */
  private ViewerFilter _restrictedInterfaceFilter;

  private ViewerFilter _showComponentExchangeFilter;

  /**
   * Text field used to store the name of a created operation.
   */
  private Text _operationText;
  /**
   * Text field used to store the name and the interface (through widget data) instance that will have a new operation.<br>
   * If getData returns <code>null</code>, a new interface must be created with entered name.
   */
  private Text _interfaceText;
  /**
   * Button that allows the end-user to select an existing interface when creating a new operation.
   */
  private Button _selectInterfaceButton;
  /**
   * Button to enable / disable creation operation.
   */
  private Button _enableCreationButton;
  private ElementSupportedType _elementSupportedType;
  private List<? extends EObject> _restrictedElements;
  private Button _createPortsButton;
  private boolean _isPortStrategy;
  private Button _showComponentExchanges;

  // radio buttons corresponding to EI types (and its group)
  private Button _eventRadioButton;
  private Button _flowRadioButton;
  private Button _operationRadioButton;
  private Button _sharedRadioButton;
  private Group _eiTypeGroup;
  private Button _unsetRadioButton;
  private InstanceRole _sourceIR;
  private InstanceRole _targetIR;
  private MessageKind _messageKind;

  public SelectOperationDialogForSharedDataAndEvent(Shell parentShell, String dialogTitle, String dialogMessage,
      List<? extends EObject> wholeElements, List<? extends EObject> restrictedElements, InstanceRole sourceIR,
      InstanceRole targetIR, MessageKind messageKind, ElementSupportedType type) {
    super(parentShell, dialogTitle, dialogMessage, wholeElements);
    _sourceIR = sourceIR;
    _targetIR = targetIR;
    _elementSupportedType = type;
    _restrictedElements = restrictedElements;
    _messageKind = messageKind;
  }

  /**
   * Configure a handler for the button that enable / disable the creation operation button.
   * @param enableCreationButton
   */
  private void configureEnableCreationButtonHandler() {
    SelectionAdapter listener = new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent event) {
        updateWindow();
      }

    };
    if (_enableCreationButton != null) {
      _enableCreationButton.addSelectionListener(listener);
      if (_restrictedElements.size() == 0) {
        _enableCreationButton.setSelection(true);
      } else {
        _enableCreationButton.setSelection(false);
      }
    }
    // Force to initialize all widgets depending on this checkbox button.
    listener.widgetSelected(null);
  }

  /**
   * Enable or disable part of window according context.
   */
  void updateWindow() {
    boolean enabledCreationOperation = false;
    boolean enabledCreationInterface = true;

    if (_enableCreationButton != null) {
      enabledCreationOperation = _enableCreationButton.getSelection();
      enabledCreationInterface = _enableCreationButton.getSelection();

    }
    enabledCreationInterface = enabledCreationOperation;
    enabledCreationInterface |= (getAnExchangeItemSelected() instanceof ExchangeItem);

    if (_operationText != null) {
      _eiTypeGroup.setEnabled(enabledCreationInterface);
      _operationText.setEnabled(enabledCreationOperation);

      updateRadioButtons(enabledCreationOperation);
    }
    _interfaceText.setEnabled(enabledCreationInterface);
    if (_createPortsButton != null) {
      _createPortsButton.setEnabled(enabledCreationInterface);
    }
    _selectInterfaceButton.setEnabled(enabledCreationInterface);

    // Disable the main viewer to prevent from selection.
    TreeAndListViewer viewer = getViewer();
    ISelection selection = null;
    if (null != viewer) {
      viewer.setEnabled(!enabledCreationOperation);
      selection = viewer.getSelection();
    }
    if (null != _restrictedTreeViewerButton) {
      _restrictedTreeViewerButton.setEnabled(!enabledCreationOperation);
    }
    if (_restrictedTreeViewerButton != null && InterfaceHelper.isSharedDataAccess(_sourceIR, _targetIR)) {
      _restrictedTreeViewerButton.setEnabled(false);
    }
    updateButtons(selection);
  }

  /**
   * Updates radio button following the message type
   * @param enabledCreationOperation
   */
  private void updateRadioButtons(boolean enabledCreationOperation) {
    if (enabledCreationOperation) {
      if (_messageKind == MessageKind.ASYNCHRONOUS_CALL) {
        _eventRadioButton.setEnabled(true);
        _sharedRadioButton.setEnabled(true);
      } else {
        _eventRadioButton.setEnabled(false);
        _sharedRadioButton.setEnabled(false);
      }
      _flowRadioButton.setEnabled(true);
      _unsetRadioButton.setEnabled(true);
      _operationRadioButton.setEnabled(true);
      // unset is always legal
    } else {
      _eventRadioButton.setEnabled(false);
      _sharedRadioButton.setEnabled(false);
      _flowRadioButton.setEnabled(false);
      _unsetRadioButton.setEnabled(false);
      _operationRadioButton.setEnabled(false);
    }

  }

  private List<EObject> getAvailableInterfaces() {
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(InteractionPackage.Literals.SEQUENCE_MESSAGE,
            InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END);
    return query.getAvailableElements(_sourceIR);
  }

  /**
   * Configure a handler to select an existing interface for edit sequence message.
   */
  private void configureSelectInterfaceButtonHandler() {
    _selectInterfaceButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        SelectElementsDialog selectInterfaceDialog =
            new SelectElementsDialog(getParentShell(),
                Messages.SelectOperationDialog_SelectInterfaceDialog_Title, Messages.SelectOperationDialog_SelectInterfaceDialog_Message,
                getAvailableInterfaces());
        if (Window.OK == selectInterfaceDialog.open()) {
          AbstractNamedElement selectedInterface = (AbstractNamedElement) selectInterfaceDialog.getResult().get(0);
          _interfaceText.setText(selectedInterface.getName());
          _interfaceText.setData(selectedInterface);
        }
      }
    });
  }

  /**
   * Create creation operation widgets.
   * @param parent
   */
  private void createCreationOperationPart(Composite parent) {
    // Add a group surrounding the create operation part.
    final Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(3, false)); /*
                                                              * 3 columns one for the label, one the text and the last one for the button
                                                              */
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_CreateNewExchangeItem);

    _enableCreationButton = new Button(treeViewerPartGroup, SWT.CHECK);
    _enableCreationButton.setText(Messages.SelectOperationDialog_EnableCreationButton_Title);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    _enableCreationButton.setLayoutData(layoutData);

    // Create a text field to host the operation name.
    createLabel(treeViewerPartGroup, Messages.SelectOperationDialog_Operation_Title);
    _operationText = createText(treeViewerPartGroup);
    ((GridData) _operationText.getLayoutData()).horizontalSpan = 3; // No
    // button
    // following.

    // Create a modify text adapter to update buttons.
    ModifyListener modifyListener = new ModifyListener() {
      /**
       * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
       */
      @SuppressWarnings("synthetic-access")
      public void modifyText(ModifyEvent e) {
        updateButtons(null);
      }
    };
    _operationText.addModifyListener(modifyListener);

    _eiTypeGroup = new Group(treeViewerPartGroup, SWT.NONE);
    _eiTypeGroup.setText(Messages.SelectOperationDialog_2);
    _eiTypeGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    _eiTypeGroup.setLayout(new GridLayout(5, false)); /*
                                                       * 4 columns one for the label, one the text and the last one for the button
                                                       */
    _eventRadioButton = new Button(_eiTypeGroup, SWT.RADIO);
    _eventRadioButton.setText(Messages.SelectOperationDialog_3);

    _flowRadioButton = new Button(_eiTypeGroup, SWT.RADIO);
    _flowRadioButton.setText(Messages.SelectOperationDialog_4);

    _operationRadioButton = new Button(_eiTypeGroup, SWT.RADIO);
    _operationRadioButton.setText(Messages.SelectOperationDialog_5);
    _operationRadioButton.setEnabled(true);
    _operationRadioButton.setSelection(true);

    _sharedRadioButton = new Button(_eiTypeGroup, SWT.RADIO);
    _sharedRadioButton.setText(Messages.SelectOperationDialog_6);

    _unsetRadioButton = new Button(_eiTypeGroup, SWT.RADIO);
    _unsetRadioButton.setText(Messages.SelectOperationDialog_7);

  }

  /**
   * Create creation operation widgets.
   * @param parent
   */
  private void createInterfacePart(Composite parent) {
    // Add a group surrounding the create operation part.
    final Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_CreateOrSelectInterface);
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, true));
    treeViewerPartGroup.setLayout(new GridLayout(3, false)); /*
                                                              * 3 columns one for the label, one the text and the last one for the button
                                                              */

    // Create a text field to host the Interface name.
    createLabel(treeViewerPartGroup, Messages.SelectOperationDialog_Interface_Title);
    _interfaceText = createText(treeViewerPartGroup);

    // Create a modify text adapter to update buttons.
    ModifyListener modifyListener = new ModifyListener() {
      /**
       * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
       */
      @SuppressWarnings("synthetic-access")
      public void modifyText(ModifyEvent e) {
        updateButtons(null);
      }
    };
    _interfaceText.addModifyListener(modifyListener);

    _selectInterfaceButton = new Button(treeViewerPartGroup, SWT.PUSH);
    _selectInterfaceButton.setImage(ToolkitPlugin.getDefault().getImageRegistry().get(ToolkitPlugin.BROWSE_IMAGE_ITEM_ID));

    List<EObject> accessiblesInterfaces = getAvailableInterfaces();
    // filtering accessible interfaces to select one used/implemented by
    // components
    Interface bestInterface = null;
    boolean multipleBest = false;
    for (EObject capellaElement : accessiblesInterfaces) {
      if (isGoodInterface(capellaElement)) {
        if (bestInterface != null) {
          // second best interface, select it to null
          multipleBest = true;
          break;
        }
        bestInterface = (Interface) capellaElement;
      }
    }

    if (bestInterface == null) {
      // component-> component default name is "source to target"
      // else  is EI_Interface
      StringBuilder builder = new StringBuilder();
      if ((_sourceIR != null) && (_sourceIR.getRepresentedInstance() instanceof ExchangeItemInstance)) {
        builder.append(_sourceIR.getName());
        builder.append("_Interface"); //$NON-NLS-1$
      } else if ((_targetIR != null) && (_targetIR.getRepresentedInstance() instanceof ExchangeItemInstance)) {
        builder.append(_targetIR.getName());
        builder.append("_Interface"); //$NON-NLS-1$
      } else {
        if ((_sourceIR != null) && (null != _sourceIR.getRepresentedInstance())) {
          AbstractType abstractType = _sourceIR.getRepresentedInstance().getAbstractType();
          if (null != abstractType) {
            builder.append(abstractType.getName());
          } else {
            builder.append(_sourceIR.getName());
          }
        }
        if ((_sourceIR != null) && (_targetIR != null)) {
          builder.append("_to_"); //$NON-NLS-1$
        }
        if ((_targetIR != null) && (null != _targetIR.getRepresentedInstance())) {
          AbstractType abstractType = _targetIR.getRepresentedInstance().getAbstractType();
          if (null != abstractType) {
            builder.append(abstractType.getName());
          } else {
            builder.append(_targetIR.getName());
          }
        }
      }
      _interfaceText.setText(builder.toString());
    } else if (multipleBest) {
      _interfaceText.setData(null);
      _interfaceText.setText(""); //$NON-NLS-1$
    } else {
      _interfaceText.setData(bestInterface);
      _interfaceText.setText(bestInterface.getName());
      if (InterfaceHelper.isSharedDataAccess(_sourceIR, _targetIR)) {
        // if we have a good interface, we don't have to change it in
        // communication pattern
        _interfaceText.setEnabled(false);
      }
    }

  }

  /**
   * @param capellaElement
   * @return
   */
  private boolean isGoodInterface(EObject capellaElement) {
    Interface interf = (Interface) capellaElement;
    AbstractType src = _sourceIR == null ? null : _sourceIR.getRepresentedInstance().getAbstractType();
    AbstractType tgt = _targetIR == null ? null : _targetIR.getRepresentedInstance().getAbstractType();
    Component srcComp = null;
    Component tgtComp = null;
    ExchangeItem ei = null;

    if (src instanceof Component) {
      srcComp = (Component) src;
    } else {
      ei = (ExchangeItem) src;
    }

    if (tgt instanceof Component) {
      tgtComp = (Component) tgt;
    } else {
      ei = (ExchangeItem) tgt;
    }

    if (ei == null) {
      return ComponentExt.isImplementingInterface(tgtComp, interf) && ComponentExt.isUsingInterface(srcComp, interf);
      // the interface is OK if it already allocates the EI
    }

    for (ExchangeItemAllocation eia : interf.getOwnedExchangeItemAllocations()) {
      if (eia.getAllocatedItem() == ei) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param parent
   */
  private void createCreatePortButton(final Composite parent) {
    _createPortsButton = new Button(parent, SWT.CHECK);
    _createPortsButton.setText(Messages.SelectOperationDialog_0);
    GridData layoutData = new GridData(GridData.FILL, GridData.BEGINNING, false, false);
    layoutData.horizontalSpan = 3;
    _createPortsButton.setLayoutData(layoutData);
  }

  /**
   * Gets or create the selected interface.
   * @return the selected interface
   */
  private Interface getOrCreateInterface() {
    Interface selectedInterface = (Interface) _interfaceText.getData();
    if ((null == selectedInterface) || !(selectedInterface.getName().equals(_interfaceText.getText()))) {
      selectedInterface = CreationAlgorithms.INSTANCE.createInterface(_interfaceText.getText(), _sourceIR, _targetIR, false);
      _interfaceText.setData(selectedInterface);
    }
    return selectedInterface;
  }

  /**
   * Create a new operation based on values entered by the end-user.
   * @return
   */
  private ExchangeItem createExchangeItem() {
    ExchangeItem result = InformationFactory.eINSTANCE.createExchangeItem();
    Interface itf = getOrCreateInterface();

    // the ei must be by default in the same package as the allocating interface
    EObject container = itf.eContainer();
    if (container instanceof InterfacePkg) {
      InterfacePkg ipkg = (InterfacePkg) container;
      ipkg.getOwnedExchangeItems().add(result);
    } else {
      InterfaceExt.getRootOwnerInterfacePkg(itf).getOwnedExchangeItems().add(result);
    }
    result.setExchangeMechanism(ExchangeMechanism.OPERATION);
    if (_operationRadioButton.getSelection()) {
      result.setExchangeMechanism(ExchangeMechanism.OPERATION);
    }
    if (_eventRadioButton.getSelection()) {
      result.setExchangeMechanism(ExchangeMechanism.EVENT);
    }
    if (_sharedRadioButton.getSelection()) {
      result.setExchangeMechanism(ExchangeMechanism.SHARED_DATA);
    }
    if (_flowRadioButton.getSelection()) {
      result.setExchangeMechanism(ExchangeMechanism.FLOW);
    }
    if (_unsetRadioButton.getSelection()) {
      result.setExchangeMechanism(ExchangeMechanism.UNSET);
    }

    org.polarsys.capella.core.model.helpers.CapellaElementExt.creationService(result);
    result.setName(_operationText.getText());
    return result;
  }

  /**
   * Allocate the exchange item to the interface selected
   * @param getAnExchangeItemSelected
   * @return
   */
  private ExchangeItemAllocation allocateExchangeItem(ExchangeItem exchangeItem) {
    Interface selectedInterface = getOrCreateInterface();
    ExchangeItemAllocation result = InterfaceExt.addExchangeItem(selectedInterface, exchangeItem);
    if (_operationText == null) {
      return allocateExchangeItemForSharedData(exchangeItem, result);
    }
    if (_messageKind == MessageKind.SYNCHRONOUS_CALL) {
      result.setSendProtocol(CommunicationLinkProtocol.SYNCHRONOUS);
    }
    if (_messageKind == MessageKind.ASYNCHRONOUS_CALL) {
      result.setSendProtocol(CommunicationLinkProtocol.ASYNCHRONOUS);
    }
    if (_messageKind == MessageKind.CREATE) {
      result.setSendProtocol(CommunicationLinkProtocol.BROADCAST);
    }
    return result;
  }

  /**
   * Initialize given ExchangeItemAllocation with information from the CommunicationLink (if one is available) or with information deduced from the message
   * itself.
   * @param exchangeItem
   * @param result
   * @return
   */
  private ExchangeItemAllocation allocateExchangeItemForSharedData(ExchangeItem exchangeItem, ExchangeItemAllocation result) {

    Component component;
    ExchangeItem ei;
    CommunicationLink communicationLink = null;
    // Get involved ExchangeItem and Component.
    if (_sourceIR.getRepresentedInstance() instanceof ExchangeItemInstance) {
      ei = (ExchangeItem) _sourceIR.getRepresentedInstance().getAbstractType();
      component = (Component) _targetIR.getRepresentedInstance().getAbstractType();
    } else {
      component = (Component) _sourceIR.getRepresentedInstance().getAbstractType();
      ei = (ExchangeItem) _targetIR.getRepresentedInstance().getAbstractType();
    }
    // Looking for a communication link between Component and ExchangeItem.
    for (CommunicationLink cl : CommunicationLinkExt.getAllCommunicationLinks(component)) {
      if (cl.getExchangeItem() == ei) {
        // found the correct Communication link
        communicationLink = cl;
        break;
      }
    }
    if (null == communicationLink) {
      // No communication link found -> modeling exclusively from the sequence diagram.
      // in this case, the initialization depends of the message itself;
      if (_messageKind == MessageKind.CREATE) {
        result.setReceiveProtocol(CommunicationLinkProtocol.UNSET);
      } else if (_messageKind == MessageKind.SYNCHRONOUS_CALL) {
        if (ei.getExchangeMechanism() == ExchangeMechanism.SHARED_DATA) {
          result.setReceiveProtocol(CommunicationLinkProtocol.READ);
        }
      } else if (_messageKind == MessageKind.ASYNCHRONOUS_CALL) {
        if (_sourceIR.getRepresentedInstance().getAbstractType() instanceof ExchangeItem) {
          result.setReceiveProtocol(CommunicationLinkProtocol.ACCEPT);
        }
      }
    } else {
      // A communication link found -> use it to configure the ExchangeItemAllocation.
      CommunicationLinkKind communicationLinkKind = communicationLink.getKind();
      CommunicationLinkProtocol communicationLinkProtocol = communicationLink.getProtocol();
      if ((CommunicationLinkKind.SEND == communicationLinkKind) || (CommunicationLinkKind.PRODUCE == communicationLinkKind)
          || (CommunicationLinkKind.CALL == communicationLinkKind) || (CommunicationLinkKind.WRITE == communicationLinkKind)
          || (CommunicationLinkKind.TRANSMIT == communicationLinkKind)) {
        // Actually, only WRITE should be tested since we are in a SharedData case.
        result.setSendProtocol(communicationLinkProtocol);
      } else if ((CommunicationLinkKind.RECEIVE == communicationLinkKind) || (CommunicationLinkKind.CONSUME == communicationLinkKind)
                 || (CommunicationLinkKind.EXECUTE == communicationLinkKind) || (CommunicationLinkKind.ACCESS == communicationLinkKind)
                 || (CommunicationLinkKind.ACQUIRE == communicationLinkKind)) {
        // Actually, only ACCESS should be tested since we are in a SharedData case.
        result.setReceiveProtocol(communicationLinkProtocol);
      }
      // Case UNSET -> nothing to do...
    }
    return result;
  }

  /**
   * Create the restricted tree viewer button to display or not restricted interfaces.
   * @param control
   */
  private void createRestrictedTreeViewerButton(Composite parent) {
    _restrictedInterfaceFilter = new ViewerFilter() {
      /**
       * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element) {
        if ((element instanceof ExchangeItemAllocation) || (element instanceof ExchangeItem)) {
          return _restrictedElements.contains(element);
        } else if (element instanceof AbstractEventOperation) {
          return true; // this is not the filter which decides
        } else if (element instanceof CapellaElement) {
          // Recursive case: this element should only be displayed if its direct or indirect "contained" contains one of _restrictedElement
          TreeIterator<EObject> iterator = ((CapellaElement) element).eAllContents();
          while (iterator.hasNext()) {
            EObject obj = iterator.next();
            if (_restrictedElements.contains(obj)) {
              return true;
            }
          }
          return false; // capellaElement without a restricted object
          // in its contents
        }
        return true;
      }
    };

    _restrictedTreeViewerButton = new Button(parent, SWT.CHECK);
    _restrictedTreeViewerButton.setText(Messages.SelectOperationDialog_RestrictedInterfacesButton_Title);
    _restrictedTreeViewerButton.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {

        if (((Button) event.widget).getSelection()) {
          // Add a viewer filter to filter out restricted
          // interface.
          if (null != _restrictedInterfaceFilter) {
            getViewer().getClientViewer().addFilter(_restrictedInterfaceFilter);
          }
        } else {
          // Remove the filter.
          if (null != _restrictedInterfaceFilter) {
            getViewer().getClientViewer().removeFilter(_restrictedInterfaceFilter);
          }
        }
      }
    });
    _restrictedTreeViewerButton.setSelection(true);

    getViewer().getClientViewer().addSelectionChangedListener(new ISelectionChangedListener() {

      public void selectionChanged(SelectionChangedEvent event) {
        updateWindow();
      }
    });
    getViewer().getClientViewer().addFilter(_restrictedInterfaceFilter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getTreeViewerStyle() {
    return IViewerStyle.SHOW_STATUS_BAR;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog#createTreeViewerPart(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createTreeViewerPart(Composite parent) {
    // Add a group surrounding the tree viewer part.
    Group treeViewerPartGroup = new Group(parent, SWT.NONE);
    treeViewerPartGroup.setText(Messages.SelectOperationDialog_SelectExistingOperationGroup_Title);
    treeViewerPartGroup.setLayout(new GridLayout());
    treeViewerPartGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    super.createTreeViewerPart(treeViewerPartGroup);
    if (_elementSupportedType == ElementSupportedType.OPERATION) {
      createRestrictedTreeViewerButton(getViewer().getControl());
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    // Create Operation creation part.
    if (_elementSupportedType == ElementSupportedType.OPERATION) {
      // don't have the "create exchangeItem" part if the source or target
      // of the message
      // is an exchange item, it will be it. But we keep the create
      // interface part
      // to allow creation of a exchangeItemAllocation
      if (!InterfaceHelper.isSharedDataAccess(_sourceIR, _targetIR)) {
        createCreationOperationPart(parent);
      }

      createInterfacePart(parent);
      super.doCreateDialogArea(parent);

      // create a button to show/hide exchange items
      createShowCEButton(parent);

      // Add a selection handler to all the selection of an existing
      // interface.
      configureSelectInterfaceButtonHandler();
      createCreatePortButton(parent);

      // Add a selection handler to enable / disable creation of an
      // Operation.
      // We must call that here only to make sure all widgets are created.
      configureEnableCreationButtonHandler();
    } else {

      super.doCreateDialogArea(parent);
    }
  }

  /**
   * @param parent
   */
  private void createShowCEButton(Composite parent) {
    _showComponentExchangeFilter = new ViewerFilter() {

      @SuppressWarnings("synthetic-access")
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof ExchangeItemAllocation) {
          return true; // this is not the filter which decides
        } else if (element instanceof AbstractEventOperation) {
          return _restrictedElements.contains(element);
        } else if (element instanceof CapellaElement) {
          //Recursive case: this element should only be displayed if its direct or indirect "contained" contains one of _restrictedElement
          TreeIterator<EObject> iterator = ((CapellaElement) element).eAllContents();
          while (iterator.hasNext()) {
            EObject obj = iterator.next();
            if (_restrictedElements.contains(obj)) {
              return true;
            }
          }
          return false; // capellaElement without a restricted object
          // in its contents
        }
        return true;
      }

    };

    _showComponentExchanges = new Button(getViewer().getControl(), SWT.CHECK);
    _showComponentExchanges.setText(Messages.SelectOperationDialog_1);
    _showComponentExchanges.setSelection(true);
    _showComponentExchanges.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        if (((Button) event.widget).getSelection()) {
          // Add a viewer filter to filter view component exchanges.
          if (null != _showComponentExchangeFilter) {
            getViewer().getClientViewer().removeFilter(_showComponentExchangeFilter);
          }
        } else {
          // Remove the filter.
          if (null != _showComponentExchangeFilter) {
            getViewer().getClientViewer().addFilter(_showComponentExchangeFilter);
          }
        }

      }

    });
  }

  @Override
  public void create() {
    super.create();
    getShell().setMinimumSize(661, 800);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog#handleResult()
   */
  @Override
  protected List<? extends EObject> handleResult() {
    CapellaElement getAnExchangeItemSelected = getAnExchangeItemSelected();
    _isPortStrategy = _createPortsButton.getSelection();

    // If not checked, the end-user has selected an existing operation.
    if ((_elementSupportedType == ElementSupportedType.EXCHANGE)
        || ((_enableCreationButton != null) && !_enableCreationButton.getSelection() && (getAnExchangeItemSelected == null))) {
      return super.handleResult();
    }

    if ((getAnExchangeItemSelected instanceof ExchangeItemAllocation) && ((_enableCreationButton == null) || !_enableCreationButton.getSelection())) {
      return Collections.singletonList(getAnExchangeItemSelected);
    }
    // It's an exchangeItem
    ExchangeItemAllocation allocation;
    if ((getAnExchangeItemSelected != null) && ((_enableCreationButton == null) || !_enableCreationButton.getSelection())) {
      allocation = allocateExchangeItem((ExchangeItem) getAnExchangeItemSelected);
    } else {
      allocation = allocateExchangeItem(createExchangeItem());
    }
    // The end-user has created a new operation.
    return Collections.singletonList(allocation);
  }

  private CapellaElement getAnExchangeItemSelected() {
    if (getViewer() == null) {
      return null;
    }
    ISelection selection = getViewer().getSelection();
    if (selection == null) {
      return null;
    }
    if (!(selection instanceof TreeSelection)) {
      return null;
    }
    Object selectionItem = ((TreeSelection) selection).getFirstElement();
    if (selectionItem == null) {
      return null;
    }
    if (selectionItem instanceof ExchangeItem) {
      return (ExchangeItem) selectionItem;
    }
    if (selectionItem instanceof ExchangeItemAllocation) {
      return (ExchangeItemAllocation) selectionItem;
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.SelectElementsDialog#isOkToClose(org.eclipse.jface.viewers.ISelection)
   */
  @Override
  protected boolean isOkToClose(ISelection selection) {
    CapellaElement getAnExchangeItemSelected = getAnExchangeItemSelected();

    // If not checked, or no selection of exchangeItem in the list, the
    // end-user has selected an existing allocation.
    if ((_elementSupportedType == ElementSupportedType.EXCHANGE)
        || ((_enableCreationButton != null) && !_enableCreationButton.getSelection() && (getAnExchangeItemSelected == null))) {
      return super.isOkToClose(selection);
    }

    boolean interfaceSet = !_interfaceText.getText().equals(ICommonConstants.EMPTY_STRING);
    boolean exchangeItemSelected = getAnExchangeItemSelected instanceof ExchangeItem;
    boolean exchangeItemAllocationSelected = getAnExchangeItemSelected instanceof ExchangeItemAllocation;
    boolean operationSet = (((_operationText != null) && !_operationText.getText().equals(ICommonConstants.EMPTY_STRING)));

    if (exchangeItemAllocationSelected) {
      return true;
    }
    if (exchangeItemSelected) {
      return interfaceSet;
    }

    // nothing is selected into the tree.
    return (interfaceSet && operationSet);
  }

  /**
   * @return
   */
  public boolean isPortStrategy() {
    return _isPortStrategy;
  }
}
