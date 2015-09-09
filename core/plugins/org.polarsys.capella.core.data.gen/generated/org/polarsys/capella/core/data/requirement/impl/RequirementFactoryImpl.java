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
package org.polarsys.capella.core.data.requirement.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.core.data.requirement.*;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementFactoryImpl extends EFactoryImpl implements RequirementFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RequirementFactory init() {
		try {
			RequirementFactory theRequirementFactory = (RequirementFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.polarsys.org/capella/core/requirement/1.0.0"); //$NON-NLS-1$ 
			if (theRequirementFactory != null) {
				return theRequirementFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RequirementFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementFactoryImpl() {
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
			case RequirementPackage.REQUIREMENTS_PKG: return createRequirementsPkg();
			case RequirementPackage.REQUIREMENTS_TRACE: return createRequirementsTrace();
			case RequirementPackage.SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT: return createSystemFunctionalInterfaceRequirement();
			case RequirementPackage.SYSTEM_FUNCTIONAL_REQUIREMENT: return createSystemFunctionalRequirement();
			case RequirementPackage.SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT: return createSystemNonFunctionalInterfaceRequirement();
			case RequirementPackage.SYSTEM_NON_FUNCTIONAL_REQUIREMENT: return createSystemNonFunctionalRequirement();
			case RequirementPackage.SYSTEM_USER_REQUIREMENT: return createSystemUserRequirement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementsPkg createRequirementsPkg() {
		RequirementsPkgImpl requirementsPkg = new RequirementsPkgImpl();
    //begin-capella-code
    //end-capella-code
		return requirementsPkg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementsTrace createRequirementsTrace() {
		RequirementsTraceImpl requirementsTrace = new RequirementsTraceImpl();
    //begin-capella-code
    //end-capella-code
		return requirementsTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemFunctionalInterfaceRequirement createSystemFunctionalInterfaceRequirement() {
		SystemFunctionalInterfaceRequirementImpl systemFunctionalInterfaceRequirement = new SystemFunctionalInterfaceRequirementImpl();
    //begin-capella-code
    //end-capella-code
		return systemFunctionalInterfaceRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemFunctionalRequirement createSystemFunctionalRequirement() {
		SystemFunctionalRequirementImpl systemFunctionalRequirement = new SystemFunctionalRequirementImpl();
    //begin-capella-code
    //end-capella-code
		return systemFunctionalRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemNonFunctionalInterfaceRequirement createSystemNonFunctionalInterfaceRequirement() {
		SystemNonFunctionalInterfaceRequirementImpl systemNonFunctionalInterfaceRequirement = new SystemNonFunctionalInterfaceRequirementImpl();
    //begin-capella-code
    //end-capella-code
		return systemNonFunctionalInterfaceRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemNonFunctionalRequirement createSystemNonFunctionalRequirement() {
		SystemNonFunctionalRequirementImpl systemNonFunctionalRequirement = new SystemNonFunctionalRequirementImpl();
    //begin-capella-code
    //end-capella-code
		return systemNonFunctionalRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemUserRequirement createSystemUserRequirement() {
		SystemUserRequirementImpl systemUserRequirement = new SystemUserRequirementImpl();
    //begin-capella-code
    //end-capella-code
		return systemUserRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementPackage getRequirementPackage() {
		return (RequirementPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RequirementPackage getPackage() {
		return RequirementPackage.eINSTANCE;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public RequirementsPkg createRequirementsPkg(String name_p) {
	  RequirementsPkg requirementsPkg = createRequirementsPkg();
		requirementsPkg.setName(name_p);	  
		return requirementsPkg;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemFunctionalInterfaceRequirement createSystemFunctionalInterfaceRequirement(String name_p) {
	  SystemFunctionalInterfaceRequirement systemFunctionalInterfaceRequirement = createSystemFunctionalInterfaceRequirement();
		systemFunctionalInterfaceRequirement.setName(name_p);	  
		return systemFunctionalInterfaceRequirement;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemFunctionalRequirement createSystemFunctionalRequirement(String name_p) {
	  SystemFunctionalRequirement systemFunctionalRequirement = createSystemFunctionalRequirement();
		systemFunctionalRequirement.setName(name_p);	  
		return systemFunctionalRequirement;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemNonFunctionalInterfaceRequirement createSystemNonFunctionalInterfaceRequirement(String name_p) {
	  SystemNonFunctionalInterfaceRequirement systemNonFunctionalInterfaceRequirement = createSystemNonFunctionalInterfaceRequirement();
		systemNonFunctionalInterfaceRequirement.setName(name_p);	  
		return systemNonFunctionalInterfaceRequirement;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemNonFunctionalRequirement createSystemNonFunctionalRequirement(String name_p) {
	  SystemNonFunctionalRequirement systemNonFunctionalRequirement = createSystemNonFunctionalRequirement();
		systemNonFunctionalRequirement.setName(name_p);	  
		return systemNonFunctionalRequirement;
	}

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public SystemUserRequirement createSystemUserRequirement(String name_p) {
	  SystemUserRequirement systemUserRequirement = createSystemUserRequirement();
		systemUserRequirement.setName(name_p);	  
		return systemUserRequirement;
	}

	//begin-capella-code

	//end-capella-code
} //RequirementFactoryImpl
