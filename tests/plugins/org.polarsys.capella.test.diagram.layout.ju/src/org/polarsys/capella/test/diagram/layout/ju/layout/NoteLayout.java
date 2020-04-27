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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Note Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout#getBounds <em>Bounds</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getNoteLayout()
 * @model
 * @generated
 */
public interface NoteLayout extends ISemanticLayout {
  /**
   * Returns the value of the '<em><b>Bounds</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounds</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounds</em>' containment reference.
   * @see #setBounds(Bounds)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getNoteLayout_Bounds()
   * @model containment="true"
   * @generated
   */
  Bounds getBounds();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout#getBounds <em>Bounds</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounds</em>' containment reference.
   * @see #getBounds()
   * @generated
   */
  void setBounds(Bounds value);

} // NoteLayout
