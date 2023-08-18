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
package org.polarsys.capella.core.data.interaction;

import org.polarsys.capella.core.data.capellacore.Relationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Capability Include</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getIncluded <em>Included</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getInclusion <em>Inclusion</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityInclude()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractCapabilityInclude'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Include' stereotype='eng.AbstractCapabilityInclude'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The Include is a relationship between two use cases, implying that the behavior of the included use case is inserted into the behavior of the including use case. It is also a kind of NamedElement so that it can have a name in the context of its owning use case. \r\nThe including use case may only depend on the result (value) of the included use case. This value is obtained as a result of the execution of the included use case.\r\n\r\nThis concept is similar to UML Include concept.\r\n[source:UML Superstructure v2.2]\r\n\r\nNote that the included use case is not optional, and is always required for the including use case to execute correctly.' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed Include to map UML concept' reference\040documentation='n/a'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Include' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractCapabilityInclude extends Relationship {





	/**
   * Returns the value of the '<em><b>Included</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Included</em>' reference.
   * @see #setIncluded(AbstractCapability)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityInclude_Included()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='addition' featureOwner='Include'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='included'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability being included\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Include::addition' explanation='none' constraints='none'"
   * @generated
   */

	AbstractCapability getIncluded();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude#getIncluded <em>Included</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Included</em>' reference.
   * @see #getIncluded()
   * @generated
   */

	void setIncluded(AbstractCapability value);







	/**
   * Returns the value of the '<em><b>Inclusion</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inclusion</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Inclusion</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityInclude_Inclusion()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='includingCase' featureOwner='Include'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='inclusion'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability performing the inclusion of the other Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Include::includingCase' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='includes'"
   * @generated
   */

	AbstractCapability getInclusion();





} // AbstractCapabilityInclude
