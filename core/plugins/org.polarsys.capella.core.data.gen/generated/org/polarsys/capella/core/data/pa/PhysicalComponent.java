/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.pa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
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
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponents <em>Owned Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedPhysicalComponentPkgs <em>Owned Physical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getOwnedLogicalComponentRealizations <em>Owned Logical Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalComponentRealizations <em>Logical Component Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getLogicalInterfaceRealizations <em>Logical Interface Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getSubPhysicalComponents <em>Sub Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents <em>Realized Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalComponents <em>Deploying Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getDeployingPhysicalActors <em>Deploying Physical Actors</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='PhysicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.PhysicalComponent'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical Components are the artifacts enabling to describe architectural solutions to satisfy the logical architecture identified at the upper abstraction level. Physical components are identified according to physical rationals (i.e. components reuse, available COTS, non functional constraints...)\r\nExamples: Software component, executable, hardware component (mechanical devices, electronical boards, equipments)' usage\040guideline='refer to description' arcadia_description='Physical Components are the artefacts enabling to describe the final physical decomposition of the system. Physical components are identified according to physical and development constraints.\r\nTwo kinds of physical components exist: behavioural and implementation components.\r\nTwo kinds of physical components are identified:\r\n- A behavioural component is a physical component in charge of implementing / realising part of the functions allocated to the system\r\nExample: software component, VHDL program (for a programmable device), hardware selector...\r\n- An implementation component  is a material physical component, resource embedding some behavioural components, and necessary to their expected behaviour.\r\nExample: Hardware computing board, computer, FPGA (programmable device), ...\r\n' usage\040examples='../img/usage_examples/example_physical_components.png' used\040in\040levels='physical' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='cannot map to uml::Component, which is not part of UML4SysML' constraints='none'"
 * @generated
 */
public interface PhysicalComponent extends AbstractPhysicalComponent, AbstractPhysicalArtifact {





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
	 * @generated
	 */

	EList<PhysicalComponentPkg> getOwnedPhysicalComponentPkgs();







	/**
	 * Returns the value of the '<em><b>Owned Logical Component Realizations</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalComponentRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Component Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Logical Component Realizations</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_OwnedLogicalComponentRealizations()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the logical component realization relationships that are stored/owned by this physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain packages, hence we find the nearest available package to store them.' constraints='none'"
	 * @generated
	 */

	EList<LogicalComponentRealization> getOwnedLogicalComponentRealizations();







	/**
	 * Returns the value of the '<em><b>Logical Component Realizations</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalComponentRealization}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Component Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Component Realizations</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_LogicalComponentRealizations()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the logical component realization links that involve this physical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalComponentRealization> getLogicalComponentRealizations();







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
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of realizations links coming from logical components, and in which this physical component is involved\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<LogicalComponent> getRealizedLogicalComponents();







	/**
	 * Returns the value of the '<em><b>Allocated Physical Functions</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalFunction}.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalComponents <em>Allocator Physical Components</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Physical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Physical Functions</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_AllocatedPhysicalFunctions()
	 * @see org.polarsys.capella.core.data.pa.PhysicalFunction#getAllocatorPhysicalComponents
	 * @model opposite="allocatorPhysicalComponents" transient="true" changeable="false" volatile="true" derived="true"
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
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalComponent> getDeployingPhysicalComponents();







	/**
	 * Returns the value of the '<em><b>Deploying Physical Actors</b></em>' reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalActor}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deploying Physical Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deploying Physical Actors</em>' reference list.
	 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalComponent_DeployingPhysicalActors()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	EList<PhysicalActor> getDeployingPhysicalActors();





} // PhysicalComponent
