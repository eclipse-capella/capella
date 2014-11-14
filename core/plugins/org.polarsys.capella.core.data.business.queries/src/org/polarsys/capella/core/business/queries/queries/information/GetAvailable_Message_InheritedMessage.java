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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

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
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.GenericPkgExt;
import org.polarsys.capella.core.model.helpers.MessageExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_Message_InheritedMessage extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
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
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		boolean isMessageFromSharedPkg = false;
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element_p);
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
		if (element_p instanceof Message) {
			Message currentMessage = (Message) element_p;
			if (!isMessageFromSharedPkg) {
				availableElements.addAll(getRule_MQRY_Message_Inherited_11(currentMessage));
				availableElements.addAll(getRule_MQRY_Message_Inherited_12(currentMessage));
				availableElements.addAll(getRule_MQRY_Message_Inherited_13(currentMessage));
			}
			availableElements.addAll(getRule_MQRY_Message_Inherited_14(currentMessage, systemEngineering));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_11(Message currentMessage_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage_p);
		if (null != messagePkg) {
			for (Message message : DataPkgExt.getAllMessages(messagePkg)) {
				if ((message == null) || (message.equals(currentMessage_p)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
					availableElements.add(message);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_12(Message currentMessage_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage_p);
		if (null != messagePkg) {
			ComponentArchitecture arch = DataPkgExt.getRootComponentArchitecture(messagePkg);
			if (null != arch) {
				DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(arch);
				if (null != dataPkg) {
					for (Message message : DataPkgExt.getAllMessages(dataPkg)) {
						if ((message == null) || (message.equals(currentMessage_p)))
							continue;
						if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
								&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
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
							if ((message == null) || (message.equals(currentMessage_p)))
								continue;
							if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
									&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
								availableElements.add(message);
							}
						}
					}
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_13(Message currentMessage_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		DataPkg messagePkg = MessageExt.getRootOwnerDataPkg(currentMessage_p);
		if (null != messagePkg) {
			for (Message message : DataPkgExt.getMessagesFromParentHierarchy(messagePkg)) {
				if ((message == null) || (message.equals(currentMessage_p)))
					continue;
				if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
						&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
					availableElements.add(message);
				}
			}
		}
		return availableElements;
	}

	private List<CapellaElement> getRule_MQRY_Message_Inherited_14(Message currentMessage_p, SystemEngineering systemEngineering_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		for (SharedPkg sharedPkg : SystemEngineeringExt.getSharedPkgs(systemEngineering_p)) {
			DataPkg dataPkg = sharedPkg.getOwnedDataPkg();
			if (null != dataPkg) {
				for (Message message : DataPkgExt.getAllMessages(dataPkg)) {
					if ((message == null) || (message.equals(currentMessage_p)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
						availableElements.add(message);
					}
				}
			}
			GenericPkg pkg = sharedPkg.getOwnedGenericPkg();
			if (pkg != null) {
				for (Message message : GenericPkgExt.getAllMessages(pkg)) {
					if ((message == null) || (message.equals(currentMessage_p)))
						continue;
					if (!GeneralizableElementExt.getAllSuperGeneralizableElements(currentMessage_p).contains(message)
							&& !GeneralizableElementExt.getAllSuperGeneralizableElements(message).contains(currentMessage_p)) {
						availableElements.add(message);
					}
				}
			}
		}
		return availableElements;
	}

}