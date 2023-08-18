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

package org.polarsys.capella.core.data.information.communication.impl;

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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Exchanger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getOwnedCommunicationLinks <em>Owned Communication Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getProduce <em>Produce</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getConsume <em>Consume</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getSend <em>Send</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getReceive <em>Receive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getCall <em>Call</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getExecute <em>Execute</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getWrite <em>Write</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getAccess <em>Access</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getAcquire <em>Acquire</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.communication.impl.CommunicationLinkExchangerImpl#getTransmit <em>Transmit</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CommunicationLinkExchangerImpl extends EObjectImpl implements CommunicationLinkExchanger {

	/**
   * The cached value of the '{@link #getOwnedCommunicationLinks() <em>Owned Communication Links</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedCommunicationLinks()
   * @generated
   * @ordered
   */
	protected EList<CommunicationLink> ownedCommunicationLinks;












































	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CommunicationLinkExchangerImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getOwnedCommunicationLinks() {

    if (ownedCommunicationLinks == null) {
      ownedCommunicationLinks = new EObjectContainmentEList.Resolving<CommunicationLink>(CommunicationLink.class, this, CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS);
    }
    return ownedCommunicationLinks;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunicationLink> getProduce() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__PRODUCE, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getConsume() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CONSUME, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getSend() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__SEND, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getReceive() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__RECEIVE, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getCall() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__CALL, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getExecute() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__EXECUTE, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getWrite() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__WRITE, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getAccess() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACCESS, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getAcquire() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__ACQUIRE, resultAsList.size(), resultAsList.toArray());
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

	public EList<CommunicationLink> getTransmit() {


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
    EAnnotation annotation = CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    Collection<CommunicationLink> resultAsList = (Collection<CommunicationLink>) result;
    return new EcoreEList.UnmodifiableEList<CommunicationLink>(this, CommunicationPackage.Literals.COMMUNICATION_LINK_EXCHANGER__TRANSMIT, resultAsList.size(), resultAsList.toArray());
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
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
        return ((InternalEList<?>)getOwnedCommunicationLinks()).basicRemove(otherEnd, msgs);
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
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
        return getOwnedCommunicationLinks();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__PRODUCE:
        return getProduce();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CONSUME:
        return getConsume();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__SEND:
        return getSend();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__RECEIVE:
        return getReceive();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CALL:
        return getCall();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__EXECUTE:
        return getExecute();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__WRITE:
        return getWrite();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACCESS:
        return getAccess();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACQUIRE:
        return getAcquire();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__TRANSMIT:
        return getTransmit();
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
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
        getOwnedCommunicationLinks().clear();
        getOwnedCommunicationLinks().addAll((Collection<? extends CommunicationLink>)newValue);
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
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
        getOwnedCommunicationLinks().clear();
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
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS:
        return ownedCommunicationLinks != null && !ownedCommunicationLinks.isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__PRODUCE:
        return !getProduce().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CONSUME:
        return !getConsume().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__SEND:
        return !getSend().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__RECEIVE:
        return !getReceive().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__CALL:
        return !getCall().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__EXECUTE:
        return !getExecute().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__WRITE:
        return !getWrite().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACCESS:
        return !getAccess().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__ACQUIRE:
        return !getAcquire().isEmpty();
      case CommunicationPackage.COMMUNICATION_LINK_EXCHANGER__TRANSMIT:
        return !getTransmit().isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CommunicationLinkExchangerImpl