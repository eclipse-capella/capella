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
package org.polarsys.capella.common.data.activity.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.activity.AbstractAction;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.InputPin;
import org.polarsys.capella.common.data.activity.OutputPin;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActionImpl#getLocalPrecondition <em>Local Precondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActionImpl#getLocalPostcondition <em>Local Postcondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.AbstractActionImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractActionImpl extends ExecutableNodeImpl implements AbstractAction {

	/**
   * The cached value of the '{@link #getLocalPrecondition() <em>Local Precondition</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocalPrecondition()
   * @generated
   * @ordered
   */
	protected AbstractConstraint localPrecondition;





	/**
   * The cached value of the '{@link #getLocalPostcondition() <em>Local Postcondition</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocalPostcondition()
   * @generated
   * @ordered
   */
	protected AbstractConstraint localPostcondition;





	/**
   * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getContext()
   * @generated
   * @ordered
   */
	protected AbstractType context;





	/**
   * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInputs()
   * @generated
   * @ordered
   */
	protected EList<InputPin> inputs;





	/**
   * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOutputs()
   * @generated
   * @ordered
   */
	protected EList<OutputPin> outputs;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractActionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.ABSTRACT_ACTION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint getLocalPrecondition() {

    if (localPrecondition != null && localPrecondition.eIsProxy()) {
      InternalEObject oldLocalPrecondition = (InternalEObject)localPrecondition;
      localPrecondition = (AbstractConstraint)eResolveProxy(oldLocalPrecondition);
      if (localPrecondition != oldLocalPrecondition) {
        InternalEObject newLocalPrecondition = (InternalEObject)localPrecondition;
        NotificationChain msgs = oldLocalPrecondition.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, null, null);
        if (newLocalPrecondition.eInternalContainer() == null) {
          msgs = newLocalPrecondition.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, oldLocalPrecondition, localPrecondition));
      }
    }
    return localPrecondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint basicGetLocalPrecondition() {

    return localPrecondition;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetLocalPrecondition(AbstractConstraint newLocalPrecondition, NotificationChain msgs) {

    AbstractConstraint oldLocalPrecondition = localPrecondition;
    localPrecondition = newLocalPrecondition;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, oldLocalPrecondition, newLocalPrecondition);
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
	public void setLocalPrecondition(AbstractConstraint newLocalPrecondition) {

    if (newLocalPrecondition != localPrecondition) {
      NotificationChain msgs = null;
      if (localPrecondition != null)
        msgs = ((InternalEObject)localPrecondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, null, msgs);
      if (newLocalPrecondition != null)
        msgs = ((InternalEObject)newLocalPrecondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, null, msgs);
      msgs = basicSetLocalPrecondition(newLocalPrecondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION, newLocalPrecondition, newLocalPrecondition));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint getLocalPostcondition() {

    if (localPostcondition != null && localPostcondition.eIsProxy()) {
      InternalEObject oldLocalPostcondition = (InternalEObject)localPostcondition;
      localPostcondition = (AbstractConstraint)eResolveProxy(oldLocalPostcondition);
      if (localPostcondition != oldLocalPostcondition) {
        InternalEObject newLocalPostcondition = (InternalEObject)localPostcondition;
        NotificationChain msgs = oldLocalPostcondition.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, null, null);
        if (newLocalPostcondition.eInternalContainer() == null) {
          msgs = newLocalPostcondition.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, null, msgs);
        }
        if (msgs != null) msgs.dispatch();
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, oldLocalPostcondition, localPostcondition));
      }
    }
    return localPostcondition;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractConstraint basicGetLocalPostcondition() {

    return localPostcondition;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetLocalPostcondition(AbstractConstraint newLocalPostcondition, NotificationChain msgs) {

    AbstractConstraint oldLocalPostcondition = localPostcondition;
    localPostcondition = newLocalPostcondition;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, oldLocalPostcondition, newLocalPostcondition);
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
	public void setLocalPostcondition(AbstractConstraint newLocalPostcondition) {

    if (newLocalPostcondition != localPostcondition) {
      NotificationChain msgs = null;
      if (localPostcondition != null)
        msgs = ((InternalEObject)localPostcondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, null, msgs);
      if (newLocalPostcondition != null)
        msgs = ((InternalEObject)newLocalPostcondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, null, msgs);
      msgs = basicSetLocalPostcondition(newLocalPostcondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION, newLocalPostcondition, newLocalPostcondition));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType getContext() {

    if (context != null && context.eIsProxy()) {
      InternalEObject oldContext = (InternalEObject)context;
      context = (AbstractType)eResolveProxy(oldContext);
      if (context != oldContext) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.ABSTRACT_ACTION__CONTEXT, oldContext, context));
      }
    }
    return context;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractType basicGetContext() {

    return context;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setContext(AbstractType newContext) {

    AbstractType oldContext = context;
    context = newContext;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.ABSTRACT_ACTION__CONTEXT, oldContext, context));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<InputPin> getInputs() {

    if (inputs == null) {
      inputs = new EObjectContainmentEList.Resolving<InputPin>(InputPin.class, this, ActivityPackage.ABSTRACT_ACTION__INPUTS);
    }
    return inputs;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<OutputPin> getOutputs() {

    if (outputs == null) {
      outputs = new EObjectContainmentEList.Resolving<OutputPin>(OutputPin.class, this, ActivityPackage.ABSTRACT_ACTION__OUTPUTS);
    }
    return outputs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION:
        return basicSetLocalPrecondition(null, msgs);
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION:
        return basicSetLocalPostcondition(null, msgs);
      case ActivityPackage.ABSTRACT_ACTION__INPUTS:
        return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
      case ActivityPackage.ABSTRACT_ACTION__OUTPUTS:
        return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
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
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION:
        if (resolve) return getLocalPrecondition();
        return basicGetLocalPrecondition();
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION:
        if (resolve) return getLocalPostcondition();
        return basicGetLocalPostcondition();
      case ActivityPackage.ABSTRACT_ACTION__CONTEXT:
        if (resolve) return getContext();
        return basicGetContext();
      case ActivityPackage.ABSTRACT_ACTION__INPUTS:
        return getInputs();
      case ActivityPackage.ABSTRACT_ACTION__OUTPUTS:
        return getOutputs();
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
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION:
          setLocalPrecondition((AbstractConstraint)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION:
          setLocalPostcondition((AbstractConstraint)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTION__CONTEXT:
          setContext((AbstractType)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTION__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection<? extends InputPin>)newValue);
        return;
      case ActivityPackage.ABSTRACT_ACTION__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection<? extends OutputPin>)newValue);
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
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION:
        setLocalPrecondition((AbstractConstraint)null);
        return;
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION:
        setLocalPostcondition((AbstractConstraint)null);
        return;
      case ActivityPackage.ABSTRACT_ACTION__CONTEXT:
        setContext((AbstractType)null);
        return;
      case ActivityPackage.ABSTRACT_ACTION__INPUTS:
        getInputs().clear();
        return;
      case ActivityPackage.ABSTRACT_ACTION__OUTPUTS:
        getOutputs().clear();
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
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_PRECONDITION:
        return localPrecondition != null;
      case ActivityPackage.ABSTRACT_ACTION__LOCAL_POSTCONDITION:
        return localPostcondition != null;
      case ActivityPackage.ABSTRACT_ACTION__CONTEXT:
        return context != null;
      case ActivityPackage.ABSTRACT_ACTION__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case ActivityPackage.ABSTRACT_ACTION__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //AbstractActionImpl