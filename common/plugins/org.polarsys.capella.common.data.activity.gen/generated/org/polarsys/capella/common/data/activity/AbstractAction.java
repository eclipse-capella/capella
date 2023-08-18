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
package org.polarsys.capella.common.data.activity;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractAction#getLocalPrecondition <em>Local Precondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractAction#getLocalPostcondition <em>Local Postcondition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractAction#getContext <em>Context</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractAction#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.AbstractAction#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An Action is a named element that is the fundamental unit of executable functionality. The execution of an action\r\nrepresents some transformation or processing in the modeled system, be it a computer system or otherwise\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Activity' constraints='none'"
 * @generated
 */

public interface AbstractAction extends ExecutableNode, AbstractNamedElement {





	/**
   * Returns the value of the '<em><b>Local Precondition</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Precondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Local Precondition</em>' containment reference.
   * @see #setLocalPrecondition(AbstractConstraint)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction_LocalPrecondition()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The preconditions for an action define conditions that must be true when the action is invoked.\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Behavior::precondition' explanation='none' constraints='none'"
   * @generated
   */

	AbstractConstraint getLocalPrecondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AbstractAction#getLocalPrecondition <em>Local Precondition</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Local Precondition</em>' containment reference.
   * @see #getLocalPrecondition()
   * @generated
   */

	void setLocalPrecondition(AbstractConstraint value);







	/**
   * Returns the value of the '<em><b>Local Postcondition</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Postcondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Local Postcondition</em>' containment reference.
   * @see #setLocalPostcondition(AbstractConstraint)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction_LocalPostcondition()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The postconditions for an action define conditions that will be true when the invocation of the action completes\r\nsuccessfully, assuming the preconditions were satisfied\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Behavior::postcondition' explanation='none' constraints='none'"
   * @generated
   */

	AbstractConstraint getLocalPostcondition();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AbstractAction#getLocalPostcondition <em>Local Postcondition</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Local Postcondition</em>' containment reference.
   * @see #getLocalPostcondition()
   * @generated
   */

	void setLocalPostcondition(AbstractConstraint value);







	/**
   * Returns the value of the '<em><b>Context</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' reference.
   * @see #setContext(AbstractType)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction_Context()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The classifier that owns the behavior of which this action is a part.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Behavior::context#keyword::none' explanation='' constraints='none'"
   * @generated
   */

	AbstractType getContext();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.AbstractAction#getContext <em>Context</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' reference.
   * @see #getContext()
   * @generated
   */

	void setContext(AbstractType value);







	/**
   * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.InputPin}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Inputs</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction_Inputs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The ordered set of input pins connected to the Action. These are among the total set of inputs\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::node' explanation='mapped to either InputPin or ActivityParameterNode, depending on whether the associated function is an Activity, or a callBehaviorAction to an Activity' constraints='uml::Activity::node elements on which activity::InputPin stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<InputPin> getInputs();







	/**
   * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.OutputPin}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Outputs</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getAbstractAction_Outputs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The ordered set of output pins connected to the Action. The action places its results onto pins in this set\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Activity::node' explanation='mapped to either OutputPin or ActivityParameterNode, depending on whether the associated function is an Activity, or a callBehaviorAction to an Activity' constraints='uml::Activity::node elements on which activity::OutputPin stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<OutputPin> getOutputs();





} // AbstractAction
