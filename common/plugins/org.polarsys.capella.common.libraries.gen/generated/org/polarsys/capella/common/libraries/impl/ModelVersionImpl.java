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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.libraries.ModelVersion;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.ModelVersionImpl#getMajorVersionNumber <em>Major Version Number</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.ModelVersionImpl#getMinorVersionNumber <em>Minor Version Number</em>}</li>
 *   <li>{@link org.polarsys.capella.common.libraries.impl.ModelVersionImpl#getLastModifiedFileStamp <em>Last Modified File Stamp</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelVersionImpl extends LibraryAbstractElementImpl implements ModelVersion {

	/**
   * The default value of the '{@link #getMajorVersionNumber() <em>Major Version Number</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMajorVersionNumber()
   * @generated
   * @ordered
   */
	protected static final int MAJOR_VERSION_NUMBER_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getMajorVersionNumber() <em>Major Version Number</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMajorVersionNumber()
   * @generated
   * @ordered
   */
	protected int majorVersionNumber = MAJOR_VERSION_NUMBER_EDEFAULT;





	/**
   * The default value of the '{@link #getMinorVersionNumber() <em>Minor Version Number</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMinorVersionNumber()
   * @generated
   * @ordered
   */
	protected static final int MINOR_VERSION_NUMBER_EDEFAULT = 0;

	/**
   * The cached value of the '{@link #getMinorVersionNumber() <em>Minor Version Number</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getMinorVersionNumber()
   * @generated
   * @ordered
   */
	protected int minorVersionNumber = MINOR_VERSION_NUMBER_EDEFAULT;





	/**
   * The default value of the '{@link #getLastModifiedFileStamp() <em>Last Modified File Stamp</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLastModifiedFileStamp()
   * @generated
   * @ordered
   */
	protected static final long LAST_MODIFIED_FILE_STAMP_EDEFAULT = 0L;

	/**
   * The cached value of the '{@link #getLastModifiedFileStamp() <em>Last Modified File Stamp</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLastModifiedFileStamp()
   * @generated
   * @ordered
   */
	protected long lastModifiedFileStamp = LAST_MODIFIED_FILE_STAMP_EDEFAULT;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelVersionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return LibrariesPackage.Literals.MODEL_VERSION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public int getMajorVersionNumber() {

    return majorVersionNumber;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMajorVersionNumber(int newMajorVersionNumber) {

    int oldMajorVersionNumber = majorVersionNumber;
    majorVersionNumber = newMajorVersionNumber;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER, oldMajorVersionNumber, majorVersionNumber));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public int getMinorVersionNumber() {

    return minorVersionNumber;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setMinorVersionNumber(int newMinorVersionNumber) {

    int oldMinorVersionNumber = minorVersionNumber;
    minorVersionNumber = newMinorVersionNumber;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER, oldMinorVersionNumber, minorVersionNumber));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public long getLastModifiedFileStamp() {

    return lastModifiedFileStamp;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setLastModifiedFileStamp(long newLastModifiedFileStamp) {

    long oldLastModifiedFileStamp = lastModifiedFileStamp;
    lastModifiedFileStamp = newLastModifiedFileStamp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP, oldLastModifiedFileStamp, lastModifiedFileStamp));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER:
        return getMajorVersionNumber();
      case LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER:
        return getMinorVersionNumber();
      case LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP:
        return getLastModifiedFileStamp();
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
      case LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER:
          setMajorVersionNumber((Integer)newValue);
        return;
      case LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER:
          setMinorVersionNumber((Integer)newValue);
        return;
      case LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP:
          setLastModifiedFileStamp((Long)newValue);
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
      case LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER:
        setMajorVersionNumber(MAJOR_VERSION_NUMBER_EDEFAULT);
        return;
      case LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER:
        setMinorVersionNumber(MINOR_VERSION_NUMBER_EDEFAULT);
        return;
      case LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP:
        setLastModifiedFileStamp(LAST_MODIFIED_FILE_STAMP_EDEFAULT);
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
      case LibrariesPackage.MODEL_VERSION__MAJOR_VERSION_NUMBER:
        return majorVersionNumber != MAJOR_VERSION_NUMBER_EDEFAULT;
      case LibrariesPackage.MODEL_VERSION__MINOR_VERSION_NUMBER:
        return minorVersionNumber != MINOR_VERSION_NUMBER_EDEFAULT;
      case LibrariesPackage.MODEL_VERSION__LAST_MODIFIED_FILE_STAMP:
        return lastModifiedFileStamp != LAST_MODIFIED_FILE_STAMP_EDEFAULT;
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
    result.append(" (majorVersionNumber: "); //$NON-NLS-1$
    result.append(majorVersionNumber);
    result.append(", minorVersionNumber: "); //$NON-NLS-1$
    result.append(minorVersionNumber);
    result.append(", lastModifiedFileStamp: "); //$NON-NLS-1$
    result.append(lastModifiedFileStamp);
    result.append(')');
    return result.toString();
  }


} //ModelVersionImpl