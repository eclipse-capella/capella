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
package org.polarsys.capella.core.data.cs.validation.physicalPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

public class MDCHK_PhysicalPath_InvolvedLinks extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();

    if (eType == EMFEventType.NULL) {

      if (eObj instanceof PhysicalPath) {
        PhysicalPath chain = (PhysicalPath) eObj;

        //Check if PhysicalPath is empty
        if (chain.getOwnedPhysicalPathInvolvements().isEmpty()) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_PhysicalPath_InvolvedLinks_IsEmpty });
        }

        //Check if PhysicalPath contains invalid involvements
        String involvedElements = ICommonConstants.EMPTY_STRING;
        Collection<PhysicalPathInvolvement> invsNoValid = new ArrayList<PhysicalPathInvolvement>();
        for (PhysicalPathInvolvement inv : chain.getOwnedPhysicalPathInvolvements()) {
          IStatus status = PhysicalPathExt.isPhysicalPathInvolvementValidWithStatus(inv);

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
          return ctx.createFailureStatus(
              new Object[] { chain.getName(),
                            NLS.bind(Messages.MDCHK_PhysicalPath_InvolvedLinks_InvolvementInvalid, String.valueOf(invsNoValid.size()), involvedElements) });
        }

        //Check if PhysicalPath is not well formed
        if (!PhysicalPathExt.isPhysicalPathWellFormed(chain)) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_PhysicalPath_InvolvedLinks_NotWellFormed });
        }

        //Check if PhysicalPath contains no source
        List<PhysicalPathInvolvement> sources = chain.getFirstPhysicalPathInvolvements();
        if (sources.isEmpty()) {
          return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_PhysicalPath_InvolvedLinks_NoSource });
        }

        //Check if PhysicalPath contains a cycle
        for (PhysicalPathInvolvement source : sources) {
          if (PhysicalPathExt.containsACycle(source, new HashSet<PhysicalPathInvolvement>())) {
            return ctx.createFailureStatus(new Object[] { chain.getName(), Messages.MDCHK_PhysicalPath_InvolvedLinks_ContainsACycle });
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
