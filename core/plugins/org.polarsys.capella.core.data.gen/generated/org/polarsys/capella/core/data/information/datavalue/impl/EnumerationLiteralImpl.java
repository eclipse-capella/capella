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
package org.polarsys.capella.core.data.information.datavalue.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationLiteralImpl#getDomainValue <em>Domain Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumerationLiteralImpl extends AbstractEnumerationValueImpl implements EnumerationLiteral {

	/**
   * The cached value of the '{@link #getDomainValue() <em>Domain Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDomainValue()
   * @generated
   * @ordered
   */
	protected DataValue domainValue;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EnumerationLiteralImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatavaluePackage.Literals.ENUMERATION_LITERAL;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getDomainValue() {

    if (domainValue != null && domainValue.eIsProxy()) {
      InternalEObject oldDomainValue = (InternalEObject)domainValue;
      domainValue = (DataValue)eResolveProxy(oldDomainValue);
      if (domainValue != oldDomainValue) {
        InternalEObject newDomainValue = (InternalEObject)domainValue;
        NotificationChain msgs = oldDomainValue.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, null, null);
        if (newDomainValue.eInternalContainer() == null) {
          msgs = newDomainValue.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, oldDomainValue, domainValue));
      }
    }
    return domainValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue basicGetDomainValue() {

    return domainValue;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetDomainValue(DataValue newDomainValue, NotificationChain msgs) {

    DataValue oldDomainValue = domainValue;
    domainValue = newDomainValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, oldDomainValue, newDomainValue);
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
	public void setDomainValue(DataValue newDomainValue) {

    if (newDomainValue != domainValue) {
      NotificationChain msgs = null;
      if (domainValue != null)
        msgs = ((InternalEObject)domainValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, null, msgs);
      if (newDomainValue != null)
        msgs = ((InternalEObject)newDomainValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, null, msgs);
      msgs = basicSetDomainValue(newDomainValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE, newDomainValue, newDomainValue));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE:
        return basicSetDomainValue(null, msgs);
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
      case DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE:
        if (resolve) return getDomainValue();
        return basicGetDomainValue();
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
      case DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE:
          setDomainValue((DataValue)newValue);
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
      case DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE:
        setDomainValue((DataValue)null);
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
      case DatavaluePackage.ENUMERATION_LITERAL__DOMAIN_VALUE:
        return domainValue != null;
    }
    return super.eIsSet(featureID);
  }



} //EnumerationLiteralImpl