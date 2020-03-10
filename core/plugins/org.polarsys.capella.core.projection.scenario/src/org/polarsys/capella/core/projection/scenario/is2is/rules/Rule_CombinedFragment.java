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
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_CombinedFragment extends CommonRule {

  public Rule_CombinedFragment() {
    super(InteractionPackage.Literals.COMBINED_FRAGMENT, InteractionPackage.Literals.COMBINED_FRAGMENT);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected void firstAttach(EObject sourceElement, ITransfo transfo) throws TransfoException {

    // Src CombinedFragment
    CombinedFragment srcCombinedFragment = (CombinedFragment) sourceElement;

    // Transformed CombiendFragments
    List<CombinedFragment> unattachedTransformedCombiendFragments = (List<CombinedFragment>) Query
        .retrieveUnattachedTransformedElements(srcCombinedFragment, transfo, getTargetType());

    for (CombinedFragment transformedCombinedFragment : unattachedTransformedCombiendFragments) {
      // CombinedFragment_Start
      if (transformedCombinedFragment.getStart() == null) {
        InteractionFragment transformedInteractionFragment = (InteractionFragment) Query
            .retrieveFirstTransformedElement(srcCombinedFragment.getStart(), transfo,
                InteractionPackage.Literals.INTERACTION_FRAGMENT);
        transformedCombinedFragment.setStart(transformedInteractionFragment);
      }

      // CombinedFragment_End
      if (transformedCombinedFragment.getFinish() == null) {
        InteractionFragment transformedInteractionFragment = (InteractionFragment) Query
            .retrieveFirstTransformedElement(srcCombinedFragment.getFinish(), transfo,
                InteractionPackage.Literals.INTERACTION_FRAGMENT);
        transformedCombinedFragment.setFinish(transformedInteractionFragment);
      }

      // CombiendFragment_ReferencedOperands
      if (transformedCombinedFragment.getReferencedOperands().isEmpty()) {
        for (InteractionOperand srcInteractionOperand : srcCombinedFragment.getReferencedOperands()) {
          InteractionOperand operand = (InteractionOperand) Query.retrieveFirstTransformedElement(srcInteractionOperand,
              transfo, InteractionPackage.Literals.INTERACTION_OPERAND);
          if (operand != null) {
            transformedCombinedFragment.getReferencedOperands().add(operand);
          }
        }
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(srcCombinedFragment, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES, transfo);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update_(EObject element, ITransfo transfo) {
    super.update_(element, transfo);

    CombinedFragment srcCombinedFragment = (CombinedFragment) element;
    for (CombinedFragment target : (List<CombinedFragment>) Query
        .retrieveUnattachedTransformedElements(srcCombinedFragment, transfo, getTargetType())) {
      target.setOperator(srcCombinedFragment.getOperator());
    }
  }
}
