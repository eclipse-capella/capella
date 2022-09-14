/*******************************************************************************
 * Copyright (c) 2018, 2022 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.interaction.validation.abstractCapability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_Capability_FunctionalChains_Involvement extends AbstractValidationRule {
  private static final int ERROR_CODE = 1;

  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    java.util.Collection<IStatus> statuses = new ArrayList<IStatus>();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof AbstractCapability) {

        AbstractCapability capability = (AbstractCapability) eObj;
        Set<EObject> relatedFCs = new HashSet<EObject>();
        EList<FunctionalChain> functionalChainsInvolved = capability.getInvolvedFunctionalChains();
        EList<FunctionalChain> ownedFCs = capability.getOwnedFunctionalChains();
        relatedFCs.addAll(ownedFCs);
        for (FunctionalChain ownedFC : ownedFCs) {
          relatedFCs.addAll(FunctionalChainExt.getFlatInvolvedElements(ownedFC, FaPackage.Literals.FUNCTIONAL_CHAIN));
        }

        for (EObject functionalChain : relatedFCs) {
          if (!functionalChainsInvolved.contains(functionalChain)) {
            addCtxStatus(statuses, ctx, eObj, capability, (FunctionalChain) functionalChain);
          }
        }
      }
    }

    if (statuses.isEmpty()) {
      return ctx.createSuccessStatus();
    }
    return ConstraintStatus.createMultiStatus(ctx, statuses);
  }

  private void addCtxStatus(Collection<IStatus> statuses, IValidationContext ctx, EObject eObj,
      AbstractCapability capability, FunctionalChain functionalChain) {
    Object[] msgArguments = new Object[] { capability.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(capability, false), functionalChain.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(functionalChain, false), capability.getName(),
        EObjectLabelProviderHelper.getMetaclassLabel(capability, false) };
    Collection<EObject> resultLocus = new ArrayList<EObject>();
    resultLocus.add(capability);
    resultLocus.add(functionalChain);
    statuses.add(ConstraintStatus.createStatus(ctx, eObj, resultLocus, IStatus.WARNING, ERROR_CODE,
        Messages.DWF_CA_09_Validator_Message, msgArguments));
  }
}
