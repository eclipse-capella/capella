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

package org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.CompatibilityDefinition;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.CreationAlgorithms;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.OptionsDefinition;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.algorithms.SelectionAlgorithms;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.InterfaceCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.LinkCommunication;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.UndefinedCommunication;

public class SelectInvokedOperationModel implements ISelectInvokedOperationModel {

  protected CompatibilityDefinition compatibilityDef = CompatibilityDefinition.INSTANCE;
  protected CreationAlgorithms creationAlgos = CreationAlgorithms.INSTANCE;
  protected OptionsDefinition optionsDefinition = OptionsDefinition.INSTANCE;

  protected InstanceRole sourceIR;
  protected InstanceRole targetIR;
  protected Component source;
  protected Component target;
  protected List<AbstractCommunication> possibleElements;
  protected List<AbstractCommunication> selectableElements;
  protected List<EObject> selectableElementsContainers;
  protected List<AbstractCommunication> communications;
  protected List<InterfaceCommunication> interfaceCommunications;
  protected List<LinkCommunication> linkCommunications;
  protected List<UndefinedCommunication> undefinedCommunications;
  protected MessageKind messageKind;
  protected boolean withReturn;
  protected boolean hideTechnicalInterfaceNames = true;
  protected boolean restrictToExistingStaticCommunicationCompatibility = true;
  protected boolean allowSelectionOfExistingExchangeItems = false;
  protected boolean elementMustBeCreated = false;
  protected boolean portsMustBeCreated = false;
  protected boolean portsCreationCanBeToggled = false;
  protected boolean communicationLinksMustBeCreated = false;
  protected boolean communicationLinksCreationCanBeToggled = false;
  protected boolean validInterfaceName = true;
  protected AbstractCommunication selectedElement;
  protected AbstractCommunication oldSelectedElement;
  protected String createdElementName = ""; //$NON-NLS-1$
  protected ExchangeMechanism selectedExchangeMechanism;
  protected Interface selectedInterface;
  protected String selectedInterfaceName = ""; //$NON-NLS-1$
  protected boolean interfaceNameCanBeEdited = false;
  protected boolean interfaceCanBeChosen = false;
  protected Interface technicalInterface;
  protected String defaultInterfaceName;
  protected List<Interface> allInterfacesBetweenSourceAndTarget;
  protected List<Interface> allStructuralInterfaces;
  protected List<Interface> restrictedStructuralInterfaces;
  protected List<Interface> allTechnicalInterfaces;

  public SelectInvokedOperationModel(InstanceRole sourceIR, InstanceRole targetIR, boolean withReturn) {
    this.sourceIR = sourceIR;
    this.targetIR = targetIR;
    this.source = (Component) this.sourceIR.getRepresentedInstance().getAbstractType();
    this.target = (Component) this.targetIR.getRepresentedInstance().getAbstractType();
    this.withReturn = withReturn;
    this.messageKind = SelectionAlgorithms.getDefaultMessageKind(this.withReturn);
    this.selectedExchangeMechanism = getCompatibleExchangeMechanism().get(0);
    initializeModel();
    computePossibleElements();
    computeSelectableElements();
    this.elementMustBeCreated = this.elementMustBeCreated && (this.selectableElements.size() == 0);
  }

  @Override
  public boolean isValid() {
    boolean interfaceSet = !selectedInterfaceName.equals(ICommonConstants.EMPTY_STRING);
    boolean createdElementNameSet = ((!createdElementName.equals(ICommonConstants.EMPTY_STRING)));
    return validInterfaceName && interfaceSet && ((elementMustBeCreated && createdElementNameSet) || (!elementMustBeCreated && (selectedElement != null)));
  }

  @Override
  public AbstractCommunication getSelectedElement() {
    return selectedElement;
  }
  
  public InstanceRole getSourceInstanceRole() {
    return sourceIR;
  }
  
  public InstanceRole getTargetInstanceRole() {
    return targetIR;
  }

