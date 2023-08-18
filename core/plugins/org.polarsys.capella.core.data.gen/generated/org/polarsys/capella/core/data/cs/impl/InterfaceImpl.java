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
import org.polarsys.capella.core.data.capellacore.impl.GeneralClassImpl;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceAllocation;
import org.polarsys.capella.core.data.cs.InterfaceAllocator;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.information.ExchangeItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getOwnedInterfaceAllocations <em>Owned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getProvisionedInterfaceAllocations <em>Provisioned Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getAllocatedInterfaces <em>Allocated Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getMechanism <em>Mechanism</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#isStructural <em>Structural</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getImplementorComponents <em>Implementor Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getUserComponents <em>User Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getInterfaceImplementations <em>Interface Implementations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getInterfaceUses <em>Interface Uses</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getProvisioningInterfaceAllocations <em>Provisioning Interface Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getAllocatingInterfaces <em>Allocating Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getAllocatingComponents <em>Allocating Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getExchangeItems <em>Exchange Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getOwnedExchangeItemAllocations <em>Owned Exchange Item Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRequiringComponents <em>Requiring Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRequiringComponentPorts <em>Requiring Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getProvidingComponents <em>Providing Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getProvidingComponentPorts <em>Providing Component Ports</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRealizingLogicalInterfaces <em>Realizing Logical Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRealizedContextInterfaces <em>Realized Context Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRealizingPhysicalInterfaces <em>Realizing Physical Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.InterfaceImpl#getRealizedLogicalInterfaces <em>Realized Logical Interfaces</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InterfaceImpl extends GeneralClassImpl implements Interface {

	/**
   * The cached value of the '{@link #getOwnedInterfaceAllocations() <em>Owned Interface Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedInterfaceAllocations()
   * @generated
   * @ordered
   */
	protected EList<InterfaceAllocation> ownedInterfaceAllocations;













	/**
   * The default value of the '{@link #getMechanism() <em>Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMechanism()
   * @generated
   * @ordered
   */
	protected static final String MECHANISM_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getMechanism() <em>Mechanism</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMechanism()
   * @generated
   * @ordered
   */
	protected String mechanism = MECHANISM_EDEFAULT;





































	/**
   * The default value of the '{@link #isStructural() <em>Structural</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isStructural()
   * @generated
   * @ordered
   */
	protected static final boolean STRUCTURAL_EDEFAULT = true;













	/**
   * The cached value of the '{@link #isStructural() <em>Structural</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isStructural()
   * @generated
   * @ordered
   */
	protected boolean structural = STRUCTURAL_EDEFAULT;













	/**
   * The cached value of the '{@link #getOwnedExchangeItemAllocations() <em>Owned Exchange Item Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedExchangeItemAllocations()
   * @generated
   * @ordered
   */
	protected EList<ExchangeItemAllocation> ownedExchangeItemAllocations;




































	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InterfaceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.INTERFACE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceAllocation> getOwnedInterfaceAllocations() {

    if (ownedInterfaceAllocations == null) {
      ownedInterfaceAllocations = new EObjectContainmentEList.Resolving<InterfaceAllocation>(InterfaceAllocation.class, this, CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS);
    }
    return ownedInterfaceAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InterfaceAllocation> getProvisionedInterfaceAllocations() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceAllocation> resultAsList = (Collection<InterfaceAllocation>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceAllocation>(this, CsPackage.Literals.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getAllocatedInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public String getMechanism() {

    return mechanism;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMechanism(String newMechanism) {

    String oldMechanism = mechanism;
    mechanism = newMechanism;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.INTERFACE__MECHANISM, oldMechanism, mechanism));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isStructural() {

    return structural;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setStructural(boolean newStructural) {

    boolean oldStructural = structural;
    structural = newStructural;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.INTERFACE__STRUCTURAL, oldStructural, structural));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Component> getImplementorComponents() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__IMPLEMENTOR_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__IMPLEMENTOR_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.INTERFACE__IMPLEMENTOR_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Component> getUserComponents() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__USER_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__USER_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.INTERFACE__USER_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<InterfaceImplementation> getInterfaceImplementations() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__INTERFACE_IMPLEMENTATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__INTERFACE_IMPLEMENTATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceImplementation> resultAsList = (Collection<InterfaceImplementation>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceImplementation>(this, CsPackage.Literals.INTERFACE__INTERFACE_IMPLEMENTATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<InterfaceUse> getInterfaceUses() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__INTERFACE_USES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__INTERFACE_USES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceUse> resultAsList = (Collection<InterfaceUse>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceUse>(this, CsPackage.Literals.INTERFACE__INTERFACE_USES, resultAsList.size(), resultAsList.toArray());
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

	public EList<InterfaceAllocation> getProvisioningInterfaceAllocations() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<InterfaceAllocation> resultAsList = (Collection<InterfaceAllocation>) result;
    return new EcoreEList.UnmodifiableEList<InterfaceAllocation>(this, CsPackage.Literals.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getAllocatingInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__ALLOCATING_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__ALLOCATING_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE__ALLOCATING_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Component> getAllocatingComponents() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__ALLOCATING_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__ALLOCATING_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.INTERFACE__ALLOCATING_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeItem> getExchangeItems() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__EXCHANGE_ITEMS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__EXCHANGE_ITEMS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ExchangeItem> resultAsList = (Collection<ExchangeItem>) result;
    return new EcoreEList.UnmodifiableEList<ExchangeItem>(this, CsPackage.Literals.INTERFACE__EXCHANGE_ITEMS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ExchangeItemAllocation> getOwnedExchangeItemAllocations() {

    if (ownedExchangeItemAllocations == null) {
      ownedExchangeItemAllocations = new EObjectContainmentEList.Resolving<ExchangeItemAllocation>(ExchangeItemAllocation.class, this, CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS);
    }
    return ownedExchangeItemAllocations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Component> getRequiringComponents() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REQUIRING_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REQUIRING_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.INTERFACE__REQUIRING_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ComponentPort> getRequiringComponentPorts() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REQUIRING_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REQUIRING_COMPONENT_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
    return new EcoreEList.UnmodifiableEList<ComponentPort>(this, CsPackage.Literals.INTERFACE__REQUIRING_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Component> getProvidingComponents() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__PROVIDING_COMPONENTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__PROVIDING_COMPONENTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Component> resultAsList = (Collection<Component>) result;
    return new EcoreEList.UnmodifiableEList<Component>(this, CsPackage.Literals.INTERFACE__PROVIDING_COMPONENTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<ComponentPort> getProvidingComponentPorts() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__PROVIDING_COMPONENT_PORTS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__PROVIDING_COMPONENT_PORTS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<ComponentPort> resultAsList = (Collection<ComponentPort>) result;
    return new EcoreEList.UnmodifiableEList<ComponentPort>(this, CsPackage.Literals.INTERFACE__PROVIDING_COMPONENT_PORTS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getRealizingLogicalInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REALIZING_LOGICAL_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REALIZING_LOGICAL_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE__REALIZING_LOGICAL_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getRealizedContextInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REALIZED_CONTEXT_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REALIZED_CONTEXT_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE__REALIZED_CONTEXT_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getRealizingPhysicalInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REALIZING_PHYSICAL_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REALIZING_PHYSICAL_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE__REALIZING_PHYSICAL_INTERFACES, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getRealizedLogicalInterfaces() {


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
    EAnnotation annotation = CsPackage.Literals.INTERFACE__REALIZED_LOGICAL_INTERFACES.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CsPackage.Literals.INTERFACE__REALIZED_LOGICAL_INTERFACES, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<Interface> resultAsList = (Collection<Interface>) result;
    return new EcoreEList.UnmodifiableEList<Interface>(this, CsPackage.Literals.INTERFACE__REALIZED_LOGICAL_INTERFACES, resultAsList.size(), resultAsList.toArray());
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
      case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedInterfaceAllocations()).basicRemove(otherEnd, msgs);
      case CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedExchangeItemAllocations()).basicRemove(otherEnd, msgs);
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
      case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS:
        return getOwnedInterfaceAllocations();
      case CsPackage.INTERFACE__PROVISIONED_INTERFACE_ALLOCATIONS:
        return getProvisionedInterfaceAllocations();
      case CsPackage.INTERFACE__ALLOCATED_INTERFACES:
        return getAllocatedInterfaces();
      case CsPackage.INTERFACE__MECHANISM:
        return getMechanism();
      case CsPackage.INTERFACE__STRUCTURAL:
        return isStructural();
      case CsPackage.INTERFACE__IMPLEMENTOR_COMPONENTS:
        return getImplementorComponents();
      case CsPackage.INTERFACE__USER_COMPONENTS:
        return getUserComponents();
      case CsPackage.INTERFACE__INTERFACE_IMPLEMENTATIONS:
        return getInterfaceImplementations();
      case CsPackage.INTERFACE__INTERFACE_USES:
        return getInterfaceUses();
      case CsPackage.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS:
        return getProvisioningInterfaceAllocations();
      case CsPackage.INTERFACE__ALLOCATING_INTERFACES:
        return getAllocatingInterfaces();
      case CsPackage.INTERFACE__ALLOCATING_COMPONENTS:
        return getAllocatingComponents();
      case CsPackage.INTERFACE__EXCHANGE_ITEMS:
        return getExchangeItems();
      case CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS:
        return getOwnedExchangeItemAllocations();
      case CsPackage.INTERFACE__REQUIRING_COMPONENTS:
        return getRequiringComponents();
      case CsPackage.INTERFACE__REQUIRING_COMPONENT_PORTS:
        return getRequiringComponentPorts();
      case CsPackage.INTERFACE__PROVIDING_COMPONENTS:
        return getProvidingComponents();
      case CsPackage.INTERFACE__PROVIDING_COMPONENT_PORTS:
        return getProvidingComponentPorts();
      case CsPackage.INTERFACE__REALIZING_LOGICAL_INTERFACES:
        return getRealizingLogicalInterfaces();
      case CsPackage.INTERFACE__REALIZED_CONTEXT_INTERFACES:
        return getRealizedContextInterfaces();
      case CsPackage.INTERFACE__REALIZING_PHYSICAL_INTERFACES:
        return getRealizingPhysicalInterfaces();
      case CsPackage.INTERFACE__REALIZED_LOGICAL_INTERFACES:
        return getRealizedLogicalInterfaces();
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
      case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS:
        getOwnedInterfaceAllocations().clear();
        getOwnedInterfaceAllocations().addAll((Collection<? extends InterfaceAllocation>)newValue);
        return;
      case CsPackage.INTERFACE__MECHANISM:
          setMechanism((String)newValue);
        return;
      case CsPackage.INTERFACE__STRUCTURAL:
          setStructural((Boolean)newValue);
        return;
      case CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS:
        getOwnedExchangeItemAllocations().clear();
        getOwnedExchangeItemAllocations().addAll((Collection<? extends ExchangeItemAllocation>)newValue);
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
      case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS:
        getOwnedInterfaceAllocations().clear();
        return;
      case CsPackage.INTERFACE__MECHANISM:
        setMechanism(MECHANISM_EDEFAULT);
        return;
      case CsPackage.INTERFACE__STRUCTURAL:
        setStructural(STRUCTURAL_EDEFAULT);
        return;
      case CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS:
        getOwnedExchangeItemAllocations().clear();
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
      case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS:
        return ownedInterfaceAllocations != null && !ownedInterfaceAllocations.isEmpty();
      case CsPackage.INTERFACE__PROVISIONED_INTERFACE_ALLOCATIONS:
        return !getProvisionedInterfaceAllocations().isEmpty();
      case CsPackage.INTERFACE__ALLOCATED_INTERFACES:
        return !getAllocatedInterfaces().isEmpty();
      case CsPackage.INTERFACE__MECHANISM:
        return MECHANISM_EDEFAULT == null ? mechanism != null : !MECHANISM_EDEFAULT.equals(mechanism);
      case CsPackage.INTERFACE__STRUCTURAL:
        return structural != STRUCTURAL_EDEFAULT;
      case CsPackage.INTERFACE__IMPLEMENTOR_COMPONENTS:
        return !getImplementorComponents().isEmpty();
      case CsPackage.INTERFACE__USER_COMPONENTS:
        return !getUserComponents().isEmpty();
      case CsPackage.INTERFACE__INTERFACE_IMPLEMENTATIONS:
        return !getInterfaceImplementations().isEmpty();
      case CsPackage.INTERFACE__INTERFACE_USES:
        return !getInterfaceUses().isEmpty();
      case CsPackage.INTERFACE__PROVISIONING_INTERFACE_ALLOCATIONS:
        return !getProvisioningInterfaceAllocations().isEmpty();
      case CsPackage.INTERFACE__ALLOCATING_INTERFACES:
        return !getAllocatingInterfaces().isEmpty();
      case CsPackage.INTERFACE__ALLOCATING_COMPONENTS:
        return !getAllocatingComponents().isEmpty();
      case CsPackage.INTERFACE__EXCHANGE_ITEMS:
        return !getExchangeItems().isEmpty();
      case CsPackage.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS:
        return ownedExchangeItemAllocations != null && !ownedExchangeItemAllocations.isEmpty();
      case CsPackage.INTERFACE__REQUIRING_COMPONENTS:
        return !getRequiringComponents().isEmpty();
      case CsPackage.INTERFACE__REQUIRING_COMPONENT_PORTS:
        return !getRequiringComponentPorts().isEmpty();
      case CsPackage.INTERFACE__PROVIDING_COMPONENTS:
        return !getProvidingComponents().isEmpty();
      case CsPackage.INTERFACE__PROVIDING_COMPONENT_PORTS:
        return !getProvidingComponentPorts().isEmpty();
      case CsPackage.INTERFACE__REALIZING_LOGICAL_INTERFACES:
        return !getRealizingLogicalInterfaces().isEmpty();
      case CsPackage.INTERFACE__REALIZED_CONTEXT_INTERFACES:
        return !getRealizedContextInterfaces().isEmpty();
      case CsPackage.INTERFACE__REALIZING_PHYSICAL_INTERFACES:
        return !getRealizingPhysicalInterfaces().isEmpty();
      case CsPackage.INTERFACE__REALIZED_LOGICAL_INTERFACES:
        return !getRealizedLogicalInterfaces().isEmpty();
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
    if (baseClass == InterfaceAllocator.class) {
      switch (derivedFeatureID) {
        case CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE__PROVISIONED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE__ALLOCATED_INTERFACES: return CsPackage.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES;
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
    if (baseClass == InterfaceAllocator.class) {
      switch (baseFeatureID) {
        case CsPackage.INTERFACE_ALLOCATOR__OWNED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE__OWNED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE_ALLOCATOR__PROVISIONED_INTERFACE_ALLOCATIONS: return CsPackage.INTERFACE__PROVISIONED_INTERFACE_ALLOCATIONS;
        case CsPackage.INTERFACE_ALLOCATOR__ALLOCATED_INTERFACES: return CsPackage.INTERFACE__ALLOCATED_INTERFACES;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (mechanism: "); //$NON-NLS-1$
    result.append(mechanism);
    result.append(", structural: "); //$NON-NLS-1$
    result.append(structural);
    result.append(')');
    return result.toString();
  }


} //InterfaceImpl
