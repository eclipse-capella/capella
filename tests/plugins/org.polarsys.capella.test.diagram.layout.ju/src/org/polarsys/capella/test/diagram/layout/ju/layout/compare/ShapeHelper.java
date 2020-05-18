/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.ui.IEditorPart;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.sirius.analysis.ShapeUtil;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.layout.ju.layout.LayoutFactory;
import org.polarsys.capella.test.diagram.layout.ju.layout.Location;
import org.polarsys.capella.test.diagram.layout.ju.layout.Size;

/**
 * Utility tools to use Draw2D like in retrieving the coordinates of diagram nodes
 */
public class ShapeHelper {
  /*********************************************************************************************************
   * Utilities to calculate position from the closest diagram element (DEdge or DNode) from the origin (0,0)
   *********************************************************************************************************/

  /**
   * Get the diagram origin point <br>
   * this point will be used to calculate draw2d node in absolute coordinates
   * 
   * @param diagram_p
   * @return
   */
  private static Point getDiagramOrigin(Diagram diagram_p) {

    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if ((diagram_p != null) && (editor instanceof DiagramEditor)) {

      IDiagramGraphicalViewer diagramGraphicalViewer = ((DiagramEditor) editor).getDiagramGraphicalViewer();
      final Map<?, ?> editPartRegistry = diagramGraphicalViewer.getEditPartRegistry();
      Object currentEditPart = editPartRegistry.get(diagram_p);
      IFigure figure = ((DDiagramEditPart) currentEditPart).getFigure();
      return new Point(figure.getBounds().x, figure.getBounds().y);
    }
    return null;
  }

  /**
   * Sometimes after migration, some diagrams are shifted. In this case, if we compare node location by absolute
   * coordinates, the result will be false.<br>
   * To avoid this problem, we calculate relative coordinates from a Reference Point. This point is the closest node to
   * the origin.
   * 
   * @param diagram_p
   * @return
   */
  private static Point getClosestPointToOrigin(List<Point> diagramPoints_p) {
    int pointsNumber = diagramPoints_p.size();
    switch (pointsNumber) {
    case 0:
      return null;
    case 1:
      return diagramPoints_p.get(0);
    default:
      Point result = diagramPoints_p.get(0);
      for (int i = 1; i < pointsNumber; i++) {
        if (getDistaceFromOrigin(diagramPoints_p.get(i)) < getDistaceFromOrigin(result)) {
          result = diagramPoints_p.get(i);
        }
      }
      return result;
    }
  }

  /**
   * Get the closest Point (corresponding to a diagram element) to the origin Point(0,0)
   * 
   * @param diagram_p
   * @return
   */
  public static Point getClosestPointToOriginInDiagram(DDiagram diagram_p) {
    Map<Point, DDiagramElement> map = ShapeHelper.getDiagramDraw2DPointsInMap(diagram_p);
    Set<Point> keySet = map.keySet();
    List<Point> pointsList = new ArrayList<Point>();
    for (Point key : keySet) {
      pointsList.add(key);
    }
    return ShapeHelper.getClosestPointToOrigin(pointsList);
  }

  /**
   * Get the Draw2D location points of the input diagram. <br>
   * The result is returned in a map of point and the corresponding DDiagramElement element
   * 
   * @param diagram_p
   * @return
   */
  private static Map<Point, DDiagramElement> getDiagramDraw2DPointsInMap(DDiagram diagram_p) {

    // get visible diagram elements
    List<DDiagramElement> visibleDiagramElements = DiagramHelper.getVisibleDiagramElements(diagram_p);
    Diagram diagram = DiagramHelper.getDiagram(diagram_p);
    Map<Point, DDiagramElement> map = new HashMap<Point, DDiagramElement>();
    for (DDiagramElement currentElement : visibleDiagramElements) {

      if (currentElement instanceof DEdge) {
        PointList edgePoints = getDraw2DPointListForDEdge(diagram, currentElement);
        if (edgePoints != null) {
          for (int i = 0; i < edgePoints.size(); i++) {
            Point currentPoint = edgePoints.getPoint(i);
            if (currentPoint != null) {
              map.put(currentPoint, currentElement);
            }
          }
        }
      } else {
        Point nodePoint = getDraw2DCoordinatesForDNode(diagram, currentElement);
        map.put(nodePoint, currentElement);
      }

    }
    return map;
  }

