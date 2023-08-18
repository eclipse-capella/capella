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

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Capability Extension Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getAbstractCapability <em>Abstract Capability</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint#getExtendLinks <em>Extend Links</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtensionPoint()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='AbstractCapabilityExtensionPoint'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='ExtensionPoint' stereotype='eng.AbstractCapabilityExtensionPoint'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An extension point identifies a point in the behavior of a use case where that behavior can be extended by the behavior of\r\nsome other (extending) use case, as specified by an extend relationship.\r\n\r\nThis concept is similar to UML ExtensionPoint.\r\n[source:UML Superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational, system, logical, physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::ExtensionPoint' explanation='none' constraints='none'"
 * @generated
 */
public interface AbstractCapabilityExtensionPoint extends NamedRelationship {





	/**
   * Returns the value of the '<em><b>Abstract Capability</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Capability</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract Capability</em>' reference.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtensionPoint_AbstractCapability()
   * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='useCase' featureOwner='ExtensionPoint'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='abstractCapability'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Capability to which this extension point belongs\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ExtensionPoint::useCase' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='opposite' viatra.expression='abstractCapabilityExtensionPoints'"
   * @generated
   */

	AbstractCapability getAbstractCapability();




	/**
   * Returns the value of the '<em><b>Extend Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation <em>Extension Location</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extend Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Extend Links</em>' reference list.
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#getAbstractCapabilityExtensionPoint_ExtendLinks()
   * @see org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend#getExtensionLocation
   * @model opposite="extensionLocation"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping umlOppositeReference='extensionLocation' umlOppositeReferenceOwner='Extend'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='extendLinks'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the extension links starting from this extension point\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Opposite reference of uml::Extend::extensionLocation' constraints='uml::NamedElement::clientDependency elements on which AbstractCapabilityExtend stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   * @generated
   */

	EList<AbstractCapabilityExtend> getExtendLinks();





} // AbstractCapabilityExtensionPoint
