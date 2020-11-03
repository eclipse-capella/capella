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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetCurrent_Message_InheritedMessage extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> currentElements = getCurrentElements(capellaElement, false);
		return (List) currentElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<EObject> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<EObject> currentElements = new ArrayList<EObject>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
			for (ReuseLink link : sharedPkg.getReuseLinks()) {
				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
					break;
				}
			}
			if (systemEngineering == null)
				return currentElements;
		}
		if (element instanceof Message) {
			Message currentMessage = (Message) element;
			currentElements.addAll(currentMessage.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(currentMessage);
		}
		return currentElements;
	}

}