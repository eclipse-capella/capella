/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.editpart;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.polarsys.capella.core.sirius.analysis.activator.SiriusViewActivator;

/**
 * An utility class to create rotated images and access them. It stores images in the ImageRegistry of this plugin
 */
public class RotativeWorkspaceImageHelper {

  /**
   * Return an image from the given path.
   * 
   * @param path
   *          {@link org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath <em>Workspace Path</em>}</li>
   * @param orientation
   *          one of PositionConstants.WEST, PositionConstants.EAST, PositionConstants.SOUTH, PositionConstants.NORTH
   * @return
   */
  public static Image getImage(String path, int orientation) {
    String key = path + orientation;

    ImageRegistry registry = SiriusViewActivator.getInstance().getImageRegistry();
    Image image = registry.get(key);
    if (image != null) {
      return image;
    }

    switch (orientation) {

    case PositionConstants.NORTH:
      image = WorkspaceImageFigure.flyWeightImage(path);
      break;
    case PositionConstants.SOUTH:
    case PositionConstants.WEST:
    case PositionConstants.EAST:
      image = rotate(getImage(path, PositionConstants.NORTH), orientation);
      break;
    default:
      throw new IllegalArgumentException("invalid orientation");
    }

    if (image != null) {
      registry.put(key, image);
    }
    return image;
  }

  /**
   * Store in cache the given image
   * 
   * @param orientation
   *          one of PositionConstants.WEST, PositionConstants.EAST, PositionConstants.SOUTH, PositionConstants.NORTH
   */
  @Deprecated
  static void putImage(String path, int orientation, Image image) {
    SiriusViewActivator.getInstance().getImageRegistry().put(path + orientation, image);
  }

  /**
   * Rotate an image
   * 
   * @param image
   *          the source
   * @param orientation
   *          PositionConstants.WEST will rotate by 90 degrees on the left, PositionConstants.EAST will rotate by 90
   *          degrees on the right, PositionConstants.SOUTH will rotate by 180 degrees.
   * @return the rotated image
   */
  private static Image rotate(Image image, int orientation) {
    if (image == null) {
      // We don't want to rotate a null image
      return null;
    }
    ImageData srcData = image.getImageData();
    int bytesPerPixel = srcData.bytesPerLine / srcData.width;
    int destBytesPerLine = (orientation == PositionConstants.SOUTH) ? srcData.width * bytesPerPixel
        : srcData.height * bytesPerPixel;
    byte[] newData = new byte[srcData.data.length];

    boolean isAlpha = srcData.alphaData != null;
    byte[] newAlphaData = null;

    if (isAlpha) {
      newAlphaData = new byte[srcData.alphaData.length];
    }
    ImageData imgData = new ImageData((orientation == PositionConstants.SOUTH) ? srcData.width : srcData.height,
        (orientation == PositionConstants.SOUTH) ? srcData.height : srcData.width, srcData.depth, srcData.palette,
        destBytesPerLine, newData);
    if (isAlpha) {
      imgData.alphaData = newAlphaData;
    }
    imgData.alpha = srcData.alpha;

    for (int srcY = 0; srcY < srcData.height; srcY++) {
      for (int srcX = 0; srcX < srcData.width; srcX++) {
        int destX = 0, destY = 0;

        switch (orientation) {
        case PositionConstants.WEST: // left 90 degrees
          destX = srcY;
          destY = srcData.width - srcX - 1;
          break;
        case PositionConstants.EAST: // right 90 degrees
          destX = srcData.height - srcY - 1;
          destY = srcX;
          break;
        case PositionConstants.SOUTH: // 180 degrees
          destX = srcData.width - srcX - 1;
          destY = srcData.height - srcY - 1;
          break;
        }

        imgData.setPixel(destX, destY, srcData.getPixel(srcX, srcY));
        if (isAlpha) {
          imgData.setAlpha(destX, destY, srcData.getAlpha(srcX, srcY));
        }
      }
    }

    return new Image(image.getDevice(), imgData);
  }

}
