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
package org.polarsys.capella.common.data.behavior.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.behavior.*;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.behavior.AbstractMessageEvent;
import org.polarsys.capella.common.data.behavior.AbstractSignal;
import org.polarsys.capella.common.data.behavior.AbstractSignalEvent;
import org.polarsys.capella.common.data.behavior.AbstractTimeEvent;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.behavior.TimeExpression;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
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
 * @see org.polarsys.capella.common.data.behavior.BehaviorPackage
 * @generated
 */
public class BehaviorSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static BehaviorPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public BehaviorSwitch() {
    if (modelPackage == null) {
      modelPackage = BehaviorPackage.eINSTANCE;
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
      case BehaviorPackage.ABSTRACT_BEHAVIOR: {
        AbstractBehavior abstractBehavior = (AbstractBehavior)theEObject;
        T result = caseAbstractBehavior(abstractBehavior);
        if (result == null) result = caseAbstractNamedElement(abstractBehavior);
        if (result == null) result = caseModelElement(abstractBehavior);
        if (result == null) result = caseExtensibleElement(abstractBehavior);
        if (result == null) result = caseElement(abstractBehavior);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.ABSTRACT_SIGNAL: {
        AbstractSignal abstractSignal = (AbstractSignal)theEObject;
        T result = caseAbstractSignal(abstractSignal);
        if (result == null) result = caseAbstractType(abstractSignal);
        if (result == null) result = caseAbstractNamedElement(abstractSignal);
        if (result == null) result = caseModelElement(abstractSignal);
        if (result == null) result = caseExtensibleElement(abstractSignal);
        if (result == null) result = caseElement(abstractSignal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.ABSTRACT_EVENT: {
        AbstractEvent abstractEvent = (AbstractEvent)theEObject;
        T result = caseAbstractEvent(abstractEvent);
        if (result == null) result = caseAbstractType(abstractEvent);
        if (result == null) result = caseAbstractNamedElement(abstractEvent);
        if (result == null) result = caseModelElement(abstractEvent);
        if (result == null) result = caseExtensibleElement(abstractEvent);
        if (result == null) result = caseElement(abstractEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.ABSTRACT_TIME_EVENT: {
        AbstractTimeEvent abstractTimeEvent = (AbstractTimeEvent)theEObject;
        T result = caseAbstractTimeEvent(abstractTimeEvent);
        if (result == null) result = caseAbstractEvent(abstractTimeEvent);
        if (result == null) result = caseAbstractType(abstractTimeEvent);
        if (result == null) result = caseAbstractNamedElement(abstractTimeEvent);
        if (result == null) result = caseModelElement(abstractTimeEvent);
        if (result == null) result = caseExtensibleElement(abstractTimeEvent);
        if (result == null) result = caseElement(abstractTimeEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.ABSTRACT_MESSAGE_EVENT: {
        AbstractMessageEvent abstractMessageEvent = (AbstractMessageEvent)theEObject;
        T result = caseAbstractMessageEvent(abstractMessageEvent);
        if (result == null) result = caseAbstractEvent(abstractMessageEvent);
        if (result == null) result = caseAbstractType(abstractMessageEvent);
        if (result == null) result = caseAbstractNamedElement(abstractMessageEvent);
        if (result == null) result = caseModelElement(abstractMessageEvent);
        if (result == null) result = caseExtensibleElement(abstractMessageEvent);
        if (result == null) result = caseElement(abstractMessageEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.ABSTRACT_SIGNAL_EVENT: {
        AbstractSignalEvent abstractSignalEvent = (AbstractSignalEvent)theEObject;
        T result = caseAbstractSignalEvent(abstractSignalEvent);
        if (result == null) result = caseAbstractMessageEvent(abstractSignalEvent);
        if (result == null) result = caseAbstractEvent(abstractSignalEvent);
        if (result == null) result = caseAbstractType(abstractSignalEvent);
        if (result == null) result = caseAbstractNamedElement(abstractSignalEvent);
        if (result == null) result = caseModelElement(abstractSignalEvent);
        if (result == null) result = caseExtensibleElement(abstractSignalEvent);
        if (result == null) result = caseElement(abstractSignalEvent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BehaviorPackage.TIME_EXPRESSION: {
        TimeExpression timeExpression = (TimeExpression)theEObject;
        T result = caseTimeExpression(timeExpression);
        if (result == null) result = caseValueSpecification(timeExpression);
        if (result == null) result = caseAbstractTypedElement(timeExpression);
        if (result == null) result = caseAbstractNamedElement(timeExpression);
        if (result == null) result = caseModelElement(timeExpression);
        if (result == null) result = caseExtensibleElement(timeExpression);
        if (result == null) result = caseElement(timeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Behavior</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Behavior</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractBehavior(AbstractBehavior object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Signal</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Signal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractSignal(AbstractSignal object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Time Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Time Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractTimeEvent(AbstractTimeEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Message Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Message Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractMessageEvent(AbstractMessageEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Signal Event</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Signal Event</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractSignalEvent(AbstractSignalEvent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Time Expression</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Time Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTimeExpression(TimeExpression object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseValueSpecification(ValueSpecification object) {
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

} //BehaviorSwitch
