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
package org.polarsys.capella.test.diagram.layout.ju.layout;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory
 * @model kind="package"
 * @generated
 */
public interface LayoutPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "layout";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://org.polarsys.capella.layout";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "layout";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  LayoutPackage eINSTANCE = org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl.init();

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ILayout <em>ILayout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ILayout
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getILayout()
   * @generated
   */
  int ILAYOUT = 1;

  /**
   * The number of structural features of the '<em>ILayout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ILAYOUT_FEATURE_COUNT = ViewpointPackage.DREFRESHABLE_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>ILayout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ILAYOUT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout <em>ISemantic Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getISemanticLayout()
   * @generated
   */
  int ISEMANTIC_LAYOUT = 2;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT__ID = ILAYOUT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT__NAME = ILAYOUT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT__ACTUAL_MAPPING = ILAYOUT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Applied Filters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT__APPLIED_FILTERS = ILAYOUT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Applied Styles</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT__APPLIED_STYLES = ILAYOUT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>ISemantic Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT_FEATURE_COUNT = ILAYOUT_FEATURE_COUNT + 5;

  /**
   * The number of operations of the '<em>ISemantic Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ISEMANTIC_LAYOUT_OPERATION_COUNT = ILAYOUT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl <em>Diagram Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getDiagramLayout()
   * @generated
   */
  int DIAGRAM_LAYOUT = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__ID = ISEMANTIC_LAYOUT__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__NAME = ISEMANTIC_LAYOUT__NAME;

  /**
   * The feature id for the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__ACTUAL_MAPPING = ISEMANTIC_LAYOUT__ACTUAL_MAPPING;

  /**
   * The feature id for the '<em><b>Applied Filters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__APPLIED_FILTERS = ISEMANTIC_LAYOUT__APPLIED_FILTERS;

  /**
   * The feature id for the '<em><b>Applied Styles</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__APPLIED_STYLES = ISEMANTIC_LAYOUT__APPLIED_STYLES;

  /**
   * The feature id for the '<em><b>Owned Layouts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__OWNED_LAYOUTS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Applied Layers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__APPLIED_LAYERS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Synchronized</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__SYNCHRONIZED = ISEMANTIC_LAYOUT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT__DESCRIPTION = ISEMANTIC_LAYOUT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Diagram Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT_FEATURE_COUNT = ISEMANTIC_LAYOUT_FEATURE_COUNT + 4;

  /**
   * The number of operations of the '<em>Diagram Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_LAYOUT_OPERATION_COUNT = ISEMANTIC_LAYOUT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl <em>Edge Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getEdgeLayout()
   * @generated
   */
  int EDGE_LAYOUT = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__ID = ISEMANTIC_LAYOUT__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__NAME = ISEMANTIC_LAYOUT__NAME;

  /**
   * The feature id for the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__ACTUAL_MAPPING = ISEMANTIC_LAYOUT__ACTUAL_MAPPING;

  /**
   * The feature id for the '<em><b>Applied Filters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__APPLIED_FILTERS = ISEMANTIC_LAYOUT__APPLIED_FILTERS;

  /**
   * The feature id for the '<em><b>Applied Styles</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__APPLIED_STYLES = ISEMANTIC_LAYOUT__APPLIED_STYLES;

  /**
   * The feature id for the '<em><b>Bendpoints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__BENDPOINTS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__SOURCE = ISEMANTIC_LAYOUT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT__TARGET = ISEMANTIC_LAYOUT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Edge Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT_FEATURE_COUNT = ISEMANTIC_LAYOUT_FEATURE_COUNT + 3;

  /**
   * The number of operations of the '<em>Edge Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_LAYOUT_OPERATION_COUNT = ISEMANTIC_LAYOUT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl <em>Node Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getNodeLayout()
   * @generated
   */
  int NODE_LAYOUT = 4;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__ID = ISEMANTIC_LAYOUT__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__NAME = ISEMANTIC_LAYOUT__NAME;

  /**
   * The feature id for the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__ACTUAL_MAPPING = ISEMANTIC_LAYOUT__ACTUAL_MAPPING;

  /**
   * The feature id for the '<em><b>Applied Filters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__APPLIED_FILTERS = ISEMANTIC_LAYOUT__APPLIED_FILTERS;

  /**
   * The feature id for the '<em><b>Applied Styles</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__APPLIED_STYLES = ISEMANTIC_LAYOUT__APPLIED_STYLES;

  /**
   * The feature id for the '<em><b>Bounds</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__BOUNDS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Owned Layouts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT__OWNED_LAYOUTS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Node Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT_FEATURE_COUNT = ISEMANTIC_LAYOUT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Node Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_LAYOUT_OPERATION_COUNT = ISEMANTIC_LAYOUT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NoteLayoutImpl <em>Note Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.NoteLayoutImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getNoteLayout()
   * @generated
   */
  int NOTE_LAYOUT = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__ID = ISEMANTIC_LAYOUT__ID;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__NAME = ISEMANTIC_LAYOUT__NAME;

  /**
   * The feature id for the '<em><b>Actual Mapping</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__ACTUAL_MAPPING = ISEMANTIC_LAYOUT__ACTUAL_MAPPING;

  /**
   * The feature id for the '<em><b>Applied Filters</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__APPLIED_FILTERS = ISEMANTIC_LAYOUT__APPLIED_FILTERS;

  /**
   * The feature id for the '<em><b>Applied Styles</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__APPLIED_STYLES = ISEMANTIC_LAYOUT__APPLIED_STYLES;

  /**
   * The feature id for the '<em><b>Bounds</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT__BOUNDS = ISEMANTIC_LAYOUT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Note Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT_FEATURE_COUNT = ISEMANTIC_LAYOUT_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Note Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NOTE_LAYOUT_OPERATION_COUNT = ISEMANTIC_LAYOUT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.SessionLayoutImpl <em>Session Layout</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.SessionLayoutImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getSessionLayout()
   * @generated
   */
  int SESSION_LAYOUT = 6;

  /**
   * The feature id for the '<em><b>Owned Layouts</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SESSION_LAYOUT__OWNED_LAYOUTS = 0;

  /**
   * The number of structural features of the '<em>Session Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SESSION_LAYOUT_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Session Layout</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SESSION_LAYOUT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.LocationImpl <em>Location</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LocationImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getLocation()
   * @generated
   */
  int LOCATION = 8;

  /**
   * The feature id for the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__X = 0;

  /**
   * The feature id for the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__Y = 1;

  /**
   * The feature id for the '<em><b>Relative</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__RELATIVE = 2;

  /**
   * The number of structural features of the '<em>Location</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Location</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.BoundsImpl <em>Bounds</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.BoundsImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getBounds()
   * @generated
   */
  int BOUNDS = 7;

  /**
   * The feature id for the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS__X = LOCATION__X;

  /**
   * The feature id for the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS__Y = LOCATION__Y;

  /**
   * The feature id for the '<em><b>Relative</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS__RELATIVE = LOCATION__RELATIVE;

  /**
   * The feature id for the '<em><b>Width</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS__WIDTH = LOCATION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Height</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS__HEIGHT = LOCATION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Bounds</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS_FEATURE_COUNT = LOCATION_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Bounds</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOUNDS_OPERATION_COUNT = LOCATION_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.SizeImpl <em>Size</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.SizeImpl
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getSize()
   * @generated
   */
  int SIZE = 9;

  /**
   * The feature id for the '<em><b>Width</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE__WIDTH = 0;

  /**
   * The feature id for the '<em><b>Height</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE__HEIGHT = 1;

  /**
   * The number of structural features of the '<em>Size</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Size</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout <em>Diagram Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Diagram Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout
   * @generated
   */
  EClass getDiagramLayout();

  /**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getOwnedLayouts <em>Owned Layouts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Layouts</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getOwnedLayouts()
   * @see #getDiagramLayout()
   * @generated
   */
  EReference getDiagramLayout_OwnedLayouts();

  /**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getAppliedLayers <em>Applied Layers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Applied Layers</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getAppliedLayers()
   * @see #getDiagramLayout()
   * @generated
   */
  EAttribute getDiagramLayout_AppliedLayers();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#isSynchronized <em>Synchronized</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Synchronized</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#isSynchronized()
   * @see #getDiagramLayout()
   * @generated
   */
  EAttribute getDiagramLayout_Synchronized();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout#getDescription()
   * @see #getDiagramLayout()
   * @generated
   */
  EAttribute getDiagramLayout_Description();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ILayout <em>ILayout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ILayout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ILayout
   * @generated
   */
  EClass getILayout();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout <em>ISemantic Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ISemantic Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout
   * @generated
   */
  EClass getISemanticLayout();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getId()
   * @see #getISemanticLayout()
   * @generated
   */
  EAttribute getISemanticLayout_Id();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getName()
   * @see #getISemanticLayout()
   * @generated
   */
  EAttribute getISemanticLayout_Name();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getActualMapping <em>Actual Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Actual Mapping</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getActualMapping()
   * @see #getISemanticLayout()
   * @generated
   */
  EAttribute getISemanticLayout_ActualMapping();

  /**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedFilters <em>Applied Filters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Applied Filters</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedFilters()
   * @see #getISemanticLayout()
   * @generated
   */
  EAttribute getISemanticLayout_AppliedFilters();

  /**
   * Returns the meta object for the attribute list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedStyles <em>Applied Styles</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Applied Styles</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout#getAppliedStyles()
   * @see #getISemanticLayout()
   * @generated
   */
  EAttribute getISemanticLayout_AppliedStyles();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout <em>Edge Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Edge Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout
   * @generated
   */
  EClass getEdgeLayout();

  /**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getBendpoints <em>Bendpoints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bendpoints</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getBendpoints()
   * @see #getEdgeLayout()
   * @generated
   */
  EReference getEdgeLayout_Bendpoints();

  /**
   * Returns the meta object for the reference '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getSource()
   * @see #getEdgeLayout()
   * @generated
   */
  EReference getEdgeLayout_Source();

  /**
   * Returns the meta object for the reference '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout#getTarget()
   * @see #getEdgeLayout()
   * @generated
   */
  EReference getEdgeLayout_Target();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout <em>Node Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout
   * @generated
   */
  EClass getNodeLayout();

  /**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getBounds <em>Bounds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bounds</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getBounds()
   * @see #getNodeLayout()
   * @generated
   */
  EReference getNodeLayout_Bounds();

  /**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getOwnedLayouts <em>Owned Layouts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Layouts</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout#getOwnedLayouts()
   * @see #getNodeLayout()
   * @generated
   */
  EReference getNodeLayout_OwnedLayouts();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout <em>Note Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Note Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout
   * @generated
   */
  EClass getNoteLayout();

  /**
   * Returns the meta object for the containment reference '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout#getBounds <em>Bounds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bounds</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout#getBounds()
   * @see #getNoteLayout()
   * @generated
   */
  EReference getNoteLayout_Bounds();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout <em>Session Layout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Session Layout</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout
   * @generated
   */
  EClass getSessionLayout();

  /**
   * Returns the meta object for the containment reference list '{@link org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout#getOwnedLayouts <em>Owned Layouts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Layouts</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout#getOwnedLayouts()
   * @see #getSessionLayout()
   * @generated
   */
  EReference getSessionLayout_OwnedLayouts();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Bounds <em>Bounds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Bounds</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Bounds
   * @generated
   */
  EClass getBounds();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Location</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Location
   * @generated
   */
  EClass getLocation();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getX <em>X</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>X</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Location#getX()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_X();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#getY <em>Y</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Y</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Location#getY()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_Y();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location#isRelative <em>Relative</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Relative</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Location#isRelative()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_Relative();

  /**
   * Returns the meta object for class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Size <em>Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Size</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Size
   * @generated
   */
  EClass getSize();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Size#getWidth <em>Width</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Width</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Size#getWidth()
   * @see #getSize()
   * @generated
   */
  EAttribute getSize_Width();

  /**
   * Returns the meta object for the attribute '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Size#getHeight <em>Height</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Height</em>'.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Size#getHeight()
   * @see #getSize()
   * @generated
   */
  EAttribute getSize_Height();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  LayoutFactory getLayoutFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl <em>Diagram Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.DiagramLayoutImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getDiagramLayout()
     * @generated
     */
    EClass DIAGRAM_LAYOUT = eINSTANCE.getDiagramLayout();

    /**
     * The meta object literal for the '<em><b>Owned Layouts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAGRAM_LAYOUT__OWNED_LAYOUTS = eINSTANCE.getDiagramLayout_OwnedLayouts();

    /**
     * The meta object literal for the '<em><b>Applied Layers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAGRAM_LAYOUT__APPLIED_LAYERS = eINSTANCE.getDiagramLayout_AppliedLayers();

    /**
     * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAGRAM_LAYOUT__SYNCHRONIZED = eINSTANCE.getDiagramLayout_Synchronized();

    /**
     * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAGRAM_LAYOUT__DESCRIPTION = eINSTANCE.getDiagramLayout_Description();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ILayout <em>ILayout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.ILayout
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getILayout()
     * @generated
     */
    EClass ILAYOUT = eINSTANCE.getILayout();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout <em>ISemantic Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getISemanticLayout()
     * @generated
     */
    EClass ISEMANTIC_LAYOUT = eINSTANCE.getISemanticLayout();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ISEMANTIC_LAYOUT__ID = eINSTANCE.getISemanticLayout_Id();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ISEMANTIC_LAYOUT__NAME = eINSTANCE.getISemanticLayout_Name();

    /**
     * The meta object literal for the '<em><b>Actual Mapping</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ISEMANTIC_LAYOUT__ACTUAL_MAPPING = eINSTANCE.getISemanticLayout_ActualMapping();

    /**
     * The meta object literal for the '<em><b>Applied Filters</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ISEMANTIC_LAYOUT__APPLIED_FILTERS = eINSTANCE.getISemanticLayout_AppliedFilters();

    /**
     * The meta object literal for the '<em><b>Applied Styles</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ISEMANTIC_LAYOUT__APPLIED_STYLES = eINSTANCE.getISemanticLayout_AppliedStyles();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl <em>Edge Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.EdgeLayoutImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getEdgeLayout()
     * @generated
     */
    EClass EDGE_LAYOUT = eINSTANCE.getEdgeLayout();

    /**
     * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_LAYOUT__BENDPOINTS = eINSTANCE.getEdgeLayout_Bendpoints();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_LAYOUT__SOURCE = eINSTANCE.getEdgeLayout_Source();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_LAYOUT__TARGET = eINSTANCE.getEdgeLayout_Target();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl <em>Node Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.NodeLayoutImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getNodeLayout()
     * @generated
     */
    EClass NODE_LAYOUT = eINSTANCE.getNodeLayout();

    /**
     * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_LAYOUT__BOUNDS = eINSTANCE.getNodeLayout_Bounds();

    /**
     * The meta object literal for the '<em><b>Owned Layouts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_LAYOUT__OWNED_LAYOUTS = eINSTANCE.getNodeLayout_OwnedLayouts();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.NoteLayoutImpl <em>Note Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.NoteLayoutImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getNoteLayout()
     * @generated
     */
    EClass NOTE_LAYOUT = eINSTANCE.getNoteLayout();

    /**
     * The meta object literal for the '<em><b>Bounds</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NOTE_LAYOUT__BOUNDS = eINSTANCE.getNoteLayout_Bounds();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.SessionLayoutImpl <em>Session Layout</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.SessionLayoutImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getSessionLayout()
     * @generated
     */
    EClass SESSION_LAYOUT = eINSTANCE.getSessionLayout();

    /**
     * The meta object literal for the '<em><b>Owned Layouts</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SESSION_LAYOUT__OWNED_LAYOUTS = eINSTANCE.getSessionLayout_OwnedLayouts();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.BoundsImpl <em>Bounds</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.BoundsImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getBounds()
     * @generated
     */
    EClass BOUNDS = eINSTANCE.getBounds();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.LocationImpl <em>Location</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LocationImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getLocation()
     * @generated
     */
    EClass LOCATION = eINSTANCE.getLocation();

    /**
     * The meta object literal for the '<em><b>X</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCATION__X = eINSTANCE.getLocation_X();

    /**
     * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCATION__Y = eINSTANCE.getLocation_Y();

    /**
     * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCATION__RELATIVE = eINSTANCE.getLocation_Relative();

    /**
     * The meta object literal for the '{@link org.polarsys.capella.test.diagram.layout.ju.layout.impl.SizeImpl <em>Size</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.SizeImpl
     * @see org.polarsys.capella.test.diagram.layout.ju.layout.impl.LayoutPackageImpl#getSize()
     * @generated
     */
    EClass SIZE = eINSTANCE.getSize();

    /**
     * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE__WIDTH = eINSTANCE.getSize_Width();

    /**
     * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE__HEIGHT = eINSTANCE.getSize_Height();

  }

} //LayoutPackage
