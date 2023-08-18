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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.activity.ObjectFlow;
import org.polarsys.capella.common.data.behavior.AbstractBehavior;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ObjectFlowImpl#isIsMulticast <em>Is Multicast</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ObjectFlowImpl#isIsMultireceive <em>Is Multireceive</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ObjectFlowImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.impl.ObjectFlowImpl#getSelection <em>Selection</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ObjectFlowImpl extends ActivityEdgeImpl implements ObjectFlow {

	/**
   * The default value of the '{@link #isIsMulticast() <em>Is Multicast</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMulticast()
   * @generated
   * @ordered
   */
	protected static final boolean IS_MULTICAST_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsMulticast() <em>Is Multicast</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMulticast()
   * @generated
   * @ordered
   */
	protected boolean isMulticast = IS_MULTICAST_EDEFAULT;





	/**
   * The default value of the '{@link #isIsMultireceive() <em>Is Multireceive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMultireceive()
   * @generated
   * @ordered
   */
	protected static final boolean IS_MULTIRECEIVE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsMultireceive() <em>Is Multireceive</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMultireceive()
   * @generated
   * @ordered
   */
	protected boolean isMultireceive = IS_MULTIRECEIVE_EDEFAULT;





	/**
   * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTransformation()
   * @generated
   * @ordered
   */
	protected AbstractBehavior transformation;





	/**
   * The cached value of the '{@link #getSelection() <em>Selection</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSelection()
   * @generated
   * @ordered
   */
	protected AbstractBehavior selection;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ObjectFlowImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return ActivityPackage.Literals.OBJECT_FLOW;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsMulticast() {

    return isMulticast;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsMulticast(boolean newIsMulticast) {

    boolean oldIsMulticast = isMulticast;
    isMulticast = newIsMulticast;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.OBJECT_FLOW__IS_MULTICAST, oldIsMulticast, isMulticast));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsMultireceive() {

    return isMultireceive;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsMultireceive(boolean newIsMultireceive) {

    boolean oldIsMultireceive = isMultireceive;
    isMultireceive = newIsMultireceive;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.OBJECT_FLOW__IS_MULTIRECEIVE, oldIsMultireceive, isMultireceive));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior getTransformation() {

    if (transformation != null && transformation.eIsProxy()) {
      InternalEObject oldTransformation = (InternalEObject)transformation;
      transformation = (AbstractBehavior)eResolveProxy(oldTransformation);
      if (transformation != oldTransformation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.OBJECT_FLOW__TRANSFORMATION, oldTransformation, transformation));
      }
    }
    return transformation;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior basicGetTransformation() {

    return transformation;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTransformation(AbstractBehavior newTransformation) {

    AbstractBehavior oldTransformation = transformation;
    transformation = newTransformation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.OBJECT_FLOW__TRANSFORMATION, oldTransformation, transformation));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior getSelection() {

    if (selection != null && selection.eIsProxy()) {
      InternalEObject oldSelection = (InternalEObject)selection;
      selection = (AbstractBehavior)eResolveProxy(oldSelection);
      if (selection != oldSelection) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ActivityPackage.OBJECT_FLOW__SELECTION, oldSelection, selection));
      }
    }
    return selection;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AbstractBehavior basicGetSelection() {

    return selection;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSelection(AbstractBehavior newSelection) {

    AbstractBehavior oldSelection = selection;
    selection = newSelection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ActivityPackage.OBJECT_FLOW__SELECTION, oldSelection, selection));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ActivityPackage.OBJECT_FLOW__IS_MULTICAST:
        return isIsMulticast();
      case ActivityPackage.OBJECT_FLOW__IS_MULTIRECEIVE:
        return isIsMultireceive();
      case ActivityPackage.OBJECT_FLOW__TRANSFORMATION:
        if (resolve) return getTransformation();
        return basicGetTransformation();
      case ActivityPackage.OBJECT_FLOW__SELECTION:
        if (resolve) return getSelection();
        return basicGetSelection();
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
      case ActivityPackage.OBJECT_FLOW__IS_MULTICAST:
          setIsMulticast((Boolean)newValue);
        return;
      case ActivityPackage.OBJECT_FLOW__IS_MULTIRECEIVE:
          setIsMultireceive((Boolean)newValue);
        return;
      case ActivityPackage.OBJECT_FLOW__TRANSFORMATION:
          setTransformation((AbstractBehavior)newValue);
        return;
      case ActivityPackage.OBJECT_FLOW__SELECTION:
          setSelection((AbstractBehavior)newValue);
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
      case ActivityPackage.OBJECT_FLOW__IS_MULTICAST:
        setIsMulticast(IS_MULTICAST_EDEFAULT);
        return;
      case ActivityPackage.OBJECT_FLOW__IS_MULTIRECEIVE:
        setIsMultireceive(IS_MULTIRECEIVE_EDEFAULT);
        return;
      case ActivityPackage.OBJECT_FLOW__TRANSFORMATION:
        setTransformation((AbstractBehavior)null);
        return;
      case ActivityPackage.OBJECT_FLOW__SELECTION:
        setSelection((AbstractBehavior)null);
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
      case ActivityPackage.OBJECT_FLOW__IS_MULTICAST:
        return isMulticast != IS_MULTICAST_EDEFAULT;
      case ActivityPackage.OBJECT_FLOW__IS_MULTIRECEIVE:
        return isMultireceive != IS_MULTIRECEIVE_EDEFAULT;
      case ActivityPackage.OBJECT_FLOW__TRANSFORMATION:
        return transformation != null;
      case ActivityPackage.OBJECT_FLOW__SELECTION:
        return selection != null;
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
    result.append(" (isMulticast: "); //$NON-NLS-1$
    result.append(isMulticast);
    result.append(", isMultireceive: "); //$NON-NLS-1$
    result.append(isMultireceive);
    result.append(')');
    return result.toString();
  }


} //ObjectFlowImpl