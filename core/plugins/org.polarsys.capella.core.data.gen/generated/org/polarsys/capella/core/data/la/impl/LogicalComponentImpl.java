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
import org.polarsys.capella.core.data.cs.impl.SystemComponentImpl;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.SystemRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalComponents <em>Owned Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalArchitectures <em>Owned Logical Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedLogicalComponentPkgs <em>Owned Logical Component Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getOwnedSystemRealizations <em>Owned System Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getSystemRealizations <em>System Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getSubLogicalComponents <em>Sub Logical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getAllocatedLogicalFunctions <em>Allocated Logical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getRealizingPhysicalComponents <em>Realizing Physical Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.la.impl.LogicalComponentImpl#getRealizedSystems <em>Realized Systems</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalComponentImpl extends SystemComponentImpl implements LogicalComponent {

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
	 * The cached value of the '{@link #getOwnedSystemRealizations() <em>Owned System Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSystemRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemRealization> ownedSystemRealizations;
























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

	public EList<SystemRealization> getOwnedSystemRealizations() {

		if (ownedSystemRealizations == null) {
			ownedSystemRealizations = new EObjectContainmentEList.Resolving<SystemRealization>(SystemRealization.class, this, LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS);
		}
		return ownedSystemRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<SystemRealization> getSystemRealizations() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<SystemRealization> resultAsList = (Collection<SystemRealization>) result;
		return new EcoreEList.UnmodifiableEList<SystemRealization>(this, LaPackage.Literals.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<org.polarsys.capella.core.data.ctx.System> getRealizedSystems() {


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
    EAnnotation annotation = LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEMS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<org.polarsys.capella.core.data.ctx.System> resultAsList = (Collection<org.polarsys.capella.core.data.ctx.System>) result;
		return new EcoreEList.UnmodifiableEList<org.polarsys.capella.core.data.ctx.System>(this, LaPackage.Literals.LOGICAL_COMPONENT__REALIZED_SYSTEMS, resultAsList.size(), resultAsList.toArray());
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
			case LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS:
				return ((InternalEList<?>)getOwnedSystemRealizations()).basicRemove(otherEnd, msgs);
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
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
				return getOwnedLogicalComponents();
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
				return getOwnedLogicalArchitectures();
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
				return getOwnedLogicalComponentPkgs();
			case LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS:
				return getOwnedSystemRealizations();
			case LaPackage.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS:
				return getSystemRealizations();
			case LaPackage.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS:
				return getSubLogicalComponents();
			case LaPackage.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS:
				return getAllocatedLogicalFunctions();
			case LaPackage.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS:
				return getRealizingPhysicalComponents();
			case LaPackage.LOGICAL_COMPONENT__REALIZED_SYSTEMS:
				return getRealizedSystems();
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
			case LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS:
				getOwnedSystemRealizations().clear();
				getOwnedSystemRealizations().addAll((Collection<? extends SystemRealization>)newValue);
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
			case LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS:
				getOwnedSystemRealizations().clear();
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
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS:
				return ownedLogicalComponents != null && !ownedLogicalComponents.isEmpty();
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_ARCHITECTURES:
				return ownedLogicalArchitectures != null && !ownedLogicalArchitectures.isEmpty();
			case LaPackage.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS:
				return ownedLogicalComponentPkgs != null && !ownedLogicalComponentPkgs.isEmpty();
			case LaPackage.LOGICAL_COMPONENT__OWNED_SYSTEM_REALIZATIONS:
				return ownedSystemRealizations != null && !ownedSystemRealizations.isEmpty();
			case LaPackage.LOGICAL_COMPONENT__SYSTEM_REALIZATIONS:
				return !getSystemRealizations().isEmpty();
			case LaPackage.LOGICAL_COMPONENT__SUB_LOGICAL_COMPONENTS:
				return !getSubLogicalComponents().isEmpty();
			case LaPackage.LOGICAL_COMPONENT__ALLOCATED_LOGICAL_FUNCTIONS:
				return !getAllocatedLogicalFunctions().isEmpty();
			case LaPackage.LOGICAL_COMPONENT__REALIZING_PHYSICAL_COMPONENTS:
				return !getRealizingPhysicalComponents().isEmpty();
			case LaPackage.LOGICAL_COMPONENT__REALIZED_SYSTEMS:
				return !getRealizedSystems().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //LogicalComponentImpl