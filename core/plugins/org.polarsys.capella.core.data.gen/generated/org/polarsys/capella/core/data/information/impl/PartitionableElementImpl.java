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

package org.polarsys.capella.core.data.information.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partitionable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PartitionableElementImpl#getOwnedPartitions <em>Owned Partitions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PartitionableElementImpl#getRepresentingPartitions <em>Representing Partitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PartitionableElementImpl extends ClassifierImpl implements PartitionableElement {








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PartitionableElementImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InformationPackage.Literals.PARTITIONABLE_ELEMENT;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<Partition> getOwnedPartitions() {


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
    EAnnotation annotation = InformationPackage.Literals.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Partition> resultAsList = (Collection<Partition>) result;
		return new EcoreEList.UnmodifiableEList<Partition>(this, InformationPackage.Literals.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Partition> getRepresentingPartitions() {


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
    EAnnotation annotation = InformationPackage.Literals.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<Partition> resultAsList = (Collection<Partition>) result;
		return new EcoreEList.UnmodifiableEList<Partition>(this, InformationPackage.Literals.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS, resultAsList.size(), resultAsList.toArray());
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

	public boolean isDecomposed() {
		return this.getOwnedPartitions().size() > 0;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InformationPackage.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS:
				return getOwnedPartitions();
			case InformationPackage.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS:
				return getRepresentingPartitions();
		}
		return super.eGet(featureID, resolve, coreType);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case InformationPackage.PARTITIONABLE_ELEMENT__OWNED_PARTITIONS:
				return !getOwnedPartitions().isEmpty();
			case InformationPackage.PARTITIONABLE_ELEMENT__REPRESENTING_PARTITIONS:
				return !getRepresentingPartitions().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //PartitionableElementImpl