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
import org.polarsys.capella.core.ui.fastlinker.view.providers.FastLinkerLabelProvider;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.model.links.helpers.LinkInfo;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class FastLinkerFigureCanvas extends FigureCanvas implements ISelectionProvider {
  /**
   */
  protected class SelectionDragManager extends MouseMotionListener.Stub implements MouseListener {

    private IFigure _draggedFigure;

    private Point _lastFigureLocation;

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDoubleClicked(MouseEvent me_p) {
      fireDoubleClick();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (null == _draggedFigure) {
        return;
      }
      Point newFigureLocation = e.getLocation();
      Dimension delta = newFigureLocation.getDifference(_lastFigureLocation);
      _lastFigureLocation = newFigureLocation;
      if (_fastLinkerFigure.getLayoutManager() instanceof FilteringGridLayout) {
        switchToXYLayout(_fastLinkerFigure);
      }
      Rectangle newBounds = _draggedFigure.getBounds().getTranslated(delta.width, delta.height);
      // Forbid to drag the figure beyond the left and top limits of the canvas.
      if ((newBounds.x > 0) && (newBounds.y > 0)) {
        _draggedFigure.getParent().setConstraint(_draggedFigure, newBounds);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      _lastFigureLocation = e.getLocation();
      e.consume();
      // Get Label at event location.
      IFigure newSelectedFigure = _fastLinkerFigure.findFigureAt(e.x, e.y, LABEL_FILTER);
      if (_selectedFigure != newSelectedFigure) {
        if (null != _selectedFigure) {
          _selectedFigure.setBorder(null);
        }
        if (null != newSelectedFigure) {
          newSelectedFigure.setBorder(new LineBorder());
          if (_fastLinkerFigure.getLayoutManager().getConstraint(newSelectedFigure) instanceof Rectangle) {
            ((Rectangle) _fastLinkerFigure.getLayoutManager().getConstraint(newSelectedFigure)).setSize(newSelectedFigure.getPreferredSize());
          }
        }
        _selectedFigure = newSelectedFigure;
        fireSelectionChanged();
      }
      if (null != newSelectedFigure) {
        _draggedFigure = newSelectedFigure;
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      _draggedFigure = null;
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
    public boolean accept(IFigure figure_p) {
      // Accept figure of type Label only (and so ignore Connections and main figure).
      return (figure_p instanceof Label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean prune(IFigure figure_p) {
      return false;
    }
  };

  protected final List<IDoubleClickListener> _doubleClickListeners;

  protected final Figure _fastLinkerFigure;

  protected final Map<IFigure, Collection> _figureToModelElement;
protected final FastLinkerLabelProvider _capellaElementLabelProvider;
  

  protected IFigure _selectedFigure;

  protected final List<ISelectionChangedListener> _selectionListeners;

  protected final SelectionDragManager _sharedDragManager;

  /**
   * Constructor.
   * @param parent_p
   */
  public FastLinkerFigureCanvas(Composite parent_p, int style) {
    super(parent_p, style);

    _figureToModelElement = new HashMap<IFigure, Collection>();
    _selectionListeners = new ArrayList<ISelectionChangedListener>();
    _doubleClickListeners = new ArrayList<IDoubleClickListener>();

    // Label provider for ModelElements displayed in the view.
    _capellaElementLabelProvider = new FastLinkerLabelProvider();

    _fastLinkerFigure = new Figure();
    setContents(_fastLinkerFigure);

    _sharedDragManager = new SelectionDragManager();
    _fastLinkerFigure.addMouseListener(_sharedDragManager);
    _fastLinkerFigure.addMouseMotionListener(_sharedDragManager);

    setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
    setScrollBarVisibility(FigureCanvas.AUTOMATIC);
  }

  public void addDoubleClickListener(IDoubleClickListener listener_p) {
    _doubleClickListeners.add(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener_p) {
    _selectionListeners.add(listener_p);
  }

  protected IFigure createConnection(IFigure sourceFigure_p, IFigure targetFigure_p, LinkStyle linkGraphicalRepresentation_p) {
    PolylineConnection connection = new PolylineConnection();
    connection.setSourceAnchor(new ChopboxAnchor(sourceFigure_p));
    connection.setTargetAnchor(new ChopboxAnchor(targetFigure_p));
    connection.setAntialias(SWT.ON);
    // Line style.
    if (LinkStyle.LINE_DASHED == linkGraphicalRepresentation_p) {
      connection.setLineStyle(SWT.LINE_CUSTOM);
      connection.setLineDash(new float[] { 4f, 4f });
    } else {
      connection.setLineStyle(SWT.LINE_SOLID);
    }
    // Arrow.
    if (LinkStyle.LINE_SOLID_WITH_EMPTY_ARROW == linkGraphicalRepresentation_p) {
      PolygonDecoration pd = new PolygonDecoration();
      pd.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
      connection.setTargetDecoration(pd);
    } else if (LinkStyle.LINE_SOLID_WITH_FILLED_ARROW == linkGraphicalRepresentation_p) {
      PolygonDecoration pd = new PolygonDecoration();
      connection.setTargetDecoration(pd);
    }
    return connection;
  }

  protected Label createModelElementFigure(Collection modelElement_p, boolean displayInBold_p) {
    Label modelElementFigure = new Label(_capellaElementLabelProvider.getText(modelElement_p), _capellaElementLabelProvider.getImage(modelElement_p));
    if (displayInBold_p) {
      // Get the BOLD font from the FontRegistry.
      Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);
      modelElementFigure.setFont(boldFont);
    }
    _figureToModelElement.put(modelElementFigure, modelElement_p);
    return modelElementFigure;
  }

  public void fillFigure(Collection firstElement_p, Collection secondElement_p, Collection pinnedElement_p, LinkInfo linkRepresentation_p) {
    _figureToModelElement.clear();
    _fastLinkerFigure.removeAll();
    _selectedFigure = null;
    fireSelectionChanged();

    _fastLinkerFigure.setLayoutManager(new FilteringGridLayout(1, false));

    IFigure secondElementFigure = null;
    if (null != secondElement_p) {
      secondElementFigure = createModelElementFigure(secondElement_p, secondElement_p == pinnedElement_p);
      _fastLinkerFigure.add(secondElementFigure, new FilteringGridData(SWT.CENTER, SWT.CENTER, true, true));
    }

    IFigure firstElementFigure = null;
    if (null != firstElement_p) {
      firstElementFigure = createModelElementFigure(firstElement_p, firstElement_p == pinnedElement_p);
      _fastLinkerFigure.add(firstElementFigure, new FilteringGridData(SWT.CENTER, SWT.CENTER, true, true));
    }

    if (null != linkRepresentation_p) {
      IFigure connection = null;
			if (firstElement_p != null && (firstElement_p.contains(linkRepresentation_p._sourceElement))
					&& secondElement_p != null && (secondElement_p
							.contains(linkRepresentation_p._targetElement))) {
				connection = createConnection(firstElementFigure,
						secondElementFigure, linkRepresentation_p._linkStyle);
			} else if (secondElement_p != null && (secondElement_p
					.contains(linkRepresentation_p._sourceElement))
					&& firstElement_p != null && (firstElement_p
							.contains(linkRepresentation_p._targetElement))) {
				connection = createConnection(secondElementFigure,
						firstElementFigure, linkRepresentation_p._linkStyle);
			}
			if (null != connection) {
				_fastLinkerFigure.add(connection);
			}
    }
  }

  protected void fireDoubleClick() {
    final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    for (final IDoubleClickListener listener : _doubleClickListeners) {
      listener.doubleClick(event);
    }
  }

  protected void fireSelectionChanged() {
    final SelectionChangedEvent event = new SelectionChangedEvent(this, getSelection());
    for (final ISelectionChangedListener listener : _selectionListeners) {
      listener.selectionChanged(event);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelection getSelection() {
    if ((null == _selectedFigure) || (null == _figureToModelElement.get(_selectedFigure))) {
      return StructuredSelection.EMPTY;
    }

    return new StructuredSelection(_figureToModelElement.get(_selectedFigure));
  }

  public void removeDoubleClickListener(IDoubleClickListener listener_p) {
    _doubleClickListeners.remove(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeSelectionChangedListener(ISelectionChangedListener listener_p) {
    _selectionListeners.remove(listener_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSelection(ISelection selection_p) {
    // Nothing to do.
  }

  protected void switchToXYLayout(IFigure figure_p) {
    figure_p.setLayoutManager(new XYLayout());
    // Add constraints.
    List<?> children = figure_p.getChildren();
    for (Object child : children) {
      IFigure childFigure = (IFigure) child;
      figure_p.setConstraint(childFigure, childFigure.getBounds());
    }

  }

}
