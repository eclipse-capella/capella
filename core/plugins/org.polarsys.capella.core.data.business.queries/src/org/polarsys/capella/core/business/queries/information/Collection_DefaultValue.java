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
package org.polarsys.capella.core.business.queries.information;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;

/**
 * This is the query for collections default values.
 * 
 * */
public class Collection_DefaultValue extends
		AbstractMultiplicityElement_DefaultValue {

	/**
	 * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
	 *      org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	@Override
	public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p,
			CapellaElement capellaElement_p) {
		// get standard data from abstract class
		// filter based on 'Type'
		List<CapellaElement> dataFromLevel = super.getDataFromLevel(dataPkg_p,
				capellaElement_p);
		// get data considering the GeneralizableElement All Super Elements for
		// type compare
		// filter based on 'Super'
		if (capellaElement_p instanceof MultiplicityElement) {
			dataFromLevel.addAll(CapellaElementsHelperForBusinessQueries
					.getApplicableValuesForMultEleConsideringSuperGenElements(
							dataPkg_p, (MultiplicityElement) capellaElement_p,
							getEStructuralFeatures()));
		}
		return dataFromLevel;
	}

	/**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
	 */
	public EClass getEClass() {
		return InformationPackage.Literals.COLLECTION;
	}
}
