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
package org.polarsys.capella.core.transition.system.topdown.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionalChainRule extends org.polarsys.capella.core.transition.system.rules.fa.FunctionalChainRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public EClass getTargetType(EObject element, IContext context) {
    return FaPackage.Literals.FUNCTIONAL_CHAIN;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    FunctionalChain element = (FunctionalChain) source;
    retrieveDeepValidChain(element, result, context);
  }

  @Override
  protected void retrieveDeepValidChain(EObject source, List<EObject> result, IContext context) {
    super.retrieveDeepValidChain(source, result, context);
    FunctionalChain element = (FunctionalChain) source;
    for (Involvement involvement : element.getInvolvingInvolvements()) {
      org.polarsys.capella.core.data.capellacore.InvolverElement fc = involvement.getInvolver();

      // Add all involving Capabilities
      if (involvement instanceof FunctionalChainAbstractCapabilityInvolvement) {
        result.add(involvement);
        if (fc instanceof AbstractCapability) {
          result.add(fc);
        }
      }
    }
  }

}
