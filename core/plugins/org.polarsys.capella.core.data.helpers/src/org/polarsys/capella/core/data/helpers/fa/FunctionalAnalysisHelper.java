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
package org.polarsys.capella.core.data.helpers.fa;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.ExchangeContainment;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionalBlockHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeEndHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeFunctionalExchangeAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentFunctionalAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentPortAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ExchangeLinkHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionInputPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionOutputPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionSpecificationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainInvolvementHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainReferenceHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeSpecificationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.common.tig.model.HelperNotFoundException;
import org.polarsys.capella.common.tig.model.IHelper;

public class FunctionalAnalysisHelper implements IHelper {

	public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {

		Object ret = null;

		if (object_p instanceof AbstractFunctionalBlock) {
			ret = AbstractFunctionalBlockHelper.getInstance().doSwitch((AbstractFunctionalBlock) object_p, feature_p);
		}
		else if (object_p instanceof FunctionSpecification) {
			ret = FunctionSpecificationHelper.getInstance().doSwitch((FunctionSpecification) object_p, feature_p);
		}
    else if (object_p instanceof ComponentPort) {
      ret = ComponentPortHelper.getInstance().doSwitch((ComponentPort) object_p, feature_p);
    }
    else if (object_p instanceof ComponentExchange) {
      ret = ComponentExchangeHelper.getInstance().doSwitch((ComponentExchange) object_p, feature_p);
    }
    else if (object_p instanceof FunctionalChain) {
			ret = FunctionalChainHelper.getInstance().doSwitch((FunctionalChain) object_p, feature_p);
		}
    else if (object_p instanceof FunctionalChainReference) {
      ret = FunctionalChainReferenceHelper.getInstance().doSwitch((FunctionalChainReference) object_p, feature_p);
    }
    else if (object_p instanceof FunctionalChainInvolvement) {
      ret = FunctionalChainInvolvementHelper.getInstance().doSwitch((FunctionalChainInvolvement) object_p, feature_p);
    }
		else if (object_p instanceof ExchangeLink) {
			ret = ExchangeLinkHelper.getInstance().doSwitch((ExchangeLink) object_p, feature_p);
		}
    else if (object_p instanceof ExchangeContainment) {
      ret = RelationshipHelper.getInstance().doSwitch((ExchangeContainment) object_p, feature_p);
    }
		else if (object_p instanceof FunctionalExchangeSpecification) {
			ret = FunctionalExchangeSpecificationHelper.getInstance().doSwitch((FunctionalExchangeSpecification) object_p, feature_p);
		}
		else if (object_p instanceof FunctionalExchange) {
			ret = FunctionalExchangeHelper.getInstance().doSwitch((FunctionalExchange) object_p, feature_p);
		}
		else if (object_p instanceof AbstractFunction) {
			ret = AbstractFunctionHelper.getInstance().doSwitch((AbstractFunction) object_p, feature_p);
		}
		else if (object_p instanceof ComponentFunctionalAllocation) {
			ret = ComponentFunctionalAllocationHelper.getInstance().doSwitch((ComponentFunctionalAllocation) object_p, feature_p);
		}
		else if (object_p instanceof FunctionRealization) {
			ret = FunctionRealizationHelper.getInstance().doSwitch((FunctionRealization) object_p, feature_p);
		}
		else if (object_p instanceof FunctionalExchangeRealization) {
			ret = FunctionalExchangeRealizationHelper.getInstance().doSwitch((FunctionalExchangeRealization) object_p, feature_p);
		}
		else if (object_p instanceof ComponentExchangeRealization) {
			ret = ComponentExchangeRealizationHelper.getInstance().doSwitch((ComponentExchangeRealization) object_p, feature_p);
		}
    else if (object_p instanceof FunctionalChainRealization) {
      ret = AllocationHelper.getInstance().doSwitch((FunctionalChainRealization) object_p, feature_p);
    }
		else if (object_p instanceof ComponentExchangeFunctionalExchangeAllocation) {
			ret = ComponentExchangeFunctionalExchangeAllocationHelper.getInstance().doSwitch((ComponentExchangeFunctionalExchangeAllocation) object_p, feature_p);
		}
		else if (object_p instanceof FunctionInputPort) {
			ret = FunctionInputPortHelper.getInstance().doSwitch((FunctionInputPort) object_p, feature_p);
		}
    else if (object_p instanceof FunctionOutputPort) {
      ret = FunctionOutputPortHelper.getInstance().doSwitch((FunctionOutputPort) object_p, feature_p);
    }
    else if (object_p instanceof ExchangeCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((ExchangeCategory) object_p, feature_p);
    }
    else if (object_p instanceof ComponentExchangeEnd) {
      ret = ComponentExchangeEndHelper.getInstance().doSwitch((ComponentExchangeEnd) object_p, feature_p);
    }
    else if (object_p instanceof ComponentExchangeCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((ComponentExchangeCategory) object_p, feature_p);
    }
    else if (object_p instanceof ComponentPortAllocation) {
      ret = ComponentPortAllocationHelper.getInstance().doSwitch((ComponentPortAllocation) object_p, feature_p);
    }
    else if (object_p instanceof ComponentPortAllocationEnd) {
      ret = CapellaElementHelper.getInstance().doSwitch((ComponentPortAllocationEnd) object_p, feature_p);
    }
    else if (object_p instanceof ComponentExchangeAllocation) {
      ret = ComponentExchangeAllocationHelper.getInstance().doSwitch((ComponentExchangeAllocation) object_p, feature_p);
    }

		if (null != ret || feature_p.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();

	}

}
