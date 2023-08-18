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

package org.polarsys.capella.core.data.pa.impl;

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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionImpl;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl#getOwnedPhysicalFunctionPkgs <em>Owned Physical Function Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl#getAllocatingPhysicalComponents <em>Allocating Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl#getRealizedLogicalFunctions <em>Realized Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl#getContainedPhysicalFunctions <em>Contained Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalFunctionImpl#getChildrenPhysicalFunctions <em>Children Physical Functions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalFunctionImpl extends AbstractFunctionImpl implements PhysicalFunction {

	/**
   * The cached value of the '{@link #getOwnedPhysicalFunctionPkgs() <em>Owned Physical Function Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPhysicalFunctionPkgs()
   * @generated
   * @ordered
   */
	protected EList<PhysicalFunctionPkg> ownedPhysicalFunctionPkgs;
























	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalFunctionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return PaPackage.Literals.PHYSICAL_FUNCTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalFunctionPkg> getOwnedPhysicalFunctionPkgs() {

    if (ownedPhysicalFunctionPkgs == null) {
      ownedPhysicalFunctionPkgs = new EObjectContainmentEList.Resolving<PhysicalFunctionPkg>(PhysicalFunctionPkg.class, this, PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS);
    }
    return ownedPhysicalFunctionPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalComponent> getAllocatingPhysicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getRealizedLogicalFunctions() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, PaPackage.Literals.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalFunction> getContainedPhysicalFunctions() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, PaPackage.Literals.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalFunction> getChildrenPhysicalFunctions() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, PaPackage.Literals.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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
      case PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS:
        return ((InternalEList<?>)getOwnedPhysicalFunctionPkgs()).basicRemove(otherEnd, msgs);
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
      case PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS:
        return getOwnedPhysicalFunctionPkgs();
      case PaPackage.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS:
        return getAllocatingPhysicalComponents();
      case PaPackage.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS:
        return getRealizedLogicalFunctions();
      case PaPackage.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS:
        return getContainedPhysicalFunctions();
      case PaPackage.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS:
        return getChildrenPhysicalFunctions();
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
      case PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS:
        getOwnedPhysicalFunctionPkgs().clear();
        getOwnedPhysicalFunctionPkgs().addAll((Collection<? extends PhysicalFunctionPkg>)newValue);
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
      case PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS:
        getOwnedPhysicalFunctionPkgs().clear();
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
      case PaPackage.PHYSICAL_FUNCTION__OWNED_PHYSICAL_FUNCTION_PKGS:
        return ownedPhysicalFunctionPkgs != null && !ownedPhysicalFunctionPkgs.isEmpty();
      case PaPackage.PHYSICAL_FUNCTION__ALLOCATING_PHYSICAL_COMPONENTS:
        return !getAllocatingPhysicalComponents().isEmpty();
      case PaPackage.PHYSICAL_FUNCTION__REALIZED_LOGICAL_FUNCTIONS:
        return !getRealizedLogicalFunctions().isEmpty();
      case PaPackage.PHYSICAL_FUNCTION__CONTAINED_PHYSICAL_FUNCTIONS:
        return !getContainedPhysicalFunctions().isEmpty();
      case PaPackage.PHYSICAL_FUNCTION__CHILDREN_PHYSICAL_FUNCTIONS:
        return !getChildrenPhysicalFunctions().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //PhysicalFunctionImpl