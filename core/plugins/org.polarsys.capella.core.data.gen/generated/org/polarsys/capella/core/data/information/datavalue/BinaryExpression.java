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
 * A representation of the model object '<em><b>Binary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedLeftOperand <em>Owned Left Operand</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedRightOperand <em>Owned Right Operand</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getBinaryExpression()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='BinaryExpression'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Expression' stereotype='eng.BinaryExpression'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specification of a condition that can only evaluate to \"true\" or \"false\"\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Expression' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface BinaryExpression extends AbstractExpressionValue {





	/**
   * Returns the value of the '<em><b>Operator</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.core.data.information.datavalue.BinaryOperator}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryOperator
   * @see #setOperator(BinaryOperator)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getBinaryExpression_Operator()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the operator between the left and right operands\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	BinaryOperator getOperator();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOperator <em>Operator</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see org.polarsys.capella.core.data.information.datavalue.BinaryOperator
   * @see #getOperator()
   * @generated
   */

	void setOperator(BinaryOperator value);







	/**
   * Returns the value of the '<em><b>Owned Left Operand</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Left Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Left Operand</em>' containment reference.
   * @see #setOwnedLeftOperand(DataValue)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getBinaryExpression_OwnedLeftOperand()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='operands'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of the operands being part of the boolean expression\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Expression::operand' explanation='_todo_ Check that uml::Expression::operand contains BooleanValue' constraints='uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedLeftOperand();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedLeftOperand <em>Owned Left Operand</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Left Operand</em>' containment reference.
   * @see #getOwnedLeftOperand()
   * @generated
   */

	void setOwnedLeftOperand(DataValue value);







	/**
   * Returns the value of the '<em><b>Owned Right Operand</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Right Operand</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Right Operand</em>' containment reference.
   * @see #setOwnedRightOperand(DataValue)
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#getBinaryExpression_OwnedRightOperand()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='clientDependency' featureOwner='NamedElement'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='operands'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='list of the operands being part of the boolean expression\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Expression::operand' explanation='_todo_ Check that uml::Expression::operand contains BooleanValue' constraints='uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	DataValue getOwnedRightOperand();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.information.datavalue.BinaryExpression#getOwnedRightOperand <em>Owned Right Operand</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owned Right Operand</em>' containment reference.
   * @see #getOwnedRightOperand()
   * @generated
   */

	void setOwnedRightOperand(DataValue value);





} // BinaryExpression
