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
package org.polarsys.capella.core.data.pa.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.core.data.pa.*;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentKind;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PaFactoryImpl extends EFactoryImpl implements PaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PaFactory init() {
		try {
			PaFactory thePaFactory = (PaFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.polarsys.org/capella/core/pa/1.0.0"); //$NON-NLS-1$ 
			if (thePaFactory != null) {
				return thePaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaFactoryImpl() {
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
			case PaPackage.PHYSICAL_ARCHITECTURE_PKG: return createPhysicalArchitecturePkg();
			case PaPackage.PHYSICAL_ARCHITECTURE: return createPhysicalArchitecture();
			case PaPackage.PHYSICAL_FUNCTION: return createPhysicalFunction();
			case PaPackage.PHYSICAL_FUNCTION_PKG: return createPhysicalFunctionPkg();
			case PaPackage.PHYSICAL_COMPONENT: return createPhysicalComponent();
			case PaPackage.PHYSICAL_ACTOR_PKG: return createPhysicalActorPkg();
			case PaPackage.PHYSICAL_COMPONENT_PKG: return createPhysicalComponentPkg();
			case PaPackage.PHYSICAL_ACTOR: return createPhysicalActor();
			case PaPackage.LOGICAL_ACTOR_REALIZATION: return createLogicalActorRealization();
			case PaPackage.PHYSICAL_NODE: return createPhysicalNode();
			case PaPackage.LOGICAL_ARCHITECTURE_REALIZATION: return createLogicalArchitectureRealization();
			case PaPackage.LOGICAL_COMPONENT_REALIZATION: return createLogicalComponentRealization();
			case PaPackage.LOGICAL_INTERFACE_REALIZATION: return createLogicalInterfaceRealization();
			case PaPackage.PHYSICAL_CONTEXT: return createPhysicalContext();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PaPackage.PHYSICAL_COMPONENT_KIND:
				return createPhysicalComponentKindFromString(eDataType, initialValue);
			case PaPackage.PHYSICAL_COMPONENT_NATURE:
				return createPhysicalComponentNatureFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PaPackage.PHYSICAL_COMPONENT_KIND:
				return convertPhysicalComponentKindToString(eDataType, instanceValue);
			case PaPackage.PHYSICAL_COMPONENT_NATURE:
				return convertPhysicalComponentNatureToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalArchitecturePkg createPhysicalArchitecturePkg() {
		PhysicalArchitecturePkgImpl physicalArchitecturePkg = new PhysicalArchitecturePkgImpl();
    //begin-capella-code
    //end-capella-code
		return physicalArchitecturePkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalArchitecture createPhysicalArchitecture() {
		PhysicalArchitectureImpl physicalArchitecture = new PhysicalArchitectureImpl();
    //begin-capella-code
    //end-capella-code
		return physicalArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalFunction createPhysicalFunction() {
		PhysicalFunctionImpl physicalFunction = new PhysicalFunctionImpl();
    //begin-capella-code
    //end-capella-code
		return physicalFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalFunctionPkg createPhysicalFunctionPkg() {
		PhysicalFunctionPkgImpl physicalFunctionPkg = new PhysicalFunctionPkgImpl();
    //begin-capella-code
    //end-capella-code
		return physicalFunctionPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalComponent createPhysicalComponent() {
		PhysicalComponentImpl physicalComponent = new PhysicalComponentImpl();
    //begin-capella-code
    //end-capella-code
		return physicalComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalActorPkg createPhysicalActorPkg() {
		PhysicalActorPkgImpl physicalActorPkg = new PhysicalActorPkgImpl();
    //begin-capella-code
    //end-capella-code
		return physicalActorPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalComponentPkg createPhysicalComponentPkg() {
		PhysicalComponentPkgImpl physicalComponentPkg = new PhysicalComponentPkgImpl();
    //begin-capella-code
    //end-capella-code
		return physicalComponentPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalActor createPhysicalActor() {
		PhysicalActorImpl physicalActor = new PhysicalActorImpl();
    //begin-capella-code
    //end-capella-code
		return physicalActor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalActorRealization createLogicalActorRealization() {
		LogicalActorRealizationImpl logicalActorRealization = new LogicalActorRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return logicalActorRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalNode createPhysicalNode() {
		PhysicalNodeImpl physicalNode = new PhysicalNodeImpl();
    //begin-capella-code
    //end-capella-code
		return physicalNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalArchitectureRealization createLogicalArchitectureRealization() {
		LogicalArchitectureRealizationImpl logicalArchitectureRealization = new LogicalArchitectureRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return logicalArchitectureRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalComponentRealization createLogicalComponentRealization() {
		LogicalComponentRealizationImpl logicalComponentRealization = new LogicalComponentRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return logicalComponentRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalInterfaceRealization createLogicalInterfaceRealization() {
		LogicalInterfaceRealizationImpl logicalInterfaceRealization = new LogicalInterfaceRealizationImpl();
    //begin-capella-code
    //end-capella-code
		return logicalInterfaceRealization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalContext createPhysicalContext() {
		PhysicalContextImpl physicalContext = new PhysicalContextImpl();
    //begin-capella-code
    //end-capella-code
		return physicalContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalComponentKind createPhysicalComponentKindFromString(EDataType eDataType, String initialValue) {
		PhysicalComponentKind result = PhysicalComponentKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhysicalComponentKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PhysicalComponentNature createPhysicalComponentNatureFromString(EDataType eDataType, String initialValue) {
		PhysicalComponentNature result = PhysicalComponentNature.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPhysicalComponentNatureToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PaPackage getPaPackage() {
		return (PaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PaPackage getPackage() {
		return PaPackage.eINSTANCE;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalArchitecturePkg createPhysicalArchitecturePkg(String name_p) {
	  PhysicalArchitecturePkg physicalArchitecturePkg = createPhysicalArchitecturePkg();
		physicalArchitecturePkg.setName(name_p);	  
		return physicalArchitecturePkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalArchitecture createPhysicalArchitecture(String name_p) {
	  PhysicalArchitecture physicalArchitecture = createPhysicalArchitecture();
		physicalArchitecture.setName(name_p);	  
		return physicalArchitecture;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalFunction createPhysicalFunction(String name_p) {
	  PhysicalFunction physicalFunction = createPhysicalFunction();
		physicalFunction.setName(name_p);	  
		return physicalFunction;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalFunctionPkg createPhysicalFunctionPkg(String name_p) {
	  PhysicalFunctionPkg physicalFunctionPkg = createPhysicalFunctionPkg();
		physicalFunctionPkg.setName(name_p);	  
		return physicalFunctionPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalComponent createPhysicalComponent(String name_p) {
	  PhysicalComponent physicalComponent = createPhysicalComponent();
		physicalComponent.setName(name_p);	  
		return physicalComponent;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalActorPkg createPhysicalActorPkg(String name_p) {
	  PhysicalActorPkg physicalActorPkg = createPhysicalActorPkg();
		physicalActorPkg.setName(name_p);	  
		return physicalActorPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalComponentPkg createPhysicalComponentPkg(String name_p) {
	  PhysicalComponentPkg physicalComponentPkg = createPhysicalComponentPkg();
		physicalComponentPkg.setName(name_p);	  
		return physicalComponentPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalActor createPhysicalActor(String name_p) {
	  PhysicalActor physicalActor = createPhysicalActor();
		physicalActor.setName(name_p);	  
		return physicalActor;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalNode createPhysicalNode(String name_p) {
	  PhysicalNode physicalNode = createPhysicalNode();
		physicalNode.setName(name_p);	  
		return physicalNode;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalContext createPhysicalContext(String name_p) {
	  PhysicalContext physicalContext = createPhysicalContext();
		physicalContext.setName(name_p);	  
		return physicalContext;
	}

	//begin-capella-code

	//end-capella-code
} //PaFactoryImpl
