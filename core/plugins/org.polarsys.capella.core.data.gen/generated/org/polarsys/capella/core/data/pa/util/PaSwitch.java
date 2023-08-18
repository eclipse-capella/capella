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
package org.polarsys.capella.core.data.pa.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.activity.CallAction;
import org.polarsys.capella.common.data.activity.CallBehaviorAction;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.InvocationAction;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecturePkg;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecturePkg;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.pa.*;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalInterfaceRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecturePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;
import org.polarsys.capella.core.data.pa.PhysicalNode;
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
 * @see org.polarsys.capella.core.data.pa.PaPackage
 * @generated
 */
public class PaSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static PaPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PaSwitch() {
    if (modelPackage == null) {
      modelPackage = PaPackage.eINSTANCE;
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
      case PaPackage.PHYSICAL_ARCHITECTURE_PKG: {
        PhysicalArchitecturePkg physicalArchitecturePkg = (PhysicalArchitecturePkg)theEObject;
        T result = casePhysicalArchitecturePkg(physicalArchitecturePkg);
        if (result == null) result = caseBlockArchitecturePkg(physicalArchitecturePkg);
        if (result == null) result = caseModellingArchitecturePkg(physicalArchitecturePkg);
        if (result == null) result = caseStructure(physicalArchitecturePkg);
        if (result == null) result = caseNamespace(physicalArchitecturePkg);
        if (result == null) result = caseNamedElement(physicalArchitecturePkg);
        if (result == null) result = caseAbstractNamedElement(physicalArchitecturePkg);
        if (result == null) result = caseCapellaElement(physicalArchitecturePkg);
        if (result == null) result = caseTraceableElement(physicalArchitecturePkg);
        if (result == null) result = casePublishableElement(physicalArchitecturePkg);
        if (result == null) result = caseModelElement(physicalArchitecturePkg);
        if (result == null) result = caseExtensibleElement(physicalArchitecturePkg);
        if (result == null) result = caseElement(physicalArchitecturePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_ARCHITECTURE: {
        PhysicalArchitecture physicalArchitecture = (PhysicalArchitecture)theEObject;
        T result = casePhysicalArchitecture(physicalArchitecture);
        if (result == null) result = caseComponentArchitecture(physicalArchitecture);
        if (result == null) result = caseBlockArchitecture(physicalArchitecture);
        if (result == null) result = caseAbstractFunctionalArchitecture(physicalArchitecture);
        if (result == null) result = caseModellingArchitecture(physicalArchitecture);
        if (result == null) result = caseStructure(physicalArchitecture);
        if (result == null) result = caseNamespace(physicalArchitecture);
        if (result == null) result = caseNamedElement(physicalArchitecture);
        if (result == null) result = caseAbstractNamedElement(physicalArchitecture);
        if (result == null) result = caseCapellaElement(physicalArchitecture);
        if (result == null) result = caseTraceableElement(physicalArchitecture);
        if (result == null) result = casePublishableElement(physicalArchitecture);
        if (result == null) result = caseModelElement(physicalArchitecture);
        if (result == null) result = caseExtensibleElement(physicalArchitecture);
        if (result == null) result = caseElement(physicalArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_FUNCTION: {
        PhysicalFunction physicalFunction = (PhysicalFunction)theEObject;
        T result = casePhysicalFunction(physicalFunction);
        if (result == null) result = caseAbstractFunction(physicalFunction);
        if (result == null) result = caseNamespace(physicalFunction);
        if (result == null) result = caseInvolvedElement(physicalFunction);
        if (result == null) result = caseAbstractInstance(physicalFunction);
        if (result == null) result = caseAbstractFunctionalChainContainer(physicalFunction);
        if (result == null) result = caseCallBehaviorAction(physicalFunction);
        if (result == null) result = caseAbstractEvent(physicalFunction);
        if (result == null) result = caseProperty(physicalFunction);
        if (result == null) result = caseCallAction(physicalFunction);
        if (result == null) result = caseAbstractType(physicalFunction);
        if (result == null) result = caseFeature(physicalFunction);
        if (result == null) result = caseTypedElement(physicalFunction);
        if (result == null) result = caseMultiplicityElement(physicalFunction);
        if (result == null) result = caseFinalizableElement(physicalFunction);
        if (result == null) result = caseInvocationAction(physicalFunction);
        if (result == null) result = caseNamedElement(physicalFunction);
        if (result == null) result = caseCapellaElement(physicalFunction);
        if (result == null) result = caseTraceableElement(physicalFunction);
        if (result == null) result = casePublishableElement(physicalFunction);
        if (result == null) result = caseAbstractTypedElement(physicalFunction);
        if (result == null) result = caseAbstractAction(physicalFunction);
        if (result == null) result = caseModelElement(physicalFunction);
        if (result == null) result = caseExtensibleElement(physicalFunction);
        if (result == null) result = caseExecutableNode(physicalFunction);
        if (result == null) result = caseElement(physicalFunction);
        if (result == null) result = caseActivityNode(physicalFunction);
        if (result == null) result = caseAbstractNamedElement(physicalFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_FUNCTION_PKG: {
        PhysicalFunctionPkg physicalFunctionPkg = (PhysicalFunctionPkg)theEObject;
        T result = casePhysicalFunctionPkg(physicalFunctionPkg);
        if (result == null) result = caseFunctionPkg(physicalFunctionPkg);
        if (result == null) result = caseStructure(physicalFunctionPkg);
        if (result == null) result = caseNamespace(physicalFunctionPkg);
        if (result == null) result = caseNamedElement(physicalFunctionPkg);
        if (result == null) result = caseAbstractNamedElement(physicalFunctionPkg);
        if (result == null) result = caseCapellaElement(physicalFunctionPkg);
        if (result == null) result = caseTraceableElement(physicalFunctionPkg);
        if (result == null) result = casePublishableElement(physicalFunctionPkg);
        if (result == null) result = caseModelElement(physicalFunctionPkg);
        if (result == null) result = caseExtensibleElement(physicalFunctionPkg);
        if (result == null) result = caseElement(physicalFunctionPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_COMPONENT: {
        PhysicalComponent physicalComponent = (PhysicalComponent)theEObject;
        T result = casePhysicalComponent(physicalComponent);
        if (result == null) result = caseAbstractPhysicalArtifact(physicalComponent);
        if (result == null) result = caseComponent(physicalComponent);
        if (result == null) result = caseCapabilityRealizationInvolvedElement(physicalComponent);
        if (result == null) result = caseDeployableElement(physicalComponent);
        if (result == null) result = caseDeploymentTarget(physicalComponent);
        if (result == null) result = caseBlock(physicalComponent);
        if (result == null) result = caseClassifier(physicalComponent);
        if (result == null) result = caseInterfaceAllocator(physicalComponent);
        if (result == null) result = caseCommunicationLinkExchanger(physicalComponent);
        if (result == null) result = caseInvolvedElement(physicalComponent);
        if (result == null) result = caseTraceableElement(physicalComponent);
        if (result == null) result = casePublishableElement(physicalComponent);
        if (result == null) result = caseAbstractFunctionalBlock(physicalComponent);
        if (result == null) result = caseGeneralizableElement(physicalComponent);
        if (result == null) result = caseModellingBlock(physicalComponent);
        if (result == null) result = caseType(physicalComponent);
        if (result == null) result = caseExtensibleElement(physicalComponent);
        if (result == null) result = caseAbstractType(physicalComponent);
        if (result == null) result = caseNamespace(physicalComponent);
        if (result == null) result = caseElement(physicalComponent);
        if (result == null) result = caseNamedElement(physicalComponent);
        if (result == null) result = caseCapellaElement(physicalComponent);
        if (result == null) result = caseModelElement(physicalComponent);
        if (result == null) result = caseAbstractNamedElement(physicalComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_COMPONENT_PKG: {
        PhysicalComponentPkg physicalComponentPkg = (PhysicalComponentPkg)theEObject;
        T result = casePhysicalComponentPkg(physicalComponentPkg);
        if (result == null) result = caseComponentPkg(physicalComponentPkg);
        if (result == null) result = caseAssociationPkg(physicalComponentPkg);
        if (result == null) result = caseStructure(physicalComponentPkg);
        if (result == null) result = caseNamespace(physicalComponentPkg);
        if (result == null) result = caseNamedElement(physicalComponentPkg);
        if (result == null) result = caseAbstractNamedElement(physicalComponentPkg);
        if (result == null) result = caseCapellaElement(physicalComponentPkg);
        if (result == null) result = caseTraceableElement(physicalComponentPkg);
        if (result == null) result = casePublishableElement(physicalComponentPkg);
        if (result == null) result = caseModelElement(physicalComponentPkg);
        if (result == null) result = caseExtensibleElement(physicalComponentPkg);
        if (result == null) result = caseElement(physicalComponentPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.PHYSICAL_NODE: {
        PhysicalNode physicalNode = (PhysicalNode)theEObject;
        T result = casePhysicalNode(physicalNode);
        if (result == null) result = casePhysicalComponent(physicalNode);
        if (result == null) result = caseAbstractPhysicalArtifact(physicalNode);
        if (result == null) result = caseComponent(physicalNode);
        if (result == null) result = caseCapabilityRealizationInvolvedElement(physicalNode);
        if (result == null) result = caseDeployableElement(physicalNode);
        if (result == null) result = caseDeploymentTarget(physicalNode);
        if (result == null) result = caseBlock(physicalNode);
        if (result == null) result = caseClassifier(physicalNode);
        if (result == null) result = caseInterfaceAllocator(physicalNode);
        if (result == null) result = caseCommunicationLinkExchanger(physicalNode);
        if (result == null) result = caseInvolvedElement(physicalNode);
        if (result == null) result = caseTraceableElement(physicalNode);
        if (result == null) result = casePublishableElement(physicalNode);
        if (result == null) result = caseAbstractFunctionalBlock(physicalNode);
        if (result == null) result = caseGeneralizableElement(physicalNode);
        if (result == null) result = caseModellingBlock(physicalNode);
        if (result == null) result = caseType(physicalNode);
        if (result == null) result = caseExtensibleElement(physicalNode);
        if (result == null) result = caseAbstractType(physicalNode);
        if (result == null) result = caseNamespace(physicalNode);
        if (result == null) result = caseElement(physicalNode);
        if (result == null) result = caseNamedElement(physicalNode);
        if (result == null) result = caseCapellaElement(physicalNode);
        if (result == null) result = caseModelElement(physicalNode);
        if (result == null) result = caseAbstractNamedElement(physicalNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.LOGICAL_ARCHITECTURE_REALIZATION: {
        LogicalArchitectureRealization logicalArchitectureRealization = (LogicalArchitectureRealization)theEObject;
        T result = caseLogicalArchitectureRealization(logicalArchitectureRealization);
        if (result == null) result = caseArchitectureAllocation(logicalArchitectureRealization);
        if (result == null) result = caseAllocation(logicalArchitectureRealization);
        if (result == null) result = caseRelationship(logicalArchitectureRealization);
        if (result == null) result = caseAbstractTrace(logicalArchitectureRealization);
        if (result == null) result = caseAbstractRelationship(logicalArchitectureRealization);
        if (result == null) result = caseCapellaElement(logicalArchitectureRealization);
        if (result == null) result = caseTraceableElement(logicalArchitectureRealization);
        if (result == null) result = casePublishableElement(logicalArchitectureRealization);
        if (result == null) result = caseModelElement(logicalArchitectureRealization);
        if (result == null) result = caseExtensibleElement(logicalArchitectureRealization);
        if (result == null) result = caseElement(logicalArchitectureRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PaPackage.LOGICAL_INTERFACE_REALIZATION: {
        LogicalInterfaceRealization logicalInterfaceRealization = (LogicalInterfaceRealization)theEObject;
        T result = caseLogicalInterfaceRealization(logicalInterfaceRealization);
        if (result == null) result = caseInterfaceAllocation(logicalInterfaceRealization);
        if (result == null) result = caseAllocation(logicalInterfaceRealization);
        if (result == null) result = caseRelationship(logicalInterfaceRealization);
        if (result == null) result = caseAbstractTrace(logicalInterfaceRealization);
        if (result == null) result = caseAbstractRelationship(logicalInterfaceRealization);
        if (result == null) result = caseCapellaElement(logicalInterfaceRealization);
        if (result == null) result = caseTraceableElement(logicalInterfaceRealization);
        if (result == null) result = casePublishableElement(logicalInterfaceRealization);
        if (result == null) result = caseModelElement(logicalInterfaceRealization);
        if (result == null) result = caseExtensibleElement(logicalInterfaceRealization);
        if (result == null) result = caseElement(logicalInterfaceRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Architecture Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalArchitecturePkg(PhysicalArchitecturePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalArchitecture(PhysicalArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Function</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalFunction(PhysicalFunction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Function Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalFunctionPkg(PhysicalFunctionPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Component</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalComponent(PhysicalComponent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Component Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalComponentPkg(PhysicalComponentPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Physical Node</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Physical Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePhysicalNode(PhysicalNode object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Architecture Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Architecture Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalArchitectureRealization(LogicalArchitectureRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Interface Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Interface Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalInterfaceRealization(LogicalInterfaceRealization object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Realization Involved Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityRealizationInvolvedElement(CapabilityRealizationInvolvedElement object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Association Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Association Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAssociationPkg(AssociationPkg object) {
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

} //PaSwitch
