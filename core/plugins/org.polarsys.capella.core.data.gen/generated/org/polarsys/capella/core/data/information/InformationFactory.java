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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.core.data.information.InformationPackage
 * @generated
 */
public interface InformationFactory extends EFactory {
	/**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	InformationFactory eINSTANCE = org.polarsys.capella.core.data.information.impl.InformationFactoryImpl.init();

	/**
   * Returns a new object of class '<em>Association</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Association</em>'.
   * @generated
   */
	Association createAssociation();

	/**
   * Returns a new object of class '<em>Class</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Class</em>'.
   * @generated
   */
	Class createClass();

	/**
   * Returns a new object of class '<em>Collection</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection</em>'.
   * @generated
   */
	Collection createCollection();

	/**
   * Returns a new object of class '<em>Collection Value</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Value</em>'.
   * @generated
   */
	CollectionValue createCollectionValue();

	/**
   * Returns a new object of class '<em>Collection Value Reference</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Value Reference</em>'.
   * @generated
   */
	CollectionValueReference createCollectionValueReference();

	/**
   * Returns a new object of class '<em>Data Pkg</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Pkg</em>'.
   * @generated
   */
	DataPkg createDataPkg();

	/**
   * Returns a new object of class '<em>Domain Element</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Domain Element</em>'.
   * @generated
   */
	DomainElement createDomainElement();

	/**
   * Returns a new object of class '<em>Key Part</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Key Part</em>'.
   * @generated
   */
	KeyPart createKeyPart();

	/**
   * Returns a new object of class '<em>Operation Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Allocation</em>'.
   * @generated
   */
	OperationAllocation createOperationAllocation();

	/**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
	Parameter createParameter();

	/**
   * Returns a new object of class '<em>Property</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Property</em>'.
   * @generated
   */
	Property createProperty();

	/**
   * Returns a new object of class '<em>Service</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Service</em>'.
   * @generated
   */
	Service createService();

	/**
   * Returns a new object of class '<em>Union</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Union</em>'.
   * @generated
   */
	Union createUnion();

	/**
   * Returns a new object of class '<em>Union Property</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Union Property</em>'.
   * @generated
   */
	UnionProperty createUnionProperty();

	/**
   * Returns a new object of class '<em>Unit</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Unit</em>'.
   * @generated
   */
	Unit createUnit();

	/**
   * Returns a new object of class '<em>Port Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Port Realization</em>'.
   * @generated
   */
	PortRealization createPortRealization();

	/**
   * Returns a new object of class '<em>Port Allocation</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Port Allocation</em>'.
   * @generated
   */
	PortAllocation createPortAllocation();

	/**
   * Returns a new object of class '<em>Exchange Item</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Item</em>'.
   * @generated
   */
	ExchangeItem createExchangeItem();

	/**
   * Returns a new object of class '<em>Exchange Item Element</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Item Element</em>'.
   * @generated
   */
	ExchangeItemElement createExchangeItemElement();

	/**
   * Returns a new object of class '<em>Exchange Item Instance</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Item Instance</em>'.
   * @generated
   */
	ExchangeItemInstance createExchangeItemInstance();

	/**
   * Returns a new object of class '<em>Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Realization</em>'.
   * @generated
   */
	InformationRealization createInformationRealization();

	/**
   * Returns a new object of class '<em>Exchange Item Realization</em>'.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return a new object of class '<em>Exchange Item Realization</em>'.
   * @generated
   */
	ExchangeItemRealization createExchangeItemRealization();

	/**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
	InformationPackage getInformationPackage();

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Association createAssociation(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Class createClass(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Collection createCollection(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CollectionValue createCollectionValue(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	CollectionValueReference createCollectionValueReference(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DataPkg createDataPkg(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	DomainElement createDomainElement(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Parameter createParameter(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Property createProperty(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Service createService(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Union createUnion(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	UnionProperty createUnionProperty(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	Unit createUnit(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeItem createExchangeItem(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeItemElement createExchangeItemElement(String name_p);

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	ExchangeItemInstance createExchangeItemInstance(String name_p);

	//begin-capella-code
	//end-capella-code
} //InformationFactory
