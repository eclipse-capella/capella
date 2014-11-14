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
package org.polarsys.capella.core.projection.scenario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class CommonScenarioHelper {

  /**
   * Retrieve the operation related to the given scenario element_p
   * @param element_p
   * @return
   */
  public static AbstractEventOperation getOperation(EObject element_p, ITransfo transfo_p) {

    AbstractEventOperation operation = null;
    if (element_p instanceof SequenceMessage) {
      operation = ((SequenceMessage) element_p).getInvokedOperation();
    }
    if (element_p instanceof EventSentOperation) {
      operation = ((EventSentOperation) element_p).getOperation();
    }
    if (element_p instanceof EventReceiptOperation) {
      operation = ((EventReceiptOperation) element_p).getOperation();
    }

    if (element_p instanceof AbstractEnd) {
      return getOperation(((AbstractEnd) element_p).getEvent(), transfo_p);
    }

    return operation;
  }

  public static InstanceRole getOppositeCoveredIR(AbstractEnd end_p) {
    MessageEnd messageEnd = null;
    if (end_p instanceof ExecutionEnd) {
      messageEnd = (MessageEnd) ((ExecutionEnd) end_p).getExecution().getStart();

    } else if (end_p instanceof MessageEnd) {
      messageEnd = (MessageEnd) end_p;
    }

    if (messageEnd != null) {
      MessageEnd opposite =
          messageEnd.getMessage().getSendingEnd() == messageEnd ? messageEnd.getMessage().getReceivingEnd() : messageEnd.getMessage().getSendingEnd();
      if (opposite != null) {
        return opposite.getCovered();
      }
    }

    return null;
  }

  public static InstanceRole getOppositeCoveredIR(ExecutionEnd executionEnd_p) {
    MessageEnd me = (MessageEnd) executionEnd_p.getExecution().getStart();
    return getOppositeCoveredIR(me);
  }

  /**
   * @param exec_p
   */
  public static InstanceRole getOppositeCoveredIR(Execution exec_p) {
    return getOppositeCoveredIR((MessageEnd) exec_p.getStart());
  }

  public static String getTitle(ITransfo transfo_p) {
    Object source = transfo_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (source instanceof EObject) {
      return NLS.bind(Messages.Rule_InstanceRole_TransitionTitleDetailled, EObjectLabelProviderHelper.getText((EObject) source));
    }
    return Messages.Rule_InstanceRole_TransitionTitle;
  }

  @SuppressWarnings("unchecked")
  public static List<ExchangeItem> getExchangeItems(AbstractEventOperation operation) {
    List<ExchangeItem> result = new ArrayList<ExchangeItem>();

    if (operation instanceof ExchangeItem) {
      result.add((ExchangeItem) operation);
    }
    if (operation instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) operation;
      if ((allocation.getAllocatedItem() != null) && (allocation.getAllocatedItem() instanceof ExchangeItem)) {
        result.add(allocation.getAllocatedItem());
      }
    }
    if (operation instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) operation;
      result.addAll(fe.getExchangedItems());

    } else if (operation instanceof ComponentExchange) {
      ComponentExchange connection = (ComponentExchange) operation;
      for (AbstractExchangeItem item : connection.getConvoyedInformations()) {
        if (item instanceof ExchangeItem) {
          result.add((ExchangeItem) item);
        }
      }
    }

    return result;

  }

  /**
   * @param element_p
   * @param sequenceMessageExchangedItems_p
   * @param transfo_p
   */
  public static void attachToBestAndValidElements(EObject element_p, EReference reference_p, Collection<EObject> objects, ITransfo transfo_p) {

    if ((element_p == null) || !TigerRelationshipHelper.isApplicable(element_p.eClass(), reference_p)) {
      return;
    }

    for (EObject targetElement : Query.retrieveTransformedElements(element_p, transfo_p)) {
      if (TigerRelationshipHelper.isApplicable(targetElement.eClass(), reference_p)) {

        Object resultTarget = targetElement.eGet(reference_p);

        for (EObject sourceElement : TigerRelationshipHelper.retrieveReferenceAsList(element_p, reference_p)) {
          for (EObject bestElement : TigerRelationshipHelper.retrieveBestElements(targetElement, sourceElement, (EClass) reference_p.getEType(), transfo_p)) {
            if (!objects.contains(bestElement)) {
              continue;
            }
            if (reference_p.isMany() || (resultTarget == null)
                || ((resultTarget != null) && (resultTarget.equals(sourceElement) || resultTarget.equals(bestElement)))) {
              if (bestElement != sourceElement) {
                TigerRelationshipHelper.detachElementByRel(targetElement, sourceElement, reference_p);
              }
              TigerRelationshipHelper.attachElementByRel(targetElement, bestElement, reference_p);
            }
          }
        }

      }

    }

  }

}
