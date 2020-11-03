/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.swt.graphics.RGB;

public final class FlexibilityColors {

  public static final String BG_INFO = "infoStatusBgColor"; //$NON-NLS-1$
  public static final String BG_WARNING = "warningStatusBgColor"; //$NON-NLS-1$
  public static final String BG_ERROR = "errorStatusBgColor"; //$NON-NLS-1$

  public static final String FG_GREEN = "greenColor"; //$NON-NLS-1$

  private static ColorRegistry colorRegistry;

  public static ColorRegistry getColorRegistry() {
    if (colorRegistry == null) {
      colorRegistry = new ColorRegistry();
      initializeColorRegistry(colorRegistry);
    }
    return colorRegistry;
  }

  private static void initializeColorRegistry(ColorRegistry registry) {
    registry.put(BG_INFO, new RGB(217, 255, 209));
    registry.put(BG_WARNING, new RGB(254, 241, 137));
    registry.put(BG_ERROR, new RGB(252, 222, 222));
    registry.put(FG_GREEN, new RGB(0,128,0));
  }


}
