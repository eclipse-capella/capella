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

	public boolean doesElementMustBeCreated();
	public boolean doesCommunicationLinksMustBeCreated();
	public boolean doesCommunicationLinksCreationCanBeToggled();
	public boolean doesPortsMustBeCreated();
	public boolean doesPortsCreationCanBeToggled();
	public boolean doesTheMessageReturnAValue();
	public boolean doesHideTechnicalInterfaceNames();
	public boolean doesRestrictToExistingStaticCommunicationCompatibility();
	public boolean doesAllowSelectionOfExistingExchangeItems();
	public List<AbstractCommunication> getSelectableElements();
	public List<EObject> getSelectableElementContainers();
	public List<AbstractCommunication> getPossibleElements();
	public List<Interface> getInterfaces(boolean onlyStructural, boolean restrictToStaticCommunications);
	public MessageKind getMessageKind();
	public List<ExchangeMechanism> getCompatibleExchangeMechanism();
	public CapellaElement createOrUpdateElement();
	public boolean isValid();
	public AbstractCommunication getSelectedElement();
	public boolean doesInterfaceNameCanBeEdited();
	public boolean doesInterfaceCanBeChosen();
	public String getSelectedInterfaceName();
}