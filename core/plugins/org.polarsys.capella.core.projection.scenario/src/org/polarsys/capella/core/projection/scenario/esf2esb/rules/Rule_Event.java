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
package org.polarsys.capella.core.projection.scenario.esf2esb.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.esf2esb.ESF2ESBExt;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Event extends CommonRule {

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    if (UnwantedObjects.contains(element_p, transfo_p)) {
      return false;
    }
    return true;
  }

  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    Event event = (Event) element_p;
    AbstractEventOperation operation = ScenarioExt.getOperation(event);
    if (operation != null) {
      if (operation instanceof ComponentExchange) {
        return ProjectionMessages.RelatedConnectionConveyNoExchangeItem;

      } else if (operation instanceof FunctionalExchange) {
        return ProjectionMessages.RelatedFunctionalExchangeConveyNoExchangeItem;

      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @param eclass_p
   */
  public Rule_Event() {
    super(InteractionPackage.Literals.EVENT, InteractionPackage.Literals.EVENT);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {

    int i = 0;
    if (element_p instanceof EventSentOperation) {
      EventSentOperation src = (EventSentOperation) element_p;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p, InteractionPackage.Literals.EVENT_SENT_OPERATION)) {
        EventSentOperation tgt = (EventSentOperation) eTgt;
        AbstractEventOperation operation = getRelatedConnection(src.getOperation(), (Event) element_p, i, transfo_p);
        if (operation != null) {
          TigerRelationshipHelper.attachElementByRel(tgt, operation, InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION);
        }
        i++;
      }

    } else if (element_p instanceof EventReceiptOperation) {
      EventReceiptOperation src = (EventReceiptOperation) element_p;
      for (EObject eTgt : Query.retrieveUnattachedTransformedElements(src, transfo_p, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION)) {
        EventReceiptOperation tgt = (EventReceiptOperation) eTgt;
        AbstractEventOperation operation = getRelatedConnection(src.getOperation(), (Event) element_p, i, transfo_p);
        if (operation != null) {
          TigerRelationshipHelper.attachElementByRel(tgt, operation, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION);
        }
        i++;
      }
    }

    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, transfo_p);

  }

  /**
   * @param operation_p
   * @return
   * @throws TransfoException 
   */
  private AbstractEventOperation getRelatedConnection(AbstractEventOperation operation_p, Event event_p, int n, ITransfo transfo_p) {
    SequenceMessage relatedMessage = ScenarioExt.getRelatedSequenceMessage(event_p);
    AbstractEventOperation operation = ESF2ESBExt.getTargetOperation(operation_p, relatedMessage, transfo_p);
    if (operation == null) {
      notifyMessage(
          NLS.bind(Messages.Rule_Event_FunctionalExchangeNotAllocated, EObjectLabelProviderHelper.getText(operation_p),
              EObjectLabelProviderHelper.getText(relatedMessage)), new Object[] { relatedMessage, operation_p }, ReportManagerConstants.LOG_LEVEL_WARN,
          transfo_p);

    }
    return operation;
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    EPackage pkg = (EPackage) element_p.eClass().eContainer();
    return pkg.getEFactoryInstance().create(element_p.eClass());
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    //Nothing here
  }

}
