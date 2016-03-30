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

package org.polarsys.capella.core.data.epbs.impl;

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
import org.polarsys.capella.core.data.cs.impl.ComponentArchitectureImpl;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.ConfigurationItemPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EPBS Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getOwnedEPBSContext <em>Owned EPBS Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getOwnedConfigurationItem <em>Owned Configuration Item</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getOwnedConfigurationItemPkg <em>Owned Configuration Item Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getContainedCapabilityRealizationPkg <em>Contained Capability Realization Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getOwnedPhysicalArchitectureRealizations <em>Owned Physical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getAllocatedPhysicalArchitectureRealizations <em>Allocated Physical Architecture Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.epbs.impl.EPBSArchitectureImpl#getAllocatedPhysicalArchitectures <em>Allocated Physical Architectures</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EPBSArchitectureImpl extends ComponentArchitectureImpl implements EPBSArchitecture {

	/**
	 * The cached value of the '{@link #getOwnedEPBSContext() <em>Owned EPBS Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedEPBSContext()
	 * @generated
	 * @ordered
	 */
	protected EPBSContext ownedEPBSContext;





	/**
	 * The cached value of the '{@link #getOwnedConfigurationItem() <em>Owned Configuration Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConfigurationItem()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationItem ownedConfigurationItem;





	/**
	 * The cached value of the '{@link #getOwnedConfigurationItemPkg() <em>Owned Configuration Item Pkg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConfigurationItemPkg()
	 * @generated
	 * @ordered
	 */
	protected ConfigurationItemPkg ownedConfigurationItemPkg;









	/**
	 * The cached value of the '{@link #getOwnedPhysicalArchitectureRealizations() <em>Owned Physical Architecture Realizations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPhysicalArchitectureRealizations()
	 * @generated
	 * @ordered
	 */
	protected EList<PhysicalArchitectureRealization> ownedPhysicalArchitectureRealizations;












	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EPBSArchitectureImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EpbsPackage.Literals.EPBS_ARCHITECTURE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EPBSContext getOwnedEPBSContext() {

		if (ownedEPBSContext != null && ownedEPBSContext.eIsProxy()) {
			InternalEObject oldOwnedEPBSContext = (InternalEObject)ownedEPBSContext;
			ownedEPBSContext = (EPBSContext)eResolveProxy(oldOwnedEPBSContext);
			if (ownedEPBSContext != oldOwnedEPBSContext) {
				InternalEObject newOwnedEPBSContext = (InternalEObject)ownedEPBSContext;
				NotificationChain msgs = oldOwnedEPBSContext.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, null, null);
				if (newOwnedEPBSContext.eInternalContainer() == null) {
					msgs = newOwnedEPBSContext.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, oldOwnedEPBSContext, ownedEPBSContext));
			}
		}
		return ownedEPBSContext;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EPBSContext basicGetOwnedEPBSContext() {

		return ownedEPBSContext;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedEPBSContext(EPBSContext newOwnedEPBSContext, NotificationChain msgs) {

		EPBSContext oldOwnedEPBSContext = ownedEPBSContext;
		ownedEPBSContext = newOwnedEPBSContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, oldOwnedEPBSContext, newOwnedEPBSContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedEPBSContext(EPBSContext newOwnedEPBSContext) {

		if (newOwnedEPBSContext != ownedEPBSContext) {
			NotificationChain msgs = null;
			if (ownedEPBSContext != null)
				msgs = ((InternalEObject)ownedEPBSContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, null, msgs);
			if (newOwnedEPBSContext != null)
				msgs = ((InternalEObject)newOwnedEPBSContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, null, msgs);
			msgs = basicSetOwnedEPBSContext(newOwnedEPBSContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT, newOwnedEPBSContext, newOwnedEPBSContext));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ConfigurationItem getOwnedConfigurationItem() {

		if (ownedConfigurationItem != null && ownedConfigurationItem.eIsProxy()) {
			InternalEObject oldOwnedConfigurationItem = (InternalEObject)ownedConfigurationItem;
			ownedConfigurationItem = (ConfigurationItem)eResolveProxy(oldOwnedConfigurationItem);
			if (ownedConfigurationItem != oldOwnedConfigurationItem) {
				InternalEObject newOwnedConfigurationItem = (InternalEObject)ownedConfigurationItem;
				NotificationChain msgs = oldOwnedConfigurationItem.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, null, null);
				if (newOwnedConfigurationItem.eInternalContainer() == null) {
					msgs = newOwnedConfigurationItem.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, oldOwnedConfigurationItem, ownedConfigurationItem));
			}
		}
		return ownedConfigurationItem;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ConfigurationItem basicGetOwnedConfigurationItem() {

		return ownedConfigurationItem;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedConfigurationItem(ConfigurationItem newOwnedConfigurationItem, NotificationChain msgs) {

		ConfigurationItem oldOwnedConfigurationItem = ownedConfigurationItem;
		ownedConfigurationItem = newOwnedConfigurationItem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, oldOwnedConfigurationItem, newOwnedConfigurationItem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedConfigurationItem(ConfigurationItem newOwnedConfigurationItem) {

		if (newOwnedConfigurationItem != ownedConfigurationItem) {
			NotificationChain msgs = null;
			if (ownedConfigurationItem != null)
				msgs = ((InternalEObject)ownedConfigurationItem).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, null, msgs);
			if (newOwnedConfigurationItem != null)
				msgs = ((InternalEObject)newOwnedConfigurationItem).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, null, msgs);
			msgs = basicSetOwnedConfigurationItem(newOwnedConfigurationItem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM, newOwnedConfigurationItem, newOwnedConfigurationItem));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ConfigurationItemPkg getOwnedConfigurationItemPkg() {

		if (ownedConfigurationItemPkg != null && ownedConfigurationItemPkg.eIsProxy()) {
			InternalEObject oldOwnedConfigurationItemPkg = (InternalEObject)ownedConfigurationItemPkg;
			ownedConfigurationItemPkg = (ConfigurationItemPkg)eResolveProxy(oldOwnedConfigurationItemPkg);
			if (ownedConfigurationItemPkg != oldOwnedConfigurationItemPkg) {
				InternalEObject newOwnedConfigurationItemPkg = (InternalEObject)ownedConfigurationItemPkg;
				NotificationChain msgs = oldOwnedConfigurationItemPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, null, null);
				if (newOwnedConfigurationItemPkg.eInternalContainer() == null) {
					msgs = newOwnedConfigurationItemPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, oldOwnedConfigurationItemPkg, ownedConfigurationItemPkg));
			}
		}
		return ownedConfigurationItemPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public ConfigurationItemPkg basicGetOwnedConfigurationItemPkg() {

		return ownedConfigurationItemPkg;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public NotificationChain basicSetOwnedConfigurationItemPkg(ConfigurationItemPkg newOwnedConfigurationItemPkg, NotificationChain msgs) {

		ConfigurationItemPkg oldOwnedConfigurationItemPkg = ownedConfigurationItemPkg;
		ownedConfigurationItemPkg = newOwnedConfigurationItemPkg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, oldOwnedConfigurationItemPkg, newOwnedConfigurationItemPkg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}

		return msgs;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setOwnedConfigurationItemPkg(ConfigurationItemPkg newOwnedConfigurationItemPkg) {

		if (newOwnedConfigurationItemPkg != ownedConfigurationItemPkg) {
			NotificationChain msgs = null;
			if (ownedConfigurationItemPkg != null)
				msgs = ((InternalEObject)ownedConfigurationItemPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, null, msgs);
			if (newOwnedConfigurationItemPkg != null)
				msgs = ((InternalEObject)newOwnedConfigurationItemPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, null, msgs);
			msgs = basicSetOwnedConfigurationItemPkg(newOwnedConfigurationItemPkg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG, newOwnedConfigurationItemPkg, newOwnedConfigurationItemPkg));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityRealizationPkg getContainedCapabilityRealizationPkg() {

		CapabilityRealizationPkg containedCapabilityRealizationPkg = basicGetContainedCapabilityRealizationPkg();
		return containedCapabilityRealizationPkg != null && containedCapabilityRealizationPkg.eIsProxy() ? (CapabilityRealizationPkg)eResolveProxy((InternalEObject)containedCapabilityRealizationPkg) : containedCapabilityRealizationPkg;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public CapabilityRealizationPkg basicGetContainedCapabilityRealizationPkg() {


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
    EAnnotation annotation = EpbsPackage.Literals.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, EpbsPackage.Literals.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG, annotation);
		
		try {
			return (CapabilityRealizationPkg) result;
	  } catch (ClassCastException exception) {
	     exception.printStackTrace();
	    return null;
	  }
		
	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PhysicalArchitectureRealization> getOwnedPhysicalArchitectureRealizations() {

		if (ownedPhysicalArchitectureRealizations == null) {
			ownedPhysicalArchitectureRealizations = new EObjectContainmentEList.Resolving<PhysicalArchitectureRealization>(PhysicalArchitectureRealization.class, this, EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS);
		}
		return ownedPhysicalArchitectureRealizations;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EList<PhysicalArchitectureRealization> getAllocatedPhysicalArchitectureRealizations() {


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
    EAnnotation annotation = EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalArchitectureRealization> resultAsList = (Collection<PhysicalArchitectureRealization>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalArchitectureRealization>(this, EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PhysicalArchitecture> getAllocatedPhysicalArchitectures() {


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
    EAnnotation annotation = EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES, annotation);
		
		try {
		@SuppressWarnings("unchecked")
		Collection<PhysicalArchitecture> resultAsList = (Collection<PhysicalArchitecture>) result;
		return new EcoreEList.UnmodifiableEList<PhysicalArchitecture>(this, EpbsPackage.Literals.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES, resultAsList.size(), resultAsList.toArray());
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
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT:
				return basicSetOwnedEPBSContext(null, msgs);
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM:
				return basicSetOwnedConfigurationItem(null, msgs);
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG:
				return basicSetOwnedConfigurationItemPkg(null, msgs);
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				return ((InternalEList<?>)getOwnedPhysicalArchitectureRealizations()).basicRemove(otherEnd, msgs);
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
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT:
				if (resolve) return getOwnedEPBSContext();
				return basicGetOwnedEPBSContext();
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM:
				if (resolve) return getOwnedConfigurationItem();
				return basicGetOwnedConfigurationItem();
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG:
				if (resolve) return getOwnedConfigurationItemPkg();
				return basicGetOwnedConfigurationItemPkg();
			case EpbsPackage.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				if (resolve) return getContainedCapabilityRealizationPkg();
				return basicGetContainedCapabilityRealizationPkg();
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				return getOwnedPhysicalArchitectureRealizations();
			case EpbsPackage.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				return getAllocatedPhysicalArchitectureRealizations();
			case EpbsPackage.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES:
				return getAllocatedPhysicalArchitectures();
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
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT:
				// begin-extension-code
				if (newValue == null || newValue instanceof EPBSContext) {
				// end-extension-code
					setOwnedEPBSContext((EPBSContext)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM:
				// begin-extension-code
				if (newValue == null || newValue instanceof ConfigurationItem) {
				// end-extension-code
					setOwnedConfigurationItem((ConfigurationItem)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG:
				// begin-extension-code
				if (newValue == null || newValue instanceof ConfigurationItemPkg) {
				// end-extension-code
					setOwnedConfigurationItemPkg((ConfigurationItemPkg)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				getOwnedPhysicalArchitectureRealizations().clear();
				getOwnedPhysicalArchitectureRealizations().addAll((Collection<? extends PhysicalArchitectureRealization>)newValue);
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
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT:
				setOwnedEPBSContext((EPBSContext)null);
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM:
				setOwnedConfigurationItem((ConfigurationItem)null);
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG:
				setOwnedConfigurationItemPkg((ConfigurationItemPkg)null);
				return;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				getOwnedPhysicalArchitectureRealizations().clear();
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
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_EPBS_CONTEXT:
				return ownedEPBSContext != null;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM:
				return ownedConfigurationItem != null;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_CONFIGURATION_ITEM_PKG:
				return ownedConfigurationItemPkg != null;
			case EpbsPackage.EPBS_ARCHITECTURE__CONTAINED_CAPABILITY_REALIZATION_PKG:
				return basicGetContainedCapabilityRealizationPkg() != null;
			case EpbsPackage.EPBS_ARCHITECTURE__OWNED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				return ownedPhysicalArchitectureRealizations != null && !ownedPhysicalArchitectureRealizations.isEmpty();
			case EpbsPackage.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURE_REALIZATIONS:
				return !getAllocatedPhysicalArchitectureRealizations().isEmpty();
			case EpbsPackage.EPBS_ARCHITECTURE__ALLOCATED_PHYSICAL_ARCHITECTURES:
				return !getAllocatedPhysicalArchitectures().isEmpty();
		}
		return super.eIsSet(featureID);
	}



} //EPBSArchitectureImpl