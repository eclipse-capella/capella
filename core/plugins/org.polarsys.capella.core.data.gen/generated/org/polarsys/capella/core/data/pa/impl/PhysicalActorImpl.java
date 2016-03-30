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
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl#getOwnedLogicalActorRealizations <em>Owned Logical Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl#getLogicalActorRealizations <em>Logical Actor Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl#getAllocatedPhysicalFunctions <em>Allocated Physical Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl#getRealizedLogicalActors <em>Realized Logical Actors</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.pa.impl.PhysicalActorImpl#getDeployedPhysicalComponents <em>Deployed Physical Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PhysicalActorImpl extends AbstractPhysicalComponentImpl implements PhysicalActor {

	/**
	 * The cached value of the '{@link #getOwnedLogicalActorRealizations() <em>Owned Logical Actor Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLogicalActorRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalActorRealization> ownedLogicalActorRealizations;




















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhysicalActorImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PaPackage.Literals.PHYSICAL_ACTOR;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalActorRealization> getOwnedLogicalActorRealizations() {

		if (ownedLogicalActorRealizations == null) {
			ownedLogicalActorRealizations = new EObjectContainmentEList.Resolving<LogicalActorRealization>(LogicalActorRealization.class, this, PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS);
		}
		return ownedLogicalActorRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<LogicalActorRealization> getLogicalActorRealizations() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalActorRealization> resultAsList = (Collection<LogicalActorRealization>) result;
		return new EcoreEList.UnmodifiableEList<LogicalActorRealization>(this, PaPackage.Literals.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalFunction> getAllocatedPhysicalFunctions() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalFunction> resultAsList = (Collection<PhysicalFunction>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalFunction>(this, PaPackage.Literals.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<LogicalActor> getRealizedLogicalActors() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<LogicalActor> resultAsList = (Collection<LogicalActor>) result;
		return new EcoreEList.UnmodifiableEList<LogicalActor>(this, PaPackage.Literals.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalComponent> getDeployedPhysicalComponents() {


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
    EAnnotation annotation = PaPackage.Literals.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, PaPackage.Literals.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalComponent> resultAsList = (Collection<PhysicalComponent>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalComponent>(this, PaPackage.Literals.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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
			case PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS:
				return ((InternalEList<?>)getOwnedLogicalActorRealizations()).basicRemove(otherEnd, msgs);
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
			case PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS:
				return getOwnedLogicalActorRealizations();
			case PaPackage.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS:
				return getLogicalActorRealizations();
			case PaPackage.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS:
				return getAllocatedPhysicalFunctions();
			case PaPackage.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS:
				return getRealizedLogicalActors();
			case PaPackage.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS:
				return getDeployedPhysicalComponents();
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
			case PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS:
				getOwnedLogicalActorRealizations().clear();
				getOwnedLogicalActorRealizations().addAll((Collection<? extends LogicalActorRealization>)newValue);
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
			case PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS:
				getOwnedLogicalActorRealizations().clear();
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
			case PaPackage.PHYSICAL_ACTOR__OWNED_LOGICAL_ACTOR_REALIZATIONS:
				return ownedLogicalActorRealizations != null && !ownedLogicalActorRealizations.isEmpty();
			case PaPackage.PHYSICAL_ACTOR__LOGICAL_ACTOR_REALIZATIONS:
				return !getLogicalActorRealizations().isEmpty();
			case PaPackage.PHYSICAL_ACTOR__ALLOCATED_PHYSICAL_FUNCTIONS:
				return !getAllocatedPhysicalFunctions().isEmpty();
			case PaPackage.PHYSICAL_ACTOR__REALIZED_LOGICAL_ACTORS:
				return !getRealizedLogicalActors().isEmpty();
			case PaPackage.PHYSICAL_ACTOR__DEPLOYED_PHYSICAL_COMPONENTS:
				return !getDeployedPhysicalComponents().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //PhysicalActorImpl