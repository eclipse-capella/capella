/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.resolver;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;

/**
 *
 */
public class MultipleDecompositionResolver implements IResolver {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Multiple Decomposition Resolver"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(java.util.List, org.polarsys.capella.core.common.model.MessageEnd, org.polarsys.capella.core.data.helpers.interaction.services.model.helpers.MessageEndExt.COMPONENT_TYPE)
   *
   * This 'Resolver' verifies if the candidate components belongs
   * to the same decomposition hierarchy tree as the scenario.
   * 
   * @param candidateAbstractInstances
   * @param srcTree
   * @param srcMsg
   * @param type
   * 
   * @return List<Component>
   * 
   * @throws ResolverException
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances, ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, AbstractEnd srcMsg, COMPONENT_TYPE type) throws ResolverException {
    List<AbstractInstance> filteredAbstractInstances = new ArrayList<AbstractInstance>();
    List<AbstractInstance> selectedAbstractInstances = new ArrayList<AbstractInstance>();

    if ((candidateAbstractInstances != null) && (tgtTree != null)) {
      for (AbstractInstance part : candidateAbstractInstances) {
        Component cpnt = (Component) part.getAbstractType();
        if (CapellaElementExt.areInSameDecompositionAlternative(cpnt, tgtTree.getScenario())) {
          filteredAbstractInstances.add(part);
        }
      }
    }

    if (filteredAbstractInstances.size() > 0){
      selectedAbstractInstances.addAll(filteredAbstractInstances);
    }
    else {
      selectedAbstractInstances.addAll(candidateAbstractInstances);
    }

    return selectedAbstractInstances;
  }
}
