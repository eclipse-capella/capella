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
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.BinaryExpression;
import org.polarsys.capella.core.data.information.datavalue.BinaryOperator;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DataValueContainer;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.data.information.datavalue.LiteralBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.information.datavalue.LiteralStringValue;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.data.information.datavalue.UnaryExpression;
import org.polarsys.capella.core.data.information.datavalue.UnaryOperator;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
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
public class DatavaluePackageImpl extends EPackageImpl implements DatavaluePackage {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass dataValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass dataValueContainerEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractBooleanValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass literalBooleanValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass booleanReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractEnumerationValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass enumerationLiteralEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass enumerationReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractStringValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass literalStringValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass stringReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass numericValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass literalNumericValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass numericReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractComplexValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass complexValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass complexValueReferenceEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass valuePartEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass abstractExpressionValueEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass binaryExpressionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass unaryExpressionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EClass opaqueExpressionEClass = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum binaryOperatorEEnum = null;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private EEnum unaryOperatorEEnum = null;

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
   * @see org.polarsys.capella.core.data.information.datavalue.DatavaluePackage#eNS_URI
   * @see #init()
   * @generated
   */
	private DatavaluePackageImpl() {
    super(eNS_URI, DatavalueFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link DatavaluePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
	public static DatavaluePackage init() {
    if (isInited) return (DatavaluePackage)EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI);

    // Obtain or create and register package
    Object registeredDatavaluePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
    DatavaluePackageImpl theDatavaluePackage = registeredDatavaluePackage instanceof DatavaluePackageImpl ? (DatavaluePackageImpl)registeredDatavaluePackage : new DatavaluePackageImpl();

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
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI);
    CsPackageImpl theCsPackage = (CsPackageImpl)(registeredPackage instanceof CsPackageImpl ? registeredPackage : CsPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI);
    FaPackageImpl theFaPackage = (FaPackageImpl)(registeredPackage instanceof FaPackageImpl ? registeredPackage : FaPackage.eINSTANCE);
    registeredPackage = EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI);
    InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(registeredPackage instanceof InteractionPackageImpl ? registeredPackage : InteractionPackage.eINSTANCE);

    // Create package meta-data objects
    theDatavaluePackage.createPackageContents();
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
    theCsPackage.createPackageContents();
    theFaPackage.createPackageContents();
    theInteractionPackage.createPackageContents();

