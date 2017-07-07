/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.layout;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout#getOwnedLayouts <em>Owned Layouts</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getSessionLayout()
 * @model
 * @generated
 */
public interface SessionLayout extends EObject {
  /**
   * Returns the value of the '<em><b>Owned Layouts</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owned Layouts</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Layouts</em>' containment reference list.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#getSessionLayout_OwnedLayouts()
   * @model containment="true"
   * @generated
   */
  EList<DiagramLayout> getOwnedLayouts();

} // SessionLayout
