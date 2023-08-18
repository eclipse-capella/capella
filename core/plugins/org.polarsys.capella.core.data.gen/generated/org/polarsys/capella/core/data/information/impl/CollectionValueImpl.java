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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionValueImpl#getOwnedElements <em>Owned Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionValueImpl#getOwnedDefaultElement <em>Owned Default Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionValueImpl extends AbstractCollectionValueImpl implements CollectionValue {

	/**
   * The cached value of the '{@link #getOwnedElements() <em>Owned Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedElements()
   * @generated
   * @ordered
   */
	protected EList<DataValue> ownedElements;





	/**
   * The cached value of the '{@link #getOwnedDefaultElement() <em>Owned Default Element</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefaultElement()
   * @generated
   * @ordered
   */
	protected DataValue ownedDefaultElement;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CollectionValueImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.COLLECTION_VALUE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<DataValue> getOwnedElements() {

    if (ownedElements == null) {
      ownedElements = new EObjectContainmentEList.Resolving<DataValue>(DataValue.class, this, InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS);
    }
    return ownedElements;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedDefaultElement() {

    if (ownedDefaultElement != null && ownedDefaultElement.eIsProxy()) {
      InternalEObject oldOwnedDefaultElement = (InternalEObject)ownedDefaultElement;
      ownedDefaultElement = (DataValue)eResolveProxy(oldOwnedDefaultElement);
      if (ownedDefaultElement != oldOwnedDefaultElement) {
        InternalEObject newOwnedDefaultElement = (InternalEObject)ownedDefaultElement;
        NotificationChain msgs = oldOwnedDefaultElement.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, null, null);
        if (newOwnedDefaultElement.eInternalContainer() == null) {
          msgs = newOwnedDefaultElement.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, oldOwnedDefaultElement, ownedDefaultElement));
      }
    }
    return ownedDefaultElement;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue basicGetOwnedDefaultElement() {

    return ownedDefaultElement;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDefaultElement(DataValue newOwnedDefaultElement, NotificationChain msgs) {

    DataValue oldOwnedDefaultElement = ownedDefaultElement;
    ownedDefaultElement = newOwnedDefaultElement;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, oldOwnedDefaultElement, newOwnedDefaultElement);
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
	public void setOwnedDefaultElement(DataValue newOwnedDefaultElement) {

    if (newOwnedDefaultElement != ownedDefaultElement) {
      NotificationChain msgs = null;
      if (ownedDefaultElement != null)
        msgs = ((InternalEObject)ownedDefaultElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, null, msgs);
      if (newOwnedDefaultElement != null)
        msgs = ((InternalEObject)newOwnedDefaultElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, null, msgs);
      msgs = basicSetOwnedDefaultElement(newOwnedDefaultElement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT, newOwnedDefaultElement, newOwnedDefaultElement));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS:
        return ((InternalEList<?>)getOwnedElements()).basicRemove(otherEnd, msgs);
      case InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT:
        return basicSetOwnedDefaultElement(null, msgs);
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
      case InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS:
        return getOwnedElements();
      case InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT:
        if (resolve) return getOwnedDefaultElement();
        return basicGetOwnedDefaultElement();
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
      case InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS:
        getOwnedElements().clear();
        getOwnedElements().addAll((Collection<? extends DataValue>)newValue);
        return;
      case InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT:
          setOwnedDefaultElement((DataValue)newValue);
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
      case InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS:
        getOwnedElements().clear();
        return;
      case InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT:
        setOwnedDefaultElement((DataValue)null);
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
      case InformationPackage.COLLECTION_VALUE__OWNED_ELEMENTS:
        return ownedElements != null && !ownedElements.isEmpty();
      case InformationPackage.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT:
        return ownedDefaultElement != null;
    }
    return super.eIsSet(featureID);
  }



} //CollectionValueImpl