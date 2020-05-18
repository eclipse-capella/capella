/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.AncestorListener;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * Specific Edit Part for rotative image
 */
public class RotativeImageEditPart extends WorkspaceImageEditPart implements IStyleEditPart {

  @Deprecated
  public static final String[] IMAGES_ID = RotativeImageEditPartProvider.IMAGES_IDS.toArray(new String[0]);

  private SwitchImageListener switchImageListener;

  /**
   * Creates a new port edit part.
   * @param view the GMF view.
   */
  public RotativeImageEditPart(View view) {
    super(view);
  }

  /**
   * @generated NOT
   */
  @Override
  protected IFigure createNodeShape() {
    EObject obj = resolveSemanticElement();
    if (obj instanceof WorkspaceImage) {
      WorkspaceImage wkImage = (WorkspaceImage) obj;
      primaryShape = new RotativeWorkspaceImageFigure(wkImage.getWorkspacePath());
      switchImageListener = new SwitchImageListener(this);
      primaryShape.addAncestorListener(switchImageListener);
      primaryShape.addPropertyChangeListener(switchImageListener);
    }
    return primaryShape;
  }

  /**
   * Creates figure for this edit part. Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and
   * modify it.
   * @not-generated
   */
  @Override
  protected NodeFigure createNodeFigure() {
    NodeFigure node = createNodePlate();
    node.setLayoutManager(new XYLayout()); // TODO find why it is different than super implementation
    IFigure shape = createNodeShape();
    node.add(shape);
    contentPane = setupContentPane(shape);
    return node;
  }

  private static class SwitchImageListener implements AncestorListener, PropertyChangeListener, FigureListener {

    private RotativeImageEditPart editPart;

    public SwitchImageListener(RotativeImageEditPart editPart) {
      this.editPart = editPart;
    }

    public void ancestorAdded(IFigure ancestor) {
      updateImage();
    }

    public void ancestorMoved(IFigure ancestor) {
      updateImage();
    }

    public void ancestorRemoved(IFigure ancestor) {
      updateImage();
    }

    public void propertyChange(PropertyChangeEvent arg0) {
      updateImage();
    }

    public void figureMoved(IFigure source) {
      updateImage();
    }

    @SuppressWarnings("synthetic-access")
    public void updateImage() {
      if (editPart.figure == null || editPart.getPrimaryShape() == null)
        return;
      IBorderItemLocator borderItemLocator = getBorderItemLocator();
      if (borderItemLocator != null) {
        BorderedNodeFigure borderedNodeFigure = getBorderedNodeFigure();
        int side = DBorderItemLocator.findClosestSideOfParent(editPart.getFigure().getBounds(), borderedNodeFigure.getBounds());
        ((RotativeWorkspaceImageFigure) editPart.getPrimaryShape()).setOrientation(side);
      }
    }

    private BorderedNodeFigure getBorderedNodeFigure() {
      boolean first = false;
      BorderedNodeFigure borderedNodeFigure = null;
      IGraphicalEditPart current = editPart;
      IBorderedShapeEditPart borderNodeEditPart = null;
      while (current != null && borderNodeEditPart == null) {
        if (current instanceof IBorderedShapeEditPart) {
          if (first)
            borderNodeEditPart = (IBorderedShapeEditPart) current;
          else
            first = true;
        }
        current = (IGraphicalEditPart) current.getParent();
      }

      borderedNodeFigure = borderNodeEditPart != null ? borderNodeEditPart.getBorderedFigure() : null;
      return borderedNodeFigure;
    }

    private IBorderItemLocator getBorderItemLocator() {
      IBorderItemLocator borderItemLocator = null;
      IGraphicalEditPart current = editPart;
      IDiagramBorderNodeEditPart borderNodeEditPart = null;
      while (current != null && borderNodeEditPart == null) {
        if (current instanceof IDiagramBorderNodeEditPart) {
          borderNodeEditPart = (IDiagramBorderNodeEditPart) current;
        }
        current = (IGraphicalEditPart) current.getParent();
      }

      if (borderNodeEditPart instanceof IBorderItemEditPart) {
        borderItemLocator = ((IBorderItemEditPart) borderNodeEditPart).getBorderItemLocator();
      }

      return borderItemLocator;
    }
  }
}
