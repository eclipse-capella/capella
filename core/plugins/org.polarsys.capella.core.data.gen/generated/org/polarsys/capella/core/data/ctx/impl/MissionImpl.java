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

package org.polarsys.capella.core.data.ctx.impl;

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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mission</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getInvolvedInvolvements <em>Involved Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getOwnedActorMissionInvolvements <em>Owned Actor Mission Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getOwnedSystemMissionInvolvement <em>Owned System Mission Involvement</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getOwnedCapabilityExploitations <em>Owned Capability Exploitations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getParticipatingActors <em>Participating Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getParticipatingSystem <em>Participating System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getInvolvedActors <em>Involved Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getInvolvedSystem <em>Involved System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.MissionImpl#getExploitedCapabilities <em>Exploited Capabilities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MissionImpl extends NamedElementImpl implements Mission {





	/**
	 * The cached value of the '{@link #getOwnedActorMissionInvolvements() <em>Owned Actor Mission Involvements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedActorMissionInvolvements()
	 * @generated
	 * @ordered
	 */
	protected EList<ActorMissionInvolvement> ownedActorMissionInvolvements;





	/**
	 * The cached value of the '{@link #getOwnedSystemMissionInvolvement() <em>Owned System Mission Involvement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemMissionInvolvement()
	 * @generated
	 * @ordered
	 */
	protected SystemMissionInvolvement ownedSystemMissionInvolvement;





	/**
	 * The cached value of the '{@link #getOwnedCapabilityExploitations() <em>Owned Capability Exploitations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCapabilityExploitations()
	 * @generated
	 * @ordered
	 */
	protected EList<CapabilityExploitation> ownedCapabilityExploitations;
























	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MissionImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CtxPackage.Literals.MISSION;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Involvement> getInvolvedInvolvements() {


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
    EAnnotation annotation = CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Involvement> resultAsList = (Collection<Involvement>) result;
		return new EcoreEList.UnmodifiableEList<Involvement>(this, CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ActorMissionInvolvement> getOwnedActorMissionInvolvements() {

		if (ownedActorMissionInvolvements == null) {
			ownedActorMissionInvolvements = new EObjectContainmentEList<ActorMissionInvolvement>(ActorMissionInvolvement.class, this, CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS);
		}
		return ownedActorMissionInvolvements;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemMissionInvolvement getOwnedSystemMissionInvolvement() {

		return ownedSystemMissionInvolvement;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedSystemMissionInvolvement(SystemMissionInvolvement newOwnedSystemMissionInvolvement, NotificationChain msgs) {

		SystemMissionInvolvement oldOwnedSystemMissionInvolvement = ownedSystemMissionInvolvement;
		ownedSystemMissionInvolvement = newOwnedSystemMissionInvolvement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT, oldOwnedSystemMissionInvolvement, newOwnedSystemMissionInvolvement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedSystemMissionInvolvement(SystemMissionInvolvement newOwnedSystemMissionInvolvement) {

		if (newOwnedSystemMissionInvolvement != ownedSystemMissionInvolvement) {
			NotificationChain msgs = null;
			if (ownedSystemMissionInvolvement != null)
				msgs = ((InternalEObject)ownedSystemMissionInvolvement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT, null, msgs);
			if (newOwnedSystemMissionInvolvement != null)
				msgs = ((InternalEObject)newOwnedSystemMissionInvolvement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT, null, msgs);
			msgs = basicSetOwnedSystemMissionInvolvement(newOwnedSystemMissionInvolvement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT, newOwnedSystemMissionInvolvement, newOwnedSystemMissionInvolvement));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<CapabilityExploitation> getOwnedCapabilityExploitations() {

		if (ownedCapabilityExploitations == null) {
			ownedCapabilityExploitations = new EObjectContainmentWithInverseEList<CapabilityExploitation>(CapabilityExploitation.class, this, CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS, CtxPackage.CAPABILITY_EXPLOITATION__MISSION);
		}
		return ownedCapabilityExploitations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Actor> getParticipatingActors() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION__PARTICIPATING_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION__PARTICIPATING_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Actor> resultAsList = (Collection<Actor>) result;
		return new EcoreEList.UnmodifiableEList<Actor>(this, CtxPackage.Literals.MISSION__PARTICIPATING_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public org.polarsys.capella.core.data.ctx.System getParticipatingSystem() {

		org.polarsys.capella.core.data.ctx.System participatingSystem = basicGetParticipatingSystem();
		return participatingSystem != null && participatingSystem.eIsProxy() ? (org.polarsys.capella.core.data.ctx.System)eResolveProxy((InternalEObject)participatingSystem) : participatingSystem;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public org.polarsys.capella.core.data.ctx.System basicGetParticipatingSystem() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION__PARTICIPATING_SYSTEM, annotation);
		
		try {
			return (org.polarsys.capella.core.data.ctx.System) result;
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

	public EList<ActorMissionInvolvement> getInvolvedActors() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION__INVOLVED_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION__INVOLVED_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorMissionInvolvement> resultAsList = (Collection<ActorMissionInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorMissionInvolvement>(this, CtxPackage.Literals.MISSION__INVOLVED_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public SystemMissionInvolvement getInvolvedSystem() {

		SystemMissionInvolvement involvedSystem = basicGetInvolvedSystem();
		return involvedSystem != null && involvedSystem.eIsProxy() ? (SystemMissionInvolvement)eResolveProxy((InternalEObject)involvedSystem) : involvedSystem;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemMissionInvolvement basicGetInvolvedSystem() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION__INVOLVED_SYSTEM.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION__INVOLVED_SYSTEM, annotation);
		
		try {
			return (SystemMissionInvolvement) result;
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

	public EList<Capability> getExploitedCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Capability> resultAsList = (Collection<Capability>) result;
		return new EcoreEList.UnmodifiableEList<Capability>(this, CtxPackage.Literals.MISSION__EXPLOITED_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedCapabilityExploitations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
				return ((InternalEList<?>)getOwnedActorMissionInvolvements()).basicRemove(otherEnd, msgs);
			case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
				return basicSetOwnedSystemMissionInvolvement(null, msgs);
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				return ((InternalEList<?>)getOwnedCapabilityExploitations()).basicRemove(otherEnd, msgs);
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
			case CtxPackage.MISSION__INVOLVED_INVOLVEMENTS:
				return getInvolvedInvolvements();
			case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
				return getOwnedActorMissionInvolvements();
			case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
				return getOwnedSystemMissionInvolvement();
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				return getOwnedCapabilityExploitations();
			case CtxPackage.MISSION__PARTICIPATING_ACTORS:
				return getParticipatingActors();
			case CtxPackage.MISSION__PARTICIPATING_SYSTEM:
				if (resolve) return getParticipatingSystem();
				return basicGetParticipatingSystem();
			case CtxPackage.MISSION__INVOLVED_ACTORS:
				return getInvolvedActors();
			case CtxPackage.MISSION__INVOLVED_SYSTEM:
				if (resolve) return getInvolvedSystem();
				return basicGetInvolvedSystem();
			case CtxPackage.MISSION__EXPLOITED_CAPABILITIES:
				return getExploitedCapabilities();
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
			case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
				getOwnedActorMissionInvolvements().clear();
				getOwnedActorMissionInvolvements().addAll((Collection<? extends ActorMissionInvolvement>)newValue);
				return;
			case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
				// begin-extension-code
				if (newValue == null || newValue instanceof SystemMissionInvolvement) {
				// end-extension-code
					setOwnedSystemMissionInvolvement((SystemMissionInvolvement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				getOwnedCapabilityExploitations().clear();
				getOwnedCapabilityExploitations().addAll((Collection<? extends CapabilityExploitation>)newValue);
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
			case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
				getOwnedActorMissionInvolvements().clear();
				return;
			case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
				setOwnedSystemMissionInvolvement((SystemMissionInvolvement)null);
				return;
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				getOwnedCapabilityExploitations().clear();
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
			case CtxPackage.MISSION__INVOLVED_INVOLVEMENTS:
				return !getInvolvedInvolvements().isEmpty();
			case CtxPackage.MISSION__OWNED_ACTOR_MISSION_INVOLVEMENTS:
				return ownedActorMissionInvolvements != null && !ownedActorMissionInvolvements.isEmpty();
			case CtxPackage.MISSION__OWNED_SYSTEM_MISSION_INVOLVEMENT:
				return ownedSystemMissionInvolvement != null;
			case CtxPackage.MISSION__OWNED_CAPABILITY_EXPLOITATIONS:
				return ownedCapabilityExploitations != null && !ownedCapabilityExploitations.isEmpty();
			case CtxPackage.MISSION__PARTICIPATING_ACTORS:
				return !getParticipatingActors().isEmpty();
			case CtxPackage.MISSION__PARTICIPATING_SYSTEM:
				return basicGetParticipatingSystem() != null;
			case CtxPackage.MISSION__INVOLVED_ACTORS:
				return !getInvolvedActors().isEmpty();
			case CtxPackage.MISSION__INVOLVED_SYSTEM:
				return basicGetInvolvedSystem() != null;
			case CtxPackage.MISSION__EXPLOITED_CAPABILITIES:
				return !getExploitedCapabilities().isEmpty();
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
		if (baseClass == InvolverElement.class) {
			switch (derivedFeatureID) {
				case CtxPackage.MISSION__INVOLVED_INVOLVEMENTS: return CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS;
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
		if (baseClass == InvolverElement.class) {
			switch (baseFeatureID) {
				case CapellacorePackage.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS: return CtxPackage.MISSION__INVOLVED_INVOLVEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


} //MissionImpl