    // Initialize created meta-data
    theDatavaluePackage.initializePackageContents();
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
    theCsPackage.initializePackageContents();
    theFaPackage.initializePackageContents();
    theInteractionPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDatavaluePackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DatavaluePackage.eNS_URI, theDatavaluePackage);
    return theDatavaluePackage;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDataValue() {
    return dataValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getDataValue_Abstract() {
    return (EAttribute)dataValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataValue_Type() {
    return (EReference)dataValueEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getDataValueContainer() {
    return dataValueContainerEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getDataValueContainer_OwnedDataValues() {
    return (EReference)dataValueContainerEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractBooleanValue() {
    return abstractBooleanValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractBooleanValue_BooleanType() {
    return (EReference)abstractBooleanValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLiteralBooleanValue() {
    return literalBooleanValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getLiteralBooleanValue_Value() {
    return (EAttribute)literalBooleanValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBooleanReference() {
    return booleanReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBooleanReference_ReferencedValue() {
    return (EReference)booleanReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBooleanReference_ReferencedProperty() {
    return (EReference)booleanReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractEnumerationValue() {
    return abstractEnumerationValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractEnumerationValue_EnumerationType() {
    return (EReference)abstractEnumerationValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEnumerationLiteral() {
    return enumerationLiteralEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumerationLiteral_DomainValue() {
    return (EReference)enumerationLiteralEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getEnumerationReference() {
    return enumerationReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumerationReference_ReferencedValue() {
    return (EReference)enumerationReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getEnumerationReference_ReferencedProperty() {
    return (EReference)enumerationReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractStringValue() {
    return abstractStringValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractStringValue_StringType() {
    return (EReference)abstractStringValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLiteralStringValue() {
    return literalStringValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getLiteralStringValue_Value() {
    return (EAttribute)literalStringValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getStringReference() {
    return stringReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringReference_ReferencedValue() {
    return (EReference)stringReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getStringReference_ReferencedProperty() {
    return (EReference)stringReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getNumericValue() {
    return numericValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericValue_Unit() {
    return (EReference)numericValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericValue_NumericType() {
    return (EReference)numericValueEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getLiteralNumericValue() {
    return literalNumericValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getLiteralNumericValue_Value() {
    return (EAttribute)literalNumericValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getNumericReference() {
    return numericReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericReference_ReferencedValue() {
    return (EReference)numericReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getNumericReference_ReferencedProperty() {
    return (EReference)numericReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractComplexValue() {
    return abstractComplexValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractComplexValue_ComplexType() {
    return (EReference)abstractComplexValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComplexValue() {
    return complexValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComplexValue_OwnedParts() {
    return (EReference)complexValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getComplexValueReference() {
    return complexValueReferenceEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComplexValueReference_ReferencedValue() {
    return (EReference)complexValueReferenceEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getComplexValueReference_ReferencedProperty() {
    return (EReference)complexValueReferenceEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getValuePart() {
    return valuePartEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getValuePart_ReferencedProperty() {
    return (EReference)valuePartEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getValuePart_OwnedValue() {
    return (EReference)valuePartEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getAbstractExpressionValue() {
    return abstractExpressionValueEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractExpressionValue_Expression() {
    return (EAttribute)abstractExpressionValueEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getAbstractExpressionValue_UnparsedExpression() {
    return (EAttribute)abstractExpressionValueEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getAbstractExpressionValue_ExpressionType() {
    return (EReference)abstractExpressionValueEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getBinaryExpression() {
    return binaryExpressionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getBinaryExpression_Operator() {
    return (EAttribute)binaryExpressionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBinaryExpression_OwnedLeftOperand() {
    return (EReference)binaryExpressionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getBinaryExpression_OwnedRightOperand() {
    return (EReference)binaryExpressionEClass.getEStructuralFeatures().get(2);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getUnaryExpression() {
    return unaryExpressionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getUnaryExpression_Operator() {
    return (EAttribute)unaryExpressionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EReference getUnaryExpression_OwnedOperand() {
    return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EClass getOpaqueExpression() {
    return opaqueExpressionEClass;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getOpaqueExpression_Bodies() {
    return (EAttribute)opaqueExpressionEClass.getEStructuralFeatures().get(0);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EAttribute getOpaqueExpression_Languages() {
    return (EAttribute)opaqueExpressionEClass.getEStructuralFeatures().get(1);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getBinaryOperator() {
    return binaryOperatorEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EEnum getUnaryOperator() {
    return unaryOperatorEEnum;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public DatavalueFactory getDatavalueFactory() {
    return (DatavalueFactory)getEFactoryInstance();
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
    dataValueEClass = createEClass(DATA_VALUE);
    createEAttribute(dataValueEClass, DATA_VALUE__ABSTRACT);
    createEReference(dataValueEClass, DATA_VALUE__TYPE);

    dataValueContainerEClass = createEClass(DATA_VALUE_CONTAINER);
    createEReference(dataValueContainerEClass, DATA_VALUE_CONTAINER__OWNED_DATA_VALUES);

    abstractBooleanValueEClass = createEClass(ABSTRACT_BOOLEAN_VALUE);
    createEReference(abstractBooleanValueEClass, ABSTRACT_BOOLEAN_VALUE__BOOLEAN_TYPE);

    literalBooleanValueEClass = createEClass(LITERAL_BOOLEAN_VALUE);
    createEAttribute(literalBooleanValueEClass, LITERAL_BOOLEAN_VALUE__VALUE);

    booleanReferenceEClass = createEClass(BOOLEAN_REFERENCE);
    createEReference(booleanReferenceEClass, BOOLEAN_REFERENCE__REFERENCED_VALUE);
    createEReference(booleanReferenceEClass, BOOLEAN_REFERENCE__REFERENCED_PROPERTY);

    abstractEnumerationValueEClass = createEClass(ABSTRACT_ENUMERATION_VALUE);
    createEReference(abstractEnumerationValueEClass, ABSTRACT_ENUMERATION_VALUE__ENUMERATION_TYPE);

    enumerationLiteralEClass = createEClass(ENUMERATION_LITERAL);
    createEReference(enumerationLiteralEClass, ENUMERATION_LITERAL__DOMAIN_VALUE);

    enumerationReferenceEClass = createEClass(ENUMERATION_REFERENCE);
    createEReference(enumerationReferenceEClass, ENUMERATION_REFERENCE__REFERENCED_VALUE);
    createEReference(enumerationReferenceEClass, ENUMERATION_REFERENCE__REFERENCED_PROPERTY);

    abstractStringValueEClass = createEClass(ABSTRACT_STRING_VALUE);
    createEReference(abstractStringValueEClass, ABSTRACT_STRING_VALUE__STRING_TYPE);

    literalStringValueEClass = createEClass(LITERAL_STRING_VALUE);
    createEAttribute(literalStringValueEClass, LITERAL_STRING_VALUE__VALUE);

    stringReferenceEClass = createEClass(STRING_REFERENCE);
    createEReference(stringReferenceEClass, STRING_REFERENCE__REFERENCED_VALUE);
    createEReference(stringReferenceEClass, STRING_REFERENCE__REFERENCED_PROPERTY);

    numericValueEClass = createEClass(NUMERIC_VALUE);
    createEReference(numericValueEClass, NUMERIC_VALUE__UNIT);
    createEReference(numericValueEClass, NUMERIC_VALUE__NUMERIC_TYPE);

    literalNumericValueEClass = createEClass(LITERAL_NUMERIC_VALUE);
    createEAttribute(literalNumericValueEClass, LITERAL_NUMERIC_VALUE__VALUE);

    numericReferenceEClass = createEClass(NUMERIC_REFERENCE);
    createEReference(numericReferenceEClass, NUMERIC_REFERENCE__REFERENCED_VALUE);
    createEReference(numericReferenceEClass, NUMERIC_REFERENCE__REFERENCED_PROPERTY);

    abstractComplexValueEClass = createEClass(ABSTRACT_COMPLEX_VALUE);
    createEReference(abstractComplexValueEClass, ABSTRACT_COMPLEX_VALUE__COMPLEX_TYPE);

    complexValueEClass = createEClass(COMPLEX_VALUE);
    createEReference(complexValueEClass, COMPLEX_VALUE__OWNED_PARTS);

    complexValueReferenceEClass = createEClass(COMPLEX_VALUE_REFERENCE);
    createEReference(complexValueReferenceEClass, COMPLEX_VALUE_REFERENCE__REFERENCED_VALUE);
    createEReference(complexValueReferenceEClass, COMPLEX_VALUE_REFERENCE__REFERENCED_PROPERTY);

    valuePartEClass = createEClass(VALUE_PART);
    createEReference(valuePartEClass, VALUE_PART__REFERENCED_PROPERTY);
    createEReference(valuePartEClass, VALUE_PART__OWNED_VALUE);

    abstractExpressionValueEClass = createEClass(ABSTRACT_EXPRESSION_VALUE);
    createEAttribute(abstractExpressionValueEClass, ABSTRACT_EXPRESSION_VALUE__EXPRESSION);
    createEAttribute(abstractExpressionValueEClass, ABSTRACT_EXPRESSION_VALUE__UNPARSED_EXPRESSION);
    createEReference(abstractExpressionValueEClass, ABSTRACT_EXPRESSION_VALUE__EXPRESSION_TYPE);

    binaryExpressionEClass = createEClass(BINARY_EXPRESSION);
    createEAttribute(binaryExpressionEClass, BINARY_EXPRESSION__OPERATOR);
    createEReference(binaryExpressionEClass, BINARY_EXPRESSION__OWNED_LEFT_OPERAND);
    createEReference(binaryExpressionEClass, BINARY_EXPRESSION__OWNED_RIGHT_OPERAND);

    unaryExpressionEClass = createEClass(UNARY_EXPRESSION);
    createEAttribute(unaryExpressionEClass, UNARY_EXPRESSION__OPERATOR);
    createEReference(unaryExpressionEClass, UNARY_EXPRESSION__OWNED_OPERAND);

    opaqueExpressionEClass = createEClass(OPAQUE_EXPRESSION);
    createEAttribute(opaqueExpressionEClass, OPAQUE_EXPRESSION__BODIES);
    createEAttribute(opaqueExpressionEClass, OPAQUE_EXPRESSION__LANGUAGES);

    // Create enums
    binaryOperatorEEnum = createEEnum(BINARY_OPERATOR);
    unaryOperatorEEnum = createEEnum(UNARY_OPERATOR);
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
    ModellingcorePackage theModellingcorePackage = (ModellingcorePackage)EPackage.Registry.INSTANCE.getEPackage(ModellingcorePackage.eNS_URI);
    DatatypePackage theDatatypePackage = (DatatypePackage)EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);
    InformationPackage theInformationPackage = (InformationPackage)EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    dataValueEClass.getESuperTypes().add(theCapellacorePackage.getNamedElement());
    dataValueEClass.getESuperTypes().add(theModellingcorePackage.getValueSpecification());
    dataValueContainerEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
    abstractBooleanValueEClass.getESuperTypes().add(this.getDataValue());
    literalBooleanValueEClass.getESuperTypes().add(this.getAbstractBooleanValue());
    booleanReferenceEClass.getESuperTypes().add(this.getAbstractBooleanValue());
    abstractEnumerationValueEClass.getESuperTypes().add(this.getDataValue());
    enumerationLiteralEClass.getESuperTypes().add(this.getAbstractEnumerationValue());
    enumerationReferenceEClass.getESuperTypes().add(this.getAbstractEnumerationValue());
    abstractStringValueEClass.getESuperTypes().add(this.getDataValue());
    literalStringValueEClass.getESuperTypes().add(this.getAbstractStringValue());
    stringReferenceEClass.getESuperTypes().add(this.getAbstractStringValue());
    numericValueEClass.getESuperTypes().add(this.getDataValue());
    literalNumericValueEClass.getESuperTypes().add(this.getNumericValue());
    numericReferenceEClass.getESuperTypes().add(this.getNumericValue());
    abstractComplexValueEClass.getESuperTypes().add(this.getDataValue());
    complexValueEClass.getESuperTypes().add(this.getAbstractComplexValue());
    complexValueReferenceEClass.getESuperTypes().add(this.getAbstractComplexValue());
    valuePartEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    abstractExpressionValueEClass.getESuperTypes().add(this.getAbstractBooleanValue());
    abstractExpressionValueEClass.getESuperTypes().add(this.getAbstractComplexValue());
    abstractExpressionValueEClass.getESuperTypes().add(this.getAbstractEnumerationValue());
    abstractExpressionValueEClass.getESuperTypes().add(this.getNumericValue());
    abstractExpressionValueEClass.getESuperTypes().add(this.getAbstractStringValue());
    binaryExpressionEClass.getESuperTypes().add(this.getAbstractExpressionValue());
    unaryExpressionEClass.getESuperTypes().add(this.getAbstractExpressionValue());
    opaqueExpressionEClass.getESuperTypes().add(theCapellacorePackage.getCapellaElement());
    opaqueExpressionEClass.getESuperTypes().add(theModellingcorePackage.getValueSpecification());

    // Initialize classes and features; add operations and parameters
    initEClass(dataValueEClass, DataValue.class, "DataValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getDataValue_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, DataValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getDataValue_Type(), theCapellacorePackage.getType(), null, "type", null, 0, 1, DataValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(dataValueContainerEClass, DataValueContainer.class, "DataValueContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getDataValueContainer_OwnedDataValues(), this.getDataValue(), null, "ownedDataValues", null, 0, -1, DataValueContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractBooleanValueEClass, AbstractBooleanValue.class, "AbstractBooleanValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractBooleanValue_BooleanType(), theDatatypePackage.getBooleanType(), null, "booleanType", null, 0, 1, AbstractBooleanValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(literalBooleanValueEClass, LiteralBooleanValue.class, "LiteralBooleanValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getLiteralBooleanValue_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, LiteralBooleanValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(booleanReferenceEClass, BooleanReference.class, "BooleanReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getBooleanReference_ReferencedValue(), this.getAbstractBooleanValue(), null, "referencedValue", null, 0, 1, BooleanReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBooleanReference_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, BooleanReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractEnumerationValueEClass, AbstractEnumerationValue.class, "AbstractEnumerationValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractEnumerationValue_EnumerationType(), theDatatypePackage.getEnumeration(), null, "enumerationType", null, 0, 1, AbstractEnumerationValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(enumerationLiteralEClass, EnumerationLiteral.class, "EnumerationLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEnumerationLiteral_DomainValue(), this.getDataValue(), null, "domainValue", null, 0, 1, EnumerationLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(enumerationReferenceEClass, EnumerationReference.class, "EnumerationReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getEnumerationReference_ReferencedValue(), this.getAbstractEnumerationValue(), null, "referencedValue", null, 0, 1, EnumerationReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getEnumerationReference_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, EnumerationReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractStringValueEClass, AbstractStringValue.class, "AbstractStringValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractStringValue_StringType(), theDatatypePackage.getStringType(), null, "stringType", null, 0, 1, AbstractStringValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(literalStringValueEClass, LiteralStringValue.class, "LiteralStringValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getLiteralStringValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, LiteralStringValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(stringReferenceEClass, StringReference.class, "StringReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getStringReference_ReferencedValue(), this.getAbstractStringValue(), null, "referencedValue", null, 0, 1, StringReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getStringReference_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, StringReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(numericValueEClass, NumericValue.class, "NumericValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getNumericValue_Unit(), theInformationPackage.getUnit(), null, "unit", null, 0, 1, NumericValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getNumericValue_NumericType(), theDatatypePackage.getNumericType(), null, "numericType", null, 0, 1, NumericValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(literalNumericValueEClass, LiteralNumericValue.class, "LiteralNumericValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getLiteralNumericValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, LiteralNumericValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(numericReferenceEClass, NumericReference.class, "NumericReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getNumericReference_ReferencedValue(), this.getNumericValue(), null, "referencedValue", null, 0, 1, NumericReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getNumericReference_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, NumericReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractComplexValueEClass, AbstractComplexValue.class, "AbstractComplexValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getAbstractComplexValue_ComplexType(), theCapellacorePackage.getClassifier(), null, "complexType", null, 0, 1, AbstractComplexValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(complexValueEClass, ComplexValue.class, "ComplexValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComplexValue_OwnedParts(), this.getValuePart(), null, "ownedParts", null, 0, -1, ComplexValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(complexValueReferenceEClass, ComplexValueReference.class, "ComplexValueReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getComplexValueReference_ReferencedValue(), this.getAbstractComplexValue(), null, "referencedValue", null, 0, 1, ComplexValueReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getComplexValueReference_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, ComplexValueReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(valuePartEClass, ValuePart.class, "ValuePart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(getValuePart_ReferencedProperty(), theInformationPackage.getProperty(), null, "referencedProperty", null, 0, 1, ValuePart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getValuePart_OwnedValue(), this.getDataValue(), null, "ownedValue", null, 0, 1, ValuePart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(abstractExpressionValueEClass, AbstractExpressionValue.class, "AbstractExpressionValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getAbstractExpressionValue_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, AbstractExpressionValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getAbstractExpressionValue_UnparsedExpression(), ecorePackage.getEString(), "unparsedExpression", null, 0, 1, AbstractExpressionValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getAbstractExpressionValue_ExpressionType(), theDatatypePackage.getDataType(), null, "expressionType", null, 0, 1, AbstractExpressionValue.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(binaryExpressionEClass, BinaryExpression.class, "BinaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getBinaryExpression_Operator(), this.getBinaryOperator(), "operator", null, 0, 1, BinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBinaryExpression_OwnedLeftOperand(), this.getDataValue(), null, "ownedLeftOperand", null, 0, 1, BinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getBinaryExpression_OwnedRightOperand(), this.getDataValue(), null, "ownedRightOperand", null, 0, 1, BinaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getUnaryExpression_Operator(), this.getUnaryOperator(), "operator", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEReference(getUnaryExpression_OwnedOperand(), this.getDataValue(), null, "ownedOperand", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    initEClass(opaqueExpressionEClass, OpaqueExpression.class, "OpaqueExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(getOpaqueExpression_Bodies(), ecorePackage.getEString(), "bodies", null, 0, -1, OpaqueExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
    initEAttribute(getOpaqueExpression_Languages(), ecorePackage.getEString(), "languages", null, 0, -1, OpaqueExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(binaryOperatorEEnum, BinaryOperator.class, "BinaryOperator"); //$NON-NLS-1$
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.UNSET);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.ADD);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.MUL);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.SUB);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.DIV);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.POW);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.MIN);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.MAX);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.EQU);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.IOR);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.XOR);
    addEEnumLiteral(binaryOperatorEEnum, BinaryOperator.AND);

    initEEnum(unaryOperatorEEnum, UnaryOperator.class, "UnaryOperator"); //$NON-NLS-1$
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.UNSET);
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.NOT);
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.POS);
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.VAL);
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.SUC);
    addEEnumLiteral(unaryOperatorEEnum, UnaryOperator.PRE);

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
         "description", "sub-package containing the definition of all predefined kinds of data values\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "n/a" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataValueEClass,
       source,
       new String[] {
         "description", "Generic class for the specification of value for data of a given type\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a (Abstract)", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValue_Abstract(),
       source,
       new String[] {
         "description", "whether or not the value is abstract", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValue_Type(),
       source,
       new String[] {
         "description", "The type of the TypedElement\r\n[source:UML Superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataValueContainerEClass,
       source,
       new String[] {
         "description", "container for DataValue elements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "Use DataValue packages to provide an appropriate structure to the data model", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
         "description", "DataValue elements contained in this package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractBooleanValueEClass,
       source,
       new String[] {
         "description", "Base class for defining type-specific boolean values\r\n[source: Capella light-light study]\r\n", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalBooleanValueEClass,
       source,
       new String[] {
         "description", "A literal Boolean is a specification of a Boolean value\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralBooleanValue_Value(),
       source,
       new String[] {
         "description", "the value \"true\" or \"false\"\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanReferenceEClass,
       source,
       new String[] {
         "description", "A reference to a boolean value, allowing the reuse of boolean values across data value structures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the boolean value that this reference points to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that is using this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEnumerationValueEClass,
       source,
       new String[] {
         "description", "Base class for defining type-specific enumeration values\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationLiteralEClass,
       source,
       new String[] {
         "description", "A value specification composed of a finite list of predefined values\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationLiteral_DomainValue(),
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
      (enumerationReferenceEClass,
       source,
       new String[] {
         "description", "A reference to an abstract enumeration value, allowing the reuse of enumeration values across data value structures", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the abstract enumeration value that this reference points to", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that is using this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractStringValueEClass,
       source,
       new String[] {
         "description", "A value defined by an ordered set of characters\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalStringValueEClass,
       source,
       new String[] {
         "description", "A literal string is a specification of a string value\r\n[source: UML superstructure v2.2]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralStringValue_Value(),
       source,
       new String[] {
         "description", "the specific string\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringReferenceEClass,
       source,
       new String[] {
         "description", "A reference to a string value, allowing the reuse of string values across data value structures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the string value that this reference points to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that uses this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericValueEClass,
       source,
       new String[] {
         "description", "A value expressed as a number\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_Unit(),
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
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalNumericValueEClass,
       source,
       new String[] {
         "description", "A literal value expressed as a number (ordered set of digits)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralNumericValue_Value(),
       source,
       new String[] {
         "description", "the number defining this value, expressed as a string\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericReferenceEClass,
       source,
       new String[] {
         "description", "a reference to a numeric value, allowing the reuse of numeric values across data value structures\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the numeric value being referenced\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that uses this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractComplexValueEClass,
       source,
       new String[] {
         "description", "Base class for defining complex value type\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (complexValueEClass,
       source,
       new String[] {
         "description", "Data type characterizing a complex number\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValue_OwnedParts(),
       source,
       new String[] {
         "description", "stores the different parts that make a complex value\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (complexValueReferenceEClass,
       source,
       new String[] {
         "description", "A reference to a complex value\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedValue(),
       source,
       new String[] {
         "description", "the complex value being referenced", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
         "description", "the property that uses this reference\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (valuePartEClass,
       source,
       new String[] {
         "description", "Used in the decomposition of complex values into smaller unitary elements\r\n[source: Capella light-light study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getValuePart_ReferencedProperty(),
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
      (getValuePart_OwnedValue(),
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
      (abstractExpressionValueEClass,
       source,
       new String[] {
         "description", "Abstract class to support the implementation of Expression specifications\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_Expression(),
       source,
       new String[] {
         "description", "textual specification of the expression\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_UnparsedExpression(),
       source,
       new String[] {
         "description", "raw textual specification of the expression\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "description", "the type of the data being valued\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryExpressionEClass,
       source,
       new String[] {
         "description", "Specification of a condition that can only evaluate to \"true\" or \"false\"\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_Operator(),
       source,
       new String[] {
         "description", "the operator between the left and right operands\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
         "description", "list of the operands being part of the boolean expression\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
         "description", "list of the operands being part of the boolean expression\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryExpressionEClass,
       source,
       new String[] {
         "description", "Specification of a condition that can only evaluate to \"true\" or \"false\"\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
         "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_Operator(),
       source,
       new String[] {
         "description", "the operator applying to the operand\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
       source,
       new String[] {
         "description", "list of the operands being part of the boolean expression\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum,
       source,
       new String[] {
         "description", "Specifies the kind of this binary operator", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Used when the binary operator is not initialized", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Used when the binary operator refers to an addition", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a multiplication", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a substraction", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a division", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a power operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(6),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a min operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(7),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a max operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(8),
       source,
       new String[] {
         "description", "Used when the binary operator refers to an equal operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(9),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a logical inclusive OR operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(10),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a logical exclusive OR operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(11),
       source,
       new String[] {
         "description", "Used when the binary operator refers to a logical AND operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum,
       source,
       new String[] {
         "description", "Specifies the kind of this unary operator", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(0),
       source,
       new String[] {
         "description", "Used when the unary operator is not initialized", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(1),
       source,
       new String[] {
         "description", "Used when the unary operator refers to a NOT operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(2),
       source,
       new String[] {
         "description", "Used when the unary operator refers to a position operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(3),
       source,
       new String[] {
         "description", "Used when the unary operator refers to a value operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(4),
       source,
       new String[] {
         "description", "Used when the unary operator refers to a successor operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(5),
       source,
       new String[] {
         "description", "Used when the unary operator refers to a predecessor operation", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (opaqueExpressionEClass,
       source,
       new String[] {
         "description", "An opaque expression contains language-specific text strings used to describe a value or values, and an optional specification of\r\nthe languages.\r\nOne predefined language for specifying expressions is OCL. Natural language or programming languages may also be\r\nused.", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "If the language attribute is not empty, then the size of the body and language arrays must be the same." //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOpaqueExpression_Bodies(),
       source,
       new String[] {
         "description", "The text of the expression, possibly in multiple languages." //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getOpaqueExpression_Languages(),
       source,
       new String[] {
         "description", "Specifies the languages in which the expression is stated. The interpretation of the expression body depends on the\r\nlanguages. If the languages are unspecified, they might be implicit from the expression body or the context.\r\nLanguages are matched to body strings by order." //$NON-NLS-1$ //$NON-NLS-2$
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
      (getDataValue_Abstract(),
       source,
       new String[] {
       });
    addAnnotation
      (getDataValue_Type(),
       source,
       new String[] {
         "feature", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalBooleanValueEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLiteralBooleanValue_Value(),
       source,
       new String[] {
       });
    addAnnotation
      (booleanReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationLiteralEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getEnumerationLiteral_DomainValue(),
       source,
       new String[] {
       });
    addAnnotation
      (enumerationReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getEnumerationReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalStringValueEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLiteralStringValue_Value(),
       source,
       new String[] {
       });
    addAnnotation
      (stringReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericValue_Unit(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalNumericValueEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getLiteralNumericValue_Value(),
       source,
       new String[] {
       });
    addAnnotation
      (numericReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (complexValueEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComplexValue_OwnedParts(),
       source,
       new String[] {
       });
    addAnnotation
      (complexValueReferenceEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getComplexValueReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (valuePartEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getValuePart_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getValuePart_OwnedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractExpressionValue_Expression(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractExpressionValue_UnparsedExpression(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "feature", "abstractType", //$NON-NLS-1$ //$NON-NLS-2$
         "excludefrom", "xmlpivot" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryExpressionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getBinaryExpression_Operator(),
       source,
       new String[] {
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
       });
    addAnnotation
      (unaryExpressionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getUnaryExpression_Operator(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
       source,
       new String[] {
       });
    addAnnotation
      (binaryOperatorEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (unaryOperatorEEnum,
       source,
       new String[] {
       });
    addAnnotation
      (opaqueExpressionEClass,
       source,
       new String[] {
       });
    addAnnotation
      (getOpaqueExpression_Bodies(),
       source,
       new String[] {
       });
    addAnnotation
      (getOpaqueExpression_Languages(),
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
      (dataValueEClass,
       source,
       new String[] {
         "Label", "DataValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataValueContainerEClass,
       source,
       new String[] {
         "Label", "DataTypePkg" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
         "Label", "ownedDataValues" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalBooleanValueEClass,
       source,
       new String[] {
         "Label", "LiteralBooleanValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanReferenceEClass,
       source,
       new String[] {
         "Label", "BooleanReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationLiteralEClass,
       source,
       new String[] {
         "Label", "EnumerationLiteral" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractStringValueEClass,
       source,
       new String[] {
         "Label", "StringValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalStringValueEClass,
       source,
       new String[] {
         "Label", "LiteralStringValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringReferenceEClass,
       source,
       new String[] {
         "Label", "StringReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericValueEClass,
       source,
       new String[] {
         "Label", "NumericValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalNumericValueEClass,
       source,
       new String[] {
         "Label", "LiteralNumericValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericReferenceEClass,
       source,
       new String[] {
         "Label", "NumericReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
         "Label", "reference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "Label", "type" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryExpressionEClass,
       source,
       new String[] {
         "Label", "BinaryExpression" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
         "Label", "operands" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
         "Label", "operands" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryExpressionEClass,
       source,
       new String[] {
         "Label", "UnaryExpression" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
       source,
       new String[] {
         "Label", "operands" //$NON-NLS-1$ //$NON-NLS-2$
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
      (dataValueEClass,
       source,
       new String[] {
         "metaclass", "LiteralSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.DataValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataValueContainerEClass,
       source,
       new String[] {
         "metaclass", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
         "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalBooleanValueEClass,
       source,
       new String[] {
         "metaclass", "LiteralBoolean", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LiteralBooleanValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralBooleanValue_Value(),
       source,
       new String[] {
         "featureName", "value", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "LiteralBoolean" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanReferenceEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.BooleanReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationLiteralEClass,
       source,
       new String[] {
         "metaclass", "EnumerationLiteral", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.EnumerationLiteral" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractStringValueEClass,
       source,
       new String[] {
         "metaclass", "ValueSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalStringValueEClass,
       source,
       new String[] {
         "metaclass", "LiteralString", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LiteralStringValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralStringValue_Value(),
       source,
       new String[] {
         "featureName", "value", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "LiteralString" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringReferenceEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.StringReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericValueEClass,
       source,
       new String[] {
         "metaclass", "ValueSpecification" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalNumericValueEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.LiteralNumericValue" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralNumericValue_Value(),
       source,
       new String[] {
         "featureName", "symbol", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "Expression" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericReferenceEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.NumericReference" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "featureName", "type", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "TypedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryExpressionEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.BinaryExpression" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
         "featureName", "clientDependency", //$NON-NLS-1$ //$NON-NLS-2$
         "featureOwner", "NamedElement" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryExpressionEClass,
       source,
       new String[] {
         "metaclass", "Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "stereotype", "eng.UnaryExpression" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
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
      (dataValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::ValueSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValue_Abstract(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValue_Type(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (dataValueContainerEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Package::packagedElement elements on which DataValue stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractBooleanValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalBooleanValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::LiteralBoolean", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralBooleanValue_Value(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::LiteralBoolean::value", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (booleanReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractEnumerationValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationLiteralEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::EnumerationLiteral", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationLiteral_DomainValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (enumerationReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractStringValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::ValueSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalStringValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::LiteralString", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralStringValue_Value(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::LiteralString::value", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (stringReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::LiteralSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_Unit(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (literalNumericValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getLiteralNumericValue_Value(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::LiteralString::value", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (numericReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractComplexValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::ValueSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (complexValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::LiteralSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValue_OwnedParts(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "***** elements on which ValuePart stereotype or any stereotype that inherits from it is applied\r\n" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (complexValueReferenceEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "uml::LiteralSpecification", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (valuePartEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getValuePart_ReferencedProperty(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getValuePart_OwnedValue(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::specific", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "****** elements on which DataValue stereotype or any stereotype that inherits from it is applied\r\nMultiplicity must be [0..1]" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (abstractExpressionValueEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_Expression(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_UnparsedExpression(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryExpressionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_Operator(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Expression::operand", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Check that uml::Expression::operand contains BooleanValue", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Expression::operand", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Check that uml::Expression::operand contains BooleanValue", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryExpressionEClass,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "base metaclass in UML/SysML profile ", "uml::Expression", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_Operator(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "uml::Expression::operand", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "_todo_ Check that uml::Expression::operand contains BooleanValue", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "uml::Expression::operand elements on which ValueSpecification stereotype or any stereotype that inherits from it is applied" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(6),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(7),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(8),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(9),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(10),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (binaryOperatorEEnum.getELiterals().get(11),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum,
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(0),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(1),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(2),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(3),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(4),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (unaryOperatorEEnum.getELiterals().get(5),
       source,
       new String[] {
         "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
         "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
         "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (opaqueExpressionEClass,
       source,
       new String[] {
         "base metaclass in UML/SysML profile ", "uml::OpaqueExpression" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getDataValue_Type(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
       });
    addAnnotation
      (getAbstractExpressionValue_Expression(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
         "viatra.variant", "alias", //$NON-NLS-1$ //$NON-NLS-2$
         "viatra.expression", "abstractType" //$NON-NLS-1$ //$NON-NLS-2$
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
      (getDataValueContainer_OwnedDataValues(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractBooleanValue_BooleanType(),
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getBooleanReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractEnumerationValue_EnumerationType(),
       source,
       new String[] {
       });
    addAnnotation
      (getEnumerationReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractStringValue_StringType(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getStringReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericValue_NumericType(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericReference_ReferencedValue(),
       source,
       new String[] {
       });
    addAnnotation
      (getNumericReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractComplexValue_ComplexType(),
       source,
       new String[] {
       });
    addAnnotation
      (getComplexValueReference_ReferencedProperty(),
       source,
       new String[] {
       });
    addAnnotation
      (getAbstractExpressionValue_ExpressionType(),
       source,
       new String[] {
       });
    addAnnotation
      (getBinaryExpression_OwnedLeftOperand(),
       source,
       new String[] {
       });
    addAnnotation
      (getBinaryExpression_OwnedRightOperand(),
       source,
       new String[] {
       });
    addAnnotation
      (getUnaryExpression_OwnedOperand(),
       source,
       new String[] {
       });
  }

} //DatavaluePackageImpl
