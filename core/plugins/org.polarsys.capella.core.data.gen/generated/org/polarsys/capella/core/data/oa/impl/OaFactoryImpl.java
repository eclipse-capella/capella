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
package org.polarsys.capella.core.data.oa.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.oa.*;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CapabilityConfiguration;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.CommunityOfInterest;
import org.polarsys.capella.core.data.oa.CommunityOfInterestComposition;
import org.polarsys.capella.core.data.oa.Concept;
import org.polarsys.capella.core.data.oa.ConceptCompliance;
import org.polarsys.capella.core.data.oa.ConceptPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.ItemInConcept;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.OperationalProcess;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RoleAssemblyUsage;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.data.oa.Swimlane;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OaFactoryImpl extends EFactoryImpl implements OaFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static OaFactory init() {
    try {
      OaFactory theOaFactory = (OaFactory)EPackage.Registry.INSTANCE.getEFactory(OaPackage.eNS_URI);
      if (theOaFactory != null) {
        return theOaFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new OaFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OaFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case OaPackage.OPERATIONAL_ANALYSIS: return createOperationalAnalysis();
      case OaPackage.OPERATIONAL_ACTIVITY_PKG: return createOperationalActivityPkg();
      case OaPackage.OPERATIONAL_ACTIVITY: return createOperationalActivity();
      case OaPackage.OPERATIONAL_PROCESS: return createOperationalProcess();
      case OaPackage.SWIMLANE: return createSwimlane();
      case OaPackage.OPERATIONAL_CAPABILITY_PKG: return createOperationalCapabilityPkg();
      case OaPackage.OPERATIONAL_CAPABILITY: return createOperationalCapability();
      case OaPackage.ACTIVITY_ALLOCATION: return createActivityAllocation();
      case OaPackage.ROLE_PKG: return createRolePkg();
      case OaPackage.ROLE: return createRole();
      case OaPackage.ROLE_ASSEMBLY_USAGE: return createRoleAssemblyUsage();
      case OaPackage.ROLE_ALLOCATION: return createRoleAllocation();
      case OaPackage.ENTITY_PKG: return createEntityPkg();
      case OaPackage.ENTITY: return createEntity();
      case OaPackage.CONCEPT_PKG: return createConceptPkg();
      case OaPackage.CONCEPT: return createConcept();
      case OaPackage.CONCEPT_COMPLIANCE: return createConceptCompliance();
      case OaPackage.ITEM_IN_CONCEPT: return createItemInConcept();
      case OaPackage.COMMUNITY_OF_INTEREST: return createCommunityOfInterest();
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION: return createCommunityOfInterestComposition();
      case OaPackage.ORGANISATIONAL_UNIT: return createOrganisationalUnit();
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION: return createOrganisationalUnitComposition();
      case OaPackage.LOCATION: return createLocation();
      case OaPackage.CAPABILITY_CONFIGURATION: return createCapabilityConfiguration();
      case OaPackage.COMMUNICATION_MEAN: return createCommunicationMean();
      case OaPackage.ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENT: return createEntityOperationalCapabilityInvolvement();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalAnalysis createOperationalAnalysis() {
    OperationalAnalysisImpl operationalAnalysis = new OperationalAnalysisImpl();
    //begin-capella-code
    operationalAnalysis.setId(IdGenerator.createId());
    //end-capella-code
    return operationalAnalysis;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalActivityPkg createOperationalActivityPkg() {
    OperationalActivityPkgImpl operationalActivityPkg = new OperationalActivityPkgImpl();
    //begin-capella-code
    operationalActivityPkg.setId(IdGenerator.createId());
    //end-capella-code
    return operationalActivityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalActivity createOperationalActivity() {
    OperationalActivityImpl operationalActivity = new OperationalActivityImpl();
    //begin-capella-code
    operationalActivity.setId(IdGenerator.createId());
    //end-capella-code
    return operationalActivity;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalProcess createOperationalProcess() {
    OperationalProcessImpl operationalProcess = new OperationalProcessImpl();
    //begin-capella-code
    operationalProcess.setId(IdGenerator.createId());
    //end-capella-code
    return operationalProcess;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Swimlane createSwimlane() {
    SwimlaneImpl swimlane = new SwimlaneImpl();
    //begin-capella-code
    swimlane.setId(IdGenerator.createId());
    //end-capella-code
    return swimlane;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalCapabilityPkg createOperationalCapabilityPkg() {
    OperationalCapabilityPkgImpl operationalCapabilityPkg = new OperationalCapabilityPkgImpl();
    //begin-capella-code
    operationalCapabilityPkg.setId(IdGenerator.createId());
    //end-capella-code
    return operationalCapabilityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationalCapability createOperationalCapability() {
    OperationalCapabilityImpl operationalCapability = new OperationalCapabilityImpl();
    //begin-capella-code
    operationalCapability.setId(IdGenerator.createId());
    //end-capella-code
    return operationalCapability;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ActivityAllocation createActivityAllocation() {
    ActivityAllocationImpl activityAllocation = new ActivityAllocationImpl();
    //begin-capella-code
    activityAllocation.setId(IdGenerator.createId());
    //end-capella-code
    return activityAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public RolePkg createRolePkg() {
    RolePkgImpl rolePkg = new RolePkgImpl();
    //begin-capella-code
    rolePkg.setId(IdGenerator.createId());
    //end-capella-code
    return rolePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Role createRole() {
    RoleImpl role = new RoleImpl();
    //begin-capella-code
    role.setId(IdGenerator.createId());
    //end-capella-code
    return role;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public RoleAssemblyUsage createRoleAssemblyUsage() {
    RoleAssemblyUsageImpl roleAssemblyUsage = new RoleAssemblyUsageImpl();
    //begin-capella-code
    roleAssemblyUsage.setId(IdGenerator.createId());
    //end-capella-code
    return roleAssemblyUsage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public RoleAllocation createRoleAllocation() {
    RoleAllocationImpl roleAllocation = new RoleAllocationImpl();
    //begin-capella-code
    roleAllocation.setId(IdGenerator.createId());
    //end-capella-code
    return roleAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EntityPkg createEntityPkg() {
    EntityPkgImpl entityPkg = new EntityPkgImpl();
    //begin-capella-code
    entityPkg.setId(IdGenerator.createId());
    //end-capella-code
    return entityPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Entity createEntity() {
    EntityImpl entity = new EntityImpl();
    //begin-capella-code
    entity.setId(IdGenerator.createId());
    //end-capella-code
    return entity;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ConceptPkg createConceptPkg() {
    ConceptPkgImpl conceptPkg = new ConceptPkgImpl();
    //begin-capella-code
    conceptPkg.setId(IdGenerator.createId());
    //end-capella-code
    return conceptPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Concept createConcept() {
    ConceptImpl concept = new ConceptImpl();
    //begin-capella-code
    concept.setId(IdGenerator.createId());
    //end-capella-code
    return concept;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ConceptCompliance createConceptCompliance() {
    ConceptComplianceImpl conceptCompliance = new ConceptComplianceImpl();
    //begin-capella-code
    conceptCompliance.setId(IdGenerator.createId());
    //end-capella-code
    return conceptCompliance;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ItemInConcept createItemInConcept() {
    ItemInConceptImpl itemInConcept = new ItemInConceptImpl();
    //begin-capella-code
    itemInConcept.setId(IdGenerator.createId());
    //end-capella-code
    return itemInConcept;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunityOfInterest createCommunityOfInterest() {
    CommunityOfInterestImpl communityOfInterest = new CommunityOfInterestImpl();
    //begin-capella-code
    communityOfInterest.setId(IdGenerator.createId());
    //end-capella-code
    return communityOfInterest;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunityOfInterestComposition createCommunityOfInterestComposition() {
    CommunityOfInterestCompositionImpl communityOfInterestComposition = new CommunityOfInterestCompositionImpl();
    //begin-capella-code
    communityOfInterestComposition.setId(IdGenerator.createId());
    //end-capella-code
    return communityOfInterestComposition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OrganisationalUnit createOrganisationalUnit() {
    OrganisationalUnitImpl organisationalUnit = new OrganisationalUnitImpl();
    //begin-capella-code
    organisationalUnit.setId(IdGenerator.createId());
    //end-capella-code
    return organisationalUnit;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OrganisationalUnitComposition createOrganisationalUnitComposition() {
    OrganisationalUnitCompositionImpl organisationalUnitComposition = new OrganisationalUnitCompositionImpl();
    //begin-capella-code
    organisationalUnitComposition.setId(IdGenerator.createId());
    //end-capella-code
    return organisationalUnitComposition;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Location createLocation() {
    LocationImpl location = new LocationImpl();
    //begin-capella-code
    location.setId(IdGenerator.createId());
    //end-capella-code
    return location;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapabilityConfiguration createCapabilityConfiguration() {
    CapabilityConfigurationImpl capabilityConfiguration = new CapabilityConfigurationImpl();
    //begin-capella-code
    capabilityConfiguration.setId(IdGenerator.createId());
    //end-capella-code
    return capabilityConfiguration;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunicationMean createCommunicationMean() {
    CommunicationMeanImpl communicationMean = new CommunicationMeanImpl();
    //begin-capella-code
    communicationMean.setId(IdGenerator.createId());
    //end-capella-code
    return communicationMean;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EntityOperationalCapabilityInvolvement createEntityOperationalCapabilityInvolvement() {
    EntityOperationalCapabilityInvolvementImpl entityOperationalCapabilityInvolvement = new EntityOperationalCapabilityInvolvementImpl();
    //begin-capella-code
    entityOperationalCapabilityInvolvement.setId(IdGenerator.createId());
    //end-capella-code
    return entityOperationalCapabilityInvolvement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OaPackage getOaPackage() {
    return (OaPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static OaPackage getPackage() {
    return OaPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalAnalysis createOperationalAnalysis(String name_p) {
    OperationalAnalysis operationalAnalysis = createOperationalAnalysis();
    operationalAnalysis.setName(name_p);	  
    return operationalAnalysis;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalActivityPkg createOperationalActivityPkg(String name_p) {
    OperationalActivityPkg operationalActivityPkg = createOperationalActivityPkg();
    operationalActivityPkg.setName(name_p);	  
    return operationalActivityPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalActivity createOperationalActivity(String name_p) {
    OperationalActivity operationalActivity = createOperationalActivity();
    operationalActivity.setName(name_p);	  
    return operationalActivity;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalProcess createOperationalProcess(String name_p) {
    OperationalProcess operationalProcess = createOperationalProcess();
    operationalProcess.setName(name_p);	  
    return operationalProcess;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Swimlane createSwimlane(String name_p) {
    Swimlane swimlane = createSwimlane();
    swimlane.setName(name_p);	  
    return swimlane;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalCapabilityPkg createOperationalCapabilityPkg(String name_p) {
    OperationalCapabilityPkg operationalCapabilityPkg = createOperationalCapabilityPkg();
    operationalCapabilityPkg.setName(name_p);	  
    return operationalCapabilityPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OperationalCapability createOperationalCapability(String name_p) {
    OperationalCapability operationalCapability = createOperationalCapability();
    operationalCapability.setName(name_p);	  
    return operationalCapability;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public RolePkg createRolePkg(String name_p) {
    RolePkg rolePkg = createRolePkg();
    rolePkg.setName(name_p);	  
    return rolePkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Role createRole(String name_p) {
    Role role = createRole();
    role.setName(name_p);	  
    return role;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public RoleAssemblyUsage createRoleAssemblyUsage(String name_p) {
    RoleAssemblyUsage roleAssemblyUsage = createRoleAssemblyUsage();
    roleAssemblyUsage.setName(name_p);	  
    return roleAssemblyUsage;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EntityPkg createEntityPkg(String name_p) {
    EntityPkg entityPkg = createEntityPkg();
    entityPkg.setName(name_p);	  
    return entityPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Entity createEntity(String name_p) {
    Entity entity = createEntity();
    entity.setName(name_p);	  
    return entity;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ConceptPkg createConceptPkg(String name_p) {
    ConceptPkg conceptPkg = createConceptPkg();
    conceptPkg.setName(name_p);	  
    return conceptPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Concept createConcept(String name_p) {
    Concept concept = createConcept();
    concept.setName(name_p);	  
    return concept;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ItemInConcept createItemInConcept(String name_p) {
    ItemInConcept itemInConcept = createItemInConcept();
    itemInConcept.setName(name_p);	  
    return itemInConcept;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CommunityOfInterest createCommunityOfInterest(String name_p) {
    CommunityOfInterest communityOfInterest = createCommunityOfInterest();
    communityOfInterest.setName(name_p);	  
    return communityOfInterest;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CommunityOfInterestComposition createCommunityOfInterestComposition(String name_p) {
    CommunityOfInterestComposition communityOfInterestComposition = createCommunityOfInterestComposition();
    communityOfInterestComposition.setName(name_p);	  
    return communityOfInterestComposition;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OrganisationalUnit createOrganisationalUnit(String name_p) {
    OrganisationalUnit organisationalUnit = createOrganisationalUnit();
    organisationalUnit.setName(name_p);	  
    return organisationalUnit;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public OrganisationalUnitComposition createOrganisationalUnitComposition(String name_p) {
    OrganisationalUnitComposition organisationalUnitComposition = createOrganisationalUnitComposition();
    organisationalUnitComposition.setName(name_p);	  
    return organisationalUnitComposition;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Location createLocation(String name_p) {
    Location location = createLocation();
    location.setName(name_p);	  
    return location;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CapabilityConfiguration createCapabilityConfiguration(String name_p) {
    CapabilityConfiguration capabilityConfiguration = createCapabilityConfiguration();
    capabilityConfiguration.setName(name_p);	  
    return capabilityConfiguration;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CommunicationMean createCommunicationMean(String name_p) {
    CommunicationMean communicationMean = createCommunicationMean();
    communicationMean.setName(name_p);	  
    return communicationMean;
  }

	//begin-capella-code

	//end-capella-code
} //OaFactoryImpl
