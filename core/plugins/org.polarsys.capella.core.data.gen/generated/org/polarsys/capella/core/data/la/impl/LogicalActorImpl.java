/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.impl.AbstractActorImpl;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getOwnedSystemActorRealizations <em>Owned System Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getSystemActorRealizations <em>System Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getRealizedSystemActors <em>Realized System Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalActorImpl#getRealizingPhysicalActors <em>Realizing Physical Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalActorImpl extends AbstractActorImpl implements LogicalActor {

	/**
	 * The cached value of the '{@link #getOwnedSystemActorRealizations() <em>Owned System Actor Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemActorRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemActorRealization> ownedSystemActorRealizations;
























	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogicalActorImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LaPackage.Literals.LOGICAL_ACTOR;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemActorRealization> getOwnedSystemActorRealizations() {

		if (ownedSystemActorRealizations == null) {
			ownedSystemActorRealizations = new EObjectContainmentEList.Resolving<SystemActorRealization>(SystemActorRealization.class, this, LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS);
		}
		return ownedSystemActorRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemActorRealization> getSystemActorRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemActorRealization> resultAsList = (Collection<SystemActorRealization>) result;
		return new EcoreEList.UnmodifiableEList<SystemActorRealization>(this, LaPackage.Literals.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ActorCapabilityRealizationInvolvement> getParticipationsInCapabilityRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorCapabilityRealizationInvolvement> resultAsList = (Collection<ActorCapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorCapabilityRealizationInvolvement>(this, LaPackage.Literals.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getAllocatedLogicalFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
		return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, LaPackage.Literals.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Actor> getRealizedSystemActors() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Actor> resultAsList = (Collection<Actor>) result;
		return new EcoreEList.UnmodifiableEList<Actor>(this, LaPackage.Literals.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalActor> getRealizingPhysicalActors() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalActor> resultAsList = (Collection<PhysicalActor>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalActor>(this, LaPackage.Literals.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS, resultAsList.size(), resultAsList.toArray());
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
			case LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS:
				return ((InternalEList<?>)getOwnedSystemActorRealizations()).basicRemove(otherEnd, msgs);
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
			case LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS:
				return getOwnedSystemActorRealizations();
			case LaPackage.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS:
				return getSystemActorRealizations();
			case LaPackage.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return getParticipationsInCapabilityRealizations();
			case LaPackage.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS:
				return getAllocatedLogicalFunctions();
			case LaPackage.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS:
				return getRealizedSystemActors();
			case LaPackage.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS:
				return getRealizingPhysicalActors();
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
			case LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS:
				getOwnedSystemActorRealizations().clear();
				getOwnedSystemActorRealizations().addAll((Collection<? extends SystemActorRealization>)newValue);
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
			case LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS:
				getOwnedSystemActorRealizations().clear();
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
			case LaPackage.LOGICAL_ACTOR__OWNED_SYSTEM_ACTOR_REALIZATIONS:
				return ownedSystemActorRealizations != null && !ownedSystemActorRealizations.isEmpty();
			case LaPackage.LOGICAL_ACTOR__SYSTEM_ACTOR_REALIZATIONS:
				return !getSystemActorRealizations().isEmpty();
			case LaPackage.LOGICAL_ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return !getParticipationsInCapabilityRealizations().isEmpty();
			case LaPackage.LOGICAL_ACTOR__ALLOCATED_LOGICAL_FUNCTIONS:
				return !getAllocatedLogicalFunctions().isEmpty();
			case LaPackage.LOGICAL_ACTOR__REALIZED_SYSTEM_ACTORS:
				return !getRealizedSystemActors().isEmpty();
			case LaPackage.LOGICAL_ACTOR__REALIZING_PHYSICAL_ACTORS:
				return !getRealizingPhysicalActors().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //LogicalActorImpl