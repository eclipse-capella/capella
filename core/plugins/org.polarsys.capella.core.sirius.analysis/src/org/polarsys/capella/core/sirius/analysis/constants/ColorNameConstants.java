/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.constants;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

/**
 * Centralizes constants about colors.
 */
public final class ColorNameConstants {

  public static final String ACTOR = "_CAP_Actor_Blue";

  private static final String COLOR_PALETTE = "Migration Palette";

  private static final URI COMMON = URI.createURI("viewpoint:/" + SiriusViewActivator.ID + "/Common");

  private ColorNameConstants() {
    super();
  }
  
  /**
   * Retrieve the color from common color palette or null if not found
   */
  public static RGBValues get(String colorConstant) {
    Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(COMMON);
    Optional<UserColorsPalette> palette = ((Group) vp.eContainer()).getUserColorsPalettes().stream()
        .filter(x -> COLOR_PALETTE.equals(x.getName())).findFirst();
    if (palette.isPresent()) {
      Optional<UserFixedColor> color = palette.get().getEntries().stream().filter(UserFixedColor.class::isInstance)
          .map(UserFixedColor.class::cast).filter(x -> x.getName().equals(colorConstant)).findFirst();
      if (color.isPresent()) {
        return new RGBValuesProvider().getRGBValues(color.get());
      }
    }
    return null;
  }

  /**
   * Returns whether the given color is the same than the constant one
   */
  public static boolean equals(RGBValues value, String colorConstant) {
    RGBValues color = get(colorConstant);
    if (color == null) {
      return value == null;
    }
    return color.equals(value);
  }
}
