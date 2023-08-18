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
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Pseudostate;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.interaction.AbstractCapability;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getOwnedRegions <em>Owned Regions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getOwnedConnectionPoints <em>Owned Connection Points</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getAvailableAbstractFunctions <em>Available Abstract Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getAvailableFunctionalChains <em>Available Functional Chains</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getAvailableAbstractCapabilities <em>Available Abstract Capabilities</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getEntry <em>Entry</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getDoActivity <em>Do Activity</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getExit <em>Exit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.StateImpl#getStateInvariant <em>State Invariant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends AbstractStateImpl implements State {

	/**
   * The cached value of the '{@link #getOwnedRegions() <em>Owned Regions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedRegions()
   * @generated
   * @ordered
   */
	protected EList<Region> ownedRegions;

















	/**
   * The cached value of the '{@link #getOwnedConnectionPoints() <em>Owned Connection Points</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConnectionPoints()
   * @generated
   * @ordered
   */
	protected EList<Pseudostate> ownedConnectionPoints;

















	/**
   * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEntry()
   * @generated
   * @ordered
   */
	protected EList<AbstractEvent> entry;





	/**
   * The cached value of the '{@link #getDoActivity() <em>Do Activity</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDoActivity()
   * @generated
   * @ordered
   */
	protected EList<AbstractEvent> doActivity;





	/**
   * The cached value of the '{@link #getExit() <em>Exit</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExit()
   * @generated
   * @ordered
   */
	protected EList<AbstractEvent> exit;





	/**
   * The cached value of the '{@link #getStateInvariant() <em>State Invariant</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getStateInvariant()
   * @generated
   * @ordered
   */
	protected AbstractConstraint stateInvariant;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StateImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacommonPackage.Literals.STATE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Region> getOwnedRegions() {

    if (ownedRegions == null) {
      ownedRegions = new EObjectContainmentEList.Resolving<Region>(Region.class, this, CapellacommonPackage.STATE__OWNED_REGIONS);
    }
    return ownedRegions;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Pseudostate> getOwnedConnectionPoints() {

    if (ownedConnectionPoints == null) {
      ownedConnectionPoints = new EObjectContainmentEList.Resolving<Pseudostate>(Pseudostate.class, this, CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS);
    }
    return ownedConnectionPoints;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractFunction> getAvailableAbstractFunctions() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<FunctionalChain> getAvailableFunctionalChains() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.STATE__AVAILABLE_FUNCTIONAL_CHAINS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE__AVAILABLE_FUNCTIONAL_CHAINS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<FunctionalChain> resultAsList = (Collection<FunctionalChain>) result;
    return new EcoreEList.UnmodifiableEList<FunctionalChain>(this, CapellacommonPackage.Literals.STATE__AVAILABLE_FUNCTIONAL_CHAINS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractCapability> getAvailableAbstractCapabilities() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_CAPABILITIES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_CAPABILITIES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractCapability> resultAsList = (Collection<AbstractCapability>) result;
    return new EcoreEList.UnmodifiableEList<AbstractCapability>(this, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_CAPABILITIES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractEvent> getEntry() {

    if (entry == null) {
      entry = new EObjectResolvingEList<AbstractEvent>(AbstractEvent.class, this, CapellacommonPackage.STATE__ENTRY);
    }
    return entry;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractEvent> getDoActivity() {

    if (doActivity == null) {
      doActivity = new EObjectResolvingEList<AbstractEvent>(AbstractEvent.class, this, CapellacommonPackage.STATE__DO_ACTIVITY);
    }
    return doActivity;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractEvent> getExit() {

    if (exit == null) {
      exit = new EObjectResolvingEList<AbstractEvent>(AbstractEvent.class, this, CapellacommonPackage.STATE__EXIT);
    }
    return exit;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint getStateInvariant() {

    if (stateInvariant != null && stateInvariant.eIsProxy()) {
      InternalEObject oldStateInvariant = (InternalEObject)stateInvariant;
      stateInvariant = (AbstractConstraint)eResolveProxy(oldStateInvariant);
      if (stateInvariant != oldStateInvariant) {
        InternalEObject newStateInvariant = (InternalEObject)stateInvariant;
        NotificationChain msgs = oldStateInvariant.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE__STATE_INVARIANT, null, null);
        if (newStateInvariant.eInternalContainer() == null) {
          msgs = newStateInvariant.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE__STATE_INVARIANT, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacommonPackage.STATE__STATE_INVARIANT, oldStateInvariant, stateInvariant));
      }
    }
    return stateInvariant;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint basicGetStateInvariant() {

    return stateInvariant;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetStateInvariant(AbstractConstraint newStateInvariant, NotificationChain msgs) {

    AbstractConstraint oldStateInvariant = stateInvariant;
    stateInvariant = newStateInvariant;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE__STATE_INVARIANT, oldStateInvariant, newStateInvariant);
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
	public void setStateInvariant(AbstractConstraint newStateInvariant) {

    if (newStateInvariant != stateInvariant) {
      NotificationChain msgs = null;
      if (stateInvariant != null)
        msgs = ((InternalEObject)stateInvariant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE__STATE_INVARIANT, null, msgs);
      if (newStateInvariant != null)
        msgs = ((InternalEObject)newStateInvariant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CapellacommonPackage.STATE__STATE_INVARIANT, null, msgs);
      msgs = basicSetStateInvariant(newStateInvariant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacommonPackage.STATE__STATE_INVARIANT, newStateInvariant, newStateInvariant));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CapellacommonPackage.STATE__OWNED_REGIONS:
        return ((InternalEList<?>)getOwnedRegions()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS:
        return ((InternalEList<?>)getOwnedConnectionPoints()).basicRemove(otherEnd, msgs);
      case CapellacommonPackage.STATE__STATE_INVARIANT:
        return basicSetStateInvariant(null, msgs);
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
      case CapellacommonPackage.STATE__OWNED_REGIONS:
        return getOwnedRegions();
      case CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS:
        return getOwnedConnectionPoints();
      case CapellacommonPackage.STATE__AVAILABLE_ABSTRACT_FUNCTIONS:
        return getAvailableAbstractFunctions();
      case CapellacommonPackage.STATE__AVAILABLE_FUNCTIONAL_CHAINS:
        return getAvailableFunctionalChains();
      case CapellacommonPackage.STATE__AVAILABLE_ABSTRACT_CAPABILITIES:
        return getAvailableAbstractCapabilities();
      case CapellacommonPackage.STATE__ENTRY:
        return getEntry();
      case CapellacommonPackage.STATE__DO_ACTIVITY:
        return getDoActivity();
      case CapellacommonPackage.STATE__EXIT:
        return getExit();
      case CapellacommonPackage.STATE__STATE_INVARIANT:
        if (resolve) return getStateInvariant();
        return basicGetStateInvariant();
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
      case CapellacommonPackage.STATE__OWNED_REGIONS:
        getOwnedRegions().clear();
        getOwnedRegions().addAll((Collection<? extends Region>)newValue);
        return;
      case CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS:
        getOwnedConnectionPoints().clear();
        getOwnedConnectionPoints().addAll((Collection<? extends Pseudostate>)newValue);
        return;
      case CapellacommonPackage.STATE__ENTRY:
        getEntry().clear();
        getEntry().addAll((Collection<? extends AbstractEvent>)newValue);
        return;
      case CapellacommonPackage.STATE__DO_ACTIVITY:
        getDoActivity().clear();
        getDoActivity().addAll((Collection<? extends AbstractEvent>)newValue);
        return;
      case CapellacommonPackage.STATE__EXIT:
        getExit().clear();
        getExit().addAll((Collection<? extends AbstractEvent>)newValue);
        return;
      case CapellacommonPackage.STATE__STATE_INVARIANT:
          setStateInvariant((AbstractConstraint)newValue);
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
      case CapellacommonPackage.STATE__OWNED_REGIONS:
        getOwnedRegions().clear();
        return;
      case CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS:
        getOwnedConnectionPoints().clear();
        return;
      case CapellacommonPackage.STATE__ENTRY:
        getEntry().clear();
        return;
      case CapellacommonPackage.STATE__DO_ACTIVITY:
        getDoActivity().clear();
        return;
      case CapellacommonPackage.STATE__EXIT:
        getExit().clear();
        return;
      case CapellacommonPackage.STATE__STATE_INVARIANT:
        setStateInvariant((AbstractConstraint)null);
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
      case CapellacommonPackage.STATE__OWNED_REGIONS:
        return ownedRegions != null && !ownedRegions.isEmpty();
      case CapellacommonPackage.STATE__OWNED_CONNECTION_POINTS:
        return ownedConnectionPoints != null && !ownedConnectionPoints.isEmpty();
      case CapellacommonPackage.STATE__AVAILABLE_ABSTRACT_FUNCTIONS:
        return !getAvailableAbstractFunctions().isEmpty();
      case CapellacommonPackage.STATE__AVAILABLE_FUNCTIONAL_CHAINS:
        return !getAvailableFunctionalChains().isEmpty();
      case CapellacommonPackage.STATE__AVAILABLE_ABSTRACT_CAPABILITIES:
        return !getAvailableAbstractCapabilities().isEmpty();
      case CapellacommonPackage.STATE__ENTRY:
        return entry != null && !entry.isEmpty();
      case CapellacommonPackage.STATE__DO_ACTIVITY:
        return doActivity != null && !doActivity.isEmpty();
      case CapellacommonPackage.STATE__EXIT:
        return exit != null && !exit.isEmpty();
      case CapellacommonPackage.STATE__STATE_INVARIANT:
        return stateInvariant != null;
    }
    return super.eIsSet(featureID);
  }



} //StateImpl