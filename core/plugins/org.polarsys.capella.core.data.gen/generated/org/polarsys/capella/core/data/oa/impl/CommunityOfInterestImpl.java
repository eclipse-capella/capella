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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.oa.CommunityOfInterest;
import org.polarsys.capella.core.data.oa.CommunityOfInterestComposition;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Community Of Interest</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.CommunityOfInterestImpl#getCommunityOfInterestCompositions <em>Community Of Interest Compositions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CommunityOfInterestImpl extends NamedElementImpl implements CommunityOfInterest {

	/**
   * The cached value of the '{@link #getCommunityOfInterestCompositions() <em>Community Of Interest Compositions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCommunityOfInterestCompositions()
   * @generated
   * @ordered
   */
	protected EList<CommunityOfInterestComposition> communityOfInterestCompositions;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CommunityOfInterestImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.COMMUNITY_OF_INTEREST;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CommunityOfInterestComposition> getCommunityOfInterestCompositions() {

    if (communityOfInterestCompositions == null) {
      communityOfInterestCompositions = new EObjectContainmentEList.Resolving<CommunityOfInterestComposition>(CommunityOfInterestComposition.class, this, OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS);
    }
    return communityOfInterestCompositions;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS:
        return ((InternalEList<?>)getCommunityOfInterestCompositions()).basicRemove(otherEnd, msgs);
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
      case OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS:
        return getCommunityOfInterestCompositions();
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
      case OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS:
        getCommunityOfInterestCompositions().clear();
        getCommunityOfInterestCompositions().addAll((Collection<? extends CommunityOfInterestComposition>)newValue);
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
      case OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS:
        getCommunityOfInterestCompositions().clear();
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
      case OaPackage.COMMUNITY_OF_INTEREST__COMMUNITY_OF_INTEREST_COMPOSITIONS:
        return communityOfInterestCompositions != null && !communityOfInterestCompositions.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CommunityOfInterestImpl