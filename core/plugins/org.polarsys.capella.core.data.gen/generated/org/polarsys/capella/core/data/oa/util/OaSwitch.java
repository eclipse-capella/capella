/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.oa.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.common.data.activity.ActivityGroup;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.CallAction;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InvocationAction;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.*;
import org.polarsys.capella.core.data.oa.AbstractConceptItem;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CapabilityConfiguration;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.CommunityOfInterest;
import org.polarsys.capella.core.data.oa.CommunityOfInterestComposition;
import org.polarsys.capella.core.data.oa.Concept;
import org.polarsys.capella.core.data.oa.ConceptCompliance;
import org.polarsys.capella.core.data.oa.ConceptPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.ItemInConcept;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.OperationalScenario;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.oa.Swimlane;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.oa.OaPackage
 * @generated
 */
public class OaSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static OaPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OaSwitch() {
    if (modelPackage == null) {
      modelPackage = OaPackage.eINSTANCE;
    }
  }

	/**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

	/**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case OaPackage.OPERATIONAL_ANALYSIS: {
        OperationalAnalysis operationalAnalysis = (OperationalAnalysis)theEObject;
        T result = caseOperationalAnalysis(operationalAnalysis);
        if (result == null) result = caseBlockArchitecture(operationalAnalysis);
        if (result == null) result = caseAbstractFunctionalArchitecture(operationalAnalysis);
        if (result == null) result = caseModellingArchitecture(operationalAnalysis);
        if (result == null) result = caseStructure(operationalAnalysis);
        if (result == null) result = caseNamespace(operationalAnalysis);
        if (result == null) result = caseNamedElement(operationalAnalysis);
        if (result == null) result = caseAbstractNamedElement(operationalAnalysis);
        if (result == null) result = caseCapellaElement(operationalAnalysis);
        if (result == null) result = caseTraceableElement(operationalAnalysis);
        if (result == null) result = casePublishableElement(operationalAnalysis);
        if (result == null) result = caseModelElement(operationalAnalysis);
        if (result == null) result = caseExtensibleElement(operationalAnalysis);
        if (result == null) result = caseElement(operationalAnalysis);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_SCENARIO: {
        OperationalScenario operationalScenario = (OperationalScenario)theEObject;
        T result = caseOperationalScenario(operationalScenario);
        if (result == null) result = caseNamedElement(operationalScenario);
        if (result == null) result = caseAbstractNamedElement(operationalScenario);
        if (result == null) result = caseCapellaElement(operationalScenario);
        if (result == null) result = caseTraceableElement(operationalScenario);
        if (result == null) result = casePublishableElement(operationalScenario);
        if (result == null) result = caseModelElement(operationalScenario);
        if (result == null) result = caseExtensibleElement(operationalScenario);
        if (result == null) result = caseElement(operationalScenario);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_ACTIVITY_PKG: {
        OperationalActivityPkg operationalActivityPkg = (OperationalActivityPkg)theEObject;
        T result = caseOperationalActivityPkg(operationalActivityPkg);
        if (result == null) result = caseFunctionPkg(operationalActivityPkg);
        if (result == null) result = caseStructure(operationalActivityPkg);
        if (result == null) result = caseNamespace(operationalActivityPkg);
        if (result == null) result = caseNamedElement(operationalActivityPkg);
        if (result == null) result = caseAbstractNamedElement(operationalActivityPkg);
        if (result == null) result = caseCapellaElement(operationalActivityPkg);
        if (result == null) result = caseTraceableElement(operationalActivityPkg);
        if (result == null) result = casePublishableElement(operationalActivityPkg);
        if (result == null) result = caseModelElement(operationalActivityPkg);
        if (result == null) result = caseExtensibleElement(operationalActivityPkg);
        if (result == null) result = caseElement(operationalActivityPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_ACTIVITY: {
        OperationalActivity operationalActivity = (OperationalActivity)theEObject;
        T result = caseOperationalActivity(operationalActivity);
        if (result == null) result = caseAbstractFunction(operationalActivity);
        if (result == null) result = caseNamespace(operationalActivity);
        if (result == null) result = caseInvolvedElement(operationalActivity);
        if (result == null) result = caseAbstractInstance(operationalActivity);
        if (result == null) result = caseAbstractFunctionalChainContainer(operationalActivity);
        if (result == null) result = caseCallBehaviorAction(operationalActivity);
        if (result == null) result = caseAbstractEvent(operationalActivity);
        if (result == null) result = caseProperty(operationalActivity);
        if (result == null) result = caseCallAction(operationalActivity);
        if (result == null) result = caseAbstractType(operationalActivity);
        if (result == null) result = caseFeature(operationalActivity);
        if (result == null) result = caseTypedElement(operationalActivity);
        if (result == null) result = caseMultiplicityElement(operationalActivity);
        if (result == null) result = caseFinalizableElement(operationalActivity);
        if (result == null) result = caseInvocationAction(operationalActivity);
        if (result == null) result = caseNamedElement(operationalActivity);
        if (result == null) result = caseCapellaElement(operationalActivity);
        if (result == null) result = caseTraceableElement(operationalActivity);
        if (result == null) result = casePublishableElement(operationalActivity);
        if (result == null) result = caseAbstractTypedElement(operationalActivity);
        if (result == null) result = caseAbstractAction(operationalActivity);
        if (result == null) result = caseModelElement(operationalActivity);
        if (result == null) result = caseExtensibleElement(operationalActivity);
        if (result == null) result = caseExecutableNode(operationalActivity);
        if (result == null) result = caseElement(operationalActivity);
        if (result == null) result = caseActivityNode(operationalActivity);
        if (result == null) result = caseAbstractNamedElement(operationalActivity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_PROCESS: {
        OperationalProcess operationalProcess = (OperationalProcess)theEObject;
        T result = caseOperationalProcess(operationalProcess);
        if (result == null) result = caseFunctionalChain(operationalProcess);
        if (result == null) result = caseNamedElement(operationalProcess);
        if (result == null) result = caseInvolverElement(operationalProcess);
        if (result == null) result = caseInvolvedElement(operationalProcess);
        if (result == null) result = caseAbstractNamedElement(operationalProcess);
        if (result == null) result = caseCapellaElement(operationalProcess);
        if (result == null) result = caseTraceableElement(operationalProcess);
        if (result == null) result = casePublishableElement(operationalProcess);
        if (result == null) result = caseModelElement(operationalProcess);
        if (result == null) result = caseExtensibleElement(operationalProcess);
        if (result == null) result = caseElement(operationalProcess);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.SWIMLANE: {
        Swimlane swimlane = (Swimlane)theEObject;
        T result = caseSwimlane(swimlane);
        if (result == null) result = caseNamedElement(swimlane);
        if (result == null) result = caseActivityPartition(swimlane);
        if (result == null) result = caseAbstractNamedElement(swimlane);
        if (result == null) result = caseCapellaElement(swimlane);
        if (result == null) result = caseActivityGroup(swimlane);
        if (result == null) result = caseTraceableElement(swimlane);
        if (result == null) result = casePublishableElement(swimlane);
        if (result == null) result = caseModelElement(swimlane);
        if (result == null) result = caseExtensibleElement(swimlane);
        if (result == null) result = caseElement(swimlane);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_CAPABILITY_PKG: {
        OperationalCapabilityPkg operationalCapabilityPkg = (OperationalCapabilityPkg)theEObject;
        T result = caseOperationalCapabilityPkg(operationalCapabilityPkg);
        if (result == null) result = caseAbstractCapabilityPkg(operationalCapabilityPkg);
        if (result == null) result = caseStructure(operationalCapabilityPkg);
        if (result == null) result = caseNamespace(operationalCapabilityPkg);
        if (result == null) result = caseNamedElement(operationalCapabilityPkg);
        if (result == null) result = caseAbstractNamedElement(operationalCapabilityPkg);
        if (result == null) result = caseCapellaElement(operationalCapabilityPkg);
        if (result == null) result = caseTraceableElement(operationalCapabilityPkg);
        if (result == null) result = casePublishableElement(operationalCapabilityPkg);
        if (result == null) result = caseModelElement(operationalCapabilityPkg);
        if (result == null) result = caseExtensibleElement(operationalCapabilityPkg);
        if (result == null) result = caseElement(operationalCapabilityPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.OPERATIONAL_CAPABILITY: {
        OperationalCapability operationalCapability = (OperationalCapability)theEObject;
        T result = caseOperationalCapability(operationalCapability);
        if (result == null) result = caseAbstractCapability(operationalCapability);
        if (result == null) result = caseStructure(operationalCapability);
        if (result == null) result = caseInvolverElement(operationalCapability);
        if (result == null) result = caseAbstractFunctionalChainContainer(operationalCapability);
        if (result == null) result = caseNamespace(operationalCapability);
        if (result == null) result = caseNamedElement(operationalCapability);
        if (result == null) result = caseAbstractNamedElement(operationalCapability);
        if (result == null) result = caseCapellaElement(operationalCapability);
        if (result == null) result = caseTraceableElement(operationalCapability);
        if (result == null) result = casePublishableElement(operationalCapability);
        if (result == null) result = caseModelElement(operationalCapability);
        if (result == null) result = caseExtensibleElement(operationalCapability);
        if (result == null) result = caseElement(operationalCapability);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ACTIVITY_ALLOCATION: {
        ActivityAllocation activityAllocation = (ActivityAllocation)theEObject;
        T result = caseActivityAllocation(activityAllocation);
        if (result == null) result = caseAllocation(activityAllocation);
        if (result == null) result = caseRelationship(activityAllocation);
        if (result == null) result = caseAbstractTrace(activityAllocation);
        if (result == null) result = caseAbstractRelationship(activityAllocation);
        if (result == null) result = caseCapellaElement(activityAllocation);
        if (result == null) result = caseTraceableElement(activityAllocation);
        if (result == null) result = casePublishableElement(activityAllocation);
        if (result == null) result = caseModelElement(activityAllocation);
        if (result == null) result = caseExtensibleElement(activityAllocation);
        if (result == null) result = caseElement(activityAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ROLE_PKG: {
        RolePkg rolePkg = (RolePkg)theEObject;
        T result = caseRolePkg(rolePkg);
        if (result == null) result = caseStructure(rolePkg);
        if (result == null) result = caseNamespace(rolePkg);
        if (result == null) result = caseNamedElement(rolePkg);
        if (result == null) result = caseAbstractNamedElement(rolePkg);
        if (result == null) result = caseCapellaElement(rolePkg);
        if (result == null) result = caseTraceableElement(rolePkg);
        if (result == null) result = casePublishableElement(rolePkg);
        if (result == null) result = caseModelElement(rolePkg);
        if (result == null) result = caseExtensibleElement(rolePkg);
        if (result == null) result = caseElement(rolePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ROLE: {
        Role role = (Role)theEObject;
        T result = caseRole(role);
        if (result == null) result = caseAbstractInstance(role);
        if (result == null) result = caseProperty(role);
        if (result == null) result = caseFeature(role);
        if (result == null) result = caseTypedElement(role);
        if (result == null) result = caseMultiplicityElement(role);
        if (result == null) result = caseFinalizableElement(role);
        if (result == null) result = caseNamedElement(role);
        if (result == null) result = caseAbstractTypedElement(role);
        if (result == null) result = caseAbstractNamedElement(role);
        if (result == null) result = caseCapellaElement(role);
        if (result == null) result = caseTraceableElement(role);
        if (result == null) result = casePublishableElement(role);
        if (result == null) result = caseModelElement(role);
        if (result == null) result = caseExtensibleElement(role);
        if (result == null) result = caseElement(role);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ROLE_ASSEMBLY_USAGE: {
        RoleAssemblyUsage roleAssemblyUsage = (RoleAssemblyUsage)theEObject;
        T result = caseRoleAssemblyUsage(roleAssemblyUsage);
        if (result == null) result = caseNamedElement(roleAssemblyUsage);
        if (result == null) result = caseAbstractNamedElement(roleAssemblyUsage);
        if (result == null) result = caseCapellaElement(roleAssemblyUsage);
        if (result == null) result = caseTraceableElement(roleAssemblyUsage);
        if (result == null) result = casePublishableElement(roleAssemblyUsage);
        if (result == null) result = caseModelElement(roleAssemblyUsage);
        if (result == null) result = caseExtensibleElement(roleAssemblyUsage);
        if (result == null) result = caseElement(roleAssemblyUsage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ROLE_ALLOCATION: {
        RoleAllocation roleAllocation = (RoleAllocation)theEObject;
        T result = caseRoleAllocation(roleAllocation);
        if (result == null) result = caseAllocation(roleAllocation);
        if (result == null) result = caseRelationship(roleAllocation);
        if (result == null) result = caseAbstractTrace(roleAllocation);
        if (result == null) result = caseAbstractRelationship(roleAllocation);
        if (result == null) result = caseCapellaElement(roleAllocation);
        if (result == null) result = caseTraceableElement(roleAllocation);
        if (result == null) result = casePublishableElement(roleAllocation);
        if (result == null) result = caseModelElement(roleAllocation);
        if (result == null) result = caseExtensibleElement(roleAllocation);
        if (result == null) result = caseElement(roleAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ENTITY_PKG: {
        EntityPkg entityPkg = (EntityPkg)theEObject;
        T result = caseEntityPkg(entityPkg);
        if (result == null) result = caseComponentPkg(entityPkg);
        if (result == null) result = caseStructure(entityPkg);
        if (result == null) result = caseNamespace(entityPkg);
        if (result == null) result = caseNamedElement(entityPkg);
        if (result == null) result = caseAbstractNamedElement(entityPkg);
        if (result == null) result = caseCapellaElement(entityPkg);
        if (result == null) result = caseTraceableElement(entityPkg);
        if (result == null) result = casePublishableElement(entityPkg);
        if (result == null) result = caseModelElement(entityPkg);
        if (result == null) result = caseExtensibleElement(entityPkg);
        if (result == null) result = caseElement(entityPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ENTITY: {
        Entity entity = (Entity)theEObject;
        T result = caseEntity(entity);
        if (result == null) result = caseAbstractConceptItem(entity);
        if (result == null) result = caseInformationsExchanger(entity);
        if (result == null) result = caseInvolvedElement(entity);
        if (result == null) result = caseComponent(entity);
        if (result == null) result = caseBlock(entity);
        if (result == null) result = caseClassifier(entity);
        if (result == null) result = caseInterfaceAllocator(entity);
        if (result == null) result = caseCommunicationLinkExchanger(entity);
        if (result == null) result = caseAbstractFunctionalBlock(entity);
        if (result == null) result = caseGeneralizableElement(entity);
        if (result == null) result = caseModellingBlock(entity);
        if (result == null) result = caseType(entity);
        if (result == null) result = caseAbstractType(entity);
        if (result == null) result = caseNamespace(entity);
        if (result == null) result = caseNamedElement(entity);
        if (result == null) result = caseAbstractNamedElement(entity);
        if (result == null) result = caseCapellaElement(entity);
        if (result == null) result = caseExtensibleElement(entity);
        if (result == null) result = caseTraceableElement(entity);
        if (result == null) result = casePublishableElement(entity);
        if (result == null) result = caseModelElement(entity);
        if (result == null) result = caseElement(entity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.CONCEPT_PKG: {
        ConceptPkg conceptPkg = (ConceptPkg)theEObject;
        T result = caseConceptPkg(conceptPkg);
        if (result == null) result = caseStructure(conceptPkg);
        if (result == null) result = caseNamespace(conceptPkg);
        if (result == null) result = caseNamedElement(conceptPkg);
        if (result == null) result = caseAbstractNamedElement(conceptPkg);
        if (result == null) result = caseCapellaElement(conceptPkg);
        if (result == null) result = caseTraceableElement(conceptPkg);
        if (result == null) result = casePublishableElement(conceptPkg);
        if (result == null) result = caseModelElement(conceptPkg);
        if (result == null) result = caseExtensibleElement(conceptPkg);
        if (result == null) result = caseElement(conceptPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.CONCEPT: {
        Concept concept = (Concept)theEObject;
        T result = caseConcept(concept);
        if (result == null) result = caseNamedElement(concept);
        if (result == null) result = caseAbstractNamedElement(concept);
        if (result == null) result = caseCapellaElement(concept);
        if (result == null) result = caseTraceableElement(concept);
        if (result == null) result = casePublishableElement(concept);
        if (result == null) result = caseModelElement(concept);
        if (result == null) result = caseExtensibleElement(concept);
        if (result == null) result = caseElement(concept);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.CONCEPT_COMPLIANCE: {
        ConceptCompliance conceptCompliance = (ConceptCompliance)theEObject;
        T result = caseConceptCompliance(conceptCompliance);
        if (result == null) result = caseRelationship(conceptCompliance);
        if (result == null) result = caseAbstractRelationship(conceptCompliance);
        if (result == null) result = caseCapellaElement(conceptCompliance);
        if (result == null) result = caseTraceableElement(conceptCompliance);
        if (result == null) result = casePublishableElement(conceptCompliance);
        if (result == null) result = caseModelElement(conceptCompliance);
        if (result == null) result = caseExtensibleElement(conceptCompliance);
        if (result == null) result = caseElement(conceptCompliance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ITEM_IN_CONCEPT: {
        ItemInConcept itemInConcept = (ItemInConcept)theEObject;
        T result = caseItemInConcept(itemInConcept);
        if (result == null) result = caseNamedElement(itemInConcept);
        if (result == null) result = caseAbstractNamedElement(itemInConcept);
        if (result == null) result = caseCapellaElement(itemInConcept);
        if (result == null) result = caseTraceableElement(itemInConcept);
        if (result == null) result = casePublishableElement(itemInConcept);
        if (result == null) result = caseModelElement(itemInConcept);
        if (result == null) result = caseExtensibleElement(itemInConcept);
        if (result == null) result = caseElement(itemInConcept);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ABSTRACT_CONCEPT_ITEM: {
        AbstractConceptItem abstractConceptItem = (AbstractConceptItem)theEObject;
        T result = caseAbstractConceptItem(abstractConceptItem);
        if (result == null) result = caseComponent(abstractConceptItem);
        if (result == null) result = caseBlock(abstractConceptItem);
        if (result == null) result = caseClassifier(abstractConceptItem);
        if (result == null) result = caseInterfaceAllocator(abstractConceptItem);
        if (result == null) result = caseCommunicationLinkExchanger(abstractConceptItem);
        if (result == null) result = caseAbstractFunctionalBlock(abstractConceptItem);
        if (result == null) result = caseGeneralizableElement(abstractConceptItem);
        if (result == null) result = caseModellingBlock(abstractConceptItem);
        if (result == null) result = caseType(abstractConceptItem);
        if (result == null) result = caseAbstractType(abstractConceptItem);
        if (result == null) result = caseNamespace(abstractConceptItem);
        if (result == null) result = caseNamedElement(abstractConceptItem);
        if (result == null) result = caseAbstractNamedElement(abstractConceptItem);
        if (result == null) result = caseCapellaElement(abstractConceptItem);
        if (result == null) result = caseExtensibleElement(abstractConceptItem);
        if (result == null) result = caseTraceableElement(abstractConceptItem);
        if (result == null) result = casePublishableElement(abstractConceptItem);
        if (result == null) result = caseModelElement(abstractConceptItem);
        if (result == null) result = caseElement(abstractConceptItem);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.COMMUNITY_OF_INTEREST: {
        CommunityOfInterest communityOfInterest = (CommunityOfInterest)theEObject;
        T result = caseCommunityOfInterest(communityOfInterest);
        if (result == null) result = caseNamedElement(communityOfInterest);
        if (result == null) result = caseAbstractNamedElement(communityOfInterest);
        if (result == null) result = caseCapellaElement(communityOfInterest);
        if (result == null) result = caseTraceableElement(communityOfInterest);
        if (result == null) result = casePublishableElement(communityOfInterest);
        if (result == null) result = caseModelElement(communityOfInterest);
        if (result == null) result = caseExtensibleElement(communityOfInterest);
        if (result == null) result = caseElement(communityOfInterest);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION: {
        CommunityOfInterestComposition communityOfInterestComposition = (CommunityOfInterestComposition)theEObject;
        T result = caseCommunityOfInterestComposition(communityOfInterestComposition);
        if (result == null) result = caseNamedElement(communityOfInterestComposition);
        if (result == null) result = caseAbstractNamedElement(communityOfInterestComposition);
        if (result == null) result = caseCapellaElement(communityOfInterestComposition);
        if (result == null) result = caseTraceableElement(communityOfInterestComposition);
        if (result == null) result = casePublishableElement(communityOfInterestComposition);
        if (result == null) result = caseModelElement(communityOfInterestComposition);
        if (result == null) result = caseExtensibleElement(communityOfInterestComposition);
        if (result == null) result = caseElement(communityOfInterestComposition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ORGANISATIONAL_UNIT: {
        OrganisationalUnit organisationalUnit = (OrganisationalUnit)theEObject;
        T result = caseOrganisationalUnit(organisationalUnit);
        if (result == null) result = caseNamedElement(organisationalUnit);
        if (result == null) result = caseAbstractNamedElement(organisationalUnit);
        if (result == null) result = caseCapellaElement(organisationalUnit);
        if (result == null) result = caseTraceableElement(organisationalUnit);
        if (result == null) result = casePublishableElement(organisationalUnit);
        if (result == null) result = caseModelElement(organisationalUnit);
        if (result == null) result = caseExtensibleElement(organisationalUnit);
        if (result == null) result = caseElement(organisationalUnit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION: {
        OrganisationalUnitComposition organisationalUnitComposition = (OrganisationalUnitComposition)theEObject;
        T result = caseOrganisationalUnitComposition(organisationalUnitComposition);
        if (result == null) result = caseNamedElement(organisationalUnitComposition);
        if (result == null) result = caseAbstractNamedElement(organisationalUnitComposition);
        if (result == null) result = caseCapellaElement(organisationalUnitComposition);
        if (result == null) result = caseTraceableElement(organisationalUnitComposition);
        if (result == null) result = casePublishableElement(organisationalUnitComposition);
        if (result == null) result = caseModelElement(organisationalUnitComposition);
        if (result == null) result = caseExtensibleElement(organisationalUnitComposition);
        if (result == null) result = caseElement(organisationalUnitComposition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.LOCATION: {
        Location location = (Location)theEObject;
        T result = caseLocation(location);
        if (result == null) result = caseAbstractConceptItem(location);
        if (result == null) result = caseComponent(location);
        if (result == null) result = caseBlock(location);
        if (result == null) result = caseClassifier(location);
        if (result == null) result = caseInterfaceAllocator(location);
        if (result == null) result = caseCommunicationLinkExchanger(location);
        if (result == null) result = caseAbstractFunctionalBlock(location);
        if (result == null) result = caseGeneralizableElement(location);
        if (result == null) result = caseModellingBlock(location);
        if (result == null) result = caseType(location);
        if (result == null) result = caseAbstractType(location);
        if (result == null) result = caseNamespace(location);
        if (result == null) result = caseNamedElement(location);
        if (result == null) result = caseAbstractNamedElement(location);
        if (result == null) result = caseCapellaElement(location);
        if (result == null) result = caseExtensibleElement(location);
        if (result == null) result = caseTraceableElement(location);
        if (result == null) result = casePublishableElement(location);
        if (result == null) result = caseModelElement(location);
        if (result == null) result = caseElement(location);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.CAPABILITY_CONFIGURATION: {
        CapabilityConfiguration capabilityConfiguration = (CapabilityConfiguration)theEObject;
        T result = caseCapabilityConfiguration(capabilityConfiguration);
        if (result == null) result = caseAbstractConceptItem(capabilityConfiguration);
        if (result == null) result = caseComponent(capabilityConfiguration);
        if (result == null) result = caseBlock(capabilityConfiguration);
        if (result == null) result = caseClassifier(capabilityConfiguration);
        if (result == null) result = caseInterfaceAllocator(capabilityConfiguration);
        if (result == null) result = caseCommunicationLinkExchanger(capabilityConfiguration);
        if (result == null) result = caseAbstractFunctionalBlock(capabilityConfiguration);
        if (result == null) result = caseGeneralizableElement(capabilityConfiguration);
        if (result == null) result = caseModellingBlock(capabilityConfiguration);
        if (result == null) result = caseType(capabilityConfiguration);
        if (result == null) result = caseAbstractType(capabilityConfiguration);
        if (result == null) result = caseNamespace(capabilityConfiguration);
        if (result == null) result = caseNamedElement(capabilityConfiguration);
        if (result == null) result = caseAbstractNamedElement(capabilityConfiguration);
        if (result == null) result = caseCapellaElement(capabilityConfiguration);
        if (result == null) result = caseExtensibleElement(capabilityConfiguration);
        if (result == null) result = caseTraceableElement(capabilityConfiguration);
        if (result == null) result = casePublishableElement(capabilityConfiguration);
        if (result == null) result = caseModelElement(capabilityConfiguration);
        if (result == null) result = caseElement(capabilityConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.COMMUNICATION_MEAN: {
        CommunicationMean communicationMean = (CommunicationMean)theEObject;
        T result = caseCommunicationMean(communicationMean);
        if (result == null) result = caseNamedRelationship(communicationMean);
        if (result == null) result = caseComponentExchange(communicationMean);
        if (result == null) result = caseRelationship(communicationMean);
        if (result == null) result = caseAbstractEvent(communicationMean);
        if (result == null) result = caseAbstractEventOperation(communicationMean);
        if (result == null) result = caseExchangeSpecification(communicationMean);
        if (result == null) result = caseCapellaElement(communicationMean);
        if (result == null) result = caseNamedElement(communicationMean);
        if (result == null) result = caseAbstractType(communicationMean);
        if (result == null) result = caseActivityExchange(communicationMean);
        if (result == null) result = caseTraceableElement(communicationMean);
        if (result == null) result = casePublishableElement(communicationMean);
        if (result == null) result = caseAbstractInformationFlow(communicationMean);
        if (result == null) result = caseAbstractRelationship(communicationMean);
        if (result == null) result = caseModelElement(communicationMean);
        if (result == null) result = caseExtensibleElement(communicationMean);
        if (result == null) result = caseAbstractNamedElement(communicationMean);
        if (result == null) result = caseElement(communicationMean);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case OaPackage.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT: {
        EntityOperationalCapabilityInvolvement entityOperationalCapabilityInvolvement = (EntityOperationalCapabilityInvolvement)theEObject;
        T result = caseEntityOperationalCapabilityInvolvement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseInvolvement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseRelationship(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseAbstractRelationship(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseCapellaElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseTraceableElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = casePublishableElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseModelElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseExtensibleElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = caseElement(entityOperationalCapabilityInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Analysis</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Analysis</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalAnalysis(OperationalAnalysis object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Scenario</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Scenario</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalScenario(OperationalScenario object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Activity Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Activity Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalActivityPkg(OperationalActivityPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Activity</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalActivity(OperationalActivity object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Process</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Process</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalProcess(OperationalProcess object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Swimlane</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Swimlane</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSwimlane(Swimlane object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Capability Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalCapabilityPkg(OperationalCapabilityPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Operational Capability</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operational Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOperationalCapability(OperationalCapability object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Activity Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Activity Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityAllocation(ActivityAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Role Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Role Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRolePkg(RolePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Role</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRole(Role object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Role Assembly Usage</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Role Assembly Usage</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRoleAssemblyUsage(RoleAssemblyUsage object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Role Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Role Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRoleAllocation(RoleAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Entity Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEntityPkg(EntityPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEntity(Entity object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Concept Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Concept Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConceptPkg(ConceptPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Concept</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Concept</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConcept(Concept object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Concept Compliance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Concept Compliance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConceptCompliance(ConceptCompliance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Item In Concept</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Item In Concept</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseItemInConcept(ItemInConcept object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Concept Item</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Concept Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractConceptItem(AbstractConceptItem object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Community Of Interest</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Community Of Interest</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCommunityOfInterest(CommunityOfInterest object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Community Of Interest Composition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Community Of Interest Composition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCommunityOfInterestComposition(CommunityOfInterestComposition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Organisational Unit</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Organisational Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOrganisationalUnit(OrganisationalUnit object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Organisational Unit Composition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Organisational Unit Composition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOrganisationalUnitComposition(OrganisationalUnitComposition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Location</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Location</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLocation(Location object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capability Configuration</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityConfiguration(CapabilityConfiguration object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Communication Mean</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Communication Mean</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCommunicationMean(CommunicationMean object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Entity Operational Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Entity Operational Capability Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEntityOperationalCapabilityInvolvement(EntityOperationalCapabilityInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseElement(Element object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModelElement(ModelElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTraceableElement(TraceableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePublishableElement(PublishableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapellaElement(CapellaElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedElement(NamedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamespace(Namespace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Structure</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructure(Structure object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingArchitecture(ModellingArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalArchitecture(AbstractFunctionalArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Block Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBlockArchitecture(BlockArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionPkg(FunctionPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvedElement(InvolvedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFeature(Feature object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTypedElement(AbstractTypedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypedElement(TypedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMultiplicityElement(MultiplicityElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Finalizable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Finalizable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFinalizableElement(FinalizableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseProperty(Property object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractInstance(AbstractInstance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalChainContainer(AbstractFunctionalChainContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityNode(ActivityNode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Executable Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Executable Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExecutableNode(ExecutableNode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractAction(AbstractAction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Invocation Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Invocation Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvocationAction(InvocationAction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Call Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCallAction(CallAction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Call Behavior Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Behavior Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCallBehaviorAction(CallBehaviorAction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractType(AbstractType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEvent(AbstractEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Function</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunction(AbstractFunction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolverElement(InvolverElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChain(FunctionalChain object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityGroup(ActivityGroup object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Partition</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Partition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityPartition(ActivityPartition object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityPkg(AbstractCapabilityPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapability(AbstractCapability object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractRelationship(AbstractRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRelationship(Relationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Trace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTrace(AbstractTrace object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAllocation(Allocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseClassifier(Classifier object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentPkg(ComponentPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseType(Type object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingBlock(ModellingBlock object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalBlock(AbstractFunctionalBlock object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBlock(Block object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Generalizable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Generalizable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGeneralizableElement(GeneralizableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Allocator</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Allocator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfaceAllocator(InterfaceAllocator object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Link Exchanger</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link Exchanger</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCommunicationLinkExchanger(CommunicationLinkExchanger object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponent(Component object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Informations Exchanger</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Informations Exchanger</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInformationsExchanger(InformationsExchanger object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedRelationship(NamedRelationship object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Event Operation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Event Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEventOperation(AbstractEventOperation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Information Flow</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Information Flow</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractInformationFlow(AbstractInformationFlow object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityExchange(ActivityExchange object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeSpecification(ExchangeSpecification object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchange(ComponentExchange object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvement(Involvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
	@Override
	public T defaultCase(EObject object) {
    return null;
  }

} //OaSwitch
