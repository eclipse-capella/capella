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
package org.polarsys.capella.core.data.information.impl;

import static org.polarsys.capella.core.data.information.InformationPackage.CLASS;

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
import org.polarsys.capella.core.data.information.AbstractCollectionValue;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionKind;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.DomainElement;
import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.information.ExchangeItemRealization;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.MultiplicityElement;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.OperationAllocation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.ParameterDirection;
import org.polarsys.capella.core.data.information.PassingMode;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortAllocation;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.SynchronismKind;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.UnionKind;
import org.polarsys.capella.core.data.information.UnionProperty;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.communication.impl.CommunicationPackageImpl;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.impl.DatatypePackageImpl;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.impl.DatavaluePackageImpl;
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
public class InformationPackageImpl extends EPackageImpl implements InformationPackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractInstanceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass associationPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass associationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass classEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractCollectionValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass collectionValueReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass dataPkgEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass domainElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass keyPartEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass multiplicityElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass operationAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass parameterEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass propertyEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass serviceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unionPropertyEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unitEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass portEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass portRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass portAllocationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeItemEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeItemElementEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeItemInstanceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass informationRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass exchangeItemRealizationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractEventOperationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum aggregationKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum parameterDirectionEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum passingModeEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum synchronismKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum unionKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum exchangeMechanismEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum elementKindEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum collectionKindEEnum = null;

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
   * @see org.polarsys.capella.core.data.information.InformationPackage#eNS_URI
   * @see #init()
   * @generated
   */
	private InformationPackageImpl() {
    super(eNS_URI, InformationFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link InformationPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static InformationPackage init() {
    if (isInited) return (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);

    // Obtain or create and register package
    Object registeredInformationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    InformationPackageImpl theInformationPackage = registeredInformationPackage instanceof InformationPackageImpl ? (InformationPackageImpl)registeredInformationPackage : new InformationPackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theInformationPackage.createPackageContents();
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
    theCommunicationPackage.createPackageContents();
    theDatatypePackage.createPackageContents();
    theDatavaluePackage.createPackageContents();
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theInformationPackage.initializePackageContents();
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
    theCommunicationPackage.initializePackageContents();
    theDatatypePackage.initializePackageContents();
    theDatavaluePackage.initializePackageContents();
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theInformationPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(InformationPackage.eNS_URI, theInformationPackage);
    return theInformationPackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractInstance() {
    return abstractInstanceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractInstance_RepresentingInstanceRoles() {
    return (EReference)abstractInstanceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAssociationPkg() {
    return associationPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAssociationPkg_Visibility() {
    return (EAttribute)associationPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAssociationPkg_OwnedAssociations() {
    return (EReference)associationPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAssociation() {
    return associationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAssociation_OwnedMembers() {
    return (EReference)associationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAssociation_NavigableMembers() {
    return (EReference)associationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getClass_() {
    return classEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getClass_IsPrimitive() {
    return (EAttribute)classEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_KeyParts() {
    return (EReference)classEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_OwnedStateMachines() {
    return (EReference)classEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_OwnedDataValues() {
    return (EReference)classEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_OwnedInformationRealizations() {
    return (EReference)classEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_RealizedClasses() {
    return (EReference)classEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getClass_RealizingClasses() {
    return (EReference)classEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCollection() {
    return collectionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCollection_IsPrimitive() {
    return (EAttribute)collectionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCollection_Visibility() {
    return (EAttribute)collectionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCollection_Kind() {
    return (EAttribute)collectionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getCollection_AggregationKind() {
    return (EAttribute)collectionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollection_Type() {
    return (EReference)collectionEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollection_Index() {
    return (EReference)collectionEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollection_ContainedOperations() {
    return (EReference)collectionEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractCollectionValue() {
    return abstractCollectionValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCollectionValue() {
    return collectionValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollectionValue_OwnedElements() {
    return (EReference)collectionValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollectionValue_OwnedDefaultElement() {
    return (EReference)collectionValueEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getCollectionValueReference() {
    return collectionValueReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollectionValueReference_ReferencedValue() {
    return (EReference)collectionValueReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getCollectionValueReference_ReferencedProperty() {
    return (EReference)collectionValueReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDataPkg() {
    return dataPkgEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedDataPkgs() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedClasses() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedKeyParts() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedCollections() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedUnits() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedDataTypes() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedSignals() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedMessages() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedExceptions() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataPkg_OwnedStateEvents() {
    return (EReference)dataPkgEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDomainElement() {
    return domainElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getKeyPart() {
    return keyPartEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getKeyPart_Property() {
    return (EReference)keyPartEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getMultiplicityElement() {
    return multiplicityElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getMultiplicityElement_Ordered() {
    return (EAttribute)multiplicityElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getMultiplicityElement_Unique() {
    return (EAttribute)multiplicityElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getMultiplicityElement_MinInclusive() {
    return (EAttribute)multiplicityElementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getMultiplicityElement_MaxInclusive() {
    return (EAttribute)multiplicityElementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedDefaultValue() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMinValue() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMaxValue() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedNullValue() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMinCard() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMinLength() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMaxCard() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(10);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getMultiplicityElement_OwnedMaxLength() {
    return (EReference)multiplicityElementEClass.getEStructuralFeatures().get(11);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getOperation() {
    return operationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_OwnedParameters() {
    return (EReference)operationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_AllocatingOperations() {
    return (EReference)operationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_AllocatedOperations() {
    return (EReference)operationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_OwnedOperationAllocation() {
    return (EReference)operationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_OwnedExchangeItemRealizations() {
    return (EReference)operationEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperation_RealizedExchangeItems() {
    return (EReference)operationEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getOperationAllocation() {
    return operationAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperationAllocation_AllocatedOperation() {
    return (EReference)operationAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getOperationAllocation_AllocatingOperation() {
    return (EReference)operationAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getParameter() {
    return parameterEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getParameter_Direction() {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getParameter_PassingMode() {
    return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getProperty() {
    return propertyEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getProperty_AggregationKind() {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getProperty_IsDerived() {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getProperty_IsReadOnly() {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getProperty_IsPartOfKey() {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getProperty_Association() {
    return (EReference)propertyEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getService() {
    return serviceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getService_SynchronismKind() {
    return (EAttribute)serviceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getService_ThrownExceptions() {
    return (EReference)serviceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getService_Messages() {
    return (EReference)serviceEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getService_MessageReferences() {
    return (EReference)serviceEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getUnion() {
    return unionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getUnion_Kind() {
    return (EAttribute)unionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getUnion_Discriminant() {
    return (EReference)unionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getUnion_DefaultProperty() {
    return (EReference)unionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getUnion_ContainedUnionProperties() {
    return (EReference)unionEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getUnionProperty() {
    return unionPropertyEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getUnionProperty_Qualifier() {
    return (EReference)unionPropertyEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getUnit() {
    return unitEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPort() {
    return portEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_IncomingPortRealizations() {
    return (EReference)portEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_OutgoingPortRealizations() {
    return (EReference)portEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_OwnedProtocols() {
    return (EReference)portEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_IncomingPortAllocations() {
    return (EReference)portEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_OutgoingPortAllocations() {
    return (EReference)portEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_ProvidedInterfaces() {
    return (EReference)portEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_RequiredInterfaces() {
    return (EReference)portEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_OwnedPortRealizations() {
    return (EReference)portEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPort_OwnedPortAllocations() {
    return (EReference)portEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPortRealization() {
    return portRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPortRealization_RealizedPort() {
    return (EReference)portRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPortRealization_RealizingPort() {
    return (EReference)portRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPortAllocation() {
    return portAllocationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPortAllocation_AllocatedPort() {
    return (EReference)portAllocationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPortAllocation_AllocatingPort() {
    return (EReference)portAllocationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeItem() {
    return exchangeItemEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItem_ExchangeMechanism() {
    return (EAttribute)exchangeItemEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_OwnedElements() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_OwnedInformationRealizations() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_OwnedExchangeItemInstances() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_AllocatorInterfaces() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_RealizedExchangeItems() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_RealizingExchangeItems() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItem_RealizingOperations() {
    return (EReference)exchangeItemEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeItemElement() {
    return exchangeItemElementEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItemElement_Kind() {
    return (EAttribute)exchangeItemElementEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItemElement_Direction() {
    return (EAttribute)exchangeItemElementEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getExchangeItemElement_Composite() {
    return (EAttribute)exchangeItemElementEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItemElement_ReferencedProperties() {
    return (EReference)exchangeItemElementEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeItemInstance() {
    return exchangeItemInstanceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getInformationRealization() {
    return informationRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getExchangeItemRealization() {
    return exchangeItemRealizationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItemRealization_RealizedItem() {
    return (EReference)exchangeItemRealizationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getExchangeItemRealization_RealizingOperation() {
    return (EReference)exchangeItemRealizationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractEventOperation() {
    return abstractEventOperationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractEventOperation_InvokingSequenceMessages() {
    return (EReference)abstractEventOperationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getAggregationKind() {
    return aggregationKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getParameterDirection() {
    return parameterDirectionEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getPassingMode() {
    return passingModeEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getSynchronismKind() {
    return synchronismKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getUnionKind() {
    return unionKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getExchangeMechanism() {
    return exchangeMechanismEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getElementKind() {
    return elementKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getCollectionKind() {
    return collectionKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public InformationFactory getInformationFactory() {
    return (InformationFactory)getEFactoryInstance();
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
    abstractInstanceEClass = createEClass(ABSTRACT_INSTANCE);
    createEReference(abstractInstanceEClass, ABSTRACT_INSTANCE__REPRESENTING_INSTANCE_ROLES);

    associationPkgEClass = createEClass(ASSOCIATION_PKG);
    createEAttribute(associationPkgEClass, ASSOCIATION_PKG__VISIBILITY);
    createEReference(associationPkgEClass, ASSOCIATION_PKG__OWNED_ASSOCIATIONS);

    associationEClass = createEClass(ASSOCIATION);
    createEReference(associationEClass, ASSOCIATION__OWNED_MEMBERS);
    createEReference(associationEClass, ASSOCIATION__NAVIGABLE_MEMBERS);

    classEClass = createEClass(CLASS);
    createEAttribute(classEClass, CLASS__IS_PRIMITIVE);
    createEReference(classEClass, CLASS__KEY_PARTS);
    createEReference(classEClass, CLASS__OWNED_STATE_MACHINES);
    createEReference(classEClass, CLASS__OWNED_DATA_VALUES);
    createEReference(classEClass, CLASS__OWNED_INFORMATION_REALIZATIONS);
    createEReference(classEClass, CLASS__REALIZED_CLASSES);
    createEReference(classEClass, CLASS__REALIZING_CLASSES);

    collectionEClass = createEClass(COLLECTION);
    createEAttribute(collectionEClass, COLLECTION__IS_PRIMITIVE);
    createEAttribute(collectionEClass, COLLECTION__VISIBILITY);
    createEAttribute(collectionEClass, COLLECTION__KIND);
    createEAttribute(collectionEClass, COLLECTION__AGGREGATION_KIND);
    createEReference(collectionEClass, COLLECTION__TYPE);
    createEReference(collectionEClass, COLLECTION__INDEX);
    createEReference(collectionEClass, COLLECTION__CONTAINED_OPERATIONS);

    abstractCollectionValueEClass = createEClass(ABSTRACT_COLLECTION_VALUE);

    collectionValueEClass = createEClass(COLLECTION_VALUE);
    createEReference(collectionValueEClass, COLLECTION_VALUE__OWNED_ELEMENTS);
    createEReference(collectionValueEClass, COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT);

    collectionValueReferenceEClass = createEClass(COLLECTION_VALUE_REFERENCE);
    createEReference(collectionValueReferenceEClass, COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE);
    createEReference(collectionValueReferenceEClass, COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY);

    dataPkgEClass = createEClass(DATA_PKG);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_DATA_PKGS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_CLASSES);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_KEY_PARTS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_COLLECTIONS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_UNITS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_DATA_TYPES);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_SIGNALS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_MESSAGES);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_EXCEPTIONS);
    createEReference(dataPkgEClass, DATA_PKG__OWNED_STATE_EVENTS);

    domainElementEClass = createEClass(DOMAIN_ELEMENT);

    keyPartEClass = createEClass(KEY_PART);
    createEReference(keyPartEClass, KEY_PART__PROPERTY);

    multiplicityElementEClass = createEClass(MULTIPLICITY_ELEMENT);
    createEAttribute(multiplicityElementEClass, MULTIPLICITY_ELEMENT__ORDERED);
    createEAttribute(multiplicityElementEClass, MULTIPLICITY_ELEMENT__UNIQUE);
    createEAttribute(multiplicityElementEClass, MULTIPLICITY_ELEMENT__MIN_INCLUSIVE);
    createEAttribute(multiplicityElementEClass, MULTIPLICITY_ELEMENT__MAX_INCLUSIVE);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MIN_CARD);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MAX_CARD);
    createEReference(multiplicityElementEClass, MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH);

    operationEClass = createEClass(OPERATION);
    createEReference(operationEClass, OPERATION__OWNED_PARAMETERS);
    createEReference(operationEClass, OPERATION__ALLOCATING_OPERATIONS);
    createEReference(operationEClass, OPERATION__ALLOCATED_OPERATIONS);
    createEReference(operationEClass, OPERATION__OWNED_OPERATION_ALLOCATION);
    createEReference(operationEClass, OPERATION__OWNED_EXCHANGE_ITEM_REALIZATIONS);
    createEReference(operationEClass, OPERATION__REALIZED_EXCHANGE_ITEMS);

    operationAllocationEClass = createEClass(OPERATION_ALLOCATION);
    createEReference(operationAllocationEClass, OPERATION_ALLOCATION__ALLOCATED_OPERATION);
    createEReference(operationAllocationEClass, OPERATION_ALLOCATION__ALLOCATING_OPERATION);

    parameterEClass = createEClass(PARAMETER);
    createEAttribute(parameterEClass, PARAMETER__DIRECTION);
    createEAttribute(parameterEClass, PARAMETER__PASSING_MODE);

    propertyEClass = createEClass(PROPERTY);
    createEAttribute(propertyEClass, PROPERTY__AGGREGATION_KIND);
    createEAttribute(propertyEClass, PROPERTY__IS_DERIVED);
    createEAttribute(propertyEClass, PROPERTY__IS_READ_ONLY);
    createEAttribute(propertyEClass, PROPERTY__IS_PART_OF_KEY);
    createEReference(propertyEClass, PROPERTY__ASSOCIATION);

    serviceEClass = createEClass(SERVICE);
    createEAttribute(serviceEClass, SERVICE__SYNCHRONISM_KIND);
    createEReference(serviceEClass, SERVICE__THROWN_EXCEPTIONS);
    createEReference(serviceEClass, SERVICE__MESSAGES);
    createEReference(serviceEClass, SERVICE__MESSAGE_REFERENCES);

    unionEClass = createEClass(UNION);
    createEAttribute(unionEClass, UNION__KIND);
    createEReference(unionEClass, UNION__DISCRIMINANT);
    createEReference(unionEClass, UNION__DEFAULT_PROPERTY);
    createEReference(unionEClass, UNION__CONTAINED_UNION_PROPERTIES);

    unionPropertyEClass = createEClass(UNION_PROPERTY);
    createEReference(unionPropertyEClass, UNION_PROPERTY__QUALIFIER);

    unitEClass = createEClass(UNIT);

    portEClass = createEClass(PORT);
    createEReference(portEClass, PORT__INCOMING_PORT_REALIZATIONS);
    createEReference(portEClass, PORT__OUTGOING_PORT_REALIZATIONS);
    createEReference(portEClass, PORT__OWNED_PROTOCOLS);
    createEReference(portEClass, PORT__INCOMING_PORT_ALLOCATIONS);
    createEReference(portEClass, PORT__OUTGOING_PORT_ALLOCATIONS);
    createEReference(portEClass, PORT__PROVIDED_INTERFACES);
    createEReference(portEClass, PORT__REQUIRED_INTERFACES);
    createEReference(portEClass, PORT__OWNED_PORT_REALIZATIONS);
    createEReference(portEClass, PORT__OWNED_PORT_ALLOCATIONS);

    portRealizationEClass = createEClass(PORT_REALIZATION);
    createEReference(portRealizationEClass, PORT_REALIZATION__REALIZED_PORT);
    createEReference(portRealizationEClass, PORT_REALIZATION__REALIZING_PORT);

    portAllocationEClass = createEClass(PORT_ALLOCATION);
    createEReference(portAllocationEClass, PORT_ALLOCATION__ALLOCATED_PORT);
    createEReference(portAllocationEClass, PORT_ALLOCATION__ALLOCATING_PORT);

    exchangeItemEClass = createEClass(EXCHANGE_ITEM);
    createEAttribute(exchangeItemEClass, EXCHANGE_ITEM__EXCHANGE_MECHANISM);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__OWNED_ELEMENTS);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__OWNED_INFORMATION_REALIZATIONS);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__OWNED_EXCHANGE_ITEM_INSTANCES);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__ALLOCATOR_INTERFACES);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__REALIZED_EXCHANGE_ITEMS);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__REALIZING_EXCHANGE_ITEMS);
    createEReference(exchangeItemEClass, EXCHANGE_ITEM__REALIZING_OPERATIONS);

    exchangeItemElementEClass = createEClass(EXCHANGE_ITEM_ELEMENT);
    createEAttribute(exchangeItemElementEClass, EXCHANGE_ITEM_ELEMENT__KIND);
    createEAttribute(exchangeItemElementEClass, EXCHANGE_ITEM_ELEMENT__DIRECTION);
    createEAttribute(exchangeItemElementEClass, EXCHANGE_ITEM_ELEMENT__COMPOSITE);
    createEReference(exchangeItemElementEClass, EXCHANGE_ITEM_ELEMENT__REFERENCED_PROPERTIES);

    exchangeItemInstanceEClass = createEClass(EXCHANGE_ITEM_INSTANCE);

    informationRealizationEClass = createEClass(INFORMATION_REALIZATION);

    exchangeItemRealizationEClass = createEClass(EXCHANGE_ITEM_REALIZATION);
    createEReference(exchangeItemRealizationEClass, EXCHANGE_ITEM_REALIZATION__REALIZED_ITEM);
    createEReference(exchangeItemRealizationEClass, EXCHANGE_ITEM_REALIZATION__REALIZING_OPERATION);

    abstractEventOperationEClass = createEClass(ABSTRACT_EVENT_OPERATION);
    createEReference(abstractEventOperationEClass, ABSTRACT_EVENT_OPERATION__INVOKING_SEQUENCE_MESSAGES);

    // Create enums
    aggregationKindEEnum = createEEnum(AGGREGATION_KIND);
    parameterDirectionEEnum = createEEnum(PARAMETER_DIRECTION);
    passingModeEEnum = createEEnum(PASSING_MODE);
    synchronismKindEEnum = createEEnum(SYNCHRONISM_KIND);
    unionKindEEnum = createEEnum(UNION_KIND);
    exchangeMechanismEEnum = createEEnum(EXCHANGE_MECHANISM);
    elementKindEEnum = createEEnum(ELEMENT_KIND);
    collectionKindEEnum = createEEnum(COLLECTION_KIND);
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
    CommunicationPackage theCommunicationPackage = (CommunicationPackage)EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI);
    DatatypePackage theDatatypePackage = (DatatypePackage)EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);
    DatavaluePackage theDatavaluePackage = (DatavaluePackage)EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    InteractionPackage theInteractionPackage = (InteractionPackage)EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    CapellacorePackage theCapellacorePackage = (CapellacorePackage)EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI);
    CapellacommonPackage theCapellacommonPackage = (CapellacommonPackage)EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI);
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);
    BehaviorPackage theBehaviorPackage = (BehaviorPackage)EPackage.Registry.INSTANCE.getEPackage(BehaviorPackage.eNS_URI);
    CsPackage theCsPackage = (CsPackage)EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);

    // Add subpackages
    getESubpackages().add(theCommunicationPackage);
    getESubpackages().add(theDatatypePackage);
    getESubpackages().add(theDatavaluePackage);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    abstractInstanceEClass.getESuperTypes().add(this.getProperty());
    associationPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    associationEClass.getESuperTypes().add(theCapellacorePackage.getNamedRelationship());
    classEClass.getESuperTypes().add(theCapellacorePackage.getGeneralClass());
    collectionEClass.getESuperTypes().add(theCapellacorePackage.getClassifier());
    collectionEClass.getESuperTypes().add(this.getMultiplicityElement());
    collectionEClass.getESuperTypes().add(theDatavaluePackage.getDataValueContainer());
    collectionEClass.getESuperTypes().add(theModellingcorePackage.getFinalizableElement());
    abstractCollectionValueEClass.getESuperTypes().add(theDatavaluePackage.getDataValue());
    collectionValueEClass.getESuperTypes().add(this.getAbstractCollectionValue());
    collectionValueReferenceEClass.getESuperTypes().add(this.getAbstractCollectionValue());
    dataPkgEClass.getESuperTypes().add(theCapellacorePackage.getAbstractDependenciesPkg());
    dataPkgEClass.getESuperTypes().add(theCapellacorePackage.getAbstractExchangeItemPkg());
    dataPkgEClass.getESuperTypes().add(this.getAssociationPkg());
    dataPkgEClass.getESuperTypes().add(theDatavaluePackage.getDataValueContainer());
    dataPkgEClass.getESuperTypes().add(theCommunicationPackage.getMessageReferencePkg());
    domainElementEClass.getESuperTypes().add(this.getClass_());
    keyPartEClass.getESuperTypes().add(theCapellacorePackage.getRelationship());
    multiplicityElementEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    operationEClass.getESuperTypes().add(theCapellacorePackage.getFeature());
    operationEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    operationEClass.getESuperTypes().add(this.getAbstractEventOperation());
    operationAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    parameterEClass.getESuperTypes().add(theCapellacorePackage.getTypedElement());
    parameterEClass.getESuperTypes().add(this.getMultiplicityElement());
    parameterEClass.getESuperTypes().add(theModellingcorePackage.getAbstractParameter());
    propertyEClass.getESuperTypes().add(theCapellacorePackage.getFeature());
    propertyEClass.getESuperTypes().add(theCapellacorePackage.getTypedElement());
    propertyEClass.getESuperTypes().add(this.getMultiplicityElement());
    propertyEClass.getESuperTypes().add(theModellingcorePackage.getFinalizableElement());
    serviceEClass.getESuperTypes().add(this.getOperation());
    unionEClass.getESuperTypes().add(this.getClass_());
    unionPropertyEClass.getESuperTypes().add(this.getProperty());
    unitEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    portEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    portRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    portAllocationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    exchangeItemEClass.getESuperTypes().add(theModellingcorePackage.getAbstractExchangeItem());
    exchangeItemEClass.getESuperTypes().add(theBehaviorPackage.getAbstractEvent());
    exchangeItemEClass.getESuperTypes().add(theBehaviorPackage.getAbstractSignal());
    exchangeItemEClass.getESuperTypes().add(theModellingcorePackage.getFinalizableElement());
    exchangeItemEClass.getESuperTypes().add(theCapellacorePackage.getGeneralizableElement());
    exchangeItemElementEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    exchangeItemElementEClass.getESuperTypes().add(this.getMultiplicityElement());
    exchangeItemElementEClass.getESuperTypes().add(theCapellacorePackage.getTypedElement());
    exchangeItemInstanceEClass.getESuperTypes().add(this.getAbstractInstance());
    informationRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    exchangeItemRealizationEClass.getESuperTypes().add(theCapellacorePackage.getAllocation());
    abstractEventOperationEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());

    // Initialize classes and features; add operations and parameters
    initEClass(abstractInstanceEClass, AbstractInstance.class, "AbstractInstance", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractInstance_RepresentingInstanceRoles(), theInteractionPackage.getInstanceRole(), null, "representingInstanceRoles", null, 0, -1, AbstractInstance.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(associationPkgEClass, AssociationPkg.class, "AssociationPkg", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getAssociationPkg_Visibility(), theCapellacorePackage.getVisibilityKind(), "visibility", null, 0, 1, AssociationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAssociationPkg_OwnedAssociations(), this.getAssociation(), null, "ownedAssociations", null, 0, -1, AssociationPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAssociation_OwnedMembers(), this.getProperty(), null, "ownedMembers", null, 0, 2, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAssociation_NavigableMembers(), this.getProperty(), null, "navigableMembers", null, 0, 2, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(classEClass, org.polarsys.capella.core.data.information.Class.class, "Class", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getClass_IsPrimitive(), ecorePackage.getEBoolean(), "isPrimitive", null, 0, 1, org.polarsys.capella.core.data.information.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_KeyParts(), this.getKeyPart(), null, "keyParts", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_OwnedStateMachines(), theCapellacommonPackage.getStateMachine(), null, "ownedStateMachines", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_OwnedDataValues(), theDatavaluePackage.getDataValue(), null, "ownedDataValues", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_OwnedInformationRealizations(), this.getInformationRealization(), null, "ownedInformationRealizations", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_RealizedClasses(), this.getClass_(), this.getClass_RealizingClasses(), "realizedClasses", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getClass_RealizingClasses(), this.getClass_(), this.getClass_RealizedClasses(), "realizingClasses", null, 0, -1, org.polarsys.capella.core.data.information.Class.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionEClass, Collection.class, "Collection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getCollection_IsPrimitive(), ecorePackage.getEBoolean(), "isPrimitive", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCollection_Visibility(), theCapellacorePackage.getVisibilityKind(), "visibility", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCollection_Kind(), this.getCollectionKind(), "kind", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getCollection_AggregationKind(), this.getAggregationKind(), "aggregationKind", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCollection_Type(), theCapellacorePackage.getType(), null, "type", null, 0, 1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCollection_Index(), theDatatypePackage.getDataType(), null, "index", null, 0, -1, Collection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCollection_ContainedOperations(), this.getOperation(), null, "containedOperations", null, 0, -1, Collection.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractCollectionValueEClass, AbstractCollectionValue.class, "AbstractCollectionValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(collectionValueEClass, CollectionValue.class, "CollectionValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCollectionValue_OwnedElements(), theDatavaluePackage.getDataValue(), null, "ownedElements", null, 0, -1, CollectionValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCollectionValue_OwnedDefaultElement(), theDatavaluePackage.getDataValue(), null, "ownedDefaultElement", null, 0, 1, CollectionValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionValueReferenceEClass, CollectionValueReference.class, "CollectionValueReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getCollectionValueReference_ReferencedValue(), this.getAbstractCollectionValue(), null, "referencedValue", null, 0, 1, CollectionValueReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getCollectionValueReference_ReferencedProperty(), this.getProperty(), null, "referencedProperty", null, 0, 1, CollectionValueReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(dataPkgEClass, DataPkg.class, "DataPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedDataPkgs(), this.getDataPkg(), null, "ownedDataPkgs", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedClasses(), this.getClass_(), null, "ownedClasses", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedKeyParts(), this.getKeyPart(), null, "ownedKeyParts", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedCollections(), this.getCollection(), null, "ownedCollections", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedUnits(), this.getUnit(), null, "ownedUnits", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedDataTypes(), theDatatypePackage.getDataType(), null, "ownedDataTypes", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedSignals(), theCommunicationPackage.getSignal(), null, "ownedSignals", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedMessages(), theCommunicationPackage.getMessage(), null, "ownedMessages", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedExceptions(), theCommunicationPackage.getException(), null, "ownedExceptions", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataPkg_OwnedStateEvents(), theCapellacommonPackage.getStateEvent(), null, "ownedStateEvents", null, 0, -1, DataPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(domainElementEClass, DomainElement.class, "DomainElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(keyPartEClass, KeyPart.class, "KeyPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getKeyPart_Property(), this.getProperty(), null, "property", null, 1, 1, KeyPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(multiplicityElementEClass, MultiplicityElement.class, "MultiplicityElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getMultiplicityElement_Ordered(), ecorePackage.getEBoolean(), "ordered", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getMultiplicityElement_Unique(), ecorePackage.getEBoolean(), "unique", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getMultiplicityElement_MinInclusive(), ecorePackage.getEBoolean(), "minInclusive", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getMultiplicityElement_MaxInclusive(), ecorePackage.getEBoolean(), "maxInclusive", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedDefaultValue(), theDatavaluePackage.getDataValue(), null, "ownedDefaultValue", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMinValue(), theDatavaluePackage.getDataValue(), null, "ownedMinValue", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMaxValue(), theDatavaluePackage.getDataValue(), null, "ownedMaxValue", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedNullValue(), theDatavaluePackage.getDataValue(), null, "ownedNullValue", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMinCard(), theDatavaluePackage.getNumericValue(), null, "ownedMinCard", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMinLength(), theDatavaluePackage.getNumericValue(), null, "ownedMinLength", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMaxCard(), theDatavaluePackage.getNumericValue(), null, "ownedMaxCard", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getMultiplicityElement_OwnedMaxLength(), theDatavaluePackage.getNumericValue(), null, "ownedMaxLength", null, 0, 1, MultiplicityElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getOperation_OwnedParameters(), this.getParameter(), null, "ownedParameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperation_AllocatingOperations(), this.getOperation(), this.getOperation_AllocatedOperations(), "allocatingOperations", null, 0, -1, Operation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperation_AllocatedOperations(), this.getOperation(), this.getOperation_AllocatingOperations(), "allocatedOperations", null, 0, -1, Operation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperation_OwnedOperationAllocation(), this.getOperationAllocation(), null, "ownedOperationAllocation", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperation_OwnedExchangeItemRealizations(), this.getExchangeItemRealization(), null, "ownedExchangeItemRealizations", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperation_RealizedExchangeItems(), this.getExchangeItem(), this.getExchangeItem_RealizingOperations(), "realizedExchangeItems", null, 0, -1, Operation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(operationAllocationEClass, OperationAllocation.class, "OperationAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getOperationAllocation_AllocatedOperation(), this.getOperation(), null, "allocatedOperation", null, 1, 1, OperationAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getOperationAllocation_AllocatingOperation(), this.getOperation(), null, "allocatingOperation", null, 1, 1, OperationAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getParameter_Direction(), this.getParameterDirection(), "direction", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getParameter_PassingMode(), this.getPassingMode(), "passingMode", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getProperty_AggregationKind(), this.getAggregationKind(), "aggregationKind", "UNSET", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getProperty_IsDerived(), ecorePackage.getEBoolean(), "isDerived", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getProperty_IsReadOnly(), ecorePackage.getEBoolean(), "isReadOnly", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getProperty_IsPartOfKey(), ecorePackage.getEBoolean(), "isPartOfKey", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getProperty_Association(), this.getAssociation(), null, "association", null, 0, 1, Property.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(serviceEClass, Service.class, "Service", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getService_SynchronismKind(), this.getSynchronismKind(), "synchronismKind", null, 0, 1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getService_ThrownExceptions(), theCommunicationPackage.getException(), null, "thrownExceptions", null, 0, -1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getService_Messages(), theCommunicationPackage.getMessage(), null, "messages", null, 0, -1, Service.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getService_MessageReferences(), theCommunicationPackage.getMessageReference(), null, "messageReferences", null, 0, -1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(unionEClass, Union.class, "Union", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getUnion_Kind(), this.getUnionKind(), "kind", null, 0, 1, Union.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getUnion_Discriminant(), this.getUnionProperty(), null, "discriminant", null, 0, 1, Union.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getUnion_DefaultProperty(), this.getUnionProperty(), null, "defaultProperty", null, 0, 1, Union.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getUnion_ContainedUnionProperties(), this.getUnionProperty(), null, "containedUnionProperties", null, 0, -1, Union.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(unionPropertyEClass, UnionProperty.class, "UnionProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getUnionProperty_Qualifier(), theDatavaluePackage.getDataValue(), null, "qualifier", null, 0, -1, UnionProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(unitEClass, Unit.class, "Unit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(portEClass, Port.class, "Port", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPort_IncomingPortRealizations(), this.getPortRealization(), this.getPortRealization_RealizedPort(), "incomingPortRealizations", null, 0, -1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_OutgoingPortRealizations(), this.getPortRealization(), this.getPortRealization_RealizingPort(), "outgoingPortRealizations", null, 0, -1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_OwnedProtocols(), theCapellacommonPackage.getStateMachine(), null, "ownedProtocols", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_IncomingPortAllocations(), this.getPortAllocation(), this.getPortAllocation_AllocatedPort(), "incomingPortAllocations", null, 0, -1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_OutgoingPortAllocations(), this.getPortAllocation(), this.getPortAllocation_AllocatingPort(), "outgoingPortAllocations", null, 0, -1, Port.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_ProvidedInterfaces(), theCsPackage.getInterface(), null, "providedInterfaces", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_RequiredInterfaces(), theCsPackage.getInterface(), null, "requiredInterfaces", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_OwnedPortRealizations(), this.getPortRealization(), null, "ownedPortRealizations", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPort_OwnedPortAllocations(), this.getPortAllocation(), null, "ownedPortAllocations", null, 0, -1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(portRealizationEClass, PortRealization.class, "PortRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPortRealization_RealizedPort(), this.getPort(), this.getPort_IncomingPortRealizations(), "realizedPort", null, 1, 1, PortRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPortRealization_RealizingPort(), this.getPort(), this.getPort_OutgoingPortRealizations(), "realizingPort", null, 1, 1, PortRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(portAllocationEClass, PortAllocation.class, "PortAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPortAllocation_AllocatedPort(), this.getPort(), this.getPort_IncomingPortAllocations(), "allocatedPort", null, 0, 1, PortAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getPortAllocation_AllocatingPort(), this.getPort(), this.getPort_OutgoingPortAllocations(), "allocatingPort", null, 0, 1, PortAllocation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeItemEClass, ExchangeItem.class, "ExchangeItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getExchangeItem_ExchangeMechanism(), this.getExchangeMechanism(), "exchangeMechanism", null, 1, 1, ExchangeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_OwnedElements(), this.getExchangeItemElement(), null, "ownedElements", null, 0, -1, ExchangeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_OwnedInformationRealizations(), this.getInformationRealization(), null, "ownedInformationRealizations", null, 0, -1, ExchangeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_OwnedExchangeItemInstances(), this.getExchangeItemInstance(), null, "ownedExchangeItemInstances", null, 0, -1, ExchangeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_AllocatorInterfaces(), theCsPackage.getInterface(), null, "allocatorInterfaces", null, 0, -1, ExchangeItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_RealizedExchangeItems(), this.getExchangeItem(), this.getExchangeItem_RealizingExchangeItems(), "realizedExchangeItems", null, 0, -1, ExchangeItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_RealizingExchangeItems(), this.getExchangeItem(), this.getExchangeItem_RealizedExchangeItems(), "realizingExchangeItems", null, 0, -1, ExchangeItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItem_RealizingOperations(), this.getOperation(), this.getOperation_RealizedExchangeItems(), "realizingOperations", null, 0, -1, ExchangeItem.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeItemElementEClass, ExchangeItemElement.class, "ExchangeItemElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getExchangeItemElement_Kind(), this.getElementKind(), "kind", null, 0, 1, ExchangeItemElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getExchangeItemElement_Direction(), this.getParameterDirection(), "direction", null, 0, 1, ExchangeItemElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getExchangeItemElement_Composite(), ecorePackage.getEBoolean(), "composite", null, 0, 1, ExchangeItemElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItemElement_ReferencedProperties(), this.getProperty(), null, "referencedProperties", null, 0, -1, ExchangeItemElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(exchangeItemInstanceEClass, ExchangeItemInstance.class, "ExchangeItemInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(informationRealizationEClass, InformationRealization.class, "InformationRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(exchangeItemRealizationEClass, ExchangeItemRealization.class, "ExchangeItemRealization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getExchangeItemRealization_RealizedItem(), theModellingcorePackage.getAbstractExchangeItem(), null, "realizedItem", null, 0, 1, ExchangeItemRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getExchangeItemRealization_RealizingOperation(), this.getOperation(), null, "realizingOperation", null, 0, 1, ExchangeItemRealization.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractEventOperationEClass, AbstractEventOperation.class, "AbstractEventOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractEventOperation_InvokingSequenceMessages(), theInteractionPackage.getSequenceMessage(), null, "invokingSequenceMessages", null, 0, -1, AbstractEventOperation.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(aggregationKindEEnum, AggregationKind.class, "AggregationKind"); //$NON-NLS-1$
    addEEnumLiteral(aggregationKindEEnum, AggregationKind.UNSET);
    addEEnumLiteral(aggregationKindEEnum, AggregationKind.ASSOCIATION);
    addEEnumLiteral(aggregationKindEEnum, AggregationKind.AGGREGATION);
    addEEnumLiteral(aggregationKindEEnum, AggregationKind.COMPOSITION);

    initEEnum(parameterDirectionEEnum, ParameterDirection.class, "ParameterDirection"); //$NON-NLS-1$
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.IN);
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.OUT);
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.INOUT);
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.RETURN);
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.EXCEPTION);
    addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.UNSET);

    initEEnum(passingModeEEnum, PassingMode.class, "PassingMode"); //$NON-NLS-1$
    addEEnumLiteral(passingModeEEnum, PassingMode.UNSET);
    addEEnumLiteral(passingModeEEnum, PassingMode.BY_REF);
    addEEnumLiteral(passingModeEEnum, PassingMode.BY_VALUE);

    initEEnum(synchronismKindEEnum, SynchronismKind.class, "SynchronismKind"); //$NON-NLS-1$
    addEEnumLiteral(synchronismKindEEnum, SynchronismKind.UNSET);
    addEEnumLiteral(synchronismKindEEnum, SynchronismKind.SYNCHRONOUS);
    addEEnumLiteral(synchronismKindEEnum, SynchronismKind.ASYNCHRONOUS);

    initEEnum(unionKindEEnum, UnionKind.class, "UnionKind"); //$NON-NLS-1$
    addEEnumLiteral(unionKindEEnum, UnionKind.UNION);
    addEEnumLiteral(unionKindEEnum, UnionKind.VARIANT);

    initEEnum(exchangeMechanismEEnum, ExchangeMechanism.class, "ExchangeMechanism"); //$NON-NLS-1$
    addEEnumLiteral(exchangeMechanismEEnum, ExchangeMechanism.UNSET);
    addEEnumLiteral(exchangeMechanismEEnum, ExchangeMechanism.FLOW);
    addEEnumLiteral(exchangeMechanismEEnum, ExchangeMechanism.OPERATION);
    addEEnumLiteral(exchangeMechanismEEnum, ExchangeMechanism.EVENT);
    addEEnumLiteral(exchangeMechanismEEnum, ExchangeMechanism.SHARED_DATA);

    initEEnum(elementKindEEnum, ElementKind.class, "ElementKind"); //$NON-NLS-1$
    addEEnumLiteral(elementKindEEnum, ElementKind.TYPE);
    addEEnumLiteral(elementKindEEnum, ElementKind.MEMBER);

    initEEnum(collectionKindEEnum, CollectionKind.class, "CollectionKind"); //$NON-NLS-1$
    addEEnumLiteral(collectionKindEEnum, CollectionKind.ARRAY);
    addEEnumLiteral(collectionKindEEnum, CollectionKind.SEQUENCE);

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
    // http://www.polarsys.org/capella/derived
    createDerivedAnnotations();
    // http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
    createSegmentAnnotations();
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
         "description", "Information aims at defining the data transmission language (named Information due to the namespacing strange effects if it would have been named Data). It includes the notion of data as well as the different data communication means.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This package depends on the model CapellaCore.ecore", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractInstanceEClass,
       source,
       new String[] {
         "description", "Base class used to derive specific types of instances of classifiers (e.g very high-level/generic class)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum,
       source,
       new String[] {
         "description", "defines the specific kind of a relationship, as per UML definitions\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when value is not defined by the user\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "An association specifies a semantic relationship that can occur between typed instances. It has at least two ends\r\nrepresented by properties, each of which is connected to the type of the end. More than one end of the association may\r\nhave the same type.\r\n[source: UML superstructure v2.2]\r\n\r\nIndicates that the property has no aggregation.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "An aggregation specifies a semantic relationship between a part and a whole. The part has a lifecycle of its own, and is potentially shared among several aggregators\r\n[source: Capella study]\r\n\r\nIndicates that the property has a shared aggregation.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "A composition specifies a semantic relationship between whole and its parts. The parts lifecycles are tied to that of the whole, and they are not shared with any other aggregator.\r\n[source: Capella study]\r\n\r\nIndicates that the property is aggregated compositely, i.e., the composite object has responsibility for the existence\r\nand storage of the composed objects.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationPkgEClass,
       source,
       new String[] {
         "description", "A container for Association elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_Visibility(),
       source,
       new String[] {
         "description", "Determines where the NamedElement appears within different Namespaces within the overall model, and its\r\naccessibility.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to VisibilityKind description", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
         "description", "the Associations elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationEClass,
       source,
       new String[] {
         "description", "An association specifies a semantic relationship that can occur between typed instances. It has at least two ends\r\nrepresented by properties, each of which is connected to the type of the end. More than one end of the association may\r\nhave the same type.\r\nAn end property of an association that is owned by an end class or that is a navigable owned end of the association\r\nindicates that the association is navigable from the opposite ends; otherwise, the association is not navigable from the\r\nopposite ends.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- An association specializing another association has the same number of ends as the other association.\r\nself.parents()->forAll(p | p.memberEnd.size() = self.memberEnd.size())\r\n- When an association specializes another association, every end of the specific association corresponds to an end of the\r\ngeneral association, and the specific end reaches the same type or a subtype of the more general end.\r\n- endType is derived from the types of the member ends.\r\nself.endType = self.memberEnd->collect(e | e.type)\r\n- Only binary associations can be aggregations.\r\nself.memberEnd->exists(aggregation <> Aggregation::none) implies self.memberEnd->size() = 2\r\n- Association ends of associations with more than two ends must be owned by the association.\r\nif memberEnd->size() > 2 then ownedEnd->includesAll(memberEnd)\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
         "description", "Each end represents participation of instances of the classifier connected to the end in links of the association.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
         "description", "The navigable ends that are owned by the association itself\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (classEClass,
       source,
       new String[] {
         "description", "A class describes a set of objects that share the same specifications of features, constraints, and semantics\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_IsPrimitive(),
       source,
       new String[] {
         "description", "indicates whether or not the class inherits from a parent class.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "\"true\" means that there is no super class that this class inherits from.", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
         "description", "The KeyPart elements owned by this class\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_OwnedStateMachines(),
       source,
       new String[] {
         "description", "the state machines associated to this class, supporting the characterization of its dynamic behavior\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_OwnedDataValues(),
       source,
       new String[] {
         "description", "the list of DataValue elements owned by this class\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_RealizedClasses(),
       source,
       new String[] {
         "description", "class(es) realized by this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_RealizingClasses(),
       source,
       new String[] {
         "description", "class(es) realizing this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionEClass,
       source,
       new String[] {
         "description", "A set of items of a given type.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_IsPrimitive(),
       source,
       new String[] {
         "description", "indicates whether this collection is a first level assembly using native types, or if it is using previously defined Collections \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "true if the Collection is not assembling other Collections", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Visibility(),
       source,
       new String[] {
         "description", "specifies the visibility status for this collection\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "Refer to VisibilityKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Kind(),
       source,
       new String[] {
         "description", "specifies the kind status for this collection", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "Refer to CollectionKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_AggregationKind(),
       source,
       new String[] {
         "description", "Specifies the kind of aggregation that applies to the Collection", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Type(),
       source,
       new String[] {
         "description", "the type of the elements being collected\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Index(),
       source,
       new String[] {
         "description", "index pointing to a specific part of this collection \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "description", "The operations associated to this collection\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCollectionValueEClass,
       source,
       new String[] {
         "description", "Base class for defining type-specific collection values\r\n[source: Capella light-light study]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionValueEClass,
       source,
       new String[] {
         "description", "Caracterizes a value that represents a collection of elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValue_OwnedElements(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValue_OwnedDefaultElement(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionValueReferenceEClass,
       source,
       new String[] {
         "description", "A reference to a collection value, allowing the reuse of collection values across data value structures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the collection value that this reference points to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that is using this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataPkgEClass,
       source,
       new String[] {
         "description", "A container for data structures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
         "description", "Sub data packages contained in this data package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
         "description", "the class elements contained in the package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
         "description", "KeyPart elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
         "description", "Collection elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
         "description", "the Unit elements contained in the package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
         "description", "Data types contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
         "description", "the Signal elements contained in the package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
         "description", "the Messages contained in this Message package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
         "description", "the Exception elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedStateEvents(),
       source,
       new String[] {
         "description", "the StateEvent elements contained in the package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (domainElementEClass,
       source,
       new String[] {
         "description", "A reinterpretable representation of information in a formalized manner suitable for communication, interpretation, or processing.\r\n[source: Open Archival Information System (OAIS), IEC]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella 1.0.3", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
         "description", "The relationship of a Part with something that it serves as a key for\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
         "description", "the Part/Property being declared as the key to access some other element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (multiplicityElementEClass,
       source,
       new String[] {
         "description", "A MultiplicityElement is an abstract metaclass that includes optional attributes for defining the bounds of a multiplicity.\r\nA MultiplicityElement also includes specifications of whether the values in an instantiation of this element must be\r\nunique or ordered.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "These constraints must handle situations where the upper bound may be specified by an expression not computable in the\r\nmodel.\r\n- A multiplicity must define at least one valid cardinality that is greater than zero.\r\nupperBound()->notEmpty() implies upperBound() > 0\r\n- The lower bound must be a non-negative integer literal.\r\nlowerBound()->notEmpty() implies lowerBound() >= 0\r\n- The upper bound must be greater than or equal to the lower bound.\r\n(upperBound()->notEmpty() and lowerBound()->notEmpty()) implies upperBound() >= lowerBound()\r\n- If a non-literal ValueSpecification is used for the lower or upper bound, then evaluating that specification must not have\r\nside effects.\r\nCannot be expressed in OCL.\r\n- If a non-literal ValueSpecification is used for the lower or upper bound, then that specification must be a constant\r\nexpression.\r\nCannot be expressed in OCL.\r\n- The derived lower attribute must equal the lowerBound.\r\nlower = lowerBound()\r\n- The derived upper attribute must equal the upperBound.\r\n[source: UML superstructure v2.2]\r\nupper = upperBound()", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_Ordered(),
       source,
       new String[] {
         "description", "For a multivalued multiplicity, this attribute specifies whether the values in an instantiation of this element are\r\nsequentially ordered\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_Unique(),
       source,
       new String[] {
         "description", "specifies whether or not this element is unique\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "true is element is unique", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_MinInclusive(),
       source,
       new String[] {
         "description", "specifies whether the min value of the range is included or not\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_MaxInclusive(),
       source,
       new String[] {
         "description", "specifies whether the max value of the range is included or not\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedDefaultValue(),
       source,
       new String[] {
         "description", "the value assigned by default to this multiplicity element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
         "description", "minimum specified value for this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
         "description", "specified max value for this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedNullValue(),
       source,
       new String[] {
         "description", "the reference to the null value among the set of values contained in this MultiplicityElement\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
         "description", "Specifies the lower bound of the multiplicity interval, if it is expressed as an integer\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
         "description", "Specified minimum length for this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
         "description", "Specifies the upper bound of the multiplicity interval, if it is expressed as an unlimited natural\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
         "description", "Specified max length for this element\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationEClass,
       source,
       new String[] {
         "description", "An operation is a behavioral feature of a classifier that specifies the name, type, parameters, and constraints for invoking\r\nan associated behavior\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/operation_parameters.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- An operation can have at most one return parameter (i.e., an owned parameter with the direction set to \"return\").\r\nownedParameter->select(par | par.direction = #return)->size() <= 1\r\n- If this operation has a return parameter, isOrdered equals the value of isOrdered for that parameter; otherwise, isOrdered\r\nis false.\r\nisOrdered = if returnResult()->notEmpty() then returnResult()->any().isOrdered else false endif\r\n- If this operation has a return parameter, isUnique equals the value of isUnique for that parameter; otherwise, isUnique is\r\ntrue.\r\nisUnique = if returnResult()->notEmpty() then returnResult()->any().isUnique else true endif\r\n- If this operation has a return parameter, lower equals the value of lower for that parameter; otherwise, lower is not\r\ndefined.\r\nlower = if returnResult()->notEmpty() then returnResult()->any().lower else Set{} endif\r\n- If this operation has a return parameter, upper equals the value of upper for that parameter; otherwise, upper is not\r\ndefined.\r\nupper = if returnResult()->notEmpty() then returnResult()->any().upper else Set{} endif\r\n- If this operation has a return parameter, type equals the value of type for that parameter; otherwise, type is not defined.\r\ntype = if returnResult()->notEmpty() then returnResult()->any().type else Set{} endif\r\n- A bodyCondition can only be specified for a query operation.\r\nbodyCondition->notEmpty() implies isQuery\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
         "description", "the parameters associated to this operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatingOperations(),
       source,
       new String[] {
         "description", "(automatically computed) the operation allocation relationships that point to this Operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatedOperations(),
       source,
       new String[] {
         "description", "(automatically computed) the operation allocation relationships that start from this Operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedOperationAllocation(),
       source,
       new String[] {
         "description", "list of the allocations of this operation to/from other operations.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedExchangeItemRealizations(),
       source,
       new String[] {
         "description", "list of the exchange items that the operation is realizing\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_RealizedExchangeItems(),
       source,
       new String[] {
         "description", "class(es) realized by this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationAllocationEClass,
       source,
       new String[] {
         "description", "Mediator class supporting the implementation of the allocation link between two Operations\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatedOperation(),
       source,
       new String[] {
         "description", "contains the \"target\" of the allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatingOperation(),
       source,
       new String[] {
         "description", "contains the \"source\" of the allocation link\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEClass,
       source,
       new String[] {
         "description", "A parameter is a specification of an argument used to pass information into or out of an invocation of a behavioral\r\nfeature.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/operation_parameters.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_Direction(),
       source,
       new String[] {
         "description", "specifies whether the parameter is an input, an output, or both.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see ParameterDirection definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_PassingMode(),
       source,
       new String[] {
         "description", "specifies the way the parameter is passed along from the caller to the callee\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see PassingMode enumeration definition for possible values", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum,
       source,
       new String[] {
         "description", "specifies the direction in which data is passed along through a parameter \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "could be renamed ParameterDirectionKind to match UML" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the parameter represents an input of the operation it is used in\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the parameter represents an output of the operation it is used in\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the parameter represents both an input and on output of the operation it is used in\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "used when the parameter represents the return value of the operation it is used in\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "the parameter is like an exception", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "used when the CommunicationLink protocol is not yet set", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum,
       source,
       new String[] {
         "description", "specifies the data passing mechanism for parameters of an operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the data passing mechanism is not precised\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the data is being passed by reference to the operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used when the data is being passed by value to the operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (propertyEClass,
       source,
       new String[] {
         "description", "A property is a structural feature.\r\nA property related to a classifier by ownedAttribute represents an attribute, and it may also represent an association end.\r\nIt relates an instance of the class to a value or collection of values of the type of the attribute.\r\nA property related to an Association by memberEnd or its specializations represents an end of the association. The type\r\nof property is the type of the end of the association.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "- If this property is owned by a class associated with a binary association, and the other end of the association is also owned by a class, then opposite gives the other end.\r\n- A multiplicity on an aggregate end of a composite aggregation must not have an upper bound greater than 1.\r\n- Subsetting may only occur when the context of the subsetting property conforms to the context of the subsetted property.\r\n- A redefined property must be inherited from a more general classifier containing the redefining property.\r\n- A subsetting property may strengthen the type of the subsetted property, and its upper bound may be less.\r\n- Only a navigable property can be marked as readOnly.\r\n- A derived union is derived.\r\n- A derived union is read only.\r\n- The value of isComposite is true only if aggregation is composite.\r\n-  A Property cannot be subset by a Property with the same name\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_AggregationKind(),
       source,
       new String[] {
         "description", "Specifies the kind of aggregation that applies to the Property\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsDerived(),
       source,
       new String[] {
         "description", "Specifies whether the Property is derived, i.e., whether its value or values can be computed from other information\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsReadOnly(),
       source,
       new String[] {
         "description", "If true, the attribute may only be read, and not written\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsPartOfKey(),
       source,
       new String[] {
         "description", "specifies whether this Property is involved as a key to a table of values\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "\"true\" if the Property is used as a key", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
         "description", "an association relationship related to this Property\r\n[source: Capella study]\r\n\r\nReferences the association of which this property is a member, if any.\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum,
       source,
       new String[] {
         "description", "specifies the synchronicity of an operation invocation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the synchronicity of the operation is not precised\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used to specify that the invocation of the operation is synchronous, e.g. does not complete before the actions performed by the operation are complete\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "used to specify that the invocation of the operation is asynchronous, i.e. it is potentially completed before the actions performed in the operation are completed\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (serviceEClass,
       source,
       new String[] {
         "description", "Specification of an action to be performed by a class, to fulfill a predefined need.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/operation_parameters.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_SynchronismKind(),
       source,
       new String[] {
         "description", "specifies the synchronicity of the service call\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see SynchronismKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
         "description", "list of exceptions that can be raised by this service\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_Messages(),
       source,
       new String[] {
         "description", "(automatically computed) the Messages involving this Service\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_MessageReferences(),
       source,
       new String[] {
         "description", "the reference objects to the Messages involving this Service\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionEClass,
       source,
       new String[] {
         "description", "(not used)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Kind(),
       source,
       new String[] {
         "description", "specifies the type of the union\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see UnionKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
         "description", "Link to the discriminant union property", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_DefaultProperty(),
       source,
       new String[] {
         "description", "Link to the default union property", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum,
       source,
       new String[] {
         "description", "defines the specific kind of a Union structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the structure represents a union \r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the structure represents possible variants of the same data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionPropertyEClass,
       source,
       new String[] {
         "description", "(not used)", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "not used/implemented as of Capella", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
         "description", "(not used)", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unitEClass,
       source,
       new String[] {
         "description", "A Unit is a quantity in terms of which the magnitudes of other quantities that have the same dimension can be stated. A\r\nunit often relies on precise and reproducible ways to measure the unit. For example, a unit of length such as meter may\r\nbe specified as a multiple of a particular wavelength of light. A unit may also specify less stable or precise ways to\r\nexpress some value, such as a cost expressed in some currency, or a severity rating measured by a numerical scale\r\n[source: SysML specification v1.1]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "a unit is typically associated to a physical dimension element", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system/logical/physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portEClass,
       source,
       new String[] {
         "description", "A port is an interaction point between a block or part and its environment that\r\nis connected with other ports via connectors\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "arcardia_description", "The connection point of an exchange on an entity is called a port.", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/ports_exchanges.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortRealizations(),
       source,
       new String[] {
         "description", "contains the list of port realization link(s) pointing from other (typically lower level) port(s) to this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortRealizations(),
       source,
       new String[] {
         "description", "(automatically computed) list of port realization links starting from this port, and pointing to other (typically higher-level) ports.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedProtocols(),
       source,
       new String[] {
         "description", "allows to associate state machines to this port, specifying the communication protocol of incoming data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) list of allocation links pointing from other model elements, to this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortAllocations(),
       source,
       new String[] {
         "description", "(automatically computed) list of allocations links, starting from this port towards other model elements to which this port needs to be allocated\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_ProvidedInterfaces(),
       source,
       new String[] {
         "description", "lists the Interfaces that are provided through this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_RequiredInterfaces(),
       source,
       new String[] {
         "description", "lists the Interfaces that are required by this port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedPortRealizations(),
       source,
       new String[] {
         "description", "the port realizations links that are owned/contained in this Port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedPortAllocations(),
       source,
       new String[] {
         "description", "the port allocation links that are owned/contained in this Port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portRealizationEClass,
       source,
       new String[] {
         "description", "a PortRealization is a specific kind of allocation link between two Ports (typically of different design levels, or of different nature)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "../img/usage_examples/port_realization.png", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizedPort(),
       source,
       new String[] {
         "description", "destiniation of the port realization : the port that is being realized\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizingPort(),
       source,
       new String[] {
         "description", "the source of the Port realization : the port that is realizing another port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portAllocationEClass,
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
      (getPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "description", "the \"destination\" of the allocation link : the port that is being allocated by another port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "description", "the \"source\" of the allocation link : the port that is allocating the other port\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemEClass,
       source,
       new String[] {
         "description", "Defined by functional characteristics that exist at a common boundary with co-functioning items and allow systems equipment to be compatible. \r\nAn exchange item describes a required or produced data.\r\nAn exchange item has an exchange mechanism\r\n[source:ARCADIA encyclopedia v0.8.0]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "an exchange item should be created whenever there is a need to group data type elements that are bound by an applicative meaning, and should be treated as a whole", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_ExchangeMechanism(),
       source,
       new String[] {
         "description", "Communication principle associated to this exchange item\r\n[source:ARCADIA encyclopedia v0.8.0]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to ExchangeMechanism definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_OwnedElements(),
       source,
       new String[] {
         "description", "Set of exchange item elements that form the structure of the structured exchange item\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizedExchangeItems(),
       source,
       new String[] {
         "description", "class(es) realized by this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizingExchangeItems(),
       source,
       new String[] {
         "description", "class(es) realizing this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizingOperations(),
       source,
       new String[] {
         "description", "class(es) realizing this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemElementEClass,
       source,
       new String[] {
         "description", "a part of a structured exchange item\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational, system, logical, physical, epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Kind(),
       source,
       new String[] {
         "description", "refer to ElementKind description", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to ElementKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Direction(),
       source,
       new String[] {
         "description", "specifies whether the parameter is an input, an output, or both.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "see ParameterDirection definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Composite(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_ReferencedProperties(),
       source,
       new String[] {
         "description", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (informationRealizationEClass,
       source,
       new String[] {
         "description", "Realization link between two information items", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "_todo_reviewed: To complete once documentation about extension process will be done", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum,
       source,
       new String[] {
         "description", "Enumeration of the different exchange mechanisms\r\n[source:Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Exchange mechanism not defined", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Continuous supply of a data\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Sporadic supply of a data with a returned data\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "Asynchronous information that is taken into account rapidly\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "Data taken into account (reading or writing) without taking care of producers or consumers\r\n[Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum,
       source,
       new String[] {
         "description", "enumeration listing the various possibilities regarding the visibility of a feature of a class\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when ExchangeItemElement is described as a type for its ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when ExchangeItemElement is described as a member for its ExchangeItem", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum,
       source,
       new String[] {
         "description", "defines the specific kind of a Collection structure\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "used when the collection is to be considered as an array of elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "used when the collection is to be considered as a sequence (list) of elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemRealizationEClass,
       source,
       new String[] {
         "description", "Allocation link between exchange items and operation(s) of an interface that support them\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizedItem(),
       source,
       new String[] {
         "description", "the exchange item that is being realized by the operation\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizingOperation(),
       source,
       new String[] {
         "description", "the interface\'s operation that realizes the given exchange item\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEventOperationEClass,
       source,
       new String[] {
         "description", "the element triggered by the reception of the event", //$NON-NLS-1$ //$NON-NLS-2$
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
      (aggregationKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (getAssociationPkg_Visibility(),
       source,
       new String[] {
       });
    addAnnotation
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
       });
    addAnnotation
      (associationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
       });
    addAnnotation
      (classEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getClass_IsPrimitive(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_OwnedStateMachines(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_OwnedDataValues(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_RealizedClasses(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_RealizingClasses(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_IsPrimitive(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_Visibility(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_AggregationKind(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_Type(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_Index(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "feature", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionValueEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValue_OwnedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValue_OwnedDefaultElement(),
       source,
       new String[] {
       });
    addAnnotation
      (collectionValueReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (dataPkgEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedStateEvents(),
       source,
       new String[] {
       });
    addAnnotation
      (domainElementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_Ordered(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_Unique(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_MinInclusive(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_MaxInclusive(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
       });
    addAnnotation
      (getOperation_AllocatingOperations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatedOperations(),
       source,
       new String[] {
       });
    addAnnotation
      (getOperation_RealizedExchangeItems(),
       source,
       new String[] {
       });
    addAnnotation
      (parameterEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getParameter_Direction(),
       source,
       new String[] {
       });
    addAnnotation
      (getParameter_PassingMode(),
       source,
       new String[] {
       });
    addAnnotation
      (parameterDirectionEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (passingModeEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (propertyEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getProperty_AggregationKind(),
       source,
       new String[] {
       });
    addAnnotation
      (getProperty_IsDerived(),
       source,
       new String[] {
       });
    addAnnotation
      (getProperty_IsReadOnly(),
       source,
       new String[] {
       });
    addAnnotation
      (getProperty_IsPartOfKey(),
       source,
       new String[] {
       });
    addAnnotation
      (synchronismKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (serviceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getService_SynchronismKind(),
       source,
       new String[] {
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
       });
    addAnnotation
      (unionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getUnion_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnion_DefaultProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnion_ContainedUnionProperties(),
       source,
       new String[] {
         "feature", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (unionPropertyEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
       });
    addAnnotation
      (unitEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPort_OwnedProtocols(),
       source,
       new String[] {
       });
    addAnnotation
      (getPort_ProvidedInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (getPort_RequiredInterfaces(),
       source,
       new String[] {
       });
    addAnnotation
      (exchangeItemEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItem_ExchangeMechanism(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItem_OwnedElements(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItem_OwnedExchangeItemInstances(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItem_AllocatorInterfaces(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizedExchangeItems(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItem_RealizingExchangeItems(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizingOperations(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemElementEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemElement_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemElement_Direction(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemElement_Composite(),
       source,
       new String[] {
       });
    addAnnotation
      (getExchangeItemElement_ReferencedProperties(),
       source,
       new String[] {
       });
    addAnnotation
      (exchangeItemInstanceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (exchangeMechanismEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (elementKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (collectionKindEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractEventOperation_InvokingSequenceMessages(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
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
      (abstractInstanceEClass,
       source,
       new String[] {
         "Label", "AbstractInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum,
       source,
       new String[] {
         "Label", "AggregationKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationPkgEClass,
       source,
       new String[] {
         "Label", "AssociationPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
         "Label", "ownedAssociations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationEClass,
       source,
       new String[] {
         "Label", "Association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
         "Label", "members" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
         "Label", "navigable" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (classEClass,
       source,
       new String[] {
         "Label", "Class" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
         "Label", "keyParts" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionEClass,
       source,
       new String[] {
         "Label", "Collection" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Type(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "Label", "operations" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataPkgEClass,
       source,
       new String[] {
         "Label", "DataPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
         "Label", "ownedDataPkgs" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
         "Label", "ownedClasses" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
         "Label", "ownedKeyParts" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
         "Label", "ownedCollections" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
         "Label", "ownedUnits" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
         "Label", "ownedDataTypes" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
         "Label", "ownedSignals" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
         "Label", "ownedMessages" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
         "Label", "ownedExceptions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedStateEvents(),
       source,
       new String[] {
         "Label", "ownedStateEvents" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (domainElementEClass,
       source,
       new String[] {
         "Label", "DomainElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
         "Label", "KeyPart" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
         "Label", "property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedDefaultValue(),
       source,
       new String[] {
         "Label", "Default Value" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
         "Label", "Min Value" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
         "Label", "Max Value" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedNullValue(),
       source,
       new String[] {
         "Label", "Null value" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
         "Label", "Min Card" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
         "Label", "Min Length" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
         "Label", "Max Card" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
         "Label", "Max Length" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationEClass,
       source,
       new String[] {
         "Label", "Operation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
         "Label", "parameters" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationAllocationEClass,
       source,
       new String[] {
         "Label", "Operation Allocation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEClass,
       source,
       new String[] {
         "Label", "Parameter" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum,
       source,
       new String[] {
         "Label", "ParameterDirection" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum,
       source,
       new String[] {
         "Label", "PassingMode" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (propertyEClass,
       source,
       new String[] {
         "Label", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
         "Label", "association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (serviceEClass,
       source,
       new String[] {
         "Label", "Service" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
         "Label", "thrownExceptions" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_Messages(),
       source,
       new String[] {
         "Label", "messages" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_MessageReferences(),
       source,
       new String[] {
         "Label", "messageReferences" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionEClass,
       source,
       new String[] {
         "Label", "Union" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
         "Label", "discriminant" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionPropertyEClass,
       source,
       new String[] {
         "Label", "UnionProperty" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
         "Label", "qualifier" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unitEClass,
       source,
       new String[] {
         "Label", "Unit" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portEClass,
       source,
       new String[] {
         "Label", "Port" //$NON-NLS-1$ //$NON-NLS-2$
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
      (abstractInstanceEClass,
       source,
       new String[] {
         "metaclass", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum,
       source,
       new String[] {
         "enum", "AggregationKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "NONE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "SHARED" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "enumLiteral", "COMPOSITE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationPkgEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationEClass,
       source,
       new String[] {
         "metaclass", "Association", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
         "featureName", "memberEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
         "featureName", "navigableOwnedEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Association" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (classEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Class" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_IsPrimitive(),
       source,
       new String[] {
         "featureName", "isPrimitive", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Class", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionEClass,
       source,
       new String[] {
         "metaclass", "Component", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Collection" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "featureName", "ownedOperation", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Class" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataPkgEClass,
       source,
       new String[] {
         "metaclass", "Package", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.DataPkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (domainElementEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.DomainElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
         "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.KeyPart" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
         "featureName", "supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Dependency" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
         "featureName", "lowerValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
         "featureName", "upperValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
         "featureName", "lowerValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
         "featureName", "lowerValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
         "featureName", "upperValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
         "featureName", "upperValue", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "MultiplicityElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationEClass,
       source,
       new String[] {
         "metaclass", "Operation" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
         "featureName", "ownedParameter", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "BehavioralFeature" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEClass,
       source,
       new String[] {
         "metaclass", "Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Parameter" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_Direction(),
       source,
       new String[] {
         "featureName", "direction", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Parameter" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_PassingMode(),
       source,
       new String[] {
         "featureName", "passingMode", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Parameter" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum,
       source,
       new String[] {
         "enum", "ParameterDirectionKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(0),
       source,
       new String[] {
         "enumLiteral", "IN" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "OUT" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "INOUT" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(3),
       source,
       new String[] {
         "enumLiteral", "RETURN" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum,
       source,
       new String[] {
         "enum", "PassingMode" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "BY_REF" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "BY_VALUE" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (propertyEClass,
       source,
       new String[] {
         "metaclass", "Property", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_AggregationKind(),
       source,
       new String[] {
         "featureName", "aggregation", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsReadOnly(),
       source,
       new String[] {
         "featureName", "isReadOnly", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "StructuralFeature" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsPartOfKey(),
       source,
       new String[] {
         "featureName", "isPartOfKey", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Property", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
         "featureName", "association", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Property" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum,
       source,
       new String[] {
         "enum", "SynchronismKind" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "enumLiteral", "SYNCHRONOUS" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "enumLiteral", "ASYNCHRONOUS" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (serviceEClass,
       source,
       new String[] {
         "metaclass", "Operation", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Service" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_SynchronismKind(),
       source,
       new String[] {
         "featureName", "synchronismKind", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.Service" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
         "featureName", "raisedException", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "BehavioralFeature" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_MessageReferences(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Union" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionPropertyEClass,
       source,
       new String[] {
         "metaclass", "Property", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.UnionProperty" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unitEClass,
       source,
       new String[] {
         "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Unit" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portEClass,
       source,
       new String[] {
         "metaclass", "Port" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Direction(),
       source,
       new String[] {
         "featureName", "direction", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Parameter" //$NON-NLS-1$ //$NON-NLS-2$
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
      (abstractInstanceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Property", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractInstance_RepresentingInstanceRoles(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::AggregationKind", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This enumeration literal has no UML-SysML equivalence" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::AggregationKind::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::AggregationKind::shared", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (aggregationKindEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::AggregationKind::composite", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_Visibility(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::visibility", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Association stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (associationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Association", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Association::ownedEnd, uml::Association::memberEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [2..2]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Association::navigableOwnedEnd", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (classEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_IsPrimitive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which KeyPart stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_OwnedStateMachines(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Class::nestedClassifier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Class::nestedClassifier elements on which StateMachine stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_OwnedDataValues(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which DataValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_OwnedInformationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "DataType chosen because it has a containment reference to Operations, which is required to simplify the transformation of Collection::operations derived reference", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_IsPrimitive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Visibility(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::visibility", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_AggregationKind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Type(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_Index(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::DataType::ownedOperation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractCollectionValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValue_OwnedElements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValue_OwnedDefaultElement(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionValueReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataPkgEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::nestedPackage elements on which DataPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Class stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which KeyPart stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Collectionstereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Unit stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which DataType stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Signal stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Message stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which Excpetion stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataPkg_OwnedStateEvents(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (domainElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Dependency::supplier", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Multiplicity must be [1..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (multiplicityElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::MultiplicityElement", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_Ordered(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_Unique(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_MinInclusive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_MaxInclusive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedDefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which DataValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedNullValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which DataValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Operation::ownedParameter", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatingOperations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatedOperations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedOperationAllocation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which OperationAlllocation stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_OwnedExchangeItemRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which ExchangeItemRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (operationAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatedOperation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatingOperation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_Direction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::direction", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getParameter_PassingMode(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterDirectionKind", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterDirectionKind::in", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterDirectionKind::out", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterDirectionKind::inout", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ParameterDirectionKind::return", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (parameterDirectionEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (passingModeEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (propertyEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Property", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_AggregationKind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Property::aggregation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsDerived(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Property::isDerived", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsReadOnly(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Property::isReadOnly", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_IsPartOfKey(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Property::owningAssociation, uml::Property::association", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "No equivalent enum on uml Operations. The two other candidates (CallOperationAction::isSynchronous or Message::messageSort) are not appropriate (different semantics)", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (synchronismKindEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (serviceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Operation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_SynchronismKind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Study the link with CallOperationAction::isSynchronous or Message::messageSort", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Operation::raisedException", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Exception should extend uml::Type", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_Messages(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_MessageReferences(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement::clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which MessageReference stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_DefaultProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_ContainedUnionProperties(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unionPropertyEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Property", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Order must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unitEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Blocks::Unit", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Port", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedProtocols(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_ProvidedInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_RequiredInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedPortRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which PortRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OwnedPortAllocations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::nearestpackage", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Elements are contained in the nearest possible parent container.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Some elements on which PortAllocation stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizedPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizingPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (portAllocationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "SysML::Allocations::Allocate", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Operation", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_ExchangeMechanism(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_OwnedElements(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Operation::ownedParameter", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Operation::ownedParameter elements on which ExchangeItemElement stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_OwnedInformationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_OwnedExchangeItemInstances(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_AllocatorInterfaces(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemElementEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Parameter", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Direction(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Parameter::direction", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_Composite(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemElement_ReferencedProperties(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemInstanceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (informationRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Comment", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeMechanismEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (elementKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This enumeration literal has no UML-SysML equivalence" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (collectionKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "This enumeration literal has no UML-SysML equivalence" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (exchangeItemRealizationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Realization", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizedItem(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizingOperation(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEventOperationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::ReceiveOperationEvent::operation\r\numl::SentOperationEvent::operation", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEventOperation_InvokingSequenceMessages(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAbstractInstance_RepresentingInstanceRoles(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "representedInstance" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_RealizedClasses(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Class.outgoingTraces(self, ir);\r\nInformationRealization.targetElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getClass_RealizingClasses(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Class.incomingTraces(self, ir);\r\nInformationRealization.sourceElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatingOperations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Operation.incomingTraces(self, oa);\r\nOperationAllocation.allocatingOperation(oa, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_AllocatedOperations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Operation.outgoingTraces(self, oa);\r\nOperationAllocation.allocatedOperation(oa, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperation_RealizedExchangeItems(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "Operation.outgoingTraces(self, eir);\r\nExchangeItemRealization.realizedItem(eir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatedOperation(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOperationAllocation_AllocatingOperation(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
         "viatra.variant", "opposite", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "navigableMembers" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getService_Messages(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "messageReferences.message" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnion_ContainedUnionProperties(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ownedFeatures" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortRealizations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_IncomingPortAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "incomingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPort_OutgoingPortAllocations(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "outgoingTraces" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizedPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortRealization_RealizingPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortAllocation_AllocatedPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPortAllocation_AllocatingPort(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_AllocatorInterfaces(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ExchangeItemAllocation.allocatedItem(eia, self);\r\nExchangeItemAllocation.allocatingInterface(eia, target);" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizedExchangeItems(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ExchangeItem.outgoingTraces(self, ir);\r\nInformationRealization.targetElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizingExchangeItems(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ExchangeItem.incomingTraces(self, ir);\r\nInformationRealization.sourceElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItem_RealizingOperations(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "ExchangeItem.incomingTraces(self, eir);\r\nExchangeItemRealization.realizingOperation(eir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizedItem(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getExchangeItemRealization_RealizingOperation(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEventOperation_InvokingSequenceMessages(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "SequenceMessage.receivingEnd.event(target, ero);\r\n\tEventReceiptOperation.operation(ero, self);\r\n} or {\r\n\tSequenceMessage.sendingEnd.event(target, eso);\r\n\tEventSentOperation.operation(eso, self);" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getAssociationPkg_OwnedAssociations(),
       source,
       new String[] {
       });
    addAnnotation
      (getAssociation_OwnedMembers(),
       source,
       new String[] {
       });
    addAnnotation
      (getAssociation_NavigableMembers(),
       source,
       new String[] {
       });
    addAnnotation
      (getClass_KeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollection_ContainedOperations(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValueReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getCollectionValueReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedDataPkgs(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedClasses(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedKeyParts(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedCollections(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedUnits(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedDataTypes(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedSignals(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedMessages(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedExceptions(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataPkg_OwnedStateEvents(),
       source,
       new String[] {
       });
    addAnnotation
      (getKeyPart_Property(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinCard(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMinLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxCard(),
       source,
       new String[] {
       });
    addAnnotation
      (getMultiplicityElement_OwnedMaxLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getOperation_OwnedParameters(),
       source,
       new String[] {
       });
    addAnnotation
      (getProperty_Association(),
       source,
       new String[] {
       });
    addAnnotation
      (getService_ThrownExceptions(),
       source,
       new String[] {
       });
    addAnnotation
      (getService_Messages(),
       source,
       new String[] {
       });
    addAnnotation
      (getService_MessageReferences(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnion_Discriminant(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnionProperty_Qualifier(),
       source,
       new String[] {
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
      (associationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (keyPartEClass,
       source,
       new String[] {
       });
  }

} //InformationPackageImpl
