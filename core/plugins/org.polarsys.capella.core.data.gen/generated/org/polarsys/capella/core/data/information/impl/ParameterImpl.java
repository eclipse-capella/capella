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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.ParameterEffectKind;
import org.polarsys.capella.common.data.modellingcore.RateKind;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.core.data.capellacore.impl.TypedElementImpl;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.PassingMode;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isMinInclusive <em>Min Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isMaxInclusive <em>Max Inclusive</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedDefaultValue <em>Owned Default Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMinValue <em>Owned Min Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMaxValue <em>Owned Max Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedNullValue <em>Owned Null Value</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMinCard <em>Owned Min Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMinLength <em>Owned Min Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMaxCard <em>Owned Max Card</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getOwnedMaxLength <em>Owned Max Length</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isIsException <em>Is Exception</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isIsStream <em>Is Stream</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#isIsOptional <em>Is Optional</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getKindOfRate <em>Kind Of Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getEffect <em>Effect</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getParameterSet <em>Parameter Set</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.ParameterImpl#getPassingMode <em>Passing Mode</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {

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
   * The default value of the '{@link #isIsException() <em>Is Exception</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsException()
   * @generated
   * @ordered
   */
	protected static final boolean IS_EXCEPTION_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsException() <em>Is Exception</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsException()
   * @generated
   * @ordered
   */
	protected boolean isException = IS_EXCEPTION_EDEFAULT;





	/**
   * The default value of the '{@link #isIsStream() <em>Is Stream</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStream()
   * @generated
   * @ordered
   */
	protected static final boolean IS_STREAM_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsStream() <em>Is Stream</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStream()
   * @generated
   * @ordered
   */
	protected boolean isStream = IS_STREAM_EDEFAULT;





	/**
   * The default value of the '{@link #isIsOptional() <em>Is Optional</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsOptional()
   * @generated
   * @ordered
   */
	protected static final boolean IS_OPTIONAL_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsOptional() <em>Is Optional</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsOptional()
   * @generated
   * @ordered
   */
	protected boolean isOptional = IS_OPTIONAL_EDEFAULT;





	/**
   * The default value of the '{@link #getKindOfRate() <em>Kind Of Rate</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKindOfRate()
   * @generated
   * @ordered
   */
	protected static final RateKind KIND_OF_RATE_EDEFAULT = RateKind.UNSPECIFIED;

	/**
   * The cached value of the '{@link #getKindOfRate() <em>Kind Of Rate</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getKindOfRate()
   * @generated
   * @ordered
   */
	protected RateKind kindOfRate = KIND_OF_RATE_EDEFAULT;





	/**
   * The default value of the '{@link #getEffect() <em>Effect</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEffect()
   * @generated
   * @ordered
   */
	protected static final ParameterEffectKind EFFECT_EDEFAULT = ParameterEffectKind.CREATE;

	/**
   * The cached value of the '{@link #getEffect() <em>Effect</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEffect()
   * @generated
   * @ordered
   */
	protected ParameterEffectKind effect = EFFECT_EDEFAULT;





	/**
   * The cached value of the '{@link #getRate() <em>Rate</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRate()
   * @generated
   * @ordered
   */
	protected ValueSpecification rate;





	/**
   * The cached value of the '{@link #getProbability() <em>Probability</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getProbability()
   * @generated
   * @ordered
   */
	protected ValueSpecification probability;





	/**
   * The cached value of the '{@link #getParameterSet() <em>Parameter Set</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getParameterSet()
   * @generated
   * @ordered
   */
	protected EList<AbstractParameterSet> parameterSet;





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
   * The default value of the '{@link #getPassingMode() <em>Passing Mode</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPassingMode()
   * @generated
   * @ordered
   */
	protected static final PassingMode PASSING_MODE_EDEFAULT = PassingMode.UNSET;

	/**
   * The cached value of the '{@link #getPassingMode() <em>Passing Mode</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPassingMode()
   * @generated
   * @ordered
   */
	protected PassingMode passingMode = PASSING_MODE_EDEFAULT;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ParameterImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.PARAMETER;
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__ORDERED, oldOrdered, ordered));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__UNIQUE, oldUnique, unique));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__MIN_INCLUSIVE, oldMinInclusive, minInclusive));

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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__MAX_INCLUSIVE, oldMaxInclusive, maxInclusive));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE, oldOwnedDefaultValue, newOwnedDefaultValue);
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
        msgs = ((InternalEObject)ownedDefaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE, null, msgs);
      if (newOwnedDefaultValue != null)
        msgs = ((InternalEObject)newOwnedDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE, null, msgs);
      msgs = basicSetOwnedDefaultValue(newOwnedDefaultValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE, newOwnedDefaultValue, newOwnedDefaultValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_VALUE, oldOwnedMinValue, newOwnedMinValue);
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
        msgs = ((InternalEObject)ownedMinValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_VALUE, null, msgs);
      if (newOwnedMinValue != null)
        msgs = ((InternalEObject)newOwnedMinValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_VALUE, null, msgs);
      msgs = basicSetOwnedMinValue(newOwnedMinValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_VALUE, newOwnedMinValue, newOwnedMinValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_VALUE, oldOwnedMaxValue, newOwnedMaxValue);
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
        msgs = ((InternalEObject)ownedMaxValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_VALUE, null, msgs);
      if (newOwnedMaxValue != null)
        msgs = ((InternalEObject)newOwnedMaxValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_VALUE, null, msgs);
      msgs = basicSetOwnedMaxValue(newOwnedMaxValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_VALUE, newOwnedMaxValue, newOwnedMaxValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_NULL_VALUE, oldOwnedNullValue, newOwnedNullValue);
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
        msgs = ((InternalEObject)ownedNullValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_NULL_VALUE, null, msgs);
      if (newOwnedNullValue != null)
        msgs = ((InternalEObject)newOwnedNullValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_NULL_VALUE, null, msgs);
      msgs = basicSetOwnedNullValue(newOwnedNullValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_NULL_VALUE, newOwnedNullValue, newOwnedNullValue));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_CARD, oldOwnedMinCard, newOwnedMinCard);
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
        msgs = ((InternalEObject)ownedMinCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_CARD, null, msgs);
      if (newOwnedMinCard != null)
        msgs = ((InternalEObject)newOwnedMinCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_CARD, null, msgs);
      msgs = basicSetOwnedMinCard(newOwnedMinCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_CARD, newOwnedMinCard, newOwnedMinCard));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_LENGTH, oldOwnedMinLength, newOwnedMinLength);
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
        msgs = ((InternalEObject)ownedMinLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_LENGTH, null, msgs);
      if (newOwnedMinLength != null)
        msgs = ((InternalEObject)newOwnedMinLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MIN_LENGTH, null, msgs);
      msgs = basicSetOwnedMinLength(newOwnedMinLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MIN_LENGTH, newOwnedMinLength, newOwnedMinLength));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_CARD, oldOwnedMaxCard, newOwnedMaxCard);
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
        msgs = ((InternalEObject)ownedMaxCard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_CARD, null, msgs);
      if (newOwnedMaxCard != null)
        msgs = ((InternalEObject)newOwnedMaxCard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_CARD, null, msgs);
      msgs = basicSetOwnedMaxCard(newOwnedMaxCard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_CARD, newOwnedMaxCard, newOwnedMaxCard));

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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_LENGTH, oldOwnedMaxLength, newOwnedMaxLength);
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
        msgs = ((InternalEObject)ownedMaxLength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_LENGTH, null, msgs);
      if (newOwnedMaxLength != null)
        msgs = ((InternalEObject)newOwnedMaxLength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__OWNED_MAX_LENGTH, null, msgs);
      msgs = basicSetOwnedMaxLength(newOwnedMaxLength, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__OWNED_MAX_LENGTH, newOwnedMaxLength, newOwnedMaxLength));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsException() {

    return isException;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsException(boolean newIsException) {

    boolean oldIsException = isException;
    isException = newIsException;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__IS_EXCEPTION, oldIsException, isException));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsStream() {

    return isStream;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsStream(boolean newIsStream) {

    boolean oldIsStream = isStream;
    isStream = newIsStream;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__IS_STREAM, oldIsStream, isStream));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsOptional() {

    return isOptional;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsOptional(boolean newIsOptional) {

    boolean oldIsOptional = isOptional;
    isOptional = newIsOptional;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__IS_OPTIONAL, oldIsOptional, isOptional));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public RateKind getKindOfRate() {

    return kindOfRate;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setKindOfRate(RateKind newKindOfRate) {

    RateKind oldKindOfRate = kindOfRate;
    kindOfRate = newKindOfRate == null ? KIND_OF_RATE_EDEFAULT : newKindOfRate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__KIND_OF_RATE, oldKindOfRate, kindOfRate));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ParameterEffectKind getEffect() {

    return effect;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setEffect(ParameterEffectKind newEffect) {

    ParameterEffectKind oldEffect = effect;
    effect = newEffect == null ? EFFECT_EDEFAULT : newEffect;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__EFFECT, oldEffect, effect));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getRate() {

    if (rate != null && rate.eIsProxy()) {
      InternalEObject oldRate = (InternalEObject)rate;
      rate = (ValueSpecification)eResolveProxy(oldRate);
      if (rate != oldRate) {
        InternalEObject newRate = (InternalEObject)rate;
        NotificationChain msgs = oldRate.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__RATE, null, null);
        if (newRate.eInternalContainer() == null) {
          msgs = newRate.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__RATE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.PARAMETER__RATE, oldRate, rate));
      }
    }
    return rate;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetRate() {

    return rate;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetRate(ValueSpecification newRate, NotificationChain msgs) {

    ValueSpecification oldRate = rate;
    rate = newRate;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__RATE, oldRate, newRate);
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
	public void setRate(ValueSpecification newRate) {

    if (newRate != rate) {
      NotificationChain msgs = null;
      if (rate != null)
        msgs = ((InternalEObject)rate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__RATE, null, msgs);
      if (newRate != null)
        msgs = ((InternalEObject)newRate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__RATE, null, msgs);
      msgs = basicSetRate(newRate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__RATE, newRate, newRate));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification getProbability() {

    if (probability != null && probability.eIsProxy()) {
      InternalEObject oldProbability = (InternalEObject)probability;
      probability = (ValueSpecification)eResolveProxy(oldProbability);
      if (probability != oldProbability) {
        InternalEObject newProbability = (InternalEObject)probability;
        NotificationChain msgs = oldProbability.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__PROBABILITY, null, null);
        if (newProbability.eInternalContainer() == null) {
          msgs = newProbability.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__PROBABILITY, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationPackage.PARAMETER__PROBABILITY, oldProbability, probability));
      }
    }
    return probability;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ValueSpecification basicGetProbability() {

    return probability;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetProbability(ValueSpecification newProbability, NotificationChain msgs) {

    ValueSpecification oldProbability = probability;
    probability = newProbability;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__PROBABILITY, oldProbability, newProbability);
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
	public void setProbability(ValueSpecification newProbability) {

    if (newProbability != probability) {
      NotificationChain msgs = null;
      if (probability != null)
        msgs = ((InternalEObject)probability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__PROBABILITY, null, msgs);
      if (newProbability != null)
        msgs = ((InternalEObject)newProbability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationPackage.PARAMETER__PROBABILITY, null, msgs);
      msgs = basicSetProbability(newProbability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__PROBABILITY, newProbability, newProbability));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameterSet> getParameterSet() {

    if (parameterSet == null) {
      parameterSet = new EObjectWithInverseResolvingEList.ManyInverse<AbstractParameterSet>(AbstractParameterSet.class, this, InformationPackage.PARAMETER__PARAMETER_SET, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS);
    }
    return parameterSet;
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
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__DIRECTION, oldDirection, direction));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public PassingMode getPassingMode() {

    return passingMode;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setPassingMode(PassingMode newPassingMode) {

    PassingMode oldPassingMode = passingMode;
    passingMode = newPassingMode == null ? PASSING_MODE_EDEFAULT : newPassingMode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, InformationPackage.PARAMETER__PASSING_MODE, oldPassingMode, passingMode));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case InformationPackage.PARAMETER__PARAMETER_SET:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameterSet()).basicAdd(otherEnd, msgs);
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
      case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE:
        return basicSetOwnedDefaultValue(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MIN_VALUE:
        return basicSetOwnedMinValue(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MAX_VALUE:
        return basicSetOwnedMaxValue(null, msgs);
      case InformationPackage.PARAMETER__OWNED_NULL_VALUE:
        return basicSetOwnedNullValue(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MIN_CARD:
        return basicSetOwnedMinCard(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MIN_LENGTH:
        return basicSetOwnedMinLength(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MAX_CARD:
        return basicSetOwnedMaxCard(null, msgs);
      case InformationPackage.PARAMETER__OWNED_MAX_LENGTH:
        return basicSetOwnedMaxLength(null, msgs);
      case InformationPackage.PARAMETER__RATE:
        return basicSetRate(null, msgs);
      case InformationPackage.PARAMETER__PROBABILITY:
        return basicSetProbability(null, msgs);
      case InformationPackage.PARAMETER__PARAMETER_SET:
        return ((InternalEList<?>)getParameterSet()).basicRemove(otherEnd, msgs);
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
      case InformationPackage.PARAMETER__ORDERED:
        return isOrdered();
      case InformationPackage.PARAMETER__UNIQUE:
        return isUnique();
      case InformationPackage.PARAMETER__MIN_INCLUSIVE:
        return isMinInclusive();
      case InformationPackage.PARAMETER__MAX_INCLUSIVE:
        return isMaxInclusive();
      case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE:
        return getOwnedDefaultValue();
      case InformationPackage.PARAMETER__OWNED_MIN_VALUE:
        return getOwnedMinValue();
      case InformationPackage.PARAMETER__OWNED_MAX_VALUE:
        return getOwnedMaxValue();
      case InformationPackage.PARAMETER__OWNED_NULL_VALUE:
        return getOwnedNullValue();
      case InformationPackage.PARAMETER__OWNED_MIN_CARD:
        return getOwnedMinCard();
      case InformationPackage.PARAMETER__OWNED_MIN_LENGTH:
        return getOwnedMinLength();
      case InformationPackage.PARAMETER__OWNED_MAX_CARD:
        return getOwnedMaxCard();
      case InformationPackage.PARAMETER__OWNED_MAX_LENGTH:
        return getOwnedMaxLength();
      case InformationPackage.PARAMETER__IS_EXCEPTION:
        return isIsException();
      case InformationPackage.PARAMETER__IS_STREAM:
        return isIsStream();
      case InformationPackage.PARAMETER__IS_OPTIONAL:
        return isIsOptional();
      case InformationPackage.PARAMETER__KIND_OF_RATE:
        return getKindOfRate();
      case InformationPackage.PARAMETER__EFFECT:
        return getEffect();
      case InformationPackage.PARAMETER__RATE:
        if (resolve) return getRate();
        return basicGetRate();
      case InformationPackage.PARAMETER__PROBABILITY:
        if (resolve) return getProbability();
        return basicGetProbability();
      case InformationPackage.PARAMETER__PARAMETER_SET:
        return getParameterSet();
      case InformationPackage.PARAMETER__DIRECTION:
        return getDirection();
      case InformationPackage.PARAMETER__PASSING_MODE:
        return getPassingMode();
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
      case InformationPackage.PARAMETER__ORDERED:
          setOrdered((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__UNIQUE:
          setUnique((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__MIN_INCLUSIVE:
          setMinInclusive((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__MAX_INCLUSIVE:
          setMaxInclusive((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE:
          setOwnedDefaultValue((DataValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_VALUE:
          setOwnedMinValue((DataValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_VALUE:
          setOwnedMaxValue((DataValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_NULL_VALUE:
          setOwnedNullValue((DataValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_CARD:
          setOwnedMinCard((NumericValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_LENGTH:
          setOwnedMinLength((NumericValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_CARD:
          setOwnedMaxCard((NumericValue)newValue);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_LENGTH:
          setOwnedMaxLength((NumericValue)newValue);
        return;
      case InformationPackage.PARAMETER__IS_EXCEPTION:
          setIsException((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__IS_STREAM:
          setIsStream((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__IS_OPTIONAL:
          setIsOptional((Boolean)newValue);
        return;
      case InformationPackage.PARAMETER__KIND_OF_RATE:
          setKindOfRate((RateKind)newValue);
        return;
      case InformationPackage.PARAMETER__EFFECT:
          setEffect((ParameterEffectKind)newValue);
        return;
      case InformationPackage.PARAMETER__RATE:
          setRate((ValueSpecification)newValue);
        return;
      case InformationPackage.PARAMETER__PROBABILITY:
          setProbability((ValueSpecification)newValue);
        return;
      case InformationPackage.PARAMETER__PARAMETER_SET:
        getParameterSet().clear();
        getParameterSet().addAll((Collection<? extends AbstractParameterSet>)newValue);
        return;
      case InformationPackage.PARAMETER__DIRECTION:
          setDirection((ParameterDirection)newValue);
        return;
      case InformationPackage.PARAMETER__PASSING_MODE:
          setPassingMode((PassingMode)newValue);
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
      case InformationPackage.PARAMETER__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__MIN_INCLUSIVE:
        setMinInclusive(MIN_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__MAX_INCLUSIVE:
        setMaxInclusive(MAX_INCLUSIVE_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE:
        setOwnedDefaultValue((DataValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_VALUE:
        setOwnedMinValue((DataValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_VALUE:
        setOwnedMaxValue((DataValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_NULL_VALUE:
        setOwnedNullValue((DataValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_CARD:
        setOwnedMinCard((NumericValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MIN_LENGTH:
        setOwnedMinLength((NumericValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_CARD:
        setOwnedMaxCard((NumericValue)null);
        return;
      case InformationPackage.PARAMETER__OWNED_MAX_LENGTH:
        setOwnedMaxLength((NumericValue)null);
        return;
      case InformationPackage.PARAMETER__IS_EXCEPTION:
        setIsException(IS_EXCEPTION_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__IS_STREAM:
        setIsStream(IS_STREAM_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__IS_OPTIONAL:
        setIsOptional(IS_OPTIONAL_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__KIND_OF_RATE:
        setKindOfRate(KIND_OF_RATE_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__EFFECT:
        setEffect(EFFECT_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__RATE:
        setRate((ValueSpecification)null);
        return;
      case InformationPackage.PARAMETER__PROBABILITY:
        setProbability((ValueSpecification)null);
        return;
      case InformationPackage.PARAMETER__PARAMETER_SET:
        getParameterSet().clear();
        return;
      case InformationPackage.PARAMETER__DIRECTION:
        setDirection(DIRECTION_EDEFAULT);
        return;
      case InformationPackage.PARAMETER__PASSING_MODE:
        setPassingMode(PASSING_MODE_EDEFAULT);
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
      case InformationPackage.PARAMETER__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case InformationPackage.PARAMETER__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case InformationPackage.PARAMETER__MIN_INCLUSIVE:
        return minInclusive != MIN_INCLUSIVE_EDEFAULT;
      case InformationPackage.PARAMETER__MAX_INCLUSIVE:
        return maxInclusive != MAX_INCLUSIVE_EDEFAULT;
      case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE:
        return ownedDefaultValue != null;
      case InformationPackage.PARAMETER__OWNED_MIN_VALUE:
        return ownedMinValue != null;
      case InformationPackage.PARAMETER__OWNED_MAX_VALUE:
        return ownedMaxValue != null;
      case InformationPackage.PARAMETER__OWNED_NULL_VALUE:
        return ownedNullValue != null;
      case InformationPackage.PARAMETER__OWNED_MIN_CARD:
        return ownedMinCard != null;
      case InformationPackage.PARAMETER__OWNED_MIN_LENGTH:
        return ownedMinLength != null;
      case InformationPackage.PARAMETER__OWNED_MAX_CARD:
        return ownedMaxCard != null;
      case InformationPackage.PARAMETER__OWNED_MAX_LENGTH:
        return ownedMaxLength != null;
      case InformationPackage.PARAMETER__IS_EXCEPTION:
        return isException != IS_EXCEPTION_EDEFAULT;
      case InformationPackage.PARAMETER__IS_STREAM:
        return isStream != IS_STREAM_EDEFAULT;
      case InformationPackage.PARAMETER__IS_OPTIONAL:
        return isOptional != IS_OPTIONAL_EDEFAULT;
      case InformationPackage.PARAMETER__KIND_OF_RATE:
        return kindOfRate != KIND_OF_RATE_EDEFAULT;
      case InformationPackage.PARAMETER__EFFECT:
        return effect != EFFECT_EDEFAULT;
      case InformationPackage.PARAMETER__RATE:
        return rate != null;
      case InformationPackage.PARAMETER__PROBABILITY:
        return probability != null;
      case InformationPackage.PARAMETER__PARAMETER_SET:
        return parameterSet != null && !parameterSet.isEmpty();
      case InformationPackage.PARAMETER__DIRECTION:
        return direction != DIRECTION_EDEFAULT;
      case InformationPackage.PARAMETER__PASSING_MODE:
        return passingMode != PASSING_MODE_EDEFAULT;
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
        case InformationPackage.PARAMETER__ORDERED: return InformationPackage.MULTIPLICITY_ELEMENT__ORDERED;
        case InformationPackage.PARAMETER__UNIQUE: return InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE;
        case InformationPackage.PARAMETER__MIN_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE;
        case InformationPackage.PARAMETER__MAX_INCLUSIVE: return InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE;
        case InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE;
        case InformationPackage.PARAMETER__OWNED_MIN_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE;
        case InformationPackage.PARAMETER__OWNED_MAX_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE;
        case InformationPackage.PARAMETER__OWNED_NULL_VALUE: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE;
        case InformationPackage.PARAMETER__OWNED_MIN_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD;
        case InformationPackage.PARAMETER__OWNED_MIN_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH;
        case InformationPackage.PARAMETER__OWNED_MAX_CARD: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD;
        case InformationPackage.PARAMETER__OWNED_MAX_LENGTH: return InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == AbstractParameter.class) {
      switch (derivedFeatureID) {
        case InformationPackage.PARAMETER__IS_EXCEPTION: return ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION;
        case InformationPackage.PARAMETER__IS_STREAM: return ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM;
        case InformationPackage.PARAMETER__IS_OPTIONAL: return ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL;
        case InformationPackage.PARAMETER__KIND_OF_RATE: return ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE;
        case InformationPackage.PARAMETER__EFFECT: return ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT;
        case InformationPackage.PARAMETER__RATE: return ModellingcorePackage.ABSTRACT_PARAMETER__RATE;
        case InformationPackage.PARAMETER__PROBABILITY: return ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY;
        case InformationPackage.PARAMETER__PARAMETER_SET: return ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET;
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
        case InformationPackage.MULTIPLICITY_ELEMENT__ORDERED: return InformationPackage.PARAMETER__ORDERED;
        case InformationPackage.MULTIPLICITY_ELEMENT__UNIQUE: return InformationPackage.PARAMETER__UNIQUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MIN_INCLUSIVE: return InformationPackage.PARAMETER__MIN_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__MAX_INCLUSIVE: return InformationPackage.PARAMETER__MAX_INCLUSIVE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE: return InformationPackage.PARAMETER__OWNED_DEFAULT_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE: return InformationPackage.PARAMETER__OWNED_MIN_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE: return InformationPackage.PARAMETER__OWNED_MAX_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE: return InformationPackage.PARAMETER__OWNED_NULL_VALUE;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD: return InformationPackage.PARAMETER__OWNED_MIN_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH: return InformationPackage.PARAMETER__OWNED_MIN_LENGTH;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD: return InformationPackage.PARAMETER__OWNED_MAX_CARD;
        case InformationPackage.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH: return InformationPackage.PARAMETER__OWNED_MAX_LENGTH;
        default: return -1;
      }
    }
    if (baseClass == AbstractParameter.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION: return InformationPackage.PARAMETER__IS_EXCEPTION;
        case ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM: return InformationPackage.PARAMETER__IS_STREAM;
        case ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL: return InformationPackage.PARAMETER__IS_OPTIONAL;
        case ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE: return InformationPackage.PARAMETER__KIND_OF_RATE;
        case ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT: return InformationPackage.PARAMETER__EFFECT;
        case ModellingcorePackage.ABSTRACT_PARAMETER__RATE: return InformationPackage.PARAMETER__RATE;
        case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY: return InformationPackage.PARAMETER__PROBABILITY;
        case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET: return InformationPackage.PARAMETER__PARAMETER_SET;
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
    result.append(", isException: "); //$NON-NLS-1$
    result.append(isException);
    result.append(", isStream: "); //$NON-NLS-1$
    result.append(isStream);
    result.append(", isOptional: "); //$NON-NLS-1$
    result.append(isOptional);
    result.append(", kindOfRate: "); //$NON-NLS-1$
    result.append(kindOfRate);
    result.append(", effect: "); //$NON-NLS-1$
    result.append(effect);
    result.append(", direction: "); //$NON-NLS-1$
    result.append(direction);
    result.append(", passingMode: "); //$NON-NLS-1$
    result.append(passingMode);
    result.append(')');
    return result.toString();
  }


} //ParameterImpl