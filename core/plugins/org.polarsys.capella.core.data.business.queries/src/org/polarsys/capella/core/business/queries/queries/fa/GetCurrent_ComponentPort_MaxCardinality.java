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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

@Deprecated
public class GetCurrent_ComponentPort_MaxCardinality extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> currentElements = getCurrentElements(capellaElement, false);
		return (List) currentElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
//		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
//		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
//		if (null == systemEngineering) {
//			SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
//			for (ReuseLink link : sharedPkg.getReuseLinks()) {
//				if (SystemEngineeringExt.getSystemEngineering(link) != null) {
//					systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
//					break;
//				}
//			}
//			if (systemEngineering == null)
//				return currentElements;		
//		}
//		if (element instanceof ComponentPort) {
//			ComponentPort property = (ComponentPort) element;
//			if (property.getOwnedMaxCard() != null) {
//				currentElements.add(property.getOwnedMaxCard());
//			}
//		}
//		return currentElements;
		throw new UnsupportedOperationException();
	}

}