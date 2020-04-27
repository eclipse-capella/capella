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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage
 * @generated
 */
public interface LayoutFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LayoutFactory eINSTANCE = org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Diagram Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Diagram Layout</em>'.
   * @generated
   */
  DiagramLayout createDiagramLayout();

  /**
   * Returns a new object of class '<em>Edge Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Edge Layout</em>'.
   * @generated
   */
  EdgeLayout createEdgeLayout();

  /**
   * Returns a new object of class '<em>Node Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Node Layout</em>'.
   * @generated
   */
  NodeLayout createNodeLayout();

  /**
   * Returns a new object of class '<em>Note Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Note Layout</em>'.
   * @generated
   */
  NoteLayout createNoteLayout();

  /**
   * Returns a new object of class '<em>Session Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Session Layout</em>'.
   * @generated
   */
  SessionLayout createSessionLayout();

  /**
   * Returns a new object of class '<em>Bounds</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bounds</em>'.
   * @generated
   */
  Bounds createBounds();

  /**
   * Returns a new object of class '<em>Location</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Location</em>'.
   * @generated
   */
  Location createLocation();

  /**
   * Returns a new object of class '<em>Size</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Size</em>'.
   * @generated
   */
  Size createSize();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  LayoutPackage getLayoutPackage();

} //LayoutFactory
