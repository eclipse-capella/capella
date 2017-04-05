/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.BooleanOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.FSMType;
import org.polarsys.capella.vp.ms.InSituationExpression;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see org.polarsys.capella.vp.ms.MsPackage
 * @generated
 */
public class MsSwitch<T> extends Switch<T> {
  /**
   * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static MsPackage modelPackage;

  /**
   * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public MsSwitch() {
    if (modelPackage == null) {
      modelPackage = MsPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param ePackage
   *          the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
    case MsPackage.CS_CONFIGURATION: {
      CSConfiguration csConfiguration = (CSConfiguration) theEObject;
      T result = caseCSConfiguration(csConfiguration);
      if (result == null)
        result = caseNamedElement(csConfiguration);
      if (result == null)
        result = caseElementExtension(csConfiguration);
      if (result == null)
        result = caseAbstractNamedElement(csConfiguration);
      if (result == null)
        result = caseCapellaElement(csConfiguration);
      if (result == null)
        result = caseTraceableElement(csConfiguration);
      if (result == null)
        result = casePublishableElement(csConfiguration);
      if (result == null)
        result = caseModelElement(csConfiguration);
      if (result == null)
        result = caseExtensibleElement(csConfiguration);
      if (result == null)
        result = caseElement(csConfiguration);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.FSM_TYPE: {
      FSMType fsmType = (FSMType) theEObject;
      T result = caseFSMType(fsmType);
      if (result == null)
        result = caseNamedElement(fsmType);
      if (result == null)
        result = caseElementExtension(fsmType);
      if (result == null)
        result = caseAbstractNamedElement(fsmType);
      if (result == null)
        result = caseCapellaElement(fsmType);
      if (result == null)
        result = caseTraceableElement(fsmType);
      if (result == null)
        result = casePublishableElement(fsmType);
      if (result == null)
        result = caseModelElement(fsmType);
      if (result == null)
        result = caseExtensibleElement(fsmType);
      if (result == null)
        result = caseElement(fsmType);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.SITUATION: {
      Situation situation = (Situation) theEObject;
      T result = caseSituation(situation);
      if (result == null)
        result = caseNamedElement(situation);
      if (result == null)
        result = caseElementExtension(situation);
      if (result == null)
        result = caseAbstractNamedElement(situation);
      if (result == null)
        result = caseCapellaElement(situation);
      if (result == null)
        result = caseTraceableElement(situation);
      if (result == null)
        result = casePublishableElement(situation);
      if (result == null)
        result = caseModelElement(situation);
      if (result == null)
        result = caseExtensibleElement(situation);
      if (result == null)
        result = caseElement(situation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.BOOLEAN_EXPRESSION: {
      BooleanExpression booleanExpression = (BooleanExpression) theEObject;
      T result = caseBooleanExpression(booleanExpression);
      if (result == null)
        result = caseCapellaElement(booleanExpression);
      if (result == null)
        result = caseTraceableElement(booleanExpression);
      if (result == null)
        result = casePublishableElement(booleanExpression);
      if (result == null)
        result = caseModelElement(booleanExpression);
      if (result == null)
        result = caseExtensibleElement(booleanExpression);
      if (result == null)
        result = caseElement(booleanExpression);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.BOOLEAN_OPERATION: {
      BooleanOperation booleanOperation = (BooleanOperation) theEObject;
      T result = caseBooleanOperation(booleanOperation);
      if (result == null)
        result = caseBooleanExpression(booleanOperation);
      if (result == null)
        result = caseCapellaElement(booleanOperation);
      if (result == null)
        result = caseTraceableElement(booleanOperation);
      if (result == null)
        result = casePublishableElement(booleanOperation);
      if (result == null)
        result = caseModelElement(booleanOperation);
      if (result == null)
        result = caseExtensibleElement(booleanOperation);
      if (result == null)
        result = caseElement(booleanOperation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.IN_STATE_EXPRESSION: {
      InStateExpression inStateExpression = (InStateExpression) theEObject;
      T result = caseInStateExpression(inStateExpression);
      if (result == null)
        result = caseBooleanExpression(inStateExpression);
      if (result == null)
        result = caseCapellaElement(inStateExpression);
      if (result == null)
        result = caseTraceableElement(inStateExpression);
      if (result == null)
        result = casePublishableElement(inStateExpression);
      if (result == null)
        result = caseModelElement(inStateExpression);
      if (result == null)
        result = caseExtensibleElement(inStateExpression);
      if (result == null)
        result = caseElement(inStateExpression);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.IN_SITUATION_EXPRESSION: {
      InSituationExpression inSituationExpression = (InSituationExpression) theEObject;
      T result = caseInSituationExpression(inSituationExpression);
      if (result == null)
        result = caseBooleanExpression(inSituationExpression);
      if (result == null)
        result = caseCapellaElement(inSituationExpression);
      if (result == null)
        result = caseTraceableElement(inSituationExpression);
      if (result == null)
        result = casePublishableElement(inSituationExpression);
      if (result == null)
        result = caseModelElement(inSituationExpression);
      if (result == null)
        result = caseExtensibleElement(inSituationExpression);
      if (result == null)
        result = caseElement(inSituationExpression);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.AND_OPERATION: {
      AndOperation andOperation = (AndOperation) theEObject;
      T result = caseAndOperation(andOperation);
      if (result == null)
        result = caseBooleanOperation(andOperation);
      if (result == null)
        result = caseBooleanExpression(andOperation);
      if (result == null)
        result = caseCapellaElement(andOperation);
      if (result == null)
        result = caseTraceableElement(andOperation);
      if (result == null)
        result = casePublishableElement(andOperation);
      if (result == null)
        result = caseModelElement(andOperation);
      if (result == null)
        result = caseExtensibleElement(andOperation);
      if (result == null)
        result = caseElement(andOperation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.OR_OPERATION: {
      OrOperation orOperation = (OrOperation) theEObject;
      T result = caseOrOperation(orOperation);
      if (result == null)
        result = caseBooleanOperation(orOperation);
      if (result == null)
        result = caseBooleanExpression(orOperation);
      if (result == null)
        result = caseCapellaElement(orOperation);
      if (result == null)
        result = caseTraceableElement(orOperation);
      if (result == null)
        result = casePublishableElement(orOperation);
      if (result == null)
        result = caseModelElement(orOperation);
      if (result == null)
        result = caseExtensibleElement(orOperation);
      if (result == null)
        result = caseElement(orOperation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case MsPackage.NOT_OPERATION: {
      NotOperation notOperation = (NotOperation) theEObject;
      T result = caseNotOperation(notOperation);
      if (result == null)
        result = caseBooleanOperation(notOperation);
      if (result == null)
        result = caseBooleanExpression(notOperation);
      if (result == null)
        result = caseCapellaElement(notOperation);
      if (result == null)
        result = caseTraceableElement(notOperation);
      if (result == null)
        result = casePublishableElement(notOperation);
      if (result == null)
        result = caseModelElement(notOperation);
      if (result == null)
        result = caseExtensibleElement(notOperation);
      if (result == null)
        result = caseElement(notOperation);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>CS Configuration</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>CS Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCSConfiguration(CSConfiguration object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>FSM Type</em>'. <!-- begin-user-doc --> This
   * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>FSM Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFSMType(FSMType object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Situation</em>'. <!-- begin-user-doc --> This
   * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Situation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSituation(Situation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Expression</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanExpression(BooleanExpression object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Operation</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanOperation(BooleanOperation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>In State Expression</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>In State Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInStateExpression(InStateExpression object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>In Situation Expression</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>In Situation Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInSituationExpression(InSituationExpression object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>And Operation</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>And Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAndOperation(AndOperation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Operation</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrOperation(OrOperation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Not Operation</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Not Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNotOperation(NotOperation object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element</em>'. <!-- begin-user-doc --> This
   * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElement(Element object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Extensible Element</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Extensible Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExtensibleElement(ExtensibleElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelElement(ModelElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'. <!--
   * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
   * end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAbstractNamedElement(AbstractNamedElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Traceable Element</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Traceable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTraceableElement(TraceableElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Publishable Element</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Publishable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePublishableElement(PublishableElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Capella Element</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capella Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCapellaElement(CapellaElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'. <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedElement(NamedElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element Extension</em>'. <!-- begin-user-doc
   * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element Extension</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementExtension(ElementExtension object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
   * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
   * anyway. <!-- end-user-doc -->
   * 
   * @param object
   *          the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }

} // MsSwitch
