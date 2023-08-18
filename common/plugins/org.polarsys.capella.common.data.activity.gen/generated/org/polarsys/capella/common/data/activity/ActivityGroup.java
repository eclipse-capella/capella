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
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityGroup#getSuperGroup <em>Super Group</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityGroup#getSubGroups <em>Sub Groups</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityGroup#getOwnedNodes <em>Owned Nodes</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityGroup#getOwnedEdges <em>Owned Edges</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityGroup()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Activity groups are a generic grouping construct for nodes and edges. Nodes and edges can belong to more than one\r\ngroup. They have no inherent semantics and can be used for various purposes. Subclasses of ActivityGroup may add\r\nsemantics.\r\n[source: UML superstructure v2.2]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='not used/implemented as of Capella 1.0.3' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ActivityGroup' constraints='none'"
 * @generated
 */
public interface ActivityGroup extends ModelElement {





	/**
   * Returns the value of the '<em><b>Super Group</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.ActivityGroup#getSubGroups <em>Sub Groups</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Group</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super Group</em>' container reference.
   * @see #setSuperGroup(ActivityGroup)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityGroup_SuperGroup()
   * @see org.polarsys.capella.common.data.activity.ActivityGroup#getSubGroups
   * @model opposite="subGroups" transient="false"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Group immediately containing the group\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='_todo_ If it is corresponding to UML, this attribute should be derived'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityGroup::superGroup#keyword::none' explanation='none' constraints='none'"
   * @generated
   */

	ActivityGroup getSuperGroup();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityGroup#getSuperGroup <em>Super Group</em>}' container reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Group</em>' container reference.
   * @see #getSuperGroup()
   * @generated
   */

	void setSuperGroup(ActivityGroup value);







	/**
   * Returns the value of the '<em><b>Sub Groups</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityGroup}.
   * It is bidirectional and its opposite is '{@link org.polarsys.capella.common.data.activity.ActivityGroup#getSuperGroup <em>Super Group</em>}'.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Groups</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityGroup_SubGroups()
   * @see org.polarsys.capella.common.data.activity.ActivityGroup#getSuperGroup
   * @model opposite="superGroup" containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Groups immediately contained in the group\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='_todo_ If it is corresponding to UML, this attribute should be derived'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityGroup::subgroup#keyword::none' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityGroup> getSubGroups();







	/**
   * Returns the value of the '<em><b>Owned Nodes</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityNode}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Nodes</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityGroup_OwnedNodes()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Nodes immediately contained in the group. This is a derived union\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='_todo_ If it is corresponding to UML, this attribute should be derived'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityGroup::containedNode#keyword::none' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityNode> getOwnedNodes();







	/**
   * Returns the value of the '<em><b>Owned Edges</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityEdge}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Edges</em>' containment reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityGroup_OwnedEdges()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Edges immediately contained in the group. This is a derived union\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='_todo_ If it is corresponding to UML, this attribute should be derived'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityGroup::containedEdge#keyword::none' explanation='none' constraints='Order must be computed'"
   * @generated
   */

	EList<ActivityEdge> getOwnedEdges();





} // ActivityGroup
