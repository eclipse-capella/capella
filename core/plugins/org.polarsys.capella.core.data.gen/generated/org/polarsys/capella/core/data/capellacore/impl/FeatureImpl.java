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
package org.polarsys.capella.core.data.capellacore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.FeatureImpl#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.FeatureImpl#isIsStatic <em>Is Static</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.capellacore.impl.FeatureImpl#getVisibility <em>Visibility</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FeatureImpl extends NamedElementImpl implements Feature {

	/**
   * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsAbstract()
   * @generated
   * @ordered
   */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsAbstract()
   * @generated
   * @ordered
   */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;





	/**
   * The default value of the '{@link #isIsStatic() <em>Is Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStatic()
   * @generated
   * @ordered
   */
	protected static final boolean IS_STATIC_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsStatic() <em>Is Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsStatic()
   * @generated
   * @ordered
   */
	protected boolean isStatic = IS_STATIC_EDEFAULT;





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
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected FeatureImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CapellacorePackage.Literals.FEATURE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsAbstract() {

    return isAbstract;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsAbstract(boolean newIsAbstract) {

    boolean oldIsAbstract = isAbstract;
    isAbstract = newIsAbstract;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.FEATURE__IS_ABSTRACT, oldIsAbstract, isAbstract));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isIsStatic() {

    return isStatic;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setIsStatic(boolean newIsStatic) {

    boolean oldIsStatic = isStatic;
    isStatic = newIsStatic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.FEATURE__IS_STATIC, oldIsStatic, isStatic));

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
      eNotify(new ENotificationImpl(this, Notification.SET, CapellacorePackage.FEATURE__VISIBILITY, oldVisibility, visibility));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CapellacorePackage.FEATURE__IS_ABSTRACT:
        return isIsAbstract();
      case CapellacorePackage.FEATURE__IS_STATIC:
        return isIsStatic();
      case CapellacorePackage.FEATURE__VISIBILITY:
        return getVisibility();
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
      case CapellacorePackage.FEATURE__IS_ABSTRACT:
          setIsAbstract((Boolean)newValue);
        return;
      case CapellacorePackage.FEATURE__IS_STATIC:
          setIsStatic((Boolean)newValue);
        return;
      case CapellacorePackage.FEATURE__VISIBILITY:
          setVisibility((VisibilityKind)newValue);
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
      case CapellacorePackage.FEATURE__IS_ABSTRACT:
        setIsAbstract(IS_ABSTRACT_EDEFAULT);
        return;
      case CapellacorePackage.FEATURE__IS_STATIC:
        setIsStatic(IS_STATIC_EDEFAULT);
        return;
      case CapellacorePackage.FEATURE__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
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
      case CapellacorePackage.FEATURE__IS_ABSTRACT:
        return isAbstract != IS_ABSTRACT_EDEFAULT;
      case CapellacorePackage.FEATURE__IS_STATIC:
        return isStatic != IS_STATIC_EDEFAULT;
      case CapellacorePackage.FEATURE__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
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
    result.append(" (isAbstract: "); //$NON-NLS-1$
    result.append(isAbstract);
    result.append(", isStatic: "); //$NON-NLS-1$
    result.append(isStatic);
    result.append(", visibility: "); //$NON-NLS-1$
    result.append(visibility);
    result.append(')');
    return result.toString();
  }


} //FeatureImpl