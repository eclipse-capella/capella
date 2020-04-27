/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 */
public class OverlayedImageDescriptor extends CompositeImageDescriptor {

  public static final int TOP_LEFT = 0;
  public static final int TOP_RIGHT = 1;
  public static final int BOTTOM_LEFT = 2;
  public static final int BOTTOM_RIGHT = 3;
  public static final int DEFAULT_POSITION = 3;
  private Image _baseImage;
  private Image _overlayImage;
  private int _overlayImagePosition;
  private Point _sizeOfImage;

  public OverlayedImageDescriptor(Image baseImage, Image overlayImage) {
    this(baseImage, overlayImage, false, false);
  }

  public OverlayedImageDescriptor(Image baseImage, Image overlayImage, boolean isUp, boolean isRight) {
    _baseImage = baseImage;
    _overlayImage = overlayImage;
    _overlayImagePosition = getPosition(isUp, isRight);
    //added the exta width here GV
    _sizeOfImage = new Point(baseImage.getBounds().width + overlayImage.getBounds().width, baseImage.getBounds().height);
  }

  public Image getImage() {
    return createImage();
  }

  @Override
  protected Point getSize() {
    return _sizeOfImage;
  }

  @Override
  protected void drawCompositeImage(int arg0, int arg1) {
    drawImage(_baseImage.getImageData(), 0, 0);
    ImageData imageData = _overlayImage.getImageData();
    int xOverlay = 0;
    int yOverlay = 0;
    switch (_overlayImagePosition) {
      case 0: // '\0'
        xOverlay = 0;
        yOverlay = 0;
      break;

      case 1: // '\001'
        xOverlay = _sizeOfImage.x - imageData.width;
        yOverlay = 0;
      break;

      case 2: // '\002'
        xOverlay = 0;
        yOverlay = _sizeOfImage.y - imageData.height;
      break;

      case 3: // '\003'
        xOverlay = _sizeOfImage.x - imageData.width;
        yOverlay = _sizeOfImage.y - imageData.height;
      break;

      default:
        xOverlay = 0;
        yOverlay = _sizeOfImage.y - imageData.height;
      break;
    }
    drawImage(imageData, xOverlay, yOverlay);
  }

  private int getPosition(boolean isUp, boolean isRight) {
    int position = 3;
    if (isUp) {
      if (isRight)
        position = 1;
      else
        position = 0;
    } else if (isRight)
      position = 3;
    else
      position = 2;
    return position;
  }
}
