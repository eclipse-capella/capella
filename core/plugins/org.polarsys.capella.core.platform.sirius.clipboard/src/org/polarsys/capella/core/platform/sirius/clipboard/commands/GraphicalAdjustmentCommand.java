/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Point;
import org.polarsys.capella.core.platform.sirius.clipboard.Messages;
import org.polarsys.capella.core.platform.sirius.clipboard.util.CapellaDiagramClipboard;
import org.polarsys.capella.core.platform.sirius.clipboard.util.GmfUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.MiscUtil;
import org.polarsys.capella.core.platform.sirius.clipboard.util.SiriusUtil;

/**
 * This command adjusts the layout of Sirius/GMF elements so that they look
 * like the original elements modulo a specified location.
 */
public class GraphicalAdjustmentCommand extends AbstractResultCommand {

  // The Sirius elements whose layout has to be adjusted
  private List<DSemanticDecorator> pastedSiriusElements;
  
  // A map allowing to retrieve, from a Sirius element E1, the Sirius element
  // whose layout must be replicated on E1
  private Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> originsMapping;
  
  // The view in which paste occurred
  private View targetView;
  
  // The expected location of the top-left corner of the pasted elements,
  // relative to the graphical container
  private Point relativeLocation;
    
  // A non-null helper for pinning graphical elements
  private final PinHelper pinHelper;
  
  // Whether refresh must be performed at the end (e.g. to draw arcs)
  private boolean mustRefresh;

  private final boolean applyLayout;
  
  private final boolean applyStyle;
  
  /**
   * Full constructor
   * @param targets The Sirius elements whose layout has to be adjusted
   * @param origins A map allowing to retrieve, from a Sirius element E1,
   *          the Sirius element whose layout must be replicated on E1
   * @param targetView The encompassing view (e.g. in which paste occurred)
   * @param newLocation The expected location of the top-left corner of the
   *          pasted elements, relative to the graphical container
   * @param mustRefresh Whether refresh must be performed at the end
   *          (e.g. to draw arcs)
   */
  public GraphicalAdjustmentCommand(List<? extends EObject> targets,
      Map<? extends DSemanticDecorator, ? extends DSemanticDecorator> origins,
      View targetView, Point newLocation, boolean mustRefresh, boolean applyLayout, boolean applyStyle) {
    assert origins != null;
    assert targets != null;
    this.originsMapping = origins;
    this. pastedSiriusElements = MiscUtil.filter(targets, DSemanticDecorator.class);
    this.targetView = targetView;
    this.relativeLocation = newLocation;
    this.mustRefresh = mustRefresh;
    this.pinHelper = new PinHelper();
    this.applyLayout = applyLayout;
    this.applyStyle = applyStyle;
  }
  
  /**
   * Restricted constructor for refresh only
   * @param targetView The encompassing view (e.g. in which paste occurred)
   */
  public GraphicalAdjustmentCommand(View targetView) {
    this.originsMapping = Collections.emptyMap();
    this. pastedSiriusElements = Collections.emptyList();
    this.targetView = targetView;
    this.relativeLocation = null;
    this.mustRefresh = true;
    this.pinHelper = new PinHelper();
    this.applyLayout = false;
    this.applyStyle = false;
  }
  
  
  // METHODS
  
  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    Collection<Node> gmfRoots;
    if (pastedSiriusElements != null && !pastedSiriusElements.isEmpty()) {
      // Nodes
      Collection<DSemanticDecorator> siriusRoots =
        MiscUtil.getRoots(pastedSiriusElements);
      
      
      for (DSemanticDecorator target : siriusRoots) {
        IGraphicalEditPart pastedGraphicalEditPart = LayerUtil.getGraphicalPart(target);
        if (pastedGraphicalEditPart != null) {
          CapellaDiagramClipboard.getInstance().applyFormat(originsMapping, pastedGraphicalEditPart, applyLayout, applyStyle);
        }
      }
      // Move top-left corner to mouse location, if specified
      gmfRoots = MiscUtil.filter(LayerUtil.upToGmf(siriusRoots), Node.class);
      if (relativeLocation != null && !(targetView instanceof Edge))
        moveAll(gmfRoots, targetView, relativeLocation);
    } else {
      gmfRoots = Collections.emptyList();
    }
    // Refresh diagram in order to draw edges
    if (mustRefresh)
      refresh(gmfRoots);
  }
  
  /**
   * Refresh the necessary elements in order to obtain a ready-to-visualize result
   */
  protected void refresh(Collection<? extends Node> roots) {
    // Refresh diagram to draw edges
    DDiagram diagram = null;
    if (!pastedSiriusElements.isEmpty()) {
      diagram = SiriusUtil.getOwningDiagram(pastedSiriusElements.get(0));
    } else if (targetView != null) {
      Diagram gmfDiag = targetView.getDiagram();
      EObject diagElement = gmfDiag.getElement();
      if (diagElement instanceof DDiagram)
        diagram = (DDiagram)diagElement;
    }
    if (diagram != null) {
      DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
    }
  }
 
  /**
   * Move all given Sirius elements to the given location relative to the
   * given View
   */
  protected void moveAll(Collection<? extends Node> roots,
      View targetView, Point relativeLocation) {
    if (roots.isEmpty()) return;
    // Compute new location in absolute (diagram-relative) coordinates
    Point absoluteNewLocation;
    if (targetView instanceof Diagram)
      absoluteNewLocation = relativeLocation;
    else {
      Point targetViewLocation = GmfUtil.getAbsoluteLocation((Node)targetView);
      absoluteNewLocation = new Point(targetViewLocation.x + relativeLocation.x,
          targetViewLocation.y + relativeLocation.y);
    }
    // Compute current location in absolute (diagram-relative) coordinates
    Point absoluteCurrentLocation = GmfUtil.getAbsoluteTopLeftCorner(roots);
    // Compute translation vector from current to new
    Point vector = new Point(absoluteNewLocation.x - absoluteCurrentLocation.x,
        absoluteNewLocation.y - absoluteCurrentLocation.y);
    // Apply vector on all root nodes
    for (Node root : roots)
      move(root, vector);
  }
  
  /**
   * Move a Node according to the given geometric vector
   */
  protected void move(Node node, Point vector) {
    LayoutConstraint layout = node.getLayoutConstraint();
    if (layout instanceof Location) {
      Location location = (Location)layout;
      location.setX(location.getX() + vector.x);
      location.setY(location.getY() + vector.y);
      pin(node.getElement());
    }
  }
  
  /**
   * Pin the given element if applicable, i.e., if the given
   * element is a Sirius diagram element
   * @param object an arbitrary object
   */
  protected void pin(Object object) {
    if (object instanceof DDiagramElement)
      pinHelper.markAsPinned((DDiagramElement)object);
  }
  
  @Override
  public String getName() {
    return Messages.GraphicalAdjustmentCommand_Name;
  }

}
