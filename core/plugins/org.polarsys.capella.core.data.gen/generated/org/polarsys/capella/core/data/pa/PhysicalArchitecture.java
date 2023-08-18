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
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponentPkg <em>Owned Physical Component Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getContainedPhysicalFunctionPkg <em>Contained Physical Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedDeployments <em>Owned Deployments</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedLogicalArchitectureRealizations <em>Owned Logical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectureRealizations <em>Allocated Logical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectures <em>Allocated Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatingEpbsArchitectures <em>Allocating Epbs Architectures</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Physical Architecture'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.PhysicalArchitecture'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Model describing physical architecture part - hardware components &amp; related items -  associated to (created during) a modelling phase' usage\040guideline='n/a' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalArchitecture extends ComponentArchitecture {





	/**
   * Returns the value of the '<em><b>Owned Physical Component Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Component Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Component Pkg</em>' containment reference.
   * @see #setOwnedPhysicalComponentPkg(PhysicalComponentPkg)
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_OwnedPhysicalComponentPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedComponentPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a package containing the physical components involved in this physical architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which PhysicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	PhysicalComponentPkg getOwnedPhysicalComponentPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getOwnedPhysicalComponentPkg <em>Owned Physical Component Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Physical Component Pkg</em>' containment reference.
   * @see #getOwnedPhysicalComponentPkg()
   * @generated
   */

	void setOwnedPhysicalComponentPkg(PhysicalComponentPkg value);







	/**
   * Returns the value of the '<em><b>Contained Capability Realization Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Capability Realization Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Capability Realization Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_ContainedCapabilityRealizationPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	CapabilityRealizationPkg getContainedCapabilityRealizationPkg();







	/**
   * Returns the value of the '<em><b>Contained Physical Function Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Physical Function Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Physical Function Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_ContainedPhysicalFunctionPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	PhysicalFunctionPkg getContainedPhysicalFunctionPkg();







	/**
   * Returns the value of the '<em><b>Owned Deployments</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractDeploymentLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Deployments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Deployments</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_OwnedDeployments()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDeployments'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the various deployments associated with this physical architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which AbstractDeployment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractDeploymentLink> getOwnedDeployments();







	/**
   * Returns the value of the '<em><b>Owned Logical Architecture Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalArchitectureRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Architecture Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Architecture Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_OwnedLogicalArchitectureRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of a relationships between physical architectures and the logical architectures that they realize, stored/owned by this architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which LogicalArchitectureRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<LogicalArchitectureRealization> getOwnedLogicalArchitectureRealizations();







	/**
   * Returns the value of the '<em><b>Allocated Logical Architecture Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.LogicalArchitectureRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Logical Architecture Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Logical Architecture Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_AllocatedLogicalArchitectureRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='allocatedLogicalArchitectureImplementations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived derive='self.ownedPartitions.representedElement.oclIsKindOf(PhysicalComponent) -&gt; oclAsType(PhysicalComponent)' viatra.variant='alias' viatra.expression='provisionedArchitectureAllocations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of relationships between this physical architecture and the logical architectures to which it is allocated\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalArchitectureRealization> getAllocatedLogicalArchitectureRealizations();







	/**
   * Returns the value of the '<em><b>Allocated Logical Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalArchitecture}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatingPhysicalArchitectures <em>Allocating Physical Architectures</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Logical Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Logical Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_AllocatedLogicalArchitectures()
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatingPhysicalArchitectures
   * @model opposite="allocatingPhysicalArchitectures" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<LogicalArchitecture> getAllocatedLogicalArchitectures();







	/**
   * Returns the value of the '<em><b>Allocating Epbs Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.EPBSArchitecture}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectures <em>Allocated Physical Architectures</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Epbs Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Epbs Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.pa.PaPackage#getPhysicalArchitecture_AllocatingEpbsArchitectures()
   * @see org.polarsys.capella.core.data.epbs.EPBSArchitecture#getAllocatedPhysicalArchitectures
   * @model opposite="allocatedPhysicalArchitectures" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatingArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<EPBSArchitecture> getAllocatingEpbsArchitectures();





} // PhysicalArchitecture
