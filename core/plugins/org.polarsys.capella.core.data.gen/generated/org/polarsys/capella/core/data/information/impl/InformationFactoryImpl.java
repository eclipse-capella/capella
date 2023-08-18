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
package org.polarsys.capella.core.data.information.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionKind;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.DomainElement;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.PassingMode;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.SynchronismKind;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.Unit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InformationFactoryImpl extends EFactoryImpl implements InformationFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static InformationFactory init() {
    try {
      InformationFactory theInformationFactory = (InformationFactory)EPackage.Registry.INSTANCE.getEFactory(InformationPackage.eNS_URI);
      if (theInformationFactory != null) {
        return theInformationFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new InformationFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public InformationFactoryImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case InformationPackage.ASSOCIATION: return createAssociation();
      case InformationPackage.CLASS: return createClass();
      case InformationPackage.COLLECTION: return createCollection();
      case InformationPackage.COLLECTION_VALUE: return createCollectionValue();
      case InformationPackage.COLLECTION_VALUE_REFERENCE: return createCollectionValueReference();
      case InformationPackage.DATA_PKG: return createDataPkg();
      case InformationPackage.DOMAIN_ELEMENT: return createDomainElement();
      case InformationPackage.KEY_PART: return createKeyPart();
      case InformationPackage.OPERATION_ALLOCATION: return createOperationAllocation();
      case InformationPackage.PARAMETER: return createParameter();
      case InformationPackage.PROPERTY: return createProperty();
      case InformationPackage.SERVICE: return createService();
      case InformationPackage.UNION: return createUnion();
      case InformationPackage.UNION_PROPERTY: return createUnionProperty();
      case InformationPackage.UNIT: return createUnit();
      case InformationPackage.PORT_REALIZATION: return createPortRealization();
      case InformationPackage.PORT_ALLOCATION: return createPortAllocation();
      case InformationPackage.EXCHANGE_ITEM: return createExchangeItem();
      case InformationPackage.EXCHANGE_ITEM_ELEMENT: return createExchangeItemElement();
      case InformationPackage.EXCHANGE_ITEM_INSTANCE: return createExchangeItemInstance();
      case InformationPackage.INFORMATION_REALIZATION: return createInformationRealization();
      case InformationPackage.EXCHANGE_ITEM_REALIZATION: return createExchangeItemRealization();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case InformationPackage.AGGREGATION_KIND:
        return createAggregationKindFromString(eDataType, initialValue);
      case InformationPackage.PARAMETER_DIRECTION:
        return createParameterDirectionFromString(eDataType, initialValue);
      case InformationPackage.PASSING_MODE:
        return createPassingModeFromString(eDataType, initialValue);
      case InformationPackage.SYNCHRONISM_KIND:
        return createSynchronismKindFromString(eDataType, initialValue);
      case InformationPackage.UNION_KIND:
        return createUnionKindFromString(eDataType, initialValue);
      case InformationPackage.EXCHANGE_MECHANISM:
        return createExchangeMechanismFromString(eDataType, initialValue);
      case InformationPackage.ELEMENT_KIND:
        return createElementKindFromString(eDataType, initialValue);
      case InformationPackage.COLLECTION_KIND:
        return createCollectionKindFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case InformationPackage.AGGREGATION_KIND:
        return convertAggregationKindToString(eDataType, instanceValue);
      case InformationPackage.PARAMETER_DIRECTION:
        return convertParameterDirectionToString(eDataType, instanceValue);
      case InformationPackage.PASSING_MODE:
        return convertPassingModeToString(eDataType, instanceValue);
      case InformationPackage.SYNCHRONISM_KIND:
        return convertSynchronismKindToString(eDataType, instanceValue);
      case InformationPackage.UNION_KIND:
        return convertUnionKindToString(eDataType, instanceValue);
      case InformationPackage.EXCHANGE_MECHANISM:
        return convertExchangeMechanismToString(eDataType, instanceValue);
      case InformationPackage.ELEMENT_KIND:
        return convertElementKindToString(eDataType, instanceValue);
      case InformationPackage.COLLECTION_KIND:
        return convertCollectionKindToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Association createAssociation() {
    AssociationImpl association = new AssociationImpl();
    //begin-capella-code
    association.setId(IdGenerator.createId());
    //end-capella-code
    return association;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public org.polarsys.capella.core.data.information.Class createClass() {
    ClassImpl class_ = new ClassImpl();
    //begin-capella-code
    class_.setId(IdGenerator.createId());
    //end-capella-code
    return class_;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Collection createCollection() {
    CollectionImpl collection = new CollectionImpl();
    //begin-capella-code
    collection.setId(IdGenerator.createId());
    //end-capella-code
    return collection;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CollectionValue createCollectionValue() {
    CollectionValueImpl collectionValue = new CollectionValueImpl();
    //begin-capella-code
    collectionValue.setId(IdGenerator.createId());
    //end-capella-code
    return collectionValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CollectionValueReference createCollectionValueReference() {
    CollectionValueReferenceImpl collectionValueReference = new CollectionValueReferenceImpl();
    //begin-capella-code
    collectionValueReference.setId(IdGenerator.createId());
    //end-capella-code
    return collectionValueReference;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DataPkg createDataPkg() {
    DataPkgImpl dataPkg = new DataPkgImpl();
    //begin-capella-code
    dataPkg.setId(IdGenerator.createId());
    //end-capella-code
    return dataPkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DomainElement createDomainElement() {
    DomainElementImpl domainElement = new DomainElementImpl();
    //begin-capella-code
    domainElement.setId(IdGenerator.createId());
    //end-capella-code
    return domainElement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public KeyPart createKeyPart() {
    KeyPartImpl keyPart = new KeyPartImpl();
    //begin-capella-code
    keyPart.setId(IdGenerator.createId());
    //end-capella-code
    return keyPart;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public OperationAllocation createOperationAllocation() {
    OperationAllocationImpl operationAllocation = new OperationAllocationImpl();
    //begin-capella-code
    operationAllocation.setId(IdGenerator.createId());
    //end-capella-code
    return operationAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Parameter createParameter() {
    ParameterImpl parameter = new ParameterImpl();
    //begin-capella-code
    parameter.setId(IdGenerator.createId());
    //end-capella-code
    return parameter;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Property createProperty() {
    PropertyImpl property = new PropertyImpl();
    //begin-capella-code
    property.setId(IdGenerator.createId());
    //end-capella-code
    return property;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Service createService() {
    ServiceImpl service = new ServiceImpl();
    //begin-capella-code
    service.setId(IdGenerator.createId());
    //end-capella-code
    return service;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Union createUnion() {
    UnionImpl union = new UnionImpl();
    //begin-capella-code
    union.setId(IdGenerator.createId());
    //end-capella-code
    return union;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public UnionProperty createUnionProperty() {
    UnionPropertyImpl unionProperty = new UnionPropertyImpl();
    //begin-capella-code
    unionProperty.setId(IdGenerator.createId());
    //end-capella-code
    return unionProperty;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Unit createUnit() {
    UnitImpl unit = new UnitImpl();
    //begin-capella-code
    unit.setId(IdGenerator.createId());
    //end-capella-code
    return unit;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PortRealization createPortRealization() {
    PortRealizationImpl portRealization = new PortRealizationImpl();
    //begin-capella-code
    portRealization.setId(IdGenerator.createId());
    //end-capella-code
    return portRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PortAllocation createPortAllocation() {
    PortAllocationImpl portAllocation = new PortAllocationImpl();
    //begin-capella-code
    portAllocation.setId(IdGenerator.createId());
    //end-capella-code
    return portAllocation;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExchangeItem createExchangeItem() {
    ExchangeItemImpl exchangeItem = new ExchangeItemImpl();
    //begin-capella-code
    exchangeItem.setId(IdGenerator.createId());
    //end-capella-code
    return exchangeItem;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExchangeItemElement createExchangeItemElement() {
    ExchangeItemElementImpl exchangeItemElement = new ExchangeItemElementImpl();
    //begin-capella-code
    exchangeItemElement.setId(IdGenerator.createId());
    //end-capella-code
    return exchangeItemElement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExchangeItemInstance createExchangeItemInstance() {
    ExchangeItemInstanceImpl exchangeItemInstance = new ExchangeItemInstanceImpl();
    //begin-capella-code
    exchangeItemInstance.setId(IdGenerator.createId());
    //end-capella-code
    return exchangeItemInstance;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InformationRealization createInformationRealization() {
    InformationRealizationImpl informationRealization = new InformationRealizationImpl();
    //begin-capella-code
    informationRealization.setId(IdGenerator.createId());
    //end-capella-code
    return informationRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ExchangeItemRealization createExchangeItemRealization() {
    ExchangeItemRealizationImpl exchangeItemRealization = new ExchangeItemRealizationImpl();
    //begin-capella-code
    exchangeItemRealization.setId(IdGenerator.createId());
    //end-capella-code
    return exchangeItemRealization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AggregationKind createAggregationKindFromString(EDataType eDataType, String initialValue) {
    AggregationKind result = AggregationKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertAggregationKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ParameterDirection createParameterDirectionFromString(EDataType eDataType, String initialValue) {
    ParameterDirection result = ParameterDirection.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertParameterDirectionToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PassingMode createPassingModeFromString(EDataType eDataType, String initialValue) {
    PassingMode result = PassingMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertPassingModeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SynchronismKind createSynchronismKindFromString(EDataType eDataType, String initialValue) {
    SynchronismKind result = SynchronismKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertSynchronismKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public UnionKind createUnionKindFromString(EDataType eDataType, String initialValue) {
    UnionKind result = UnionKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertUnionKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ExchangeMechanism createExchangeMechanismFromString(EDataType eDataType, String initialValue) {
    ExchangeMechanism result = ExchangeMechanism.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertExchangeMechanismToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ElementKind createElementKindFromString(EDataType eDataType, String initialValue) {
    ElementKind result = ElementKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertElementKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionKind createCollectionKindFromString(EDataType eDataType, String initialValue) {
    CollectionKind result = CollectionKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertCollectionKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InformationPackage getInformationPackage() {
    return (InformationPackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static InformationPackage getPackage() {
    return InformationPackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Association createAssociation(String name_p) {
    Association association = createAssociation();
    association.setName(name_p);	  
    return association;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public org.polarsys.capella.core.data.information.Class createClass(String name_p) {
    org.polarsys.capella.core.data.information.Class class_ = createClass();
    class_.setName(name_p);	  
    return class_;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Collection createCollection(String name_p) {
    Collection collection = createCollection();
    collection.setName(name_p);	  
    return collection;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CollectionValue createCollectionValue(String name_p) {
    CollectionValue collectionValue = createCollectionValue();
    collectionValue.setName(name_p);	  
    return collectionValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public CollectionValueReference createCollectionValueReference(String name_p) {
    CollectionValueReference collectionValueReference = createCollectionValueReference();
    collectionValueReference.setName(name_p);	  
    return collectionValueReference;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public DataPkg createDataPkg(String name_p) {
    DataPkg dataPkg = createDataPkg();
    dataPkg.setName(name_p);	  
    return dataPkg;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public DomainElement createDomainElement(String name_p) {
    DomainElement domainElement = createDomainElement();
    domainElement.setName(name_p);	  
    return domainElement;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Parameter createParameter(String name_p) {
    Parameter parameter = createParameter();
    parameter.setName(name_p);	  
    return parameter;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Property createProperty(String name_p) {
    Property property = createProperty();
    property.setName(name_p);	  
    return property;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Service createService(String name_p) {
    Service service = createService();
    service.setName(name_p);	  
    return service;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Union createUnion(String name_p) {
    Union union = createUnion();
    union.setName(name_p);	  
    return union;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public UnionProperty createUnionProperty(String name_p) {
    UnionProperty unionProperty = createUnionProperty();
    unionProperty.setName(name_p);	  
    return unionProperty;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Unit createUnit(String name_p) {
    Unit unit = createUnit();
    unit.setName(name_p);	  
    return unit;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExchangeItem createExchangeItem(String name_p) {
    ExchangeItem exchangeItem = createExchangeItem();
    exchangeItem.setName(name_p);	  
    return exchangeItem;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExchangeItemElement createExchangeItemElement(String name_p) {
    ExchangeItemElement exchangeItemElement = createExchangeItemElement();
    exchangeItemElement.setName(name_p);	  
    return exchangeItemElement;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public ExchangeItemInstance createExchangeItemInstance(String name_p) {
    ExchangeItemInstance exchangeItemInstance = createExchangeItemInstance();
    exchangeItemInstance.setName(name_p);	  
    return exchangeItemInstance;
  }

	//begin-capella-code

	//end-capella-code
} //InformationFactoryImpl
