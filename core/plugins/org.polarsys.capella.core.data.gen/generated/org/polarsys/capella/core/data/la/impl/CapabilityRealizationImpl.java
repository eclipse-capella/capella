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
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.cs.SystemComponentCapabilityRealizationInvolvement;
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
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getOwnedActorCapabilityRealizations <em>Owned Actor Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getOwnedSystemComponentCapabilityRealizations <em>Owned System Component Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getParticipatingActors <em>Participating Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getParticipatingSystemComponents <em>Participating System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getInvolvedActors <em>Involved Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getInvolvedSystemComponents <em>Involved System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizedCapabilities <em>Realized Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizedCapabilityRealizations <em>Realized Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.CapabilityRealizationImpl#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CapabilityRealizationImpl extends AbstractCapabilityImpl implements CapabilityRealization {

	/**
	 * The cached value of the '{@link #getOwnedActorCapabilityRealizations() <em>Owned Actor Capability Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedActorCapabilityRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<ActorCapabilityRealizationInvolvement> ownedActorCapabilityRealizations;





	/**
	 * The cached value of the '{@link #getOwnedSystemComponentCapabilityRealizations() <em>Owned System Component Capability Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemComponentCapabilityRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemComponentCapabilityRealizationInvolvement> ownedSystemComponentCapabilityRealizations;
































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

	public EList<ActorCapabilityRealizationInvolvement> getOwnedActorCapabilityRealizations() {

		if (ownedActorCapabilityRealizations == null) {
			ownedActorCapabilityRealizations = new EObjectContainmentEList<ActorCapabilityRealizationInvolvement>(ActorCapabilityRealizationInvolvement.class, this, LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS);
		}
		return ownedActorCapabilityRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemComponentCapabilityRealizationInvolvement> getOwnedSystemComponentCapabilityRealizations() {

		if (ownedSystemComponentCapabilityRealizations == null) {
			ownedSystemComponentCapabilityRealizations = new EObjectContainmentEList<SystemComponentCapabilityRealizationInvolvement>(SystemComponentCapabilityRealizationInvolvement.class, this, LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS);
		}
		return ownedSystemComponentCapabilityRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<AbstractActor> getParticipatingActors() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<AbstractActor> resultAsList = (Collection<AbstractActor>) result;
		return new EcoreEList.UnmodifiableEList<AbstractActor>(this, LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemComponent> getParticipatingSystemComponents() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemComponent> resultAsList = (Collection<SystemComponent>) result;
		return new EcoreEList.UnmodifiableEList<SystemComponent>(this, LaPackage.Literals.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ActorCapabilityRealizationInvolvement> getInvolvedActors() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorCapabilityRealizationInvolvement> resultAsList = (Collection<ActorCapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorCapabilityRealizationInvolvement>(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemComponentCapabilityRealizationInvolvement> getInvolvedSystemComponents() {


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
    EAnnotation annotation = LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemComponentCapabilityRealizationInvolvement> resultAsList = (Collection<SystemComponentCapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<SystemComponentCapabilityRealizationInvolvement>(this, LaPackage.Literals.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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
			case LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS:
				return ((InternalEList<?>)getOwnedActorCapabilityRealizations()).basicRemove(otherEnd, msgs);
			case LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS:
				return ((InternalEList<?>)getOwnedSystemComponentCapabilityRealizations()).basicRemove(otherEnd, msgs);
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
			case LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS:
				return getOwnedActorCapabilityRealizations();
			case LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS:
				return getOwnedSystemComponentCapabilityRealizations();
			case LaPackage.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS:
				return getParticipatingActors();
			case LaPackage.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS:
				return getParticipatingSystemComponents();
			case LaPackage.CAPABILITY_REALIZATION__INVOLVED_ACTORS:
				return getInvolvedActors();
			case LaPackage.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS:
				return getInvolvedSystemComponents();
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
			case LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS:
				getOwnedActorCapabilityRealizations().clear();
				getOwnedActorCapabilityRealizations().addAll((Collection<? extends ActorCapabilityRealizationInvolvement>)newValue);
				return;
			case LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS:
				getOwnedSystemComponentCapabilityRealizations().clear();
				getOwnedSystemComponentCapabilityRealizations().addAll((Collection<? extends SystemComponentCapabilityRealizationInvolvement>)newValue);
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
			case LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS:
				getOwnedActorCapabilityRealizations().clear();
				return;
			case LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS:
				getOwnedSystemComponentCapabilityRealizations().clear();
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
			case LaPackage.CAPABILITY_REALIZATION__OWNED_ACTOR_CAPABILITY_REALIZATIONS:
				return ownedActorCapabilityRealizations != null && !ownedActorCapabilityRealizations.isEmpty();
			case LaPackage.CAPABILITY_REALIZATION__OWNED_SYSTEM_COMPONENT_CAPABILITY_REALIZATIONS:
				return ownedSystemComponentCapabilityRealizations != null && !ownedSystemComponentCapabilityRealizations.isEmpty();
			case LaPackage.CAPABILITY_REALIZATION__PARTICIPATING_ACTORS:
				return !getParticipatingActors().isEmpty();
			case LaPackage.CAPABILITY_REALIZATION__PARTICIPATING_SYSTEM_COMPONENTS:
				return !getParticipatingSystemComponents().isEmpty();
			case LaPackage.CAPABILITY_REALIZATION__INVOLVED_ACTORS:
				return !getInvolvedActors().isEmpty();
			case LaPackage.CAPABILITY_REALIZATION__INVOLVED_SYSTEM_COMPONENTS:
				return !getInvolvedSystemComponents().isEmpty();
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