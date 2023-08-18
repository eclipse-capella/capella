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
package org.polarsys.capella.common.data.modellingcore.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.modellingcore.AbstractConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractParameter;
import org.polarsys.capella.common.data.modellingcore.AbstractParameterSet;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcoreFactory;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.ParameterEffectKind;
import org.polarsys.capella.common.data.modellingcore.PublishableElement;
import org.polarsys.capella.common.data.modellingcore.RateKind;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModellingcorePackageImpl extends EPackageImpl implements ModellingcorePackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass modelElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractRelationshipEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractNamedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass informationsExchangerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass traceableElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass finalizableElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass publishableElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractTypedElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractTraceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractConstraintEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass valueSpecificationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractParameterEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractParameterSetEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractInformationFlowEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractExchangeItemEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass iStateEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum parameterEffectKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum rateKindEEnum = null;

	/**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.polarsys.capella.common.data.modellingcore.ModellingcorePackage#eNS_URI
   * @see #init()
   * @generated
   */
	private ModellingcorePackageImpl() {
    super(eNS_URI, ModellingcoreFactory.eINSTANCE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static boolean isInited = false;

	/**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link ModellingcorePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static ModellingcorePackage init() {
    if (isInited) return (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredModellingcorePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    ModellingcorePackageImpl theModellingcorePackage = registeredModellingcorePackage instanceof ModellingcorePackageImpl ? (ModellingcorePackageImpl)registeredModellingcorePackage : new ModellingcorePackageImpl();

    isInited = true;

    // Initialize simple dependencies
    EmdePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theModellingcorePackage.createPackageContents();

    // Initialize created meta-data
    theModellingcorePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theModellingcorePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ModellingcorePackage.eNS_URI, theModellingcorePackage);
    return theModellingcorePackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getModelElement() {
    return modelElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getModelElement_Id() {
    return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getModelElement_Sid() {
    return (EAttribute)modelElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getModelElement_Constraints() {
    return (EReference)modelElementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getModelElement_OwnedConstraints() {
    return (EReference)modelElementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getModelElement_OwnedMigratedElements() {
    return (EReference)modelElementEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractRelationship() {
    return abstractRelationshipEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractRelationship_RealizedFlow() {
    return (EReference)abstractRelationshipEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractNamedElement() {
    return abstractNamedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractNamedElement_Name() {
    return (EAttribute)abstractNamedElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInformationsExchanger() {
    return informationsExchangerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInformationsExchanger_IncomingInformationFlows() {
    return (EReference)informationsExchangerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInformationsExchanger_OutgoingInformationFlows() {
    return (EReference)informationsExchangerEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInformationsExchanger_InformationFlows() {
    return (EReference)informationsExchangerEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getTraceableElement() {
    return traceableElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getTraceableElement_IncomingTraces() {
    return (EReference)traceableElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getTraceableElement_OutgoingTraces() {
    return (EReference)traceableElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFinalizableElement() {
    return finalizableElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getFinalizableElement_Final() {
    return (EAttribute)finalizableElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPublishableElement() {
    return publishableElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getPublishableElement_VisibleInDoc() {
    return (EAttribute)publishableElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getPublishableElement_VisibleInLM() {
    return (EAttribute)publishableElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractType() {
    return abstractTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractType_AbstractTypedElements() {
    return (EReference)abstractTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractTypedElement() {
    return abstractTypedElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractTypedElement_AbstractType() {
    return (EReference)abstractTypedElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractTrace() {
    return abstractTraceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractTrace_TargetElement() {
    return (EReference)abstractTraceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractTrace_SourceElement() {
    return (EReference)abstractTraceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractConstraint() {
    return abstractConstraintEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractConstraint_ConstrainedElements() {
    return (EReference)abstractConstraintEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractConstraint_OwnedSpecification() {
    return (EReference)abstractConstraintEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractConstraint_Context() {
    return (EReference)abstractConstraintEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getValueSpecification() {
    return valueSpecificationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractParameter() {
    return abstractParameterEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractParameter_IsException() {
    return (EAttribute)abstractParameterEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractParameter_IsStream() {
    return (EAttribute)abstractParameterEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractParameter_IsOptional() {
    return (EAttribute)abstractParameterEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractParameter_KindOfRate() {
    return (EAttribute)abstractParameterEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractParameter_Effect() {
    return (EAttribute)abstractParameterEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameter_Rate() {
    return (EReference)abstractParameterEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameter_Probability() {
    return (EReference)abstractParameterEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameter_ParameterSet() {
    return (EReference)abstractParameterEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractParameterSet() {
    return abstractParameterSetEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameterSet_OwnedConditions() {
    return (EReference)abstractParameterSetEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameterSet_Probability() {
    return (EReference)abstractParameterSetEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractParameterSet_Parameters() {
    return (EReference)abstractParameterSetEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractInformationFlow() {
    return abstractInformationFlowEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractInformationFlow_Realizations() {
    return (EReference)abstractInformationFlowEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractInformationFlow_ConvoyedInformations() {
    return (EReference)abstractInformationFlowEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractInformationFlow_Source() {
    return (EReference)abstractInformationFlowEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractInformationFlow_Target() {
    return (EReference)abstractInformationFlowEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractExchangeItem() {
    return abstractExchangeItemEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getIState() {
    return iStateEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getIState_ReferencedStates() {
    return (EReference)iStateEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
		public EReference getIState_ExploitedStates() {
    return (EReference)iStateEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getParameterEffectKind() {
    return parameterEffectKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getRateKind() {
    return rateKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ModellingcoreFactory getModellingcoreFactory() {
    return (ModellingcoreFactory)getEFactoryInstance();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isCreated = false;

	/**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelElementEClass = createEClass(MODEL_ELEMENT);
    createEAttribute(modelElementEClass, MODEL_ELEMENT__ID);
    createEAttribute(modelElementEClass, MODEL_ELEMENT__SID);
    createEReference(modelElementEClass, MODEL_ELEMENT__CONSTRAINTS);
    createEReference(modelElementEClass, MODEL_ELEMENT__OWNED_CONSTRAINTS);
    createEReference(modelElementEClass, MODEL_ELEMENT__OWNED_MIGRATED_ELEMENTS);

    abstractRelationshipEClass = createEClass(ABSTRACT_RELATIONSHIP);
    createEReference(abstractRelationshipEClass, ABSTRACT_RELATIONSHIP__REALIZED_FLOW);

    abstractNamedElementEClass = createEClass(ABSTRACT_NAMED_ELEMENT);
    createEAttribute(abstractNamedElementEClass, ABSTRACT_NAMED_ELEMENT__NAME);

    informationsExchangerEClass = createEClass(INFORMATIONS_EXCHANGER);
    createEReference(informationsExchangerEClass, INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS);
    createEReference(informationsExchangerEClass, INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS);
    createEReference(informationsExchangerEClass, INFORMATIONS_EXCHANGER__INFORMATION_FLOWS);

    traceableElementEClass = createEClass(TRACEABLE_ELEMENT);
    createEReference(traceableElementEClass, TRACEABLE_ELEMENT__INCOMING_TRACES);
    createEReference(traceableElementEClass, TRACEABLE_ELEMENT__OUTGOING_TRACES);

    finalizableElementEClass = createEClass(FINALIZABLE_ELEMENT);
    createEAttribute(finalizableElementEClass, FINALIZABLE_ELEMENT__FINAL);

    publishableElementEClass = createEClass(PUBLISHABLE_ELEMENT);
    createEAttribute(publishableElementEClass, PUBLISHABLE_ELEMENT__VISIBLE_IN_DOC);
    createEAttribute(publishableElementEClass, PUBLISHABLE_ELEMENT__VISIBLE_IN_LM);

    abstractTypeEClass = createEClass(ABSTRACT_TYPE);
    createEReference(abstractTypeEClass, ABSTRACT_TYPE__ABSTRACT_TYPED_ELEMENTS);

    abstractTypedElementEClass = createEClass(ABSTRACT_TYPED_ELEMENT);
    createEReference(abstractTypedElementEClass, ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);

    abstractTraceEClass = createEClass(ABSTRACT_TRACE);
    createEReference(abstractTraceEClass, ABSTRACT_TRACE__TARGET_ELEMENT);
    createEReference(abstractTraceEClass, ABSTRACT_TRACE__SOURCE_ELEMENT);

    abstractConstraintEClass = createEClass(ABSTRACT_CONSTRAINT);
    createEReference(abstractConstraintEClass, ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS);
    createEReference(abstractConstraintEClass, ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION);
    createEReference(abstractConstraintEClass, ABSTRACT_CONSTRAINT__CONTEXT);

    valueSpecificationEClass = createEClass(VALUE_SPECIFICATION);

    abstractParameterEClass = createEClass(ABSTRACT_PARAMETER);
    createEAttribute(abstractParameterEClass, ABSTRACT_PARAMETER__IS_EXCEPTION);
    createEAttribute(abstractParameterEClass, ABSTRACT_PARAMETER__IS_STREAM);
    createEAttribute(abstractParameterEClass, ABSTRACT_PARAMETER__IS_OPTIONAL);
    createEAttribute(abstractParameterEClass, ABSTRACT_PARAMETER__KIND_OF_RATE);
    createEAttribute(abstractParameterEClass, ABSTRACT_PARAMETER__EFFECT);
    createEReference(abstractParameterEClass, ABSTRACT_PARAMETER__RATE);
    createEReference(abstractParameterEClass, ABSTRACT_PARAMETER__PROBABILITY);
    createEReference(abstractParameterEClass, ABSTRACT_PARAMETER__PARAMETER_SET);

    abstractParameterSetEClass = createEClass(ABSTRACT_PARAMETER_SET);
    createEReference(abstractParameterSetEClass, ABSTRACT_PARAMETER_SET__OWNED_CONDITIONS);
    createEReference(abstractParameterSetEClass, ABSTRACT_PARAMETER_SET__PROBABILITY);
    createEReference(abstractParameterSetEClass, ABSTRACT_PARAMETER_SET__PARAMETERS);

    abstractInformationFlowEClass = createEClass(ABSTRACT_INFORMATION_FLOW);
    createEReference(abstractInformationFlowEClass, ABSTRACT_INFORMATION_FLOW__REALIZATIONS);
    createEReference(abstractInformationFlowEClass, ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS);
    createEReference(abstractInformationFlowEClass, ABSTRACT_INFORMATION_FLOW__SOURCE);
    createEReference(abstractInformationFlowEClass, ABSTRACT_INFORMATION_FLOW__TARGET);

    abstractExchangeItemEClass = createEClass(ABSTRACT_EXCHANGE_ITEM);

    iStateEClass = createEClass(ISTATE);
    createEReference(iStateEClass, ISTATE__REFERENCED_STATES);
    createEReference(iStateEClass, ISTATE__EXPLOITED_STATES);

    // Create enums
    parameterEffectKindEEnum = createEEnum(PARAMETER_EFFECT_KIND);
    rateKindEEnum = createEEnum(RATE_KIND);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private boolean isInitialized = false;

	/**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    EmdePackage theEmdePackage = (EmdePackage)EPackage.Registry.INSTANCE.getEPackage(EmdePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    modelElementEClass.getESuperTypes().add(theEmdePackage.getExtensibleElement());
    abstractRelationshipEClass.getESuperTypes().add(this.getModelElement());
    abstractNamedElementEClass.getESuperTypes().add(this.getModelElement());
    informationsExchangerEClass.getESuperTypes().add(this.getModelElement());
    traceableElementEClass.getESuperTypes().add(this.getModelElement());
    finalizableElementEClass.getESuperTypes().add(this.getModelElement());
    publishableElementEClass.getESuperTypes().add(this.getModelElement());
    abstractTypeEClass.getESuperTypes().add(this.getAbstractNamedElement());
    abstractTypedElementEClass.getESuperTypes().add(this.getAbstractNamedElement());
    abstractTraceEClass.getESuperTypes().add(this.getTraceableElement());
    abstractConstraintEClass.getESuperTypes().add(this.getModelElement());
    valueSpecificationEClass.getESuperTypes().add(this.getAbstractTypedElement());
    abstractParameterEClass.getESuperTypes().add(this.getAbstractTypedElement());
    abstractParameterSetEClass.getESuperTypes().add(this.getAbstractNamedElement());
    abstractInformationFlowEClass.getESuperTypes().add(this.getAbstractNamedElement());
    abstractInformationFlowEClass.getESuperTypes().add(this.getAbstractRelationship());
    abstractExchangeItemEClass.getESuperTypes().add(this.getAbstractType());
    iStateEClass.getESuperTypes().add(this.getAbstractNamedElement());

    // Initialize classes and features; add operations and parameters
    initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getModelElement_Id(), ecorePackage.getEString(), "id", null, 1, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getModelElement_Sid(), ecorePackage.getEString(), "sid", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getModelElement_Constraints(), this.getAbstractConstraint(), null, "constraints", null, 0, -1, ModelElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getModelElement_OwnedConstraints(), this.getAbstractConstraint(), null, "ownedConstraints", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getModelElement_OwnedMigratedElements(), this.getModelElement(), null, "ownedMigratedElements", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    addEOperation(modelElementEClass, null, "destroy", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    addEOperation(modelElementEClass, ecorePackage.getEString(), "getFullLabel", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    addEOperation(modelElementEClass, ecorePackage.getEString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    addEOperation(modelElementEClass, ecorePackage.getEBoolean(), "hasUnnamedLabel", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractRelationshipEClass, AbstractRelationship.class, "AbstractRelationship", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractRelationship_RealizedFlow(), this.getAbstractInformationFlow(), this.getAbstractInformationFlow_Realizations(), "realizedFlow", null, 0, 1, AbstractRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractNamedElementEClass, AbstractNamedElement.class, "AbstractNamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getAbstractNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, AbstractNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(informationsExchangerEClass, InformationsExchanger.class, "InformationsExchanger", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInformationsExchanger_IncomingInformationFlows(), this.getAbstractInformationFlow(), null, "incomingInformationFlows", null, 0, -1, InformationsExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInformationsExchanger_OutgoingInformationFlows(), this.getAbstractInformationFlow(), null, "outgoingInformationFlows", null, 0, -1, InformationsExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInformationsExchanger_InformationFlows(), this.getAbstractInformationFlow(), null, "informationFlows", null, 0, -1, InformationsExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(traceableElementEClass, TraceableElement.class, "TraceableElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getTraceableElement_IncomingTraces(), this.getAbstractTrace(), null, "incomingTraces", null, 0, -1, TraceableElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getTraceableElement_OutgoingTraces(), this.getAbstractTrace(), null, "outgoingTraces", null, 0, -1, TraceableElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(finalizableElementEClass, FinalizableElement.class, "FinalizableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getFinalizableElement_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, FinalizableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(publishableElementEClass, PublishableElement.class, "PublishableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getPublishableElement_VisibleInDoc(), ecorePackage.getEBoolean(), "visibleInDoc", "true", 0, 1, PublishableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getPublishableElement_VisibleInLM(), ecorePackage.getEBoolean(), "visibleInLM", "true", 0, 1, PublishableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

    initEClass(abstractTypeEClass, AbstractType.class, "AbstractType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractType_AbstractTypedElements(), this.getAbstractTypedElement(), null, "abstractTypedElements", null, 0, -1, AbstractType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractTypedElementEClass, AbstractTypedElement.class, "AbstractTypedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractTypedElement_AbstractType(), this.getAbstractType(), null, "abstractType", null, 0, 1, AbstractTypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractTraceEClass, AbstractTrace.class, "AbstractTrace", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractTrace_TargetElement(), this.getTraceableElement(), null, "targetElement", null, 1, 1, AbstractTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractTrace_SourceElement(), this.getTraceableElement(), null, "sourceElement", null, 1, 1, AbstractTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractConstraintEClass, AbstractConstraint.class, "AbstractConstraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractConstraint_ConstrainedElements(), this.getModelElement(), null, "constrainedElements", null, 0, -1, AbstractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractConstraint_OwnedSpecification(), this.getValueSpecification(), null, "ownedSpecification", null, 0, 1, AbstractConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractConstraint_Context(), this.getModelElement(), null, "context", null, 0, 1, AbstractConstraint.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(valueSpecificationEClass, ValueSpecification.class, "ValueSpecification", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(abstractParameterEClass, AbstractParameter.class, "AbstractParameter", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getAbstractParameter_IsException(), ecorePackage.getEBoolean(), "isException", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractParameter_IsStream(), ecorePackage.getEBoolean(), "isStream", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractParameter_IsOptional(), ecorePackage.getEBoolean(), "isOptional", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractParameter_KindOfRate(), this.getRateKind(), "kindOfRate", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractParameter_Effect(), this.getParameterEffectKind(), "effect", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractParameter_Rate(), this.getValueSpecification(), null, "rate", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractParameter_Probability(), this.getValueSpecification(), null, "probability", null, 0, 1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractParameter_ParameterSet(), this.getAbstractParameterSet(), this.getAbstractParameterSet_Parameters(), "parameterSet", null, 0, -1, AbstractParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractParameterSetEClass, AbstractParameterSet.class, "AbstractParameterSet", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractParameterSet_OwnedConditions(), this.getAbstractConstraint(), null, "ownedConditions", null, 0, -1, AbstractParameterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractParameterSet_Probability(), this.getValueSpecification(), null, "probability", null, 0, 1, AbstractParameterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractParameterSet_Parameters(), this.getAbstractParameter(), this.getAbstractParameter_ParameterSet(), "parameters", null, 1, -1, AbstractParameterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractInformationFlowEClass, AbstractInformationFlow.class, "AbstractInformationFlow", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractInformationFlow_Realizations(), this.getAbstractRelationship(), this.getAbstractRelationship_RealizedFlow(), "realizations", null, 0, -1, AbstractInformationFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractInformationFlow_ConvoyedInformations(), this.getAbstractExchangeItem(), null, "convoyedInformations", null, 0, -1, AbstractInformationFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractInformationFlow_Source(), this.getInformationsExchanger(), null, "source", null, 1, 1, AbstractInformationFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractInformationFlow_Target(), this.getInformationsExchanger(), null, "target", null, 1, 1, AbstractInformationFlow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractExchangeItemEClass, AbstractExchangeItem.class, "AbstractExchangeItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(iStateEClass, IState.class, "IState", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getIState_ReferencedStates(), this.getIState(), null, "referencedStates", null, 0, -1, IState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getIState_ExploitedStates(), this.getIState(), null, "exploitedStates", null, 0, -1, IState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(parameterEffectKindEEnum, ParameterEffectKind.class, "ParameterEffectKind"); //$NON-NLS-1$
    addEEnumLiteral(parameterEffectKindEEnum, ParameterEffectKind.CREATE);
    addEEnumLiteral(parameterEffectKindEEnum, ParameterEffectKind.READ);
    addEEnumLiteral(parameterEffectKindEEnum, ParameterEffectKind.UPDATE);
    addEEnumLiteral(parameterEffectKindEEnum, ParameterEffectKind.DELETE);

    initEEnum(rateKindEEnum, RateKind.class, "RateKind"); //$NON-NLS-1$
    addEEnumLiteral(rateKindEEnum, RateKind.UNSPECIFIED);
    addEEnumLiteral(rateKindEEnum, RateKind.CONTINUOUS);
    addEEnumLiteral(rateKindEEnum, RateKind.DISCRETE);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/capella/2007/BusinessInformation
    createBusinessInformationAnnotations();
    // http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
    createMappingAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore
    createIgnoreAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/UML2Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createUML2MappingAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/UML2Mapping"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
         "profileName", "ModellingCore" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (modelElementEClass,
       source,
       new String[] {
         "metaclass", "Element" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractRelationshipEClass,
       source,
       new String[] {
         "metaclass", "Element" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractNamedElementEClass,
       source,
       new String[] {
         "metaclass", "AbstractNamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractNamedElement_Name(),
       source,
       new String[] {
         "featureName", "name", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "AbstractNamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (traceableElementEClass,
       source,
       new String[] {
         "metaclass", "Element", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.TraceableElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
         "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTypedElement_AbstractType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTraceEClass,
       source,
       new String[] {
         "metaclass", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_TargetElement(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_SourceElement(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/ecore/documentation</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createDocumentationAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/ecore/documentation"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
         "description", "ModellingCore aims at defining the core concepts of any modelling language.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (modelElementEClass,
       source,
       new String[] {
         "description", "common base for all Capella elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_Id(),
       source,
       new String[] {
         "description", "the unique identifier for this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_Sid(),
       source,
       new String[] {
         "description", "the unique system identifier for this element", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_Constraints(),
       source,
       new String[] {
         "description", "the list of constraints applying to this element of the model\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_OwnedConstraints(),
       source,
       new String[] {
         "description", "the constraints that are stored/owned by this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_OwnedMigratedElements(),
       source,
       new String[] {
         "description", "Temporary migrated elements for the purpose of model migration.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractRelationshipEClass,
       source,
       new String[] {
         "description", "A relationship references one or more related elements. Relationship is an abstract metaclass\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractRelationship_RealizedFlow(),
       source,
       new String[] {
         "description", "the information flow that this relationship realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractNamedElementEClass,
       source,
       new String[] {
         "description", "A named element represents elements that may have a name. The name is used for identification of the named element\r\nwithin the namespace in which it is defined. A named element also has a qualified name that allows it to be\r\nunambiguously identified within a hierarchy of nested namespaces. NamedElement is an abstract metaclass.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- If there is no name, or one of the containing namespaces has no name, there is no qualified name.\r\n- When there is a name, and all of the containing namespaces have a name, the qualified name is constructed from the names of the containing namespaces.\r\n- If a NamedElement is not owned by a Namespace, it does not have a visibility.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractNamedElement_Name(),
       source,
       new String[] {
         "description", "The name of the NamedElement\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (informationsExchangerEClass,
       source,
       new String[] {
         "description", "a model element that is the source and/or destination of information flows\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_IncomingInformationFlows(),
       source,
       new String[] {
         "description", "list of information flows coming towards this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_OutgoingInformationFlows(),
       source,
       new String[] {
         "description", "list of information flows coming out of this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (traceableElementEClass,
       source,
       new String[] {
         "description", "Base abstract class supporting the ability to trace a model element to/from other elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
         "description", "the list of trace relationships pointing towards this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
         "description", "the list of trace relationships starting from this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (finalizableElementEClass,
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFinalizableElement_Final(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (publishableElementEClass,
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPublishableElement_VisibleInDoc(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPublishableElement_VisibleInLM(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTypeEClass,
       source,
       new String[] {
         "description", "base abstract class supporting the definition of data types\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractType_AbstractTypedElements(),
       source,
       new String[] {
         "description", "the list of typed elements that reference this type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTypedElementEClass,
       source,
       new String[] {
         "description", "a (named) model element to which a specific type is associated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTypedElement_AbstractType(),
       source,
       new String[] {
         "description", "the type associated to this model element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTraceEClass,
       source,
       new String[] {
         "description", "abstract base class supporting the ability to define a trace relationship between two model elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_TargetElement(),
       source,
       new String[] {
         "description", "the target/end of the trace link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_SourceElement(),
       source,
       new String[] {
         "description", "the source/beginning of the trace link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractConstraintEClass,
       source,
       new String[] {
         "description", "specifies a constraint applying to a given set of model elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "The value specification for a constraint must evaluate to a Boolean value.\r\n\r\nEvaluating the value specification for a constraint must not have side effects\r\n\r\nA constraint cannot be applied to itself.", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_ConstrainedElements(),
       source,
       new String[] {
         "description", "the model elements being involved in the definition of this constraint\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_OwnedSpecification(),
       source,
       new String[] {
         "description", "A condition that must be true when evaluated in order for the constraint to be satisfied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_Context(),
       source,
       new String[] {
         "description", "Specifies the element that is the context for evaluating this constraint, which is the Constraint\'s parent element." //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (valueSpecificationEClass,
       source,
       new String[] {
         "description", "A value specification is the specification of a (possibly empty) set of instances, including both objects and data values\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractParameterEClass,
       source,
       new String[] {
         "description", "A parameter is a specification of an argument used to pass information into or out of an invocation of a behavioral\r\nfeature.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- A parameter cannot be a stream and exception at the same time.\r\n- An input parameter cannot be an exception.\r\n- Reentrant behaviors cannot have stream parameters.\r\n- Only in and inout parameters may have a delete effect. Only out, inout, and return parameters may have a create effect.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsException(),
       source,
       new String[] {
         "description", "Tells whether an output parameter may emit a value to the exclusion of the other outputs\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsStream(),
       source,
       new String[] {
         "description", "Tells whether an input parameter may accept values while its behavior is executing, or whether an output parameter\r\npost values while the behavior is executing\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsOptional(),
       source,
       new String[] {
         "description", "specifies whether the parameter is optional or not\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_KindOfRate(),
       source,
       new String[] {
         "description", "refer to RateKind enumeration description\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to RateKind enumeration definition\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Effect(),
       source,
       new String[] {
         "description", "Specifies the effect that the owner of the parameter has on values passed in or out of the parameter\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see ParameterEffectKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Rate(),
       source,
       new String[] {
         "description", "the number of objects or values that flow in or out of the parameter per time interval while the behavior or operation is executing\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "this field only makes sense if the parameter is a streaming one.\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Probability(),
       source,
       new String[] {
         "description", "Likelihood that values will be output on a parameter set\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "the probability should be a number between 0 and 1", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_ParameterSet(),
       source,
       new String[] {
         "description", "The parameter sets containing the parameter. See AbstractParameterSet\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum,
       source,
       new String[] {
         "description", "The datatype ParameterEffectKind is an enumeration that indicates the effect of a behavior on values passed in or out of\r\nits parameters\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "referenced parameter value is being created upon behavior execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "referenced parameter value is only being read upon behavior execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "referenced parameter value is being updated upon behavior execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "referenced parameter value is being deleted upon behavior execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractParameterSetEClass,
       source,
       new String[] {
         "description", "A parameter set is an element that provides alternative sets of inputs or outputs that a behavior may use.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- The parameters in a parameter set must all be inputs or all be outputs of the same parameterized entity, and the parameter set is owned by that entity.\r\n- If a behavior has input parameters that are in a parameter set, then any inputs that are not in a parameter set must be streaming. Same for output parameters.\r\n- Two parameter sets cannot have exactly the same set of parameters.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_OwnedConditions(),
       source,
       new String[] {
         "description", "Constraint that should be satisfied for the owner of the parameters in an input parameter set to start execution using\r\nthe values provided for those parameters, or the owner of the parameters in an output parameter set to end execution\r\nproviding the values for those parameters, if all preconditions and conditions on input parameter sets were satisfied\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_Probability(),
       source,
       new String[] {
         "description", "the probability the parameter set will be given values at runtime\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "These must be between zero and one inclusive, and add up to one for output parameter sets of the\r\nsame behavior at the time the probabilities are used.\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_Parameters(),
       source,
       new String[] {
         "description", "Parameters in the parameter set.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum,
       source,
       new String[] {
         "description", "enumeration containing the possible caracterizations for the rate of a streaming parameter\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the rate kind is not precised\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the rate characterizes a continuous flow\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Used when the rate characterizes a discrete flow\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractInformationFlowEClass,
       source,
       new String[] {
         "description", "An InformationFlow specifies that one or more information items circulates from its sources to its targets. Information\r\nflows require some kind of \'information channel\' for transmitting information items from the source to the destination.\r\nAn information channel is represented in various ways depending on the nature of its sources and targets.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Realizations(),
       source,
       new String[] {
         "description", "Determines which Relationship will realize the specified flow.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_ConvoyedInformations(),
       source,
       new String[] {
         "description", "Specifies the information items that may circulate on this information flow\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Source(),
       source,
       new String[] {
         "description", "Defines from which source the conveyed information items are initiated\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Target(),
       source,
       new String[] {
         "description", "Defines to which target the conveyed information items are directed\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractExchangeItemEClass,
       source,
       new String[] {
         "description", "Set of exchanged element (e.g. data, material...) expected or provided, exchanged between ports", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (iStateEClass,
       source,
       new String[] {
         "description", "A vertex is an abstraction of a node in a state machine graph. In general, it can be the source or destination of any number\r\nof transitions.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getIState_ReferencedStates(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getIState_ExploitedStates(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/semantic</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createSemanticAnnotations() {
    String source = "http://www.polarsys.org/capella/semantic"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
       });
    addAnnotation
      (getModelElement_Id(),
       source,
       new String[] {
       });
    addAnnotation
      (getModelElement_Sid(),
       source,
       new String[] {
       });
    addAnnotation
      (getModelElement_Constraints(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_OwnedConstraints(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractNamedElement_Name(),
       source,
       new String[] {
       });
    addAnnotation
      (getInformationsExchanger_IncomingInformationFlows(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_OutgoingInformationFlows(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_ConstrainedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractConstraint_OwnedSpecification(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_IsException(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_IsStream(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_IsOptional(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_KindOfRate(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_Effect(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_Rate(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractParameter_Probability(),
       source,
       new String[] {
       });
    addAnnotation
      (parameterEffectKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (rateKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractInformationFlow_ConvoyedInformations(),
       source,
       new String[] {
       });
    addAnnotation
      (getIState_ReferencedStates(),
       source,
       new String[] {
       });
    addAnnotation
      (getIState_ExploitedStates(),
       source,
       new String[] {
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/emde/1.0.0/extension</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createExtensionAnnotations() {
    String source = "http://www.polarsys.org/kitalpha/emde/1.0.0/extension"; //$NON-NLS-1$
    addAnnotation
      (this,
       source,
       new String[] {
         "trackResourceModification", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "useUUIDs", "false", //$NON-NLS-1$ //$NON-NLS-2$
         "useIDAttributes", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "extensibleProviderFactory", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "childCreationExtenders", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/BusinessInformation</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createBusinessInformationAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/BusinessInformation"; //$NON-NLS-1$
    addAnnotation
      (modelElementEClass,
       source,
       new String[] {
         "Label", "ModelElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractRelationshipEClass,
       source,
       new String[] {
         "Label", "Relationship" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractNamedElementEClass,
       source,
       new String[] {
         "Label", "AbstractNamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractNamedElement_Name(),
       source,
       new String[] {
         "namingAttribute", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "Label", "name" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (traceableElementEClass,
       source,
       new String[] {
         "Label", "TraceableElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
         "Label", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
         "Label", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTypedElement_AbstractType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTraceEClass,
       source,
       new String[] {
         "Label", "AbstractTrace" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_TargetElement(),
       source,
       new String[] {
         "Label", "target" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_SourceElement(),
       source,
       new String[] {
         "Label", "source" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createMappingAnnotations() {
    String source = "http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping"; //$NON-NLS-1$
    addAnnotation
      (modelElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Element", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_Id(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_Constraints(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Constraint::constrainedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getModelElement_OwnedConstraints(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some packaged elements of uml::Element::nearestPackage on which AbstractConstraint stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractRelationshipEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Relationship", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractRelationship_RealizedFlow(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::InformationFlow::realization", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractNamedElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::NamedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractNamedElement_Name(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::name", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (informationsExchangerEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::NamedElement in order to map Capella AbstractInformationFlow::source and  AbstractInformationFlow::target to uml::InformationFlow::source and uml::InformationFlow::target", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_IncomingInformationFlows(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::InformationFlow::informationSource", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_OutgoingInformationFlows(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::InformationFlow::informationTarget", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_InformationFlows(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (traceableElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::NamedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and Transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and Transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (finalizableElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (publishableElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTypeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Type", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractType_AbstractTypedElements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and Transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTypedElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::TypedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTypedElement_AbstractType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::TypedElement::type", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractTraceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_TargetElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Dependency::supplier elements on which TraceableElement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractTrace_SourceElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::client", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Dependency::client elements on which TraceableElement stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractConstraintEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Constraint", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_ConstrainedElements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Constraint::constrainedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (valueSpecificationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::ValueSpecification,uml::InstanceSpecification\r\numl::InstanceSpecification in order to map Capella AbstractParameter::rate to SysML::Activities::Rate", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractParameterEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsException(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::isException", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsStream(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::isStream", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_IsOptional(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Capella AbstractParameter::isOptional is true if stereotype SysML::Activities::Optional is applied, false if not applied", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_KindOfRate(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Capella AbstractParameter::kindOfRate is Capella RateKind::Continuous if stereotype SysML::Activities::Continuous is applied\r\nCapella AbstractParameter::kindOfRate is Capella RateKind::Discrete if stereotype SysML::Activities::Discrete is applied\r\nIf none is applied, Capella AbstractParameter::kindOfRate is Capella RateKind::Unspecified", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Applied stereotype that inherits from SysML::Activities::Rate stereotype must be either SysML::Activities::Continuous or SysML::Activities::Discrete.\r\nIf none of both stereotypes are applied, kindOfRate is considered Unspecified" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Effect(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::effect", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Rate(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Activities::Probability does not extend uml::Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_Probability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Activities::Probability does not extend uml::Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameter_ParameterSet(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::parameterSet", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterEffectKind", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterEffectKind::create", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterEffectKind::read", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterEffectKind::update", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEffectKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterEffectKind::delete", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractParameterSetEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::ParameterSet", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_OwnedConditions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterSet::condition", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_Probability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "If SysML::Activities::Probability stereotype is applied, Capella AbstractParameterSet::probability is equal to SysML::Activities::Probability::probability.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractParameterSet_Parameters(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterSet::parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Stereotype that inherits from SysML::Activities::Rate (SysML::Activities::Continuous or SysML::Activities::Discrete)", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Neither SysML::Activities::Continuous or SysML::Activities::Discrete stereotypes are applied", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "SysML::Activities::Continuous", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Activities::Continuous stereotype is applied", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (rateKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "SysML::Activities::Discrete", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "SysML::Activities::Discrete", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractInformationFlowEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::InformationFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Realizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_ConvoyedInformations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::conveyed", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order will not be preserved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Source(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::informationSource", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInformationFlow_Target(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::informationTarget", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractExchangeItemEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Classifier in order to map Capella AbstractInformationFlow::convoyedInformations to uml::InformationFlow::conveyed.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (iStateEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Vertex", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getIState_ReferencedStates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getIState_ExploitedStates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/derived</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createDerivedAnnotations() {
    String source = "http://www.polarsys.org/capella/derived"; //$NON-NLS-1$
    addAnnotation
      (getModelElement_Constraints(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "constrainedElements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_IncomingInformationFlows(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "target" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_OutgoingInformationFlows(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "source" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInformationsExchanger_InformationFlows(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractInformationFlow.source(target, self);\r\n} or {\r\n\tAbstractInformationFlow.target(target, self);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractType_AbstractTypedElements(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractConstraint_Context(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedConstraints" //$NON-NLS-1$ //$NON-NLS-2$
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createIgnoreAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore"; //$NON-NLS-1$
    addAnnotation
      (abstractRelationshipEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractTraceEClass,
       source,
       new String[] {
       });
  }

	/**
   * Initializes the annotations for <b>http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createSegmentAnnotations() {
    String source = "http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment"; //$NON-NLS-1$
    addAnnotation
      (getTraceableElement_IncomingTraces(),
       source,
       new String[] {
       });
    addAnnotation
      (getTraceableElement_OutgoingTraces(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractTypedElement_AbstractType(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractTrace_TargetElement(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractTrace_SourceElement(),
       source,
       new String[] {
       });
  }

} //ModellingcorePackageImpl
