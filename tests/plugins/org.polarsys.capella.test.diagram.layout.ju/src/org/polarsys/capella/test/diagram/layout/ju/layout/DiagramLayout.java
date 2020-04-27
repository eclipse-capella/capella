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
 * A representation of the model object '<em><b>Diagram Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getOwnedLayouts <em>Owned Layouts</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getAppliedLayers <em>Applied Layers</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getDiagramLayout()
 * @model
 * @generated
 */
public interface DiagramLayout extends ISemanticLayout {
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
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getDiagramLayout_OwnedLayouts()
   * @model containment="true"
   * @generated
   */
  EList<ILayout> getOwnedLayouts();

  /**
   * Returns the value of the '<em><b>Applied Layers</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Applied Layers</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Layers</em>' attribute list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getDiagramLayout_AppliedLayers()
   * @model
   * @generated
   */
  EList<String> getAppliedLayers();

  /**
   * Returns the value of the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Synchronized</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Synchronized</em>' attribute.
   * @see #setSynchronized(boolean)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getDiagramLayout_Synchronized()
   * @model
   * @generated
   */
  boolean isSynchronized();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#isSynchronized <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Synchronized</em>' attribute.
   * @see #isSynchronized()
   * @generated
   */
  void setSynchronized(boolean value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' attribute.
   * @see #setDescription(String)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getDiagramLayout_Description()
   * @model
   * @generated
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getDescription <em>Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);

} // DiagramLayout
