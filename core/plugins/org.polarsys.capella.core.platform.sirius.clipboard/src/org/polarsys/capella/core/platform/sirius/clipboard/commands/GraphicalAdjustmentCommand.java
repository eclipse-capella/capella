/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.util.ViewRefactorHelper;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.tools.api.layout.PinHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Point;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;
import org.polarsys.capella.core.platform.sirius.clipboard.util.SiriusUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.GmfUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;

/**
 * This command adjusts the layout of Sirius/GMF elements so that they look
 * like the original elements modulo a specified location.
 */
public class GraphicalAdjustmentCommand extends AbstractResultCommand {

  // The Sirius elements whose layout has to be adjusted
  private List<DSemanticDecorator> _pastedSiriusElements;
  
  // A map allowing to retrieve, from a Sirius element E1, the Sirius element
  // whose layout must be replicated on E1
  private Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> _originsMapping;
  
  // The view in which paste occurred
  private View _targetView;
  
  // The expected location of the top-left corner of the pasted elements,
  // relative to the graphical container
  private Point _relativeLocation;
  
  // Whether refresh must be performed at the end (e.g. to draw arcs)
  private boolean _mustRefresh;
  
  // A non-null GMF helper for edge style adaptation
  private ViewRefactorHelper _refactorHelper;
  
  // A non-null helper for pinning graphical elements
  private final PinHelper _pinHelper;
  
  
  /**
   * Full constructor
   * @param targets_p The Sirius elements whose layout has to be adjusted
   * @param origins_p A map allowing to retrieve, from a Sirius element E1,
   *          the Sirius element whose layout must be replicated on E1
   * @param targetView_p The encompassing view (e.g. in which paste occurred)
   * @param newLocation_p The expected location of the top-left corner of the
   *          pasted elements, relative to the graphical container
   * @param mustRefresh_p Whether refresh must be performed at the end
   *          (e.g. to draw arcs)
   */
  public GraphicalAdjustmentCommand(List<? extends EObject> targets_p,
      Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> origins_p,
      View targetView_p, Point newLocation_p, boolean mustRefresh_p) {
    assert origins_p != null;
    assert targets_p != null;
    _originsMapping = origins_p;
    _pastedSiriusElements = MiscUtil.filter(targets_p, DSemanticDecorator.class);
    _targetView = targetView_p;
    _relativeLocation = newLocation_p;
    _mustRefresh = mustRefresh_p;
    _refactorHelper = new ViewRefactorHelper();
    _pinHelper = new PinHelper();
  }
  
