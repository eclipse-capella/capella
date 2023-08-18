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
package org.polarsys.capella.common.libraries.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.ModelVersion;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Information</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.ModelInformationImpl#getOwnedReferences <em>Owned References</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.ModelInformationImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelInformationImpl extends LibraryAbstractElementImpl implements ModelInformation {

	/**
   * The cached value of the '{@link #getOwnedReferences() <em>Owned References</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedReferences()
   * @generated
   * @ordered
   */
	protected EList<LibraryReference> ownedReferences;





	/**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
	protected ModelVersion version;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelInformationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LibrariesPackage.Literals.MODEL_INFORMATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<LibraryReference> getOwnedReferences() {

    if (ownedReferences == null) {
      ownedReferences = new EObjectContainmentEList.Resolving<LibraryReference>(LibraryReference.class, this, LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES);
    }
    return ownedReferences;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelVersion getVersion() {

    if (version != null && version.eIsProxy()) {
      InternalEObject oldVersion = (InternalEObject)version;
      version = (ModelVersion)eResolveProxy(oldVersion);
      if (version != oldVersion) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LibrariesPackage.MODEL_INFORMATION__VERSION, oldVersion, version));
      }
    }
    return version;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelVersion basicGetVersion() {

    return version;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setVersion(ModelVersion newVersion) {

    ModelVersion oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.MODEL_INFORMATION__VERSION, oldVersion, version));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES:
        return ((InternalEList<?>)getOwnedReferences()).basicRemove(otherEnd, msgs);
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
      case LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES:
        return getOwnedReferences();
      case LibrariesPackage.MODEL_INFORMATION__VERSION:
        if (resolve) return getVersion();
        return basicGetVersion();
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
      case LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES:
        getOwnedReferences().clear();
        getOwnedReferences().addAll((Collection<? extends LibraryReference>)newValue);
        return;
      case LibrariesPackage.MODEL_INFORMATION__VERSION:
          setVersion((ModelVersion)newValue);
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
      case LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES:
        getOwnedReferences().clear();
        return;
      case LibrariesPackage.MODEL_INFORMATION__VERSION:
        setVersion((ModelVersion)null);
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
      case LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES:
        return ownedReferences != null && !ownedReferences.isEmpty();
      case LibrariesPackage.MODEL_INFORMATION__VERSION:
        return version != null;
    }
    return super.eIsSet(featureID);
  }



} //ModelInformationImpl