  /**
   * return the distance of a point from the origin Point (0,0)
   * 
   * @param point_p
   * @return
   */

  private static double getDistaceFromOrigin(Point point_p) {
    Point zero = new Point(0, 0);
    return point_p.getDistance(zero);
  }

  /*********************************************************************************************************
   * Methods which return a Point, a Dimension or a PointList from a diagram element
   *********************************************************************************************************/

  /**
   * return the points of a diagram element (DNode or DEdge) in a list
   * 
   * @param diagram_p
   * @param element_p
   * @return
   */
  private static List<Point> getDDiagramElementPointsList(DDiagram diagram_p, DDiagramElement element_p) {
    List<Point> result = new ArrayList<Point>();
    Diagram diagram = DiagramHelper.getDiagram(diagram_p);
    Point refPoint = getClosestPointToOriginInDiagram(diagram_p);
    if (element_p instanceof DEdge) {
      PointList edgePoints = getDraw2DPointListForDEdge(diagram, element_p);
      for (int i = 0; i < edgePoints.size(); i++) {
        Point currentPoint = edgePoints.getPoint(i);
        if (currentPoint != null) {

          Point pointToAdd = new Point(currentPoint.x - refPoint.x, currentPoint.y - refPoint.y);
          result.add(pointToAdd);
        }
      }
    } else {
      Point nodePoint = getDraw2DCoordinatesForDNode(diagram, element_p);
      Point pointToAdd = new Point(nodePoint.x - refPoint.x, nodePoint.y - refPoint.y);
      result.add(pointToAdd);
    }

    return result;
  }

  static Location createRelativeLocation(int x, int y) {
    Location a = LayoutFactory.eINSTANCE.createLocation();
    a.setRelative(true);
    a.setX(x);
    a.setY(y);
    return a;
  }

  static Location createAbsoluteLocation(int x, int y) {
    Location a = LayoutFactory.eINSTANCE.createLocation();
    a.setRelative(false);
    a.setX(x);
    a.setY(y);
    return a;
  }

  /**
   * Method to calculate Draw2D location point for a DNode, DNodeContainer, DNodeList, ...
   * 
   * @param diagram_p,
   *          the target diagram containing the element
   * @param element_p,
   *          the target element
   */
  private static Point getDraw2DCoordinatesForDNode(Diagram diagram_p, DDiagramElement element_p) {
    Point diagramOrigin = getDiagramOrigin(diagram_p);

    // if the input DDiagramElement element is a DEdge, return null. The method shouldn't be used with DEdge
    if (element_p instanceof DEdge) {
      return null;
    }
    // get the view of the input DDiagramElement element
    View currentNode = retrieveNode(diagram_p, element_p);
    // check the view is not null
    if (currentNode != null) {
      // get the Draw2D location in using ShapeUtil
      // GraphicalEditPart gep = ShapeUtil.getEditPart(currentNode);

      Point locationDRAW2D = ShapeUtil.getLocationDRAW2D((Node) currentNode);
      if (locationDRAW2D == null) {
        // element is not visible, returns a GMF relative one
        return null;
      }
      // calculate absolute coordinates
      locationDRAW2D.x = locationDRAW2D.x - diagramOrigin.x;
      locationDRAW2D.y = locationDRAW2D.y - diagramOrigin.y;

      // if the element is a Port or a InstanceRole, no need to add the container coordinates
      if ((element_p instanceof Port) || (element_p instanceof InstanceRole)) {
        return locationDRAW2D;
      }

      // get the container of the input DDiagramElement element
      EObject container = element_p.eContainer();

      // The ShapeUtil getLocationDRAW2D method calculate the location from the bounds of the editpart figure.
      // These coordinates are relative of the container.
      // Here, we calculate absolute coordinates.

      if (!(container instanceof DSemanticDiagram)) {
        Point containerPoint = getDraw2DCoordinatesForDNode(diagram_p, (DDiagramElement) container);
        locationDRAW2D.x = locationDRAW2D.x + containerPoint.x;
        locationDRAW2D.y = locationDRAW2D.y + containerPoint.y;
      }
      return locationDRAW2D;
    }
    return null;
  }

