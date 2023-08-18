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
package org.polarsys.capella.core.data.information.datavalue;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOwnedOperand <em>Owned Operand</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getUnaryExpression()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='UnaryExpression'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Expression' stereotype='eng.UnaryExpression'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specification of a condition that can only evaluate to \"true\" or \"false\"\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Expression' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface UnaryExpression extends AbstractExpressionValue {





	/**
   * Returns the value of the '<em><b>Operator</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.datavalue.UnaryOperator}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryOperator
   * @see #setOperator(UnaryOperator)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getUnaryExpression_Operator()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operator applying to the operand\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	UnaryOperator getOperator();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOperator <em>Operator</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datavalue.UnaryOperator
   * @see #getOperator()
   * @generated
   */

	void setOperator(UnaryOperator value);







	/**
   * Returns the value of the '<em><b>Owned Operand</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Operand</em>' containment reference.
   * @see #setOwnedOperand(DataValue)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getUnaryExpression_OwnedOperand()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='operands'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of the operands being part of the boolean expression\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Expression::operand' explanation='_todo_ Check that uml::Expression::operand contains BooleanValue' constraints='uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedOperand();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.UnaryExpression#getOwnedOperand <em>Owned Operand</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Operand</em>' containment reference.
   * @see #getOwnedOperand()
   * @generated
   */

	void setOwnedOperand(DataValue value);





} // UnaryExpression
