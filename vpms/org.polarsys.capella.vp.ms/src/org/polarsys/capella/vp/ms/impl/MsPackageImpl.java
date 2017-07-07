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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.vp.ms.AndOperation;
import org.polarsys.capella.vp.ms.BooleanExpression;
import org.polarsys.capella.vp.ms.BooleanOperation;
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
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MsPackageImpl extends EPackageImpl implements MsPackage {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass csConfigurationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass fsmTypeEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass situationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass booleanExpressionEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass booleanOperationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass inStateExpressionEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass inSituationExpressionEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass andOperationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass orOperationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass notOperationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass comparisonEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass resultEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EEnum kind_TypeEEnum = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EEnum access_TypeEEnum = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EEnum selector_TypeEEnum = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EEnum ms_TypeEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
   * EPackage.Registry} by the package package URI value.
   * <p>
   * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
   * performs initialization of the package, or returns the registered package, if one already exists. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.polarsys.capella.vp.ms.ms.MsPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private MsPackageImpl() {
    super(eNS_URI, MsFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>
   * This method is used to initialize {@link MsPackage#eINSTANCE} when that field is accessed. Clients should not
   * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static MsPackage init() {
    if (isInited)
      return (MsPackage) EPackage.Registry.INSTANCE.getEPackage(MsPackage.eNS_URI);

    // Obtain or create and register package
    MsPackageImpl theMsPackage = (MsPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MsPackageImpl
        ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MsPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    CapellacorePackage.eINSTANCE.eClass();
    OaPackage.eINSTANCE.eClass();
    CtxPackage.eINSTANCE.eClass();
    LaPackage.eINSTANCE.eClass();
    PaPackage.eINSTANCE.eClass();
    EpbsPackage.eINSTANCE.eClass();
    RequirementPackage.eINSTANCE.eClass();
    CapellacommonPackage.eINSTANCE.eClass();
    InformationPackage.eINSTANCE.eClass();
    CsPackage.eINSTANCE.eClass();
    FaPackage.eINSTANCE.eClass();
    InteractionPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theMsPackage.createPackageContents();

    // Initialize created meta-data
    theMsPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theMsPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(MsPackage.eNS_URI, theMsPackage);
    return theMsPackage;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getCSConfiguration() {
    return csConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_SupportedModes() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Elements() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_DeploymentLinks() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Functions() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_FunctionalChains() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Scenarios() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Components() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Ports() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_ChildConfigurations() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getCSConfiguration_Kind() {
    return (EAttribute) csConfigurationEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getCSConfiguration_Access() {
    return (EAttribute) csConfigurationEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getCSConfiguration_Selector() {
    return (EAttribute) csConfigurationEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_Context() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getCSConfiguration_CompareTo() {
    return (EReference) csConfigurationEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getFSMType() {
    return fsmTypeEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getFSMType_Ms() {
    return (EAttribute) fsmTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getSituation() {
    return situationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getSituation_Expression() {
    return (EReference) situationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getBooleanExpression() {
    return booleanExpressionEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getBooleanOperation() {
    return booleanOperationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getBooleanOperation_Children() {
    return (EReference) booleanOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getInStateExpression() {
    return inStateExpressionEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getInStateExpression_State() {
    return (EReference) inStateExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getInSituationExpression() {
    return inSituationExpressionEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getInSituationExpression_Situation() {
    return (EReference) inSituationExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getAndOperation() {
    return andOperationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getOrOperation() {
    return orOperationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getNotOperation() {
    return notOperationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getComparison() {
    return comparisonEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getComparison_Configuration1() {
    return (EReference) comparisonEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getComparison_Situation() {
    return (EReference) comparisonEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getComparison_Configuration2() {
    return (EReference) comparisonEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getResult() {
    return resultEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getResult_Situation() {
    return (EReference) resultEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EEnum getkind_Type() {
    return kind_TypeEEnum;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EEnum getaccess_Type() {
    return access_TypeEEnum;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EEnum getselector_Type() {
    return selector_TypeEEnum;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EEnum getms_Type() {
    return ms_TypeEEnum;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public MsFactory getMsFactory() {
    return (MsFactory) getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but its
   * first. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void createPackageContents() {
    if (isCreated)
      return;
    isCreated = true;

    // Create classes and their features
    csConfigurationEClass = createEClass(CS_CONFIGURATION);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__SUPPORTED_MODES);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__ELEMENTS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__DEPLOYMENT_LINKS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__FUNCTIONS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__FUNCTIONAL_CHAINS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__SCENARIOS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__COMPONENTS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__PORTS);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__CHILD_CONFIGURATIONS);
    createEAttribute(csConfigurationEClass, CS_CONFIGURATION__KIND);
    createEAttribute(csConfigurationEClass, CS_CONFIGURATION__ACCESS);
    createEAttribute(csConfigurationEClass, CS_CONFIGURATION__SELECTOR);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__CONTEXT);
    createEReference(csConfigurationEClass, CS_CONFIGURATION__COMPARE_TO);

    fsmTypeEClass = createEClass(FSM_TYPE);
    createEAttribute(fsmTypeEClass, FSM_TYPE__MS);

    situationEClass = createEClass(SITUATION);
    createEReference(situationEClass, SITUATION__EXPRESSION);

    booleanExpressionEClass = createEClass(BOOLEAN_EXPRESSION);

    booleanOperationEClass = createEClass(BOOLEAN_OPERATION);
    createEReference(booleanOperationEClass, BOOLEAN_OPERATION__CHILDREN);

    inStateExpressionEClass = createEClass(IN_STATE_EXPRESSION);
    createEReference(inStateExpressionEClass, IN_STATE_EXPRESSION__STATE);

    inSituationExpressionEClass = createEClass(IN_SITUATION_EXPRESSION);
    createEReference(inSituationExpressionEClass, IN_SITUATION_EXPRESSION__SITUATION);

    andOperationEClass = createEClass(AND_OPERATION);

    orOperationEClass = createEClass(OR_OPERATION);

    notOperationEClass = createEClass(NOT_OPERATION);

    comparisonEClass = createEClass(COMPARISON);
    createEReference(comparisonEClass, COMPARISON__CONFIGURATION1);
    createEReference(comparisonEClass, COMPARISON__SITUATION);
    createEReference(comparisonEClass, COMPARISON__CONFIGURATION2);

    resultEClass = createEClass(RESULT);
    createEReference(resultEClass, RESULT__SITUATION);

    // Create enums
    kind_TypeEEnum = createEEnum(KIND_TYPE);
    access_TypeEEnum = createEEnum(ACCESS_TYPE);
    selector_TypeEEnum = createEEnum(SELECTOR_TYPE);
    ms_TypeEEnum = createEEnum(MS_TYPE);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
   * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void initializePackageContents() {
    if (isInitialized)
      return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    CapellacorePackage theCapellacorePackage = (CapellacorePackage) EPackage.Registry.INSTANCE
        .getEPackage(CapellacorePackage.eNS_URI);
    EmdePackage theEmdePackage = (EmdePackage) EPackage.Registry.INSTANCE.getEPackage(EmdePackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage) EPackage.Registry.INSTANCE
        .getEPackage(CapellacommonPackage.eNS_URI);
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage) EPackage.Registry.INSTANCE
        .getEPackage(ModellingcorePackage.eNS_URI);
    CsPackage theCsPackage = (CsPackage) EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    FaPackage theFaPackage = (FaPackage) EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    InteractionPackage theInteractionPackage = (InteractionPackage) EPackage.Registry.INSTANCE
        .getEPackage(InteractionPackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage) EPackage.Registry.INSTANCE
        .getEPackage(InformationPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    csConfigurationEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    csConfigurationEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    fsmTypeEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    fsmTypeEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    situationEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    situationEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    booleanExpressionEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    booleanOperationEClass.getESuperTypes().add(this.getBooleanExpression());
    inStateExpressionEClass.getESuperTypes().add(this.getBooleanExpression());
    inSituationExpressionEClass.getESuperTypes().add(this.getBooleanExpression());
    andOperationEClass.getESuperTypes().add(this.getBooleanOperation());
    orOperationEClass.getESuperTypes().add(this.getBooleanOperation());
    notOperationEClass.getESuperTypes().add(this.getBooleanOperation());
    comparisonEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    comparisonEClass.getESuperTypes().add(theEmdePackage.getElementExtension());
    resultEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    resultEClass.getESuperTypes().add(theEmdePackage.getElementExtension());

    // Initialize classes and features; add operations and parameters
    initEClass(csConfigurationEClass, CSConfiguration.class, "CSConfiguration", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCSConfiguration_SupportedModes(), theCapellacommonPackage.getAbstractState(), null,
        "supportedModes", null, 0, -1, CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Elements(), theModellingcorePackage.getModelElement(), null, "elements", null, 0, //$NON-NLS-1$
        -1, CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_DeploymentLinks(), theCsPackage.getAbstractDeploymentLink(), null,
        "deploymentLinks", null, 0, -1, CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Functions(), theFaPackage.getAbstractFunction(), null, "functions", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_FunctionalChains(), theFaPackage.getFunctionalChain(), null, "functionalChains", //$NON-NLS-1$
        null, 0, -1, CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Scenarios(), theInteractionPackage.getScenario(), null, "scenarios", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Components(), theCsPackage.getComponent(), null, "components", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Ports(), theInformationPackage.getPort(), null, "ports", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_ChildConfigurations(), this.getCSConfiguration(), null, "childConfigurations", //$NON-NLS-1$
        null, 0, -1, CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
        IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCSConfiguration_Kind(), this.getkind_Type(), "kind", null, 0, 1, CSConfiguration.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCSConfiguration_Access(), this.getaccess_Type(), "access", null, 0, 1, CSConfiguration.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCSConfiguration_Selector(), this.getselector_Type(), "selector", null, 0, 1, //$NON-NLS-1$
        CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_Context(), this.getSituation(), null, "context", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCSConfiguration_CompareTo(), this.getCSConfiguration(), null, "compareTo", null, 0, -1, //$NON-NLS-1$
        CSConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    EOperation op = addEOperation(csConfigurationEClass, ecorePackage.getEBoolean(), "includes", 1, 1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);
    addEParameter(op, theModellingcorePackage.getModelElement(), "element", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    addEOperation(csConfigurationEClass, theModellingcorePackage.getModelElement(), "getScope", 0, -1, IS_UNIQUE, //$NON-NLS-1$
        IS_ORDERED);

    op = addEOperation(csConfigurationEClass, null, "getScope", 0, -1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    ETypeParameter t1 = addETypeParameter(op, "T"); //$NON-NLS-1$
    EGenericType g1 = createEGenericType(theModellingcorePackage.getModelElement());
    t1.getEBounds().add(g1);
    g1 = createEGenericType(ecorePackage.getEJavaClass());
    EGenericType g2 = createEGenericType(t1);
    g1.getETypeArguments().add(g2);
    addEParameter(op, g1, "type", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
    g1 = createEGenericType(t1);
    initEOperation(op, g1);

    initEClass(fsmTypeEClass, FSMType.class, "FSMType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getFSMType_Ms(), this.getms_Type(), "ms", null, 0, 1, FSMType.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(situationEClass, Situation.class, "Situation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSituation_Expression(), this.getBooleanExpression(), null, "expression", null, 0, 1, //$NON-NLS-1$
        Situation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(booleanExpressionEClass, BooleanExpression.class, "BooleanExpression", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(booleanOperationEClass, BooleanOperation.class, "BooleanOperation", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBooleanOperation_Children(), this.getBooleanExpression(), null, "children", null, 0, -1, //$NON-NLS-1$
        BooleanOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inStateExpressionEClass, InStateExpression.class, "InStateExpression", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInStateExpression_State(), theCapellacommonPackage.getAbstractState(), null, "state", null, 0, 1, //$NON-NLS-1$
        InStateExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inSituationExpressionEClass, InSituationExpression.class, "InSituationExpression", !IS_ABSTRACT, //$NON-NLS-1$
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getInSituationExpression_Situation(), this.getSituation(), null, "situation", null, 0, 1, //$NON-NLS-1$
        InSituationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(andOperationEClass, AndOperation.class, "AndOperation", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(orOperationEClass, OrOperation.class, "OrOperation", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(notOperationEClass, NotOperation.class, "NotOperation", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);

    initEClass(comparisonEClass, Comparison.class, "Comparison", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getComparison_Configuration1(), this.getCSConfiguration(), null, "configuration1", null, 0, -1, //$NON-NLS-1$
        Comparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getComparison_Situation(), this.getSituation(), null, "Situation", null, 0, -1, Comparison.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEReference(getComparison_Configuration2(), this.getCSConfiguration(), null, "configuration2", null, 0, -1, //$NON-NLS-1$
        Comparison.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(resultEClass, Result.class, "Result", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getResult_Situation(), this.getSituation(), null, "Situation", null, 0, -1, Result.class, //$NON-NLS-1$
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(kind_TypeEEnum, kind_Type.class, "kind_Type"); //$NON-NLS-1$
    addEEnumLiteral(kind_TypeEEnum, kind_Type.ATOMIC);
    addEEnumLiteral(kind_TypeEEnum, kind_Type.GLOBAL);

    initEEnum(access_TypeEEnum, access_Type.class, "access_Type"); //$NON-NLS-1$
    addEEnumLiteral(access_TypeEEnum, access_Type.FLAT);
    addEEnumLiteral(access_TypeEEnum, access_Type.SUBCOMPONENTS);
    addEEnumLiteral(access_TypeEEnum, access_Type.FULL);

    initEEnum(selector_TypeEEnum, selector_Type.class, "selector_Type"); //$NON-NLS-1$
    addEEnumLiteral(selector_TypeEEnum, selector_Type.INCLUSION);
    addEEnumLiteral(selector_TypeEEnum, selector_Type.EXCLUSION);

    initEEnum(ms_TypeEEnum, ms_Type.class, "ms_Type"); //$NON-NLS-1$
    addEEnumLiteral(ms_TypeEEnum, ms_Type.MIXED);
    addEEnumLiteral(ms_TypeEEnum, ms_Type.STATE);
    addEEnumLiteral(ms_TypeEEnum, ms_Type.MODE);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraint
    createConstraintAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping
    createConstraintMappingAnnotations();
  }

  /**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraint</b>. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void createConstraintAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraint"; //$NON-NLS-1$
    addAnnotation(csConfigurationEClass, source,
        new String[] { "ExtendedElement", " http://www.polarsys.org/capella/core/cs/1.1.0#//Component" //$NON-NLS-1$ //$NON-NLS-2$
        });
    addAnnotation(fsmTypeEClass, source,
        new String[] { "ExtendedElement", " http://www.polarsys.org/capella/core/common/1.1.0#//StateMachine" //$NON-NLS-1$ //$NON-NLS-2$
        });
    addAnnotation(situationEClass, source,
        new String[] { "ExtendedElement", " http://www.polarsys.org/capella/core/cs/1.1.0#//Component" //$NON-NLS-1$ //$NON-NLS-2$
        });
    addAnnotation(comparisonEClass, source,
        new String[] { "ExtendedElement", " http://www.polarsys.org/capella/core/cs/1.1.0#//Component" //$NON-NLS-1$ //$NON-NLS-2$
        });
  }

  /**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping</b>. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected void createConstraintMappingAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/constraintMapping"; //$NON-NLS-1$
    addAnnotation(csConfigurationEClass, source, new String[] { "Mapping", //$NON-NLS-1$
        "platform:/plugin/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore#//Component" //$NON-NLS-1$
    });
    addAnnotation(fsmTypeEClass, source, new String[] { "Mapping", //$NON-NLS-1$
        " platform:/plugin/org.polarsys.capella.core.data.gen/model/CapellaCommon.ecore#//StateMachine" //$NON-NLS-1$
    });
    addAnnotation(situationEClass, source, new String[] { "Mapping", //$NON-NLS-1$
        " platform:/plugin/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore#//Component" //$NON-NLS-1$
    });
    addAnnotation(comparisonEClass, source, new String[] { "Mapping", //$NON-NLS-1$
        " platform:/plugin/org.polarsys.capella.core.data.gen/model/CompositeStructure.ecore#//Component" //$NON-NLS-1$
    });
  }

} // MsPackageImpl
