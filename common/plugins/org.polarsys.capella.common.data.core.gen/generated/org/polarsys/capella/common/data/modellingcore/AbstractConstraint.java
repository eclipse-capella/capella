/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractConstraint#getConstrainedElements <em>Constrained Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractConstraint()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies a constraint applying to a given set of model elements\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
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
	 * @generated
	 */

	EList<ModelElement> getConstrainedElements();





} // AbstractConstraint
