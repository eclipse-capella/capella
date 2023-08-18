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

package org.polarsys.capella.core.data.ctx.impl;

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
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getOwnedCapabilityInvolvements <em>Owned Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getInvolvedSystemComponents <em>Involved System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getPurposes <em>Purposes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getPurposeMissions <em>Purpose Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getRealizedOperationalCapabilities <em>Realized Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CapabilityImpl extends AbstractCapabilityImpl implements Capability {

	/**
   * The cached value of the '{@link #getOwnedCapabilityInvolvements() <em>Owned Capability Involvements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCapabilityInvolvements()
   * @generated
   * @ordered
   */
	protected EList<CapabilityInvolvement> ownedCapabilityInvolvements;





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CapabilityImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CtxPackage.Literals.CAPABILITY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CapabilityInvolvement> getOwnedCapabilityInvolvements() {

    if (ownedCapabilityInvolvements == null) {
      ownedCapabilityInvolvements = new EObjectContainmentEList<CapabilityInvolvement>(CapabilityInvolvement.class, this, CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS);
    }
    return ownedCapabilityInvolvements;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<SystemComponent> getInvolvedSystemComponents() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemComponent> resultAsList = (Collection<SystemComponent>) result;
    return new EcoreEList.UnmodifiableEList<SystemComponent>(this, CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityExploitation> getPurposes() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__PURPOSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__PURPOSES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityExploitation> resultAsList = (Collection<CapabilityExploitation>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityExploitation>(this, CtxPackage.Literals.CAPABILITY__PURPOSES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Mission> getPurposeMissions() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Mission> resultAsList = (Collection<Mission>) result;
    return new EcoreEList.UnmodifiableEList<Mission>(this, CtxPackage.Literals.CAPABILITY__PURPOSE_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalCapability> getRealizedOperationalCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<OperationalCapability> resultAsList = (Collection<OperationalCapability>) result;
    return new EcoreEList.UnmodifiableEList<OperationalCapability>(this, CtxPackage.Literals.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, CtxPackage.Literals.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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
      case CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS:
        return ((InternalEList<?>)getOwnedCapabilityInvolvements()).basicRemove(otherEnd, msgs);
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
      case CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS:
        return getOwnedCapabilityInvolvements();
      case CtxPackage.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS:
        return getInvolvedSystemComponents();
      case CtxPackage.CAPABILITY__PURPOSES:
        return getPurposes();
      case CtxPackage.CAPABILITY__PURPOSE_MISSIONS:
        return getPurposeMissions();
      case CtxPackage.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES:
        return getRealizedOperationalCapabilities();
      case CtxPackage.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS:
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
      case CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS:
        getOwnedCapabilityInvolvements().clear();
        getOwnedCapabilityInvolvements().addAll((Collection<? extends CapabilityInvolvement>)newValue);
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
      case CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS:
        getOwnedCapabilityInvolvements().clear();
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
      case CtxPackage.CAPABILITY__OWNED_CAPABILITY_INVOLVEMENTS:
        return ownedCapabilityInvolvements != null && !ownedCapabilityInvolvements.isEmpty();
      case CtxPackage.CAPABILITY__INVOLVED_SYSTEM_COMPONENTS:
        return !getInvolvedSystemComponents().isEmpty();
      case CtxPackage.CAPABILITY__PURPOSES:
        return !getPurposes().isEmpty();
      case CtxPackage.CAPABILITY__PURPOSE_MISSIONS:
        return !getPurposeMissions().isEmpty();
      case CtxPackage.CAPABILITY__REALIZED_OPERATIONAL_CAPABILITIES:
        return !getRealizedOperationalCapabilities().isEmpty();
      case CtxPackage.CAPABILITY__REALIZING_CAPABILITY_REALIZATIONS:
        return !getRealizingCapabilityRealizations().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CapabilityImpl