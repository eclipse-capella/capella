/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.testcases.elk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.elk.graph.ElkNode;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.ArrangeAction;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

/**
 * Test automatic Layout of diagrams using ELK runtime.
 * <p>
 * This test will open the model ElkValidation and layout the diagrams. <br/>
 * If a diagram has a corresponding ELK file in "expected" folder, the graph from the model 
 * and the graph from the file are compared.
 * </p>
 * <p>
 * For all diagrams, a graph is to store if the property 'capella.ElkDiagramLayoutTest.traces'
 * is enabled.<br/>
 * To verify a new rule:
 * <ul>
 * <li>Create a new diagram using the rule</li>
 * <li>Launch the test</li>
 * <li>Copy from the graph *.elkt from 'actual' folder of Junit workspace to 'model/expected' src folder.</li>
 * <li>Warning: Names must be unique otherwise ELK will generate random names</li>
 * <ul/>
 * </p>
 * <p>
 * If a rule evolve and some results are deprecated, replace in "expected" folder the file from
 * If a diagram has a corresponding ELK file in "expected" foler, the graph from the model 
 * and the graph from the file are compared.
 * </p>
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class ElkDiagramLayoutTest extends BasicTestCase {

  private static final boolean ANALSYS_MODE = Boolean.getBoolean("capella.ElkDiagramLayoutTest.traces"); //$NON-NLS-1$

  private static final String MODEL_NAME = "ElkValidation"; //$NON-NLS-1$

  private static final String REF_PATH = "expected/"; //$NON-NLS-1$
  private static final String RESULT_PATH = "actual/"; //$NON-NLS-1$
  private static final int EXPECTED_CASE_NUMBER = 7; // Number of files in 
  // /org.polarsys.capella.test.diagram.layout.ju/model/ElkValidation/expected

  private Path projectPath;
  private Session session;

  @Override
  public List<String> getRequiredTestModels() {
    return List.of(MODEL_NAME);
  }

  private void prepateTest() {
    session = getSessionForTestModel(MODEL_NAME);
    projectPath = ModelProviderHelper.getInstance().getModelProvider().getAirdFileForLoadedModel(MODEL_NAME)
        .getProject().getLocation().toPath();
  }

  private Path getGraphFile(DDiagram diagram, String prefix) {
    return projectPath.resolve(prefix + diagram.getName() + ElkGraphUtils.GRAPH_EXTENSION);
  }

  @Override
  public void test() throws Exception {
    prepateTest();

    List<String> failures = new ArrayList<>();
    int counter = 0;

    // We will compare N diagrams to avoid models overload.
    for (DDiagram diagram : ElkGraphUtils.getAllDiagrams(session)) {
      ElkNode graph = getArrangedGraph(diagram);

      if (graph != null) { // Only testable diagram
        counter++;
        Path reference = getGraphFile(diagram, REF_PATH);
        if (!ElkGraphUtils.hasSameContent(diagram.getName(), ElkGraphUtils.readGraphModel(reference), graph)) {
          failures.add(diagram.getName());
        }
      }
    }

    assertEquals("Layout of Diagrams are unexpected", List.of(), failures);
    assertEquals(EXPECTED_CASE_NUMBER, counter);
  }

  private ElkNode getArrangedGraph(DDiagram diagram) throws Exception {
    Path reference = getGraphFile(diagram, REF_PATH);
    boolean verifiable = Files.exists(reference);
    if (!verifiable && !ANALSYS_MODE) {
      return null;
    }
    ElkNode graph = arrange(diagram);
    if (graph == null) {
      // When the diagram does not use a ELK configuration.
      return null;
    }
    traceDiagramGraph(diagram, graph);

    return verifiable ? graph : null;
  }

  private ElkNode arrange(DDiagram representation) throws IOException {
    var editor = (IDiagramWorkbenchPart) DiagramHelper.opendiagramEditor(session, representation);

    try {
      traceDiagramImage(representation, ".1before"); //$NON-NLS-1$
      arrangeAll(editor);
      traceDiagramImage(representation, ".2after"); //$NON-NLS-1$
      return ElkGraphUtils.createElkMapping(editor.getDiagramEditPart());

    } finally {
      DiagramHelper.closeEditor((IEditorPart) editor);
    }
  }

  private static void arrangeAll(IDiagramWorkbenchPart part) throws IOException {
    ElkGraphUtils.disableSnaps(part); // As user should do for all layout with elk.

    IWorkbenchPage page = part.getSite().getPage();
    @SuppressWarnings("restriction")
    ArrangeAction action = ArrangeAction.createArrangeAllAction(page);
    if (page.getActivePart() != part) {
      page.activate(part);
    }
    action.init(); // with part as activePart of page.
    action.run();
  }

  private void traceDiagramImage(DDiagram representation, String qualifier) {
    if (ANALSYS_MODE) {
      try {
        String filename = representation.getName() + qualifier + ".png"; //$NON-NLS-1$
        ElkGraphUtils.exportPngImage(representation, session, projectPath.resolve(filename));
      } catch (IOException e) {
        // Not the test purpose
      }
    }
  }

  private void traceDiagramGraph(DDiagram diagram, ElkNode graph) throws IOException {
    if (ANALSYS_MODE) {
      ElkGraphUtils.saveAsText(graph, getGraphFile(diagram, RESULT_PATH));
    }
  }

}
