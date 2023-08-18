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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.ConceptPkg;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.RolePkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operational Analysis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getOwnedRolePkg <em>Owned Role Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getOwnedEntityPkg <em>Owned Entity Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getOwnedConceptPkg <em>Owned Concept Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getContainedOperationalCapabilityPkg <em>Contained Operational Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getContainedOperationalActivityPkg <em>Contained Operational Activity Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OperationalAnalysisImpl#getAllocatingSystemAnalyses <em>Allocating System Analyses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationalAnalysisImpl extends BlockArchitectureImpl implements OperationalAnalysis {

	/**
   * The cached value of the '{@link #getOwnedRolePkg() <em>Owned Role Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRolePkg()
   * @generated
   * @ordered
   */
	protected RolePkg ownedRolePkg;





	/**
   * The cached value of the '{@link #getOwnedEntityPkg() <em>Owned Entity Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedEntityPkg()
   * @generated
   * @ordered
   */
	protected EntityPkg ownedEntityPkg;





	/**
   * The cached value of the '{@link #getOwnedConceptPkg() <em>Owned Concept Pkg</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConceptPkg()
   * @generated
   * @ordered
   */
	protected ConceptPkg ownedConceptPkg;
















	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationalAnalysisImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.OPERATIONAL_ANALYSIS;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public RolePkg getOwnedRolePkg() {

    if (ownedRolePkg != null && ownedRolePkg.eIsProxy()) {
      InternalEObject oldOwnedRolePkg = (InternalEObject)ownedRolePkg;
      ownedRolePkg = (RolePkg)eResolveProxy(oldOwnedRolePkg);
      if (ownedRolePkg != oldOwnedRolePkg) {
        InternalEObject newOwnedRolePkg = (InternalEObject)ownedRolePkg;
        NotificationChain msgs = oldOwnedRolePkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, null, null);
        if (newOwnedRolePkg.eInternalContainer() == null) {
          msgs = newOwnedRolePkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, oldOwnedRolePkg, ownedRolePkg));
      }
    }
    return ownedRolePkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public RolePkg basicGetOwnedRolePkg() {

    return ownedRolePkg;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedRolePkg(RolePkg newOwnedRolePkg, NotificationChain msgs) {

    RolePkg oldOwnedRolePkg = ownedRolePkg;
    ownedRolePkg = newOwnedRolePkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, oldOwnedRolePkg, newOwnedRolePkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedRolePkg(RolePkg newOwnedRolePkg) {

    if (newOwnedRolePkg != ownedRolePkg) {
      NotificationChain msgs = null;
      if (ownedRolePkg != null)
        msgs = ((InternalEObject)ownedRolePkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, null, msgs);
      if (newOwnedRolePkg != null)
        msgs = ((InternalEObject)newOwnedRolePkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, null, msgs);
      msgs = basicSetOwnedRolePkg(newOwnedRolePkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG, newOwnedRolePkg, newOwnedRolePkg));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EntityPkg getOwnedEntityPkg() {

    if (ownedEntityPkg != null && ownedEntityPkg.eIsProxy()) {
      InternalEObject oldOwnedEntityPkg = (InternalEObject)ownedEntityPkg;
      ownedEntityPkg = (EntityPkg)eResolveProxy(oldOwnedEntityPkg);
      if (ownedEntityPkg != oldOwnedEntityPkg) {
        InternalEObject newOwnedEntityPkg = (InternalEObject)ownedEntityPkg;
        NotificationChain msgs = oldOwnedEntityPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, null, null);
        if (newOwnedEntityPkg.eInternalContainer() == null) {
          msgs = newOwnedEntityPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, oldOwnedEntityPkg, ownedEntityPkg));
      }
    }
    return ownedEntityPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EntityPkg basicGetOwnedEntityPkg() {

    return ownedEntityPkg;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedEntityPkg(EntityPkg newOwnedEntityPkg, NotificationChain msgs) {

    EntityPkg oldOwnedEntityPkg = ownedEntityPkg;
    ownedEntityPkg = newOwnedEntityPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, oldOwnedEntityPkg, newOwnedEntityPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedEntityPkg(EntityPkg newOwnedEntityPkg) {

    if (newOwnedEntityPkg != ownedEntityPkg) {
      NotificationChain msgs = null;
      if (ownedEntityPkg != null)
        msgs = ((InternalEObject)ownedEntityPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, null, msgs);
      if (newOwnedEntityPkg != null)
        msgs = ((InternalEObject)newOwnedEntityPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, null, msgs);
      msgs = basicSetOwnedEntityPkg(newOwnedEntityPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG, newOwnedEntityPkg, newOwnedEntityPkg));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ConceptPkg getOwnedConceptPkg() {

    if (ownedConceptPkg != null && ownedConceptPkg.eIsProxy()) {
      InternalEObject oldOwnedConceptPkg = (InternalEObject)ownedConceptPkg;
      ownedConceptPkg = (ConceptPkg)eResolveProxy(oldOwnedConceptPkg);
      if (ownedConceptPkg != oldOwnedConceptPkg) {
        InternalEObject newOwnedConceptPkg = (InternalEObject)ownedConceptPkg;
        NotificationChain msgs = oldOwnedConceptPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, null, null);
        if (newOwnedConceptPkg.eInternalContainer() == null) {
          msgs = newOwnedConceptPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, oldOwnedConceptPkg, ownedConceptPkg));
      }
    }
    return ownedConceptPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ConceptPkg basicGetOwnedConceptPkg() {

    return ownedConceptPkg;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedConceptPkg(ConceptPkg newOwnedConceptPkg, NotificationChain msgs) {

    ConceptPkg oldOwnedConceptPkg = ownedConceptPkg;
    ownedConceptPkg = newOwnedConceptPkg;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, oldOwnedConceptPkg, newOwnedConceptPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedConceptPkg(ConceptPkg newOwnedConceptPkg) {

    if (newOwnedConceptPkg != ownedConceptPkg) {
      NotificationChain msgs = null;
      if (ownedConceptPkg != null)
        msgs = ((InternalEObject)ownedConceptPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, null, msgs);
      if (newOwnedConceptPkg != null)
        msgs = ((InternalEObject)newOwnedConceptPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, null, msgs);
      msgs = basicSetOwnedConceptPkg(newOwnedConceptPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG, newOwnedConceptPkg, newOwnedConceptPkg));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapabilityPkg getContainedOperationalCapabilityPkg() {

    OperationalCapabilityPkg containedOperationalCapabilityPkg = basicGetContainedOperationalCapabilityPkg();
    return containedOperationalCapabilityPkg != null && containedOperationalCapabilityPkg.eIsProxy() ? (OperationalCapabilityPkg)eResolveProxy((InternalEObject)containedOperationalCapabilityPkg) : containedOperationalCapabilityPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalCapabilityPkg basicGetContainedOperationalCapabilityPkg() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG, annotation);
    
    try {
      return (OperationalCapabilityPkg) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalActivityPkg getContainedOperationalActivityPkg() {

    OperationalActivityPkg containedOperationalActivityPkg = basicGetContainedOperationalActivityPkg();
    return containedOperationalActivityPkg != null && containedOperationalActivityPkg.eIsProxy() ? (OperationalActivityPkg)eResolveProxy((InternalEObject)containedOperationalActivityPkg) : containedOperationalActivityPkg;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OperationalActivityPkg basicGetContainedOperationalActivityPkg() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG, annotation);
    
    try {
      return (OperationalActivityPkg) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemAnalysis> getAllocatingSystemAnalyses() {


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
    EAnnotation annotation = OaPackage.Literals.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, OaPackage.Literals.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemAnalysis> resultAsList = (Collection<SystemAnalysis>) result;
    return new EcoreEList.UnmodifiableEList<SystemAnalysis>(this, OaPackage.Literals.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES, resultAsList.size(), resultAsList.toArray());
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
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
        return basicSetOwnedRolePkg(null, msgs);
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
        return basicSetOwnedEntityPkg(null, msgs);
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
        return basicSetOwnedConceptPkg(null, msgs);
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
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
        if (resolve) return getOwnedRolePkg();
        return basicGetOwnedRolePkg();
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
        if (resolve) return getOwnedEntityPkg();
        return basicGetOwnedEntityPkg();
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
        if (resolve) return getOwnedConceptPkg();
        return basicGetOwnedConceptPkg();
      case OaPackage.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG:
        if (resolve) return getContainedOperationalCapabilityPkg();
        return basicGetContainedOperationalCapabilityPkg();
      case OaPackage.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG:
        if (resolve) return getContainedOperationalActivityPkg();
        return basicGetContainedOperationalActivityPkg();
      case OaPackage.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES:
        return getAllocatingSystemAnalyses();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
          setOwnedRolePkg((RolePkg)newValue);
        return;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
          setOwnedEntityPkg((EntityPkg)newValue);
        return;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
          setOwnedConceptPkg((ConceptPkg)newValue);
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
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
        setOwnedRolePkg((RolePkg)null);
        return;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
        setOwnedEntityPkg((EntityPkg)null);
        return;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
        setOwnedConceptPkg((ConceptPkg)null);
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
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ROLE_PKG:
        return ownedRolePkg != null;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_ENTITY_PKG:
        return ownedEntityPkg != null;
      case OaPackage.OPERATIONAL_ANALYSIS__OWNED_CONCEPT_PKG:
        return ownedConceptPkg != null;
      case OaPackage.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_CAPABILITY_PKG:
        return basicGetContainedOperationalCapabilityPkg() != null;
      case OaPackage.OPERATIONAL_ANALYSIS__CONTAINED_OPERATIONAL_ACTIVITY_PKG:
        return basicGetContainedOperationalActivityPkg() != null;
      case OaPackage.OPERATIONAL_ANALYSIS__ALLOCATING_SYSTEM_ANALYSES:
        return !getAllocatingSystemAnalyses().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //OperationalAnalysisImpl