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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.MessageExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Message_InheritedMessage extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * Gets ...
	 * <p>
	 * All the Messages contained by the Message Package (and all of its
	 * sub-packages) of the current Element.
	 * </p>
	 * <p>
	 * All the Messages contained by the Message Package (and all of its
	 * sub-packages) of the current Element's parent (can be a Component, a
	 * Component Architecture Decomposition package, or a Component Architecture
	 * root package).
	 * </p>
	 * <p>
	 * All the Messages contained by the Message Package (and all of its
	 * sub-packages) of the current Element's parents hierarchy.
	 * </p>
	 * <p>
	 * All the Messages contained by the Message Package (and all of its
	 * sub-packages) of the Shared Package.
	 * </p>
	 * <p>
	 * Except The current Message itself and The Messages in the inheritance
	 * hierarchy of the current Message
	 * </p>
	 * <p>
	 * Refer MQRY_Message_Inherited_1
	 * </p>
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		boolean isMessageFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					isMessageFromSharedPkg = true;
					break;
				}
			}
			if (systemEngineering == null)
				return availableElements;
		}
		if (element instanceof Message) {
			Message currentMessage = (Message) element;
			if (!isMessageFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Message_Inherited_11(currentMessage));
				availableElements.addAll(getRule_MQRY_Message_Inherited_12(currentMessage));
				availableElements.addAll(getRule_MQRY_Message_Inherited_13(currentMessage));
			}
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_11(Message currentMessage) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage);
		if (null != messagePkg) {
			for (Message message : DataPkgExt.getAllMessages(messagePkg)) {
				if ((message == null) || (message.equals(currentMessage)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage).contains(message)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage)) {
					availableElements.add(message);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_12(Message currentMessage) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage);
		if (null != messagePkg) {
			ComponentArchitecture arch = DataPkgExt.getRootComponentArchitecture(messagePkg);
			if (null != arch) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
				if (null != dataPkg) {
					for (Message message : DataPkgExt.getAllMessages(dataPkg)) {
						if ((message == null) || (message.equals(currentMessage)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage).contains(message)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage)) {
							availableElements.add(message);
						}
					}
				}
			}
			Component comp = DataPkgExt.getRootComponent(messagePkg);
			if (null != comp) {
				if (comp instanceof LogicalComponent) {
					DataPkg dataPkg = ((LogicalComponent) comp).getOwnedDataPkg();
					if (null != dataPkg) {
						for (Message message : DataPkgExt.getAllMessages(dataPkg)) {
							if ((message == null) || (message.equals(currentMessage)))
								continue;
							if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage).contains(message)
									&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage)) {
								availableElements.add(message);
							}
						}
					}
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_13(Message currentMessage) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage);
		if (null != messagePkg) {
			for (Message message : DataPkgExt.getMessagesFromParentHierarchy(messagePkg)) {
				if ((message == null) || (message.equals(currentMessage)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage).contains(message)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage)) {
					availableElements.add(message);
				}
			}
		}
		return availableElements;
	}

}