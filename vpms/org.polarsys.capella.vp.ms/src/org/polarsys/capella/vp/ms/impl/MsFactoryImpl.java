/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.vp.ms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.FSMType;
import org.polarsys.capella.vp.ms.InSituationExpression;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Situation;
import org.polarsys.capella.vp.ms.access_Type;
import org.polarsys.capella.vp.ms.kind_Type;
import org.polarsys.capella.vp.ms.ms_Type;
import org.polarsys.capella.vp.ms.selector_Type;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MsFactoryImpl extends EFactoryImpl implements MsFactory {
  /**
   * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public static MsFactory init() {
    try {
      MsFactory theMsFactory = (MsFactory) EPackage.Registry.INSTANCE.getEFactory(MsPackage.eNS_URI);
      if (theMsFactory != null) {
        return theMsFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MsFactoryImpl();
  }

  /**
   * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public MsFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
    case MsPackage.CS_CONFIGURATION:
      return createCSConfiguration();
    case MsPackage.FSM_TYPE:
      return createFSMType();
    case MsPackage.SITUATION:
      return createSituation();
    case MsPackage.IN_STATE_EXPRESSION:
      return createInStateExpression();
    case MsPackage.IN_SITUATION_EXPRESSION:
      return createInSituationExpression();
    case MsPackage.AND_OPERATION:
      return createAndOperation();
    case MsPackage.OR_OPERATION:
      return createOrOperation();
    case MsPackage.NOT_OPERATION:
      return createNotOperation();
    default:
      throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
    case MsPackage.KIND_TYPE:
      return createkind_TypeFromString(eDataType, initialValue);
    case MsPackage.ACCESS_TYPE:
      return createaccess_TypeFromString(eDataType, initialValue);
    case MsPackage.SELECTOR_TYPE:
      return createselector_TypeFromString(eDataType, initialValue);
    case MsPackage.MS_TYPE:
      return createms_TypeFromString(eDataType, initialValue);
    default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
    case MsPackage.KIND_TYPE:
      return convertkind_TypeToString(eDataType, instanceValue);
    case MsPackage.ACCESS_TYPE:
      return convertaccess_TypeToString(eDataType, instanceValue);
    case MsPackage.SELECTOR_TYPE:
      return convertselector_TypeToString(eDataType, instanceValue);
    case MsPackage.MS_TYPE:
      return convertms_TypeToString(eDataType, instanceValue);
    default:
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public CSConfiguration createCSConfiguration() {
    CSConfigurationImpl csConfiguration = new CSConfigurationImpl();
    return csConfiguration;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public FSMType createFSMType() {
    FSMTypeImpl fsmType = new FSMTypeImpl();
    return fsmType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Situation createSituation() {
    SituationImpl situation = new SituationImpl();
    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public InStateExpression createInStateExpression() {
    InStateExpressionImpl inStateExpression = new InStateExpressionImpl();
    return inStateExpression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public InSituationExpression createInSituationExpression() {
    InSituationExpressionImpl inSituationExpression = new InSituationExpressionImpl();
    return inSituationExpression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public AndOperation createAndOperation() {
    AndOperationImpl andOperation = new AndOperationImpl();
    return andOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public OrOperation createOrOperation() {
    OrOperationImpl orOperation = new OrOperationImpl();
    return orOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotOperation createNotOperation() {
    NotOperationImpl notOperation = new NotOperationImpl();
    return notOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public kind_Type createkind_TypeFromString(EDataType eDataType, String initialValue) {
    kind_Type result = kind_Type.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String convertkind_TypeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public access_Type createaccess_TypeFromString(EDataType eDataType, String initialValue) {
    access_Type result = access_Type.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String convertaccess_TypeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public selector_Type createselector_TypeFromString(EDataType eDataType, String initialValue) {
    selector_Type result = selector_Type.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String convertselector_TypeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public ms_Type createms_TypeFromString(EDataType eDataType, String initialValue) {
    ms_Type result = ms_Type.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String convertms_TypeToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public MsPackage getMsPackage() {
    return (MsPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MsPackage getPackage() {
    return MsPackage.eINSTANCE;
  }

} // MsFactoryImpl
