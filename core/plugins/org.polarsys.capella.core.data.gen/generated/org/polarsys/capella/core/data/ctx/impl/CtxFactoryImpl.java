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
package org.polarsys.capella.core.data.ctx.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.ctx.*;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionInvolvement;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemCommunication;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;

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
      CtxFactory theCtxFactory = (CtxFactory)EPackage.Registry.INSTANCE.getEFactory(CtxPackage.eNS_URI);
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
      case CtxPackage.SYSTEM_FUNCTION: return createSystemFunction();
      case CtxPackage.SYSTEM_FUNCTION_PKG: return createSystemFunctionPkg();
      case CtxPackage.SYSTEM_COMMUNICATION_HOOK: return createSystemCommunicationHook();
      case CtxPackage.SYSTEM_COMMUNICATION: return createSystemCommunication();
      case CtxPackage.CAPABILITY_INVOLVEMENT: return createCapabilityInvolvement();
      case CtxPackage.MISSION_INVOLVEMENT: return createMissionInvolvement();
      case CtxPackage.MISSION: return createMission();
      case CtxPackage.MISSION_PKG: return createMissionPkg();
      case CtxPackage.CAPABILITY: return createCapability();
      case CtxPackage.CAPABILITY_EXPLOITATION: return createCapabilityExploitation();
      case CtxPackage.CAPABILITY_PKG: return createCapabilityPkg();
      case CtxPackage.OPERATIONAL_ANALYSIS_REALIZATION: return createOperationalAnalysisRealization();
      case CtxPackage.SYSTEM_COMPONENT_PKG: return createSystemComponentPkg();
      case CtxPackage.SYSTEM_COMPONENT: return createSystemComponent();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemAnalysis createSystemAnalysis() {
    SystemAnalysisImpl systemAnalysis = new SystemAnalysisImpl();
    //begin-capella-code
    systemAnalysis.setId(IdGenerator.createId());
    //end-capella-code
    return systemAnalysis;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemFunction createSystemFunction() {
    SystemFunctionImpl systemFunction = new SystemFunctionImpl();
    //begin-capella-code
    systemFunction.setId(IdGenerator.createId());
    //end-capella-code
    return systemFunction;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemFunctionPkg createSystemFunctionPkg() {
    SystemFunctionPkgImpl systemFunctionPkg = new SystemFunctionPkgImpl();
    //begin-capella-code
    systemFunctionPkg.setId(IdGenerator.createId());
    //end-capella-code
    return systemFunctionPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemCommunicationHook createSystemCommunicationHook() {
    SystemCommunicationHookImpl systemCommunicationHook = new SystemCommunicationHookImpl();
    //begin-capella-code
    systemCommunicationHook.setId(IdGenerator.createId());
    //end-capella-code
    return systemCommunicationHook;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemCommunication createSystemCommunication() {
    SystemCommunicationImpl systemCommunication = new SystemCommunicationImpl();
    //begin-capella-code
    systemCommunication.setId(IdGenerator.createId());
    //end-capella-code
    return systemCommunication;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapabilityInvolvement createCapabilityInvolvement() {
    CapabilityInvolvementImpl capabilityInvolvement = new CapabilityInvolvementImpl();
    //begin-capella-code
    capabilityInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return capabilityInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public MissionInvolvement createMissionInvolvement() {
    MissionInvolvementImpl missionInvolvement = new MissionInvolvementImpl();
    //begin-capella-code
    missionInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return missionInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Mission createMission() {
    MissionImpl mission = new MissionImpl();
    //begin-capella-code
    mission.setId(IdGenerator.createId());
    //end-capella-code
    return mission;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public MissionPkg createMissionPkg() {
    MissionPkgImpl missionPkg = new MissionPkgImpl();
    //begin-capella-code
    missionPkg.setId(IdGenerator.createId());
    //end-capella-code
    return missionPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Capability createCapability() {
    CapabilityImpl capability = new CapabilityImpl();
    //begin-capella-code
    capability.setId(IdGenerator.createId());
    //end-capella-code
    return capability;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapabilityExploitation createCapabilityExploitation() {
    CapabilityExploitationImpl capabilityExploitation = new CapabilityExploitationImpl();
    //begin-capella-code
    capabilityExploitation.setId(IdGenerator.createId());
    //end-capella-code
    return capabilityExploitation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapabilityPkg createCapabilityPkg() {
    CapabilityPkgImpl capabilityPkg = new CapabilityPkgImpl();
    //begin-capella-code
    capabilityPkg.setId(IdGenerator.createId());
    //end-capella-code
    return capabilityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalAnalysisRealization createOperationalAnalysisRealization() {
    OperationalAnalysisRealizationImpl operationalAnalysisRealization = new OperationalAnalysisRealizationImpl();
    //begin-capella-code
    operationalAnalysisRealization.setId(IdGenerator.createId());
    //end-capella-code
    return operationalAnalysisRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemComponentPkg createSystemComponentPkg() {
    SystemComponentPkgImpl systemComponentPkg = new SystemComponentPkgImpl();
    //begin-capella-code
    systemComponentPkg.setId(IdGenerator.createId());
    //end-capella-code
    return systemComponentPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public SystemComponent createSystemComponent() {
    SystemComponentImpl systemComponent = new SystemComponentImpl();
    //begin-capella-code
    systemComponent.setId(IdGenerator.createId());
    //end-capella-code
    return systemComponent;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
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
	public SystemComponentPkg createSystemComponentPkg(String name_p) {
    SystemComponentPkg systemComponentPkg = createSystemComponentPkg();
    systemComponentPkg.setName(name_p);	  
    return systemComponentPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemComponent createSystemComponent(String name_p) {
    SystemComponent systemComponent = createSystemComponent();
    systemComponent.setName(name_p);	  
    return systemComponent;
  }

	//begin-capella-code

	//end-capella-code
} //CtxFactoryImpl
