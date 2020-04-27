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
 * A representation of the model object '<em><b>Edge Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getEdgeLayout()
 * @model
 * @generated
 */
public interface EdgeLayout extends ISemanticLayout {
  /**
   * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.test.diagram.layout.ju.layout.Location}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bendpoints</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bendpoints</em>' containment reference list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getEdgeLayout_Bendpoints()
   * @model containment="true"
   * @generated
   */
  EList<Location> getBendpoints();

  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(ISemanticLayout)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getEdgeLayout_Source()
   * @model
   * @generated
   */
  ISemanticLayout getSource();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(ISemanticLayout value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(ISemanticLayout)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getEdgeLayout_Target()
   * @model
   * @generated
   */
  ISemanticLayout getTarget();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(ISemanticLayout value);

} // EdgeLayout
