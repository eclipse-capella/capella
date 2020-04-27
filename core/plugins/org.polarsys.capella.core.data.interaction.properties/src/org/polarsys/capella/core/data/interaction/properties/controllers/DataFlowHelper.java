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
   * @param message
   * @param fe
   */
  public static void affectDataflowToMessageAndOpposite(SequenceMessage message, AbstractEventOperation fe) {
    if (fe != null) {
      DataFlowHelper.affectDataflowToMessage(message, fe);
      SequenceMessage oppositeMessage = SequenceLocalHelper.getOppositeSequenceMessage(message);
      if (oppositeMessage != null) {
        DataFlowHelper.affectDataflowToMessage(oppositeMessage, fe);
      }
    }
  }

  /**
   * @param message
   * @param abstractEventOperation
   */
  public static void affectDataflowToMessage(SequenceMessage message, AbstractEventOperation abstractEventOperation) {
    MessageEnd end = message.getReceivingEnd();
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
      eventR.setOperation(abstractEventOperation);
    }
    MessageEnd begin = message.getSendingEnd();
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
      eventS.setOperation(abstractEventOperation);
      message.setName(abstractEventOperation.getName());
    }
    if (message.getKind() == MessageKind.SYNCHRONOUS_CALL) {
      // same affectation on the return message
      SequenceMessage reply = SequenceLocalHelper.getOppositeSequenceMessage(message);
      affectDataflowToMessage(reply, abstractEventOperation);
    }

  }

  /**
   * find all available exchanges assignable to this message. Must be acceptable about functions realized by the source and functions implemented by
   * destination
   * @param message the message to affect
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchanges(SequenceMessage message) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message.getReceivingEnd());
    return getAvailableFonctionalExchanges(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceIR
   * @param targetIR
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchanges(InstanceRole sourceIR, InstanceRole targetIR) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> resultExchangeSender = new ArrayList<FunctionalExchange>();
    List<FunctionalExchange> resultExchangeReceiver = new ArrayList<FunctionalExchange>();

    if (targetIR != null) {
      List<AbstractFunction> resultReceiver = findFunctionRealizedByInstanceRole(targetIR);
      for (AbstractFunction function : resultReceiver) {
        resultExchangeReceiver.addAll(getExchangeDestinationByFunction(function));
      }
    }

    if (sourceIR != null) {
      List<AbstractFunction> resultSender = findFunctionRealizedByInstanceRole(sourceIR);
      for (AbstractFunction function : resultSender) {
        resultExchangeSender.addAll(getExchangeSourceByFunction(function));
      }
    }

    // the result is the intersection of the two exchange lists if there is a source, and a target.
    // otherwise, it's the whole list of one of the side.
    if (sourceIR == null) {
      result.addAll(resultExchangeReceiver);
    } else if (targetIR == null) {
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

  private static List<FunctionalExchange> getExchangeDestinationByFunction(AbstractFunction function) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    for (InputPin inputPin : function.getInputs()) {
      for (ActivityEdge ae : inputPin.getIncoming()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    if (function instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) function;
      for (ActivityEdge ae : oa.getIncoming()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    for (EObject obj : function.eContents()) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction subFunction = (AbstractFunction) obj;
        result.addAll(getExchangeDestinationByFunction(subFunction));
      }
    }
    return result;
  }

  private static List<FunctionalExchange> getExchangeSourceByFunction(AbstractFunction function) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    for (OutputPin output : function.getOutputs()) {
      for (ActivityEdge ae : output.getOutgoing()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    if (function instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) function;
      for (ActivityEdge ae : oa.getOutgoing()) {
        if (ae instanceof FunctionalExchange) {
          FunctionalExchange fe = (FunctionalExchange) ae;
          result.add(fe);
        }
      }
    }
    for (EObject obj : function.eContents()) {
      if (obj instanceof AbstractFunction) {
        AbstractFunction subFunction = (AbstractFunction) obj;
        result.addAll(getExchangeSourceByFunction(subFunction));
      }
    }
    return result;
  }

  /**
   * @param result
   * @param instanceRole
   */
  public static List<AbstractFunction> findFunctionRealizedByInstanceRole(InstanceRole instanceRole) {
    List<AbstractFunction> result = new ArrayList<AbstractFunction>();

    if (instanceRole != null) {
      AbstractInstance ai = instanceRole.getRepresentedInstance();

      if (ai != null) {
        AbstractType at = ai.getAbstractType();
        if (ai instanceof Role) {
          Role role = (Role) ai;
          for (ActivityAllocation aa : role.getOwnedActivityAllocations()) {
              if (aa != null){
            	  result.add(aa.getActivity());
              	}
             }
        }
        if (at instanceof Component) {
          Component component = (Component) at;
          for (AbstractFunctionAllocation f : component.getOwnedFunctionalAllocation()) {
            AbstractFunction function = (AbstractFunction) f.getTargetElement();
            if (function != null){
            	result.add(function);
            }
          }
          // recursion of components on FE
          for (Component subComponent : ComponentExt.getAllSubUsedAndDeployedComponents(component)) {
            for (AbstractFunctionAllocation f : subComponent.getOwnedFunctionalAllocation()) {
              AbstractFunction function = (AbstractFunction) f.getTargetElement();
              if (function != null){
                  result.add(function);
              }
            }
          }

          if (component instanceof Entity) {
            Entity entity = (Entity) component;
            for (RoleAllocation roleAlloc : entity.getOwnedRoleAllocations()) {
              Role role = roleAlloc.getRole();
              for (ActivityAllocation f : role.getActivityAllocations()) {
                AbstractFunction function = (AbstractFunction) f.getTargetElement();
                if (function != null){
                	result.add(function);
                }
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
   * @param scenario
   * @return
   */
  public static boolean isFeDiagram(Scenario scenario) {
    boolean foundCe = false;
    for (Event event : scenario.getOwnedEvents()) {
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
   * @param scenario
   * @return
   */
  public static boolean isCeDiagram(Scenario scenario) {
    boolean foundFe = false;
    for (Event event : scenario.getOwnedEvents()) {
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
   * @param message
   * @return
   */
  public static Collection<? extends AbstractEventOperation> getAvailableComponentExchanges(SequenceMessage message) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message.getReceivingEnd());
    return getAvailableComponentExchanges(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceComponent
   * @return
   */
  private static List<Component> getAllComponentHierarchy(Component component) {
    List<Component> result = new ArrayList<Component>();
    result.add(component);
    for (Generalization generalization : component.getOwnedGeneralizations()) {
      GeneralizableElement super_ = generalization.getSuper();
      if (super_ instanceof Component) {
        result.addAll(getAllComponentHierarchy((Component) super_));
      }
    }
    // deployed components if the component is a node component
    if (component instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) component;
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
   * @param sourceComponent
   * @return
   */
  private static List<ComponentExchange> getCEFromComponent(Component component, OrientationPortKind orientation) {
    List<ComponentExchange> result = new ArrayList<ComponentExchange>();
    for (Feature f : component.getOwnedFeatures()) {
      if (f instanceof ComponentPort) {
        ComponentPort port = (ComponentPort) f;
        if (isDirectionCompatible(port.getOrientation(), orientation)) {
          for (ComponentExchange componentExchange : port.getComponentExchanges()) {
            result.add(componentExchange);
            if (componentExchange.getKind().equals(ComponentExchangeKind.DELEGATION)) {
              result.addAll(ComponentExchangeExt.getDelegatedComponentExchanges(port, componentExchange));
            }
          }
        }
      }
    }
    if (component instanceof Entity) {
      Entity entity = (Entity) component;
      if (orientation == OrientationPortKind.OUT) {
        for (AbstractInformationFlow if_ : entity.getOutgoingInformationFlows()) {
          if (if_ instanceof CommunicationMean) {
            result.add((ComponentExchange) if_);
          }
        }
      }
      if (orientation == OrientationPortKind.IN) {
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
   * @param orientation
   * @param orientation2
   * @return
   */
  private static boolean isDirectionCompatible(OrientationPortKind portOrientation, OrientationPortKind searchedOrientation) {
    boolean result = false;
    switch (searchedOrientation) {
      case IN:
        result = (portOrientation == OrientationPortKind.IN) || (portOrientation == OrientationPortKind.INOUT);
      break;
      case OUT:
        result = (portOrientation == OrientationPortKind.OUT) || (portOrientation == OrientationPortKind.INOUT);
      break;
      case INOUT:
      case UNSET:
        result = true;
    }
    return result;
  }

  /**
   * @param message
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchangesFromFunctions(SequenceMessage message) {
    // Find source and target InstanceRoles from given SequenceMessage.
    InstanceRole sourceInstanceRole = AbstractEndExt.getInstanceRole(message.getSendingEnd());
    InstanceRole targetInstanceRole = AbstractEndExt.getInstanceRole(message.getReceivingEnd());
    return getAvailableFonctionalExchangesFromFunctions(sourceInstanceRole, targetInstanceRole);
  }

  /**
   * @param sourceIR
   * @param targetIR
   * @return
   */
  public static List<FunctionalExchange> getAvailableFonctionalExchangesFromFunctions(InstanceRole sourceIR, InstanceRole targetIR) {
    List<FunctionalExchange> result = new ArrayList<FunctionalExchange>();
    AbstractFunction resultReceiver = (AbstractFunction) targetIR.getRepresentedInstance();
    List<FunctionalExchange> resultExchangeReceiver = new ArrayList<FunctionalExchange>();
    resultExchangeReceiver.addAll(getExchangeDestinationByFunction(resultReceiver));

    AbstractFunction resultSender = (AbstractFunction) sourceIR.getRepresentedInstance();
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
   * @param sourceIR
   * @param targetIR
   * @return
   */
  public static Collection<? extends AbstractEventOperation> getAvailableComponentExchanges(InstanceRole sourceIR, InstanceRole targetIR) {

    List<ComponentExchange> result = new ArrayList<ComponentExchange>();

    Component sourceComponent = (Component) (sourceIR == null ? null : sourceIR.getRepresentedInstance().getAbstractType());
    Component targetComponent = (Component) (targetIR == null ? null : targetIR.getRepresentedInstance().getAbstractType());

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
