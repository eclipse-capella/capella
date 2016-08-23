/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.DataValueExt;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;


/**
 * Set Name of the referenced value of current marker element
 */
public class DataValueReferenceResolver extends AbstractCapellaMarkerResolution {

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		final EObject value = getModelElements(marker).get(0);
		if (null != value) {
			DataValue referencedValue = DataValueExt.getReferencedValue(value);
			if (null != referencedValue) {
				if (referencedValue.getName() == null || 
						referencedValue.getName().equals(ICommonConstants.EMPTY_STRING)) {
					CapellaUIPropertiesPlugin.getDefault().openWizard(referencedValue);					
				}
			}
		}
	}
@Override
protected String[] getResolvableRuleIds() {
	return noRuleIds;
}
}
