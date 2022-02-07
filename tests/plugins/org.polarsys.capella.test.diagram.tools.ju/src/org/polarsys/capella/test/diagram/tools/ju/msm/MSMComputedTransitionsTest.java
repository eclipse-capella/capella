/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.msm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.MSMDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class MSMComputedTransitionsTest extends AbstractDiagramTestCase {

  public static final String MODE4 = "184b065a-e311-4260-9506-d66e34498b97"; //$NON-NLS-1$
  public static final String MODE6 = "9576a98a-35f5-469a-8ee1-7e44e0706ed8"; //$NON-NLS-1$
  public static final String MODE8 = "b11f60ec-6ba1-4740-9f1f-0cfe4ee249f4"; //$NON-NLS-1$
  public static final String MODE3 = "cc3f249e-2954-48dc-94e4-301f7cb6a0b6"; //$NON-NLS-1$
  public static final String transition24 = "6b866b60-ab02-4137-aaed-b7454d84814e"; //$NON-NLS-1$
  public static final String transition23 = "ade45cc9-6c64-4360-b085-1510a1abd97e"; //$NON-NLS-1$
  public static final String transition6Join = "968ec028-0e70-4afd-9d1e-32781766b86c"; //$NON-NLS-1$
  public static final String transitionJoin8 = "cc0405b0-caa2-495e-9474-0ce5d9accdfc"; //$NON-NLS-1$
  public static final String transition13 = "bb822510-4444-4785-b90b-001c68207d78"; //$NON-NLS-1$
  public static final String transition14 = "2b4a930e-0f72-4181-8bc1-df905c1a23f4"; //$NON-NLS-1$
  public static final String DIAGRAM_NAME = "[MSM] Default Region"; //$NON-NLS-1$

  Session session;
  SessionContext context;
  TransactionalEditingDomain ted;
  MSMDiagram diagramContext;
  DDiagram diagram;

  @Override
  protected String getRequiredTestModel() {
    return "MSMComputedTransitions";
  }

  @Override
  public void test() throws Exception {
    session = getSessionForTestModel(getRequiredTestModel());
    context = new SessionContext(session);
    ted = session.getTransactionalEditingDomain();
    diagramContext = MSMDiagram.openDiagram(context, DIAGRAM_NAME, Type.SA);
    diagram = diagramContext.getDiagram();
    diagramContext.refreshDiagram();

    diagramContext.hasHiddenView(MODE4);
    diagramContext.hasHiddenView(MODE6);
    diagramContext.hasHiddenView(MODE8);
    hasComputedTransitionMapping(transition14);
    hasComputedTransitionMapping(transition23);
    hasComputedTransitionMapping(transition24);
    hasComputedTransitionMapping(transition6Join);
    hasComputedTransitionMapping(transitionJoin8);
    hasTransitionMapping(transition13);
  }

  private void hasComputedTransitionMapping(String id) {
    EObject semanticElement = context.getSemanticElement(id);
    List<DDiagramElement> viewsOnDiagram = getViewsOnDiagram(diagram, semanticElement);
    assertEquals(2, viewsOnDiagram.size());
    Optional<DDiagramElement> computedTransitionMapping = viewsOnDiagram.stream()
        .filter(v -> IMappingNameConstants.MSM_COMPUTED_TRANSITION_MAPPING_NAME.equals(v.getMapping().getName()))
        .findFirst();
    assertTrue(computedTransitionMapping.isPresent());
    assertTrue(computedTransitionMapping.get().isVisible());
    Optional<DDiagramElement> transitionMapping = viewsOnDiagram.stream()
        .filter(v -> IMappingNameConstants.MSM_TRANSITION_MAPPING_NAME.equals(v.getMapping().getName())).findFirst();
    assertTrue(transitionMapping.isPresent());
    assertFalse(transitionMapping.get().isVisible());
  }

  private void hasTransitionMapping(String id) {
    EObject semanticElement = context.getSemanticElement(id);
    List<DDiagramElement> viewsOnDiagram = getViewsOnDiagram(diagram, semanticElement);
    assertEquals(1, viewsOnDiagram.size());
    assertTrue(viewsOnDiagram.stream()
        .filter(v -> IMappingNameConstants.MSM_COMPUTED_TRANSITION_MAPPING_NAME.equals(v.getMapping().getName()))
        .findFirst().isEmpty());
    assertTrue(viewsOnDiagram.stream()
        .filter(v -> IMappingNameConstants.MSM_TRANSITION_MAPPING_NAME.equals(v.getMapping().getName())).findFirst()
        .isPresent());
  }

  public List<DDiagramElement> getViewsOnDiagram(final DDiagram diagram, final EObject semanticObject) {
    List<DDiagramElement> result = new ArrayList<>();
    List<DDiagramElement> elements = new LinkedList<DDiagramElement>(diagram.getDiagramElements());
    for (DDiagramElement element : elements) {
      if (element.getTarget() == semanticObject) {
        result.add(element);
      }
    }
    return result;
  }

  @Override
  protected void undoAllChanges() {
    // TODO Undo all changes does not work with this test
  }
}
