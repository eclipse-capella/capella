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
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.data.capellacore.impl.ClassifierImpl;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionKind;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMinCard <em>Owned Min Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMaxCard <em>Owned Max Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedPropertyValuePkgs <em>Owned Property Value Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getOwnedDataValues <em>Owned Data Values</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#isIsPrimitive <em>Is Primitive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getAggregationKind <em>Aggregation Kind</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.CollectionImpl#getContainedOperations <em>Contained Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CollectionImpl extends ClassifierImpl implements Collection {

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
   * The cached value of the '{@link #getOwnedPropertyValuePkgs() <em>Owned Property Value Pkgs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedPropertyValuePkgs()
   * @generated
   * @ordered
   */
	protected EList<PropertyValuePkg> ownedPropertyValuePkgs;
	/**
   * The cached value of the '{@link #getOwnedDataValues() <em>Owned Data Values</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDataValues()
   * @generated
   * @ordered
   */
	protected EList<DataValue> ownedDataValues;
	/**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected static final boolean FINAL_EDEFAULT = false;
	/**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
	protected boolean final_ = FINAL_EDEFAULT;
	/**
   * The default value of the '{@link #isIsPrimitive() <em>Is Primitive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPrimitive()
   * @generated
   * @ordered
   */
	protected static final boolean IS_PRIMITIVE_EDEFAULT = false;
	/**
   * The cached value of the '{@link #isIsPrimitive() <em>Is Primitive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsPrimitive()
   * @generated
   * @ordered
   */
	protected boolean isPrimitive = IS_PRIMITIVE_EDEFAULT;
	/**
   * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected static final VisibilityKind VISIBILITY_EDEFAULT = VisibilityKind.UNSET;
	/**
   * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVisibility()
   * @generated
   * @ordered
   */
	protected VisibilityKind visibility = VISIBILITY_EDEFAULT;
	/**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected static final CollectionKind KIND_EDEFAULT = CollectionKind.ARRAY;
	/**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
	protected CollectionKind kind = KIND_EDEFAULT;
	/**
   * The default value of the '{@link #getAggregationKind() <em>Aggregation Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAggregationKind()
   * @generated
   * @ordered
   */
	protected static final AggregationKind AGGREGATION_KIND_EDEFAULT = AggregationKind.UNSET;
	/**
   * The cached value of the '{@link #getAggregationKind() <em>Aggregation Kind</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAggregationKind()
   * @generated
   * @ordered
   */
	protected AggregationKind aggregationKind = AGGREGATION_KIND_EDEFAULT;
	/**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
	protected Type type;
	/**
   * The cached value of the '{@link #getIndex() <em>Index</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
	protected EList<DataType> index;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CollectionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.COLLECTION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__ORDERED, oldOrdered, ordered));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__UNIQUE, oldUnique, unique));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__MIN_INCLUSIVE, oldMinInclusive, minInclusive));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__MAX_INCLUSIVE, oldMaxInclusive, maxInclusive));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
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
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_VALUE, oldOwnedMinValue, newOwnedMinValue);
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
        msgs = ((InternalEObject)ownedMinValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_VALUE, null, msgs);
      if (newOwnedMinValue != null)
        msgs = ((InternalEObject)newOwnedMinValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_VALUE, null, msgs);
      msgs = basicSetOwnedMinValue(newOwnedMinValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_VALUE, newOwnedMinValue, newOwnedMinValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_VALUE, oldOwnedMaxValue, newOwnedMaxValue);
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
        msgs = ((InternalEObject)ownedMaxValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_VALUE, null, msgs);
      if (newOwnedMaxValue != null)
        msgs = ((InternalEObject)newOwnedMaxValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_VALUE, null, msgs);
      msgs = basicSetOwnedMaxValue(newOwnedMaxValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_VALUE, newOwnedMaxValue, newOwnedMaxValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_NULL_VALUE, oldOwnedNullValue, newOwnedNullValue);
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
        msgs = ((InternalEObject)ownedNullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_NULL_VALUE, null, msgs);
      if (newOwnedNullValue != null)
        msgs = ((InternalEObject)newOwnedNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_NULL_VALUE, null, msgs);
      msgs = basicSetOwnedNullValue(newOwnedNullValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_NULL_VALUE, newOwnedNullValue, newOwnedNullValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_CARD, oldOwnedMinCard, newOwnedMinCard);
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
        msgs = ((InternalEObject)ownedMinCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_CARD, null, msgs);
      if (newOwnedMinCard != null)
        msgs = ((InternalEObject)newOwnedMinCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_CARD, null, msgs);
      msgs = basicSetOwnedMinCard(newOwnedMinCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_CARD, newOwnedMinCard, newOwnedMinCard));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_LENGTH, oldOwnedMinLength, newOwnedMinLength);
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
        msgs = ((InternalEObject)ownedMinLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_LENGTH, null, msgs);
      if (newOwnedMinLength != null)
        msgs = ((InternalEObject)newOwnedMinLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MIN_LENGTH, null, msgs);
      msgs = basicSetOwnedMinLength(newOwnedMinLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MIN_LENGTH, newOwnedMinLength, newOwnedMinLength));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_CARD, oldOwnedMaxCard, newOwnedMaxCard);
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
        msgs = ((InternalEObject)ownedMaxCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_CARD, null, msgs);
      if (newOwnedMaxCard != null)
        msgs = ((InternalEObject)newOwnedMaxCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_CARD, null, msgs);
      msgs = basicSetOwnedMaxCard(newOwnedMaxCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_CARD, newOwnedMaxCard, newOwnedMaxCard));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_LENGTH, oldOwnedMaxLength, newOwnedMaxLength);
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
        msgs = ((InternalEObject)ownedMaxLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_LENGTH, null, msgs);
      if (newOwnedMaxLength != null)
        msgs = ((InternalEObject)newOwnedMaxLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.COLLECTION__OWNED_MAX_LENGTH, null, msgs);
      msgs = basicSetOwnedMaxLength(newOwnedMaxLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__OWNED_MAX_LENGTH, newOwnedMaxLength, newOwnedMaxLength));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PropertyValuePkg> getOwnedPropertyValuePkgs() {

    if (ownedPropertyValuePkgs == null) {
      ownedPropertyValuePkgs = new EObjectContainmentEList.Resolving<PropertyValuePkg>(PropertyValuePkg.class, this, InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS);
    }
    return ownedPropertyValuePkgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<DataValue> getOwnedDataValues() {

    if (ownedDataValues == null) {
      ownedDataValues = new EObjectContainmentEList<DataValue>(DataValue.class, this, InformationPackage.COLLECTION__OWNED_DATA_VALUES);
    }
    return ownedDataValues;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isFinal() {

    return final_;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setFinal(boolean newFinal) {

    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__FINAL, oldFinal, final_));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsPrimitive() {

    return isPrimitive;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsPrimitive(boolean newIsPrimitive) {

    boolean oldIsPrimitive = isPrimitive;
    isPrimitive = newIsPrimitive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__IS_PRIMITIVE, oldIsPrimitive, isPrimitive));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public VisibilityKind getVisibility() {

    return visibility;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVisibility(VisibilityKind newVisibility) {

    VisibilityKind oldVisibility = visibility;
    visibility = newVisibility == null ? VISIBILITY_EDEFAULT : newVisibility;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__VISIBILITY, oldVisibility, visibility));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CollectionKind getKind() {

    return kind;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKind(CollectionKind newKind) {

    CollectionKind oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__KIND, oldKind, kind));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AggregationKind getAggregationKind() {

    return aggregationKind;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAggregationKind(AggregationKind newAggregationKind) {

    AggregationKind oldAggregationKind = aggregationKind;
    aggregationKind = newAggregationKind == null ? AGGREGATION_KIND_EDEFAULT : newAggregationKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__AGGREGATION_KIND, oldAggregationKind, aggregationKind));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type getType() {

    if (type != null && type.eIsProxy()) {
      InternalEObject oldType = (InternalEObject)type;
      type = (Type)eResolveProxy(oldType);
      if (type != oldType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.COLLECTION__TYPE, oldType, type));
      }
    }
    return type;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Type basicGetType() {

    return type;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setType(Type newType) {

    Type oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.COLLECTION__TYPE, oldType, type));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<DataType> getIndex() {

    if (index == null) {
      index = new EObjectResolvingEList<DataType>(DataType.class, this, InformationPackage.COLLECTION__INDEX);
    }
    return index;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Operation> getContainedOperations() {


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
    EAnnotation annotation = InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS, annotation);
    
    try {
    @SuppressWarnings("unchecked")
    java.util.Collection<Operation> resultAsList = (java.util.Collection<Operation>) result;
    return new EcoreEList.UnmodifiableEList<Operation>(this, InformationPackage.Literals.COLLECTION__CONTAINED_OPERATIONS, resultAsList.size(), resultAsList.toArray());
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
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
        return basicSetOwnedMinValue(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
        return basicSetOwnedMaxValue(null, msgs);
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
        return basicSetOwnedNullValue(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
        return basicSetOwnedMinCard(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
        return basicSetOwnedMinLength(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
        return basicSetOwnedMaxCard(null, msgs);
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
        return basicSetOwnedMaxLength(null, msgs);
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
        return ((InternalEList<?>)getOwnedPropertyValuePkgs()).basicRemove(otherEnd, msgs);
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
        return ((InternalEList<?>)getOwnedDataValues()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.COLLECTION__ORDERED:
        return isOrdered();
      case InformationPackage.COLLECTION__UNIQUE:
        return isUnique();
      case InformationPackage.COLLECTION__MIN_INCLUSIVE:
        return isMinInclusive();
      case InformationPackage.COLLECTION__MAX_INCLUSIVE:
        return isMaxInclusive();
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
        return getOwnedDefaultValue();
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
        return getOwnedMinValue();
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
        return getOwnedMaxValue();
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
        return getOwnedNullValue();
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
        return getOwnedMinCard();
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
        return getOwnedMinLength();
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
        return getOwnedMaxCard();
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
        return getOwnedMaxLength();
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
        return getOwnedPropertyValuePkgs();
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
        return getOwnedDataValues();
      case InformationPackage.COLLECTION__FINAL:
        return isFinal();
      case InformationPackage.COLLECTION__IS_PRIMITIVE:
        return isIsPrimitive();
      case InformationPackage.COLLECTION__VISIBILITY:
        return getVisibility();
      case InformationPackage.COLLECTION__KIND:
        return getKind();
      case InformationPackage.COLLECTION__AGGREGATION_KIND:
        return getAggregationKind();
      case InformationPackage.COLLECTION__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case InformationPackage.COLLECTION__INDEX:
        return getIndex();
      case InformationPackage.COLLECTION__CONTAINED_OPERATIONS:
        return getContainedOperations();
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
      case InformationPackage.COLLECTION__ORDERED:
          setOrdered((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__UNIQUE:
          setUnique((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__MIN_INCLUSIVE:
          setMinInclusive((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__MAX_INCLUSIVE:
          setMaxInclusive((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((DataValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
          setOwnedMinValue((DataValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
          setOwnedMaxValue((DataValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
          setOwnedNullValue((DataValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
          setOwnedMinCard((NumericValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
          setOwnedMinLength((NumericValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
          setOwnedMaxCard((NumericValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
          setOwnedMaxLength((NumericValue)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        getOwnedPropertyValuePkgs().addAll((java.util.Collection<? extends PropertyValuePkg>)newValue);
        return;
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
        getOwnedDataValues().clear();
        getOwnedDataValues().addAll((java.util.Collection<? extends DataValue>)newValue);
        return;
      case InformationPackage.COLLECTION__FINAL:
          setFinal((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__IS_PRIMITIVE:
          setIsPrimitive((Boolean)newValue);
        return;
      case InformationPackage.COLLECTION__VISIBILITY:
          setVisibility((VisibilityKind)newValue);
        return;
      case InformationPackage.COLLECTION__KIND:
          setKind((CollectionKind)newValue);
        return;
      case InformationPackage.COLLECTION__AGGREGATION_KIND:
          setAggregationKind((AggregationKind)newValue);
        return;
      case InformationPackage.COLLECTION__TYPE:
          setType((Type)newValue);
        return;
      case InformationPackage.COLLECTION__INDEX:
        getIndex().clear();
        getIndex().addAll((java.util.Collection<? extends DataType>)newValue);
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
      case InformationPackage.COLLECTION__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__MIN_INCLUSIVE:
        setMinInclusive(MIN_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__MAX_INCLUSIVE:
        setMaxInclusive(MAX_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((DataValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
        setOwnedMinValue((DataValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
        setOwnedMaxValue((DataValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
        setOwnedNullValue((DataValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
        setOwnedMinCard((NumericValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
        setOwnedMinLength((NumericValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
        setOwnedMaxCard((NumericValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
        setOwnedMaxLength((NumericValue)null);
        return;
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
        getOwnedPropertyValuePkgs().clear();
        return;
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
        getOwnedDataValues().clear();
        return;
      case InformationPackage.COLLECTION__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__IS_PRIMITIVE:
        setIsPrimitive(IS_PRIMITIVE_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__AGGREGATION_KIND:
        setAggregationKind(AGGREGATION_KIND_EDEFAULT);
        return;
      case InformationPackage.COLLECTION__TYPE:
        setType((Type)null);
        return;
      case InformationPackage.COLLECTION__INDEX:
        getIndex().clear();
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
      case InformationPackage.COLLECTION__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case InformationPackage.COLLECTION__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case InformationPackage.COLLECTION__MIN_INCLUSIVE:
        return minInclusive != MIN_INCLUSIVE_EDEFAULT;
      case InformationPackage.COLLECTION__MAX_INCLUSIVE:
        return maxInclusive != MAX_INCLUSIVE_EDEFAULT;
      case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
      case InformationPackage.COLLECTION__OWNED_MIN_VALUE:
        return ownedMinValue != null;
      case InformationPackage.COLLECTION__OWNED_MAX_VALUE:
        return ownedMaxValue != null;
      case InformationPackage.COLLECTION__OWNED_NULL_VALUE:
        return ownedNullValue != null;
      case InformationPackage.COLLECTION__OWNED_MIN_CARD:
        return ownedMinCard != null;
      case InformationPackage.COLLECTION__OWNED_MIN_LENGTH:
        return ownedMinLength != null;
      case InformationPackage.COLLECTION__OWNED_MAX_CARD:
        return ownedMaxCard != null;
      case InformationPackage.COLLECTION__OWNED_MAX_LENGTH:
        return ownedMaxLength != null;
      case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS:
        return ownedPropertyValuePkgs != null && !ownedPropertyValuePkgs.isEmpty();
      case InformationPackage.COLLECTION__OWNED_DATA_VALUES:
        return ownedDataValues != null && !ownedDataValues.isEmpty();
      case InformationPackage.COLLECTION__FINAL:
        return final_ != FINAL_EDEFAULT;
      case InformationPackage.COLLECTION__IS_PRIMITIVE:
        return isPrimitive != IS_PRIMITIVE_EDEFAULT;
      case InformationPackage.COLLECTION__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case InformationPackage.COLLECTION__KIND:
        return kind != KIND_EDEFAULT;
      case InformationPackage.COLLECTION__AGGREGATION_KIND:
        return aggregationKind != AGGREGATION_KIND_EDEFAULT;
      case InformationPackage.COLLECTION__TYPE:
        return type != null;
      case InformationPackage.COLLECTION__INDEX:
        return index != null && !index.isEmpty();
      case InformationPackage.COLLECTION__CONTAINED_OPERATIONS:
        return !getContainedOperations().isEmpty();
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
        case InformationPackage.COLLECTION__ORDERED: return InformationPackage.MULTIPLICITY_ELEMENT__ORDERED;
        case InformationPackage.COLLECTION__UNIQUE: return InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE;
        case InformationPackage.COLLECTION__MIN_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE;
        case InformationPackage.COLLECTION__MAX_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE;
        case InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE;
        case InformationPackage.COLLECTION__OWNED_MIN_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE;
        case InformationPackage.COLLECTION__OWNED_MAX_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE;
        case InformationPackage.COLLECTION__OWNED_NULL_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE;
        case InformationPackage.COLLECTION__OWNED_MIN_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD;
        case InformationPackage.COLLECTION__OWNED_MIN_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH;
        case InformationPackage.COLLECTION__OWNED_MAX_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD;
        case InformationPackage.COLLECTION__OWNED_MAX_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == Structure.class) {
      switch (derivedFeatureID) {
        case InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS: return CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS;
        default: return -1;
      }
    }
    if (baseClass == DataValueContainer.class) {
      switch (derivedFeatureID) {
        case InformationPackage.COLLECTION__OWNED_DATA_VALUES: return DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (derivedFeatureID) {
        case InformationPackage.COLLECTION__FINAL: return ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL;
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
        case InformationPackage.MULTIPLICITY_ELEMENT__ORDERED: return InformationPackage.COLLECTION__ORDERED;
        case InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE: return InformationPackage.COLLECTION__UNIQUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE: return InformationPackage.COLLECTION__MIN_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE: return InformationPackage.COLLECTION__MAX_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE: return InformationPackage.COLLECTION__OWNED_DEFAULT_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE: return InformationPackage.COLLECTION__OWNED_MIN_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE: return InformationPackage.COLLECTION__OWNED_MAX_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE: return InformationPackage.COLLECTION__OWNED_NULL_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD: return InformationPackage.COLLECTION__OWNED_MIN_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH: return InformationPackage.COLLECTION__OWNED_MIN_LENGTH;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD: return InformationPackage.COLLECTION__OWNED_MAX_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH: return InformationPackage.COLLECTION__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == Structure.class) {
      switch (baseFeatureID) {
        case CapellacorePackage.STRUCTURE__OWNED_PROPERTY_VALUE_PKGS: return InformationPackage.COLLECTION__OWNED_PROPERTY_VALUE_PKGS;
        default: return -1;
      }
    }
    if (baseClass == DataValueContainer.class) {
      switch (baseFeatureID) {
        case DatavaluePackage.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES: return InformationPackage.COLLECTION__OWNED_DATA_VALUES;
        default: return -1;
      }
    }
    if (baseClass == FinalizableElement.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.FINALIZABLE_ELEMENT__FINAL: return InformationPackage.COLLECTION__FINAL;
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
    result.append(", final: "); //$NON-NLS-1$
    result.append(final_);
    result.append(", isPrimitive: "); //$NON-NLS-1$
    result.append(isPrimitive);
    result.append(", visibility: "); //$NON-NLS-1$
    result.append(visibility);
    result.append(", kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", aggregationKind: "); //$NON-NLS-1$
    result.append(aggregationKind);
    result.append(')');
    return result.toString();
  }
} //CollectionImpl
