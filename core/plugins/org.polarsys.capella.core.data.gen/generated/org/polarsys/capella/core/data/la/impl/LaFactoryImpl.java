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
package org.polarsys.capella.core.data.la.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.core.data.la.*;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.la.SystemRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LaFactoryImpl extends EFactoryImpl implements LaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LaFactory init() {
		try {
			LaFactory theLaFactory = (LaFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.polarsys.org/capella/core/la/1.0.0"); //$NON-NLS-1$ 
			if (theLaFactory != null) {
				return theLaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaFactoryImpl() {
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
			case LaPackage.LOGICAL_ARCHITECTURE_PKG: return createLogicalArchitecturePkg();
			case LaPackage.LOGICAL_ARCHITECTURE: return createLogicalArchitecture();
			case LaPackage.LOGICAL_FUNCTION: return createLogicalFunction();
			case LaPackage.LOGICAL_FUNCTION_PKG: return createLogicalFunctionPkg();
			case LaPackage.LOGICAL_COMPONENT: return createLogicalComponent();
			case LaPackage.LOGICAL_COMPONENT_PKG: return createLogicalComponentPkg();
			case LaPackage.CAPABILITY_REALIZATION: return createCapabilityRealization();
			case LaPackage.CAPABILITY_REALIZATION_PKG: return createCapabilityRealizationPkg();
			case LaPackage.SYSTEM_ANALYSIS_REALIZATION: return createSystemAnalysisRealization();
			case LaPackage.SYSTEM_REALIZATION: return createSystemRealization();
			case LaPackage.CONTEXT_INTERFACE_REALIZATION: return createContextInterfaceRealization();
			case LaPackage.LOGICAL_ACTOR_PKG: return createLogicalActorPkg();
			case LaPackage.LOGICAL_ACTOR: return createLogicalActor();
			case LaPackage.SYSTEM_ACTOR_REALIZATION: return createSystemActorRealization();
			case LaPackage.LOGICAL_CONTEXT: return createLogicalContext();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalArchitecturePkg createLogicalArchitecturePkg() {
		LogicalArchitecturePkgImpl logicalArchitecturePkg = new LogicalArchitecturePkgImpl();
    //begin-capella-code
    //end-capella-code
		return logicalArchitecturePkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalArchitecture createLogicalArchitecture() {
		LogicalArchitectureImpl logicalArchitecture = new LogicalArchitectureImpl();
    //begin-capella-code
    //end-capella-code
		return logicalArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalFunction createLogicalFunction() {
		LogicalFunctionImpl logicalFunction = new LogicalFunctionImpl();
    //begin-capella-code
    //end-capella-code
		return logicalFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalFunctionPkg createLogicalFunctionPkg() {
		LogicalFunctionPkgImpl logicalFunctionPkg = new LogicalFunctionPkgImpl();
    //begin-capella-code
    //end-capella-code
		return logicalFunctionPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalComponent createLogicalComponent() {
		LogicalComponentImpl logicalComponent = new LogicalComponentImpl();
    //begin-capella-code
    //end-capella-code
		return logicalComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalComponentPkg createLogicalComponentPkg() {
		LogicalComponentPkgImpl logicalComponentPkg = new LogicalComponentPkgImpl();
    //begin-capella-code
    //end-capella-code
		return logicalComponentPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityRealization createCapabilityRealization() {
		CapabilityRealizationImpl capabilityRealization = new CapabilityRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return capabilityRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CapabilityRealizationPkg createCapabilityRealizationPkg() {
		CapabilityRealizationPkgImpl capabilityRealizationPkg = new CapabilityRealizationPkgImpl();
    //begin-capella-code
    //end-capella-code
		return capabilityRealizationPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemAnalysisRealization createSystemAnalysisRealization() {
		SystemAnalysisRealizationImpl systemAnalysisRealization = new SystemAnalysisRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return systemAnalysisRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemRealization createSystemRealization() {
		SystemRealizationImpl systemRealization = new SystemRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return systemRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContextInterfaceRealization createContextInterfaceRealization() {
		ContextInterfaceRealizationImpl contextInterfaceRealization = new ContextInterfaceRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return contextInterfaceRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalActorPkg createLogicalActorPkg() {
		LogicalActorPkgImpl logicalActorPkg = new LogicalActorPkgImpl();
    //begin-capella-code
    //end-capella-code
		return logicalActorPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalActor createLogicalActor() {
		LogicalActorImpl logicalActor = new LogicalActorImpl();
    //begin-capella-code
    //end-capella-code
		return logicalActor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemActorRealization createSystemActorRealization() {
		SystemActorRealizationImpl systemActorRealization = new SystemActorRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return systemActorRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalContext createLogicalContext() {
		LogicalContextImpl logicalContext = new LogicalContextImpl();
    //begin-capella-code
    //end-capella-code
		return logicalContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LaPackage getLaPackage() {
		return (LaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LaPackage getPackage() {
		return LaPackage.eINSTANCE;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalArchitecturePkg createLogicalArchitecturePkg(String name_p) {
	  LogicalArchitecturePkg logicalArchitecturePkg = createLogicalArchitecturePkg();
		logicalArchitecturePkg.setName(name_p);	  
		return logicalArchitecturePkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalArchitecture createLogicalArchitecture(String name_p) {
	  LogicalArchitecture logicalArchitecture = createLogicalArchitecture();
		logicalArchitecture.setName(name_p);	  
		return logicalArchitecture;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalFunction createLogicalFunction(String name_p) {
	  LogicalFunction logicalFunction = createLogicalFunction();
		logicalFunction.setName(name_p);	  
		return logicalFunction;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalFunctionPkg createLogicalFunctionPkg(String name_p) {
	  LogicalFunctionPkg logicalFunctionPkg = createLogicalFunctionPkg();
		logicalFunctionPkg.setName(name_p);	  
		return logicalFunctionPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalComponent createLogicalComponent(String name_p) {
	  LogicalComponent logicalComponent = createLogicalComponent();
		logicalComponent.setName(name_p);	  
		return logicalComponent;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalComponentPkg createLogicalComponentPkg(String name_p) {
	  LogicalComponentPkg logicalComponentPkg = createLogicalComponentPkg();
		logicalComponentPkg.setName(name_p);	  
		return logicalComponentPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CapabilityRealization createCapabilityRealization(String name_p) {
	  CapabilityRealization capabilityRealization = createCapabilityRealization();
		capabilityRealization.setName(name_p);	  
		return capabilityRealization;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CapabilityRealizationPkg createCapabilityRealizationPkg(String name_p) {
	  CapabilityRealizationPkg capabilityRealizationPkg = createCapabilityRealizationPkg();
		capabilityRealizationPkg.setName(name_p);	  
		return capabilityRealizationPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalActorPkg createLogicalActorPkg(String name_p) {
	  LogicalActorPkg logicalActorPkg = createLogicalActorPkg();
		logicalActorPkg.setName(name_p);	  
		return logicalActorPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalActor createLogicalActor(String name_p) {
	  LogicalActor logicalActor = createLogicalActor();
		logicalActor.setName(name_p);	  
		return logicalActor;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public LogicalContext createLogicalContext(String name_p) {
	  LogicalContext logicalContext = createLogicalContext();
		logicalContext.setName(name_p);	  
		return logicalContext;
	}

	//begin-capella-code

	//end-capella-code
} //LaFactoryImpl
