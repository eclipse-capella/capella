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

package org.polarsys.capella.core.data.la.impl;

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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability Realization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getOwnedCapabilityRealizationInvolvements <em>Owned Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getInvolvedComponents <em>Involved Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizedCapabilities <em>Realized Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityRealizationImpl extends AbstractCapabilityImpl implements CapabilityRealization {

	/**
   * The cached value of the '{@link #getOwnedCapabilityRealizationInvolvements() <em>Owned Capability Realization Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilityRealizationInvolvements()
   * @generated
   * @ordered
   */
	protected EList<CapabilityRealizationInvolvement> ownedCapabilityRealizationInvolvements;





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityRealizationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.CAPABILITY_REALIZATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealizationInvolvement> getOwnedCapabilityRealizationInvolvements() {

    if (ownedCapabilityRealizationInvolvements == null) {
      ownedCapabilityRealizationInvolvements = new EObjectContainmentEList<CapabilityRealizationInvolvement>(CapabilityRealizationInvolvement.class, this, LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS);
    }
    return ownedCapabilityRealizationInvolvements;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityRealizationInvolvedElement> getInvolvedComponents() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealizationInvolvedElement> resultAsList = (Collection<CapabilityRealizationInvolvedElement>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealizationInvolvedElement>(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Capability> getRealizedCapabilities() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Capability> resultAsList = (Collection<Capability>) result;
    return new EcoreEList.UnmodifiableEList<Capability>(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityRealization> getRealizedCapabilityRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityRealization> getRealizingCapabilityRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, LaPackage.Literals.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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
      case LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedCapabilityRealizationInvolvements()).basicRemove(otherEnd, msgs);
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
      case LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS:
        return getOwnedCapabilityRealizationInvolvements();
      case LaPackage.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS:
        return getInvolvedComponents();
      case LaPackage.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES:
        return getRealizedCapabilities();
      case LaPackage.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS:
        return getRealizedCapabilityRealizations();
      case LaPackage.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS:
        return getRealizingCapabilityRealizations();
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
      case LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS:
        getOwnedCapabilityRealizationInvolvements().clear();
        getOwnedCapabilityRealizationInvolvements().addAll((Collection<? extends CapabilityRealizationInvolvement>)newValue);
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
      case LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS:
        getOwnedCapabilityRealizationInvolvements().clear();
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
      case LaPackage.CAPABILITY_REALIZATION__OWNED_CAPABILITY_REALIZATION_INVOLVEMENTS:
        return ownedCapabilityRealizationInvolvements != null && !ownedCapabilityRealizationInvolvements.isEmpty();
      case LaPackage.CAPABILITY_REALIZATION__INVOLVED_COMPONENTS:
        return !getInvolvedComponents().isEmpty();
      case LaPackage.CAPABILITY_REALIZATION__REALIZED_CAPABILITIES:
        return !getRealizedCapabilities().isEmpty();
      case LaPackage.CAPABILITY_REALIZATION__REALIZED_CAPABILITY_REALIZATIONS:
        return !getRealizedCapabilityRealizations().isEmpty();
      case LaPackage.CAPABILITY_REALIZATION__REALIZING_CAPABILITY_REALIZATIONS:
        return !getRealizingCapabilityRealizations().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CapabilityRealizationImpl