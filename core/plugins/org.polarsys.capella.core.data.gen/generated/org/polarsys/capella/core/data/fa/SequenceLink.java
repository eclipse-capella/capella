/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Constraint;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.SequenceLink#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.SequenceLink#getLinks <em>Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.SequenceLink#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.SequenceLink#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getSequenceLink()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='express precedence between executions of represented functions\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface SequenceLink extends CapellaElement, ReferenceHierarchyContext {





	/**
   * Returns the value of the '<em><b>Condition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' reference.
   * @see #setCondition(Constraint)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getSequenceLink_Condition()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getCondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.SequenceLink#getCondition <em>Condition</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' reference.
   * @see #getCondition()
   * @generated
   */

	void setCondition(Constraint value);







	/**
   * Returns the value of the '<em><b>Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Links</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getSequenceLink_Links()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainInvolvementLink> getLinks();







	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(SequenceLinkEnd)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getSequenceLink_Source()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	SequenceLinkEnd getSource();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.SequenceLink#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

	void setSource(SequenceLinkEnd value);







	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(SequenceLinkEnd)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getSequenceLink_Target()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	SequenceLinkEnd getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.SequenceLink#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(SequenceLinkEnd value);





} // SequenceLink
