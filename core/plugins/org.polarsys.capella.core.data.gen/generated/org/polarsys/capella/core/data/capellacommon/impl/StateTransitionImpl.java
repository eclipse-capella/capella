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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.StateTransitionRealization;
import org.polarsys.capella.core.data.capellacommon.TransitionKind;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getRealizedFlow <em>Realized Flow</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTriggerDescription <em>Trigger Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getGuard <em>Guard</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getEffect <em>Effect</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getOwnedStateTransitionRealizations <em>Owned State Transition Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getRealizedStateTransitions <em>Realized State Transitions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateTransitionImpl#getRealizingStateTransitions <em>Realizing State Transitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateTransitionImpl extends NamedElementImpl implements StateTransition {

	/**
   * The cached value of the '{@link #getRealizedFlow() <em>Realized Flow</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRealizedFlow()
   * @generated
   * @ordered
   */
	protected AbstractInformationFlow realizedFlow;

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
   * The cached value of the '{@link #getGuard() <em>Guard</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getGuard()
   * @generated
   * @ordered
   */
	protected Constraint guard;





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
   * The cached value of the '{@link #getEffect() <em>Effect</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEffect()
   * @generated
   * @ordered
   */
	protected EList<AbstractEvent> effect;





	/**
   * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTriggers()
   * @generated
   * @ordered
   */
	protected EList<AbstractEvent> triggers;

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

	public AbstractInformationFlow getRealizedFlow() {

    if (realizedFlow != null && realizedFlow.eIsProxy()) {
      InternalEObject oldRealizedFlow = (InternalEObject)realizedFlow;
      realizedFlow = (AbstractInformationFlow)eResolveProxy(oldRealizedFlow);
      if (realizedFlow != oldRealizedFlow) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW, oldRealizedFlow, realizedFlow));
      }
    }
    return realizedFlow;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractInformationFlow basicGetRealizedFlow() {

    return realizedFlow;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetRealizedFlow(AbstractInformationFlow newRealizedFlow, NotificationChain msgs) {

    AbstractInformationFlow oldRealizedFlow = realizedFlow;
    realizedFlow = newRealizedFlow;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW, oldRealizedFlow, newRealizedFlow);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setRealizedFlow(AbstractInformationFlow newRealizedFlow) {

    if (newRealizedFlow != realizedFlow) {
      NotificationChain msgs = null;
      if (realizedFlow != null)
        msgs = ((InternalEObject)realizedFlow).eInverseRemove(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
      if (newRealizedFlow != null)
        msgs = ((InternalEObject)newRealizedFlow).eInverseAdd(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
      msgs = basicSetRealizedFlow(newRealizedFlow, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW, newRealizedFlow, newRealizedFlow));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint getGuard() {

    if (guard != null && guard.eIsProxy()) {
      InternalEObject oldGuard = (InternalEObject)guard;
      guard = (Constraint)eResolveProxy(oldGuard);
      if (guard != oldGuard) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE_TRANSITION__GUARD, oldGuard, guard));
      }
    }
    return guard;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Constraint basicGetGuard() {

    return guard;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setGuard(Constraint newGuard) {

    Constraint oldGuard = guard;
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

	@Override
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

	@Override
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

	@Override
	public void setSource(AbstractState newSource) {

    AbstractState oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__SOURCE, oldSource, source));

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

	@Override
	public void setTarget(AbstractState newTarget) {

    AbstractState oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE_TRANSITION__TARGET, oldTarget, target));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractEvent> getEffect() {

    if (effect == null) {
      effect = new EObjectResolvingEList<AbstractEvent>(AbstractEvent.class, this, CapellacommonPackage.STATE_TRANSITION__EFFECT);
    }
    return effect;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractEvent> getTriggers() {

    if (triggers == null) {
      triggers = new EObjectResolvingEList<AbstractEvent>(AbstractEvent.class, this, CapellacommonPackage.STATE_TRANSITION__TRIGGERS);
    }
    return triggers;
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
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
    return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZED_STATE_TRANSITIONS, resultAsList.size(), resultAsList.toArray());
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
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
    return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.STATE_TRANSITION__REALIZING_STATE_TRANSITIONS, resultAsList.size(), resultAsList.toArray());
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
        if (realizedFlow != null)
          msgs = ((InternalEObject)realizedFlow).eInverseRemove(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
        return basicSetRealizedFlow((AbstractInformationFlow)otherEnd, msgs);
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
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
        return basicSetRealizedFlow(null, msgs);
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
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
        if (resolve) return getRealizedFlow();
        return basicGetRealizedFlow();
      case CapellacommonPackage.STATE_TRANSITION__KIND:
        return getKind();
      case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
        return getTriggerDescription();
      case CapellacommonPackage.STATE_TRANSITION__GUARD:
        if (resolve) return getGuard();
        return basicGetGuard();
      case CapellacommonPackage.STATE_TRANSITION__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case CapellacommonPackage.STATE_TRANSITION__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case CapellacommonPackage.STATE_TRANSITION__EFFECT:
        return getEffect();
      case CapellacommonPackage.STATE_TRANSITION__TRIGGERS:
        return getTriggers();
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
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
          setRealizedFlow((AbstractInformationFlow)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__KIND:
          setKind((TransitionKind)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
          setTriggerDescription((String)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__GUARD:
          setGuard((Constraint)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__SOURCE:
          setSource((AbstractState)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__TARGET:
          setTarget((AbstractState)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__EFFECT:
        getEffect().clear();
        getEffect().addAll((Collection<? extends AbstractEvent>)newValue);
        return;
      case CapellacommonPackage.STATE_TRANSITION__TRIGGERS:
        getTriggers().clear();
        getTriggers().addAll((Collection<? extends AbstractEvent>)newValue);
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
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
        setRealizedFlow((AbstractInformationFlow)null);
        return;
      case CapellacommonPackage.STATE_TRANSITION__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
        setTriggerDescription(TRIGGER_DESCRIPTION_EDEFAULT);
        return;
      case CapellacommonPackage.STATE_TRANSITION__GUARD:
        setGuard((Constraint)null);
        return;
      case CapellacommonPackage.STATE_TRANSITION__SOURCE:
        setSource((AbstractState)null);
        return;
      case CapellacommonPackage.STATE_TRANSITION__TARGET:
        setTarget((AbstractState)null);
        return;
      case CapellacommonPackage.STATE_TRANSITION__EFFECT:
        getEffect().clear();
        return;
      case CapellacommonPackage.STATE_TRANSITION__TRIGGERS:
        getTriggers().clear();
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
      case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW:
        return realizedFlow != null;
      case CapellacommonPackage.STATE_TRANSITION__KIND:
        return kind != KIND_EDEFAULT;
      case CapellacommonPackage.STATE_TRANSITION__TRIGGER_DESCRIPTION:
        return TRIGGER_DESCRIPTION_EDEFAULT == null ? triggerDescription != null : !TRIGGER_DESCRIPTION_EDEFAULT.equals(triggerDescription);
      case CapellacommonPackage.STATE_TRANSITION__GUARD:
        return guard != null;
      case CapellacommonPackage.STATE_TRANSITION__SOURCE:
        return source != null;
      case CapellacommonPackage.STATE_TRANSITION__TARGET:
        return target != null;
      case CapellacommonPackage.STATE_TRANSITION__EFFECT:
        return effect != null && !effect.isEmpty();
      case CapellacommonPackage.STATE_TRANSITION__TRIGGERS:
        return triggers != null && !triggers.isEmpty();
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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractRelationship.class) {
      switch (derivedFeatureID) {
        case CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW: return ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW;
        default: return -1;
      }
    }
    if (baseClass == Relationship.class) {
      switch (derivedFeatureID) {
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
    if (baseClass == AbstractRelationship.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW: return CapellacommonPackage.STATE_TRANSITION__REALIZED_FLOW;
        default: return -1;
      }
    }
    if (baseClass == Relationship.class) {
      switch (baseFeatureID) {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", triggerDescription: "); //$NON-NLS-1$
    result.append(triggerDescription);
    result.append(')');
    return result.toString();
  }


} //StateTransitionImpl
