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
package org.polarsys.capella.core.data.information.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.datavalue.DataValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.impl.UnionPropertyImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionPropertyImpl extends PropertyImpl implements UnionProperty {

	/**
   * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getQualifier()
   * @generated
   * @ordered
   */
	protected EList<DataValue> qualifier;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected UnionPropertyImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return InformationPackage.Literals.UNION_PROPERTY;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<DataValue> getQualifier() {

    if (qualifier == null) {
      qualifier = new EObjectResolvingEList<DataValue>(DataValue.class, this, InformationPackage.UNION_PROPERTY__QUALIFIER);
    }
    return qualifier;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case InformationPackage.UNION_PROPERTY__QUALIFIER:
        return getQualifier();
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
      case InformationPackage.UNION_PROPERTY__QUALIFIER:
        getQualifier().clear();
        getQualifier().addAll((Collection<? extends DataValue>)newValue);
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
      case InformationPackage.UNION_PROPERTY__QUALIFIER:
        getQualifier().clear();
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
      case InformationPackage.UNION_PROPERTY__QUALIFIER:
        return qualifier != null && !qualifier.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //UnionPropertyImpl