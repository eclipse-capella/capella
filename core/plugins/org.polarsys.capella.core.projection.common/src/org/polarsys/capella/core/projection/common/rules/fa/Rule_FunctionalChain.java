/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;

/**
 * This transformation rule is the one applied to the functional chains during functional transitions
 */
public class Rule_FunctionalChain extends Rule_CapellaElement {

  public Rule_FunctionalChain() {
    super(FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN, FaPackage.Literals.FUNCTIONAL_CHAIN_REALIZATION);
  }

  public Rule_FunctionalChain(EClass source, EClass target, EClass link) {
    super(source, target, link);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (!Query.isElementTransformed(source_p, _transfo)) {
      FunctionalChain sourceElement = (FunctionalChain) source_p;
      result_p.addAll(sourceElement.getOwnedFunctionalChainInvolvements());
    }
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    AbstractFunction root = FunctionExt.getRootFunction((AbstractFunction) element_p);
    AbstractFunction newRoot = (AbstractFunction) Query.retrieveFirstTransformedElement(root, context_p.getTransfo(), FaPackage.Literals.ABSTRACT_FUNCTION);
    return newRoot;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {

      List<?> elements = TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(parent, context_p);
      if (elements.size() > 0) {
        EObject commonElement = (EObject) elements.get(0);
        return commonElement;
      }

      parent = parent.eContainer();
    }
    return null;
  }

}
