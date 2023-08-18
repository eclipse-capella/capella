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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.ModelVersion;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.LibraryReferenceImpl#getLibrary <em>Library</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.LibraryReferenceImpl#getAccessPolicy <em>Access Policy</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.LibraryReferenceImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryReferenceImpl extends LibraryAbstractElementImpl implements LibraryReference {

	/**
   * The cached value of the '{@link #getLibrary() <em>Library</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLibrary()
   * @generated
   * @ordered
   */
	protected ModelInformation library;





	/**
   * The default value of the '{@link #getAccessPolicy() <em>Access Policy</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAccessPolicy()
   * @generated
   * @ordered
   */
	protected static final AccessPolicy ACCESS_POLICY_EDEFAULT = AccessPolicy.READ_ONLY;

	/**
   * The cached value of the '{@link #getAccessPolicy() <em>Access Policy</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getAccessPolicy()
   * @generated
   * @ordered
   */
	protected AccessPolicy accessPolicy = ACCESS_POLICY_EDEFAULT;





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
	protected LibraryReferenceImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LibrariesPackage.Literals.LIBRARY_REFERENCE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelInformation getLibrary() {

    if (library != null && library.eIsProxy()) {
      InternalEObject oldLibrary = (InternalEObject)library;
      library = (ModelInformation)eResolveProxy(oldLibrary);
      if (library != oldLibrary) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LibrariesPackage.LIBRARY_REFERENCE__LIBRARY, oldLibrary, library));
      }
    }
    return library;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public ModelInformation basicGetLibrary() {

    return library;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setLibrary(ModelInformation newLibrary) {

    ModelInformation oldLibrary = library;
    library = newLibrary;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.LIBRARY_REFERENCE__LIBRARY, oldLibrary, library));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public AccessPolicy getAccessPolicy() {

    return accessPolicy;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setAccessPolicy(AccessPolicy newAccessPolicy) {

    AccessPolicy oldAccessPolicy = accessPolicy;
    accessPolicy = newAccessPolicy == null ? ACCESS_POLICY_EDEFAULT : newAccessPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY, oldAccessPolicy, accessPolicy));

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
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, LibrariesPackage.LIBRARY_REFERENCE__VERSION, oldVersion, version));
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
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.LIBRARY_REFERENCE__VERSION, oldVersion, version));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case LibrariesPackage.LIBRARY_REFERENCE__LIBRARY:
        if (resolve) return getLibrary();
        return basicGetLibrary();
      case LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY:
        return getAccessPolicy();
      case LibrariesPackage.LIBRARY_REFERENCE__VERSION:
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
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case LibrariesPackage.LIBRARY_REFERENCE__LIBRARY:
          setLibrary((ModelInformation)newValue);
        return;
      case LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY:
          setAccessPolicy((AccessPolicy)newValue);
        return;
      case LibrariesPackage.LIBRARY_REFERENCE__VERSION:
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
      case LibrariesPackage.LIBRARY_REFERENCE__LIBRARY:
        setLibrary((ModelInformation)null);
        return;
      case LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY:
        setAccessPolicy(ACCESS_POLICY_EDEFAULT);
        return;
      case LibrariesPackage.LIBRARY_REFERENCE__VERSION:
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
      case LibrariesPackage.LIBRARY_REFERENCE__LIBRARY:
        return library != null;
      case LibrariesPackage.LIBRARY_REFERENCE__ACCESS_POLICY:
        return accessPolicy != ACCESS_POLICY_EDEFAULT;
      case LibrariesPackage.LIBRARY_REFERENCE__VERSION:
        return version != null;
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
    result.append(" (accessPolicy: "); //$NON-NLS-1$
    result.append(accessPolicy);
    result.append(')');
    return result.toString();
  }


} //LibraryReferenceImpl