  public void setSelectedElement(AbstractCommunication selectedElement) {
    this.selectedElement = selectedElement;
    if ((selectedElement != oldSelectedElement) || elementMustBeCreated) {
      // port creation option
      portsMustBeCreated = optionsDefinition.getDefaultValueForOptionD(selectedElement);
      portsCreationCanBeToggled = optionsDefinition.doesOptionDIsTogglable(selectedElement);

      // communication link option
      communicationLinksMustBeCreated = optionsDefinition.getDefaultValueForOptionE(selectedElement);
      communicationLinksCreationCanBeToggled = optionsDefinition.doesOptionEIsTogglable(selectedElement);

      // interface and interface name value
      if ((selectedElement == null) && !elementMustBeCreated) {
        interfaceNameCanBeEdited = false;
        interfaceCanBeChosen = false;
        selectedInterface = null;
        selectedInterfaceName = ""; //$NON-NLS-1$
      } else if (elementMustBeCreated || (selectedElement instanceof UndefinedCommunication)) {
        // case creation of EI or reuse of orphan EI
        interfaceNameCanBeEdited = true;
        interfaceCanBeChosen = allStructuralInterfaces.size() > 0;
        if (restrictedStructuralInterfaces.size() > 0) {
          Interface interfaze = restrictedStructuralInterfaces.get(0);
          selectedInterface = interfaze;
          selectedInterfaceName = interfaze.getName();
        } else {
          selectedInterface = null;
          selectedInterfaceName = creationAlgos.getDefaultInterfaceName(sourceIR, targetIR, allInterfacesBetweenSourceAndTarget);
        }
      } else if (selectedElement instanceof CommunicationLink) {
        // case communication by CL
        interfaceNameCanBeEdited = true;
        interfaceCanBeChosen = false;
        if (technicalInterface != null) {
          selectedInterface = technicalInterface;
          selectedInterfaceName = selectedInterface.getName();
        } else {
          selectedInterface = null;
          selectedInterfaceName = defaultInterfaceName;
        }
      } else if (selectedElement instanceof ExchangeItemAllocation) {
        // case communication by interface
        interfaceNameCanBeEdited = false;
        interfaceCanBeChosen = false;
        ExchangeItemAllocation allocation = (ExchangeItemAllocation) selectedElement;
        selectedInterface = allocation.getAllocatingInterface();
        selectedInterfaceName = selectedInterface.getName();
      }
    }
    oldSelectedElement = selectedElement;
  }

  private void initializeModel() {
    technicalInterface = SelectionAlgorithms.getTechnicalInterface(source, target);
    allInterfacesBetweenSourceAndTarget = getInterfaces(false, false);
    allStructuralInterfaces = getInterfaces(true, false);
    allTechnicalInterfaces = new ArrayList<Interface>(allInterfacesBetweenSourceAndTarget);
    allTechnicalInterfaces.removeAll(allStructuralInterfaces);
    restrictedStructuralInterfaces = getInterfaces(true, true);
    defaultInterfaceName = creationAlgos.getDefaultInterfaceName(sourceIR, targetIR, allInterfacesBetweenSourceAndTarget);
    interfaceCommunications = SelectionAlgorithms.getInterfaceCommunications(source, target);
    if ((selectedElement != null) && !selectableElements.contains(selectedElement)) {
      setSelectedElement(null);
    }
    linkCommunications = SelectionAlgorithms.getLinkCommunications(source, target);
    communications = new ArrayList<AbstractCommunication>();
    communications.addAll(interfaceCommunications);
    communications.addAll(linkCommunications);
    undefinedCommunications = SelectionAlgorithms.getUndefinedCommunications(source, target, communications);
    communications.addAll(undefinedCommunications);
  }

  private void computePossibleElements() {
    possibleElements = new ArrayList<AbstractCommunication>();
    for (AbstractCommunication com : communications) {
      possibleElements.add(com);
    }
  }

