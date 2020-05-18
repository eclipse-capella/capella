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
package org.polarsys.capella.test.diagram.layout.ju.layout.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.polarsys.capella.test.diagram.layout.ju.layout.Bounds;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ILayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage
 * @generated
 */
public class LayoutAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static LayoutPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LayoutAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = LayoutPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LayoutSwitch<Adapter> modelSwitch =
    new LayoutSwitch<Adapter>() {
      @Override
      public Adapter caseDiagramLayout(DiagramLayout object) {
        return createDiagramLayoutAdapter();
      }
      @Override
      public Adapter caseILayout(ILayout object) {
        return createILayoutAdapter();
      }
      @Override
      public Adapter caseISemanticLayout(ISemanticLayout object) {
        return createISemanticLayoutAdapter();
      }
      @Override
      public Adapter caseEdgeLayout(EdgeLayout object) {
        return createEdgeLayoutAdapter();
      }
      @Override
      public Adapter caseNodeLayout(NodeLayout object) {
        return createNodeLayoutAdapter();
      }
      @Override
      public Adapter caseNoteLayout(NoteLayout object) {
        return createNoteLayoutAdapter();
      }
      @Override
      public Adapter caseSessionLayout(SessionLayout object) {
        return createSessionLayoutAdapter();
      }
      @Override
      public Adapter caseBounds(Bounds object) {
        return createBoundsAdapter();
      }
      @Override
      public Adapter caseLocation(Location object) {
        return createLocationAdapter();
      }
      @Override
      public Adapter caseSize(Size object) {
        return createSizeAdapter();
      }
      @Override
      public Adapter caseDRefreshable(DRefreshable object) {
        return createDRefreshableAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout <em>Diagram Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout
   * @generated
   */
  public Adapter createDiagramLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ILayout <em>ILayout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ILayout
   * @generated
   */
  public Adapter createILayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout <em>ISemantic Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout
   * @generated
   */
  public Adapter createISemanticLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout <em>Edge Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout
   * @generated
   */
  public Adapter createEdgeLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout <em>Node Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout
   * @generated
   */
  public Adapter createNodeLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout <em>Note Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout
   * @generated
   */
  public Adapter createNoteLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout <em>Session Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout
   * @generated
   */
  public Adapter createSessionLayoutAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Bounds <em>Bounds</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Bounds
   * @generated
   */
  public Adapter createBoundsAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Location <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Location
   * @generated
   */
  public Adapter createLocationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.polarsys.capella.test.diagram.layout.ju.layout.Size <em>Size</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.polarsys.capella.test.diagram.layout.ju.layout.Size
   * @generated
   */
  public Adapter createSizeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.sirius.viewpoint.DRefreshable
   * @generated
   */
  public Adapter createDRefreshableAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} //LayoutAdapterFactory
