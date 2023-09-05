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
package org.polarsys.capella.core.data.information.communication.impl;

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
import org.polarsys.capella.core.data.information.communication.CommunicationFactory;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkExchanger;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.information.communication.MessageReferencePkg;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.communication.SignalInstance;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl;
import org.polarsys.capella.core.data.information.impl.InformationPackageImpl;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.impl.InteractionPackageImpl;
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
public class CommunicationPackageImpl extends EPackageImpl implements CommunicationPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass communicationItemEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exceptionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass messageEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass messageReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass messageReferencePkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass signalEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass signalInstanceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass communicationLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass communicationLinkExchangerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum communicationLinkKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum communicationLinkProtocolEEnum = null;

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
   * @see org.polarsys.capella.core.data.information.communication.CommunicationPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private CommunicationPackageImpl() {
    super(eNS_URI, CommunicationFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link CommunicationPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static CommunicationPackage init() {
    if (isInited) return (CommunicationPackage)EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredCommunicationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    CommunicationPackageImpl theCommunicationPackage = registeredCommunicationPackage instanceof CommunicationPackageImpl ? (CommunicationPackageImpl)registeredCommunicationPackage : new CommunicationPackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);
    DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(registeredPackage instanceof DatatypePackageImpl ? registeredPackage : DatatypePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(registeredPackage instanceof DatavaluePackageImpl ? registeredPackage : DatavaluePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    CsPackageImpl theCsPackage = (CsPackageImpl)(registeredPackage instanceof CsPackageImpl ? registeredPackage : CsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theCommunicationPackage.createPackageContents();
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
    theDatatypePackage.createPackageContents();
    theDatavaluePackage.createPackageContents();
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theCommunicationPackage.initializePackageContents();
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
    theDatatypePackage.initializePackageContents();
    theDatavaluePackage.initializePackageContents();
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCommunicationPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CommunicationPackage.eNS_URI, theCommunicationPackage);
    return theCommunicationPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCommunicationItem() {
    return communicationItemEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCommunicationItem_Visibility() {
    return (EAttribute)communicationItemEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationItem_OwnedStateMachines() {
    return (EReference)communicationItemEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationItem_Properties() {
    return (EReference)communicationItemEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getException() {
    return exceptionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMessage() {
    return messageEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMessageReference() {
    return messageReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMessageReference_Message() {
    return (EReference)messageReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMessageReferencePkg() {
    return messageReferencePkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMessageReferencePkg_OwnedMessageReferences() {
    return (EReference)messageReferencePkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSignal() {
    return signalEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSignal_SignalInstances() {
    return (EReference)signalEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSignalInstance() {
    return signalInstanceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCommunicationLink() {
    return communicationLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCommunicationLink_Kind() {
    return (EAttribute)communicationLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCommunicationLink_Protocol() {
    return (EAttribute)communicationLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLink_ExchangeItem() {
    return (EReference)communicationLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCommunicationLinkExchanger() {
    return communicationLinkExchangerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_OwnedCommunicationLinks() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Produce() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Consume() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Send() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Receive() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Call() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Execute() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Write() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Access() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Acquire() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCommunicationLinkExchanger_Transmit() {
    return (EReference)communicationLinkExchangerEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getCommunicationLinkKind() {
    return communicationLinkKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getCommunicationLinkProtocol() {
    return communicationLinkProtocolEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public CommunicationFactory getCommunicationFactory() {
    return (CommunicationFactory)getEFactoryInstance();
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
    communicationItemEClass = createEClass(COMMUNICATION_ITEM);
    createEAttribute(communicationItemEClass, COMMUNICATION_ITEM__VISIBILITY);
    createEReference(communicationItemEClass, COMMUNICATION_ITEM__OWNED_STATE_MACHINES);
    createEReference(communicationItemEClass, COMMUNICATION_ITEM__PROPERTIES);

    exceptionEClass = createEClass(EXCEPTION);

    messageEClass = createEClass(MESSAGE);

    messageReferenceEClass = createEClass(MESSAGE_REFERENCE);
    createEReference(messageReferenceEClass, MESSAGE_REFERENCE__MESSAGE);

    messageReferencePkgEClass = createEClass(MESSAGE_REFERENCE_PKG);
    createEReference(messageReferencePkgEClass, MESSAGE_REFERENCE_PKG__OWNED_MESSAGE_REFERENCES);

    signalEClass = createEClass(SIGNAL);
    createEReference(signalEClass, SIGNAL__SIGNAL_INSTANCES);

    signalInstanceEClass = createEClass(SIGNAL_INSTANCE);

    communicationLinkEClass = createEClass(COMMUNICATION_LINK);
    createEAttribute(communicationLinkEClass, COMMUNICATION_LINK__KIND);
    createEAttribute(communicationLinkEClass, COMMUNICATION_LINK__PROTOCOL);
    createEReference(communicationLinkEClass, COMMUNICATION_LINK__EXCHANGE_ITEM);

    communicationLinkExchangerEClass = createEClass(COMMUNICATION_LINK_EXCHANGER);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__OWNED_COMMUNICATION_LINKS);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__PRODUCE);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__CONSUME);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__SEND);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__RECEIVE);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__CALL);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__EXECUTE);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__WRITE);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__ACCESS);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__ACQUIRE);
    createEReference(communicationLinkExchangerEClass, COMMUNICATION_LINK_EXCHANGER__TRANSMIT);

    // Create enums
    communicationLinkKindEEnum = createEEnum(COMMUNICATION_LINK_KIND);
    communicationLinkProtocolEEnum = createEEnum(COMMUNICATION_LINK_PROTOCOL);
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
    DatavaluePackage theDatavaluePackage = (DatavaluePackage)EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    communicationItemEClass.getESuperTypes().add(theCapellacorePackage.getClassifier());
    communicationItemEClass.getESuperTypes().add(theDatavaluePackage.getDataValueContainer());
    exceptionEClass.getESuperTypes().add(this.getCommunicationItem());
    messageEClass.getESuperTypes().add(this.getCommunicationItem());
    messageReferenceEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    messageReferencePkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    signalEClass.getESuperTypes().add(this.getCommunicationItem());
    signalEClass.getESuperTypes().add(theBehaviorPackage.getAbstractSignal());
    signalInstanceEClass.getESuperTypes().add(theInformationPackage.getAbstractInstance());
    communicationLinkEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());

    // Initialize classes and features; add operations and parameters
    initEClass(communicationItemEClass, CommunicationItem.class, "CommunicationItem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getCommunicationItem_Visibility(), theCapellacorePackage.getVisibilityKind(), "visibility", null, 0, 1, CommunicationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationItem_OwnedStateMachines(), theCapellacommonPackage.getStateMachine(), null, "ownedStateMachines", null, 0, -1, CommunicationItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationItem_Properties(), theInformationPackage.getProperty(), null, "properties", null, 0, -1, CommunicationItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exceptionEClass, org.polarsys.capella.core.data.information.communication.Exception.class, "Exception", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(messageReferenceEClass, MessageReference.class, "MessageReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMessageReference_Message(), this.getMessage(), null, "message", null, 1, 1, MessageReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(messageReferencePkgEClass, MessageReferencePkg.class, "MessageReferencePkg", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getMessageReferencePkg_OwnedMessageReferences(), this.getMessageReference(), null, "ownedMessageReferences", null, 0, -1, MessageReferencePkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(signalEClass, Signal.class, "Signal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSignal_SignalInstances(), this.getSignalInstance(), null, "signalInstances", null, 0, -1, Signal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(signalInstanceEClass, SignalInstance.class, "SignalInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(communicationLinkEClass, CommunicationLink.class, "CommunicationLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getCommunicationLink_Kind(), this.getCommunicationLinkKind(), "kind", null, 0, 1, CommunicationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCommunicationLink_Protocol(), this.getCommunicationLinkProtocol(), "protocol", null, 0, 1, CommunicationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLink_ExchangeItem(), theInformationPackage.getExchangeItem(), null, "exchangeItem", null, 0, 1, CommunicationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(communicationLinkExchangerEClass, CommunicationLinkExchanger.class, "CommunicationLinkExchanger", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_OwnedCommunicationLinks(), this.getCommunicationLink(), null, "ownedCommunicationLinks", null, 0, -1, CommunicationLinkExchanger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Produce(), this.getCommunicationLink(), null, "produce", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Consume(), this.getCommunicationLink(), null, "consume", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Send(), this.getCommunicationLink(), null, "send", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Receive(), this.getCommunicationLink(), null, "receive", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Call(), this.getCommunicationLink(), null, "call", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Execute(), this.getCommunicationLink(), null, "execute", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Write(), this.getCommunicationLink(), null, "write", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Access(), this.getCommunicationLink(), null, "access", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Acquire(), this.getCommunicationLink(), null, "acquire", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCommunicationLinkExchanger_Transmit(), this.getCommunicationLink(), null, "transmit", null, 0, -1, CommunicationLinkExchanger.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(communicationLinkKindEEnum, CommunicationLinkKind.class, "CommunicationLinkKind"); //$NON-NLS-1$
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.UNSET);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.PRODUCE);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.CONSUME);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.SEND);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.RECEIVE);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.CALL);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.EXECUTE);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.WRITE);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.ACCESS);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.ACQUIRE);
    addEEnumLiteral(communicationLinkKindEEnum, CommunicationLinkKind.TRANSMIT);

    initEEnum(communicationLinkProtocolEEnum, CommunicationLinkProtocol.class, "CommunicationLinkProtocol"); //$NON-NLS-1$
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.UNSET);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.UNICAST);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.MULTICAST);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.BROADCAST);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.SYNCHRONOUS);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.ASYNCHRONOUS);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.READ);
    addEEnumLiteral(communicationLinkProtocolEEnum, CommunicationLinkProtocol.ACCEPT);

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
         "description", "sub-package containing the elements invovled in communication between elements (messages, signals, ...)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationItemEClass,
       source,
       new String[] {
         "description", "Generic class serving as a common parent for dedicated communication artifacts\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Visibility(),
       source,
       new String[] {
         "description", "refer to VisibilityKind description\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to VisibilityKind definition\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_OwnedStateMachines(),
       source,
       new String[] {
         "description", "state machines associated to this communication item, as a mean to specify communication protocols\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Properties(),
       source,
       new String[] {
         "description", "attributes of the communication item\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exceptionEClass,
       source,
       new String[] {
         "description", "A piece of  information raised (typically by an operation) to mention the occurence of an abnormal condition\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella 1.0.3", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEClass,
       source,
       new String[] {
         "description", "A piece of information flowing between two model elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferenceEClass,
       source,
       new String[] {
         "description", "Implementation class supporting the referencing of a Message element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
         "description", "The message being referenced\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferencePkgEClass,
       source,
       new String[] {
         "description", "a container for message references elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
         "description", "the list of MessageReference elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalEClass,
       source,
       new String[] {
         "description", "A signal is a specification of send request instances communicated between objects. The receiving object handles the\r\nreceived request instances as specified by its receptions. The data carried by a send request (which was passed to it by the\r\nsend invocation occurrence that caused that request) are represented as attributes of the signal. A signal is defined\r\nindependently of the classifiers handling the signal occurrence\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSignal_SignalInstances(),
       source,
       new String[] {
         "description", "list of signal instances associated with this Signal\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalInstanceEClass,
       source,
       new String[] {
         "description", "instance of a Signal element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum,
       source,
       new String[] {
         "description", "enumeration listing the various possibilities of communication links\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the CommunicationLink protocol is not yet set", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a production of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a comsumption of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a sending of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a reception of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a call of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe an execution of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a writing of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe an access to the ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe an acquisition of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a transmission of ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum,
       source,
       new String[] {
         "description", "enumeration listing the various possibilities for the protocol of the communication link", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the CommunicationLink protocol is not yet set", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a sending of ExchangeItem using the unicast protocol", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a sending of ExchangeItem using the multicast protocol", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a sending of ExchangeItem using the broadcast protocol", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a call of ExchangeItem using the synchronous protocol", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a call of ExchangeItem using the asynchronous protocol", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a access to the ExchangeItem by reading it", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "used when the CommunicationLink is used to describe a access to the ExchangeItem by accepting it", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkEClass,
       source,
       new String[] {
         "description", "describes a link of communication using exchange items", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_Kind(),
       source,
       new String[] {
         "description", "refer to CommunicationLinkKind description", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to CommunicationLinkKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_Protocol(),
       source,
       new String[] {
         "description", "refer to CommunicationLinkProtocol description", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to CommunicationLinkProtocol definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_ExchangeItem(),
       source,
       new String[] {
         "description", "describes the exchange item related to the link", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to CommunicationLinkProtocol definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkExchangerEClass,
       source,
       new String[] {
         "description", "describes an element which can communicate using ExchangeItems", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_OwnedCommunicationLinks(),
       source,
       new String[] {
         "description", "list of all communication links owned by the communication exchanger", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Produce(),
       source,
       new String[] {
         "description", "(automatically computed) list of all production CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Consume(),
       source,
       new String[] {
         "description", "(automatically computed) list of all consumption CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Send(),
       source,
       new String[] {
         "description", "(automatically computed) list of all sending CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Receive(),
       source,
       new String[] {
         "description", "(automatically computed) list of all receiving CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Call(),
       source,
       new String[] {
         "description", "(automatically computed) list of all calling CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Execute(),
       source,
       new String[] {
         "description", "(automatically computed) list of all execution CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Write(),
       source,
       new String[] {
         "description", "(automatically computed) list of all writing CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Access(),
       source,
       new String[] {
         "description", "(automatically computed) list of all accessing CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Acquire(),
       source,
       new String[] {
         "description", "(automatically computed) list of all acquiring CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Transmit(),
       source,
       new String[] {
         "description", "(automatically computed) list of all transmitting CommunicationLinks", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getCommunicationItem_Visibility(),
       source,
       new String[] {
       });
    addAnnotation
      (getCommunicationItem_OwnedStateMachines(),
       source,
       new String[] {
       });
    addAnnotation
      (exceptionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (messageEClass,
       source,
       new String[] {
       });
    addAnnotation
      (messageReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
       });
    addAnnotation
      (signalEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSignal_SignalInstances(),
       source,
       new String[] {
       });
    addAnnotation
      (signalInstanceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (communicationLinkKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (communicationLinkProtocolEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (communicationLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCommunicationLink_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getCommunicationLink_Protocol(),
       source,
       new String[] {
       });
    addAnnotation
      (getCommunicationLink_ExchangeItem(),
       source,
       new String[] {
       });
    addAnnotation
      (getCommunicationLinkExchanger_OwnedCommunicationLinks(),
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
      (communicationItemEClass,
       source,
       new String[] {
         "Label", "CommunicationItem" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Properties(),
       source,
       new String[] {
         "Label", "properties" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exceptionEClass,
       source,
       new String[] {
         "Label", "Exception" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEClass,
       source,
       new String[] {
         "Label", "Message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferenceEClass,
       source,
       new String[] {
         "Label", "MessageReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
         "Label", "message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferencePkgEClass,
       source,
       new String[] {
         "Label", "MessageReferencePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
         "Label", "ownedMessageReferences" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalEClass,
       source,
       new String[] {
         "Label", "Signal" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSignal_SignalInstances(),
       source,
       new String[] {
         "Label", "signalInstances" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalInstanceEClass,
       source,
       new String[] {
         "Label", "SignalInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_OwnedCommunicationLinks(),
       source,
       new String[] {
         "Label", "ownedCommunicationLinks" //$NON-NLS-1$ //$NON-NLS-2$
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
      (communicationItemEClass,
       source,
       new String[] {
         "metaclass", "Classifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Properties(),
       source,
       new String[] {
         "featureName", "ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exceptionEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Exception" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferenceEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.MessageReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferencePkgEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalEClass,
       source,
       new String[] {
         "metaclass", "Signal", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Signal" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSignal_SignalInstances(),
       source,
       new String[] {
         "featureName", "ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Signal" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalInstanceEClass,
       source,
       new String[] {
         "metaclass", "Property", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.SignalInstance" //$NON-NLS-1$ //$NON-NLS-2$
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
      (communicationItemEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Visibility(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::visibility", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_OwnedStateMachines(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which StateMachine stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationItem_Properties(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "no found equivalent since the three children (Exception, Signal, Message) have different hierarchies. The specific rule should  create a package, stored the Properties in this package, and finally create a packageImport under the NamedElement reference for the element", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "elements inside the imported package on which a Property stereotype or any stereotype that inherits from it  is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exceptionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Message", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (messageReferencePkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which MessageReference stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Signal", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getSignal_SignalInstances(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Signal::ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Signal::ownedAttribute elements on which SignalInstance stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (signalInstanceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Property", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(6),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(7),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(8),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(9),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkKindEEnum.getELiterals().get(10),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(6),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkProtocolEEnum.getELiterals().get(7),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_Protocol(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLink_ExchangeItem(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (communicationLinkExchangerEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_OwnedCommunicationLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Produce(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Consume(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Send(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Receive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Call(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Execute(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Write(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Access(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Acquire(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Transmit(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getCommunicationItem_Properties(),
       source,
       new String[] {
       });
    addAnnotation
      (getMessageReference_Message(),
       source,
       new String[] {
       });
    addAnnotation
      (getMessageReferencePkg_OwnedMessageReferences(),
       source,
       new String[] {
       });
    addAnnotation
      (getSignal_SignalInstances(),
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
      (getCommunicationItem_Properties(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Produce(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::PRODUCE);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Consume(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::CONSUME);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Send(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::SEND);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Receive(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::RECEIVE);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Call(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::CALL);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Execute(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::EXECUTE);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Write(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::WRITE);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Access(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::ACCESS);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Acquire(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::ACQUIRE);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCommunicationLinkExchanger_Transmit(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "CommunicationLinkExchanger.ownedCommunicationLinks(self, target);\r\nCommunicationLink.kind(target, ::TRANSMIT);" //$NON-NLS-1$ //$NON-NLS-2$
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
      (messageReferenceEClass,
       source,
       new String[] {
       });
  }

} //CommunicationPackageImpl
