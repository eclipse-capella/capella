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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

public class Rule_ConnectionEnd extends Rule_CapellaElement {

  public Rule_ConnectionEnd() {
    super(FaPackage.Literals.COMPONENT_EXCHANGE_END, FaPackage.Literals.COMPONENT_EXCHANGE_END);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ComponentExchangeEnd ce = (ComponentExchangeEnd) source_p;
    if (!Query.isElementTransformed(ce, _transfo)) {
      if (transformRequired(source_p, context_p).isOK()) {
        if (ce.getPort() != null)
          result_p.add(ce.getPort());
        if (ce.getPart() != null)
          result_p.add(ce.getPart());
        result_p.addAll(ce.getInformationFlows());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      ComponentExchangeEnd ce = (ComponentExchangeEnd) element_p;
      ComponentExchangeEnd target = (ComponentExchangeEnd) result_p;
      target.setPart((Partition) Query.retrieveFirstTransformedElement(ce.getPart(), context_p.getTransfo()));
      target.setPort((Port) Query.retrieveFirstTransformedElement(ce.getPort(), context_p.getTransfo()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      ComponentExchangeEnd ce = (ComponentExchangeEnd) element_p;

      EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
      if (element_p == transfoSource) {
        return result;
      }
      if (ce.getPort() != null && result.isOK()) {
        result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getPort(), context_p);
      }
      if (ce.getPart() != null && result.isOK()) {
        result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getPart(), context_p);
      }
    }
    return result;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return FaPackage.Literals.COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS;
  }

}
