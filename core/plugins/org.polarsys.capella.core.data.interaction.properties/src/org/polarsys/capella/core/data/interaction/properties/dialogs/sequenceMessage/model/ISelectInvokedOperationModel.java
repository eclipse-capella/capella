/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.dialogs.sequenceMessage.model.communications.AbstractCommunication;

// Keep the MVC pattern safe ! Do not add setters here (use the controller to set model attributes).
public interface ISelectInvokedOperationModel {

	boolean doesElementMustBeCreated();
	boolean doesCommunicationLinksMustBeCreated();
	boolean doesCommunicationLinksCreationCanBeToggled();
	boolean doesPortsMustBeCreated();
	boolean doesPortsCreationCanBeToggled();
	boolean doesTheMessageReturnAValue();
	boolean doesHideTechnicalInterfaceNames();
	boolean doesRestrictToExistingStaticCommunicationCompatibility();
	boolean doesAllowSelectionOfExistingExchangeItems();
	List<AbstractCommunication> getSelectableElements();
	List<EObject> getSelectableElementContainers();
	List<AbstractCommunication> getPossibleElements();
	List<Interface> getInterfaces(boolean onlyStructural, boolean restrictToStaticCommunications);
	MessageKind getMessageKind();
	List<ExchangeMechanism> getCompatibleExchangeMechanism();
	CapellaElement createOrUpdateElement();
	boolean isValid();
	AbstractCommunication getSelectedElement();
	boolean doesInterfaceNameCanBeEdited();
	boolean doesInterfaceCanBeChosen();
	String getSelectedInterfaceName();
}