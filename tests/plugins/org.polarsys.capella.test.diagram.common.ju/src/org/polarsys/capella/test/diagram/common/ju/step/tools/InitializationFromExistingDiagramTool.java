/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.step.tools;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.base.Option;
import org.junit.Assert;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

public class InitializationFromExistingDiagramTool extends InsertRemoveTool {

  private DiagramContext existingDiagramContext;

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

  /**
   * @see org.polarsys.capella.test.common.AbstractExtendedTest#postTestRun()
   */
  @Override
  protected void postRunTest() {
    DiagramHelper.refreshDiagram(getDiagramContext().getDiagram());

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
        // It's possible to tolerate some pixel
        Assert.assertEquals(existingLocation.x, initializedLocation.x);
        Assert.assertEquals(existingLocation.y, initializedLocation.y);
      }
    }

    // Fails here mean that the initialized diagram do not have any view from the existing diagram, so we should use
    // another diagram.
    Assert.assertTrue(atLeastOneCheck);
  }

  Predicate<Node> nodePredicate = new Predicate<Node>() {
    public boolean apply(Node input) {
      return input.eContainer() != null && input.eContainer() instanceof Diagram && input.getElement() != null;
    };
  };

  private Map<String, Point> getNameToLocationMap(DDiagram dDiagram) {
    Map<String, Point> locationMap = new HashMap<String, Point>();
    DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
    Option<Diagram> option = query.getAssociatedGMFDiagram();
    if (option.some()) {
      Diagram diagram = option.get();
      UnmodifiableIterator<Node> nodesIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class),
          nodePredicate);
      while (nodesIterator.hasNext()) {
        Node node = nodesIterator.next();
        // Get the position for OA
        Point location = GMFHelper.getLocation(node);
        locationMap.put(node.getElement().toString(), location);
      }
    }
    return locationMap;
  }
}
