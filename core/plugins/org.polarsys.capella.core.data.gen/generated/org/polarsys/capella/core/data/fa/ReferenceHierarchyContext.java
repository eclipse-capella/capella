/**
 *
 *  Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *     Thales - initial API and implementation
 */

package org.polarsys.capella.core.data.fa;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Hierarchy Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getSourceReferenceHierarchy <em>Source Reference Hierarchy</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.fa.ReferenceHierarchyContext#getTargetReferenceHierarchy <em>Target Reference Hierarchy</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.fa.FaPackage#getReferenceHierarchyContext()
 * @model interface="true" abstract="true"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used to uniquely identify a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface ReferenceHierarchyContext extends EObject {





	/**
   * Returns the value of the '<em><b>Source Reference Hierarchy</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainReference}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Reference Hierarchy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source Reference Hierarchy</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getReferenceHierarchyContext_SourceReferenceHierarchy()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used to uniquely identify the source of a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainReference> getSourceReferenceHierarchy();







	/**
   * Returns the value of the '<em><b>Target Reference Hierarchy</b></em>' reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.fa.FunctionalChainReference}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Reference Hierarchy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Target Reference Hierarchy</em>' reference list.
   * @see org.polarsys.capella.core.data.fa.FaPackage#getReferenceHierarchyContext_TargetReferenceHierarchy()
   * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='used to uniquely identify the target of a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<FunctionalChainReference> getTargetReferenceHierarchy();





} // ReferenceHierarchyContext
