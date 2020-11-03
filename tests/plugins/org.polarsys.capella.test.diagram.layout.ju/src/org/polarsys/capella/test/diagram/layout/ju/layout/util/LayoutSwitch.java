/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage
 * @generated
 */
public class LayoutSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static LayoutPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LayoutSwitch() {
    if (modelPackage == null) {
      modelPackage = LayoutPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case LayoutPackage.DIAGRAM_LAYOUT: {
        DiagramLayout diagramLayout = (DiagramLayout)theEObject;
        T result = caseDiagramLayout(diagramLayout);
        if (result == null) result = caseISemanticLayout(diagramLayout);
        if (result == null) result = caseILayout(diagramLayout);
        if (result == null) result = caseDRefreshable(diagramLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.ILAYOUT: {
        ILayout iLayout = (ILayout)theEObject;
        T result = caseILayout(iLayout);
        if (result == null) result = caseDRefreshable(iLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.ISEMANTIC_LAYOUT: {
        ISemanticLayout iSemanticLayout = (ISemanticLayout)theEObject;
        T result = caseISemanticLayout(iSemanticLayout);
        if (result == null) result = caseILayout(iSemanticLayout);
        if (result == null) result = caseDRefreshable(iSemanticLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.EDGE_LAYOUT: {
        EdgeLayout edgeLayout = (EdgeLayout)theEObject;
        T result = caseEdgeLayout(edgeLayout);
        if (result == null) result = caseISemanticLayout(edgeLayout);
        if (result == null) result = caseILayout(edgeLayout);
        if (result == null) result = caseDRefreshable(edgeLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.NODE_LAYOUT: {
        NodeLayout nodeLayout = (NodeLayout)theEObject;
        T result = caseNodeLayout(nodeLayout);
        if (result == null) result = caseISemanticLayout(nodeLayout);
        if (result == null) result = caseILayout(nodeLayout);
        if (result == null) result = caseDRefreshable(nodeLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.NOTE_LAYOUT: {
        NoteLayout noteLayout = (NoteLayout)theEObject;
        T result = caseNoteLayout(noteLayout);
        if (result == null) result = caseISemanticLayout(noteLayout);
        if (result == null) result = caseILayout(noteLayout);
        if (result == null) result = caseDRefreshable(noteLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.SESSION_LAYOUT: {
        SessionLayout sessionLayout = (SessionLayout)theEObject;
        T result = caseSessionLayout(sessionLayout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.BOUNDS: {
        Bounds bounds = (Bounds)theEObject;
        T result = caseBounds(bounds);
        if (result == null) result = caseLocation(bounds);
        if (result == null) result = caseSize(bounds);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.LOCATION: {
        Location location = (Location)theEObject;
        T result = caseLocation(location);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case LayoutPackage.SIZE: {
        Size size = (Size)theEObject;
        T result = caseSize(size);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Diagram Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Diagram Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDiagramLayout(DiagramLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ILayout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ILayout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseILayout(ILayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ISemantic Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ISemantic Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseISemanticLayout(ISemanticLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Edge Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Edge Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEdgeLayout(EdgeLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNodeLayout(NodeLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Note Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Note Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNoteLayout(NoteLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Session Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Session Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSessionLayout(SessionLayout object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bounds</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bounds</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBounds(Bounds object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Location</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Location</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocation(Location object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Size</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Size</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSize(Size object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>DRefreshable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>DRefreshable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDRefreshable(DRefreshable object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }

} //LayoutSwitch
