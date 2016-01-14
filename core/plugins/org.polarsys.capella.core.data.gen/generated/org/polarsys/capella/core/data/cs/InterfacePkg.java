/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.cs;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.information.communication.MessageReferencePkg;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfaces <em>Owned Interfaces</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.cs.InterfacePkg#getOwnedInterfacePkgs <em>Owned Interface Pkgs</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfacePkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='InterfacePkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.InterfacePkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A container for Interface elements\r\n[source: Capella study]' usage\040guideline='n/a' used\040in\040levels='operational,system,logical,physical,epbs' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 * @generated
 */
public interface InterfacePkg extends MessageReferencePkg, AbstractDependenciesPkg, AbstractExchangeItemPkg {





	/**
	 * Returns the value of the '<em><b>Owned Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.Interface}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interfaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Interfaces</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfacePkg_OwnedInterfaces()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedInterfaces'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the interfaces that are owned by this Package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which Interface stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<Interface> getOwnedInterfaces();







	/**
	 * Returns the value of the '<em><b>Owned Interface Pkgs</b></em>' containment reference list.
	 * The list contents are of type {@link org.polarsys.capella.core.data.cs.InterfacePkg}.

	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Interface Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Interface Pkgs</em>' containment reference list.
	 * @see org.polarsys.capella.core.data.cs.CsPackage#getInterfacePkg_OwnedInterfacePkgs()
	 * @model containment="true" resolveProxies="true"
	 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
	 *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='subInterfacePkgs'"
	 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Specifies the packages of interfaces that are owned by this Package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
	 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which InterfacePkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
	 * @generated
	 */

	EList<InterfacePkg> getOwnedInterfacePkgs();





} // InterfacePkg
