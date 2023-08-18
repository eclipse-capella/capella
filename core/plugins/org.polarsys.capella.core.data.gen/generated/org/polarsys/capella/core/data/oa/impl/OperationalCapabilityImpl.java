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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl;
import org.polarsys.capella.core.data.oa.CapabilityConfiguration;
import org.polarsys.capella.core.data.oa.ConceptCompliance;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Capability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl#getCompliances <em>Compliances</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl#getOwnedEntityOperationalCapabilityInvolvements <em>Owned Entity Operational Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl#getRealizingCapabilities <em>Realizing Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalCapabilityImpl#getInvolvedEntities <em>Involved Entities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalCapabilityImpl extends AbstractCapabilityImpl implements OperationalCapability {

	/**
   * The cached value of the '{@link #getCompliances() <em>Compliances</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCompliances()
   * @generated
   * @ordered
   */
	protected EList<ConceptCompliance> compliances;





	/**
   * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConfigurations()
   * @generated
   * @ordered
   */
	protected EList<CapabilityConfiguration> configurations;





	/**
   * The cached value of the '{@link #getOwnedEntityOperationalCapabilityInvolvements() <em>Owned Entity Operational Capability Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEntityOperationalCapabilityInvolvements()
   * @generated
   * @ordered
   */
	protected EList<EntityOperationalCapabilityInvolvement> ownedEntityOperationalCapabilityInvolvements;












	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalCapabilityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_CAPABILITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ConceptCompliance> getCompliances() {

    if (compliances == null) {
      compliances = new EObjectResolvingEList<ConceptCompliance>(ConceptCompliance.class, this, OaPackage.OPERATIONAL_CAPABILITY__COMPLIANCES);
    }
    return compliances;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityConfiguration> getConfigurations() {

    if (configurations == null) {
      configurations = new EObjectResolvingEList<CapabilityConfiguration>(CapabilityConfiguration.class, this, OaPackage.OPERATIONAL_CAPABILITY__CONFIGURATIONS);
    }
    return configurations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<EntityOperationalCapabilityInvolvement> getOwnedEntityOperationalCapabilityInvolvements() {

    if (ownedEntityOperationalCapabilityInvolvements == null) {
      ownedEntityOperationalCapabilityInvolvements = new EObjectContainmentEList.Resolving<EntityOperationalCapabilityInvolvement>(EntityOperationalCapabilityInvolvement.class, this, OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS);
    }
    return ownedEntityOperationalCapabilityInvolvements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Capability> getRealizingCapabilities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Capability> resultAsList = (Collection<Capability>) result;
    return new EcoreEList.UnmodifiableEList<Capability>(this, OaPackage.Literals.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Entity> getInvolvedEntities() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Entity> resultAsList = (Collection<Entity>) result;
    return new EcoreEList.UnmodifiableEList<Entity>(this, OaPackage.Literals.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES, resultAsList.size(), resultAsList.toArray());
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
      case OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedEntityOperationalCapabilityInvolvements()).basicRemove(otherEnd, msgs);
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
      case OaPackage.OPERATIONAL_CAPABILITY__COMPLIANCES:
        return getCompliances();
      case OaPackage.OPERATIONAL_CAPABILITY__CONFIGURATIONS:
        return getConfigurations();
      case OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS:
        return getOwnedEntityOperationalCapabilityInvolvements();
      case OaPackage.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES:
        return getRealizingCapabilities();
      case OaPackage.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES:
        return getInvolvedEntities();
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
      case OaPackage.OPERATIONAL_CAPABILITY__COMPLIANCES:
        getCompliances().clear();
        getCompliances().addAll((Collection<? extends ConceptCompliance>)newValue);
        return;
      case OaPackage.OPERATIONAL_CAPABILITY__CONFIGURATIONS:
        getConfigurations().clear();
        getConfigurations().addAll((Collection<? extends CapabilityConfiguration>)newValue);
        return;
      case OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS:
        getOwnedEntityOperationalCapabilityInvolvements().clear();
        getOwnedEntityOperationalCapabilityInvolvements().addAll((Collection<? extends EntityOperationalCapabilityInvolvement>)newValue);
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
      case OaPackage.OPERATIONAL_CAPABILITY__COMPLIANCES:
        getCompliances().clear();
        return;
      case OaPackage.OPERATIONAL_CAPABILITY__CONFIGURATIONS:
        getConfigurations().clear();
        return;
      case OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS:
        getOwnedEntityOperationalCapabilityInvolvements().clear();
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
      case OaPackage.OPERATIONAL_CAPABILITY__COMPLIANCES:
        return compliances != null && !compliances.isEmpty();
      case OaPackage.OPERATIONAL_CAPABILITY__CONFIGURATIONS:
        return configurations != null && !configurations.isEmpty();
      case OaPackage.OPERATIONAL_CAPABILITY__OWNED_ENTITY_OPERATIONAL_CAPABILITY_INVOLVEMENTS:
        return ownedEntityOperationalCapabilityInvolvements != null && !ownedEntityOperationalCapabilityInvolvements.isEmpty();
      case OaPackage.OPERATIONAL_CAPABILITY__REALIZING_CAPABILITIES:
        return !getRealizingCapabilities().isEmpty();
      case OaPackage.OPERATIONAL_CAPABILITY__INVOLVED_ENTITIES:
        return !getInvolvedEntities().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //OperationalCapabilityImpl