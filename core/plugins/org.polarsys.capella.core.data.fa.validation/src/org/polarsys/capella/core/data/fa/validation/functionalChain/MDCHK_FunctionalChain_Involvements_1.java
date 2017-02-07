/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Notice that this validation is highly related to FunctionalChainExt.isFunctionalChainValid method
 */
public class MDCHK_FunctionalChain_Involvements_1 extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof FunctionalChain) {
        FunctionalChain chain = (FunctionalChain) eObj;

        // Check if functionalChain is empty
        if (chain.getOwnedFunctionalChainInvolvements().isEmpty()) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_IsEmpty });
        }

        // Check if functionalChain contains invalid involvements
        String involvedElements = ICommonConstants.EMPTY_STRING;
        Collection<FunctionalChainInvolvement> invsNoValid = new ArrayList<FunctionalChainInvolvement>();
        for (FunctionalChainInvolvement inv : chain.getOwnedFunctionalChainInvolvements()) {
          IStatus status = FunctionalChainExt.isFunctionalChainInvolvementValidWithStatus(inv);

          if (!status.isOK()) {
            involvedElements += EObjectLabelProviderHelper.getText(inv);
            involvedElements += ICommonConstants.WHITE_SPACE_CHARACTER;
            involvedElements += ICommonConstants.PARENTHESIS_OPEN_CHARACTER;
            involvedElements += status.getMessage();
            involvedElements += ICommonConstants.PARENTHESIS_CLOSE_CHARACTER;
            involvedElements += ICommonConstants.EOL_CHARACTER;
            invsNoValid.add(inv);
          }
        }
        if (!invsNoValid.isEmpty()) {
          return ctx.createFailureStatus(new Object[] {
                                                       chain.getName(),
                                                       NLS.bind(Messages.MDCHK_FunctionalChain_Involvements_1_InvolvementInvalid,
                                                           String.valueOf(invsNoValid.size()), involvedElements) });
        }

        // Check if functionalChain is not well formed
        if (!FunctionalChainExt.isFunctionalChainWellFormed(chain)) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_NotWellFormed });
        }

        // Check if functionalChain contains no source
        List<FunctionalChainInvolvement> sources = chain.getFirstFunctionalChainInvolvements();
        if (sources.isEmpty()) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_NoSource });
        }

        // Check if functionalChain contains a cycle
        boolean cycleFound = FunctionalChainExt.containsACycle(chain);
        if (cycleFound) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_ContainsACycle });
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