  /**
   * Method to get rectangle dimensions (width and height) of a DNode
   */
  private static Dimension getRectangleDimensionForDNode(Diagram diagram_p, DDiagramElement element_p) {
    // if the input DDiagramElement element is a DEdge, return null. The method shouldn't be used with DEdge
    if (element_p instanceof DEdge) {
      return null;
    }
    // get the view of the input DDiagramElement element
    View currentNode = retrieveNode(diagram_p, element_p);
    // check the view is not null
    if (currentNode != null) {
      // get the Draw2D location in using ShapeUtil
      GraphicalEditPart gep = ShapeUtil.getEditPart(currentNode);
      if (gep != null) {
        int width = gep.getFigure().getBounds().width;
        int height = gep.getFigure().getBounds().height;
        return new Dimension(width, height);
      }
    }
    return null;
  }

  public static PointList getRelativePointListForDEdge(Diagram diagram_p, DDiagramElement element_p) {

    // if the input DDiagramElement element is not a DEdge, return null.
    if (!(element_p instanceof DEdge)) {
      return null;
    }
    // get the view for the input DDiagramElement element
    View currentNode = ShapeUtil.retrieveNode(diagram_p, element_p);
    // get the editor
    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if ((currentNode != null) && (editor instanceof DiagramEditor)) {
      PointList result = new PointList();

      // In case where edge is hidden, we retrieve default position, not absolute
      if (((Edge) currentNode).getSource() instanceof Node) {
        Node source = (Node) ((Edge) currentNode).getSource();
        result.addPoint(((org.eclipse.gmf.runtime.notation.Bounds) source.getLayoutConstraint()).getX(),
            ((org.eclipse.gmf.runtime.notation.Bounds) source.getLayoutConstraint()).getY());
      }

      if (((Edge) currentNode).getTarget() instanceof Node) {
        Node target = (Node) ((Edge) currentNode).getTarget();
        result.addPoint(((org.eclipse.gmf.runtime.notation.Bounds) target.getLayoutConstraint()).getX(),
            ((org.eclipse.gmf.runtime.notation.Bounds) target.getLayoutConstraint()).getY());
      }

      RelativeBendpoints d = (RelativeBendpoints) ((Edge) currentNode).getBendpoints();
      for (Object p : d.getPoints()) {
        RelativeBendpoint r = (RelativeBendpoint) p;
        result.addPoint(r.getSourceX(), r.getSourceY());
        result.addPoint(r.getTargetX(), r.getTargetY());
      }
      return result;

    }
    return null;
  }

  /**
   * Method to calculate Draw2D location points for a DEDge
   * 
   * @param diagram_p,
   * @param element_p
   */
  private static PointList getDraw2DPointListForDEdge(Diagram diagram_p, DDiagramElement element_p) {
    Point diagramOrigin = getDiagramOrigin(diagram_p);

    // if the input DDiagramElement element is not a DEdge, return null.
    if (!(element_p instanceof DEdge)) {
      return null;
    }
    // get the view for the input DDiagramElement element
    View currentNode = ShapeUtil.retrieveNode(diagram_p, element_p);
    // get the editor
    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if ((currentNode != null) && (editor instanceof DiagramEditor)) {
      final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
      // get the editpart corresponding to the view
      Object currentEditPart = editPartRegistry.get(currentNode);

      // the type of editpart for edge is DEdgeEditPart
      if (currentEditPart instanceof ConnectionNodeEditPart) {
        ConnectionNodeEditPart edgeNode = (ConnectionNodeEditPart) currentEditPart;
        // get the figure of the editpart
        IFigure figure = edgeNode.getFigure();
        ViewEdgeFigure edgeFigure = (ViewEdgeFigure) figure;
        PointList result = new PointList();
        // get the list of points of the figure
        PointList points = edgeFigure.getPoints();
        // calculate absolute coordinates
        for (int i = 0; i < points.size(); i++) {
          Point current = points.getPoint(i);
          Point newPoint = new Point(current.x - diagramOrigin.x, current.y - diagramOrigin.y);
          result.addPoint(newPoint);
        }

        EObject container = element_p.eContainer();
        if (!(container instanceof DSemanticDiagram)) {
          Point containerPoint = getDraw2DCoordinatesForDNode(diagram_p, (DDiagramElement) container);
          for (int i = 0; i < points.size(); i++) {
            Point current = points.getPoint(i);
            Point newPoint = new Point(current.x + containerPoint.x, current.y + containerPoint.y);
            result.addPoint(newPoint);
          }
        }
        return result;
      }

    }
    return null;
  }

