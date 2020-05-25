/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.ctx;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.fa.FunctionPkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Function Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctions <em>Owned System Functions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg#getOwnedSystemFunctionPkgs <em>Owned System Function Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunctionPkg()
 * @model annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='a container for System Functions\r\n[source: Capella study]' usage\040guideline='this can be used to structure/organize system functions in the model' used\040in\040levels='system' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface SystemFunctionPkg extends FunctionPkg {





	/**
	 * Returns the value of the '<em><b>Owned System Functions</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunction}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Functions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Functions</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunctionPkg_OwnedSystemFunctions()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='system functions contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='Order must be computed'"
	 * @generated
	 */

	EList<SystemFunction> getOwnedSystemFunctions();







	/**
	 * Returns the value of the '<em><b>Owned System Function Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.ctx.SystemFunctionPkg}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned System Function Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned System Function Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.ctx.CtxPackage#getSystemFunctionPkg_OwnedSystemFunctionPkgs()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='sub (function) package under this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='' constraints='uml::Package::nestedPackage elements on which SystemFunctionPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<SystemFunctionPkg> getOwnedSystemFunctionPkgs();





} // SystemFunctionPkg
