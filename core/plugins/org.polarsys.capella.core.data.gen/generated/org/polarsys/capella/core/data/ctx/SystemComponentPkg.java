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

package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.cs.ComponentPkg;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Component Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponents <em>Owned System Components</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemComponentPkg#getOwnedSystemComponentPkgs <em>Owned System Component Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponentPkg()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a package containing System Components\r\n[source: Capella study]' constraints='none' comment/notes='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */

public interface SystemComponentPkg extends ComponentPkg {





	/**
   * Returns the value of the '<em><b>Owned System Components</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Components</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponentPkg_OwnedSystemComponents()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the System Components included in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemComponent> getOwnedSystemComponents();







	/**
   * Returns the value of the '<em><b>Owned System Component Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemComponentPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Component Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned System Component Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemComponentPkg_OwnedSystemComponentPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub-packages of this System Component Package' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints=''"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<SystemComponentPkg> getOwnedSystemComponentPkgs();





} // SystemComponentPkg
