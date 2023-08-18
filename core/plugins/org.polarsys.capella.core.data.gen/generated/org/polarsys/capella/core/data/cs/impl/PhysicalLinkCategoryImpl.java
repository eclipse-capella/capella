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
package org.polarsys.capella.core.data.cs.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.capellacore.impl.NamedElementImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Physical Link Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.impl.PhysicalLinkCategoryImpl#getLinks <em>Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PhysicalLinkCategoryImpl extends NamedElementImpl implements PhysicalLinkCategory {

	/**
   * The cached value of the '{@link #getLinks() <em>Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getLinks()
   * @generated
   * @ordered
   */
	protected EList<PhysicalLink> links;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PhysicalLinkCategoryImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return CsPackage.Literals.PHYSICAL_LINK_CATEGORY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<PhysicalLink> getLinks() {

    if (links == null) {
      links = new EObjectResolvingEList<PhysicalLink>(PhysicalLink.class, this, CsPackage.PHYSICAL_LINK_CATEGORY__LINKS);
    }
    return links;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case CsPackage.PHYSICAL_LINK_CATEGORY__LINKS:
        return getLinks();
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
      case CsPackage.PHYSICAL_LINK_CATEGORY__LINKS:
        getLinks().clear();
        getLinks().addAll((Collection<? extends PhysicalLink>)newValue);
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
      case CsPackage.PHYSICAL_LINK_CATEGORY__LINKS:
        getLinks().clear();
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
      case CsPackage.PHYSICAL_LINK_CATEGORY__LINKS:
        return links != null && !links.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //PhysicalLinkCategoryImpl