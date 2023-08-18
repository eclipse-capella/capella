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
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.DeploymentTarget;
import org.polarsys.capella.core.data.la.LogicalComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getNature <em>Nature</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedDeploymentLinks <em>Owned Deployment Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponents <em>Owned Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalInterfaceRealizations <em>Logical Interface Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getSubPhysicalComponents <em>Sub Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents <em>Realized Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalComponents <em>Deploying Physical Components</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.PhysicalComponent'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical Components are the artifacts enabling to describe architectural solutions to satisfy the logical architecture identified at the upper abstraction level. Physical components are identified according to physical rationals (i.e. components reuse, available COTS, non functional constraints...)\r\nExamples: Software component, executable, hardware component (mechanical devices, electronical boards, equipments)' usage\040guideline='refer to description' arcadia_description='Physical Components are the artefacts enabling to describe the final physical decomposition of the system. Physical components are identified according to physical and development constraints.\r\nTwo kinds of physical components exist: behavioural and implementation components.\r\nTwo kinds of physical components are identified:\r\n- A behavioural component is a physical component in charge of implementing / realising part of the functions allocated to the system\r\nExample: software component, VHDL program (for a programmable device), hardware selector...\r\n- An implementation component  is a material physical component, resource embedding some behavioural components, and necessary to their expected behaviour.\r\nExample: Hardware computing board, computer, FPGA (programmable device), ...\r\n' usage\040examples='../img/usage_examples/example_physical_components.png' used\040in\040levels='physical' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='cannot map to uml::Component, which is not part of UML4SysML' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalComponent extends AbstractPhysicalArtifact, Component, CapabilityRealizationInvolvedElement, DeployableElement, DeploymentTarget {





	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.pa.PhysicalComponentKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.pa.PhysicalComponentKind
   * @see #setKind(PhysicalComponentKind)
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_Kind()
   * @model annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='type' fromStereotype='true' featureOwner='eng.AbstractPhysicalComponent'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the type of physical component (refer to PhysicalComponentKind for detailed description)\r\n[source: Capella study]' constraints='none' type='refer to PhysicalComponentKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	PhysicalComponentKind getKind();







	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.pa.PhysicalComponentKind
   * @see #getKind()
   * @generated
   */

	void setKind(PhysicalComponentKind value);







	/**
   * Returns the value of the '<em><b>Nature</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.pa.PhysicalComponentNature}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Nature</em>' attribute.
   * @see org.polarsys.capella.core.data.pa.PhysicalComponentNature
   * @see #setNature(PhysicalComponentNature)
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_Nature()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the nature of this physical component, typically whether it is an actual execution node, or a behavioral component like a SW part\r\n[source: Capella study]' constraints='none' type='refer to PhysicalComponentNature definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	PhysicalComponentNature getNature();







	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getNature <em>Nature</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nature</em>' attribute.
   * @see org.polarsys.capella.core.data.pa.PhysicalComponentNature
   * @see #getNature()
   * @generated
   */

	void setNature(PhysicalComponentNature value);







	/**
   * Returns the value of the '<em><b>Owned Deployment Links</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Deployment Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Deployment Links</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_OwnedDeploymentLinks()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the various deployments of this physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain AbstractDeployment\'s equivalent, hence we find the nearest available package to store them.' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractDeploymentLink> getOwnedDeploymentLinks();







	/**
   * Returns the value of the '<em><b>Owned Physical Components</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Components</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_OwnedPhysicalComponents()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the physical components stored under this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Class::nestedClassifier' explanation='the nesting relation is just convenient to store sub-components under a component in the three, even though the hierachical relationship between componenets is not\r\nderived from this nesting : instead, it relies on the Parts present in the component, that are typed by the sub-components types.' constraints='uml::Class::nestedClassifier elements on which PhysicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalComponent> getOwnedPhysicalComponents();







	/**
   * Returns the value of the '<em><b>Owned Physical Component Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponentPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Component Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Component Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_OwnedPhysicalComponentPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-(physical component) packages owned by this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain packages, hence we find the nearest available package to store them.' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalComponentPkg> getOwnedPhysicalComponentPkgs();







	/**
   * Returns the value of the '<em><b>Logical Interface Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalInterfaceRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Interface Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Logical Interface Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_LogicalInterfaceRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisionedInterfaceAllocations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of logical interfaces that this physical component reallizes\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalInterfaceRealization> getLogicalInterfaceRealizations();







	/**
   * Returns the value of the '<em><b>Sub Physical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Physical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_SubPhysicalComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subActors'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedPartitions.type'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the children components of this physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<PhysicalComponent> getSubPhysicalComponents();







	/**
   * Returns the value of the '<em><b>Realized Logical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizingPhysicalComponents <em>Realizing Physical Components</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Logical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Logical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_RealizedLogicalComponents()
   * @see org.polarsys.capella.core.data.la.LogicalComponent#getRealizingPhysicalComponents
   * @model opposite="realizingPhysicalComponents" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalComponent.outgoingTraces(self, lcr);\r\n\tLogicalComponentRealization.targetElement(lcr, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of realizations links coming from logical components, and in which this physical component is involved\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalComponent> getRealizedLogicalComponents();







	/**
   * Returns the value of the '<em><b>Allocated Physical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatingPhysicalComponents <em>Allocating Physical Components</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Physical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_AllocatedPhysicalFunctions()
   * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatingPhysicalComponents
   * @model opposite="allocatingPhysicalComponents" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalFunction> getAllocatedPhysicalFunctions();







	/**
   * Returns the value of the '<em><b>Deployed Physical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployed Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deployed Physical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_DeployedPhysicalComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Part.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, deployedPart);\r\n\tPart.abstractType(deployedPart, target);\r\n} or {\r\n\tPart.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalComponent> getDeployedPhysicalComponents();







	/**
   * Returns the value of the '<em><b>Deploying Physical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deploying Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Deploying Physical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_DeployingPhysicalComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='Part.abstractType(part, self);\r\n\tPart.deployingLinks.location(part, deployedPart);\r\n\tPart.abstractType(deployedPart, target);\r\n} or {\r\n\tPart.abstractType(part, self);\r\n\tPart.deploymentLinks.deployedElement(part, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalComponent> getDeployingPhysicalComponents();





} // PhysicalComponent
