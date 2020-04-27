/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

/**
 * Use org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool instead.
 */
@Deprecated
public class CreateEdgeTool extends AbstractToolStep<DEdge> {
  protected String sourceView;
  protected String targetView;
  protected String newIdentifier;
  protected int expectedNewElts;

  protected Collection<DDiagramElement> diagramElementsBefore;
  protected Collection<DDiagramElement> newDiagramElements;

  public CreateEdgeTool(DiagramContext context, String toolName, String newIdentifier, String sourceView,
      String targetView, int expectedNewElts) {
    super(context, toolName);
    this.sourceView = sourceView;
    this.targetView = targetView;
    this.newIdentifier = newIdentifier;
    this.expectedNewElts = expectedNewElts;
  }

  @Override
  protected void preRunTest() {
    super.preRunTest();
    diagramElementsBefore = getDiagramContext().getDiagram().getDiagramElements();
  }

  @Override
  protected void dispose() {
    super.dispose();
    diagramElementsBefore = null;
    newDiagramElements = null;
  }

  @Override
  protected void postRunTest() {
    super.postRunTest();
    newDiagramElements = new ArrayList<DDiagramElement>(getDiagramContext().getDiagram().getDiagramElements());
    newDiagramElements.removeAll(diagramElementsBefore);

    assertEquals(expectedNewElts, newDiagramElements.size());

    // Only 1 DEdge is expected
    if (Collections2.filter(newDiagramElements, Predicates.instanceOf(DEdge.class)).size() != 1) {
      assertFalse(true);
    }
  }

  @Override
  public DEdge getResult() {
    DEdge view = (DEdge) Iterables.find(newDiagramElements, Predicates.instanceOf(DEdge.class));
    if (newIdentifier != null) {
      getExecutionContext().putSemanticElement(newIdentifier, view.getTarget());
      getDiagramContext().putView(newIdentifier, view);
    }
    return view;
  }

  @Override
  protected void initToolArguments() {
    DSemanticDecorator source = getDiagramContext().getView(sourceView);
    DSemanticDecorator target = getDiagramContext().getView(targetView);
    _toolWrapper.setArgumentValue(ArgumentType.SOURCE, source);
    _toolWrapper.setArgumentValue(ArgumentType.TARGET, target);
  }
}
