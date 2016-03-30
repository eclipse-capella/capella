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
import org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Analysis</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getOwnedSystemContext <em>Owned System Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getOwnedSystem <em>Owned System</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getOwnedActorPkg <em>Owned Actor Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getOwnedMissionPkg <em>Owned Mission Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getContainedCapabilityPkg <em>Contained Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getContainedSystemFunctionPkg <em>Contained System Function Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getOwnedOperationalAnalysisRealizations <em>Owned Operational Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getAllocatedOperationalAnalysisRealizations <em>Allocated Operational Analysis Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getAllocatedOperationalAnalyses <em>Allocated Operational Analyses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.impl.SystemAnalysisImpl#getAllocatingLogicalArchitectures <em>Allocating Logical Architectures</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemAnalysisImpl extends ComponentArchitectureImpl implements SystemAnalysis {

	/**
	 * The cached value of the '{@link #getOwnedSystemContext() <em>Owned System Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemContext()
	 * @generated
	 * @ordered
	 */
	protected SystemContext ownedSystemContext;





	/**
	 * The cached value of the '{@link #getOwnedSystem() <em>Owned System</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystem()
	 * @generated
	 * @ordered
	 */
	protected org.polarsys.capella.core.data.ctx.System ownedSystem;





	/**
	 * The cached value of the '{@link #getOwnedActorPkg() <em>Owned Actor Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedActorPkg()
	 * @generated
	 * @ordered
	 */
	protected ActorPkg ownedActorPkg;





	/**
	 * The cached value of the '{@link #getOwnedMissionPkg() <em>Owned Mission Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMissionPkg()
	 * @generated
	 * @ordered
	 */
	protected MissionPkg ownedMissionPkg;













	/**
	 * The cached value of the '{@link #getOwnedOperationalAnalysisRealizations() <em>Owned Operational Analysis Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedOperationalAnalysisRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationalAnalysisRealization> ownedOperationalAnalysisRealizations;
















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemAnalysisImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CtxPackage.Literals.SYSTEM_ANALYSIS;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemContext getOwnedSystemContext() {

		if (ownedSystemContext != null && ownedSystemContext.eIsProxy()) {
			InternalEObject oldOwnedSystemContext = (InternalEObject)ownedSystemContext;
			ownedSystemContext = (SystemContext)eResolveProxy(oldOwnedSystemContext);
			if (ownedSystemContext != oldOwnedSystemContext) {
				InternalEObject newOwnedSystemContext = (InternalEObject)ownedSystemContext;
				NotificationChain msgs = oldOwnedSystemContext.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, null, null);
				if (newOwnedSystemContext.eInternalContainer() == null) {
					msgs = newOwnedSystemContext.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, oldOwnedSystemContext, ownedSystemContext));
			}
		}
		return ownedSystemContext;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemContext basicGetOwnedSystemContext() {

		return ownedSystemContext;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedSystemContext(SystemContext newOwnedSystemContext, NotificationChain msgs) {

		SystemContext oldOwnedSystemContext = ownedSystemContext;
		ownedSystemContext = newOwnedSystemContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, oldOwnedSystemContext, newOwnedSystemContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedSystemContext(SystemContext newOwnedSystemContext) {

		if (newOwnedSystemContext != ownedSystemContext) {
			NotificationChain msgs = null;
			if (ownedSystemContext != null)
				msgs = ((InternalEObject)ownedSystemContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, null, msgs);
			if (newOwnedSystemContext != null)
				msgs = ((InternalEObject)newOwnedSystemContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, null, msgs);
			msgs = basicSetOwnedSystemContext(newOwnedSystemContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT, newOwnedSystemContext, newOwnedSystemContext));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public org.polarsys.capella.core.data.ctx.System getOwnedSystem() {

		if (ownedSystem != null && ownedSystem.eIsProxy()) {
			InternalEObject oldOwnedSystem = (InternalEObject)ownedSystem;
			ownedSystem = (org.polarsys.capella.core.data.ctx.System)eResolveProxy(oldOwnedSystem);
			if (ownedSystem != oldOwnedSystem) {
				InternalEObject newOwnedSystem = (InternalEObject)ownedSystem;
				NotificationChain msgs = oldOwnedSystem.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, null, null);
				if (newOwnedSystem.eInternalContainer() == null) {
					msgs = newOwnedSystem.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, oldOwnedSystem, ownedSystem));
			}
		}
		return ownedSystem;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public org.polarsys.capella.core.data.ctx.System basicGetOwnedSystem() {

		return ownedSystem;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedSystem(org.polarsys.capella.core.data.ctx.System newOwnedSystem, NotificationChain msgs) {

		org.polarsys.capella.core.data.ctx.System oldOwnedSystem = ownedSystem;
		ownedSystem = newOwnedSystem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, oldOwnedSystem, newOwnedSystem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedSystem(org.polarsys.capella.core.data.ctx.System newOwnedSystem) {

		if (newOwnedSystem != ownedSystem) {
			NotificationChain msgs = null;
			if (ownedSystem != null)
				msgs = ((InternalEObject)ownedSystem).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, null, msgs);
			if (newOwnedSystem != null)
				msgs = ((InternalEObject)newOwnedSystem).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, null, msgs);
			msgs = basicSetOwnedSystem(newOwnedSystem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM, newOwnedSystem, newOwnedSystem));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ActorPkg getOwnedActorPkg() {

		if (ownedActorPkg != null && ownedActorPkg.eIsProxy()) {
			InternalEObject oldOwnedActorPkg = (InternalEObject)ownedActorPkg;
			ownedActorPkg = (ActorPkg)eResolveProxy(oldOwnedActorPkg);
			if (ownedActorPkg != oldOwnedActorPkg) {
				InternalEObject newOwnedActorPkg = (InternalEObject)ownedActorPkg;
				NotificationChain msgs = oldOwnedActorPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, null, null);
				if (newOwnedActorPkg.eInternalContainer() == null) {
					msgs = newOwnedActorPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, oldOwnedActorPkg, ownedActorPkg));
			}
		}
		return ownedActorPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ActorPkg basicGetOwnedActorPkg() {

		return ownedActorPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedActorPkg(ActorPkg newOwnedActorPkg, NotificationChain msgs) {

		ActorPkg oldOwnedActorPkg = ownedActorPkg;
		ownedActorPkg = newOwnedActorPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, oldOwnedActorPkg, newOwnedActorPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedActorPkg(ActorPkg newOwnedActorPkg) {

		if (newOwnedActorPkg != ownedActorPkg) {
			NotificationChain msgs = null;
			if (ownedActorPkg != null)
				msgs = ((InternalEObject)ownedActorPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, null, msgs);
			if (newOwnedActorPkg != null)
				msgs = ((InternalEObject)newOwnedActorPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, null, msgs);
			msgs = basicSetOwnedActorPkg(newOwnedActorPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG, newOwnedActorPkg, newOwnedActorPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public MissionPkg getOwnedMissionPkg() {

		if (ownedMissionPkg != null && ownedMissionPkg.eIsProxy()) {
			InternalEObject oldOwnedMissionPkg = (InternalEObject)ownedMissionPkg;
			ownedMissionPkg = (MissionPkg)eResolveProxy(oldOwnedMissionPkg);
			if (ownedMissionPkg != oldOwnedMissionPkg) {
				InternalEObject newOwnedMissionPkg = (InternalEObject)ownedMissionPkg;
				NotificationChain msgs = oldOwnedMissionPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, null, null);
				if (newOwnedMissionPkg.eInternalContainer() == null) {
					msgs = newOwnedMissionPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, oldOwnedMissionPkg, ownedMissionPkg));
			}
		}
		return ownedMissionPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public MissionPkg basicGetOwnedMissionPkg() {

		return ownedMissionPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedMissionPkg(MissionPkg newOwnedMissionPkg, NotificationChain msgs) {

		MissionPkg oldOwnedMissionPkg = ownedMissionPkg;
		ownedMissionPkg = newOwnedMissionPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, oldOwnedMissionPkg, newOwnedMissionPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedMissionPkg(MissionPkg newOwnedMissionPkg) {

		if (newOwnedMissionPkg != ownedMissionPkg) {
			NotificationChain msgs = null;
			if (ownedMissionPkg != null)
				msgs = ((InternalEObject)ownedMissionPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, null, msgs);
			if (newOwnedMissionPkg != null)
				msgs = ((InternalEObject)newOwnedMissionPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, null, msgs);
			msgs = basicSetOwnedMissionPkg(newOwnedMissionPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG, newOwnedMissionPkg, newOwnedMissionPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityPkg getContainedCapabilityPkg() {

		CapabilityPkg containedCapabilityPkg = basicGetContainedCapabilityPkg();
		return containedCapabilityPkg != null && containedCapabilityPkg.eIsProxy() ? (CapabilityPkg)eResolveProxy((InternalEObject)containedCapabilityPkg) : containedCapabilityPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityPkg basicGetContainedCapabilityPkg() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG, annotation);
		
		try {
			return (CapabilityPkg) result;
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

	public SystemFunctionPkg getContainedSystemFunctionPkg() {

		SystemFunctionPkg containedSystemFunctionPkg = basicGetContainedSystemFunctionPkg();
		return containedSystemFunctionPkg != null && containedSystemFunctionPkg.eIsProxy() ? (SystemFunctionPkg)eResolveProxy((InternalEObject)containedSystemFunctionPkg) : containedSystemFunctionPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public SystemFunctionPkg basicGetContainedSystemFunctionPkg() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG, annotation);
		
		try {
			return (SystemFunctionPkg) result;
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

	public EList<OperationalAnalysisRealization> getOwnedOperationalAnalysisRealizations() {

		if (ownedOperationalAnalysisRealizations == null) {
			ownedOperationalAnalysisRealizations = new EObjectContainmentEList.Resolving<OperationalAnalysisRealization>(OperationalAnalysisRealization.class, this, CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS);
		}
		return ownedOperationalAnalysisRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<OperationalAnalysisRealization> getAllocatedOperationalAnalysisRealizations() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<OperationalAnalysisRealization> resultAsList = (Collection<OperationalAnalysisRealization>) result;
		return new EcoreEList.UnmodifiableEList<OperationalAnalysisRealization>(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<OperationalAnalysis> getAllocatedOperationalAnalyses() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<OperationalAnalysis> resultAsList = (Collection<OperationalAnalysis>) result;
		return new EcoreEList.UnmodifiableEList<OperationalAnalysis>(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalArchitecture> getAllocatingLogicalArchitectures() {


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
    EAnnotation annotation = CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalArchitecture> resultAsList = (Collection<LogicalArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<LogicalArchitecture>(this, CtxPackage.Literals.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT:
				return basicSetOwnedSystemContext(null, msgs);
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM:
				return basicSetOwnedSystem(null, msgs);
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG:
				return basicSetOwnedActorPkg(null, msgs);
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
				return basicSetOwnedMissionPkg(null, msgs);
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				return ((InternalEList<?>)getOwnedOperationalAnalysisRealizations()).basicRemove(otherEnd, msgs);
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
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT:
				if (resolve) return getOwnedSystemContext();
				return basicGetOwnedSystemContext();
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM:
				if (resolve) return getOwnedSystem();
				return basicGetOwnedSystem();
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG:
				if (resolve) return getOwnedActorPkg();
				return basicGetOwnedActorPkg();
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
				if (resolve) return getOwnedMissionPkg();
				return basicGetOwnedMissionPkg();
			case CtxPackage.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG:
				if (resolve) return getContainedCapabilityPkg();
				return basicGetContainedCapabilityPkg();
			case CtxPackage.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG:
				if (resolve) return getContainedSystemFunctionPkg();
				return basicGetContainedSystemFunctionPkg();
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				return getOwnedOperationalAnalysisRealizations();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				return getAllocatedOperationalAnalysisRealizations();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES:
				return getAllocatedOperationalAnalyses();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES:
				return getAllocatingLogicalArchitectures();
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
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT:
				// begin-extension-code
				if (newValue == null || newValue instanceof SystemContext) {
				// end-extension-code
					setOwnedSystemContext((SystemContext)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM:
				// begin-extension-code
				if (newValue == null || newValue instanceof org.polarsys.capella.core.data.ctx.System) {
				// end-extension-code
					setOwnedSystem((org.polarsys.capella.core.data.ctx.System)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof ActorPkg) {
				// end-extension-code
					setOwnedActorPkg((ActorPkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof MissionPkg) {
				// end-extension-code
					setOwnedMissionPkg((MissionPkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				getOwnedOperationalAnalysisRealizations().clear();
				getOwnedOperationalAnalysisRealizations().addAll((Collection<? extends OperationalAnalysisRealization>)newValue);
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
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT:
				setOwnedSystemContext((SystemContext)null);
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM:
				setOwnedSystem((org.polarsys.capella.core.data.ctx.System)null);
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG:
				setOwnedActorPkg((ActorPkg)null);
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
				setOwnedMissionPkg((MissionPkg)null);
				return;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				getOwnedOperationalAnalysisRealizations().clear();
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
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM_CONTEXT:
				return ownedSystemContext != null;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_SYSTEM:
				return ownedSystem != null;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG:
				return ownedActorPkg != null;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_MISSION_PKG:
				return ownedMissionPkg != null;
			case CtxPackage.SYSTEM_ANALYSIS__CONTAINED_CAPABILITY_PKG:
				return basicGetContainedCapabilityPkg() != null;
			case CtxPackage.SYSTEM_ANALYSIS__CONTAINED_SYSTEM_FUNCTION_PKG:
				return basicGetContainedSystemFunctionPkg() != null;
			case CtxPackage.SYSTEM_ANALYSIS__OWNED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				return ownedOperationalAnalysisRealizations != null && !ownedOperationalAnalysisRealizations.isEmpty();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSIS_REALIZATIONS:
				return !getAllocatedOperationalAnalysisRealizations().isEmpty();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATED_OPERATIONAL_ANALYSES:
				return !getAllocatedOperationalAnalyses().isEmpty();
			case CtxPackage.SYSTEM_ANALYSIS__ALLOCATING_LOGICAL_ARCHITECTURES:
				return !getAllocatingLogicalArchitectures().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //SystemAnalysisImpl