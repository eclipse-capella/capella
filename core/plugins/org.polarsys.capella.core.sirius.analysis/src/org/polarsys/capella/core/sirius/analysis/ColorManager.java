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

import java.util.LinkedList;
import java.util.List;

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

}
