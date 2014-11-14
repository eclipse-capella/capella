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
package org.polarsys.capella.common.data.helpers.modellingcore;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.ModelElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.TraceableElementHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class ModellingCoreHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
		Object ret = null;

		if (object_p instanceof TraceableElement) {
			ret = TraceableElementHelper.getInstance().doSwitch((TraceableElement) object_p, feature_p);
		}
    else if (object_p instanceof InformationsExchanger) {
      ret = InformationsExchangerHelper.getInstance().doSwitch((InformationsExchanger) object_p, feature_p);
    }
		else if (object_p instanceof AbstractType) {
			ret = AbstractTypeHelper.getInstance().doSwitch((AbstractType) object_p, feature_p);
		}
    else if (object_p instanceof ModelElement) {
      ret = ModelElementHelper.getInstance().doSwitch((ModelElement) object_p, feature_p);
    }

		if(null != ret || feature_p.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}
}
