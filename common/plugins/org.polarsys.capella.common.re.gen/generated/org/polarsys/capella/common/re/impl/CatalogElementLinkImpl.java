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
package org.polarsys.capella.common.re.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catalog Element Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl#getUnsynchronizedFeatures <em>Unsynchronized Features</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.impl.CatalogElementLinkImpl#isSuffixed <em>Suffixed</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CatalogElementLinkImpl extends ReAbstractElementImpl implements CatalogElementLink {

	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected CatalogElement source;





	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected EObject target;





	/**
   * The cached value of the '{@link #getOrigin() <em>Origin</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOrigin()
   * @generated
   * @ordered
   */
	protected CatalogElementLink origin;





	/**
   * The cached value of the '{@link #getUnsynchronizedFeatures() <em>Unsynchronized Features</em>}' attribute list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUnsynchronizedFeatures()
   * @generated
   * @ordered
   */
	protected EList<String> unsynchronizedFeatures;




	/**
   * The default value of the '{@link #isSuffixed() <em>Suffixed</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isSuffixed()
   * @generated
   * @ordered
   */
	protected static final boolean SUFFIXED_EDEFAULT = false;





	/**
   * The cached value of the '{@link #isSuffixed() <em>Suffixed</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isSuffixed()
   * @generated
   * @ordered
   */
	protected boolean suffixed = SUFFIXED_EDEFAULT;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CatalogElementLinkImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RePackage.Literals.CATALOG_ELEMENT_LINK;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElement getSource() {

    if (source != null && source.eIsProxy()) {
      InternalEObject oldSource = (InternalEObject)source;
      source = (CatalogElement)eResolveProxy(oldSource);
      if (source != oldSource) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT_LINK__SOURCE, oldSource, source));
      }
    }
    return source;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElement basicGetSource() {

    return source;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSource(CatalogElement newSource) {

    CatalogElement oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT_LINK__SOURCE, oldSource, source));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EObject getTarget() {

    if (target != null && target.eIsProxy()) {
      InternalEObject oldTarget = (InternalEObject)target;
      target = eResolveProxy(oldTarget);
      if (target != oldTarget) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT_LINK__TARGET, oldTarget, target));
      }
    }
    return target;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EObject basicGetTarget() {

    return target;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setTarget(EObject newTarget) {

    EObject oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT_LINK__TARGET, oldTarget, target));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElementLink getOrigin() {

    if (origin != null && origin.eIsProxy()) {
      InternalEObject oldOrigin = (InternalEObject)origin;
      origin = (CatalogElementLink)eResolveProxy(oldOrigin);
      if (origin != oldOrigin) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, RePackage.CATALOG_ELEMENT_LINK__ORIGIN, oldOrigin, origin));
      }
    }
    return origin;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CatalogElementLink basicGetOrigin() {

    return origin;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOrigin(CatalogElementLink newOrigin) {

    CatalogElementLink oldOrigin = origin;
    origin = newOrigin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT_LINK__ORIGIN, oldOrigin, origin));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<String> getUnsynchronizedFeatures() {

    if (unsynchronizedFeatures == null) {
      unsynchronizedFeatures = new EDataTypeUniqueEList<String>(String.class, this, RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES);
    }
    return unsynchronizedFeatures;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public boolean isSuffixed() {

    return suffixed;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setSuffixed(boolean newSuffixed) {

    boolean oldSuffixed = suffixed;
    suffixed = newSuffixed;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RePackage.CATALOG_ELEMENT_LINK__SUFFIXED, oldSuffixed, suffixed));

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case RePackage.CATALOG_ELEMENT_LINK__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case RePackage.CATALOG_ELEMENT_LINK__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case RePackage.CATALOG_ELEMENT_LINK__ORIGIN:
        if (resolve) return getOrigin();
        return basicGetOrigin();
      case RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES:
        return getUnsynchronizedFeatures();
      case RePackage.CATALOG_ELEMENT_LINK__SUFFIXED:
        return isSuffixed();
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
      case RePackage.CATALOG_ELEMENT_LINK__SOURCE:
          setSource((CatalogElement)newValue);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__TARGET:
          setTarget((EObject)newValue);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__ORIGIN:
          setOrigin((CatalogElementLink)newValue);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES:
        getUnsynchronizedFeatures().clear();
        getUnsynchronizedFeatures().addAll((Collection<? extends String>)newValue);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__SUFFIXED:
          setSuffixed((Boolean)newValue);
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
      case RePackage.CATALOG_ELEMENT_LINK__SOURCE:
        setSource((CatalogElement)null);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__TARGET:
        setTarget((EObject)null);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__ORIGIN:
        setOrigin((CatalogElementLink)null);
        return;
      case RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES:
        getUnsynchronizedFeatures().clear();
        return;
      case RePackage.CATALOG_ELEMENT_LINK__SUFFIXED:
        setSuffixed(SUFFIXED_EDEFAULT);
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
      case RePackage.CATALOG_ELEMENT_LINK__SOURCE:
        return source != null;
      case RePackage.CATALOG_ELEMENT_LINK__TARGET:
        return target != null;
      case RePackage.CATALOG_ELEMENT_LINK__ORIGIN:
        return origin != null;
      case RePackage.CATALOG_ELEMENT_LINK__UNSYNCHRONIZED_FEATURES:
        return unsynchronizedFeatures != null && !unsynchronizedFeatures.isEmpty();
      case RePackage.CATALOG_ELEMENT_LINK__SUFFIXED:
        return suffixed != SUFFIXED_EDEFAULT;
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
    result.append(" (unsynchronizedFeatures: "); //$NON-NLS-1$
    result.append(unsynchronizedFeatures);
    result.append(", suffixed: "); //$NON-NLS-1$
    result.append(suffixed);
    result.append(')');
    return result.toString();
  }


} //CatalogElementLinkImpl