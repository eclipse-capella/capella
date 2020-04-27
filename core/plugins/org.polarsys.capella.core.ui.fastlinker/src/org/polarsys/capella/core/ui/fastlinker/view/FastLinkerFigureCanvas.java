/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.fastlinker.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.core.model.links.helpers.LinkInfo;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;
import org.polarsys.capella.core.ui.fastlinker.view.providers.FastLinkerLabelProvider;

/**
 */
public class FastLinkerFigureCanvas extends FigureCanvas implements ISelectionProvider {
  /**
   */
  protected class SelectionDragManager extends MouseMotionListener.Stub implements MouseListener {

    private IFigure draggedFigure;

    private Point lastFigureLocation;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDoubleClicked(MouseEvent me) {
      fireDoubleClick();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (null == draggedFigure) {
        return;
      }
      Point newFigureLocation = e.getLocation();
      Dimension delta = newFigureLocation.getDifference(lastFigureLocation);
      lastFigureLocation = newFigureLocation;
      if (fastLinkerFigure.getLayoutManager() instanceof FilteringGridLayout) {
        switchToXYLayout(fastLinkerFigure);
      }
      Rectangle newBounds = draggedFigure.getBounds().getTranslated(delta.width, delta.height);
      // Forbid to drag the figure beyond the left and top limits of the canvas.
      if ((newBounds.x > 0) && (newBounds.y > 0)) {
        draggedFigure.getParent().setConstraint(draggedFigure, newBounds);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      lastFigureLocation = e.getLocation();
      e.consume();
      // Get Label at event location.
      IFigure newSelectedFigure = fastLinkerFigure.findFigureAt(e.x, e.y, LABEL_FILTER);
      if (selectedFigure != newSelectedFigure) {
        if (null != selectedFigure) {
          selectedFigure.setBorder(null);
        }
        if (null != newSelectedFigure) {
          newSelectedFigure.setBorder(new LineBorder());
          if (fastLinkerFigure.getLayoutManager().getConstraint(newSelectedFigure) instanceof Rectangle) {
            ((Rectangle) fastLinkerFigure.getLayoutManager().getConstraint(newSelectedFigure)).setSize(newSelectedFigure.getPreferredSize());
          }
        }
        selectedFigure = newSelectedFigure;
        fireSelectionChanged();
      }
      if (null != newSelectedFigure) {
        draggedFigure = newSelectedFigure;
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      draggedFigure = null;
    }
  }

  /**
   * Filter allowing to select figures of type Label only.
   */
  protected static final TreeSearch LABEL_FILTER = new TreeSearch() {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accept(IFigure figure) {
      // Accept figure of type Label only (and so ignore Connections and main figure).
      return (figure instanceof Label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean prune(IFigure figure) {
      return false;
    }
  };

  protected final List<IDoubleClickListener> doubleClickListeners;

  protected final Figure fastLinkerFigure;

  protected final Map<IFigure, Collection> figureToModelElement;
  protected final FastLinkerLabelProvider capellaElementLabelProvider;

  protected IFigure selectedFigure;

  protected final List<ISelectionChangedListener> selectionListeners;

  protected final SelectionDragManager sharedDragManager;

  /**
   * Constructor.
   * @param parent
   */
  public FastLinkerFigureCanvas(Composite parent, int style) {
    super(parent, style);

    figureToModelElement = new HashMap<IFigure, Collection>();
    selectionListeners = new ArrayList<ISelectionChangedListener>();
    doubleClickListeners = new ArrayList<IDoubleClickListener>();

    // Label provider for ModelElements displayed in the view.
    capellaElementLabelProvider = new FastLinkerLabelProvider();

    fastLinkerFigure = new Figure();
    setContents(fastLinkerFigure);

    sharedDragManager = new SelectionDragManager();
    fastLinkerFigure.addMouseListener(sharedDragManager);
    fastLinkerFigure.addMouseMotionListener(sharedDragManager);

    setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
    setScrollBarVisibility(FigureCanvas.AUTOMATIC);
  }

  public void addDoubleClickListener(IDoubleClickListener listener) {
    doubleClickListeners.add(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener) {
    selectionListeners.add(listener);
  }

  protected IFigure createConnection(IFigure sourceFigure, IFigure targetFigure, LinkStyle linkGraphicalRepresentation) {
    PolylineConnection connection = new PolylineConnection();
    connection.setSourceAnchor(new ChopboxAnchor(sourceFigure));
    connection.setTargetAnchor(new ChopboxAnchor(targetFigure));
    connection.setAntialias(SWT.ON);
    // Line style.
    if (LinkStyle.LINE_DASHED == linkGraphicalRepresentation) {
      connection.setLineStyle(SWT.LINE_CUSTOM);
      connection.setLineDash(new float[] { 4f, 4f });
    } else {
      connection.setLineStyle(SWT.LINE_SOLID);
    }
    // Arrow.
    if (LinkStyle.LINE_SOLID_WITH_EMPTY_ARROW == linkGraphicalRepresentation) {
      PolygonDecoration pd = new PolygonDecoration();
      pd.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
      connection.setTargetDecoration(pd);
    } else if (LinkStyle.LINE_SOLID_WITH_FILLED_ARROW == linkGraphicalRepresentation) {
      PolygonDecoration pd = new PolygonDecoration();
      connection.setTargetDecoration(pd);
    }
    return connection;
  }

  protected Label createModelElementFigure(Collection modelElement, boolean displayInBold) {
    Label modelElementFigure = new Label(capellaElementLabelProvider.getText(modelElement), capellaElementLabelProvider.getImage(modelElement));
    if (displayInBold) {
      // Get the BOLD font from the FontRegistry.
      Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
      modelElementFigure.setFont(boldFont);
    }
    figureToModelElement.put(modelElementFigure, modelElement);
    return modelElementFigure;
  }

  public void fillFigure(Collection firstElement, Collection secondElement, Collection pinnedElement, LinkInfo linkRepresentation) {
    figureToModelElement.clear();
    fastLinkerFigure.removeAll();
    selectedFigure = null;
    fireSelectionChanged();

    fastLinkerFigure.setLayoutManager(new FilteringGridLayout(1, false));

    IFigure secondElementFigure = null;
    if (null != secondElement) {
      secondElementFigure = createModelElementFigure(secondElement, secondElement == pinnedElement);
      fastLinkerFigure.add(secondElementFigure, new FilteringGridData(SWT.CENTER, SWT.CENTER, true, true));
    }

    IFigure firstElementFigure = null;
    if (null != firstElement) {
      firstElementFigure = createModelElementFigure(firstElement, firstElement == pinnedElement);
      fastLinkerFigure.add(firstElementFigure, new FilteringGridData(SWT.CENTER, SWT.CENTER, true, true));
    }

    if (null != linkRepresentation) {
      IFigure connection = null;
			if (firstElement != null && (firstElement.contains(linkRepresentation._sourceElement))
					&& secondElement != null && (secondElement
							.contains(linkRepresentation._targetElement))) {
				connection = createConnection(firstElementFigure,
						secondElementFigure, linkRepresentation._linkStyle);
			} else if (secondElement != null && (secondElement
					.contains(linkRepresentation._sourceElement))
					&& firstElement != null && (firstElement
							.contains(linkRepresentation._targetElement))) {
				connection = createConnection(secondElementFigure,
						firstElementFigure, linkRepresentation._linkStyle);
			}
			if (null != connection) {
				fastLinkerFigure.add(connection);
			}
    }
  }

  protected void fireDoubleClick() {
    final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    for (final IDoubleClickListener listener : doubleClickListeners) {
      listener.doubleClick(event);
    }
  }

  protected void fireSelectionChanged() {
    final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    for (final ISelectionChangedListener listener : selectionListeners) {
      listener.selectionChanged(event);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelection getSelection() {
    if ((null == selectedFigure) || (null == figureToModelElement.get(selectedFigure))) {
      return StructuredSelection.EMPTY;
    }

    return new StructuredSelection(figureToModelElement.get(selectedFigure));
  }

  public void removeDoubleClickListener(IDoubleClickListener listener) {
    doubleClickListeners.remove(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    selectionListeners.remove(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSelection(ISelection selection) {
    // Nothing to do.
  }

  protected void switchToXYLayout(IFigure figure) {
    figure.setLayoutManager(new XYLayout());
    // Add constraints.
    List<?> children = figure.getChildren();
    for (Object child : children) {
      IFigure childFigure = (IFigure) child;
      figure.setConstraint(childFigure, childFigure.getBounds());
    }
  }
}
