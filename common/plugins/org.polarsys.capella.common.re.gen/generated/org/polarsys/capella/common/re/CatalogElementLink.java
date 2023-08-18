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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog Element Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementLink#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementLink#getTarget <em>Target</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementLink#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementLink#getUnsynchronizedFeatures <em>Unsynchronized Features</em>}</li>
 *   <li>{@link org.polarsys.capella.common.re.CatalogElementLink#isSuffixed <em>Suffixed</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink()
 * @model
 * @generated
 */

public interface CatalogElementLink extends ReAbstractElement {





	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(CatalogElement)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink_Source()
   * @model
   * @generated
   */

	CatalogElement getSource();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElementLink#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

	void setSource(CatalogElement value);







	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(EObject)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink_Target()
   * @model
   * @generated
   */

	EObject getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElementLink#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(EObject value);







	/**
   * Returns the value of the '<em><b>Origin</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Origin</em>' reference.
   * @see #setOrigin(CatalogElementLink)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink_Origin()
   * @model
   * @generated
   */

	CatalogElementLink getOrigin();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElementLink#getOrigin <em>Origin</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Origin</em>' reference.
   * @see #getOrigin()
   * @generated
   */

	void setOrigin(CatalogElementLink value);







	/**
   * Returns the value of the '<em><b>Unsynchronized Features</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unsynchronized Features</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Unsynchronized Features</em>' attribute list.
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink_UnsynchronizedFeatures()
   * @model
   * @generated
   */

	EList<String> getUnsynchronizedFeatures();




	/**
   * Returns the value of the '<em><b>Suffixed</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suffixed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Suffixed</em>' attribute.
   * @see #setSuffixed(boolean)
   * @see org.polarsys.capella.common.re.RePackage#getCatalogElementLink_Suffixed()
   * @model
   * @generated
   */

	boolean isSuffixed();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.re.CatalogElementLink#isSuffixed <em>Suffixed</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suffixed</em>' attribute.
   * @see #isSuffixed()
   * @generated
   */

	void setSuffixed(boolean value);





} // CatalogElementLink
