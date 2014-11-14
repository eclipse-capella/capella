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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A handler to content of a diagram.
 * This class is made to avoid multiple/low performance accesses to views from their semantic target.
 * 
 * A browse of diagram contents is performed only on access. 
 * This handler is not synchronized with added/removed views, so methods addView/removeView has to be called
 * to access to views.
 * 
 */
public class DDiagramContents {

  /**
   * diagram
   */
  protected DSemanticDiagram _currentDiagram;

  /**
   * diagram elements sorted by target
   */
  protected HashMapSet<EObject, DDiagramElement> _elementsTargets = null;

  protected HashSet<DDiagramElement> _elementsToShow = null;

  protected HashSet<DDiagramElement> _elementsToHide = null;

  public void deferredShow(DDiagramElement element_p) {
    _elementsToShow.add(element_p);
    if (_elementsToHide.contains(element_p)) {
      _elementsToHide.remove(element_p);
    }
  }

  public void deferredHide(DDiagramElement element_p) {
    _elementsToHide.add(element_p);
    if (_elementsToShow.contains(element_p)) {
      _elementsToShow.remove(element_p);
    }
  }

  public void commitDeferredActions() {
    for (DDiagramElement element : _elementsToShow) {
      CapellaServices.getService().show(element);
    }
    _elementsToShow.clear();
    for (DDiagramElement element : _elementsToHide) {
      CapellaServices.getService().hide(element);
    }
    _elementsToHide.clear();
  }

  public DDiagramContents(DDiagram diagram) {
    _currentDiagram = (DSemanticDiagram) diagram;
    _elementsToShow = new HashSet<DDiagramElement>();
    _elementsToHide = new HashSet<DDiagramElement>();

  }

  /**
   * Create a new diagram contents from an existing diagram contents.
   * (both diagram contents are linked by attributes, don't perform a clone)
   * @param diagramContent_p
   */
  public DDiagramContents(DDiagramContents diagramContent_p) {
    this._currentDiagram = diagramContent_p._currentDiagram;
    this._elementsTargets = diagramContent_p._elementsTargets;
    _elementsToShow = new HashSet<DDiagramElement>();
    _elementsToHide = new HashSet<DDiagramElement>();
  }

  /**
   * @return targeted diagram
   */
  public DDiagram getDDiagram() {
    return _currentDiagram;
  }

  /**
   * @return the map of elements
   */
  private HashMapSet<EObject, DDiagramElement> getMapDiagramElements() {
    if (_elementsTargets == null) {
      _elementsTargets = new HashMapSet<EObject, DDiagramElement>();
      for (DDiagramElement element : getDiagramElements()) {
        _elementsTargets.put(element.getTarget(), element);
      }
    }
    return _elementsTargets;
  }

  /**
   * Allows adding a view in the diagram content information
   * (do not add the given view in the diagram)
   * @param diagramElement_p
   */
  public void addView(DDiagramElement diagramElement_p) {
    getMapDiagramElements().put(diagramElement_p.getTarget(), diagramElement_p);
  }

  /**
   * Allows adding a view in the diagram content information
   * (do not add the given view in the diagram)
   * @param diagramElement_p
   */
  public void removeView(DDiagramElement diagramElement_p) {
    getMapDiagramElements().remove(diagramElement_p.getTarget(), diagramElement_p);
  }

  /**
   * Returns an iterable with all diagram elements
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements() {
    return DiagramServices.getDiagramServices().getDiagramElements(_currentDiagram);
  }

  /**
   * Returns an iterable with all diagram elements
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements(DiagramElementMapping mapping_p) {
    return DiagramServices.getDiagramServices().getDiagramElements(_currentDiagram, mapping_p);
  }

  /**
   * returns a collection of diagram elements with the given target
   * @param target_p
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement containerView_p, DiagramElementMapping mapping_p) {
    return DiagramServices.getDiagramServices().getDiagramElements(containerView_p, mapping_p);
  }

  public Iterable<DDiagramElement> getDiagramElements(DSemanticDecorator decorator_p, DiagramElementMapping mapping_p) {
    if (decorator_p instanceof DDiagram) {
      return getDiagramElements(mapping_p);
    }
    return getDiagramElements((DDiagramElement) decorator_p, mapping_p);
  }

  /**
   * returns a collection of diagram elements with the given target
   * @param target_p
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target_p) {
    return getMapDiagramElements().get(target_p);
  }

  /**
   * returns a collection of diagram elements with the given target
   * @param target_p
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(Collection<? extends EObject> target_p) {
    if (target_p.size() == 1) {
      return getDiagramElements(target_p.iterator().next());
    } else if (target_p.size() > 1) {
      Collection<DDiagramElement> elements = new HashSet<DDiagramElement>();
      for (EObject target : target_p) {
        elements.addAll(getDiagramElements(target));
      }
    }
    return Collections.emptyList();
  }

  /**
   * Returns whether the given target has a view in the diagram
   * @param target
   * @return
   */
  public boolean containsView(EObject target) {
    return getMapDiagramElements().containsKey(target);
  }

