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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.LayoutConnectorsService;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.graph.ElkGraphPackage;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.util.GraphIdentifierGenerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.sirius.elk.CapellaSiriusLayoutProvider;

import com.google.inject.Injector;

/**
 * Utility class for basic manipulation of ELK Graph and associated diagram.
 * 
 * @author nicolas.peransin@obeo.fr
 */
public class ElkGraphUtils {

  /** Extension of file for ELK Graph models. */
  // We cannot use "elkg".
  // Cause: deserialization uses special objects instead of API Enum.
  public static final String GRAPH_EXTENSION = ".elkt"; //$NON-NLS-1$

  
  private static final ExportFormat PNG_EXPORT_FORMAT = new ExportFormat(ExportFormat.ExportDocumentFormat.NONE, 
      ImageFileFormat.PNG, ExportFormat.ScalingPolicy.NO_SCALING, 0);
  
  private static final ElkGraphPackage PKG = ElkGraphPackage.eINSTANCE;
  
  private static final List<EStructuralFeature> GRAPH_REDUNDANCIES = List.of(
      PKG.getElkConnectableShape_OutgoingEdges(),
      PKG.getElkConnectableShape_IncomingEdges()
      );
  
  /**
   * Compares that 2 graphs are identical.
   * 
   * @param title name of the compare content
   * @param expected reference graph 
   * @param actual tested graph
   * @return true if identical
   */
  public static boolean hasSameContent(String title, EObject expected, EObject actual) {
    // Impossible to use textual comparison of ElkGraph.
    // Map attribute produces a different order for properties.
    EObjectVerification verification = new EObjectVerification(expected, actual);
    verification.setRedundancies(GRAPH_REDUNDANCIES);
    try {
      verification.perform();
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }
  

  public static ElkNode cleanGraph(ElkNode root) {
    
    // Disable the layout stored in this graph to avoid an automatic layout during the opening in "Layout Graph"
    // view of in interactive web editor (https://rtsys.informatik.uni-kiel.de/elklive/).
    root.setProperty(CoreOptions.NO_LAYOUT, true);

    // Inspired from org.eclipse.elk.graph.text.ui.ConvertGraphHandler
    // Unsupported by rendering
    removeProperty(root, CoreOptions.RESOLVED_ALGORITHM);

    // Write missing/fix identifiers into the graph.
    GraphIdentifierGenerator.forGraph(root).assertValid().assertExists().assertUnique().execute();
    
    return root;
  }

  private static void removeProperty(ElkNode it, IProperty<?> key) {
    it.setProperty(key, null);
    it.getChildren().forEach(child -> removeProperty(child, key));
  }

  /**
   * Creates a mapping of provided diagram.
   * <p>
   * Provided graph is cleaned, so textual saving and comparison is safe.
   * </p>
   * 
   * @param diagramEditPart diagram part
   * @return {@link LayoutMapping}
   */
  public static ElkNode createElkMapping(DiagramEditPart diagramEditPart) {
    @SuppressWarnings("restriction")
    CustomLayoutConfiguration layoutConfiguration = new CapellaSiriusLayoutProvider()
      .getLayoutConfiguration(diagramEditPart)
      .orElse(null);
    if (layoutConfiguration == null) {
      return null;
    }
    Injector injector = LayoutConnectorsService.getInstance().getInjector(null, diagramEditPart.getChildren());

    ElkDiagramLayoutConnector connector = injector.getInstance(ElkDiagramLayoutConnector.class);
    connector.setLayoutConfiguration(layoutConfiguration);
    LayoutMapping layoutMapping = connector.buildLayoutGraph(diagramEditPart, diagramEditPart.getChildren(), true, false);

    // Unlike the real arrange, IELKLayoutExtension are not invoked to avoid alteration of graph.
    // We need the actual graph not the one tuned for ELK engine.
    return cleanGraph(layoutMapping.getLayoutGraph());
  }
  
  /**
   * Reads the graph from a file.
   * 
   * @param target
   * @return
   * @throws IOException
   */
  public static ElkNode readGraphModel(Path target) throws IOException {
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createFileURI(target.toString()));
    resource.load(Map.of());
    return (ElkNode) resource.getContents().get(0);
  }
  
  /**
   * Saves the graph as a textual file.
   * 
   * @param mapping graph to save
   * @param target file to save i
   * @return {@code target}
   * @throws IOException if writing fails.
   */
  public static Path saveAsText(ElkNode graph, Path target) throws IOException {
    // See ExportToElkGraphHandler
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(URI.createFileURI(target.toString()));
    resource.getContents().add(graph);

    Files.createDirectories(target.getParent());
    resource.save(Collections.emptyMap());

    return target;
  }
  
  /**
   * Exports the diagram as an image in a file.
   * 
   * @param representation diagram to export
   * @param session session of the diagram
   * @param target file to export the diagram in
   * @throws IOException if export fails.
   */
  public static void exportPngImage(DRepresentation representation, Session session, Path target) throws IOException {
    try {
      DialectUIManager.INSTANCE.export(representation, session, org.eclipse.core.runtime.Path.fromOSString(target.toString()), 
          PNG_EXPORT_FORMAT, new NullProgressMonitor());
    } catch (SizeTooLargeException e) {
      throw new IOException(e);
    }
  }

  /**
   * Returns the diagrams from a session.
   * 
   * @param session container of diagrams
   * @return lists of {@link DDiagram}
   */
  public static List<DDiagram> getAllDiagrams(Session session) {
    return DialectManager.INSTANCE.getAllRepresentationDescriptors(session)
        .stream()
        .map(DRepresentationDescriptor::getRepresentation)
        .filter(DDiagram.class::isInstance)
        .map(DDiagram.class::cast)
        .collect(Collectors.toList());
  }
  
  
  /**
   * Removes Snap directives when arranging diagram elements.
   * 
   * @param part diagram part to update
   * @throws IOException if diagram preferences cannot be updated.
   */
  @SuppressWarnings("restriction")
  public static void disableSnaps(IDiagramWorkbenchPart part) throws IOException {
    DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) part.getDiagramGraphicalViewer();
    var prefStore = viewer.getWorkspaceViewerPreferenceStore();
    prefStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, false);
    prefStore.setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY, false);
    if (prefStore instanceof IPersistentPreferenceStore pStore) {
      pStore.save();
    } 
  }
}
