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

package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.impl.RelationshipImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.InteractionPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Capability Extend</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.impl.AbstractCapabilityExtendImpl#getExtensionLocation <em>Extension Location</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbstractCapabilityExtendImpl extends RelationshipImpl implements AbstractCapabilityExtend {

	/**
   * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExtended()
   * @generated
   * @ordered
   */
	protected AbstractCapability extended;









	/**
   * The cached value of the '{@link #getExtensionLocation() <em>Extension Location</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExtensionLocation()
   * @generated
   * @ordered
   */
	protected AbstractCapabilityExtensionPoint extensionLocation;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractCapabilityExtendImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability getExtended() {

    if (extended != null && extended.eIsProxy()) {
      InternalEObject oldExtended = (InternalEObject)extended;
      extended = (AbstractCapability)eResolveProxy(oldExtended);
      if (extended != oldExtended) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, oldExtended, extended));
      }
    }
    return extended;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability basicGetExtended() {

    return extended;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setExtended(AbstractCapability newExtended) {

    AbstractCapability oldExtended = extended;
    extended = newExtended;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED, oldExtended, extended));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability getExtension() {

    AbstractCapability extension = basicGetExtension();
    return extension != null && extension.eIsProxy() ? (AbstractCapability)eResolveProxy((InternalEObject)extension) : extension;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapability basicGetExtension() {


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
    EAnnotation annotation = InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION, annotation);
    
    try {
      return (AbstractCapability) result;
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

	public AbstractCapabilityExtensionPoint getExtensionLocation() {

    if (extensionLocation != null && extensionLocation.eIsProxy()) {
      InternalEObject oldExtensionLocation = (InternalEObject)extensionLocation;
      extensionLocation = (AbstractCapabilityExtensionPoint)eResolveProxy(oldExtensionLocation);
      if (extensionLocation != oldExtensionLocation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, oldExtensionLocation, extensionLocation));
      }
    }
    return extensionLocation;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractCapabilityExtensionPoint basicGetExtensionLocation() {

    return extensionLocation;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetExtensionLocation(AbstractCapabilityExtensionPoint newExtensionLocation, NotificationChain msgs) {

    AbstractCapabilityExtensionPoint oldExtensionLocation = extensionLocation;
    extensionLocation = newExtensionLocation;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, oldExtensionLocation, newExtensionLocation);
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
	public void setExtensionLocation(AbstractCapabilityExtensionPoint newExtensionLocation) {

    if (newExtensionLocation != extensionLocation) {
      NotificationChain msgs = null;
      if (extensionLocation != null)
        msgs = ((InternalEObject)extensionLocation).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
      if (newExtensionLocation != null)
        msgs = ((InternalEObject)newExtensionLocation).eInverseAdd(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
      msgs = basicSetExtensionLocation(newExtensionLocation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION, newExtensionLocation, newExtensionLocation));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
        if (extensionLocation != null)
          msgs = ((InternalEObject)extensionLocation).eInverseRemove(this, InteractionPackage.ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS, AbstractCapabilityExtensionPoint.class, msgs);
        return basicSetExtensionLocation((AbstractCapabilityExtensionPoint)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
        return basicSetExtensionLocation(null, msgs);
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
        if (resolve) return getExtended();
        return basicGetExtended();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
        if (resolve) return getExtension();
        return basicGetExtension();
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
        if (resolve) return getExtensionLocation();
        return basicGetExtensionLocation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
          setExtended((AbstractCapability)newValue);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
          setExtensionLocation((AbstractCapabilityExtensionPoint)newValue);
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
        setExtended((AbstractCapability)null);
        return;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
        setExtensionLocation((AbstractCapabilityExtensionPoint)null);
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
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENDED:
        return extended != null;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION:
        return basicGetExtension() != null;
      case InteractionPackage.ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION:
        return extensionLocation != null;
    }
    return super.eIsSet(featureID);
  }



} //AbstractCapabilityExtendImpl