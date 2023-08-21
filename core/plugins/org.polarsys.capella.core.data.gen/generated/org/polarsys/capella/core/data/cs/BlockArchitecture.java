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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisionedArchitectureAllocations <em>Provisioned Architecture Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getProvisioningArchitectureAllocations <em>Provisioning Architecture Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatedArchitectures <em>Allocated Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getAllocatingArchitectures <em>Allocating Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getSystem <em>System</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='BlockArchitecture'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Parent class for deriving specific architectures for each design phase\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Package' constraints='none'"
 * @generated
 */
public interface BlockArchitecture extends AbstractFunctionalArchitecture {





	/**
   * Returns the value of the '<em><b>Owned Abstract Capability Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Abstract Capability Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Abstract Capability Pkg</em>' containment reference.
   * @see #setOwnedAbstractCapabilityPkg(AbstractCapabilityPkg)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_OwnedAbstractCapabilityPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedAspectPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain capabilities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which AbstractCapabilityPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]'"
   * @generated
   */

	AbstractCapabilityPkg getOwnedAbstractCapabilityPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Abstract Capability Pkg</em>' containment reference.
   * @see #getOwnedAbstractCapabilityPkg()
   * @generated
   */

	void setOwnedAbstractCapabilityPkg(AbstractCapabilityPkg value);







	/**
   * Returns the value of the '<em><b>Owned Interface Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interface Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Interface Pkg</em>' containment reference.
   * @see #setOwnedInterfacePkg(InterfacePkg)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_OwnedInterfacePkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedInterfacePkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain interfaces\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	InterfacePkg getOwnedInterfacePkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Interface Pkg</em>' containment reference.
   * @see #getOwnedInterfacePkg()
   * @generated
   */

	void setOwnedInterfacePkg(InterfacePkg value);







	/**
   * Returns the value of the '<em><b>Owned Data Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Data Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Data Pkg</em>' containment reference.
   * @see #setOwnedDataPkg(DataPkg)
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_OwnedDataPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDataPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataPkg getOwnedDataPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.BlockArchitecture#getOwnedDataPkg <em>Owned Data Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Data Pkg</em>' containment reference.
   * @see #getOwnedDataPkg()
   * @generated
   */

	void setOwnedDataPkg(DataPkg value);







	/**
   * Returns the value of the '<em><b>Provisioned Architecture Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.ArchitectureAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatingArchitecture <em>Allocating Architecture</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioned Architecture Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provisioned Architecture Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_ProvisionedArchitectureAllocations()
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatingArchitecture
   * @model opposite="allocatingArchitecture" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='outgoingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocation links to other architectures\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ArchitectureAllocation> getProvisionedArchitectureAllocations();







	/**
   * Returns the value of the '<em><b>Provisioning Architecture Allocations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.ArchitectureAllocation}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatedArchitecture <em>Allocated Architecture</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provisioning Architecture Allocations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Provisioning Architecture Allocations</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_ProvisioningArchitectureAllocations()
   * @see org.polarsys.capella.core.data.cs.ArchitectureAllocation#getAllocatedArchitecture
   * @model opposite="allocatedArchitecture" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='incomingTraces'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) list of allocation links from other architectures to this one\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ArchitectureAllocation> getProvisioningArchitectureAllocations();







	/**
   * Returns the value of the '<em><b>Allocated Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.BlockArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_AllocatedArchitectures()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisionedArchitectureAllocations.allocatedArchitecture'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to the BlockArchitectures that are allocated from this one\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<BlockArchitecture> getAllocatedArchitectures();







	/**
   * Returns the value of the '<em><b>Allocating Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.BlockArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_AllocatingArchitectures()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisioningArchitectureAllocations.allocatingArchitecture'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) direct references to BlockArchitectures that allocate to this architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<BlockArchitecture> getAllocatingArchitectures();







	/**
   * Returns the value of the '<em><b>System</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>System</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlockArchitecture_System()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The system component of the architecture block.' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern BlockArchitecture__system(self : BlockArchitecture, target : Component) {\r\n\tfind SystemAnalysis__system(self, target);\r\n} or {\r\n\tfind LogicalArchitecture__system(self, target);\r\n} or {\r\n\tfind PhysicalArchitecture__system(self, target);\r\n} or {\r\n\tfind EPBSArchitecture__system(self, target);\r\n}\r\n\r\npattern SystemAnalysis__system(self : SystemAnalysis, target : SystemComponent) {\r\n\tSystemAnalysis.ownedSystemComponentPkg(self, pckg);\r\n\tSystemComponentPkg.ownedSystemComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern LogicalArchitecture__system(self : LogicalArchitecture, target : LogicalComponent) {\r\n\tLogicalArchitecture.ownedLogicalComponentPkg(self, pckg);\r\n\tLogicalComponentPkg.ownedLogicalComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern PhysicalArchitecture__system(self : PhysicalArchitecture, target : PhysicalComponent) {\r\n\tPhysicalArchitecture.ownedPhysicalComponentPkg(self, pckg);\r\n\tPhysicalComponentPkg.ownedPhysicalComponents(pckg, target);\r\n\tComponent.actor(target, false);\r\n}\r\n\r\npattern EPBSArchitecture__system(self : EPBSArchitecture, target : ConfigurationItem) {\r\n\tEPBSArchitecture.ownedConfigurationItemPkg(self, pckg);\r\n\tConfigurationItemPkg.ownedConfigurationItems(pckg, target);\r\n\tComponent.actor(target, false);\r\n}'"
   * @generated
   */

	Component getSystem();





} // BlockArchitecture
