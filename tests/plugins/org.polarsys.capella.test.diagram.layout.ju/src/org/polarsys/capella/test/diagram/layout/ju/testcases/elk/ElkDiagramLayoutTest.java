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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.text.ElkGraphStandaloneSetup;
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
 * Verifies automatic ELK layout on diagrams in the {@code ElkValidation} model. The test runs Arrange All
 * on diagrams with a reference file in {@code expected} and compares the resulting graphs with those references.
 * <p>
 * Enable {@code -Dcapella.ElkDiagramLayoutTest.traces=true} to export before/after PNGs and actual graphs
 * to the JUnit workspace. Diagrams without a reference are only arranged when tracing is enabled.
 * </p>
 * <p>
 * To verify a new rule, create a diagram that exercises it and run the test with tracing enabled.
 * To add or update a reference, inspect the exported layout before copying its {@code actual/*.elkt} file
 * to {@code model/ElkValidation/expected}. Element names must be unique to avoid generated random identifiers.
 * </p>
 *
 * <table border="1">
 * <caption>ELK regression coverage</caption>
 * <tr><th>Issue (PR)</th><th>Diagrams</th><th>Verified behavior</th></tr>
 * <tr><td>#3006 (#3083)</td><td>[LDFB] Root Logical Function, [PAB] Physical System</td>
 * <td>Minimum size of icon-only functions, including Route, Duplicate and Split.</td></tr>
 * <tr><td>#3007 (#3084)</td><td>[OCB] Operational Capabilities</td>
 * <td>Minimum capability size.</td></tr>
 * <tr><td>#3008 (#3085)</td><td>[OABD] Root Operational Activity, [SFBD] SF Container, [PFBD] PF Container</td>
 * <td>Tree hierarchy and edge orientation.</td></tr>
 * <tr><td>#3009 (#3086)</td><td>[LFCD] FunctionalChain 1</td>
 * <td>ELK Layered configuration, checked without a reference graph.</td></tr>
 * <tr><td>#3014 (#3087)</td><td>[OCB] Operational Capabilities, [LDFB] Root Logical Function, [PAB] Physical System</td>
 * <td>Balanced node placement.</td></tr>
 * <tr><td>#3015 (#3088)</td><td>[LAB] Simple flow, [SDFB] Root System Function</td>
 * <td>Readable functional-chain ends.</td></tr>
 * <tr><td>#3081 (#3089)</td><td>[LAB] Simple flow, [LDFB] Root Logical Function, [PAB] Physical System</td>
 * <td>Port sides and port-allocation direction.</td></tr>
 * </table>
 * <p>
 * [OAIB] Root Operational Activity and [OAB] Operational Entities provide additional flow-layout snapshots.
 * </p>
 *
 * @author nicolas.peransin@obeo.fr
 */
public class ElkDiagramLayoutTest extends BasicTestCase {

  private static final boolean ANALYSIS_MODE = Boolean.getBoolean("capella.ElkDiagramLayoutTest.traces"); //$NON-NLS-1$

  private static final String MODEL_NAME = "ElkValidation"; //$NON-NLS-1$

  private static final String REF_PATH = "expected"; //$NON-NLS-1$
  private static final String RESULT_PATH = "actual"; //$NON-NLS-1$

  private Path projectPath;
  private Session session;

  @Override
  public List<String> getRequiredTestModels() {
    return List.of(MODEL_NAME);
  }

  private void prepareTest() {
    // The test runtime does not install the ELK graph editor, which normally registers .elkt resources.
    ElkGraphStandaloneSetup.doSetup();
    session = getSessionForTestModel(MODEL_NAME);
    projectPath = ModelProviderHelper.getInstance().getModelProvider().getAirdFileForLoadedModel(MODEL_NAME)
        .getProject().getLocation().toPath();
  }

  private Path getGraphFile(DDiagram diagram, String prefix) {
    return projectPath.resolve(prefix).resolve(diagram.getName() + ElkGraphUtils.GRAPH_EXTENSION);
  }

  @Override
  public void test() throws Exception {
    prepareTest();

    List<String> failures = new ArrayList<>();
    List<String> dones = new ArrayList<>();

    for (DDiagram diagram : ElkGraphUtils.getAllDiagrams(session)) {
      ElkNode graph = getArrangedGraph(diagram);

      if (graph != null) { // Only testable diagram
        dones.add(diagram.getName());
        Path reference = getGraphFile(diagram, REF_PATH);
        if (!ElkGraphUtils.hasSameContent(diagram.getName(), ElkGraphUtils.readGraphModel(reference), graph)) {
          failures.add(diagram.getName());
        }
      }
    }
    Collections.sort(failures);
    assertTrue("Layout of Diagrams are unexpected: " + failures, failures.isEmpty()); //$NON-NLS-1$
    Collections.sort(dones);
    assertEquals("Some cases are missing",  getExpectedCases(), dones); //$NON-NLS-1$
  }

  private List<String> getExpectedCases() throws IOException {
    // Cases are sorted by name.
    return Files.list(projectPath.resolve(REF_PATH)) // In Project
        .map(it -> it.getFileName().toString()) // all files
        .filter(it -> it.endsWith(ElkGraphUtils.GRAPH_EXTENSION)) // of type graph
        .map(it -> it.substring(0, it.length() - ElkGraphUtils.GRAPH_EXTENSION.length())) // basename
        .sorted()
        .collect(Collectors.toList());
  }
  

  private ElkNode getArrangedGraph(DDiagram diagram) throws Exception {
    Path reference = getGraphFile(diagram, REF_PATH);
    boolean verifiable = Files.exists(reference);
    if (!verifiable && !ANALYSIS_MODE) {
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
    if (ANALYSIS_MODE) {
      try {
        String filename = representation.getName() + qualifier + ".png"; //$NON-NLS-1$
        ElkGraphUtils.exportPngImage(representation, session, projectPath.resolve(filename));
      } catch (IOException e) {
        // Not the test purpose
      }
    }
  }

  private void traceDiagramGraph(DDiagram diagram, ElkNode graph) throws IOException {
    if (ANALYSIS_MODE) {
      ElkGraphUtils.saveAsText(graph, getGraphFile(diagram, RESULT_PATH));
    }
  }

}
