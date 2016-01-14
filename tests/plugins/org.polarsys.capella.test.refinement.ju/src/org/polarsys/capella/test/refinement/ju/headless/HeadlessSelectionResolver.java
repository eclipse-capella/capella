/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.headless;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;

/**
 */
public class HeadlessSelectionResolver implements IResolver {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Headless Selection Resolver"; //$NON-NLS-1$
  }

  /**
   * We check here that:<br>
   * - the {@link candidateParts} set contains exactly the same elements as the {@link attemptedParts} set<br>
   * - the {@link candidateParts} set contains all the elements contained by the {@link selectedParts} set<br>
   * The {@link attemptedParts} and {@link selectedParts} sets are provided by {@link HeadlessResultProvider}
   * @param candidateAbstractInstances
   * @param srcTree
   * @param tgtTree
   * @param srcMsg
   * @param type
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(List, ScenarioRepresentation, ScenarioRepresentation, AbstractEnd,
   *      COMPONENT_TYPE)
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances, ScenarioRepresentation srcTree,
      ScenarioRepresentation tgtTree, AbstractEnd srcMsg, COMPONENT_TYPE type) throws ResolverException {
    List<AbstractInstance> result = new ArrayList<AbstractInstance>();

    if (candidateAbstractInstances.size() == 1) {
      result.add(candidateAbstractInstances.get(0));
    } else if (candidateAbstractInstances.size() > 1) {
      IHeadlessResult itwr = HeadlessResultProvider.INSTANCE.getCurrentResult();
      if (itwr != null) {
        List<AbstractInstance> attemptedParts = ((IResolverResult) itwr).getAttemptedAbstractInstances();
        List<AbstractInstance> selectedParts = ((IResolverResult) itwr).getSelectedAbstractInstances();

        /**
         */
        if (candidateAbstractInstances.equals(attemptedParts) && candidateAbstractInstances.containsAll(selectedParts)) {
          result.addAll(selectedParts);
        }
      }
    }

    return result;
  }
}
