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

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

/**
 *
 */
public class FunctionalExchangeRule extends org.polarsys.capella.core.transition.system.rules.fa.FunctionalExchangeRule {

  @Override
  protected void premicesExchangeRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE));
    needed_p.addAll(createDefaultPrecedencePremices(element_p, ActivityPackage.Literals.ACTIVITY_EDGE__TARGET));
  }

  @Override
  protected void attachExchangeRelated(EObject element_p, EObject result_p, IContext context_p) {

    FunctionalExchange transfoSource = (FunctionalExchange) element_p;
    FunctionalExchange transfoTarget = (FunctionalExchange) result_p;
    boolean valid = true;

    ActivityNode outputNode = transfoTarget.getSource();
    if ((outputNode == null) && (transfoSource.getSource() instanceof AbstractFunction)) {
      FunctionOutputPort outputPort = FaFactory.eINSTANCE.createFunctionOutputPort();
      transfoTarget.setSource(outputPort);

      AbstractFunction transformedAction =
          (AbstractFunction) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(transfoSource.getSource(), context_p,
              SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));

      transformedAction.getOutputs().add(outputPort);
      outputPort.setName("out" + transfoSource.getName()); //$NON-NLS-1$
      valid = false;
    }

    ActivityNode inputNode = transfoTarget.getTarget();
    if ((inputNode == null) && (transfoSource.getSource() instanceof AbstractFunction)) {
      FunctionInputPort inputPort = FaFactory.eINSTANCE.createFunctionInputPort();
      transfoTarget.setTarget(inputPort);
      AbstractFunction transformedAction =
          (AbstractFunction) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(transfoSource.getTarget(), context_p,
              SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION));

      transformedAction.getInputs().add(inputPort);
      inputPort.setName("in" + transfoSource.getName()); //$NON-NLS-1$
      valid = false;
    }

    if (valid) {
      super.attachExchangeRelated(element_p, result_p, context_p);
    }

  }

}
