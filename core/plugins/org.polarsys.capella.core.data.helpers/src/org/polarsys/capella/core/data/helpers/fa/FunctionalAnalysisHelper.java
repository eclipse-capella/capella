/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.fa;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
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
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.AbstractFunctionalBlockHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeEndHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeFunctionalExchangeAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentExchangeRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentFunctionalAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentPortAllocationEndHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentPortAllocationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ComponentPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.ExchangeLinkHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionInputPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionOutputPortHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionSpecificationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainInvolvementFunctionHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainInvolvementHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalChainReferenceHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeRealizationHelper;
import org.polarsys.capella.core.data.helpers.fa.delegates.FunctionalExchangeSpecificationHelper;

public class FunctionalAnalysisHelper implements IHelper {

	public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {

		Object ret = null;

		if (object instanceof AbstractFunctionalBlock) {
			ret = AbstractFunctionalBlockHelper.getInstance().doSwitch((AbstractFunctionalBlock) object, feature);
		}
		else if (object instanceof FunctionSpecification) {
			ret = FunctionSpecificationHelper.getInstance().doSwitch((FunctionSpecification) object, feature);
		}
    else if (object instanceof ComponentPort) {
      ret = ComponentPortHelper.getInstance().doSwitch((ComponentPort) object, feature);
    }
    else if (object instanceof ComponentExchange) {
      ret = ComponentExchangeHelper.getInstance().doSwitch((ComponentExchange) object, feature);
    }
    else if (object instanceof FunctionalChain) {
			ret = FunctionalChainHelper.getInstance().doSwitch((FunctionalChain) object, feature);
		}
    else if (object instanceof FunctionalChainReference) {
      ret = FunctionalChainReferenceHelper.getInstance().doSwitch((FunctionalChainReference) object, feature);
    }
    else if (object instanceof FunctionalChainInvolvementFunction) {
      ret = FunctionalChainInvolvementFunctionHelper.getInstance().doSwitch((FunctionalChainInvolvementFunction) object, feature);
    }
    else if (object instanceof FunctionalChainInvolvement) {
      ret = FunctionalChainInvolvementHelper.getInstance().doSwitch((FunctionalChainInvolvement) object, feature);
    }
		else if (object instanceof ExchangeLink) {
			ret = ExchangeLinkHelper.getInstance().doSwitch((ExchangeLink) object, feature);
		}
    else if (object instanceof ExchangeContainment) {
      ret = RelationshipHelper.getInstance().doSwitch((ExchangeContainment) object, feature);
    }
		else if (object instanceof FunctionalExchangeSpecification) {
			ret = FunctionalExchangeSpecificationHelper.getInstance().doSwitch((FunctionalExchangeSpecification) object, feature);
		}
		else if (object instanceof FunctionalExchange) {
			ret = FunctionalExchangeHelper.getInstance().doSwitch((FunctionalExchange) object, feature);
		}
		else if (object instanceof AbstractFunction) {
			ret = AbstractFunctionHelper.getInstance().doSwitch((AbstractFunction) object, feature);
		}
		else if (object instanceof ComponentFunctionalAllocation) {
			ret = ComponentFunctionalAllocationHelper.getInstance().doSwitch((ComponentFunctionalAllocation) object, feature);
		}
		else if (object instanceof FunctionRealization) {
			ret = FunctionRealizationHelper.getInstance().doSwitch((FunctionRealization) object, feature);
		}
		else if (object instanceof FunctionalExchangeRealization) {
			ret = FunctionalExchangeRealizationHelper.getInstance().doSwitch((FunctionalExchangeRealization) object, feature);
		}
		else if (object instanceof ComponentExchangeRealization) {
			ret = ComponentExchangeRealizationHelper.getInstance().doSwitch((ComponentExchangeRealization) object, feature);
		}
    else if (object instanceof FunctionalChainRealization) {
      ret = AllocationHelper.getInstance().doSwitch((FunctionalChainRealization) object, feature);
    }
		else if (object instanceof ComponentExchangeFunctionalExchangeAllocation) {
			ret = ComponentExchangeFunctionalExchangeAllocationHelper.getInstance().doSwitch((ComponentExchangeFunctionalExchangeAllocation) object, feature);
		}
		else if (object instanceof FunctionInputPort) {
			ret = FunctionInputPortHelper.getInstance().doSwitch((FunctionInputPort) object, feature);
		}
    else if (object instanceof FunctionOutputPort) {
      ret = FunctionOutputPortHelper.getInstance().doSwitch((FunctionOutputPort) object, feature);
    }
    else if (object instanceof ExchangeCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((ExchangeCategory) object, feature);
    }
    else if (object instanceof ComponentExchangeEnd) {
      ret = ComponentExchangeEndHelper.getInstance().doSwitch((ComponentExchangeEnd) object, feature);
    }
    else if (object instanceof ComponentExchangeCategory) {
      ret = NamedElementHelper.getInstance().doSwitch((ComponentExchangeCategory) object, feature);
    }
    else if (object instanceof ComponentPortAllocation) {
      ret = ComponentPortAllocationHelper.getInstance().doSwitch((ComponentPortAllocation) object, feature);
    }
    else if (object instanceof ComponentPortAllocationEnd) {
      ret = ComponentPortAllocationEndHelper.getInstance().doSwitch((ComponentPortAllocationEnd) object, feature);
    }
    else if (object instanceof ComponentExchangeAllocation) {
      ret = ComponentExchangeAllocationHelper.getInstance().doSwitch((ComponentExchangeAllocation) object, feature);
    }
    else if (object instanceof SequenceLink) {
      ret = CapellaElementHelper.getInstance().doSwitch((SequenceLink) object, feature);
    }
    else if (object instanceof SequenceLinkEnd) {
      ret = CapellaElementHelper.getInstance().doSwitch((SequenceLinkEnd) object, feature);
    }

		if (null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();

	}

}
