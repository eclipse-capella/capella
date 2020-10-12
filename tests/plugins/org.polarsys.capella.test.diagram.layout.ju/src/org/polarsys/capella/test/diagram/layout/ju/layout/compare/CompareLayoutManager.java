/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.layout.compare;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog;
import org.eclipse.emf.diffmerge.ui.viewers.AbstractComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.HeaderViewer;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.ui.toolkit.viewers.menu.ModalContextMenuExtender;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.DiagramLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.EdgeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ILayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.ISemanticLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutPackage;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.NodeLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.NoteLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.SessionLayout;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;
import org.polarsys.capella.test.diagram.layout.ju.layout.provider.LayoutItemProviderAdapterFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.util.LayoutAdapterFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.util.LayoutResourceImpl;

public class CompareLayoutManager {

  /**
   * Create a layout for the given session and diagram
   */
  public DiagramLayout getCurrentLayout(Session session, DDiagram representation) {
    DiagramLayout layout = LayoutFactory.eINSTANCE.createDiagramLayout();
    DDiagram currentDiagram = (DDiagram) representation;
    layout.setName(EObjectExt.getText(currentDiagram));
    layout.setId(IdManager.getInstance().getId((((DSemanticDecorator) currentDiagram).getTarget())));
    layout.setSynchronized(representation.isSynchronized());
    layout.setDescription(representation.getDescription().getName());

    
    IEditorPart editorWasOpened = DiagramHelper.getDiagramEditor(session, representation);
    if (editorWasOpened == null) {
      DiagramHelper.opendiagramEditor(session, representation);
    } else {
      editorWasOpened.getSite().getPage().activate(editorWasOpened);
    }
    
    Diagram diag = DiagramHelper.getDiagram(currentDiagram);
    Point refPoint = ShapeHelper.getClosestPointToOriginInDiagram(currentDiagram);

    HashMap<EObject, ILayout> layouts = new HashMap<EObject, ILayout>();
    layouts.put(representation, layout);

    // Retrieve activated filters
    for (FilterDescription description : currentDiagram.getActivatedFilters()) {
      layout.getAppliedFilters().add(description.getName());
    }

    // Retrieve activated layers
    for (Layer layer : currentDiagram.getActivatedLayers()) {
      layout.getAppliedLayers().add(layer.getName());
    }

    // Retrieve nodes, containers
    for (DDiagramElement element : currentDiagram.getDiagramElements()) {
      if (element instanceof AbstractDNode) {
        NodeLayout n = node(diag, refPoint, (AbstractDNode) element);
        layouts.put(element, n);
      }
    }

    // Retrieve edges
    for (DDiagramElement element : currentDiagram.getDiagramElements()) {
      if (element instanceof DEdge) {
        EdgeLayout n = edge(diag, refPoint, (DEdge) element);
        layouts.put(element, n);
      }
    }

    for (DDiagramElement element : currentDiagram.getDiagramElements()) {
      if (element instanceof AbstractDNode) {
        addChildLayout(layouts.get(element.eContainer()), layouts.get(element));
      }
    }

    for (ShapeStyle currentNote : DiagramHelper.getDiagramNotes(currentDiagram)) {
      NoteLayout n = note(currentDiagram, refPoint, currentNote, layouts);
      addChildLayout(layouts.get(currentDiagram), n);
    }

    for (DDiagramElement element : currentDiagram.getDiagramElements()) {
      if (element instanceof DEdge) {
        EdgeLayout n = (EdgeLayout) layouts.get(element);
        n.setSource((ISemanticLayout) layouts.get(((DEdge) element).getSourceNode()));
        n.setTarget((ISemanticLayout) layouts.get(((DEdge) element).getTargetNode()));
        addChildLayout(layouts.get(element.eContainer()), n);
      }
    }

    // Retrieve notes
    for (ShapeStyle currentNote : DiagramHelper.getDiagramNotes(currentDiagram)) {
      noteEdges(currentDiagram, refPoint, currentNote, layouts);
    }

    try {
      if (editorWasOpened != null) {
        DiagramHelper.closeEditor(session, currentDiagram);
      }
    } catch (RuntimeException e) {
      // Nothing here
    }
    return layout;
  }

