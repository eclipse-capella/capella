/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.extendedqueries.capellacommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_CapellaElement_AppliedPropertyValues_Lib extends
		AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		BlockArchitecture currentBlock = BlockArchitectureExt
				.getRootBlockArchitecture(element);
		IModel currentProject = ILibraryManager.INSTANCE.getModel(element);
		if (element instanceof CapellaElement) {
			Collection<IModel> libraries = LibraryManagerExt
					.getAllActivesReferences(currentProject);
			for (IModel library : libraries) {
				if (library instanceof CapellaModel) {
					BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt
							.getCorrespondingElementInLibrary(currentBlock,
									(CapellaModel) library);
					SystemEngineering systemEngineering = (SystemEngineering) correspondingBlock
							.eContainer();
					for (ModellingArchitecture anArchitecture : systemEngineering
							.getOwnedArchitectures()) {
						for (EObject pVG : EObjectExt
								.getAll(anArchitecture,
										CapellacorePackage.Literals.ABSTRACT_PROPERTY_VALUE)) {
							availableElements.add((CapellaElement) pVG);
						}
					}
				}
				availableElements = ListExt.removeDuplicates(availableElements);
			}
		}
		return availableElements;
	}
}