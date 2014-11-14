/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.editpart;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * Rotative figure
 */
public class RotativeWorkspaceImageFigure extends WorkspaceImageFigure {

  /** FIXME Should not exist, but the cache used into super class is private and not protected....... */
  private static Map<String, Image> cache = new HashMap<String, Image>();

  /**
   * Path of workspace image
   */
  private String path;

  /**
   * Image loaded which will be rotated
   */
  private Image root;

  private int mode = 0;
  private final int FOUR_IMAGES = 0;
  private final int ROTATIVE = 1;

  /**
   * Creates a rotative image
   * @param path_p the path of the top image.
   */
  public RotativeWorkspaceImageFigure(String path_p) {
    super(flyWeightImage(path_p));
    this.path = path_p;
    this.root = flyWeightImage(path_p);
    mode = ROTATIVE;
  }

  /**
   * Creates a rotative image
   * @param path_p the path of the top image.
   */
  public RotativeWorkspaceImageFigure(String path_p, Image top, Image left, Image bottom, Image right) {
    super(top);
    cache.put(path_p + "_top", top); //$NON-NLS-1$
    cache.put(path_p + "_bottom", bottom); //$NON-NLS-1$
    cache.put(path_p + "_left", left); //$NON-NLS-1$
    cache.put(path_p + "_right", right); //$NON-NLS-1$
    mode = FOUR_IMAGES;
  }

  /**
   * Rotate an image
   * @param image the source
   * @param direction : SWT.LEFT will rotate by 90 degrees on the left, SWT.RIGHT will rotate by 90 degrees on the right, SWT.DOWN will rotate by 180 degrees.
   * @return the rotated image
   */
  private Image rotate(Image image, int direction) {
    ImageData srcData = image.getImageData();
    int bytesPerPixel = srcData.bytesPerLine / srcData.width;
    int destBytesPerLine = (direction == SWT.DOWN) ? srcData.width * bytesPerPixel : srcData.height * bytesPerPixel;
    byte[] newData = new byte[srcData.data.length];

    boolean isAlpha = srcData.alphaData != null;
    byte[] newAlphaData = null;

    if (isAlpha) {
      newAlphaData = new byte[srcData.alphaData.length];
    }
    ImageData imgData =
        new ImageData((direction == SWT.DOWN) ? srcData.width : srcData.height, (direction == SWT.DOWN) ? srcData.height : srcData.width, srcData.depth,
            srcData.palette, destBytesPerLine, newData);
    if (isAlpha) {
      imgData.alphaData = newAlphaData;
    }
    imgData.alpha = srcData.alpha;

    for (int srcY = 0; srcY < srcData.height; srcY++) {
      for (int srcX = 0; srcX < srcData.width; srcX++) {
        int destX = 0, destY = 0;

        switch (direction) {
          case SWT.LEFT: // left 90 degrees
            destX = srcY;
            destY = srcData.width - srcX - 1;
          break;
          case SWT.RIGHT: // right 90 degrees
            destX = srcData.height - srcY - 1;
            destY = srcX;
          break;
          case SWT.DOWN: // 180 degrees
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

  public Image getRightImage() {
    Image img = cache.get(path + "_right"); //$NON-NLS-1$
    if (img == null && mode == ROTATIVE) {
      cache.put(path + "_right", img = rotate(root, SWT.RIGHT)); //$NON-NLS-1$
    }
    return img;
  }

  public Image getLeftImage() {
    Image img = cache.get(path + "_left"); //$NON-NLS-1$
    if (img == null && mode == ROTATIVE) {
      cache.put(path + "_left", img = rotate(root, SWT.LEFT)); //$NON-NLS-1$
    }
    return img;
  }

  public Image getBottomImage() {
    Image img = cache.get(path + "_bottom"); //$NON-NLS-1$
    if (img == null && mode == ROTATIVE) {
      cache.put(path + "_bottom", img = rotate(root, SWT.DOWN)); //$NON-NLS-1$
    }
    return img;
  }

  public Image getTopImage() {
    return root;
  }

  @Override
  public void setImage(Image image) {
    super.setImage(image);
  }

  /**
   * Refresh the figure.
   * @param bundledImage the image associated to the figure
   */
  @Override
  public void refreshFigure(final WorkspaceImage bundledImage) {
    if (this.path != bundledImage.getWorkspacePath()) {
      this.path = bundledImage.getWorkspacePath();
      this.root = flyWeightImage(path);
      this.setImage(root);
    }
    this.repaint();
  }

  /**
   * Refresh the figure.
   * @param containerStyle the style of the container
   */
  @Override
  public void refreshFigure(final ContainerStyle containerStyle) {

    if (containerStyle instanceof WorkspaceImage) {
      refreshFigure((WorkspaceImage) containerStyle);
    }
  }
}
