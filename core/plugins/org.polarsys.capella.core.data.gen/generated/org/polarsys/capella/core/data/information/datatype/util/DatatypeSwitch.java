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
package org.polarsys.capella.core.data.information.datatype.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.datatype.*;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
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
 * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage
 * @generated
 */
public class DatatypeSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static DatatypePackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DatatypeSwitch() {
    if (modelPackage == null) {
      modelPackage = DatatypePackage.eINSTANCE;
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
      case DatatypePackage.DATA_TYPE: {
        DataType dataType = (DataType)theEObject;
        T result = caseDataType(dataType);
        if (result == null) result = caseGeneralizableElement(dataType);
        if (result == null) result = caseDataValueContainer(dataType);
        if (result == null) result = caseFinalizableElement(dataType);
        if (result == null) result = caseType(dataType);
        if (result == null) result = caseStructure(dataType);
        if (result == null) result = caseAbstractType(dataType);
        if (result == null) result = caseNamespace(dataType);
        if (result == null) result = caseNamedElement(dataType);
        if (result == null) result = caseAbstractNamedElement(dataType);
        if (result == null) result = caseCapellaElement(dataType);
        if (result == null) result = caseExtensibleElement(dataType);
        if (result == null) result = caseTraceableElement(dataType);
        if (result == null) result = casePublishableElement(dataType);
        if (result == null) result = caseModelElement(dataType);
        if (result == null) result = caseElement(dataType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatatypePackage.BOOLEAN_TYPE: {
        BooleanType booleanType = (BooleanType)theEObject;
        T result = caseBooleanType(booleanType);
        if (result == null) result = caseDataType(booleanType);
        if (result == null) result = caseGeneralizableElement(booleanType);
        if (result == null) result = caseDataValueContainer(booleanType);
        if (result == null) result = caseFinalizableElement(booleanType);
        if (result == null) result = caseType(booleanType);
        if (result == null) result = caseStructure(booleanType);
        if (result == null) result = caseAbstractType(booleanType);
        if (result == null) result = caseNamespace(booleanType);
        if (result == null) result = caseNamedElement(booleanType);
        if (result == null) result = caseAbstractNamedElement(booleanType);
        if (result == null) result = caseCapellaElement(booleanType);
        if (result == null) result = caseExtensibleElement(booleanType);
        if (result == null) result = caseTraceableElement(booleanType);
        if (result == null) result = casePublishableElement(booleanType);
        if (result == null) result = caseModelElement(booleanType);
        if (result == null) result = caseElement(booleanType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatatypePackage.ENUMERATION: {
        Enumeration enumeration = (Enumeration)theEObject;
        T result = caseEnumeration(enumeration);
        if (result == null) result = caseDataType(enumeration);
        if (result == null) result = caseGeneralizableElement(enumeration);
        if (result == null) result = caseDataValueContainer(enumeration);
        if (result == null) result = caseFinalizableElement(enumeration);
        if (result == null) result = caseType(enumeration);
        if (result == null) result = caseStructure(enumeration);
        if (result == null) result = caseAbstractType(enumeration);
        if (result == null) result = caseNamespace(enumeration);
        if (result == null) result = caseNamedElement(enumeration);
        if (result == null) result = caseAbstractNamedElement(enumeration);
        if (result == null) result = caseCapellaElement(enumeration);
        if (result == null) result = caseExtensibleElement(enumeration);
        if (result == null) result = caseTraceableElement(enumeration);
        if (result == null) result = casePublishableElement(enumeration);
        if (result == null) result = caseModelElement(enumeration);
        if (result == null) result = caseElement(enumeration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatatypePackage.STRING_TYPE: {
        StringType stringType = (StringType)theEObject;
        T result = caseStringType(stringType);
        if (result == null) result = caseDataType(stringType);
        if (result == null) result = caseGeneralizableElement(stringType);
        if (result == null) result = caseDataValueContainer(stringType);
        if (result == null) result = caseFinalizableElement(stringType);
        if (result == null) result = caseType(stringType);
        if (result == null) result = caseStructure(stringType);
        if (result == null) result = caseAbstractType(stringType);
        if (result == null) result = caseNamespace(stringType);
        if (result == null) result = caseNamedElement(stringType);
        if (result == null) result = caseAbstractNamedElement(stringType);
        if (result == null) result = caseCapellaElement(stringType);
        if (result == null) result = caseExtensibleElement(stringType);
        if (result == null) result = caseTraceableElement(stringType);
        if (result == null) result = casePublishableElement(stringType);
        if (result == null) result = caseModelElement(stringType);
        if (result == null) result = caseElement(stringType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatatypePackage.NUMERIC_TYPE: {
        NumericType numericType = (NumericType)theEObject;
        T result = caseNumericType(numericType);
        if (result == null) result = caseDataType(numericType);
        if (result == null) result = caseGeneralizableElement(numericType);
        if (result == null) result = caseDataValueContainer(numericType);
        if (result == null) result = caseFinalizableElement(numericType);
        if (result == null) result = caseType(numericType);
        if (result == null) result = caseStructure(numericType);
        if (result == null) result = caseAbstractType(numericType);
        if (result == null) result = caseNamespace(numericType);
        if (result == null) result = caseNamedElement(numericType);
        if (result == null) result = caseAbstractNamedElement(numericType);
        if (result == null) result = caseCapellaElement(numericType);
        if (result == null) result = caseExtensibleElement(numericType);
        if (result == null) result = caseTraceableElement(numericType);
        if (result == null) result = casePublishableElement(numericType);
        if (result == null) result = caseModelElement(numericType);
        if (result == null) result = caseElement(numericType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DatatypePackage.PHYSICAL_QUANTITY: {
        PhysicalQuantity physicalQuantity = (PhysicalQuantity)theEObject;
        T result = casePhysicalQuantity(physicalQuantity);
        if (result == null) result = caseNumericType(physicalQuantity);
        if (result == null) result = caseDataType(physicalQuantity);
        if (result == null) result = caseGeneralizableElement(physicalQuantity);
        if (result == null) result = caseDataValueContainer(physicalQuantity);
        if (result == null) result = caseFinalizableElement(physicalQuantity);
        if (result == null) result = caseType(physicalQuantity);
        if (result == null) result = caseStructure(physicalQuantity);
        if (result == null) result = caseAbstractType(physicalQuantity);
        if (result == null) result = caseNamespace(physicalQuantity);
        if (result == null) result = caseNamedElement(physicalQuantity);
        if (result == null) result = caseAbstractNamedElement(physicalQuantity);
        if (result == null) result = caseCapellaElement(physicalQuantity);
        if (result == null) result = caseExtensibleElement(physicalQuantity);
        if (result == null) result = caseTraceableElement(physicalQuantity);
        if (result == null) result = casePublishableElement(physicalQuantity);
        if (result == null) result = caseModelElement(physicalQuantity);
        if (result == null) result = caseElement(physicalQuantity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDataType(DataType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBooleanType(BooleanType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseEnumeration(Enumeration object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>String Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStringType(StringType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Numeric Type</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Numeric Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNumericType(NumericType object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Quantity</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Quantity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalQuantity(PhysicalQuantity object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Data Value Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Value Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDataValueContainer(DataValueContainer object) {
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

} //DatatypeSwitch
