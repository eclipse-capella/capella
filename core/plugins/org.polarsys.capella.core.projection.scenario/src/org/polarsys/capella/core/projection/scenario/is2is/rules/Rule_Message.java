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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;
import org.polarsys.capella.core.projection.scenario.es2es.rules.ScenarioHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Message extends CommonRule {

  /**
   *
   */
  public Rule_Message() {
    super(InteractionPackage.Literals.SEQUENCE_MESSAGE, InteractionPackage.Literals.SEQUENCE_MESSAGE);
    registerAttributeUpdate(InteractionPackage.Literals.SEQUENCE_MESSAGE__KIND);
  }

  @Override
  protected boolean transformIsRequired(EObject element, ITransfo transfo) {

    boolean sending = true;
    boolean receiving = true;

    SequenceMessage message = (SequenceMessage) element;

    if (message.getSendingEnd() != null) {
      sending = isOrWillBeTransformed(message.getSendingEnd(), transfo);
    }
    if (message.getReceivingEnd() != null) {
      receiving = isOrWillBeTransformed(message.getReceivingEnd(), transfo);

    }

    return sending && receiving;
  }

  @Override
  protected String reasonTransformFailed(EObject element, ITransfo transfo) {
    return ProjectionMessages.EndNotTransitioned;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) {
    TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END, transfo);
    TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END, transfo);
    TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGE_CONTEXT, transfo);
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES,
        transfo);

    SequenceMessage srcMsg = (SequenceMessage) element;
    MessageEnd srcSendingEnd = srcMsg.getSendingEnd();
    MessageEnd srcReceivingEnd = srcMsg.getReceivingEnd();
    AbstractEventOperation toperation = ScenarioHelper.getTransitionedOperation(srcSendingEnd, transfo);
    // Depending on preferences interfaces may not have been transitioned
    if(toperation == null) {
      if(srcSendingEnd != null && srcSendingEnd.getEvent() != null) {
        toperation = CommonScenarioHelper.getOperation(srcSendingEnd.getEvent(), transfo);
      }else if(srcReceivingEnd != null && srcReceivingEnd.getEvent() != null){
        toperation = CommonScenarioHelper.getOperation(srcReceivingEnd.getEvent(), transfo);
      }
    }
    CommonScenarioHelper.attachToBestAndValidElements(element, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS,
        (Collection) CommonScenarioHelper.getExchangeItems(toperation), transfo);
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {

    SequenceMessage sequenceMessage = (SequenceMessage) element;
    result.add(sequenceMessage.getExchangeContext());
  }
}
