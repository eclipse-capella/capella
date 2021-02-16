/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Assert;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.test.framework.context.SessionContext;

public class SkeletonHelper {

  public static void createFunctionPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG,
          NamingConstants.CreateOpAnalysisCmd_operationalActivities_pkg_name, context);

    } else if (container instanceof SystemAnalysis) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          CtxPackage.Literals.SYSTEM_FUNCTION_PKG, NamingConstants.CreateSysAnalysisCmd_system_functions_pkg_name,
          context);

    } else if (container instanceof LogicalArchitecture) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          LaPackage.Literals.LOGICAL_FUNCTION_PKG, NamingConstants.CreateLogicalArchCmd_logicalFunctions_pkg_name,
          context);

    } else if (container instanceof PhysicalArchitecture) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG,
          PaPackage.Literals.PHYSICAL_FUNCTION_PKG, NamingConstants.CreatePhysicalArchCmd_physicalFunctions_pkg_name,
          context);

    } else if (container instanceof OperationalActivity) {
      createObject(elementId, containerId, OaPackage.Literals.OPERATIONAL_ACTIVITY__OWNED_OPERATIONAL_ACTIVITY_PKGS,
          OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG, elementId, context);

    } else if (container instanceof SystemFunction) {
      createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_FUNCTION__OWNED_SYSTEM_FUNCTION_PKGS,
          CtxPackage.Literals.SYSTEM_FUNCTION_PKG, elementId, context);

    } else if (container instanceof LogicalFunction) {
      createObject(elementId, containerId, LaPackage.Literals.LOGICAL_FUNCTION__OWNED_LOGICAL_FUNCTION_PKGS,
          LaPackage.Literals.LOGICAL_FUNCTION_PKG, elementId, context);

    } else if (container instanceof PhysicalFunction) {
      createObject(elementId, containerId, PaPackage.Literals.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS,
          PaPackage.Literals.PHYSICAL_FUNCTION_PKG, elementId, context);

    } else if (container instanceof OperationalActivityPkg) {
      createObject(elementId, containerId, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES,
          OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG, elementId, context);

    } else if (container instanceof SystemFunctionPkg) {
      createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS,
          CtxPackage.Literals.SYSTEM_FUNCTION_PKG, elementId, context);

    } else if (container instanceof LogicalFunctionPkg) {
      createObject(elementId, containerId, LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS,
          LaPackage.Literals.LOGICAL_FUNCTION_PKG, elementId, context);

    } else if (container instanceof PhysicalFunctionPkg) {
      createObject(elementId, containerId, PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS,
          PaPackage.Literals.PHYSICAL_FUNCTION_PKG, elementId, context);
    }

  }

  public static void createRootFunction(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalActivityPkg) {
      createObject(elementId, containerId, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES,
          OaPackage.Literals.OPERATIONAL_ACTIVITY, NamingConstants.CreateOpAnalysisCmd_operationalActivity_root_name,
          context);

    } else if (container instanceof SystemFunctionPkg) {
      createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS,
          CtxPackage.Literals.SYSTEM_FUNCTION, NamingConstants.CreateSysAnalysisCmd_system_function_root_name, context);

    } else if (container instanceof LogicalFunctionPkg) {
      createObject(elementId, containerId, LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS,
          LaPackage.Literals.LOGICAL_FUNCTION, NamingConstants.CreateLogicalArchCmd_logicalFunction_root_name, context);

    } else if (container instanceof PhysicalFunctionPkg) {
      createObject(elementId, containerId, PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS,
          PaPackage.Literals.PHYSICAL_FUNCTION, NamingConstants.CreatePhysicalArchCmd_physicalFunction_root_name,
          context);
    }

  }

  public static void createFunction(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalActivityPkg) {
      createObject(elementId, containerId, OaPackage.Literals.OPERATIONAL_ACTIVITY_PKG__OWNED_OPERATIONAL_ACTIVITIES,
          OaPackage.Literals.OPERATIONAL_ACTIVITY, elementId, context);

    } else if (container instanceof SystemFunctionPkg) {
      createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_FUNCTION_PKG__OWNED_SYSTEM_FUNCTIONS,
          CtxPackage.Literals.SYSTEM_FUNCTION, elementId, context);

    } else if (container instanceof LogicalFunctionPkg) {
      createObject(elementId, containerId, LaPackage.Literals.LOGICAL_FUNCTION_PKG__OWNED_LOGICAL_FUNCTIONS,
          LaPackage.Literals.LOGICAL_FUNCTION, elementId, context);

    } else if (container instanceof PhysicalFunctionPkg) {
      createObject(elementId, containerId, PaPackage.Literals.PHYSICAL_FUNCTION_PKG__OWNED_PHYSICAL_FUNCTIONS,
          PaPackage.Literals.PHYSICAL_FUNCTION, elementId, context);

    } else if (container instanceof OperationalActivity) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
          OaPackage.Literals.OPERATIONAL_ACTIVITY, elementId, context);

    } else if (container instanceof SystemFunction) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
          CtxPackage.Literals.SYSTEM_FUNCTION, elementId, context);

    } else if (container instanceof LogicalFunction) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
          LaPackage.Literals.LOGICAL_FUNCTION, elementId, context);

    } else if (container instanceof PhysicalFunction) {
      createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONS,
          PaPackage.Literals.PHYSICAL_FUNCTION, elementId, context);
    }

  }

  public static void createCapabilityPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG,
          OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG,
          NamingConstants.CreateOpAnalysisCmd_operationalCapabilities_pkg_name, context);

    } else if (container instanceof SystemAnalysis) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG,
          CtxPackage.Literals.CAPABILITY_PKG, NamingConstants.CreateSysAnalysisCmd_capabilities_pkg_name, context);

    } else if (container instanceof LogicalArchitecture) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG,
          LaPackage.Literals.CAPABILITY_REALIZATION_PKG,
          NamingConstants.CreateCommonCmd_capability_realisation_pkg_name, context);

    } else if (container instanceof PhysicalArchitecture) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG,
          LaPackage.Literals.CAPABILITY_REALIZATION_PKG,
          NamingConstants.CreateCommonCmd_capability_realisation_pkg_name, context);

    } else if (container instanceof OperationalCapabilityPkg) {
      createObject(elementId, containerId,
          OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITY_PKGS,
          OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG, elementId, context);

    } else if (container instanceof CapabilityPkg) {
      createObject(elementId, containerId, CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITY_PKGS,
          CtxPackage.Literals.CAPABILITY_PKG, elementId, context);

    } else if (container instanceof CapabilityRealizationPkg) {
      createObject(elementId, containerId,
          LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATION_PKGS,
          LaPackage.Literals.CAPABILITY_REALIZATION_PKG, elementId, context);

    }
  }

  public static <T> T createComponentPkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalAnalysis) {
      return createObject(elementId, containerId, OaPackage.Literals.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG,
          OaPackage.Literals.ENTITY_PKG, NamingConstants.CreateOpAnalysisCmd_operationalEntities_pkg_name, context);

    } else if (container instanceof SystemAnalysis) {
      return createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG,
          CtxPackage.Literals.SYSTEM_COMPONENT_PKG, NamingConstants.CreateSysAnalysisCmd_actors_pkg_name, context);

    } else if (container instanceof LogicalArchitecture) {
      return createObject(elementId, containerId, LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG,
          LaPackage.Literals.LOGICAL_COMPONENT_PKG, NamingConstants.CreateLogicalArchCmd_actors_pkg_name, context);

    } else if (container instanceof PhysicalArchitecture) {
      return createObject(elementId, containerId,
          PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG,
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG, NamingConstants.CreatePhysicalArchCmd_actors_pkg_name, context);

    } else if (container instanceof LogicalComponent) {
      return createObject(elementId, containerId, LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS,
          LaPackage.Literals.LOGICAL_COMPONENT_PKG, NamingConstants.CreateLogicalArchCmd_actors_pkg_name, context);

    } else if (container instanceof PhysicalComponent) {
      return createObject(elementId, containerId, PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS,
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG, NamingConstants.CreatePhysicalArchCmd_actors_pkg_name, context);

    } else if (container instanceof EntityPkg) {
      return createObject(elementId, containerId, OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS,
          OaPackage.Literals.ENTITY_PKG, elementId, context);

    } else if (container instanceof SystemComponentPkg) {
      return createObject(elementId, containerId, CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS,
          CtxPackage.Literals.SYSTEM_COMPONENT_PKG, elementId, context);

    } else if (container instanceof LogicalComponentPkg) {
      return createObject(elementId, containerId,
          LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS,
          LaPackage.Literals.LOGICAL_COMPONENT_PKG, NamingConstants.CreateLogicalArchCmd_actors_pkg_name, context);

    } else if (container instanceof PhysicalComponentPkg) {
      return createObject(elementId, containerId,
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS,
          PaPackage.Literals.PHYSICAL_COMPONENT_PKG, NamingConstants.CreatePhysicalArchCmd_actors_pkg_name, context);
    }
    return null;
  }

  public static void createDataPkg(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_DATA_PKG,
        InformationPackage.Literals.DATA_PKG, NamingConstants.CreateCommonCmd_data_pkg_name, context);
  }

  public static void createAssociation(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, InformationPackage.Literals.ASSOCIATION_PKG__OWNED_ASSOCIATIONS,
        InformationPackage.Literals.ASSOCIATION, elementId, context);
  }

  public static void createAssociationProperty(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS,
        InformationPackage.Literals.PROPERTY, elementId, context);
  }

  public static void createInterfacePkg(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);
    if (container instanceof Block) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK__OWNED_INTERFACE_PKG,
          CsPackage.Literals.INTERFACE_PKG, elementId, context);

    } else if (container instanceof BlockArchitecture) {
      createObject(elementId, containerId, CsPackage.Literals.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG,
          CsPackage.Literals.INTERFACE_PKG, NamingConstants.CreateCommonCmd_interfaces_pkg_name, context);
    }
  }

  public static <T> T createObject(final String id, final String containerId, final EStructuralFeature feature,
      final EClass clazz, final String name, final SessionContext context) {

    context.getExecutionManager().execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        EObject container = context.getSemanticElement(containerId);
        EObject result = ((EPackage) clazz.eContainer()).getEFactoryInstance().create(clazz);
        if (feature.isMany()) {
          ((EList) container.eGet(feature)).add(result);
        } else {
          container.eSet(feature, result);
        }
        CapellaServices.getService().creationService(result);
        if (result instanceof AbstractNamedElement) {
          ((AbstractNamedElement) result).setName(name);
        }
        context.putSemanticElement(id, result);
      }
    });

    return context.getSemanticElement(id);
  }

  public static void moveObject(final String elementId, final String containerId, final SessionContext context) {
    EObject element = context.getSemanticElement(elementId);
    setReference(containerId, elementId, element.eContainingFeature(), context);
  }

  public static void setReference(final String elementId, final String valueId, final EStructuralFeature feature,
      final SessionContext context) {
    EObject value = context.getSemanticElement(valueId);
    EObject element = context.getSemanticElement(elementId);
    setReference(element, value, feature, context);
  }

  public static void setReference(final EObject element, final EObject value, final EStructuralFeature feature,
      final SessionContext context) {
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        if (feature.isMany()) {
          ((EList) element.eGet(feature)).add(value);
        } else {
          element.eSet(feature, value);
        }
      }
    });
  }

  public static void setAttribute(final EObject element, final Object value, final EStructuralFeature feature,
      final SessionContext context) {
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        if (feature.isMany()) {
          ((EList) element.eGet(feature)).add(value);
        } else {
          element.eSet(feature, value);
        }
      }
    });
  }

  public static void checkReference(String containerId, String elementId, EStructuralFeature feature,
      SessionContext context) {
    EObject container = context.getSemanticElement(containerId);
    EObject element = context.getSemanticElement(elementId);
    if (feature.isMany()) {
      Assert.assertTrue(((EList) container.eGet(feature)).contains(element));
    } else {
      Assert.assertTrue(container.eGet(feature) == element);
    }
  }

  public static void checkNotReference(String containerId, String elementId, EReference feature,
      SessionContext context) {
    EObject container = context.getSemanticElement(containerId);
    EObject element = context.getSemanticElement(elementId);
    if (feature.isMany()) {
      Assert.assertTrue(!((EList) container.eGet(feature)).contains(element));
    } else {
      Assert.assertTrue(container.eGet(feature) != element);
    }
  }

  public static void createSequenceMessage(String containerId, String elementId, String instanceRoleId1,
      String instanceRoleId2, SessionContext context) {
    SequenceMessage message = createObject(elementId, containerId, InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES,
        InteractionPackage.Literals.SEQUENCE_MESSAGE, elementId, context);

    MessageEnd sendEnd = createObject("end", containerId,
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, InteractionPackage.Literals.MESSAGE_END,
        "end", context);

    EventSentOperation sendEvent = createObject("event", containerId,
        InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, InteractionPackage.Literals.EVENT_SENT_OPERATION, "event",
        context);

    MessageEnd receiveEnd = createObject("end", containerId,
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, InteractionPackage.Literals.MESSAGE_END,
        "end", context);

    EventReceiptOperation receiveEvent = createObject("event", containerId,
        InteractionPackage.Literals.SCENARIO__OWNED_EVENTS, InteractionPackage.Literals.EVENT_RECEIPT_OPERATION,
        "event", context);

    setReference(message, sendEnd, InteractionPackage.Literals.SEQUENCE_MESSAGE__SENDING_END, context);
    setReference(message, receiveEnd, InteractionPackage.Literals.SEQUENCE_MESSAGE__RECEIVING_END, context);
    setReference(sendEnd, sendEvent, InteractionPackage.Literals.ABSTRACT_END__EVENT, context);
    setReference(receiveEnd, receiveEvent, InteractionPackage.Literals.ABSTRACT_END__EVENT, context);

    setReference(sendEnd, context.getSemanticElement(instanceRoleId1),
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES, context);
    setReference(receiveEnd, context.getSemanticElement(instanceRoleId2),
        InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES, context);
  }

  public static void setOperation(String messageId, String operationId, SessionContext context) {
    SequenceMessage message = context.getSemanticElement(messageId);
    AbstractEventOperation operation = context.getSemanticElement(operationId);

    setReference((message.getSendingEnd().getEvent()), operation,
        InteractionPackage.Literals.EVENT_SENT_OPERATION__OPERATION, context);
    setReference((message.getReceivingEnd().getEvent()), operation,
        InteractionPackage.Literals.EVENT_RECEIPT_OPERATION__OPERATION, context);
  }

  public static void createExchangeItem(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, CapellacorePackage.Literals.ABSTRACT_EXCHANGE_ITEM_PKG__OWNED_EXCHANGE_ITEMS,
        InformationPackage.Literals.EXCHANGE_ITEM, elementId, context);
  }

  public static void createCommunicationMean(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, OaPackage.Literals.ENTITY_PKG__OWNED_COMMUNICATION_MEANS,
        OaPackage.Literals.COMMUNICATION_MEAN, elementId, context);
  }

  public static void createInteraction(String containerId, String elementId, SessionContext context) {
    createFunctionalExchange(containerId, elementId, context);
  }

  public static void createExchangeItemElement(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, InformationPackage.Literals.EXCHANGE_ITEM__OWNED_ELEMENTS,
        InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT, elementId, context);
  }

  public static void createComponentExchange(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, CsPackage.Literals.COMPONENT_PKG__OWNED_COMPONENT_EXCHANGES,
        FaPackage.Literals.COMPONENT_EXCHANGE, elementId, context);
  }

  public static void createFunctionalExchange(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, FaPackage.Literals.ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES,
        FaPackage.Literals.FUNCTIONAL_EXCHANGE, elementId, context);
  }

  public static void setName(final SessionContext context, final String name, final String elementId) {
    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        AbstractNamedElement element = context.getSemanticElement(elementId);
        element.setName(name);
      }
    });
  }

  public static void ensureNames(final SessionContext context, final String name, final String... elementIds) {
    for (String elementId : elementIds) {
      AbstractNamedElement element = context.getSemanticElement(elementId);
      Assert.assertTrue(name.equals(element.getName()));
    }
  }

  public static void createCapability(String containerId, String elementId, SessionContext context) {
    EObject container = context.getSemanticElement(containerId);

    if (container instanceof OperationalCapabilityPkg) {
      createObject(elementId, containerId,
          OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES,
          OaPackage.Literals.OPERATIONAL_CAPABILITY, elementId, context);

    } else if (container instanceof CapabilityPkg) {
      createObject(elementId, containerId, CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES,
          CtxPackage.Literals.CAPABILITY, elementId, context);

    } else if (container instanceof CapabilityRealizationPkg) {
      createObject(elementId, containerId, LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS,
          LaPackage.Literals.CAPABILITY_REALIZATION, elementId, context);

    }

  }

  public static void createScenario(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, InteractionPackage.Literals.ABSTRACT_CAPABILITY__OWNED_SCENARIOS,
        InteractionPackage.Literals.SCENARIO, elementId, context);
  }

  public static void createInstanceRole(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        InteractionPackage.Literals.INSTANCE_ROLE, elementId, context);
  }

  public static void createRegion(final String containerId, final String elementId, final SessionContext context) {
    createObject(elementId, containerId, CapellacommonPackage.Literals.STATE_MACHINE__OWNED_REGIONS,
        CapellacommonPackage.Literals.REGION, elementId, context);
  }

  public static void createState(final String containerId, final String elementId, final SessionContext context) {
    createObject(elementId, containerId, CapellacommonPackage.Literals.REGION__OWNED_STATES,
        CapellacommonPackage.Literals.STATE, elementId, context);
  }

  public static void createStateMachine(final String containerId, final String elementId,
      final SessionContext context) {
    createObject(elementId, containerId, CapellacommonPackage.Literals.REGION__OWNED_STATES,
        CapellacommonPackage.Literals.STATE, elementId, context);
  }

  public static void moveState(final String oldContainerId, final String newContainerId, final String elementId,
      final SessionContext context) {
    moveObject(elementId, newContainerId, context);
  }

  public static void createActor(final String containerId, final String elementId, String partId,
      final SessionContext context) {
    BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(
        BlockArchitectureExt.getRootBlockArchitecture(context.getSemanticElement(containerId)));
    createComponent(containerId, elementId, partId, context, type, true);
  }

  public static Object createComponent(final String containerId, final String elementId, String partId,
      final SessionContext context, BlockArchitectureExt.Type type, boolean isActor) {
    EStructuralFeature feature = null;
    EClass clazz = null;
    switch (type) {
    case OA:
      feature = OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;
      clazz = OaPackage.Literals.ENTITY;
      break;
    case SA:
      feature = CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS;
      clazz = CtxPackage.Literals.SYSTEM_COMPONENT;
      break;
    case LA:
      feature = LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
      clazz = LaPackage.Literals.LOGICAL_COMPONENT;
      break;
    case PA:
      feature = PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS;
      clazz = PaPackage.Literals.PHYSICAL_COMPONENT;
      break;
    default:
    }
    Component object = createObject(elementId, containerId, feature, clazz, elementId, context);
    TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        object.setActor(isActor);
      }
    });
    Part part = ((Component) context.getSemanticElement(elementId)).getRepresentingParts().get(0);
    context.putSemanticElement(partId, part);
    return object;
  }

  public static Object createSubcomponent(final String containerId, final String elementId, String partId,
      final SessionContext context, BlockArchitectureExt.Type type, boolean isActor) {
    EStructuralFeature feature = null;
    EClass clazz = null;
    switch (type) {
    case SA:
      feature = CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS;
      clazz = CtxPackage.Literals.SYSTEM_COMPONENT;
      break;
    case LA:
      feature = LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS;
      clazz = LaPackage.Literals.LOGICAL_COMPONENT;
      break;
    case PA:
      feature = PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
      clazz = PaPackage.Literals.PHYSICAL_COMPONENT;
      break;
    default:
    }
    Component object = createObject(elementId, containerId, feature, clazz, elementId, context);
    TestHelper.getExecutionManager(context.getSession()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        object.setActor(isActor);
      }
    });

    Part part = ((Component) context.getSemanticElement(elementId)).getRepresentingParts().get(0);
    context.putSemanticElement(partId, part);
    return object;
  }

  public static void moveComponent(final String containerId, final String elementId, final SessionContext context) {
    Component component = context.getSemanticElement(elementId);
    Part part = component.getRepresentingParts().get(0);
    SkeletonHelper.moveObject(part.getId(), containerId, context);
    SkeletonHelper.moveObject(elementId, containerId, context);
  }

  public static void cannotMoveComponent(final String containerId, final String elementId,
      final SessionContext context) {
    Component component = context.getSemanticElement(elementId);
    Part part = component.getRepresentingParts().get(0);
    SkeletonHelper.moveObject(part.getId(), containerId, context);
    SkeletonHelper.moveObject(elementId, containerId, context);
  }

  public static void createInstanceRole(final String containerId, final String elementId, final String instanceId,
      final SessionContext context) {
    createObject(elementId, containerId, InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES,
        InteractionPackage.Literals.INSTANCE_ROLE, elementId, context);

    context.getExecutionManager().execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        InstanceRole role = context.getSemanticElement(elementId);
        Part part = context.getSemanticElement(instanceId);
        role.setRepresentedInstance(part);
      }
    });
  }

  public static void createCommunicationLinkSend(String linkId, String componentId, String itemId,
      SessionContext context) {
    CommunicationLink link = createObject(linkId, componentId,
        CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS,
        CommunicationPackage.Literals.COMMUNICATION_LINK, linkId, context);
    ExchangeItem item = context.getSemanticElement(itemId);
    setAttribute(link, CommunicationLinkKind.SEND, CommunicationPackage.Literals.COMMUNICATION_LINK__KIND, context);
    setReference(link, item, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM, context);
  }

  public static void createCommunicationLinkReceive(String linkId, String componentId, String itemId,
      SessionContext context) {
    CommunicationLink link = createObject(linkId, componentId,
        CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS,
        CommunicationPackage.Literals.COMMUNICATION_LINK, linkId, context);
    ExchangeItem item = context.getSemanticElement(itemId);
    setAttribute(link, CommunicationLinkKind.RECEIVE, CommunicationPackage.Literals.COMMUNICATION_LINK__KIND, context);
    setReference(link, item, CommunicationPackage.Literals.COMMUNICATION_LINK__EXCHANGE_ITEM, context);
  }

  public static void createInterface(String containerId, String elementId, SessionContext context) {
    createObject(elementId, containerId, CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACES,
        CsPackage.Literals.INTERFACE, elementId, context);
  }

  public static void createExchangeItemAllocation(String linkId, String interfaceId, String itemId,
      SessionContext context) {
    EObject source = createObject(linkId, interfaceId, CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS,
        CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION, linkId, context);

    ExchangeItem item = context.getSemanticElement(itemId);
    setReference(source, item, CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM, context);
  }
}
