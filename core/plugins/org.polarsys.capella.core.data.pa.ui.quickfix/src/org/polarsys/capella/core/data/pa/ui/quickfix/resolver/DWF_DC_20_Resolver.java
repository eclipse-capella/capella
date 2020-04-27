/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * Delete Allocated Functions
 */
public class DWF_DC_20_Resolver extends AbstractDeleteCommandResolver {

	public Object getElementToDelete(Object obj) {
		final List<Object> linksToDelete = new ArrayList<Object>();
		if ((null != obj) && (obj instanceof PhysicalComponent)) {
			PhysicalComponent physicalComponent = (PhysicalComponent) obj;

			if (physicalComponent.getNature() == PhysicalComponentNature.NODE) {
				EList<AbstractTrace> outgoingTraces = physicalComponent
						.getOutgoingTraces();

				for (AbstractTrace abstractTrace : outgoingTraces) {
					if (abstractTrace instanceof ComponentFunctionalAllocation) {
						linksToDelete.add(abstractTrace);
					}
				}
			}
		}
		return linksToDelete;
	}

}
