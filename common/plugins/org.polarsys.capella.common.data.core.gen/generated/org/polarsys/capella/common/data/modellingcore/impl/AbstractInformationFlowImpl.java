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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Information Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl#getRealizedFlow <em>Realized Flow</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl#getRealizations <em>Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl#getConvoyedInformations <em>Convoyed Informations</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.impl.AbstractInformationFlowImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractInformationFlowImpl extends AbstractNamedElementImpl implements AbstractInformationFlow {

	/**
   * The cached value of the '{@link #getRealizedFlow() <em>Realized Flow</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRealizedFlow()
   * @generated
   * @ordered
   */
	protected AbstractInformationFlow realizedFlow;





	/**
   * The cached value of the '{@link #getRealizations() <em>Realizations</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getRealizations()
   * @generated
   * @ordered
   */
	protected EList<AbstractRelationship> realizations;





	/**
   * The cached value of the '{@link #getConvoyedInformations() <em>Convoyed Informations</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getConvoyedInformations()
   * @generated
   * @ordered
   */
	protected EList<AbstractExchangeItem> convoyedInformations;





	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected InformationsExchanger source;





	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected InformationsExchanger target;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractInformationFlowImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractInformationFlow getRealizedFlow() {

    if (realizedFlow != null && realizedFlow.eIsProxy()) {
      InternalEObject oldRealizedFlow = (InternalEObject)realizedFlow;
      realizedFlow = (AbstractInformationFlow)eResolveProxy(oldRealizedFlow);
      if (realizedFlow != oldRealizedFlow) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW, oldRealizedFlow, realizedFlow));
      }
    }
    return realizedFlow;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractInformationFlow basicGetRealizedFlow() {

    return realizedFlow;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetRealizedFlow(AbstractInformationFlow newRealizedFlow, NotificationChain msgs) {

    AbstractInformationFlow oldRealizedFlow = realizedFlow;
    realizedFlow = newRealizedFlow;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW, oldRealizedFlow, newRealizedFlow);
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
	public void setRealizedFlow(AbstractInformationFlow newRealizedFlow) {

    if (newRealizedFlow != realizedFlow) {
      NotificationChain msgs = null;
      if (realizedFlow != null)
        msgs = ((InternalEObject)realizedFlow).eInverseRemove(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
      if (newRealizedFlow != null)
        msgs = ((InternalEObject)newRealizedFlow).eInverseAdd(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
      msgs = basicSetRealizedFlow(newRealizedFlow, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW, newRealizedFlow, newRealizedFlow));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractRelationship> getRealizations() {

    if (realizations == null) {
      realizations = new EObjectWithInverseResolvingEList<AbstractRelationship>(AbstractRelationship.class, this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW);
    }
    return realizations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractExchangeItem> getConvoyedInformations() {

    if (convoyedInformations == null) {
      convoyedInformations = new EObjectResolvingEList<AbstractExchangeItem>(AbstractExchangeItem.class, this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS);
    }
    return convoyedInformations;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InformationsExchanger getSource() {

    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (InformationsExchanger)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE, oldSource, source));
      }
    }
    return source;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InformationsExchanger basicGetSource() {

    return source;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSource(InformationsExchanger newSource) {

    InformationsExchanger oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE, oldSource, source));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InformationsExchanger getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (InformationsExchanger)eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET, oldTarget, target));
      }
    }
    return target;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public InformationsExchanger basicGetTarget() {

    return target;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(InformationsExchanger newTarget) {

    InformationsExchanger oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET, oldTarget, target));

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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
        if (realizedFlow != null)
          msgs = ((InternalEObject)realizedFlow).eInverseRemove(this, ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS, AbstractInformationFlow.class, msgs);
        return basicSetRealizedFlow((AbstractInformationFlow)otherEnd, msgs);
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getRealizations()).basicAdd(otherEnd, msgs);
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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
        return basicSetRealizedFlow(null, msgs);
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        return ((InternalEList<?>)getRealizations()).basicRemove(otherEnd, msgs);
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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
        if (resolve) return getRealizedFlow();
        return basicGetRealizedFlow();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        return getRealizations();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS:
        return getConvoyedInformations();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
          setRealizedFlow((AbstractInformationFlow)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        getRealizations().clear();
        getRealizations().addAll((Collection<? extends AbstractRelationship>)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS:
        getConvoyedInformations().clear();
        getConvoyedInformations().addAll((Collection<? extends AbstractExchangeItem>)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE:
          setSource((InformationsExchanger)newValue);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET:
          setTarget((InformationsExchanger)newValue);
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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
        setRealizedFlow((AbstractInformationFlow)null);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        getRealizations().clear();
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS:
        getConvoyedInformations().clear();
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE:
        setSource((InformationsExchanger)null);
        return;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET:
        setTarget((InformationsExchanger)null);
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
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW:
        return realizedFlow != null;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZATIONS:
        return realizations != null && !realizations.isEmpty();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS:
        return convoyedInformations != null && !convoyedInformations.isEmpty();
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__SOURCE:
        return source != null;
      case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__TARGET:
        return target != null;
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
    if (baseClass == AbstractRelationship.class) {
      switch (derivedFeatureID) {
        case ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW: return ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW;
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
    if (baseClass == AbstractRelationship.class) {
      switch (baseFeatureID) {
        case ModellingcorePackage.ABSTRACT_RELATIONSHIP__REALIZED_FLOW: return ModellingcorePackage.ABSTRACT_INFORMATION_FLOW__REALIZED_FLOW;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }


} //AbstractInformationFlowImpl