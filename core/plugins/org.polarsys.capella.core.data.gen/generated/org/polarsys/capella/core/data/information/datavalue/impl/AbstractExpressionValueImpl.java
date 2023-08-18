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

package org.polarsys.capella.core.data.information.datavalue.impl;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Expression Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getComplexType <em>Complex Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getEnumerationType <em>Enumeration Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getNumericType <em>Numeric Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getStringType <em>String Type</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getUnparsedExpression <em>Unparsed Expression</em>}</li>
 *   <li>{@link org.polarsys.capella.core.data.information.datavalue.impl.AbstractExpressionValueImpl#getExpressionType <em>Expression Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractExpressionValueImpl extends AbstractBooleanValueImpl implements AbstractExpressionValue {









	/**
   * The cached value of the '{@link #getUnit() <em>Unit</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUnit()
   * @generated
   * @ordered
   */
	protected Unit unit;













	/**
   * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
	protected static final String EXPRESSION_EDEFAULT = null;





	/**
   * The default value of the '{@link #getUnparsedExpression() <em>Unparsed Expression</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUnparsedExpression()
   * @generated
   * @ordered
   */
	protected static final String UNPARSED_EXPRESSION_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getUnparsedExpression() <em>Unparsed Expression</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getUnparsedExpression()
   * @generated
   * @ordered
   */
	protected String unparsedExpression = UNPARSED_EXPRESSION_EDEFAULT;








	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractExpressionValueImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Classifier getComplexType() {

    Classifier complexType = basicGetComplexType();
    return complexType != null && complexType.eIsProxy() ? (Classifier)eResolveProxy((InternalEObject)complexType) : complexType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Classifier basicGetComplexType() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE, annotation);
    
    try {
      return (Classifier) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Enumeration getEnumerationType() {

    Enumeration enumerationType = basicGetEnumerationType();
    return enumerationType != null && enumerationType.eIsProxy() ? (Enumeration)eResolveProxy((InternalEObject)enumerationType) : enumerationType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Enumeration basicGetEnumerationType() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE, annotation);
    
    try {
      return (Enumeration) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Unit getUnit() {

    if (unit != null && unit.eIsProxy()) {
      InternalEObject oldUnit = (InternalEObject)unit;
      unit = (Unit)eResolveProxy(oldUnit);
      if (unit != oldUnit) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT, oldUnit, unit));
      }
    }
    return unit;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public Unit basicGetUnit() {

    return unit;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUnit(Unit newUnit) {

    Unit oldUnit = unit;
    unit = newUnit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT, oldUnit, unit));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericType getNumericType() {

    NumericType numericType = basicGetNumericType();
    return numericType != null && numericType.eIsProxy() ? (NumericType)eResolveProxy((InternalEObject)numericType) : numericType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public NumericType basicGetNumericType() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.NUMERIC_VALUE__NUMERIC_TYPE, annotation);
    
    try {
      return (NumericType) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public StringType getStringType() {

    StringType stringType = basicGetStringType();
    return stringType != null && stringType.eIsProxy() ? (StringType)eResolveProxy((InternalEObject)stringType) : stringType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public StringType basicGetStringType() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.ABSTRACT_STRING_VALUE__STRING_TYPE, annotation);
    
    try {
      return (StringType) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getExpression() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION, annotation);
    
    try {
      return (String) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public String getUnparsedExpression() {

    return unparsedExpression;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	@Override
	public void setUnparsedExpression(String newUnparsedExpression) {

    String oldUnparsedExpression = unparsedExpression;
    unparsedExpression = newUnparsedExpression;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION, oldUnparsedExpression, unparsedExpression));

  }






	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataType getExpressionType() {

    DataType expressionType = basicGetExpressionType();
    return expressionType != null && expressionType.eIsProxy() ? (DataType)eResolveProxy((InternalEObject)expressionType) : expressionType;
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public DataType basicGetExpressionType() {


    Object result = null;
    // Helper that can get value for current feature.
    IHelper helper = null;
    // If current object is adaptable, ask it to get its IHelper.
    if (this instanceof IAdaptable) {
    	helper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);
    }
    if (null == helper) {
      // No helper found yet.
      // Ask the platform to get the adapter 'IHelper.class' for current object.
      IAdapterManager adapterManager = Platform.getAdapterManager();
      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);
    }
    if (null == helper) {
      EPackage package_l = eClass().getEPackage();
      // Get the root package of the owner package.
      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);
      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException("No helper retrieved for nsURI " + rootPackage.getNsURI());  //$NON-NLS-1$
    } 
    // A helper is found, let's use it. 
    EAnnotation annotation = DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE.getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
    result = helper.getValue(this, DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE, annotation);
    
    try {
      return (DataType) result;
    } catch (ClassCastException exception) {
       exception.printStackTrace();
      return null;
    }
    
  }




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE:
        if (resolve) return getComplexType();
        return basicGetComplexType();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE:
        if (resolve) return getEnumerationType();
        return basicGetEnumerationType();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT:
        if (resolve) return getUnit();
        return basicGetUnit();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE:
        if (resolve) return getNumericType();
        return basicGetNumericType();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__STRING_TYPE:
        if (resolve) return getStringType();
        return basicGetStringType();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__EXPRESSION:
        return getExpression();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION:
        return getUnparsedExpression();
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE:
        if (resolve) return getExpressionType();
        return basicGetExpressionType();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT:
          setUnit((Unit)newValue);
        return;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION:
          setUnparsedExpression((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT:
        setUnit((Unit)null);
        return;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION:
        setUnparsedExpression(UNPARSED_EXPRESSION_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE:
        return basicGetComplexType() != null;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE:
        return basicGetEnumerationType() != null;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT:
        return unit != null;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE:
        return basicGetNumericType() != null;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__STRING_TYPE:
        return basicGetStringType() != null;
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__EXPRESSION:
        return EXPRESSION_EDEFAULT == null ? getExpression() != null : !EXPRESSION_EDEFAULT.equals(getExpression());
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION:
        return UNPARSED_EXPRESSION_EDEFAULT == null ? unparsedExpression != null : !UNPARSED_EXPRESSION_EDEFAULT.equals(unparsedExpression);
      case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE:
        return basicGetExpressionType() != null;
    }
    return super.eIsSet(featureID);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractComplexValue.class) {
      switch (derivedFeatureID) {
        case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE: return DatavaluePackage.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractEnumerationValue.class) {
      switch (derivedFeatureID) {
        case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE: return DatavaluePackage.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE;
        default: return -1;
      }
    }
    if (baseClass == NumericValue.class) {
      switch (derivedFeatureID) {
        case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT: return DatavaluePackage.NUMERIC_VALUE__UNIT;
        case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE: return DatavaluePackage.NUMERIC_VALUE__NUMERIC_TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractStringValue.class) {
      switch (derivedFeatureID) {
        case DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__STRING_TYPE: return DatavaluePackage.ABSTRACT_STRING_VALUE__STRING_TYPE;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == AbstractComplexValue.class) {
      switch (baseFeatureID) {
        case DatavaluePackage.ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE: return DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__COMPLEX_TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractEnumerationValue.class) {
      switch (baseFeatureID) {
        case DatavaluePackage.ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE: return DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__ENUMERATION_TYPE;
        default: return -1;
      }
    }
    if (baseClass == NumericValue.class) {
      switch (baseFeatureID) {
        case DatavaluePackage.NUMERIC_VALUE__UNIT: return DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__UNIT;
        case DatavaluePackage.NUMERIC_VALUE__NUMERIC_TYPE: return DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__NUMERIC_TYPE;
        default: return -1;
      }
    }
    if (baseClass == AbstractStringValue.class) {
      switch (baseFeatureID) {
        case DatavaluePackage.ABSTRACT_STRING_VALUE__STRING_TYPE: return DatavaluePackage.ABSTRACT_EXPRESSION_VALUE__STRING_TYPE;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (unparsedExpression: "); //$NON-NLS-1$
    result.append(unparsedExpression);
    result.append(')');
    return result.toString();
  }


} //AbstractExpressionValueImpl