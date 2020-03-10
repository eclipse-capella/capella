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
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_FragmentEnd extends CommonRule {

  public Rule_FragmentEnd() {
    super(InteractionPackage.Literals.FRAGMENT_END, InteractionPackage.Literals.FRAGMENT_END);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected void firstAttach(EObject sourceElement, ITransfo transfo) throws TransfoException {
    FragmentEnd srcFragmentEnd = (FragmentEnd) sourceElement;

    // Retrieve the transformed FragmentEnds
    for (FragmentEnd transformedFragmentEnd : (List<FragmentEnd>) Query
        .retrieveUnattachedTransformedElements(srcFragmentEnd, transfo, getTargetType())) {
      // If no covered instance roles are added to the FragmentEnd then retrieve transformed instance roles and add them
      if (transformedFragmentEnd.getCoveredInstanceRoles().isEmpty()) {
        for (InstanceRole srcInstanceRole : srcFragmentEnd.getCoveredInstanceRoles()) {
          for (InstanceRole transformedInstanceRole : (List<InstanceRole>) Query.retrieveUnattachedTransformedElements(
              srcInstanceRole, transfo, InteractionPackage.Literals.INSTANCE_ROLE)) {
            transformedFragmentEnd.getCoveredInstanceRoles().add(transformedInstanceRole);
          }
        }
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(srcFragmentEnd, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, transfo);
  }
}
