/*******************************************************************************
 * Copyright (c) 2017 ALTRAN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Altran - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Result</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.Result#getSituation <em>Situation</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getResult()
 * @model
 * @generated
 */

public interface Result extends NamedElement, ElementExtension {

  /**
   * Returns the value of the '<em><b>Situation</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.Situation}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Situation</em>' reference list isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Situation</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getResult_Situation()
   * @model
   * @generated
   */

  EList<Situation> getSituation();

} // Result
