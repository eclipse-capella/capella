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
package org.polarsys.capella.common.data.modellingcore.impl;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#isIsException <em>Is Exception</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#isIsStream <em>Is Stream</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#isIsOptional <em>Is Optional</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#getKindOfRate <em>Kind Of Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#getEffect <em>Effect</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#getRate <em>Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterImpl#getParameterSet <em>Parameter Set</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractParameterImpl extends AbstractTypedElementImpl implements AbstractParameter {

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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractParameterImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ModellingcorePackage.Literals.ABSTRACT_PARAMETER;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION, oldIsException, isException));

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
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM, oldIsStream, isStream));

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
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL, oldIsOptional, isOptional));

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
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE, oldKindOfRate, kindOfRate));

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
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT, oldEffect, effect));

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
        NotificationChain msgs = oldRate.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__RATE, null, null);
        if (newRate.eInternalContainer() == null) {
          msgs = newRate.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__RATE, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_PARAMETER__RATE, oldRate, rate));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__RATE, oldRate, newRate);
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
        msgs = ((InternalEObject)rate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__RATE, null, msgs);
      if (newRate != null)
        msgs = ((InternalEObject)newRate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__RATE, null, msgs);
      msgs = basicSetRate(newRate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__RATE, newRate, newRate));

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
        NotificationChain msgs = oldProbability.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, null, null);
        if (newProbability.eInternalContainer() == null) {
          msgs = newProbability.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, oldProbability, probability));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, oldProbability, newProbability);
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
        msgs = ((InternalEObject)probability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, null, msgs);
      if (newProbability != null)
        msgs = ((InternalEObject)newProbability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, null, msgs);
      msgs = basicSetProbability(newProbability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY, newProbability, newProbability));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameterSet> getParameterSet() {

    if (parameterSet == null) {
      parameterSet = new EObjectWithInverseResolvingEList.ManyInverse<AbstractParameterSet>(AbstractParameterSet.class, this, ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS);
    }
    return parameterSet;
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__RATE:
        return basicSetRate(null, msgs);
      case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY:
        return basicSetProbability(null, msgs);
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION:
        return isIsException();
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM:
        return isIsStream();
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL:
        return isIsOptional();
      case ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE:
        return getKindOfRate();
      case ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT:
        return getEffect();
      case ModellingcorePackage.ABSTRACT_PARAMETER__RATE:
        if (resolve) return getRate();
        return basicGetRate();
      case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY:
        if (resolve) return getProbability();
        return basicGetProbability();
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
        return getParameterSet();
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION:
          setIsException((Boolean)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM:
          setIsStream((Boolean)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL:
          setIsOptional((Boolean)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE:
          setKindOfRate((RateKind)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT:
          setEffect((ParameterEffectKind)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__RATE:
          setRate((ValueSpecification)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY:
          setProbability((ValueSpecification)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
        getParameterSet().clear();
        getParameterSet().addAll((Collection<? extends AbstractParameterSet>)newValue);
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION:
        setIsException(IS_EXCEPTION_EDEFAULT);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM:
        setIsStream(IS_STREAM_EDEFAULT);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL:
        setIsOptional(IS_OPTIONAL_EDEFAULT);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE:
        setKindOfRate(KIND_OF_RATE_EDEFAULT);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT:
        setEffect(EFFECT_EDEFAULT);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__RATE:
        setRate((ValueSpecification)null);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY:
        setProbability((ValueSpecification)null);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
        getParameterSet().clear();
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
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_EXCEPTION:
        return isException != IS_EXCEPTION_EDEFAULT;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_STREAM:
        return isStream != IS_STREAM_EDEFAULT;
      case ModellingcorePackage.ABSTRACT_PARAMETER__IS_OPTIONAL:
        return isOptional != IS_OPTIONAL_EDEFAULT;
      case ModellingcorePackage.ABSTRACT_PARAMETER__KIND_OF_RATE:
        return kindOfRate != KIND_OF_RATE_EDEFAULT;
      case ModellingcorePackage.ABSTRACT_PARAMETER__EFFECT:
        return effect != EFFECT_EDEFAULT;
      case ModellingcorePackage.ABSTRACT_PARAMETER__RATE:
        return rate != null;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PROBABILITY:
        return probability != null;
      case ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET:
        return parameterSet != null && !parameterSet.isEmpty();
    }
    return super.eIsSet(featureID);
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
    result.append(" (isException: "); //$NON-NLS-1$
    result.append(isException);
    result.append(", isStream: "); //$NON-NLS-1$
    result.append(isStream);
    result.append(", isOptional: "); //$NON-NLS-1$
    result.append(isOptional);
    result.append(", kindOfRate: "); //$NON-NLS-1$
    result.append(kindOfRate);
    result.append(", effect: "); //$NON-NLS-1$
    result.append(effect);
    result.append(')');
    return result.toString();
  }


} //AbstractParameterImpl