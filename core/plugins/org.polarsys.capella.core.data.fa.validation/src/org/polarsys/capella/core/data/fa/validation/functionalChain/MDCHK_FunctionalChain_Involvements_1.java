/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.functionalChain;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
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

    if (ctx.getEventType() == EMFEventType.NULL && ctx.getTarget() instanceof FunctionalChain) {
      FunctionalChain chain = (FunctionalChain) ctx.getTarget();

      // check if the chain contains any involvements
      EList<FunctionalChainInvolvement> ownedFunctionalChainInvolvements = chain.getOwnedFunctionalChainInvolvements();
      if (ownedFunctionalChainInvolvements.isEmpty()) {
        return ctx.createFailureStatus(chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_IsEmpty);
      }

      // check if the chain contains invalid involvements
      StringBuilder invalidInvolvementsMessage = new StringBuilder();
      int invalidInvolvementsSize = 0;

      for (FunctionalChainInvolvement involvement : ownedFunctionalChainInvolvements) {
        IStatus status = FunctionalChainExt.getFunctionalChainInvolvementValidityStatus(involvement);

        if (!status.isOK()) {
          invalidInvolvementsMessage.append(EObjectLabelProviderHelper.getText(involvement));
          invalidInvolvementsMessage.append(ICommonConstants.WHITE_SPACE_CHARACTER);
          invalidInvolvementsMessage.append(ICommonConstants.PARENTHESIS_OPEN_CHARACTER);
          invalidInvolvementsMessage.append(status.getMessage());
          invalidInvolvementsMessage.append(ICommonConstants.PARENTHESIS_CLOSE_CHARACTER);
          invalidInvolvementsMessage.append(ICommonConstants.EOL_CHARACTER);
          invalidInvolvementsSize++;
        }
      }

      if (invalidInvolvementsSize > 0) {
        return ctx.createFailureStatus(chain.getName(),
            NLS.bind(Messages.MDCHK_FunctionalChain_Involvements_1_InvolvementInvalid,
                String.valueOf(invalidInvolvementsSize), invalidInvolvementsMessage.toString()));
      }

      // check if the chain is not well formed
      if (!FunctionalChainExt.isFunctionalChainWellFormed(chain)) {
        return ctx.createFailureStatus(chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_NotWellFormed);
      }

      // check if functionalChain contains a cycle
      if (FunctionalChainExt.containsACycle(chain)) {
        return ctx.createFailureStatus(chain.getName(), Messages.MDCHK_FunctionalChain_Involvements_1_ContainsACycle);
      }
    }
    return ctx.createSuccessStatus();
  }
}
