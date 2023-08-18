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
package org.polarsys.capella.core.data.information.datatype.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.information.datatype.*;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatatypeFactoryImpl extends EFactoryImpl implements DatatypeFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static DatatypeFactory init() {
    try {
      DatatypeFactory theDatatypeFactory = (DatatypeFactory)EPackage.Registry.INSTANCE.getEFactory(DatatypePackage.eNS_URI);
      if (theDatatypeFactory != null) {
        return theDatatypeFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DatatypeFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DatatypeFactoryImpl() {
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
      case DatatypePackage.BOOLEAN_TYPE: return createBooleanType();
      case DatatypePackage.ENUMERATION: return createEnumeration();
      case DatatypePackage.STRING_TYPE: return createStringType();
      case DatatypePackage.NUMERIC_TYPE: return createNumericType();
      case DatatypePackage.PHYSICAL_QUANTITY: return createPhysicalQuantity();
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
      case DatatypePackage.NUMERIC_TYPE_KIND:
        return createNumericTypeKindFromString(eDataType, initialValue);
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
      case DatatypePackage.NUMERIC_TYPE_KIND:
        return convertNumericTypeKindToString(eDataType, instanceValue);
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
	public BooleanType createBooleanType() {
    BooleanTypeImpl booleanType = new BooleanTypeImpl();
    //begin-capella-code
    booleanType.setId(IdGenerator.createId());
    //end-capella-code
    return booleanType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Enumeration createEnumeration() {
    EnumerationImpl enumeration = new EnumerationImpl();
    //begin-capella-code
    enumeration.setId(IdGenerator.createId());
    //end-capella-code
    return enumeration;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StringType createStringType() {
    StringTypeImpl stringType = new StringTypeImpl();
    //begin-capella-code
    stringType.setId(IdGenerator.createId());
    //end-capella-code
    return stringType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NumericType createNumericType() {
    NumericTypeImpl numericType = new NumericTypeImpl();
    //begin-capella-code
    numericType.setId(IdGenerator.createId());
    //end-capella-code
    return numericType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PhysicalQuantity createPhysicalQuantity() {
    PhysicalQuantityImpl physicalQuantity = new PhysicalQuantityImpl();
    //begin-capella-code
    physicalQuantity.setId(IdGenerator.createId());
    //end-capella-code
    return physicalQuantity;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NumericTypeKind createNumericTypeKindFromString(EDataType eDataType, String initialValue) {
    NumericTypeKind result = NumericTypeKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertNumericTypeKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DatatypePackage getDatatypePackage() {
    return (DatatypePackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static DatatypePackage getPackage() {
    return DatatypePackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public BooleanType createBooleanType(String name_p) {
    BooleanType booleanType = createBooleanType();
    booleanType.setName(name_p);	  
    return booleanType;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Enumeration createEnumeration(String name_p) {
    Enumeration enumeration = createEnumeration();
    enumeration.setName(name_p);	  
    return enumeration;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StringType createStringType(String name_p) {
    StringType stringType = createStringType();
    stringType.setName(name_p);	  
    return stringType;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public NumericType createNumericType(String name_p) {
    NumericType numericType = createNumericType();
    numericType.setName(name_p);	  
    return numericType;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PhysicalQuantity createPhysicalQuantity(String name_p) {
    PhysicalQuantity physicalQuantity = createPhysicalQuantity();
    physicalQuantity.setName(name_p);	  
    return physicalQuantity;
  }

	//begin-capella-code

	//end-capella-code
} //DatatypeFactoryImpl
