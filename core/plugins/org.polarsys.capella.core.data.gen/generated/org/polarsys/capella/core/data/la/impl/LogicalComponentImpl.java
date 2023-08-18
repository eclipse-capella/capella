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

package org.polarsys.capella.core.data.la.impl;

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
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.cs.impl.ComponentImpl;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getInvolvingInvolvements <em>Involving Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getCapabilityRealizationInvolvements <em>Capability Realization Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getInvolvingCapabilityRealizations <em>Involving Capability Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalComponents <em>Owned Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getSubLogicalComponents <em>Sub Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getRealizedSystemComponents <em>Realized System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getRealizingPhysicalComponents <em>Realizing Physical Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogicalComponentImpl extends ComponentImpl implements LogicalComponent {

	/**
   * The cached value of the '{@link #getOwnedLogicalComponents() <em>Owned Logical Components</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalComponents()
   * @generated
   * @ordered
   */
	protected EList<LogicalComponent> ownedLogicalComponents;





	/**
   * The cached value of the '{@link #getOwnedLogicalArchitectures() <em>Owned Logical Architectures</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalArchitectures()
   * @generated
   * @ordered
   */
	protected EList<LogicalArchitecture> ownedLogicalArchitectures;





	/**
   * The cached value of the '{@link #getOwnedLogicalComponentPkgs() <em>Owned Logical Component Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedLogicalComponentPkgs()
   * @generated
   * @ordered
   */
	protected EList<LogicalComponentPkg> ownedLogicalComponentPkgs;





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LogicalComponentImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LaPackage.Literals.LOGICAL_COMPONENT;
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

	public EList<CapabilityRealizationInvolvement> getCapabilityRealizationInvolvements() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealizationInvolvement> resultAsList = (Collection<CapabilityRealizationInvolvement>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealizationInvolvement>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CapabilityRealization> getInvolvingCapabilityRealizations() {


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
    EAnnotation annotation = CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CapabilityRealization> resultAsList = (Collection<CapabilityRealization>) result;
    return new EcoreEList.UnmodifiableEList<CapabilityRealization>(this, CapellacommonPackage.Literals.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalComponent> getOwnedLogicalComponents() {

    if (ownedLogicalComponents == null) {
      ownedLogicalComponents = new EObjectContainmentEList.Resolving<LogicalComponent>(LogicalComponent.class, this, LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS);
    }
    return ownedLogicalComponents;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalArchitecture> getOwnedLogicalArchitectures() {

    if (ownedLogicalArchitectures == null) {
      ownedLogicalArchitectures = new EObjectContainmentEList.Resolving<LogicalArchitecture>(LogicalArchitecture.class, this, LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES);
    }
    return ownedLogicalArchitectures;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponentPkg> getOwnedLogicalComponentPkgs() {

    if (ownedLogicalComponentPkgs == null) {
      ownedLogicalComponentPkgs = new EObjectContainmentEList.Resolving<LogicalComponentPkg>(LogicalComponentPkg.class, this, LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS);
    }
    return ownedLogicalComponentPkgs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LogicalComponent> getSubLogicalComponents() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalComponent> resultAsList = (Collection<LogicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<LogicalComponent>(this, LaPackage.Literals.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalFunction> getAllocatedLogicalFunctions() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<LogicalFunction> resultAsList = (Collection<LogicalFunction>) result;
    return new EcoreEList.UnmodifiableEList<LogicalFunction>(this, LaPackage.Literals.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<SystemComponent> getRealizedSystemComponents() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<SystemComponent> resultAsList = (Collection<SystemComponent>) result;
    return new EcoreEList.UnmodifiableEList<SystemComponent>(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getRealizingPhysicalComponents() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
    return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
        return ((InternalEList<?>)getOwnedLogicalComponents()).basicRemove(otherEnd, msgs);
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
        return ((InternalEList<?>)getOwnedLogicalArchitectures()).basicRemove(otherEnd, msgs);
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
        return ((InternalEList<?>)getOwnedLogicalComponentPkgs()).basicRemove(otherEnd, msgs);
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
      case LaPackage.LOGICAL_COMPONENT__INVOLVING_INVOLVEMENTS:
        return getInvolvingInvolvements();
      case LaPackage.LOGICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS:
        return getCapabilityRealizationInvolvements();
      case LaPackage.LOGICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS:
        return getInvolvingCapabilityRealizations();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
        return getOwnedLogicalComponents();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
        return getOwnedLogicalArchitectures();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
        return getOwnedLogicalComponentPkgs();
      case LaPackage.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS:
        return getSubLogicalComponents();
      case LaPackage.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS:
        return getAllocatedLogicalFunctions();
      case LaPackage.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS:
        return getRealizedSystemComponents();
      case LaPackage.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS:
        return getRealizingPhysicalComponents();
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
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
        getOwnedLogicalComponents().clear();
        getOwnedLogicalComponents().addAll((Collection<? extends LogicalComponent>)newValue);
        return;
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
        getOwnedLogicalArchitectures().clear();
        getOwnedLogicalArchitectures().addAll((Collection<? extends LogicalArchitecture>)newValue);
        return;
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
        getOwnedLogicalComponentPkgs().clear();
        getOwnedLogicalComponentPkgs().addAll((Collection<? extends LogicalComponentPkg>)newValue);
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
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
        getOwnedLogicalComponents().clear();
        return;
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
        getOwnedLogicalArchitectures().clear();
        return;
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
        getOwnedLogicalComponentPkgs().clear();
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
      case LaPackage.LOGICAL_COMPONENT__INVOLVING_INVOLVEMENTS:
        return !getInvolvingInvolvements().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS:
        return !getCapabilityRealizationInvolvements().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS:
        return !getInvolvingCapabilityRealizations().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
        return ownedLogicalComponents != null && !ownedLogicalComponents.isEmpty();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
        return ownedLogicalArchitectures != null && !ownedLogicalArchitectures.isEmpty();
      case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
        return ownedLogicalComponentPkgs != null && !ownedLogicalComponentPkgs.isEmpty();
      case LaPackage.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS:
        return !getSubLogicalComponents().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS:
        return !getAllocatedLogicalFunctions().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__REALIZED_SYSTEM_COMPONENTS:
        return !getRealizedSystemComponents().isEmpty();
      case LaPackage.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS:
        return !getRealizingPhysicalComponents().isEmpty();
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
        case LaPackage.LOGICAL_COMPONENT__INVOLVING_INVOLVEMENTS: return CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == CapabilityRealizationInvolvedElement.class) {
      switch (derivedFeatureID) {
        case LaPackage.LOGICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS;
        case LaPackage.LOGICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS: return CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS;
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
        case CapellacorePackage.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS: return LaPackage.LOGICAL_COMPONENT__INVOLVING_INVOLVEMENTS;
        default: return -1;
      }
    }
    if (baseClass == CapabilityRealizationInvolvedElement.class) {
      switch (baseFeatureID) {
        case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__CAPABILITY_REALIZATION_INVOLVEMENTS: return LaPackage.LOGICAL_COMPONENT__CAPABILITY_REALIZATION_INVOLVEMENTS;
        case CapellacommonPackage.CAPABILITY_REALIZATION_INVOLVED_ELEMENT__INVOLVING_CAPABILITY_REALIZATIONS: return LaPackage.LOGICAL_COMPONENT__INVOLVING_CAPABILITY_REALIZATIONS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }



} //LogicalComponentImpl