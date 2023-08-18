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
package org.polarsys.capella.core.data.oa.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.Location;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.LocationImpl#getLocationDescription <em>Location Description</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.LocationImpl#getLocatedEntities <em>Located Entities</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LocationImpl extends AbstractConceptItemImpl implements Location {

	/**
   * The default value of the '{@link #getLocationDescription() <em>Location Description</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocationDescription()
   * @generated
   * @ordered
   */
	protected static final String LOCATION_DESCRIPTION_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getLocationDescription() <em>Location Description</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocationDescription()
   * @generated
   * @ordered
   */
	protected String locationDescription = LOCATION_DESCRIPTION_EDEFAULT;





	/**
   * The cached value of the '{@link #getLocatedEntities() <em>Located Entities</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLocatedEntities()
   * @generated
   * @ordered
   */
	protected EList<Entity> locatedEntities;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected LocationImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.LOCATION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getLocationDescription() {

    return locationDescription;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setLocationDescription(String newLocationDescription) {

    String oldLocationDescription = locationDescription;
    locationDescription = newLocationDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.LOCATION__LOCATION_DESCRIPTION, oldLocationDescription, locationDescription));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<Entity> getLocatedEntities() {

    if (locatedEntities == null) {
      locatedEntities = new EObjectResolvingEList<Entity>(Entity.class, this, OaPackage.LOCATION__LOCATED_ENTITIES);
    }
    return locatedEntities;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.LOCATION__LOCATION_DESCRIPTION:
        return getLocationDescription();
      case OaPackage.LOCATION__LOCATED_ENTITIES:
        return getLocatedEntities();
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
      case OaPackage.LOCATION__LOCATION_DESCRIPTION:
          setLocationDescription((String)newValue);
        return;
      case OaPackage.LOCATION__LOCATED_ENTITIES:
        getLocatedEntities().clear();
        getLocatedEntities().addAll((Collection<? extends Entity>)newValue);
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
      case OaPackage.LOCATION__LOCATION_DESCRIPTION:
        setLocationDescription(LOCATION_DESCRIPTION_EDEFAULT);
        return;
      case OaPackage.LOCATION__LOCATED_ENTITIES:
        getLocatedEntities().clear();
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
      case OaPackage.LOCATION__LOCATION_DESCRIPTION:
        return LOCATION_DESCRIPTION_EDEFAULT == null ? locationDescription != null : !LOCATION_DESCRIPTION_EDEFAULT.equals(locationDescription);
      case OaPackage.LOCATION__LOCATED_ENTITIES:
        return locatedEntities != null && !locatedEntities.isEmpty();
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
    result.append(" (locationDescription: "); //$NON-NLS-1$
    result.append(locationDescription);
    result.append(')');
    return result.toString();
  }


} //LocationImpl