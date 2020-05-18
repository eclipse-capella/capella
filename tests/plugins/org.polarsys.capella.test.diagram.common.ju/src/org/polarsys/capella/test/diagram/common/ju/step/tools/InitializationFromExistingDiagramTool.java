/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.junit.Assert;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;

import com.google.common.base.Predicate;

public class InitializationFromExistingDiagramTool extends InsertRemoveTool {

  private DiagramContext existingDiagramContext;

  private int tolerance = 0;
  
  public InitializationFromExistingDiagramTool(DiagramContext context, String toolName, String containerId,
      DiagramContext existingDiagramContext) {
    super(context, toolName, containerId);
    this.existingDiagramContext = existingDiagramContext;
  }

  public InitializationFromExistingDiagramTool(DiagramContext context, String toolName,
      DiagramContext existingDiagramContext) {
    super(context, toolName);
    this.existingDiagramContext = existingDiagramContext;
  }

  public InitializationFromExistingDiagramTool(DiagramContext context, String[] toolIdentifier, String containerId,
      DiagramContext existingDiagramContext) {
    super(context, toolIdentifier, containerId);
    this.existingDiagramContext = existingDiagramContext;
  }

  public InitializationFromExistingDiagramTool(DiagramContext context, String[] toolIdentifier,
      DiagramContext existingDiagramContext) {
    super(context, toolIdentifier);
    this.existingDiagramContext = existingDiagramContext;
  }
  
  public void setTolerance(int value) {
    tolerance = value;
  }

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    DiagramHelper.refreshDiagram(getDiagramContext().getDiagram());
    GuiActions.flushASyncGuiThread();

    // Get locations for views in existing diagram
    Map<String, Point> existingLocationMap = getNameToLocationMap(existingDiagramContext.getDiagram());
    Map<String, Point> initializedDiagramLocationMap = getNameToLocationMap(getDiagramContext().getDiagram());

    boolean atLeastOneCheck = false;
    // Compare the maps and ensure that the location is the same for views in the initialized diagram
    for (String key : initializedDiagramLocationMap.keySet()) {
      // Try to get the location for this key from the existing location map
      Point existingLocation = existingLocationMap.get(key);
      if (existingLocation != null) {
        atLeastOneCheck = true;
        Point initializedLocation = initializedDiagramLocationMap.get(key);

        // We tolerate some pixel here
        if ((Math.abs(existingLocation.y - initializedLocation.y) > tolerance)
            || (Math.abs(existingLocation.x - initializedLocation.x) > tolerance)) {
          Assert.assertFalse(true);
        }
      }
    }

    // Fails here mean that the initialized diagram do not have any view from the existing diagram, so we should use
    // another diagram.
    Assert.assertTrue(atLeastOneCheck);
  }

  Predicate<Node> nodePredicate = new Predicate<Node>() {
    public boolean apply(Node input) {
      return input.eContainer() != null && input.getElement() != null;
    };
  };

  private Map<String, Point> getNameToLocationMap(DDiagram dDiagram) {
    Map<String, Point> locationMap = new HashMap<String, Point>();

    Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) dDiagram).getTarget());
    for (DDiagramElement element : dDiagram.getDiagramElements()) {
      if (element instanceof AbstractDNode) {
        Node node = SiriusGMFHelper.getGmfNode(element, session);
        Point location = GMFHelper.getAbsoluteLocation(node);
        String name = element.getName();
        if (DiagramServices.getDiagramServices().isABorderedNode((AbstractDNode)element)) {
          name+=((AbstractDNode)element.eContainer()).getName();
        }
        locationMap.put(name, location);
      }
    }
    return locationMap;
  }
}
