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
package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Chain Involvement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getNextFunctionalChainInvolvements <em>Next Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getPreviousFunctionalChainInvolvements <em>Previous Functional Chain Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement#getInvolvedElement <em>Involved Element</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvement()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the involvement of a model element in a functional chain\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Dependency' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface FunctionalChainInvolvement extends Involvement {





	/**
   * Returns the value of the '<em><b>Next Functional Chain Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Functional Chain Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Next Functional Chain Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvement_NextFunctionalChainInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern FunctionalChainInvolvement_nextFunctionalChainInvolvements(self : FunctionalChainInvolvement, target : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvementLink.source(target, self);\r\n} or {\r\n\tFunctionalChainInvolvementLink.target(self, target);\r\n}'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainInvolvement> getNextFunctionalChainInvolvements();







	/**
   * Returns the value of the '<em><b>Previous Functional Chain Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Functional Chain Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Previous Functional Chain Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern FunctionalChainInvolvement__previousFunctionalChainInvolvements(self : FunctionalChainInvolvement, target : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvementLink.target(target, self);\r\n} or {\r\n\tFunctionalChainInvolvementLink.source(self, target);\r\n}'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainInvolvement> getPreviousFunctionalChainInvolvements();







	/**
   * Returns the value of the '<em><b>Involved Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Involved Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Involved Element</em>' reference.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvement_InvolvedElement()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='involved'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	InvolvedElement getInvolvedElement();





} // FunctionalChainInvolvement
