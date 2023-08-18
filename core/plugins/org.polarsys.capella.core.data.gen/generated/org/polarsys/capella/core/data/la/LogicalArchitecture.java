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
package org.polarsys.capella.core.data.la;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedLogicalComponentPkg <em>Owned Logical Component Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getContainedLogicalFunctionPkg <em>Contained Logical Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedSystemAnalysisRealizations <em>Owned System Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalysisRealizations <em>Allocated System Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatedSystemAnalyses <em>Allocated System Analyses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getAllocatingPhysicalArchitectures <em>Allocating Physical Architectures</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Logical Architecture'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.LogicalArchitecture'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Model describing logical architecture part (i.e. Independent from technological choices) - behavioural components &amp; related items - associated to (created during) a modelling phase' usage\040guideline='n/a' used\040in\040levels='logical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LogicalArchitecture extends ComponentArchitecture {





	/**
   * Returns the value of the '<em><b>Owned Logical Component Pkg</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Component Pkg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Component Pkg</em>' containment reference.
   * @see #setOwnedLogicalComponentPkg(LogicalComponentPkg)
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_OwnedLogicalComponentPkg()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedLogicalComponentPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Link to the package that contains logical components\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which LogicalComponentPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	LogicalComponentPkg getOwnedLogicalComponentPkg();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.la.LogicalArchitecture#getOwnedLogicalComponentPkg <em>Owned Logical Component Pkg</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Logical Component Pkg</em>' containment reference.
   * @see #getOwnedLogicalComponentPkg()
   * @generated
   */

	void setOwnedLogicalComponentPkg(LogicalComponentPkg value);







	/**
   * Returns the value of the '<em><b>Contained Capability Realization Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Capability Realization Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Capability Realization Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_ContainedCapabilityRealizationPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedAbstractCapabilityPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	CapabilityRealizationPkg getContainedCapabilityRealizationPkg();







	/**
   * Returns the value of the '<em><b>Contained Logical Function Pkg</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Logical Function Pkg</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Contained Logical Function Pkg</em>' reference.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_ContainedLogicalFunctionPkg()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/semantic feature='ownedFunctionPkg'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	LogicalFunctionPkg getContainedLogicalFunctionPkg();







	/**
   * Returns the value of the '<em><b>Owned System Analysis Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.SystemAnalysisRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Analysis Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Analysis Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_OwnedSystemAnalysisRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Set of system analysis realization links that are owned/contained by the logical architecture\r\n[source:Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which ContextArchitectureRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<SystemAnalysisRealization> getOwnedSystemAnalysisRealizations();







	/**
   * Returns the value of the '<em><b>Allocated System Analysis Realizations</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.SystemAnalysisRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated System Analysis Realizations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated System Analysis Realizations</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_AllocatedSystemAnalysisRealizations()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='allocatedLogicalArchitectureImplementations'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived derive='self.ownedPartitions.representedElement.oclIsKindOf(PhysicalComponent) -&gt; oclAsType(PhysicalComponent)' viatra.variant='alias' viatra.expression='provisionedArchitectureAllocations'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the realisation links from system analysis that point to this logical architecture\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<SystemAnalysisRealization> getAllocatedSystemAnalysisRealizations();







	/**
   * Returns the value of the '<em><b>Allocated System Analyses</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemAnalysis}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatingLogicalArchitectures <em>Allocating Logical Architectures</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated System Analyses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated System Analyses</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_AllocatedSystemAnalyses()
   * @see org.polarsys.capella.core.data.ctx.SystemAnalysis#getAllocatingLogicalArchitectures
   * @model opposite="allocatingLogicalArchitectures" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemAnalysis> getAllocatedSystemAnalyses();







	/**
   * Returns the value of the '<em><b>Allocating Physical Architectures</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalArchitecture}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectures <em>Allocated Logical Architectures</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocating Physical Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocating Physical Architectures</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalArchitecture_AllocatingPhysicalArchitectures()
   * @see org.polarsys.capella.core.data.pa.PhysicalArchitecture#getAllocatedLogicalArchitectures
   * @model opposite="allocatedLogicalArchitectures" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatingArchitectures'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<PhysicalArchitecture> getAllocatingPhysicalArchitectures();





} // LogicalArchitecture
