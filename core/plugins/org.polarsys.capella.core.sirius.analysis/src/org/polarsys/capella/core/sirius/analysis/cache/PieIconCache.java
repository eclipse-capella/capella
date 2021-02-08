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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * 
 *  A cache of pie icon created from input colors
 */
public class PieIconCache {

  private static PieIconCache instance;

  public static PieIconCache getInstance() {
    if (instance == null) {
      instance = new PieIconCache();
    }
    return instance;
  }

  private Map<List<RGBValues>, Image> colors2Icons = new HashMap<>();

  private PieIconCache() {
  }
  
  private Comparator<RGBValues> colorComparator = (l, r) -> {
    if (l.getRed() != r.getRed()) {
      return l.getRed() - r.getRed();

    } else if (l.getGreen() != r.getGreen()) {
      return l.getGreen() - r.getGreen();

    }
    return l.getBlue() - r.getBlue();
  };

  public Image getIcon(List<RGBValues> colors) {
    if (!colors2Icons.containsKey(colors)) {
      // Sort colors to always have the same icon regardless of the order of the input colors
      colors.sort(colorComparator);
      Display display = Display.getDefault();
      Image canvas = new Image(display, 16, 16);
      GC gc = new GC(canvas);
      gc.setAntialias(SWT.ON);
      
      // First set the alpha on the entire canvas to transparent
      gc.setAlpha(0);
      gc.fillRectangle(0, 0, 16, 16);
      // Reset the alpha to opaque
      gc.setAlpha(255);
      
      // Draw the circle
      int diameter = 16;
      int radius = diameter/2;
      int nbOfColors = colors.size();
      int angle = 360 / nbOfColors;
      Iterator<RGBValues> iterator = colors.iterator();
      int i = 0;
      while (iterator.hasNext()) {
        RGBValues rgbValues = iterator.next();
        gc.setBackground(new Color(gc.getDevice(), rgbValues.getRed(), rgbValues.getGreen(), rgbValues.getBlue()));
        gc.fillArc(0, 0, diameter, diameter, i * angle, angle);
        i++;
      }
      
      // Draw lines to separate different colors
      for (int j = 0; j < colors.size(); j++) {
        gc.setForeground(new Color(gc.getDevice(), 255, 255, 255));
        int xCoord = radius + (int) Math.floor(radius * Math.cos(Math.toRadians(j * (double) angle)) + 0.5);
        int yCoord = radius + (int) Math.floor(radius * Math.sin(Math.toRadians(j * (double) angle)) + 0.5);
        gc.drawLine(radius, radius, xCoord, yCoord);
      }
      
      // Update alphaData with the alpha byte (the 4th byte in case of 32bit image data) to make the uncolored region
      // transparent
      ImageData canvasData = canvas.getImageData();
      canvasData.alphaData = new byte[16 * 16];
      for (int idx = 0; idx < (16 * 16); idx++) {
        int coord = (idx * 4) + 3;
        canvasData.alphaData[idx] = canvasData.data[coord];
      }

      Image finalImage = new Image(gc.getDevice(), canvasData);
      canvas.dispose();
      gc.dispose();
      colors2Icons.put(colors, finalImage);
    }
    return colors2Icons.get(colors);
  }
}
