/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Functional Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getFunction <em>Function</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation#getBlock <em>Block</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentFunctionalAllocation()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='FunctionAllocationToLogicalComponent'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency' stereotype='eng.FunctionAllocationToLogicalComponent'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a allocation link between a function and a component\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='SysML::Allocations::Allocate' explanation='none' constraints='none'"
 * @generated
 */
public interface ComponentFunctionalAllocation extends AbstractFunctionAllocation {





	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.AbstractFunction#getComponentFunctionalAllocations <em>Component Functional Allocations</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentFunctionalAllocation_Function()
	 * @see org.polarsys.capella.core.data.fa.AbstractFunction#getComponentFunctionalAllocations
	 * @model opposite="componentFunctionalAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the function involved in this allocation link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	AbstractFunction getFunction();







	/**
	 * Returns the value of the '<em><b>Block</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getFunctionalAllocations <em>Functional Allocations</em>}'.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block</em>' reference.
	 * @see org.polarsys.capella.core.data.fa.FaPackage#getComponentFunctionalAllocation_Block()
	 * @see org.polarsys.capella.core.data.fa.AbstractFunctionalBlock#getFunctionalAllocations
	 * @model opposite="functionalAllocations" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the block involved in this allocation link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
	 * @generated
	 */

	AbstractFunctionalBlock getBlock();





} // ComponentFunctionalAllocation
