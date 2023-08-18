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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ExceptionHandler;
import org.polarsys.capella.common.data.activity.ExecutableNode;
import org.polarsys.capella.common.data.activity.ObjectNode;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exception Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ExceptionHandlerImpl#getProtectedNode <em>Protected Node</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ExceptionHandlerImpl#getHandlerBody <em>Handler Body</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ExceptionHandlerImpl#getExceptionInput <em>Exception Input</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ExceptionHandlerImpl#getExceptionTypes <em>Exception Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ExceptionHandlerImpl extends ModelElementImpl implements ExceptionHandler {





	/**
   * The cached value of the '{@link #getHandlerBody() <em>Handler Body</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getHandlerBody()
   * @generated
   * @ordered
   */
	protected ExecutableNode handlerBody;





	/**
   * The cached value of the '{@link #getExceptionInput() <em>Exception Input</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExceptionInput()
   * @generated
   * @ordered
   */
	protected ObjectNode exceptionInput;





	/**
   * The cached value of the '{@link #getExceptionTypes() <em>Exception Types</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExceptionTypes()
   * @generated
   * @ordered
   */
	protected EList<AbstractType> exceptionTypes;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ExceptionHandlerImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.EXCEPTION_HANDLER;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExecutableNode getProtectedNode() {

    if (eContainerFeatureID() != ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE) return null;
    return (ExecutableNode)eContainer();
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExecutableNode basicGetProtectedNode() {

    if (eContainerFeatureID() != ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE) return null;
    return (ExecutableNode)eInternalContainer();
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NotificationChain basicSetProtectedNode(ExecutableNode newProtectedNode, NotificationChain msgs) {

    msgs = eBasicSetContainer((InternalEObject)newProtectedNode, ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE, msgs);

    return msgs;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setProtectedNode(ExecutableNode newProtectedNode) {

    if (newProtectedNode != eInternalContainer() || (eContainerFeatureID() != ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE && newProtectedNode != null)) {
      if (EcoreUtil.isAncestor(this, newProtectedNode))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newProtectedNode != null)
        msgs = ((InternalEObject)newProtectedNode).eInverseAdd(this, ActivityPackage.EXECUTABLE_NODE__OWNED_HANDLERS, ExecutableNode.class, msgs);
      msgs = basicSetProtectedNode(newProtectedNode, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE, newProtectedNode, newProtectedNode));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExecutableNode getHandlerBody() {

    if (handlerBody != null && handlerBody.eIsProxy()) {
      InternalEObject oldHandlerBody = (InternalEObject)handlerBody;
      handlerBody = (ExecutableNode)eResolveProxy(oldHandlerBody);
      if (handlerBody != oldHandlerBody) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY, oldHandlerBody, handlerBody));
      }
    }
    return handlerBody;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ExecutableNode basicGetHandlerBody() {

    return handlerBody;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setHandlerBody(ExecutableNode newHandlerBody) {

    ExecutableNode oldHandlerBody = handlerBody;
    handlerBody = newHandlerBody;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY, oldHandlerBody, handlerBody));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ObjectNode getExceptionInput() {

    if (exceptionInput != null && exceptionInput.eIsProxy()) {
      InternalEObject oldExceptionInput = (InternalEObject)exceptionInput;
      exceptionInput = (ObjectNode)eResolveProxy(oldExceptionInput);
      if (exceptionInput != oldExceptionInput) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT, oldExceptionInput, exceptionInput));
      }
    }
    return exceptionInput;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ObjectNode basicGetExceptionInput() {

    return exceptionInput;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setExceptionInput(ObjectNode newExceptionInput) {

    ObjectNode oldExceptionInput = exceptionInput;
    exceptionInput = newExceptionInput;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT, oldExceptionInput, exceptionInput));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<AbstractType> getExceptionTypes() {

    if (exceptionTypes == null) {
      exceptionTypes = new EObjectResolvingEList<AbstractType>(AbstractType.class, this, ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_TYPES);
    }
    return exceptionTypes;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetProtectedNode((ExecutableNode)otherEnd, msgs);
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
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        return basicSetProtectedNode(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID()) {
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        return eInternalContainer().eInverseRemove(this, ActivityPackage.EXECUTABLE_NODE__OWNED_HANDLERS, ExecutableNode.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        if (resolve) return getProtectedNode();
        return basicGetProtectedNode();
      case ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY:
        if (resolve) return getHandlerBody();
        return basicGetHandlerBody();
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT:
        if (resolve) return getExceptionInput();
        return basicGetExceptionInput();
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_TYPES:
        return getExceptionTypes();
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
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
          setProtectedNode((ExecutableNode)newValue);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY:
          setHandlerBody((ExecutableNode)newValue);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT:
          setExceptionInput((ObjectNode)newValue);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_TYPES:
        getExceptionTypes().clear();
        getExceptionTypes().addAll((Collection<? extends AbstractType>)newValue);
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
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        setProtectedNode((ExecutableNode)null);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY:
        setHandlerBody((ExecutableNode)null);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT:
        setExceptionInput((ObjectNode)null);
        return;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_TYPES:
        getExceptionTypes().clear();
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
      case ActivityPackage.EXCEPTION_HANDLER__PROTECTED_NODE:
        return basicGetProtectedNode() != null;
      case ActivityPackage.EXCEPTION_HANDLER__HANDLER_BODY:
        return handlerBody != null;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_INPUT:
        return exceptionInput != null;
      case ActivityPackage.EXCEPTION_HANDLER__EXCEPTION_TYPES:
        return exceptionTypes != null && !exceptionTypes.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //ExceptionHandlerImpl