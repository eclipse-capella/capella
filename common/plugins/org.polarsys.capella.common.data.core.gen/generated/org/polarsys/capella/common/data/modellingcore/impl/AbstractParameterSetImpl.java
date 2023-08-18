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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Parameter Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl#getOwnedConditions <em>Owned Conditions</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractParameterSetImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractParameterSetImpl extends AbstractNamedElementImpl implements AbstractParameterSet {

	/**
   * The cached value of the '{@link #getOwnedConditions() <em>Owned Conditions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedConditions()
   * @generated
   * @ordered
   */
	protected EList<AbstractConstraint> ownedConditions;





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
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
	protected EList<AbstractParameter> parameters;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractParameterSetImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ModellingcorePackage.Literals.ABSTRACT_PARAMETER_SET;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractConstraint> getOwnedConditions() {

    if (ownedConditions == null) {
      ownedConditions = new EObjectContainmentEList.Resolving<AbstractConstraint>(AbstractConstraint.class, this, ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS);
    }
    return ownedConditions;
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
        NotificationChain msgs = oldProbability.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, null, null);
        if (newProbability.eInternalContainer() == null) {
          msgs = newProbability.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, oldProbability, probability));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, oldProbability, newProbability);
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
        msgs = ((InternalEObject)probability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, null, msgs);
      if (newProbability != null)
        msgs = ((InternalEObject)newProbability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, null, msgs);
      msgs = basicSetProbability(newProbability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY, newProbability, newProbability));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractParameter> getParameters() {

    if (parameters == null) {
      parameters = new EObjectWithInverseResolvingEList.ManyInverse<AbstractParameter>(AbstractParameter.class, this, ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS, ModellingcorePackage.ABSTRACT_PARAMETER__PARAMETER_SET);
    }
    return parameters;
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameters()).basicAdd(otherEnd, msgs);
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS:
        return ((InternalEList<?>)getOwnedConditions()).basicRemove(otherEnd, msgs);
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY:
        return basicSetProbability(null, msgs);
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS:
        return getOwnedConditions();
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY:
        if (resolve) return getProbability();
        return basicGetProbability();
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        return getParameters();
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS:
        getOwnedConditions().clear();
        getOwnedConditions().addAll((Collection<? extends AbstractConstraint>)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY:
          setProbability((ValueSpecification)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AbstractParameter>)newValue);
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS:
        getOwnedConditions().clear();
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY:
        setProbability((ValueSpecification)null);
        return;
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        getParameters().clear();
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
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS:
        return ownedConditions != null && !ownedConditions.isEmpty();
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PROBABILITY:
        return probability != null;
      case ModellingcorePackage.ABSTRACT_PARAMETER_SET__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //AbstractParameterSetImpl