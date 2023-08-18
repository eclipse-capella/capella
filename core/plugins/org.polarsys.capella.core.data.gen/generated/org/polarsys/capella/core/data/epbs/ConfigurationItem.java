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
package org.polarsys.capella.core.data.epbs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact;
import org.polarsys.capella.core.data.cs.Component;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getItemIdentifier <em>Item Identifier</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItems <em>Owned Configuration Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedConfigurationItemPkgs <em>Owned Configuration Item Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getOwnedPhysicalArtifactRealizations <em>Owned Physical Artifact Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getAllocatedPhysicalArtifacts <em>Allocated Physical Artifacts</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ConfigurationItem'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Component'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Aggregation of hardware, software, processed materials, services, or any of their discrete portions designated for configuration management and treated as a single entity in the configuration management process.' usage\040guideline='A configuration item is an abstract concept. Concrete concepts are : COTCI, CSCI, HWCI, InterfaceCI, NDICI, PrimeItemCI and SystemCI' used\040in\040levels='epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none' arcadia_description='A configuration item (CI) is a part of the system, to be \r\n- Designed and produced, or purchased\r\n- Duplicated as much as it is used in the system\r\n- assembled with others \r\nin order to build each copy of the system. \r\nExamples of configuration items are cabinets, racks, electronic boards, wiring &amp; plugs, software components...\r\nCI are usually qualified as Hardware (HWCI), Computer Software (CSCI), Commercial off the Shelf (COTS, purchased item), Prime Item...\r\n'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Blocks::Block' explanation='Could have been mapped to Package (to be closer to the semantic of a \"group of\" physical components, \r\nbut it is not possible since there are Parts associated to CI\'s, and packages do not inherit from Type, hence cannot be used to type a Part.' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface ConfigurationItem extends CapabilityRealizationInvolvedElement, Component {





	/**
   * Returns the value of the '<em><b>Item Identifier</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Item Identifier</em>' attribute.
   * @see #setItemIdentifier(String)
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_ItemIdentifier()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	String getItemIdentifier();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getItemIdentifier <em>Item Identifier</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Item Identifier</em>' attribute.
   * @see #getItemIdentifier()
   * @generated
   */

	void setItemIdentifier(String value);







	/**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The default value is <code>"Unset"</code>.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.epbs.ConfigurationItemKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
   * @see #setKind(ConfigurationItemKind)
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_Kind()
   * @model default="Unset"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ConfigurationItemKind getKind();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.epbs.ConfigurationItem#getKind <em>Kind</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see org.polarsys.capella.core.data.epbs.ConfigurationItemKind
   * @see #getKind()
   * @generated
   */

	void setKind(ConfigurationItemKind value);







	/**
   * Returns the value of the '<em><b>Owned Configuration Items</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.ConfigurationItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Configuration Items</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Configuration Items</em>' containment reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_OwnedConfigurationItems()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedConfigurationItems'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the children of this ConfigurationItem \r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Class::nestedClassifier' explanation='none' constraints='uml::Class::nestedClassifier elements on which ConfigurationItem stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConfigurationItem> getOwnedConfigurationItems();







	/**
   * Returns the value of the '<em><b>Owned Configuration Item Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.ConfigurationItemPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Configuration Item Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Configuration Item Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_OwnedConfigurationItemPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Component'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedConfigurationItemPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the sub-(configuration item) packages owned by this component' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::nearestpackage' explanation='none' constraints='store them in the nearest possible package, since a Block cannot contain packages'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ConfigurationItemPkg> getOwnedConfigurationItemPkgs();







	/**
   * Returns the value of the '<em><b>Owned Physical Artifact Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.epbs.PhysicalArtifactRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Artifact Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Artifact Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_OwnedPhysicalArtifactRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Physical Artifact Realization links owned by this Configuration Item' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalArtifactRealization> getOwnedPhysicalArtifactRealizations();




	/**
   * Returns the value of the '<em><b>Allocated Physical Artifacts</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact#getAllocatorConfigurationItems <em>Allocator Configuration Items</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Physical Artifacts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Allocated Physical Artifacts</em>' reference list.
   * @see org.polarsys.capella.core.data.epbs.EpbsPackage#getConfigurationItem_AllocatedPhysicalArtifacts()
   * @see org.polarsys.capella.core.data.cs.AbstractPhysicalArtifact#getAllocatorConfigurationItems
   * @model opposite="allocatorConfigurationItems" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalArtifactRealization.sourceElement(par, self);\r\nPhysicalArtifactRealization.targetElement(par, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='(automatically computed) the list of realizations links coming from physical artifacts, and in which this ConfigurationItem is involved\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<AbstractPhysicalArtifact> getAllocatedPhysicalArtifacts();





} // ConfigurationItem
