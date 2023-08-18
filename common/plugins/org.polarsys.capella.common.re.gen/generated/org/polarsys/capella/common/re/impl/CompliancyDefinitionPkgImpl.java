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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.CompliancyDefinitionPkg;
import org.polarsys.capella.common.re.RePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compliancy Definition Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.impl.CompliancyDefinitionPkgImpl#getOwnedDefinitions <em>Owned Definitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompliancyDefinitionPkgImpl extends ReNamedElementImpl implements CompliancyDefinitionPkg {

	/**
   * The cached value of the '{@link #getOwnedDefinitions() <em>Owned Definitions</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOwnedDefinitions()
   * @generated
   * @ordered
   */
	protected EList<CompliancyDefinition> ownedDefinitions;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CompliancyDefinitionPkgImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return RePackage.Literals.COMPLIANCY_DEFINITION_PKG;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<CompliancyDefinition> getOwnedDefinitions() {

    if (ownedDefinitions == null) {
      ownedDefinitions = new EObjectContainmentEList.Resolving<CompliancyDefinition>(CompliancyDefinition.class, this, RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS);
    }
    return ownedDefinitions;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS:
        return ((InternalEList<?>)getOwnedDefinitions()).basicRemove(otherEnd, msgs);
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
      case RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS:
        return getOwnedDefinitions();
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
      case RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS:
        getOwnedDefinitions().clear();
        getOwnedDefinitions().addAll((Collection<? extends CompliancyDefinition>)newValue);
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
      case RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS:
        getOwnedDefinitions().clear();
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
      case RePackage.COMPLIANCY_DEFINITION_PKG__OWNED_DEFINITIONS:
        return ownedDefinitions != null && !ownedDefinitions.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //CompliancyDefinitionPkgImpl