  private void computeSelectableElements() {
    selectableElements = new ArrayList<AbstractCommunication>();
    for (InterfaceCommunication com : interfaceCommunications) {
      if (!restrictToExistingStaticCommunicationCompatibility || (restrictToExistingStaticCommunicationCompatibility && !com.isPartial)) {
        if (compatibilityDef.isCompatible(com.toCommunicationInfo(), messageKind, withReturn)) {
          selectableElements.add(com);
        }
      }
    }
    for (LinkCommunication com : linkCommunications) {
      if (!restrictToExistingStaticCommunicationCompatibility || (restrictToExistingStaticCommunicationCompatibility && !com.isPartial)) {
        if (compatibilityDef.isCompatible(com.toCommunicationInfo(), messageKind, withReturn)) {
          selectableElements.add(com);
        }
      }
    }
    if (doesAllowSelectionOfExistingExchangeItems()) {
      for (UndefinedCommunication com : undefinedCommunications) {
        if (compatibilityDef.isCompatible(com.toCommunicationInfo(), messageKind, withReturn)) {
          selectableElements.add(com);
        }
      }
    }
    if ((selectedElement != null) && !selectableElements.contains(selectedElement)) {
      setSelectedElement(null);
    }
    // create the set of all containers of selectable elements
    selectableElementsContainers = new ArrayList<EObject>();
    for (AbstractCommunication com : selectableElements) {
      EObject object = com.getRepresentativeElement();
      EObject container = object.eContainer();
      while ((container != null) && !selectableElementsContainers.contains(container)) {
        selectableElementsContainers.add(container);
        container = container.eContainer();
      }
    }
  }

  @Override
  public boolean doesInterfaceNameCanBeEdited() {
    return interfaceNameCanBeEdited;
  }

  @Override
  public boolean doesInterfaceCanBeChosen() {
    return interfaceCanBeChosen;
  }

  @Override
  public boolean doesElementMustBeCreated() {
    return elementMustBeCreated;
  }

  public void setElementMustBeCreated(boolean state) {
    elementMustBeCreated = state;
  }

  @Override
  public boolean doesCommunicationLinksMustBeCreated() {
    return communicationLinksMustBeCreated;
  }

  @Override
  public boolean doesCommunicationLinksCreationCanBeToggled() {
    return communicationLinksCreationCanBeToggled;
  }

  public void setCommunicationLinksMustBeCreated(boolean state) {
    communicationLinksMustBeCreated = state;
  }

  @Override
  public boolean doesPortsMustBeCreated() {
    return portsMustBeCreated;
  }

  @Override
  public boolean doesPortsCreationCanBeToggled() {
    return elementMustBeCreated || (selectedElement instanceof ExchangeItem);
  }

  public void setPortsMustBeCreated(boolean state) {
    portsMustBeCreated = state;
  }

  @Override
  public boolean doesTheMessageReturnAValue() {
    return withReturn;
  }

  @Override
  public boolean doesHideTechnicalInterfaceNames() {
    return hideTechnicalInterfaceNames;
  }

  public void setHideTechnicalInterfaceNames(boolean state) {
    hideTechnicalInterfaceNames = state;
  }

  @Override
  public boolean doesRestrictToExistingStaticCommunicationCompatibility() {
    return restrictToExistingStaticCommunicationCompatibility;
  }

  public void setMessageKind(MessageKind messageKind) {
    this.messageKind = messageKind;
    computeSelectableElements();
  }

  public void setRestrictToExistingStaticCommunicationCompatibility(boolean state) {
    restrictToExistingStaticCommunicationCompatibility = state;
    computeSelectableElements();
  }

  @Override
  public boolean doesAllowSelectionOfExistingExchangeItems() {
    return allowSelectionOfExistingExchangeItems;
  }

  public void setAllowSelectionOfExistingExchangeItems(boolean state) {
    allowSelectionOfExistingExchangeItems = state;
    computeSelectableElements();
  }

  public void setCreatedElementName(String createdElementName) {
    this.createdElementName = createdElementName;
  }

  public void setSelectedExchangeMechanism(ExchangeMechanism selectedExchangeMechanism) {
    this.selectedExchangeMechanism = selectedExchangeMechanism;
  }

