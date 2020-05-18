/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.Bounds;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ILayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutPackageImpl extends EPackageImpl implements LayoutPackage {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass diagramLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iSemanticLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass edgeLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass noteLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sessionLayoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass boundsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass locationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sizeEClass = null;

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
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private LayoutPackageImpl() {
    super(eNS_URI, LayoutFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link LayoutPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static LayoutPackage init() {
    if (isInited) return (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);

    // Obtain or create and register package
    LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LayoutPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();
    ViewpointPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theLayoutPackage.createPackageContents();

    // Initialize created meta-data
    theLayoutPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theLayoutPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(LayoutPackage.eNS_URI, theLayoutPackage);
    return theLayoutPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDiagramLayout() {
    return diagramLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDiagramLayout_OwnedLayouts() {
    return (EReference)diagramLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagramLayout_AppliedLayers() {
    return (EAttribute)diagramLayoutEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagramLayout_Synchronized() {
    return (EAttribute)diagramLayoutEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagramLayout_Description() {
    return (EAttribute)diagramLayoutEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getILayout() {
    return iLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getISemanticLayout() {
    return iSemanticLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getISemanticLayout_Id() {
    return (EAttribute)iSemanticLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getISemanticLayout_Name() {
    return (EAttribute)iSemanticLayoutEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getISemanticLayout_ActualMapping() {
    return (EAttribute)iSemanticLayoutEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getISemanticLayout_AppliedFilters() {
    return (EAttribute)iSemanticLayoutEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getISemanticLayout_AppliedStyles() {
    return (EAttribute)iSemanticLayoutEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEdgeLayout() {
    return edgeLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeLayout_Bendpoints() {
    return (EReference)edgeLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeLayout_Source() {
    return (EReference)edgeLayoutEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeLayout_Target() {
    return (EReference)edgeLayoutEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeLayout() {
    return nodeLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeLayout_Bounds() {
    return (EReference)nodeLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeLayout_OwnedLayouts() {
    return (EReference)nodeLayoutEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNoteLayout() {
    return noteLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNoteLayout_Bounds() {
    return (EReference)noteLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSessionLayout() {
    return sessionLayoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSessionLayout_OwnedLayouts() {
    return (EReference)sessionLayoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBounds() {
    return boundsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLocation() {
    return locationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocation_X() {
    return (EAttribute)locationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocation_Y() {
    return (EAttribute)locationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocation_Relative() {
    return (EAttribute)locationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSize() {
    return sizeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSize_Width() {
    return (EAttribute)sizeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSize_Height() {
    return (EAttribute)sizeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LayoutFactory getLayoutFactory() {
    return (LayoutFactory)getEFactoryInstance();
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
    diagramLayoutEClass = createEClass(DIAGRAM_LAYOUT);
    createEReference(diagramLayoutEClass, DIAGRAM_LAYOUT__OWNED_LAYOUTS);
    createEAttribute(diagramLayoutEClass, DIAGRAM_LAYOUT__APPLIED_LAYERS);
    createEAttribute(diagramLayoutEClass, DIAGRAM_LAYOUT__SYNCHRONIZED);
    createEAttribute(diagramLayoutEClass, DIAGRAM_LAYOUT__DESCRIPTION);

    iLayoutEClass = createEClass(ILAYOUT);

    iSemanticLayoutEClass = createEClass(ISEMANTIC_LAYOUT);
    createEAttribute(iSemanticLayoutEClass, ISEMANTIC_LAYOUT__ID);
    createEAttribute(iSemanticLayoutEClass, ISEMANTIC_LAYOUT__NAME);
    createEAttribute(iSemanticLayoutEClass, ISEMANTIC_LAYOUT__ACTUAL_MAPPING);
    createEAttribute(iSemanticLayoutEClass, ISEMANTIC_LAYOUT__APPLIED_FILTERS);
    createEAttribute(iSemanticLayoutEClass, ISEMANTIC_LAYOUT__APPLIED_STYLES);

    edgeLayoutEClass = createEClass(EDGE_LAYOUT);
    createEReference(edgeLayoutEClass, EDGE_LAYOUT__BENDPOINTS);
    createEReference(edgeLayoutEClass, EDGE_LAYOUT__SOURCE);
    createEReference(edgeLayoutEClass, EDGE_LAYOUT__TARGET);

    nodeLayoutEClass = createEClass(NODE_LAYOUT);
    createEReference(nodeLayoutEClass, NODE_LAYOUT__BOUNDS);
    createEReference(nodeLayoutEClass, NODE_LAYOUT__OWNED_LAYOUTS);

    noteLayoutEClass = createEClass(NOTE_LAYOUT);
    createEReference(noteLayoutEClass, NOTE_LAYOUT__BOUNDS);

    sessionLayoutEClass = createEClass(SESSION_LAYOUT);
    createEReference(sessionLayoutEClass, SESSION_LAYOUT__OWNED_LAYOUTS);

    boundsEClass = createEClass(BOUNDS);

    locationEClass = createEClass(LOCATION);
    createEAttribute(locationEClass, LOCATION__X);
    createEAttribute(locationEClass, LOCATION__Y);
    createEAttribute(locationEClass, LOCATION__RELATIVE);

    sizeEClass = createEClass(SIZE);
    createEAttribute(sizeEClass, SIZE__WIDTH);
    createEAttribute(sizeEClass, SIZE__HEIGHT);
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
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
    ViewpointPackage theViewpointPackage = (ViewpointPackage)EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    diagramLayoutEClass.getESuperTypes().add(this.getISemanticLayout());
    iLayoutEClass.getESuperTypes().add(theViewpointPackage.getDRefreshable());
    iSemanticLayoutEClass.getESuperTypes().add(this.getILayout());
    edgeLayoutEClass.getESuperTypes().add(this.getISemanticLayout());
    nodeLayoutEClass.getESuperTypes().add(this.getISemanticLayout());
    noteLayoutEClass.getESuperTypes().add(this.getISemanticLayout());
    boundsEClass.getESuperTypes().add(this.getLocation());
    boundsEClass.getESuperTypes().add(this.getSize());

    // Initialize classes, features, and operations; add parameters
    initEClass(diagramLayoutEClass, DiagramLayout.class, "DiagramLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDiagramLayout_OwnedLayouts(), this.getILayout(), null, "ownedLayouts", null, 0, -1, DiagramLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagramLayout_AppliedLayers(), theEcorePackage.getEString(), "appliedLayers", null, 0, -1, DiagramLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagramLayout_Synchronized(), theEcorePackage.getEBoolean(), "synchronized", null, 0, 1, DiagramLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagramLayout_Description(), theEcorePackage.getEString(), "description", null, 0, 1, DiagramLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(iLayoutEClass, ILayout.class, "ILayout", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(iSemanticLayoutEClass, ISemanticLayout.class, "ISemanticLayout", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getISemanticLayout_Id(), theEcorePackage.getEString(), "id", null, 0, 1, ISemanticLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getISemanticLayout_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ISemanticLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getISemanticLayout_ActualMapping(), theEcorePackage.getEString(), "actualMapping", null, 0, 1, ISemanticLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getISemanticLayout_AppliedFilters(), theEcorePackage.getEString(), "appliedFilters", null, 0, -1, ISemanticLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getISemanticLayout_AppliedStyles(), theEcorePackage.getEString(), "appliedStyles", null, 0, -1, ISemanticLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(edgeLayoutEClass, EdgeLayout.class, "EdgeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEdgeLayout_Bendpoints(), this.getLocation(), null, "bendpoints", null, 0, -1, EdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeLayout_Source(), this.getISemanticLayout(), null, "source", null, 0, 1, EdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeLayout_Target(), this.getISemanticLayout(), null, "target", null, 0, 1, EdgeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeLayoutEClass, NodeLayout.class, "NodeLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNodeLayout_Bounds(), this.getBounds(), null, "bounds", null, 0, 1, NodeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeLayout_OwnedLayouts(), this.getILayout(), null, "ownedLayouts", null, 0, -1, NodeLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(noteLayoutEClass, NoteLayout.class, "NoteLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNoteLayout_Bounds(), this.getBounds(), null, "bounds", null, 0, 1, NoteLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sessionLayoutEClass, SessionLayout.class, "SessionLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSessionLayout_OwnedLayouts(), this.getDiagramLayout(), null, "ownedLayouts", null, 0, -1, SessionLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(boundsEClass, Bounds.class, "Bounds", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(locationEClass, Location.class, "Location", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocation_X(), theEcorePackage.getEInt(), "x", "-1", 0, 1, Location.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLocation_Y(), theEcorePackage.getEInt(), "y", "-1", 0, 1, Location.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLocation_Relative(), theEcorePackage.getEBoolean(), "relative", "false", 0, 1, Location.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sizeEClass, Size.class, "Size", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSize_Width(), theEcorePackage.getEInt(), "width", "-1", 0, 1, Size.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSize_Height(), theEcorePackage.getEInt(), "height", "-1", 0, 1, Size.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //LayoutPackageImpl
