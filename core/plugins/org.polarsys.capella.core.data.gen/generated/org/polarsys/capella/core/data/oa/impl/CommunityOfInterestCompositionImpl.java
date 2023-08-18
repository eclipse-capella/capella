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
import org.polarsys.capella.core.data.oa.CommunityOfInterest;
import org.polarsys.capella.core.data.oa.CommunityOfInterestComposition;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OrganisationalUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Community Of Interest Composition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl#getCommunityOfInterest <em>Community Of Interest</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestCompositionImpl#getInterestedOrganisationUnit <em>Interested Organisation Unit</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CommunityOfInterestCompositionImpl extends NamedElementImpl implements CommunityOfInterestComposition {

	/**
   * The cached value of the '{@link #getCommunityOfInterest() <em>Community Of Interest</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCommunityOfInterest()
   * @generated
   * @ordered
   */
	protected CommunityOfInterest communityOfInterest;





	/**
   * The cached value of the '{@link #getInterestedOrganisationUnit() <em>Interested Organisation Unit</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInterestedOrganisationUnit()
   * @generated
   * @ordered
   */
	protected OrganisationalUnit interestedOrganisationUnit;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CommunityOfInterestCompositionImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.COMMUNITY_OF_INTEREST_COMPOSITION;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CommunityOfInterest getCommunityOfInterest() {

    if (communityOfInterest != null && communityOfInterest.eIsProxy()) {
      InternalEObject oldCommunityOfInterest = (InternalEObject)communityOfInterest;
      communityOfInterest = (CommunityOfInterest)eResolveProxy(oldCommunityOfInterest);
      if (communityOfInterest != oldCommunityOfInterest) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST, oldCommunityOfInterest, communityOfInterest));
      }
    }
    return communityOfInterest;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public CommunityOfInterest basicGetCommunityOfInterest() {

    return communityOfInterest;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setCommunityOfInterest(CommunityOfInterest newCommunityOfInterest) {

    CommunityOfInterest oldCommunityOfInterest = communityOfInterest;
    communityOfInterest = newCommunityOfInterest;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST, oldCommunityOfInterest, communityOfInterest));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OrganisationalUnit getInterestedOrganisationUnit() {

    if (interestedOrganisationUnit != null && interestedOrganisationUnit.eIsProxy()) {
      InternalEObject oldInterestedOrganisationUnit = (InternalEObject)interestedOrganisationUnit;
      interestedOrganisationUnit = (OrganisationalUnit)eResolveProxy(oldInterestedOrganisationUnit);
      if (interestedOrganisationUnit != oldInterestedOrganisationUnit) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT, oldInterestedOrganisationUnit, interestedOrganisationUnit));
      }
    }
    return interestedOrganisationUnit;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public OrganisationalUnit basicGetInterestedOrganisationUnit() {

    return interestedOrganisationUnit;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setInterestedOrganisationUnit(OrganisationalUnit newInterestedOrganisationUnit) {

    OrganisationalUnit oldInterestedOrganisationUnit = interestedOrganisationUnit;
    interestedOrganisationUnit = newInterestedOrganisationUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT, oldInterestedOrganisationUnit, interestedOrganisationUnit));

  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST:
        if (resolve) return getCommunityOfInterest();
        return basicGetCommunityOfInterest();
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT:
        if (resolve) return getInterestedOrganisationUnit();
        return basicGetInterestedOrganisationUnit();
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
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST:
          setCommunityOfInterest((CommunityOfInterest)newValue);
        return;
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT:
          setInterestedOrganisationUnit((OrganisationalUnit)newValue);
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
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST:
        setCommunityOfInterest((CommunityOfInterest)null);
        return;
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT:
        setInterestedOrganisationUnit((OrganisationalUnit)null);
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
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__COMMUNITY_OF_INTEREST:
        return communityOfInterest != null;
      case OaPackage.COMMUNITY_OF_INTEREST_COMPOSITION__INTERESTED_ORGANISATION_UNIT:
        return interestedOrganisationUnit != null;
    }
    return super.eIsSet(featureID);
  }



} //CommunityOfInterestCompositionImpl