  /**
   * Returns whether the given target has a view form the given mapping
   * @param target
   * @param mapping
   * @return
   */
  public boolean containsView(EObject target, DiagramElementMapping mapping_p) {
    if (!getMapDiagramElements().containsKey(target)) {
      return false;
    }
    if ((mapping_p == null) && getMapDiagramElements().containsKey(target)) {
      return true;
    }

    for (DDiagramElement view : getDiagramElements(target)) {
      if (mapping_p.equals(view.getDiagramElementMapping())) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns a collection of diagram elements with the given target
   * @param target_p
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target_p, DiagramElementMapping mapping_p) {
    if (!getMapDiagramElements().containsKey(target_p)) {
      return Collections.emptyList();
    }
    if ((mapping_p == null) && getMapDiagramElements().containsKey(target_p)) {
      return getMapDiagramElements().get(target_p);
    }

    ArrayList<DDiagramElement> result = new ArrayList<DDiagramElement>();
    for (DDiagramElement view : getMapDiagramElements().get(target_p)) {
      if (mapping_p.equals(view.getDiagramElementMapping())) {
        result.add(view);
      }
    }
    return result;
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * for a bordered node, we returns if strict containment
   * @param target_p
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target_p, DiagramElementMapping mapping_p, DSemanticDecorator containerView_p) {
    if (!getMapDiagramElements().containsKey(target_p)) {
      return Collections.emptyList();
    }

    ArrayList<DDiagramElement> result = new ArrayList<DDiagramElement>();
    for (DDiagramElement view : getMapDiagramElements().get(target_p)) {
      if ((mapping_p == null) || mapping_p.equals(view.getDiagramElementMapping())) {
        if ((containerView_p == null) || EcoreUtil2.isContainedBy(view, containerView_p)) {
          result.add(view);
        }
      }
    }
    return result;
  }

  /**
   * Returns all nodes targeting the given target_p
   * @param target
   * @return
   */
  public Collection<AbstractDNode> getNodes(EObject target_p) {
    return DiagramServices.getDiagramServices().filterNodes(getDiagramElements(target_p));
  }

  /**
   * Returns all node containers targeting the given target_p
   * @param target
   * @return
   */
  public Collection<DNodeContainer> getNodeContainers(EObject target_p) {
    return DiagramServices.getDiagramServices().filterNodeContainers(getDiagramElements(target_p));
  }

  /**
   * Returns all node containers targeting the given target_p
   * @param target
   * @return
   */
  public Collection<DEdge> getEdges(EObject target) {
    return DiagramServices.getDiagramServices().filterEdges(getDiagramElements(target));
  }

  /**
   * Returns the first node targeting the given target_p
   * @param target
   * @return
   */
  @Deprecated
  public AbstractDNode getNode(EObject target) {
    Collection<AbstractDNode> elt = getNodes(target);
    if (elt.size() > 0) {
      return elt.iterator().next();
    }
    return null;
  }

  /**
   * Returns the first edge targeting the given target_p
   * @param target
   * @return
   */
  @Deprecated
  public DEdge getEdge(EObject target) {
    Collection<DEdge> elt = getEdges(target);
    if (elt.size() > 0) {
      return elt.iterator().next();
    }
    return null;
  }

  /**
   * Returns the container which should containing the given anElement_p
   * @param anElement_p
   * @param diagram_p
   * @param diagramContent_p
   * @return
   */
  public DragAndDropTarget getBestContainer(DDiagramElement anElement_p) {
    EObject object = anElement_p.getTarget();
    DragAndDropTarget result = getBestContainer(object);
    if (result == null) {
      result = (DragAndDropTarget) anElement_p.eContainer();
    }
    return result;
  }

  /**
   * Returns the best container of the given semantic element.
   * (sometimes, we need to retrieve the best container for a new element)
   * @param anElement_p
   * @param diagram_p
   * @param diagramContent_p
   * @return
   */
  public DragAndDropTarget getBestContainer(EObject semantic_p) {
    EObject object = semantic_p;
    LinkedList<EObject> toVisit = new LinkedList<EObject>();
    if (object != null) {
      toVisit.addAll(getParents(object, semantic_p));
      while (toVisit.size() > 0) {
        EObject element = toVisit.removeFirst();

        if (element != null) {
          if (this.containsView(element)) {
            for (DDiagramElement view : this.getNodeContainers(element)) {
              return (DragAndDropTarget) view;
            }
          }
          toVisit.addAll(getParents(element, semantic_p));
        }

      }
    }

    DDiagram diagram = this.getDDiagram();
    if (diagram instanceof DSemanticDiagram) {
      return diagram;
    }
    return null;
  }

  /**
   * Returns possible parents of the given semantic element
   * @param object_p
   * @return
   */
  public Collection<EObject> getParents(EObject object_p, EObject context_p) {
    return Collections.singleton(object_p.eContainer());
  }

  /**
   * Returns possible parents of the given semantic element
   * @param object_p
   * @return
   */
  public EObject getElement(EObject object_p, EObject context_p) {
    return object_p;
  }

  /**
   * @param edge_p
   * @return
   */
  public boolean isVisible(DDiagramElement edge_p) {
    if (_elementsToHide.contains(edge_p)) {
      return false;
    }
    if (_elementsToShow.contains(edge_p)) {
      return true;
    }
    return DiagramServices.getDiagramServices().isVisible(edge_p);
  }

}
