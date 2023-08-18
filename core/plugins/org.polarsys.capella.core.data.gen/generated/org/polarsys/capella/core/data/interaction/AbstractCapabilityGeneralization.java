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
 * A representation of the model object '<em><b>Abstract Capability Generalization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSuper <em>Super</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSub <em>Sub</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityGeneralization()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractCapabilityGeneralization'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Generalization' stereotype='eng.AbstractCapabilityGeneralization'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A specific kind of generalization link between Capabilities.\r\n[source: Capella study]\r\n\r\nThe generalization is useful for Capability reuse (override or extension of Capability).' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Generalization' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractCapabilityGeneralization extends Relationship {





	/**
   * Returns the value of the '<em><b>Super</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super</em>' reference.
   * @see #setSuper(AbstractCapability)
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityGeneralization_Super()
   * @model required="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='general' featureOwner='Generalization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='super'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the parent Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Generalization::general' explanation='none' constraints='none'"
   * @generated
   */

	AbstractCapability getSuper();




	/**
   * Sets the value of the '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization#getSuper <em>Super</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super</em>' reference.
   * @see #getSuper()
   * @generated
   */

	void setSuper(AbstractCapability value);







	/**
   * Returns the value of the '<em><b>Sub</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityGeneralization_Sub()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='general' featureOwner='Generalization'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='super'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the child Capability\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Generalization::specific' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='superGeneralizations'"
   * @generated
   */

	AbstractCapability getSub();





} // AbstractCapabilityGeneralization
