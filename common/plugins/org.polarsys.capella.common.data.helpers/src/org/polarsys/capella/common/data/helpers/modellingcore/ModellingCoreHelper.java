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

package org.polarsys.capella.common.data.helpers.modellingcore;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypeHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.AbstractTypedElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.InformationsExchangerHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.ModelElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.TraceableElementHelper;
import org.polarsys.capella.common.data.helpers.modellingcore.delegates.ValueSpecificationHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;

public class ModellingCoreHelper implements IHelper {

	@Override
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof ValueSpecification){
		  ret = ValueSpecificationHelper.getInstance().doSwitch((ValueSpecification) object, feature);
		}
		else if (object instanceof AbstractTypedElement){
		  ret = AbstractTypedElementHelper.getInstance().doSwitch((AbstractTypedElement) object, feature);
		}
		else if (object instanceof TraceableElement) {
			ret = TraceableElementHelper.getInstance().doSwitch((TraceableElement) object, feature);
		}
    else if (object instanceof InformationsExchanger) {
      ret = InformationsExchangerHelper.getInstance().doSwitch((InformationsExchanger) object, feature);
    }
		else if (object instanceof AbstractType) {
			ret = AbstractTypeHelper.getInstance().doSwitch((AbstractType) object, feature);
		}
    else if (object instanceof ModelElement) {
      ret = ModelElementHelper.getInstance().doSwitch((ModelElement) object, feature);
    }

		if(null != ret || feature.getUpperBound() == 1)
			return ret;
		
		throw new HelperNotFoundException();
	}
}
