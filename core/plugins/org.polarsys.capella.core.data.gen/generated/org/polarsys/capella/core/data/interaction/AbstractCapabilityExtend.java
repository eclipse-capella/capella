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
 * A representation of the model object '<em><b>Abstract Capability Extend</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtended <em>Extended</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation <em>Extension Location</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtend()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractCapabilityExtend'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Extend' stereotype='eng.AbstractCapabilityExtend'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A relationship from an extending use case to an extended use case that specifies how and when the behavior defined in\r\nthe extending use case can be inserted into the behavior defined in the extended use case.\r\n\r\nThis concept is similar to UML Extend concept.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='Should be renamed Extend to map UML concept' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Extend' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractCapabilityExtend extends Relationship {





	/**
   * Returns the value of the '<em><b>Extended</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extended</em>' reference.
   * @see #setExtended(AbstractCapability)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtend_Extended()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='extendedCase' featureOwner='Extend'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extended'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability being extended\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Extend::extendedCase' explanation='none' constraints='none'"
   * @generated
   */

	AbstractCapability getExtended();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtended <em>Extended</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extended</em>' reference.
   * @see #getExtended()
   * @generated
   */

	void setExtended(AbstractCapability value);







	/**
   * Returns the value of the '<em><b>Extension</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extension</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtend_Extension()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='extension' featureOwner='Extend'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extension'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability that realizes the extension\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Extend::extension' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='^extends'"
   * @generated
   */

	AbstractCapability getExtension();




	/**
   * Returns the value of the '<em><b>Extension Location</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getExtendLinks <em>Extend Links</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Location</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extension Location</em>' reference.
   * @see #setExtensionLocation(AbstractCapabilityExtensionPoint)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtend_ExtensionLocation()
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getExtendLinks
   * @model opposite="extendLinks"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='extensionLocation' featureOwner='Extend'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extensionLocation'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the extension point to which the extending Capability is attached\r\n[source: Capella study]' constraints='this extension location must be one of the extensions of the Capability pointed by the  \"extended\" reference\r\n' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Extend::extensionLocation' explanation='none' constraints='Multiplicity must be [0..1]'"
   * @generated
   */

	AbstractCapabilityExtensionPoint getExtensionLocation();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation <em>Extension Location</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extension Location</em>' reference.
   * @see #getExtensionLocation()
   * @generated
   */

	void setExtensionLocation(AbstractCapabilityExtensionPoint value);





} // AbstractCapabilityExtend
