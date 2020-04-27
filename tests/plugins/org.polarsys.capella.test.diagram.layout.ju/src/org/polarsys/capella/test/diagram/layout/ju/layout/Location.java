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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getX <em>X</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getY <em>Y</em>}</li>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#isRelative <em>Relative</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getLocation()
 * @model
 * @generated
 */
public interface Location extends EObject {
  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>X</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #setX(int)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getLocation_X()
   * @model default="-1"
   * @generated
   */
  int getX();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #getX()
   * @generated
   */
  void setX(int value);

  /**
   * Returns the value of the '<em><b>Y</b></em>' attribute.
   * The default value is <code>"-1"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Y</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Y</em>' attribute.
   * @see #setY(int)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getLocation_Y()
   * @model default="-1"
   * @generated
   */
  int getY();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Y</em>' attribute.
   * @see #getY()
   * @generated
   */
  void setY(int value);

  /**
   * Returns the value of the '<em><b>Relative</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relative</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relative</em>' attribute.
   * @see #setRelative(boolean)
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getLocation_Relative()
   * @model default="false"
   * @generated
   */
  boolean isRelative();

  /**
   * Sets the value of the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#isRelative <em>Relative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Relative</em>' attribute.
   * @see #isRelative()
   * @generated
   */
  void setRelative(boolean value);

} // Location
