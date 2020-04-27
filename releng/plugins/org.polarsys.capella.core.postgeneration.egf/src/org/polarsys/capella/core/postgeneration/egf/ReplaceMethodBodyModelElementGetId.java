/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.postgeneration.egf;

/**
 */
public class ReplaceMethodBodyModelElementGetId extends AbstractReplaceMethodBody {

	/**
	 * {@inheritDoc}
	 */
	protected String getMethodBody() {
		return
		"String id = (String) eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID, true);" +
		"if (null == id) {" +
		"  id = org.polarsys.capella.common.lib.IdGenerator.createId();" +
		"  int featureId = eDerivedStructuralFeatureID(ModellingcorePackage.Literals.MODEL_ELEMENT__ID);" +
		"  eSettings().dynamicSet(featureId, id);" +
		"}" +
		"return id;";
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getMethodName() {
		return "getId";
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getFilePath() {
		return "org.polarsys.capella.common.data.core.gen.cdo/generated/org/polarsys/capella/common/data/modellingcore/impl/ModelElementImpl.java";
	}
}
