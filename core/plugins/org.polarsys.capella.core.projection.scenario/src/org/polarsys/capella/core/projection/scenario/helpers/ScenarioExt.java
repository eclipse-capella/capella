package org.polarsys.capella.core.projection.scenario.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.Role;

public class ScenarioExt {

  public static List<ExchangeItem> getExchangeItems(AbstractEventOperation operation) {
    List<ExchangeItem> result = new ArrayList<ExchangeItem>();

    if (operation instanceof ExchangeItem) {
      result.add((ExchangeItem) operation);

    } else if (operation instanceof ExchangeItemAllocation) {
      ExchangeItemAllocation allocation = (ExchangeItemAllocation) operation;
      if (allocation.getAllocatedItem() instanceof ExchangeItem) {
        result.add(allocation.getAllocatedItem());
      }

    } else if (operation instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) operation;
      result.addAll(fe.getExchangedItems());

    } else if (operation instanceof ComponentExchange) {
      ComponentExchange exchange = (ComponentExchange) operation;
      for (AbstractExchangeItem item : exchange.getConvoyedInformations()) {
        if (item instanceof ExchangeItem) {
          result.add((ExchangeItem) item);
        }
      }
    }

    return result;

  }

  /**
   * Retrieve the operation related to the given scenario element_p
   * 
   * @param element
   * @return
   */
  public static AbstractEventOperation getOperation(EObject element) {

    AbstractEventOperation operation = null;
    if (element instanceof SequenceMessage) {
      operation = ((SequenceMessage) element).getInvokedOperation();
    }
    if (element instanceof EventSentOperation) {
      operation = ((EventSentOperation) element).getOperation();
    }
    if (element instanceof EventReceiptOperation) {
      operation = ((EventReceiptOperation) element).getOperation();
    }

    if (element instanceof AbstractEnd) {
      return getOperation(((AbstractEnd) element).getEvent());
    }

    return operation;
  }

  public static InstanceRole getOppositeCoveredIR(AbstractEnd end) {
    MessageEnd messageEnd = null;
    if (end instanceof ExecutionEnd) {
      messageEnd = (MessageEnd) ((ExecutionEnd) end).getExecution().getStart();

    } else if (end instanceof MessageEnd) {
      messageEnd = (MessageEnd) end;
    }

    if (messageEnd != null) {
      MessageEnd opposite = messageEnd.getMessage().getSendingEnd() == messageEnd
          ? messageEnd.getMessage().getReceivingEnd()
          : messageEnd.getMessage().getSendingEnd();
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
   * @param exec
   */
  public static InstanceRole getOppositeCoveredIR(Execution exec) {
    return getOppositeCoveredIR((MessageEnd) exec.getStart());
  }

  public static List<ExchangeItem> getExchangeItems(Event e) {
    if ((e instanceof EventSentOperation) || (e instanceof EventReceiptOperation)) {
      SequenceMessage sm = ScenarioExt.getSequenceMessage(e);
      return sm.getExchangedItems();
    }
    return getExchangeItems(getOperation(e));
  }

  public static SequenceMessage getSequenceMessage(Event e) {
    Collection<EObject> msgs = new HashSet<EObject>();
    for (EObject obj : EObjectExt.getReferencers(e, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
      msgs.addAll(EObjectExt.getReferencers(obj, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END));
      msgs.addAll(EObjectExt.getReferencers(obj, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END));
    }
    if (!msgs.isEmpty()) {
      return (SequenceMessage) msgs.iterator().next();
    }
    return null;

  }

  /**
   * Returns whether the given end is related to the source instance role.
   * 
   * The source instance role here is the source of the initial SM in a group SM+Execution+ReplySM, so if the related
   * sequence message is the Reply message, then its the targeted instanceRole which is the 'source' (source of SM)
   */
  public static boolean isSource(AbstractEnd end_p) {
    AbstractEnd end = end_p;
    if (end instanceof ExecutionEnd) {
      ExecutionEnd eend = (ExecutionEnd) end;
      if (eend.getExecution() != null) {
        end = (AbstractEnd) eend.getExecution().getStart();
      }
    }

    Event event = end.getEvent();
    boolean isSource = (event instanceof EventSentOperation);

    // Source of a reply message is a Receipt, not a Sent
    if (end instanceof MessageEnd) {
      if (((MessageEnd) end).getMessage() != null && ((MessageEnd) end).getMessage().getKind() == MessageKind.REPLY) {
        isSource = (event instanceof EventReceiptOperation);
      }
    }
    return isSource;
  }

  /**
   * Returns whether the given end is on a ExchangeItemInstance instance role
   */
  public static boolean isExchangeItemInstanceRole(AbstractEnd end) {
    // an end has always only one coveredInstance role, so get(0) is OK here
    return end.getCoveredInstanceRoles().size() > 0
        && end.getCoveredInstanceRoles().get(0).getRepresentedInstance() instanceof ExchangeItemInstance;
  }

  /**
   * Returns whether the given end is on a Role instance role
   */
  public static boolean isRoleInstanceRole(AbstractEnd end) {
    return end.getCoveredInstanceRoles().size() > 0
        && end.getCoveredInstanceRoles().get(0).getRepresentedInstance() instanceof Role;
  }
  
  /**
   * Returns whether the given end is on a Part instance role
   */
  public static boolean isPartInstanceRole(AbstractEnd end) {
    return end.getCoveredInstanceRoles().size() > 0
        && end.getCoveredInstanceRoles().get(0).getRepresentedInstance() instanceof Part;
  }
  
  /**
   * Returns whether the given end is on a AbstractFunction instance role
   */
  public static boolean isFunctionalInstanceRole(AbstractEnd end) {
    return end.getCoveredInstanceRoles().size() > 0
        && end.getCoveredInstanceRoles().get(0).getRepresentedInstance() instanceof AbstractFunction;
  }
  
  /**
   * Return the ExchangeItemInstance related to the abstract end.
   * 
   * @implNote we suppose here that isExchangeItemInstanceRole(end) is true
   */
  public static ExchangeItemInstance getExchangeItemInstanceRole(AbstractEnd end) {
    return (ExchangeItemInstance) end.getCoveredInstanceRoles().get(0).getRepresentedInstance();
  }

  /**
   * Should be message related to the event. If the message is a reply, should be the sender message to avoid different
   * connections for sent and reply messages
   * 
   * @param event_p
   * @return
   */
  public static SequenceMessage getRelatedSequenceMessage(Event event_p) {
    for (EObject referencer : EObjectExt.getReferencers(event_p, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
      if (referencer instanceof AbstractEnd) {
        AbstractEnd end = (AbstractEnd) referencer;
        return ScenarioExt.getRelatedSequenceMessage(end);
      }
    }

    return null;
  }

  /**
   * Should be message related to the event. If the message is a reply, should be the sender message to avoid different
   * connections for sent and reply messages
   * 
   * @param event_p
   * @return
   */
  public static SequenceMessage getRelatedSequenceMessage(AbstractEnd end_p) {
    AbstractEnd end = end_p;

    if (end instanceof ExecutionEnd) {
      ExecutionEnd eend = (ExecutionEnd) end;
      if (eend.getExecution() != null) {
        end = (AbstractEnd) eend.getExecution().getStart();
      }
    }

    if (end instanceof MessageEnd) {
      MessageEnd mend = (MessageEnd) end;
      SequenceMessage message = mend.getMessage();
      if (message.getKind() == MessageKind.REPLY) {
        message = SequenceMessageExt.getOppositeSequenceMessage(message);
      }
      return message;
    }
    return null;
  }

}
