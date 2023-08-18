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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.cs.CsPackage
 * @generated
 */
public class CsAdapterFactory extends AdapterFactoryImpl {
	/**
   * The cached model package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static CsPackage modelPackage;

	/**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CsAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = CsPackage.eINSTANCE;
    }
  }

	/**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

	/**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CsSwitch<Adapter> modelSwitch =
		new CsSwitch<Adapter>() {
      @Override
      public Adapter caseBlockArchitecturePkg(BlockArchitecturePkg object) {
        return createBlockArchitecturePkgAdapter();
      }
      @Override
      public Adapter caseBlockArchitecture(BlockArchitecture object) {
        return createBlockArchitectureAdapter();
      }
      @Override
      public Adapter caseBlock(Block object) {
        return createBlockAdapter();
      }
      @Override
      public Adapter caseComponentArchitecture(ComponentArchitecture object) {
        return createComponentArchitectureAdapter();
      }
      @Override
      public Adapter caseComponent(Component object) {
        return createComponentAdapter();
      }
      @Override
      public Adapter casePart(Part object) {
        return createPartAdapter();
      }
      @Override
      public Adapter caseArchitectureAllocation(ArchitectureAllocation object) {
        return createArchitectureAllocationAdapter();
      }
      @Override
      public Adapter caseComponentRealization(ComponentRealization object) {
        return createComponentRealizationAdapter();
      }
      @Override
      public Adapter caseInterfacePkg(InterfacePkg object) {
        return createInterfacePkgAdapter();
      }
      @Override
      public Adapter caseInterface(Interface object) {
        return createInterfaceAdapter();
      }
      @Override
      public Adapter caseInterfaceImplementation(InterfaceImplementation object) {
        return createInterfaceImplementationAdapter();
      }
      @Override
      public Adapter caseInterfaceUse(InterfaceUse object) {
        return createInterfaceUseAdapter();
      }
      @Override
      public Adapter caseProvidedInterfaceLink(ProvidedInterfaceLink object) {
        return createProvidedInterfaceLinkAdapter();
      }
      @Override
      public Adapter caseRequiredInterfaceLink(RequiredInterfaceLink object) {
        return createRequiredInterfaceLinkAdapter();
      }
      @Override
      public Adapter caseInterfaceAllocation(InterfaceAllocation object) {
        return createInterfaceAllocationAdapter();
      }
      @Override
      public Adapter caseInterfaceAllocator(InterfaceAllocator object) {
        return createInterfaceAllocatorAdapter();
      }
      @Override
      public Adapter caseExchangeItemAllocation(ExchangeItemAllocation object) {
        return createExchangeItemAllocationAdapter();
      }
      @Override
      public Adapter caseDeployableElement(DeployableElement object) {
        return createDeployableElementAdapter();
      }
      @Override
      public Adapter caseDeploymentTarget(DeploymentTarget object) {
        return createDeploymentTargetAdapter();
      }
      @Override
      public Adapter caseAbstractDeploymentLink(AbstractDeploymentLink object) {
        return createAbstractDeploymentLinkAdapter();
      }
      @Override
      public Adapter caseAbstractPathInvolvedElement(AbstractPathInvolvedElement object) {
        return createAbstractPathInvolvedElementAdapter();
      }
      @Override
      public Adapter caseAbstractPhysicalArtifact(AbstractPhysicalArtifact object) {
        return createAbstractPhysicalArtifactAdapter();
      }
      @Override
      public Adapter caseAbstractPhysicalLinkEnd(AbstractPhysicalLinkEnd object) {
        return createAbstractPhysicalLinkEndAdapter();
      }
      @Override
      public Adapter caseAbstractPhysicalPathLink(AbstractPhysicalPathLink object) {
        return createAbstractPhysicalPathLinkAdapter();
      }
      @Override
      public Adapter casePhysicalLink(PhysicalLink object) {
        return createPhysicalLinkAdapter();
      }
      @Override
      public Adapter casePhysicalLinkCategory(PhysicalLinkCategory object) {
        return createPhysicalLinkCategoryAdapter();
      }
      @Override
      public Adapter casePhysicalLinkEnd(PhysicalLinkEnd object) {
        return createPhysicalLinkEndAdapter();
      }
      @Override
      public Adapter casePhysicalLinkRealization(PhysicalLinkRealization object) {
        return createPhysicalLinkRealizationAdapter();
      }
      @Override
      public Adapter casePhysicalPath(PhysicalPath object) {
        return createPhysicalPathAdapter();
      }
      @Override
      public Adapter casePhysicalPathInvolvement(PhysicalPathInvolvement object) {
        return createPhysicalPathInvolvementAdapter();
      }
      @Override
      public Adapter casePhysicalPathReference(PhysicalPathReference object) {
        return createPhysicalPathReferenceAdapter();
      }
      @Override
      public Adapter casePhysicalPathRealization(PhysicalPathRealization object) {
        return createPhysicalPathRealizationAdapter();
      }
      @Override
      public Adapter casePhysicalPort(PhysicalPort object) {
        return createPhysicalPortAdapter();
      }
      @Override
      public Adapter casePhysicalPortRealization(PhysicalPortRealization object) {
        return createPhysicalPortRealizationAdapter();
      }
      @Override
      public Adapter caseComponentPkg(ComponentPkg object) {
        return createComponentPkgAdapter();
      }
      @Override
      public Adapter caseElement(Element object) {
        return createElementAdapter();
      }
      @Override
      public Adapter caseExtensibleElement(ExtensibleElement object) {
        return createExtensibleElementAdapter();
      }
      @Override
      public Adapter caseModelElement(ModelElement object) {
        return createModelElementAdapter();
      }
      @Override
      public Adapter caseAbstractNamedElement(AbstractNamedElement object) {
        return createAbstractNamedElementAdapter();
      }
      @Override
      public Adapter caseTraceableElement(TraceableElement object) {
        return createTraceableElementAdapter();
      }
      @Override
      public Adapter casePublishableElement(PublishableElement object) {
        return createPublishableElementAdapter();
      }
      @Override
      public Adapter caseCapellaElement(CapellaElement object) {
        return createCapellaElementAdapter();
      }
      @Override
      public Adapter caseNamedElement(NamedElement object) {
        return createNamedElementAdapter();
      }
      @Override
      public Adapter caseNamespace(Namespace object) {
        return createNamespaceAdapter();
      }
      @Override
      public Adapter caseStructure(Structure object) {
        return createStructureAdapter();
      }
      @Override
      public Adapter caseModellingArchitecturePkg(ModellingArchitecturePkg object) {
        return createModellingArchitecturePkgAdapter();
      }
      @Override
      public Adapter caseModellingArchitecture(ModellingArchitecture object) {
        return createModellingArchitectureAdapter();
      }
      @Override
      public Adapter caseAbstractFunctionalArchitecture(AbstractFunctionalArchitecture object) {
        return createAbstractFunctionalArchitectureAdapter();
      }
      @Override
      public Adapter caseAbstractType(AbstractType object) {
        return createAbstractTypeAdapter();
      }
      @Override
      public Adapter caseType(Type object) {
        return createTypeAdapter();
      }
      @Override
      public Adapter caseModellingBlock(ModellingBlock object) {
        return createModellingBlockAdapter();
      }
      @Override
      public Adapter caseAbstractFunctionalBlock(AbstractFunctionalBlock object) {
        return createAbstractFunctionalBlockAdapter();
      }
      @Override
      public Adapter caseGeneralizableElement(GeneralizableElement object) {
        return createGeneralizableElementAdapter();
      }
      @Override
      public Adapter caseClassifier(Classifier object) {
        return createClassifierAdapter();
      }
      @Override
      public Adapter caseCommunicationLinkExchanger(CommunicationLinkExchanger object) {
        return createCommunicationLinkExchangerAdapter();
      }
      @Override
      public Adapter caseFeature(Feature object) {
        return createFeatureAdapter();
      }
      @Override
      public Adapter caseAbstractTypedElement(AbstractTypedElement object) {
        return createAbstractTypedElementAdapter();
      }
      @Override
      public Adapter caseTypedElement(TypedElement object) {
        return createTypedElementAdapter();
      }
      @Override
      public Adapter caseMultiplicityElement(MultiplicityElement object) {
        return createMultiplicityElementAdapter();
      }
      @Override
      public Adapter caseFinalizableElement(FinalizableElement object) {
        return createFinalizableElementAdapter();
      }
      @Override
      public Adapter caseProperty(Property object) {
        return createPropertyAdapter();
      }
      @Override
      public Adapter caseAbstractInstance(AbstractInstance object) {
        return createAbstractInstanceAdapter();
      }
      @Override
      public Adapter caseInformationsExchanger(InformationsExchanger object) {
        return createInformationsExchangerAdapter();
      }
      @Override
      public Adapter caseInvolvedElement(InvolvedElement object) {
        return createInvolvedElementAdapter();
      }
      @Override
      public Adapter caseAbstractRelationship(AbstractRelationship object) {
        return createAbstractRelationshipAdapter();
      }
      @Override
      public Adapter caseRelationship(Relationship object) {
        return createRelationshipAdapter();
      }
      @Override
      public Adapter caseAbstractTrace(AbstractTrace object) {
        return createAbstractTraceAdapter();
      }
      @Override
      public Adapter caseAllocation(Allocation object) {
        return createAllocationAdapter();
      }
      @Override
      public Adapter caseMessageReferencePkg(MessageReferencePkg object) {
        return createMessageReferencePkgAdapter();
      }
      @Override
      public Adapter caseAbstractDependenciesPkg(AbstractDependenciesPkg object) {
        return createAbstractDependenciesPkgAdapter();
      }
      @Override
      public Adapter caseAbstractExchangeItemPkg(AbstractExchangeItemPkg object) {
        return createAbstractExchangeItemPkgAdapter();
      }
      @Override
      public Adapter caseGeneralClass(GeneralClass object) {
        return createGeneralClassAdapter();
      }
      @Override
      public Adapter caseAbstractEventOperation(AbstractEventOperation object) {
        return createAbstractEventOperationAdapter();
      }
      @Override
      public Adapter caseComponentExchangeAllocator(ComponentExchangeAllocator object) {
        return createComponentExchangeAllocatorAdapter();
      }
      @Override
      public Adapter caseInvolverElement(InvolverElement object) {
        return createInvolverElementAdapter();
      }
      @Override
      public Adapter caseInvolvement(Involvement object) {
        return createInvolvementAdapter();
      }
      @Override
      public Adapter casePort(Port object) {
        return createPortAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

	/**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
	@Override
	public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.BlockArchitecturePkg <em>Block Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecturePkg
   * @generated
   */
	public Adapter createBlockArchitecturePkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.BlockArchitecture <em>Block Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.BlockArchitecture
   * @generated
   */
	public Adapter createBlockArchitectureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.Block <em>Block</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.Block
   * @generated
   */
	public Adapter createBlockAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ComponentArchitecture <em>Component Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ComponentArchitecture
   * @generated
   */
	public Adapter createComponentArchitectureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.Component <em>Component</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.Component
   * @generated
   */
	public Adapter createComponentAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.Part <em>Part</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.Part
   * @generated
   */
	public Adapter createPartAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation <em>Architecture Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation
   * @generated
   */
	public Adapter createArchitectureAllocationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ComponentRealization <em>Component Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ComponentRealization
   * @generated
   */
	public Adapter createComponentRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.InterfacePkg <em>Interface Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.InterfacePkg
   * @generated
   */
	public Adapter createInterfacePkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.Interface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.Interface
   * @generated
   */
	public Adapter createInterfaceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.InterfaceImplementation <em>Interface Implementation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.InterfaceImplementation
   * @generated
   */
	public Adapter createInterfaceImplementationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.InterfaceUse <em>Interface Use</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.InterfaceUse
   * @generated
   */
	public Adapter createInterfaceUseAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ProvidedInterfaceLink <em>Provided Interface Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ProvidedInterfaceLink
   * @generated
   */
	public Adapter createProvidedInterfaceLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.RequiredInterfaceLink <em>Required Interface Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.RequiredInterfaceLink
   * @generated
   */
	public Adapter createRequiredInterfaceLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.InterfaceAllocation <em>Interface Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocation
   * @generated
   */
	public Adapter createInterfaceAllocationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.InterfaceAllocator <em>Interface Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.InterfaceAllocator
   * @generated
   */
	public Adapter createInterfaceAllocatorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ExchangeItemAllocation <em>Exchange Item Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ExchangeItemAllocation
   * @generated
   */
	public Adapter createExchangeItemAllocationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.DeployableElement <em>Deployable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.DeployableElement
   * @generated
   */
	public Adapter createDeployableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.DeploymentTarget <em>Deployment Target</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.DeploymentTarget
   * @generated
   */
	public Adapter createDeploymentTargetAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink <em>Abstract Deployment Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.AbstractDeploymentLink
   * @generated
   */
	public Adapter createAbstractDeploymentLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement <em>Abstract Path Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement
   * @generated
   */
	public Adapter createAbstractPathInvolvedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact <em>Abstract Physical Artifact</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact
   * @generated
   */
	public Adapter createAbstractPhysicalArtifactAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd <em>Abstract Physical Link End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd
   * @generated
   */
	public Adapter createAbstractPhysicalLinkEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink <em>Abstract Physical Path Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink
   * @generated
   */
	public Adapter createAbstractPhysicalPathLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalLink <em>Physical Link</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalLink
   * @generated
   */
	public Adapter createPhysicalLinkAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkCategory <em>Physical Link Category</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkCategory
   * @generated
   */
	public Adapter createPhysicalLinkCategoryAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkEnd <em>Physical Link End</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkEnd
   * @generated
   */
	public Adapter createPhysicalLinkEndAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalLinkRealization <em>Physical Link Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalLinkRealization
   * @generated
   */
	public Adapter createPhysicalLinkRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPath <em>Physical Path</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPath
   * @generated
   */
	public Adapter createPhysicalPathAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement <em>Physical Path Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathInvolvement
   * @generated
   */
	public Adapter createPhysicalPathInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPathReference <em>Physical Path Reference</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathReference
   * @generated
   */
	public Adapter createPhysicalPathReferenceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPathRealization <em>Physical Path Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPathRealization
   * @generated
   */
	public Adapter createPhysicalPathRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPort <em>Physical Port</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPort
   * @generated
   */
	public Adapter createPhysicalPortAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.PhysicalPortRealization <em>Physical Port Realization</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.PhysicalPortRealization
   * @generated
   */
	public Adapter createPhysicalPortRealizationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.cs.ComponentPkg <em>Component Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.cs.ComponentPkg
   * @generated
   */
	public Adapter createComponentPkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.Element
   * @generated
   */
	public Adapter createElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.kitalpha.emde.model.ExtensibleElement <em>Extensible Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.kitalpha.emde.model.ExtensibleElement
   * @generated
   */
	public Adapter createExtensibleElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.ModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.ModelElement
   * @generated
   */
	public Adapter createModelElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractNamedElement <em>Abstract Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractNamedElement
   * @generated
   */
	public Adapter createAbstractNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.TraceableElement <em>Traceable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.TraceableElement
   * @generated
   */
	public Adapter createTraceableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.PublishableElement <em>Publishable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.PublishableElement
   * @generated
   */
	public Adapter createPublishableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.CapellaElement <em>Capella Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.CapellaElement
   * @generated
   */
	public Adapter createCapellaElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.NamedElement
   * @generated
   */
	public Adapter createNamedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Namespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Namespace
   * @generated
   */
	public Adapter createNamespaceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Structure <em>Structure</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Structure
   * @generated
   */
	public Adapter createStructureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg <em>Modelling Architecture Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg
   * @generated
   */
	public Adapter createModellingArchitecturePkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.ModellingArchitecture <em>Modelling Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.ModellingArchitecture
   * @generated
   */
	public Adapter createModellingArchitectureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture <em>Abstract Functional Architecture</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture
   * @generated
   */
	public Adapter createAbstractFunctionalArchitectureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractType <em>Abstract Type</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractType
   * @generated
   */
	public Adapter createAbstractTypeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Type
   * @generated
   */
	public Adapter createTypeAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.ModellingBlock <em>Modelling Block</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.ModellingBlock
   * @generated
   */
	public Adapter createModellingBlockAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock <em>Abstract Functional Block</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock
   * @generated
   */
	public Adapter createAbstractFunctionalBlockAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Classifier <em>Classifier</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Classifier
   * @generated
   */
	public Adapter createClassifierAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.GeneralizableElement <em>Generalizable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.GeneralizableElement
   * @generated
   */
	public Adapter createGeneralizableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger <em>Link Exchanger</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger
   * @generated
   */
	public Adapter createCommunicationLinkExchangerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.InvolvedElement <em>Involved Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.InvolvedElement
   * @generated
   */
	public Adapter createInvolvedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Feature
   * @generated
   */
	public Adapter createFeatureAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTypedElement <em>Abstract Typed Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTypedElement
   * @generated
   */
	public Adapter createAbstractTypedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.TypedElement <em>Typed Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.TypedElement
   * @generated
   */
	public Adapter createTypedElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.MultiplicityElement <em>Multiplicity Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.MultiplicityElement
   * @generated
   */
	public Adapter createMultiplicityElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.FinalizableElement <em>Finalizable Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.FinalizableElement
   * @generated
   */
	public Adapter createFinalizableElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.Property
   * @generated
   */
	public Adapter createPropertyAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.AbstractInstance <em>Abstract Instance</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.AbstractInstance
   * @generated
   */
	public Adapter createAbstractInstanceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.InformationsExchanger <em>Informations Exchanger</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.InformationsExchanger
   * @generated
   */
	public Adapter createInformationsExchangerAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractRelationship <em>Abstract Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractRelationship
   * @generated
   */
	public Adapter createAbstractRelationshipAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Relationship <em>Relationship</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Relationship
   * @generated
   */
	public Adapter createRelationshipAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace <em>Abstract Trace</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.common.data.modellingcore.AbstractTrace
   * @generated
   */
	public Adapter createAbstractTraceAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Allocation <em>Allocation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Allocation
   * @generated
   */
	public Adapter createAllocationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.communication.MessageReferencePkg <em>Message Reference Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.communication.MessageReferencePkg
   * @generated
   */
	public Adapter createMessageReferencePkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg <em>Abstract Dependencies Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg
   * @generated
   */
	public Adapter createAbstractDependenciesPkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg <em>Abstract Exchange Item Pkg</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg
   * @generated
   */
	public Adapter createAbstractExchangeItemPkgAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.GeneralClass <em>General Class</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.GeneralClass
   * @generated
   */
	public Adapter createGeneralClassAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.Involvement <em>Involvement</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.Involvement
   * @generated
   */
	public Adapter createInvolvementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.AbstractEventOperation <em>Abstract Event Operation</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.AbstractEventOperation
   * @generated
   */
	public Adapter createAbstractEventOperationAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.fa.ComponentExchangeAllocator <em>Component Exchange Allocator</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.fa.ComponentExchangeAllocator
   * @generated
   */
	public Adapter createComponentExchangeAllocatorAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.capellacore.InvolverElement <em>Involver Element</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.capellacore.InvolverElement
   * @generated
   */
	public Adapter createInvolverElementAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.core.data.information.Port <em>Port</em>}'.
   * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.core.data.information.Port
   * @generated
   */
	public Adapter createPortAdapter() {
    return null;
  }

	/**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
	public Adapter createEObjectAdapter() {
    return null;
  }

} //CsAdapterFactory
