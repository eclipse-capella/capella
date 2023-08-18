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
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Physical Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getInvolvedLinks <em>Involved Links</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathInvolvements <em>Owned Physical Path Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getFirstPhysicalPathInvolvements <em>First Physical Path Involvements</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getOwnedPhysicalPathRealizations <em>Owned Physical Path Realizations</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getRealizedPhysicalPaths <em>Realized Physical Paths</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.PhysicalPath#getRealizingPhysicalPaths <em>Realizing Physical Paths</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the specification of a given path of informations flowing across physical links and interfaces.\r\n[source: Capella study]' usage\040guideline='this is the equivalent for the physical architecture, of a functional chain defined at system level' used\040in\040levels='physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Class' explanation='_todo_' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface PhysicalPath extends NamedElement, ComponentExchangeAllocator, AbstractPathInvolvedElement, InvolverElement {





	/**
   * Returns the value of the '<em><b>Involved Links</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.AbstractPhysicalPathLink}.

   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @deprecated : 'involvedLinks' shall not be used anymore
   * <!-- end-model-doc -->
   * @return the value of the '<em>Involved Links</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_InvolvedLinks()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the list of steps of this physical path\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   * @generated
   */

	@Deprecated
	EList<AbstractPhysicalPathLink> getInvolvedLinks();







	/**
   * Returns the value of the '<em><b>Owned Physical Path Involvements</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Path Involvements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Path Involvements</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_OwnedPhysicalPathInvolvements()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalPathInvolvement> getOwnedPhysicalPathInvolvements();







	/**
   * Returns the value of the '<em><b>First Physical Path Involvements</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPathInvolvement}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Physical Path Involvements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>First Physical Path Involvements</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_FirstPhysicalPathInvolvements()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='freeform' viatra.expression='pattern PhysicalPath__firstPhysicalPathInvolvements(self : PhysicalPath, target : PhysicalPathInvolvement) {\r\n\tPhysicalPath.ownedPhysicalPathInvolvements(self, target);\r\n\tPhysicalPathInvolvement.involved(target, _);\r\n\tneg find _PreviousInvolvement(target, _);\r\n}\r\nprivate pattern _PreviousInvolvement(ppi : PhysicalPathInvolvement, previous : PhysicalPathInvolvement) {\r\n\tPhysicalPathInvolvement.previousInvolvements(ppi, previous);\r\n}\r\n'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='keyword::none' explanation='Derived and transient' constraints='none'"
   * @generated
   */

	EList<PhysicalPathInvolvement> getFirstPhysicalPathInvolvements();







	/**
   * Returns the value of the '<em><b>Owned Physical Path Realizations</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPathRealization}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Physical Path Realizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Physical Path Realizations</em>' containment reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_OwnedPhysicalPathRealizations()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='none' explanation='none' constraints='none'"
   * @generated
   */

	EList<PhysicalPathRealization> getOwnedPhysicalPathRealizations();







	/**
   * Returns the value of the '<em><b>Realized Physical Paths</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPath}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized Physical Paths</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realized Physical Paths</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_RealizedPhysicalPaths()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPathRealization.sourceElement(ppr, self);\r\nPhysicalPathRealization.targetElement(ppr, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<PhysicalPath> getRealizedPhysicalPaths();







	/**
   * Returns the value of the '<em><b>Realizing Physical Paths</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.cs.PhysicalPath}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizing Physical Paths</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Realizing Physical Paths</em>' reference list.
   * @see org.polarsys.capella.core.data.cs.CsPackage#getPhysicalPath_RealizingPhysicalPaths()
   * @model transient="true" changeable="false" volatile="true" derived="true"
   *        annotation="http://www.polarsys.org/capella/derived viatra.variant='patternbody' viatra.expression='PhysicalPathRealization.targetElement(ppr, self);\r\nPhysicalPathRealization.sourceElement(ppr, target);'"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='none' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/semantic excludefrom='xmlpivot'"
   * @generated
   */

	EList<PhysicalPath> getRealizingPhysicalPaths();





} // PhysicalPath
