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

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getIncomingInformationFlows <em>Incoming Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getOutgoingInformationFlows <em>Outgoing Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getInformationFlows <em>Information Flows</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getRoleAllocations <em>Role Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getOrganisationalUnitMemberships <em>Organisational Unit Memberships</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getActualLocation <em>Actual Location</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getSubEntities <em>Sub Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getOwnedEntities <em>Owned Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getOwnedCommunicationMeans <em>Owned Communication Means</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getOwnedRoleAllocations <em>Owned Role Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getAllocatedOperationalActivities <em>Allocated Operational Activities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getAllocatedRoles <em>Allocated Roles</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getInvolvingOperationalCapabilities <em>Involving Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.EntityImpl#getRealizingSystemComponents <em>Realizing System Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityImpl extends AbstractConceptItemImpl implements Entity {





















	/**
   * The cached value of the '{@link #getOrganisationalUnitMemberships() <em>Organisational Unit Memberships</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOrganisationalUnitMemberships()
   * @generated
   * @ordered
   */
	protected EList<OrganisationalUnitComposition> organisationalUnitMemberships;





	/**
   * The cached value of the '{@link #getActualLocation() <em>Actual Location</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getActualLocation()
   * @generated
   * @ordered
   */
	protected Location actualLocation;









	/**
   * The cached value of the '{@link #getOwnedEntities() <em>Owned Entities</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEntities()
   * @generated
   * @ordered
   */
	protected EList<Entity> ownedEntities;





	/**
   * The cached value of the '{@link #getOwnedCommunicationMeans() <em>Owned Communication Means</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCommunicationMeans()
   * @generated
   * @ordered
   */
	protected EList<CommunicationMean> ownedCommunicationMeans;





	/**
   * The cached value of the '{@link #getOwnedRoleAllocations() <em>Owned Role Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRoleAllocations()
   * @generated
   * @ordered
   */
	protected EList<RoleAllocation> ownedRoleAllocations;
























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EntityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ENTITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractInformationFlow> getIncomingInformationFlows() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractInformationFlow> getOutgoingInformationFlows() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractInformationFlow> getInformationFlows() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractInformationFlow> resultAsList = (Collection<AbstractInformationFlow>) result;
    return new EcoreEList.UnmodifiableEList<AbstractInformationFlow>(this, ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Involvement> getInvolvingInvolvements() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Involvement> resultAsList = (Collection<Involvement>) result;
    return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<RoleAllocation> getRoleAllocations() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__ROLE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__ROLE_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<RoleAllocation> resultAsList = (Collection<RoleAllocation>) result;
    return new EcoreEList.UnmodifiableEList<RoleAllocation>(this, OaPackage.Literals.ENTITY__ROLE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OrganisationalUnitComposition> getOrganisationalUnitMemberships() {

    if (organisationalUnitMemberships == null) {
      organisationalUnitMemberships = new EObjectResolvingEList<OrganisationalUnitComposition>(OrganisationalUnitComposition.class, this, OaPackage.ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS);
    }
    return organisationalUnitMemberships;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Location getActualLocation() {

    if (actualLocation != null && actualLocation.eIsProxy()) {
      InternalEObject oldActualLocation = (InternalEObject)actualLocation;
      actualLocation = (Location)eResolveProxy(oldActualLocation);
      if (actualLocation != oldActualLocation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ENTITY__ACTUAL_LOCATION, oldActualLocation, actualLocation));
      }
    }
    return actualLocation;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Location basicGetActualLocation() {

    return actualLocation;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setActualLocation(Location newActualLocation) {

    Location oldActualLocation = actualLocation;
    actualLocation = newActualLocation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ENTITY__ACTUAL_LOCATION, oldActualLocation, actualLocation));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Entity> getSubEntities() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__SUB_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__SUB_ENTITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Entity> resultAsList = (Collection<Entity>) result;
    return new EcoreEList.UnmodifiableEList<Entity>(this, OaPackage.Literals.ENTITY__SUB_ENTITIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Entity> getOwnedEntities() {

    if (ownedEntities == null) {
      ownedEntities = new EObjectContainmentEList.Resolving<Entity>(Entity.class, this, OaPackage.ENTITY__OWNED_ENTITIES);
    }
    return ownedEntities;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationMean> getOwnedCommunicationMeans() {

    if (ownedCommunicationMeans == null) {
      ownedCommunicationMeans = new EObjectContainmentEList.Resolving<CommunicationMean>(CommunicationMean.class, this, OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS);
    }
    return ownedCommunicationMeans;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<RoleAllocation> getOwnedRoleAllocations() {

    if (ownedRoleAllocations == null) {
      ownedRoleAllocations = new EObjectContainmentEList.Resolving<RoleAllocation>(RoleAllocation.class, this, OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS);
    }
    return ownedRoleAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalActivity> getAllocatedOperationalActivities() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalActivity> resultAsList = (Collection<OperationalActivity>) result;
    return new EcoreEList.UnmodifiableEList<OperationalActivity>(this, OaPackage.Literals.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Role> getAllocatedRoles() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__ALLOCATED_ROLES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__ALLOCATED_ROLES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Role> resultAsList = (Collection<Role>) result;
    return new EcoreEList.UnmodifiableEList<Role>(this, OaPackage.Literals.ENTITY__ALLOCATED_ROLES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OperationalCapability> getInvolvingOperationalCapabilities() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalCapability> resultAsList = (Collection<OperationalCapability>) result;
    return new EcoreEList.UnmodifiableEList<OperationalCapability>(this, OaPackage.Literals.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponent> getRealizingSystemComponents() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = OaPackage.Literals.ENTITY__REALIZING_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.ENTITY__REALIZING_SYSTEM_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemComponent> resultAsList = (Collection<SystemComponent>) result;
    return new EcoreEList.UnmodifiableEList<SystemComponent>(this, OaPackage.Literals.ENTITY__REALIZING_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
    } catch (ClassCastException exception) {
    	exception.printStackTrace();
    	return org.eclipse.emf.common.util.ECollections.emptyEList();
    }
    
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case OaPackage.ENTITY__OWNED_ENTITIES:
        return ((InternalEList<?>)getOwnedEntities()).basicRemove(otherEnd, msgs);
      case OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS:
        return ((InternalEList<?>)getOwnedCommunicationMeans()).basicRemove(otherEnd, msgs);
      case OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedRoleAllocations()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.ENTITY__INCOMING_INFORMATION_FLOWS:
        return getIncomingInformationFlows();
      case OaPackage.ENTITY__OUTGOING_INFORMATION_FLOWS:
        return getOutgoingInformationFlows();
      case OaPackage.ENTITY__INFORMATION_FLOWS:
        return getInformationFlows();
      case OaPackage.ENTITY__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case OaPackage.ENTITY__ROLE_ALLOCATIONS:
        return getRoleAllocations();
      case OaPackage.ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS:
        return getOrganisationalUnitMemberships();
      case OaPackage.ENTITY__ACTUAL_LOCATION:
        if (resolve) return getActualLocation();
        return basicGetActualLocation();
      case OaPackage.ENTITY__SUB_ENTITIES:
        return getSubEntities();
      case OaPackage.ENTITY__OWNED_ENTITIES:
        return getOwnedEntities();
      case OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS:
        return getOwnedCommunicationMeans();
      case OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS:
        return getOwnedRoleAllocations();
      case OaPackage.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES:
        return getAllocatedOperationalActivities();
      case OaPackage.ENTITY__ALLOCATED_ROLES:
        return getAllocatedRoles();
      case OaPackage.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES:
        return getInvolvingOperationalCapabilities();
      case OaPackage.ENTITY__REALIZING_SYSTEM_COMPONENTS:
        return getRealizingSystemComponents();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case OaPackage.ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS:
        getOrganisationalUnitMemberships().clear();
        getOrganisationalUnitMemberships().addAll((Collection<? extends OrganisationalUnitComposition>)newValue);
        return;
      case OaPackage.ENTITY__ACTUAL_LOCATION:
          setActualLocation((Location)newValue);
        return;
      case OaPackage.ENTITY__OWNED_ENTITIES:
        getOwnedEntities().clear();
        getOwnedEntities().addAll((Collection<? extends Entity>)newValue);
        return;
      case OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS:
        getOwnedCommunicationMeans().clear();
        getOwnedCommunicationMeans().addAll((Collection<? extends CommunicationMean>)newValue);
        return;
      case OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS:
        getOwnedRoleAllocations().clear();
        getOwnedRoleAllocations().addAll((Collection<? extends RoleAllocation>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case OaPackage.ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS:
        getOrganisationalUnitMemberships().clear();
        return;
      case OaPackage.ENTITY__ACTUAL_LOCATION:
        setActualLocation((Location)null);
        return;
      case OaPackage.ENTITY__OWNED_ENTITIES:
        getOwnedEntities().clear();
        return;
      case OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS:
        getOwnedCommunicationMeans().clear();
        return;
      case OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS:
        getOwnedRoleAllocations().clear();
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case OaPackage.ENTITY__INCOMING_INFORMATION_FLOWS:
        return !getIncomingInformationFlows().isEmpty();
      case OaPackage.ENTITY__OUTGOING_INFORMATION_FLOWS:
        return !getOutgoingInformationFlows().isEmpty();
      case OaPackage.ENTITY__INFORMATION_FLOWS:
        return !getInformationFlows().isEmpty();
      case OaPackage.ENTITY__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case OaPackage.ENTITY__ROLE_ALLOCATIONS:
        return !getRoleAllocations().isEmpty();
      case OaPackage.ENTITY__ORGANISATIONAL_UNIT_MEMBERSHIPS:
        return organisationalUnitMemberships != null && !organisationalUnitMemberships.isEmpty();
      case OaPackage.ENTITY__ACTUAL_LOCATION:
        return actualLocation != null;
      case OaPackage.ENTITY__SUB_ENTITIES:
        return !getSubEntities().isEmpty();
      case OaPackage.ENTITY__OWNED_ENTITIES:
        return ownedEntities != null && !ownedEntities.isEmpty();
      case OaPackage.ENTITY__OWNED_COMMUNICATION_MEANS:
        return ownedCommunicationMeans != null && !ownedCommunicationMeans.isEmpty();
      case OaPackage.ENTITY__OWNED_ROLE_ALLOCATIONS:
        return ownedRoleAllocations != null && !ownedRoleAllocations.isEmpty();
      case OaPackage.ENTITY__ALLOCATED_OPERATIONAL_ACTIVITIES:
        return !getAllocatedOperationalActivities().isEmpty();
      case OaPackage.ENTITY__ALLOCATED_ROLES:
        return !getAllocatedRoles().isEmpty();
      case OaPackage.ENTITY__INVOLVING_OPERATIONAL_CAPABILITIES:
        return !getInvolvingOperationalCapabilities().isEmpty();
      case OaPackage.ENTITY__REALIZING_SYSTEM_COMPONENTS:
        return !getRealizingSystemComponents().isEmpty();
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == InformationsExchanger.class) {
      switch (derivedFeatureID) {
        case OaPackage.ENTITY__INCOMING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS;
        case OaPackage.ENTITY__OUTGOING_INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS;
        case OaPackage.ENTITY__INFORMATION_FLOWS: return ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (derivedFeatureID) {
        case OaPackage.ENTITY__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == InformationsExchanger.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS: return OaPackage.ENTITY__INCOMING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS: return OaPackage.ENTITY__OUTGOING_INFORMATION_FLOWS;
        case ModellingcorePackage.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS: return OaPackage.ENTITY__INFORMATION_FLOWS;
        default: return -1;
      }
    }
    if (baseClass == InvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return OaPackage.ENTITY__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //EntityImpl