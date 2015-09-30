/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.ctx.util;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.CallAction;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InvocationAction;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
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
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
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
 * @see org.polarsys.capella.core.data.ctx.CtxPackage
 * @generated
 */
public class CtxSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CtxPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CtxSwitch() {
		if (modelPackage == null) {
			modelPackage = CtxPackage.eINSTANCE;
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
			case CtxPackage.SYSTEM_ANALYSIS: {
				SystemAnalysis systemAnalysis = (SystemAnalysis)theEObject;
				T result = caseSystemAnalysis(systemAnalysis);
				if (result == null) result = caseComponentArchitecture(systemAnalysis);
				if (result == null) result = caseBlockArchitecture(systemAnalysis);
				if (result == null) result = caseAbstractFunctionalArchitecture(systemAnalysis);
				if (result == null) result = caseModellingArchitecture(systemAnalysis);
				if (result == null) result = caseStructure(systemAnalysis);
				if (result == null) result = caseNamespace(systemAnalysis);
				if (result == null) result = caseNamedElement(systemAnalysis);
				if (result == null) result = caseAbstractNamedElement(systemAnalysis);
				if (result == null) result = caseCapellaElement(systemAnalysis);
				if (result == null) result = caseTraceableElement(systemAnalysis);
				if (result == null) result = casePublishableElement(systemAnalysis);
				if (result == null) result = caseModelElement(systemAnalysis);
				if (result == null) result = caseExtensibleElement(systemAnalysis);
				if (result == null) result = caseElement(systemAnalysis);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM: {
				org.polarsys.capella.core.data.ctx.System system = (org.polarsys.capella.core.data.ctx.System)theEObject;
				T result = caseSystem(system);
				if (result == null) result = caseComponent(system);
				if (result == null) result = caseCapabilityRealizationInvolvedElement(system);
				if (result == null) result = caseBlock(system);
				if (result == null) result = casePartitionableElement(system);
				if (result == null) result = caseInterfaceAllocator(system);
				if (result == null) result = caseCommunicationLinkExchanger(system);
				if (result == null) result = caseInvolvedElement(system);
				if (result == null) result = caseAbstractFunctionalBlock(system);
				if (result == null) result = caseClassifier(system);
				if (result == null) result = caseModellingBlock(system);
				if (result == null) result = caseGeneralizableElement(system);
				if (result == null) result = caseType(system);
				if (result == null) result = caseAbstractType(system);
				if (result == null) result = caseNamespace(system);
				if (result == null) result = caseNamedElement(system);
				if (result == null) result = caseAbstractNamedElement(system);
				if (result == null) result = caseCapellaElement(system);
				if (result == null) result = caseExtensibleElement(system);
				if (result == null) result = caseTraceableElement(system);
				if (result == null) result = casePublishableElement(system);
				if (result == null) result = caseModelElement(system);
				if (result == null) result = caseElement(system);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_FUNCTION: {
				SystemFunction systemFunction = (SystemFunction)theEObject;
				T result = caseSystemFunction(systemFunction);
				if (result == null) result = caseAbstractFunction(systemFunction);
				if (result == null) result = caseNamespace(systemFunction);
				if (result == null) result = caseInvolvedElement(systemFunction);
				if (result == null) result = caseAbstractInstance(systemFunction);
				if (result == null) result = caseAbstractFunctionalChainContainer(systemFunction);
				if (result == null) result = caseCallBehaviorAction(systemFunction);
				if (result == null) result = caseAbstractEvent(systemFunction);
				if (result == null) result = caseProperty(systemFunction);
				if (result == null) result = caseCallAction(systemFunction);
				if (result == null) result = caseAbstractType(systemFunction);
				if (result == null) result = caseFeature(systemFunction);
				if (result == null) result = caseTypedElement(systemFunction);
				if (result == null) result = caseMultiplicityElement(systemFunction);
				if (result == null) result = caseFinalizableElement(systemFunction);
				if (result == null) result = caseInvocationAction(systemFunction);
				if (result == null) result = caseNamedElement(systemFunction);
				if (result == null) result = caseCapellaElement(systemFunction);
				if (result == null) result = caseTraceableElement(systemFunction);
				if (result == null) result = casePublishableElement(systemFunction);
				if (result == null) result = caseAbstractTypedElement(systemFunction);
				if (result == null) result = caseAbstractAction(systemFunction);
				if (result == null) result = caseModelElement(systemFunction);
				if (result == null) result = caseExtensibleElement(systemFunction);
				if (result == null) result = caseExecutableNode(systemFunction);
				if (result == null) result = caseElement(systemFunction);
				if (result == null) result = caseActivityNode(systemFunction);
				if (result == null) result = caseAbstractNamedElement(systemFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_FUNCTION_PKG: {
				SystemFunctionPkg systemFunctionPkg = (SystemFunctionPkg)theEObject;
				T result = caseSystemFunctionPkg(systemFunctionPkg);
				if (result == null) result = caseFunctionPkg(systemFunctionPkg);
				if (result == null) result = caseStructure(systemFunctionPkg);
				if (result == null) result = caseNamespace(systemFunctionPkg);
				if (result == null) result = caseNamedElement(systemFunctionPkg);
				if (result == null) result = caseAbstractNamedElement(systemFunctionPkg);
				if (result == null) result = caseCapellaElement(systemFunctionPkg);
				if (result == null) result = caseTraceableElement(systemFunctionPkg);
				if (result == null) result = casePublishableElement(systemFunctionPkg);
				if (result == null) result = caseModelElement(systemFunctionPkg);
				if (result == null) result = caseExtensibleElement(systemFunctionPkg);
				if (result == null) result = caseElement(systemFunctionPkg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_COMMUNICATION_HOOK: {
				SystemCommunicationHook systemCommunicationHook = (SystemCommunicationHook)theEObject;
				T result = caseSystemCommunicationHook(systemCommunicationHook);
				if (result == null) result = caseNamedElement(systemCommunicationHook);
				if (result == null) result = caseAbstractNamedElement(systemCommunicationHook);
				if (result == null) result = caseCapellaElement(systemCommunicationHook);
				if (result == null) result = caseTraceableElement(systemCommunicationHook);
				if (result == null) result = casePublishableElement(systemCommunicationHook);
				if (result == null) result = caseModelElement(systemCommunicationHook);
				if (result == null) result = caseExtensibleElement(systemCommunicationHook);
				if (result == null) result = caseElement(systemCommunicationHook);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_COMMUNICATION: {
				SystemCommunication systemCommunication = (SystemCommunication)theEObject;
				T result = caseSystemCommunication(systemCommunication);
				if (result == null) result = caseRelationship(systemCommunication);
				if (result == null) result = caseAbstractRelationship(systemCommunication);
				if (result == null) result = caseCapellaElement(systemCommunication);
				if (result == null) result = caseTraceableElement(systemCommunication);
				if (result == null) result = casePublishableElement(systemCommunication);
				if (result == null) result = caseModelElement(systemCommunication);
				if (result == null) result = caseExtensibleElement(systemCommunication);
				if (result == null) result = caseElement(systemCommunication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				T result = caseActor(actor);
				if (result == null) result = caseAbstractActor(actor);
				if (result == null) result = caseComponent(actor);
				if (result == null) result = caseCapabilityRealizationInvolvedElement(actor);
				if (result == null) result = caseBlock(actor);
				if (result == null) result = casePartitionableElement(actor);
				if (result == null) result = caseInterfaceAllocator(actor);
				if (result == null) result = caseCommunicationLinkExchanger(actor);
				if (result == null) result = caseInvolvedElement(actor);
				if (result == null) result = caseAbstractFunctionalBlock(actor);
				if (result == null) result = caseClassifier(actor);
				if (result == null) result = caseModellingBlock(actor);
				if (result == null) result = caseGeneralizableElement(actor);
				if (result == null) result = caseType(actor);
				if (result == null) result = caseAbstractType(actor);
				if (result == null) result = caseNamespace(actor);
				if (result == null) result = caseNamedElement(actor);
				if (result == null) result = caseAbstractNamedElement(actor);
				if (result == null) result = caseCapellaElement(actor);
				if (result == null) result = caseExtensibleElement(actor);
				if (result == null) result = caseTraceableElement(actor);
				if (result == null) result = casePublishableElement(actor);
				if (result == null) result = caseModelElement(actor);
				if (result == null) result = caseElement(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.ACTOR_CAPABILITY_INVOLVEMENT: {
				ActorCapabilityInvolvement actorCapabilityInvolvement = (ActorCapabilityInvolvement)theEObject;
				T result = caseActorCapabilityInvolvement(actorCapabilityInvolvement);
				if (result == null) result = caseInvolvement(actorCapabilityInvolvement);
				if (result == null) result = caseRelationship(actorCapabilityInvolvement);
				if (result == null) result = caseAbstractRelationship(actorCapabilityInvolvement);
				if (result == null) result = caseCapellaElement(actorCapabilityInvolvement);
				if (result == null) result = caseTraceableElement(actorCapabilityInvolvement);
				if (result == null) result = casePublishableElement(actorCapabilityInvolvement);
				if (result == null) result = caseModelElement(actorCapabilityInvolvement);
				if (result == null) result = caseExtensibleElement(actorCapabilityInvolvement);
				if (result == null) result = caseElement(actorCapabilityInvolvement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.ACTOR_MISSION_INVOLVEMENT: {
				ActorMissionInvolvement actorMissionInvolvement = (ActorMissionInvolvement)theEObject;
				T result = caseActorMissionInvolvement(actorMissionInvolvement);
				if (result == null) result = caseInvolvement(actorMissionInvolvement);
				if (result == null) result = caseRelationship(actorMissionInvolvement);
				if (result == null) result = caseAbstractRelationship(actorMissionInvolvement);
				if (result == null) result = caseCapellaElement(actorMissionInvolvement);
				if (result == null) result = caseTraceableElement(actorMissionInvolvement);
				if (result == null) result = casePublishableElement(actorMissionInvolvement);
				if (result == null) result = caseModelElement(actorMissionInvolvement);
				if (result == null) result = caseExtensibleElement(actorMissionInvolvement);
				if (result == null) result = caseElement(actorMissionInvolvement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.ACTOR_PKG: {
				ActorPkg actorPkg = (ActorPkg)theEObject;
				T result = caseActorPkg(actorPkg);
				if (result == null) result = caseStructure(actorPkg);
				if (result == null) result = caseNamespace(actorPkg);
				if (result == null) result = caseNamedElement(actorPkg);
				if (result == null) result = caseAbstractNamedElement(actorPkg);
				if (result == null) result = caseCapellaElement(actorPkg);
				if (result == null) result = caseTraceableElement(actorPkg);
				if (result == null) result = casePublishableElement(actorPkg);
				if (result == null) result = caseModelElement(actorPkg);
				if (result == null) result = caseExtensibleElement(actorPkg);
				if (result == null) result = caseElement(actorPkg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.MISSION: {
				Mission mission = (Mission)theEObject;
				T result = caseMission(mission);
				if (result == null) result = caseNamedElement(mission);
				if (result == null) result = caseInvolverElement(mission);
				if (result == null) result = caseAbstractNamedElement(mission);
				if (result == null) result = caseCapellaElement(mission);
				if (result == null) result = caseTraceableElement(mission);
				if (result == null) result = casePublishableElement(mission);
				if (result == null) result = caseModelElement(mission);
				if (result == null) result = caseExtensibleElement(mission);
				if (result == null) result = caseElement(mission);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.MISSION_PKG: {
				MissionPkg missionPkg = (MissionPkg)theEObject;
				T result = caseMissionPkg(missionPkg);
				if (result == null) result = caseStructure(missionPkg);
				if (result == null) result = caseNamespace(missionPkg);
				if (result == null) result = caseNamedElement(missionPkg);
				if (result == null) result = caseAbstractNamedElement(missionPkg);
				if (result == null) result = caseCapellaElement(missionPkg);
				if (result == null) result = caseTraceableElement(missionPkg);
				if (result == null) result = casePublishableElement(missionPkg);
				if (result == null) result = caseModelElement(missionPkg);
				if (result == null) result = caseExtensibleElement(missionPkg);
				if (result == null) result = caseElement(missionPkg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_MISSION_INVOLVEMENT: {
				SystemMissionInvolvement systemMissionInvolvement = (SystemMissionInvolvement)theEObject;
				T result = caseSystemMissionInvolvement(systemMissionInvolvement);
				if (result == null) result = caseInvolvement(systemMissionInvolvement);
				if (result == null) result = caseRelationship(systemMissionInvolvement);
				if (result == null) result = caseAbstractRelationship(systemMissionInvolvement);
				if (result == null) result = caseCapellaElement(systemMissionInvolvement);
				if (result == null) result = caseTraceableElement(systemMissionInvolvement);
				if (result == null) result = casePublishableElement(systemMissionInvolvement);
				if (result == null) result = caseModelElement(systemMissionInvolvement);
				if (result == null) result = caseExtensibleElement(systemMissionInvolvement);
				if (result == null) result = caseElement(systemMissionInvolvement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.CAPABILITY: {
				Capability capability = (Capability)theEObject;
				T result = caseCapability(capability);
				if (result == null) result = caseAbstractCapability(capability);
				if (result == null) result = caseStructure(capability);
				if (result == null) result = caseInvolverElement(capability);
				if (result == null) result = caseAbstractFunctionalChainContainer(capability);
				if (result == null) result = caseNamespace(capability);
				if (result == null) result = caseNamedElement(capability);
				if (result == null) result = caseAbstractNamedElement(capability);
				if (result == null) result = caseCapellaElement(capability);
				if (result == null) result = caseTraceableElement(capability);
				if (result == null) result = casePublishableElement(capability);
				if (result == null) result = caseModelElement(capability);
				if (result == null) result = caseExtensibleElement(capability);
				if (result == null) result = caseElement(capability);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.CAPABILITY_EXPLOITATION: {
				CapabilityExploitation capabilityExploitation = (CapabilityExploitation)theEObject;
				T result = caseCapabilityExploitation(capabilityExploitation);
				if (result == null) result = caseRelationship(capabilityExploitation);
				if (result == null) result = caseAbstractRelationship(capabilityExploitation);
				if (result == null) result = caseCapellaElement(capabilityExploitation);
				if (result == null) result = caseTraceableElement(capabilityExploitation);
				if (result == null) result = casePublishableElement(capabilityExploitation);
				if (result == null) result = caseModelElement(capabilityExploitation);
				if (result == null) result = caseExtensibleElement(capabilityExploitation);
				if (result == null) result = caseElement(capabilityExploitation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.CAPABILITY_PKG: {
				CapabilityPkg capabilityPkg = (CapabilityPkg)theEObject;
				T result = caseCapabilityPkg(capabilityPkg);
				if (result == null) result = caseAbstractCapabilityPkg(capabilityPkg);
				if (result == null) result = caseStructure(capabilityPkg);
				if (result == null) result = caseNamespace(capabilityPkg);
				if (result == null) result = caseNamedElement(capabilityPkg);
				if (result == null) result = caseAbstractNamedElement(capabilityPkg);
				if (result == null) result = caseCapellaElement(capabilityPkg);
				if (result == null) result = caseTraceableElement(capabilityPkg);
				if (result == null) result = casePublishableElement(capabilityPkg);
				if (result == null) result = caseModelElement(capabilityPkg);
				if (result == null) result = caseExtensibleElement(capabilityPkg);
				if (result == null) result = caseElement(capabilityPkg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_CAPABILITY_INVOLVEMENT: {
				SystemCapabilityInvolvement systemCapabilityInvolvement = (SystemCapabilityInvolvement)theEObject;
				T result = caseSystemCapabilityInvolvement(systemCapabilityInvolvement);
				if (result == null) result = caseInvolvement(systemCapabilityInvolvement);
				if (result == null) result = caseRelationship(systemCapabilityInvolvement);
				if (result == null) result = caseAbstractRelationship(systemCapabilityInvolvement);
				if (result == null) result = caseCapellaElement(systemCapabilityInvolvement);
				if (result == null) result = caseTraceableElement(systemCapabilityInvolvement);
				if (result == null) result = casePublishableElement(systemCapabilityInvolvement);
				if (result == null) result = caseModelElement(systemCapabilityInvolvement);
				if (result == null) result = caseExtensibleElement(systemCapabilityInvolvement);
				if (result == null) result = caseElement(systemCapabilityInvolvement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.OPERATIONAL_ACTOR_REALIZATION: {
				OperationalActorRealization operationalActorRealization = (OperationalActorRealization)theEObject;
				T result = caseOperationalActorRealization(operationalActorRealization);
				if (result == null) result = caseComponentAllocation(operationalActorRealization);
				if (result == null) result = caseAllocation(operationalActorRealization);
				if (result == null) result = caseRelationship(operationalActorRealization);
				if (result == null) result = caseAbstractTrace(operationalActorRealization);
				if (result == null) result = caseAbstractRelationship(operationalActorRealization);
				if (result == null) result = caseCapellaElement(operationalActorRealization);
				if (result == null) result = caseTraceableElement(operationalActorRealization);
				if (result == null) result = casePublishableElement(operationalActorRealization);
				if (result == null) result = caseModelElement(operationalActorRealization);
				if (result == null) result = caseExtensibleElement(operationalActorRealization);
				if (result == null) result = caseElement(operationalActorRealization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.OPERATIONAL_ANALYSIS_REALIZATION: {
				OperationalAnalysisRealization operationalAnalysisRealization = (OperationalAnalysisRealization)theEObject;
				T result = caseOperationalAnalysisRealization(operationalAnalysisRealization);
				if (result == null) result = caseArchitectureAllocation(operationalAnalysisRealization);
				if (result == null) result = caseAllocation(operationalAnalysisRealization);
				if (result == null) result = caseRelationship(operationalAnalysisRealization);
				if (result == null) result = caseAbstractTrace(operationalAnalysisRealization);
				if (result == null) result = caseAbstractRelationship(operationalAnalysisRealization);
				if (result == null) result = caseCapellaElement(operationalAnalysisRealization);
				if (result == null) result = caseTraceableElement(operationalAnalysisRealization);
				if (result == null) result = casePublishableElement(operationalAnalysisRealization);
				if (result == null) result = caseModelElement(operationalAnalysisRealization);
				if (result == null) result = caseExtensibleElement(operationalAnalysisRealization);
				if (result == null) result = caseElement(operationalAnalysisRealization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.OPERATIONAL_ENTITY_REALIZATION: {
				OperationalEntityRealization operationalEntityRealization = (OperationalEntityRealization)theEObject;
				T result = caseOperationalEntityRealization(operationalEntityRealization);
				if (result == null) result = caseComponentAllocation(operationalEntityRealization);
				if (result == null) result = caseAllocation(operationalEntityRealization);
				if (result == null) result = caseRelationship(operationalEntityRealization);
				if (result == null) result = caseAbstractTrace(operationalEntityRealization);
				if (result == null) result = caseAbstractRelationship(operationalEntityRealization);
				if (result == null) result = caseCapellaElement(operationalEntityRealization);
				if (result == null) result = caseTraceableElement(operationalEntityRealization);
				if (result == null) result = casePublishableElement(operationalEntityRealization);
				if (result == null) result = caseModelElement(operationalEntityRealization);
				if (result == null) result = caseExtensibleElement(operationalEntityRealization);
				if (result == null) result = caseElement(operationalEntityRealization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CtxPackage.SYSTEM_CONTEXT: {
				SystemContext systemContext = (SystemContext)theEObject;
				T result = caseSystemContext(systemContext);
				if (result == null) result = caseComponentContext(systemContext);
				if (result == null) result = caseComponent(systemContext);
				if (result == null) result = caseBlock(systemContext);
				if (result == null) result = casePartitionableElement(systemContext);
				if (result == null) result = caseInterfaceAllocator(systemContext);
				if (result == null) result = caseCommunicationLinkExchanger(systemContext);
				if (result == null) result = caseAbstractFunctionalBlock(systemContext);
				if (result == null) result = caseClassifier(systemContext);
				if (result == null) result = caseModellingBlock(systemContext);
				if (result == null) result = caseGeneralizableElement(systemContext);
				if (result == null) result = caseType(systemContext);
				if (result == null) result = caseAbstractType(systemContext);
				if (result == null) result = caseNamespace(systemContext);
				if (result == null) result = caseNamedElement(systemContext);
				if (result == null) result = caseAbstractNamedElement(systemContext);
				if (result == null) result = caseCapellaElement(systemContext);
				if (result == null) result = caseExtensibleElement(systemContext);
				if (result == null) result = caseTraceableElement(systemContext);
				if (result == null) result = casePublishableElement(systemContext);
				if (result == null) result = caseModelElement(systemContext);
				if (result == null) result = caseElement(systemContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Analysis</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Analysis</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemAnalysis(SystemAnalysis object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystem(org.polarsys.capella.core.data.ctx.System object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemFunction(SystemFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Function Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Function Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemFunctionPkg(SystemFunctionPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Communication Hook</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Communication Hook</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemCommunicationHook(SystemCommunicationHook object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Communication</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Communication</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemCommunication(SystemCommunication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActor(Actor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor Capability Involvement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor Capability Involvement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActorCapabilityInvolvement(ActorCapabilityInvolvement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor Mission Involvement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor Mission Involvement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActorMissionInvolvement(ActorMissionInvolvement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActorPkg(ActorPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMission(Mission object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mission Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mission Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMissionPkg(MissionPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Mission Involvement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Mission Involvement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemMissionInvolvement(SystemMissionInvolvement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capability</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapability(Capability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capability Exploitation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability Exploitation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapabilityExploitation(CapabilityExploitation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Capability Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapabilityPkg(CapabilityPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Capability Involvement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Capability Involvement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemCapabilityInvolvement(SystemCapabilityInvolvement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operational Actor Realization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operational Actor Realization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationalActorRealization(OperationalActorRealization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operational Analysis Realization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operational Analysis Realization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationalAnalysisRealization(OperationalAnalysisRealization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operational Entity Realization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operational Entity Realization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationalEntityRealization(OperationalEntityRealization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemContext(SystemContext object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Component Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Architecture</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentArchitecture(ComponentArchitecture object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Partitionable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partitionable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartitionableElement(PartitionableElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCapabilityRealizationInvolvedElement(CapabilityRealizationInvolvedElement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractActor(AbstractActor object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Component Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Allocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentAllocation(ComponentAllocation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Architecture Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Architecture Allocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchitectureAllocation(ArchitectureAllocation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentContext(ComponentContext object) {
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

} //CtxSwitch
