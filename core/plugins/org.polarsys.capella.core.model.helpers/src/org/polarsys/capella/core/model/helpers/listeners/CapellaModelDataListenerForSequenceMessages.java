/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 * This listener is used to synchronize names of elements in the Capella model. The following synchronizations are done :
 * <ol>
 * <li>ExchangeItem, FunctionalExchange or ComponentExchange name to corresponding SequenceMessage name (when the operation of an
 * EventSentOperation/EventReceiveOperation is changed, or the name of a source element is changed,</li>
 * <li>SequenceMessage name to corresponding ExchangeItem, FunctionalExchange or ComponentExchange (when the name of a SequenceMessage is changed).</li>
 * </ol>
 */
public class CapellaModelDataListenerForSequenceMessages extends CapellaModelDataListener {
  /**
   * Get SequenceMessages associated with given AbstractEventOperations.<br>
   * @param operations
   * @return
   */
  protected Collection<SequenceMessage> getAssociatedSequenceMessages(Collection<? extends AbstractEventOperation> operations) {
    Set<SequenceMessage> sequenceMessages = new HashSet<SequenceMessage>();
    for (AbstractEventOperation operation : operations) {
      sequenceMessages.addAll(operation.getInvokingSequenceMessages());
    }
    return sequenceMessages;
  }

  /**
   * Get SequenceMessages associated with given Event.
   * @param event the event
   * @return the sequence messages linked to the given event
   */
  protected List<EObject> getSequenceMessages(Event event) {
    List<EObject> result = new ArrayList<EObject>();
    // Get AbstractEnds referencing the given Event.
    List<EObject> abstractEnds = EObjectExt.getReferencers(event, InteractionPackage.Literals.ABSTRACT_END, InteractionPackage.Literals.ABSTRACT_END__EVENT);
    for (EObject abstractEnd : abstractEnds) {
      result.addAll(EObjectExt.getReferencers(abstractEnd, InteractionPackage.Literals.SEQUENCE_MESSAGE,
          InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END));
      result.addAll(EObjectExt.getReferencers(abstractEnd, InteractionPackage.Literals.SEQUENCE_MESSAGE,
          InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END));
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification) {
    // Preconditions :
    // Call contributed filters.
    if (filterNotification(notification)) {
      return;
    }
    // Only SET notifications are wanted.
    if (notification.getEventType() != Notification.SET) {
      return;
    }

    Object notifier = notification.getNotifier();
    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();

    // The "Operation" feature of an EventSentOperation or an EventReceiptOperation has changed
    // -> synchronize the name of the SequenceMessage with the name of the new operation.
    if (((notifier instanceof EventSentOperation) && InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION.equals(feature))
        || ((notifier instanceof EventReceiptOperation) && InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION.equals(feature))) {
      final Object value = notification.getNewValue();
      // Resolve the new name of the SequenceMessage.
      String name = ICommonConstants.EMPTY_STRING;
      if (value instanceof ExchangeItemAllocation) {
        // Operation is an ExchangeItemAllocation -> use the name of the ExchangeItem.
        AbstractExchangeItem exchangeItem = ((ExchangeItemAllocation) value).getAllocatedItem();
        if (exchangeItem != null) {
          name = exchangeItem.getName();
        }
      } else if (value instanceof AbstractEventOperation) {
        name = ((AbstractEventOperation) value).getName();
      }
      Collection<EObject> sequenceMessages = getSequenceMessages((Event) notifier);
      renameElements(sequenceMessages, name);
    }
    // The "Name" feature of an element has been changed.
    else if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature)) {
      final String value = notification.getNewStringValue();
      if ((notifier instanceof ExchangeItem) || (notifier instanceof AbstractEventOperation)) {
        // A FonctionalExchange, a ComponentExchange or an ExchangeItem has been renamed -> rename the corresponding SequenceMessageElement.
        Collection<? extends AbstractEventOperation> operations = Collections.emptyList();
        if (notifier instanceof ExchangeItem) {
          // Renamed element is an ExchangeItem -> get its ExchangeItemAllocations.
          operations = ExchangeItemExt.getRelatedExchangeItemAllocations((ExchangeItem) notifier);
        } else if (notifier instanceof AbstractEventOperation && !(notifier instanceof ExchangeItemAllocation)) {
          operations = Collections.singletonList((AbstractEventOperation) notifier);
        }
        // Get SequencesMessages associated with the operations.
        Collection<SequenceMessage> sequenceMessages = getAssociatedSequenceMessages(operations);
        renameElements(sequenceMessages, value);
      } else if (notifier instanceof SequenceMessage) {
        // A SequenceMessage has been renamed -> rename the corresponding FonctionalExchange, ComponentExchange or ExchangeItem.
        // Resolve the element to rename.
        AbstractEventOperation op = ((SequenceMessage) notifier).getInvokedOperation();
        AbstractNamedElement elementToRename = null;
        if (op instanceof ExchangeItemAllocation) {
          // Using the ExchangeItemAllocation, get the ExchangeItem to rename it.
          AbstractExchangeItem exchangeItem = ((ExchangeItemAllocation) op).getAllocatedItem();
          if (null != exchangeItem) {
            elementToRename = exchangeItem;
          }
        } else {
          elementToRename = op;
        }
        renameElements(Collections.singletonList(elementToRename), value);
      }
    }

  }

  /**
   * Rename given elements with the given name.<br>
   * An element is renamed only if :
   * <ul>
   * <li>it is an instance of AbstractNamedElement,</li>
   * <li>it doesn't already has the new name.</li>
   * </ul>
   * One command is used to rename all given elements.
   * @param elementsToRename
   * @param newName
   */
  protected void renameElements(final Collection<? extends EObject> elementsToRename, final String newName) {
    // Precondition : doesn't execute a command if nothing to rename (avoid unwanted Ctrl+z...).
    boolean renameNeeded = false;
    for (EObject elementToRename : elementsToRename) {
      if (!(elementToRename instanceof AbstractNamedElement)) {
        // Ignore elements which are null or not instance of AbstractNamedElement.
        continue;
      }
      AbstractNamedElement namedElementToRename = (AbstractNamedElement) elementToRename;
      if (!StringUtils.equals(namedElementToRename.getName(), newName)) {
        renameNeeded = true;
        break;
      }
    }
    if (!renameNeeded) {
      return;
    }
    // Execute command.
    executeCommand(elementsToRename, new AbstractReadWriteCommand() {
      public void run() {
        for (EObject elementToRename : elementsToRename) {
          if (!(elementToRename instanceof AbstractNamedElement)) {
            // Ignore elements which are null or not instance of AbstractNamedElement.
            continue;
          }
          AbstractNamedElement namedElementToRename = (AbstractNamedElement) elementToRename;
          if (!StringUtils.equals(namedElementToRename.getName(), newName)) {
            namedElementToRename.setName(newName);
          }
        }
      }
    });
  }
}
