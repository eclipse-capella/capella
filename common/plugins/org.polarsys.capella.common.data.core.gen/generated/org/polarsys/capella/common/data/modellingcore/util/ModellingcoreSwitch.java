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
package org.polarsys.capella.common.data.modellingcore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.*;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
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
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage
 * @generated
 */
public class ModellingcoreSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static ModellingcorePackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ModellingcoreSwitch() {
    if (modelPackage == null) {
      modelPackage = ModellingcorePackage.eINSTANCE;
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
      case ModellingcorePackage.MODEL_ELEMENT: {
        ModelElement modelElement = (ModelElement)theEObject;
        T result = caseModelElement(modelElement);
        if (result == null) result = caseExtensibleElement(modelElement);
        if (result == null) result = caseElement(modelElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_RELATIONSHIP: {
        AbstractRelationship abstractRelationship = (AbstractRelationship)theEObject;
        T result = caseAbstractRelationship(abstractRelationship);
        if (result == null) result = caseModelElement(abstractRelationship);
        if (result == null) result = caseExtensibleElement(abstractRelationship);
        if (result == null) result = caseElement(abstractRelationship);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_NAMED_ELEMENT: {
        AbstractNamedElement abstractNamedElement = (AbstractNamedElement)theEObject;
        T result = caseAbstractNamedElement(abstractNamedElement);
        if (result == null) result = caseModelElement(abstractNamedElement);
        if (result == null) result = caseExtensibleElement(abstractNamedElement);
        if (result == null) result = caseElement(abstractNamedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.INFORMATIONS_EXCHANGER: {
        InformationsExchanger informationsExchanger = (InformationsExchanger)theEObject;
        T result = caseInformationsExchanger(informationsExchanger);
        if (result == null) result = caseModelElement(informationsExchanger);
        if (result == null) result = caseExtensibleElement(informationsExchanger);
        if (result == null) result = caseElement(informationsExchanger);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.TRACEABLE_ELEMENT: {
        TraceableElement traceableElement = (TraceableElement)theEObject;
        T result = caseTraceableElement(traceableElement);
        if (result == null) result = caseModelElement(traceableElement);
        if (result == null) result = caseExtensibleElement(traceableElement);
        if (result == null) result = caseElement(traceableElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.FINALIZABLE_ELEMENT: {
        FinalizableElement finalizableElement = (FinalizableElement)theEObject;
        T result = caseFinalizableElement(finalizableElement);
        if (result == null) result = caseModelElement(finalizableElement);
        if (result == null) result = caseExtensibleElement(finalizableElement);
        if (result == null) result = caseElement(finalizableElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.PUBLISHABLE_ELEMENT: {
        PublishableElement publishableElement = (PublishableElement)theEObject;
        T result = casePublishableElement(publishableElement);
        if (result == null) result = caseModelElement(publishableElement);
        if (result == null) result = caseExtensibleElement(publishableElement);
        if (result == null) result = caseElement(publishableElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_TYPE: {
        AbstractType abstractType = (AbstractType)theEObject;
        T result = caseAbstractType(abstractType);
        if (result == null) result = caseAbstractNamedElement(abstractType);
        if (result == null) result = caseModelElement(abstractType);
        if (result == null) result = caseExtensibleElement(abstractType);
        if (result == null) result = caseElement(abstractType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_TYPED_ELEMENT: {
        AbstractTypedElement abstractTypedElement = (AbstractTypedElement)theEObject;
        T result = caseAbstractTypedElement(abstractTypedElement);
        if (result == null) result = caseAbstractNamedElement(abstractTypedElement);
        if (result == null) result = caseModelElement(abstractTypedElement);
        if (result == null) result = caseExtensibleElement(abstractTypedElement);
        if (result == null) result = caseElement(abstractTypedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_TRACE: {
        AbstractTrace abstractTrace = (AbstractTrace)theEObject;
        T result = caseAbstractTrace(abstractTrace);
        if (result == null) result = caseTraceableElement(abstractTrace);
        if (result == null) result = caseModelElement(abstractTrace);
        if (result == null) result = caseExtensibleElement(abstractTrace);
        if (result == null) result = caseElement(abstractTrace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_CONSTRAINT: {
        AbstractConstraint abstractConstraint = (AbstractConstraint)theEObject;
        T result = caseAbstractConstraint(abstractConstraint);
        if (result == null) result = caseModelElement(abstractConstraint);
        if (result == null) result = caseExtensibleElement(abstractConstraint);
        if (result == null) result = caseElement(abstractConstraint);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.VALUE_SPECIFICATION: {
        ValueSpecification valueSpecification = (ValueSpecification)theEObject;
        T result = caseValueSpecification(valueSpecification);
        if (result == null) result = caseAbstractTypedElement(valueSpecification);
        if (result == null) result = caseAbstractNamedElement(valueSpecification);
        if (result == null) result = caseModelElement(valueSpecification);
        if (result == null) result = caseExtensibleElement(valueSpecification);
        if (result == null) result = caseElement(valueSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_PARAMETER: {
        AbstractParameter abstractParameter = (AbstractParameter)theEObject;
        T result = caseAbstractParameter(abstractParameter);
        if (result == null) result = caseAbstractTypedElement(abstractParameter);
        if (result == null) result = caseAbstractNamedElement(abstractParameter);
        if (result == null) result = caseModelElement(abstractParameter);
        if (result == null) result = caseExtensibleElement(abstractParameter);
        if (result == null) result = caseElement(abstractParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET: {
        AbstractParameterSet abstractParameterSet = (AbstractParameterSet)theEObject;
        T result = caseAbstractParameterSet(abstractParameterSet);
        if (result == null) result = caseAbstractNamedElement(abstractParameterSet);
        if (result == null) result = caseModelElement(abstractParameterSet);
        if (result == null) result = caseExtensibleElement(abstractParameterSet);
        if (result == null) result = caseElement(abstractParameterSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW: {
        AbstractInformationFlow abstractInformationFlow = (AbstractInformationFlow)theEObject;
        T result = caseAbstractInformationFlow(abstractInformationFlow);
        if (result == null) result = caseAbstractNamedElement(abstractInformationFlow);
        if (result == null) result = caseAbstractRelationship(abstractInformationFlow);
        if (result == null) result = caseModelElement(abstractInformationFlow);
        if (result == null) result = caseExtensibleElement(abstractInformationFlow);
        if (result == null) result = caseElement(abstractInformationFlow);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ABSTRACT_EXCHANGE_ITEM: {
        AbstractExchangeItem abstractExchangeItem = (AbstractExchangeItem)theEObject;
        T result = caseAbstractExchangeItem(abstractExchangeItem);
        if (result == null) result = caseAbstractType(abstractExchangeItem);
        if (result == null) result = caseAbstractNamedElement(abstractExchangeItem);
        if (result == null) result = caseModelElement(abstractExchangeItem);
        if (result == null) result = caseExtensibleElement(abstractExchangeItem);
        if (result == null) result = caseElement(abstractExchangeItem);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ModellingcorePackage.ISTATE: {
        IState iState = (IState)theEObject;
        T result = caseIState(iState);
        if (result == null) result = caseAbstractNamedElement(iState);
        if (result == null) result = caseModelElement(iState);
        if (result == null) result = caseExtensibleElement(iState);
        if (result == null) result = caseElement(iState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
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
   * Returns the result of interpreting the object as an instance of '<em>Informations Exchanger</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Informations Exchanger</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInformationsExchanger(InformationsExchanger object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Constraint</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractConstraint(AbstractConstraint object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Parameter</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractParameter(AbstractParameter object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Parameter Set</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Parameter Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractParameterSet(AbstractParameterSet object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Exchange Item</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Exchange Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractExchangeItem(AbstractExchangeItem object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>IState</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IState</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseIState(IState object) {
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

} //ModellingcoreSwitch
