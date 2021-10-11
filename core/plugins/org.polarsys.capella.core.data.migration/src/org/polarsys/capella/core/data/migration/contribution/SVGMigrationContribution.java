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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.viewpoint.Style;
import org.osgi.framework.Version;
import org.polarsys.capella.core.data.migration.context.MigrationContext;

public class SVGMigrationContribution extends AbstractMigrationContribution {
  /*
   * Map containing the workspace paths of the images replaced and what they're replaced with.
   */
  public static final Map<String, String> CHANGED_IMAGES;

  static {
    Map<String, String> tempMap = new HashMap();
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/SystemActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/SystemActor.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Duplicate.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionDuplicateDiagram.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Gather.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionGatherDiagram.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Route.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionRouteDiagram.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Select.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSelectDiagram.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/FunctionKind_Split.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/FunctionSplitDiagram.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/initialState.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/initialState.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/finalState.png.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/finalState.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/terminateState.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/Actor.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/Actor.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalActor.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponent.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponent.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/LogicalComponentHuman.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/LogicalComponentHuman.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalActor.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalActor.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentNode.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentNode.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanNode.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanNode.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentBehavior.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentBehavior.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponent.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponent.svg");
    tempMap.put("/org.polarsys.capella.core.ui.resources/icons/full/png/PhysicalComponentHumanUnset.png",
        "/org.polarsys.capella.core.ui.resources/icons/full/svg/PhysicalComponentHumanUnset.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/CSCI.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/CapabilityRealization.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/eol.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/eol.svg");
    tempMap.put("/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.png",
        "/org.polarsys.capella.core.sirius.analysis/description/images/handlelifeline.svg");
    CHANGED_IMAGES = Collections.unmodifiableMap(tempMap);
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
      }
    }
  }

  private boolean isValidModelVersion(MigrationContext context) {
    Version version = context.getCurrentVersion();
    return version.getMajor() < 5 || (version.getMajor() == 5 && version.getMinor() <= 2);
  }
}
