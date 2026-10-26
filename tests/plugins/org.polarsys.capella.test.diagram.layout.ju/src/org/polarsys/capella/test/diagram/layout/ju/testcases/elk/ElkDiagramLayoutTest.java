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
 * Test Layout of diagram using ELK runtime.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class ElkDiagramLayoutTest extends BasicTestCase {
  
  private static final boolean ANALSYS_MODE = Boolean.getBoolean("capella.ElkDiagramLayoutTest.traces"); //$NON-NLS-1$
  
  private static final String MODEL_NAME = "ElkValidation"; //$NON-NLS-1$
  
  private static final String REF_PATH = "expected/"; //$NON-NLS-1$
  private static final String RESULT_PATH = "actual/"; //$NON-NLS-1$
  private static final int EXPECTED_CASE_NUMBER = 6;

  private Path projectPath;
  private Session session;
  
  @Override
  public List<String> getRequiredTestModels() {
    return List.of(MODEL_NAME);
  }
  
  private void prepateTest() {
    session = getSessionForTestModel(MODEL_NAME);
    projectPath = ModelProviderHelper.getInstance()
        .getModelProvider()
        .getAirdFileForLoadedModel(MODEL_NAME)
        .getProject().getLocation().toPath();
  }
  
  @Override
  public void test() throws Exception {
    prepateTest();

    List<String> failures = new ArrayList<>();
    int counter = 0;
    
    // We will compare N diagrams to avoid models overload.
    for (DDiagram diagram : ElkGraphUtils.getAllDiagrams(session)) {
      ElkNode graph = getArrangedGraph(diagram);
      
      if (graph != null) {
        counter++;
        Path reference = getGraphFile(diagram, REF_PATH);
        if (!ElkGraphUtils.hasSameContent(diagram.getName(), ElkGraphUtils.readGraphModel(reference), 
            graph)) {
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
      return null;
    }
    traceDiagramGraph(diagram, graph);
    
    return verifiable ? graph : null;
  }

  private void traceDiagramGraph(DDiagram diagram, ElkNode graph) throws IOException {
    if (ANALSYS_MODE) {
      ElkGraphUtils.saveAsText(graph, getGraphFile(diagram, RESULT_PATH));
    }
  }
  
  private Path getGraphFile(DDiagram diagram, String prefix) {
    return projectPath.resolve(prefix + diagram.getName() + ElkGraphUtils.GRAPH_EXTENSION);
  }
  
  private ElkNode arrange(DDiagram representation) throws IOException {
    var editor = (IDiagramWorkbenchPart) DiagramHelper.opendiagramEditor(session, representation);

    try {
      traceDiagramImage(representation, ".1before"); //$NON-NLS-1$
      arrangeAll(editor);
      traceDiagramImage(representation, ".2after"); //$NON-NLS-1$
      return ElkGraphUtils.createElkMapping(editor.getDiagramEditPart());
      
    } finally {
      DiagramHelper.closeEditor((IEditorPart)editor);
    }
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

}
