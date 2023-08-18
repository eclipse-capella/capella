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
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.ExchangeItem;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Chain Involvement Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangeContext <em>Exchange Context</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangedItems <em>Exchanged Items</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getSource <em>Source</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementLink()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies the involvement of a model element in form of link in a functional chain\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface FunctionalChainInvolvementLink extends FunctionalChainInvolvement, ReferenceHierarchyContext {





	/**
   * Returns the value of the '<em><b>Exchange Context</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exchange Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exchange Context</em>' reference.
   * @see #setExchangeContext(Constraint)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementLink_ExchangeContext()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	Constraint getExchangeContext();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getExchangeContext <em>Exchange Context</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Exchange Context</em>' reference.
   * @see #getExchangeContext()
   * @generated
   */

	void setExchangeContext(Constraint value);







	/**
   * Returns the value of the '<em><b>Exchanged Items</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.ExchangeItem}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exchanged Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Exchanged Items</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementLink_ExchangedItems()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the ExchangeItems carried by this Functional Chain Involvement Link' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ExchangeItem> getExchangedItems();




	/**
   * Returns the value of the '<em><b>Source</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(FunctionalChainInvolvementFunction)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementLink_Source()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	FunctionalChainInvolvementFunction getSource();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getSource <em>Source</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */

	void setSource(FunctionalChainInvolvementFunction value);




	/**
   * Returns the value of the '<em><b>Target</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(FunctionalChainInvolvementFunction)
   * @see org.polarsys.capella.core.data.fa.FaPackage#getFunctionalChainInvolvementLink_Target()
   * @model annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	FunctionalChainInvolvementFunction getTarget();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink#getTarget <em>Target</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */

	void setTarget(FunctionalChainInvolvementFunction value);





} // FunctionalChainInvolvementLink