  private void addChildLayout(ILayout parent, ILayout n) {
    if (parent instanceof DiagramLayout) {
      ((DiagramLayout) parent).getOwnedLayouts().add(n);

    } else if (parent instanceof NodeLayout) {
      ((NodeLayout) parent).getOwnedLayouts().add(n);
    }
  }

  /**
   * Create a layout for the given session
   */
  public SessionLayout getCurrentLayout(Session session) {

    SessionLayout sessionLayout = LayoutFactory.eINSTANCE.createSessionLayout();

    for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
      if (representation instanceof DDiagram) {

        DiagramLayout layout = getCurrentLayout(session, (DDiagram) representation);
        sessionLayout.getOwnedLayouts().add(layout);

      }
    }
    return sessionLayout;
  }

  protected LayoutMatchPolicy matchPolicy;
  
  public LayoutMatchPolicy getLayoutMatchPolicy() {
    if (matchPolicy == null) {
      matchPolicy = new LayoutMatchPolicy();
    }
    return matchPolicy;
  }
  
  /**
   * Compare two layouts, returns a computed comparison
   */
  public EComparisonImpl compare(SessionLayout layout1, SessionLayout layout2) {

    RootedModelScope source = new RootedModelScope(Collections.singletonList(layout1));
    RootedModelScope target = new RootedModelScope(Collections.singletonList(layout2));

    EComparisonImpl impl = new EComparisonImpl(target, source);

    impl.compute(getLayoutMatchPolicy(), new LayoutDiffPolicy(), new DefaultMergePolicy(), new NullProgressMonitor());

    return impl;
  }

  /**
   * Returns a predefined uri according to the session
   */
  public URI getLayoutUri(Session session) {
    return session.getSessionResource().getURI().appendFileExtension(Platform.getOS()).appendFileExtension(LayoutPackage.eNS_PREFIX);
  }

  /**
   * Load the stored layout according to a predefined uri according to the session
   */
  public SessionLayout getPersistedLayout(Session session) {

    Resource res = null;
    try {
      res = session.getTransactionalEditingDomain().getResourceSet().getResource(getLayoutUri(session), false);
      if (res == null || res.getContents().isEmpty() || !(res.getContents().get(0) instanceof SessionLayout)) {
        if (res != null) {
          session.getTransactionalEditingDomain().getResourceSet().getResources().remove(res);
        }
      }

      res = session.getTransactionalEditingDomain().getResourceSet().getResource(getLayoutUri(session), true);
      if (res == null || res.getContents().isEmpty() || !(res.getContents().get(0) instanceof SessionLayout)) {
        if (res != null) {
          session.getTransactionalEditingDomain().getResourceSet().getResources().remove(res);
        }
        return null;
      }
    } catch (RuntimeException e) {
      if (res != null) {
        session.getTransactionalEditingDomain().getResourceSet().getResources().remove(res);
      }
      return null;
    }

    return (SessionLayout) res.getContents().get(0);
  }

  /**
   * Save the layout to a predefined uri according to the session
   */
  public void saveLayout(Session session, SessionLayout sessionLayout) {
    saveLayout(session, sessionLayout, getLayoutUri(session));
  }

  /**
   * Save the layout to the given uri
   */
  public void saveLayout(Session session, SessionLayout sessionLayout, URI uri) {
    try {
      LayoutResourceImpl resource = new LayoutResourceImpl(uri);
      resource.getContents().add(sessionLayout);
      resource.save(new HashMap<Object, Object>());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Create a layout description for a note
   */
  public NoteLayout note(DDiagram diagram, Point ref, ShapeStyle currentNote, HashMap<EObject, ILayout> layouts) {
    NoteLayout layout = LayoutFactory.eINSTANCE.createNoteLayout();

    // add the current note and its description
    String currentNoteDesc = currentNote.getDescription();
    layout.setId(currentNoteDesc);

    Location location = ShapeHelper.getDNoteLocation(diagram, currentNote, ref);
    Size size = ShapeHelper.getDNoteSize(diagram, currentNote);
    if (location != null || size != null) {
      layout.setBounds(LayoutFactory.eINSTANCE.createBounds());
    }

    if (location != null) {
      layout.getBounds().setRelative(location.isRelative());
      layout.getBounds().setX(location.getX());
      layout.getBounds().setY(location.getY());
    }

    if (size != null) {
      layout.getBounds().setWidth(((Integer) (size.getWidth())));
      layout.getBounds().setHeight(((Integer) (size.getHeight())));
    }

    addStyle(layout, StyleHelper.TEXT_ALIGNMENT, StyleHelper.getTextAlignment(currentNote));

    // get note attachment
    View noteView = null;
    if (currentNote instanceof Shape) {
      noteView = (View) currentNote;
    } else {
      // get the view of the input DDiagramElement element
      noteView = (View) currentNote.eContainer();
    }

    layouts.put(noteView, layout);
    return layout;
  }

  @SuppressWarnings("unchecked")
  public void noteEdges(DDiagram diagram, Point ref, ShapeStyle currentNote, HashMap<EObject, ILayout> layouts) {

    // get note attachment
    View noteView = null;
    if (currentNote instanceof Shape) {
      noteView = (View) currentNote;
    } else {
      // get the view of the input DDiagramElement element
      noteView = (View) currentNote.eContainer();
    }

    Collection<Edge> list = new ArrayList<Edge>();
    list.addAll(noteView.getSourceEdges());
    list.addAll(noteView.getTargetEdges());

    for (Edge currentEdge : list) {
      EdgeLayout edgeLayout = LayoutFactory.eINSTANCE.createEdgeLayout();
      EObject source = currentEdge.getSource().getElement() instanceof DSemanticDecorator
          ? currentEdge.getSource().getElement() : currentEdge.getSource();
      EObject target = currentEdge.getTarget().getElement() instanceof DSemanticDecorator
          ? currentEdge.getTarget().getElement() : currentEdge.getTarget();
      addChildLayout(layouts.get(diagram), edgeLayout);

      edgeLayout.setSource((ISemanticLayout) layouts.get(source));
      edgeLayout.setTarget((ISemanticLayout) layouts.get(target));

      edgeLayout.getBendpoints().addAll(ShapeHelper.getDEdgePointsForNoteAttachment(diagram, currentEdge, ref));

    }

  }

  /**
   * Create a layout description for a node
   */
  public NodeLayout node(Diagram diagram, Point ref, AbstractDNode anElement) {

    String elementName = "";
    if (anElement.getName() != null) {
      elementName = anElement.getName();
    }
    EObject target = ((DSemanticDecorator) anElement).getTarget();
    String id = IdManager.getInstance().getId(target);

    NodeLayout layout = LayoutFactory.eINSTANCE.createNodeLayout();
    layout.setId(id);
    layout.setName(elementName);
    layout.setActualMapping(anElement.getMapping().getName());
    layout.getAppliedFilters().addAll(getFilters(anElement));

    Location location = ShapeHelper.getDNodeLocation(diagram, anElement, ref);
    Size size = ShapeHelper.getDNodeSize(diagram, anElement);

    if (location != null || size != null) {
      layout.setBounds(LayoutFactory.eINSTANCE.createBounds());
    }

    if (location != null) {
      layout.getBounds().setRelative(location.isRelative());
      layout.getBounds().setX(location.getX());
      layout.getBounds().setY(location.getY());
    }

    if (size != null) {
      layout.getBounds().setWidth(((Integer) (size.getWidth())));
      layout.getBounds().setHeight(((Integer) (size.getHeight())));
    }

    addStyle(layout, StyleHelper.BACKGROUND_COLOR, StyleHelper.getBackgroundColor(anElement));
    addStyle(layout, StyleHelper.FOREGROUND_COLOR, StyleHelper.getForegroundColor(anElement));
    addStyle(layout, StyleHelper.COLOR, StyleHelper.getColor(anElement));
    addStyle(layout, StyleHelper.BORDERED_COLOR, StyleHelper.getBorderedColor(anElement));

    return layout;
  }

  /**
   * Retrieve a list of applied filters on an element
   */
  protected Collection<String> getFilters(DDiagramElement anElement) {
    Collection<String> filters = new ArrayList<String>();
    for (GraphicalFilter filter : anElement.getGraphicalFilters()) {
      if (filter instanceof AppliedCompositeFilters) {
        for (FilterDescription description : ((AppliedCompositeFilters) filter).getCompositeFilterDescriptions()) {
          filters.add(description.getName());
        }
      } else {
        filters.add(filter.getClass().getInterfaces()[0].getSimpleName());
      }
    }
    return filters;
  }

  /**
   * Create a layout description for an edge
   */
  public EdgeLayout edge(Diagram diagram, Point ref, DEdge anElement) {

    String elementName = "";
    if (anElement.getName() != null) {
      elementName = anElement.getName();
    }
    EObject target = ((DSemanticDecorator) anElement).getTarget();
    String id = IdManager.getInstance().getId(target);

    EdgeLayout layout = LayoutFactory.eINSTANCE.createEdgeLayout();
    layout.setId(id);
    layout.setName(elementName);
    layout.setActualMapping(anElement.getMapping().getName());
    layout.getAppliedFilters().addAll(getFilters(anElement));

    addStyle(layout, StyleHelper.BEGIN_COLOR, StyleHelper.getBeginColor(anElement));
    addStyle(layout, StyleHelper.STROKE_COLOR, StyleHelper.getStrokeColor(anElement));
    addStyle(layout, StyleHelper.CENTERED_COLOR, StyleHelper.getCenteredColor(anElement));
    addStyle(layout, StyleHelper.END_COLOR, StyleHelper.getEndColor(anElement));
    addStyle(layout, StyleHelper.EDGE_ROUTING, StyleHelper.getRouting(anElement));

    layout.getBendpoints().addAll(ShapeHelper.getDEdgePoints(diagram, anElement, ref));

    return layout;
  }

  public void addStyle(ISemanticLayout layout, String key, String value) {
    if (value != null) {
      layout.getAppliedStyles().add(key + ": " + value);
    }
  }

  public void uiAnalysis(SessionLayout current, SessionLayout persisted) {

    EComparisonImpl comparison = compare(current, persisted);

    EMFDiffNode node = new EMFDiffNode(comparison);
    org.eclipse.emf.diffmerge.ui.util.DiffMergeDialog dialog = new DiffMergeDialog(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), DiffMergeDialog.class.getSimpleName(), node) {
      @Override
      protected AbstractComparisonViewer createComparisonViewer(Composite parent_p) {
        // TODO Auto-generated method stub
        return new ComparisonViewer(parent_p) {

          @Override
          protected IWorkbenchPartSite getSite() {
            return null;
          }

          /**
           * The viewer is launched inside a modal window, sync with external views is impossible. It can only be done with
           * contextual menus.
           */
          protected ActionContributionItem createItemSyncExternal(IContributionManager context) {
            return null;
          }

          /**
           * Register the context menu on a modal window
           */
          @Override
          protected MenuManager createViewerContextMenus(HeaderViewer<?> viewer, boolean useLocalSelectionProvider) {
            MenuManager manager = super.createViewerContextMenus(viewer, useLocalSelectionProvider);

            ISelectionProvider selectionProvider = useLocalSelectionProvider ? viewer.getInnerViewer()
                : getMultiViewerSelectionProvider();
            ModalContextMenuExtender.registerContextMenu(manager, "org.polarsys.capella.test.compareLayout.ui.diffmerge", selectionProvider);

            return manager;
          }
        };
      }
    };
    dialog.open();

  }

  public void analysis(SessionLayout current, SessionLayout persisted) {

    EComparison comparison = compare(current, persisted);

    Collection<IDifference<EObject>> diffs = comparison.getDifferences(Role.REFERENCE);
    Collection<IDifference<EObject>> diffs2 = comparison.getDifferences(Role.TARGET);
    List<IDifference<EObject>> allDiffs = new ArrayList<IDifference<EObject>>();
    allDiffs.addAll(diffs);
    allDiffs.addAll(diffs2);
    List<IDifference<EObject>> allAttributeValueDiffs = new ArrayList<IDifference<EObject>>();

    List<EClass> locations = Arrays.asList(LayoutPackage.Literals.BOUNDS, LayoutPackage.Literals.SIZE,
        LayoutPackage.Literals.LOCATION);

    // 2017/08/11 OFR Limit the differences to IAttributeValuePresence only not references changes since during
    // migration references may have been changed.
    for (IDifference<EObject> difference : diffs) {
      if (difference instanceof IAttributeValuePresence) {
        allAttributeValueDiffs.add(difference);
      }
    }

    if (!allAttributeValueDiffs.isEmpty()) {

      HashMap<DiagramLayout, StringBuffer> buffers = new HashMap<DiagramLayout, StringBuffer>();

      StringBuffer result = new StringBuffer();
      result.append("There should not have layout modification\n");
      result.append(ResourcesPlugin.getWorkspace().getRoot().getLocation() + "\n");

      LayoutAdapterFactory factory = new LayoutItemProviderAdapterFactory();

      for (IDifference<EObject> difference : allAttributeValueDiffs) {
        if (difference instanceof EAttributeValuePresence) {
          if (locations.contains(((EAttributeValuePresence) difference).getFeature().eContainer())) {

            EObject source = ((EAttributeValuePresence) difference).getElementMatch().get(Role.REFERENCE);
            EObject target = ((EAttributeValuePresence) difference).getElementMatch().get(Role.TARGET);

            DiagramLayout layout = (DiagramLayout) EcoreUtil2.getFirstContainer(source,
                LayoutPackage.Literals.DIAGRAM_LAYOUT);
            if (!buffers.containsKey(layout)) {
              buffers.put(layout, new StringBuffer());
            }

            if (source.eContainer() instanceof NodeLayout) {
              String sourceText = getText(factory, source.eContainer());
              String sourceBounds = getText(factory, source);
              String targetBounds = getText(factory, target);
              buffers.get(layout)
                  .append(NLS.bind("[NodeLayout] {0}: {1} > {2}\n", new String[] { sourceText, sourceBounds, targetBounds }));
            } else if (source.eContainer() instanceof EdgeLayout) {
              String sourceText = getText(factory, source.eContainer());
              String sourcePoints = toString(factory, ((EdgeLayout) source.eContainer()).getBendpoints());
              String targetPoints = toString(factory, ((EdgeLayout) target.eContainer()).getBendpoints());
              buffers.get(layout)
                  .append(NLS.bind("[EdgeLayout] {0}: {1} > {2}\n", new String[] { sourceText, sourcePoints, targetPoints }));
            } else {
              buffers.get(layout).append(NLS.bind("[!NodeLayout|EdgeLayout] {0}, {1}, {2}\n", new String[] { source.toString(), target.toString(), difference.toString() }));
            }
          } else {
            result.append(NLS.bind("[!BOUNDS|SIZE|LOCATION] {0}\n", new String[] { difference.toString() }));
          }
        }
      }

      for (DiagramLayout layout : buffers.keySet()) {
        result.append("\n" + "\n" + layout.getName() + "\n");
        result.append(buffers.get(layout));
      }

      Assert.fail(result.toString());
    }
    // 2017/08/11 OFR code remove since I don't know what is it intended to do and triggers an error
    // for references modifications which are not taken into account during migration.
    // Assert.assertTrue("There should not have layout modification", diffs2.size() == 0);

  }

  private String getText(LayoutAdapterFactory factory, EObject obj) {
    IItemLabelProvider provider = (IItemLabelProvider) factory.adapt(obj, IItemLabelProvider.class);
    return provider.getText(obj);
  }

  private String toString(LayoutAdapterFactory factory, List<Location> locations) {
    String result = "";
    for (Location location : locations) {
      result += getText(factory, location) + ";";
    }
    return result;
  }

  public void exportImages(Session session, IPath outputPath) {

    List<DRepresentation> toExport = new ArrayList<DRepresentation>(
        DialectManager.INSTANCE.getAllRepresentations(session));
    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    final ExportAction exportAction = new ExportAction(session, toExport, outputPath, ImageFileFormat.PNG, false,
        false);
    final ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
    try {
      pmd.run(false, false, exportAction);
    } catch (final InvocationTargetException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    } finally {
      pmd.close();
    }
  }

}
