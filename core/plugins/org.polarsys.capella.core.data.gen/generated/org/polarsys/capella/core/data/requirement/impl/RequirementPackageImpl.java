/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.requirement.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.polarsys.capella.common.data.activity.ActivityPackage;
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
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.data.requirement.RequirementFactory;
import org.polarsys.capella.core.data.requirement.RequirementPackage;
import org.polarsys.capella.core.data.requirement.RequirementsPkg;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.data.requirement.SystemFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalInterfaceRequirement;
import org.polarsys.capella.core.data.requirement.SystemNonFunctionalRequirement;
import org.polarsys.capella.core.data.requirement.SystemUserRequirement;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.data.sharedmodel.impl.SharedmodelPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RequirementPackageImpl extends EPackageImpl implements RequirementPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementsPkgEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementsTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemFunctionalInterfaceRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemFunctionalRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemNonFunctionalInterfaceRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemNonFunctionalRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemUserRequirementEClass = null;

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
	 * @see org.polarsys.capella.core.data.requirement.RequirementPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RequirementPackageImpl() {
		super(eNS_URI, RequirementFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RequirementPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RequirementPackage init() {
		if (isInited) return (RequirementPackage)EPackage.Registry.INSTANCE.getEPackage(RequirementPackage.eNS_URI);

		// Obtain or create and register package
		RequirementPackageImpl theRequirementPackage = (RequirementPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RequirementPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RequirementPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ActivityPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CapellamodellerPackageImpl theCapellamodellerPackage = (CapellamodellerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) instanceof CapellamodellerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellamodellerPackage.eNS_URI) : CapellamodellerPackage.eINSTANCE);
		CapellacorePackageImpl theCapellacorePackage = (CapellacorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) instanceof CapellacorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellacorePackage.eNS_URI) : CapellacorePackage.eINSTANCE);
		OaPackageImpl theOaPackage = (OaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) instanceof OaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OaPackage.eNS_URI) : OaPackage.eINSTANCE);
		CtxPackageImpl theCtxPackage = (CtxPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI) instanceof CtxPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CtxPackage.eNS_URI) : CtxPackage.eINSTANCE);
		LaPackageImpl theLaPackage = (LaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI) instanceof LaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LaPackage.eNS_URI) : LaPackage.eINSTANCE);
		PaPackageImpl thePaPackage = (PaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI) instanceof PaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PaPackage.eNS_URI) : PaPackage.eINSTANCE);
		DeploymentPackageImpl theDeploymentPackage = (DeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) instanceof DeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DeploymentPackage.eNS_URI) : DeploymentPackage.eINSTANCE);
		EpbsPackageImpl theEpbsPackage = (EpbsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI) instanceof EpbsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EpbsPackage.eNS_URI) : EpbsPackage.eINSTANCE);
		SharedmodelPackageImpl theSharedmodelPackage = (SharedmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI) instanceof SharedmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SharedmodelPackage.eNS_URI) : SharedmodelPackage.eINSTANCE);
		CapellacommonPackageImpl theCapellacommonPackage = (CapellacommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI) instanceof CapellacommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CapellacommonPackage.eNS_URI) : CapellacommonPackage.eINSTANCE);
		InformationPackageImpl theInformationPackage = (InformationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI) instanceof InformationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InformationPackage.eNS_URI) : InformationPackage.eINSTANCE);
		CommunicationPackageImpl theCommunicationPackage = (CommunicationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI) instanceof CommunicationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommunicationPackage.eNS_URI) : CommunicationPackage.eINSTANCE);
		DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) instanceof DatatypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) : DatatypePackage.eINSTANCE);
		DatavaluePackageImpl theDatavaluePackage = (DatavaluePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI) instanceof DatavaluePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatavaluePackage.eNS_URI) : DatavaluePackage.eINSTANCE);
		CsPackageImpl theCsPackage = (CsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI) instanceof CsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CsPackage.eNS_URI) : CsPackage.eINSTANCE);
		FaPackageImpl theFaPackage = (FaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI) instanceof FaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FaPackage.eNS_URI) : FaPackage.eINSTANCE);
		InteractionPackageImpl theInteractionPackage = (InteractionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI) instanceof InteractionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InteractionPackage.eNS_URI) : InteractionPackage.eINSTANCE);

		// Create package meta-data objects
		theRequirementPackage.createPackageContents();
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
		theInteractionPackage.createPackageContents();

		// Initialize created meta-data
		theRequirementPackage.initializePackageContents();
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
		theInteractionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRequirementPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RequirementPackage.eNS_URI, theRequirementPackage);
		return theRequirementPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirementsPkg() {
		return requirementsPkgEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementsPkg_AdditionalInformation() {
		return (EAttribute)requirementsPkgEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirementsPkg_Level() {
		return (EAttribute)requirementsPkgEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirementsPkg_OwnedRequirements() {
		return (EReference)requirementsPkgEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirementsPkg_OwnedRequirementPkgs() {
		return (EReference)requirementsPkgEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirementsTrace() {
		return requirementsTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirementsTrace_Source() {
		return (EReference)requirementsTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirementsTrace_Target() {
		return (EReference)requirementsTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_IsObsolete() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_RequirementId() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_AdditionalInformation() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_VerificationMethod() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_VerificationPhase() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_ImplementationVersion() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequirement_Feature() {
		return (EAttribute)requirementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirement_RelatedCapellaElements() {
		return (EReference)requirementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemFunctionalInterfaceRequirement() {
		return systemFunctionalInterfaceRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemFunctionalRequirement() {
		return systemFunctionalRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemNonFunctionalInterfaceRequirement() {
		return systemNonFunctionalInterfaceRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemNonFunctionalRequirement() {
		return systemNonFunctionalRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemUserRequirement() {
		return systemUserRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequirementFactory getRequirementFactory() {
		return (RequirementFactory)getEFactoryInstance();
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
		requirementsPkgEClass = createEClass(REQUIREMENTS_PKG);
		createEAttribute(requirementsPkgEClass, REQUIREMENTS_PKG__ADDITIONAL_INFORMATION);
		createEAttribute(requirementsPkgEClass, REQUIREMENTS_PKG__LEVEL);
		createEReference(requirementsPkgEClass, REQUIREMENTS_PKG__OWNED_REQUIREMENTS);
		createEReference(requirementsPkgEClass, REQUIREMENTS_PKG__OWNED_REQUIREMENT_PKGS);

		requirementsTraceEClass = createEClass(REQUIREMENTS_TRACE);
		createEReference(requirementsTraceEClass, REQUIREMENTS_TRACE__SOURCE);
		createEReference(requirementsTraceEClass, REQUIREMENTS_TRACE__TARGET);

		requirementEClass = createEClass(REQUIREMENT);
		createEAttribute(requirementEClass, REQUIREMENT__IS_OBSOLETE);
		createEAttribute(requirementEClass, REQUIREMENT__REQUIREMENT_ID);
		createEAttribute(requirementEClass, REQUIREMENT__ADDITIONAL_INFORMATION);
		createEAttribute(requirementEClass, REQUIREMENT__VERIFICATION_METHOD);
		createEAttribute(requirementEClass, REQUIREMENT__VERIFICATION_PHASE);
		createEAttribute(requirementEClass, REQUIREMENT__IMPLEMENTATION_VERSION);
		createEAttribute(requirementEClass, REQUIREMENT__FEATURE);
		createEReference(requirementEClass, REQUIREMENT__RELATED_CAPELLA_ELEMENTS);

		systemFunctionalInterfaceRequirementEClass = createEClass(SYSTEM_FUNCTIONAL_INTERFACE_REQUIREMENT);

		systemFunctionalRequirementEClass = createEClass(SYSTEM_FUNCTIONAL_REQUIREMENT);

		systemNonFunctionalInterfaceRequirementEClass = createEClass(SYSTEM_NON_FUNCTIONAL_INTERFACE_REQUIREMENT);

		systemNonFunctionalRequirementEClass = createEClass(SYSTEM_NON_FUNCTIONAL_REQUIREMENT);

		systemUserRequirementEClass = createEClass(SYSTEM_USER_REQUIREMENT);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		requirementsPkgEClass.getESuperTypes().add(theCapellacorePackage.getStructure());
		requirementsTraceEClass.getESuperTypes().add(theCapellacorePackage.getTrace());
		requirementEClass.getESuperTypes().add(theCapellacorePackage.getNamespace());
		systemFunctionalInterfaceRequirementEClass.getESuperTypes().add(this.getRequirement());
		systemFunctionalRequirementEClass.getESuperTypes().add(this.getRequirement());
		systemNonFunctionalInterfaceRequirementEClass.getESuperTypes().add(this.getRequirement());
		systemNonFunctionalRequirementEClass.getESuperTypes().add(this.getRequirement());
		systemUserRequirementEClass.getESuperTypes().add(this.getRequirement());

		// Initialize classes and features; add operations and parameters
		initEClass(requirementsPkgEClass, RequirementsPkg.class, "RequirementsPkg", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getRequirementsPkg_AdditionalInformation(), ecorePackage.getEString(), "additionalInformation", null, 0, 1, RequirementsPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirementsPkg_Level(), ecorePackage.getEString(), "level", null, 0, 1, RequirementsPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRequirementsPkg_OwnedRequirements(), this.getRequirement(), null, "ownedRequirements", null, 0, -1, RequirementsPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRequirementsPkg_OwnedRequirementPkgs(), this.getRequirementsPkg(), null, "ownedRequirementPkgs", null, 0, -1, RequirementsPkg.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(requirementsTraceEClass, RequirementsTrace.class, "RequirementsTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRequirementsTrace_Source(), theModellingcorePackage.getTraceableElement(), null, "source", null, 1, 1, RequirementsTrace.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRequirementsTrace_Target(), theModellingcorePackage.getTraceableElement(), null, "target", null, 1, 1, RequirementsTrace.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(requirementEClass, Requirement.class, "Requirement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getRequirement_IsObsolete(), ecorePackage.getEBoolean(), "isObsolete", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_RequirementId(), ecorePackage.getEString(), "requirementId", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_AdditionalInformation(), ecorePackage.getEString(), "additionalInformation", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_VerificationMethod(), ecorePackage.getEString(), "verificationMethod", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_VerificationPhase(), ecorePackage.getEString(), "verificationPhase", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_ImplementationVersion(), ecorePackage.getEString(), "implementationVersion", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getRequirement_Feature(), ecorePackage.getEString(), "feature", null, 0, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getRequirement_RelatedCapellaElements(), theCapellacorePackage.getCapellaElement(), null, "relatedCapellaElements", null, 0, -1, Requirement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(systemFunctionalInterfaceRequirementEClass, SystemFunctionalInterfaceRequirement.class, "SystemFunctionalInterfaceRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemFunctionalRequirementEClass, SystemFunctionalRequirement.class, "SystemFunctionalRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemNonFunctionalInterfaceRequirementEClass, SystemNonFunctionalInterfaceRequirement.class, "SystemNonFunctionalInterfaceRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemNonFunctionalRequirementEClass, SystemNonFunctionalRequirement.class, "SystemNonFunctionalRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(systemUserRequirementEClass, SystemUserRequirement.class, "SystemUserRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.polarsys.org/kitalpha/dsl/2007/dslfactory
		createDslfactoryAnnotations();
		// http://www.polarsys.org/kitalpha/ecore/documentation
		createDocumentationAnnotations();
		// http://www.polarsys.org/capella/semantic
		createSemanticAnnotations();
		// http://www.polarsys.org/kitalpha/emde/1.0.0/extension
		createExtensionAnnotations();
		// http://www.polarsys.org/capella/2007/BusinessInformation
		createBusinessInformationAnnotations();
		// http://www.polarsys.org/capella/MNoE/CapellaLike/Mapping
		createMappingAnnotations();
		// http://www.polarsys.org/capella/2007/UML2Mapping
		createUML2MappingAnnotations();
		// http://www.polarsys.org/capella/2007/ImpactAnalysis/Segment
		createSegmentAnnotations();
		// http://www.polarsys.org/capella/2007/ImpactAnalysis/Ignore
		createIgnoreAnnotations();
		// http://www.polarsys.org/capella/derived
		createDerivedAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.polarsys.org/kitalpha/dsl/2007/dslfactory</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createDslfactoryAnnotations() {
		String source = "http://www.polarsys.org/kitalpha/dsl/2007/dslfactory"; //$NON-NLS-1$	
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
			 "description", "Requirement aims at defining the requirements expression language.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "This package depends on the model CapellaCore.ecore", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementsPkgEClass, 
		   source, 
		   new String[] {
			 "description", "a container for Requirement elements, and sub packages containing Requirements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "description", "free information field to characterize this requirement package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_Level(), 
		   source, 
		   new String[] {
			 "description", "the design level to which this requirement package applies\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
			 "description", "the Requirements contained in this requirement package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
		   source, 
		   new String[] {
			 "description", "the sub-(Requirement) packages contained in this requirement package\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementsTraceEClass, 
		   source, 
		   new String[] {
			 "description", "a specialized kind of Trace to indicate the relationship between two Requirements\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "used to keep track of links between Requirements, typically which requirement answers to which other requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementEClass, 
		   source, 
		   new String[] {
			 "description", "a capability or condition that must (or should) be satisfied\r\n[source: SysML glossary for SysML v1.0]", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical,physical,epbs", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_IsObsolete(), 
		   source, 
		   new String[] {
			 "description", "field used to flag obsolete requirement (that for some reason we want to keep in the model though)\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_RequirementId(), 
		   source, 
		   new String[] {
			 "description", "a unique identifier for this requirement\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "description", "a free field to capture any additional information required to complement this requirement statement\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationMethod(), 
		   source, 
		   new String[] {
			 "description", "this field receives the description of the method that will be used to verify that this requirement is fulfilled.\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationPhase(), 
		   source, 
		   new String[] {
			 "description", "a field receiving the description of the design phase in which this requirement can/will be verified\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_ImplementationVersion(), 
		   source, 
		   new String[] {
			 "description", "a unique identifier to keep track of the version of this requirement\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_Feature(), 
		   source, 
		   new String[] {
			 "description", "the system feature that this requirement corresponds to\r\n[source: Capella study]", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "type", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "description", "A requirement related to an interface functional property", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system, logical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "description", "A requirement related a system functional property", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "description", "Any non functional constraint to be satisfied at interfaces level.\r\n(e.g. resources usage, environmental constraints, criticity, performance...) ", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "description", "Non functional requirements are any non functional constraints to be satisfied.\r\n(e.g. resources usage, environmental constraints, criticity, performance...) ", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system,logical,physical", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage examples", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "comment/notes", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "reference documentation", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemUserRequirementEClass, 
		   source, 
		   new String[] {
			 "description", "A User requirement that has to be fulfilled by the System", //$NON-NLS-1$ //$NON-NLS-2$
			 "usage guideline", "n/a", //$NON-NLS-1$ //$NON-NLS-2$
			 "used in levels", "operational,system", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (requirementsPkgEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsPkg_AdditionalInformation(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsPkg_Level(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (requirementsTraceEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsTrace_Source(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsTrace_Target(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_IsObsolete(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_RequirementId(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_AdditionalInformation(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_VerificationMethod(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_VerificationPhase(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_ImplementationVersion(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_Feature(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_RelatedCapellaElements(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemFunctionalRequirementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemNonFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemNonFunctionalRequirementEClass, 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (systemUserRequirementEClass, 
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
		  (requirementsPkgEClass, 
		   source, 
		   new String[] {
			 "Label", "RequirementPkg" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
			 "Label", "ownedRequirements" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
		   source, 
		   new String[] {
			 "Label", "subRequirementPkgs" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementsTraceEClass, 
		   source, 
		   new String[] {
			 "Label", "RequirementTrace" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementEClass, 
		   source, 
		   new String[] {
			 "Label", "Requirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "Label", "SystemFunctionalInterfaceRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "Label", "SystemFunctionalRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "Label", "SystemNonFunctionalInterfaceRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "Label", "SystemNonFunctionalRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemUserRequirementEClass, 
		   source, 
		   new String[] {
			 "Label", "SystemUserRequirement" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (requirementsPkgEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Package", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_Level(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::packagedElement elements on which Requirement stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "uml::Package::nestedPackage#uml::Package::packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "uml::Package::nestedPackage elements on which RequirementPkg stereotype or any stereotype that inherits from it is applied\r\nOrder must be computed" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementsTraceEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "uml::Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_IsObsolete(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_RequirementId(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "SysML::Requirements::Requirement::id", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationMethod(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationPhase(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_ImplementationVersion(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_Feature(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_RelatedCapellaElements(), 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "keyword::none", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "Derived and transient", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "explanation", "none", //$NON-NLS-1$ //$NON-NLS-2$
			 "constraints", "none" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemUserRequirementEClass, 
		   source, 
		   new String[] {
			 "UML/SysML semantic equivalences", "", //$NON-NLS-1$ //$NON-NLS-2$
			 "base metaclass in UML/SysML profile ", "SysML::Requirements::Requirement", //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getRequirementsPkg_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "featureName", "additionalInformation", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.RequirementPkg", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_Level(), 
		   source, 
		   new String[] {
			 "featureName", "level", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.RequirementPkg", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
		   source, 
		   new String[] {
			 "featureName", "packagedElement", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "Package" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementsTraceEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Dependency", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.RequirementTrace" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (requirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.Requirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_RequirementId(), 
		   source, 
		   new String[] {
			 "featureName", "requirementId", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_AdditionalInformation(), 
		   source, 
		   new String[] {
			 "featureName", "additionalInformation", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationMethod(), 
		   source, 
		   new String[] {
			 "featureName", "verificationMethod", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_VerificationPhase(), 
		   source, 
		   new String[] {
			 "featureName", "verificationPhase", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_ImplementationVersion(), 
		   source, 
		   new String[] {
			 "featureName", "implementationVersion", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (getRequirement_Feature(), 
		   source, 
		   new String[] {
			 "featureName", "feature", //$NON-NLS-1$ //$NON-NLS-2$
			 "featureOwner", "eng.Requirement", //$NON-NLS-1$ //$NON-NLS-2$
			 "fromStereotype", "true" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.sys.SystemFunctionalInterfaceRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.sys.SystemFunctionalRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalInterfaceRequirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.sys.SystemNonFunctionalInterfaceRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemNonFunctionalRequirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.sys.SystemNonFunctionalRequirement" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
		addAnnotation
		  (systemUserRequirementEClass, 
		   source, 
		   new String[] {
			 "metaclass", "Class", //$NON-NLS-1$ //$NON-NLS-2$
			 "stereotype", "eng.sys.SystemUserRequirement" //$NON-NLS-1$ //$NON-NLS-2$
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
		  (getRequirementsPkg_OwnedRequirements(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsPkg_OwnedRequirementPkgs(), 
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
		  (requirementsTraceEClass, 
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
		  (getRequirementsTrace_Source(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirementsTrace_Target(), 
		   source, 
		   new String[] {
		   });	
		addAnnotation
		  (getRequirement_RelatedCapellaElements(), 
		   source, 
		   new String[] {
		   });
	}

} //RequirementPackageImpl
