/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.capellacore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Property Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.EnumerationPropertyValueImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationPropertyValueImpl extends AbstractPropertyValueImpl implements EnumerationPropertyValue {

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EnumerationPropertyType type;





	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected EnumerationPropertyLiteral value;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationPropertyValueImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EnumerationPropertyType getType() {

		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (EnumerationPropertyType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE, oldType, type));
			}
		}
		return type;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EnumerationPropertyType basicGetType() {

		return type;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setType(EnumerationPropertyType newType) {

		EnumerationPropertyType oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE, oldType, type));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EnumerationPropertyLiteral getValue() {

		if (value != null && value.eIsProxy()) {
			InternalEObject oldValue = (InternalEObject)value;
			value = (EnumerationPropertyLiteral)eResolveProxy(oldValue);
			if (value != oldValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE, oldValue, value));
			}
		}
		return value;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public EnumerationPropertyLiteral basicGetValue() {

		return value;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setValue(EnumerationPropertyLiteral newValue) {

		EnumerationPropertyLiteral oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE, oldValue, value));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE:
				if (resolve) return getValue();
				return basicGetValue();
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
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE:
				// begin-extension-code
				if (newValue == null || newValue instanceof EnumerationPropertyType) {
				// end-extension-code
					setType((EnumerationPropertyType)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE:
				// begin-extension-code
				if (newValue == null || newValue instanceof EnumerationPropertyLiteral) {
				// end-extension-code
					setValue((EnumerationPropertyLiteral)newValue);
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
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE:
				setType((EnumerationPropertyType)null);
				return;
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE:
				setValue((EnumerationPropertyLiteral)null);
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
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__TYPE:
				return type != null;
			case CapellacorePackage.ENUMERATION_PROPERTY_VALUE__VALUE:
				return value != null;
		}
		return super.eIsSet(featureID);
	}



} //EnumerationPropertyValueImpl