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
package org.polarsys.capella.core.data.fa.impl;

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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.AbstractFunctionAllocation;
import org.polarsys.capella.core.data.fa.AbstractFunctionalArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalChainContainer;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.fa.ComponentPortKind;
import org.polarsys.capella.core.data.fa.ControlNode;
import org.polarsys.capella.core.data.fa.ControlNodeKind;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.ExchangeContainment;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionSpecification;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainKind;
import org.polarsys.capella.core.data.fa.FunctionalChainRealization;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.core.data.fa.OrientationPortKind;
import org.polarsys.capella.core.data.fa.ReferenceHierarchyContext;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl;
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
public class FaPackageImpl extends EPackageImpl implements FaPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionalArchitectureEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionalBlockEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionSpecificationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeCategoryEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeContainmentEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeSpecificationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalExchangeSpecificationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionalChainContainerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainInvolvementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionInputPortEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionOutputPortEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentFunctionalAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeSpecificationRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalExchangeRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalExchangeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractFunctionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionPortEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeAllocatorEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeCategoryEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeFunctionalExchangeAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentExchangeRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentPortEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentPortAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass componentPortAllocationEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainInvolvementLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass sequenceLinkEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass sequenceLinkEndEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass functionalChainInvolvementFunctionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass controlNodeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass referenceHierarchyContextEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum functionalChainKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum functionKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum componentExchangeKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum componentPortKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum orientationPortKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum controlNodeKindEEnum = null;

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
   * @see org.polarsys.capella.core.data.fa.FaPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private FaPackageImpl() {
    super(eNS_URI, FaFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link FaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static FaPackage init() {
    if (isInited) return (FaPackage)EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredFaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    FaPackageImpl theFaPackage = registeredFaPackage instanceof FaPackageImpl ? (FaPackageImpl)registeredFaPackage : new FaPackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theFaPackage.createPackageContents();
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
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theFaPackage.initializePackageContents();
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
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theFaPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(FaPackage.eNS_URI, theFaPackage);
    return theFaPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunctionalArchitecture() {
    return abstractFunctionalArchitectureEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedFunctionPkg() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedComponentExchanges() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedFunctionalLinks() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedFunctionalAllocations() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations() {
    return (EReference)abstractFunctionalArchitectureEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunctionalBlock() {
    return abstractFunctionalBlockEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_OwnedFunctionalAllocation() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_OwnedComponentExchanges() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_OwnedComponentExchangeCategories() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_FunctionalAllocations() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_AllocatedFunctions() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_InExchangeLinks() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalBlock_OutExchangeLinks() {
    return (EReference)abstractFunctionalBlockEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionPkg() {
    return functionPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPkg_OwnedFunctionalLinks() {
    return (EReference)functionPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPkg_OwnedExchanges() {
    return (EReference)functionPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPkg_OwnedExchangeSpecificationRealizations() {
    return (EReference)functionPkgEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPkg_OwnedCategories() {
    return (EReference)functionPkgEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPkg_OwnedFunctionSpecifications() {
    return (EReference)functionPkgEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionSpecification() {
    return functionSpecificationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionSpecification_InExchangeLinks() {
    return (EReference)functionSpecificationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionSpecification_OutExchangeLinks() {
    return (EReference)functionSpecificationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionSpecification_OwnedFunctionPorts() {
    return (EReference)functionSpecificationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionSpecification_SubFunctionSpecifications() {
    return (EReference)functionSpecificationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeCategory() {
    return exchangeCategoryEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeCategory_Exchanges() {
    return (EReference)exchangeCategoryEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeLink() {
    return exchangeLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeLink_Exchanges() {
    return (EReference)exchangeLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeLink_ExchangeContainmentLinks() {
    return (EReference)exchangeLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeLink_OwnedExchangeContainments() {
    return (EReference)exchangeLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeLink_Sources() {
    return (EReference)exchangeLinkEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeLink_Destinations() {
    return (EReference)exchangeLinkEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeContainment() {
    return exchangeContainmentEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeContainment_Exchange() {
    return (EReference)exchangeContainmentEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeContainment_Link() {
    return (EReference)exchangeContainmentEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeSpecification() {
    return exchangeSpecificationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecification_ContainingLink() {
    return (EReference)exchangeSpecificationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecification_Link() {
    return (EReference)exchangeSpecificationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecification_OutgoingExchangeSpecificationRealizations() {
    return (EReference)exchangeSpecificationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecification_IncomingExchangeSpecificationRealizations() {
    return (EReference)exchangeSpecificationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalExchangeSpecification() {
    return functionalExchangeSpecificationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchangeSpecification_FunctionalExchanges() {
    return (EReference)functionalExchangeSpecificationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChain() {
    return functionalChainEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getFunctionalChain_Kind() {
    return (EAttribute)functionalChainEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_OwnedFunctionalChainInvolvements() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_OwnedFunctionalChainRealizations() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvedFunctionalChainInvolvements() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvedFunctions() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvedFunctionalExchanges() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvedElements() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_EnactedFunctions() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_EnactedFunctionalBlocks() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_AvailableInStates() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_FirstFunctionalChainInvolvements() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvingCapabilities() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_InvolvingCapabilityRealizations() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_RealizedFunctionalChains() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_RealizingFunctionalChains() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_PreCondition() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_PostCondition() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_OwnedSequenceNodes() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(17);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChain_OwnedSequenceLinks() {
    return (EReference)functionalChainEClass.getEStructuralFeatures().get(18);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunctionalChainContainer() {
    return abstractFunctionalChainContainerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunctionalChainContainer_OwnedFunctionalChains() {
    return (EReference)abstractFunctionalChainContainerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainInvolvement() {
    return functionalChainInvolvementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvement_NextFunctionalChainInvolvements() {
    return (EReference)functionalChainInvolvementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements() {
    return (EReference)functionalChainInvolvementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvement_InvolvedElement() {
    return (EReference)functionalChainInvolvementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainReference() {
    return functionalChainReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainReference_ReferencedFunctionalChain() {
    return (EReference)functionalChainReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionInputPort() {
    return functionInputPortEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionInputPort_IncomingExchangeItems() {
    return (EReference)functionInputPortEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionInputPort_IncomingFunctionalExchanges() {
    return (EReference)functionInputPortEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionOutputPort() {
    return functionOutputPortEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionOutputPort_OutgoingExchangeItems() {
    return (EReference)functionOutputPortEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionOutputPort_OutgoingFunctionalExchanges() {
    return (EReference)functionOutputPortEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunctionAllocation() {
    return abstractFunctionAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentFunctionalAllocation() {
    return componentFunctionalAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentFunctionalAllocation_Function() {
    return (EReference)componentFunctionalAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentFunctionalAllocation_Block() {
    return (EReference)componentFunctionalAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainRealization() {
    return functionalChainRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeSpecificationRealization() {
    return exchangeSpecificationRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecificationRealization_RealizedExchangeSpecification() {
    return (EReference)exchangeSpecificationRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeSpecificationRealization_RealizingExchangeSpecification() {
    return (EReference)exchangeSpecificationRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalExchangeRealization() {
    return functionalExchangeRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchangeRealization_RealizedFunctionalExchange() {
    return (EReference)functionalExchangeRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchangeRealization_RealizingFunctionalExchange() {
    return (EReference)functionalExchangeRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionRealization() {
    return functionRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionRealization_AllocatedFunction() {
    return (EReference)functionRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionRealization_AllocatingFunction() {
    return (EReference)functionRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalExchange() {
    return functionalExchangeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_ExchangeSpecifications() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_InvolvingFunctionalChains() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_ExchangedItems() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_AllocatingComponentExchanges() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_IncomingFunctionalExchangeRealizations() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_OutgoingFunctionalExchangeRealizations() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_Categories() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_OwnedFunctionalExchangeRealizations() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_SourceFunctionOutputPort() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_TargetFunctionInputPort() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_RealizedFunctionalExchanges() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalExchange_RealizingFunctionalExchanges() {
    return (EReference)functionalExchangeEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractFunction() {
    return abstractFunctionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractFunction_Kind() {
    return (EAttribute)abstractFunctionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractFunction_Condition() {
    return (EAttribute)abstractFunctionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_OwnedFunctions() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_OwnedFunctionRealizations() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_OwnedFunctionalExchanges() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_SubFunctions() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_OutFunctionRealizations() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_InFunctionRealizations() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_ComponentFunctionalAllocations() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_AllocationBlocks() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_AvailableInStates() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_InvolvingCapabilities() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_InvolvingCapabilityRealizations() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_InvolvingFunctionalChains() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_LinkedStateMachine() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractFunction_LinkedFunctionSpecification() {
    return (EReference)abstractFunctionEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionPort() {
    return functionPortEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @deprecated See {@link org.polarsys.capella.core.data.fa.FunctionPort#getRepresentedComponentPort() model documentation} for details.
   * @generated
   */
	@Deprecated
		@Override
	public EReference getFunctionPort_RepresentedComponentPort() {
    return (EReference)functionPortEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPort_AllocatorComponentPorts() {
    return (EReference)functionPortEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPort_RealizedFunctionPorts() {
    return (EReference)functionPortEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionPort_RealizingFunctionPorts() {
    return (EReference)functionPortEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchange() {
    return componentExchangeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponentExchange_Kind() {
    return (EAttribute)componentExchangeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponentExchange_Oriented() {
    return (EAttribute)componentExchangeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_AllocatedFunctionalExchanges() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_IncomingComponentExchangeRealizations() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_OutgoingComponentExchangeRealizations() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_OwnedComponentExchangeRealizations() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_OwnedComponentExchangeEnds() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_SourcePort() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_SourcePart() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_TargetPort() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_TargetPart() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(12);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_Categories() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(13);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_AllocatorPhysicalLinks() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(14);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_RealizedComponentExchanges() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(15);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchange_RealizingComponentExchanges() {
    return (EReference)componentExchangeEClass.getEStructuralFeatures().get(16);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeAllocation() {
    return componentExchangeAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeAllocation_ComponentExchangeAllocated() {
    return (EReference)componentExchangeAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeAllocation_ComponentExchangeAllocator() {
    return (EReference)componentExchangeAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeAllocator() {
    return componentExchangeAllocatorEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeAllocator_OwnedComponentExchangeAllocations() {
    return (EReference)componentExchangeAllocatorEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeAllocator_AllocatedComponentExchanges() {
    return (EReference)componentExchangeAllocatorEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeCategory() {
    return componentExchangeCategoryEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeCategory_Exchanges() {
    return (EReference)componentExchangeCategoryEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeEnd() {
    return componentExchangeEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeEnd_Port() {
    return (EReference)componentExchangeEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeEnd_Part() {
    return (EReference)componentExchangeEndEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeFunctionalExchangeAllocation() {
    return componentExchangeFunctionalExchangeAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange() {
    return (EReference)componentExchangeFunctionalExchangeAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange() {
    return (EReference)componentExchangeFunctionalExchangeAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentExchangeRealization() {
    return componentExchangeRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeRealization_AllocatedComponentExchange() {
    return (EReference)componentExchangeRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentExchangeRealization_AllocatingComponentExchange() {
    return (EReference)componentExchangeRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentPort() {
    return componentPortEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponentPort_Orientation() {
    return (EAttribute)componentPortEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getComponentPort_Kind() {
    return (EAttribute)componentPortEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_ComponentExchanges() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_AllocatedFunctionPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_DelegatedComponentPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_DelegatingComponentPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_AllocatingPhysicalPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_RealizedComponentPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPort_RealizingComponentPorts() {
    return (EReference)componentPortEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentPortAllocation() {
    return componentPortAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocation_OwnedComponentPortAllocationEnds() {
    return (EReference)componentPortAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocation_AllocatedPort() {
    return (EReference)componentPortAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocation_AllocatingPort() {
    return (EReference)componentPortAllocationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComponentPortAllocationEnd() {
    return componentPortAllocationEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocationEnd_Port() {
    return (EReference)componentPortAllocationEndEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocationEnd_Part() {
    return (EReference)componentPortAllocationEndEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComponentPortAllocationEnd_OwningComponentPortAllocation() {
    return (EReference)componentPortAllocationEndEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainInvolvementLink() {
    return functionalChainInvolvementLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementLink_ExchangeContext() {
    return (EReference)functionalChainInvolvementLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementLink_ExchangedItems() {
    return (EReference)functionalChainInvolvementLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementLink_Source() {
    return (EReference)functionalChainInvolvementLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementLink_Target() {
    return (EReference)functionalChainInvolvementLinkEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSequenceLink() {
    return sequenceLinkEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceLink_Condition() {
    return (EReference)sequenceLinkEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceLink_Links() {
    return (EReference)sequenceLinkEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceLink_Source() {
    return (EReference)sequenceLinkEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getSequenceLink_Target() {
    return (EReference)sequenceLinkEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getSequenceLinkEnd() {
    return sequenceLinkEndEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getFunctionalChainInvolvementFunction() {
    return functionalChainInvolvementFunctionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks() {
    return (EReference)functionalChainInvolvementFunctionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getFunctionalChainInvolvementFunction_IncomingInvolvementLinks() {
    return (EReference)functionalChainInvolvementFunctionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getControlNode() {
    return controlNodeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getControlNode_Kind() {
    return (EAttribute)controlNodeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getReferenceHierarchyContext() {
    return referenceHierarchyContextEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getReferenceHierarchyContext_SourceReferenceHierarchy() {
    return (EReference)referenceHierarchyContextEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getReferenceHierarchyContext_TargetReferenceHierarchy() {
    return (EReference)referenceHierarchyContextEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getFunctionalChainKind() {
    return functionalChainKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getFunctionKind() {
    return functionKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getComponentExchangeKind() {
    return componentExchangeKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getComponentPortKind() {
    return componentPortKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getOrientationPortKind() {
    return orientationPortKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getControlNodeKind() {
    return controlNodeKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public FaFactory getFaFactory() {
    return (FaFactory)getEFactoryInstance();
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
    abstractFunctionalArchitectureEClass = createEClass(ABSTRACT_FUNCTIONAL_ARCHITECTURE);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTION_PKG);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGES);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_LINKS);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_FUNCTIONAL_ALLOCATIONS);
    createEReference(abstractFunctionalArchitectureEClass, ABSTRACT_FUNCTIONAL_ARCHITECTURE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS);

    abstractFunctionalBlockEClass = createEClass(ABSTRACT_FUNCTIONAL_BLOCK);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__OWNED_FUNCTIONAL_ALLOCATION);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGE_CATEGORIES);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__FUNCTIONAL_ALLOCATIONS);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__ALLOCATED_FUNCTIONS);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__IN_EXCHANGE_LINKS);
    createEReference(abstractFunctionalBlockEClass, ABSTRACT_FUNCTIONAL_BLOCK__OUT_EXCHANGE_LINKS);

    functionPkgEClass = createEClass(FUNCTION_PKG);
    createEReference(functionPkgEClass, FUNCTION_PKG__OWNED_FUNCTIONAL_LINKS);
    createEReference(functionPkgEClass, FUNCTION_PKG__OWNED_EXCHANGES);
    createEReference(functionPkgEClass, FUNCTION_PKG__OWNED_EXCHANGE_SPECIFICATION_REALIZATIONS);
    createEReference(functionPkgEClass, FUNCTION_PKG__OWNED_CATEGORIES);
    createEReference(functionPkgEClass, FUNCTION_PKG__OWNED_FUNCTION_SPECIFICATIONS);

    functionSpecificationEClass = createEClass(FUNCTION_SPECIFICATION);
    createEReference(functionSpecificationEClass, FUNCTION_SPECIFICATION__IN_EXCHANGE_LINKS);
    createEReference(functionSpecificationEClass, FUNCTION_SPECIFICATION__OUT_EXCHANGE_LINKS);
    createEReference(functionSpecificationEClass, FUNCTION_SPECIFICATION__OWNED_FUNCTION_PORTS);
    createEReference(functionSpecificationEClass, FUNCTION_SPECIFICATION__SUB_FUNCTION_SPECIFICATIONS);

    exchangeCategoryEClass = createEClass(EXCHANGE_CATEGORY);
    createEReference(exchangeCategoryEClass, EXCHANGE_CATEGORY__EXCHANGES);

    exchangeLinkEClass = createEClass(EXCHANGE_LINK);
    createEReference(exchangeLinkEClass, EXCHANGE_LINK__EXCHANGES);
    createEReference(exchangeLinkEClass, EXCHANGE_LINK__EXCHANGE_CONTAINMENT_LINKS);
    createEReference(exchangeLinkEClass, EXCHANGE_LINK__OWNED_EXCHANGE_CONTAINMENTS);
    createEReference(exchangeLinkEClass, EXCHANGE_LINK__SOURCES);
    createEReference(exchangeLinkEClass, EXCHANGE_LINK__DESTINATIONS);

    exchangeContainmentEClass = createEClass(EXCHANGE_CONTAINMENT);
    createEReference(exchangeContainmentEClass, EXCHANGE_CONTAINMENT__EXCHANGE);
    createEReference(exchangeContainmentEClass, EXCHANGE_CONTAINMENT__LINK);

    exchangeSpecificationEClass = createEClass(EXCHANGE_SPECIFICATION);
    createEReference(exchangeSpecificationEClass, EXCHANGE_SPECIFICATION__CONTAINING_LINK);
    createEReference(exchangeSpecificationEClass, EXCHANGE_SPECIFICATION__LINK);
    createEReference(exchangeSpecificationEClass, EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS);
    createEReference(exchangeSpecificationEClass, EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS);

    functionalExchangeSpecificationEClass = createEClass(FUNCTIONAL_EXCHANGE_SPECIFICATION);
    createEReference(functionalExchangeSpecificationEClass, FUNCTIONAL_EXCHANGE_SPECIFICATION__FUNCTIONAL_EXCHANGES);

    functionalChainEClass = createEClass(FUNCTIONAL_CHAIN);
    createEAttribute(functionalChainEClass, FUNCTIONAL_CHAIN__KIND);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__PRE_CONDITION);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__POST_CONDITION);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES);
    createEReference(functionalChainEClass, FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS);

    abstractFunctionalChainContainerEClass = createEClass(ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER);
    createEReference(abstractFunctionalChainContainerEClass, ABSTRACT_FUNCTIONAL_CHAIN_CONTAINER__OWNED_FUNCTIONAL_CHAINS);

    functionalChainInvolvementEClass = createEClass(FUNCTIONAL_CHAIN_INVOLVEMENT);
    createEReference(functionalChainInvolvementEClass, FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    createEReference(functionalChainInvolvementEClass, FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS);
    createEReference(functionalChainInvolvementEClass, FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT);

    functionalChainReferenceEClass = createEClass(FUNCTIONAL_CHAIN_REFERENCE);
    createEReference(functionalChainReferenceEClass, FUNCTIONAL_CHAIN_REFERENCE__REFERENCED_FUNCTIONAL_CHAIN);

    functionInputPortEClass = createEClass(FUNCTION_INPUT_PORT);
    createEReference(functionInputPortEClass, FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS);
    createEReference(functionInputPortEClass, FUNCTION_INPUT_PORT__INCOMING_FUNCTIONAL_EXCHANGES);

    functionOutputPortEClass = createEClass(FUNCTION_OUTPUT_PORT);
    createEReference(functionOutputPortEClass, FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS);
    createEReference(functionOutputPortEClass, FUNCTION_OUTPUT_PORT__OUTGOING_FUNCTIONAL_EXCHANGES);

    abstractFunctionAllocationEClass = createEClass(ABSTRACT_FUNCTION_ALLOCATION);

    componentFunctionalAllocationEClass = createEClass(COMPONENT_FUNCTIONAL_ALLOCATION);
    createEReference(componentFunctionalAllocationEClass, COMPONENT_FUNCTIONAL_ALLOCATION__FUNCTION);
    createEReference(componentFunctionalAllocationEClass, COMPONENT_FUNCTIONAL_ALLOCATION__BLOCK);

    functionalChainRealizationEClass = createEClass(FUNCTIONAL_CHAIN_REALIZATION);

    exchangeSpecificationRealizationEClass = createEClass(EXCHANGE_SPECIFICATION_REALIZATION);
    createEReference(exchangeSpecificationRealizationEClass, EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION);
    createEReference(exchangeSpecificationRealizationEClass, EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION);

    functionalExchangeRealizationEClass = createEClass(FUNCTIONAL_EXCHANGE_REALIZATION);
    createEReference(functionalExchangeRealizationEClass, FUNCTIONAL_EXCHANGE_REALIZATION__REALIZED_FUNCTIONAL_EXCHANGE);
    createEReference(functionalExchangeRealizationEClass, FUNCTIONAL_EXCHANGE_REALIZATION__REALIZING_FUNCTIONAL_EXCHANGE);

    functionRealizationEClass = createEClass(FUNCTION_REALIZATION);
    createEReference(functionRealizationEClass, FUNCTION_REALIZATION__ALLOCATED_FUNCTION);
    createEReference(functionRealizationEClass, FUNCTION_REALIZATION__ALLOCATING_FUNCTION);

    functionalExchangeEClass = createEClass(FUNCTIONAL_EXCHANGE);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__EXCHANGE_SPECIFICATIONS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__INVOLVING_FUNCTIONAL_CHAINS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__EXCHANGED_ITEMS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__ALLOCATING_COMPONENT_EXCHANGES);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_REALIZATIONS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__INCOMING_FUNCTIONAL_EXCHANGE_REALIZATIONS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__OUTGOING_FUNCTIONAL_EXCHANGE_REALIZATIONS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__CATEGORIES);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__OWNED_FUNCTIONAL_EXCHANGE_REALIZATIONS);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__SOURCE_FUNCTION_OUTPUT_PORT);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__TARGET_FUNCTION_INPUT_PORT);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__REALIZED_FUNCTIONAL_EXCHANGES);
    createEReference(functionalExchangeEClass, FUNCTIONAL_EXCHANGE__REALIZING_FUNCTIONAL_EXCHANGES);

    abstractFunctionEClass = createEClass(ABSTRACT_FUNCTION);
    createEAttribute(abstractFunctionEClass, ABSTRACT_FUNCTION__KIND);
    createEAttribute(abstractFunctionEClass, ABSTRACT_FUNCTION__CONDITION);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__OWNED_FUNCTIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__OWNED_FUNCTION_REALIZATIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__OWNED_FUNCTIONAL_EXCHANGES);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__SUB_FUNCTIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__OUT_FUNCTION_REALIZATIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__IN_FUNCTION_REALIZATIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__COMPONENT_FUNCTIONAL_ALLOCATIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__ALLOCATION_BLOCKS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__AVAILABLE_IN_STATES);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__INVOLVING_CAPABILITIES);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__INVOLVING_CAPABILITY_REALIZATIONS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__INVOLVING_FUNCTIONAL_CHAINS);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__LINKED_STATE_MACHINE);
    createEReference(abstractFunctionEClass, ABSTRACT_FUNCTION__LINKED_FUNCTION_SPECIFICATION);

    functionPortEClass = createEClass(FUNCTION_PORT);
    createEReference(functionPortEClass, FUNCTION_PORT__REPRESENTED_COMPONENT_PORT);
    createEReference(functionPortEClass, FUNCTION_PORT__ALLOCATOR_COMPONENT_PORTS);
    createEReference(functionPortEClass, FUNCTION_PORT__REALIZED_FUNCTION_PORTS);
    createEReference(functionPortEClass, FUNCTION_PORT__REALIZING_FUNCTION_PORTS);

    componentExchangeEClass = createEClass(COMPONENT_EXCHANGE);
    createEAttribute(componentExchangeEClass, COMPONENT_EXCHANGE__KIND);
    createEAttribute(componentExchangeEClass, COMPONENT_EXCHANGE__ORIENTED);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__ALLOCATED_FUNCTIONAL_EXCHANGES);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__INCOMING_COMPONENT_EXCHANGE_REALIZATIONS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_REALIZATIONS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__OUTGOING_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATIONS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_REALIZATIONS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__OWNED_COMPONENT_EXCHANGE_ENDS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__SOURCE_PORT);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__SOURCE_PART);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__TARGET_PORT);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__TARGET_PART);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__CATEGORIES);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__ALLOCATOR_PHYSICAL_LINKS);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__REALIZED_COMPONENT_EXCHANGES);
    createEReference(componentExchangeEClass, COMPONENT_EXCHANGE__REALIZING_COMPONENT_EXCHANGES);

    componentExchangeAllocationEClass = createEClass(COMPONENT_EXCHANGE_ALLOCATION);
    createEReference(componentExchangeAllocationEClass, COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATED);
    createEReference(componentExchangeAllocationEClass, COMPONENT_EXCHANGE_ALLOCATION__COMPONENT_EXCHANGE_ALLOCATOR);

    componentExchangeAllocatorEClass = createEClass(COMPONENT_EXCHANGE_ALLOCATOR);
    createEReference(componentExchangeAllocatorEClass, COMPONENT_EXCHANGE_ALLOCATOR__OWNED_COMPONENT_EXCHANGE_ALLOCATIONS);
    createEReference(componentExchangeAllocatorEClass, COMPONENT_EXCHANGE_ALLOCATOR__ALLOCATED_COMPONENT_EXCHANGES);

    componentExchangeCategoryEClass = createEClass(COMPONENT_EXCHANGE_CATEGORY);
    createEReference(componentExchangeCategoryEClass, COMPONENT_EXCHANGE_CATEGORY__EXCHANGES);

    componentExchangeEndEClass = createEClass(COMPONENT_EXCHANGE_END);
    createEReference(componentExchangeEndEClass, COMPONENT_EXCHANGE_END__PORT);
    createEReference(componentExchangeEndEClass, COMPONENT_EXCHANGE_END__PART);

    componentExchangeFunctionalExchangeAllocationEClass = createEClass(COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION);
    createEReference(componentExchangeFunctionalExchangeAllocationEClass, COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATED_FUNCTIONAL_EXCHANGE);
    createEReference(componentExchangeFunctionalExchangeAllocationEClass, COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION__ALLOCATING_COMPONENT_EXCHANGE);

    componentExchangeRealizationEClass = createEClass(COMPONENT_EXCHANGE_REALIZATION);
    createEReference(componentExchangeRealizationEClass, COMPONENT_EXCHANGE_REALIZATION__ALLOCATED_COMPONENT_EXCHANGE);
    createEReference(componentExchangeRealizationEClass, COMPONENT_EXCHANGE_REALIZATION__ALLOCATING_COMPONENT_EXCHANGE);

    componentPortEClass = createEClass(COMPONENT_PORT);
    createEAttribute(componentPortEClass, COMPONENT_PORT__ORIENTATION);
    createEAttribute(componentPortEClass, COMPONENT_PORT__KIND);
    createEReference(componentPortEClass, COMPONENT_PORT__COMPONENT_EXCHANGES);
    createEReference(componentPortEClass, COMPONENT_PORT__ALLOCATED_FUNCTION_PORTS);
    createEReference(componentPortEClass, COMPONENT_PORT__DELEGATED_COMPONENT_PORTS);
    createEReference(componentPortEClass, COMPONENT_PORT__DELEGATING_COMPONENT_PORTS);
    createEReference(componentPortEClass, COMPONENT_PORT__ALLOCATING_PHYSICAL_PORTS);
    createEReference(componentPortEClass, COMPONENT_PORT__REALIZED_COMPONENT_PORTS);
    createEReference(componentPortEClass, COMPONENT_PORT__REALIZING_COMPONENT_PORTS);

    componentPortAllocationEClass = createEClass(COMPONENT_PORT_ALLOCATION);
    createEReference(componentPortAllocationEClass, COMPONENT_PORT_ALLOCATION__OWNED_COMPONENT_PORT_ALLOCATION_ENDS);
    createEReference(componentPortAllocationEClass, COMPONENT_PORT_ALLOCATION__ALLOCATED_PORT);
    createEReference(componentPortAllocationEClass, COMPONENT_PORT_ALLOCATION__ALLOCATING_PORT);

    componentPortAllocationEndEClass = createEClass(COMPONENT_PORT_ALLOCATION_END);
    createEReference(componentPortAllocationEndEClass, COMPONENT_PORT_ALLOCATION_END__PORT);
    createEReference(componentPortAllocationEndEClass, COMPONENT_PORT_ALLOCATION_END__PART);
    createEReference(componentPortAllocationEndEClass, COMPONENT_PORT_ALLOCATION_END__OWNING_COMPONENT_PORT_ALLOCATION);

    functionalChainInvolvementLinkEClass = createEClass(FUNCTIONAL_CHAIN_INVOLVEMENT_LINK);
    createEReference(functionalChainInvolvementLinkEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGE_CONTEXT);
    createEReference(functionalChainInvolvementLinkEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS);
    createEReference(functionalChainInvolvementLinkEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE);
    createEReference(functionalChainInvolvementLinkEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET);

    sequenceLinkEClass = createEClass(SEQUENCE_LINK);
    createEReference(sequenceLinkEClass, SEQUENCE_LINK__CONDITION);
    createEReference(sequenceLinkEClass, SEQUENCE_LINK__LINKS);
    createEReference(sequenceLinkEClass, SEQUENCE_LINK__SOURCE);
    createEReference(sequenceLinkEClass, SEQUENCE_LINK__TARGET);

    sequenceLinkEndEClass = createEClass(SEQUENCE_LINK_END);

    functionalChainInvolvementFunctionEClass = createEClass(FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION);
    createEReference(functionalChainInvolvementFunctionEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OUTGOING_INVOLVEMENT_LINKS);
    createEReference(functionalChainInvolvementFunctionEClass, FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INCOMING_INVOLVEMENT_LINKS);

    controlNodeEClass = createEClass(CONTROL_NODE);
    createEAttribute(controlNodeEClass, CONTROL_NODE__KIND);

    referenceHierarchyContextEClass = createEClass(REFERENCE_HIERARCHY_CONTEXT);
    createEReference(referenceHierarchyContextEClass, REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY);
    createEReference(referenceHierarchyContextEClass, REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY);

    // Create enums
    functionalChainKindEEnum = createEEnum(FUNCTIONAL_CHAIN_KIND);
    functionKindEEnum = createEEnum(FUNCTION_KIND);
    componentExchangeKindEEnum = createEEnum(COMPONENT_EXCHANGE_KIND);
    componentPortKindEEnum = createEEnum(COMPONENT_PORT_KIND);
    orientationPortKindEEnum = createEEnum(ORIENTATION_PORT_KIND);
    controlNodeKindEEnum = createEEnum(CONTROL_NODE_KIND);
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
    ActivityPackage theActivityPackage = (ActivityPackage)EPackage.Registry.INSTANCE.getEPackage(ActivityPackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    CtxPackage theCtxPackage = (CtxPackage)EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI);
    LaPackage theLaPackage = (LaPackage)EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);
    BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);
    CsPackage theCsPackage = (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    abstractFunctionalArchitectureEClass.getESuperTypes().add(theCapellacorePackage.getModellingArchitecture());
    abstractFunctionalBlockEClass.getESuperTypes().add(theCapellacorePackage.getModellingBlock());
    functionPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    functionSpecificationEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());
    functionSpecificationEClass.getESuperTypes().add(theActivityPackage.getAbstractActivity());
    exchangeCategoryEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    exchangeLinkEClass.getESuperTypes().add(theCapellacorePackage.getNamedRelationship());
    exchangeContainmentEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    exchangeSpecificationEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    exchangeSpecificationEClass.getESuperTypes().add(theActivityPackage.getActivityExchange());
    functionalExchangeSpecificationEClass.getESuperTypes().add(this.getExchangeSpecification());
    functionalChainEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    functionalChainEClass.getESuperTypes().add(theCapellacorePackage.getInvolverElement());
    functionalChainEClass.getESuperTypes().add(theCapellacorePackage.getInvolvedElement());
    abstractFunctionalChainContainerEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    functionalChainInvolvementEClass.getESuperTypes().add(theCapellacorePackage.getInvolvement());
    functionalChainReferenceEClass.getESuperTypes().add(this.getFunctionalChainInvolvement());
    functionInputPortEClass.getESuperTypes().add(this.getFunctionPort());
    functionInputPortEClass.getESuperTypes().add(theActivityPackage.getInputPin());
    functionOutputPortEClass.getESuperTypes().add(this.getFunctionPort());
    functionOutputPortEClass.getESuperTypes().add(theActivityPackage.getOutputPin());
    abstractFunctionAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    componentFunctionalAllocationEClass.getESuperTypes().add(this.getAbstractFunctionAllocation());
    functionalChainRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    exchangeSpecificationRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    functionalExchangeRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    functionRealizationEClass.getESuperTypes().add(this.getAbstractFunctionAllocation());
    functionalExchangeEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    functionalExchangeEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    functionalExchangeEClass.getESuperTypes().add(theCapellacorePackage.getInvolvedElement());
    functionalExchangeEClass.getESuperTypes().add(theActivityPackage.getObjectFlow());
    functionalExchangeEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    functionalExchangeEClass.getESuperTypes().add(theInformationPackage.getAbstractEventOperation());
    abstractFunctionEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());
    abstractFunctionEClass.getESuperTypes().add(theCapellacorePackage.getInvolvedElement());
    abstractFunctionEClass.getESuperTypes().add(theInformationPackage.getAbstractInstance());
    abstractFunctionEClass.getESuperTypes().add(this.getAbstractFunctionalChainContainer());
    abstractFunctionEClass.getESuperTypes().add(theActivityPackage.getCallBehaviorAction());
    abstractFunctionEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    functionPortEClass.getESuperTypes().add(theInformationPackage.getPort());
    functionPortEClass.getESuperTypes().add(theCapellacorePackage.getTypedElement());
    functionPortEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    componentExchangeEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    componentExchangeEClass.getESuperTypes().add(theInformationPackage.getAbstractEventOperation());
    componentExchangeEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    componentExchangeEClass.getESuperTypes().add(this.getExchangeSpecification());
    componentExchangeAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    componentExchangeAllocatorEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    componentExchangeCategoryEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    componentExchangeEndEClass.getESuperTypes().add(theModellingcorePackage.getInformationsExchanger());
    componentExchangeEndEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    componentExchangeFunctionalExchangeAllocationEClass.getESuperTypes().add(this.getAbstractFunctionAllocation());
    componentExchangeRealizationEClass.getESuperTypes().add(this.getExchangeSpecificationRealization());
    componentPortEClass.getESuperTypes().add(theInformationPackage.getPort());
    componentPortEClass.getESuperTypes().add(theModellingcorePackage.getInformationsExchanger());
    componentPortEClass.getESuperTypes().add(theInformationPackage.getProperty());
    componentPortAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    componentPortAllocationEndEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    functionalChainInvolvementLinkEClass.getESuperTypes().add(this.getFunctionalChainInvolvement());
    functionalChainInvolvementLinkEClass.getESuperTypes().add(this.getReferenceHierarchyContext());
    sequenceLinkEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    sequenceLinkEClass.getESuperTypes().add(this.getReferenceHierarchyContext());
    sequenceLinkEndEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    functionalChainInvolvementFunctionEClass.getESuperTypes().add(this.getFunctionalChainInvolvement());
    functionalChainInvolvementFunctionEClass.getESuperTypes().add(this.getSequenceLinkEnd());
    controlNodeEClass.getESuperTypes().add(this.getSequenceLinkEnd());

    // Initialize classes and features; add operations and parameters
    initEClass(abstractFunctionalArchitectureEClass, AbstractFunctionalArchitecture.class, "AbstractFunctionalArchitecture", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedFunctionPkg(), this.getFunctionPkg(), null, "ownedFunctionPkg", null, 0, 1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedComponentExchanges(), this.getComponentExchange(), null, "ownedComponentExchanges", null, 0, -1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories(), this.getComponentExchangeCategory(), null, "ownedComponentExchangeCategories", null, 0, -1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedFunctionalLinks(), this.getExchangeLink(), null, "ownedFunctionalLinks", null, 0, -1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedFunctionalAllocations(), this.getComponentFunctionalAllocation(), null, "ownedFunctionalAllocations", null, 0, -1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations(), this.getComponentExchangeRealization(), null, "ownedComponentExchangeRealizations", null, 0, -1, AbstractFunctionalArchitecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFunctionalBlockEClass, AbstractFunctionalBlock.class, "AbstractFunctionalBlock", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_OwnedFunctionalAllocation(), this.getComponentFunctionalAllocation(), null, "ownedFunctionalAllocation", null, 0, -1, AbstractFunctionalBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_OwnedComponentExchanges(), this.getComponentExchange(), null, "ownedComponentExchanges", null, 0, -1, AbstractFunctionalBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_OwnedComponentExchangeCategories(), this.getComponentExchangeCategory(), null, "ownedComponentExchangeCategories", null, 0, -1, AbstractFunctionalBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_FunctionalAllocations(), this.getComponentFunctionalAllocation(), this.getComponentFunctionalAllocation_Block(), "functionalAllocations", null, 0, -1, AbstractFunctionalBlock.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_AllocatedFunctions(), this.getAbstractFunction(), this.getAbstractFunction_AllocationBlocks(), "allocatedFunctions", null, 0, -1, AbstractFunctionalBlock.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_InExchangeLinks(), this.getExchangeLink(), null, "inExchangeLinks", null, 0, -1, AbstractFunctionalBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunctionalBlock_OutExchangeLinks(), this.getExchangeLink(), null, "outExchangeLinks", null, 0, -1, AbstractFunctionalBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionPkgEClass, FunctionPkg.class, "FunctionPkg", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionPkg_OwnedFunctionalLinks(), this.getExchangeLink(), null, "ownedFunctionalLinks", null, 0, -1, FunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPkg_OwnedExchanges(), this.getFunctionalExchangeSpecification(), null, "ownedExchanges", null, 0, -1, FunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPkg_OwnedExchangeSpecificationRealizations(), this.getExchangeSpecificationRealization(), null, "ownedExchangeSpecificationRealizations", null, 0, -1, FunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPkg_OwnedCategories(), this.getExchangeCategory(), null, "ownedCategories", null, 0, -1, FunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPkg_OwnedFunctionSpecifications(), this.getFunctionSpecification(), null, "ownedFunctionSpecifications", null, 0, -1, FunctionPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionSpecificationEClass, FunctionSpecification.class, "FunctionSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionSpecification_InExchangeLinks(), this.getExchangeLink(), null, "inExchangeLinks", null, 0, -1, FunctionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionSpecification_OutExchangeLinks(), this.getExchangeLink(), null, "outExchangeLinks", null, 0, -1, FunctionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionSpecification_OwnedFunctionPorts(), this.getFunctionPort(), null, "ownedFunctionPorts", null, 0, -1, FunctionSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionSpecification_SubFunctionSpecifications(), this.getFunctionSpecification(), null, "subFunctionSpecifications", null, 0, -1, FunctionSpecification.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeCategoryEClass, ExchangeCategory.class, "ExchangeCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeCategory_Exchanges(), this.getFunctionalExchange(), null, "exchanges", null, 0, -1, ExchangeCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeLinkEClass, ExchangeLink.class, "ExchangeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeLink_Exchanges(), this.getExchangeSpecification(), null, "exchanges", null, 0, -1, ExchangeLink.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeLink_ExchangeContainmentLinks(), this.getExchangeContainment(), this.getExchangeContainment_Link(), "exchangeContainmentLinks", null, 0, -1, ExchangeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeLink_OwnedExchangeContainments(), this.getExchangeContainment(), null, "ownedExchangeContainments", null, 0, -1, ExchangeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeLink_Sources(), this.getFunctionSpecification(), null, "sources", null, 0, -1, ExchangeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeLink_Destinations(), this.getFunctionSpecification(), null, "destinations", null, 0, -1, ExchangeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeContainmentEClass, ExchangeContainment.class, "ExchangeContainment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeContainment_Exchange(), this.getExchangeSpecification(), this.getExchangeSpecification_Link(), "exchange", null, 1, 1, ExchangeContainment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeContainment_Link(), this.getExchangeLink(), this.getExchangeLink_ExchangeContainmentLinks(), "link", null, 1, 1, ExchangeContainment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeSpecificationEClass, ExchangeSpecification.class, "ExchangeSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeSpecification_ContainingLink(), this.getExchangeLink(), null, "containingLink", null, 0, 1, ExchangeSpecification.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeSpecification_Link(), this.getExchangeContainment(), this.getExchangeContainment_Exchange(), "link", null, 0, 1, ExchangeSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeSpecification_OutgoingExchangeSpecificationRealizations(), this.getExchangeSpecificationRealization(), this.getExchangeSpecificationRealization_RealizingExchangeSpecification(), "outgoingExchangeSpecificationRealizations", null, 0, -1, ExchangeSpecification.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeSpecification_IncomingExchangeSpecificationRealizations(), this.getExchangeSpecificationRealization(), this.getExchangeSpecificationRealization_RealizedExchangeSpecification(), "incomingExchangeSpecificationRealizations", null, 0, -1, ExchangeSpecification.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalExchangeSpecificationEClass, FunctionalExchangeSpecification.class, "FunctionalExchangeSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalExchangeSpecification_FunctionalExchanges(), this.getFunctionalExchange(), null, "functionalExchanges", null, 0, -1, FunctionalExchangeSpecification.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainEClass, FunctionalChain.class, "FunctionalChain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getFunctionalChain_Kind(), this.getFunctionalChainKind(), "kind", null, 0, 1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_OwnedFunctionalChainInvolvements(), this.getFunctionalChainInvolvement(), null, "ownedFunctionalChainInvolvements", null, 0, -1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_OwnedFunctionalChainRealizations(), this.getFunctionalChainRealization(), null, "ownedFunctionalChainRealizations", null, 0, -1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvedFunctionalChainInvolvements(), this.getFunctionalChainInvolvement(), null, "involvedFunctionalChainInvolvements", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvedFunctions(), this.getAbstractFunction(), this.getAbstractFunction_InvolvingFunctionalChains(), "involvedFunctions", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvedFunctionalExchanges(), this.getFunctionalExchange(), this.getFunctionalExchange_InvolvingFunctionalChains(), "involvedFunctionalExchanges", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvedElements(), theCapellacorePackage.getInvolvedElement(), null, "involvedElements", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_EnactedFunctions(), this.getAbstractFunction(), null, "enactedFunctions", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_EnactedFunctionalBlocks(), this.getAbstractFunctionalBlock(), null, "enactedFunctionalBlocks", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_AvailableInStates(), theCapellacommonPackage.getState(), null, "availableInStates", null, 0, -1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_FirstFunctionalChainInvolvements(), this.getFunctionalChainInvolvement(), null, "firstFunctionalChainInvolvements", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvingCapabilities(), theCtxPackage.getCapability(), null, "involvingCapabilities", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_InvolvingCapabilityRealizations(), theLaPackage.getCapabilityRealization(), null, "involvingCapabilityRealizations", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_RealizedFunctionalChains(), this.getFunctionalChain(), null, "realizedFunctionalChains", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_RealizingFunctionalChains(), this.getFunctionalChain(), null, "realizingFunctionalChains", null, 0, -1, FunctionalChain.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_PreCondition(), theCapellacorePackage.getConstraint(), null, "preCondition", null, 0, 1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_PostCondition(), theCapellacorePackage.getConstraint(), null, "postCondition", null, 0, 1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_OwnedSequenceNodes(), this.getControlNode(), null, "ownedSequenceNodes", null, 0, -1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChain_OwnedSequenceLinks(), this.getSequenceLink(), null, "ownedSequenceLinks", null, 0, -1, FunctionalChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFunctionalChainContainerEClass, AbstractFunctionalChainContainer.class, "AbstractFunctionalChainContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractFunctionalChainContainer_OwnedFunctionalChains(), this.getFunctionalChain(), null, "ownedFunctionalChains", null, 0, -1, AbstractFunctionalChainContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainInvolvementEClass, FunctionalChainInvolvement.class, "FunctionalChainInvolvement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvement_NextFunctionalChainInvolvements(), this.getFunctionalChainInvolvement(), null, "nextFunctionalChainInvolvements", null, 0, -1, FunctionalChainInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements(), this.getFunctionalChainInvolvement(), null, "previousFunctionalChainInvolvements", null, 0, -1, FunctionalChainInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvement_InvolvedElement(), theCapellacorePackage.getInvolvedElement(), null, "involvedElement", null, 0, 1, FunctionalChainInvolvement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainReferenceEClass, FunctionalChainReference.class, "FunctionalChainReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalChainReference_ReferencedFunctionalChain(), this.getFunctionalChain(), null, "referencedFunctionalChain", null, 0, 1, FunctionalChainReference.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionInputPortEClass, FunctionInputPort.class, "FunctionInputPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionInputPort_IncomingExchangeItems(), theInformationPackage.getExchangeItem(), null, "incomingExchangeItems", null, 0, -1, FunctionInputPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionInputPort_IncomingFunctionalExchanges(), this.getFunctionalExchange(), null, "incomingFunctionalExchanges", null, 0, -1, FunctionInputPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionOutputPortEClass, FunctionOutputPort.class, "FunctionOutputPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionOutputPort_OutgoingExchangeItems(), theInformationPackage.getExchangeItem(), null, "outgoingExchangeItems", null, 0, -1, FunctionOutputPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionOutputPort_OutgoingFunctionalExchanges(), this.getFunctionalExchange(), null, "outgoingFunctionalExchanges", null, 0, -1, FunctionOutputPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFunctionAllocationEClass, AbstractFunctionAllocation.class, "AbstractFunctionAllocation", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(componentFunctionalAllocationEClass, ComponentFunctionalAllocation.class, "ComponentFunctionalAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentFunctionalAllocation_Function(), this.getAbstractFunction(), this.getAbstractFunction_ComponentFunctionalAllocations(), "function", null, 1, 1, ComponentFunctionalAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentFunctionalAllocation_Block(), this.getAbstractFunctionalBlock(), this.getAbstractFunctionalBlock_FunctionalAllocations(), "block", null, 1, 1, ComponentFunctionalAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainRealizationEClass, FunctionalChainRealization.class, "FunctionalChainRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(exchangeSpecificationRealizationEClass, ExchangeSpecificationRealization.class, "ExchangeSpecificationRealization", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeSpecificationRealization_RealizedExchangeSpecification(), this.getExchangeSpecification(), this.getExchangeSpecification_IncomingExchangeSpecificationRealizations(), "realizedExchangeSpecification", null, 1, 1, ExchangeSpecificationRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeSpecificationRealization_RealizingExchangeSpecification(), this.getExchangeSpecification(), this.getExchangeSpecification_OutgoingExchangeSpecificationRealizations(), "realizingExchangeSpecification", null, 1, 1, ExchangeSpecificationRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalExchangeRealizationEClass, FunctionalExchangeRealization.class, "FunctionalExchangeRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalExchangeRealization_RealizedFunctionalExchange(), this.getFunctionalExchange(), this.getFunctionalExchange_IncomingFunctionalExchangeRealizations(), "realizedFunctionalExchange", null, 1, 1, FunctionalExchangeRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchangeRealization_RealizingFunctionalExchange(), this.getFunctionalExchange(), this.getFunctionalExchange_OutgoingFunctionalExchangeRealizations(), "realizingFunctionalExchange", null, 1, 1, FunctionalExchangeRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionRealizationEClass, FunctionRealization.class, "FunctionRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionRealization_AllocatedFunction(), this.getAbstractFunction(), this.getAbstractFunction_InFunctionRealizations(), "allocatedFunction", null, 1, 1, FunctionRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionRealization_AllocatingFunction(), this.getAbstractFunction(), this.getAbstractFunction_OutFunctionRealizations(), "allocatingFunction", null, 1, 1, FunctionRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalExchangeEClass, FunctionalExchange.class, "FunctionalExchange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalExchange_ExchangeSpecifications(), this.getFunctionalExchangeSpecification(), null, "exchangeSpecifications", null, 0, -1, FunctionalExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_InvolvingFunctionalChains(), this.getFunctionalChain(), this.getFunctionalChain_InvolvedFunctionalExchanges(), "involvingFunctionalChains", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_ExchangedItems(), theInformationPackage.getExchangeItem(), null, "exchangedItems", null, 0, -1, FunctionalExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_AllocatingComponentExchanges(), this.getComponentExchange(), this.getComponentExchange_AllocatedFunctionalExchanges(), "allocatingComponentExchanges", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations(), this.getComponentExchangeFunctionalExchangeAllocation(), this.getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange(), "incomingComponentExchangeFunctionalExchangeRealizations", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_IncomingFunctionalExchangeRealizations(), this.getFunctionalExchangeRealization(), this.getFunctionalExchangeRealization_RealizedFunctionalExchange(), "incomingFunctionalExchangeRealizations", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_OutgoingFunctionalExchangeRealizations(), this.getFunctionalExchangeRealization(), this.getFunctionalExchangeRealization_RealizingFunctionalExchange(), "outgoingFunctionalExchangeRealizations", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_Categories(), this.getExchangeCategory(), null, "categories", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_OwnedFunctionalExchangeRealizations(), this.getFunctionalExchangeRealization(), null, "ownedFunctionalExchangeRealizations", null, 0, -1, FunctionalExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_SourceFunctionOutputPort(), this.getFunctionOutputPort(), null, "sourceFunctionOutputPort", null, 0, 1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_TargetFunctionInputPort(), this.getFunctionInputPort(), null, "targetFunctionInputPort", null, 0, 1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_RealizedFunctionalExchanges(), this.getFunctionalExchange(), this.getFunctionalExchange_RealizingFunctionalExchanges(), "realizedFunctionalExchanges", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalExchange_RealizingFunctionalExchanges(), this.getFunctionalExchange(), this.getFunctionalExchange_RealizedFunctionalExchanges(), "realizingFunctionalExchanges", null, 0, -1, FunctionalExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractFunctionEClass, AbstractFunction.class, "AbstractFunction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getAbstractFunction_Kind(), this.getFunctionKind(), "kind", null, 0, 1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractFunction_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_OwnedFunctions(), this.getAbstractFunction(), null, "ownedFunctions", null, 0, -1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_OwnedFunctionRealizations(), this.getFunctionRealization(), null, "ownedFunctionRealizations", null, 0, -1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_OwnedFunctionalExchanges(), this.getFunctionalExchange(), null, "ownedFunctionalExchanges", null, 0, -1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_SubFunctions(), this.getAbstractFunction(), null, "subFunctions", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_OutFunctionRealizations(), this.getFunctionRealization(), this.getFunctionRealization_AllocatingFunction(), "outFunctionRealizations", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_InFunctionRealizations(), this.getFunctionRealization(), this.getFunctionRealization_AllocatedFunction(), "inFunctionRealizations", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_ComponentFunctionalAllocations(), this.getComponentFunctionalAllocation(), this.getComponentFunctionalAllocation_Function(), "componentFunctionalAllocations", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_AllocationBlocks(), this.getAbstractFunctionalBlock(), this.getAbstractFunctionalBlock_AllocatedFunctions(), "allocationBlocks", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_AvailableInStates(), theCapellacommonPackage.getState(), null, "availableInStates", null, 0, -1, AbstractFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_InvolvingCapabilities(), theCtxPackage.getCapability(), null, "involvingCapabilities", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_InvolvingCapabilityRealizations(), theLaPackage.getCapabilityRealization(), null, "involvingCapabilityRealizations", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_InvolvingFunctionalChains(), this.getFunctionalChain(), this.getFunctionalChain_InvolvedFunctions(), "involvingFunctionalChains", null, 0, -1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_LinkedStateMachine(), theCapellacommonPackage.getStateMachine(), null, "linkedStateMachine", null, 0, 1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractFunction_LinkedFunctionSpecification(), this.getFunctionSpecification(), null, "linkedFunctionSpecification", null, 0, 1, AbstractFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionPortEClass, FunctionPort.class, "FunctionPort", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionPort_RepresentedComponentPort(), this.getComponentPort(), null, "representedComponentPort", null, 0, 1, FunctionPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPort_AllocatorComponentPorts(), this.getComponentPort(), this.getComponentPort_AllocatedFunctionPorts(), "allocatorComponentPorts", null, 0, -1, FunctionPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPort_RealizedFunctionPorts(), this.getFunctionPort(), null, "realizedFunctionPorts", null, 0, -1, FunctionPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionPort_RealizingFunctionPorts(), this.getFunctionPort(), null, "realizingFunctionPorts", null, 0, -1, FunctionPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeEClass, ComponentExchange.class, "ComponentExchange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getComponentExchange_Kind(), this.getComponentExchangeKind(), "kind", null, 0, 1, ComponentExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getComponentExchange_Oriented(), ecorePackage.getEBoolean(), "oriented", "false", 0, 1, ComponentExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getComponentExchange_AllocatedFunctionalExchanges(), this.getFunctionalExchange(), this.getFunctionalExchange_AllocatingComponentExchanges(), "allocatedFunctionalExchanges", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_IncomingComponentExchangeRealizations(), this.getComponentExchangeRealization(), this.getComponentExchangeRealization_AllocatedComponentExchange(), "incomingComponentExchangeRealizations", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_OutgoingComponentExchangeRealizations(), this.getComponentExchangeRealization(), this.getComponentExchangeRealization_AllocatingComponentExchange(), "outgoingComponentExchangeRealizations", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations(), this.getComponentExchangeFunctionalExchangeAllocation(), this.getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange(), "outgoingComponentExchangeFunctionalExchangeAllocations", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations(), this.getComponentExchangeFunctionalExchangeAllocation(), null, "ownedComponentExchangeFunctionalExchangeAllocations", null, 0, -1, ComponentExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_OwnedComponentExchangeRealizations(), this.getComponentExchangeRealization(), null, "ownedComponentExchangeRealizations", null, 0, -1, ComponentExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_OwnedComponentExchangeEnds(), this.getComponentExchangeEnd(), null, "ownedComponentExchangeEnds", null, 0, -1, ComponentExchange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_SourcePort(), theInformationPackage.getPort(), null, "sourcePort", null, 0, 1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_SourcePart(), theCsPackage.getPart(), null, "sourcePart", null, 0, 1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_TargetPort(), theInformationPackage.getPort(), null, "targetPort", null, 0, 1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_TargetPart(), theCsPackage.getPart(), null, "targetPart", null, 0, 1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_Categories(), this.getComponentExchangeCategory(), null, "categories", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_AllocatorPhysicalLinks(), theCsPackage.getPhysicalLink(), null, "allocatorPhysicalLinks", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_RealizedComponentExchanges(), this.getComponentExchange(), this.getComponentExchange_RealizingComponentExchanges(), "realizedComponentExchanges", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchange_RealizingComponentExchanges(), this.getComponentExchange(), this.getComponentExchange_RealizedComponentExchanges(), "realizingComponentExchanges", null, 0, -1, ComponentExchange.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeAllocationEClass, ComponentExchangeAllocation.class, "ComponentExchangeAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeAllocation_ComponentExchangeAllocated(), this.getComponentExchange(), null, "componentExchangeAllocated", null, 1, 1, ComponentExchangeAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchangeAllocation_ComponentExchangeAllocator(), this.getComponentExchangeAllocator(), null, "componentExchangeAllocator", null, 1, 1, ComponentExchangeAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeAllocatorEClass, ComponentExchangeAllocator.class, "ComponentExchangeAllocator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeAllocator_OwnedComponentExchangeAllocations(), this.getComponentExchangeAllocation(), null, "ownedComponentExchangeAllocations", null, 0, -1, ComponentExchangeAllocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchangeAllocator_AllocatedComponentExchanges(), this.getComponentExchange(), null, "allocatedComponentExchanges", null, 0, -1, ComponentExchangeAllocator.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeCategoryEClass, ComponentExchangeCategory.class, "ComponentExchangeCategory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeCategory_Exchanges(), this.getComponentExchange(), null, "exchanges", null, 0, -1, ComponentExchangeCategory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeEndEClass, ComponentExchangeEnd.class, "ComponentExchangeEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeEnd_Port(), theInformationPackage.getPort(), null, "port", null, 0, 1, ComponentExchangeEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchangeEnd_Part(), theCsPackage.getPart(), null, "part", null, 0, 1, ComponentExchangeEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeFunctionalExchangeAllocationEClass, ComponentExchangeFunctionalExchangeAllocation.class, "ComponentExchangeFunctionalExchangeAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange(), this.getFunctionalExchange(), this.getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations(), "allocatedFunctionalExchange", null, 1, 1, ComponentExchangeFunctionalExchangeAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange(), this.getComponentExchange(), this.getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations(), "allocatingComponentExchange", null, 1, 1, ComponentExchangeFunctionalExchangeAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentExchangeRealizationEClass, ComponentExchangeRealization.class, "ComponentExchangeRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentExchangeRealization_AllocatedComponentExchange(), this.getComponentExchange(), this.getComponentExchange_IncomingComponentExchangeRealizations(), "allocatedComponentExchange", null, 1, 1, ComponentExchangeRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentExchangeRealization_AllocatingComponentExchange(), this.getComponentExchange(), this.getComponentExchange_OutgoingComponentExchangeRealizations(), "allocatingComponentExchange", null, 1, 1, ComponentExchangeRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentPortEClass, ComponentPort.class, "ComponentPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getComponentPort_Orientation(), this.getOrientationPortKind(), "orientation", null, 0, 1, ComponentPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getComponentPort_Kind(), this.getComponentPortKind(), "kind", null, 0, 1, ComponentPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_ComponentExchanges(), this.getComponentExchange(), null, "componentExchanges", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_AllocatedFunctionPorts(), this.getFunctionPort(), this.getFunctionPort_AllocatorComponentPorts(), "allocatedFunctionPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_DelegatedComponentPorts(), this.getComponentPort(), this.getComponentPort_DelegatingComponentPorts(), "delegatedComponentPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_DelegatingComponentPorts(), this.getComponentPort(), this.getComponentPort_DelegatedComponentPorts(), "delegatingComponentPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_AllocatingPhysicalPorts(), theCsPackage.getPhysicalPort(), theCsPackage.getPhysicalPort_AllocatedComponentPorts(), "allocatingPhysicalPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_RealizedComponentPorts(), this.getComponentPort(), this.getComponentPort_RealizingComponentPorts(), "realizedComponentPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPort_RealizingComponentPorts(), this.getComponentPort(), this.getComponentPort_RealizedComponentPorts(), "realizingComponentPorts", null, 0, -1, ComponentPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentPortAllocationEClass, ComponentPortAllocation.class, "ComponentPortAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentPortAllocation_OwnedComponentPortAllocationEnds(), this.getComponentPortAllocationEnd(), null, "ownedComponentPortAllocationEnds", null, 0, -1, ComponentPortAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPortAllocation_AllocatedPort(), theInformationPackage.getPort(), null, "allocatedPort", null, 0, 1, ComponentPortAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPortAllocation_AllocatingPort(), theInformationPackage.getPort(), null, "allocatingPort", null, 0, 1, ComponentPortAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(componentPortAllocationEndEClass, ComponentPortAllocationEnd.class, "ComponentPortAllocationEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComponentPortAllocationEnd_Port(), theInformationPackage.getPort(), null, "port", null, 0, 1, ComponentPortAllocationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPortAllocationEnd_Part(), theCsPackage.getPart(), null, "part", null, 0, 1, ComponentPortAllocationEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComponentPortAllocationEnd_OwningComponentPortAllocation(), this.getComponentPortAllocation(), null, "owningComponentPortAllocation", null, 0, 1, ComponentPortAllocationEnd.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(functionalChainInvolvementLinkEClass, FunctionalChainInvolvementLink.class, "FunctionalChainInvolvementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementLink_ExchangeContext(), theCapellacorePackage.getConstraint(), null, "exchangeContext", null, 0, 1, FunctionalChainInvolvementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementLink_ExchangedItems(), theInformationPackage.getExchangeItem(), null, "exchangedItems", null, 0, -1, FunctionalChainInvolvementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementLink_Source(), this.getFunctionalChainInvolvementFunction(), null, "source", null, 0, 1, FunctionalChainInvolvementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementLink_Target(), this.getFunctionalChainInvolvementFunction(), null, "target", null, 0, 1, FunctionalChainInvolvementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(sequenceLinkEClass, SequenceLink.class, "SequenceLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getSequenceLink_Condition(), theCapellacorePackage.getConstraint(), null, "condition", null, 0, 1, SequenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceLink_Links(), this.getFunctionalChainInvolvementLink(), null, "links", null, 0, -1, SequenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceLink_Source(), this.getSequenceLinkEnd(), null, "source", null, 0, 1, SequenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getSequenceLink_Target(), this.getSequenceLinkEnd(), null, "target", null, 0, 1, SequenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(sequenceLinkEndEClass, SequenceLinkEnd.class, "SequenceLinkEnd", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(functionalChainInvolvementFunctionEClass, FunctionalChainInvolvementFunction.class, "FunctionalChainInvolvementFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks(), this.getFunctionalChainInvolvementLink(), null, "outgoingInvolvementLinks", null, 0, -1, FunctionalChainInvolvementFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getFunctionalChainInvolvementFunction_IncomingInvolvementLinks(), this.getFunctionalChainInvolvementLink(), null, "incomingInvolvementLinks", null, 0, -1, FunctionalChainInvolvementFunction.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(controlNodeEClass, ControlNode.class, "ControlNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getControlNode_Kind(), this.getControlNodeKind(), "kind", null, 0, 1, ControlNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(referenceHierarchyContextEClass, ReferenceHierarchyContext.class, "ReferenceHierarchyContext", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getReferenceHierarchyContext_SourceReferenceHierarchy(), this.getFunctionalChainReference(), null, "sourceReferenceHierarchy", null, 0, -1, ReferenceHierarchyContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getReferenceHierarchyContext_TargetReferenceHierarchy(), this.getFunctionalChainReference(), null, "targetReferenceHierarchy", null, 0, -1, ReferenceHierarchyContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(functionalChainKindEEnum, FunctionalChainKind.class, "FunctionalChainKind"); //$NON-NLS-1$
    addEEnumLiteral(functionalChainKindEEnum, FunctionalChainKind.SIMPLE);
    addEEnumLiteral(functionalChainKindEEnum, FunctionalChainKind.COMPOSITE);
    addEEnumLiteral(functionalChainKindEEnum, FunctionalChainKind.FRAGMENT);

    initEEnum(functionKindEEnum, FunctionKind.class, "FunctionKind"); //$NON-NLS-1$
    addEEnumLiteral(functionKindEEnum, FunctionKind.FUNCTION);
    addEEnumLiteral(functionKindEEnum, FunctionKind.DUPLICATE);
    addEEnumLiteral(functionKindEEnum, FunctionKind.GATHER);
    addEEnumLiteral(functionKindEEnum, FunctionKind.SELECT);
    addEEnumLiteral(functionKindEEnum, FunctionKind.SPLIT);
    addEEnumLiteral(functionKindEEnum, FunctionKind.ROUTE);

    initEEnum(componentExchangeKindEEnum, ComponentExchangeKind.class, "ComponentExchangeKind"); //$NON-NLS-1$
    addEEnumLiteral(componentExchangeKindEEnum, ComponentExchangeKind.UNSET);
    addEEnumLiteral(componentExchangeKindEEnum, ComponentExchangeKind.DELEGATION);
    addEEnumLiteral(componentExchangeKindEEnum, ComponentExchangeKind.ASSEMBLY);
    addEEnumLiteral(componentExchangeKindEEnum, ComponentExchangeKind.FLOW);

    initEEnum(componentPortKindEEnum, ComponentPortKind.class, "ComponentPortKind"); //$NON-NLS-1$
    addEEnumLiteral(componentPortKindEEnum, ComponentPortKind.STANDARD);
    addEEnumLiteral(componentPortKindEEnum, ComponentPortKind.FLOW);

    initEEnum(orientationPortKindEEnum, OrientationPortKind.class, "OrientationPortKind"); //$NON-NLS-1$
    addEEnumLiteral(orientationPortKindEEnum, OrientationPortKind.UNSET);
    addEEnumLiteral(orientationPortKindEEnum, OrientationPortKind.IN);
    addEEnumLiteral(orientationPortKindEEnum, OrientationPortKind.OUT);
    addEEnumLiteral(orientationPortKindEEnum, OrientationPortKind.INOUT);

    initEEnum(controlNodeKindEEnum, ControlNodeKind.class, "ControlNodeKind"); //$NON-NLS-1$
    addEEnumLiteral(controlNodeKindEEnum, ControlNodeKind.OR);
    addEEnumLiteral(controlNodeKindEEnum, ControlNodeKind.AND);
    addEEnumLiteral(controlNodeKindEEnum, ControlNodeKind.ITERATE);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.polarsys.org/kitalpha/emde/1.0.0/extension
    createExtensionAnnotations();
    // http://www.polarsys.org/kitalpha/ecore/documentation
    createDocumentationAnnotations();
    // http://www.polarsys.org/capella/semantic
    createSemanticAnnotations();
    // http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
    createMappingAnnotations();
    // http://www.polarsys.org/capella/2007/UML2Mapping
    createUML2MappingAnnotations();
    // http://www.polarsys.org/capella/2007/BusinessInformation
    createBusinessInformationAnnotations();
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
         "description", "FunctionalAnalysis aims at defining the system engineering usual functional breakdown and functional data flow language (close to the UML Activity machine and SysML Activity as Block, partially).\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CapellaCommon.ecore\r\nThis package depends on the model Information.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionalArchitectureEClass,
       source,
       new String[] {
         "description", "a base class supporting the definition of architectures stating the functional interactions between entities\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionPkg(),
       source,
       new String[] {
         "description", "the function packages contained in this functional architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchanges(),
       source,
       new String[] {
         "description", "the component exchanges contained directly under this functional architecture, e.g. exchanges between top level components\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalLinks(),
       source,
       new String[] {
         "description", "the exchange links contained directly under this functional architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalAllocations(),
       source,
       new String[] {
         "description", "the list of component <=> functions allocation links contained directly under this functional architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "description", "the list of component exchange realizations contained directly under this functional architecture\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionalBlockEClass,
       source,
       new String[] {
         "description", "a specialization of a generic modelling block, with added ability to hold allocation links to functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedFunctionalAllocation(),
       source,
       new String[] {
         "description", "allocation relationships between Functions and Blocks, that are owned by this Block\r\n[source: Capella study]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchanges(),
       source,
       new String[] {
         "description", "the connections associated with this block\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchangeCategories(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_FunctionalAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) the allocation links between this block, and the functions that are allocated to it.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_AllocatedFunctions(),
       source,
       new String[] {
         "description", "the list of functions allocated to this block\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_InExchangeLinks(),
       source,
       new String[] {
         "description", "the (functional) exchanges that have this block as their target/destination\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OutExchangeLinks(),
       source,
       new String[] {
         "description", "the (functional) exchanges that have this block as their source\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPkgEClass,
       source,
       new String[] {
         "description", "a base class for deriving packages aimed at containing functional entities (functions, exchanges between functions, ...)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "description", "the (functional) exchange links contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedExchanges(),
       source,
       new String[] {
         "description", "the exchanges specifications contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedExchangeSpecificationRealizations(),
       source,
       new String[] {
         "description", "the exchange realization links contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedCategories(),
       source,
       new String[] {
         "description", "the exchange categories (families) contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionSpecifications(),
       source,
       new String[] {
         "description", "the functions (specifications) included in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionSpecificationEClass,
       source,
       new String[] {
         "description", "a function specification is to a function what a classifier is to an instance : it characterizes the common properties that all function instances will share\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_InExchangeLinks(),
       source,
       new String[] {
         "description", "inbound exchange links\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_OutExchangeLinks(),
       source,
       new String[] {
         "description", "outbound exchange links\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_OwnedFunctionPorts(),
       source,
       new String[] {
         "description", "flow ports owned by functions instanciating this function specification\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
         "description", "(automatically computed) list of sub-specifications of this function specification \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeCategoryEClass,
       source,
       new String[] {
         "description", "defines a family of exchanges, all associated to a common applicative criteria\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "could for example be used to declare a grouping of all physical exchanges sharing the same communication medium\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeCategory_Exchanges(),
       source,
       new String[] {
         "description", "the list of functional exchanges that are part of this exchange category\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeLinkEClass,
       source,
       new String[] {
         "description", "a grouping of functional exchanges, all participating in the same applicative link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Exchanges(),
       source,
       new String[] {
         "description", "(automatically computed) the exchanges involved in this exchange link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_ExchangeContainmentLinks(),
       source,
       new String[] {
         "description", "the exchange containments that are part of this exchange link \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_OwnedExchangeContainments(),
       source,
       new String[] {
         "description", "the exchange containments that are owned by this exchange link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Sources(),
       source,
       new String[] {
         "description", "the functions that are at the starting point(s) of this exchange link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Destinations(),
       source,
       new String[] {
         "description", "the functions that are at the destination point(s) of this exchange link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeContainmentEClass,
       source,
       new String[] {
         "description", "a mediator class allowing to implement a referencing between an Exchange and an ExchangeLink\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Exchange(),
       source,
       new String[] {
         "description", "the exchange (specification) involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Link(),
       source,
       new String[] {
         "description", "the exchange link involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationEClass,
       source,
       new String[] {
         "description", "a high-level abstract class specifying a set of constraints that concrete exchanges might fulfill (e.g. implement this specification)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_ContainingLink(),
       source,
       new String[] {
         "description", "the exchange link associated with this exchange specification\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_Link(),
       source,
       new String[] {
         "description", "the exchange containment associated with this exchange specification\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_OutgoingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "description", "the realization links between exchange specifications, for which this exchange specification is the origin of the link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_IncomingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "description", "the realization links between exchange specifications, for which this exchange specification is the destination of the link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeSpecificationEClass,
       source,
       new String[] {
         "description", "a specialized version of an exchange specification, dedicated to specify exchanges between two functions of the system\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeSpecification_FunctionalExchanges(),
       source,
       new String[] {
         "description", "the functional exchanges that fulfill this specification\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainEClass,
       source,
       new String[] {
         "description", "A functional chain is a set of Functions, activated through an activation graph (or path) and carrying non functional properties such as latency, criticity level ... \r\nIt provides a high-level description of a contribution of the system, users or external entities to an operational capability.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "a functional chain is used highlight a specific path in the function flow, that is of particular interest in the context of the targeted application (performance constraint, safety path, ...)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/example_functional_chain.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_Kind(),
       source,
       new String[] {
         "description", "Defines the kind of this FunctionalChain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to FunctionalChainKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedFunctionalChainInvolvements(),
       source,
       new String[] {
         "description", "the list of involvement relationships owned by this functional chain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedFunctionalChainRealizations(),
       source,
       new String[] {
         "description", "the list of realization relationships owned by this functional chain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalChainInvolvements(),
       source,
       new String[] {
         "description", "(automatically computed) the list of involvement relationships included in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctions(),
       source,
       new String[] {
         "description", "(automatically computed) the functions involved in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalExchanges(),
       source,
       new String[] {
         "description", "(automatically computed) the functional exchanges involved in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedElements(),
       source,
       new String[] {
         "description", "(automatically computed) the list of model elements involved in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctions(),
       source,
       new String[] {
         "description", "(automatically computed) the functions involved in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctionalBlocks(),
       source,
       new String[] {
         "description", "(automatically computed) the functional blocks involved in this functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_AvailableInStates(),
       source,
       new String[] {
         "description", "the list of (system) states in which this functional chain is actually available\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilities(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceNodes(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceLinks(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum,
       source,
       new String[] {
         "description", "Enumeration of the different functional chains\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "simple functional chain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "composite functional chain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "fragment functional chain", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionalChainContainerEClass,
       source,
       new String[] {
         "description", "Base class for possible containers of functional chains (may be both functional or use case containers)\r\n[source: MBSD unified approach]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
         "description", "the functional chains associated to this function, e.g. functional chains that involve only sub-functions of this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementEClass,
       source,
       new String[] {
         "description", "specifies the involvement of a model element in a functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_NextFunctionalChainInvolvements(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionInputPortEClass,
       source,
       new String[] {
         "description", "an input interface of its owning function, to receive functional exchanges from other functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "It is necessary to create a function input port on a function, to be able to set this function as the receiving end of a functional exchange. Note however that the Capella tool automatically creates a function input port on the destination function, when a functional exchange is created.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/ports_exchanges.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionInputPort_IncomingExchangeItems(),
       source,
       new String[] {
         "description", "the exchange items that are declared as potential flowing into this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionOutputPortEClass,
       source,
       new String[] {
         "description", "an output interface of its owning function, to be the origin of functional exchanges towards other functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "It is necessary to create a function output port on a function, to be able to set this function as the origin of a functional exchange. Note however that the Capella tool automatically creates a function output port on the origin function, when a functional exchange is created.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/ports_exchanges.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingExchangeItems(),
       source,
       new String[] {
         "description", "the exchange items that are declared as potentially flowing out of this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAllocationEClass,
       source,
       new String[] {
         "description", "a base class for deriving allocation relationships between a function, and some other model element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentFunctionalAllocationEClass,
       source,
       new String[] {
         "description", "a allocation link between a function and a component\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Function(),
       source,
       new String[] {
         "description", "the function involved in this allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Block(),
       source,
       new String[] {
         "description", "the block involved in this allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainRealizationEClass,
       source,
       new String[] {
         "description", "an allocation link between two Functional Chains", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this link is typically generated by the Capella tool during automated transitions between design levels", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationRealizationEClass,
       source,
       new String[] {
         "description", "Base class for deriving specific realization links between exchange specifications and the model elements that realize them.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizedExchangeSpecification(),
       source,
       new String[] {
         "description", "the exchange specification that is being realized by the other (typically lower level) exchange specification involved in this link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizingExchangeSpecification(),
       source,
       new String[] {
         "description", "the exchange specification that performs the realization of the other exchange specification involved in this link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeRealizationEClass,
       source,
       new String[] {
         "description", "a realization link between a functional exchange, and the (typically higher level) functional exchange that it realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizedFunctionalExchange(),
       source,
       new String[] {
         "description", "the functional exchange that is being realized by the other functional exchange involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizingFunctionalExchange(),
       source,
       new String[] {
         "description", "the functional exchange that is realising the other functional exchange involved in this relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionRealizationEClass,
       source,
       new String[] {
         "description", "an allocation link between a function, and the function that it realizes\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this link is typically generated by the Capella tool during automated transitions between design levels", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatedFunction(),
       source,
       new String[] {
         "description", "the function that is being allocated by/from the other function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatingFunction(),
       source,
       new String[] {
         "description", "the function that allocates (to) the other function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
         "description", "an exchange between two functions of the system\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "a functional exchange is used between two functions whenever there is an interaction between these two functions, be it the providing of some data or just the transition of control from/to a function.", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/ports_exchanges.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangeSpecifications(),
       source,
       new String[] {
         "description", "the exchange specification(s) that this exchange complies to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_InvolvingFunctionalChains(),
       source,
       new String[] {
         "description", "the functional chains in which this exchange is involved\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangedItems(),
       source,
       new String[] {
         "description", "the exchange items that are carried along this functional exchange\r\n[source: Capella study]\r\n\r\nSpecifies the information items that may circulate on this information flow.\r\n[source: UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_AllocatingComponentExchanges(),
       source,
       new String[] {
         "description", "the component exchanges associated to this functional exchange, e.g. the exchanges between the components to which the source/destination of this functional exchange are allocated.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations(),
       source,
       new String[] {
         "description", "the allocation links between component exchanges and functional exchanges, that have this functional exchange as their destination\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) the realization links between functional exchanges, that have this functional exchange as their destination\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_OutgoingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "description", "the realization links between functional exchanges, that have this functional exchange as their origin\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_Categories(),
       source,
       new String[] {
         "description", "the exchange categories (families) to which this functional exchange belongs\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_OwnedFunctionalExchangeRealizations(),
       source,
       new String[] {
         "description", "the realization links between functional exchanges, that are owned by this functional exchange\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionEClass,
       source,
       new String[] {
         "description", "Specifies an operation or an action that is performed by an entity.\r\n\r\nA transformation of inputs to outputs that may include the creation, monitoring, modification or destruction of elements, or a null transformation.\r\n[source: SysML glossary for SysML v1.0]\r\n\r\nThis is an abstract base class for the derivation of specific function types at each design level\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "A function is an action, an operation or a service fulfilled by the system or by an actor when interacting with the system. Example: tune radio frequency, display radio name...", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctions(),
       source,
       new String[] {
         "description", "the functions that are owned (in terms of model structure) by this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctionRealizations(),
       source,
       new String[] {
         "description", "the function realisation links that are associated to this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctionalExchanges(),
       source,
       new String[] {
         "description", "the functional exchanges that are owned by this function, e.g. that have their source and destination on sub-functions of this function.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_SubFunctions(),
       source,
       new String[] {
         "description", "(automatically computed) the children functions of this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OutFunctionRealizations(),
       source,
       new String[] {
         "description", "function realization links that have this function as their origin\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InFunctionRealizations(),
       source,
       new String[] {
         "description", "the function realisation links that have this function as their destination\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_ComponentFunctionalAllocations(),
       source,
       new String[] {
         "description", "the mediator classes that implement the allocation of this function to/from components (blocks)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_AllocationBlocks(),
       source,
       new String[] {
         "description", "the blocks to/from which this function is allocated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_AvailableInStates(),
       source,
       new String[] {
         "description", "the list of (system) states in which this function is actually available\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilities(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingFunctionalChains(),
       source,
       new String[] {
         "description", "the functional chains that involve this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
         "description", "the state machine associated to this function\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedFunctionSpecification(),
       source,
       new String[] {
         "description", "the function specification with which this function complies\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPortEClass,
       source,
       new String[] {
         "description", "A port is an interaction point between a block or part and its environment that is connected with other ports via connectors\r\n[source: SysML specification v1.1]\r\n\r\nBase abstract class for actual port implementations\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RepresentedComponentPort(),
       source,
       new String[] {
         "description", "the ComponentPort that this function port represents\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum,
       source,
       new String[] {
         "description", "ConnectorKind is an enumeration of the following literal values:\r\n- assembly\r\nIndicates that the connector is an assembly connector.\r\n- delegation\r\nIndicates that the connector is a delegation connector.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Communication kind is not set\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Indicates that the connector is a delegation connector.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Indicates that the connector is an assembly connector.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "Describes a flow communication", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum,
       source,
       new String[] {
         "description", "ComponentPortKind is an enumeration of the following literal values:\r\nstandard:\r\nA port is an interaction point between a Block or sub-Block and its environment that supports Exchanges with other ports.\r\nflow:\r\nA FlowPorts is an interaction point through which input and/or output of items such as data, material, or energy may flow", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Describes a standard port : \r\nA port is an interaction point between a Block or sub-Block and its environment that supports Exchanges with other ports.\r\n[source: SysML glossary for SysML v1.0]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Describes a flow port : \r\nA FlowPorts is an interaction point through which input and/or output of items such as data, material, or energy may flow\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum,
       source,
       new String[] {
         "description", "ComponentPortKind is an enumeration of the following literal values:\r\nstandard:\r\nA port is an interaction point between a Block or sub-Block and its environment that supports Exchanges with other ports.\r\nflow:\r\nA FlowPorts is an interaction point through which input and/or output of items such as data, material, or energy may flow", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the port orientation is undefined", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the port represents an input of the component it is used in", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the port represents an output of the component it is used in", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "used when the port represents both an input and on output of the component it is used in", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEClass,
       source,
       new String[] {
         "description", "a specialized version of an exchange specification, dedicated to characterize exchanges between components\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "arcadia_description", "An Exchange is an interaction between some entities such as actors, the system, functions or components, which is likely to influence their behaviour. Example: tuning frequency, radio selection command..." //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Kind(),
       source,
       new String[] {
         "description", "Kind of the connection", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to ConnectionKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Oriented(),
       source,
       new String[] {
         "description", "describes the orientation of the connection. The connection can be oriented or not", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to OrientationConnectionKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_AllocatedFunctionalExchanges(),
       source,
       new String[] {
         "description", "the functional exchanges associated with this component exchange (e.g. the functional exchanges that happen between functions allocated to the two components involved in this component exchange)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_IncomingComponentExchangeRealizations(),
       source,
       new String[] {
         "description", "the component exchange realization links that have this component exchange as their destination\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) the component exchange realization links that have this component exchange as their source\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) the allocation links between functional exchanges and component exchanges, for which this component exchange is the source\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "description", "the allocation links between functional exchanges and component exchanges, owned by this component exchange\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "description", "the component exchange realization links that are owned by this component exchange\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
         "description", "the connection endpoints involved in this link (potentially, an arbitrary number of them can be present)\r\n[source: Capella study]\r\n\r\nA connector consists of at least two connector ends, each representing the participation of instances of the classifiers\r\ntyping the connectable elements attached to this end. The set of connector ends is ordered.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Categories(),
       source,
       new String[] {
         "description", "the exchange categories (families) to which this functional exchange belongs\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeAllocationEClass,
       source,
       new String[] {
         "description", "Mediator class implementing an allocation relationship, between a component exchange, and the element that allocates it\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocated(),
       source,
       new String[] {
         "description", "The connection being allocated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocator(),
       source,
       new String[] {
         "description", "The element that allocates the connection\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeAllocatorEClass,
       source,
       new String[] {
         "description", "Base class for elements that are intended to allocate to/from connections\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_OwnedComponentExchangeAllocations(),
       source,
       new String[] {
         "description", "the component exchanges allocations contained in this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_AllocatedComponentExchanges(),
       source,
       new String[] {
         "description", "(automatically computed) direct references to the component exchanges being allocated by this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeCategoryEClass,
       source,
       new String[] {
         "description", "defines a family of exchanges, all associated to a common applicative criteria\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "could for example be used to declare a grouping of all physical exchanges sharing the same communication medium\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeCategory_Exchanges(),
       source,
       new String[] {
         "description", "the list of functional exchanges that are part of this exchange category\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEndEClass,
       source,
       new String[] {
         "description", "an endpoint of a connection link\r\n\r\nA connector end is an endpoint of a connector, which attaches the connector to a connectable element. Each connector\r\nend is part of one connector.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
         "description", "the port to which this communication endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
         "description", "the part to which this connect endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeFunctionalExchangeAllocationEClass,
       source,
       new String[] {
         "description", "allocation link between a connection and a functional exchange\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange(),
       source,
       new String[] {
         "description", "the functional exchange involved in this allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange(),
       source,
       new String[] {
         "description", "the connection involved in this allocation relationship\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeRealizationEClass,
       source,
       new String[] {
         "description", "an allocation link between a connection, and another (typically lower level) connection that realizes it", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "this kind of link is typically generated automatically by the Capella tool when performing a transition between design levels", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatedComponentExchange(),
       source,
       new String[] {
         "description", "the connection that is being allocated by/from the other connection involved in this link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatingComponentExchange(),
       source,
       new String[] {
         "description", "the connection that is allocating that other connection involved in this link \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortEClass,
       source,
       new String[] {
         "description", "A component port is the unification of the standard port and the flow port.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_Orientation(),
       source,
       new String[] {
         "description", "the orientation of a component port. ", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "should be set only when the component port is a flow port", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_Kind(),
       source,
       new String[] {
         "description", "A component port is the unification of the standard port and the flow port.\r\nsee the ComponentPortKind enumeration.\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortAllocationEClass,
       source,
       new String[] {
         "description", "specific kind of allocation link, between two Ports.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_OwnedComponentPortAllocationEnds(),
       source,
       new String[] {
         "description", "the component port allocation endpoints involved in this link\r\n\r\nA connector consists of at least two connector ends, each representing the participation of instances of the classifiers\r\ntyping the connectable elements attached to this end. The set of connector ends is ordered.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "description", "the \"destination\" of the allocation link : the port that is being allocated by another port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "description", "the \"source\" of the allocation link : the port that is allocating the other port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortAllocationEndEClass,
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Port(),
       source,
       new String[] {
         "description", "the port to which this communication endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Part(),
       source,
       new String[] {
         "description", "the part to which this connect endpoint is attached\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
       source,
       new String[] {
         "description", "the ComponentPortAllocation link that contains this endpoint\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementLinkEClass,
       source,
       new String[] {
         "description", "specifies the involvement of a model element in form of link in a functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_ExchangedItems(),
       source,
       new String[] {
         "description", "the ExchangeItems carried by this Functional Chain Involvement Link", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (sequenceLinkEClass,
       source,
       new String[] {
         "description", "express precedence between executions of represented functions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (sequenceLinkEndEClass,
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementFunctionEClass,
       source,
       new String[] {
         "description", "specifies the involvement of a model element in form of function in a functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (controlNodeEClass,
       source,
       new String[] {
         "description", "used to control the flow of executions of represented functions in a functional chain\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getControlNode_Kind(),
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (controlNodeKindEEnum,
       source,
       new String[] {
         "description", "", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (referenceHierarchyContextEClass,
       source,
       new String[] {
         "description", "used to uniquely identify a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getReferenceHierarchyContext_SourceReferenceHierarchy(),
       source,
       new String[] {
         "description", "used to uniquely identify the source of a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getReferenceHierarchyContext_TargetReferenceHierarchy(),
       source,
       new String[] {
         "description", "used to uniquely identify the target of a link between involvement functions when their functional chain is referenced more than once.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAbstractFunctionalArchitecture_OwnedComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchangeCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchangeCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionPkg_OwnedCategories(),
       source,
       new String[] {
       });
    addAnnotation
      (exchangeCategoryEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeCategory_Exchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_OwnedFunctionalChainInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_AvailableInStates(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_FirstFunctionalChainInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilities(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_RealizedFunctionalChains(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_RealizingFunctionalChains(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_PreCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_PostCondition(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceNodes(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
       });
    addAnnotation
      (functionalChainInvolvementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvement_NextFunctionalChainInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvement_InvolvedElement(),
       source,
       new String[] {
       });
    addAnnotation
      (functionalChainReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainReference_ReferencedFunctionalChain(),
       source,
       new String[] {
       });
    addAnnotation
      (functionInputPortEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionInputPort_IncomingExchangeItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionInputPort_IncomingFunctionalExchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionOutputPortEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingExchangeItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingFunctionalExchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_InvolvingFunctionalChains(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangedItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_AllocatingComponentExchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_Categories(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_SourceFunctionOutputPort(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_TargetFunctionInputPort(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_RealizedFunctionalExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_RealizingFunctionalExchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_Condition(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctionalExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_AvailableInStates(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilities(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingFunctionalChains(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionPort_AllocatorComponentPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RealizedFunctionPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionPort_RealizingFunctionPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_Oriented(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_AllocatedFunctionalExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_SourcePort(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_SourcePart(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_TargetPort(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_TargetPart(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_Categories(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_AllocatorPhysicalLinks(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_RealizedComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_RealizingComponentExchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_AllocatedComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (componentExchangeCategoryEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchangeCategory_Exchanges(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEndEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
       });
    addAnnotation
      (componentPortEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_Orientation(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_ComponentExchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_AllocatedFunctionPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_DelegatedComponentPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_DelegatingComponentPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_AllocatingPhysicalPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_RealizedComponentPorts(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPort_RealizingComponentPorts(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_ExchangeContext(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_ExchangedItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_Source(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_Target(),
       source,
       new String[] {
       });
    addAnnotation
      (sequenceLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceLink_Condition(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceLink_Links(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceLink_Source(),
       source,
       new String[] {
       });
    addAnnotation
      (getSequenceLink_Target(),
       source,
       new String[] {
       });
    addAnnotation
      (sequenceLinkEndEClass,
       source,
       new String[] {
       });
    addAnnotation
      (functionalChainInvolvementFunctionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_IncomingInvolvementLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (controlNodeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (referenceHierarchyContextEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getReferenceHierarchyContext_SourceReferenceHierarchy(),
       source,
       new String[] {
       });
    addAnnotation
      (getReferenceHierarchyContext_TargetReferenceHierarchy(),
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
   * Initializes the annotations for <b>http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping</b>.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createMappingAnnotations() {
    String source = "http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping"; //$NON-NLS-1$
    addAnnotation
      (abstractFunctionalArchitectureEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionPkg(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which FunctionPkg stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentExchange stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ExchangeLink stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentFunctionalAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ComponentExchangeRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionalBlockEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedFunctionalAllocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which ComponentFunctionalAllocation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "In uml::Element::nearestPackage, exchanges between two elements contained by this block. Thoses exchanges are packaged elements on which ComponentExchange stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_FunctionalAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_AllocatedFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_InExchangeLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OutExchangeLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ExchangeLink stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which FunctionalExchangeSpecification stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedExchangeSpecificationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ExchangeSpecificationRealisation stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedCategories(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which ExchangeCategory stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionSpecifications(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which FunctionSpecification stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionSpecificationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Activity", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "cannot be mapped to uml::Component since it is not part of UML4SysML", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_InExchangeLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_OutExchangeLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_OwnedFunctionPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::StructuredClassifier::ownedAttribute", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::StructuredClassifier::ownedAttribute elements on which FlowPort stereotype or any stereotype that inherits from it is applied\r\nOrder will not be preserved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeCategoryEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeCategory_Exchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::InformationFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Exchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_ExchangeContainmentLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which ExchangeContainment stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_OwnedExchangeContainments(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which ExchangeContainment stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Sources(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::informationSource", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Destinations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::InformationFlow::informationTarget", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeContainmentEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Exchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Link(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::client", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::InformationFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_ContainingLink(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_Link(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_OutgoingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_IncomingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeSpecificationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::InformationFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeSpecification_FunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedFunctionalChainInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency::keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which FunctionalChain stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedFunctionalChainRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency::keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which FunctionalChainInvolvement stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalChainInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedElements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctionalBlocks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_AvailableInStates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_FirstFunctionalChainInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_RealizedFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_RealizingFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceNodes(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_OwnedSequenceLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionalChainContainerEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Class::nestedClassifier elements on which FunctionalChain stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_NextFunctionalChainInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_InvolvedElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainReference_ReferencedFunctionalChain(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionInputPortEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ActivityParameterNode", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "use ActivityParameterNodes, delegation will add uml::InputPin on callBeahviorAction\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionInputPort_IncomingExchangeItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionInputPort_IncomingFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionOutputPortEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ActivityParameterNode", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "use ActivityParameterNodes, delegation will add uml::OutputPin on call BehaviorAction", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingExchangeItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentFunctionalAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Function(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Block(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizedExchangeSpecification(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizingExchangeSpecification(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizedFunctionalExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizingFunctionalExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatedFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatingFunction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ObjectFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangeSpecifications(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_InvolvingFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangedItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_AllocatingComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_OutgoingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_Categories(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_OwnedFunctionalExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which FunctionalExchangeRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_SourceFunctionOutputPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_TargetFunctionInputPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_RealizedFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_RealizingFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Activity", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_Condition(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "the nesting relation is not representing the hierarchy of functions, but helps storing the functions in a structured way", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctionRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which FunctionRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OwnedFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Activity::edge", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Activity::edge elements on which FunctionalExchange stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_SubFunctions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OutFunctionRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InFunctionRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_ComponentFunctionalAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_AllocationBlocks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_AvailableInStates(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilities(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingFunctionalChains(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedFunctionSpecification(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPortEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RepresentedComponentPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_AllocatorComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RealizedFunctionPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RealizingFunctionPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorKind", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This value does not exist for uml::ConnectorKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorKind::delegation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorKind::assembly", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This value does not exist for uml::ConnectorKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (orientationPortKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::InformationFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Oriented(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_AllocatedFunctionalExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_IncomingComponentExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which ComponentFunctionalExchangeAllocation stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which ComponentExchangeRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Connector::end", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_SourcePort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_SourcePart(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_TargetPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_TargetPart(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Categories(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_AllocatorPhysicalLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_RealizedComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_RealizingComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocated(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocator(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeAllocatorEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_OwnedComponentExchangeAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which ConnectionAllocation stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_AllocatedComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeCategoryEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeCategory_Exchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ConnectorEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::role", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::ConnectorEnd::role elements on which StandardPort stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeFunctionalExchangeAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatedComponentExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatingComponentExchange(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_Orientation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_ComponentExchanges(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_AllocatedFunctionPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_DelegatedComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_DelegatingComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_AllocatingPhysicalPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_RealizedComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_RealizingComponentPorts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_OwnedComponentPortAllocationEnds(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Connector::end", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortAllocationEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::ConnectorEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Port(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::role", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::ConnectorEnd::role elements on which PhysicalPort stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Part(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ConnectorEnd::partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Opposite reference of uml::Connector::end", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementLink_ExchangedItems(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (sequenceLinkEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (sequenceLinkEndEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainInvolvementFunctionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_IncomingInvolvementLinks(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (controlNodeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getControlNode_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (controlNodeKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (referenceHierarchyContextEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getReferenceHierarchyContext_SourceReferenceHierarchy(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getReferenceHierarchyContext_TargetReferenceHierarchy(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAbstractFunctionalArchitecture_OwnedFunctionPkg(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchanges(),
       source,
       new String[] {
         "featureName", "ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalLinks(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedComponentExchangeRealizations(),
       source,
       new String[] {
         "featureName", "ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OwnedComponentExchanges(),
       source,
       new String[] {
         "featureName", "ownedConnector", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuredClassifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.sys.FunctionalAnalysis" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionSpecificationEClass,
       source,
       new String[] {
         "metaclass", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeLinkEClass,
       source,
       new String[] {
         "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.sys.FunctionalLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_ExchangeContainmentLinks(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_OwnedExchangeContainments(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Component" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Sources(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Destinations(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeContainmentEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.ExchangeContainment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Exchange(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Link(),
       source,
       new String[] {
         "featureName", "client", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_Link(),
       source,
       new String[] {
         "umlOppositeReference", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeSpecificationEClass,
       source,
       new String[] {
         "metaclass", "Connector", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Exchange" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainEClass,
       source,
       new String[] {
         "metaclass", "StructuredActivityNode", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.FunctionalChain" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAllocationEClass,
       source,
       new String[] {
         "metaclass", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentFunctionalAllocationEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.FunctionAllocationToLogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationRealizationEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.FunctionAllocationToLogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
         "metaclass", "ObjectFlow", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Transition" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionEClass,
       source,
       new String[] {
         "metaclass", "OpaqueAction", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Action" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum,
       source,
       new String[] {
         "enum", "ConnectorKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "DELEGATION" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "ASSEMBLY" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
         "featureName", "end", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Connector" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
         "featureName", "role", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
         "featureName", "partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Port(),
       source,
       new String[] {
         "featureName", "role", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Part(),
       source,
       new String[] {
         "featureName", "partWithPort", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "ConnectorEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
       source,
       new String[] {
         "umlOppositeReference", "end", //$NON-NLS-1$ //$NON-NLS-2$
         "umlOppositeReferenceOwner", "Connector" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAbstractFunctionalArchitecture_OwnedFunctionPkg(),
       source,
       new String[] {
         "Label", "ownedFunctionPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalLinks(),
       source,
       new String[] {
         "Label", "ownedFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_InExchangeLinks(),
       source,
       new String[] {
         "Label", "inFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_OutExchangeLinks(),
       source,
       new String[] {
         "Label", "outFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPkgEClass,
       source,
       new String[] {
         "Label", "FunctionalAnalysis" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
         "Label", "ownedFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionSpecificationEClass,
       source,
       new String[] {
         "Label", "Function Specification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_InExchangeLinks(),
       source,
       new String[] {
         "Label", "inFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_OutExchangeLinks(),
       source,
       new String[] {
         "Label", "outFunctionalLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
         "Label", "subFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeLinkEClass,
       source,
       new String[] {
         "Label", "FunctionalLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Exchanges(),
       source,
       new String[] {
         "Label", "exchanges" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_ExchangeContainmentLinks(),
       source,
       new String[] {
         "Label", "exchangeContainmentLinks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_OwnedExchangeContainments(),
       source,
       new String[] {
         "Label", "ownedExchangeContainments" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Sources(),
       source,
       new String[] {
         "Label", "sources" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Destinations(),
       source,
       new String[] {
         "Label", "destinations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeContainmentEClass,
       source,
       new String[] {
         "Label", "ExchangeContainment" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Exchange(),
       source,
       new String[] {
         "Label", "exchange" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeContainment_Link(),
       source,
       new String[] {
         "Label", "link" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_ContainingLink(),
       source,
       new String[] {
         "Label", "containingLink" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_Link(),
       source,
       new String[] {
         "Label", "link" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeSpecificationEClass,
       source,
       new String[] {
         "Label", "Functional Exchange Specification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalChainEClass,
       source,
       new String[] {
         "Label", "FunctionalChain" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
         "Label", "ownedFunctionalChains" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionAllocationEClass,
       source,
       new String[] {
         "Label", "FunctionAllocation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentFunctionalAllocationEClass,
       source,
       new String[] {
         "Label", "FunctionAllocationToLogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeSpecificationRealizationEClass,
       source,
       new String[] {
         "Label", "FunctionAllocationToLogicalComponent" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeRealizationEClass,
       source,
       new String[] {
         "Label", "Functional Exchange Realization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
         "Label", "Transition" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_ExchangeSpecifications(),
       source,
       new String[] {
         "Label", "exchanges" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractFunctionEClass,
       source,
       new String[] {
         "Label", "Action" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
         "Label", "linkedFunction" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedFunctionSpecification(),
       source,
       new String[] {
         "Label", "linkedFunctionSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (functionPortEClass,
       source,
       new String[] {
         "Label", "Function Port" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeKindEEnum,
       source,
       new String[] {
         "Label", "ConnectionKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentPortKindEEnum,
       source,
       new String[] {
         "Label", "ComponentPortKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
         "Label", "ownedConnectionEnds" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeEndEClass,
       source,
       new String[] {
         "Label", "ConnectionEnd" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
         "Label", "port" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
         "Label", "part" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeFunctionalExchangeAllocationEClass,
       source,
       new String[] {
         "Label", "Component Functional Exchange Allocation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (componentExchangeRealizationEClass,
       source,
       new String[] {
         "Label", "Connection Realization" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Port(),
       source,
       new String[] {
         "Label", "port" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_Part(),
       source,
       new String[] {
         "Label", "part" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
       source,
       new String[] {
         "Label", "owningComponentPortAllocation" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAbstractFunctionalArchitecture_OwnedFunctionPkg(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalArchitecture_OwnedFunctionalLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalBlock_InExchangeLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalBlock_OutExchangeLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionPkg_OwnedFunctionalLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionSpecification_InExchangeLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionSpecification_OutExchangeLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeLink_Exchanges(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeLink_ExchangeContainmentLinks(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeLink_OwnedExchangeContainments(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeLink_Sources(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeLink_Destinations(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeContainment_Exchange(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeContainment_Link(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeSpecification_ContainingLink(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeSpecification_Link(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunctionalChainContainer_OwnedFunctionalChains(),
       source,
       new String[] {
       });
    addAnnotation
      (getFunctionalExchange_ExchangeSpecifications(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractFunction_LinkedFunctionSpecification(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchange_OwnedComponentExchangeEnds(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchangeEnd_Port(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentExchangeEnd_Part(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPortAllocationEnd_Port(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPortAllocationEnd_Part(),
       source,
       new String[] {
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
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
      (getAbstractFunctionalBlock_FunctionalAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunctionalBlock_AllocatedFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "functionalAllocations.function" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionSpecification_SubFunctionSpecifications(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionSpecification.ownedNodes(self, af);\r\nAbstractFunction.linkedFunctionSpecification(af, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeLink_Exchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedExchangeContainments.exchange" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_ContainingLink(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "link.link" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_OutgoingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecification_IncomingExchangeSpecificationRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeSpecification_FunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "realizations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalChainInvolvements(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFunctionalChainInvolvements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involvedElements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involvedElements" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvedElements(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involvedFunctionalChainInvolvements.involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctions(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involvedFunctions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_EnactedFunctionalBlocks(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "enactedFunctions.allocationBlocks" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_FirstFunctionalChainInvolvements(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern FunctionalChain__firstFunctionalChainInvolvements(self : FunctionalChain, target : FunctionalChainInvolvement) {\r\n\tFunctionalChain.ownedFunctionalChainInvolvements(self, target);\r\n\tFunctionalChainInvolvement.involved(target, _);\r\n\tneg find _PreviousInvolvement(target, _);\r\n}\r\nprivate pattern _PreviousInvolvement(fci : FunctionalChainInvolvement, previous : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvement.previousFunctionalChainInvolvements(fci, previous);\r\n}\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalChain.involvingInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.capability(fcaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalChain.involvingInvolvements(self, fcaci);\r\nFunctionalChainAbstractCapabilityInvolvement.capability(fcaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_RealizedFunctionalChains(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalChainRealization.sourceElement(fcr, self);\r\nFunctionalChainRealization.targetElement(fcr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChain_RealizingFunctionalChains(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalChainRealization.targetElement(fcr, self);\r\nFunctionalChainRealization.sourceElement(fcr, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_NextFunctionalChainInvolvements(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern FunctionalChainInvolvement_nextFunctionalChainInvolvements(self : FunctionalChainInvolvement, target : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvementLink.source(target, self);\r\n} or {\r\n\tFunctionalChainInvolvementLink.target(self, target);\r\n}" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_PreviousFunctionalChainInvolvements(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern FunctionalChainInvolvement__previousFunctionalChainInvolvements(self : FunctionalChainInvolvement, target : FunctionalChainInvolvement) {\r\n\tFunctionalChainInvolvementLink.target(target, self);\r\n} or {\r\n\tFunctionalChainInvolvementLink.source(self, target);\r\n}" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvement_InvolvedElement(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainReference_ReferencedFunctionalChain(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "involved" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionInputPort_IncomingFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incoming" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionOutputPort_OutgoingFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoing" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Function(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentFunctionalAllocation_Block(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizedExchangeSpecification(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeSpecificationRealization_RealizingExchangeSpecification(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizedFunctionalExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchangeRealization_RealizingFunctionalExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatedFunction(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionRealization_AllocatingFunction(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_InvolvingFunctionalChains(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalExchange.involvingInvolvements(self, fci);\r\nFunctionalChainInvolvement.involver(fci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_AllocatingComponentExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingComponentExchangeFunctionalExchangeRealizations.allocatingComponentExchange" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingComponentExchangeFunctionalExchangeRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_IncomingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_OutgoingFunctionalExchangeRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_Categories(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "exchanges" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_SourceFunctionOutputPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "source" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_TargetFunctionInputPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "target" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_RealizedFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalExchangeRealization.sourceElement(fer, self);\r\nFunctionalExchangeRealization.targetElement(fer, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalExchange_RealizingFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "FunctionalExchangeRealization.targetElement(fer, self);\r\nFunctionalExchangeRealization.sourceElement(fer, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_SubFunctions(),
       source,
       new String[] {
         "viatra.variant", "freeform", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "pattern AbstractFunction__subFunctions(self : AbstractFunction, target : AbstractFunction) {\r\n\t// sub function directly in function\r\n\tAbstractFunction.ownedFunctions(self, target);\r\n} or { // sub function in function first level package\r\n\tfind _AbstractFunction__ownedFunctionPkgs(self, pkg);\r\n\tfind _FunctionPkg__ownedFunctions(pkg, target);\r\n}\r\nor { // sub function in function first level package sub packages\r\n\tfind _AbstractFunction__ownedFunctionPkgs(self, pkg);\r\n\tfind _FunctionPkg__ownedFunctionPkgs+(pkg, subpkg);\r\n\tfind _FunctionPkg__ownedFunctions(subpkg, target);\r\n}\r\n\r\nprivate pattern _AbstractFunction__ownedFunctionPkgs(af : AbstractFunction, ownedpkg : FunctionPkg) {\r\n\tOperationalActivity.ownedOperationalActivityPkgs(af, ownedpkg);\r\n} or {\r\n\tSystemFunction.ownedSystemFunctionPkgs(af, ownedpkg);\r\n} or {\r\n\tLogicalFunction.ownedLogicalFunctionPkgs(af, ownedpkg);\r\n} or {\r\n\tPhysicalFunction.ownedPhysicalFunctionPkgs(af, ownedpkg);\r\n}\r\n\r\nprivate pattern _FunctionPkg__ownedFunctionPkgs(pkg : FunctionPkg, ownedpkg : FunctionPkg) {\r\n\tOperationalActivityPkg.ownedOperationalActivityPkgs(pkg, ownedpkg);\r\n} or {\r\n\tSystemFunctionPkg.ownedSystemFunctionPkgs(pkg, ownedpkg);\r\n} or {\r\n\tLogicalFunctionPkg.ownedLogicalFunctionPkgs(pkg, ownedpkg);\r\n} or {\r\n\tPhysicalFunctionPkg.ownedPhysicalFunctionPkgs(pkg, ownedpkg);\r\n}\r\n\r\nprivate pattern _FunctionPkg__ownedFunctions(pkg : FunctionPkg, af : AbstractFunction) {\r\n\tOperationalActivityPkg.ownedOperationalActivities(pkg, af);\r\n} or {\r\n\tSystemFunctionPkg.ownedSystemFunctions(pkg, af);\r\n} or {\r\n\tLogicalFunctionPkg.ownedLogicalFunctions(pkg, af);\r\n} or {\r\n\tPhysicalFunctionPkg.ownedPhysicalFunctions(pkg, af);\r\n}\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_OutFunctionRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InFunctionRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_ComponentFunctionalAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_AllocationBlocks(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "componentFunctionalAllocations.block" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilities(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractFunction.involvingInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.capability(afaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingCapabilityRealizations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractFunction.involvingInvolvements(self, afaci);\r\nAbstractFunctionAbstractCapabilityInvolvement.capability(afaci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_InvolvingFunctionalChains(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "AbstractFunction.involvingInvolvements(self, fci);\r\nFunctionalChainInvolvement.involver(fci, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedStateMachine(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "behavior" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractFunction_LinkedFunctionSpecification(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "behavior" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_AllocatorComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingPortAllocations.allocatingPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RealizedFunctionPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingPortRealizations.realizedPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionPort_RealizingFunctionPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingPortRealizations.realizingPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_AllocatedFunctionalExchanges(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingComponentExchangeFunctionalExchangeAllocations.allocatedFunctionalExchange" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_IncomingComponentExchangeRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_OutgoingComponentExchangeFunctionalExchangeAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_SourcePort(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.source(self, target);\r\n} or {\r\n\tComponentExchange.source(self, cee);\r\n\tComponentExchangeEnd.port(cee, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_SourcePart(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.source(self, target);\r\n} or {\r\n\tComponentExchange.source(self, cee);\r\n\tComponentExchangeEnd.part(cee, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_TargetPort(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.target(self, target);\r\n} or {\r\n\tComponentExchange.target(self, cee);\r\n\tComponentExchangeEnd.port(cee, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_TargetPart(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.target(self, target);\r\n} or {\r\n\tComponentExchange.target(self, cee);\r\n\tComponentExchangeEnd.part(cee, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_Categories(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "exchanges" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_AllocatorPhysicalLinks(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.incomingTraces(self, cea);\r\nComponentExchangeAllocation.componentExchangeAllocator(cea, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_RealizedComponentExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.outgoingTraces(self, cer);\r\nComponentExchangeRealization.allocatedComponentExchange(cer, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchange_RealizingComponentExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchange.incomingTraces(self, cer);\r\nComponentExchangeRealization.allocatingComponentExchange(cer, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocated(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocation_ComponentExchangeAllocator(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeAllocator_AllocatedComponentExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentExchangeAllocator.outgoingTraces(self, cea);\r\nComponentExchangeAllocation.componentExchangeAllocated(cea, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatedFunctionalExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeFunctionalExchangeAllocation_AllocatingComponentExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatedComponentExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentExchangeRealization_AllocatingComponentExchange(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_ComponentExchanges(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentPort.informationFlows(self, target);\r\n} or {\r\n\tComponentExchangeEnd.port(cee, self);\r\n\tComponentExchange.ownedComponentExchangeEnds(target, cee);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_AllocatedFunctionPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingPortAllocations.allocatedPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_DelegatedComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentPort.outgoingInformationFlows(self, ce);\r\nComponentExchange.kind(ce, ::DELEGATION);\r\nComponentExchange.targetPort(ce, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_DelegatingComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentPort.incomingInformationFlows(self, ce);\r\nComponentExchange.kind(ce, ::DELEGATION);\r\nComponentExchange.sourcePort(ce, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_AllocatingPhysicalPorts(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ComponentPort.incomingTraces(self, cpa);\r\nComponentPortAllocation.allocatingPort(cpa, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_RealizedComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingPortRealizations.realizedPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPort_RealizingComponentPorts(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingPortRealizations.realizingPort" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComponentPortAllocationEnd_OwningComponentPortAllocation(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedComponentPortAllocationEnds" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_OutgoingInvolvementLinks(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "source" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getFunctionalChainInvolvementFunction_IncomingInvolvementLinks(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "target" //$NON-NLS-1$ //$NON-NLS-2$
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
      (exchangeLinkEClass,
       source,
       new String[] {
       });
    addAnnotation
      (exchangeContainmentEClass,
       source,
       new String[] {
       });
    addAnnotation
      (functionalExchangeSpecificationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (abstractFunctionAllocationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (componentFunctionalAllocationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (exchangeSpecificationRealizationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (functionalExchangeEClass,
       source,
       new String[] {
       });
  }

} //FaPackageImpl