  public void setSelectedInterfaceName(String selectedInterfaceName) {
    this.selectedInterfaceName = selectedInterfaceName;
    if ((selectedInterface != null) && !selectedInterfaceName.equals(selectedInterface.getName())) {
      selectedInterface = null;
    }
    validInterfaceName = true;
    if (elementMustBeCreated || (selectedElement instanceof UndefinedCommunication)) {
      validInterfaceName = !creationAlgos.interfaceExistWithName(selectedInterfaceName, allTechnicalInterfaces);
    }
  }

  public boolean doesInterfaceExist() {
    return creationAlgos.interfaceExistWithName(selectedInterfaceName, allInterfacesBetweenSourceAndTarget);
  }

  @Override
  public String getSelectedInterfaceName() {
    return selectedInterfaceName;
  }

  public void setSelectedInterface(Interface selectedInterface) {
    this.selectedInterface = selectedInterface;
  }

  @Override
  public List<AbstractCommunication> getSelectableElements() {
    return selectableElements;
  }

  @Override
  public List<EObject> getSelectableElementContainers() {
    return selectableElementsContainers;
  }

  @Override
  public List<AbstractCommunication> getPossibleElements() {
    return possibleElements;
  }

  @Override
  public MessageKind getMessageKind() {
    return messageKind;
  }

  @Override
  public List<Interface> getInterfaces(boolean onlyStructural, boolean restrictToStaticCommunications) {
    List<Interface> res = new ArrayList<Interface>();
    IBusinessQuery query =
        BusinessQueriesProvider.getInstance().getContribution(InteractionPackage.Literals.SEQUENCE_MESSAGE,
            InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END);
    for (EObject capellaElement : query.getAvailableElements(sourceIR)) {
      Interface interface_ = (Interface) capellaElement;
      if (!onlyStructural || interface_.isStructural()) {
        if (!restrictToStaticCommunications || creationAlgos.isGoodInterface(interface_, sourceIR, targetIR)) {
          res.add(interface_);
        }
      }
    }
    return res;
  }

  public boolean doesInterfaceToCreateIsStructural() {
    return !(selectedElement instanceof CommunicationLink);
  }

  @Override
  public List<ExchangeMechanism> getCompatibleExchangeMechanism() {
    return compatibilityDef.getCompatibleExchangeMechanismFor(withReturn);
  }

  @Override
  public CapellaElement createOrUpdateElement() {
    // create/rename the interface if necessary
    if (selectedInterface == null) {
      selectedInterface = creationAlgos.createInterface(selectedInterfaceName, sourceIR, targetIR, doesInterfaceToCreateIsStructural());
    } else if (!selectedInterface.getName().equals(selectedInterfaceName)) {
      selectedInterface.setName(selectedInterfaceName);
    }
    // get the corresponding communication or create it if the user select exchange item creation
    AbstractCommunication com = selectedElement;
    if (elementMustBeCreated) {
      ExchangeItem item = creationAlgos.createExchangeItem(createdElementName, selectedInterface, selectedExchangeMechanism);
      com = new UndefinedCommunication(item);
    }
    // update information of the communication for further treatment
    com.source = source;
    com.target = target;
    com.interfaze = selectedInterface;
    // get or create the allocation and update it so that it is compatible
    if (com.exchangeItemAllocation == null) {
      com.exchangeItemAllocation = creationAlgos.getOrCreateAllocation(com, messageKind, withReturn);
    }
    // update port communication or interface communication, depending of the user choice
    if (!portsMustBeCreated) {
      creationAlgos.updateInterfaceCommunication(com);
    }
    // update link communication if necessary
    if (communicationLinksMustBeCreated) {
      creationAlgos.updateLinkCommunication(com, messageKind, withReturn);
    }
    return com.exchangeItemAllocation;
  }

  public ExchangeMechanism getSelectedExchangeMechanism() {
    return selectedExchangeMechanism;
  }

  public boolean isValidInterfaceName() {
    return validInterfaceName;
  }

}
