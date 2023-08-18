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
package org.polarsys.capella.core.data.cs.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralClass;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.*;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecturePkg;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLinkRealization;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathRealization;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.cs.PhysicalPortRealization;
import org.polarsys.capella.core.data.cs.ProvidedInterfaceLink;
import org.polarsys.capella.core.data.cs.RequiredInterfaceLink;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.MessageReferencePkg;
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
 * @see org.polarsys.capella.core.data.cs.CsPackage
 * @generated
 */
public class CsSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CsPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CsSwitch() {
    if (modelPackage == null) {
      modelPackage = CsPackage.eINSTANCE;
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
      case CsPackage.BLOCK_ARCHITECTURE_PKG: {
        BlockArchitecturePkg blockArchitecturePkg = (BlockArchitecturePkg)theEObject;
        T result = caseBlockArchitecturePkg(blockArchitecturePkg);
        if (result == null) result = caseModellingArchitecturePkg(blockArchitecturePkg);
        if (result == null) result = caseStructure(blockArchitecturePkg);
        if (result == null) result = caseNamespace(blockArchitecturePkg);
        if (result == null) result = caseNamedElement(blockArchitecturePkg);
        if (result == null) result = caseAbstractNamedElement(blockArchitecturePkg);
        if (result == null) result = caseCapellaElement(blockArchitecturePkg);
        if (result == null) result = caseTraceableElement(blockArchitecturePkg);
        if (result == null) result = casePublishableElement(blockArchitecturePkg);
        if (result == null) result = caseModelElement(blockArchitecturePkg);
        if (result == null) result = caseExtensibleElement(blockArchitecturePkg);
        if (result == null) result = caseElement(blockArchitecturePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.BLOCK_ARCHITECTURE: {
        BlockArchitecture blockArchitecture = (BlockArchitecture)theEObject;
        T result = caseBlockArchitecture(blockArchitecture);
        if (result == null) result = caseAbstractFunctionalArchitecture(blockArchitecture);
        if (result == null) result = caseModellingArchitecture(blockArchitecture);
        if (result == null) result = caseStructure(blockArchitecture);
        if (result == null) result = caseNamespace(blockArchitecture);
        if (result == null) result = caseNamedElement(blockArchitecture);
        if (result == null) result = caseAbstractNamedElement(blockArchitecture);
        if (result == null) result = caseCapellaElement(blockArchitecture);
        if (result == null) result = caseTraceableElement(blockArchitecture);
        if (result == null) result = casePublishableElement(blockArchitecture);
        if (result == null) result = caseModelElement(blockArchitecture);
        if (result == null) result = caseExtensibleElement(blockArchitecture);
        if (result == null) result = caseElement(blockArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.BLOCK: {
        Block block = (Block)theEObject;
        T result = caseBlock(block);
        if (result == null) result = caseAbstractFunctionalBlock(block);
        if (result == null) result = caseModellingBlock(block);
        if (result == null) result = caseType(block);
        if (result == null) result = caseAbstractType(block);
        if (result == null) result = caseNamespace(block);
        if (result == null) result = caseNamedElement(block);
        if (result == null) result = caseAbstractNamedElement(block);
        if (result == null) result = caseCapellaElement(block);
        if (result == null) result = caseExtensibleElement(block);
        if (result == null) result = caseTraceableElement(block);
        if (result == null) result = casePublishableElement(block);
        if (result == null) result = caseModelElement(block);
        if (result == null) result = caseElement(block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.COMPONENT_ARCHITECTURE: {
        ComponentArchitecture componentArchitecture = (ComponentArchitecture)theEObject;
        T result = caseComponentArchitecture(componentArchitecture);
        if (result == null) result = caseBlockArchitecture(componentArchitecture);
        if (result == null) result = caseAbstractFunctionalArchitecture(componentArchitecture);
        if (result == null) result = caseModellingArchitecture(componentArchitecture);
        if (result == null) result = caseStructure(componentArchitecture);
        if (result == null) result = caseNamespace(componentArchitecture);
        if (result == null) result = caseNamedElement(componentArchitecture);
        if (result == null) result = caseAbstractNamedElement(componentArchitecture);
        if (result == null) result = caseCapellaElement(componentArchitecture);
        if (result == null) result = caseTraceableElement(componentArchitecture);
        if (result == null) result = casePublishableElement(componentArchitecture);
        if (result == null) result = caseModelElement(componentArchitecture);
        if (result == null) result = caseExtensibleElement(componentArchitecture);
        if (result == null) result = caseElement(componentArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.COMPONENT: {
        Component component = (Component)theEObject;
        T result = caseComponent(component);
        if (result == null) result = caseBlock(component);
        if (result == null) result = caseClassifier(component);
        if (result == null) result = caseInterfaceAllocator(component);
        if (result == null) result = caseCommunicationLinkExchanger(component);
        if (result == null) result = caseAbstractFunctionalBlock(component);
        if (result == null) result = caseGeneralizableElement(component);
        if (result == null) result = caseModellingBlock(component);
        if (result == null) result = caseType(component);
        if (result == null) result = caseAbstractType(component);
        if (result == null) result = caseNamespace(component);
        if (result == null) result = caseNamedElement(component);
        if (result == null) result = caseAbstractNamedElement(component);
        if (result == null) result = caseCapellaElement(component);
        if (result == null) result = caseExtensibleElement(component);
        if (result == null) result = caseTraceableElement(component);
        if (result == null) result = casePublishableElement(component);
        if (result == null) result = caseModelElement(component);
        if (result == null) result = caseElement(component);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PART: {
        Part part = (Part)theEObject;
        T result = casePart(part);
        if (result == null) result = caseAbstractInstance(part);
        if (result == null) result = caseInformationsExchanger(part);
        if (result == null) result = caseDeployableElement(part);
        if (result == null) result = caseDeploymentTarget(part);
        if (result == null) result = caseAbstractPathInvolvedElement(part);
        if (result == null) result = caseProperty(part);
        if (result == null) result = caseInvolvedElement(part);
        if (result == null) result = caseFeature(part);
        if (result == null) result = caseTypedElement(part);
        if (result == null) result = caseMultiplicityElement(part);
        if (result == null) result = caseFinalizableElement(part);
        if (result == null) result = caseNamedElement(part);
        if (result == null) result = caseAbstractTypedElement(part);
        if (result == null) result = caseAbstractNamedElement(part);
        if (result == null) result = caseCapellaElement(part);
        if (result == null) result = caseTraceableElement(part);
        if (result == null) result = casePublishableElement(part);
        if (result == null) result = caseModelElement(part);
        if (result == null) result = caseExtensibleElement(part);
        if (result == null) result = caseElement(part);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ARCHITECTURE_ALLOCATION: {
        ArchitectureAllocation architectureAllocation = (ArchitectureAllocation)theEObject;
        T result = caseArchitectureAllocation(architectureAllocation);
        if (result == null) result = caseAllocation(architectureAllocation);
        if (result == null) result = caseRelationship(architectureAllocation);
        if (result == null) result = caseAbstractTrace(architectureAllocation);
        if (result == null) result = caseAbstractRelationship(architectureAllocation);
        if (result == null) result = caseCapellaElement(architectureAllocation);
        if (result == null) result = caseTraceableElement(architectureAllocation);
        if (result == null) result = casePublishableElement(architectureAllocation);
        if (result == null) result = caseModelElement(architectureAllocation);
        if (result == null) result = caseExtensibleElement(architectureAllocation);
        if (result == null) result = caseElement(architectureAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.COMPONENT_REALIZATION: {
        ComponentRealization componentRealization = (ComponentRealization)theEObject;
        T result = caseComponentRealization(componentRealization);
        if (result == null) result = caseAllocation(componentRealization);
        if (result == null) result = caseRelationship(componentRealization);
        if (result == null) result = caseAbstractTrace(componentRealization);
        if (result == null) result = caseAbstractRelationship(componentRealization);
        if (result == null) result = caseCapellaElement(componentRealization);
        if (result == null) result = caseTraceableElement(componentRealization);
        if (result == null) result = casePublishableElement(componentRealization);
        if (result == null) result = caseModelElement(componentRealization);
        if (result == null) result = caseExtensibleElement(componentRealization);
        if (result == null) result = caseElement(componentRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE_PKG: {
        InterfacePkg interfacePkg = (InterfacePkg)theEObject;
        T result = caseInterfacePkg(interfacePkg);
        if (result == null) result = caseMessageReferencePkg(interfacePkg);
        if (result == null) result = caseAbstractDependenciesPkg(interfacePkg);
        if (result == null) result = caseAbstractExchangeItemPkg(interfacePkg);
        if (result == null) result = caseStructure(interfacePkg);
        if (result == null) result = caseNamespace(interfacePkg);
        if (result == null) result = caseNamedElement(interfacePkg);
        if (result == null) result = caseAbstractNamedElement(interfacePkg);
        if (result == null) result = caseCapellaElement(interfacePkg);
        if (result == null) result = caseTraceableElement(interfacePkg);
        if (result == null) result = casePublishableElement(interfacePkg);
        if (result == null) result = caseModelElement(interfacePkg);
        if (result == null) result = caseExtensibleElement(interfacePkg);
        if (result == null) result = caseElement(interfacePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE: {
        Interface interface_ = (Interface)theEObject;
        T result = caseInterface(interface_);
        if (result == null) result = caseGeneralClass(interface_);
        if (result == null) result = caseInterfaceAllocator(interface_);
        if (result == null) result = caseClassifier(interface_);
        if (result == null) result = caseFinalizableElement(interface_);
        if (result == null) result = caseGeneralizableElement(interface_);
        if (result == null) result = caseType(interface_);
        if (result == null) result = caseAbstractType(interface_);
        if (result == null) result = caseNamespace(interface_);
        if (result == null) result = caseNamedElement(interface_);
        if (result == null) result = caseAbstractNamedElement(interface_);
        if (result == null) result = caseCapellaElement(interface_);
        if (result == null) result = caseExtensibleElement(interface_);
        if (result == null) result = caseTraceableElement(interface_);
        if (result == null) result = casePublishableElement(interface_);
        if (result == null) result = caseModelElement(interface_);
        if (result == null) result = caseElement(interface_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE_IMPLEMENTATION: {
        InterfaceImplementation interfaceImplementation = (InterfaceImplementation)theEObject;
        T result = caseInterfaceImplementation(interfaceImplementation);
        if (result == null) result = caseRelationship(interfaceImplementation);
        if (result == null) result = caseAbstractRelationship(interfaceImplementation);
        if (result == null) result = caseCapellaElement(interfaceImplementation);
        if (result == null) result = caseTraceableElement(interfaceImplementation);
        if (result == null) result = casePublishableElement(interfaceImplementation);
        if (result == null) result = caseModelElement(interfaceImplementation);
        if (result == null) result = caseExtensibleElement(interfaceImplementation);
        if (result == null) result = caseElement(interfaceImplementation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE_USE: {
        InterfaceUse interfaceUse = (InterfaceUse)theEObject;
        T result = caseInterfaceUse(interfaceUse);
        if (result == null) result = caseRelationship(interfaceUse);
        if (result == null) result = caseAbstractRelationship(interfaceUse);
        if (result == null) result = caseCapellaElement(interfaceUse);
        if (result == null) result = caseTraceableElement(interfaceUse);
        if (result == null) result = casePublishableElement(interfaceUse);
        if (result == null) result = caseModelElement(interfaceUse);
        if (result == null) result = caseExtensibleElement(interfaceUse);
        if (result == null) result = caseElement(interfaceUse);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PROVIDED_INTERFACE_LINK: {
        ProvidedInterfaceLink providedInterfaceLink = (ProvidedInterfaceLink)theEObject;
        T result = caseProvidedInterfaceLink(providedInterfaceLink);
        if (result == null) result = caseRelationship(providedInterfaceLink);
        if (result == null) result = caseAbstractRelationship(providedInterfaceLink);
        if (result == null) result = caseCapellaElement(providedInterfaceLink);
        if (result == null) result = caseTraceableElement(providedInterfaceLink);
        if (result == null) result = casePublishableElement(providedInterfaceLink);
        if (result == null) result = caseModelElement(providedInterfaceLink);
        if (result == null) result = caseExtensibleElement(providedInterfaceLink);
        if (result == null) result = caseElement(providedInterfaceLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.REQUIRED_INTERFACE_LINK: {
        RequiredInterfaceLink requiredInterfaceLink = (RequiredInterfaceLink)theEObject;
        T result = caseRequiredInterfaceLink(requiredInterfaceLink);
        if (result == null) result = caseRelationship(requiredInterfaceLink);
        if (result == null) result = caseAbstractRelationship(requiredInterfaceLink);
        if (result == null) result = caseCapellaElement(requiredInterfaceLink);
        if (result == null) result = caseTraceableElement(requiredInterfaceLink);
        if (result == null) result = casePublishableElement(requiredInterfaceLink);
        if (result == null) result = caseModelElement(requiredInterfaceLink);
        if (result == null) result = caseExtensibleElement(requiredInterfaceLink);
        if (result == null) result = caseElement(requiredInterfaceLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE_ALLOCATION: {
        InterfaceAllocation interfaceAllocation = (InterfaceAllocation)theEObject;
        T result = caseInterfaceAllocation(interfaceAllocation);
        if (result == null) result = caseAllocation(interfaceAllocation);
        if (result == null) result = caseRelationship(interfaceAllocation);
        if (result == null) result = caseAbstractTrace(interfaceAllocation);
        if (result == null) result = caseAbstractRelationship(interfaceAllocation);
        if (result == null) result = caseCapellaElement(interfaceAllocation);
        if (result == null) result = caseTraceableElement(interfaceAllocation);
        if (result == null) result = casePublishableElement(interfaceAllocation);
        if (result == null) result = caseModelElement(interfaceAllocation);
        if (result == null) result = caseExtensibleElement(interfaceAllocation);
        if (result == null) result = caseElement(interfaceAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.INTERFACE_ALLOCATOR: {
        InterfaceAllocator interfaceAllocator = (InterfaceAllocator)theEObject;
        T result = caseInterfaceAllocator(interfaceAllocator);
        if (result == null) result = caseCapellaElement(interfaceAllocator);
        if (result == null) result = caseTraceableElement(interfaceAllocator);
        if (result == null) result = casePublishableElement(interfaceAllocator);
        if (result == null) result = caseModelElement(interfaceAllocator);
        if (result == null) result = caseExtensibleElement(interfaceAllocator);
        if (result == null) result = caseElement(interfaceAllocator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.EXCHANGE_ITEM_ALLOCATION: {
        ExchangeItemAllocation exchangeItemAllocation = (ExchangeItemAllocation)theEObject;
        T result = caseExchangeItemAllocation(exchangeItemAllocation);
        if (result == null) result = caseRelationship(exchangeItemAllocation);
        if (result == null) result = caseAbstractEventOperation(exchangeItemAllocation);
        if (result == null) result = caseFinalizableElement(exchangeItemAllocation);
        if (result == null) result = caseAbstractRelationship(exchangeItemAllocation);
        if (result == null) result = caseNamedElement(exchangeItemAllocation);
        if (result == null) result = caseCapellaElement(exchangeItemAllocation);
        if (result == null) result = caseTraceableElement(exchangeItemAllocation);
        if (result == null) result = casePublishableElement(exchangeItemAllocation);
        if (result == null) result = caseAbstractNamedElement(exchangeItemAllocation);
        if (result == null) result = caseModelElement(exchangeItemAllocation);
        if (result == null) result = caseExtensibleElement(exchangeItemAllocation);
        if (result == null) result = caseElement(exchangeItemAllocation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.DEPLOYABLE_ELEMENT: {
        DeployableElement deployableElement = (DeployableElement)theEObject;
        T result = caseDeployableElement(deployableElement);
        if (result == null) result = caseNamedElement(deployableElement);
        if (result == null) result = caseAbstractNamedElement(deployableElement);
        if (result == null) result = caseCapellaElement(deployableElement);
        if (result == null) result = caseTraceableElement(deployableElement);
        if (result == null) result = casePublishableElement(deployableElement);
        if (result == null) result = caseModelElement(deployableElement);
        if (result == null) result = caseExtensibleElement(deployableElement);
        if (result == null) result = caseElement(deployableElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.DEPLOYMENT_TARGET: {
        DeploymentTarget deploymentTarget = (DeploymentTarget)theEObject;
        T result = caseDeploymentTarget(deploymentTarget);
        if (result == null) result = caseNamedElement(deploymentTarget);
        if (result == null) result = caseAbstractNamedElement(deploymentTarget);
        if (result == null) result = caseCapellaElement(deploymentTarget);
        if (result == null) result = caseTraceableElement(deploymentTarget);
        if (result == null) result = casePublishableElement(deploymentTarget);
        if (result == null) result = caseModelElement(deploymentTarget);
        if (result == null) result = caseExtensibleElement(deploymentTarget);
        if (result == null) result = caseElement(deploymentTarget);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ABSTRACT_DEPLOYMENT_LINK: {
        AbstractDeploymentLink abstractDeploymentLink = (AbstractDeploymentLink)theEObject;
        T result = caseAbstractDeploymentLink(abstractDeploymentLink);
        if (result == null) result = caseRelationship(abstractDeploymentLink);
        if (result == null) result = caseAbstractRelationship(abstractDeploymentLink);
        if (result == null) result = caseCapellaElement(abstractDeploymentLink);
        if (result == null) result = caseTraceableElement(abstractDeploymentLink);
        if (result == null) result = casePublishableElement(abstractDeploymentLink);
        if (result == null) result = caseModelElement(abstractDeploymentLink);
        if (result == null) result = caseExtensibleElement(abstractDeploymentLink);
        if (result == null) result = caseElement(abstractDeploymentLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ABSTRACT_PATH_INVOLVED_ELEMENT: {
        AbstractPathInvolvedElement abstractPathInvolvedElement = (AbstractPathInvolvedElement)theEObject;
        T result = caseAbstractPathInvolvedElement(abstractPathInvolvedElement);
        if (result == null) result = caseInvolvedElement(abstractPathInvolvedElement);
        if (result == null) result = caseCapellaElement(abstractPathInvolvedElement);
        if (result == null) result = caseTraceableElement(abstractPathInvolvedElement);
        if (result == null) result = casePublishableElement(abstractPathInvolvedElement);
        if (result == null) result = caseModelElement(abstractPathInvolvedElement);
        if (result == null) result = caseExtensibleElement(abstractPathInvolvedElement);
        if (result == null) result = caseElement(abstractPathInvolvedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ABSTRACT_PHYSICAL_ARTIFACT: {
        AbstractPhysicalArtifact abstractPhysicalArtifact = (AbstractPhysicalArtifact)theEObject;
        T result = caseAbstractPhysicalArtifact(abstractPhysicalArtifact);
        if (result == null) result = caseCapellaElement(abstractPhysicalArtifact);
        if (result == null) result = caseTraceableElement(abstractPhysicalArtifact);
        if (result == null) result = casePublishableElement(abstractPhysicalArtifact);
        if (result == null) result = caseModelElement(abstractPhysicalArtifact);
        if (result == null) result = caseExtensibleElement(abstractPhysicalArtifact);
        if (result == null) result = caseElement(abstractPhysicalArtifact);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ABSTRACT_PHYSICAL_LINK_END: {
        AbstractPhysicalLinkEnd abstractPhysicalLinkEnd = (AbstractPhysicalLinkEnd)theEObject;
        T result = caseAbstractPhysicalLinkEnd(abstractPhysicalLinkEnd);
        if (result == null) result = caseCapellaElement(abstractPhysicalLinkEnd);
        if (result == null) result = caseTraceableElement(abstractPhysicalLinkEnd);
        if (result == null) result = casePublishableElement(abstractPhysicalLinkEnd);
        if (result == null) result = caseModelElement(abstractPhysicalLinkEnd);
        if (result == null) result = caseExtensibleElement(abstractPhysicalLinkEnd);
        if (result == null) result = caseElement(abstractPhysicalLinkEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.ABSTRACT_PHYSICAL_PATH_LINK: {
        AbstractPhysicalPathLink abstractPhysicalPathLink = (AbstractPhysicalPathLink)theEObject;
        T result = caseAbstractPhysicalPathLink(abstractPhysicalPathLink);
        if (result == null) result = caseComponentExchangeAllocator(abstractPhysicalPathLink);
        if (result == null) result = caseNamedElement(abstractPhysicalPathLink);
        if (result == null) result = caseAbstractNamedElement(abstractPhysicalPathLink);
        if (result == null) result = caseCapellaElement(abstractPhysicalPathLink);
        if (result == null) result = caseTraceableElement(abstractPhysicalPathLink);
        if (result == null) result = casePublishableElement(abstractPhysicalPathLink);
        if (result == null) result = caseModelElement(abstractPhysicalPathLink);
        if (result == null) result = caseExtensibleElement(abstractPhysicalPathLink);
        if (result == null) result = caseElement(abstractPhysicalPathLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_LINK: {
        PhysicalLink physicalLink = (PhysicalLink)theEObject;
        T result = casePhysicalLink(physicalLink);
        if (result == null) result = caseAbstractPhysicalPathLink(physicalLink);
        if (result == null) result = caseAbstractPhysicalArtifact(physicalLink);
        if (result == null) result = caseAbstractPathInvolvedElement(physicalLink);
        if (result == null) result = caseComponentExchangeAllocator(physicalLink);
        if (result == null) result = caseInvolvedElement(physicalLink);
        if (result == null) result = caseNamedElement(physicalLink);
        if (result == null) result = caseAbstractNamedElement(physicalLink);
        if (result == null) result = caseCapellaElement(physicalLink);
        if (result == null) result = caseTraceableElement(physicalLink);
        if (result == null) result = casePublishableElement(physicalLink);
        if (result == null) result = caseModelElement(physicalLink);
        if (result == null) result = caseExtensibleElement(physicalLink);
        if (result == null) result = caseElement(physicalLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_LINK_CATEGORY: {
        PhysicalLinkCategory physicalLinkCategory = (PhysicalLinkCategory)theEObject;
        T result = casePhysicalLinkCategory(physicalLinkCategory);
        if (result == null) result = caseNamedElement(physicalLinkCategory);
        if (result == null) result = caseAbstractNamedElement(physicalLinkCategory);
        if (result == null) result = caseCapellaElement(physicalLinkCategory);
        if (result == null) result = caseTraceableElement(physicalLinkCategory);
        if (result == null) result = casePublishableElement(physicalLinkCategory);
        if (result == null) result = caseModelElement(physicalLinkCategory);
        if (result == null) result = caseExtensibleElement(physicalLinkCategory);
        if (result == null) result = caseElement(physicalLinkCategory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_LINK_END: {
        PhysicalLinkEnd physicalLinkEnd = (PhysicalLinkEnd)theEObject;
        T result = casePhysicalLinkEnd(physicalLinkEnd);
        if (result == null) result = caseAbstractPhysicalLinkEnd(physicalLinkEnd);
        if (result == null) result = caseCapellaElement(physicalLinkEnd);
        if (result == null) result = caseTraceableElement(physicalLinkEnd);
        if (result == null) result = casePublishableElement(physicalLinkEnd);
        if (result == null) result = caseModelElement(physicalLinkEnd);
        if (result == null) result = caseExtensibleElement(physicalLinkEnd);
        if (result == null) result = caseElement(physicalLinkEnd);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_LINK_REALIZATION: {
        PhysicalLinkRealization physicalLinkRealization = (PhysicalLinkRealization)theEObject;
        T result = casePhysicalLinkRealization(physicalLinkRealization);
        if (result == null) result = caseAllocation(physicalLinkRealization);
        if (result == null) result = caseRelationship(physicalLinkRealization);
        if (result == null) result = caseAbstractTrace(physicalLinkRealization);
        if (result == null) result = caseAbstractRelationship(physicalLinkRealization);
        if (result == null) result = caseCapellaElement(physicalLinkRealization);
        if (result == null) result = caseTraceableElement(physicalLinkRealization);
        if (result == null) result = casePublishableElement(physicalLinkRealization);
        if (result == null) result = caseModelElement(physicalLinkRealization);
        if (result == null) result = caseExtensibleElement(physicalLinkRealization);
        if (result == null) result = caseElement(physicalLinkRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PATH: {
        PhysicalPath physicalPath = (PhysicalPath)theEObject;
        T result = casePhysicalPath(physicalPath);
        if (result == null) result = caseComponentExchangeAllocator(physicalPath);
        if (result == null) result = caseAbstractPathInvolvedElement(physicalPath);
        if (result == null) result = caseInvolverElement(physicalPath);
        if (result == null) result = caseNamedElement(physicalPath);
        if (result == null) result = caseAbstractNamedElement(physicalPath);
        if (result == null) result = caseInvolvedElement(physicalPath);
        if (result == null) result = caseCapellaElement(physicalPath);
        if (result == null) result = caseTraceableElement(physicalPath);
        if (result == null) result = casePublishableElement(physicalPath);
        if (result == null) result = caseModelElement(physicalPath);
        if (result == null) result = caseExtensibleElement(physicalPath);
        if (result == null) result = caseElement(physicalPath);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PATH_INVOLVEMENT: {
        PhysicalPathInvolvement physicalPathInvolvement = (PhysicalPathInvolvement)theEObject;
        T result = casePhysicalPathInvolvement(physicalPathInvolvement);
        if (result == null) result = caseInvolvement(physicalPathInvolvement);
        if (result == null) result = caseRelationship(physicalPathInvolvement);
        if (result == null) result = caseAbstractRelationship(physicalPathInvolvement);
        if (result == null) result = caseCapellaElement(physicalPathInvolvement);
        if (result == null) result = caseTraceableElement(physicalPathInvolvement);
        if (result == null) result = casePublishableElement(physicalPathInvolvement);
        if (result == null) result = caseModelElement(physicalPathInvolvement);
        if (result == null) result = caseExtensibleElement(physicalPathInvolvement);
        if (result == null) result = caseElement(physicalPathInvolvement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PATH_REFERENCE: {
        PhysicalPathReference physicalPathReference = (PhysicalPathReference)theEObject;
        T result = casePhysicalPathReference(physicalPathReference);
        if (result == null) result = casePhysicalPathInvolvement(physicalPathReference);
        if (result == null) result = caseInvolvement(physicalPathReference);
        if (result == null) result = caseRelationship(physicalPathReference);
        if (result == null) result = caseAbstractRelationship(physicalPathReference);
        if (result == null) result = caseCapellaElement(physicalPathReference);
        if (result == null) result = caseTraceableElement(physicalPathReference);
        if (result == null) result = casePublishableElement(physicalPathReference);
        if (result == null) result = caseModelElement(physicalPathReference);
        if (result == null) result = caseExtensibleElement(physicalPathReference);
        if (result == null) result = caseElement(physicalPathReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PATH_REALIZATION: {
        PhysicalPathRealization physicalPathRealization = (PhysicalPathRealization)theEObject;
        T result = casePhysicalPathRealization(physicalPathRealization);
        if (result == null) result = caseAllocation(physicalPathRealization);
        if (result == null) result = caseRelationship(physicalPathRealization);
        if (result == null) result = caseAbstractTrace(physicalPathRealization);
        if (result == null) result = caseAbstractRelationship(physicalPathRealization);
        if (result == null) result = caseCapellaElement(physicalPathRealization);
        if (result == null) result = caseTraceableElement(physicalPathRealization);
        if (result == null) result = casePublishableElement(physicalPathRealization);
        if (result == null) result = caseModelElement(physicalPathRealization);
        if (result == null) result = caseExtensibleElement(physicalPathRealization);
        if (result == null) result = caseElement(physicalPathRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PORT: {
        PhysicalPort physicalPort = (PhysicalPort)theEObject;
        T result = casePhysicalPort(physicalPort);
        if (result == null) result = casePort(physicalPort);
        if (result == null) result = caseAbstractPhysicalArtifact(physicalPort);
        if (result == null) result = caseInformationsExchanger(physicalPort);
        if (result == null) result = caseAbstractPhysicalLinkEnd(physicalPort);
        if (result == null) result = caseProperty(physicalPort);
        if (result == null) result = caseFeature(physicalPort);
        if (result == null) result = caseTypedElement(physicalPort);
        if (result == null) result = caseMultiplicityElement(physicalPort);
        if (result == null) result = caseFinalizableElement(physicalPort);
        if (result == null) result = caseNamedElement(physicalPort);
        if (result == null) result = caseCapellaElement(physicalPort);
        if (result == null) result = caseAbstractTypedElement(physicalPort);
        if (result == null) result = caseAbstractNamedElement(physicalPort);
        if (result == null) result = caseTraceableElement(physicalPort);
        if (result == null) result = casePublishableElement(physicalPort);
        if (result == null) result = caseModelElement(physicalPort);
        if (result == null) result = caseExtensibleElement(physicalPort);
        if (result == null) result = caseElement(physicalPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.PHYSICAL_PORT_REALIZATION: {
        PhysicalPortRealization physicalPortRealization = (PhysicalPortRealization)theEObject;
        T result = casePhysicalPortRealization(physicalPortRealization);
        if (result == null) result = caseAllocation(physicalPortRealization);
        if (result == null) result = caseRelationship(physicalPortRealization);
        if (result == null) result = caseAbstractTrace(physicalPortRealization);
        if (result == null) result = caseAbstractRelationship(physicalPortRealization);
        if (result == null) result = caseCapellaElement(physicalPortRealization);
        if (result == null) result = caseTraceableElement(physicalPortRealization);
        if (result == null) result = casePublishableElement(physicalPortRealization);
        if (result == null) result = caseModelElement(physicalPortRealization);
        if (result == null) result = caseExtensibleElement(physicalPortRealization);
        if (result == null) result = caseElement(physicalPortRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CsPackage.COMPONENT_PKG: {
        ComponentPkg componentPkg = (ComponentPkg)theEObject;
        T result = caseComponentPkg(componentPkg);
        if (result == null) result = caseStructure(componentPkg);
        if (result == null) result = caseNamespace(componentPkg);
        if (result == null) result = caseNamedElement(componentPkg);
        if (result == null) result = caseAbstractNamedElement(componentPkg);
        if (result == null) result = caseCapellaElement(componentPkg);
        if (result == null) result = caseTraceableElement(componentPkg);
        if (result == null) result = casePublishableElement(componentPkg);
        if (result == null) result = caseModelElement(componentPkg);
        if (result == null) result = caseExtensibleElement(componentPkg);
        if (result == null) result = caseElement(componentPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Block Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block Architecture Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBlockArchitecturePkg(BlockArchitecturePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Block Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBlockArchitecture(BlockArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Block</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseBlock(Block object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentArchitecture(ComponentArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponent(Component object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Part</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePart(Part object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Architecture Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Architecture Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseArchitectureAllocation(ArchitectureAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentRealization(ComponentRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfacePkg(InterfacePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterface(Interface object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Implementation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Implementation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfaceImplementation(InterfaceImplementation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Use</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Use</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfaceUse(InterfaceUse object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Provided Interface Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Provided Interface Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseProvidedInterfaceLink(ProvidedInterfaceLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Required Interface Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Required Interface Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseRequiredInterfaceLink(RequiredInterfaceLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfaceAllocation(InterfaceAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Interface Allocator</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interface Allocator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInterfaceAllocator(InterfaceAllocator object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Exchange Item Allocation</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exchange Item Allocation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseExchangeItemAllocation(ExchangeItemAllocation object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Deployable Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Deployable Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeployableElement(DeployableElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Deployment Target</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Deployment Target</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeploymentTarget(DeploymentTarget object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Deployment Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractDeploymentLink(AbstractDeploymentLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Path Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Path Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPathInvolvedElement(AbstractPathInvolvedElement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Physical Artifact</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Physical Artifact</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPhysicalArtifact(AbstractPhysicalArtifact object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Physical Link End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Physical Link End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPhysicalLinkEnd(AbstractPhysicalLinkEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Physical Path Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Physical Path Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPhysicalPathLink(AbstractPhysicalPathLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalLink(PhysicalLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Link Category</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Link Category</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalLinkCategory(PhysicalLinkCategory object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Link End</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Link End</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalLinkEnd(PhysicalLinkEnd object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Link Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Link Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalLinkRealization(PhysicalLinkRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Path</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Path</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPath(PhysicalPath object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Path Involvement</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Path Involvement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPathInvolvement(PhysicalPathInvolvement object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Path Reference</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Path Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPathReference(PhysicalPathReference object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Path Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Path Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPathRealization(PhysicalPathRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Port</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPort(PhysicalPort object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Port Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Port Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalPortRealization(PhysicalPortRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentPkg(ComponentPkg object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Modelling Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modelling Architecture Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseModellingArchitecturePkg(ModellingArchitecturePkg object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseClassifier(Classifier object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Link Exchanger</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link Exchanger</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCommunicationLinkExchanger(CommunicationLinkExchanger object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Message Reference Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Message Reference Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseMessageReferencePkg(MessageReferencePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Dependencies Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Dependencies Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractDependenciesPkg(AbstractDependenciesPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Exchange Item Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Exchange Item Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractExchangeItemPkg(AbstractExchangeItemPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>General Class</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>General Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseGeneralClass(GeneralClass object) {
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

} //CsSwitch
