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
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.ModellingBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.information.DataPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.Block#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Block#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Block#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.Block#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getBlock()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Block'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A modular unit that describes the structure of a system or element.\r\n[source: SysML specification v1.1]' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a (abstract)' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::BehavioredClassifier' constraints='none'"
 * @generated
 */
public interface Block extends ModellingBlock, AbstractFunctionalBlock {





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
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlock_OwnedAbstractCapabilityPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='aspectPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain capabilities\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='Descendants are mapped to SysML::Blocks::Block, which cannot contain a Package.\r\nTherefore, store these AbstractCapabilityPackages in the nearest available package.' constraints='Multiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractCapabilityPkg getOwnedAbstractCapabilityPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Block#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}' containment reference.

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
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlock_OwnedInterfacePkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedInterfacePkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain interfaces\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	InterfacePkg getOwnedInterfacePkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Block#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}' containment reference.

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
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlock_OwnedDataPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDataPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to packages that contain data\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataPkg getOwnedDataPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.cs.Block#getOwnedDataPkg <em>Owned Data Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Data Pkg</em>' containment reference.
   * @see #getOwnedDataPkg()
   * @generated
   */

	void setOwnedDataPkg(DataPkg value);







	/**
   * Returns the value of the '<em><b>Owned State Machines</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateMachine}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned State Machines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned State Machines</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getBlock_OwnedStateMachines()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to related state machines\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::BehavioredClassifier::ownedBehavior' explanation='none' constraints='uml::BehavioredClassifier::ownedBehavior elements on which StateMachine stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<StateMachine> getOwnedStateMachines();





} // Block
