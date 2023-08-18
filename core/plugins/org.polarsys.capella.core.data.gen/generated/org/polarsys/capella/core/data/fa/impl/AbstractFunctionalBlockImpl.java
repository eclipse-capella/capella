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

package org.polarsys.capella.core.data.fa.impl;

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
import org.polarsys.capella.core.data.capellacore.impl.TypeImpl;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.FaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Functional Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getOwnedFunctionalAllocation <em>Owned Functional Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getFunctionalAllocations <em>Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getAllocatedFunctions <em>Allocated Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.impl.AbstractFunctionalBlockImpl#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractFunctionalBlockImpl extends TypeImpl implements AbstractFunctionalBlock {

	/**
   * The cached value of the '{@link #getOwnedFunctionalAllocation() <em>Owned Functional Allocation</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedFunctionalAllocation()
   * @generated
   * @ordered
   */
	protected EList<ComponentFunctionalAllocation> ownedFunctionalAllocation;





	/**
   * The cached value of the '{@link #getOwnedComponentExchanges() <em>Owned Component Exchanges</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchanges()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchange> ownedComponentExchanges;





	/**
   * The cached value of the '{@link #getOwnedComponentExchangeCategories() <em>Owned Component Exchange Categories</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedComponentExchangeCategories()
   * @generated
   * @ordered
   */
	protected EList<ComponentExchangeCategory> ownedComponentExchangeCategories;













	/**
   * The cached value of the '{@link #getInExchangeLinks() <em>In Exchange Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInExchangeLinks()
   * @generated
   * @ordered
   */
	protected EList<ExchangeLink> inExchangeLinks;





	/**
   * The cached value of the '{@link #getOutExchangeLinks() <em>Out Exchange Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOutExchangeLinks()
   * @generated
   * @ordered
   */
	protected EList<ExchangeLink> outExchangeLinks;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractFunctionalBlockImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocation() {

    if (ownedFunctionalAllocation == null) {
      ownedFunctionalAllocation = new EObjectContainmentEList.Resolving<ComponentFunctionalAllocation>(ComponentFunctionalAllocation.class, this, FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
    }
    return ownedFunctionalAllocation;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchange> getOwnedComponentExchanges() {

    if (ownedComponentExchanges == null) {
      ownedComponentExchanges = new EObjectContainmentEList<ComponentExchange>(ComponentExchange.class, this, FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES);
    }
    return ownedComponentExchanges;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentExchangeCategory> getOwnedComponentExchangeCategories() {

    if (ownedComponentExchangeCategories == null) {
      ownedComponentExchangeCategories = new EObjectContainmentEList<ComponentExchangeCategory>(ComponentExchangeCategory.class, this, FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    }
    return ownedComponentExchangeCategories;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getFunctionalAllocations() {


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
    EAnnotation annotation = FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentFunctionalAllocation> resultAsList = (Collection<ComponentFunctionalAllocation>) result;
    return new EcoreEList.UnmodifiableEList<ComponentFunctionalAllocation>(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<AbstractFunction> getAllocatedFunctions() {


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
    EAnnotation annotation = FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<AbstractFunction> resultAsList = (Collection<AbstractFunction>) result;
    return new EcoreEList.UnmodifiableEList<AbstractFunction>(this, FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeLink> getInExchangeLinks() {

    if (inExchangeLinks == null) {
      inExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS);
    }
    return inExchangeLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ExchangeLink> getOutExchangeLinks() {

    if (outExchangeLinks == null) {
      outExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS);
    }
    return outExchangeLinks;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return ((InternalEList<?>)getOwnedFunctionalAllocation()).basicRemove(otherEnd, msgs);
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES:
        return ((InternalEList<?>)getOwnedComponentExchanges()).basicRemove(otherEnd, msgs);
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ((InternalEList<?>)getOwnedComponentExchangeCategories()).basicRemove(otherEnd, msgs);
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
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return getOwnedFunctionalAllocation();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES:
        return getOwnedComponentExchanges();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return getOwnedComponentExchangeCategories();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS:
        return getFunctionalAllocations();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS:
        return getAllocatedFunctions();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS:
        return getInExchangeLinks();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS:
        return getOutExchangeLinks();
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
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        getOwnedFunctionalAllocation().addAll((Collection<? extends ComponentFunctionalAllocation>)newValue);
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        getOwnedComponentExchanges().addAll((Collection<? extends ComponentExchange>)newValue);
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        getOwnedComponentExchangeCategories().addAll((Collection<? extends ComponentExchangeCategory>)newValue);
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        getInExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        getOutExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
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
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        return;
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
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
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return ownedFunctionalAllocation != null && !ownedFunctionalAllocation.isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES:
        return ownedComponentExchanges != null && !ownedComponentExchanges.isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ownedComponentExchangeCategories != null && !ownedComponentExchangeCategories.isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS:
        return !getFunctionalAllocations().isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS:
        return !getAllocatedFunctions().isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS:
        return inExchangeLinks != null && !inExchangeLinks.isEmpty();
      case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS:
        return outExchangeLinks != null && !outExchangeLinks.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //AbstractFunctionalBlockImpl