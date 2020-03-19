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
package org.polarsys.capella.core.projection.scenario.es2is.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.interfaces.generateInterfaces.Rule_FunctionalExchange_Interface;
import org.polarsys.capella.core.projection.scenario.common.rules.Rule_InteractionElement;
import org.polarsys.capella.core.projection.scenario.helpers.ScenarioExt;
import org.polarsys.capella.core.projection.scenario.helpers.UnwantedObjects;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Event extends Rule_InteractionElement {

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    Event event = (Event) element_p;

    if ((ScenarioExt.getOperation(event) != null) && (ScenarioExt.getExchangeItems(event).size() == 0)) {
      return false;
    }
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
   * {@inheritDoc}
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) throws TransfoException {
    int i = 0;

    List<ExchangeItem> items = ScenarioExt.getExchangeItems((Event) element_p);
    for (EObject obj : Query.retrieveTransformedElements(element_p, transfo_p, getTargetType())) {
      if (obj instanceof EventSentOperation) {
        EventSentOperation src = (EventSentOperation) element_p;
        EventSentOperation tgt = (EventSentOperation) obj;
        if (!items.isEmpty() && (src.getOperation() != null)) {
          AbstractEventOperation operation = getRelatedExchangeItemAllocation(src.getOperation(), items.get(i), i, transfo_p);
          if (operation != null) {
            TigerRelationshipHelper.attachElementByRel(tgt, operation, InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION);
          }
        }
      }
      if (obj instanceof EventReceiptOperation) {
        EventReceiptOperation src = (EventReceiptOperation) element_p;
        EventReceiptOperation tgt = (EventReceiptOperation) obj;
        if (!items.isEmpty() && (src.getOperation() != null)) {
          AbstractEventOperation operation = getRelatedExchangeItemAllocation(src.getOperation(), items.get(i), i, transfo_p);
          if (operation != null) {
            TigerRelationshipHelper.attachElementByRel(tgt, operation, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION);
          }
        }
      }
      i++;
    }
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element_p, getTargetType(), InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, transfo_p);

  }

  /**
   * @param operation_p
   * @return
   */
  private AbstractEventOperation getRelatedExchangeItemAllocation(AbstractEventOperation operation_p, ExchangeItem item, int n, ITransfo transfo) {

    Interface itf = null;

    if (operation_p instanceof FunctionalExchange){
      itf = Rule_FunctionalExchange_Interface.getInterface(((FunctionalExchange)operation_p), transfo);
    } else {
      itf = (Interface) Query.retrieveFirstTransformedElement(operation_p, _transfo, CsPackage.Literals.INTERFACE);
    }

    if (itf != null) {
      for (ExchangeItemAllocation alloc : itf.getOwnedExchangeItemAllocations()) {
        if (alloc.getAllocatedItem().equals(item)) {
          return alloc;
        }
      }
      if (itf.getOwnedExchangeItemAllocations().size() > n) {
        return itf.getOwnedExchangeItemAllocations().get(n);
      }
    }
    return null;
  }

  @Override
  @SuppressWarnings("unused")
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    EPackage pkg = (EPackage) element_p.eClass().eContainer();
    List<ExchangeItem> eis = ScenarioExt.getExchangeItems((Event) element_p);
    if (eis.size() <= 1) {
      return pkg.getEFactoryInstance().create(element_p.eClass());
    }

    // int i=0;
    List<Event> result = new ArrayList<Event>(eis.size());
    for (ExchangeItem exchangeItem : eis) {
      Event end = (Event) pkg.getEFactoryInstance().create(element_p.eClass());
      result.add(end);
      //end.setName(element_p.eClass().getName()+": "+exchangeItem.getName()+" ("+(i++)+")"); //$NON-NLS-1$
    }
    return result;
  }

}
