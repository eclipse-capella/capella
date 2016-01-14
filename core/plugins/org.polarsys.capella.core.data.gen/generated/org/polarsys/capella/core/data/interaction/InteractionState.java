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
package org.polarsys.capella.core.data.interaction;

import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.fa.AbstractFunction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState <em>Related Abstract State</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction <em>Related Abstract Function</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.InteractionState#getCovered <em>Covered</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionState()
 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 * @generated
 */
public interface InteractionState extends InteractionFragment {





	/**
	 * Returns the value of the '<em><b>Related Abstract State</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @deprecated : relatedAbstractState shall not be used anymore
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Abstract State</em>' reference.
	 * @see #setRelatedAbstractState(AbstractState)
	 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionState_RelatedAbstractState()
	 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
	 * @generated
	 */

	AbstractState getRelatedAbstractState();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState <em>Related Abstract State</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Related Abstract State</em>' reference.
	 * @see #getRelatedAbstractState()
	 * @generated
	 */

	void setRelatedAbstractState(AbstractState value);







	/**
	 * Returns the value of the '<em><b>Related Abstract Function</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @deprecated : relatedAbstractFunction shall not be used anymore
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Abstract Function</em>' reference.
	 * @see #setRelatedAbstractFunction(AbstractFunction)
	 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionState_RelatedAbstractFunction()
	 * @model annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='n/a' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
	 * @generated
	 */

	AbstractFunction getRelatedAbstractFunction();




	/**
	 * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction <em>Related Abstract Function</em>}' reference.

	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Related Abstract Function</em>' reference.
	 * @see #getRelatedAbstractFunction()
	 * @generated
	 */

	void setRelatedAbstractFunction(AbstractFunction value);







	/**
	 * Returns the value of the '<em><b>Covered</b></em>' reference.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Covered</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Covered</em>' reference.
	 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getInteractionState_Covered()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='covered' featureOwner='InteractionFragment'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='instanceRole'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the instance role (lifeline) to which this interaction endpoint is attached\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::InteractionFragment::covered' explanation='none' constraints='Multiplicity must be [1..1]'"
	 * @generated
	 */

	InstanceRole getCovered();





} // InteractionState
