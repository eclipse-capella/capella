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
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.Messages;

public class FixCustomFeaturesHelper extends AbstractFixDiagramHelper {

  private static final RGBValues LIGHT_ORANGE = createRGBValues(252, 233, 79);
  private static final RGBValues DARK_ORANGE = createRGBValues(252, 175, 62);
  private static final RGBValues LIGHT_GREEN = createRGBValues(204, 242, 166);
  private static final RGBValues DARK_GREEN = createRGBValues(138, 226, 52);

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
        // check if backgroundColor is red="204" green="242" blue="166"
        // and foregroundColor is red="138" green="226" blue="52" OR backgroundColor is red="252" green="233"
        // blue="79"
        // and foregroundColor is red="252" green="175" blue="62",
        // remove all custom features expect borderColor and borderSize
        FlatContainerStyle style = (FlatContainerStyle) obj;
        RGBValues backgroundColor = style.getBackgroundColor();
        RGBValues foregroundColor = style.getForegroundColor();
        if ((isExpectedColor(LIGHT_GREEN, backgroundColor) && isExpectedColor(DARK_GREEN, foregroundColor))
            || (isExpectedColor(LIGHT_ORANGE, backgroundColor) && isExpectedColor(DARK_ORANGE, foregroundColor))) {
          retainBorderColorAndBorderSize(style, diagramToModifiedObjectCount);
        }
      } else if (isSquareWithCustomColor(obj)) {
        Square square = (Square) obj;
        RGBValues color = square.getColor();
        if (isExpectedColor(DARK_GREEN, color) || isExpectedColor(DARK_ORANGE, color)) {
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
    return candidate instanceof Square
        && ((Square) candidate).getCustomFeatures().contains(DiagramPackage.Literals.SQUARE__COLOR.getName())
        && candidate.eContainer() instanceof DNode
        && (((DNode) candidate.eContainer()).getTarget() instanceof PhysicalFunction
            || ((DNode) candidate.eContainer()).getTarget() instanceof LogicalFunction
            || ((DNode) candidate.eContainer()).getTarget() instanceof SystemFunction
            || ((DNode) candidate.eContainer()).getTarget() instanceof OperationalActivity);
  }

  private boolean isStyleWithCustomForegroundBackground(EObject candidate) {
    return candidate instanceof FlatContainerStyle
        && ((FlatContainerStyle) candidate).getCustomFeatures()
            .contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR.getName())
        && ((FlatContainerStyle) candidate).getCustomFeatures()
            .contains(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR.getName())
        && candidate.eContainer() instanceof DNodeContainer
        && (((DNodeContainer) candidate.eContainer()).getTarget() instanceof SystemFunction
            || ((DNodeContainer) candidate.eContainer()).getTarget() instanceof LogicalFunction
            || ((DNodeContainer) candidate.eContainer()).getTarget() instanceof OperationalActivity);

  }

  /**
   *
   * @param expectedColor
   * @param actualColor
   * @return
   */
  private boolean isExpectedColor(RGBValues expectedColor, RGBValues actualColor) {
    return actualColor != null && expectedColor.getRed() == actualColor.getRed()
        && expectedColor.getGreen() == actualColor.getGreen() && expectedColor.getBlue() == actualColor.getBlue();
  }

  private static RGBValues createRGBValues(int red, int green, int blue) {
    RGBValues rgb = ViewpointFactory.eINSTANCE.createRGBValues();
    rgb.setRed(red);
    rgb.setGreen(green);
    rgb.setBlue(blue);
    return rgb;
  }
}
