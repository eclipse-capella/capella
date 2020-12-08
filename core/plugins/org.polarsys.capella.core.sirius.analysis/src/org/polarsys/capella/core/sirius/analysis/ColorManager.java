/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.RGB;

public class ColorManager {

  private final RGB GRAY_RGB_COLOR = new RGB(136, 136, 136);
  private final RGB YELLOW_RGB_COLOR = new RGB(249, 252, 103);
  private final RGB PURPLE_RGB_COLOR = new RGB(160, 32, 240);
  private final RGB GREEN_RGB_COLOR = new RGB(34, 139, 34);
  private final RGB ORANGE_RGB_COLOR = new RGB(255, 165, 0);
  private final RGB BROWN_RGB_COLOR = new RGB(165, 42, 42);
  private final RGB BLUE_RGB_COLOR = new RGB(24, 114, 248);

  private static ColorManager instance = null;

  private static Comparator<RGBValues> comparator = (l, r) -> {
    if (l.getRed() != r.getRed()) {
      return l.getRed() - r.getRed();

    } else if (l.getGreen() != r.getGreen()) {
      return l.getGreen() - r.getGreen();

    }
    return l.getRed() - r.getRed();
  };

  public static ColorManager getInstance() {
    if (instance == null) {
      instance = new ColorManager();
    }

    return instance;
  }

  private ColorManager() {
    // this constructor is empty in order to avoid accidental instantiation

  }

  public List<RGB> getColorList() {
    LinkedList<RGB> colorList = new LinkedList<>();
    colorList.addLast(BLUE_RGB_COLOR);
    colorList.addLast(BROWN_RGB_COLOR);
    colorList.addLast(GREEN_RGB_COLOR);
    colorList.addLast(PURPLE_RGB_COLOR);
    colorList.addLast(YELLOW_RGB_COLOR);
    colorList.addLast(ORANGE_RGB_COLOR);
    colorList.addLast(GRAY_RGB_COLOR);

    return colorList;

  }

  public RGB getGrayColor() {
    return GRAY_RGB_COLOR;
  }

  public RGBValues blend(RGBValues value1, RGBValues value2, double ratio) {
    float r = (float) ratio;
    float ir = (float) 1.0 - r;

    float r1 = value1.getRed();
    float g1 = value1.getGreen();
    float b1 = value1.getBlue();

    float r2 = value2.getRed();
    float g2 = value2.getGreen();
    float b2 = value2.getBlue();

    int mr = (int) (r1 * r + r2 * ir);
    int mg = (int) (g1 * r + g2 * ir);
    int mb = (int) (b1 * r + b2 * ir);

    return RGBValues.create(mr, mg, mb);
  }

  public RGBValues blend(RGBValues[] values, double ratio) {
    if (values == null || values.length == 0) {
      return null;
    }

    if (values.length == 1) {
      return values[0];
    }

    // make sure we always compute the colors in the same order
    Arrays.sort(values, comparator);
    RGBValues blendedValue = blend(values[0], values[1], ratio);

    if (values.length == 2) {
      return blendedValue;
    }

    for (int i = 2; i < values.length; i++) {
      blendedValue = blend(blendedValue, values[i], ratio);
    }

    return blendedValue;
  }

}
