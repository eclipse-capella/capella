/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.is2is.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_InteractionOperand extends CommonRule {

  public Rule_InteractionOperand() {
    super(InteractionPackage.Literals.INTERACTION_OPERAND, InteractionPackage.Literals.INTERACTION_OPERAND);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected void firstAttach(EObject sourceElement, ITransfo transfo) throws TransfoException {
    InteractionOperand srcInteractionOperand = (InteractionOperand) sourceElement;

    // Retrieve the transformed operands
    for (InteractionOperand transformedOperand : (List<InteractionOperand>) Query
        .retrieveUnattachedTransformedElements(srcInteractionOperand, transfo, getTargetType())) {
      // If no covered instance roles are added to the operand then retrieve transformed instance roles and add them
      if (transformedOperand.getCoveredInstanceRoles().isEmpty()) {
        for (InstanceRole srcInstanceRole : srcInteractionOperand.getCoveredInstanceRoles()) {
          for (InstanceRole transformedInstanceRole : (List<InstanceRole>) Query.retrieveUnattachedTransformedElements(
              srcInstanceRole, transfo, InteractionPackage.Literals.INSTANCE_ROLE)) {
            transformedOperand.getCoveredInstanceRoles().add(transformedInstanceRole);
          }
        }
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(srcInteractionOperand, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, transfo);
  }
}
