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
 * A representation of the model object '<em><b>Abstract Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsException <em>Is Exception</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsStream <em>Is Stream</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsOptional <em>Is Optional</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getKindOfRate <em>Kind Of Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getEffect <em>Effect</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getRate <em>Rate</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getParameterSet <em>Parameter Set</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A parameter is a specification of an argument used to pass information into or out of an invocation of a behavioral\r\nfeature.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='- A parameter cannot be a stream and exception at the same time.\r\n- An input parameter cannot be an exception.\r\n- Reentrant behaviors cannot have stream parameters.\r\n- Only in and inout parameters may have a delete effect. Only out, inout, and return parameters may have a create effect.\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Parameter' constraints='none'"
 * @generated
 */
public interface AbstractParameter extends AbstractTypedElement {





	/**
   * Returns the value of the '<em><b>Is Exception</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Exception</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Exception</em>' attribute.
   * @see #setIsException(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_IsException()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether an output parameter may emit a value to the exclusion of the other outputs\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Parameter::isException' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsException();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsException <em>Is Exception</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Exception</em>' attribute.
   * @see #isIsException()
   * @generated
   */

	void setIsException(boolean value);







	/**
   * Returns the value of the '<em><b>Is Stream</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Stream</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Stream</em>' attribute.
   * @see #setIsStream(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_IsStream()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether an input parameter may accept values while its behavior is executing, or whether an output parameter\r\npost values while the behavior is executing\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Parameter::isStream' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsStream();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsStream <em>Is Stream</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Stream</em>' attribute.
   * @see #isIsStream()
   * @generated
   */

	void setIsStream(boolean value);







	/**
   * Returns the value of the '<em><b>Is Optional</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Optional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Optional</em>' attribute.
   * @see #setIsOptional(boolean)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_IsOptional()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='specifies whether the parameter is optional or not\r\n[source: Capella study]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Capella AbstractParameter::isOptional is true if stereotype SysML::Activities::Optional is applied, false if not applied' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsOptional();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#isIsOptional <em>Is Optional</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Optional</em>' attribute.
   * @see #isIsOptional()
   * @generated
   */

	void setIsOptional(boolean value);







	/**
   * Returns the value of the '<em><b>Kind Of Rate</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.data.modellingcore.RateKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind Of Rate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Kind Of Rate</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @see #setKindOfRate(RateKind)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_KindOfRate()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='refer to RateKind enumeration description\r\n[source: Capella study]' constraints='none' type='refer to RateKind enumeration definition\r\n[source: Capella study]' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Capella AbstractParameter::kindOfRate is Capella RateKind::Continuous if stereotype SysML::Activities::Continuous is applied\r\nCapella AbstractParameter::kindOfRate is Capella RateKind::Discrete if stereotype SysML::Activities::Discrete is applied\r\nIf none is applied, Capella AbstractParameter::kindOfRate is Capella RateKind::Unspecified' constraints='Applied stereotype that inherits from SysML::Activities::Rate stereotype must be either SysML::Activities::Continuous or SysML::Activities::Discrete.\r\nIf none of both stereotypes are applied, kindOfRate is considered Unspecified'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	RateKind getKindOfRate();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getKindOfRate <em>Kind Of Rate</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind Of Rate</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.RateKind
   * @see #getKindOfRate()
   * @generated
   */

	void setKindOfRate(RateKind value);







	/**
   * Returns the value of the '<em><b>Effect</b></em>' attribute.
   * The literals are from the enumeration {@link org.polarsys.capella.common.data.modellingcore.ParameterEffectKind}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Effect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Effect</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.ParameterEffectKind
   * @see #setEffect(ParameterEffectKind)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_Effect()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the effect that the owner of the parameter has on values passed in or out of the parameter\r\n[source: UML superstructure v2.2]' constraints='none' type='see ParameterEffectKind definition' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Parameter::effect' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ParameterEffectKind getEffect();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getEffect <em>Effect</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Effect</em>' attribute.
   * @see org.polarsys.capella.common.data.modellingcore.ParameterEffectKind
   * @see #getEffect()
   * @generated
   */

	void setEffect(ParameterEffectKind value);







	/**
   * Returns the value of the '<em><b>Rate</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rate</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Rate</em>' containment reference.
   * @see #setRate(ValueSpecification)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_Rate()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the number of objects or values that flow in or out of the parameter per time interval while the behavior or operation is executing\r\n[source: SysML specification v1.1]' constraints='this field only makes sense if the parameter is a streaming one.\r\n[source: SysML specification v1.1]' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='SysML::Activities::Probability does not extend uml::Parameter' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getRate();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getRate <em>Rate</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rate</em>' containment reference.
   * @see #getRate()
   * @generated
   */

	void setRate(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Probability</b></em>' containment reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Probability</em>' containment reference.
   * @see #setProbability(ValueSpecification)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_Probability()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Likelihood that values will be output on a parameter set\r\n[source: SysML specification v1.1]' constraints='the probability should be a number between 0 and 1' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::specific' explanation='SysML::Activities::Probability does not extend uml::Parameter' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	ValueSpecification getProbability();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameter#getProbability <em>Probability</em>}' containment reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Probability</em>' containment reference.
   * @see #getProbability()
   * @generated
   */

	void setProbability(ValueSpecification value);







	/**
   * Returns the value of the '<em><b>Parameter Set</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getParameters <em>Parameters</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Set</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Set</em>' reference list.
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractParameter_ParameterSet()
   * @see org.polarsys.capella.common.data.modellingcore.AbstractParameterSet#getParameters
   * @model opposite="parameters"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The parameter sets containing the parameter. See AbstractParameterSet\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Parameter::parameterSet' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<AbstractParameterSet> getParameterSet();





} // AbstractParameter