  /*********************************************************************************************************
   * Utilities for note and note attachment
   *********************************************************************************************************/

  /**
   * Calculate the draw2d coordinates for a note
   * 
   * @param diagram_p
   * @param note_p
   */
  private static Point getDraw2DCoordinatesForNote(DDiagram diagram_p, ShapeStyle note_p) {
    Point refPoint = getClosestPointToOriginInDiagram(diagram_p);
    Diagram diag = DiagramHelper.getDiagram(diagram_p);
    Point diagramOrigin = getDiagramOrigin(diag);
    View currentNode = null;
    if (note_p instanceof Shape) {
      currentNode = (View) note_p;
    } else {
      // get the view of the input DDiagramElement element
      currentNode = (View) note_p.eContainer();
    }
    // check the view is not null
    if (currentNode != null) {
      // get the Draw2D location in using ShapeUtil
      Point locationDRAW2D = ShapeUtil.getLocationDRAW2D((Node) currentNode);
      // calculate absolute coordinates
      locationDRAW2D.x = locationDRAW2D.x - diagramOrigin.x - refPoint.x;
      locationDRAW2D.y = locationDRAW2D.y - diagramOrigin.y - refPoint.y;

      return locationDRAW2D;
    }
    return null;
  }

  /**
   * Method to get rectangle dimensions (width and height) of a Note
   * 
   * @param note_p
   */
  private static Dimension getRectangleDimensionForNote(ShapeStyle note_p) {
    View currentNode = null;
    if (note_p instanceof Shape) {
      currentNode = (View) note_p;
    } else {
      // get the view of the input DDiagramElement element
      currentNode = (View) note_p.eContainer();
    }
    // check the view is not null
    if (currentNode != null) {
      // get the Draw2D location in using ShapeUtil
      GraphicalEditPart gep = ShapeUtil.getEditPart(currentNode);
      int width = gep.getFigure().getBounds().width;
      int height = gep.getFigure().getBounds().height;
      return new Dimension(width, height);
    }
    return null;
  }

  /**
   * calculates the bendpoints of a NoteAttachment <br>
   * the process is different from a DEdge because the type of editpart is different
   * 
   * @param diagram_p
   * @param edge_p
   * @return
   */

  private static PointList getDraw2DPointListForNoteAttachment(DDiagram diagram_p, Edge edge_p) {
    Point refPoint = getClosestPointToOriginInDiagram(diagram_p);
    Diagram diag = DiagramHelper.getDiagram(diagram_p);
    Point diagramOrigin = getDiagramOrigin(diag);
    final IEditorPart editor = EclipseUIUtil.getActiveEditor();
    if ((edge_p != null) && (editor instanceof DiagramEditor)) {
      final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
      // get the editpart corresponding to the view
      Object currentEditPart = editPartRegistry.get(edge_p);
      // the type of editpart for edge is DEdgeEditPart
      if (currentEditPart instanceof NoteAttachmentEditPart) {
        NoteAttachmentEditPart edgeNode = (NoteAttachmentEditPart) currentEditPart;
        // get the figure of the editpart
        PolylineConnectionEx figure = (PolylineConnectionEx) edgeNode.getFigure();

        // get the list of points of the figure
        PointList points = figure.getPoints();
        PointList result = new PointList();
        // calculate absolute coordinates
        for (int i = 0; i < points.size(); i++) {
          Point current = points.getPoint(i);
          Point newPoint = new Point(current.x - diagramOrigin.x - refPoint.x,
              current.y - diagramOrigin.y - refPoint.y);
          result.addPoint(newPoint);

        }

        EObject container = edge_p.eContainer();
        if (!(container instanceof Diagram)) {
          Point containerPoint = getDraw2DCoordinatesForNode(diagram_p, (Node) container);
          for (int i = 0; i < points.size(); i++) {
            Point current = points.getPoint(i);
            Point newPoint = new Point(current.x + containerPoint.x, current.y + containerPoint.y);
            result.addPoint(newPoint);
          }
        }
        return result;
      }
    }
    return null;

  }

