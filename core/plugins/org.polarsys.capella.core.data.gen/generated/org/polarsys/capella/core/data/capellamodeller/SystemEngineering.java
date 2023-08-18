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
package org.polarsys.capella.core.data.capellamodeller;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.AbstractModellingStructure;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Engineering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedOperationalAnalysis <em>Contained Operational Analysis</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSystemAnalysis <em>Contained System Analysis</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedLogicalArchitectures <em>Contained Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedPhysicalArchitectures <em>Contained Physical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedEPBSArchitectures <em>Contained EPBS Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellamodeller.SystemEngineering#getContainedSharedPkgs <em>Contained Shared Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='System Engineering'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.SystemEngineering'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='System engineering is an interdisciplinary approach encompassing the entire technical effort to evolve and verify an integrated and life-cycle balanced set of system people, product, and process solutions that satisfy customer needs.\r\nSystems engineering encompasses:\r\n- the technical efforts related to the development, manufacturing, verification, deployment, operations,\r\nsupport, disposal of, and user training for, systems products and processes;\r\n- the definition and management of the system configuration;\r\n- the translation of the system definition into work breakdown structures;\r\n- and development of information for management decision making\r\n[source:MIL-STD 499B standard]\r\n' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical, epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface SystemEngineering extends AbstractModellingStructure, ModelRoot {





	/**
   * Returns the value of the '<em><b>Contained Operational Analysis</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalAnalysis}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Operational Analysis</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Operational Analysis</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedOperationalAnalysis()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalAnalysis> getContainedOperationalAnalysis();







	/**
   * Returns the value of the '<em><b>Contained System Analysis</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemAnalysis}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained System Analysis</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained System Analysis</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedSystemAnalysis()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemAnalysis> getContainedSystemAnalysis();







	/**
   * Returns the value of the '<em><b>Contained Logical Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Logical Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Logical Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedLogicalArchitectures()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalArchitecture> getContainedLogicalArchitectures();







	/**
   * Returns the value of the '<em><b>Contained Physical Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Physical Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Physical Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedPhysicalArchitectures()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalArchitecture> getContainedPhysicalArchitectures();







	/**
   * Returns the value of the '<em><b>Contained EPBS Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.EPBSArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained EPBS Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained EPBS Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedEPBSArchitectures()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<EPBSArchitecture> getContainedEPBSArchitectures();







	/**
   * Returns the value of the '<em><b>Contained Shared Pkgs</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.sharedmodel.SharedPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Shared Pkgs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Shared Pkgs</em>' reference list.
   * @see org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage#getSystemEngineering_ContainedSharedPkgs()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='unimplemented' viatra.expression='ownedArchitectures as SharedPkg, but SharedPkg is not a subclass of ModellingArchitecture !'"
   * @generated
   */

	EList<SharedPkg> getContainedSharedPkgs();





} // SystemEngineering
