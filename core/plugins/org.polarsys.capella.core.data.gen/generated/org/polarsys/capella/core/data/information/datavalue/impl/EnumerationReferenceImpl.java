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
package org.polarsys.capella.core.data.information.datavalue.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl#getReferencedValue <em>Referenced Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.EnumerationReferenceImpl#getReferencedProperty <em>Referenced Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationReferenceImpl extends AbstractEnumerationValueImpl implements EnumerationReference {

	/**
	 * The cached value of the '{@link #getReferencedValue() <em>Referenced Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedValue()
	 * @generated
	 * @ordered
	 */
	protected AbstractEnumerationValue referencedValue;





	/**
	 * The cached value of the '{@link #getReferencedProperty() <em>Referenced Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedProperty()
	 * @generated
	 * @ordered
	 */
	protected Property referencedProperty;




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationReferenceImpl() {

		super();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatavaluePackage.Literals.ENUMERATION_REFERENCE;
	}





	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEnumerationValue getReferencedValue() {

		if (referencedValue != null && referencedValue.eIsProxy()) {
			InternalEObject oldReferencedValue = (InternalEObject)referencedValue;
			referencedValue = (AbstractEnumerationValue)eResolveProxy(oldReferencedValue);
			if (referencedValue != oldReferencedValue) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE, oldReferencedValue, referencedValue));
			}
		}
		return referencedValue;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public AbstractEnumerationValue basicGetReferencedValue() {

		return referencedValue;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setReferencedValue(AbstractEnumerationValue newReferencedValue) {

		AbstractEnumerationValue oldReferencedValue = referencedValue;
		referencedValue = newReferencedValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE, oldReferencedValue, referencedValue));

	}






	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public Property getReferencedProperty() {

		if (referencedProperty != null && referencedProperty.eIsProxy()) {
			InternalEObject oldReferencedProperty = (InternalEObject)referencedProperty;
			referencedProperty = (Property)eResolveProxy(oldReferencedProperty);
			if (referencedProperty != oldReferencedProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY, oldReferencedProperty, referencedProperty));
			}
		}
		return referencedProperty;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public Property basicGetReferencedProperty() {

		return referencedProperty;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	public void setReferencedProperty(Property newReferencedProperty) {

		Property oldReferencedProperty = referencedProperty;
		referencedProperty = newReferencedProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY, oldReferencedProperty, referencedProperty));

	}




	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE:
				if (resolve) return getReferencedValue();
				return basicGetReferencedValue();
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY:
				if (resolve) return getReferencedProperty();
				return basicGetReferencedProperty();
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
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE:
				// begin-extension-code
				if (newValue == null || newValue instanceof AbstractEnumerationValue) {
				// end-extension-code
					setReferencedValue((AbstractEnumerationValue)newValue);
				// begin-extension-code
				}
				// end-extension-code
				return;
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY:
				// begin-extension-code
				if (newValue == null || newValue instanceof Property) {
				// end-extension-code
					setReferencedProperty((Property)newValue);
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
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE:
				setReferencedValue((AbstractEnumerationValue)null);
				return;
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY:
				setReferencedProperty((Property)null);
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
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_VALUE:
				return referencedValue != null;
			case DatavaluePackage.ENUMERATION_REFERENCE__REFERENCED_PROPERTY:
				return referencedProperty != null;
		}
		return super.eIsSet(featureID);
	}



} //EnumerationReferenceImpl