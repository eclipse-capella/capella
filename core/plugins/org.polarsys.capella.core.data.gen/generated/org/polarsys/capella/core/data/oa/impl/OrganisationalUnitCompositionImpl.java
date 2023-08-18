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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;
import org.polarsys.capella.core.data.oa.OrganisationalUnitComposition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Organisational Unit Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl#getOrganisationalUnit <em>Organisational Unit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.OrganisationalUnitCompositionImpl#getParticipatingEntity <em>Participating Entity</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OrganisationalUnitCompositionImpl extends NamedElementImpl implements OrganisationalUnitComposition {

	/**
   * The cached value of the '{@link #getOrganisationalUnit() <em>Organisational Unit</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOrganisationalUnit()
   * @generated
   * @ordered
   */
	protected OrganisationalUnit organisationalUnit;





	/**
   * The cached value of the '{@link #getParticipatingEntity() <em>Participating Entity</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getParticipatingEntity()
   * @generated
   * @ordered
   */
	protected Entity participatingEntity;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OrganisationalUnitCompositionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ORGANISATIONAL_UNIT_COMPOSITION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OrganisationalUnit getOrganisationalUnit() {

    if (organisationalUnit != null && organisationalUnit.eIsProxy()) {
      InternalEObject oldOrganisationalUnit = (InternalEObject)organisationalUnit;
      organisationalUnit = (OrganisationalUnit)eResolveProxy(oldOrganisationalUnit);
      if (organisationalUnit != oldOrganisationalUnit) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT, oldOrganisationalUnit, organisationalUnit));
      }
    }
    return organisationalUnit;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OrganisationalUnit basicGetOrganisationalUnit() {

    return organisationalUnit;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setOrganisationalUnit(OrganisationalUnit newOrganisationalUnit) {

    OrganisationalUnit oldOrganisationalUnit = organisationalUnit;
    organisationalUnit = newOrganisationalUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT, oldOrganisationalUnit, organisationalUnit));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Entity getParticipatingEntity() {

    if (participatingEntity != null && participatingEntity.eIsProxy()) {
      InternalEObject oldParticipatingEntity = (InternalEObject)participatingEntity;
      participatingEntity = (Entity)eResolveProxy(oldParticipatingEntity);
      if (participatingEntity != oldParticipatingEntity) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY, oldParticipatingEntity, participatingEntity));
      }
    }
    return participatingEntity;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Entity basicGetParticipatingEntity() {

    return participatingEntity;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setParticipatingEntity(Entity newParticipatingEntity) {

    Entity oldParticipatingEntity = participatingEntity;
    participatingEntity = newParticipatingEntity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY, oldParticipatingEntity, participatingEntity));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT:
        if (resolve) return getOrganisationalUnit();
        return basicGetOrganisationalUnit();
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY:
        if (resolve) return getParticipatingEntity();
        return basicGetParticipatingEntity();
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
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT:
          setOrganisationalUnit((OrganisationalUnit)newValue);
        return;
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY:
          setParticipatingEntity((Entity)newValue);
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
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT:
        setOrganisationalUnit((OrganisationalUnit)null);
        return;
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY:
        setParticipatingEntity((Entity)null);
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
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__ORGANISATIONAL_UNIT:
        return organisationalUnit != null;
      case OaPackage.ORGANISATIONAL_UNIT_COMPOSITION__PARTICIPATING_ENTITY:
        return participatingEntity != null;
    }
    return super.eIsSet(featureID);
  }



} //OrganisationalUnitCompositionImpl