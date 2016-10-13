/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.Messages;

public class FixCustomFeaturesHelper extends AbstractFixDiagramHelper {

  private static final RGBValues LIGHT_ORANGE_RC = createRGBValues(252, 233, 79);
  private static final RGBValues DARK_ORANGE_RC = createRGBValues(252, 175, 62);
  private static final RGBValues LIGHT_GREEN_RC = createRGBValues(204, 242, 166);
  private static final RGBValues DARK_GREEN_RC = createRGBValues(138, 226, 52);
  private static final RGBValues LIGHT_BLUE_RC = createRGBValues(194, 239, 255);
  private static final RGBValues DARK_BLUE_RC = createRGBValues(114, 159, 207);

  private static final RGBValues LIGHT_ORANGE_0_8 = createRGBValues(255, 255, 255);
  private static final RGBValues DARK_ORANGE_0_8 = createRGBValues(247, 218, 116);
  private static final RGBValues LIGHT_GREEN_0_8 = createRGBValues(255, 255, 255);
  private static final RGBValues DARK_GREEN_0_8 = createRGBValues(222, 255, 204);
  private static final RGBValues LIGHT_BLUE_0_8 = createRGBValues(255, 255, 255);
  private static final RGBValues DARK_BLUE_0_8 = createRGBValues(198, 230, 255);

  public FixCustomFeaturesHelper() {
    setLogPrefix(Messages.FixCustomFeaturesJobName);
  }

  @Override
  protected Map<DRepresentation, Integer> doFixDiagrams(Resource resource) {
    Map<DRepresentation, Integer> diagramToModifiedObjectCount = new HashMap<DRepresentation, Integer>();

    Iterator<EObject> it = resource.getAllContents();
    while (it.hasNext()) {
      EObject obj = it.next();
      if (isStyleWithCustomForegroundBackground(obj)) {
        // This is a style with custom foregroundColor/ backgroundColor,
        // check if backgroundColor remove all custom features except borderColor and borderSize
        FlatContainerStyle style = (FlatContainerStyle) obj;
        RGBValues backgroundColor = style.getBackgroundColor();
        RGBValues foregroundColor = style.getForegroundColor();

        if ((isExpectedColor(LIGHT_GREEN_RC, backgroundColor) && isExpectedColor(DARK_GREEN_RC, foregroundColor))
            || (isExpectedColor(LIGHT_ORANGE_RC, backgroundColor) && isExpectedColor(DARK_ORANGE_RC, foregroundColor))
            || (isExpectedColor(LIGHT_BLUE_RC, backgroundColor) && isExpectedColor(DARK_BLUE_RC, foregroundColor))
            || (isExpectedColor(LIGHT_GREEN_0_8, backgroundColor) && isExpectedColor(DARK_GREEN_0_8, foregroundColor))
            || (isExpectedColor(LIGHT_ORANGE_0_8, backgroundColor) && isExpectedColor(DARK_ORANGE_0_8,
                foregroundColor))
            || (isExpectedColor(LIGHT_BLUE_0_8, backgroundColor) && isExpectedColor(DARK_BLUE_0_8, foregroundColor))) {
          retainBorderColorAndBorderSize(style, diagramToModifiedObjectCount);
        }
      } else if (isSquareWithCustomColor(obj)) {
        Square square = (Square) obj;
        RGBValues color = square.getColor();
        if (isExpectedColor(DARK_GREEN_RC, color) || isExpectedColor(DARK_ORANGE_RC, color)
            || isExpectedColor(DARK_BLUE_RC, color) || isExpectedColor(DARK_GREEN_0_8, color)
            || isExpectedColor(DARK_ORANGE_0_8, color) || isExpectedColor(DARK_BLUE_0_8, color)) {
          retainBorderColorAndBorderSize(square, diagramToModifiedObjectCount);
        }
      }
    }

    return diagramToModifiedObjectCount;
  }

  private void retainBorderColorAndBorderSize(Customizable customizable,
      Map<DRepresentation, Integer> diagramToModifiedObjectCount) {
    DDiagramElement dDiagramElement = (DDiagramElement) customizable.eContainer();
    DDiagram diagram = DiagramHelper.getService().getDiagramContainer(dDiagramElement);

    // Log info
    logInfo("Removing custom features from: " + dDiagramElement.getName() + " in diagram: " + diagram.getName());

    // Do the change
    List<String> toRetain = new ArrayList<String>();
    toRetain.add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_COLOR.getName());
    toRetain.add(DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE.getName());
    customizable.getCustomFeatures().retainAll(toRetain);

    // Put in the map
    Integer count = diagramToModifiedObjectCount.get(diagram);
    if (count != null) {
      count = count + 1;
      diagramToModifiedObjectCount.put(diagram, count);
    } else {
      diagramToModifiedObjectCount.put(diagram, 1);
    }
  }

  private boolean isSquareWithCustomColor(EObject candidate) {
    return (candidate instanceof Square)
        && ((Square) candidate).getCustomFeatures().contains(DiagramPackage.Literals.SQUARE__COLOR.getName())
        && (candidate.eContainer() instanceof DNode)
        && ((((DNode) candidate.eContainer()).getTarget() instanceof AbstractFunction));
  }

  private boolean isStyleWithCustomForegroundBackground(EObject candidate) {
    return (candidate instanceof FlatContainerStyle)
        && ((FlatContainerStyle) candidate).getCustomFeatures()
            .contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR.getName())
        && ((FlatContainerStyle) candidate).getCustomFeatures()
            .contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR.getName())
        && (candidate.eContainer() instanceof DNodeContainer)
        && (((DNodeContainer) candidate.eContainer()).getTarget() instanceof AbstractFunction);
  }

  private boolean isExpectedColor(RGBValues expectedColor, RGBValues actualColor) {
    return (actualColor != null) && (expectedColor.getRed() == actualColor.getRed())
        && (expectedColor.getGreen() == actualColor.getGreen()) && (expectedColor.getBlue() == actualColor.getBlue());
  }

  private static RGBValues createRGBValues(int red, int green, int blue) {
    return RGBValues.create(red, green, blue);
  }
}
