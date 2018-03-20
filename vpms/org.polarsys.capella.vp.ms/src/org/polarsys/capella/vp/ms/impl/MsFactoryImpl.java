/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *    Altran - Compare Configurations
 *******************************************************************************/

package org.polarsys.capella.vp.ms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.polarsys.capella.common.lib.IdGenerator;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.CSConfiguration;
import org.polarsys.capella.vp.ms.Comparison;
import org.polarsys.capella.vp.ms.FSMType;
import org.polarsys.capella.vp.ms.InSituationExpression;
import org.polarsys.capella.vp.ms.InStateExpression;
import org.polarsys.capella.vp.ms.MsFactory;
import org.polarsys.capella.vp.ms.MsPackage;
import org.polarsys.capella.vp.ms.NotOperation;
import org.polarsys.capella.vp.ms.OrOperation;
import org.polarsys.capella.vp.ms.Result;
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
    case MsPackage.COMPARISON:
      return createComparison();
    case MsPackage.RESULT:
      return createResult();
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
    // begin-capella-code

    csConfiguration.setId(IdGenerator.createId());

    // end-capella-code
    return csConfiguration;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public FSMType createFSMType() {
    FSMTypeImpl fsmType = new FSMTypeImpl();
    // begin-capella-code

    fsmType.setId(IdGenerator.createId());

    // end-capella-code
    return fsmType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Situation createSituation() {
    SituationImpl situation = new SituationImpl();
    // begin-capella-code

    situation.setId(IdGenerator.createId());

    // end-capella-code
    return situation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public InStateExpression createInStateExpression() {
    InStateExpressionImpl inStateExpression = new InStateExpressionImpl();
    // begin-capella-code

    inStateExpression.setId(IdGenerator.createId());

    // end-capella-code
    return inStateExpression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public InSituationExpression createInSituationExpression() {
    InSituationExpressionImpl inSituationExpression = new InSituationExpressionImpl();
    // begin-capella-code

    inSituationExpression.setId(IdGenerator.createId());

    // end-capella-code
    return inSituationExpression;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public AndOperation createAndOperation() {
    AndOperationImpl andOperation = new AndOperationImpl();
    // begin-capella-code

    andOperation.setId(IdGenerator.createId());

    // end-capella-code
    return andOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public OrOperation createOrOperation() {
    OrOperationImpl orOperation = new OrOperationImpl();
    // begin-capella-code

    orOperation.setId(IdGenerator.createId());

    // end-capella-code
    return orOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotOperation createNotOperation() {
    NotOperationImpl notOperation = new NotOperationImpl();
    // begin-capella-code

    notOperation.setId(IdGenerator.createId());

    // end-capella-code
    return notOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Comparison createComparison() {
    ComparisonImpl comparison = new ComparisonImpl();
    // begin-capella-code

    comparison.setId(IdGenerator.createId());

    // end-capella-code
    return comparison;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Result createResult() {
    ResultImpl result = new ResultImpl();
    // begin-capella-code

    result.setId(IdGenerator.createId());

    // end-capella-code
    return result;
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

  /**
   * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
   *
   * @param name_p
   *          : default name of created element
   * @generated
   */
  public CSConfiguration createCSConfiguration(String name_p) {
    CSConfiguration csConfiguration = createCSConfiguration();
    csConfiguration.setName(name_p);
    return csConfiguration;
  }

  /**
   * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
   *
   * @param name_p
   *          : default name of created element
   * @generated
   */
  public FSMType createFSMType(String name_p) {
    FSMType fsmType = createFSMType();
    fsmType.setName(name_p);
    return fsmType;
  }

  /**
   * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
   *
   * @param name_p
   *          : default name of created element
   * @generated
   */
  public Situation createSituation(String name_p) {
    Situation situation = createSituation();
    situation.setName(name_p);
    return situation;
  }

  /**
   * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
   *
   * @param name_p
   *          : default name of created element
   * @generated
   */
  public Comparison createComparison(String name_p) {
    Comparison comparison = createComparison();
    comparison.setName(name_p);
    return comparison;
  }

  /**
   * Creates class and sets its name (This method comes from a customization of the standard EMF generator)
   *
   * @param name_p
   *          : default name of created element
   * @generated
   */
  public Result createResult(String name_p) {
    Result result = createResult();
    result.setName(name_p);
    return result;
  }

} // MsFactoryImpl
