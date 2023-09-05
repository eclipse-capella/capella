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
package org.polarsys.capella.core.data.interaction.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.impl.CapellacommonPackageImpl;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.impl.CapellacorePackageImpl;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.impl.CapellamodellerPackageImpl;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.impl.CsPackageImpl;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.impl.CtxPackageImpl;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.epbs.impl.EpbsPackageImpl;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.impl.FaPackageImpl;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl;
import org.polarsys.capella.core.data.information.impl.InformationPackageImpl;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.ArmTimerEvent;
import org.polarsys.capella.core.data.interaction.CancelTimerEvent;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.ConstraintDuration;
import org.polarsys.capella.core.data.interaction.CreationEvent;
import org.polarsys.capella.core.data.interaction.DestructionEvent;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEvent;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.SequenceMessageValuation;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.impl.LaPackageImpl;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.impl.OaPackageImpl;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.pa.deployment.impl.DeploymentPackageImpl;
import org.polarsys.capella.core.data.pa.impl.PaPackageImpl;


import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InteractionPackageImpl extends EPackageImpl implements InteractionPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass sequenceMessageEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass scenarioEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass messageEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass executionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass executionEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass creationEventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass destructionEventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass executionEventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass instanceRoleEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass eventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass eventReceiptOperationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass eventSentOperationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass mergeLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass refinementLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityExtendEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityExtensionPointEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityGeneralizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCapabilityIncludeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interactionFragmentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interactionStateEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interactionUseEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass combinedFragmentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass gateEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass interactionOperandEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass timeLapseEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFragmentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass fragmentEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainAbstractCapabilityInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionAbstractCapabilityInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass scenarioRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass stateFragmentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass armTimerEventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass cancelTimerEventEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass constraintDurationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass sequenceMessageValuationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum messageKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum scenarioKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum interactionOperatorKindEEnum = null;

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
   * @see org.polarsys.capella.core.data.interaction.InteractionPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private InteractionPackageImpl() {
    super(eNS_URI, InteractionFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link InteractionPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static InteractionPackage init() {
    if (isInited) return (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredInteractionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    InteractionPackageImpl theInteractionPackage = registeredInteractionPackage instanceof InteractionPackageImpl ? (InteractionPackageImpl)registeredInteractionPackage : new InteractionPackageImpl();

    isInited = true;

    // Initialize simple dependencies
    ModellingcorePackage.eINSTANCE.eClass();
    EmdePackage.eINSTANCE.eClass();
    ActivityPackage.eINSTANCE.eClass();
    BehaviorPackage.eINSTANCE.eClass();

    // Obtain or create and register interdependencies
    Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI);
    CapellamodellerPackageImpl theCapellamodellerPackage = (CapellamodellerPackageImpl)(registeredPackage instanceof CapellamodellerPackageImpl ? registeredPackage : CapellamodellerPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    CapellacorePackageImpl theCapellacorePackage = (CapellacorePackageImpl)(registeredPackage instanceof CapellacorePackageImpl ? registeredPackage : CapellacorePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI);
    OaPackageImpl theOaPackage = (OaPackageImpl)(registeredPackage instanceof OaPackageImpl ? registeredPackage : OaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);
    CtxPackageImpl theCtxPackage = (CtxPackageImpl)(registeredPackage instanceof CtxPackageImpl ? registeredPackage : CtxPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    LaPackageImpl theLaPackage = (LaPackageImpl)(registeredPackage instanceof LaPackageImpl ? registeredPackage : LaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI);
    PaPackageImpl thePaPackage = (PaPackageImpl)(registeredPackage instanceof PaPackageImpl ? registeredPackage : PaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI);
    DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(registeredPackage instanceof DeploymentPackageImpl ? registeredPackage : DeploymentPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI);
    EpbsPackageImpl theEpbsPackage = (EpbsPackageImpl)(registeredPackage instanceof EpbsPackageImpl ? registeredPackage : EpbsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI);
    SharedmodelPackageImpl theSharedmodelPackage = (SharedmodelPackageImpl)(registeredPackage instanceof SharedmodelPackageImpl ? registeredPackage : SharedmodelPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    CapellacommonPackageImpl theCapellacommonPackage = (CapellacommonPackageImpl)(registeredPackage instanceof CapellacommonPackageImpl ? registeredPackage : CapellacommonPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    InformationPackageImpl theInformationPackage = (InformationPackageImpl)(registeredPackage instanceof InformationPackageImpl ? registeredPackage : InformationPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI);
    CommunicationPackageImpl theCommunicationPackage = (CommunicationPackageImpl)(registeredPackage instanceof CommunicationPackageImpl ? registeredPackage : CommunicationPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);
    DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(registeredPackage instanceof DatatypePackageImpl ? registeredPackage : DatatypePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(registeredPackage instanceof DatavaluePackageImpl ? registeredPackage : DatavaluePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    CsPackageImpl theCsPackage = (CsPackageImpl)(registeredPackage instanceof CsPackageImpl ? registeredPackage : CsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);

    // Create package meta-data objects
    theInteractionPackage.createPackageContents();
    theCapellamodellerPackage.createPackageContents();
    theCapellacorePackage.createPackageContents();
    theOaPackage.createPackageContents();
    theCtxPackage.createPackageContents();
    theLaPackage.createPackageContents();
    thePaPackage.createPackageContents();
    theDeploymentPackage.createPackageContents();
    theEpbsPackage.createPackageContents();
    theSharedmodelPackage.createPackageContents();
    theCapellacommonPackage.createPackageContents();
    theInformationPackage.createPackageContents();
    theCommunicationPackage.createPackageContents();
    theDatatypePackage.createPackageContents();
    theDatavaluePackage.createPackageContents();
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();

    // Initialize created meta-data
    theInteractionPackage.initializePackageContents();
    theCapellamodellerPackage.initializePackageContents();
    theCapellacorePackage.initializePackageContents();
    theOaPackage.initializePackageContents();
    theCtxPackage.initializePackageContents();
    theLaPackage.initializePackageContents();
    thePaPackage.initializePackageContents();
    theDeploymentPackage.initializePackageContents();
    theEpbsPackage.initializePackageContents();
    theSharedmodelPackage.initializePackageContents();
    theCapellacommonPackage.initializePackageContents();
    theInformationPackage.initializePackageContents();
    theCommunicationPackage.initializePackageContents();
    theDatatypePackage.initializePackageContents();
    theDatavaluePackage.initializePackageContents();
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theInteractionPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(InteractionPackage.eNS_URI, theInteractionPackage);
    return theInteractionPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSequenceMessage() {
    return sequenceMessageEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getSequenceMessage_Kind() {
    return (EAttribute)sequenceMessageEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_ExchangeContext() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_SendingEnd() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_ReceivingEnd() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_InvokedOperation() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_ExchangedItems() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_SendingPart() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_ReceivingPart() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_SendingFunction() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_ReceivingFunction() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessage_OwnedSequenceMessageValuations() {
    return (EReference)sequenceMessageEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getScenario() {
    return scenarioEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_PreCondition() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_PostCondition() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getScenario_Kind() {
    return (EAttribute)scenarioEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getScenario_Merged() {
    return (EAttribute)scenarioEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedInstanceRoles() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedMessages() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedInteractionFragments() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedTimeLapses() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedEvents() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedFormalGates() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedScenarioRealization() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_OwnedConstraintDurations() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_ContainedFunctions() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_ContainedParts() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_ReferencedScenarios() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_RealizedScenarios() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenario_RealizingScenarios() {
    return (EReference)scenarioEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMessageEnd() {
    return messageEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMessageEnd_Message() {
    return (EReference)messageEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExecution() {
    return executionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExecution_Covered() {
    return (EReference)executionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExecutionEnd() {
    return executionEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExecutionEnd_Execution() {
    return (EReference)executionEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCreationEvent() {
    return creationEventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDestructionEvent() {
    return destructionEventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExecutionEvent() {
    return executionEventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInstanceRole() {
    return instanceRoleEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInstanceRole_AbstractEnds() {
    return (EReference)instanceRoleEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInstanceRole_RepresentedInstance() {
    return (EReference)instanceRoleEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractEnd() {
    return abstractEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractEnd_Event() {
    return (EReference)abstractEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractEnd_Covered() {
    return (EReference)abstractEndEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEvent() {
    return eventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEventReceiptOperation() {
    return eventReceiptOperationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEventReceiptOperation_Operation() {
    return (EReference)eventReceiptOperationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEventSentOperation() {
    return eventSentOperationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEventSentOperation_Operation() {
    return (EReference)eventSentOperationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMergeLink() {
    return mergeLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getRefinementLink() {
    return refinementLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapabilityRealization() {
    return abstractCapabilityRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityRealization_RealizedCapability() {
    return (EReference)abstractCapabilityRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityRealization_RealizingCapability() {
    return (EReference)abstractCapabilityRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapability() {
    return abstractCapabilityEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_PreCondition() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_PostCondition() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_OwnedScenarios() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_IncomingCapabilityAllocation() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_OutgoingCapabilityAllocation() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Extends() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Extending() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_AbstractCapabilityExtensionPoints() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_SuperGeneralizations() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_SubGeneralizations() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Includes() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Including() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Super() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_Sub() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_IncludedAbstractCapabilities() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_IncludingAbstractCapabilities() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_ExtendedAbstractCapabilities() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_ExtendingAbstractCapabilities() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(17);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(18);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(19);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_AvailableInStates() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(20);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_OwnedAbstractCapabilityRealizations() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(21);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_InvolvedAbstractFunctions() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(22);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapability_InvolvedFunctionalChains() {
    return (EReference)abstractCapabilityEClass.getEStructuralFeatures().get(23);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapabilityExtend() {
    return abstractCapabilityExtendEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityExtend_Extended() {
    return (EReference)abstractCapabilityExtendEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityExtend_Extension() {
    return (EReference)abstractCapabilityExtendEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityExtend_ExtensionLocation() {
    return (EReference)abstractCapabilityExtendEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapabilityExtensionPoint() {
    return abstractCapabilityExtensionPointEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityExtensionPoint_AbstractCapability() {
    return (EReference)abstractCapabilityExtensionPointEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityExtensionPoint_ExtendLinks() {
    return (EReference)abstractCapabilityExtensionPointEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapabilityGeneralization() {
    return abstractCapabilityGeneralizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityGeneralization_Super() {
    return (EReference)abstractCapabilityGeneralizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityGeneralization_Sub() {
    return (EReference)abstractCapabilityGeneralizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCapabilityInclude() {
    return abstractCapabilityIncludeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityInclude_Included() {
    return (EReference)abstractCapabilityIncludeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractCapabilityInclude_Inclusion() {
    return (EReference)abstractCapabilityIncludeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInteractionFragment() {
    return interactionFragmentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionFragment_CoveredInstanceRoles() {
    return (EReference)interactionFragmentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInteractionState() {
    return interactionStateEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractState() model documentation} for details.
   * @generated
   */
	@Deprecated
		@Override
	public EReference getInteractionState_RelatedAbstractState() {
    return (EReference)interactionStateEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.interaction.InteractionState#getRelatedAbstractFunction() model documentation} for details.
   * @generated
   */
	@Deprecated
		@Override
	public EReference getInteractionState_RelatedAbstractFunction() {
    return (EReference)interactionStateEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionState_Covered() {
    return (EReference)interactionStateEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInteractionUse() {
    return interactionUseEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionUse_ReferencedScenario() {
    return (EReference)interactionUseEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionUse_ActualGates() {
    return (EReference)interactionUseEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCombinedFragment() {
    return combinedFragmentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCombinedFragment_Operator() {
    return (EAttribute)combinedFragmentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCombinedFragment_ReferencedOperands() {
    return (EReference)combinedFragmentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCombinedFragment_ExpressionGates() {
    return (EReference)combinedFragmentEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getGate() {
    return gateEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInteractionOperand() {
    return interactionOperandEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionOperand_Guard() {
    return (EReference)interactionOperandEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getInteractionOperand_ReferencedInteractionFragments() {
    return (EReference)interactionOperandEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getTimeLapse() {
    return timeLapseEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getTimeLapse_Start() {
    return (EReference)timeLapseEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getTimeLapse_Finish() {
    return (EReference)timeLapseEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFragment() {
    return abstractFragmentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFragment_OwnedGates() {
    return (EReference)abstractFragmentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFragmentEnd() {
    return fragmentEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFragmentEnd_AbstractFragment() {
    return (EReference)fragmentEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainAbstractCapabilityInvolvement() {
    return functionalChainAbstractCapabilityInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainAbstractCapabilityInvolvement_Capability() {
    return (EReference)functionalChainAbstractCapabilityInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain() {
    return (EReference)functionalChainAbstractCapabilityInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunctionAbstractCapabilityInvolvement() {
    return abstractFunctionAbstractCapabilityInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionAbstractCapabilityInvolvement_Capability() {
    return (EReference)abstractFunctionAbstractCapabilityInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionAbstractCapabilityInvolvement_Function() {
    return (EReference)abstractFunctionAbstractCapabilityInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getScenarioRealization() {
    return scenarioRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenarioRealization_RealizedScenario() {
    return (EReference)scenarioRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getScenarioRealization_RealizingScenario() {
    return (EReference)scenarioRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getStateFragment() {
    return stateFragmentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStateFragment_RelatedAbstractState() {
    return (EReference)stateFragmentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStateFragment_RelatedAbstractFunction() {
    return (EReference)stateFragmentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getArmTimerEvent() {
    return armTimerEventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCancelTimerEvent() {
    return cancelTimerEventEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getConstraintDuration() {
    return constraintDurationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getConstraintDuration_Duration() {
    return (EAttribute)constraintDurationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConstraintDuration_Start() {
    return (EReference)constraintDurationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getConstraintDuration_Finish() {
    return (EReference)constraintDurationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSequenceMessageValuation() {
    return sequenceMessageValuationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessageValuation_ExchangeItemElement() {
    return (EReference)sequenceMessageValuationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceMessageValuation_Value() {
    return (EReference)sequenceMessageValuationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getMessageKind() {
    return messageKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getScenarioKind() {
    return scenarioKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getInteractionOperatorKind() {
    return interactionOperatorKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InteractionFactory getInteractionFactory() {
    return (InteractionFactory)getEFactoryInstance();
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
	@SuppressWarnings("deprecation")
	public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    sequenceMessageEClass = createEClass(SEQUENCE_MESSAGE);
    createEAttribute(sequenceMessageEClass, SEQUENCE_MESSAGE__KIND);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__EXCHANGE_CONTEXT);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__SENDING_END);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__RECEIVING_END);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__INVOKED_OPERATION);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__SENDING_PART);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__RECEIVING_PART);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__SENDING_FUNCTION);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__RECEIVING_FUNCTION);
    createEReference(sequenceMessageEClass, SEQUENCE_MESSAGE__OWNED_SEQUENCE_MESSAGE_VALUATIONS);

    scenarioEClass = createEClass(SCENARIO);
    createEAttribute(scenarioEClass, SCENARIO__KIND);
    createEAttribute(scenarioEClass, SCENARIO__MERGED);
    createEReference(scenarioEClass, SCENARIO__PRE_CONDITION);
    createEReference(scenarioEClass, SCENARIO__POST_CONDITION);
    createEReference(scenarioEClass, SCENARIO__OWNED_INSTANCE_ROLES);
    createEReference(scenarioEClass, SCENARIO__OWNED_MESSAGES);
    createEReference(scenarioEClass, SCENARIO__OWNED_INTERACTION_FRAGMENTS);
    createEReference(scenarioEClass, SCENARIO__OWNED_TIME_LAPSES);
    createEReference(scenarioEClass, SCENARIO__OWNED_EVENTS);
    createEReference(scenarioEClass, SCENARIO__OWNED_FORMAL_GATES);
    createEReference(scenarioEClass, SCENARIO__OWNED_SCENARIO_REALIZATION);
    createEReference(scenarioEClass, SCENARIO__OWNED_CONSTRAINT_DURATIONS);
    createEReference(scenarioEClass, SCENARIO__CONTAINED_FUNCTIONS);
    createEReference(scenarioEClass, SCENARIO__CONTAINED_PARTS);
    createEReference(scenarioEClass, SCENARIO__REFERENCED_SCENARIOS);
    createEReference(scenarioEClass, SCENARIO__REALIZED_SCENARIOS);
    createEReference(scenarioEClass, SCENARIO__REALIZING_SCENARIOS);

    messageEndEClass = createEClass(MESSAGE_END);
    createEReference(messageEndEClass, MESSAGE_END__MESSAGE);

    executionEClass = createEClass(EXECUTION);
    createEReference(executionEClass, EXECUTION__COVERED);

    executionEndEClass = createEClass(EXECUTION_END);
    createEReference(executionEndEClass, EXECUTION_END__EXECUTION);

    creationEventEClass = createEClass(CREATION_EVENT);

    destructionEventEClass = createEClass(DESTRUCTION_EVENT);

    executionEventEClass = createEClass(EXECUTION_EVENT);

    instanceRoleEClass = createEClass(INSTANCE_ROLE);
    createEReference(instanceRoleEClass, INSTANCE_ROLE__ABSTRACT_ENDS);
    createEReference(instanceRoleEClass, INSTANCE_ROLE__REPRESENTED_INSTANCE);

    abstractEndEClass = createEClass(ABSTRACT_END);
    createEReference(abstractEndEClass, ABSTRACT_END__EVENT);
    createEReference(abstractEndEClass, ABSTRACT_END__COVERED);

    eventEClass = createEClass(EVENT);

    eventReceiptOperationEClass = createEClass(EVENT_RECEIPT_OPERATION);
    createEReference(eventReceiptOperationEClass, EVENT_RECEIPT_OPERATION__OPERATION);

    eventSentOperationEClass = createEClass(EVENT_SENT_OPERATION);
    createEReference(eventSentOperationEClass, EVENT_SENT_OPERATION__OPERATION);

    mergeLinkEClass = createEClass(MERGE_LINK);

    refinementLinkEClass = createEClass(REFINEMENT_LINK);

    abstractCapabilityRealizationEClass = createEClass(ABSTRACT_CAPABILITY_REALIZATION);
    createEReference(abstractCapabilityRealizationEClass, ABSTRACT_CAPABILITY_REALIZATION__REALIZED_CAPABILITY);
    createEReference(abstractCapabilityRealizationEClass, ABSTRACT_CAPABILITY_REALIZATION__REALIZING_CAPABILITY);

    abstractCapabilityEClass = createEClass(ABSTRACT_CAPABILITY);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__PRE_CONDITION);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__POST_CONDITION);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__OWNED_SCENARIOS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INCOMING_CAPABILITY_ALLOCATION);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__OUTGOING_CAPABILITY_ALLOCATION);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__EXTENDS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__EXTENDING);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__ABSTRACT_CAPABILITY_EXTENSION_POINTS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__SUPER_GENERALIZATIONS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__SUB_GENERALIZATIONS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INCLUDES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INCLUDING);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__SUPER);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__SUB);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INCLUDED_ABSTRACT_CAPABILITIES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INCLUDING_ABSTRACT_CAPABILITIES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__EXTENDED_ABSTRACT_CAPABILITIES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__EXTENDING_ABSTRACT_CAPABILITIES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__OWNED_FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENTS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__OWNED_ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENTS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__AVAILABLE_IN_STATES);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__OWNED_ABSTRACT_CAPABILITY_REALIZATIONS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INVOLVED_ABSTRACT_FUNCTIONS);
    createEReference(abstractCapabilityEClass, ABSTRACT_CAPABILITY__INVOLVED_FUNCTIONAL_CHAINS);

    abstractCapabilityExtendEClass = createEClass(ABSTRACT_CAPABILITY_EXTEND);
    createEReference(abstractCapabilityExtendEClass, ABSTRACT_CAPABILITY_EXTEND__EXTENDED);
    createEReference(abstractCapabilityExtendEClass, ABSTRACT_CAPABILITY_EXTEND__EXTENSION);
    createEReference(abstractCapabilityExtendEClass, ABSTRACT_CAPABILITY_EXTEND__EXTENSION_LOCATION);

    abstractCapabilityExtensionPointEClass = createEClass(ABSTRACT_CAPABILITY_EXTENSION_POINT);
    createEReference(abstractCapabilityExtensionPointEClass, ABSTRACT_CAPABILITY_EXTENSION_POINT__ABSTRACT_CAPABILITY);
    createEReference(abstractCapabilityExtensionPointEClass, ABSTRACT_CAPABILITY_EXTENSION_POINT__EXTEND_LINKS);

    abstractCapabilityGeneralizationEClass = createEClass(ABSTRACT_CAPABILITY_GENERALIZATION);
    createEReference(abstractCapabilityGeneralizationEClass, ABSTRACT_CAPABILITY_GENERALIZATION__SUPER);
    createEReference(abstractCapabilityGeneralizationEClass, ABSTRACT_CAPABILITY_GENERALIZATION__SUB);

    abstractCapabilityIncludeEClass = createEClass(ABSTRACT_CAPABILITY_INCLUDE);
    createEReference(abstractCapabilityIncludeEClass, ABSTRACT_CAPABILITY_INCLUDE__INCLUDED);
    createEReference(abstractCapabilityIncludeEClass, ABSTRACT_CAPABILITY_INCLUDE__INCLUSION);

    interactionFragmentEClass = createEClass(INTERACTION_FRAGMENT);
    createEReference(interactionFragmentEClass, INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES);

    interactionStateEClass = createEClass(INTERACTION_STATE);
    createEReference(interactionStateEClass, INTERACTION_STATE__RELATED_ABSTRACT_STATE);
    createEReference(interactionStateEClass, INTERACTION_STATE__RELATED_ABSTRACT_FUNCTION);
    createEReference(interactionStateEClass, INTERACTION_STATE__COVERED);

    interactionUseEClass = createEClass(INTERACTION_USE);
    createEReference(interactionUseEClass, INTERACTION_USE__REFERENCED_SCENARIO);
    createEReference(interactionUseEClass, INTERACTION_USE__ACTUAL_GATES);

    combinedFragmentEClass = createEClass(COMBINED_FRAGMENT);
    createEAttribute(combinedFragmentEClass, COMBINED_FRAGMENT__OPERATOR);
    createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__REFERENCED_OPERANDS);
    createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__EXPRESSION_GATES);

    gateEClass = createEClass(GATE);

    interactionOperandEClass = createEClass(INTERACTION_OPERAND);
    createEReference(interactionOperandEClass, INTERACTION_OPERAND__REFERENCED_INTERACTION_FRAGMENTS);
    createEReference(interactionOperandEClass, INTERACTION_OPERAND__GUARD);

    timeLapseEClass = createEClass(TIME_LAPSE);
    createEReference(timeLapseEClass, TIME_LAPSE__START);
    createEReference(timeLapseEClass, TIME_LAPSE__FINISH);

    abstractFragmentEClass = createEClass(ABSTRACT_FRAGMENT);
    createEReference(abstractFragmentEClass, ABSTRACT_FRAGMENT__OWNED_GATES);

    fragmentEndEClass = createEClass(FRAGMENT_END);
    createEReference(fragmentEndEClass, FRAGMENT_END__ABSTRACT_FRAGMENT);

    functionalChainAbstractCapabilityInvolvementEClass = createEClass(FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT);
    createEReference(functionalChainAbstractCapabilityInvolvementEClass, FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY);
    createEReference(functionalChainAbstractCapabilityInvolvementEClass, FUNCTIONAL_CHAIN_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTIONAL_CHAIN);

    abstractFunctionAbstractCapabilityInvolvementEClass = createEClass(ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT);
    createEReference(abstractFunctionAbstractCapabilityInvolvementEClass, ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__CAPABILITY);
    createEReference(abstractFunctionAbstractCapabilityInvolvementEClass, ABSTRACT_FUNCTION_ABSTRACT_CAPABILITY_INVOLVEMENT__FUNCTION);

    scenarioRealizationEClass = createEClass(SCENARIO_REALIZATION);
    createEReference(scenarioRealizationEClass, SCENARIO_REALIZATION__REALIZED_SCENARIO);
    createEReference(scenarioRealizationEClass, SCENARIO_REALIZATION__REALIZING_SCENARIO);

    stateFragmentEClass = createEClass(STATE_FRAGMENT);
    createEReference(stateFragmentEClass, STATE_FRAGMENT__RELATED_ABSTRACT_STATE);
    createEReference(stateFragmentEClass, STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION);

    armTimerEventEClass = createEClass(ARM_TIMER_EVENT);

    cancelTimerEventEClass = createEClass(CANCEL_TIMER_EVENT);

    constraintDurationEClass = createEClass(CONSTRAINT_DURATION);
    createEAttribute(constraintDurationEClass, CONSTRAINT_DURATION__DURATION);
    createEReference(constraintDurationEClass, CONSTRAINT_DURATION__START);
    createEReference(constraintDurationEClass, CONSTRAINT_DURATION__FINISH);

    sequenceMessageValuationEClass = createEClass(SEQUENCE_MESSAGE_VALUATION);
    createEReference(sequenceMessageValuationEClass, SEQUENCE_MESSAGE_VALUATION__EXCHANGE_ITEM_ELEMENT);
    createEReference(sequenceMessageValuationEClass, SEQUENCE_MESSAGE_VALUATION__VALUE);

    // Create enums
    messageKindEEnum = createEEnum(MESSAGE_KIND);
    scenarioKindEEnum = createEEnum(SCENARIO_KIND);
    interactionOperatorKindEEnum = createEEnum(INTERACTION_OPERATOR_KIND);
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
    CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    CsPackage theCsPackage = (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    FaPackage theFaPackage = (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    sequenceMessageEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    scenarioEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());
    scenarioEClass.getESuperTypes().add(theBehaviorPackage.getAbstractBehavior());
    messageEndEClass.getESuperTypes().add(this.getAbstractEnd());
    executionEClass.getESuperTypes().add(this.getTimeLapse());
    executionEndEClass.getESuperTypes().add(this.getAbstractEnd());
    creationEventEClass.getESuperTypes().add(this.getEvent());
    destructionEventEClass.getESuperTypes().add(this.getEvent());
    executionEventEClass.getESuperTypes().add(this.getEvent());
    instanceRoleEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    abstractEndEClass.getESuperTypes().add(this.getInteractionFragment());
    eventEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    eventEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    eventReceiptOperationEClass.getESuperTypes().add(this.getEvent());
    eventSentOperationEClass.getESuperTypes().add(this.getEvent());
    mergeLinkEClass.getESuperTypes().add(theCapellacorePackage.getTrace());
    refinementLinkEClass.getESuperTypes().add(theCapellacorePackage.getTrace());
    abstractCapabilityRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    abstractCapabilityEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    abstractCapabilityEClass.getESuperTypes().add(theCapellacorePackage.getInvolverElement());
    abstractCapabilityEClass.getESuperTypes().add(theFaPackage.getAbstractFunctionalChainContainer());
    abstractCapabilityExtendEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    abstractCapabilityExtensionPointEClass.getESuperTypes().add(theCapellacorePackage.getNamedRelationship());
    abstractCapabilityGeneralizationEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    abstractCapabilityIncludeEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    interactionFragmentEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    interactionStateEClass.getESuperTypes().add(this.getInteractionFragment());
    interactionUseEClass.getESuperTypes().add(this.getAbstractFragment());
    combinedFragmentEClass.getESuperTypes().add(this.getAbstractFragment());
    gateEClass.getESuperTypes().add(this.getMessageEnd());
    interactionOperandEClass.getESuperTypes().add(this.getInteractionFragment());
    timeLapseEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    abstractFragmentEClass.getESuperTypes().add(this.getTimeLapse());
    fragmentEndEClass.getESuperTypes().add(this.getInteractionFragment());
    functionalChainAbstractCapabilityInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    abstractFunctionAbstractCapabilityInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    scenarioRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    stateFragmentEClass.getESuperTypes().add(this.getTimeLapse());
    armTimerEventEClass.getESuperTypes().add(this.getEvent());
    cancelTimerEventEClass.getESuperTypes().add(this.getEvent());
    constraintDurationEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    sequenceMessageValuationEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());

    // Initialize classes and features; add operations and parameters
    initEClass(sequenceMessageEClass, SequenceMessage.class, "SequenceMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getSequenceMessage_Kind(), this.getMessageKind(), "kind", null, 0, 1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_ExchangeContext(), theCapellacorePackage.getConstraint(), null, "exchangeContext", null, 0, 1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_SendingEnd(), this.getMessageEnd(), null, "sendingEnd", null, 0, 1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_ReceivingEnd(), this.getMessageEnd(), null, "receivingEnd", null, 0, 1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_InvokedOperation(), theInformationPackage.getAbstractEventOperation(), null, "invokedOperation", null, 0, 1, SequenceMessage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_ExchangedItems(), theInformationPackage.getExchangeItem(), null, "exchangedItems", null, 0, -1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_SendingPart(), theCsPackage.getPart(), null, "sendingPart", null, 0, 1, SequenceMessage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_ReceivingPart(), theCsPackage.getPart(), null, "receivingPart", null, 0, 1, SequenceMessage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_SendingFunction(), theFaPackage.getAbstractFunction(), null, "sendingFunction", null, 0, 1, SequenceMessage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_ReceivingFunction(), theFaPackage.getAbstractFunction(), null, "receivingFunction", null, 0, 1, SequenceMessage.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessage_OwnedSequenceMessageValuations(), this.getSequenceMessageValuation(), null, "ownedSequenceMessageValuations", null, 0, -1, SequenceMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(scenarioEClass, Scenario.class, "Scenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getScenario_Kind(), this.getScenarioKind(), "kind", "UNSET", 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getScenario_Merged(), ecorePackage.getEBoolean(), "merged", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_PreCondition(), theCapellacorePackage.getConstraint(), null, "preCondition", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_PostCondition(), theCapellacorePackage.getConstraint(), null, "postCondition", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedInstanceRoles(), this.getInstanceRole(), null, "ownedInstanceRoles", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedMessages(), this.getSequenceMessage(), null, "ownedMessages", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedInteractionFragments(), this.getInteractionFragment(), null, "ownedInteractionFragments", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedTimeLapses(), this.getTimeLapse(), null, "ownedTimeLapses", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedEvents(), this.getEvent(), null, "ownedEvents", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedFormalGates(), this.getGate(), null, "ownedFormalGates", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedScenarioRealization(), this.getScenarioRealization(), null, "ownedScenarioRealization", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_OwnedConstraintDurations(), this.getConstraintDuration(), null, "ownedConstraintDurations", null, 0, -1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_ContainedFunctions(), theFaPackage.getAbstractFunction(), null, "containedFunctions", null, 0, -1, Scenario.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_ContainedParts(), theCsPackage.getPart(), null, "containedParts", null, 0, -1, Scenario.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_ReferencedScenarios(), this.getScenario(), null, "referencedScenarios", null, 0, -1, Scenario.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_RealizedScenarios(), this.getScenario(), this.getScenario_RealizingScenarios(), "realizedScenarios", null, 0, -1, Scenario.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenario_RealizingScenarios(), this.getScenario(), this.getScenario_RealizedScenarios(), "realizingScenarios", null, 0, -1, Scenario.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(messageEndEClass, MessageEnd.class, "MessageEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMessageEnd_Message(), this.getSequenceMessage(), null, "message", null, 1, 1, MessageEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(executionEClass, Execution.class, "Execution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExecution_Covered(), this.getInstanceRole(), null, "covered", null, 1, 1, Execution.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(executionEndEClass, ExecutionEnd.class, "ExecutionEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExecutionEnd_Execution(), this.getExecution(), null, "execution", null, 1, 1, ExecutionEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(creationEventEClass, CreationEvent.class, "CreationEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(destructionEventEClass, DestructionEvent.class, "DestructionEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(executionEventEClass, ExecutionEvent.class, "ExecutionEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(instanceRoleEClass, InstanceRole.class, "InstanceRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInstanceRole_AbstractEnds(), this.getAbstractEnd(), this.getAbstractEnd_Covered(), "abstractEnds", null, 0, -1, InstanceRole.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInstanceRole_RepresentedInstance(), theInformationPackage.getAbstractInstance(), null, "representedInstance", null, 1, 1, InstanceRole.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractEndEClass, AbstractEnd.class, "AbstractEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractEnd_Event(), this.getEvent(), null, "event", null, 1, 1, AbstractEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractEnd_Covered(), this.getInstanceRole(), this.getInstanceRole_AbstractEnds(), "covered", null, 1, 1, AbstractEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(eventEClass, Event.class, "Event", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(eventReceiptOperationEClass, EventReceiptOperation.class, "EventReceiptOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEventReceiptOperation_Operation(), theInformationPackage.getAbstractEventOperation(), null, "operation", null, 0, 1, EventReceiptOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(eventSentOperationEClass, EventSentOperation.class, "EventSentOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEventSentOperation_Operation(), theInformationPackage.getAbstractEventOperation(), null, "operation", null, 0, 1, EventSentOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(mergeLinkEClass, MergeLink.class, "MergeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(refinementLinkEClass, RefinementLink.class, "RefinementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(abstractCapabilityRealizationEClass, AbstractCapabilityRealization.class, "AbstractCapabilityRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapabilityRealization_RealizedCapability(), this.getAbstractCapability(), this.getAbstractCapability_IncomingCapabilityAllocation(), "realizedCapability", null, 1, 1, AbstractCapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityRealization_RealizingCapability(), this.getAbstractCapability(), this.getAbstractCapability_OutgoingCapabilityAllocation(), "realizingCapability", null, 1, 1, AbstractCapabilityRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCapabilityEClass, AbstractCapability.class, "AbstractCapability", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapability_PreCondition(), theCapellacorePackage.getConstraint(), null, "preCondition", null, 0, 1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_PostCondition(), theCapellacorePackage.getConstraint(), null, "postCondition", null, 0, 1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_OwnedScenarios(), this.getScenario(), null, "ownedScenarios", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_IncomingCapabilityAllocation(), this.getAbstractCapabilityRealization(), this.getAbstractCapabilityRealization_RealizedCapability(), "incomingCapabilityAllocation", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_OutgoingCapabilityAllocation(), this.getAbstractCapabilityRealization(), this.getAbstractCapabilityRealization_RealizingCapability(), "outgoingCapabilityAllocation", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Extends(), this.getAbstractCapabilityExtend(), null, "extends", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Extending(), this.getAbstractCapabilityExtend(), null, "extending", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_AbstractCapabilityExtensionPoints(), this.getAbstractCapabilityExtensionPoint(), null, "abstractCapabilityExtensionPoints", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_SuperGeneralizations(), this.getAbstractCapabilityGeneralization(), null, "superGeneralizations", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_SubGeneralizations(), this.getAbstractCapabilityGeneralization(), null, "subGeneralizations", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Includes(), this.getAbstractCapabilityInclude(), null, "includes", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Including(), this.getAbstractCapabilityInclude(), null, "including", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Super(), this.getAbstractCapability(), null, "super", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_Sub(), this.getAbstractCapability(), null, "sub", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_IncludedAbstractCapabilities(), this.getAbstractCapability(), null, "includedAbstractCapabilities", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_IncludingAbstractCapabilities(), this.getAbstractCapability(), null, "includingAbstractCapabilities", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_ExtendedAbstractCapabilities(), this.getAbstractCapability(), null, "extendedAbstractCapabilities", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_ExtendingAbstractCapabilities(), this.getAbstractCapability(), null, "extendingAbstractCapabilities", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements(), this.getFunctionalChainAbstractCapabilityInvolvement(), null, "ownedFunctionalChainAbstractCapabilityInvolvements", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements(), this.getAbstractFunctionAbstractCapabilityInvolvement(), null, "ownedAbstractFunctionAbstractCapabilityInvolvements", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_AvailableInStates(), theCapellacommonPackage.getState(), null, "availableInStates", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_OwnedAbstractCapabilityRealizations(), this.getAbstractCapabilityRealization(), null, "ownedAbstractCapabilityRealizations", null, 0, -1, AbstractCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_InvolvedAbstractFunctions(), theFaPackage.getAbstractFunction(), null, "involvedAbstractFunctions", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapability_InvolvedFunctionalChains(), theFaPackage.getFunctionalChain(), null, "involvedFunctionalChains", null, 0, -1, AbstractCapability.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCapabilityExtendEClass, AbstractCapabilityExtend.class, "AbstractCapabilityExtend", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapabilityExtend_Extended(), this.getAbstractCapability(), null, "extended", null, 1, 1, AbstractCapabilityExtend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityExtend_Extension(), this.getAbstractCapability(), null, "extension", null, 1, 1, AbstractCapabilityExtend.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityExtend_ExtensionLocation(), this.getAbstractCapabilityExtensionPoint(), this.getAbstractCapabilityExtensionPoint_ExtendLinks(), "extensionLocation", null, 0, 1, AbstractCapabilityExtend.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCapabilityExtensionPointEClass, AbstractCapabilityExtensionPoint.class, "AbstractCapabilityExtensionPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapabilityExtensionPoint_AbstractCapability(), this.getAbstractCapability(), null, "abstractCapability", null, 1, 1, AbstractCapabilityExtensionPoint.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityExtensionPoint_ExtendLinks(), this.getAbstractCapabilityExtend(), this.getAbstractCapabilityExtend_ExtensionLocation(), "extendLinks", null, 0, -1, AbstractCapabilityExtensionPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCapabilityGeneralizationEClass, AbstractCapabilityGeneralization.class, "AbstractCapabilityGeneralization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapabilityGeneralization_Super(), this.getAbstractCapability(), null, "super", null, 1, 1, AbstractCapabilityGeneralization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityGeneralization_Sub(), this.getAbstractCapability(), null, "sub", null, 1, 1, AbstractCapabilityGeneralization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCapabilityIncludeEClass, AbstractCapabilityInclude.class, "AbstractCapabilityInclude", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractCapabilityInclude_Included(), this.getAbstractCapability(), null, "included", null, 1, 1, AbstractCapabilityInclude.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractCapabilityInclude_Inclusion(), this.getAbstractCapability(), null, "inclusion", null, 1, 1, AbstractCapabilityInclude.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interactionFragmentEClass, InteractionFragment.class, "InteractionFragment", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInteractionFragment_CoveredInstanceRoles(), this.getInstanceRole(), null, "coveredInstanceRoles", null, 1, -1, InteractionFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interactionStateEClass, InteractionState.class, "InteractionState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInteractionState_RelatedAbstractState(), theCapellacommonPackage.getAbstractState(), null, "relatedAbstractState", null, 0, 1, InteractionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInteractionState_RelatedAbstractFunction(), theFaPackage.getAbstractFunction(), null, "relatedAbstractFunction", null, 0, 1, InteractionState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInteractionState_Covered(), this.getInstanceRole(), null, "covered", null, 1, 1, InteractionState.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(interactionUseEClass, InteractionUse.class, "InteractionUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInteractionUse_ReferencedScenario(), this.getScenario(), null, "referencedScenario", null, 0, 1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInteractionUse_ActualGates(), this.getGate(), null, "actualGates", null, 0, -1, InteractionUse.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(combinedFragmentEClass, CombinedFragment.class, "CombinedFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getCombinedFragment_Operator(), this.getInteractionOperatorKind(), "operator", "UNSET", 0, 1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getCombinedFragment_ReferencedOperands(), this.getInteractionOperand(), null, "referencedOperands", null, 0, -1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCombinedFragment_ExpressionGates(), this.getGate(), null, "expressionGates", null, 0, -1, CombinedFragment.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(gateEClass, Gate.class, "Gate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(interactionOperandEClass, InteractionOperand.class, "InteractionOperand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getInteractionOperand_ReferencedInteractionFragments(), this.getInteractionFragment(), null, "referencedInteractionFragments", null, 0, -1, InteractionOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getInteractionOperand_Guard(), theCapellacorePackage.getConstraint(), null, "guard", null, 0, 1, InteractionOperand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(timeLapseEClass, TimeLapse.class, "TimeLapse", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getTimeLapse_Start(), this.getInteractionFragment(), null, "start", null, 1, 1, TimeLapse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getTimeLapse_Finish(), this.getInteractionFragment(), null, "finish", null, 1, 1, TimeLapse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFragmentEClass, AbstractFragment.class, "AbstractFragment", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractFragment_OwnedGates(), this.getGate(), null, "ownedGates", null, 0, -1, AbstractFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(fragmentEndEClass, FragmentEnd.class, "FragmentEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFragmentEnd_AbstractFragment(), this.getAbstractFragment(), null, "abstractFragment", null, 1, 1, FragmentEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainAbstractCapabilityInvolvementEClass, FunctionalChainAbstractCapabilityInvolvement.class, "FunctionalChainAbstractCapabilityInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalChainAbstractCapabilityInvolvement_Capability(), this.getAbstractCapability(), null, "capability", null, 1, 1, FunctionalChainAbstractCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain(), theFaPackage.getFunctionalChain(), null, "functionalChain", null, 1, 1, FunctionalChainAbstractCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFunctionAbstractCapabilityInvolvementEClass, AbstractFunctionAbstractCapabilityInvolvement.class, "AbstractFunctionAbstractCapabilityInvolvement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractFunctionAbstractCapabilityInvolvement_Capability(), this.getAbstractCapability(), null, "capability", null, 1, 1, AbstractFunctionAbstractCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionAbstractCapabilityInvolvement_Function(), theFaPackage.getAbstractFunction(), null, "function", null, 1, 1, AbstractFunctionAbstractCapabilityInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(scenarioRealizationEClass, ScenarioRealization.class, "ScenarioRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getScenarioRealization_RealizedScenario(), this.getScenario(), null, "realizedScenario", null, 0, 1, ScenarioRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getScenarioRealization_RealizingScenario(), this.getScenario(), null, "realizingScenario", null, 0, 1, ScenarioRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(stateFragmentEClass, StateFragment.class, "StateFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getStateFragment_RelatedAbstractState(), theCapellacommonPackage.getAbstractState(), null, "relatedAbstractState", null, 0, 1, StateFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getStateFragment_RelatedAbstractFunction(), theFaPackage.getAbstractFunction(), null, "relatedAbstractFunction", null, 0, 1, StateFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(armTimerEventEClass, ArmTimerEvent.class, "ArmTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(cancelTimerEventEClass, CancelTimerEvent.class, "CancelTimerEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(constraintDurationEClass, ConstraintDuration.class, "ConstraintDuration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getConstraintDuration_Duration(), ecorePackage.getEString(), "duration", null, 0, 1, ConstraintDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConstraintDuration_Start(), this.getInteractionFragment(), null, "start", null, 0, 1, ConstraintDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getConstraintDuration_Finish(), this.getInteractionFragment(), null, "finish", null, 0, 1, ConstraintDuration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(sequenceMessageValuationEClass, SequenceMessageValuation.class, "SequenceMessageValuation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSequenceMessageValuation_ExchangeItemElement(), theInformationPackage.getExchangeItemElement(), null, "exchangeItemElement", null, 0, 1, SequenceMessageValuation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceMessageValuation_Value(), theModellingcorePackage.getValueSpecification(), null, "value", null, 0, 1, SequenceMessageValuation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(messageKindEEnum, MessageKind.class, "MessageKind"); //$NON-NLS-1$
    addEEnumLiteral(messageKindEEnum, MessageKind.UNSET);
    addEEnumLiteral(messageKindEEnum, MessageKind.ASYNCHRONOUS_CALL);
    addEEnumLiteral(messageKindEEnum, MessageKind.SYNCHRONOUS_CALL);
    addEEnumLiteral(messageKindEEnum, MessageKind.REPLY);
    addEEnumLiteral(messageKindEEnum, MessageKind.DELETE);
    addEEnumLiteral(messageKindEEnum, MessageKind.CREATE);
    addEEnumLiteral(messageKindEEnum, MessageKind.TIMER);

    initEEnum(scenarioKindEEnum, ScenarioKind.class, "ScenarioKind"); //$NON-NLS-1$
    addEEnumLiteral(scenarioKindEEnum, ScenarioKind.UNSET);
    addEEnumLiteral(scenarioKindEEnum, ScenarioKind.INTERFACE);
    addEEnumLiteral(scenarioKindEEnum, ScenarioKind.DATA_FLOW);
    addEEnumLiteral(scenarioKindEEnum, ScenarioKind.INTERACTION);
    addEEnumLiteral(scenarioKindEEnum, ScenarioKind.FUNCTIONAL);

    initEEnum(interactionOperatorKindEEnum, InteractionOperatorKind.class, "InteractionOperatorKind"); //$NON-NLS-1$
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.UNSET);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.ALT);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.OPT);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.PAR);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.LOOP);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.CRITICAL);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.NEG);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.ASSERT);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.STRICT);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.SEQ);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.IGNORE);
    addEEnumLiteral(interactionOperatorKindEEnum, InteractionOperatorKind.CONSIDER);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/capella/2007/BusinessInformation
    createBusinessInformationAnnotations();
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
    createMappingAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore
    createIgnoreAnnotations();
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
         "description", "Interaction aims at defining the components interaction language (close from the UML Sequence diagram, partially).\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model FunctionalAnalysis.ecore\r\nThis package depends on the model Behavior.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (sequenceMessageEClass,
       source,
       new String[] {
         "description", "A Message defines a particular communication between Lifelines of an Interaction.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_sequence_scenario.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed Message to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_Kind(),
       source,
       new String[] {
         "description", "The sort of communication reflected by the Message.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see MessageKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingEnd(),
       source,
       new String[] {
         "description", "This is equivalent to UML Message::sendEvent :\r\nReferences the Sending of the Message.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingEnd(),
       source,
       new String[] {
         "description", "This is equivalent to UML Message::sendEvent :\r\nReferences the Receiving of the Message.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_InvokedOperation(),
       source,
       new String[] {
         "description", "the AbstractEventOperation triggered by this sequence message", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ExchangedItems(),
       source,
       new String[] {
         "description", "the ExchangeItems carried by this sequence message", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioEClass,
       source,
       new String[] {
         "description", "Definition of a dynamic behaviour composed of the following information :\r\nContext, objective, pre-conditions, post-conditions, used capabilities, involved roles & actors, operational exchanges & interactions, processes and activities. Ability to be validated. Temporal & performance description.Criticity.\r\nScenarios can be gathered in a set of Use Cases.\r\n\r\nA scenario describes a temporal dynamic interaction between actors (included the system or possibly its components) through their exchanges, it also describes the initialisation and the evolution of the context of the interaction.\r\n[source:ARCADIA encyclopedia v0.8.0]\r\n\r\nA scenario is similar to UML Interaction concept :\r\nAn interaction is a unit of behavior that focuses on the observable exchange of information between\r\nConnectableElements.\r\n\r\nA scenario can be compared to an UML sequence diagram :\r\nA sequence diagram describes an Interaction by focusing on the sequence of Messages that are exchanged, along with\r\ntheir corresponding OccurrenceSpecifications on the Lifelines.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_Kind(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_Merged(),
       source,
       new String[] {
         "description", "Whether the scenario underwent a merge operation for the transition from one level to the next\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_PreCondition(),
       source,
       new String[] {
         "description", "the prerequisite conditions for the use of this Scenario", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_PostCondition(),
       source,
       new String[] {
         "description", "the conditions applying after this Scenario has been exercized", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
         "description", "Link to the set of instance roles (lifelines)\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
         "description", "Link to the owned sequence messages\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInteractionFragments(),
       source,
       new String[] {
         "description", "Link to the owned message and operation ends\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
         "description", "Link to the set of owned executions\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedEvents(),
       source,
       new String[] {
         "description", "the Events associated to this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedFormalGates(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedScenarioRealization(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedConstraintDurations(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedFunctions(),
       source,
       new String[] {
         "description", "the SequenceMessage list, in sequence order", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedParts(),
       source,
       new String[] {
         "description", "the SequenceMessage list, in sequence order", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ReferencedScenarios(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizedScenarios(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizingScenarios(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEndEClass,
       source,
       new String[] {
         "description", "Specifies the occurrence of events, such as sending and receiving of signals or invoking or receiving of operation calls. A\r\nmessage occurrence specification is a kind of message end. Messages are generated either by synchronous operation calls\r\nor asynchronous signal sends. They are received by the execution of corresponding accept event actions.\r\n\r\nThis concept can be compared to UML MessageOccurrenceSpecification.\r\n[source:UML Superstructure v2.2] ", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed MessageOccurrenceSpecification to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
         "description", "the Message to which this MessageEnd is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEClass,
       source,
       new String[] {
         "description", "An Execution Specification is a specification of the execution of a unit of behavior or action within the Lifeline. The\r\nduration of an ExecutionSpecification is represented by two ExecutionOccurrenceSpecifications, the start\r\nExecutionOccurrenceSpecification and the finish ExecutionOccurrenceSpecification.\r\n\r\nExecution can be compared to UML Execution Specification.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed ExecutionSpecification to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
         "description", "the instance role that performs this Execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEndEClass,
       source,
       new String[] {
         "description", "This concept can be compared to UML ExecutionOccurrenceSpecification : \r\nAn ExecutionOccurrenceSpecification represents moments in time at which actions or behaviors start or finish.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed ExecutionOccurrenceSpecification to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
         "description", "the Execution to which this ExecutionEnd is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (creationEventEClass,
       source,
       new String[] {
         "description", "A CreationEvent models the creation of an object.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (destructionEventEClass,
       source,
       new String[] {
         "description", "A DestructionEvent models the destruction of an object.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEventEClass,
       source,
       new String[] {
         "description", "An ExecutionEvent models the start or finish of an execution occurrence.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (instanceRoleEClass,
       source,
       new String[] {
         "description", "Instance role can be compared to UML Lifeline : A lifeline represents an individual participant in the Interaction.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_instancerole.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "May be renamed Lifeline", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
         "description", "the start/end points of interactions that are attached to this lifeline\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
         "description", "the instance that this lifeline represents the activity of\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "description", "This concept can be compared to UML OccurrenceSpecification : The semantics of an OccurrenceSpecification is just the trace of that single OccurrenceSpecification.\r\nThe understanding and deeper meaning of the OccurrenceSpecification is dependent upon the associated Message and the\r\ninformation that it conveys.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed OccurrenceSpecification to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "description", "the scenario that this interaction endpoint is related to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Event(),
       source,
       new String[] {
         "description", "the Event associated to this interaction endpoint\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
         "description", "the instance role (lifeline) to which this interaction endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum,
       source,
       new String[] {
         "description", "This concept is similar to UML MessageSort :\r\nThis is an enumerated type that identifies the type of message.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed MessageSort to map UML concept" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "The message kind is not specified\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "This enumeration literal is equivalent to UML MessageSort::asynchCall :\r\nThe message was generated by an asynchronous call to an operation.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "This enumeration literal is equivalent to UML MessageSort::synchCall :\r\nThe message was generated by a synchronous call to an operation.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "This enumeration literal is equivalent to UML MessageSort::reply :\r\nThe message is a reply message to an operation call.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "This enumeration literal is equivalent to UML MessageSort::deleteMessage :\r\nThe message designating the termination of another lifeline.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "The message designating the creation of an instance role\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventEClass,
       source,
       new String[] {
         "description", "Event is similar to UML MessageEvent : A message event specifies the receipt by an object of either a call or a signal. MessageEvent is an abstract metaclass.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventReceiptOperationEClass,
       source,
       new String[] {
         "description", "This concept is similar to UML ReceiveOperationEvent : This specifies the event of receiving an operation invocation for a particular operation by the target entity.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed ReceiveOperationEvent to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventReceiptOperation_Operation(),
       source,
       new String[] {
         "description", "the Operation triggered by the reception of this event\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventSentOperationEClass,
       source,
       new String[] {
         "description", "This concept is similar to UML SendOperationEvent : A SendOperationEvent models the invocation of an operation call.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed SendOperationEvent to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventSentOperation_Operation(),
       source,
       new String[] {
         "description", "the Operation triggering associated to the sending of this Event\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (mergeLinkEClass,
       source,
       new String[] {
         "description", "a specific kind of trace, indicating an operation of merge between two entities, for example two scenarios, merged into one in the refinement process towards the lower abstraction level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (refinementLinkEClass,
       source,
       new String[] {
         "description", "a kind of trace between a model element at a given design level, and a model element at a low design level, refining the source element.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "refinement links are automatically created/maintained by the tool when performing refinement operations from one abstraction level to the next", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityRealizationEClass,
       source,
       new String[] {
         "description", "An abstract capability realization describes an realization between an realizing capability and an realized capability\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizedCapability(),
       source,
       new String[] {
         "description", "the Capability being realized from the other Capability", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizingCapability(),
       source,
       new String[] {
         "description", "the Capability starting the realization relationships towards the other capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityEClass,
       source,
       new String[] {
         "description", "Base class for Capabilities (Capability and Capability Realization)\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_PreCondition(),
       source,
       new String[] {
         "description", "the prerequisite conditions for the use of this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_PostCondition(),
       source,
       new String[] {
         "description", "the conditions applying after this Capability has been exercized\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
         "description", "the Scenarios describing the dynamic aspects of this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncomingCapabilityAllocation(),
       source,
       new String[] {
         "description", "(automatically computed) the allocations links which destination is this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OutgoingCapabilityAllocation(),
       source,
       new String[] {
         "description", "(automatically computed) the allocation links having this Capability as their start point\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extends(),
       source,
       new String[] {
         "description", "the list of reference elements to the Capabilities that this Capability extends\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
         "description", "the list of reference elements to Capabilities that extend this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AbstractCapabilityExtensionPoints(),
       source,
       new String[] {
         "description", "the extension points that this Capability provides\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SuperGeneralizations(),
       source,
       new String[] {
         "description", "the list of references to Capabilities from which this Capability inherits\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
         "description", "the list of references to Capabilities that derive from this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Includes(),
       source,
       new String[] {
         "description", "the list of references to Capabilities used/included by this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
         "description", "the list of references to Capabilities that use/include this Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to Capabilities from which this Capability inherit", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to Capabilities that inherit from this Capability", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to the Capabilities that this Capability uses/includes", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to the Capabilities that this Capability uses/includes", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to the Capabilities that this Capability extends", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
         "description", "(automatically computed) the direct references to the Capabilities that this Capability extends", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AvailableInStates(),
       source,
       new String[] {
         "description", "the list of (system) states in which this abstract capability is actually available\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedAbstractFunctions(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedFunctionalChains(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtendEClass,
       source,
       new String[] {
         "description", "A relationship from an extending use case to an extended use case that specifies how and when the behavior defined in\r\nthe extending use case can be inserted into the behavior defined in the extended use case.\r\n\r\nThis concept is similar to UML Extend concept.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed Extend to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extended(),
       source,
       new String[] {
         "description", "the Capability being extended\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
         "description", "the Capability that realizes the extension\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_ExtensionLocation(),
       source,
       new String[] {
         "description", "the extension point to which the extending Capability is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "this extension location must be one of the extensions of the Capability pointed by the  \"extended\" reference\r\n", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtensionPointEClass,
       source,
       new String[] {
         "description", "An extension point identifies a point in the behavior of a use case where that behavior can be extended by the behavior of\r\nsome other (extending) use case, as specified by an extend relationship.\r\n\r\nThis concept is similar to UML ExtensionPoint.\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
         "description", "the Capability to which this extension point belongs\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_ExtendLinks(),
       source,
       new String[] {
         "description", "the extension links starting from this extension point\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityGeneralizationEClass,
       source,
       new String[] {
         "description", "A specific kind of generalization link between Capabilities.\r\n[source: Capella study]\r\n\r\nThe generalization is useful for Capability reuse (override or extension of Capability).", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Super(),
       source,
       new String[] {
         "description", "the parent Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
         "description", "the child Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityIncludeEClass,
       source,
       new String[] {
         "description", "The Include is a relationship between two use cases, implying that the behavior of the included use case is inserted into the behavior of the including use case. It is also a kind of NamedElement so that it can have a name in the context of its owning use case. \r\nThe including use case may only depend on the result (value) of the included use case. This value is obtained as a result of the execution of the included use case.\r\n\r\nThis concept is similar to UML Include concept.\r\n[source:UML Superstructure v2.2]\r\n\r\nNote that the included use case is not optional, and is always required for the including use case to execute correctly.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "Should be renamed Include to map UML concept", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Included(),
       source,
       new String[] {
         "description", "the Capability being included\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
         "description", "the Capability performing the inclusion of the other Capability\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum,
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionFragmentEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionFragment_CoveredInstanceRoles(),
       source,
       new String[] {
         "description", "the instance role that performs this Execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionStateEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_RelatedAbstractState(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_RelatedAbstractFunction(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
         "description", "the instance role (lifeline) to which this interaction endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionUseEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionUse_ReferencedScenario(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionUse_ActualGates(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (combinedFragmentEClass,
       source,
       new String[] {
         "description", "A Combined Fragment.\r\n\r\nThe concept is closed to the UML Combined Fragment.\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_Operator(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_ReferencedOperands(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_ExpressionGates(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (gateEClass,
       source,
       new String[] {
         "description", "A gate is a way to model the passing of information between a sequence diagram and its context.\r\nIt is a message end.\r\n\r\nThis concept is closed to the UML Gate.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperandEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionOperand_ReferencedInteractionFragments(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionOperand_Guard(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(11),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (timeLapseEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Start(),
       source,
       new String[] {
         "description", "the starting point of this Execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Finish(),
       source,
       new String[] {
         "description", "the ending point of this Execution\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFragmentEClass,
       source,
       new String[] {
         "description", "Base class for Fragments in Scenarios.\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFragment_OwnedGates(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (fragmentEndEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFragmentEnd_AbstractFragment(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainAbstractCapabilityInvolvementEClass,
       source,
       new String[] {
         "description", "A functional chain can be involved in capability\r\n[source: MBSD unified approach]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAbstractCapabilityInvolvementEClass,
       source,
       new String[] {
         "description", "A function can be involved in a capability.\r\n[source: MBSD unified approach]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioRealizationEClass,
       source,
       new String[] {
         "description", "an allocation link between a scenario, and the scenario that it realizes", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this link is typically generated by the Capella tool during automated transitions between design levels", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizedScenario(),
       source,
       new String[] {
         "description", "the scenario that is being realized by/from the other scenario", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizingScenario(),
       source,
       new String[] {
         "description", "the scenario that realizes (to) the other scenario", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stateFragmentEClass,
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStateFragment_RelatedAbstractState(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStateFragment_RelatedAbstractFunction(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (armTimerEventEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (cancelTimerEventEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (constraintDurationEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConstraintDuration_Duration(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConstraintDuration_Start(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getConstraintDuration_Finish(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
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
      (sequenceMessageEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_ExchangeContext(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_InvokedOperation(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_ExchangedItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_SendingPart(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_ReceivingPart(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_SendingFunction(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_ReceivingFunction(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_OwnedSequenceMessageValuations(),
       source,
       new String[] {
       });
    addAnnotation
      (scenarioEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_Merged(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_PreCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_PostCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedConstraintDurations(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_ContainedFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_ContainedParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_ReferencedScenarios(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_RealizedScenarios(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_RealizingScenarios(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (instanceRoleEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_PreCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_PostCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AvailableInStates(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_InvolvedAbstractFunctions(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_InvolvedFunctionalChains(),
       source,
       new String[] {
       });
    addAnnotation
      (getCombinedFragment_Operator(),
       source,
       new String[] {
       });
    addAnnotation
      (getInteractionOperand_Guard(),
       source,
       new String[] {
       });
    addAnnotation
      (stateFragmentEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getStateFragment_RelatedAbstractState(),
       source,
       new String[] {
       });
    addAnnotation
      (getStateFragment_RelatedAbstractFunction(),
       source,
       new String[] {
       });
    addAnnotation
      (constraintDurationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getConstraintDuration_Duration(),
       source,
       new String[] {
       });
    addAnnotation
      (sequenceMessageValuationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessageValuation_ExchangeItemElement(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessageValuation_Value(),
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
      (sequenceMessageEClass,
       source,
       new String[] {
         "Label", "SequenceMessage" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingEnd(),
       source,
       new String[] {
         "Label", "sendingEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingEnd(),
       source,
       new String[] {
         "Label", "receivingEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioEClass,
       source,
       new String[] {
         "Label", "Scenario" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
         "Label", "instanceRoles" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
         "Label", "messages" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInteractionFragments(),
       source,
       new String[] {
         "Label", "ownedAbstractEnds" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
         "Label", "ownedExecutions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedEvents(),
       source,
       new String[] {
         "Label", "ownedEvents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEndEClass,
       source,
       new String[] {
         "Label", "MessageEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
         "Label", "message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEClass,
       source,
       new String[] {
         "Label", "Execution" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
         "Label", "covers" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEndEClass,
       source,
       new String[] {
         "Label", "ExecutionEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
         "Label", "execution" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (creationEventEClass,
       source,
       new String[] {
         "Label", "CreationEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (destructionEventEClass,
       source,
       new String[] {
         "Label", "DestructionEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEventEClass,
       source,
       new String[] {
         "Label", "ExecutionEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (instanceRoleEClass,
       source,
       new String[] {
         "Label", "InstanceRole" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
         "Label", "abstractEnds" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
         "Label", "representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "Label", "AbstractEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Event(),
       source,
       new String[] {
         "Label", "event" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
         "Label", "instanceRole" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum,
       source,
       new String[] {
         "Label", "MessageKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventEClass,
       source,
       new String[] {
         "Label", "Event" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventReceiptOperationEClass,
       source,
       new String[] {
         "Label", "EventReceiptOperation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventReceiptOperation_Operation(),
       source,
       new String[] {
         "Label", "operation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventSentOperationEClass,
       source,
       new String[] {
         "Label", "EventSentOperation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventSentOperation_Operation(),
       source,
       new String[] {
         "Label", "operation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (mergeLinkEClass,
       source,
       new String[] {
         "Label", "MergeLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (refinementLinkEClass,
       source,
       new String[] {
         "Label", "RefinementLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityEClass,
       source,
       new String[] {
         "Label", "AbstractCapability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
         "Label", "scenarios" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extends(),
       source,
       new String[] {
         "Label", "extends" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
         "Label", "extending" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AbstractCapabilityExtensionPoints(),
       source,
       new String[] {
         "Label", "abstractCapabilityExtensionPoints" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SuperGeneralizations(),
       source,
       new String[] {
         "Label", "generalizations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
         "Label", "generalizations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Includes(),
       source,
       new String[] {
         "Label", "includes" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
         "Label", "including" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
         "Label", "superAbstractCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
         "Label", "superAbstractCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
         "Label", "includedAbstractCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
         "Label", "includedAbstractCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
         "Label", "extendedCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
         "Label", "extendedCapabilityUseCases" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtendEClass,
       source,
       new String[] {
         "Label", "AbstractCapabilityExtend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extended(),
       source,
       new String[] {
         "Label", "extended" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
         "Label", "extension" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_ExtensionLocation(),
       source,
       new String[] {
         "Label", "extensionLocation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtensionPointEClass,
       source,
       new String[] {
         "Label", "AbstractCapabilityExtensionPoint" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
         "Label", "abstractCapability" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_ExtendLinks(),
       source,
       new String[] {
         "Label", "extendLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityGeneralizationEClass,
       source,
       new String[] {
         "Label", "AbstractCapabilityGeneralization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Super(),
       source,
       new String[] {
         "Label", "super" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
         "Label", "super" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityIncludeEClass,
       source,
       new String[] {
         "Label", "AbstractCapabilityInclude" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Included(),
       source,
       new String[] {
         "Label", "included" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
         "Label", "inclusion" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionFragment_CoveredInstanceRoles(),
       source,
       new String[] {
         "Label", "covers" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
         "Label", "instanceRole" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Start(),
       source,
       new String[] {
         "Label", "start" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Finish(),
       source,
       new String[] {
         "Label", "finish" //$NON-NLS-1$ //$NON-NLS-2$
       });
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
      (sequenceMessageEClass,
       source,
       new String[] {
         "metaclass", "Message", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SequenceMessage" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_Kind(),
       source,
       new String[] {
         "featureName", "messageSort", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingEnd(),
       source,
       new String[] {
         "featureName", "sendEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingEnd(),
       source,
       new String[] {
         "featureName", "receiveEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioEClass,
       source,
       new String[] {
         "metaclass", "Interaction", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Scenario" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_Merged(),
       source,
       new String[] {
         "featureName", "isMerged", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Scenario", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
         "featureName", "lifeline", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Interaction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
         "featureName", "message", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Interaction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInteractionFragments(),
       source,
       new String[] {
         "featureName", "fragment", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Interaction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
         "featureName", "fragment", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Interaction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedEvents(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEndEClass,
       source,
       new String[] {
         "metaclass", "MessageOccurrenceSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.MessageEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
         "featureName", "message", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MessageEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEClass,
       source,
       new String[] {
         "metaclass", "BehaviorExecutionSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Execution" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
         "featureName", "covered", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InteractionFragment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEndEClass,
       source,
       new String[] {
         "metaclass", "ExecutionOccurrenceSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.ExecutionEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
         "featureName", "execution", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ExecutionOccurrenceSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (creationEventEClass,
       source,
       new String[] {
         "metaclass", "CreationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.CreationEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (destructionEventEClass,
       source,
       new String[] {
         "metaclass", "DestructionEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.DestructionEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEventEClass,
       source,
       new String[] {
         "metaclass", "ExecutionEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.ExecutionEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (instanceRoleEClass,
       source,
       new String[] {
         "metaclass", "Lifeline", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.InstanceRole" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
         "featureName", "coveredBy", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Lifeline" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
         "featureName", "represents", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Lifeline" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "metaclass", "OccurrenceSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Event(),
       source,
       new String[] {
         "featureName", "event", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "OccurrenceSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
         "featureName", "covered", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InteractionFragment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum,
       source,
       new String[] {
         "enum", "MessageSort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "ASYNCH_CALL" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "SYNCH_CALL" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "enumLiteral", "REPLY" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "enumLiteral", "DELETE_MESSAGE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "enumLiteral", "CREATE_MESSAGE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventEClass,
       source,
       new String[] {
         "metaclass", "Event" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventReceiptOperationEClass,
       source,
       new String[] {
         "metaclass", "ReceiveOperationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.EventReceiptOperation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventReceiptOperation_Operation(),
       source,
       new String[] {
         "featureName", "operation", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ReceiveOperationEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventSentOperationEClass,
       source,
       new String[] {
         "metaclass", "SendOperationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.EventSentOperation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventSentOperation_Operation(),
       source,
       new String[] {
         "featureName", "operation", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "SendOperationEvent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (mergeLinkEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.MergeLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (refinementLinkEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.RefinementLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
         "featureName", "ownedBehavior", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "BehavioredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extends(),
       source,
       new String[] {
         "featureName", "extend", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "UseCase" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
         "umlOppositeReference", "extendedCase", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Extend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AbstractCapabilityExtensionPoints(),
       source,
       new String[] {
         "featureName", "extensionPoint", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "UseCase" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SuperGeneralizations(),
       source,
       new String[] {
         "featureName", "generalization", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Classifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
         "featureName", "generalization", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Classifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Includes(),
       source,
       new String[] {
         "featureName", "include", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "UseCase" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
         "umlOppositeReference", "addition", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtendEClass,
       source,
       new String[] {
         "metaclass", "Extend", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.AbstractCapabilityExtend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extended(),
       source,
       new String[] {
         "featureName", "extendedCase", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Extend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
         "featureName", "extension", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Extend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_ExtensionLocation(),
       source,
       new String[] {
         "featureName", "extensionLocation", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Extend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtensionPointEClass,
       source,
       new String[] {
         "metaclass", "ExtensionPoint", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.AbstractCapabilityExtensionPoint" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
         "featureName", "useCase", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ExtensionPoint" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_ExtendLinks(),
       source,
       new String[] {
         "umlOppositeReference", "extensionLocation", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Extend" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityGeneralizationEClass,
       source,
       new String[] {
         "metaclass", "Generalization", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.AbstractCapabilityGeneralization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Super(),
       source,
       new String[] {
         "featureName", "general", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Generalization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
         "featureName", "general", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Generalization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityIncludeEClass,
       source,
       new String[] {
         "metaclass", "Include", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.AbstractCapabilityInclude" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Included(),
       source,
       new String[] {
         "featureName", "addition", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
         "featureName", "includingCase", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Include" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionFragment_CoveredInstanceRoles(),
       source,
       new String[] {
         "featureName", "covered", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InteractionFragment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
         "featureName", "covered", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "InteractionFragment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Start(),
       source,
       new String[] {
         "featureName", "start", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ExecutionSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Finish(),
       source,
       new String[] {
         "featureName", "finish", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ExecutionSpecification" //$NON-NLS-1$ //$NON-NLS-2$
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
      (sequenceMessageEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Message", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Message::messageSort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingEnd(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Message::sendEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingEnd(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Message::receiveEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_InvokedOperation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ExchangedItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingPart(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingPart(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Interaction", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_Merged(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_PreCondition(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_PostCondition(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Interaction::lifeline", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Interaction::lifeline elements on which InstanceRole stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Interaction::message", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedInteractionFragments(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Interaction::fragment", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Interaction::fragment elements on which AbstractEnd stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Interaction::fragment", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Interaction::fragment elements on which Execution stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedEvents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "specific rule : a package will be created in the nearest package, the events will be stored there, and the Capability will have a package import element.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedFormalGates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedScenarioRealization(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_OwnedConstraintDurations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ReferencedScenarios(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizedScenarios(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizingScenarios(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::MessageOccurrenceSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageEnd::message", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::BehaviorExecutionSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InteractionFragment::covered", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ExecutionOccurrenceSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ExecutionOccurrenceSpecification::execution", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (creationEventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::CreationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (destructionEventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::DestructionEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (executionEventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ExecutionEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (instanceRoleEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Lifeline", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Lifeline::coveredBy", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Lifeline::represents", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::OccurrenceSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InteractionFragment::enclosingInteraction", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Event(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::OccurrenceSpecification::event", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InteractionFragment::covered", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort::asynchCall", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort::synchCall", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort::reply", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort::deleteMessage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::MessageSort::createMessage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventReceiptOperationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ReceiveOperationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventReceiptOperation_Operation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ReceiveOperationEvent::operation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (eventSentOperationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::SendOperationEvent", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEventSentOperation_Operation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::SendOperationEvent::operation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (mergeLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (refinementLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizedCapability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizingCapability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::UseCase", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_PreCondition(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_PostCondition(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::BehavioredClassifier::ownedBehavior", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::BehavioredClassifier::ownedBehavior elements on which Scenario stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncomingCapabilityAllocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OutgoingCapabilityAllocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extends(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::UseCase::extend", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Extend::extendedCase", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AbstractCapabilityExtensionPoints(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::UseCase::extensionPoint", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SuperGeneralizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Classifier::generalization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Generalization::general", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Includes(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::UseCase::include", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Include::addition", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedFunctionalChainAbstractCapabilityInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedAbstractFunctionAbstractCapabilityInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_AvailableInStates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OwnedAbstractCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedAbstractFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtendEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Extend", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extended(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Extend::extendedCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Extend::extension", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_ExtensionLocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Extend::extensionLocation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityExtensionPointEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ExtensionPoint", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ExtensionPoint::useCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_ExtendLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Extend::extensionLocation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which AbstractCapabilityExtend stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityGeneralizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Generalization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Super(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Generalization::general", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Generalization::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCapabilityIncludeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Include", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Included(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Include::addition", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Include::includingCase", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionFragmentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionFragment_CoveredInstanceRoles(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InteractionFragment::covered", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionStateEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_RelatedAbstractState(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_RelatedAbstractFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InteractionFragment::covered", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionUseEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionUse_ReferencedScenario(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionUse_ActualGates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (combinedFragmentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_Operator(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_ReferencedOperands(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_ExpressionGates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (gateEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperandEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionOperand_ReferencedInteractionFragments(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionOperand_Guard(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (interactionOperatorKindEEnum.getELiterals().get(11),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (timeLapseEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Start(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ExecutionSpecification::start", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getTimeLapse_Finish(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ExecutionSpecification::finish", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFragmentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFragment_OwnedGates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (fragmentEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFragmentEnd_AbstractFragment(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainAbstractCapabilityInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainAbstractCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAbstractCapabilityInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionAbstractCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionAbstractCapabilityInvolvement_Function(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (scenarioRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizedScenario(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizingScenario(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stateFragmentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStateFragment_RelatedAbstractState(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStateFragment_RelatedAbstractFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (armTimerEventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (cancelTimerEventEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (constraintDurationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getSequenceMessage_SendingEnd(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceMessage_ReceivingEnd(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedInstanceRoles(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedMessages(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedInteractionFragments(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedTimeLapses(),
       source,
       new String[] {
       });
    addAnnotation
      (getScenario_OwnedEvents(),
       source,
       new String[] {
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
       });
    addAnnotation
      (getInstanceRole_RepresentedInstance(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractEnd_Event(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
       });
    addAnnotation
      (getEventReceiptOperation_Operation(),
       source,
       new String[] {
       });
    addAnnotation
      (getEventSentOperation_Operation(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_OwnedScenarios(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Extends(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_AbstractCapabilityExtensionPoints(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_SuperGeneralizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Includes(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extended(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityExtend_ExtensionLocation(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_ExtendLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Super(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityInclude_Included(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
       });
    addAnnotation
      (getInteractionFragment_CoveredInstanceRoles(),
       source,
       new String[] {
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
       });
    addAnnotation
      (getTimeLapse_Start(),
       source,
       new String[] {
       });
    addAnnotation
      (getTimeLapse_Finish(),
       source,
       new String[] {
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
      (getSequenceMessage_InvokedOperation(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SequenceMessage.receivingEnd.event(self, ero);\r\n\tEventReceiptOperation.operation(ero, target);\r\n} or {\r\n\tSequenceMessage.sendingEnd.event(self, eso);\r\n\tEventSentOperation.operation(eso, target);\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingPart(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sendingEnd.covered.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingPart(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "receivingEnd.covered.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_SendingFunction(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sendingEnd.covered.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSequenceMessage_ReceivingFunction(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "receivingEnd.covered.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInstanceRoles.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ContainedParts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedInstanceRoles.representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_ReferencedScenarios(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Scenario.ownedTimeLapses(self, iu);\r\nInteractionUse.referencedScenario(iu, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizedScenarios(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Scenario.outgoingTraces(self, sr);\r\nScenarioRealization.realizedScenario(sr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenario_RealizingScenarios(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Scenario.incomingTraces(self, sr);\r\nScenarioRealization.realizingScenario(sr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageEnd_Message(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SequenceMessage.sendingEnd(target, self);\r\n} or {\r\n\tSequenceMessage.receivingEnd(target, self);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecution_Covered(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Execution.start.coveredInstanceRoles(self, target);\r\n} or {\r\n\tExecution.finish.coveredInstanceRoles(self, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExecutionEnd_Execution(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Execution.start(target, self);\r\n} or {\r\n\tExecution.finish(target, self);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInstanceRole_AbstractEnds(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "covered" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnd_Covered(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "coveredInstanceRoles" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizedCapability(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityRealization_RealizingCapability(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncomingCapabilityAllocation(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_OutgoingCapabilityAllocation(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Extending(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "extended" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_SubGeneralizations(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "^super" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Including(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "included" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Super(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "superGeneralizations.^super" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_Sub(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "subGeneralizations.sub" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludedAbstractCapabilities(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "includes.included" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_IncludingAbstractCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapabilityInclude.included(aci, self);\r\nAbstractCapabilityInclude.inclusion(aci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendedAbstractCapabilities(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "^extends.extended" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_ExtendingAbstractCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapabilityExtend.extended(ace, self);\r\nAbstractCapabilityExtend.^extension(ace, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedAbstractFunctions(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapability.involvedInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.function(afaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapability_InvolvedFunctionalChains(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractCapability.involvedInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.functionalChain(fcaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtend_Extension(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "^extends" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityExtensionPoint_AbstractCapability(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractCapabilityExtensionPoints" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityGeneralization_Sub(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "superGeneralizations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractCapabilityInclude_Inclusion(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "includes" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionState_Covered(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "coveredInstanceRoles" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getInteractionUse_ActualGates(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedGates" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCombinedFragment_ExpressionGates(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedGates" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFragmentEnd_AbstractFragment(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractFragment.start(target, self);\r\n} or {\r\n\tAbstractFragment.finish(target, self);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainAbstractCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involver" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainAbstractCapabilityInvolvement_FunctionalChain(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionAbstractCapabilityInvolvement_Capability(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involver" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionAbstractCapabilityInvolvement_Function(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizedScenario(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getScenarioRealization_RealizingScenario(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
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
      (mergeLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (refinementLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractCapabilityExtendEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractCapabilityExtensionPointEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractCapabilityGeneralizationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractCapabilityIncludeEClass,
       source,
       new String[] {
       });
  }

} //InteractionPackageImpl
