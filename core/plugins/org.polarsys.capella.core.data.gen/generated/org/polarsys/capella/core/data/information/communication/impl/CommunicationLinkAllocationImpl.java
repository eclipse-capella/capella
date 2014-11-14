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
package org.polarsys.capella.core.data.information.communication.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.polarsys.capella.common.tig.model.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.AllocationImpl;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkAllocation;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkAllocationImpl#getAllocatingLink <em>Allocating Link</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkAllocationImpl#getAllocatedLink <em>Allocated Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommunicationLinkAllocationImpl extends AllocationImpl implements CommunicationLinkAllocation {








	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommunicationLinkAllocationImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CommunicationLink getAllocatingLink() {

		CommunicationLink allocatingLink = basicGetAllocatingLink();
		return allocatingLink != null && allocatingLink.eIsProxy() ? (CommunicationLink)eResolveProxy((InternalEObject)allocatingLink) : allocatingLink;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CommunicationLink basicGetAllocatingLink() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATING_LINK.getEAnnotation(org.polarsys.capella.common.tig.model.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATING_LINK, annotation);
		
		try {
			return (CommunicationLink) result;
	  } catch (ClassCastException cce_p) {
	     cce_p.printStackTrace();
	    return null;
	  }
		
	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CommunicationLink getAllocatedLink() {

		CommunicationLink allocatedLink = basicGetAllocatedLink();
		return allocatedLink != null && allocatedLink.eIsProxy() ? (CommunicationLink)eResolveProxy((InternalEObject)allocatedLink) : allocatedLink;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CommunicationLink basicGetAllocatedLink() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATED_LINK.getEAnnotation(org.polarsys.capella.common.tig.model.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_ALLOCATION__ALLOCATED_LINK, annotation);
		
		try {
			return (CommunicationLink) result;
	  } catch (ClassCastException cce_p) {
	     cce_p.printStackTrace();
	    return null;
	  }
		
	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommunicationPackage.COMMUNICATION_LINK_ALLOCATION__ALLOCATING_LINK:
				if (resolve) return getAllocatingLink();
				return basicGetAllocatingLink();
			case CommunicationPackage.COMMUNICATION_LINK_ALLOCATION__ALLOCATED_LINK:
				if (resolve) return getAllocatedLink();
				return basicGetAllocatedLink();
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
			case CommunicationPackage.COMMUNICATION_LINK_ALLOCATION__ALLOCATING_LINK:
				return basicGetAllocatingLink() != null;
			case CommunicationPackage.COMMUNICATION_LINK_ALLOCATION__ALLOCATED_LINK:
				return basicGetAllocatedLink() != null;
		}
		return super.eIsSet(featureID);
	}



} //CommunicationLinkAllocationImpl