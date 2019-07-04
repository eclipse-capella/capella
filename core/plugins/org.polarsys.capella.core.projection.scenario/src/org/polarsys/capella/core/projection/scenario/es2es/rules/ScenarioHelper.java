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
package org.polarsys.capella.core.projection.scenario.es2es.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.projection.common.TransitionHelper;
import org.polarsys.capella.core.projection.common.resolver.ResolverFinalizer;
import org.polarsys.capella.core.projection.scenario.CommonScenarioHelper;
import org.polarsys.capella.core.projection.scenario.Messages;
import org.polarsys.capella.core.projection.scenario.ScenarioFinalizer;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class ScenarioHelper {

  /**
   * Retrieve related instances which will be used in the transitioned scenario of the given scenario
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<AbstractInstance> getRelatedInstances(Scenario scenario, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    for (InteractionFragment fragment : scenario.getOwnedInteractionFragments()) {
      if (fragment instanceof AbstractEnd) {
        AbstractEnd end = (AbstractEnd) fragment;
        AbstractEventOperation transitionedOperation = getTransitionedOperation(end, transfo_p);
        AbstractInstance instance = getRelatedInstance(end, end, transitionedOperation, transfo_p);
        if (instance != null) {
          partBounds.add(instance);
        }
      }
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    return partBounds;
  }

  /**
   * Retrieve related instances which will be used in the transitioned instance role of the given instance role
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static List<AbstractInstance> getRelatedInstances(InstanceRole role, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    for (AbstractEnd end : role.getAbstractEnds()) {
      AbstractEventOperation transitionedOperation = getTransitionedOperation(end, transfo_p);
      AbstractInstance instance = getRelatedInstance(end, end, transitionedOperation, transfo_p);
      if (instance != null) {
        partBounds.add(instance);
      } else {
        ScenarioFinalizer.registerUnwantedObject(end.getEvent(), transfo_p);
      }
    }

    if ((partBounds.size() == 0) && (role.getRepresentedInstance() != null)) {
      String messageTxxt = Messages.Scenario_MultipleTransitionedElements;
      List<? extends EObject> availables =
          Query.retrieveTransformedElements(role.getRepresentedInstance(), transfo_p, InformationPackage.Literals.ABSTRACT_INSTANCE);
      if ((availables.size() == 0) && (role.getRepresentedInstance() instanceof Part)) {
        for (Component element : (List<Component>) Query.retrieveTransformedElements(role.getRepresentedInstance().getAbstractType(),
            transfo_p, CsPackage.Literals.COMPONENT)) {
          availables.addAll((List) element.getRepresentingParts());
        }
      }

      if (availables.size() <= 1) {
        partBounds.addAll((List) availables);
      } else {
        IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
        if (resolver != null) {
          List<EObject> res =
              resolver.resolve(role.getRepresentedInstance(), (List) availables, CommonScenarioHelper.getTitle(transfo_p), messageTxxt, false, transfo_p,
                  new EObject[] { role.getRepresentedInstance() });
          if ((res != null) && (res.size() > 0)) {
            partBounds.add((AbstractInstance) res.get(0));
          }
        }
      }
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    return partBounds;
  }

  /**
   * Retrieve an instance for the given end and given operation_p. sourceEnd is used to restrict
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static AbstractInstance getRelatedInstance(AbstractEnd end, AbstractEnd sourceEnd, AbstractEventOperation operation_p, ITransfo transfo_p) {
    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    boolean isLinked = false;
    List<AbstractInstance> relatedParts = getRelatedInstances(end, operation_p, transfo_p);

    if (sourceEnd != null) {
      for (AbstractInstance relatedPart : relatedParts) {
        for (AbstractInstance parent : getRelatedElements(relatedPart)) {
          for (InstanceRole relatedRole : sourceEnd.getCoveredInstanceRoles()) {
            if (RefinementLinkExt.isLinkedTo(parent, relatedRole.getRepresentedInstance())) {
              isLinked = true;
              partBounds.add(relatedPart);
            }
          }
        }
      }
    }

    if (!isLinked) {
      partBounds.addAll(relatedParts);
    }

    partBounds = (List) ListExt.removeDuplicates((List) partBounds);

    if (partBounds.size() == 0) {
      return null;
    } else if (partBounds.size() == 1) {
      return partBounds.get(0);
    }

    IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
    if (resolver == null) {
      return partBounds.get(0);
    }

    SequenceMessage message = getRelatedSequenceMessage(sourceEnd);
    boolean isSource = isEndRelatedToSendingRole(end);

    String messageTxt =
        NLS.bind((isSource ? Messages.Scenario_SourceMessage : Messages.Scenario_TargetMessage),
            new Object[] { getPosition(message), EObjectLabelProviderHelper.getText(message), EObjectLabelProviderHelper.getText(operation_p),
                          EObjectLabelProviderHelper.getMetaclassLabel(operation_p, true) });

    EObject context = (isSource ? message.getSendingEnd() : message.getReceivingEnd());
    return (Part) resolver.resolve(operation_p, (List) partBounds, CommonScenarioHelper.getTitle(transfo_p), messageTxt, false, transfo_p,
        new EObject[] { message, operation_p, context }).get(0);
  }

  /**
   * Retrieve for an instance, all instances which can be considered to be the instance
   */
  private static Collection<AbstractInstance> getRelatedElements(AbstractInstance relatedPart_p) {

    List<AbstractInstance> parents = new ArrayList<AbstractInstance>();
    parents.add(relatedPart_p);

    if (relatedPart_p instanceof Part) {
      parents.addAll(ComponentExt.getPartAncestors((Part) relatedPart_p, true));
    }
    return parents;
  }

  /**
   * @param message_p
   * @return
   */
  private static String getPosition(SequenceMessage message_p) {
    EObject container = message_p.eContainer();
    EStructuralFeature reference = message_p.eContainingFeature();
    Object value = null;
    int position = 1;

    if ((container != null) && (reference != null) && reference.isMany()) {
      value = container.eGet(reference);
      if (value instanceof List<?>) {
        position = ((List<?>) value).indexOf(message_p) + 1;
      }
    }

    switch (position % 10) {
      case 1:
        return position + Messages.Scenario_First;
      case 2:
        return position + Messages.Scenario_Second;
      case 3:
        return position + Messages.Scenario_Third;
      default:
        return position + Messages.Scenario_Nth;
    }
  }

  /**
   * Retrieve for an abstract end and a related operation, instances which are related to the bound of the operation according to the end of the message For a
   * bound of a ce, returns related parts For a bound of a fe, returns related functions, or part according to scenario kind
   */
  public static List<AbstractInstance> getRelatedInstances(AbstractEnd end, EObject operation, ITransfo transfo_p) {

    Scenario scenario = (Scenario) transfo_p.get(TransfoEngine.TRANSFO_SOURCE);
    // On functional scenario, we return directly related function, otherwise, allocating parts
    boolean shouldRetrieveAllocating = !TransitionHelper.getService().isFunctionalScenario(scenario);

    Event event = end.getEvent();
    boolean isSource = false;

    if (event instanceof EventSentOperation) {
      isSource = true;
    } else {
      isSource = false;
    }

    // Source and target
    if (end instanceof MessageEnd) {
      if (((MessageEnd) end).getMessage() != null) {
        isSource = (((MessageEnd) end).getMessage().getKind() == MessageKind.REPLY ? !isSource : isSource);
      }
    }

    List<AbstractInstance> partBounds = new ArrayList<AbstractInstance>();

    if (operation != null) {

      if (operation instanceof ComponentExchange) {
        // Retrieve related parts for a bound of a connection (the connected part or any representing parts of the connected type)
        Part partBound = null;
        Component typeBound = null;
        ComponentExchange connection = (ComponentExchange) operation;

        if (isSource || !connection.isOriented()) {
          // Retrieve parts for the source
          partBound = ComponentExchangeExt.getSourcePart((ComponentExchange) operation);
          typeBound = ComponentExchangeExt.getSourceComponent((ComponentExchange) operation);
          if (partBound != null) {
            partBounds.add(partBound);
          } else if (typeBound != null) {
            partBounds.addAll(typeBound.getRepresentingParts());
          }
        }

        if (!isSource || !connection.isOriented()) {
          // Retrieve parts for the target
          partBound = ComponentExchangeExt.getTargetPart((ComponentExchange) operation);
          typeBound = ComponentExchangeExt.getTargetComponent((ComponentExchange) operation);

          if (partBound != null) {
            partBounds.add(partBound);
          } else if (typeBound != null) {
            partBounds.addAll(typeBound.getRepresentingParts());
          }
        }

      } else if (operation instanceof FunctionalExchange) {
        // Retrieve related parts for a bound of a functional exchange (any representing parts of components allocating the function)
        FunctionalExchange exchange = (FunctionalExchange) operation;
        AbstractFunction function = null;

        ActivityNode current = exchange.getTarget();
        if (isSource) {
          current = exchange.getSource();
        }

        if (current != null) {
          if (current instanceof AbstractFunction) {
            function = (AbstractFunction) current;
          } else {
            function = (AbstractFunction) EcoreUtil2.getFirstContainer(current, FaPackage.Literals.ABSTRACT_FUNCTION);
          }
        }

        if (function != null) {
          if (shouldRetrieveAllocating) {
            for (ComponentFunctionalAllocation allocation : function.getComponentFunctionalAllocations()) {
              if ((allocation.getBlock() != null) && (allocation.getBlock() instanceof Component)) {
                partBounds.addAll(((Component) allocation.getBlock()).getRepresentingParts());
              }
            }

            if (function instanceof OperationalActivity) {
              for (ActivityAllocation allocation : ((OperationalActivity) function).getActivityAllocations()) {
                Role allocatingRole = allocation.getRole();
                if (allocatingRole != null) {
                  for (RoleAllocation roleAllocation : allocatingRole.getRoleAllocations()) {
                    if (roleAllocation.getEntity() != null) {
                      partBounds.addAll(roleAllocation.getEntity().getRepresentingParts());
                    }
                  }
                }
              }
            }

          } else {
            partBounds.add(function);
          }
        }
      }
    }

    return partBounds;

  }

  /**
   * Returns the operation which should be replaced into the transitioned scenario
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static AbstractEventOperation getTransitionedOperation(EObject element_p, ITransfo transfo_p) {

    if (element_p instanceof ExecutionEnd) {
      return getTransitionedOperation(((ExecutionEnd) element_p).getExecution().getStart(), transfo_p);
    }
    if (element_p instanceof AbstractEnd) {
      return getTransitionedOperation(((AbstractEnd) element_p).getEvent(), transfo_p);
    }

    if (element_p instanceof Event) {
      SequenceMessage message = getRelatedSequenceMessage((Event) element_p);
      AbstractEventOperation operation = CommonScenarioHelper.getOperation(element_p, transfo_p);

      String messageTxxt = Messages.Scenario_MultipleTransitionedElements;

      List<? extends EObject> availables = Query.retrieveTransformedElements(operation, transfo_p, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION);
      if (availables.size() == 0) {
        return null;
      }
      if (availables.size() == 1) {
        return (AbstractEventOperation) availables.get(0);
      }
      IResolver resolver = ResolverFinalizer.getResolver(transfo_p);
      if (resolver == null) {
        // If no resolver, return the first
        return (AbstractEventOperation) availables.get(0);
      }
      List<EObject> res =
          resolver.resolve(operation, (List) availables, CommonScenarioHelper.getTitle(transfo_p), messageTxxt, false, transfo_p, new EObject[] { operation,
                                                                                                                                                 message });
      if ((res != null) && (res.size() > 0)) {
        return (AbstractEventOperation) res.get(0);
      }

    }

    return null;

  }

  /**
   * Should be message related to the event. If the message is a reply, should be the sender message to avoid different connections for sent and reply messages
   * @param event_p
   * @return
   */
  public static SequenceMessage getRelatedSequenceMessage(Event event_p) {
    for (EObject referencer : EObjectExt.getReferencers(event_p, InteractionPackage.Literals.ABSTRACT_END__EVENT)) {
      if (referencer instanceof AbstractEnd) {
        AbstractEnd end = (AbstractEnd) referencer;
        return getRelatedSequenceMessage(end);
      }
    }

    return null;
  }

  /**
   * Should be message related to the event. If the message is a reply, should be the sender message to avoid different connections for sent and reply messages
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

    if ((end != null) && (end instanceof MessageEnd)) {
      MessageEnd mend = (MessageEnd) end;
      SequenceMessage message = mend.getMessage();
      if (message.getKind() == MessageKind.REPLY) {
        message = SequenceMessageExt.getOppositeSequenceMessage(message);
      }
      return message;
    }
    return null;
  }

  /**
   * Returns whether the given end is an ends which is related to the sending instance role of the source message (for a reply message, should be the receiver
   * of the message)
   */
  public static boolean isEndRelatedToSendingRole(AbstractEnd end_p) {
    AbstractEnd end = end_p;

    if (end instanceof ExecutionEnd) {
      ExecutionEnd eend = (ExecutionEnd) end;
      if (eend.getExecution() != null) {
        end = (AbstractEnd) eend.getExecution().getStart();
      }
    }

    if ((end != null) && (end instanceof MessageEnd)) {
      MessageEnd mend = (MessageEnd) end;
      SequenceMessage message = mend.getMessage();
      if (message.getKind() == MessageKind.REPLY) {
        return mend.getEvent() instanceof EventReceiptOperation;
      }
      return mend.getEvent() instanceof EventSentOperation;
    }
    return false;
  }

}
