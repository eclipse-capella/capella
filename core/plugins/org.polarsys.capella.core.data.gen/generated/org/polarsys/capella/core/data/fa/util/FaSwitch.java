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
package org.polarsys.capella.core.data.fa.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.AbstractActivity;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityExchange;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.CallAction;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.InvocationAction;
import org.polarsys.capella.common.data.activity.ObjectFlow;
import org.polarsys.capella.common.data.activity.ObjectNode;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.activity.Pin;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.fa.*;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionAllocation;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.ExchangeContainment;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.Property;
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
 * @see org.polarsys.capella.core.data.fa.FaPackage
 * @generated
 */
public class FaSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static FaPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public FaSwitch() {
    if (modelPackage == null) {
      modelPackage = FaPackage.eINSTANCE;
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
      case FaPackage.ABSTRACT_FUNCTIONAL_ARCHITECTURE: {
        AbstractFunctionalArchitecture abstractFunctionalArchitecture = (AbstractFunctionalArchitecture)theEObject;
        T result = caseAbstractFunctionalArchitecture(abstractFunctionalArchitecture);
        if (result == null) result = caseModellingArchitecture(abstractFunctionalArchitecture);
        if (result == null) result = caseStructure(abstractFunctionalArchitecture);
        if (result == null) result = caseNamespace(abstractFunctionalArchitecture);
        if (result == null) result = caseNamedElement(abstractFunctionalArchitecture);
        if (result == null) result = caseAbstractNamedElement(abstractFunctionalArchitecture);
        if (result == null) result = caseCapellaElement(abstractFunctionalArchitecture);
        if (result == null) result = caseTraceableElement(abstractFunctionalArchitecture);
        if (result == null) result = casePublishableElement(abstractFunctionalArchitecture);
        if (result == null) result = caseModelElement(abstractFunctionalArchitecture);
        if (result == null) result = caseExtensibleElement(abstractFunctionalArchitecture);
        if (result == null) result = caseElement(abstractFunctionalArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK: {
        AbstractFunctionalBlock abstractFunctionalBlock = (AbstractFunctionalBlock)theEObject;
        T result = caseAbstractFunctionalBlock(abstractFunctionalBlock);
        if (result == null) result = caseModellingBlock(abstractFunctionalBlock);
        if (result == null) result = caseType(abstractFunctionalBlock);
        if (result == null) result = caseAbstractType(abstractFunctionalBlock);
        if (result == null) result = caseNamespace(abstractFunctionalBlock);
        if (result == null) result = caseNamedElement(abstractFunctionalBlock);
        if (result == null) result = caseAbstractNamedElement(abstractFunctionalBlock);
        if (result == null) result = caseCapellaElement(abstractFunctionalBlock);
        if (result == null) result = caseExtensibleElement(abstractFunctionalBlock);
        if (result == null) result = caseTraceableElement(abstractFunctionalBlock);
        if (result == null) result = casePublishableElement(abstractFunctionalBlock);
        if (result == null) result = caseModelElement(abstractFunctionalBlock);
        if (result == null) result = caseElement(abstractFunctionalBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_PKG: {
        FunctionPkg functionPkg = (FunctionPkg)theEObject;
        T result = caseFunctionPkg(functionPkg);
        if (result == null) result = caseStructure(functionPkg);
        if (result == null) result = caseNamespace(functionPkg);
        if (result == null) result = caseNamedElement(functionPkg);
        if (result == null) result = caseAbstractNamedElement(functionPkg);
        if (result == null) result = caseCapellaElement(functionPkg);
        if (result == null) result = caseTraceableElement(functionPkg);
        if (result == null) result = casePublishableElement(functionPkg);
        if (result == null) result = caseModelElement(functionPkg);
        if (result == null) result = caseExtensibleElement(functionPkg);
        if (result == null) result = caseElement(functionPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_SPECIFICATION: {
        FunctionSpecification functionSpecification = (FunctionSpecification)theEObject;
        T result = caseFunctionSpecification(functionSpecification);
        if (result == null) result = caseNamespace(functionSpecification);
        if (result == null) result = caseAbstractActivity(functionSpecification);
        if (result == null) result = caseNamedElement(functionSpecification);
        if (result == null) result = caseAbstractBehavior(functionSpecification);
        if (result == null) result = caseAbstractNamedElement(functionSpecification);
        if (result == null) result = caseCapellaElement(functionSpecification);
        if (result == null) result = caseTraceableElement(functionSpecification);
        if (result == null) result = casePublishableElement(functionSpecification);
        if (result == null) result = caseModelElement(functionSpecification);
        if (result == null) result = caseExtensibleElement(functionSpecification);
        if (result == null) result = caseElement(functionSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.EXCHANGE_CATEGORY: {
        ExchangeCategory exchangeCategory = (ExchangeCategory)theEObject;
        T result = caseExchangeCategory(exchangeCategory);
        if (result == null) result = caseNamedElement(exchangeCategory);
        if (result == null) result = caseAbstractNamedElement(exchangeCategory);
        if (result == null) result = caseCapellaElement(exchangeCategory);
        if (result == null) result = caseTraceableElement(exchangeCategory);
        if (result == null) result = casePublishableElement(exchangeCategory);
        if (result == null) result = caseModelElement(exchangeCategory);
        if (result == null) result = caseExtensibleElement(exchangeCategory);
        if (result == null) result = caseElement(exchangeCategory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.EXCHANGE_LINK: {
        ExchangeLink exchangeLink = (ExchangeLink)theEObject;
        T result = caseExchangeLink(exchangeLink);
        if (result == null) result = caseNamedRelationship(exchangeLink);
        if (result == null) result = caseRelationship(exchangeLink);
        if (result == null) result = caseNamedElement(exchangeLink);
        if (result == null) result = caseAbstractRelationship(exchangeLink);
        if (result == null) result = caseCapellaElement(exchangeLink);
        if (result == null) result = caseAbstractNamedElement(exchangeLink);
        if (result == null) result = caseTraceableElement(exchangeLink);
        if (result == null) result = casePublishableElement(exchangeLink);
        if (result == null) result = caseModelElement(exchangeLink);
        if (result == null) result = caseExtensibleElement(exchangeLink);
        if (result == null) result = caseElement(exchangeLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.EXCHANGE_CONTAINMENT: {
        ExchangeContainment exchangeContainment = (ExchangeContainment)theEObject;
        T result = caseExchangeContainment(exchangeContainment);
        if (result == null) result = caseRelationship(exchangeContainment);
        if (result == null) result = caseAbstractRelationship(exchangeContainment);
        if (result == null) result = caseCapellaElement(exchangeContainment);
        if (result == null) result = caseTraceableElement(exchangeContainment);
        if (result == null) result = casePublishableElement(exchangeContainment);
        if (result == null) result = caseModelElement(exchangeContainment);
        if (result == null) result = caseExtensibleElement(exchangeContainment);
        if (result == null) result = caseElement(exchangeContainment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.EXCHANGE_SPECIFICATION: {
        ExchangeSpecification exchangeSpecification = (ExchangeSpecification)theEObject;
        T result = caseExchangeSpecification(exchangeSpecification);
        if (result == null) result = caseNamedElement(exchangeSpecification);
        if (result == null) result = caseActivityExchange(exchangeSpecification);
        if (result == null) result = caseCapellaElement(exchangeSpecification);
        if (result == null) result = caseAbstractInformationFlow(exchangeSpecification);
        if (result == null) result = caseAbstractNamedElement(exchangeSpecification);
        if (result == null) result = caseTraceableElement(exchangeSpecification);
        if (result == null) result = casePublishableElement(exchangeSpecification);
        if (result == null) result = caseAbstractRelationship(exchangeSpecification);
        if (result == null) result = caseModelElement(exchangeSpecification);
        if (result == null) result = caseExtensibleElement(exchangeSpecification);
        if (result == null) result = caseElement(exchangeSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_EXCHANGE_SPECIFICATION: {
        FunctionalExchangeSpecification functionalExchangeSpecification = (FunctionalExchangeSpecification)theEObject;
        T result = caseFunctionalExchangeSpecification(functionalExchangeSpecification);
        if (result == null) result = caseExchangeSpecification(functionalExchangeSpecification);
        if (result == null) result = caseNamedElement(functionalExchangeSpecification);
        if (result == null) result = caseActivityExchange(functionalExchangeSpecification);
        if (result == null) result = caseCapellaElement(functionalExchangeSpecification);
        if (result == null) result = caseAbstractInformationFlow(functionalExchangeSpecification);
        if (result == null) result = caseAbstractNamedElement(functionalExchangeSpecification);
        if (result == null) result = caseTraceableElement(functionalExchangeSpecification);
        if (result == null) result = casePublishableElement(functionalExchangeSpecification);
        if (result == null) result = caseAbstractRelationship(functionalExchangeSpecification);
        if (result == null) result = caseModelElement(functionalExchangeSpecification);
        if (result == null) result = caseExtensibleElement(functionalExchangeSpecification);
        if (result == null) result = caseElement(functionalExchangeSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN: {
        FunctionalChain functionalChain = (FunctionalChain)theEObject;
        T result = caseFunctionalChain(functionalChain);
        if (result == null) result = caseNamedElement(functionalChain);
        if (result == null) result = caseInvolverElement(functionalChain);
        if (result == null) result = caseInvolvedElement(functionalChain);
        if (result == null) result = caseAbstractNamedElement(functionalChain);
        if (result == null) result = caseCapellaElement(functionalChain);
        if (result == null) result = caseTraceableElement(functionalChain);
        if (result == null) result = casePublishableElement(functionalChain);
        if (result == null) result = caseModelElement(functionalChain);
        if (result == null) result = caseExtensibleElement(functionalChain);
        if (result == null) result = caseElement(functionalChain);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER: {
        AbstractFunctionalChainContainer abstractFunctionalChainContainer = (AbstractFunctionalChainContainer)theEObject;
        T result = caseAbstractFunctionalChainContainer(abstractFunctionalChainContainer);
        if (result == null) result = caseCapellaElement(abstractFunctionalChainContainer);
        if (result == null) result = caseTraceableElement(abstractFunctionalChainContainer);
        if (result == null) result = casePublishableElement(abstractFunctionalChainContainer);
        if (result == null) result = caseModelElement(abstractFunctionalChainContainer);
        if (result == null) result = caseExtensibleElement(abstractFunctionalChainContainer);
        if (result == null) result = caseElement(abstractFunctionalChainContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT: {
        FunctionalChainInvolvement functionalChainInvolvement = (FunctionalChainInvolvement)theEObject;
        T result = caseFunctionalChainInvolvement(functionalChainInvolvement);
        if (result == null) result = caseInvolvement(functionalChainInvolvement);
        if (result == null) result = caseRelationship(functionalChainInvolvement);
        if (result == null) result = caseAbstractRelationship(functionalChainInvolvement);
        if (result == null) result = caseCapellaElement(functionalChainInvolvement);
        if (result == null) result = caseTraceableElement(functionalChainInvolvement);
        if (result == null) result = casePublishableElement(functionalChainInvolvement);
        if (result == null) result = caseModelElement(functionalChainInvolvement);
        if (result == null) result = caseExtensibleElement(functionalChainInvolvement);
        if (result == null) result = caseElement(functionalChainInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN_REFERENCE: {
        FunctionalChainReference functionalChainReference = (FunctionalChainReference)theEObject;
        T result = caseFunctionalChainReference(functionalChainReference);
        if (result == null) result = caseFunctionalChainInvolvement(functionalChainReference);
        if (result == null) result = caseInvolvement(functionalChainReference);
        if (result == null) result = caseRelationship(functionalChainReference);
        if (result == null) result = caseAbstractRelationship(functionalChainReference);
        if (result == null) result = caseCapellaElement(functionalChainReference);
        if (result == null) result = caseTraceableElement(functionalChainReference);
        if (result == null) result = casePublishableElement(functionalChainReference);
        if (result == null) result = caseModelElement(functionalChainReference);
        if (result == null) result = caseExtensibleElement(functionalChainReference);
        if (result == null) result = caseElement(functionalChainReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_INPUT_PORT: {
        FunctionInputPort functionInputPort = (FunctionInputPort)theEObject;
        T result = caseFunctionInputPort(functionInputPort);
        if (result == null) result = caseFunctionPort(functionInputPort);
        if (result == null) result = caseInputPin(functionInputPort);
        if (result == null) result = casePort(functionInputPort);
        if (result == null) result = caseTypedElement(functionInputPort);
        if (result == null) result = caseAbstractEvent(functionInputPort);
        if (result == null) result = casePin(functionInputPort);
        if (result == null) result = caseNamedElement(functionInputPort);
        if (result == null) result = caseAbstractType(functionInputPort);
        if (result == null) result = caseObjectNode(functionInputPort);
        if (result == null) result = caseCapellaElement(functionInputPort);
        if (result == null) result = caseAbstractTypedElement(functionInputPort);
        if (result == null) result = caseActivityNode(functionInputPort);
        if (result == null) result = caseAbstractNamedElement(functionInputPort);
        if (result == null) result = caseTraceableElement(functionInputPort);
        if (result == null) result = casePublishableElement(functionInputPort);
        if (result == null) result = caseModelElement(functionInputPort);
        if (result == null) result = caseExtensibleElement(functionInputPort);
        if (result == null) result = caseElement(functionInputPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_OUTPUT_PORT: {
        FunctionOutputPort functionOutputPort = (FunctionOutputPort)theEObject;
        T result = caseFunctionOutputPort(functionOutputPort);
        if (result == null) result = caseFunctionPort(functionOutputPort);
        if (result == null) result = caseOutputPin(functionOutputPort);
        if (result == null) result = casePort(functionOutputPort);
        if (result == null) result = caseTypedElement(functionOutputPort);
        if (result == null) result = caseAbstractEvent(functionOutputPort);
        if (result == null) result = casePin(functionOutputPort);
        if (result == null) result = caseNamedElement(functionOutputPort);
        if (result == null) result = caseAbstractType(functionOutputPort);
        if (result == null) result = caseObjectNode(functionOutputPort);
        if (result == null) result = caseCapellaElement(functionOutputPort);
        if (result == null) result = caseAbstractTypedElement(functionOutputPort);
        if (result == null) result = caseActivityNode(functionOutputPort);
        if (result == null) result = caseAbstractNamedElement(functionOutputPort);
        if (result == null) result = caseTraceableElement(functionOutputPort);
        if (result == null) result = casePublishableElement(functionOutputPort);
        if (result == null) result = caseModelElement(functionOutputPort);
        if (result == null) result = caseExtensibleElement(functionOutputPort);
        if (result == null) result = caseElement(functionOutputPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.ABSTRACT_FUNCTION_ALLOCATION: {
        AbstractFunctionAllocation abstractFunctionAllocation = (AbstractFunctionAllocation)theEObject;
        T result = caseAbstractFunctionAllocation(abstractFunctionAllocation);
        if (result == null) result = caseAllocation(abstractFunctionAllocation);
        if (result == null) result = caseRelationship(abstractFunctionAllocation);
        if (result == null) result = caseAbstractTrace(abstractFunctionAllocation);
        if (result == null) result = caseAbstractRelationship(abstractFunctionAllocation);
        if (result == null) result = caseCapellaElement(abstractFunctionAllocation);
        if (result == null) result = caseTraceableElement(abstractFunctionAllocation);
        if (result == null) result = casePublishableElement(abstractFunctionAllocation);
        if (result == null) result = caseModelElement(abstractFunctionAllocation);
        if (result == null) result = caseExtensibleElement(abstractFunctionAllocation);
        if (result == null) result = caseElement(abstractFunctionAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_FUNCTIONAL_ALLOCATION: {
        ComponentFunctionalAllocation componentFunctionalAllocation = (ComponentFunctionalAllocation)theEObject;
        T result = caseComponentFunctionalAllocation(componentFunctionalAllocation);
        if (result == null) result = caseAbstractFunctionAllocation(componentFunctionalAllocation);
        if (result == null) result = caseAllocation(componentFunctionalAllocation);
        if (result == null) result = caseRelationship(componentFunctionalAllocation);
        if (result == null) result = caseAbstractTrace(componentFunctionalAllocation);
        if (result == null) result = caseAbstractRelationship(componentFunctionalAllocation);
        if (result == null) result = caseCapellaElement(componentFunctionalAllocation);
        if (result == null) result = caseTraceableElement(componentFunctionalAllocation);
        if (result == null) result = casePublishableElement(componentFunctionalAllocation);
        if (result == null) result = caseModelElement(componentFunctionalAllocation);
        if (result == null) result = caseExtensibleElement(componentFunctionalAllocation);
        if (result == null) result = caseElement(componentFunctionalAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN_REALIZATION: {
        FunctionalChainRealization functionalChainRealization = (FunctionalChainRealization)theEObject;
        T result = caseFunctionalChainRealization(functionalChainRealization);
        if (result == null) result = caseAllocation(functionalChainRealization);
        if (result == null) result = caseRelationship(functionalChainRealization);
        if (result == null) result = caseAbstractTrace(functionalChainRealization);
        if (result == null) result = caseAbstractRelationship(functionalChainRealization);
        if (result == null) result = caseCapellaElement(functionalChainRealization);
        if (result == null) result = caseTraceableElement(functionalChainRealization);
        if (result == null) result = casePublishableElement(functionalChainRealization);
        if (result == null) result = caseModelElement(functionalChainRealization);
        if (result == null) result = caseExtensibleElement(functionalChainRealization);
        if (result == null) result = caseElement(functionalChainRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.EXCHANGE_SPECIFICATION_REALIZATION: {
        ExchangeSpecificationRealization exchangeSpecificationRealization = (ExchangeSpecificationRealization)theEObject;
        T result = caseExchangeSpecificationRealization(exchangeSpecificationRealization);
        if (result == null) result = caseAllocation(exchangeSpecificationRealization);
        if (result == null) result = caseRelationship(exchangeSpecificationRealization);
        if (result == null) result = caseAbstractTrace(exchangeSpecificationRealization);
        if (result == null) result = caseAbstractRelationship(exchangeSpecificationRealization);
        if (result == null) result = caseCapellaElement(exchangeSpecificationRealization);
        if (result == null) result = caseTraceableElement(exchangeSpecificationRealization);
        if (result == null) result = casePublishableElement(exchangeSpecificationRealization);
        if (result == null) result = caseModelElement(exchangeSpecificationRealization);
        if (result == null) result = caseExtensibleElement(exchangeSpecificationRealization);
        if (result == null) result = caseElement(exchangeSpecificationRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_EXCHANGE_REALIZATION: {
        FunctionalExchangeRealization functionalExchangeRealization = (FunctionalExchangeRealization)theEObject;
        T result = caseFunctionalExchangeRealization(functionalExchangeRealization);
        if (result == null) result = caseAllocation(functionalExchangeRealization);
        if (result == null) result = caseRelationship(functionalExchangeRealization);
        if (result == null) result = caseAbstractTrace(functionalExchangeRealization);
        if (result == null) result = caseAbstractRelationship(functionalExchangeRealization);
        if (result == null) result = caseCapellaElement(functionalExchangeRealization);
        if (result == null) result = caseTraceableElement(functionalExchangeRealization);
        if (result == null) result = casePublishableElement(functionalExchangeRealization);
        if (result == null) result = caseModelElement(functionalExchangeRealization);
        if (result == null) result = caseExtensibleElement(functionalExchangeRealization);
        if (result == null) result = caseElement(functionalExchangeRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_REALIZATION: {
        FunctionRealization functionRealization = (FunctionRealization)theEObject;
        T result = caseFunctionRealization(functionRealization);
        if (result == null) result = caseAbstractFunctionAllocation(functionRealization);
        if (result == null) result = caseAllocation(functionRealization);
        if (result == null) result = caseRelationship(functionRealization);
        if (result == null) result = caseAbstractTrace(functionRealization);
        if (result == null) result = caseAbstractRelationship(functionRealization);
        if (result == null) result = caseCapellaElement(functionRealization);
        if (result == null) result = caseTraceableElement(functionRealization);
        if (result == null) result = casePublishableElement(functionRealization);
        if (result == null) result = caseModelElement(functionRealization);
        if (result == null) result = caseExtensibleElement(functionRealization);
        if (result == null) result = caseElement(functionRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_EXCHANGE: {
        FunctionalExchange functionalExchange = (FunctionalExchange)theEObject;
        T result = caseFunctionalExchange(functionalExchange);
        if (result == null) result = caseRelationship(functionalExchange);
        if (result == null) result = caseInvolvedElement(functionalExchange);
        if (result == null) result = caseObjectFlow(functionalExchange);
        if (result == null) result = caseAbstractEvent(functionalExchange);
        if (result == null) result = caseAbstractEventOperation(functionalExchange);
        if (result == null) result = caseNamedElement(functionalExchange);
        if (result == null) result = caseCapellaElement(functionalExchange);
        if (result == null) result = caseActivityEdge(functionalExchange);
        if (result == null) result = caseAbstractType(functionalExchange);
        if (result == null) result = caseAbstractNamedElement(functionalExchange);
        if (result == null) result = caseTraceableElement(functionalExchange);
        if (result == null) result = casePublishableElement(functionalExchange);
        if (result == null) result = caseAbstractRelationship(functionalExchange);
        if (result == null) result = caseModelElement(functionalExchange);
        if (result == null) result = caseExtensibleElement(functionalExchange);
        if (result == null) result = caseElement(functionalExchange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.ABSTRACT_FUNCTION: {
        AbstractFunction abstractFunction = (AbstractFunction)theEObject;
        T result = caseAbstractFunction(abstractFunction);
        if (result == null) result = caseNamespace(abstractFunction);
        if (result == null) result = caseInvolvedElement(abstractFunction);
        if (result == null) result = caseAbstractInstance(abstractFunction);
        if (result == null) result = caseAbstractFunctionalChainContainer(abstractFunction);
        if (result == null) result = caseCallBehaviorAction(abstractFunction);
        if (result == null) result = caseAbstractEvent(abstractFunction);
        if (result == null) result = caseProperty(abstractFunction);
        if (result == null) result = caseCallAction(abstractFunction);
        if (result == null) result = caseAbstractType(abstractFunction);
        if (result == null) result = caseFeature(abstractFunction);
        if (result == null) result = caseTypedElement(abstractFunction);
        if (result == null) result = caseMultiplicityElement(abstractFunction);
        if (result == null) result = caseFinalizableElement(abstractFunction);
        if (result == null) result = caseInvocationAction(abstractFunction);
        if (result == null) result = caseNamedElement(abstractFunction);
        if (result == null) result = caseCapellaElement(abstractFunction);
        if (result == null) result = caseTraceableElement(abstractFunction);
        if (result == null) result = casePublishableElement(abstractFunction);
        if (result == null) result = caseAbstractTypedElement(abstractFunction);
        if (result == null) result = caseAbstractAction(abstractFunction);
        if (result == null) result = caseModelElement(abstractFunction);
        if (result == null) result = caseExtensibleElement(abstractFunction);
        if (result == null) result = caseExecutableNode(abstractFunction);
        if (result == null) result = caseElement(abstractFunction);
        if (result == null) result = caseActivityNode(abstractFunction);
        if (result == null) result = caseAbstractNamedElement(abstractFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTION_PORT: {
        FunctionPort functionPort = (FunctionPort)theEObject;
        T result = caseFunctionPort(functionPort);
        if (result == null) result = casePort(functionPort);
        if (result == null) result = caseTypedElement(functionPort);
        if (result == null) result = caseAbstractEvent(functionPort);
        if (result == null) result = caseNamedElement(functionPort);
        if (result == null) result = caseAbstractTypedElement(functionPort);
        if (result == null) result = caseAbstractType(functionPort);
        if (result == null) result = caseAbstractNamedElement(functionPort);
        if (result == null) result = caseCapellaElement(functionPort);
        if (result == null) result = caseTraceableElement(functionPort);
        if (result == null) result = casePublishableElement(functionPort);
        if (result == null) result = caseModelElement(functionPort);
        if (result == null) result = caseExtensibleElement(functionPort);
        if (result == null) result = caseElement(functionPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE: {
        ComponentExchange componentExchange = (ComponentExchange)theEObject;
        T result = caseComponentExchange(componentExchange);
        if (result == null) result = caseAbstractEvent(componentExchange);
        if (result == null) result = caseAbstractEventOperation(componentExchange);
        if (result == null) result = caseExchangeSpecification(componentExchange);
        if (result == null) result = caseAbstractType(componentExchange);
        if (result == null) result = caseNamedElement(componentExchange);
        if (result == null) result = caseActivityExchange(componentExchange);
        if (result == null) result = caseCapellaElement(componentExchange);
        if (result == null) result = caseAbstractInformationFlow(componentExchange);
        if (result == null) result = caseAbstractNamedElement(componentExchange);
        if (result == null) result = caseTraceableElement(componentExchange);
        if (result == null) result = casePublishableElement(componentExchange);
        if (result == null) result = caseAbstractRelationship(componentExchange);
        if (result == null) result = caseModelElement(componentExchange);
        if (result == null) result = caseExtensibleElement(componentExchange);
        if (result == null) result = caseElement(componentExchange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATION: {
        ComponentExchangeAllocation componentExchangeAllocation = (ComponentExchangeAllocation)theEObject;
        T result = caseComponentExchangeAllocation(componentExchangeAllocation);
        if (result == null) result = caseAllocation(componentExchangeAllocation);
        if (result == null) result = caseRelationship(componentExchangeAllocation);
        if (result == null) result = caseAbstractTrace(componentExchangeAllocation);
        if (result == null) result = caseAbstractRelationship(componentExchangeAllocation);
        if (result == null) result = caseCapellaElement(componentExchangeAllocation);
        if (result == null) result = caseTraceableElement(componentExchangeAllocation);
        if (result == null) result = casePublishableElement(componentExchangeAllocation);
        if (result == null) result = caseModelElement(componentExchangeAllocation);
        if (result == null) result = caseExtensibleElement(componentExchangeAllocation);
        if (result == null) result = caseElement(componentExchangeAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_ALLOCATOR: {
        ComponentExchangeAllocator componentExchangeAllocator = (ComponentExchangeAllocator)theEObject;
        T result = caseComponentExchangeAllocator(componentExchangeAllocator);
        if (result == null) result = caseNamedElement(componentExchangeAllocator);
        if (result == null) result = caseAbstractNamedElement(componentExchangeAllocator);
        if (result == null) result = caseCapellaElement(componentExchangeAllocator);
        if (result == null) result = caseTraceableElement(componentExchangeAllocator);
        if (result == null) result = casePublishableElement(componentExchangeAllocator);
        if (result == null) result = caseModelElement(componentExchangeAllocator);
        if (result == null) result = caseExtensibleElement(componentExchangeAllocator);
        if (result == null) result = caseElement(componentExchangeAllocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_CATEGORY: {
        ComponentExchangeCategory componentExchangeCategory = (ComponentExchangeCategory)theEObject;
        T result = caseComponentExchangeCategory(componentExchangeCategory);
        if (result == null) result = caseNamedElement(componentExchangeCategory);
        if (result == null) result = caseAbstractNamedElement(componentExchangeCategory);
        if (result == null) result = caseCapellaElement(componentExchangeCategory);
        if (result == null) result = caseTraceableElement(componentExchangeCategory);
        if (result == null) result = casePublishableElement(componentExchangeCategory);
        if (result == null) result = caseModelElement(componentExchangeCategory);
        if (result == null) result = caseExtensibleElement(componentExchangeCategory);
        if (result == null) result = caseElement(componentExchangeCategory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_END: {
        ComponentExchangeEnd componentExchangeEnd = (ComponentExchangeEnd)theEObject;
        T result = caseComponentExchangeEnd(componentExchangeEnd);
        if (result == null) result = caseInformationsExchanger(componentExchangeEnd);
        if (result == null) result = caseCapellaElement(componentExchangeEnd);
        if (result == null) result = caseTraceableElement(componentExchangeEnd);
        if (result == null) result = casePublishableElement(componentExchangeEnd);
        if (result == null) result = caseModelElement(componentExchangeEnd);
        if (result == null) result = caseExtensibleElement(componentExchangeEnd);
        if (result == null) result = caseElement(componentExchangeEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION: {
        ComponentExchangeFunctionalExchangeAllocation componentExchangeFunctionalExchangeAllocation = (ComponentExchangeFunctionalExchangeAllocation)theEObject;
        T result = caseComponentExchangeFunctionalExchangeAllocation(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseAbstractFunctionAllocation(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseAllocation(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseRelationship(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseAbstractTrace(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseAbstractRelationship(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseCapellaElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseTraceableElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = casePublishableElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseModelElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseExtensibleElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = caseElement(componentExchangeFunctionalExchangeAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_EXCHANGE_REALIZATION: {
        ComponentExchangeRealization componentExchangeRealization = (ComponentExchangeRealization)theEObject;
        T result = caseComponentExchangeRealization(componentExchangeRealization);
        if (result == null) result = caseExchangeSpecificationRealization(componentExchangeRealization);
        if (result == null) result = caseAllocation(componentExchangeRealization);
        if (result == null) result = caseRelationship(componentExchangeRealization);
        if (result == null) result = caseAbstractTrace(componentExchangeRealization);
        if (result == null) result = caseAbstractRelationship(componentExchangeRealization);
        if (result == null) result = caseCapellaElement(componentExchangeRealization);
        if (result == null) result = caseTraceableElement(componentExchangeRealization);
        if (result == null) result = casePublishableElement(componentExchangeRealization);
        if (result == null) result = caseModelElement(componentExchangeRealization);
        if (result == null) result = caseExtensibleElement(componentExchangeRealization);
        if (result == null) result = caseElement(componentExchangeRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_PORT: {
        ComponentPort componentPort = (ComponentPort)theEObject;
        T result = caseComponentPort(componentPort);
        if (result == null) result = casePort(componentPort);
        if (result == null) result = caseInformationsExchanger(componentPort);
        if (result == null) result = caseProperty(componentPort);
        if (result == null) result = caseFeature(componentPort);
        if (result == null) result = caseTypedElement(componentPort);
        if (result == null) result = caseMultiplicityElement(componentPort);
        if (result == null) result = caseFinalizableElement(componentPort);
        if (result == null) result = caseNamedElement(componentPort);
        if (result == null) result = caseCapellaElement(componentPort);
        if (result == null) result = caseAbstractTypedElement(componentPort);
        if (result == null) result = caseAbstractNamedElement(componentPort);
        if (result == null) result = caseTraceableElement(componentPort);
        if (result == null) result = casePublishableElement(componentPort);
        if (result == null) result = caseModelElement(componentPort);
        if (result == null) result = caseExtensibleElement(componentPort);
        if (result == null) result = caseElement(componentPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_PORT_ALLOCATION: {
        ComponentPortAllocation componentPortAllocation = (ComponentPortAllocation)theEObject;
        T result = caseComponentPortAllocation(componentPortAllocation);
        if (result == null) result = caseAllocation(componentPortAllocation);
        if (result == null) result = caseRelationship(componentPortAllocation);
        if (result == null) result = caseAbstractTrace(componentPortAllocation);
        if (result == null) result = caseAbstractRelationship(componentPortAllocation);
        if (result == null) result = caseCapellaElement(componentPortAllocation);
        if (result == null) result = caseTraceableElement(componentPortAllocation);
        if (result == null) result = casePublishableElement(componentPortAllocation);
        if (result == null) result = caseModelElement(componentPortAllocation);
        if (result == null) result = caseExtensibleElement(componentPortAllocation);
        if (result == null) result = caseElement(componentPortAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.COMPONENT_PORT_ALLOCATION_END: {
        ComponentPortAllocationEnd componentPortAllocationEnd = (ComponentPortAllocationEnd)theEObject;
        T result = caseComponentPortAllocationEnd(componentPortAllocationEnd);
        if (result == null) result = caseCapellaElement(componentPortAllocationEnd);
        if (result == null) result = caseTraceableElement(componentPortAllocationEnd);
        if (result == null) result = casePublishableElement(componentPortAllocationEnd);
        if (result == null) result = caseModelElement(componentPortAllocationEnd);
        if (result == null) result = caseExtensibleElement(componentPortAllocationEnd);
        if (result == null) result = caseElement(componentPortAllocationEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK: {
        FunctionalChainInvolvementLink functionalChainInvolvementLink = (FunctionalChainInvolvementLink)theEObject;
        T result = caseFunctionalChainInvolvementLink(functionalChainInvolvementLink);
        if (result == null) result = caseFunctionalChainInvolvement(functionalChainInvolvementLink);
        if (result == null) result = caseReferenceHierarchyContext(functionalChainInvolvementLink);
        if (result == null) result = caseInvolvement(functionalChainInvolvementLink);
        if (result == null) result = caseRelationship(functionalChainInvolvementLink);
        if (result == null) result = caseAbstractRelationship(functionalChainInvolvementLink);
        if (result == null) result = caseCapellaElement(functionalChainInvolvementLink);
        if (result == null) result = caseTraceableElement(functionalChainInvolvementLink);
        if (result == null) result = casePublishableElement(functionalChainInvolvementLink);
        if (result == null) result = caseModelElement(functionalChainInvolvementLink);
        if (result == null) result = caseExtensibleElement(functionalChainInvolvementLink);
        if (result == null) result = caseElement(functionalChainInvolvementLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.SEQUENCE_LINK: {
        SequenceLink sequenceLink = (SequenceLink)theEObject;
        T result = caseSequenceLink(sequenceLink);
        if (result == null) result = caseCapellaElement(sequenceLink);
        if (result == null) result = caseReferenceHierarchyContext(sequenceLink);
        if (result == null) result = caseTraceableElement(sequenceLink);
        if (result == null) result = casePublishableElement(sequenceLink);
        if (result == null) result = caseModelElement(sequenceLink);
        if (result == null) result = caseExtensibleElement(sequenceLink);
        if (result == null) result = caseElement(sequenceLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.SEQUENCE_LINK_END: {
        SequenceLinkEnd sequenceLinkEnd = (SequenceLinkEnd)theEObject;
        T result = caseSequenceLinkEnd(sequenceLinkEnd);
        if (result == null) result = caseCapellaElement(sequenceLinkEnd);
        if (result == null) result = caseTraceableElement(sequenceLinkEnd);
        if (result == null) result = casePublishableElement(sequenceLinkEnd);
        if (result == null) result = caseModelElement(sequenceLinkEnd);
        if (result == null) result = caseExtensibleElement(sequenceLinkEnd);
        if (result == null) result = caseElement(sequenceLinkEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION: {
        FunctionalChainInvolvementFunction functionalChainInvolvementFunction = (FunctionalChainInvolvementFunction)theEObject;
        T result = caseFunctionalChainInvolvementFunction(functionalChainInvolvementFunction);
        if (result == null) result = caseFunctionalChainInvolvement(functionalChainInvolvementFunction);
        if (result == null) result = caseSequenceLinkEnd(functionalChainInvolvementFunction);
        if (result == null) result = caseInvolvement(functionalChainInvolvementFunction);
        if (result == null) result = caseRelationship(functionalChainInvolvementFunction);
        if (result == null) result = caseAbstractRelationship(functionalChainInvolvementFunction);
        if (result == null) result = caseCapellaElement(functionalChainInvolvementFunction);
        if (result == null) result = caseTraceableElement(functionalChainInvolvementFunction);
        if (result == null) result = casePublishableElement(functionalChainInvolvementFunction);
        if (result == null) result = caseModelElement(functionalChainInvolvementFunction);
        if (result == null) result = caseExtensibleElement(functionalChainInvolvementFunction);
        if (result == null) result = caseElement(functionalChainInvolvementFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.CONTROL_NODE: {
        ControlNode controlNode = (ControlNode)theEObject;
        T result = caseControlNode(controlNode);
        if (result == null) result = caseSequenceLinkEnd(controlNode);
        if (result == null) result = caseCapellaElement(controlNode);
        if (result == null) result = caseTraceableElement(controlNode);
        if (result == null) result = casePublishableElement(controlNode);
        if (result == null) result = caseModelElement(controlNode);
        if (result == null) result = caseExtensibleElement(controlNode);
        if (result == null) result = caseElement(controlNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case FaPackage.REFERENCE_HIERARCHY_CONTEXT: {
        ReferenceHierarchyContext referenceHierarchyContext = (ReferenceHierarchyContext)theEObject;
        T result = caseReferenceHierarchyContext(referenceHierarchyContext);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalArchitecture(AbstractFunctionalArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalBlock(AbstractFunctionalBlock object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionPkg(FunctionPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionSpecification(FunctionSpecification object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Category</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Category</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeCategory(ExchangeCategory object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeLink(ExchangeLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Containment</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Containment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeContainment(ExchangeContainment object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeSpecification(ExchangeSpecification object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Exchange Specification</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Exchange Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalExchangeSpecification(FunctionalExchangeSpecification object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChain(FunctionalChain object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Functional Chain Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionalChainContainer(AbstractFunctionalChainContainer object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainInvolvement(FunctionalChainInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainReference(FunctionalChainReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Input Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Input Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionInputPort(FunctionInputPort object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Output Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Output Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionOutputPort(FunctionOutputPort object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Function Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Function Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunctionAllocation(AbstractFunctionAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Functional Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Functional Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentFunctionalAllocation(ComponentFunctionalAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainRealization(FunctionalChainRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Specification Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Specification Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeSpecificationRealization(ExchangeSpecificationRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Exchange Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Exchange Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalExchangeRealization(FunctionalExchangeRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionRealization(FunctionRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Exchange</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Exchange</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalExchange(FunctionalExchange object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Function</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractFunction(AbstractFunction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Function Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionPort(FunctionPort object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchange(ComponentExchange object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeAllocation(ComponentExchangeAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange Allocator</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange Allocator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeAllocator(ComponentExchangeAllocator object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange Category</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange Category</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeCategory(ComponentExchangeCategory object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeEnd(ComponentExchangeEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange Functional Exchange Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange Functional Exchange Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeFunctionalExchangeAllocation(ComponentExchangeFunctionalExchangeAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Exchange Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Exchange Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentExchangeRealization(ComponentExchangeRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentPort(ComponentPort object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Port Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Port Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentPortAllocation(ComponentPortAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Port Allocation End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Port Allocation End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentPortAllocationEnd(ComponentPortAllocationEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Involvement Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Involvement Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainInvolvementLink(FunctionalChainInvolvementLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Sequence Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sequence Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSequenceLink(SequenceLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Sequence Link End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sequence Link End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSequenceLinkEnd(SequenceLinkEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Functional Chain Involvement Function</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Functional Chain Involvement Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFunctionalChainInvolvementFunction(FunctionalChainInvolvementFunction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Control Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Control Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseControlNode(ControlNode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Reference Hierarchy Context</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference Hierarchy Context</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseReferenceHierarchyContext(ReferenceHierarchyContext object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingArchitecture(ModellingArchitecture object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingBlock(ModellingBlock object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Relationship</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseNamedRelationship(NamedRelationship object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involver Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolverElement(InvolverElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvedElement(InvolvedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInvolvement(Involvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePort(Port object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypedElement(TypedElement object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAllocation(Allocation object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Event Operation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Event Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractEventOperation(AbstractEventOperation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseFeature(Feature object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multiplicity Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMultiplicityElement(MultiplicityElement object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseProperty(Property object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractInstance(AbstractInstance object) {
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

} //FaSwitch
