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
package org.polarsys.capella.core.projection.common.rules.identity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
public class Rule_Message extends TransfoRule {

  /**
   * @param eclass_p
   */
  public Rule_Message() {
    super(InteractionPackage.Literals.SEQUENCE_MESSAGE);
    registerAttributeUpdate(InteractionPackage.Literals.SEQUENCE_MESSAGE__KIND);
  }

  @SuppressWarnings("unchecked")
  List<ExchangeItem> getExchangeItems(SequenceMessage mesg_p) {
    List<ExchangeItem> result = new ArrayList<ExchangeItem>();
    MessageEnd end = mesg_p.getSendingEnd();
    Event e = end.getEvent();
    AbstractEventOperation operation = null;
    if (e instanceof EventSentOperation) {
      EventSentOperation eso = (EventSentOperation) e;
      operation = eso.getOperation();
    }
    if (e instanceof EventReceiptOperation) {
      EventReceiptOperation ero = (EventReceiptOperation) e;
      operation = ero.getOperation();
    }
    if (operation instanceof ExchangeItem) {
      result.add((ExchangeItem) operation);
    }
    if (operation instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) operation;
      result.addAll((Collection<? extends ExchangeItem>) fe.getExchangedItems());

    }

    return result;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    //Object result = Query.retrieveTransformedElement(element_p, transfo_p);
    TigerRelationshipHelper.attachTransformedContainedElements(element_p, transfo_p, InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END, transfo_p);
    TigerRelationshipHelper.attachTransformedRelatedElements(element_p, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END, transfo_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#retrieveRelatedElements_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    return TigerRelationshipHelper.getJustContainer(element_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @SuppressWarnings("unused")
  @Override
  public Object transform_(EObject element_p, ITransfo transfo_p) {
    List<ExchangeItem> eis = getExchangeItems((SequenceMessage) element_p);
    if (eis.size() == 1)
      return InteractionFactory.eINSTANCE.createSequenceMessage();
    List<SequenceMessage> result = new ArrayList<SequenceMessage>(eis.size());
    for (ExchangeItem exchangeItem : eis) {
      result.add(InteractionFactory.eINSTANCE.createSequenceMessage());
    }
    return result;
  }
}
