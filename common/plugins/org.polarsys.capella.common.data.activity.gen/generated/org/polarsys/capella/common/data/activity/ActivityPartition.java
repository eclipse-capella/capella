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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityPartition#isIsDimension <em>Is Dimension</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityPartition#isIsExternal <em>Is External</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityPartition#getRepresentedElement <em>Represented Element</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityPartition#getSuperPartition <em>Super Partition</em>}</li>
 *   <li>{@link org.polarsys.capella.common.data.activity.ActivityPartition#getSubPartitions <em>Sub Partitions</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition()
 * @model abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An activity partition is a kind of activity group for identifying actions that have some characteristic in common.\r\n[source: UML superstructure v2.2]\r\n' usage\040guideline='n/a (abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='' explanation='uml::ActivityPartition' constraints='none'"
 * @generated
 */
public interface ActivityPartition extends ActivityGroup, AbstractNamedElement {





	/**
   * Returns the value of the '<em><b>Is Dimension</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Dimension</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Dimension</em>' attribute.
   * @see #setIsDimension(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition_IsDimension()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether the partition groups other partitions along a dimension\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityPartition::isDimension' explanation='none' constraints='Cardinality of uml::ActivityPartition::isDimension is [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsDimension();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityPartition#isIsDimension <em>Is Dimension</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Dimension</em>' attribute.
   * @see #isIsDimension()
   * @generated
   */

	void setIsDimension(boolean value);







	/**
   * Returns the value of the '<em><b>Is External</b></em>' attribute.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is External</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is External</em>' attribute.
   * @see #setIsExternal(boolean)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition_IsExternal()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Tells whether the partition represents an entity to which the partitioning structure does not apply\r\n[source: UML superstructure v2.2]' constraints='none' type='n/a' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityPartition::isExternal' explanation='none' constraints='Cardinality of uml::ActivityPartition::isExternal is [1..1]'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	boolean isIsExternal();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityPartition#isIsExternal <em>Is External</em>}' attribute.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is External</em>' attribute.
   * @see #isIsExternal()
   * @generated
   */

	void setIsExternal(boolean value);







	/**
   * Returns the value of the '<em><b>Represented Element</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Represented Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Represented Element</em>' reference.
   * @see #setRepresentedElement(AbstractType)
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition_RepresentedElement()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='An element constraining behaviors invoked by nodes in the partition\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityPartition::represents' explanation='none' constraints='none'"
   * @generated
   */

	AbstractType getRepresentedElement();




	/**
   * Sets the value of the '{@link org.polarsys.capella.common.data.activity.ActivityPartition#getRepresentedElement <em>Represented Element</em>}' reference.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Represented Element</em>' reference.
   * @see #getRepresentedElement()
   * @generated
   */

	void setRepresentedElement(AbstractType value);







	/**
   * Returns the value of the '<em><b>Super Partition</b></em>' reference.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Partition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Super Partition</em>' reference.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition_SuperPartition()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='superGroup'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Partition immediately containing the partition.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	ActivityPartition getSuperPartition();







	/**
   * Returns the value of the '<em><b>Sub Partitions</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.common.data.activity.ActivityPartition}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Partitions</em>' reference list.
   * @see org.polarsys.capella.common.data.activity.ActivityPackage#getActivityPartition_SubPartitions()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='alias' viatra.expression='subGroups'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Partitions immediately contained in the partition.\r\n[source: UML superstructure v2.2]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::ActivityPartition::subpartition' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<ActivityPartition> getSubPartitions();





} // ActivityPartition
