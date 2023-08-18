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
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReferencePkg;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedDataPkgs <em>Owned Data Pkgs</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedClasses <em>Owned Classes</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedKeyParts <em>Owned Key Parts</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedCollections <em>Owned Collections</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedUnits <em>Owned Units</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedDataTypes <em>Owned Data Types</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedSignals <em>Owned Signals</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedMessages <em>Owned Messages</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedExceptions <em>Owned Exceptions</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.DataPkg#getOwnedStateEvents <em>Owned State Events</em>}</li>
 * </ul>
 *
 * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg()
 * @model annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='DataPkg'"
 *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping metaclass='Package' stereotype='eng.DataPkg'"
 *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='A container for data structures\r\n[source: Capella study]' usage\040guideline='n/a (Abstract)' used\040in\040levels='n/a' usage\040examples='n/a' constraints='none' comment/notes='none' reference\040documentation='none'"
 *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' base\040metaclass\040in\040UML/SysML\040profile\040='uml::Package' explanation='none' constraints='none'"
 *        annotation="http://www.polarsys.org/capella/semantic"
 * @generated
 */
public interface DataPkg extends AbstractDependenciesPkg, AbstractExchangeItemPkg, AssociationPkg, DataValueContainer, MessageReferencePkg {





	/**
   * Returns the value of the '<em><b>Owned Data Pkgs</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.DataPkg}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Data Pkgs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Data Pkgs</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedDataPkgs()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDataPkgs'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Sub data packages contained in this data package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::nestedPackage#uml::Package::packagedElement' explanation='none' constraints='uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataPkg> getOwnedDataPkgs();







	/**
   * Returns the value of the '<em><b>Owned Classes</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Class}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Classes</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedClasses()
   * @model containment="true" resolveProxies="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedClasses'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the class elements contained in the package' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Class stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<org.polarsys.capella.core.data.information.Class> getOwnedClasses();







	/**
   * Returns the value of the '<em><b>Owned Key Parts</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.KeyPart}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Key Parts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Key Parts</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedKeyParts()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedKeyParts'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='KeyPart elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which KeyPart stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<KeyPart> getOwnedKeyParts();







	/**
   * Returns the value of the '<em><b>Owned Collections</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Collection}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Collections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Collections</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedCollections()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedCollections'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Collection elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Collectionstereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Collection> getOwnedCollections();







	/**
   * Returns the value of the '<em><b>Owned Units</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.Unit}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Units</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Units</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedUnits()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedUnits'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Unit elements contained in the package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Unit stereotype or any stereotype that inherits from it is applied'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Unit> getOwnedUnits();







	/**
   * Returns the value of the '<em><b>Owned Data Types</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.datatype.DataType}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Data Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Data Types</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedDataTypes()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedDataTypes'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='Data types contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which DataType stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<DataType> getOwnedDataTypes();







	/**
   * Returns the value of the '<em><b>Owned Signals</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.Signal}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Signals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Signals</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedSignals()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedSignals'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Signal elements contained in the package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Signal stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Signal> getOwnedSignals();







	/**
   * Returns the value of the '<em><b>Owned Messages</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.Message}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Messages</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedMessages()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedMessages'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Messages contained in this Message package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Message stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<Message> getOwnedMessages();







	/**
   * Returns the value of the '<em><b>Owned Exceptions</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.information.communication.Exception}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Exceptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Exceptions</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedExceptions()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/UML2Mapping featureName='packagedElement' featureOwner='Package'"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedExceptions'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the Exception elements contained in this package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='uml::Package::packagedElement' explanation='none' constraints='uml::Package::packagedElement elements on which Excpetion stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<org.polarsys.capella.core.data.information.communication.Exception> getOwnedExceptions();







	/**
   * Returns the value of the '<em><b>Owned State Events</b></em>' containment reference list.
   * The list contents are of type {@link org.polarsys.capella.core.data.capellacommon.StateEvent}.

   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned State Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Owned State Events</em>' containment reference list.
   * @see org.polarsys.capella.core.data.information.InformationPackage#getDataPkg_OwnedStateEvents()
   * @model containment="true"
   *        annotation="http://www.polarsys.org/capella/2007/BusinessInformation Label='ownedStateEvents'"
   *        annotation="http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"
   *        annotation="http://www.polarsys.org/kitalpha/ecore/documentation description='the StateEvent elements contained in the package\r\n[source: Capella study]' constraints='none' comment/notes='none'"
   *        annotation="http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping UML/SysML\040semantic\040equivalences='' explanation='none' constraints='none'"
   *        annotation="http://www.polarsys.org/capella/semantic"
   * @generated
   */

	EList<StateEvent> getOwnedStateEvents();





} // DataPkg
