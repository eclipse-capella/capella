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
package org.polarsys.capella.core.data.requirement.util;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.requirement.*;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;
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
 * @see org.polarsys.capella.core.data.requirement.RequirementPackage
 * @generated
 */
public class RequirementSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RequirementPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementSwitch() {
		if (modelPackage == null) {
			modelPackage = RequirementPackage.eINSTANCE;
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
			case RequirementPackage.REQUIREMENTS_PKG: {
				RequirementsPkg requirementsPkg = (RequirementsPkg)theEObject;
				T result = caseRequirementsPkg(requirementsPkg);
				if (result == null) result = caseStructure(requirementsPkg);
				if (result == null) result = caseNamespace(requirementsPkg);
				if (result == null) result = caseNamedElement(requirementsPkg);
				if (result == null) result = caseAbstractNamedElement(requirementsPkg);
				if (result == null) result = caseCapellaElement(requirementsPkg);
				if (result == null) result = caseTraceableElement(requirementsPkg);
				if (result == null) result = casePublishableElement(requirementsPkg);
				if (result == null) result = caseModelElement(requirementsPkg);
				if (result == null) result = caseExtensibleElement(requirementsPkg);
				if (result == null) result = caseElement(requirementsPkg);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.REQUIREMENTS_TRACE: {
				RequirementsTrace requirementsTrace = (RequirementsTrace)theEObject;
				T result = caseRequirementsTrace(requirementsTrace);
				if (result == null) result = caseTrace(requirementsTrace);
				if (result == null) result = caseRelationship(requirementsTrace);
				if (result == null) result = caseAbstractTrace(requirementsTrace);
				if (result == null) result = caseAbstractRelationship(requirementsTrace);
				if (result == null) result = caseCapellaElement(requirementsTrace);
				if (result == null) result = caseTraceableElement(requirementsTrace);
				if (result == null) result = casePublishableElement(requirementsTrace);
				if (result == null) result = caseModelElement(requirementsTrace);
				if (result == null) result = caseExtensibleElement(requirementsTrace);
				if (result == null) result = caseElement(requirementsTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.REQUIREMENT: {
				Requirement requirement = (Requirement)theEObject;
				T result = caseRequirement(requirement);
				if (result == null) result = caseNamespace(requirement);
				if (result == null) result = caseNamedElement(requirement);
				if (result == null) result = caseAbstractNamedElement(requirement);
				if (result == null) result = caseCapellaElement(requirement);
				if (result == null) result = caseTraceableElement(requirement);
				if (result == null) result = casePublishableElement(requirement);
				if (result == null) result = caseModelElement(requirement);
				if (result == null) result = caseExtensibleElement(requirement);
				if (result == null) result = caseElement(requirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT: {
				SystemFunctionalInterfaceRequirement systemFunctionalInterfaceRequirement = (SystemFunctionalInterfaceRequirement)theEObject;
				T result = caseSystemFunctionalInterfaceRequirement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseRequirement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseNamespace(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseNamedElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseAbstractNamedElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseCapellaElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseTraceableElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = casePublishableElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseModelElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseExtensibleElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = caseElement(systemFunctionalInterfaceRequirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.SYSTEM_FUNCTIONAL_REQUIREMENT: {
				SystemFunctionalRequirement systemFunctionalRequirement = (SystemFunctionalRequirement)theEObject;
				T result = caseSystemFunctionalRequirement(systemFunctionalRequirement);
				if (result == null) result = caseRequirement(systemFunctionalRequirement);
				if (result == null) result = caseNamespace(systemFunctionalRequirement);
				if (result == null) result = caseNamedElement(systemFunctionalRequirement);
				if (result == null) result = caseAbstractNamedElement(systemFunctionalRequirement);
				if (result == null) result = caseCapellaElement(systemFunctionalRequirement);
				if (result == null) result = caseTraceableElement(systemFunctionalRequirement);
				if (result == null) result = casePublishableElement(systemFunctionalRequirement);
				if (result == null) result = caseModelElement(systemFunctionalRequirement);
				if (result == null) result = caseExtensibleElement(systemFunctionalRequirement);
				if (result == null) result = caseElement(systemFunctionalRequirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT: {
				SystemNonFunctionalInterfaceRequirement systemNonFunctionalInterfaceRequirement = (SystemNonFunctionalInterfaceRequirement)theEObject;
				T result = caseSystemNonFunctionalInterfaceRequirement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseRequirement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseNamespace(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseNamedElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseAbstractNamedElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseCapellaElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseTraceableElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = casePublishableElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseModelElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseExtensibleElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = caseElement(systemNonFunctionalInterfaceRequirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.SYSTEM_NON_FUNCTIONAL_REQUIREMENT: {
				SystemNonFunctionalRequirement systemNonFunctionalRequirement = (SystemNonFunctionalRequirement)theEObject;
				T result = caseSystemNonFunctionalRequirement(systemNonFunctionalRequirement);
				if (result == null) result = caseRequirement(systemNonFunctionalRequirement);
				if (result == null) result = caseNamespace(systemNonFunctionalRequirement);
				if (result == null) result = caseNamedElement(systemNonFunctionalRequirement);
				if (result == null) result = caseAbstractNamedElement(systemNonFunctionalRequirement);
				if (result == null) result = caseCapellaElement(systemNonFunctionalRequirement);
				if (result == null) result = caseTraceableElement(systemNonFunctionalRequirement);
				if (result == null) result = casePublishableElement(systemNonFunctionalRequirement);
				if (result == null) result = caseModelElement(systemNonFunctionalRequirement);
				if (result == null) result = caseExtensibleElement(systemNonFunctionalRequirement);
				if (result == null) result = caseElement(systemNonFunctionalRequirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RequirementPackage.SYSTEM_USER_REQUIREMENT: {
				SystemUserRequirement systemUserRequirement = (SystemUserRequirement)theEObject;
				T result = caseSystemUserRequirement(systemUserRequirement);
				if (result == null) result = caseRequirement(systemUserRequirement);
				if (result == null) result = caseNamespace(systemUserRequirement);
				if (result == null) result = caseNamedElement(systemUserRequirement);
				if (result == null) result = caseAbstractNamedElement(systemUserRequirement);
				if (result == null) result = caseCapellaElement(systemUserRequirement);
				if (result == null) result = caseTraceableElement(systemUserRequirement);
				if (result == null) result = casePublishableElement(systemUserRequirement);
				if (result == null) result = caseModelElement(systemUserRequirement);
				if (result == null) result = caseExtensibleElement(systemUserRequirement);
				if (result == null) result = caseElement(systemUserRequirement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirements Pkg</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirements Pkg</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirementsPkg(RequirementsPkg object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirements Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirements Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirementsTrace(RequirementsTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirement(Requirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Functional Interface Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Functional Interface Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemFunctionalInterfaceRequirement(SystemFunctionalInterfaceRequirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Functional Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Functional Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemFunctionalRequirement(SystemFunctionalRequirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Non Functional Interface Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Non Functional Interface Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemNonFunctionalInterfaceRequirement(SystemNonFunctionalInterfaceRequirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Non Functional Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Non Functional Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemNonFunctionalRequirement(SystemNonFunctionalRequirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System User Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System User Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemUserRequirement(SystemUserRequirement object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrace(Trace object) {
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

} //RequirementSwitch
