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
package org.polarsys.capella.core.data.capellacore.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.core.data.capellacore.*;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.FloatPropertyValue;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CapellacoreFactoryImpl extends EFactoryImpl implements CapellacoreFactory {
	/**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static CapellacoreFactory init() {
    try {
      CapellacoreFactory theCapellacoreFactory = (CapellacoreFactory)EPackage.Registry.INSTANCE.getEFactory(CapellacorePackage.eNS_URI);
      if (theCapellacoreFactory != null) {
        return theCapellacoreFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CapellacoreFactoryImpl();
  }

	/**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CapellacoreFactoryImpl() {
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
      case CapellacorePackage.NAMING_RULE: return createNamingRule();
      case CapellacorePackage.CONSTRAINT: return createConstraint();
      case CapellacorePackage.KEY_VALUE: return createKeyValue();
      case CapellacorePackage.REUSE_LINK: return createReuseLink();
      case CapellacorePackage.GENERALIZATION: return createGeneralization();
      case CapellacorePackage.STRING_PROPERTY_VALUE: return createStringPropertyValue();
      case CapellacorePackage.INTEGER_PROPERTY_VALUE: return createIntegerPropertyValue();
      case CapellacorePackage.BOOLEAN_PROPERTY_VALUE: return createBooleanPropertyValue();
      case CapellacorePackage.FLOAT_PROPERTY_VALUE: return createFloatPropertyValue();
      case CapellacorePackage.ENUMERATION_PROPERTY_VALUE: return createEnumerationPropertyValue();
      case CapellacorePackage.ENUMERATION_PROPERTY_TYPE: return createEnumerationPropertyType();
      case CapellacorePackage.ENUMERATION_PROPERTY_LITERAL: return createEnumerationPropertyLiteral();
      case CapellacorePackage.PROPERTY_VALUE_GROUP: return createPropertyValueGroup();
      case CapellacorePackage.PROPERTY_VALUE_PKG: return createPropertyValuePkg();
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
      case CapellacorePackage.VISIBILITY_KIND:
        return createVisibilityKindFromString(eDataType, initialValue);
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
      case CapellacorePackage.VISIBILITY_KIND:
        return convertVisibilityKindToString(eDataType, instanceValue);
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
	public NamingRule createNamingRule() {
    NamingRuleImpl namingRule = new NamingRuleImpl();
    //begin-capella-code
    namingRule.setId(IdGenerator.createId());
    //end-capella-code
    return namingRule;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Constraint createConstraint() {
    ConstraintImpl constraint = new ConstraintImpl();
    //begin-capella-code
    constraint.setId(IdGenerator.createId());
    //end-capella-code
    return constraint;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public KeyValue createKeyValue() {
    KeyValueImpl keyValue = new KeyValueImpl();
    //begin-capella-code
    keyValue.setId(IdGenerator.createId());
    //end-capella-code
    return keyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ReuseLink createReuseLink() {
    ReuseLinkImpl reuseLink = new ReuseLinkImpl();
    //begin-capella-code
    reuseLink.setId(IdGenerator.createId());
    //end-capella-code
    return reuseLink;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Generalization createGeneralization() {
    GeneralizationImpl generalization = new GeneralizationImpl();
    //begin-capella-code
    generalization.setId(IdGenerator.createId());
    //end-capella-code
    return generalization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public StringPropertyValue createStringPropertyValue() {
    StringPropertyValueImpl stringPropertyValue = new StringPropertyValueImpl();
    //begin-capella-code
    stringPropertyValue.setId(IdGenerator.createId());
    //end-capella-code
    return stringPropertyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public IntegerPropertyValue createIntegerPropertyValue() {
    IntegerPropertyValueImpl integerPropertyValue = new IntegerPropertyValueImpl();
    //begin-capella-code
    integerPropertyValue.setId(IdGenerator.createId());
    //end-capella-code
    return integerPropertyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public BooleanPropertyValue createBooleanPropertyValue() {
    BooleanPropertyValueImpl booleanPropertyValue = new BooleanPropertyValueImpl();
    //begin-capella-code
    booleanPropertyValue.setId(IdGenerator.createId());
    //end-capella-code
    return booleanPropertyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public FloatPropertyValue createFloatPropertyValue() {
    FloatPropertyValueImpl floatPropertyValue = new FloatPropertyValueImpl();
    //begin-capella-code
    floatPropertyValue.setId(IdGenerator.createId());
    //end-capella-code
    return floatPropertyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EnumerationPropertyValue createEnumerationPropertyValue() {
    EnumerationPropertyValueImpl enumerationPropertyValue = new EnumerationPropertyValueImpl();
    //begin-capella-code
    enumerationPropertyValue.setId(IdGenerator.createId());
    //end-capella-code
    return enumerationPropertyValue;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EnumerationPropertyType createEnumerationPropertyType() {
    EnumerationPropertyTypeImpl enumerationPropertyType = new EnumerationPropertyTypeImpl();
    //begin-capella-code
    enumerationPropertyType.setId(IdGenerator.createId());
    //end-capella-code
    return enumerationPropertyType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EnumerationPropertyLiteral createEnumerationPropertyLiteral() {
    EnumerationPropertyLiteralImpl enumerationPropertyLiteral = new EnumerationPropertyLiteralImpl();
    //begin-capella-code
    enumerationPropertyLiteral.setId(IdGenerator.createId());
    //end-capella-code
    return enumerationPropertyLiteral;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PropertyValueGroup createPropertyValueGroup() {
    PropertyValueGroupImpl propertyValueGroup = new PropertyValueGroupImpl();
    //begin-capella-code
    propertyValueGroup.setId(IdGenerator.createId());
    //end-capella-code
    return propertyValueGroup;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public PropertyValuePkg createPropertyValuePkg() {
    PropertyValuePkgImpl propertyValuePkg = new PropertyValuePkgImpl();
    //begin-capella-code
    propertyValuePkg.setId(IdGenerator.createId());
    //end-capella-code
    return propertyValuePkg;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VisibilityKind createVisibilityKindFromString(EDataType eDataType, String initialValue) {
    VisibilityKind result = VisibilityKind.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String convertVisibilityKindToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CapellacorePackage getCapellacorePackage() {
    return (CapellacorePackage)getEPackage();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
	@Deprecated
	public static CapellacorePackage getPackage() {
    return CapellacorePackage.eINSTANCE;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public Constraint createConstraint(String name_p) {
    Constraint constraint = createConstraint();
    constraint.setName(name_p);	  
    return constraint;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public StringPropertyValue createStringPropertyValue(String name_p) {
    StringPropertyValue stringPropertyValue = createStringPropertyValue();
    stringPropertyValue.setName(name_p);	  
    return stringPropertyValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public IntegerPropertyValue createIntegerPropertyValue(String name_p) {
    IntegerPropertyValue integerPropertyValue = createIntegerPropertyValue();
    integerPropertyValue.setName(name_p);	  
    return integerPropertyValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public BooleanPropertyValue createBooleanPropertyValue(String name_p) {
    BooleanPropertyValue booleanPropertyValue = createBooleanPropertyValue();
    booleanPropertyValue.setName(name_p);	  
    return booleanPropertyValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public FloatPropertyValue createFloatPropertyValue(String name_p) {
    FloatPropertyValue floatPropertyValue = createFloatPropertyValue();
    floatPropertyValue.setName(name_p);	  
    return floatPropertyValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EnumerationPropertyValue createEnumerationPropertyValue(String name_p) {
    EnumerationPropertyValue enumerationPropertyValue = createEnumerationPropertyValue();
    enumerationPropertyValue.setName(name_p);	  
    return enumerationPropertyValue;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EnumerationPropertyType createEnumerationPropertyType(String name_p) {
    EnumerationPropertyType enumerationPropertyType = createEnumerationPropertyType();
    enumerationPropertyType.setName(name_p);	  
    return enumerationPropertyType;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public EnumerationPropertyLiteral createEnumerationPropertyLiteral(String name_p) {
    EnumerationPropertyLiteral enumerationPropertyLiteral = createEnumerationPropertyLiteral();
    enumerationPropertyLiteral.setName(name_p);	  
    return enumerationPropertyLiteral;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PropertyValueGroup createPropertyValueGroup(String name_p) {
    PropertyValueGroup propertyValueGroup = createPropertyValueGroup();
    propertyValueGroup.setName(name_p);	  
    return propertyValueGroup;
  }

	/**
	 * Creates class and sets its name
	 * (This method comes from a customization of the standard EMF generator)
	 *
	 * @param name_p : default name of created element
	 * @generated
	 */
	public PropertyValuePkg createPropertyValuePkg(String name_p) {
    PropertyValuePkg propertyValuePkg = createPropertyValuePkg();
    propertyValuePkg.setName(name_p);	  
    return propertyValuePkg;
  }

	//begin-capella-code

	//end-capella-code
} //CapellacoreFactoryImpl