  /**
   * Method to calculate Draw2D location point for a Node <br>
   * 
   * @param diagram_p
   * @param node_p
   */
  private static Point getDraw2DCoordinatesForNode(DDiagram diagram_p, Node node_p) {
    Point refPoint = getClosestPointToOriginInDiagram(diagram_p);
    Diagram diag = DiagramHelper.getDiagram(diagram_p);
    Point diagramOrigin = getDiagramOrigin(diag);

    // check the view is not null
    if (node_p != null) {
      // get the Draw2D location in using ShapeUtil
      // GraphicalEditPart gep = ShapeUtil.getEditPart(currentNode);

      Point locationDRAW2D = ShapeUtil.getLocationDRAW2D(node_p);
      // calculate absolute coordinates
      locationDRAW2D.x = locationDRAW2D.x - diagramOrigin.x - refPoint.x;
      locationDRAW2D.y = locationDRAW2D.y - diagramOrigin.y - refPoint.y;

      // get the container of the input DDiagramElement element
      EObject container = node_p.eContainer();

      // The ShapeUtil getLocationDRAW2D method calculate the location from the bounds of the editpart figure.
      // These coordinates are relative of the container.
      // Here, we calculate absolute coordinates.

      if (!(container instanceof Diagram)) {
        Point containerPoint = getDraw2DCoordinatesForNode(diagram_p, (Node) container);
        locationDRAW2D.x = locationDRAW2D.x + containerPoint.x;
        locationDRAW2D.y = locationDRAW2D.y + containerPoint.y;
      }
      return locationDRAW2D;
    }
    return null;
  }

  /*********************************************************************************************************
   * Specific case of some methods of sirius.analysis.ShapeUtil
   *********************************************************************************************************/
  /**
   * add cases for DNodeList and DNodeListElement
   */
  private static View retrieveNode(Diagram diagram, DDiagramElement node) {
    View ret = null;

    if (node instanceof DEdge) {
      for (Object oIte : diagram.getPersistedEdges()) {
        if (oIte instanceof Edge) {
          Edge edgeIte = (Edge) oIte;
          if (edgeIte.getElement() == node) {
            ret = edgeIte;
            break;
          }
        }
      }
    } else if ((node instanceof DNodeContainer) || (node instanceof DNode) || (node instanceof DNodeList)
        || (node instanceof DNodeListElement)) {
      ret = retrieveNodeRec(diagram, node);
    }

    return ret;
  }

  /**
   * Test the text alignment of the input note
   * 
   * @param note,
   *          the note to test
   * @param expectedAlignment,
   *          the expected text alignment
   */
  @SuppressWarnings("rawtypes")
  public static String getNoteTextAlignment(ShapeStyle note) {

    TextAlignment actualAlignment = null;
    View v = null;
    if (note instanceof View) {
      v = (View) note;
    } else if (note.eContainer() instanceof View) {
      v = (View) note.eContainer();
    }
    if (v != null) {
      EList styles = v.getStyles();
      Iterator iterator = styles.iterator();
      while (iterator.hasNext()) {
        Object next = iterator.next();
        if (next instanceof TextStyle) {
          actualAlignment = ((TextStyle) next).getTextAlignment();
          return actualAlignment.getLiteral();
        }
      }
    }
    return null;
  }

