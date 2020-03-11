/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.topdown.rules;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.topdown.TopDownExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Message extends CommonRule {

  /**
   * @param eclass_p
   */
  public Rule_Message() {
    super(InteractionPackage.Literals.SEQUENCE_MESSAGE, InteractionPackage.Literals.SEQUENCE_MESSAGE);
    registerAttributeUpdate(InteractionPackage.Literals.SEQUENCE_MESSAGE__KIND);
  }

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {

    boolean sending = true;
    boolean receiving = true;

    SequenceMessage message = (SequenceMessage) element_p;

    if (message.getSendingEnd() != null) {
      sending = isOrWillBeTransformed(message.getSendingEnd(), transfo_p);
    }
    if (message.getReceivingEnd() != null) {
      receiving = isOrWillBeTransformed(message.getReceivingEnd(), transfo_p);

    }

    return sending && receiving;
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ProjectionMessages.EndNotTransitioned;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT, transfo_p);
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES,
        transfo_p);

    AbstractEventOperation toperation = TopDownExt.getTransitionedOperation(((SequenceMessage) element_p).getSendingEnd(), transfo_p);
    TigerRelationshipHelper.attachToBestAndValidElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS,
        (Collection) ScenarioExt.getExchangeItems(toperation), transfo_p);
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {

    SequenceMessage s = (SequenceMessage) element_p;

    result_p.add(s.getExchangeContext());
  }

}
