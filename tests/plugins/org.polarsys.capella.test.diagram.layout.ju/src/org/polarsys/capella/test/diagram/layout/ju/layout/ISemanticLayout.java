/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISemantic Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getId <em>Id</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getName <em>Name</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getActualMapping <em>Actual Mapping</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedFilters <em>Applied Filters</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedStyles <em>Applied Styles</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ISemanticLayout extends ILayout {
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actual Mapping</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actual Mapping</em>' attribute.
   * @see #setActualMapping(String)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout_ActualMapping()
   * @model
   * @generated
   */
  String getActualMapping();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getActualMapping <em>Actual Mapping</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actual Mapping</em>' attribute.
   * @see #getActualMapping()
   * @generated
   */
  void setActualMapping(String value);

  /**
   * Returns the value of the '<em><b>Applied Filters</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Applied Filters</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Filters</em>' attribute list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout_AppliedFilters()
   * @model
   * @generated
   */
  EList<String> getAppliedFilters();

  /**
   * Returns the value of the '<em><b>Applied Styles</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Applied Styles</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Styles</em>' attribute list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getISemanticLayout_AppliedStyles()
   * @model
   * @generated
   */
  EList<String> getAppliedStyles();

} // ISemanticLayout
