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
package org.polarsys.capella.common.data.modellingcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getConstrainedElements <em>Constrained Elements</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractConstraint()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies a constraint applying to a given set of model elements\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='The value specification for a constraint must evaluate to a Boolean value.\r\n\r\nEvaluating the value specification for a constraint must not have side effects\r\n\r\nA constraint cannot be applied to itself.' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Constraint' constraints='none'"
 * @generated
 */
public interface AbstractConstraint extends ModelElement {





	/**
   * Returns the value of the '<em><b>Constrained Elements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.ModelElement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constrained Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Constrained Elements</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractConstraint_ConstrainedElements()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the model elements being involved in the definition of this constraint\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Constraint::constrainedElement' explanation='' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<ModelElement> getConstrainedElements();

	/**
   * Returns the value of the '<em><b>Owned Specification</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Specification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Specification</em>' containment reference.
   * @see #setOwnedSpecification(ValueSpecification)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractConstraint_OwnedSpecification()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A condition that must be true when evaluated in order for the constraint to be satisfied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getOwnedSpecification();

	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getOwnedSpecification <em>Owned Specification</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Specification</em>' containment reference.
   * @see #getOwnedSpecification()
   * @generated
   */

	void setOwnedSpecification(ValueSpecification value);

	/**
   * Returns the value of the '<em><b>Context</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' reference.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractConstraint_Context()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='ownedConstraints'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the element that is the context for evaluating this constraint, which is the Constraint\'s parent element.'"
   * @generated
   */

	ModelElement getContext();





} // AbstractConstraint
