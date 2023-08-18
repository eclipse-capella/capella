/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.Involvement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Path Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getNextInvolvements <em>Next Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getPreviousInvolvements <em>Previous Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedElement <em>Involved Element</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement#getInvolvedComponent <em>Involved Component</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPathInvolvement()
 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalPathInvolvement extends Involvement {





	/**
   * Returns the value of the '<em><b>Next Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Next Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPathInvolvement_NextInvolvements()
   * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalPathInvolvement> getNextInvolvements();







	/**
   * Returns the value of the '<em><b>Previous Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Previous Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPathInvolvement_PreviousInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPathInvolvement.nextInvolvements(target, self);\r\n// TODO understand why we should verify that target is in the same path than self ...\r\nPhysicalPath.ownedPhysicalPathInvolvements(pp, self);\r\nPhysicalPath.ownedPhysicalPathInvolvements(pp, target);'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalPathInvolvement> getPreviousInvolvements();







	/**
   * Returns the value of the '<em><b>Involved Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Element</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPathInvolvement_InvolvedElement()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involved'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	AbstractPathInvolvedElement getInvolvedElement();







	/**
   * Returns the value of the '<em><b>Involved Component</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Component</em>' reference.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPathInvolvement_InvolvedComponent()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPathInvolvement.involved(self, part);\r\nPart.type(part, target);'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	Component getInvolvedComponent();





} // PhysicalPathInvolvement
