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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Analysis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemComponentPkg <em>Owned System Component Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedMissionPkg <em>Owned Mission Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedCapabilityPkg <em>Contained Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getContainedSystemFunctionPkg <em>Contained System Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedOperationalAnalysisRealizations <em>Owned Operational Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalysisRealizations <em>Allocated Operational Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatedOperationalAnalyses <em>Allocated Operational Analyses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatingLogicalArchitectures <em>Allocating Logical Architectures</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='System Analysis'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.ContextArchitecture'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Model describing functional and non-functional issues - functions &amp; related items - associated to (created during) a modelling phase' usage\040guideline='n/a' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface SystemAnalysis extends ComponentArchitecture {





	/**
   * Returns the value of the '<em><b>Owned System Component Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Component Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Component Pkg</em>' containment reference.
   * @see #setOwnedSystemComponentPkg(SystemComponentPkg)
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_OwnedSystemComponentPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to a package that contains System Components' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	SystemComponentPkg getOwnedSystemComponentPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedSystemComponentPkg <em>Owned System Component Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned System Component Pkg</em>' containment reference.
   * @see #getOwnedSystemComponentPkg()
   * @generated
   */

	void setOwnedSystemComponentPkg(SystemComponentPkg value);




	/**
   * Returns the value of the '<em><b>Owned Mission Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Mission Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Mission Pkg</em>' containment reference.
   * @see #setOwnedMissionPkg(MissionPkg)
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_OwnedMissionPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedMissionPkg'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the package that contains system analysis missions\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which MissionPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	MissionPkg getOwnedMissionPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getOwnedMissionPkg <em>Owned Mission Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Mission Pkg</em>' containment reference.
   * @see #getOwnedMissionPkg()
   * @generated
   */

	void setOwnedMissionPkg(MissionPkg value);







	/**
   * Returns the value of the '<em><b>Contained Capability Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Capability Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Capability Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_ContainedCapabilityPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	CapabilityPkg getContainedCapabilityPkg();







	/**
   * Returns the value of the '<em><b>Contained System Function Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained System Function Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained System Function Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_ContainedSystemFunctionPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	SystemFunctionPkg getContainedSystemFunctionPkg();







	/**
   * Returns the value of the '<em><b>Owned Operational Analysis Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Operational Analysis Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Operational Analysis Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_OwnedOperationalAnalysisRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the realization links between Operational analysis and System analysis that are owned by this System analysis element\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which OperationalAnalysisRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<OperationalAnalysisRealization> getOwnedOperationalAnalysisRealizations();







	/**
   * Returns the value of the '<em><b>Allocated Operational Analysis Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Operational Analysis Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Operational Analysis Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_AllocatedOperationalAnalysisRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='provisionedArchitectureAllocations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) reference to operational analysis elements that this system analysis is realizing\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<OperationalAnalysisRealization> getAllocatedOperationalAnalysisRealizations();







	/**
   * Returns the value of the '<em><b>Allocated Operational Analyses</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.oa.OperationalAnalysis}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.oa.OperationalAnalysis#getAllocatingSystemAnalyses <em>Allocating System Analyses</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Operational Analyses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Operational Analyses</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_AllocatedOperationalAnalyses()
   * @see org.polarsys.capella.core.data.oa.OperationalAnalysis#getAllocatingSystemAnalyses
   * @model opposite="allocatingSystemAnalyses" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<OperationalAnalysis> getAllocatedOperationalAnalyses();







	/**
   * Returns the value of the '<em><b>Allocating Logical Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalArchitecture}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalyses <em>Allocated System Analyses</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Logical Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Logical Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemAnalysis_AllocatingLogicalArchitectures()
   * @see org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalyses
   * @model opposite="allocatedSystemAnalyses" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatingArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<LogicalArchitecture> getAllocatingLogicalArchitectures();





} // SystemAnalysis
