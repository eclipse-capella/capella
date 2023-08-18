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
package org.polarsys.capella.core.data.oa;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.oa.OaPackage
 * @generated
 */
public interface OaFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	OaFactory eINSTANCE = org.polarsys.capella.core.data.oa.impl.OaFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Operational Analysis</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Analysis</em>'.
   * @generated
   */
	OperationalAnalysis createOperationalAnalysis();

	/**
   * Returns a new object of class '<em>Operational Activity Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Activity Pkg</em>'.
   * @generated
   */
	OperationalActivityPkg createOperationalActivityPkg();

	/**
   * Returns a new object of class '<em>Operational Activity</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Activity</em>'.
   * @generated
   */
	OperationalActivity createOperationalActivity();

	/**
   * Returns a new object of class '<em>Operational Process</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Process</em>'.
   * @generated
   */
	OperationalProcess createOperationalProcess();

	/**
   * Returns a new object of class '<em>Swimlane</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Swimlane</em>'.
   * @generated
   */
	Swimlane createSwimlane();

	/**
   * Returns a new object of class '<em>Operational Capability Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Capability Pkg</em>'.
   * @generated
   */
	OperationalCapabilityPkg createOperationalCapabilityPkg();

	/**
   * Returns a new object of class '<em>Operational Capability</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operational Capability</em>'.
   * @generated
   */
	OperationalCapability createOperationalCapability();

	/**
   * Returns a new object of class '<em>Activity Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Activity Allocation</em>'.
   * @generated
   */
	ActivityAllocation createActivityAllocation();

	/**
   * Returns a new object of class '<em>Role Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Role Pkg</em>'.
   * @generated
   */
	RolePkg createRolePkg();

	/**
   * Returns a new object of class '<em>Role</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Role</em>'.
   * @generated
   */
	Role createRole();

	/**
   * Returns a new object of class '<em>Role Assembly Usage</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Role Assembly Usage</em>'.
   * @generated
   */
	RoleAssemblyUsage createRoleAssemblyUsage();

	/**
   * Returns a new object of class '<em>Role Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Role Allocation</em>'.
   * @generated
   */
	RoleAllocation createRoleAllocation();

	/**
   * Returns a new object of class '<em>Entity Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Entity Pkg</em>'.
   * @generated
   */
	EntityPkg createEntityPkg();

	/**
   * Returns a new object of class '<em>Entity</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Entity</em>'.
   * @generated
   */
	Entity createEntity();

	/**
   * Returns a new object of class '<em>Concept Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Concept Pkg</em>'.
   * @generated
   */
	ConceptPkg createConceptPkg();

	/**
   * Returns a new object of class '<em>Concept</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Concept</em>'.
   * @generated
   */
	Concept createConcept();

	/**
   * Returns a new object of class '<em>Concept Compliance</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Concept Compliance</em>'.
   * @generated
   */
	ConceptCompliance createConceptCompliance();

	/**
   * Returns a new object of class '<em>Item In Concept</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Item In Concept</em>'.
   * @generated
   */
	ItemInConcept createItemInConcept();

	/**
   * Returns a new object of class '<em>Community Of Interest</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Community Of Interest</em>'.
   * @generated
   */
	CommunityOfInterest createCommunityOfInterest();

	/**
   * Returns a new object of class '<em>Community Of Interest Composition</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Community Of Interest Composition</em>'.
   * @generated
   */
	CommunityOfInterestComposition createCommunityOfInterestComposition();

	/**
   * Returns a new object of class '<em>Organisational Unit</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Organisational Unit</em>'.
   * @generated
   */
	OrganisationalUnit createOrganisationalUnit();

	/**
   * Returns a new object of class '<em>Organisational Unit Composition</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Organisational Unit Composition</em>'.
   * @generated
   */
	OrganisationalUnitComposition createOrganisationalUnitComposition();

	/**
   * Returns a new object of class '<em>Location</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Location</em>'.
   * @generated
   */
	Location createLocation();

	/**
   * Returns a new object of class '<em>Capability Configuration</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Configuration</em>'.
   * @generated
   */
	CapabilityConfiguration createCapabilityConfiguration();

	/**
   * Returns a new object of class '<em>Communication Mean</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Communication Mean</em>'.
   * @generated
   */
	CommunicationMean createCommunicationMean();

	/**
   * Returns a new object of class '<em>Entity Operational Capability Involvement</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Entity Operational Capability Involvement</em>'.
   * @generated
   */
	EntityOperationalCapabilityInvolvement createEntityOperationalCapabilityInvolvement();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	OaPackage getOaPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalAnalysis createOperationalAnalysis(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalActivityPkg createOperationalActivityPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalActivity createOperationalActivity(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalProcess createOperationalProcess(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Swimlane createSwimlane(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalCapabilityPkg createOperationalCapabilityPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OperationalCapability createOperationalCapability(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	RolePkg createRolePkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Role createRole(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	RoleAssemblyUsage createRoleAssemblyUsage(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	EntityPkg createEntityPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Entity createEntity(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ConceptPkg createConceptPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Concept createConcept(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ItemInConcept createItemInConcept(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CommunityOfInterest createCommunityOfInterest(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CommunityOfInterestComposition createCommunityOfInterestComposition(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OrganisationalUnit createOrganisationalUnit(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	OrganisationalUnitComposition createOrganisationalUnitComposition(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Location createLocation(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CapabilityConfiguration createCapabilityConfiguration(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CommunicationMean createCommunicationMean(String name_p);

	//begin-capella-code
	//end-capella-code
} //OaFactory
