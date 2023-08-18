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
package org.polarsys.capella.core.data.information;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.NamedRelationship;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.Association#getOwnedMembers <em>Owned Members</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.Association#getNavigableMembers <em>Navigable Members</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getAssociation()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='Association'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Association' stereotype='eng.Association'"
 *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An association specifies a semantic relationship that can occur between typed instances. It has at least two ends\r\nrepresented by properties, each of which is connected to the type of the end. More than one end of the association may\r\nhave the same type.\r\nAn end property of an association that is owned by an end class or that is a navigable owned end of the association\r\nindicates that the association is navigable from the opposite ends; otherwise, the association is not navigable from the\r\nopposite ends.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='n/a' usage\040examples='n/a' constraints='- An association specializing another association has the same number of ends as the other association.\r\nself.parents()-&gt;forAll(p | p.memberEnd.size() = self.memberEnd.size())\r\n- When an association specializes another association, every end of the specific association corresponds to an end of the\r\ngeneral association, and the specific end reaches the same type or a subtype of the more general end.\r\n- endType is derived from the types of the member ends.\r\nself.endType = self.memberEnd-&gt;collect(e | e.type)\r\n- Only binary associations can be aggregations.\r\nself.memberEnd-&gt;exists(aggregation &lt;&gt; Aggregation::none) implies self.memberEnd-&gt;size() = 2\r\n- Association ends of associations with more than two ends must be owned by the association.\r\nif memberEnd-&gt;size() &gt; 2 then ownedEnd-&gt;includesAll(memberEnd)\r\n[source: UML superstructure v2.2]' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Association' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface Association extends NamedRelationship {





	/**
   * Returns the value of the '<em><b>Owned Members</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Property}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Members</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getAssociation_OwnedMembers()
   * @model containment="true" resolveProxies="true" upper="2"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='memberEnd' featureOwner='Association'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='members'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Each end represents participation of instances of the classifier connected to the end in links of the association.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Association::ownedEnd, uml::Association::memberEnd' explanation='none' constraints='Multiplicity must be [2..2]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Property> getOwnedMembers();







	/**
   * Returns the value of the '<em><b>Navigable Members</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Property}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigable Members</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Navigable Members</em>' reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getAssociation_NavigableMembers()
   * @model upper="2"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='navigableOwnedEnd' featureOwner='Association'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='navigable'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='The navigable ends that are owned by the association itself\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Association::navigableOwnedEnd' explanation='none' constraints='Order must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Property> getNavigableMembers();





} // Association
