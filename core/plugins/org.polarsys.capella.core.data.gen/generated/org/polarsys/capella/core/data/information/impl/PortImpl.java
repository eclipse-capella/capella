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

package org.polarsys.capella.core.data.information.impl;

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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getIncomingPortRealizations <em>Incoming Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getOutgoingPortRealizations <em>Outgoing Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getOwnedProtocols <em>Owned Protocols</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getIncomingPortAllocations <em>Incoming Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getOutgoingPortAllocations <em>Outgoing Port Allocations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getOwnedPortRealizations <em>Owned Port Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.PortImpl#getOwnedPortAllocations <em>Owned Port Allocations</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PortImpl extends NamedElementImpl implements Port {









	/**
   * The cached value of the '{@link #getOwnedProtocols() <em>Owned Protocols</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedProtocols()
   * @generated
   * @ordered
   */
	protected EList<StateMachine> ownedProtocols;













	/**
   * The cached value of the '{@link #getProvidedInterfaces() <em>Provided Interfaces</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getProvidedInterfaces()
   * @generated
   * @ordered
   */
	protected EList<Interface> providedInterfaces;





	/**
   * The cached value of the '{@link #getRequiredInterfaces() <em>Required Interfaces</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRequiredInterfaces()
   * @generated
   * @ordered
   */
	protected EList<Interface> requiredInterfaces;





	/**
   * The cached value of the '{@link #getOwnedPortRealizations() <em>Owned Port Realizations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPortRealizations()
   * @generated
   * @ordered
   */
	protected EList<PortRealization> ownedPortRealizations;





	/**
   * The cached value of the '{@link #getOwnedPortAllocations() <em>Owned Port Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPortAllocations()
   * @generated
   * @ordered
   */
	protected EList<PortAllocation> ownedPortAllocations;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PortImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.PORT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PortRealization> getIncomingPortRealizations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PortRealization> resultAsList = (Collection<PortRealization>) result;
    return new EcoreEList.UnmodifiableEList<PortRealization>(this, InformationPackage.Literals.PORT__INCOMING_PORT_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PortRealization> getOutgoingPortRealizations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PortRealization> resultAsList = (Collection<PortRealization>) result;
    return new EcoreEList.UnmodifiableEList<PortRealization>(this, InformationPackage.Literals.PORT__OUTGOING_PORT_REALIZATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<StateMachine> getOwnedProtocols() {

    if (ownedProtocols == null) {
      ownedProtocols = new EObjectContainmentEList.Resolving<StateMachine>(StateMachine.class, this, InformationPackage.PORT__OWNED_PROTOCOLS);
    }
    return ownedProtocols;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PortAllocation> getIncomingPortAllocations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PortAllocation> resultAsList = (Collection<PortAllocation>) result;
    return new EcoreEList.UnmodifiableEList<PortAllocation>(this, InformationPackage.Literals.PORT__INCOMING_PORT_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<PortAllocation> getOutgoingPortAllocations() {


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
    EAnnotation annotation = InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<PortAllocation> resultAsList = (Collection<PortAllocation>) result;
    return new EcoreEList.UnmodifiableEList<PortAllocation>(this, InformationPackage.Literals.PORT__OUTGOING_PORT_ALLOCATIONS, resultAsList.size(), resultAsList.toArray());
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

	public EList<Interface> getProvidedInterfaces() {

    if (providedInterfaces == null) {
      providedInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, InformationPackage.PORT__PROVIDED_INTERFACES);
    }
    return providedInterfaces;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Interface> getRequiredInterfaces() {

    if (requiredInterfaces == null) {
      requiredInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, InformationPackage.PORT__REQUIRED_INTERFACES);
    }
    return requiredInterfaces;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PortRealization> getOwnedPortRealizations() {

    if (ownedPortRealizations == null) {
      ownedPortRealizations = new EObjectContainmentEList.Resolving<PortRealization>(PortRealization.class, this, InformationPackage.PORT__OWNED_PORT_REALIZATIONS);
    }
    return ownedPortRealizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PortAllocation> getOwnedPortAllocations() {

    if (ownedPortAllocations == null) {
      ownedPortAllocations = new EObjectContainmentEList.Resolving<PortAllocation>(PortAllocation.class, this, InformationPackage.PORT__OWNED_PORT_ALLOCATIONS);
    }
    return ownedPortAllocations;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InformationPackage.PORT__OWNED_PROTOCOLS:
        return ((InternalEList<?>)getOwnedProtocols()).basicRemove(otherEnd, msgs);
      case InformationPackage.PORT__OWNED_PORT_REALIZATIONS:
        return ((InternalEList<?>)getOwnedPortRealizations()).basicRemove(otherEnd, msgs);
      case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS:
        return ((InternalEList<?>)getOwnedPortAllocations()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.PORT__INCOMING_PORT_REALIZATIONS:
        return getIncomingPortRealizations();
      case InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS:
        return getOutgoingPortRealizations();
      case InformationPackage.PORT__OWNED_PROTOCOLS:
        return getOwnedProtocols();
      case InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS:
        return getIncomingPortAllocations();
      case InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS:
        return getOutgoingPortAllocations();
      case InformationPackage.PORT__PROVIDED_INTERFACES:
        return getProvidedInterfaces();
      case InformationPackage.PORT__REQUIRED_INTERFACES:
        return getRequiredInterfaces();
      case InformationPackage.PORT__OWNED_PORT_REALIZATIONS:
        return getOwnedPortRealizations();
      case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS:
        return getOwnedPortAllocations();
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
      case InformationPackage.PORT__OWNED_PROTOCOLS:
        getOwnedProtocols().clear();
        getOwnedProtocols().addAll((Collection<? extends StateMachine>)newValue);
        return;
      case InformationPackage.PORT__PROVIDED_INTERFACES:
        getProvidedInterfaces().clear();
        getProvidedInterfaces().addAll((Collection<? extends Interface>)newValue);
        return;
      case InformationPackage.PORT__REQUIRED_INTERFACES:
        getRequiredInterfaces().clear();
        getRequiredInterfaces().addAll((Collection<? extends Interface>)newValue);
        return;
      case InformationPackage.PORT__OWNED_PORT_REALIZATIONS:
        getOwnedPortRealizations().clear();
        getOwnedPortRealizations().addAll((Collection<? extends PortRealization>)newValue);
        return;
      case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS:
        getOwnedPortAllocations().clear();
        getOwnedPortAllocations().addAll((Collection<? extends PortAllocation>)newValue);
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
      case InformationPackage.PORT__OWNED_PROTOCOLS:
        getOwnedProtocols().clear();
        return;
      case InformationPackage.PORT__PROVIDED_INTERFACES:
        getProvidedInterfaces().clear();
        return;
      case InformationPackage.PORT__REQUIRED_INTERFACES:
        getRequiredInterfaces().clear();
        return;
      case InformationPackage.PORT__OWNED_PORT_REALIZATIONS:
        getOwnedPortRealizations().clear();
        return;
      case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS:
        getOwnedPortAllocations().clear();
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
      case InformationPackage.PORT__INCOMING_PORT_REALIZATIONS:
        return !getIncomingPortRealizations().isEmpty();
      case InformationPackage.PORT__OUTGOING_PORT_REALIZATIONS:
        return !getOutgoingPortRealizations().isEmpty();
      case InformationPackage.PORT__OWNED_PROTOCOLS:
        return ownedProtocols != null && !ownedProtocols.isEmpty();
      case InformationPackage.PORT__INCOMING_PORT_ALLOCATIONS:
        return !getIncomingPortAllocations().isEmpty();
      case InformationPackage.PORT__OUTGOING_PORT_ALLOCATIONS:
        return !getOutgoingPortAllocations().isEmpty();
      case InformationPackage.PORT__PROVIDED_INTERFACES:
        return providedInterfaces != null && !providedInterfaces.isEmpty();
      case InformationPackage.PORT__REQUIRED_INTERFACES:
        return requiredInterfaces != null && !requiredInterfaces.isEmpty();
      case InformationPackage.PORT__OWNED_PORT_REALIZATIONS:
        return ownedPortRealizations != null && !ownedPortRealizations.isEmpty();
      case InformationPackage.PORT__OWNED_PORT_ALLOCATIONS:
        return ownedPortAllocations != null && !ownedPortAllocations.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //PortImpl