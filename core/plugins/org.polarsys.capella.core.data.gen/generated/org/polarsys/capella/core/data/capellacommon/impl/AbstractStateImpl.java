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
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.AbstractStateRealization;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getReferencedStates <em>Referenced States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getExploitedStates <em>Exploited States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getOwnedAbstractStateRealizations <em>Owned Abstract State Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getRealizedAbstractStates <em>Realized Abstract States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getRealizingAbstractStates <em>Realizing Abstract States</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacommon.impl.AbstractStateImpl#getInvolverRegions <em>Involver Regions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractStateImpl extends NamedElementImpl implements AbstractState {

	/**
   * The cached value of the '{@link #getReferencedStates() <em>Referenced States</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedStates()
   * @generated
   * @ordered
   */
	protected EList<IState> referencedStates;





	/**
   * The cached value of the '{@link #getExploitedStates() <em>Exploited States</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExploitedStates()
   * @generated
   * @ordered
   */
  protected EList<IState> exploitedStates;





  /**
   * The cached value of the '{@link #getOwnedAbstractStateRealizations() <em>Owned Abstract State Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedAbstractStateRealizations()
   * @generated
   * @ordered
   */
	protected EList<AbstractStateRealization> ownedAbstractStateRealizations;













	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractStateImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacommonPackage.Literals.ABSTRACT_STATE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<IState> getReferencedStates() {

    if (referencedStates == null) {
      referencedStates = new EObjectResolvingEList<IState>(IState.class, this, CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES);
    }
    return referencedStates;
  }





	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */

  public EList<IState> getExploitedStates() {

    if (exploitedStates == null) {
      exploitedStates = new EObjectResolvingEList<IState>(IState.class, this, CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES);
    }
    return exploitedStates;
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractStateRealization> getOwnedAbstractStateRealizations() {

    if (ownedAbstractStateRealizations == null) {
      ownedAbstractStateRealizations = new EObjectContainmentEList.Resolving<AbstractStateRealization>(AbstractStateRealization.class, this, CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS);
    }
    return ownedAbstractStateRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractState> getRealizedAbstractStates() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractState> resultAsList = (Collection<AbstractState>) result;
    return new EcoreEList.UnmodifiableEList<AbstractState>(this, CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractState> getRealizingAbstractStates() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractState> resultAsList = (Collection<AbstractState>) result;
    return new EcoreEList.UnmodifiableEList<AbstractState>(this, CapellacommonPackage.Literals.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES, resultAsList.size(), resultAsList.toArray());
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

	public EList<StateTransition> getOutgoing() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.ABSTRACT_STATE__OUTGOING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.ABSTRACT_STATE__OUTGOING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
    return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.ABSTRACT_STATE__OUTGOING, resultAsList.size(), resultAsList.toArray());
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

	public EList<StateTransition> getIncoming() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.ABSTRACT_STATE__INCOMING.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.ABSTRACT_STATE__INCOMING, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<StateTransition> resultAsList = (Collection<StateTransition>) result;
    return new EcoreEList.UnmodifiableEList<StateTransition>(this, CapellacommonPackage.Literals.ABSTRACT_STATE__INCOMING, resultAsList.size(), resultAsList.toArray());
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

	public EList<Region> getInvolverRegions() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Region> resultAsList = (Collection<Region>) result;
    return new EcoreEList.UnmodifiableEList<Region>(this, CapellacommonPackage.Literals.ABSTRACT_STATE__INVOLVER_REGIONS, resultAsList.size(), resultAsList.toArray());
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
      case CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS:
        return ((InternalEList<?>)getOwnedAbstractStateRealizations()).basicRemove(otherEnd, msgs);
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
      case CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES:
        return getReferencedStates();
      case CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES:
        return getExploitedStates();
      case CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS:
        return getOwnedAbstractStateRealizations();
      case CapellacommonPackage.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES:
        return getRealizedAbstractStates();
      case CapellacommonPackage.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES:
        return getRealizingAbstractStates();
      case CapellacommonPackage.ABSTRACT_STATE__OUTGOING:
        return getOutgoing();
      case CapellacommonPackage.ABSTRACT_STATE__INCOMING:
        return getIncoming();
      case CapellacommonPackage.ABSTRACT_STATE__INVOLVER_REGIONS:
        return getInvolverRegions();
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
      case CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES:
        getReferencedStates().clear();
        getReferencedStates().addAll((Collection<? extends IState>)newValue);
        return;
      case CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES:
        getExploitedStates().clear();
        getExploitedStates().addAll((Collection<? extends IState>)newValue);
        return;
      case CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS:
        getOwnedAbstractStateRealizations().clear();
        getOwnedAbstractStateRealizations().addAll((Collection<? extends AbstractStateRealization>)newValue);
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
      case CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES:
        getReferencedStates().clear();
        return;
      case CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES:
        getExploitedStates().clear();
        return;
      case CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS:
        getOwnedAbstractStateRealizations().clear();
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
      case CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES:
        return referencedStates != null && !referencedStates.isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES:
        return exploitedStates != null && !exploitedStates.isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__OWNED_ABSTRACT_STATE_REALIZATIONS:
        return ownedAbstractStateRealizations != null && !ownedAbstractStateRealizations.isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__REALIZED_ABSTRACT_STATES:
        return !getRealizedAbstractStates().isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__REALIZING_ABSTRACT_STATES:
        return !getRealizingAbstractStates().isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__OUTGOING:
        return !getOutgoing().isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__INCOMING:
        return !getIncoming().isEmpty();
      case CapellacommonPackage.ABSTRACT_STATE__INVOLVER_REGIONS:
        return !getInvolverRegions().isEmpty();
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
    if (baseClass == IState.class) {
      switch (derivedFeatureID) {
        case CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES: return ModellingcorePackage.ISTATE__REFERENCED_STATES;
        case CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES: return ModellingcorePackage.ISTATE__EXPLOITED_STATES;
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
    if (baseClass == IState.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ISTATE__REFERENCED_STATES: return CapellacommonPackage.ABSTRACT_STATE__REFERENCED_STATES;
        case ModellingcorePackage.ISTATE__EXPLOITED_STATES: return CapellacommonPackage.ABSTRACT_STATE__EXPLOITED_STATES;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //AbstractStateImpl
