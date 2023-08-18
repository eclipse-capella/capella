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
package org.polarsys.capella.common.data.activity.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.activity.*;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.AcceptEventAction;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.common.data.activity.ActivityGroup;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ActivityPartition;
import org.polarsys.capella.common.data.activity.CallAction;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.activity.ControlFlow;
import org.polarsys.capella.common.data.activity.ExceptionHandler;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.InterruptibleActivityRegion;
import org.polarsys.capella.common.data.activity.InvocationAction;
import org.polarsys.capella.common.data.activity.ObjectFlow;
import org.polarsys.capella.common.data.activity.ObjectNode;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.activity.SendSignalAction;
import org.polarsys.capella.common.data.activity.StructuredActivityNode;
import org.polarsys.capella.common.data.activity.ValuePin;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
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
 * @see org.polarsys.capella.common.data.activity.ActivityPackage
 * @generated
 */
public class ActivitySwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static ActivityPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ActivitySwitch() {
    if (modelPackage == null) {
      modelPackage = ActivityPackage.eINSTANCE;
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
      case ActivityPackage.ABSTRACT_ACTIVITY: {
        AbstractActivity abstractActivity = (AbstractActivity)theEObject;
        T result = caseAbstractActivity(abstractActivity);
        if (result == null) result = caseAbstractBehavior(abstractActivity);
        if (result == null) result = caseTraceableElement(abstractActivity);
        if (result == null) result = caseAbstractNamedElement(abstractActivity);
        if (result == null) result = caseModelElement(abstractActivity);
        if (result == null) result = caseExtensibleElement(abstractActivity);
        if (result == null) result = caseElement(abstractActivity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.EXCEPTION_HANDLER: {
        ExceptionHandler exceptionHandler = (ExceptionHandler)theEObject;
        T result = caseExceptionHandler(exceptionHandler);
        if (result == null) result = caseModelElement(exceptionHandler);
        if (result == null) result = caseExtensibleElement(exceptionHandler);
        if (result == null) result = caseElement(exceptionHandler);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACTIVITY_GROUP: {
        ActivityGroup activityGroup = (ActivityGroup)theEObject;
        T result = caseActivityGroup(activityGroup);
        if (result == null) result = caseModelElement(activityGroup);
        if (result == null) result = caseExtensibleElement(activityGroup);
        if (result == null) result = caseElement(activityGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.INTERRUPTIBLE_ACTIVITY_REGION: {
        InterruptibleActivityRegion interruptibleActivityRegion = (InterruptibleActivityRegion)theEObject;
        T result = caseInterruptibleActivityRegion(interruptibleActivityRegion);
        if (result == null) result = caseActivityGroup(interruptibleActivityRegion);
        if (result == null) result = caseModelElement(interruptibleActivityRegion);
        if (result == null) result = caseExtensibleElement(interruptibleActivityRegion);
        if (result == null) result = caseElement(interruptibleActivityRegion);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACTIVITY_EDGE: {
        ActivityEdge activityEdge = (ActivityEdge)theEObject;
        T result = caseActivityEdge(activityEdge);
        if (result == null) result = caseAbstractRelationship(activityEdge);
        if (result == null) result = caseModelElement(activityEdge);
        if (result == null) result = caseExtensibleElement(activityEdge);
        if (result == null) result = caseElement(activityEdge);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.CONTROL_FLOW: {
        ControlFlow controlFlow = (ControlFlow)theEObject;
        T result = caseControlFlow(controlFlow);
        if (result == null) result = caseActivityEdge(controlFlow);
        if (result == null) result = caseAbstractRelationship(controlFlow);
        if (result == null) result = caseModelElement(controlFlow);
        if (result == null) result = caseExtensibleElement(controlFlow);
        if (result == null) result = caseElement(controlFlow);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.OBJECT_FLOW: {
        ObjectFlow objectFlow = (ObjectFlow)theEObject;
        T result = caseObjectFlow(objectFlow);
        if (result == null) result = caseActivityEdge(objectFlow);
        if (result == null) result = caseAbstractRelationship(objectFlow);
        if (result == null) result = caseModelElement(objectFlow);
        if (result == null) result = caseExtensibleElement(objectFlow);
        if (result == null) result = caseElement(objectFlow);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACTIVITY_PARTITION: {
        ActivityPartition activityPartition = (ActivityPartition)theEObject;
        T result = caseActivityPartition(activityPartition);
        if (result == null) result = caseActivityGroup(activityPartition);
        if (result == null) result = caseAbstractNamedElement(activityPartition);
        if (result == null) result = caseModelElement(activityPartition);
        if (result == null) result = caseExtensibleElement(activityPartition);
        if (result == null) result = caseElement(activityPartition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACTIVITY_EXCHANGE: {
        ActivityExchange activityExchange = (ActivityExchange)theEObject;
        T result = caseActivityExchange(activityExchange);
        if (result == null) result = caseAbstractInformationFlow(activityExchange);
        if (result == null) result = caseAbstractNamedElement(activityExchange);
        if (result == null) result = caseAbstractRelationship(activityExchange);
        if (result == null) result = caseModelElement(activityExchange);
        if (result == null) result = caseExtensibleElement(activityExchange);
        if (result == null) result = caseElement(activityExchange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACTIVITY_NODE: {
        ActivityNode activityNode = (ActivityNode)theEObject;
        T result = caseActivityNode(activityNode);
        if (result == null) result = caseAbstractNamedElement(activityNode);
        if (result == null) result = caseModelElement(activityNode);
        if (result == null) result = caseExtensibleElement(activityNode);
        if (result == null) result = caseElement(activityNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.EXECUTABLE_NODE: {
        ExecutableNode executableNode = (ExecutableNode)theEObject;
        T result = caseExecutableNode(executableNode);
        if (result == null) result = caseActivityNode(executableNode);
        if (result == null) result = caseAbstractNamedElement(executableNode);
        if (result == null) result = caseModelElement(executableNode);
        if (result == null) result = caseExtensibleElement(executableNode);
        if (result == null) result = caseElement(executableNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.STRUCTURED_ACTIVITY_NODE: {
        StructuredActivityNode structuredActivityNode = (StructuredActivityNode)theEObject;
        T result = caseStructuredActivityNode(structuredActivityNode);
        if (result == null) result = caseActivityGroup(structuredActivityNode);
        if (result == null) result = caseAbstractAction(structuredActivityNode);
        if (result == null) result = caseExecutableNode(structuredActivityNode);
        if (result == null) result = caseExtensibleElement(structuredActivityNode);
        if (result == null) result = caseActivityNode(structuredActivityNode);
        if (result == null) result = caseElement(structuredActivityNode);
        if (result == null) result = caseAbstractNamedElement(structuredActivityNode);
        if (result == null) result = caseModelElement(structuredActivityNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ABSTRACT_ACTION: {
        AbstractAction abstractAction = (AbstractAction)theEObject;
        T result = caseAbstractAction(abstractAction);
        if (result == null) result = caseExecutableNode(abstractAction);
        if (result == null) result = caseActivityNode(abstractAction);
        if (result == null) result = caseAbstractNamedElement(abstractAction);
        if (result == null) result = caseModelElement(abstractAction);
        if (result == null) result = caseExtensibleElement(abstractAction);
        if (result == null) result = caseElement(abstractAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.ACCEPT_EVENT_ACTION: {
        AcceptEventAction acceptEventAction = (AcceptEventAction)theEObject;
        T result = caseAcceptEventAction(acceptEventAction);
        if (result == null) result = caseAbstractAction(acceptEventAction);
        if (result == null) result = caseExecutableNode(acceptEventAction);
        if (result == null) result = caseActivityNode(acceptEventAction);
        if (result == null) result = caseAbstractNamedElement(acceptEventAction);
        if (result == null) result = caseModelElement(acceptEventAction);
        if (result == null) result = caseExtensibleElement(acceptEventAction);
        if (result == null) result = caseElement(acceptEventAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.INVOCATION_ACTION: {
        InvocationAction invocationAction = (InvocationAction)theEObject;
        T result = caseInvocationAction(invocationAction);
        if (result == null) result = caseAbstractAction(invocationAction);
        if (result == null) result = caseExecutableNode(invocationAction);
        if (result == null) result = caseActivityNode(invocationAction);
        if (result == null) result = caseAbstractNamedElement(invocationAction);
        if (result == null) result = caseModelElement(invocationAction);
        if (result == null) result = caseExtensibleElement(invocationAction);
        if (result == null) result = caseElement(invocationAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.SEND_SIGNAL_ACTION: {
        SendSignalAction sendSignalAction = (SendSignalAction)theEObject;
        T result = caseSendSignalAction(sendSignalAction);
        if (result == null) result = caseInvocationAction(sendSignalAction);
        if (result == null) result = caseAbstractAction(sendSignalAction);
        if (result == null) result = caseExecutableNode(sendSignalAction);
        if (result == null) result = caseActivityNode(sendSignalAction);
        if (result == null) result = caseAbstractNamedElement(sendSignalAction);
        if (result == null) result = caseModelElement(sendSignalAction);
        if (result == null) result = caseExtensibleElement(sendSignalAction);
        if (result == null) result = caseElement(sendSignalAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.CALL_ACTION: {
        CallAction callAction = (CallAction)theEObject;
        T result = caseCallAction(callAction);
        if (result == null) result = caseInvocationAction(callAction);
        if (result == null) result = caseAbstractAction(callAction);
        if (result == null) result = caseExecutableNode(callAction);
        if (result == null) result = caseActivityNode(callAction);
        if (result == null) result = caseAbstractNamedElement(callAction);
        if (result == null) result = caseModelElement(callAction);
        if (result == null) result = caseExtensibleElement(callAction);
        if (result == null) result = caseElement(callAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.CALL_BEHAVIOR_ACTION: {
        CallBehaviorAction callBehaviorAction = (CallBehaviorAction)theEObject;
        T result = caseCallBehaviorAction(callBehaviorAction);
        if (result == null) result = caseCallAction(callBehaviorAction);
        if (result == null) result = caseInvocationAction(callBehaviorAction);
        if (result == null) result = caseAbstractAction(callBehaviorAction);
        if (result == null) result = caseExecutableNode(callBehaviorAction);
        if (result == null) result = caseActivityNode(callBehaviorAction);
        if (result == null) result = caseAbstractNamedElement(callBehaviorAction);
        if (result == null) result = caseModelElement(callBehaviorAction);
        if (result == null) result = caseExtensibleElement(callBehaviorAction);
        if (result == null) result = caseElement(callBehaviorAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.OBJECT_NODE: {
        ObjectNode objectNode = (ObjectNode)theEObject;
        T result = caseObjectNode(objectNode);
        if (result == null) result = caseActivityNode(objectNode);
        if (result == null) result = caseAbstractTypedElement(objectNode);
        if (result == null) result = caseAbstractNamedElement(objectNode);
        if (result == null) result = caseModelElement(objectNode);
        if (result == null) result = caseExtensibleElement(objectNode);
        if (result == null) result = caseElement(objectNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.PIN: {
        Pin pin = (Pin)theEObject;
        T result = casePin(pin);
        if (result == null) result = caseObjectNode(pin);
        if (result == null) result = caseActivityNode(pin);
        if (result == null) result = caseAbstractTypedElement(pin);
        if (result == null) result = caseAbstractNamedElement(pin);
        if (result == null) result = caseModelElement(pin);
        if (result == null) result = caseExtensibleElement(pin);
        if (result == null) result = caseElement(pin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.INPUT_PIN: {
        InputPin inputPin = (InputPin)theEObject;
        T result = caseInputPin(inputPin);
        if (result == null) result = casePin(inputPin);
        if (result == null) result = caseObjectNode(inputPin);
        if (result == null) result = caseActivityNode(inputPin);
        if (result == null) result = caseAbstractTypedElement(inputPin);
        if (result == null) result = caseAbstractNamedElement(inputPin);
        if (result == null) result = caseModelElement(inputPin);
        if (result == null) result = caseExtensibleElement(inputPin);
        if (result == null) result = caseElement(inputPin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.VALUE_PIN: {
        ValuePin valuePin = (ValuePin)theEObject;
        T result = caseValuePin(valuePin);
        if (result == null) result = caseInputPin(valuePin);
        if (result == null) result = casePin(valuePin);
        if (result == null) result = caseObjectNode(valuePin);
        if (result == null) result = caseActivityNode(valuePin);
        if (result == null) result = caseAbstractTypedElement(valuePin);
        if (result == null) result = caseAbstractNamedElement(valuePin);
        if (result == null) result = caseModelElement(valuePin);
        if (result == null) result = caseExtensibleElement(valuePin);
        if (result == null) result = caseElement(valuePin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ActivityPackage.OUTPUT_PIN: {
        OutputPin outputPin = (OutputPin)theEObject;
        T result = caseOutputPin(outputPin);
        if (result == null) result = casePin(outputPin);
        if (result == null) result = caseObjectNode(outputPin);
        if (result == null) result = caseActivityNode(outputPin);
        if (result == null) result = caseAbstractTypedElement(outputPin);
        if (result == null) result = caseAbstractNamedElement(outputPin);
        if (result == null) result = caseModelElement(outputPin);
        if (result == null) result = caseExtensibleElement(outputPin);
        if (result == null) result = caseElement(outputPin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Activity</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Activity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractActivity(AbstractActivity object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exception Handler</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exception Handler</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExceptionHandler(ExceptionHandler object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Interruptible Activity Region</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interruptible Activity Region</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterruptibleActivityRegion(InterruptibleActivityRegion object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Edge</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Edge</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseActivityEdge(ActivityEdge object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Control Flow</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Control Flow</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseControlFlow(ControlFlow object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Object Flow</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Object Flow</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseObjectFlow(ObjectFlow object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Structured Activity Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structured Activity Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseStructuredActivityNode(StructuredActivityNode object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Accept Event Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Accept Event Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAcceptEventAction(AcceptEventAction object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Send Signal Action</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Send Signal Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSendSignalAction(SendSignalAction object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Object Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Object Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseObjectNode(ObjectNode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Pin</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pin</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePin(Pin object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Input Pin</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Input Pin</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInputPin(InputPin object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Value Pin</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Pin</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseValuePin(ValuePin object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Output Pin</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Output Pin</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseOutputPin(OutputPin object) {
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

} //ActivitySwitch
