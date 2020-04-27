/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/*
 * Rule that checks if an Involvement element has duplicates
 */
public class UniqueInvolvementConstraint extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {

    Involvement involvement = (Involvement) ctx.getTarget();
    InvolverElement involvementSource = involvement.getInvolver();
    InvolvedElement involvementTarget = involvement.getInvolved();

    if (null != involvementSource && null != involvementTarget) {

      EList<Involvement> involvementLinks = involvementSource.getInvolvedInvolvements();

      // Check if the source of the validated Involvement has multiple Involvements pointing to the same Target
      Collection<Involvement> equivalentInvolvements = new ArrayList<Involvement>();
      for (Involvement inv : involvementLinks) {

        // We need to ignore Involvements of type FunctionalChainInvolvement and PhyiscalPathInvolvement
        // Duplicates of these Involvement types are valid
        if (!(inv instanceof FunctionalChainInvolvement || inv instanceof PhysicalPathInvolvement)) {

          if (inv.getInvolved().equals(involvementTarget)) {
            equivalentInvolvements.add(inv);
          }
        }
      }

      // If there is more than 1 Involvement with the same source and target, then we have duplicates
      if (equivalentInvolvements.size() > 1) {

        ctx.skipCurrentConstraintForAll(equivalentInvolvements);

        return ConstraintStatus.createStatus(ctx, equivalentInvolvements,
            "Multiple equivalent relationships of type ''{0}'' between ''{1}'' and ''{2}''", //$NON-NLS-1$
            involvement.eClass().getName(), involvementSource, involvementTarget);
      }
    }

    return ctx.createSuccessStatus();
  }
}
