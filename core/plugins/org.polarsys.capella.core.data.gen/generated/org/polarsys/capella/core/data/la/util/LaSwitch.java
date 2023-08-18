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
package org.polarsys.capella.core.data.la.util;

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
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
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
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecturePkg;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.*;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.ContextInterfaceRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecturePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
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
 * @see org.polarsys.capella.core.data.la.LaPackage
 * @generated
 */
public class LaSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static LaPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public LaSwitch() {
    if (modelPackage == null) {
      modelPackage = LaPackage.eINSTANCE;
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
      case LaPackage.LOGICAL_ARCHITECTURE_PKG: {
        LogicalArchitecturePkg logicalArchitecturePkg = (LogicalArchitecturePkg)theEObject;
        T result = caseLogicalArchitecturePkg(logicalArchitecturePkg);
        if (result == null) result = caseBlockArchitecturePkg(logicalArchitecturePkg);
        if (result == null) result = caseModellingArchitecturePkg(logicalArchitecturePkg);
        if (result == null) result = caseStructure(logicalArchitecturePkg);
        if (result == null) result = caseNamespace(logicalArchitecturePkg);
        if (result == null) result = caseNamedElement(logicalArchitecturePkg);
        if (result == null) result = caseAbstractNamedElement(logicalArchitecturePkg);
        if (result == null) result = caseCapellaElement(logicalArchitecturePkg);
        if (result == null) result = caseTraceableElement(logicalArchitecturePkg);
        if (result == null) result = casePublishableElement(logicalArchitecturePkg);
        if (result == null) result = caseModelElement(logicalArchitecturePkg);
        if (result == null) result = caseExtensibleElement(logicalArchitecturePkg);
        if (result == null) result = caseElement(logicalArchitecturePkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.LOGICAL_ARCHITECTURE: {
        LogicalArchitecture logicalArchitecture = (LogicalArchitecture)theEObject;
        T result = caseLogicalArchitecture(logicalArchitecture);
        if (result == null) result = caseComponentArchitecture(logicalArchitecture);
        if (result == null) result = caseBlockArchitecture(logicalArchitecture);
        if (result == null) result = caseAbstractFunctionalArchitecture(logicalArchitecture);
        if (result == null) result = caseModellingArchitecture(logicalArchitecture);
        if (result == null) result = caseStructure(logicalArchitecture);
        if (result == null) result = caseNamespace(logicalArchitecture);
        if (result == null) result = caseNamedElement(logicalArchitecture);
        if (result == null) result = caseAbstractNamedElement(logicalArchitecture);
        if (result == null) result = caseCapellaElement(logicalArchitecture);
        if (result == null) result = caseTraceableElement(logicalArchitecture);
        if (result == null) result = casePublishableElement(logicalArchitecture);
        if (result == null) result = caseModelElement(logicalArchitecture);
        if (result == null) result = caseExtensibleElement(logicalArchitecture);
        if (result == null) result = caseElement(logicalArchitecture);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.LOGICAL_FUNCTION: {
        LogicalFunction logicalFunction = (LogicalFunction)theEObject;
        T result = caseLogicalFunction(logicalFunction);
        if (result == null) result = caseAbstractFunction(logicalFunction);
        if (result == null) result = caseNamespace(logicalFunction);
        if (result == null) result = caseInvolvedElement(logicalFunction);
        if (result == null) result = caseAbstractInstance(logicalFunction);
        if (result == null) result = caseAbstractFunctionalChainContainer(logicalFunction);
        if (result == null) result = caseCallBehaviorAction(logicalFunction);
        if (result == null) result = caseAbstractEvent(logicalFunction);
        if (result == null) result = caseProperty(logicalFunction);
        if (result == null) result = caseCallAction(logicalFunction);
        if (result == null) result = caseAbstractType(logicalFunction);
        if (result == null) result = caseFeature(logicalFunction);
        if (result == null) result = caseTypedElement(logicalFunction);
        if (result == null) result = caseMultiplicityElement(logicalFunction);
        if (result == null) result = caseFinalizableElement(logicalFunction);
        if (result == null) result = caseInvocationAction(logicalFunction);
        if (result == null) result = caseNamedElement(logicalFunction);
        if (result == null) result = caseCapellaElement(logicalFunction);
        if (result == null) result = caseTraceableElement(logicalFunction);
        if (result == null) result = casePublishableElement(logicalFunction);
        if (result == null) result = caseAbstractTypedElement(logicalFunction);
        if (result == null) result = caseAbstractAction(logicalFunction);
        if (result == null) result = caseModelElement(logicalFunction);
        if (result == null) result = caseExtensibleElement(logicalFunction);
        if (result == null) result = caseExecutableNode(logicalFunction);
        if (result == null) result = caseElement(logicalFunction);
        if (result == null) result = caseActivityNode(logicalFunction);
        if (result == null) result = caseAbstractNamedElement(logicalFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.LOGICAL_FUNCTION_PKG: {
        LogicalFunctionPkg logicalFunctionPkg = (LogicalFunctionPkg)theEObject;
        T result = caseLogicalFunctionPkg(logicalFunctionPkg);
        if (result == null) result = caseFunctionPkg(logicalFunctionPkg);
        if (result == null) result = caseStructure(logicalFunctionPkg);
        if (result == null) result = caseNamespace(logicalFunctionPkg);
        if (result == null) result = caseNamedElement(logicalFunctionPkg);
        if (result == null) result = caseAbstractNamedElement(logicalFunctionPkg);
        if (result == null) result = caseCapellaElement(logicalFunctionPkg);
        if (result == null) result = caseTraceableElement(logicalFunctionPkg);
        if (result == null) result = casePublishableElement(logicalFunctionPkg);
        if (result == null) result = caseModelElement(logicalFunctionPkg);
        if (result == null) result = caseExtensibleElement(logicalFunctionPkg);
        if (result == null) result = caseElement(logicalFunctionPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.LOGICAL_COMPONENT: {
        LogicalComponent logicalComponent = (LogicalComponent)theEObject;
        T result = caseLogicalComponent(logicalComponent);
        if (result == null) result = caseComponent(logicalComponent);
        if (result == null) result = caseCapabilityRealizationInvolvedElement(logicalComponent);
        if (result == null) result = caseBlock(logicalComponent);
        if (result == null) result = caseClassifier(logicalComponent);
        if (result == null) result = caseInterfaceAllocator(logicalComponent);
        if (result == null) result = caseCommunicationLinkExchanger(logicalComponent);
        if (result == null) result = caseInvolvedElement(logicalComponent);
        if (result == null) result = caseAbstractFunctionalBlock(logicalComponent);
        if (result == null) result = caseGeneralizableElement(logicalComponent);
        if (result == null) result = caseModellingBlock(logicalComponent);
        if (result == null) result = caseType(logicalComponent);
        if (result == null) result = caseAbstractType(logicalComponent);
        if (result == null) result = caseNamespace(logicalComponent);
        if (result == null) result = caseNamedElement(logicalComponent);
        if (result == null) result = caseAbstractNamedElement(logicalComponent);
        if (result == null) result = caseCapellaElement(logicalComponent);
        if (result == null) result = caseExtensibleElement(logicalComponent);
        if (result == null) result = caseTraceableElement(logicalComponent);
        if (result == null) result = casePublishableElement(logicalComponent);
        if (result == null) result = caseModelElement(logicalComponent);
        if (result == null) result = caseElement(logicalComponent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.LOGICAL_COMPONENT_PKG: {
        LogicalComponentPkg logicalComponentPkg = (LogicalComponentPkg)theEObject;
        T result = caseLogicalComponentPkg(logicalComponentPkg);
        if (result == null) result = caseComponentPkg(logicalComponentPkg);
        if (result == null) result = caseStructure(logicalComponentPkg);
        if (result == null) result = caseNamespace(logicalComponentPkg);
        if (result == null) result = caseNamedElement(logicalComponentPkg);
        if (result == null) result = caseAbstractNamedElement(logicalComponentPkg);
        if (result == null) result = caseCapellaElement(logicalComponentPkg);
        if (result == null) result = caseTraceableElement(logicalComponentPkg);
        if (result == null) result = casePublishableElement(logicalComponentPkg);
        if (result == null) result = caseModelElement(logicalComponentPkg);
        if (result == null) result = caseExtensibleElement(logicalComponentPkg);
        if (result == null) result = caseElement(logicalComponentPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.CAPABILITY_REALIZATION: {
        CapabilityRealization capabilityRealization = (CapabilityRealization)theEObject;
        T result = caseCapabilityRealization(capabilityRealization);
        if (result == null) result = caseAbstractCapability(capabilityRealization);
        if (result == null) result = caseStructure(capabilityRealization);
        if (result == null) result = caseInvolverElement(capabilityRealization);
        if (result == null) result = caseAbstractFunctionalChainContainer(capabilityRealization);
        if (result == null) result = caseNamespace(capabilityRealization);
        if (result == null) result = caseNamedElement(capabilityRealization);
        if (result == null) result = caseAbstractNamedElement(capabilityRealization);
        if (result == null) result = caseCapellaElement(capabilityRealization);
        if (result == null) result = caseTraceableElement(capabilityRealization);
        if (result == null) result = casePublishableElement(capabilityRealization);
        if (result == null) result = caseModelElement(capabilityRealization);
        if (result == null) result = caseExtensibleElement(capabilityRealization);
        if (result == null) result = caseElement(capabilityRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.CAPABILITY_REALIZATION_PKG: {
        CapabilityRealizationPkg capabilityRealizationPkg = (CapabilityRealizationPkg)theEObject;
        T result = caseCapabilityRealizationPkg(capabilityRealizationPkg);
        if (result == null) result = caseAbstractCapabilityPkg(capabilityRealizationPkg);
        if (result == null) result = caseStructure(capabilityRealizationPkg);
        if (result == null) result = caseNamespace(capabilityRealizationPkg);
        if (result == null) result = caseNamedElement(capabilityRealizationPkg);
        if (result == null) result = caseAbstractNamedElement(capabilityRealizationPkg);
        if (result == null) result = caseCapellaElement(capabilityRealizationPkg);
        if (result == null) result = caseTraceableElement(capabilityRealizationPkg);
        if (result == null) result = casePublishableElement(capabilityRealizationPkg);
        if (result == null) result = caseModelElement(capabilityRealizationPkg);
        if (result == null) result = caseExtensibleElement(capabilityRealizationPkg);
        if (result == null) result = caseElement(capabilityRealizationPkg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.SYSTEM_ANALYSIS_REALIZATION: {
        SystemAnalysisRealization systemAnalysisRealization = (SystemAnalysisRealization)theEObject;
        T result = caseSystemAnalysisRealization(systemAnalysisRealization);
        if (result == null) result = caseArchitectureAllocation(systemAnalysisRealization);
        if (result == null) result = caseAllocation(systemAnalysisRealization);
        if (result == null) result = caseRelationship(systemAnalysisRealization);
        if (result == null) result = caseAbstractTrace(systemAnalysisRealization);
        if (result == null) result = caseAbstractRelationship(systemAnalysisRealization);
        if (result == null) result = caseCapellaElement(systemAnalysisRealization);
        if (result == null) result = caseTraceableElement(systemAnalysisRealization);
        if (result == null) result = casePublishableElement(systemAnalysisRealization);
        if (result == null) result = caseModelElement(systemAnalysisRealization);
        if (result == null) result = caseExtensibleElement(systemAnalysisRealization);
        if (result == null) result = caseElement(systemAnalysisRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LaPackage.CONTEXT_INTERFACE_REALIZATION: {
        ContextInterfaceRealization contextInterfaceRealization = (ContextInterfaceRealization)theEObject;
        T result = caseContextInterfaceRealization(contextInterfaceRealization);
        if (result == null) result = caseInterfaceAllocation(contextInterfaceRealization);
        if (result == null) result = caseAllocation(contextInterfaceRealization);
        if (result == null) result = caseRelationship(contextInterfaceRealization);
        if (result == null) result = caseAbstractTrace(contextInterfaceRealization);
        if (result == null) result = caseAbstractRelationship(contextInterfaceRealization);
        if (result == null) result = caseCapellaElement(contextInterfaceRealization);
        if (result == null) result = caseTraceableElement(contextInterfaceRealization);
        if (result == null) result = casePublishableElement(contextInterfaceRealization);
        if (result == null) result = caseModelElement(contextInterfaceRealization);
        if (result == null) result = caseExtensibleElement(contextInterfaceRealization);
        if (result == null) result = caseElement(contextInterfaceRealization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Architecture Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Architecture Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalArchitecturePkg(LogicalArchitecturePkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Architecture</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Architecture</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalArchitecture(LogicalArchitecture object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Function</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalFunction(LogicalFunction object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Function Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Function Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalFunctionPkg(LogicalFunctionPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Component</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalComponent(LogicalComponent object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Logical Component Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Component Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseLogicalComponentPkg(LogicalComponentPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capability Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityRealization(CapabilityRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Capability Realization Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Realization Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseCapabilityRealizationPkg(CapabilityRealizationPkg object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>System Analysis Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>System Analysis Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseSystemAnalysisRealization(SystemAnalysisRealization object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Context Interface Realization</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Context Interface Realization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseContextInterfaceRealization(ContextInterfaceRealization object) {
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
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapability(AbstractCapability object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Capability Pkg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractCapabilityPkg(AbstractCapabilityPkg object) {
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

} //LaSwitch
