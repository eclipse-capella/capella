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
package org.polarsys.capella.core.data.interaction.properties.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.helpers.interaction.services.AbstractEndExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class DataFlowHelper {

  /**
   * @param message_p
   * @param fe_p
   */
  public static void affectDataflowToMessageAndOpposite(SequenceMessage message_p, AbstractEventOperation fe_p) {
    if (fe_p != null) {
      DataFlowHelper.affectDataflowToMessage(message_p, fe_p);
      SequenceMessage oppositeMessage = SequenceLocalHelper.getOppositeSequenceMessage(message_p);
      if (oppositeMessage != null) {
        DataFlowHelper.affectDataflowToMessage(oppositeMessage, fe_p);
      }
    }
  }

  /**
   * @param message_p
   * @param abstractEventOperation_p
   */
  public static void affectDataflowToMessage(SequenceMessage message_p, AbstractEventOperation abstractEventOperation_p) {
    MessageEnd end = message_p.getReceivingEnd();
    if (end != null) {
      Event event = end.getEvent();
      EventReceiptOperation eventR = null;
      if (event instanceof EventReceiptOperation) {
        eventR = (EventReceiptOperation) event;
      }
      if (eventR == null) {
        eventR = InteractionFactory.eINSTANCE.createEventReceiptOperation();
        end.setEvent(eventR);
      }
      eventR.setOperation(abstractEventOperation_p);
    }
    MessageEnd begin = message_p.getSendingEnd();
    if (begin != null) {
      Event event = begin.getEvent();
      EventSentOperation eventS = null;
      if (event instanceof EventSentOperation) {
        eventS = (EventSentOperation) event;
      }
      if (eventS == null) {
        eventS = InteractionFactory.eINSTANCE.createEventSentOperation();
        begin.setEvent(eventS);
      }
      eventS.setOperation(abstractEventOperation_p);
      message_p.setName(abstractEventOperation_p.getName());
    }
    if (message_p.getKind() == MessageKind.SYNCHRONOUS_CALL) {
      // same affectation on the return message
      SequenceMessage reply = SequenceLocalHelper.getOppositeSequenceMessage(message_p);
      affectDataflowToMessage(reply, abstractEventOperation_p);
    }

  }

  /**
   * find all available exchanges assignable to this message. Must be acceptable about functions realized by the source and functions implemented by
   * destination
   * @param message_p the message to affect
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchanges(SequenceMessage message_p) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message_p.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message_p.getReceivingEnd());
    return getAvailableFonctionalExchanges(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceIR_p
   * @param targetIR_p
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchanges(InstanceRole sourceIR_p, InstanceRole targetIR_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> resultExchangeSender = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> resultExchangeReceiver = new ArrayList<FunctionalExchange>();

    if (targetIR_p != null) {
      List<AbstractFunction> resultReceiver = findFunctionRealizedByInstanceRole(targetIR_p);
      for (AbstractFunction function : resultReceiver) {
        resultExchangeReceiver.addAll(getExchangeDestinationByFunction(function));
      }
    }

    if (sourceIR_p != null) {
      List<AbstractFunction> resultSender = findFunctionRealizedByInstanceRole(sourceIR_p);
      for (AbstractFunction function : resultSender) {
        resultExchangeSender.addAll(getExchangeSourceByFunction(function));
      }
    }

    // the result is the intersection of the two exchange lists if there is a source, and a target.
    // otherwise, it's the whole list of one of the side.
    if (sourceIR_p == null) {
      result.addAll(resultExchangeReceiver);
    } else if (targetIR_p == null) {
      result.addAll(resultExchangeSender);
    } else {
      for (FunctionalExchange functionalExchange : resultExchangeSender) {
        if (resultExchangeReceiver.contains(functionalExchange)) {
          result.add(functionalExchange);
        }
      }
    }
    return result;
  }

  private static List<FunctionalExchange> getExchangeDestinationByFunction(AbstractFunction function_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    for (InputPin inputPin : function_p.getInputs()) {
      for (ActivityEdge ae : inputPin.getIncoming()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    if (function_p instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) function_p;
      for (ActivityEdge ae : oa.getIncoming()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    for (EObject obj : function_p.eContents()) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction subFunction = (AbstractFunction) obj;
        result.addAll(getExchangeDestinationByFunction(subFunction));
      }
    }
    return result;
  }

  private static List<FunctionalExchange> getExchangeSourceByFunction(AbstractFunction function_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    for (OutputPin output : function_p.getOutputs()) {
      for (ActivityEdge ae : output.getOutgoing()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    if (function_p instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) function_p;
      for (ActivityEdge ae : oa.getOutgoing()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    for (EObject obj : function_p.eContents()) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction subFunction = (AbstractFunction) obj;
        result.addAll(getExchangeSourceByFunction(subFunction));
      }
    }
    return result;
  }

  /**
   * @param result
   * @param instanceRole_p
   */
  public static List<AbstractFunction> findFunctionRealizedByInstanceRole(InstanceRole instanceRole_p) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();

    if (instanceRole_p != null) {
      AbstractInstance ai = instanceRole_p.getRepresentedInstance();

      if (ai != null) {
        AbstractType at = ai.getAbstractType();
        if (ai instanceof Role) {
          Role role = (Role) ai;
          for (ActivityAllocation aa : role.getOwnedActivityAllocations()) {
            result.add(aa.getActivity());
          }
        }
        if (at instanceof Component) {
          Component component = (Component) at;
          for (AbstractFunctionAllocation f : component.getOwnedFunctionalAllocation()) {
            AbstractFunction function = (AbstractFunction) f.getTargetElement();
            result.add(function);
          }
          // recursion of components on FE
          for (Component subComponent : ComponentExt.getAllSubUsedAndDeployedComponents(component)) {
            for (AbstractFunctionAllocation f : subComponent.getOwnedFunctionalAllocation()) {
              AbstractFunction function = (AbstractFunction) f.getTargetElement();
              result.add(function);
            }
          }

          if (component instanceof Entity) {
            Entity entity = (Entity) component;
            for (RoleAllocation roleAlloc : entity.getOwnedRoleAllocations()) {
              Role role = roleAlloc.getRole();
              for (ActivityAllocation f : role.getActivityAllocations()) {
                AbstractFunction function = (AbstractFunction) f.getTargetElement();
                result.add(function);
              }
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Check if a diagram doesn't contain a component exchange (connection) to avoid mix of CE and FE in dataflow scenario
   * @param scenario_p
   * @return
   */
  public static boolean isFeDiagram(Scenario scenario_p) {
    boolean foundCe = false;
    for (Event event : scenario_p.getOwnedEvents()) {
      if (event instanceof EventSentOperation) {
        EventSentOperation eso = (EventSentOperation) event;
        if (eso.getOperation() instanceof ComponentExchange) {
          foundCe = true;
        }
      }
    }
    return !foundCe;
  }

  /**
   * Check if a diagram doesn't contain a component exchange (connection) to avoid mix of CE and FE in dataflow scenario
   * @param scenario_p
   * @return
   */
  public static boolean isCeDiagram(Scenario scenario_p) {
    boolean foundFe = false;
    for (Event event : scenario_p.getOwnedEvents()) {
      if (event instanceof EventSentOperation) {
        EventSentOperation eso = (EventSentOperation) event;
        if (eso.getOperation() instanceof FunctionalExchange) {
          foundFe = true;
        }
      }
    }
    return !foundFe;
  }

  /**
   * @param message_p
   * @return
   */
  public static Collection<? extends AbstractEventOperation> getAvailableComponentExchanges(SequenceMessage message_p) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message_p.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message_p.getReceivingEnd());
    return getAvailableComponentExchanges(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceComponent_p
   * @return
   */
  private static List<Component> getAllComponentHierarchy(Component component_p) {
    List<Component> result = new ArrayList<Component>();
    result.add(component_p);
    for (Generalization generalization : component_p.getOwnedGeneralizations()) {
      GeneralizableElement super_ = generalization.getSuper();
      if (super_ instanceof Component) {
        result.addAll(getAllComponentHierarchy((Component) super_));
      }
    }
    // deployed components if the component is a node component
    if (component_p instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) component_p;
      if (pc.getNature().equals(PhysicalComponentNature.NODE)) {
        for (PhysicalComponent deployedPC : pc.getDeployedPhysicalComponents()) {
          result.addAll(getAllComponentHierarchy(deployedPC));
        }
        for (Component sub : ComponentExt.getSubDefinedComponents(pc)) {
          result.addAll(getAllComponentHierarchy(sub));
        }
      }
    }
    return result;
  }

  /**
   * @param sourceComponent_p
   * @return
   */
  private static List<ComponentExchange> getCEFromComponent(Component component_p, OrientationPortKind orientation_p) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();
    for (Feature f : component_p.getOwnedFeatures()) {
      if (f instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) f;
        if (isDirectionCompatible(port.getOrientation(), orientation_p)) {
          for (ComponentExchange componentExchange : port.getComponentExchanges()) {
            result.add(componentExchange);
            if (componentExchange.getKind().equals(ComponentExchangeKind.DELEGATION)) {
              result.addAll(ComponentExchangeExt.getDelegatedComponentExchanges(port, componentExchange));
            }
          }
        }
      }
    }
    if (component_p instanceof Entity) {
      Entity entity = (Entity) component_p;
      if (orientation_p == OrientationPortKind.OUT) {
        for (AbstractInformationFlow if_ : entity.getOutgoingInformationFlows()) {
          if (if_ instanceof CommunicationMean) {
            result.add((ComponentExchange) if_);
          }
        }
      }
      if (orientation_p == OrientationPortKind.IN) {
        for (AbstractInformationFlow if_ : entity.getIncomingInformationFlows()) {
          if (if_ instanceof CommunicationMean) {
            result.add((ComponentExchange) if_);
          }
        }
      }
    }
    return result;
  }

  /**
   * @param orientation_p
   * @param orientation2_p
   * @return
   */
  private static boolean isDirectionCompatible(OrientationPortKind portOrientation_p, OrientationPortKind searchedOrientation_p) {
    boolean result = false;
    switch (searchedOrientation_p) {
      case IN:
        result = (portOrientation_p == OrientationPortKind.IN) || (portOrientation_p == OrientationPortKind.INOUT);
      break;
      case OUT:
        result = (portOrientation_p == OrientationPortKind.OUT) || (portOrientation_p == OrientationPortKind.INOUT);
      break;
      case INOUT:
      case UNSET:
        result = true;
    }
    return result;
  }

  /**
   * @param message_p
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchangesFromFunctions(SequenceMessage message_p) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message_p.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message_p.getReceivingEnd());
    return getAvailableFonctionalExchangesFromFunctions(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceIR_p
   * @param targetIR_p
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchangesFromFunctions(InstanceRole sourceIR_p, InstanceRole targetIR_p) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    AbstractFunction resultReceiver = (AbstractFunction) targetIR_p.getRepresentedInstance();
    List<FunctionalExchange> resultExchangeReceiver = new ArrayList<FunctionalExchange>();
    resultExchangeReceiver.addAll(getExchangeDestinationByFunction(resultReceiver));

    AbstractFunction resultSender = (AbstractFunction) sourceIR_p.getRepresentedInstance();
    List<FunctionalExchange> resultExchangeSender = new ArrayList<FunctionalExchange>();
    resultExchangeSender.addAll(getExchangeSourceByFunction(resultSender));

    // the result is the intersection of the two exchange lists.
    for (FunctionalExchange functionalExchange : resultExchangeSender) {
      if (resultExchangeReceiver.contains(functionalExchange)) {
        result.add(functionalExchange);
      }
    }
    return result;

  }

  /**
   * @param sourceIR_p
   * @param targetIR_p
   * @return
   */
  public static Collection<? extends AbstractEventOperation> getAvailableComponentExchanges(InstanceRole sourceIR_p, InstanceRole targetIR_p) {

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    Component sourceComponent = (Component) (sourceIR_p == null ? null : sourceIR_p.getRepresentedInstance().getAbstractType());
    Component targetComponent = (Component) (targetIR_p == null ? null : targetIR_p.getRepresentedInstance().getAbstractType());

    List<ComponentExchange> sourceConnections = new ArrayList<ComponentExchange>();
    if (sourceComponent != null) {
      for (Component component : getAllComponentHierarchy(sourceComponent)) {
        sourceConnections.addAll(getCEFromComponent(component, OrientationPortKind.OUT));
      }
    }

    List<ComponentExchange> targetConnections = new ArrayList<ComponentExchange>();
    if (targetComponent != null) {
      for (Component component : getAllComponentHierarchy(targetComponent)) {
        targetConnections.addAll(getCEFromComponent(component, OrientationPortKind.IN));
      }
    }

    if (sourceComponent == null) {
      result.addAll(targetConnections);
    } else if (targetComponent == null) {
      result.addAll(sourceConnections);
    } else {
      // the result is the intersection of these two lists
      for (ComponentExchange connection : targetConnections) {
        if (sourceConnections.contains(connection)) {
          result.add(connection);
        }
      }
    }

    return result;
  }
}
