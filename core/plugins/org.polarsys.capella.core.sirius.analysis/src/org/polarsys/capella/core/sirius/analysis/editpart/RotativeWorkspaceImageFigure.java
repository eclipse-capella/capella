/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.swt.graphics.Image;

/**
 * A rotative figure
 */
public class RotativeWorkspaceImageFigure extends WorkspaceImageFigure {

  private String path;

  private int orientation;
  
  /**
   * Creates a rotative image
   * 
   * @param path
   *          the NORTH image
   */
  public RotativeWorkspaceImageFigure(String path) {
    super(RotativeWorkspaceImageHelper.getImage(path, PositionConstants.NORTH));
    this.path = path;
    this.orientation = PositionConstants.NORTH;
  }

  @Deprecated
  public RotativeWorkspaceImageFigure(String path, Image top, Image left, Image bottom, Image right) {
    super(top);
    RotativeWorkspaceImageHelper.putImage(path, PositionConstants.WEST, left);
    RotativeWorkspaceImageHelper.putImage(path, PositionConstants.EAST, right);
    RotativeWorkspaceImageHelper.putImage(path, PositionConstants.SOUTH, bottom);
    RotativeWorkspaceImageHelper.putImage(path, PositionConstants.NORTH, top);
  }
  
  /**
   * Refresh the figure.
   * 
   * @param bundledImage
   *          the image associated to the figure
   */
  @Override
  public void refreshFigure(WorkspaceImage bundledImage) {
    if (path != bundledImage.getWorkspacePath()) {
      path = bundledImage.getWorkspacePath();
      Image image = RotativeWorkspaceImageHelper.getImage(path, orientation);
      if (getImage() != image) {
        setImage(image);
      }
      this.repaint();
    }
  }

  /**
   * @param orientation
   *          one of PositionConstants.WEST, PositionConstants.EAST, PositionConstants.SOUTH, PositionConstants.NORTH
   */
  public void setOrientation(int orientation) {
    this.orientation = orientation;
    Image image = RotativeWorkspaceImageHelper.getImage(path, orientation);
    if (getImage() != image) {
      setImage(image);
    }
  }

  @Deprecated
  public Image getRightImage() {
    return RotativeWorkspaceImageHelper.getImage(path, PositionConstants.EAST);
  }

  @Deprecated
  public Image getLeftImage() {
    return RotativeWorkspaceImageHelper.getImage(path, PositionConstants.WEST);
  }

  @Deprecated
  public Image getBottomImage() {
    return RotativeWorkspaceImageHelper.getImage(path, PositionConstants.SOUTH);
  }

  @Deprecated
  public Image getTopImage() {
    return RotativeWorkspaceImageHelper.getImage(path, PositionConstants.NORTH);
  }
  
}
