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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.impl.ComponentImpl;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.OperationalEntityRealization;
import org.polarsys.capella.core.data.ctx.SystemCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.SystemCommunicationHook;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemMissionInvolvement;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getInvolvingCapabilityRealizationInvolvements <em>Involving Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getContributedCapabilities <em>Contributed Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getParticipationsInCapabilities <em>Participations In Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getContributedMissions <em>Contributed Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getParticipationsInMissions <em>Participations In Missions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getExternalCommunication <em>External Communication</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getOwnedEntityRealizations <em>Owned Entity Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getAllocatedEntityRealizations <em>Allocated Entity Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getAllocatedSystemFunctions <em>Allocated System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getRealizedEntities <em>Realized Entities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemImpl#getRealizingLogicalComponents <em>Realizing Logical Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemImpl extends ComponentImpl implements org.polarsys.capella.core.data.ctx.System {

























	/**
	 * The cached value of the '{@link #getExternalCommunication() <em>External Communication</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalCommunication()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemCommunicationHook> externalCommunication;





	/**
	 * The cached value of the '{@link #getOwnedEntityRealizations() <em>Owned Entity Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedEntityRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationalEntityRealization> ownedEntityRealizations;




















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CtxPackage.Literals.SYSTEM;
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

	public EList<CapabilityRealizationInvolvement> getInvolvingCapabilityRealizationInvolvements() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<CapabilityRealizationInvolvement> resultAsList = (Collection<CapabilityRealizationInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<CapabilityRealizationInvolvement>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__CONTRIBUTED_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__CONTRIBUTED_CAPABILITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Capability> resultAsList = (Collection<Capability>) result;
		return new EcoreEList.UnmodifiableEList<Capability>(this, CtxPackage.Literals.SYSTEM__CONTRIBUTED_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemCapabilityInvolvement> getParticipationsInCapabilities() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemCapabilityInvolvement> resultAsList = (Collection<SystemCapabilityInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<SystemCapabilityInvolvement>(this, CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__CONTRIBUTED_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__CONTRIBUTED_MISSIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Mission> resultAsList = (Collection<Mission>) result;
		return new EcoreEList.UnmodifiableEList<Mission>(this, CtxPackage.Literals.SYSTEM__CONTRIBUTED_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemMissionInvolvement> getParticipationsInMissions() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_MISSIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_MISSIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemMissionInvolvement> resultAsList = (Collection<SystemMissionInvolvement>) result;
		return new EcoreEList.UnmodifiableEList<SystemMissionInvolvement>(this, CtxPackage.Literals.SYSTEM__PARTICIPATIONS_IN_MISSIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemCommunicationHook> getExternalCommunication() {

		if (externalCommunication == null) {
			externalCommunication = new EObjectResolvingEList<SystemCommunicationHook>(SystemCommunicationHook.class, this, CtxPackage.SYSTEM__EXTERNAL_COMMUNICATION);
		}
		return externalCommunication;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<OperationalEntityRealization> getOwnedEntityRealizations() {

		if (ownedEntityRealizations == null) {
			ownedEntityRealizations = new EObjectContainmentEList.Resolving<OperationalEntityRealization>(OperationalEntityRealization.class, this, CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS);
		}
		return ownedEntityRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<OperationalEntityRealization> getAllocatedEntityRealizations() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<OperationalEntityRealization> resultAsList = (Collection<OperationalEntityRealization>) result;
		return new EcoreEList.UnmodifiableEList<OperationalEntityRealization>(this, CtxPackage.Literals.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemFunction> resultAsList = (Collection<SystemFunction>) result;
		return new EcoreEList.UnmodifiableEList<SystemFunction>(this, CtxPackage.Literals.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__REALIZED_ENTITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__REALIZED_ENTITIES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Entity> resultAsList = (Collection<Entity>) result;
		return new EcoreEList.UnmodifiableEList<Entity>(this, CtxPackage.Literals.SYSTEM__REALIZED_ENTITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalComponent> getRealizingLogicalComponents() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM__REALIZING_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM__REALIZING_LOGICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, CtxPackage.Literals.SYSTEM__REALIZING_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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
			case CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS:
				return ((InternalEList<?>)getOwnedEntityRealizations()).basicRemove(otherEnd, msgs);
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
			case CtxPackage.SYSTEM__INVOLVING_INVOLVEMENTS:
				return getInvolvingInvolvements();
			case CtxPackage.SYSTEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS:
				return getInvolvingCapabilityRealizationInvolvements();
			case CtxPackage.SYSTEM__CONTRIBUTED_CAPABILITIES:
				return getContributedCapabilities();
			case CtxPackage.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES:
				return getParticipationsInCapabilities();
			case CtxPackage.SYSTEM__CONTRIBUTED_MISSIONS:
				return getContributedMissions();
			case CtxPackage.SYSTEM__PARTICIPATIONS_IN_MISSIONS:
				return getParticipationsInMissions();
			case CtxPackage.SYSTEM__EXTERNAL_COMMUNICATION:
				return getExternalCommunication();
			case CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS:
				return getOwnedEntityRealizations();
			case CtxPackage.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS:
				return getAllocatedEntityRealizations();
			case CtxPackage.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS:
				return getAllocatedSystemFunctions();
			case CtxPackage.SYSTEM__REALIZED_ENTITIES:
				return getRealizedEntities();
			case CtxPackage.SYSTEM__REALIZING_LOGICAL_COMPONENTS:
				return getRealizingLogicalComponents();
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
			case CtxPackage.SYSTEM__EXTERNAL_COMMUNICATION:
				getExternalCommunication().clear();
				getExternalCommunication().addAll((Collection<? extends SystemCommunicationHook>)newValue);
				return;
			case CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS:
				getOwnedEntityRealizations().clear();
				getOwnedEntityRealizations().addAll((Collection<? extends OperationalEntityRealization>)newValue);
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
			case CtxPackage.SYSTEM__EXTERNAL_COMMUNICATION:
				getExternalCommunication().clear();
				return;
			case CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS:
				getOwnedEntityRealizations().clear();
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
			case CtxPackage.SYSTEM__INVOLVING_INVOLVEMENTS:
				return !getInvolvingInvolvements().isEmpty();
			case CtxPackage.SYSTEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS:
				return !getInvolvingCapabilityRealizationInvolvements().isEmpty();
			case CtxPackage.SYSTEM__CONTRIBUTED_CAPABILITIES:
				return !getContributedCapabilities().isEmpty();
			case CtxPackage.SYSTEM__PARTICIPATIONS_IN_CAPABILITIES:
				return !getParticipationsInCapabilities().isEmpty();
			case CtxPackage.SYSTEM__CONTRIBUTED_MISSIONS:
				return !getContributedMissions().isEmpty();
			case CtxPackage.SYSTEM__PARTICIPATIONS_IN_MISSIONS:
				return !getParticipationsInMissions().isEmpty();
			case CtxPackage.SYSTEM__EXTERNAL_COMMUNICATION:
				return externalCommunication != null && !externalCommunication.isEmpty();
			case CtxPackage.SYSTEM__OWNED_ENTITY_REALIZATIONS:
				return ownedEntityRealizations != null && !ownedEntityRealizations.isEmpty();
			case CtxPackage.SYSTEM__ALLOCATED_ENTITY_REALIZATIONS:
				return !getAllocatedEntityRealizations().isEmpty();
			case CtxPackage.SYSTEM__ALLOCATED_SYSTEM_FUNCTIONS:
				return !getAllocatedSystemFunctions().isEmpty();
			case CtxPackage.SYSTEM__REALIZED_ENTITIES:
				return !getRealizedEntities().isEmpty();
			case CtxPackage.SYSTEM__REALIZING_LOGICAL_COMPONENTS:
				return !getRealizingLogicalComponents().isEmpty();
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
		if (baseClass == InvolvedElement.class) {
			switch (derivedFeatureID) {
				case CtxPackage.SYSTEM__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
				default: return -1;
			}
		}
		if (baseClass == CapabilityRealizationInvolvedElement.class) {
			switch (derivedFeatureID) {
				case CtxPackage.SYSTEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;
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
		if (baseClass == InvolvedElement.class) {
			switch (baseFeatureID) {
				case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return CtxPackage.SYSTEM__INVOLVING_INVOLVEMENTS;
				default: return -1;
			}
		}
		if (baseClass == CapabilityRealizationInvolvedElement.class) {
			switch (baseFeatureID) {
				case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS: return CtxPackage.SYSTEM__INVOLVING_CAPABILITY_REALIZATION_INVOLVEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}


} //SystemImpl