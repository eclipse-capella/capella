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
package org.polarsys.capella.core.data.pa.deployment.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.pa.deployment.*;
import org.polarsys.capella.core.data.pa.deployment.AbstractPhysicalInstance;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.ConnectionInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentAspect;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.InstanceDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.data.pa.deployment.PortInstance;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;
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
 * @see org.polarsys.capella.core.data.pa.deployment.DeploymentPackage
 * @generated
 */
public class DeploymentSwitch<T> extends Switch<T> {
	/**
   * The cached model package
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected static DeploymentPackage modelPackage;

	/**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DeploymentSwitch() {
    if (modelPackage == null) {
      modelPackage = DeploymentPackage.eINSTANCE;
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
      case DeploymentPackage.COMPONENT_INSTANCE: {
        ComponentInstance componentInstance = (ComponentInstance)theEObject;
        T result = caseComponentInstance(componentInstance);
        if (result == null) result = caseAbstractPhysicalInstance(componentInstance);
        if (result == null) result = caseDeployableElement(componentInstance);
        if (result == null) result = caseDeploymentTarget(componentInstance);
        if (result == null) result = caseNamedElement(componentInstance);
        if (result == null) result = caseCapellaElement(componentInstance);
        if (result == null) result = caseTraceableElement(componentInstance);
        if (result == null) result = casePublishableElement(componentInstance);
        if (result == null) result = caseAbstractNamedElement(componentInstance);
        if (result == null) result = caseModelElement(componentInstance);
        if (result == null) result = caseExtensibleElement(componentInstance);
        if (result == null) result = caseElement(componentInstance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.CONNECTION_INSTANCE: {
        ConnectionInstance connectionInstance = (ConnectionInstance)theEObject;
        T result = caseConnectionInstance(connectionInstance);
        if (result == null) result = caseAbstractPhysicalInstance(connectionInstance);
        if (result == null) result = caseCapellaElement(connectionInstance);
        if (result == null) result = caseTraceableElement(connectionInstance);
        if (result == null) result = casePublishableElement(connectionInstance);
        if (result == null) result = caseModelElement(connectionInstance);
        if (result == null) result = caseExtensibleElement(connectionInstance);
        if (result == null) result = caseElement(connectionInstance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.DEPLOYMENT_ASPECT: {
        DeploymentAspect deploymentAspect = (DeploymentAspect)theEObject;
        T result = caseDeploymentAspect(deploymentAspect);
        if (result == null) result = caseStructure(deploymentAspect);
        if (result == null) result = caseNamespace(deploymentAspect);
        if (result == null) result = caseNamedElement(deploymentAspect);
        if (result == null) result = caseAbstractNamedElement(deploymentAspect);
        if (result == null) result = caseCapellaElement(deploymentAspect);
        if (result == null) result = caseTraceableElement(deploymentAspect);
        if (result == null) result = casePublishableElement(deploymentAspect);
        if (result == null) result = caseModelElement(deploymentAspect);
        if (result == null) result = caseExtensibleElement(deploymentAspect);
        if (result == null) result = caseElement(deploymentAspect);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.DEPLOYMENT_CONFIGURATION: {
        DeploymentConfiguration deploymentConfiguration = (DeploymentConfiguration)theEObject;
        T result = caseDeploymentConfiguration(deploymentConfiguration);
        if (result == null) result = caseNamedElement(deploymentConfiguration);
        if (result == null) result = caseAbstractNamedElement(deploymentConfiguration);
        if (result == null) result = caseCapellaElement(deploymentConfiguration);
        if (result == null) result = caseTraceableElement(deploymentConfiguration);
        if (result == null) result = casePublishableElement(deploymentConfiguration);
        if (result == null) result = caseModelElement(deploymentConfiguration);
        if (result == null) result = caseExtensibleElement(deploymentConfiguration);
        if (result == null) result = caseElement(deploymentConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.INSTANCE_DEPLOYMENT_LINK: {
        InstanceDeploymentLink instanceDeploymentLink = (InstanceDeploymentLink)theEObject;
        T result = caseInstanceDeploymentLink(instanceDeploymentLink);
        if (result == null) result = caseAbstractDeploymentLink(instanceDeploymentLink);
        if (result == null) result = caseRelationship(instanceDeploymentLink);
        if (result == null) result = caseAbstractRelationship(instanceDeploymentLink);
        if (result == null) result = caseCapellaElement(instanceDeploymentLink);
        if (result == null) result = caseTraceableElement(instanceDeploymentLink);
        if (result == null) result = casePublishableElement(instanceDeploymentLink);
        if (result == null) result = caseModelElement(instanceDeploymentLink);
        if (result == null) result = caseExtensibleElement(instanceDeploymentLink);
        if (result == null) result = caseElement(instanceDeploymentLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.PART_DEPLOYMENT_LINK: {
        PartDeploymentLink partDeploymentLink = (PartDeploymentLink)theEObject;
        T result = casePartDeploymentLink(partDeploymentLink);
        if (result == null) result = caseAbstractDeploymentLink(partDeploymentLink);
        if (result == null) result = caseRelationship(partDeploymentLink);
        if (result == null) result = caseAbstractRelationship(partDeploymentLink);
        if (result == null) result = caseCapellaElement(partDeploymentLink);
        if (result == null) result = caseTraceableElement(partDeploymentLink);
        if (result == null) result = casePublishableElement(partDeploymentLink);
        if (result == null) result = caseModelElement(partDeploymentLink);
        if (result == null) result = caseExtensibleElement(partDeploymentLink);
        if (result == null) result = caseElement(partDeploymentLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.ABSTRACT_PHYSICAL_INSTANCE: {
        AbstractPhysicalInstance abstractPhysicalInstance = (AbstractPhysicalInstance)theEObject;
        T result = caseAbstractPhysicalInstance(abstractPhysicalInstance);
        if (result == null) result = caseCapellaElement(abstractPhysicalInstance);
        if (result == null) result = caseTraceableElement(abstractPhysicalInstance);
        if (result == null) result = casePublishableElement(abstractPhysicalInstance);
        if (result == null) result = caseModelElement(abstractPhysicalInstance);
        if (result == null) result = caseExtensibleElement(abstractPhysicalInstance);
        if (result == null) result = caseElement(abstractPhysicalInstance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.PORT_INSTANCE: {
        PortInstance portInstance = (PortInstance)theEObject;
        T result = casePortInstance(portInstance);
        if (result == null) result = caseAbstractPhysicalInstance(portInstance);
        if (result == null) result = caseCapellaElement(portInstance);
        if (result == null) result = caseTraceableElement(portInstance);
        if (result == null) result = casePublishableElement(portInstance);
        if (result == null) result = caseModelElement(portInstance);
        if (result == null) result = caseExtensibleElement(portInstance);
        if (result == null) result = caseElement(portInstance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DeploymentPackage.TYPE_DEPLOYMENT_LINK: {
        TypeDeploymentLink typeDeploymentLink = (TypeDeploymentLink)theEObject;
        T result = caseTypeDeploymentLink(typeDeploymentLink);
        if (result == null) result = caseAbstractDeploymentLink(typeDeploymentLink);
        if (result == null) result = caseRelationship(typeDeploymentLink);
        if (result == null) result = caseAbstractRelationship(typeDeploymentLink);
        if (result == null) result = caseCapellaElement(typeDeploymentLink);
        if (result == null) result = caseTraceableElement(typeDeploymentLink);
        if (result == null) result = casePublishableElement(typeDeploymentLink);
        if (result == null) result = caseModelElement(typeDeploymentLink);
        if (result == null) result = caseExtensibleElement(typeDeploymentLink);
        if (result == null) result = caseElement(typeDeploymentLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Component Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseComponentInstance(ComponentInstance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Connection Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Connection Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseConnectionInstance(ConnectionInstance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeploymentAspect(DeploymentAspect object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseDeploymentConfiguration(DeploymentConfiguration object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Instance Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Instance Deployment Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseInstanceDeploymentLink(InstanceDeploymentLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Part Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Part Deployment Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePartDeploymentLink(PartDeploymentLink object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Abstract Physical Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Abstract Physical Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseAbstractPhysicalInstance(AbstractPhysicalInstance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Port Instance</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Port Instance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T casePortInstance(PortInstance object) {
    return null;
  }

	/**
   * Returns the result of interpreting the object as an instance of '<em>Type Deployment Link</em>'.
   * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Deployment Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
	public T caseTypeDeploymentLink(TypeDeploymentLink object) {
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

} //DeploymentSwitch
