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
package org.polarsys.capella.core.data.information.datatype.impl;

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
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.NumericTypeKind;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.data.information.datatype.StringType;
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
public class DatatypePackageImpl extends EPackageImpl implements DatatypePackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass dataTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass booleanTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass enumerationEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass stringTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass numericTypeEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass physicalQuantityEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum numericTypeKindEEnum = null;

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
   * @see org.polarsys.capella.core.data.information.datatype.DatatypePackage#eNS_URI
   * @see #init()
   * @generated
   */
	private DatatypePackageImpl() {
    super(eNS_URI, DatatypeFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link DatatypePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static DatatypePackage init() {
    if (isInited) return (DatatypePackage)EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredDatatypePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    DatatypePackageImpl theDatatypePackage = registeredDatatypePackage instanceof DatatypePackageImpl ? (DatatypePackageImpl)registeredDatatypePackage : new DatatypePackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);
    DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(registeredPackage instanceof DatavaluePackageImpl ? registeredPackage : DatavaluePackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    CsPackageImpl theCsPackage = (CsPackageImpl)(registeredPackage instanceof CsPackageImpl ? registeredPackage : CsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theDatatypePackage.createPackageContents();
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
    theDatavaluePackage.createPackageContents();
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theDatatypePackage.initializePackageContents();
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
    theDatavaluePackage.initializePackageContents();
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDatatypePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DatatypePackage.eNS_URI, theDatatypePackage);
    return theDatatypePackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDataType() {
    return dataTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataType_Discrete() {
    return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataType_MinInclusive() {
    return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataType_MaxInclusive() {
    return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataType_Pattern() {
    return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataType_Visibility() {
    return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataType_DefaultValue() {
    return (EReference)dataTypeEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataType_NullValue() {
    return (EReference)dataTypeEClass.getEStructuralFeatures().get(6);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataType_OwnedInformationRealizations() {
    return (EReference)dataTypeEClass.getEStructuralFeatures().get(7);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataType_RealizedDataTypes() {
    return (EReference)dataTypeEClass.getEStructuralFeatures().get(8);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataType_RealizingDataTypes() {
    return (EReference)dataTypeEClass.getEStructuralFeatures().get(9);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBooleanType() {
    return booleanTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBooleanType_OwnedLiterals() {
    return (EReference)booleanTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBooleanType_OwnedDefaultValue() {
    return (EReference)booleanTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEnumeration() {
    return enumerationEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_OwnedLiterals() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_OwnedDefaultValue() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_OwnedNullValue() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_OwnedMinValue() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_OwnedMaxValue() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumeration_DomainType() {
    return (EReference)enumerationEClass.getEStructuralFeatures().get(5);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getStringType() {
    return stringTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringType_OwnedDefaultValue() {
    return (EReference)stringTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringType_OwnedNullValue() {
    return (EReference)stringTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringType_OwnedMinLength() {
    return (EReference)stringTypeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringType_OwnedMaxLength() {
    return (EReference)stringTypeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getNumericType() {
    return numericTypeEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getNumericType_Kind() {
    return (EAttribute)numericTypeEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericType_OwnedDefaultValue() {
    return (EReference)numericTypeEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericType_OwnedNullValue() {
    return (EReference)numericTypeEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericType_OwnedMinValue() {
    return (EReference)numericTypeEClass.getEStructuralFeatures().get(3);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericType_OwnedMaxValue() {
    return (EReference)numericTypeEClass.getEStructuralFeatures().get(4);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getPhysicalQuantity() {
    return physicalQuantityEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getPhysicalQuantity_Unit() {
    return (EReference)physicalQuantityEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getNumericTypeKind() {
    return numericTypeKindEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DatatypeFactory getDatatypeFactory() {
    return (DatatypeFactory)getEFactoryInstance();
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
    dataTypeEClass = createEClass(DATA_TYPE);
    createEAttribute(dataTypeEClass, DATA_TYPE__DISCRETE);
    createEAttribute(dataTypeEClass, DATA_TYPE__MIN_INCLUSIVE);
    createEAttribute(dataTypeEClass, DATA_TYPE__MAX_INCLUSIVE);
    createEAttribute(dataTypeEClass, DATA_TYPE__PATTERN);
    createEAttribute(dataTypeEClass, DATA_TYPE__VISIBILITY);
    createEReference(dataTypeEClass, DATA_TYPE__DEFAULT_VALUE);
    createEReference(dataTypeEClass, DATA_TYPE__NULL_VALUE);
    createEReference(dataTypeEClass, DATA_TYPE__OWNED_INFORMATION_REALIZATIONS);
    createEReference(dataTypeEClass, DATA_TYPE__REALIZED_DATA_TYPES);
    createEReference(dataTypeEClass, DATA_TYPE__REALIZING_DATA_TYPES);

    booleanTypeEClass = createEClass(BOOLEAN_TYPE);
    createEReference(booleanTypeEClass, BOOLEAN_TYPE__OWNED_LITERALS);
    createEReference(booleanTypeEClass, BOOLEAN_TYPE__OWNED_DEFAULT_VALUE);

    enumerationEClass = createEClass(ENUMERATION);
    createEReference(enumerationEClass, ENUMERATION__OWNED_LITERALS);
    createEReference(enumerationEClass, ENUMERATION__OWNED_DEFAULT_VALUE);
    createEReference(enumerationEClass, ENUMERATION__OWNED_NULL_VALUE);
    createEReference(enumerationEClass, ENUMERATION__OWNED_MIN_VALUE);
    createEReference(enumerationEClass, ENUMERATION__OWNED_MAX_VALUE);
    createEReference(enumerationEClass, ENUMERATION__DOMAIN_TYPE);

    stringTypeEClass = createEClass(STRING_TYPE);
    createEReference(stringTypeEClass, STRING_TYPE__OWNED_DEFAULT_VALUE);
    createEReference(stringTypeEClass, STRING_TYPE__OWNED_NULL_VALUE);
    createEReference(stringTypeEClass, STRING_TYPE__OWNED_MIN_LENGTH);
    createEReference(stringTypeEClass, STRING_TYPE__OWNED_MAX_LENGTH);

    numericTypeEClass = createEClass(NUMERIC_TYPE);
    createEAttribute(numericTypeEClass, NUMERIC_TYPE__KIND);
    createEReference(numericTypeEClass, NUMERIC_TYPE__OWNED_DEFAULT_VALUE);
    createEReference(numericTypeEClass, NUMERIC_TYPE__OWNED_NULL_VALUE);
    createEReference(numericTypeEClass, NUMERIC_TYPE__OWNED_MIN_VALUE);
    createEReference(numericTypeEClass, NUMERIC_TYPE__OWNED_MAX_VALUE);

    physicalQuantityEClass = createEClass(PHYSICAL_QUANTITY);
    createEReference(physicalQuantityEClass, PHYSICAL_QUANTITY__UNIT);

    // Create enums
    numericTypeKindEEnum = createEEnum(NUMERIC_TYPE_KIND);
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
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    dataTypeEClass.getESuperTypes().add(theCapellacorePackage.getGeneralizableElement());
    dataTypeEClass.getESuperTypes().add(theDatavaluePackage.getDataValueContainer());
    dataTypeEClass.getESuperTypes().add(theModellingcorePackage.getFinalizableElement());
    booleanTypeEClass.getESuperTypes().add(this.getDataType());
    enumerationEClass.getESuperTypes().add(this.getDataType());
    stringTypeEClass.getESuperTypes().add(this.getDataType());
    numericTypeEClass.getESuperTypes().add(this.getDataType());
    physicalQuantityEClass.getESuperTypes().add(this.getNumericType());

    // Initialize classes and features; add operations and parameters
    initEClass(dataTypeEClass, DataType.class, "DataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getDataType_Discrete(), ecorePackage.getEBoolean(), "discrete", "true", 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getDataType_MinInclusive(), ecorePackage.getEBoolean(), "minInclusive", "true", 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getDataType_MaxInclusive(), ecorePackage.getEBoolean(), "maxInclusive", "true", 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(getDataType_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getDataType_Visibility(), theCapellacorePackage.getVisibilityKind(), "visibility", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataType_DefaultValue(), theDatavaluePackage.getDataValue(), null, "defaultValue", null, 0, 1, DataType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataType_NullValue(), theDatavaluePackage.getDataValue(), null, "nullValue", null, 0, 1, DataType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataType_OwnedInformationRealizations(), theInformationPackage.getInformationRealization(), null, "ownedInformationRealizations", null, 0, -1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataType_RealizedDataTypes(), this.getDataType(), this.getDataType_RealizingDataTypes(), "realizedDataTypes", null, 0, -1, DataType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataType_RealizingDataTypes(), this.getDataType(), this.getDataType_RealizedDataTypes(), "realizingDataTypes", null, 0, -1, DataType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(booleanTypeEClass, BooleanType.class, "BooleanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getBooleanType_OwnedLiterals(), theDatavaluePackage.getLiteralBooleanValue(), null, "ownedLiterals", null, 0, 2, BooleanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBooleanType_OwnedDefaultValue(), theDatavaluePackage.getAbstractBooleanValue(), null, "ownedDefaultValue", null, 0, 1, BooleanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(enumerationEClass, Enumeration.class, "Enumeration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEnumeration_OwnedLiterals(), theDatavaluePackage.getEnumerationLiteral(), null, "ownedLiterals", null, 0, -1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumeration_OwnedDefaultValue(), theDatavaluePackage.getAbstractEnumerationValue(), null, "ownedDefaultValue", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumeration_OwnedNullValue(), theDatavaluePackage.getAbstractEnumerationValue(), null, "ownedNullValue", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumeration_OwnedMinValue(), theDatavaluePackage.getAbstractEnumerationValue(), null, "ownedMinValue", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumeration_OwnedMaxValue(), theDatavaluePackage.getAbstractEnumerationValue(), null, "ownedMaxValue", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumeration_DomainType(), this.getDataType(), null, "domainType", null, 0, 1, Enumeration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(stringTypeEClass, StringType.class, "StringType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getStringType_OwnedDefaultValue(), theDatavaluePackage.getAbstractStringValue(), null, "ownedDefaultValue", null, 0, 1, StringType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getStringType_OwnedNullValue(), theDatavaluePackage.getAbstractStringValue(), null, "ownedNullValue", null, 0, 1, StringType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getStringType_OwnedMinLength(), theDatavaluePackage.getNumericValue(), null, "ownedMinLength", null, 0, 1, StringType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getStringType_OwnedMaxLength(), theDatavaluePackage.getNumericValue(), null, "ownedMaxLength", null, 0, 1, StringType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(numericTypeEClass, NumericType.class, "NumericType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getNumericType_Kind(), this.getNumericTypeKind(), "kind", "INTEGER", 0, 1, NumericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(getNumericType_OwnedDefaultValue(), theDatavaluePackage.getNumericValue(), null, "ownedDefaultValue", null, 0, 1, NumericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getNumericType_OwnedNullValue(), theDatavaluePackage.getNumericValue(), null, "ownedNullValue", null, 0, 1, NumericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getNumericType_OwnedMinValue(), theDatavaluePackage.getNumericValue(), null, "ownedMinValue", null, 0, 1, NumericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getNumericType_OwnedMaxValue(), theDatavaluePackage.getNumericValue(), null, "ownedMaxValue", null, 0, 1, NumericType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(physicalQuantityEClass, PhysicalQuantity.class, "PhysicalQuantity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getPhysicalQuantity_Unit(), theInformationPackage.getUnit(), null, "unit", null, 0, 1, PhysicalQuantity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(numericTypeKindEEnum, NumericTypeKind.class, "NumericTypeKind"); //$NON-NLS-1$
    addEEnumLiteral(numericTypeKindEEnum, NumericTypeKind.INTEGER);
    addEEnumLiteral(numericTypeKindEEnum, NumericTypeKind.FLOAT);

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
         "description", "sub-package containing the definition of the predefined data types\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataTypeEClass,
       source,
       new String[] {
         "description", "A data type is a type whose instances are identified only by their value. A DataType may contain attributes to support the\r\nmodeling of structured data types\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "DataTypes should be created for every grouping of data in the system that makes sense from an application standpoint.\r\nIt is especially valuable to avoid redondancy of data structure definitions, and to breakdown the complexity of data structures into manageable bits.", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Discrete(),
       source,
       new String[] {
         "description", "specifies whether or not this data type characterizes a discrete value (versus continuous value)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_MinInclusive(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_MaxInclusive(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Pattern(),
       source,
       new String[] {
         "description", "textual specification of a constraint associated to this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Visibility(),
       source,
       new String[] {
         "description", "refer to VisibilityKind description\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to VisibilityKind definition", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_DefaultValue(),
       source,
       new String[] {
         "description", "allows to specify a default value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_NullValue(),
       source,
       new String[] {
         "description", "allows to specify the nature/value of the \"null\" value for this type of data\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_RealizedDataTypes(),
       source,
       new String[] {
         "description", "class(es) realized by this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_RealizingDataTypes(),
       source,
       new String[] {
         "description", "class(es) realizing this class", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanTypeEClass,
       source,
       new String[] {
         "description", "A boolean is an instance of PrimitiveType. In the metamodel, Boolean defines an enumeration that denotes a logical\r\ncondition. Its enumeration literals are:\r\n- true - The Boolean condition is satisfied.\r\n- false - The Boolean condition is not satisfied\r\n[source: UML superstructure v2.2]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
         "description", "the literals that are contained in this enumeration\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
         "description", "default value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationEClass,
       source,
       new String[] {
         "description", "An enumeration is a kind of data type, whose instances may be any of a number of user-defined enumeration literals\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "an enumeration should be created/used whenever all possible values for a parameter are predefined (and the number of values is reasonably limited...)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
         "description", "the literals that are contained in this enumeration\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedDefaultValue(),
       source,
       new String[] {
         "description", "default value among this enumeration\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedNullValue(),
       source,
       new String[] {
         "description", "Null value among this enumeration\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
         "description", "specification of the minimum value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
         "description", "specification of the maximum value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_DomainType(),
       source,
       new String[] {
         "description", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringTypeEClass,
       source,
       new String[] {
         "description", "A string is a sequence of characters in some suitable character set used to display information about the model\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
         "description", "the default value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
         "description", "the neutral value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
         "description", "specification of the minimum length of the string\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
         "description", "specification of the maximum length of the string\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeEClass,
       source,
       new String[] {
         "description", "Characterizes a value that can be expressed by a sequence of numbers\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_Kind(),
       source,
       new String[] {
         "description", "specifies the kind of this numeric type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "refer to NumericTypeKind", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
         "description", "the default value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
         "description", "the neutral value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
         "description", "specification of the minimum value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
         "description", "specification of the maximum value for this data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalQuantityEClass,
       source,
       new String[] {
         "description", "A Physical Quantity is a measurable value of a physical property of a thing or a movement. It referes to a Unit.\r\n\r\nA Dimension (SysML notion of Physical Quantity) is a kind of quantity that may be stated by means of defined units. For example, the dimension of length may be measured by units of meters, kilometers, or feet\r\n[source: SysML specification v1.1]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system/logical/physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
         "description", "the unit of this physical dimension\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum,
       source,
       new String[] {
         "description", "Specifies the kind of this numeric data type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Used when the numeric type refers to an integer value\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Used when the numeric type refers to a float value\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getDataType_Discrete(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_MinInclusive(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_MaxInclusive(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_Pattern(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_Visibility(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_DefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_NullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_RealizedDataTypes(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataType_RealizingDataTypes(),
       source,
       new String[] {
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanTypeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (enumerationEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_DomainType(),
       source,
       new String[] {
       });
    addAnnotation
      (stringTypeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
       });
    addAnnotation
      (numericTypeEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_Kind(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (physicalQuantityEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
       });
    addAnnotation
      (numericTypeKindEEnum,
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
      (dataTypeEClass,
       source,
       new String[] {
         "Label", "DataType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanTypeEClass,
       source,
       new String[] {
         "Label", "BooleanType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
         "Label", "literals" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
         "Label", "defaultValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationEClass,
       source,
       new String[] {
         "Label", "Enumeration" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
         "Label", "literals" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
         "Label", "minValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
         "Label", "maxValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringTypeEClass,
       source,
       new String[] {
         "Label", "StringType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
         "Label", "defaultValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
         "Label", "nullValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
         "Label", "minLength" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
         "Label", "maxLength" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeEClass,
       source,
       new String[] {
         "Label", "NumericType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
         "Label", "defaultValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
         "Label", "nullValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
         "Label", "minValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
         "Label", "maxValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalQuantityEClass,
       source,
       new String[] {
         "Label", "PhysicalDimension" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
         "Label", "unit" //$NON-NLS-1$ //$NON-NLS-2$
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
      (dataTypeEClass,
       source,
       new String[] {
         "metaclass", "DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.DataType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Discrete(),
       source,
       new String[] {
         "featureName", "isDiscreet", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Pattern(),
       source,
       new String[] {
         "featureName", "constraint", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "eng.DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanTypeEClass,
       source,
       new String[] {
         "metaclass", "DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.BooleanType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
         "featureName", "ownedLiteral", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Enumeration" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationEClass,
       source,
       new String[] {
         "metaclass", "Enumeration", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.Enumeration" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
         "featureName", "ownedLiteral", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Enumeration" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringTypeEClass,
       source,
       new String[] {
         "metaclass", "DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.StringType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeEClass,
       source,
       new String[] {
         "metaclass", "DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.NumericType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalQuantityEClass,
       source,
       new String[] {
         "metaclass", "DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.PhysicalDimension" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
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
      (dataTypeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Discrete(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_MinInclusive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_MaxInclusive(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Pattern(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_Visibility(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::NamedElement:visibility", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_DefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_NullValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_OwnedInformationRealizations(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanTypeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::PrimitiveType", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which BooleanValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Enumeration", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Enumeration::ownedLiteral", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedDefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which EnumerationValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedNullValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which EnumerationValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which NumericType stereotype or any stereotype that inherits from it is applied", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Treat difference between default, null, min and max values\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::NamedElement::clientDependency elements on which NumericType stereotype or any stereotype that inherits from it is applied", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Treat difference between default, null, min and max values\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumeration_DomainType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringTypeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which StringValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which StringValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which FunctionRealization stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which UnlimitedNaturalValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::DataType", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_Kind(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "Elements on which NumericValue stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (physicalQuantityEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "should be SysML::Blocks::ValueType, but its parent is concrete and already mapped (to uml::DataType), \r\nso do not map this one too to prevent Papyrus errors.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericTypeKindEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
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
      (getDataType_DefaultValue(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "BooleanType.ownedDefaultValue(self, target);\r\n} or {\r\n\tStringType.ownedDefaultValue(self, target);\r\n} or {\r\n\tEnumeration.ownedDefaultValue(self, target);\r\n} or {\r\n\tNumericType.ownedDefaultValue(self, target);\r\n} or {\r\n\tPhysicalQuantity.ownedDefaultValue(self, target);\r\n\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_NullValue(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "StringType.ownedNullValue(self, target);\r\n} or {\r\n\tEnumeration.ownedNullValue(self, target);\r\n} or {\r\n\tNumericType.ownedNullValue(self, target);\r\n} or {\r\n\tPhysicalQuantity.ownedNullValue(self, target);\r\n\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_RealizedDataTypes(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "DataType.outgoingTraces(self, ir);\r\nInformationRealization.targetElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataType_RealizingDataTypes(),
       source,
       new String[] {
         "viatra.variant", "patternbody", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "DataType.incomingTraces(self, ir);\r\nInformationRealization.sourceElement(ir, target); " //$NON-NLS-1$ //$NON-NLS-2$
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
      (getBooleanType_OwnedLiterals(),
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedLiterals(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumeration_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedMinLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringType_OwnedMaxLength(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedDefaultValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedNullValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedMinValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericType_OwnedMaxValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getPhysicalQuantity_Unit(),
       source,
       new String[] {
       });
  }

} //DatatypePackageImpl
