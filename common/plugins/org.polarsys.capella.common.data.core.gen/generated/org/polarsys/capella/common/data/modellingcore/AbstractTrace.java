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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getTargetElement <em>Target Element</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getSourceElement <em>Source Element</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractTrace()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractTrace'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Dependency'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='abstract base class supporting the ability to define a trace relationship between two model elements\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::Dependency' constraints='none'"
 * @generated
 */
public interface AbstractTrace extends TraceableElement {





	/**
   * Returns the value of the '<em><b>Target Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Element</em>' reference.
   * @see #setTargetElement(TraceableElement)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractTrace_TargetElement()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='supplier' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='target'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the target/end of the trace link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::supplier' explanation='none' constraints='uml::Dependency::supplier elements on which TraceableElement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]\r\n'"
   * @generated
   */

	TraceableElement getTargetElement();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getTargetElement <em>Target Element</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Element</em>' reference.
   * @see #getTargetElement()
   * @generated
   */

	void setTargetElement(TraceableElement value);







	/**
   * Returns the value of the '<em><b>Source Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Element</em>' reference.
   * @see #setSourceElement(TraceableElement)
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#getAbstractTrace_SourceElement()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='client' featureOwner='Dependency'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='source'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the source/beginning of the trace link\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Dependency::client' explanation='none' constraints='uml::Dependency::client elements on which TraceableElement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]'"
   * @generated
   */

	TraceableElement getSourceElement();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.modellingcore.AbstractTrace#getSourceElement <em>Source Element</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source Element</em>' reference.
   * @see #getSourceElement()
   * @generated
   */

	void setSourceElement(TraceableElement value);





} // AbstractTrace
