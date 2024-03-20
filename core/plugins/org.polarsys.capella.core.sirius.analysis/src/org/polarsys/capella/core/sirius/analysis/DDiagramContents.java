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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A handler to content of a diagram. This class is made to avoid multiple/low performance accesses to views from their
 * semantic target.
 * 
 * A browse of diagram contents is performed only on access. This handler is not synchronized with added/removed views,
 * so methods addView/removeView has to be called to access to views.
 * 
 */
public class DDiagramContents {

  /**
   * diagram
   */
  protected DSemanticDiagram _currentDiagram;
  
  protected DiagramDescription _currentDescription;

  /**
   * diagram elements sorted by target
   */
  protected HashMapSet<EObject, DDiagramElement> _elementsTargets = null;

  protected HashSet<DDiagramElement> _elementsToShow = null;

  protected HashSet<DDiagramElement> _elementsToHide = null;

  protected HashMap<String, DiagramElementMapping> _mappings = null;
  
  public void deferredShow(DDiagramElement element) {
    _elementsToShow.add(element);
    if (_elementsToHide.contains(element)) {
      _elementsToHide.remove(element);
    }
  }

  public void deferredHide(DDiagramElement element) {
    _elementsToHide.add(element);
    if (_elementsToShow.contains(element)) {
      _elementsToShow.remove(element);
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
    _currentDescription = _currentDiagram.getDescription();
    _elementsToShow = new HashSet<>();
    _elementsToHide = new HashSet<>();
  }

  /**
   * Create a new diagram contents from an existing diagram contents. (both diagram contents are linked by attributes,
   * don't perform a clone)
   * 
   * @param diagramContent
   */
  public DDiagramContents(DDiagramContents diagramContent) {
    this._currentDiagram = diagramContent._currentDiagram;
    this._currentDescription = diagramContent.getDescription();
    this._elementsTargets = diagramContent._elementsTargets;
    _elementsToShow = new HashSet<>();
    _elementsToHide = new HashSet<>();
  }

  public DiagramDescription getDescription() {
    return _currentDescription;
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
      _elementsTargets = new HashMapSet<>();
      for (DDiagramElement element : getDiagramElements()) {
        _elementsTargets.put(element.getTarget(), element);
      }
    }
    return _elementsTargets;
  }

  /**
   * Allows adding a view in the diagram content information (do not add the given view in the diagram)
   * 
   * @param diagramElement
   */
  public void addView(DDiagramElement diagramElement) {
    getMapDiagramElements().put(diagramElement.getTarget(), diagramElement);
  }

  /**
   * Allows adding a view in the diagram content information (do not add the given view in the diagram)
   * 
   * @param diagramElement
   */
  public void removeView(DDiagramElement diagramElement) {
    getMapDiagramElements().remove(diagramElement.getTarget(), diagramElement);
  }

  /**
   * Returns an iterable with all diagram elements
   * 
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements() {
    return DiagramServices.getDiagramServices().getDiagramElements(_currentDiagram);
  }

  /**
   * Returns an iterable with all diagram elements
   * 
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements(DiagramElementMapping mapping) {
    return DiagramServices.getDiagramServices().getDiagramElements(_currentDiagram, mapping);
  }

  public List<DDiagramElement> getVisibleDiagramElements(DiagramElementMapping mapping) {
    List<DDiagramElement> lstVisibleElements = new ArrayList<>();
    for (DDiagramElement element : DiagramServices.getDiagramServices().getDiagramElements(_currentDiagram, mapping)) {
      if (element.isVisible()) {
        lstVisibleElements.add(element);
      }
    }
    return lstVisibleElements;
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * @param containerView
   * @param mapping
   * @return
   */
  public Iterable<DDiagramElement> getDiagramElements(DDiagramElement containerView, DiagramElementMapping mapping) {
    return DiagramServices.getDiagramServices().getDiagramElements(containerView, mapping);
  }

  public Iterable<DDiagramElement> getDiagramElements(DSemanticDecorator decorator, DiagramElementMapping mapping) {
    if (decorator instanceof DDiagram) {
      return getDiagramElements(mapping);
    }
    return getDiagramElements((DDiagramElement) decorator, mapping);
  }

  public <T> Collection<T> asList(Iterable<T> iterable) {
    Collection<T> result = new ArrayList<>();
    for (T t : iterable) {
      result.add(t);
    }
    return result;
  }

