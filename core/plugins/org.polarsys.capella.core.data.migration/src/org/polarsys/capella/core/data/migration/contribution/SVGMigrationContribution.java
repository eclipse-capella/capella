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
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/SystemActor.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/SystemActor.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Duplicate.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionDuplicateDiagram.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Gather.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionGatherDiagram.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Route.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionRouteDiagram.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Select.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSelectDiagram.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Split.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSplitDiagram.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/initialState.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/initialState.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/finalState.png.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/finalState.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/Actor.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/Actor.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalActor.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalActor.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponent.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponent.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponentHuman.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponentHuman.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalActor.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalActor.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentNode.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentNode.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanNode.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanNode.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentBehavior.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentBehavior.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponent.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponent.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanUnset.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanUnset.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/eol.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/eol.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CategoryInput.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/CategoryInput.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/CategoryOutput.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/CategoryOutput.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FlowPort.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FlowPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionInputPort.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionInputPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionOutputPort.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionOutputPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/InFlowPort.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/InFlowPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/OutFlowPort.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/OutFlowPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPortSmall.png",  //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPortSmall.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_provided.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_provided.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_providedrequired.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_providedrequired.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_required.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/StandardPort_required.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/PhysicalPort.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/PhysicalPort.svg"); //$NON-NLS-1$
    changedImagesTemp.put("/org.polarsys.capella.core.sirius.analysis/description/images/PhysicalLink.png", //$NON-NLS-1$
        "/org.polarsys.capella.core.sirius.analysis/description/images/PhysicalLink.svg"); //$NON-NLS-1$
    CHANGED_IMAGES = Collections.unmodifiableMap(changedImagesTemp);

    Map<String, Map<String, Dimension>> differentSizePerDiagram = new HashMap<>();
    Map<String, Dimension> differentSizeTemp = new HashMap<>();
    differentSizeTemp.put("Data Flow Blank", new Dimension(56, 55)); //$NON-NLS-1$
    differentSizeTemp.put("Architecture Blank", new Dimension(50, 50)); //$NON-NLS-1$
    differentSizeTemp.put("Functional Chain Description", new Dimension(50, 49)); //$NON-NLS-1$
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionDuplicateDiagram.svg", //$NON-NLS-1$
        differentSizeTemp);
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionRouteDiagram.svg", differentSizeTemp); //$NON-NLS-1$
    differentSizeTemp = new HashMap<>();

    differentSizeTemp.put("Data Flow Blank", new Dimension(56, 56)); //$NON-NLS-1$
    differentSizeTemp.put("Architecture Blank", new Dimension(50, 50)); //$NON-NLS-1$
    differentSizeTemp.put("Functional Chain Description", new Dimension(50, 50)); //$NON-NLS-1$
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionGatherDiagram.svg", differentSizeTemp); //$NON-NLS-1$
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSelectDiagram.svg", differentSizeTemp); //$NON-NLS-1$
    differentSizePerDiagram.put(
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSplitDiagram.svg", differentSizeTemp); //$NON-NLS-1$
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
