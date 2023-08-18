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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exchange Item Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMinCard <em>Owned Min Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMaxCard <em>Owned Max Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getAbstractType <em>Abstract Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#isComposite <em>Composite</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ExchangeItemElementImpl#getReferencedProperties <em>Referenced Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExchangeItemElementImpl extends NamedElementImpl implements ExchangeItemElement {

	/**
   * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
	protected static final boolean ORDERED_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isOrdered()
   * @generated
   * @ordered
   */
	protected boolean ordered = ORDERED_EDEFAULT;





	/**
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
	protected static final boolean UNIQUE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
	protected boolean unique = UNIQUE_EDEFAULT;





	/**
   * The default value of the '{@link #isMinInclusive() <em>Min Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMinInclusive()
   * @generated
   * @ordered
   */
	protected static final boolean MIN_INCLUSIVE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isMinInclusive() <em>Min Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMinInclusive()
   * @generated
   * @ordered
   */
	protected boolean minInclusive = MIN_INCLUSIVE_EDEFAULT;





	/**
   * The default value of the '{@link #isMaxInclusive() <em>Max Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMaxInclusive()
   * @generated
   * @ordered
   */
	protected static final boolean MAX_INCLUSIVE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isMaxInclusive() <em>Max Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isMaxInclusive()
   * @generated
   * @ordered
   */
	protected boolean maxInclusive = MAX_INCLUSIVE_EDEFAULT;





	/**
   * The cached value of the '{@link #getOwnedDefaultValue() <em>Owned Default Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefaultValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedDefaultValue;





	/**
   * The cached value of the '{@link #getOwnedMinValue() <em>Owned Min Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedMinValue;





	/**
   * The cached value of the '{@link #getOwnedMaxValue() <em>Owned Max Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedMaxValue;





	/**
   * The cached value of the '{@link #getOwnedNullValue() <em>Owned Null Value</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedNullValue()
   * @generated
   * @ordered
   */
	protected DataValue ownedNullValue;





	/**
   * The cached value of the '{@link #getOwnedMinCard() <em>Owned Min Card</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinCard()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMinCard;





	/**
   * The cached value of the '{@link #getOwnedMinLength() <em>Owned Min Length</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMinLength()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMinLength;





	/**
   * The cached value of the '{@link #getOwnedMaxCard() <em>Owned Max Card</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxCard()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMaxCard;





	/**
   * The cached value of the '{@link #getOwnedMaxLength() <em>Owned Max Length</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedMaxLength()
   * @generated
   * @ordered
   */
	protected NumericValue ownedMaxLength;





	/**
   * The cached value of the '{@link #getAbstractType() <em>Abstract Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAbstractType()
   * @generated
   * @ordered
   */
	protected AbstractType abstractType;









	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final ElementKind KIND_EDEFAULT = ElementKind.TYPE;

	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected ElementKind kind = KIND_EDEFAULT;





	/**
   * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDirection()
   * @generated
   * @ordered
   */
	protected static final ParameterDirection DIRECTION_EDEFAULT = ParameterDirection.IN;

	/**
   * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDirection()
   * @generated
   * @ordered
   */
	protected ParameterDirection direction = DIRECTION_EDEFAULT;





	/**
   * The default value of the '{@link #isComposite() <em>Composite</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isComposite()
   * @generated
   * @ordered
   */
	protected static final boolean COMPOSITE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isComposite() <em>Composite</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isComposite()
   * @generated
   * @ordered
   */
	protected boolean composite = COMPOSITE_EDEFAULT;





	/**
   * The cached value of the '{@link #getReferencedProperties() <em>Referenced Properties</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getReferencedProperties()
   * @generated
   * @ordered
   */
	protected EList<Property> referencedProperties;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ExchangeItemElementImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.EXCHANGE_ITEM_ELEMENT;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isOrdered() {

    return ordered;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOrdered(boolean newOrdered) {

    boolean oldOrdered = ordered;
    ordered = newOrdered;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED, oldOrdered, ordered));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isUnique() {

    return unique;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUnique(boolean newUnique) {

    boolean oldUnique = unique;
    unique = newUnique;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE, oldUnique, unique));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isMinInclusive() {

    return minInclusive;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMinInclusive(boolean newMinInclusive) {

    boolean oldMinInclusive = minInclusive;
    minInclusive = newMinInclusive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE, oldMinInclusive, minInclusive));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isMaxInclusive() {

    return maxInclusive;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMaxInclusive(boolean newMaxInclusive) {

    boolean oldMaxInclusive = maxInclusive;
    maxInclusive = newMaxInclusive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE, oldMaxInclusive, maxInclusive));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedDefaultValue() {

    return ownedDefaultValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedDefaultValue(DataValue newOwnedDefaultValue, NotificationChain msgs) {

    DataValue oldOwnedDefaultValue = ownedDefaultValue;
    ownedDefaultValue = newOwnedDefaultValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
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
	public void setOwnedDefaultValue(DataValue newOwnedDefaultValue) {

    if (newOwnedDefaultValue != ownedDefaultValue) {
      NotificationChain msgs = null;
      if (ownedDefaultValue != null)
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedMinValue() {

    return ownedMinValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinValue(DataValue newOwnedMinValue, NotificationChain msgs) {

    DataValue oldOwnedMinValue = ownedMinValue;
    ownedMinValue = newOwnedMinValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE, oldOwnedMinValue, newOwnedMinValue);
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
	public void setOwnedMinValue(DataValue newOwnedMinValue) {

    if (newOwnedMinValue != ownedMinValue) {
      NotificationChain msgs = null;
      if (ownedMinValue != null)
        msgs = ((InternalEObject)ownedMinValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE, null, msgs);
      if (newOwnedMinValue != null)
        msgs = ((InternalEObject)newOwnedMinValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE, null, msgs);
      msgs = basicSetOwnedMinValue(newOwnedMinValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE, newOwnedMinValue, newOwnedMinValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedMaxValue() {

    return ownedMaxValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxValue(DataValue newOwnedMaxValue, NotificationChain msgs) {

    DataValue oldOwnedMaxValue = ownedMaxValue;
    ownedMaxValue = newOwnedMaxValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE, oldOwnedMaxValue, newOwnedMaxValue);
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
	public void setOwnedMaxValue(DataValue newOwnedMaxValue) {

    if (newOwnedMaxValue != ownedMaxValue) {
      NotificationChain msgs = null;
      if (ownedMaxValue != null)
        msgs = ((InternalEObject)ownedMaxValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE, null, msgs);
      if (newOwnedMaxValue != null)
        msgs = ((InternalEObject)newOwnedMaxValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE, null, msgs);
      msgs = basicSetOwnedMaxValue(newOwnedMaxValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE, newOwnedMaxValue, newOwnedMaxValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataValue getOwnedNullValue() {

    return ownedNullValue;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedNullValue(DataValue newOwnedNullValue, NotificationChain msgs) {

    DataValue oldOwnedNullValue = ownedNullValue;
    ownedNullValue = newOwnedNullValue;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE, oldOwnedNullValue, newOwnedNullValue);
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
	public void setOwnedNullValue(DataValue newOwnedNullValue) {

    if (newOwnedNullValue != ownedNullValue) {
      NotificationChain msgs = null;
      if (ownedNullValue != null)
        msgs = ((InternalEObject)ownedNullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE, null, msgs);
      if (newOwnedNullValue != null)
        msgs = ((InternalEObject)newOwnedNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE, null, msgs);
      msgs = basicSetOwnedNullValue(newOwnedNullValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE, newOwnedNullValue, newOwnedNullValue));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMinCard() {

    return ownedMinCard;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinCard(NumericValue newOwnedMinCard, NotificationChain msgs) {

    NumericValue oldOwnedMinCard = ownedMinCard;
    ownedMinCard = newOwnedMinCard;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD, oldOwnedMinCard, newOwnedMinCard);
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
	public void setOwnedMinCard(NumericValue newOwnedMinCard) {

    if (newOwnedMinCard != ownedMinCard) {
      NotificationChain msgs = null;
      if (ownedMinCard != null)
        msgs = ((InternalEObject)ownedMinCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD, null, msgs);
      if (newOwnedMinCard != null)
        msgs = ((InternalEObject)newOwnedMinCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD, null, msgs);
      msgs = basicSetOwnedMinCard(newOwnedMinCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD, newOwnedMinCard, newOwnedMinCard));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMinLength() {

    return ownedMinLength;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMinLength(NumericValue newOwnedMinLength, NotificationChain msgs) {

    NumericValue oldOwnedMinLength = ownedMinLength;
    ownedMinLength = newOwnedMinLength;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH, oldOwnedMinLength, newOwnedMinLength);
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
	public void setOwnedMinLength(NumericValue newOwnedMinLength) {

    if (newOwnedMinLength != ownedMinLength) {
      NotificationChain msgs = null;
      if (ownedMinLength != null)
        msgs = ((InternalEObject)ownedMinLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH, null, msgs);
      if (newOwnedMinLength != null)
        msgs = ((InternalEObject)newOwnedMinLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH, null, msgs);
      msgs = basicSetOwnedMinLength(newOwnedMinLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH, newOwnedMinLength, newOwnedMinLength));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMaxCard() {

    return ownedMaxCard;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxCard(NumericValue newOwnedMaxCard, NotificationChain msgs) {

    NumericValue oldOwnedMaxCard = ownedMaxCard;
    ownedMaxCard = newOwnedMaxCard;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD, oldOwnedMaxCard, newOwnedMaxCard);
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
	public void setOwnedMaxCard(NumericValue newOwnedMaxCard) {

    if (newOwnedMaxCard != ownedMaxCard) {
      NotificationChain msgs = null;
      if (ownedMaxCard != null)
        msgs = ((InternalEObject)ownedMaxCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD, null, msgs);
      if (newOwnedMaxCard != null)
        msgs = ((InternalEObject)newOwnedMaxCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD, null, msgs);
      msgs = basicSetOwnedMaxCard(newOwnedMaxCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD, newOwnedMaxCard, newOwnedMaxCard));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericValue getOwnedMaxLength() {

    return ownedMaxLength;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetOwnedMaxLength(NumericValue newOwnedMaxLength, NotificationChain msgs) {

    NumericValue oldOwnedMaxLength = ownedMaxLength;
    ownedMaxLength = newOwnedMaxLength;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH, oldOwnedMaxLength, newOwnedMaxLength);
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
	public void setOwnedMaxLength(NumericValue newOwnedMaxLength) {

    if (newOwnedMaxLength != ownedMaxLength) {
      NotificationChain msgs = null;
      if (ownedMaxLength != null)
        msgs = ((InternalEObject)ownedMaxLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH, null, msgs);
      if (newOwnedMaxLength != null)
        msgs = ((InternalEObject)newOwnedMaxLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH, null, msgs);
      msgs = basicSetOwnedMaxLength(newOwnedMaxLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH, newOwnedMaxLength, newOwnedMaxLength));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType getAbstractType() {

    if (abstractType != null && abstractType.eIsProxy()) {
      InternalEObject oldAbstractType = (InternalEObject)abstractType;
      abstractType = (AbstractType)eResolveProxy(oldAbstractType);
      if (abstractType != oldAbstractType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE, oldAbstractType, abstractType));
      }
    }
    return abstractType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetAbstractType() {

    return abstractType;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAbstractType(AbstractType newAbstractType) {

    AbstractType oldAbstractType = abstractType;
    abstractType = newAbstractType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE, oldAbstractType, abstractType));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type getType() {

    Type type = basicGetType();
    return type != null && type.eIsProxy() ? (Type)eResolveProxy((InternalEObject)type) : type;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type basicGetType() {


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
    EAnnotation annotation = CapellacorePackage.Literals.TYPED_ELEMENT__TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, CapellacorePackage.Literals.TYPED_ELEMENT__TYPE, annotation);
    
    try {
      return (Type) result;
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

	public ElementKind getKind() {

    return kind;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(ElementKind newKind) {

    ElementKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__KIND, oldKind, kind));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ParameterDirection getDirection() {

    return direction;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setDirection(ParameterDirection newDirection) {

    ParameterDirection oldDirection = direction;
    direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__DIRECTION, oldDirection, direction));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isComposite() {

    return composite;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setComposite(boolean newComposite) {

    boolean oldComposite = composite;
    composite = newComposite;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.EXCHANGE_ITEM_ELEMENT__COMPOSITE, oldComposite, composite));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Property> getReferencedProperties() {

    if (referencedProperties == null) {
      referencedProperties = new EObjectResolvingEList<Property>(Property.class, this, InformationPackage.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES);
    }
    return referencedProperties;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE:
        return basicSetOwnedMinValue(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE:
        return basicSetOwnedMaxValue(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE:
        return basicSetOwnedNullValue(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD:
        return basicSetOwnedMinCard(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH:
        return basicSetOwnedMinLength(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD:
        return basicSetOwnedMaxCard(null, msgs);
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH:
        return basicSetOwnedMaxLength(null, msgs);
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
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED:
        return isOrdered();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE:
        return isUnique();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE:
        return isMinInclusive();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE:
        return isMaxInclusive();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE:
        return getOwnedDefaultValue();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE:
        return getOwnedMinValue();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE:
        return getOwnedMaxValue();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE:
        return getOwnedNullValue();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD:
        return getOwnedMinCard();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH:
        return getOwnedMinLength();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD:
        return getOwnedMaxCard();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH:
        return getOwnedMaxLength();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE:
        if (resolve) return getAbstractType();
        return basicGetAbstractType();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__KIND:
        return getKind();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__DIRECTION:
        return getDirection();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__COMPOSITE:
        return isComposite();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES:
        return getReferencedProperties();
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
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED:
          setOrdered((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE:
          setUnique((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE:
          setMinInclusive((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE:
          setMaxInclusive((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((DataValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE:
          setOwnedMinValue((DataValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE:
          setOwnedMaxValue((DataValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE:
          setOwnedNullValue((DataValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD:
          setOwnedMinCard((NumericValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH:
          setOwnedMinLength((NumericValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD:
          setOwnedMaxCard((NumericValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH:
          setOwnedMaxLength((NumericValue)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE:
          setAbstractType((AbstractType)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__KIND:
          setKind((ElementKind)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__DIRECTION:
          setDirection((ParameterDirection)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__COMPOSITE:
          setComposite((Boolean)newValue);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES:
        getReferencedProperties().clear();
        getReferencedProperties().addAll((Collection<? extends Property>)newValue);
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
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE:
        setMinInclusive(MIN_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE:
        setMaxInclusive(MAX_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((DataValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE:
        setOwnedMinValue((DataValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE:
        setOwnedMaxValue((DataValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE:
        setOwnedNullValue((DataValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD:
        setOwnedMinCard((NumericValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH:
        setOwnedMinLength((NumericValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD:
        setOwnedMaxCard((NumericValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH:
        setOwnedMaxLength((NumericValue)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE:
        setAbstractType((AbstractType)null);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__DIRECTION:
        setDirection(DIRECTION_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__COMPOSITE:
        setComposite(COMPOSITE_EDEFAULT);
        return;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES:
        getReferencedProperties().clear();
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
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE:
        return minInclusive != MIN_INCLUSIVE_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE:
        return maxInclusive != MAX_INCLUSIVE_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE:
        return ownedMinValue != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE:
        return ownedMaxValue != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE:
        return ownedNullValue != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD:
        return ownedMinCard != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH:
        return ownedMinLength != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD:
        return ownedMaxCard != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH:
        return ownedMaxLength != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE:
        return abstractType != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__TYPE:
        return basicGetType() != null;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__KIND:
        return kind != KIND_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__DIRECTION:
        return direction != DIRECTION_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__COMPOSITE:
        return composite != COMPOSITE_EDEFAULT;
      case InformationPackage.EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES:
        return referencedProperties != null && !referencedProperties.isEmpty();
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
    if (baseClass == MultiplicityElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED: return InformationPackage.MULTIPLICITY_ELEMENT__ORDERED;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE: return InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD;
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == AbstractTypedElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE: return ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.EXCHANGE_ITEM_ELEMENT__TYPE: return CapellacorePackage.TYPED_ELEMENT__TYPE;
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
    if (baseClass == MultiplicityElement.class) {
      switch (baseFeatureID) {
        case InformationPackage.MULTIPLICITY_ELEMENT__ORDERED: return InformationPackage.EXCHANGE_ITEM_ELEMENT__ORDERED;
        case InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__UNIQUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__MIN_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__MAX_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_DEFAULT_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_NULL_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MIN_LENGTH;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH: return InformationPackage.EXCHANGE_ITEM_ELEMENT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == AbstractTypedElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__ABSTRACT_TYPE;
        default: return -1;
      }
    }
    if (baseClass == TypedElement.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.TYPED_ELEMENT__TYPE: return InformationPackage.EXCHANGE_ITEM_ELEMENT__TYPE;
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
    result.append(" (ordered: "); //$NON-NLS-1$
    result.append(ordered);
    result.append(", unique: "); //$NON-NLS-1$
    result.append(unique);
    result.append(", minInclusive: "); //$NON-NLS-1$
    result.append(minInclusive);
    result.append(", maxInclusive: "); //$NON-NLS-1$
    result.append(maxInclusive);
    result.append(", kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", direction: "); //$NON-NLS-1$
    result.append(direction);
    result.append(", composite: "); //$NON-NLS-1$
    result.append(composite);
    result.append(')');
    return result.toString();
  }


} //ExchangeItemElementImpl