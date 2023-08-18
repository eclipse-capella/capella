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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.impl.TypeImpl;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.DataPkg;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedFunctionalAllocation <em>Owned Functional Allocation</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedComponentExchanges <em>Owned Component Exchanges</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedComponentExchangeCategories <em>Owned Component Exchange Categories</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getFunctionalAllocations <em>Functional Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getAllocatedFunctions <em>Allocated Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getInExchangeLinks <em>In Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOutExchangeLinks <em>Out Exchange Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedAbstractCapabilityPkg <em>Owned Abstract Capability Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedInterfacePkg <em>Owned Interface Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedDataPkg <em>Owned Data Pkg</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.BlockImpl#getOwnedStateMachines <em>Owned State Machines</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BlockImpl extends TypeImpl implements Block {

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
   * The cached value of the '{@link #getOwnedStateMachines() <em>Owned State Machines</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedStateMachines()
   * @generated
   * @ordered
   */
	protected EList<StateMachine> ownedStateMachines;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BlockImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.BLOCK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ComponentFunctionalAllocation> getOwnedFunctionalAllocation() {

    if (ownedFunctionalAllocation == null) {
      ownedFunctionalAllocation = new EObjectContainmentEList.Resolving<ComponentFunctionalAllocation>(ComponentFunctionalAllocation.class, this, CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
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
      ownedComponentExchanges = new EObjectContainmentEList<ComponentExchange>(ComponentExchange.class, this, CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES);
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
      ownedComponentExchangeCategories = new EObjectContainmentEList<ComponentExchangeCategory>(ComponentExchangeCategory.class, this, CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
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
      inExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, CsPackage.BLOCK__IN_EXCHANGE_LINKS);
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
      outExchangeLinks = new EObjectResolvingEList<ExchangeLink>(ExchangeLink.class, this, CsPackage.BLOCK__OUT_EXCHANGE_LINKS);
    }
    return outExchangeLinks;
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
        NotificationChain msgs = oldOwnedAbstractCapabilityPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, null, null);
        if (newOwnedAbstractCapabilityPkg.eInternalContainer() == null) {
          msgs = newOwnedAbstractCapabilityPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, ownedAbstractCapabilityPkg));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, oldOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedAbstractCapabilityPkg(AbstractCapabilityPkg newOwnedAbstractCapabilityPkg) {

    if (newOwnedAbstractCapabilityPkg != ownedAbstractCapabilityPkg) {
      NotificationChain msgs = null;
      if (ownedAbstractCapabilityPkg != null)
        msgs = ((InternalEObject)ownedAbstractCapabilityPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
      if (newOwnedAbstractCapabilityPkg != null)
        msgs = ((InternalEObject)newOwnedAbstractCapabilityPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, null, msgs);
      msgs = basicSetOwnedAbstractCapabilityPkg(newOwnedAbstractCapabilityPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG, newOwnedAbstractCapabilityPkg, newOwnedAbstractCapabilityPkg));

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
        NotificationChain msgs = oldOwnedInterfacePkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_INTERFACE_PKG, null, null);
        if (newOwnedInterfacePkg.eInternalContainer() == null) {
          msgs = newOwnedInterfacePkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_INTERFACE_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, ownedInterfacePkg));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_INTERFACE_PKG, oldOwnedInterfacePkg, newOwnedInterfacePkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedInterfacePkg(InterfacePkg newOwnedInterfacePkg) {

    if (newOwnedInterfacePkg != ownedInterfacePkg) {
      NotificationChain msgs = null;
      if (ownedInterfacePkg != null)
        msgs = ((InternalEObject)ownedInterfacePkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_INTERFACE_PKG, null, msgs);
      if (newOwnedInterfacePkg != null)
        msgs = ((InternalEObject)newOwnedInterfacePkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_INTERFACE_PKG, null, msgs);
      msgs = basicSetOwnedInterfacePkg(newOwnedInterfacePkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_INTERFACE_PKG, newOwnedInterfacePkg, newOwnedInterfacePkg));

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
        NotificationChain msgs = oldOwnedDataPkg.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_DATA_PKG, null, null);
        if (newOwnedDataPkg.eInternalContainer() == null) {
          msgs = newOwnedDataPkg.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_DATA_PKG, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CsPackage.BLOCK__OWNED_DATA_PKG, oldOwnedDataPkg, ownedDataPkg));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_DATA_PKG, oldOwnedDataPkg, newOwnedDataPkg);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOwnedDataPkg(DataPkg newOwnedDataPkg) {

    if (newOwnedDataPkg != ownedDataPkg) {
      NotificationChain msgs = null;
      if (ownedDataPkg != null)
        msgs = ((InternalEObject)ownedDataPkg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_DATA_PKG, null, msgs);
      if (newOwnedDataPkg != null)
        msgs = ((InternalEObject)newOwnedDataPkg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CsPackage.BLOCK__OWNED_DATA_PKG, null, msgs);
      msgs = basicSetOwnedDataPkg(newOwnedDataPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CsPackage.BLOCK__OWNED_DATA_PKG, newOwnedDataPkg, newOwnedDataPkg));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<StateMachine> getOwnedStateMachines() {

    if (ownedStateMachines == null) {
      ownedStateMachines = new EObjectContainmentEList.Resolving<StateMachine>(StateMachine.class, this, CsPackage.BLOCK__OWNED_STATE_MACHINES);
    }
    return ownedStateMachines;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return ((InternalEList<?>)getOwnedFunctionalAllocation()).basicRemove(otherEnd, msgs);
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES:
        return ((InternalEList<?>)getOwnedComponentExchanges()).basicRemove(otherEnd, msgs);
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ((InternalEList<?>)getOwnedComponentExchangeCategories()).basicRemove(otherEnd, msgs);
      case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG:
        return basicSetOwnedAbstractCapabilityPkg(null, msgs);
      case CsPackage.BLOCK__OWNED_INTERFACE_PKG:
        return basicSetOwnedInterfacePkg(null, msgs);
      case CsPackage.BLOCK__OWNED_DATA_PKG:
        return basicSetOwnedDataPkg(null, msgs);
      case CsPackage.BLOCK__OWNED_STATE_MACHINES:
        return ((InternalEList<?>)getOwnedStateMachines()).basicRemove(otherEnd, msgs);
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
      case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return getOwnedFunctionalAllocation();
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES:
        return getOwnedComponentExchanges();
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return getOwnedComponentExchangeCategories();
      case CsPackage.BLOCK__FUNCTIONAL_ALLOCATIONS:
        return getFunctionalAllocations();
      case CsPackage.BLOCK__ALLOCATED_FUNCTIONS:
        return getAllocatedFunctions();
      case CsPackage.BLOCK__IN_EXCHANGE_LINKS:
        return getInExchangeLinks();
      case CsPackage.BLOCK__OUT_EXCHANGE_LINKS:
        return getOutExchangeLinks();
      case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG:
        if (resolve) return getOwnedAbstractCapabilityPkg();
        return basicGetOwnedAbstractCapabilityPkg();
      case CsPackage.BLOCK__OWNED_INTERFACE_PKG:
        if (resolve) return getOwnedInterfacePkg();
        return basicGetOwnedInterfacePkg();
      case CsPackage.BLOCK__OWNED_DATA_PKG:
        if (resolve) return getOwnedDataPkg();
        return basicGetOwnedDataPkg();
      case CsPackage.BLOCK__OWNED_STATE_MACHINES:
        return getOwnedStateMachines();
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
      case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        getOwnedFunctionalAllocation().addAll((Collection<? extends ComponentFunctionalAllocation>)newValue);
        return;
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        getOwnedComponentExchanges().addAll((Collection<? extends ComponentExchange>)newValue);
        return;
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        getOwnedComponentExchangeCategories().addAll((Collection<? extends ComponentExchangeCategory>)newValue);
        return;
      case CsPackage.BLOCK__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        getInExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case CsPackage.BLOCK__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        getOutExchangeLinks().addAll((Collection<? extends ExchangeLink>)newValue);
        return;
      case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG:
          setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)newValue);
        return;
      case CsPackage.BLOCK__OWNED_INTERFACE_PKG:
          setOwnedInterfacePkg((InterfacePkg)newValue);
        return;
      case CsPackage.BLOCK__OWNED_DATA_PKG:
          setOwnedDataPkg((DataPkg)newValue);
        return;
      case CsPackage.BLOCK__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
        getOwnedStateMachines().addAll((Collection<? extends StateMachine>)newValue);
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
      case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        getOwnedFunctionalAllocation().clear();
        return;
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES:
        getOwnedComponentExchanges().clear();
        return;
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        getOwnedComponentExchangeCategories().clear();
        return;
      case CsPackage.BLOCK__IN_EXCHANGE_LINKS:
        getInExchangeLinks().clear();
        return;
      case CsPackage.BLOCK__OUT_EXCHANGE_LINKS:
        getOutExchangeLinks().clear();
        return;
      case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG:
        setOwnedAbstractCapabilityPkg((AbstractCapabilityPkg)null);
        return;
      case CsPackage.BLOCK__OWNED_INTERFACE_PKG:
        setOwnedInterfacePkg((InterfacePkg)null);
        return;
      case CsPackage.BLOCK__OWNED_DATA_PKG:
        setOwnedDataPkg((DataPkg)null);
        return;
      case CsPackage.BLOCK__OWNED_STATE_MACHINES:
        getOwnedStateMachines().clear();
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
      case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION:
        return ownedFunctionalAllocation != null && !ownedFunctionalAllocation.isEmpty();
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES:
        return ownedComponentExchanges != null && !ownedComponentExchanges.isEmpty();
      case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES:
        return ownedComponentExchangeCategories != null && !ownedComponentExchangeCategories.isEmpty();
      case CsPackage.BLOCK__FUNCTIONAL_ALLOCATIONS:
        return !getFunctionalAllocations().isEmpty();
      case CsPackage.BLOCK__ALLOCATED_FUNCTIONS:
        return !getAllocatedFunctions().isEmpty();
      case CsPackage.BLOCK__IN_EXCHANGE_LINKS:
        return inExchangeLinks != null && !inExchangeLinks.isEmpty();
      case CsPackage.BLOCK__OUT_EXCHANGE_LINKS:
        return outExchangeLinks != null && !outExchangeLinks.isEmpty();
      case CsPackage.BLOCK__OWNED_ABSTRACT_CAPABILITY_PKG:
        return ownedAbstractCapabilityPkg != null;
      case CsPackage.BLOCK__OWNED_INTERFACE_PKG:
        return ownedInterfacePkg != null;
      case CsPackage.BLOCK__OWNED_DATA_PKG:
        return ownedDataPkg != null;
      case CsPackage.BLOCK__OWNED_STATE_MACHINES:
        return ownedStateMachines != null && !ownedStateMachines.isEmpty();
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
    if (baseClass == AbstractFunctionalBlock.class) {
      switch (derivedFeatureID) {
        case CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION;
        case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES;
        case CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
        case CsPackage.BLOCK__FUNCTIONAL_ALLOCATIONS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS;
        case CsPackage.BLOCK__ALLOCATED_FUNCTIONS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS;
        case CsPackage.BLOCK__IN_EXCHANGE_LINKS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS;
        case CsPackage.BLOCK__OUT_EXCHANGE_LINKS: return FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS;
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
    if (baseClass == AbstractFunctionalBlock.class) {
      switch (baseFeatureID) {
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION: return CsPackage.BLOCK__OWNED_FUNCTIONAL_ALLOCATION;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES: return CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGES;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES: return CsPackage.BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS: return CsPackage.BLOCK__FUNCTIONAL_ALLOCATIONS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS: return CsPackage.BLOCK__ALLOCATED_FUNCTIONS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS: return CsPackage.BLOCK__IN_EXCHANGE_LINKS;
        case FaPackage.ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS: return CsPackage.BLOCK__OUT_EXCHANGE_LINKS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //BlockImpl