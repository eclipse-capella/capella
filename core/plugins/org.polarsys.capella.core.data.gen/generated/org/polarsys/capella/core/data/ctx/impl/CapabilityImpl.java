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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityImpl;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Capability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getOwnedActorCapabilityInvolvements <em>Owned Actor Capability Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getOwnedSystemCapabilityInvolvement <em>Owned System Capability Involvement</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getInvolvedActors <em>Involved Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getInvolvedSystem <em>Involved System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getParticipatingActors <em>Participating Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getParticipatingSystem <em>Participating System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getPurposes <em>Purposes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getPurposeMissions <em>Purpose Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getRealizedOperationalCapabilities <em>Realized Operational Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.CapabilityImpl#getRealizingCapabilityRealizations <em>Realizing Capability Realizations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CapabilityImpl extends AbstractCapabilityImpl implements Capability {

	/**
	 * The cached value of the '{@link #getOwnedActorCapabilityInvolvements() <em>Owned Actor Capability Involvements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedActorCapabilityInvolvements()
	 * @generated
	 * @ordered
	 */
	protected EList<ActorCapabilityInvolvement> ownedActorCapabilityInvolvements;





	/**
	 * The cached value of the '{@link #getOwnedSystemCapabilityInvolvement() <em>Owned System Capability Involvement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemCapabilityInvolvement()
	 * @generated
	 * @ordered
	 */
	protected SystemCapabilityInvolvement ownedSystemCapabilityInvolvement;





















	/**
	 * The cached value of the '{@link #getPurposes() <em>Purposes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPurposes()
	 * @generated
	 * @ordered
	 */
	protected EList<CapabilityExploitation> purposes;
















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

	public EList<ActorCapabilityInvolvement> getOwnedActorCapabilityInvolvements() {

		if (ownedActorCapabilityInvolvements == null) {
			ownedActorCapabilityInvolvements = new EObjectContainmentEList<ActorCapabilityInvolvement>(ActorCapabilityInvolvement.class, this, CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS);
		}
		return ownedActorCapabilityInvolvements;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemCapabilityInvolvement getOwnedSystemCapabilityInvolvement() {

		return ownedSystemCapabilityInvolvement;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedSystemCapabilityInvolvement(SystemCapabilityInvolvement newOwnedSystemCapabilityInvolvement, NotificationChain msgs) {

		SystemCapabilityInvolvement oldOwnedSystemCapabilityInvolvement = ownedSystemCapabilityInvolvement;
		ownedSystemCapabilityInvolvement = newOwnedSystemCapabilityInvolvement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT, oldOwnedSystemCapabilityInvolvement, newOwnedSystemCapabilityInvolvement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedSystemCapabilityInvolvement(SystemCapabilityInvolvement newOwnedSystemCapabilityInvolvement) {

		if (newOwnedSystemCapabilityInvolvement != ownedSystemCapabilityInvolvement) {
			NotificationChain msgs = null;
			if (ownedSystemCapabilityInvolvement != null)
				msgs = ((InternalEObject)ownedSystemCapabilityInvolvement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT, null, msgs);
			if (newOwnedSystemCapabilityInvolvement != null)
				msgs = ((InternalEObject)newOwnedSystemCapabilityInvolvement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT, null, msgs);
			msgs = basicSetOwnedSystemCapabilityInvolvement(newOwnedSystemCapabilityInvolvement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT, newOwnedSystemCapabilityInvolvement, newOwnedSystemCapabilityInvolvement));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<ActorCapabilityInvolvement> getInvolvedActors() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorCapabilityInvolvement> resultAsList = (Collection<ActorCapabilityInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorCapabilityInvolvement>(this, CtxPackage.Literals.CAPABILITY__INVOLVED_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public SystemCapabilityInvolvement getInvolvedSystem() {

		SystemCapabilityInvolvement involvedSystem = basicGetInvolvedSystem();
		return involvedSystem != null && involvedSystem.eIsProxy() ? (SystemCapabilityInvolvement)eResolveProxy((InternalEObject)involvedSystem) : involvedSystem;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemCapabilityInvolvement basicGetInvolvedSystem() {


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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__INVOLVED_SYSTEM, annotation);
		
		try {
			return (SystemCapabilityInvolvement) result;
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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Actor> resultAsList = (Collection<Actor>) result;
		return new EcoreEList.UnmodifiableEList<Actor>(this, CtxPackage.Literals.CAPABILITY__PARTICIPATING_ACTORS, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.CAPABILITY__PARTICIPATING_SYSTEM, annotation);
		
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

	public EList<CapabilityExploitation> getPurposes() {

		if (purposes == null) {
			purposes = new EObjectWithInverseResolvingEList<CapabilityExploitation>(CapabilityExploitation.class, this, CtxPackage.CAPABILITY__PURPOSES, CtxPackage.CAPABILITY_EXPLOITATION__CAPABILITY);
		}
		return purposes;
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CtxPackage.CAPABILITY__PURPOSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPurposes()).basicAdd(otherEnd, msgs);
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
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
				return ((InternalEList<?>)getOwnedActorCapabilityInvolvements()).basicRemove(otherEnd, msgs);
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
				return basicSetOwnedSystemCapabilityInvolvement(null, msgs);
			case CtxPackage.CAPABILITY__PURPOSES:
				return ((InternalEList<?>)getPurposes()).basicRemove(otherEnd, msgs);
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
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
				return getOwnedActorCapabilityInvolvements();
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
				return getOwnedSystemCapabilityInvolvement();
			case CtxPackage.CAPABILITY__INVOLVED_ACTORS:
				return getInvolvedActors();
			case CtxPackage.CAPABILITY__INVOLVED_SYSTEM:
				if (resolve) return getInvolvedSystem();
				return basicGetInvolvedSystem();
			case CtxPackage.CAPABILITY__PARTICIPATING_ACTORS:
				return getParticipatingActors();
			case CtxPackage.CAPABILITY__PARTICIPATING_SYSTEM:
				if (resolve) return getParticipatingSystem();
				return basicGetParticipatingSystem();
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
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
				getOwnedActorCapabilityInvolvements().clear();
				getOwnedActorCapabilityInvolvements().addAll((Collection<? extends ActorCapabilityInvolvement>)newValue);
				return;
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
				// begin-extension-code
				if (newValue == null || newValue instanceof SystemCapabilityInvolvement) {
				// end-extension-code
					setOwnedSystemCapabilityInvolvement((SystemCapabilityInvolvement)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.CAPABILITY__PURPOSES:
				getPurposes().clear();
				getPurposes().addAll((Collection<? extends CapabilityExploitation>)newValue);
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
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
				getOwnedActorCapabilityInvolvements().clear();
				return;
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
				setOwnedSystemCapabilityInvolvement((SystemCapabilityInvolvement)null);
				return;
			case CtxPackage.CAPABILITY__PURPOSES:
				getPurposes().clear();
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
			case CtxPackage.CAPABILITY__OWNED_ACTOR_CAPABILITY_INVOLVEMENTS:
				return ownedActorCapabilityInvolvements != null && !ownedActorCapabilityInvolvements.isEmpty();
			case CtxPackage.CAPABILITY__OWNED_SYSTEM_CAPABILITY_INVOLVEMENT:
				return ownedSystemCapabilityInvolvement != null;
			case CtxPackage.CAPABILITY__INVOLVED_ACTORS:
				return !getInvolvedActors().isEmpty();
			case CtxPackage.CAPABILITY__INVOLVED_SYSTEM:
				return basicGetInvolvedSystem() != null;
			case CtxPackage.CAPABILITY__PARTICIPATING_ACTORS:
				return !getParticipatingActors().isEmpty();
			case CtxPackage.CAPABILITY__PARTICIPATING_SYSTEM:
				return basicGetParticipatingSystem() != null;
			case CtxPackage.CAPABILITY__PURPOSES:
				return purposes != null && !purposes.isEmpty();
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