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
package org.polarsys.capella.common.re;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.common.re.RePackage
 * @generated
 */
public interface ReFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	ReFactory eINSTANCE = org.polarsys.capella.common.re.impl.ReFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Rec Catalog</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Rec Catalog</em>'.
   * @generated
   */
	RecCatalog createRecCatalog();

	/**
   * Returns a new object of class '<em>Grouping Element Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Grouping Element Pkg</em>'.
   * @generated
   */
	GroupingElementPkg createGroupingElementPkg();

	/**
   * Returns a new object of class '<em>Catalog Element Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Catalog Element Pkg</em>'.
   * @generated
   */
	CatalogElementPkg createCatalogElementPkg();

	/**
   * Returns a new object of class '<em>Catalog Element Link</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Catalog Element Link</em>'.
   * @generated
   */
	CatalogElementLink createCatalogElementLink();

	/**
   * Returns a new object of class '<em>Catalog Element</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Catalog Element</em>'.
   * @generated
   */
	CatalogElement createCatalogElement();

	/**
   * Returns a new object of class '<em>Compliancy Definition Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Compliancy Definition Pkg</em>'.
   * @generated
   */
	CompliancyDefinitionPkg createCompliancyDefinitionPkg();

	/**
   * Returns a new object of class '<em>Compliancy Definition</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Compliancy Definition</em>'.
   * @generated
   */
	CompliancyDefinition createCompliancyDefinition();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	RePackage getRePackage();

	//begin-capella-code
	
	//end-capella-code
} //ReFactory