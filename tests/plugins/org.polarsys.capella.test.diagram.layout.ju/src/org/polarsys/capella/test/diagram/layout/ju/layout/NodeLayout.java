/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
 * A representation of the model object '<em><b>Node Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getBounds <em>Bounds</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getOwnedLayouts <em>Owned Layouts</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getNodeLayout()
 * @model
 * @generated
 */
public interface NodeLayout extends ISemanticLayout {
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
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getNodeLayout_Bounds()
   * @model containment="true"
   * @generated
   */
  Bounds getBounds();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getBounds <em>Bounds</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounds</em>' containment reference.
   * @see #getBounds()
   * @generated
   */
  void setBounds(Bounds value);

  /**
   * Returns the value of the '<em><b>Owned Layouts</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.test.diagram.layout.ju.layout.ILayout}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owned Layouts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Layouts</em>' containment reference list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getNodeLayout_OwnedLayouts()
   * @model containment="true"
   * @generated
   */
  EList<ILayout> getOwnedLayouts();

} // NodeLayout
