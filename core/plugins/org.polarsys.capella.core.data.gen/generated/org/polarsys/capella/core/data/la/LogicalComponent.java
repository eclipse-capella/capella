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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponents <em>Owned Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getSubLogicalComponents <em>Sub Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizedSystemComponents <em>Realized System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.LogicalComponent#getRealizingPhysicalComponents <em>Realizing Physical Components</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='LogicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component' stereotype='eng.LogicalComponent'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Logical Components are the artifacts enabling decomposition of the system as a \"white box\", independently from any technological solutions. Logical components are identified according to logical abstractions (i.e. functional grouping, logical interfaces)' usage\040guideline='n/a' arcadia_description='Logical Components are the artefacts enabling a notional decomposition of the system as a \"white box\", independently from any technological solutions, but dealing with major system decomposition constraints.' used\040in\040levels='logical' usage\040examples='../img/usage_examples/example_logicalcomponent.png' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='cannot map to uml::Component since this mapping is for a SysML profile, and uml::Component is not part of UML4SysML' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface LogicalComponent extends Component, CapabilityRealizationInvolvedElement {





	/**
   * Returns the value of the '<em><b>Owned Logical Components</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Components</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_OwnedLogicalComponents()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subLogicalComponents'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='children logical components of this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Class::nestedClassifier' explanation='the nesting relation is just convenient to store sub-components under a component in the three, even though the hierachical relationship between componenets is not\r\nderived from this nesting : instead, it relies on the Parts present in the component, that are typed by the sub-components types.' constraints='uml::Class::nestedClassifier elements on which LogicalComponent stereotype or any stereotype that inherits from it is applied\r\nOrder will not be preserved'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<LogicalComponent> getOwnedLogicalComponents();







	/**
   * Returns the value of the '<em><b>Owned Logical Architectures</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalArchitecture}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Architectures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Architectures</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_OwnedLogicalArchitectures()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedLogicalArchitectures'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the various logical architecture (alternatives) associated to this component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain LogicalArchitecture\'s equivalent, hence we find the nearest available package to store them.' constraints='none'"
   * @generated
   */

	EList<LogicalArchitecture> getOwnedLogicalArchitectures();







	/**
   * Returns the value of the '<em><b>Owned Logical Component Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponentPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Logical Component Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Logical Component Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_OwnedLogicalComponentPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedLogicalComponentPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='logical component packages contained in this logical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='SysML::Blocks::Block cannot contain packages, hence we find the nearest available package to store them.' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<LogicalComponentPkg> getOwnedLogicalComponentPkgs();







	/**
   * Returns the value of the '<em><b>Sub Logical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Logical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Logical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_SubLogicalComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subActors'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='ownedPartitions.type'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the children components of this logical component\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<LogicalComponent> getSubLogicalComponents();







	/**
   * Returns the value of the '<em><b>Allocated Logical Functions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.la.LogicalFunction}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.la.LogicalFunction#getAllocatingLogicalComponents <em>Allocating Logical Components</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Logical Functions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Logical Functions</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_AllocatedLogicalFunctions()
   * @see org.polarsys.capella.core.data.la.LogicalFunction#getAllocatingLogicalComponents
   * @model opposite="allocatingLogicalComponents" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatedFunctions'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<LogicalFunction> getAllocatedLogicalFunctions();







	/**
   * Returns the value of the '<em><b>Realized System Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized System Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized System Components</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_RealizedSystemComponents()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='System Components that are realized by this Logical Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='realizedComponents'"
   * @generated
   */

	EList<SystemComponent> getRealizedSystemComponents();







	/**
   * Returns the value of the '<em><b>Realizing Physical Components</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.pa.PhysicalComponent}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents <em>Realized Logical Components</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Physical Components</em>' reference list.
   * @see org.polarsys.capella.core.data.la.LaPackage#getLogicalComponent_RealizingPhysicalComponents()
   * @see org.polarsys.capella.core.data.pa.PhysicalComponent#getRealizedLogicalComponents
   * @model opposite="realizedLogicalComponents" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical Components that realize this Logical Component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='allocatingComponents'"
   * @generated
   */

	EList<PhysicalComponent> getRealizingPhysicalComponents();





} // LogicalComponent
