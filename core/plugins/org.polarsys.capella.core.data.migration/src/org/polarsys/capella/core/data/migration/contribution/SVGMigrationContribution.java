/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contribution;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.viewpoint.Style;
import org.osgi.framework.Version;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public class SVGMigrationContribution extends AbstractMigrationContribution {
  /*
   * Map containing the workspace paths of the images replaced and what they're replaced with.
   */
  public static final Map<String, String> CHANGED_IMAGES;

  /*
   * Map containing the elements whose sizes need to be changed, and their respective proper values within each diagram.
   */
  public static final Map<String, Map<String, Dimension>> DIFFERENT_IMAGE_SIZE_PER_DIAGRAM;

  static {
    Map<String, String> changedImagesTemp = new HashMap<>();
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/SystemActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/SystemActor.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Duplicate.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionDuplicateDiagram.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Gather.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionGatherDiagram.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Route.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionRouteDiagram.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Select.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSelectDiagram.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Split.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSplitDiagram.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/initialState.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/initialState.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/finalState.png.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/finalState.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/Actor.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/Actor.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalActor.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponent.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponent.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponentHuman.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponentHuman.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalActor.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentNode.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentNode.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanNode.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanNode.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentBehavior.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentBehavior.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponent.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponent.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanUnset.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanUnset.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/eol.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/eol.svg");
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.svg");
    CHANGED_IMAGES = Collections.unmodifiableMap(changedImagesTemp);

    Map<String, Map<String, Dimension>> differentSizePerDiagram = new HashMap<>();
    Map<String, Dimension> differentSizeTemp = new HashMap<>();
    differentSizeTemp.put("Data Flow Blank", new Dimension(56, 55));
    differentSizeTemp.put("Architecture Blank", new Dimension(50, 50));
    differentSizeTemp.put("Functional Chain Description", new Dimension(50, 49));
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionDuplicateDiagram.svg",
        differentSizeTemp);
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionRouteDiagram.svg", differentSizeTemp);
    differentSizeTemp = new HashMap<>();

    differentSizeTemp.put("Data Flow Blank", new Dimension(56, 56));
    differentSizeTemp.put("Architecture Blank", new Dimension(50, 50));
    differentSizeTemp.put("Functional Chain Description", new Dimension(50, 50));
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionGatherDiagram.svg", differentSizeTemp);
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSelectDiagram.svg", differentSizeTemp);
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSplitDiagram.svg", differentSizeTemp);
    differentSizeTemp = new HashMap<>();

    DIFFERENT_IMAGE_SIZE_PER_DIAGRAM = Collections.unmodifiableMap(differentSizePerDiagram);
  }

  /*
   * If the model has a valid version, go through every diagram element, check if its one of the changed ones and if so
   * replace its image.
   */
  @Override
  public void unaryMigrationExecute(EObject currentElement, MigrationContext context) {
    super.unaryMigrationExecute(currentElement, context);

    if (isValidModelVersion(context) && currentElement instanceof AbstractDNode) {
      Style elementStyle = ((AbstractDNode) currentElement).getStyle();

      if (elementStyle instanceof WorkspaceImage) {
        WorkspaceImage image = ((WorkspaceImage) elementStyle);
        String newWorkspacePath = CHANGED_IMAGES.get(image.getWorkspacePath());

        if (newWorkspacePath != null) {
          image.setWorkspacePath(newWorkspacePath);
        }
        changeSizeOf(currentElement, image.getWorkspacePath());
      }
    }
  }

  /*
   * Changes the size of the given element.
   */
  private void changeSizeOf(EObject currentElement, String newWorkspacePath) {
    if (DIFFERENT_IMAGE_SIZE_PER_DIAGRAM.containsKey(newWorkspacePath)) {
      DDiagram diagram = getDiagramOf(currentElement);
      Dimension dimension = getNewDimensionOfElementWithinDiagram(diagram, newWorkspacePath);
      Node node = getNodeWithinDiagram(diagram, currentElement);
      if (node == null)
        return;
      Bounds bounds = (Bounds) (node).getLayoutConstraint();

      // if the size of the diagram element hasn't been modified by the user,
      // we change it manually to the previous default value
      if (bounds != null && bounds.getHeight() == -1 && bounds.getWidth() == -1 && dimension != null) {
        bounds.setWidth(dimension.width());
        bounds.setHeight(dimension.height());
      }

      // set the size of the sirius element manually to 4 so we don't trigger a resizing of the diagram elements
      if (currentElement instanceof DNode) {
        ((DNode) currentElement).setHeight(4);
        ((DNode) currentElement).setWidth(4);
      }
    }
  }

  /*
   * Retrieves the diagram node of the given element.
   */
  private Node getNodeWithinDiagram(DDiagram diagram, EObject currentElement) {
    final Iterator<EObject> it = diagram.eAllContents();
    while (it.hasNext()) {
      final EObject curr = it.next();
      if (curr instanceof Node && ((Node) curr).getElement() == currentElement)
        return (Node) curr;
    }
    return null;
  }

  /*
   * Retrieves the proper dimension of the element within the given diagram.
   */
  private Dimension getNewDimensionOfElementWithinDiagram(DDiagram diagram, String newWorkspacePath) {
    String diagramType = diagram.getDescription().getName();
    return DIFFERENT_IMAGE_SIZE_PER_DIAGRAM.get(newWorkspacePath).entrySet().stream()
        .filter(entry -> diagramType.contains(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
  }

  private DDiagram getDiagramOf(EObject currentElement) {
    return ((AbstractDNode) currentElement).getParentDiagram();
  }

  private boolean isValidModelVersion(MigrationContext context) {
    Version version = context.getCurrentVersion();
    return version.getMajor() < 5 || (version.getMajor() == 5 && version.getMinor() <= 2);
  }
}
