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
package org.polarsys.capella.core.transfo.operationalactivity.rules;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 */
public class Rule_FunctionalExchange extends org.polarsys.capella.core.projection.common.rules.fa.Rule_FunctionalExchange {

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    FunctionalExchange transfoSource = (FunctionalExchange) element_p;
    FunctionalExchange transfoTarget = (FunctionalExchange) result_p;

    ActivityNode outputNode = transfoTarget.getSource();
    if (outputNode == null) {
      FunctionOutputPort outputPort = FaFactory.eINSTANCE.createFunctionOutputPort();
      transfoTarget.setSource(outputPort);
      AbstractAction action = (AbstractAction) transfoSource.getSource();
      AbstractAction transformedAction = (AbstractAction) Query.retrieveFirstTransformedElement(action, context_p.getTransfo());
      transformedAction.getOutputs().add(outputPort);
      outputPort.setName("out" + transfoSource.getName()); //$NON-NLS-1$
    }
    ActivityNode inputNode = transfoTarget.getTarget();
    if (inputNode == null) {
      FunctionInputPort inputPort = FaFactory.eINSTANCE.createFunctionInputPort();
      transfoTarget.setTarget(inputPort);
      AbstractAction action = (AbstractAction) transfoSource.getTarget();
      AbstractAction transformedAction = (AbstractAction) Query.retrieveFirstTransformedElement(action, context_p.getTransfo());
      transformedAction.getInputs().add(inputPort);
      inputPort.setName("in" + transfoSource.getName()); //$NON-NLS-1$
    }

    TigerRelationshipHelper.attachToBestElement(element_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS, context_p.getTransfo());
    TigerRelationshipHelper.invertedAttachTransformedRelatedElements(element_p, FaPackage.Literals.FUNCTIONAL_EXCHANGE__CATEGORIES, FaPackage.Literals.EXCHANGE_CATEGORY__EXCHANGES, context_p.getTransfo());

  }

}
