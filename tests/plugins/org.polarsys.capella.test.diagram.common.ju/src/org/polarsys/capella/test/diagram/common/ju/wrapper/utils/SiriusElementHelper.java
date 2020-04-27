/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * Utilitary class on various Sirius elements
 */
public class SiriusElementHelper {

  public static final Collection<ArrangeConstraint> PINNED_CONSTRAINTS = new ArrayList<ArrangeConstraint>();

  static {
    PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_LOCATION);
    PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_SIZE);
    PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_RATIO);
  }

  /**
   * Test if an EdgeTarget element has the same ID than a ModelElement
   * @param anEdgeTarget_p , the EdgeTarget to test
   * @param expectedTarget_p , the ModelElement reference to compare
   * @return a boolean: true if an EdgeTarget has the same ID than the expected ModelElement otherwise false.
   */

  public static void testComparisonEdgeTarget(EdgeTarget anEdgeTarget_p, ModelElement expectedTarget_p) {

    boolean result = false;
    result = compareEdgeTargetByID(anEdgeTarget_p, expectedTarget_p);
    Assert.assertTrue(MessageFormat.format(Messages.edgeTargetComparisonFalse,
        ((ModelElement) ((DSemanticDecorator) anEdgeTarget_p).getTarget()).getId(), expectedTarget_p.getId()), result);
  }

  /**
   * Check if an EdgeTarget element has the same ID than a ModelElement
   * @param anEdgeTarget_p , the EdgeTarget to test
   * @param expectedTarget_p , the ModelElement reference to compare
   * @return a boolean: true if an EdgeTarget has the same ID than the expected ModelElement otherwise false.
   */

  public static boolean compareEdgeTargetByID(EdgeTarget anEdgeTarget_p, ModelElement expectedTarget_p) {

    boolean result = false;

    result = compareEdgeTarget(anEdgeTarget_p, expectedTarget_p, ModellingcorePackage.Literals.MODEL_ELEMENT, ModellingcorePackage.Literals.MODEL_ELEMENT__ID);

    return result;
  }

  /**
   * Generic method to compare an EdgeTarget with EObject by a feature
   * @param anEdgeTarget_p , the EdgeTarget to test
   * @param expectedTarget_p , the EObject reference to compare
   * @param expectedSemanticMetaClass_p , the class of the feature to check
   * @param featureToCompare_p , the comparison criteria
   * @return a boolean: true if an EdgeTarget has the same feature value than the expected EObject otherwise false.
   */
  public static boolean compareEdgeTarget(EdgeTarget anEdgeTarget_p, EObject expectedTarget_p, EClass expectedSemanticMetaClass_p,
      EStructuralFeature featureToCompare_p) {

    boolean result = false;

    EObject semanticTarget = ((DSemanticDecorator) anEdgeTarget_p).getTarget();

    result = expectedSemanticMetaClass_p.isSuperTypeOf(expectedSemanticMetaClass_p);

    result &= semanticTarget.eGet(featureToCompare_p).equals(expectedTarget_p.eGet(featureToCompare_p));

    return result;
  }

  /**
   * Method to compare Diagram Element name
   * @param element_p , the DDiagramElement to test
   * @param expectedName_p , the expected diagram element name
   */
  public static void checkDDiagramElementName(DDiagramElement element_p, String expectedName_p) {
    String actualName = element_p.getName();
    boolean hasSameName = actualName.equals(expectedName_p);
    Assert.assertTrue(MessageFormat.format(Messages.wrongElementName, expectedName_p, actualName), hasSameName);
  }

  public static void checkMultiEdgesTargets(DDiagram diagram_p, CapellaElement source_p, List<CapellaElement> targetList_p) {
    List<DEdge> edges = diagram_p.getEdges();
    // source DEdges target from Model
    List<CapellaElement> actualEdgeTargets = new ArrayList<CapellaElement>();
    // build list of source_p targets
    for (int i = 0; i < edges.size(); i++) {
      EdgeTarget edgeSource = edges.get(i).getSourceNode();
      EdgeTarget edgeTarget = edges.get(i).getTargetNode();
      if (SiriusElementHelper.compareEdgeTargetByID(edgeSource, source_p)) {
        actualEdgeTargets.add((CapellaElement) ((DSemanticDecorator) edgeTarget).getTarget());
      }
    }
    // compare the source_p targets list with the expected targets list
    boolean hasSameTargets = (targetList_p.containsAll(actualEdgeTargets) && actualEdgeTargets.containsAll(targetList_p));

    Assert.assertTrue(MessageFormat.format(Messages.multiEdgeTargetsError, source_p.getLabel()), hasSameTargets);
  }

  public static void checkEdgeSourceAndTarget(DDiagram diagram_p, CapellaElement edge_p, CapellaElement source_p, CapellaElement target_p) {
    DDiagramElement element = DiagramHelper.getOnDiagram(diagram_p, edge_p);
    Assert.assertTrue(MessageFormat.format(Messages.noEdgeDetected, source_p.getLabel()), element instanceof DEdge);
    DEdge edge = (DEdge) element;
    // Link test
    EdgeTarget edgeSource = edge.getSourceNode();
    EdgeTarget edgeTarget = edge.getTargetNode();
    SiriusElementHelper.testComparisonEdgeTarget(edgeSource, source_p);
    SiriusElementHelper.testComparisonEdgeTarget(edgeTarget, target_p);
  }

  public static void checkEdgeSourceAndTarget(DEdge edge_p, DNode source_p, DNode target_p) {
    // source
    EdgeTarget edgeSource = edge_p.getSourceNode();
    boolean hasSameSource = edgeSource.equals(source_p);
    Assert.assertTrue("The expected source and the actual one are different.", hasSameSource); //$NON-NLS-1$
    // target
    EdgeTarget edgeTarget = edge_p.getTargetNode();
    boolean hasSameTarget = edgeTarget.equals(target_p);
    Assert.assertTrue("The expected target and the actual one are different.", hasSameTarget); //$NON-NLS-1$
  }

  /**
   * method to get all DSemanticDiagramSpec of a session
   */
  public static Collection<DRepresentation> getAllDSemanticDiagrams(final Session session_p) {

    Collection<DRepresentation> dRepresentations = DialectManager.INSTANCE.getAllRepresentations(session_p);
    Collection<DRepresentation> result = new ArrayList<DRepresentation>();
    for (DRepresentation aRepresentation : dRepresentations) {
      if (aRepresentation instanceof DSemanticDiagram) {
        result.add(aRepresentation);
      }
    }
    return result;
  }

  /**
   * method to get all DEdge of a diagram
   */
  public static Collection<DEdge> getDSemanticDiagramDEdge(DRepresentation representation_p) {
    DDiagram diagram = (DDiagram) representation_p;
    EList<DDiagramElement> diagramElements = diagram.getDiagramElements();
    Collection<DEdge> result = new ArrayList<DEdge>();
    for (DDiagramElement anElement : diagramElements) {
      if (anElement instanceof DEdge) {
        result.add((DEdge) anElement);
      }
    }
    return result;
  }

  /**
   * get the DEdge by its source and target
   */
  public static DEdge getDEdgeByIdSourceTarget(DDiagram diagram_p, String expectedId_p, EdgeTarget expectedSource_p, EdgeTarget expectedTarget_p) {
    Collection<DEdge> allDiagramEdges = getDSemanticDiagramDEdge(diagram_p);
    for (DEdge anEdge : allDiagramEdges) {
      String id = (String) (((DSemanticDecorator) anEdge).getTarget()).eGet(ModellingcorePackage.Literals.MODEL_ELEMENT__ID);
      if (id.equals(expectedId_p) && anEdge.getSourceNode().equals(expectedSource_p) && anEdge.getTargetNode().equals(expectedTarget_p)) {
        return anEdge;
      }
    }

    return null;
  }

  /**
   * Get the Arrange Constraints of an element
   * @param diagramElement
   * @return
   */

  private static List<ArrangeConstraint> getArrangeConstraints(final EObject diagramElement) {
    List<ArrangeConstraint> constraints = null;
    if (diagramElement instanceof AbstractDNode) {
      final AbstractDNode node = (AbstractDNode) diagramElement;
      constraints = node.getArrangeConstraints();
    } else if (diagramElement instanceof DEdge) {
      final DEdge edge = (DEdge) diagramElement;
      constraints = edge.getArrangeConstraints();
    }
    return constraints;
  }

  /**
   * return the boolean value if the input element is pinned
   * @param dDiagramElement
   * @return
   */
  public static boolean isPinned(final DDiagramElement dDiagramElement) {
    boolean isPinned = false;
    List<ArrangeConstraint> constraints = getArrangeConstraints(dDiagramElement);
    isPinned = (constraints != null) && constraints.containsAll(PINNED_CONSTRAINTS);
    return isPinned;
  }
}
