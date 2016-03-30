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

package org.polarsys.capella.core.data.cs.impl;

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
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.cs.ArchitectureAllocation;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.impl.AbstractFunctionalArchitectureImpl;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getOwnedRequirementPkgs <em>Owned Requirement Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getProvisionedArchitectureAllocations <em>Provisioned Architecture Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getProvisioningArchitectureAllocations <em>Provisioning Architecture Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getAllocatedArchitectures <em>Allocated Architectures</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockArchitectureImpl#getAllocatingArchitectures <em>Allocating Architectures</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BlockArchitectureImpl extends AbstractFunctionalArchitectureImpl implements BlockArchitecture {

	/**
	 * The cached value of the '{@link #getOwnedRequirementPkgs() <em>Owned Requirement Pkgs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedRequirementPkgs()
	 * @generated
	 * @ordered
	 */
	protected EList<RequirementsPkg> ownedRequirementPkgs;





	/**
	 * The cached value of the '{@link #getOwnedAbstractCapabilityPkg() <em>Owned Abstract Capability Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAbstractCapabilityPkg()
	 * @generated
	 * @ordered
	 */
	protected AbstractCapabilityPkg ownedAbstractCapabilityPkg;





	/**
	 * The cached value of the '{@link #getOwnedInterfacePkg() <em>Owned Interface Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedInterfacePkg()
	 * @generated
	 * @ordered
	 */
	protected InterfacePkg ownedInterfacePkg;





	/**
	 * The cached value of the '{@link #getOwnedDataPkg() <em>Owned Data Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedDataPkg()
	 * @generated
	 * @ordered
	 */
	protected DataPkg ownedDataPkg;




















	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BlockArchitectureImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CsPackage.Literals.BLOCK_ARCHITECTURE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<RequirementsPkg> getOwnedRequirementPkgs() {

		if (ownedRequirementPkgs == null) {
			ownedRequirementPkgs = new EObjectContainmentEList.Resolving<RequirementsPkg>(RequirementsPkg.class, this, CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS);
		}
		return ownedRequirementPkgs;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapabilityPkg getOwnedAbstractCapabilityPkg() {

		if (ownedAbstractCapabilityPkg != null && ownedAbstractCapabilityPkg.eIsProxy()) {
			InternalEObject oldOwnedAbstractCapabilityPkg = (InternalEObject)ownedAbstractCapabilityPkg;
			ownedAbstractCapabilityPkg = (AbstractCapabilityPkg)eResolveProxy(oldOwnedAbstractCapabilityPkg);
			if (ownedAbstractCapabilityPkg != oldOwnedAbstractCapabilityPkg) {
				InternalEObject newOwnedAbstractCapabilityPkg = (InternalEObject)ownedAbstractCapabilityPkg;
				NotificationChain msgs = oldOwnedAbstractCapabilityPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, null, null);
				if (newOwnedAbstractCapabilityPkg.eInternalContainer() == null) {
					msgs = newOwnedAbstractCapabilityPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, ownedAbstractCapabilityPkg));
			}
		}
		return ownedAbstractCapabilityPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractCapabilityPkg basicGetOwnedAbstractCapabilityPkg() {

		return ownedAbstractCapabilityPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedAbstractCapabilityPkg(AbstractCapabilityPkg newOwnedAbstractCapabilityPkg, NotificationChain msgs) {

		AbstractCapabilityPkg oldOwnedAbstractCapabilityPkg = ownedAbstractCapabilityPkg;
		ownedAbstractCapabilityPkg = newOwnedAbstractCapabilityPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedAbstractCapabilityPkg(AbstractCapabilityPkg newOwnedAbstractCapabilityPkg) {

		if (newOwnedAbstractCapabilityPkg != ownedAbstractCapabilityPkg) {
			NotificationChain msgs = null;
			if (ownedAbstractCapabilityPkg != null)
				msgs = ((InternalEObject)ownedAbstractCapabilityPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
			if (newOwnedAbstractCapabilityPkg != null)
				msgs = ((InternalEObject)newOwnedAbstractCapabilityPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
			msgs = basicSetOwnedAbstractCapabilityPkg(newOwnedAbstractCapabilityPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG, newOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InterfacePkg getOwnedInterfacePkg() {

		if (ownedInterfacePkg != null && ownedInterfacePkg.eIsProxy()) {
			InternalEObject oldOwnedInterfacePkg = (InternalEObject)ownedInterfacePkg;
			ownedInterfacePkg = (InterfacePkg)eResolveProxy(oldOwnedInterfacePkg);
			if (ownedInterfacePkg != oldOwnedInterfacePkg) {
				InternalEObject newOwnedInterfacePkg = (InternalEObject)ownedInterfacePkg;
				NotificationChain msgs = oldOwnedInterfacePkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, null, null);
				if (newOwnedInterfacePkg.eInternalContainer() == null) {
					msgs = newOwnedInterfacePkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, ownedInterfacePkg));
			}
		}
		return ownedInterfacePkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public InterfacePkg basicGetOwnedInterfacePkg() {

		return ownedInterfacePkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedInterfacePkg(InterfacePkg newOwnedInterfacePkg, NotificationChain msgs) {

		InterfacePkg oldOwnedInterfacePkg = ownedInterfacePkg;
		ownedInterfacePkg = newOwnedInterfacePkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, newOwnedInterfacePkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedInterfacePkg(InterfacePkg newOwnedInterfacePkg) {

		if (newOwnedInterfacePkg != ownedInterfacePkg) {
			NotificationChain msgs = null;
			if (ownedInterfacePkg != null)
				msgs = ((InternalEObject)ownedInterfacePkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, null, msgs);
			if (newOwnedInterfacePkg != null)
				msgs = ((InternalEObject)newOwnedInterfacePkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, null, msgs);
			msgs = basicSetOwnedInterfacePkg(newOwnedInterfacePkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG, newOwnedInterfacePkg, newOwnedInterfacePkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DataPkg getOwnedDataPkg() {

		if (ownedDataPkg != null && ownedDataPkg.eIsProxy()) {
			InternalEObject oldOwnedDataPkg = (InternalEObject)ownedDataPkg;
			ownedDataPkg = (DataPkg)eResolveProxy(oldOwnedDataPkg);
			if (ownedDataPkg != oldOwnedDataPkg) {
				InternalEObject newOwnedDataPkg = (InternalEObject)ownedDataPkg;
				NotificationChain msgs = oldOwnedDataPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, null, null);
				if (newOwnedDataPkg.eInternalContainer() == null) {
					msgs = newOwnedDataPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, oldOwnedDataPkg, ownedDataPkg));
			}
		}
		return ownedDataPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public DataPkg basicGetOwnedDataPkg() {

		return ownedDataPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedDataPkg(DataPkg newOwnedDataPkg, NotificationChain msgs) {

		DataPkg oldOwnedDataPkg = ownedDataPkg;
		ownedDataPkg = newOwnedDataPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, oldOwnedDataPkg, newOwnedDataPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedDataPkg(DataPkg newOwnedDataPkg) {

		if (newOwnedDataPkg != ownedDataPkg) {
			NotificationChain msgs = null;
			if (ownedDataPkg != null)
				msgs = ((InternalEObject)ownedDataPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, null, msgs);
			if (newOwnedDataPkg != null)
				msgs = ((InternalEObject)newOwnedDataPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, null, msgs);
			msgs = basicSetOwnedDataPkg(newOwnedDataPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG, newOwnedDataPkg, newOwnedDataPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<ArchitectureAllocation> getProvisionedArchitectureAllocations() {


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
    EAnnotation annotation = CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ArchitectureAllocation> resultAsList = (Collection<ArchitectureAllocation>) result;
		return new EcoreEList.UnmodifiableEList<ArchitectureAllocation>(this, CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ArchitectureAllocation> getProvisioningArchitectureAllocations() {


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
    EAnnotation annotation = CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<ArchitectureAllocation> resultAsList = (Collection<ArchitectureAllocation>) result;
		return new EcoreEList.UnmodifiableEList<ArchitectureAllocation>(this, CsPackage.Literals.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<BlockArchitecture> getAllocatedArchitectures() {


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
    EAnnotation annotation = CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<BlockArchitecture> resultAsList = (Collection<BlockArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<BlockArchitecture>(this, CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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

	public EList<BlockArchitecture> getAllocatingArchitectures() {


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
    EAnnotation annotation = CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<BlockArchitecture> resultAsList = (Collection<BlockArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<BlockArchitecture>(this, CsPackage.Literals.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS:
				return ((InternalEList<?>)getOwnedRequirementPkgs()).basicRemove(otherEnd, msgs);
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG:
				return basicSetOwnedAbstractCapabilityPkg(null, msgs);
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG:
				return basicSetOwnedInterfacePkg(null, msgs);
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG:
				return basicSetOwnedDataPkg(null, msgs);
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
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS:
				return getOwnedRequirementPkgs();
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG:
				if (resolve) return getOwnedAbstractCapabilityPkg();
				return basicGetOwnedAbstractCapabilityPkg();
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG:
				if (resolve) return getOwnedInterfacePkg();
				return basicGetOwnedInterfacePkg();
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG:
				if (resolve) return getOwnedDataPkg();
				return basicGetOwnedDataPkg();
			case CsPackage.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS:
				return getProvisionedArchitectureAllocations();
			case CsPackage.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS:
				return getProvisioningArchitectureAllocations();
			case CsPackage.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES:
				return getAllocatedArchitectures();
			case CsPackage.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES:
				return getAllocatingArchitectures();
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
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS:
				getOwnedRequirementPkgs().clear();
				getOwnedRequirementPkgs().addAll((Collection<? extends RequirementsPkg>)newValue);
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractCapabilityPkg) {
				// end-extension-code
					setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof InterfacePkg) {
				// end-extension-code
					setOwnedInterfacePkg((InterfacePkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof DataPkg) {
				// end-extension-code
					setOwnedDataPkg((DataPkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
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
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS:
				getOwnedRequirementPkgs().clear();
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG:
				setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)null);
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG:
				setOwnedInterfacePkg((InterfacePkg)null);
				return;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG:
				setOwnedDataPkg((DataPkg)null);
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
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_REQUIREMENT_PKGS:
				return ownedRequirementPkgs != null && !ownedRequirementPkgs.isEmpty();
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_ABSTRACT_CAPABILITY_PKG:
				return ownedAbstractCapabilityPkg != null;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_INTERFACE_PKG:
				return ownedInterfacePkg != null;
			case CsPackage.BLOCK_ARCHITECTURE__OWNED_DATA_PKG:
				return ownedDataPkg != null;
			case CsPackage.BLOCK_ARCHITECTURE__PROVISIONED_ARCHITECTURE_ALLOCATIONS:
				return !getProvisionedArchitectureAllocations().isEmpty();
			case CsPackage.BLOCK_ARCHITECTURE__PROVISIONING_ARCHITECTURE_ALLOCATIONS:
				return !getProvisioningArchitectureAllocations().isEmpty();
			case CsPackage.BLOCK_ARCHITECTURE__ALLOCATED_ARCHITECTURES:
				return !getAllocatedArchitectures().isEmpty();
			case CsPackage.BLOCK_ARCHITECTURE__ALLOCATING_ARCHITECTURES:
				return !getAllocatingArchitectures().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //BlockArchitectureImpl