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
package org.polarsys.capella.core.data.ctx.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CtxFactoryImpl extends EFactoryImpl implements CtxFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CtxFactory init() {
		try {
			CtxFactory theCtxFactory = (CtxFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.polarsys.org/capella/core/ctx/1.0.0"); //$NON-NLS-1$ 
			if (theCtxFactory != null) {
				return theCtxFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CtxFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CtxFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CtxPackage.SYSTEM_ANALYSIS: return createSystemAnalysis();
			case CtxPackage.SYSTEM: return createSystem();
			case CtxPackage.SYSTEM_FUNCTION: return createSystemFunction();
			case CtxPackage.SYSTEM_FUNCTION_PKG: return createSystemFunctionPkg();
			case CtxPackage.SYSTEM_COMMUNICATION_HOOK: return createSystemCommunicationHook();
			case CtxPackage.SYSTEM_COMMUNICATION: return createSystemCommunication();
			case CtxPackage.ACTOR: return createActor();
			case CtxPackage.ACTOR_CAPABILITY_INVOLVEMENT: return createActorCapabilityInvolvement();
			case CtxPackage.ACTOR_MISSION_INVOLVEMENT: return createActorMissionInvolvement();
			case CtxPackage.ACTOR_PKG: return createActorPkg();
			case CtxPackage.MISSION: return createMission();
			case CtxPackage.MISSION_PKG: return createMissionPkg();
			case CtxPackage.SYSTEM_MISSION_INVOLVEMENT: return createSystemMissionInvolvement();
			case CtxPackage.CAPABILITY: return createCapability();
			case CtxPackage.CAPABILITY_EXPLOITATION: return createCapabilityExploitation();
			case CtxPackage.CAPABILITY_PKG: return createCapabilityPkg();
			case CtxPackage.SYSTEM_CAPABILITY_INVOLVEMENT: return createSystemCapabilityInvolvement();
			case CtxPackage.OPERATIONAL_ACTOR_REALIZATION: return createOperationalActorRealization();
			case CtxPackage.OPERATIONAL_ANALYSIS_REALIZATION: return createOperationalAnalysisRealization();
			case CtxPackage.OPERATIONAL_ENTITY_REALIZATION: return createOperationalEntityRealization();
			case CtxPackage.SYSTEM_CONTEXT: return createSystemContext();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemAnalysis createSystemAnalysis() {
		SystemAnalysisImpl systemAnalysis = new SystemAnalysisImpl();
    //begin-capella-code
    //end-capella-code
		return systemAnalysis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.polarsys.capella.core.data.ctx.System createSystem() {
		SystemImpl system = new SystemImpl();
    //begin-capella-code
    //end-capella-code
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemFunction createSystemFunction() {
		SystemFunctionImpl systemFunction = new SystemFunctionImpl();
    //begin-capella-code
    //end-capella-code
		return systemFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemFunctionPkg createSystemFunctionPkg() {
		SystemFunctionPkgImpl systemFunctionPkg = new SystemFunctionPkgImpl();
    //begin-capella-code
    //end-capella-code
		return systemFunctionPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemCommunicationHook createSystemCommunicationHook() {
		SystemCommunicationHookImpl systemCommunicationHook = new SystemCommunicationHookImpl();
    //begin-capella-code
    //end-capella-code
		return systemCommunicationHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemCommunication createSystemCommunication() {
		SystemCommunicationImpl systemCommunication = new SystemCommunicationImpl();
    //begin-capella-code
    //end-capella-code
		return systemCommunication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor createActor() {
		ActorImpl actor = new ActorImpl();
    //begin-capella-code
    //end-capella-code
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActorCapabilityInvolvement createActorCapabilityInvolvement() {
		ActorCapabilityInvolvementImpl actorCapabilityInvolvement = new ActorCapabilityInvolvementImpl();
    //begin-capella-code
    //end-capella-code
		return actorCapabilityInvolvement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActorMissionInvolvement createActorMissionInvolvement() {
		ActorMissionInvolvementImpl actorMissionInvolvement = new ActorMissionInvolvementImpl();
    //begin-capella-code
    //end-capella-code
		return actorMissionInvolvement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActorPkg createActorPkg() {
		ActorPkgImpl actorPkg = new ActorPkgImpl();
    //begin-capella-code
    //end-capella-code
		return actorPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mission createMission() {
		MissionImpl mission = new MissionImpl();
    //begin-capella-code
    //end-capella-code
		return mission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MissionPkg createMissionPkg() {
		MissionPkgImpl missionPkg = new MissionPkgImpl();
    //begin-capella-code
    //end-capella-code
		return missionPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemMissionInvolvement createSystemMissionInvolvement() {
		SystemMissionInvolvementImpl systemMissionInvolvement = new SystemMissionInvolvementImpl();
    //begin-capella-code
    //end-capella-code
		return systemMissionInvolvement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Capability createCapability() {
		CapabilityImpl capability = new CapabilityImpl();
    //begin-capella-code
    //end-capella-code
		return capability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityExploitation createCapabilityExploitation() {
		CapabilityExploitationImpl capabilityExploitation = new CapabilityExploitationImpl();
    //begin-capella-code
    //end-capella-code
		return capabilityExploitation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityPkg createCapabilityPkg() {
		CapabilityPkgImpl capabilityPkg = new CapabilityPkgImpl();
    //begin-capella-code
    //end-capella-code
		return capabilityPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemCapabilityInvolvement createSystemCapabilityInvolvement() {
		SystemCapabilityInvolvementImpl systemCapabilityInvolvement = new SystemCapabilityInvolvementImpl();
    //begin-capella-code
    //end-capella-code
		return systemCapabilityInvolvement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalActorRealization createOperationalActorRealization() {
		OperationalActorRealizationImpl operationalActorRealization = new OperationalActorRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return operationalActorRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalAnalysisRealization createOperationalAnalysisRealization() {
		OperationalAnalysisRealizationImpl operationalAnalysisRealization = new OperationalAnalysisRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return operationalAnalysisRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalEntityRealization createOperationalEntityRealization() {
		OperationalEntityRealizationImpl operationalEntityRealization = new OperationalEntityRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return operationalEntityRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemContext createSystemContext() {
		SystemContextImpl systemContext = new SystemContextImpl();
    //begin-capella-code
    //end-capella-code
		return systemContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CtxPackage getCtxPackage() {
		return (CtxPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CtxPackage getPackage() {
		return CtxPackage.eINSTANCE;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemAnalysis createSystemAnalysis(String name_p) {
	  SystemAnalysis systemAnalysis = createSystemAnalysis();
		systemAnalysis.setName(name_p);	  
		return systemAnalysis;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public org.polarsys.capella.core.data.ctx.System createSystem(String name_p) {
	  org.polarsys.capella.core.data.ctx.System system = createSystem();
		system.setName(name_p);	  
		return system;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemFunction createSystemFunction(String name_p) {
	  SystemFunction systemFunction = createSystemFunction();
		systemFunction.setName(name_p);	  
		return systemFunction;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemFunctionPkg createSystemFunctionPkg(String name_p) {
	  SystemFunctionPkg systemFunctionPkg = createSystemFunctionPkg();
		systemFunctionPkg.setName(name_p);	  
		return systemFunctionPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemCommunicationHook createSystemCommunicationHook(String name_p) {
	  SystemCommunicationHook systemCommunicationHook = createSystemCommunicationHook();
		systemCommunicationHook.setName(name_p);	  
		return systemCommunicationHook;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Actor createActor(String name_p) {
	  Actor actor = createActor();
		actor.setName(name_p);	  
		return actor;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ActorPkg createActorPkg(String name_p) {
	  ActorPkg actorPkg = createActorPkg();
		actorPkg.setName(name_p);	  
		return actorPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Mission createMission(String name_p) {
	  Mission mission = createMission();
		mission.setName(name_p);	  
		return mission;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public MissionPkg createMissionPkg(String name_p) {
	  MissionPkg missionPkg = createMissionPkg();
		missionPkg.setName(name_p);	  
		return missionPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Capability createCapability(String name_p) {
	  Capability capability = createCapability();
		capability.setName(name_p);	  
		return capability;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CapabilityPkg createCapabilityPkg(String name_p) {
	  CapabilityPkg capabilityPkg = createCapabilityPkg();
		capabilityPkg.setName(name_p);	  
		return capabilityPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemContext createSystemContext(String name_p) {
	  SystemContext systemContext = createSystemContext();
		systemContext.setName(name_p);	  
		return systemContext;
	}

	//begin-capella-code

	//end-capella-code
} //CtxFactoryImpl
