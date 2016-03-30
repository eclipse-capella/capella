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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.impl.AbstractActorImpl;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalActorRealization;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getParticipationsInMissions <em>Participations In Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getParticipationsInCapabilities <em>Participations In Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getParticipationsInCapabilityRealizations <em>Participations In Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getContributedMissions <em>Contributed Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getContributedCapabilities <em>Contributed Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getSystemCommunication <em>System Communication</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getOwnedOperationalActorRealizations <em>Owned Operational Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getOwnedOperationalEntityRealizations <em>Owned Operational Entity Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getAllocatedSystemFunctions <em>Allocated System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getRealizedOperationalActors <em>Realized Operational Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.ActorImpl#getRealizingLogicalActors <em>Realizing Logical Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends AbstractActorImpl implements Actor {





















	/**
	 * The cached value of the '{@link #getSystemCommunication() <em>System Communication</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemCommunication()
	 * @generated
	 * @ordered
	 */
	protected SystemCommunicationHook systemCommunication;





	/**
	 * The cached value of the '{@link #getOwnedOperationalActorRealizations() <em>Owned Operational Actor Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedOperationalActorRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationalActorRealization> ownedOperationalActorRealizations;





	/**
	 * The cached value of the '{@link #getOwnedOperationalEntityRealizations() <em>Owned Operational Entity Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedOperationalEntityRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationalEntityRealization> ownedOperationalEntityRealizations;




















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActorImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CtxPackage.Literals.ACTOR;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<ActorMissionInvolvement> getParticipationsInMissions() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_MISSIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorMissionInvolvement> resultAsList = (Collection<ActorMissionInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorMissionInvolvement>(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ActorCapabilityInvolvement> getParticipationsInCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorCapabilityInvolvement> resultAsList = (Collection<ActorCapabilityInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorCapabilityInvolvement>(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ActorCapabilityRealizationInvolvement> resultAsList = (Collection<ActorCapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<ActorCapabilityRealizationInvolvement>(this, CtxPackage.Literals.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Mission> getContributedMissions() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__CONTRIBUTED_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__CONTRIBUTED_MISSIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Mission> resultAsList = (Collection<Mission>) result;
		return new EcoreEList.UnmodifiableEList<Mission>(this, CtxPackage.Literals.ACTOR__CONTRIBUTED_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Capability> getContributedCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__CONTRIBUTED_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__CONTRIBUTED_CAPABILITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Capability> resultAsList = (Collection<Capability>) result;
		return new EcoreEList.UnmodifiableEList<Capability>(this, CtxPackage.Literals.ACTOR__CONTRIBUTED_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public SystemCommunicationHook getSystemCommunication() {

		if (systemCommunication != null && systemCommunication.eIsProxy()) {
			InternalEObject oldSystemCommunication = (InternalEObject)systemCommunication;
			systemCommunication = (SystemCommunicationHook)eResolveProxy(oldSystemCommunication);
			if (systemCommunication != oldSystemCommunication) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.ACTOR__SYSTEM_COMMUNICATION, oldSystemCommunication, systemCommunication));
			}
		}
		return systemCommunication;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemCommunicationHook basicGetSystemCommunication() {

		return systemCommunication;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setSystemCommunication(SystemCommunicationHook newSystemCommunication) {

		SystemCommunicationHook oldSystemCommunication = systemCommunication;
		systemCommunication = newSystemCommunication;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.ACTOR__SYSTEM_COMMUNICATION, oldSystemCommunication, systemCommunication));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<OperationalActorRealization> getOwnedOperationalActorRealizations() {

		if (ownedOperationalActorRealizations == null) {
			ownedOperationalActorRealizations = new EObjectContainmentEList.Resolving<OperationalActorRealization>(OperationalActorRealization.class, this, CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS);
		}
		return ownedOperationalActorRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<OperationalEntityRealization> getOwnedOperationalEntityRealizations() {

		if (ownedOperationalEntityRealizations == null) {
			ownedOperationalEntityRealizations = new EObjectContainmentEList.Resolving<OperationalEntityRealization>(OperationalEntityRealization.class, this, CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS);
		}
		return ownedOperationalEntityRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemFunction> getAllocatedSystemFunctions() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
		return new EcoreEList.UnmodifiableEList<SystemFunction>(this, CtxPackage.Literals.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Entity> getRealizedEntities() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__REALIZED_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__REALIZED_ENTITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Entity> resultAsList = (Collection<Entity>) result;
		return new EcoreEList.UnmodifiableEList<Entity>(this, CtxPackage.Literals.ACTOR__REALIZED_ENTITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalActor> getRealizedOperationalActors() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__REALIZED_OPERATIONAL_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__REALIZED_OPERATIONAL_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<OperationalActor> resultAsList = (Collection<OperationalActor>) result;
		return new EcoreEList.UnmodifiableEList<OperationalActor>(this, CtxPackage.Literals.ACTOR__REALIZED_OPERATIONAL_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalActor> getRealizingLogicalActors() {


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
    EAnnotation annotation = CtxPackage.Literals.ACTOR__REALIZING_LOGICAL_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.ACTOR__REALIZING_LOGICAL_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalActor> resultAsList = (Collection<LogicalActor>) result;
		return new EcoreEList.UnmodifiableEList<LogicalActor>(this, CtxPackage.Literals.ACTOR__REALIZING_LOGICAL_ACTORS, resultAsList.size(), resultAsList.toArray());
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
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS:
				return ((InternalEList<?>)getOwnedOperationalActorRealizations()).basicRemove(otherEnd, msgs);
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS:
				return ((InternalEList<?>)getOwnedOperationalEntityRealizations()).basicRemove(otherEnd, msgs);
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
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_MISSIONS:
				return getParticipationsInMissions();
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_CAPABILITIES:
				return getParticipationsInCapabilities();
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return getParticipationsInCapabilityRealizations();
			case CtxPackage.ACTOR__CONTRIBUTED_MISSIONS:
				return getContributedMissions();
			case CtxPackage.ACTOR__CONTRIBUTED_CAPABILITIES:
				return getContributedCapabilities();
			case CtxPackage.ACTOR__SYSTEM_COMMUNICATION:
				if (resolve) return getSystemCommunication();
				return basicGetSystemCommunication();
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS:
				return getOwnedOperationalActorRealizations();
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS:
				return getOwnedOperationalEntityRealizations();
			case CtxPackage.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS:
				return getAllocatedSystemFunctions();
			case CtxPackage.ACTOR__REALIZED_ENTITIES:
				return getRealizedEntities();
			case CtxPackage.ACTOR__REALIZED_OPERATIONAL_ACTORS:
				return getRealizedOperationalActors();
			case CtxPackage.ACTOR__REALIZING_LOGICAL_ACTORS:
				return getRealizingLogicalActors();
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
			case CtxPackage.ACTOR__SYSTEM_COMMUNICATION:
				// begin-extension-code
				if (newValue == null || newValue instanceof SystemCommunicationHook) {
				// end-extension-code
					setSystemCommunication((SystemCommunicationHook)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS:
				getOwnedOperationalActorRealizations().clear();
				getOwnedOperationalActorRealizations().addAll((Collection<? extends OperationalActorRealization>)newValue);
				return;
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS:
				getOwnedOperationalEntityRealizations().clear();
				getOwnedOperationalEntityRealizations().addAll((Collection<? extends OperationalEntityRealization>)newValue);
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
			case CtxPackage.ACTOR__SYSTEM_COMMUNICATION:
				setSystemCommunication((SystemCommunicationHook)null);
				return;
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS:
				getOwnedOperationalActorRealizations().clear();
				return;
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS:
				getOwnedOperationalEntityRealizations().clear();
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
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_MISSIONS:
				return !getParticipationsInMissions().isEmpty();
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_CAPABILITIES:
				return !getParticipationsInCapabilities().isEmpty();
			case CtxPackage.ACTOR__PARTICIPATIONS_IN_CAPABILITY_REALIZATIONS:
				return !getParticipationsInCapabilityRealizations().isEmpty();
			case CtxPackage.ACTOR__CONTRIBUTED_MISSIONS:
				return !getContributedMissions().isEmpty();
			case CtxPackage.ACTOR__CONTRIBUTED_CAPABILITIES:
				return !getContributedCapabilities().isEmpty();
			case CtxPackage.ACTOR__SYSTEM_COMMUNICATION:
				return systemCommunication != null;
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ACTOR_REALIZATIONS:
				return ownedOperationalActorRealizations != null && !ownedOperationalActorRealizations.isEmpty();
			case CtxPackage.ACTOR__OWNED_OPERATIONAL_ENTITY_REALIZATIONS:
				return ownedOperationalEntityRealizations != null && !ownedOperationalEntityRealizations.isEmpty();
			case CtxPackage.ACTOR__ALLOCATED_SYSTEM_FUNCTIONS:
				return !getAllocatedSystemFunctions().isEmpty();
			case CtxPackage.ACTOR__REALIZED_ENTITIES:
				return !getRealizedEntities().isEmpty();
			case CtxPackage.ACTOR__REALIZED_OPERATIONAL_ACTORS:
				return !getRealizedOperationalActors().isEmpty();
			case CtxPackage.ACTOR__REALIZING_LOGICAL_ACTORS:
				return !getRealizingLogicalActors().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //ActorImpl