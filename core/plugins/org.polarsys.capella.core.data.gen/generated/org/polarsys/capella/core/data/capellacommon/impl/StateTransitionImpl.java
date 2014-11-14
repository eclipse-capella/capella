/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.capellacommon.impl;

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
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.tig.model.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TransitionKind;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTriggerDescription <em>Trigger Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getEffect <em>Effect</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getOwnedGuardCondition <em>Owned Guard Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getOwnedStateTransitionRealizations <em>Owned State Transition Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getRealizedStateTransitions <em>Realized State Transitions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getRealizingStateTransitions <em>Realizing State Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateTransitionImpl extends RelationshipImpl implements StateTransition {

	/**
	 * The default value of the '{@link #getGuard() <em>Guard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuard()
	 * @generated
	 * @ordered
	 */
	protected static final String GUARD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGuard() <em>Guard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuard()
	 * @generated
	 * @ordered
	 */
	protected String guard = GUARD_EDEFAULT;





	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final TransitionKind KIND_EDEFAULT = TransitionKind.INTERNAL;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected TransitionKind kind = KIND_EDEFAULT;





	/**
	 * The default value of the '{@link #getTriggerDescription() <em>Trigger Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String TRIGGER_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTriggerDescription() <em>Trigger Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerDescription()
	 * @generated
	 * @ordered
	 */
	protected String triggerDescription = TRIGGER_DESCRIPTION_EDEFAULT;





	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected AbstractState source;





	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected AbstractState target;





	/**
	 * The cached value of the '{@link #getEffect() <em>Effect</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffect()
	 * @generated
	 * @ordered
	 */
	protected AbstractEvent effect;





	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected AbstractEvent trigger;





	/**
	 * The cached value of the '{@link #getOwnedGuardCondition() <em>Owned Guard Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedGuardCondition()
	 * @generated
	 * @ordered
	 */
	protected AbstractConstraint ownedGuardCondition;





	/**
	 * The cached value of the '{@link #getOwnedStateTransitionRealizations() <em>Owned State Transition Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedStateTransitionRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<StateTransitionRealization> ownedStateTransitionRealizations;












	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateTransitionImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CapellacommonPackage.Literals.STATE_TRANSITION;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public String getGuard() {

		return guard;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setGuard(String newGuard) {

		String oldGuard = guard;
		guard = newGuard;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__GUARD, oldGuard, guard));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public TransitionKind getKind() {

		return kind;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setKind(TransitionKind newKind) {

		TransitionKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__KIND, oldKind, kind));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public String getTriggerDescription() {

		return triggerDescription;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setTriggerDescription(String newTriggerDescription) {

		String oldTriggerDescription = triggerDescription;
		triggerDescription = newTriggerDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION, oldTriggerDescription, triggerDescription));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractState getSource() {

		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (AbstractState)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__SOURCE, oldSource, source));
			}
		}
		return source;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractState basicGetSource() {

		return source;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetSource(AbstractState newSource, NotificationChain msgs) {

		AbstractState oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setSource(AbstractState newSource) {

		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, CapellacommonPackage.ABSTRACT_STATE__OUTGOING, AbstractState.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, CapellacommonPackage.ABSTRACT_STATE__OUTGOING, AbstractState.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__SOURCE, newSource, newSource));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractState getTarget() {

		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (AbstractState)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__TARGET, oldTarget, target));
			}
		}
		return target;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractState basicGetTarget() {

		return target;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetTarget(AbstractState newTarget, NotificationChain msgs) {

		AbstractState oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setTarget(AbstractState newTarget) {

		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, CapellacommonPackage.ABSTRACT_STATE__INCOMING, AbstractState.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, CapellacommonPackage.ABSTRACT_STATE__INCOMING, AbstractState.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__TARGET, newTarget, newTarget));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEvent getEffect() {

		if (effect != null && effect.eIsProxy()) {
			InternalEObject oldEffect = (InternalEObject)effect;
			effect = (AbstractEvent)eResolveProxy(oldEffect);
			if (effect != oldEffect) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__EFFECT, oldEffect, effect));
			}
		}
		return effect;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEvent basicGetEffect() {

		return effect;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setEffect(AbstractEvent newEffect) {

		AbstractEvent oldEffect = effect;
		effect = newEffect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__EFFECT, oldEffect, effect));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEvent getTrigger() {

		if (trigger != null && trigger.eIsProxy()) {
			InternalEObject oldTrigger = (InternalEObject)trigger;
			trigger = (AbstractEvent)eResolveProxy(oldTrigger);
			if (trigger != oldTrigger) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__TRIGGER, oldTrigger, trigger));
			}
		}
		return trigger;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEvent basicGetTrigger() {

		return trigger;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setTrigger(AbstractEvent newTrigger) {

		AbstractEvent oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__TRIGGER, oldTrigger, trigger));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractConstraint getOwnedGuardCondition() {

		if (ownedGuardCondition != null && ownedGuardCondition.eIsProxy()) {
			InternalEObject oldOwnedGuardCondition = (InternalEObject)ownedGuardCondition;
			ownedGuardCondition = (AbstractConstraint)eResolveProxy(oldOwnedGuardCondition);
			if (ownedGuardCondition != oldOwnedGuardCondition) {
				InternalEObject newOwnedGuardCondition = (InternalEObject)ownedGuardCondition;
				NotificationChain msgs = oldOwnedGuardCondition.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, null, null);
				if (newOwnedGuardCondition.eInternalContainer() == null) {
					msgs = newOwnedGuardCondition.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, oldOwnedGuardCondition, ownedGuardCondition));
			}
		}
		return ownedGuardCondition;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractConstraint basicGetOwnedGuardCondition() {

		return ownedGuardCondition;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedGuardCondition(AbstractConstraint newOwnedGuardCondition, NotificationChain msgs) {

		AbstractConstraint oldOwnedGuardCondition = ownedGuardCondition;
		ownedGuardCondition = newOwnedGuardCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, oldOwnedGuardCondition, newOwnedGuardCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedGuardCondition(AbstractConstraint newOwnedGuardCondition) {

		if (newOwnedGuardCondition != ownedGuardCondition) {
			NotificationChain msgs = null;
			if (ownedGuardCondition != null)
				msgs = ((InternalEObject)ownedGuardCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, null, msgs);
			if (newOwnedGuardCondition != null)
				msgs = ((InternalEObject)newOwnedGuardCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, null, msgs);
			msgs = basicSetOwnedGuardCondition(newOwnedGuardCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION, newOwnedGuardCondition, newOwnedGuardCondition));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<StateTransitionRealization> getOwnedStateTransitionRealizations() {

		if (ownedStateTransitionRealizations == null) {
			ownedStateTransitionRealizations = new EObjectContainmentEList.Resolving<StateTransitionRealization>(StateTransitionRealization.class, this, CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS);
		}
		return ownedStateTransitionRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<StateTransition> getRealizedStateTransitions() {


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
      throw new org.polarsys.capella.common.tig.model.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS.getEAnnotation(org.polarsys.capella.common.tig.model.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
		return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException cce_p) {
	  	cce_p.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<StateTransition> getRealizingStateTransitions() {


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
      throw new org.polarsys.capella.common.tig.model.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS.getEAnnotation(org.polarsys.capella.common.tig.model.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
		return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS, resultAsList.size(), resultAsList.toArray());
		} catch (ClassCastException cce_p) {
	  	cce_p.printStackTrace();
	  	return org.eclipse.emf.common.util.ECollections.emptyEList();
	  }
		
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, CapellacommonPackage.ABSTRACT_STATE__OUTGOING, AbstractState.class, msgs);
				return basicSetSource((AbstractState)otherEnd, msgs);
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, CapellacommonPackage.ABSTRACT_STATE__INCOMING, AbstractState.class, msgs);
				return basicSetTarget((AbstractState)otherEnd, msgs);
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
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				return basicSetSource(null, msgs);
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				return basicSetTarget(null, msgs);
			case CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION:
				return basicSetOwnedGuardCondition(null, msgs);
			case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
				return ((InternalEList<?>)getOwnedStateTransitionRealizations()).basicRemove(otherEnd, msgs);
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
			case CapellacommonPackage.STATE_TRANSITION__GUARD:
				return getGuard();
			case CapellacommonPackage.STATE_TRANSITION__KIND:
				return getKind();
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
				return getTriggerDescription();
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case CapellacommonPackage.STATE_TRANSITION__EFFECT:
				if (resolve) return getEffect();
				return basicGetEffect();
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER:
				if (resolve) return getTrigger();
				return basicGetTrigger();
			case CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION:
				if (resolve) return getOwnedGuardCondition();
				return basicGetOwnedGuardCondition();
			case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
				return getOwnedStateTransitionRealizations();
			case CapellacommonPackage.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS:
				return getRealizedStateTransitions();
			case CapellacommonPackage.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS:
				return getRealizingStateTransitions();
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
			case CapellacommonPackage.STATE_TRANSITION__GUARD:
				// begin-extension-code
				if (newValue == null || newValue instanceof String) {
				// end-extension-code
					setGuard((String)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__KIND:
				// begin-extension-code
				if (newValue == null || newValue instanceof TransitionKind) {
				// end-extension-code
					setKind((TransitionKind)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
				// begin-extension-code
				if (newValue == null || newValue instanceof String) {
				// end-extension-code
					setTriggerDescription((String)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractState) {
				// end-extension-code
					setSource((AbstractState)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractState) {
				// end-extension-code
					setTarget((AbstractState)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__EFFECT:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractEvent) {
				// end-extension-code
					setEffect((AbstractEvent)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractEvent) {
				// end-extension-code
					setTrigger((AbstractEvent)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractConstraint) {
				// end-extension-code
					setOwnedGuardCondition((AbstractConstraint)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
				getOwnedStateTransitionRealizations().clear();
				getOwnedStateTransitionRealizations().addAll((Collection<? extends StateTransitionRealization>)newValue);
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
			case CapellacommonPackage.STATE_TRANSITION__GUARD:
				setGuard(GUARD_EDEFAULT);
				return;
			case CapellacommonPackage.STATE_TRANSITION__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
				setTriggerDescription(TRIGGER_DESCRIPTION_EDEFAULT);
				return;
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				setSource((AbstractState)null);
				return;
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				setTarget((AbstractState)null);
				return;
			case CapellacommonPackage.STATE_TRANSITION__EFFECT:
				setEffect((AbstractEvent)null);
				return;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER:
				setTrigger((AbstractEvent)null);
				return;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION:
				setOwnedGuardCondition((AbstractConstraint)null);
				return;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
				getOwnedStateTransitionRealizations().clear();
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
			case CapellacommonPackage.STATE_TRANSITION__GUARD:
				return GUARD_EDEFAULT == null ? guard != null : !GUARD_EDEFAULT.equals(guard);
			case CapellacommonPackage.STATE_TRANSITION__KIND:
				return kind != KIND_EDEFAULT;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
				return TRIGGER_DESCRIPTION_EDEFAULT == null ? triggerDescription != null : !TRIGGER_DESCRIPTION_EDEFAULT.equals(triggerDescription);
			case CapellacommonPackage.STATE_TRANSITION__SOURCE:
				return source != null;
			case CapellacommonPackage.STATE_TRANSITION__TARGET:
				return target != null;
			case CapellacommonPackage.STATE_TRANSITION__EFFECT:
				return effect != null;
			case CapellacommonPackage.STATE_TRANSITION__TRIGGER:
				return trigger != null;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_GUARD_CONDITION:
				return ownedGuardCondition != null;
			case CapellacommonPackage.STATE_TRANSITION__OWNED_STATE_TRANSITION_REALIZATIONS:
				return ownedStateTransitionRealizations != null && !ownedStateTransitionRealizations.isEmpty();
			case CapellacommonPackage.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS:
				return !getRealizedStateTransitions().isEmpty();
			case CapellacommonPackage.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS:
				return !getRealizingStateTransitions().isEmpty();
		}
		return super.eIsSet(featureID);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (guard: "); //$NON-NLS-1$
		result.append(guard);
		result.append(", kind: "); //$NON-NLS-1$
		result.append(kind);
		result.append(", triggerDescription: "); //$NON-NLS-1$
		result.append(triggerDescription);
		result.append(')');
		return result.toString();
	}


} //StateTransitionImpl