  public Collection<EObject> asSemantic(Iterable<?> iterable) {
    Collection<EObject> result = new ArrayList<>();
    for (Object t : iterable) {
      if (t instanceof DSemanticDecorator) {
        DSemanticDecorator view = (DSemanticDecorator) t;
        if (!result.contains(view.getTarget())) {
          result.add(view.getTarget());
        }
      }
    }
    return result;
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * @param target
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target) {
    return getMapDiagramElements().get(target);
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * @param target
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(Collection<? extends EObject> target) {
    if (target.size() == 1) {
      return getDiagramElements(target.iterator().next());
    } else if (target.size() > 1) {
      Collection<DDiagramElement> elements = new HashSet<>();
      for (EObject tgt : target) {
        elements.addAll(getDiagramElements(tgt));
      }
    }
    return Collections.emptyList();
  }

  /**
   * Returns whether the given target has a view in the diagram
   * 
   * @param target
   * @return
   */
  public boolean containsView(EObject target) {
    return getMapDiagramElements().containsKey(target);
  }

  /**
   * Returns whether the given target has a view form the given mapping
   * 
   * @param target
   * @param mapping
   * @return
   */
  public boolean containsView(EObject target, DiagramElementMapping mapping) {
    if (!getMapDiagramElements().containsKey(target)) {
      return false;
    }
    if ((mapping == null) && getMapDiagramElements().containsKey(target)) {
      return true;
    }

    for (DDiagramElement view : getDiagramElements(target)) {
      if (mapping != null && mapping.equals(view.getDiagramElementMapping())) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * @param target
   * @param mapping
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target, DiagramElementMapping mapping) {
    if (!getMapDiagramElements().containsKey(target)) {
      return Collections.emptyList();
    }
    if ((mapping == null) && getMapDiagramElements().containsKey(target)) {
      return getMapDiagramElements().get(target);
    }

    ArrayList<DDiagramElement> result = new ArrayList<>();
    for (DDiagramElement view : getMapDiagramElements().get(target)) {
      if (mapping != null && mapping.equals(view.getDiagramElementMapping())) {
        result.add(view);
      }
    }
    return result;
  }

  /**
   * returns a collection of diagram elements with the given target
   * 
   * for a bordered node, we returns if strict containment
   * 
   * @param target
   * @param mapping
   * @param containerView
   * @return
   */
  public Collection<DDiagramElement> getDiagramElements(EObject target, DiagramElementMapping mapping,
      DSemanticDecorator containerView) {
    if (!getMapDiagramElements().containsKey(target)) {
      return Collections.emptyList();
    }

    ArrayList<DDiagramElement> result = new ArrayList<>();
    for (DDiagramElement view : getMapDiagramElements().get(target)) {
      if ((mapping == null) || mapping.equals(view.getDiagramElementMapping())) {
        if ((containerView == null) || EcoreUtil2.isContainedBy(view, containerView)) {
          result.add(view);
        }
      }
    }
    return result;
  }

  /**
   * Returns all nodes targeting the given target
   * 
   * @param target
   * @return
   */
  public Collection<AbstractDNode> getNodes(EObject target) {
    return DiagramServices.getDiagramServices().filterNodes(getDiagramElements(target));
  }

  /**
   * Returns all node containers targeting the given target
   * 
   * @param target
   * @return
   */
  public Collection<DNodeContainer> getNodeContainers(EObject target) {
    return DiagramServices.getDiagramServices().filterNodeContainers(getDiagramElements(target));
  }

  /**
   * Returns all node containers targeting the given target
   * 
   * @param target
   * @return
   */
  public Collection<DEdge> getEdges(EObject target) {
    return DiagramServices.getDiagramServices().filterEdges(getDiagramElements(target));
  }

  /**
   * Returns the first node targeting the given target
   * 
   * @param target
   * @return
   */
  @Deprecated
  public AbstractDNode getNode(EObject target) {
    Collection<AbstractDNode> elt = getNodes(target);
    if (!elt.isEmpty()) {
      return elt.iterator().next();
    }
    return null;
  }

  /**
   * Returns the first edge targeting the given target
   * 
   * @param target
   * @return
   */
  @Deprecated
  public DEdge getEdge(EObject target) {
    Collection<DEdge> elt = getEdges(target);
    if (!elt.isEmpty()) {
      return elt.iterator().next();
    }
    return null;
  }

  /**
   * Returns the container which should containing the given anElement
   * 
   * @param anElement
   * @return
   */
  public DragAndDropTarget getBestContainer(DDiagramElement anElement) {
    EObject object = anElement.getTarget();
    DragAndDropTarget result = getBestContainer(object);
    if (result == null) {
      result = (DragAndDropTarget) anElement.eContainer();
    }
    return result;
  }

  /**
   * Returns the best container of the given semantic element. (sometimes, we need to retrieve the best container for a
   * new element)
   * 
   * @param semantic
   * @return
   */
  public DragAndDropTarget getBestContainer(EObject semantic) {
    EObject object = semantic;
    LinkedList<EObject> toVisit = new LinkedList<>();
    if (object != null) {
      toVisit.addAll(getParents(object, semantic));
      while (!toVisit.isEmpty()) {
        EObject element = toVisit.removeFirst();

        if (element != null) {
          if (this.containsView(element)) {
            for (DDiagramElement view : this.getNodes(element)) {
              if (view.isVisible()) {
                return (DragAndDropTarget) view;
              }
            }
          }
          toVisit.addAll(getParents(element, semantic));
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
   * 
   * @param object
   * @param context
   * @return
   */
  public Collection<EObject> getParents(EObject object, EObject context) {
    return Collections.singleton(object.eContainer());
  }

  /**
   * Returns possible parents of the given semantic element
   * 
   * @param object
   * @param context
   * @return
   */
  public EObject getElement(EObject object, EObject context) {
    return object;
  }
  
  public Collection<DiagramElementMapping> getMappings(Collection<String> names) {
    Collection<DiagramElementMapping> result = new ArrayList<>();
    for (String name: names) {
      result.add(getMapping(name));
    }
    return result;
  }
  
  public DiagramElementMapping getMapping(String name) {
    if (_mappings == null) {
      _mappings = DiagramServices.getDiagramServices().getAllMappingsByName(getDescription());
    }
    if (_mappings.containsKey(name)) {
      return _mappings.get(name);
    }
    return null;
  }
  
  /**
   * @param edge
   * @return
   */
  public boolean isVisible(DDiagramElement edge) {
    if (_elementsToHide.contains(edge)) {
      return false;
    }
    if (_elementsToShow.contains(edge)) {
      return true;
    }
    return DiagramServices.getDiagramServices().isVisible(edge);
  }
}
