/*******************************************************************************
 * Copyright (c) 2017 Altran.
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Comparison</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.polarsys.capella.vp.ms.Comparison#getConfiguration1 <em>Configuration1</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.Comparison#getSituation <em>Situation</em>}</li>
 * <li>{@link org.polarsys.capella.vp.ms.Comparison#getConfiguration2 <em>Configuration2</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.vp.ms.MsPackage#getComparison()
 * @model annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraint ExtendedElement='
 *        http://www.polarsys.org/capella/core/cs/1.1.0#//Component'"
 *        annotation="http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping Mapping='
 *        platform:/plugin/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore#//Component'"
 * @generated
 */

public interface Comparison extends NamedElement, ElementExtension {

  /**
   * Returns the value of the '<em><b>Configuration1</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.CSConfiguration}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Configuration1</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Configuration1</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getComparison_Configuration1()
   * @model
   * @generated
   */

  EList<CSConfiguration> getConfiguration1();

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
   * @see org.polarsys.capella.vp.ms.MsPackage#getComparison_Situation()
   * @model
   * @generated
   */

  EList<Situation> getSituation();

  /**
   * Returns the value of the '<em><b>Configuration2</b></em>' reference list. The list contents are of type
   * {@link org.polarsys.capella.vp.ms.CSConfiguration}.
   * 
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Configuration2</em>' reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Configuration2</em>' reference list.
   * @see org.polarsys.capella.vp.ms.MsPackage#getComparison_Configuration2()
   * @model
   * @generated
   */

  EList<CSConfiguration> getConfiguration2();

} // Comparison