  /**
   * Restricted constructor for refresh only
   * @param targetView_p The encompassing view (e.g. in which paste occurred)
   */
  public GraphicalAdjustmentCommand(View targetView_p) {
    _originsMapping = Collections.emptyMap();
    _pastedSiriusElements = Collections.emptyList();
    _targetView = targetView_p;
    _relativeLocation = null;
    _mustRefresh = true;
    _refactorHelper = new ViewRefactorHelper();
    _pinHelper = new PinHelper();
  }
  
  
  // METHODS
  
  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    Collection<Node> gmfRoots;
    if (_pastedSiriusElements != null && !_pastedSiriusElements.isEmpty()) {
      // Nodes
      Collection<DSemanticDecorator> siriusRoots =
        MiscUtil.getRoots(_pastedSiriusElements);
      for (DSemanticDecorator target : siriusRoots) {
        EObject origin = _originsMapping.get(target);
        if (origin instanceof DSemanticDecorator) {
          View originView = LayerUtil.getUpGmfElement((DSemanticDecorator)origin);
          View targetView = LayerUtil.getUpGmfElement(target);
          if (originView instanceof Node && targetView instanceof Node)
            adaptLayoutRec((Node)originView, (Node)targetView);
        }
      }
      // Move top-left corner to mouse location, if specified
      gmfRoots = MiscUtil.filter(LayerUtil.upToGmf(siriusRoots), Node.class);
      if (_relativeLocation != null && !(_targetView instanceof Edge))
        moveAll(gmfRoots, _targetView, _relativeLocation);
      // Edges
      adaptEdgesLayout();
    } else {
      gmfRoots = Collections.emptyList();
    }
    // Refresh diagram in order to draw edges
    if (_mustRefresh)
      refresh(gmfRoots);
  }
  
  /**
   * Refresh the necessary elements in order to obtain a ready-to-visualize result
   */
  protected void refresh(Collection<? extends Node> roots_p) {
    // Refresh diagram to draw edges
    DDiagram diagram = null;
    if (!_pastedSiriusElements.isEmpty()) {
      diagram = SiriusUtil.getOwningDiagram(_pastedSiriusElements.get(0));
    } else if (_targetView != null) {
      Diagram gmfDiag = _targetView.getDiagram();
      EObject diagElement = gmfDiag.getElement();
      if (diagElement instanceof DDiagram)
        diagram = (DDiagram)diagElement;
    }
    if (diagram != null) diagram.refresh();
  }
  
  /**
   * Adapt the layout and style of the target Edge so that it looks the same as
   * the origin Edge.
   * @param origin_p a non-null GMF edge
   * @param target_p a non-null GMF edge
   */
  protected void adaptEdgeLayout(Edge origin_p, Edge target_p) {
    _refactorHelper.copyViewAppearance(origin_p, target_p, Collections.emptyList());
    Bendpoints originBendpoints = origin_p.getBendpoints();
    if (originBendpoints != null) {
      Bendpoints bendpointsCopy = EcoreUtil.copy(originBendpoints);
      target_p.setBendpoints(bendpointsCopy);
      pin(target_p.getElement());
    }
  }
  
  /**
   * Adapt the layout and style of all target edges.
   */
  @SuppressWarnings("unchecked")
  protected void adaptEdgesLayout() {
    Diagram gmfDiagram = null;
    if (_targetView != null)
      gmfDiagram = _targetView.getDiagram();
    if (gmfDiagram != null) {
      // We have to look for target edges because they cannot be all retrieved
      // via the cross-referencer yet (edges between bordered nodes on non-root nodes)
      for (Edge edge : (List<Edge>)gmfDiagram.getEdges()) {
        EObject element = edge.getElement();
        if (_pastedSiriusElements.contains(element)) {
          // The edge is a target one
          DSemanticDecorator originSiriusElement = _originsMapping.get(element);
          EObject gmfCounterpart = LayerUtil.getUpGmfElement(originSiriusElement);
          if (gmfCounterpart instanceof Edge)
            adaptEdgeLayout((Edge)gmfCounterpart, edge);
        }
      }
    }
  }
  
  /**
   * Adapt the layout of the target Node so that it looks the same as
   * the origin Node, and recursively for their children nodes.
   * We cannot use a mapping from newly created Nodes to origin Nodes
   * because Nodes are managed by Sirius, so we use the mapping between
   * Sirius elements and navigate up and down the Sirius/GMF layers.
   */
  protected void adaptLayoutRec(Node origin_p, Node target_p) {
    adaptNodeLayout(origin_p, target_p);
    for (Object targetSubObject : target_p.getChildren()) {
      if (targetSubObject instanceof View) {
        View targetSubView = (View)targetSubObject;
        if (targetSubView.getElement() instanceof DSemanticDecorator) {
          DSemanticDecorator targetSirius =
            (DSemanticDecorator)targetSubView.getElement();
          DSemanticDecorator originSirius = _originsMapping.get(targetSirius);
          View originSubView = LayerUtil.getUpGmfElement(originSirius);
          if (targetSubView instanceof Node && originSubView instanceof Node)
            adaptLayoutRec((Node)originSubView, (Node)targetSubView);
        }
      }
    }
  }
  
  /**
   * Adapt the layout and style of the target Node so that it looks the same as
   * the origin node
   */
  protected void adaptNodeLayout(Node origin_p, Node target_p) {
    LayoutConstraint originLayout = origin_p.getLayoutConstraint();
    LayoutConstraint targetLayout = target_p.getLayoutConstraint();
    if (originLayout instanceof Size && targetLayout instanceof Size) {
      Size originSize = (Size)originLayout;
      Size targetSize = (Size)targetLayout;
      targetSize.setHeight(originSize.getHeight());
      targetSize.setWidth(originSize.getWidth());
    }
    if (originLayout instanceof Location && targetLayout instanceof Location) {
      Location originLoc = (Location)originLayout;
      Location targetLoc = (Location)targetLayout;
      targetLoc.setX(originLoc.getX());
      targetLoc.setY(originLoc.getY());
    }
    if (!origin_p.getStyles().isEmpty() && !target_p.getStyles().isEmpty()) {
      Style originStyle = (Style)origin_p.getStyles().get(0);
      Style targetStyle = (Style)target_p.getStyles().get(0);
      if (originStyle instanceof FontStyle && targetStyle instanceof FontStyle)
        ((FontStyle)(targetStyle)).setFontName(((FontStyle)originStyle).getFontName());
    }
    checkRefresh(target_p);
  }
  
  /**
   * Ensure that a given node is properly refreshed
   */
  protected static void checkRefresh(Node node_p) {
    if (node_p.getElement() instanceof DNode) {
      DNode siriusNode = (DNode)node_p.getElement();
      // BorderedNodes may have a non-refreshed label
      if (SiriusUtil.isBorderedNode(siriusNode))
        siriusNode.refresh();
    }
  }
  
  /**
   * Move all given Sirius elements to the given location relative to the
   * given View
   */
  protected void moveAll(Collection<? extends Node> roots_p,
      View targetView_p, Point relativeLocation_p) {
    if (roots_p.isEmpty()) return;
    // Compute new location in absolute (diagram-relative) coordinates
    Point absoluteNewLocation;
    if (targetView_p instanceof Diagram)
      absoluteNewLocation = relativeLocation_p;
    else {
      Point targetViewLocation = GmfUtil.getAbsoluteLocation((Node)targetView_p);
      absoluteNewLocation = new Point(targetViewLocation.x + relativeLocation_p.x,
          targetViewLocation.y + relativeLocation_p.y);
    }
    // Compute current location in absolute (diagram-relative) coordinates
    Point absoluteCurrentLocation = GmfUtil.getAbsoluteTopLeftCorner(roots_p);
    // Compute translation vector from current to new
    Point vector = new Point(absoluteNewLocation.x - absoluteCurrentLocation.x,
        absoluteNewLocation.y - absoluteCurrentLocation.y);
    // Apply vector on all root nodes
    for (Node root : roots_p)
      move(root, vector);
  }
  
  /**
   * Move a Node according to the given geometric vector
   */
  protected void move(Node node_p, Point vector_p) {
    LayoutConstraint layout = node_p.getLayoutConstraint();
    if (layout instanceof Location) {
      Location location = (Location)layout;
      location.setX(location.getX() + vector_p.x);
      location.setY(location.getY() + vector_p.y);
      pin(node_p.getElement());
    }
  }
  
  /**
   * Pin the given element if applicable, i.e., if the given
   * element is a Sirius diagram element
   * @param object_p an arbitrary object
   */
  protected void pin(Object object_p) {
    if (object_p instanceof DDiagramElement)
      _pinHelper.markAsPinned((DDiagramElement)object_p);
  }
  
  @Override
  public String getName() {
    return Messages.GraphicalAdjustmentCommand_Name;
  }

}