  /**
   * then retrieveNodeRec uses the retrieveNode method from ShapeHelper
   */
  public static Node retrieveNodeRec(View anchor, DDiagramElement rootNode) {
    Node ret = null;

    for (Object oIte : anchor.getPersistedChildren()) {
      if (oIte instanceof Node) {
        Node nodeIte = (Node) oIte;
        if (nodeIte.getElement() == rootNode) {
          ret = nodeIte;
          break;
        }
        Node node = retrieveNodeRec(nodeIte, rootNode);
        if (node != null) {
          ret = node;
          break;
        }
      }
    }
    return ret;
  }

  public static Location adapt(Point currentSize) {
    if (currentSize == null) {
      return null;
    }
    Location b = LayoutFactory.eINSTANCE.createLocation();
    b.setX(currentSize.x);
    b.setY(currentSize.y);
    return b;
  }

  public static Size adapt(Dimension currentSize) {
    if (currentSize == null) {
      return null;
    }
    Size b = LayoutFactory.eINSTANCE.createSize();
    b.setHeight(currentSize.height);
    b.setWidth(currentSize.width);
    return b;
  }

  public static Collection<Location> getDEdgePoints(Diagram diagram, DEdge anElement, Point ref) {
    Collection<Location> result = new ArrayList<Location>();
    PointList edgePoints = ShapeHelper.getDraw2DPointListForDEdge(diagram, anElement);
    boolean isAbsolute = true;
    if (edgePoints == null) {
      edgePoints = ShapeHelper.getRelativePointListForDEdge(diagram, anElement);
      isAbsolute = false;
    }
    if (edgePoints != null) {
      for (int i = 0; i < edgePoints.size(); i++) {
        Point currentPoint = edgePoints.getPoint(i);
        if (currentPoint != null) {
          Location location = adapt(currentPoint);
          if (isAbsolute) {
            location = remove(location, ref);
          }
          location.setRelative(!isAbsolute);
          result.add(location);
        }
      }
    }
    return result;
  }

  private static Location remove(Location adapt, Point ref) {

    Location b = LayoutFactory.eINSTANCE.createLocation();
    b.setX(adapt.getX() - ref.x);
    b.setY(adapt.getY() - ref.y);
    b.setRelative(adapt.isRelative());
    return b;

  }

  public static Collection<Location> getDEdgePointsForNoteAttachment(DDiagram diagram, Edge currentEdge, Point ref) {
    Collection<Location> result = new ArrayList<Location>();
    PointList edgePoints = ShapeHelper.getDraw2DPointListForNoteAttachment(diagram, currentEdge);
    if (edgePoints != null) {
      for (int i = 0; i < edgePoints.size(); i++) {
        Point currentPoint = edgePoints.getPoint(i);
        if (currentPoint != null) {
          result.add(remove(adapt(currentPoint), ref));
        }
      }
    }
    return result;
  }

  public static Location getDNodeLocation(Diagram diagram_p, DDiagramElement element_p, Point ref) {
    Point p = getDraw2DCoordinatesForDNode(diagram_p, element_p);
    if (p != null) {
      return remove(createAbsoluteLocation(p.x, p.y), ref);
    }

    View currentNode = retrieveNode(diagram_p, element_p);
    if (currentNode != null) {
      // element is not visible, returns a GMF relative one
      p = new Point(((org.eclipse.gmf.runtime.notation.Location) (((Node) currentNode).getLayoutConstraint())).getX(),
          ((org.eclipse.gmf.runtime.notation.Location) (((Node) currentNode).getLayoutConstraint())).getY());
      return remove(createRelativeLocation(p.x, p.y), ref);
    }
    return null;
  }

  public static Location getDNoteLocation(DDiagram diagram, ShapeStyle currentNote, Point ref) {
    return remove(adapt(ShapeHelper.getDraw2DCoordinatesForNote(diagram, currentNote)), ref);
  }

  public static Size getDNodeSize(Diagram diagram, AbstractDNode anElement) {
    return adapt(ShapeHelper.getRectangleDimensionForDNode(diagram, anElement));
  }

  public static Size getDNoteSize(DDiagram diagram, ShapeStyle currentNote) {
    return adapt(ShapeHelper.getRectangleDimensionForNote(currentNote));
  }

}
