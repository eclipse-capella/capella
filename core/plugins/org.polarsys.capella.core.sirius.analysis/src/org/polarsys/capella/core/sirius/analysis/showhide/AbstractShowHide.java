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
package org.polarsys.capella.core.sirius.analysis.showhide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.tool.HashMapSet;

/**
 * A service to perform show/hide on diagrams An element requires some relatedObject to be shown before being itself
 * shown.<br>
 * With this principle, this service tries to help writing complex showHide. For instance: <br>
 * - a FunctionalExchange to be shown requires that its FunctionPort must be shown <br>
 * - a FunctionPort to be shown requires that its containing Function must be shown <br>
 * - a Function to be shown requires that its containing Function must be shown.<br>
 * The tool will show relatedObjects, starting from the first object without relatedObject<br>
 * - Define which related objects<br>
 * - Define which mapping must be used for an object<br>
 * - Define if an element must be shown/hidden<br>
 * All you need to do is to implement: <br>
 * - getMapping <br>
 * - retrieveDefaultContainer <br>
 * - getRelatedObjects: to show/hide a DNode put the container object (often the diagram) using the key 'CONTAINER' in
 * the returned HashMapSet<String, EObject>. To show/hide a DEdge, put the source and/or target objects using the keys
 * 'SOURCE' and 'TARGET'<br>
 */
public class AbstractShowHide implements IShowHide {

  public static final String SOURCE = "source"; //$NON-NLS-1$
  public static final String TARGET = "target"; //$NON-NLS-1$

  public static final String CONTAINER = "container"; //$NON-NLS-1$
  public static final String ROOT = "root"; //$NON-NLS-1$
  public static final String VIEWS = "views"; //$NON-NLS-1$
  public static final String INITIAL_VIEWS = "initial"; //$NON-NLS-1$

  protected DDiagramContents _content;

  protected DDiagramContents getContent() {
    return _content;
  }

  protected interface ContextItem {
    // Nothing here
  }

  protected class ContextItemVariable implements ContextItem {
    private String _key;
    private Object _value;

    ContextItemVariable(String key, Object value) {
      _key = key;
      _value = value;
    }

    /**
     * @param value
     *          the value to set
     */
    public void setValue(Object value) {
      _value = value;
    }

    /**
     * @return the key
     */
    public String getKey() {
      return _key;
    }

    /**
     * @return the value
     */
    public Object getValue() {
      return _value;
    }

    @Override
    public String toString() {
      String result = _value instanceof AbstractNamedElement ? ((AbstractNamedElement) _value).getName() : _value.toString();
      return "Var(" + _key + "=" + result + ")";
    }
  }

  protected class ContextItemEnd implements ContextItem {

  }

  protected class ContextItemElement implements ContextItem {
    private String _key;
    private EObject _value;
    private ContextItemView _ancestor;
    private ArrayList<ContextItemView> _views = null;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    ContextItemElement(String key, EObject value) {
      this._key = key;
      this._value = value;

      ContextItemView initialView = new ContextItemView(this);
      addView(initialView);

      initialView.getViews().putAll(INITIAL_VIEWS, (Collection) getContent().getDiagramElements(value));
      initialView.getViews().putAll(VIEWS, (Collection) getContent().getDiagramElements(value));
    }

    ContextItemElement(String key, EObject value, ContextItemView ancestor) {
      this(key, value);
      this._ancestor = ancestor;
    }

    /**
     * @param value
     *          the value to set
     */
    public void setValue(EObject value) {
      _value = value;
    }

    /**
     * @return the key
     */
    public ContextItemView getAncestor() {
      return _ancestor;
    }

    /**
     * @return the key
     */
    public String getKey() {
      return _key;
    }

    /**
     * @return the value
     */
    public EObject getValue() {
      return _value;
    }

    @Override
    public String toString() {
      String result = _value instanceof AbstractNamedElement ? ((AbstractNamedElement) _value).getName() : _value.toString();
      return "Element(" + _key + "=" + result + ")";
    }

    public ArrayList<ContextItemView> getViews() {
      if (_views == null) {
        _views = new ArrayList<AbstractShowHide.ContextItemView>();
      }
      return _views;
    }

    /**
     * @param contextItemView
     */
    public void addView(ContextItemView contextItemView) {
      getViews().add(contextItemView);
    }
  }

  protected class ContextItemView implements ContextItem {
    ContextItemElement _element;
    HashMapSet<String, DSemanticDecorator> _map;

    /**
     * @param element
     */
    public ContextItemView(ContextItemElement element) {
      super();
      _element = element;
      _element.addView(this);
      _map = new HashMapSet<String, DSemanticDecorator>();
    }

    /**
     * @return
     */
    public HashMapSet<String, DSemanticDecorator> getViews() {
      return _map;
    }

    /**
     * @return the element
     */
    public ContextItemElement getElement() {
      return _element;
    }

    @Override
    public String toString() {
      return "View(" + _element.toString() + ")";
    }

  }

  public class DiagramContext extends LinkedList<ContextItem> {
    private static final long serialVersionUID = 6026544019176965896L;

    public DiagramContext() {
      super();
    }

    public DiagramContext(DiagramContext context) {
      super();
      addAll(context);
    }

    public void setElement(String key, EObject value) {
      ContextItem entry = getLast(key);
      if (entry == null) {
        add(new ContextItemElement(key, value));
      } else {
        ((ContextItemElement) entry).setValue(value);
      }
    }

    public void setVariable(String key, Object value) {
      ContextItemVariable entry = getLastVariable(key);
      if (entry == null) {
        add(new ContextItemVariable(key, value));
      } else {
        (entry).setValue(value);
      }
    }

    public void unsetVariable(String key) {
      ContextItemVariable entry = getLastVariable(key);
      if (entry != null) {
        remove(entry);
      }
    }

    public ContextItemVariable getLastVariable(String key) {
      ContextItemVariable value = null;
      Iterator<? extends ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemVariable) && ((ContextItemVariable) next).getKey().equals(key)) {
          value = (ContextItemVariable) next;
        }
      }
      return value;
    }

    @Override
    public ContextItemElement getLast() {
      ContextItemElement value = null;
      Iterator<? extends ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if (next instanceof ContextItemElement) {
          value = (ContextItemElement) next;
        }
      }
      return value;
    }

    public ContextItemElement getLast(String key) {
      ContextItemElement value = null;
      Iterator<? extends ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemElement) && ((ContextItemElement) next).getKey().equals(key)) {
          value = (ContextItemElement) next;
        }
      }
      return value;
    }

    public void removeLast(String key) {
      ContextItem value = null;
      Iterator<ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemElement) && ((ContextItemElement) next).getKey().equals(key)) {
          value = next;
        }
      }
      remove(value);
    }

    @Override
    public String toString() {
      String result = "";
      Iterator<ContextItem> items = iterator();
      while (items.hasNext()) {
        ContextItem item = items.next();
        result += item.toString() + ";";
      }
      return result;
    }

  }

  public AbstractShowHide(DDiagramContents content) {
    this._content = content;
  }

  protected ContextItemView getNextContextItemView(DiagramContext context, ContextItemElement skip) {
    for (ContextItem obj : context) {
      if (obj instanceof ContextItemView) {
        if (((ContextItemView) obj).getElement() != skip) {
          return (ContextItemView) obj;
        }
      }
    }
    return null;
  }

  /**
   * For an element, retrieve all related objects with access to all views of related objects Returns an ordered list of
   * ContextItemView
   */
  protected DiagramContext getComputedDiagramContext(EObject semantic, DiagramContext context, boolean readOnly) {
    DiagramContext contextToVisit = new DiagramContext();
    DiagramContext contextToHide = new DiagramContext();

    ContextItemEnd endContext = new ContextItemEnd();

    contextToVisit.add(context.getLast());

    int i = 0;
    int maximalDepth = getMaximalDepth();

    while ((contextToVisit.size() > 0) && (i < maximalDepth)) {
      i++;
      ContextItem couple = contextToVisit.removeFirst();

      if (couple instanceof ContextItemView) {
        ContextItemView itemView = (ContextItemView) couple;
        ContextItemElement element = itemView.getElement();
        ContextItemView nextCouple = element.getAncestor();

        // Retrieve existing views for all ContextItemView of the given element
        for (ContextItemView view : element.getViews()) {
          if (view.getViews().get(VIEWS).isEmpty()) {
            Collection<DSemanticDecorator> v = get(element.getValue(), context, null, view.getViews());
            if (!v.isEmpty()) {
              view.getViews().putAll(VIEWS, v);
            }
          }
        }

        Collection<DSemanticDecorator> v = null;
        if (readOnly) {
          v = itemView.getViews().get(VIEWS);

        } else if (!mustShow(element, context, itemView.getViews())) {
          v = itemView.getViews().get(VIEWS);

        } else if (bypassRelatedElements(element, context)) {
          v = itemView.getViews().get(VIEWS);

        } else {
          v = show(element.getValue(), context, null, itemView.getViews());
          itemView.getViews().putAll(VIEWS, v);
        }

        if (nextCouple != null) {
          nextCouple.getViews().putAll(element.getKey(), v);
        }
        if (mustHide(element, context)) {
          contextToHide.addFirst(couple);
        }

      } else if (couple instanceof ContextItemEnd) {
        context.removeLast();

      } else if (couple instanceof ContextItemElement) {
        ContextItemElement element = (ContextItemElement) couple;
        EObject semanticElement = element.getValue();

        contextToVisit.addFirst(endContext);

        if (bypassRelatedElements(element, context) && !readOnly) {
          contextToVisit.addFirst(new ContextItemView(element));

        } else {
          HashMapSet<String, EObject> relatedElements = getRelatedObjects(semanticElement, context);

          ContextItemView lastView = null;
          Set<String> keys = relatedElements.keySet();
          if (keys.size() == 0) {
            contextToVisit.addFirst(new ContextItemView(element));
          }
          ArrayList<ContextItem> items = new ArrayList<AbstractShowHide.ContextItem>();

          if (keys.size() == 1) {
            String key = keys.iterator().next();
            for (EObject related : relatedElements.get(key)) {
              lastView = new ContextItemView(element);
              contextToVisit.addFirst(lastView);
              ContextItemElement relatedCouple = new ContextItemElement(key, related, lastView);
              items.add(relatedCouple);
            }

          } else if (keys.size() == 2) {
            String key = keys.iterator().next();
            for (EObject related : relatedElements.get(key)) {
              Iterator<String> a = keys.iterator();
              a.next();
              String key2 = a.next();
              for (EObject related2 : relatedElements.get(key2)) {
                lastView = new ContextItemView(element);
                contextToVisit.addFirst(lastView);

                ContextItemElement relatedCouple = new ContextItemElement(key, related, lastView);
                ContextItemElement relatedCouple2 = new ContextItemElement(key2, related2, lastView);
                items.add(relatedCouple);
                items.add(relatedCouple2);
              }
            }
          }

          for (ContextItem item : items) {
            context.add(item);
            contextToVisit.addFirst(item);
          }

        }
      }

    }

    return contextToHide;
  }

  /**
   * Bypass related elements can be useful to avoid to create views of related elements for any reason (for instance, if
   * the semantic element is already displayed, we don't want to create views of related elements )
   * 
   * @param context
   * @param semantic
   * @return
   */
  protected boolean bypassRelatedElements(ContextItemElement originCouple, DiagramContext context) {
    return false;
  }

  /**
   * Maximal depth in a diagram. (avoid any loop)
   */
  protected int getMaximalDepth() {
    return 500;
  }

  /**
   * Hide the given semantic element
   * 
   * @param semantic
   * @param context
   */
  public void hide(EObject semantic, DiagramContext context) {
    context.add(new ContextItemElement(ROOT, semantic));
    DiagramContext ctx = getComputedDiagramContext(semantic, context, true);

    // Hide all elements
    for (ContextItem couple : ctx) {
      ContextItem cA = couple;
      if (cA instanceof ContextItemView) {
        ContextItemView itemView = (ContextItemView) cA;
        ContextItemElement element = itemView.getElement();
        context.add(element);
        HashMapSet<String, DSemanticDecorator> itemViewGetViews = itemView.getViews();
        hide(get(element.getValue(), context, null, itemViewGetViews), context);
        context.removeLast();
      }
    }
  }

  /**
   * Hide the given semantic element
   * 
   * @param semantic
   * @param context
   */
  public Collection<DSemanticDecorator> get(EObject semantic, DiagramContext context) {
    context.add(new ContextItemElement(ROOT, semantic));
    DiagramContext ctx = getComputedDiagramContext(semantic, context, true);

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();

    for (ContextItem couple : ctx) {
      ContextItem cA = couple;
      if (cA instanceof ContextItemView) {
        ContextItemView itemView = (ContextItemView) cA;
        ContextItemElement element = itemView.getElement();
        context.add(element);
        result.addAll(get(element.getValue(), context, null, itemView.getViews()));
        context.removeLast();
      }
    }

    return result;
  }

  /**
   * Show the given semantic element
   * 
   * @param semantic
   * @param context
   */
  public Collection<DSemanticDecorator> showWithResult(EObject semantic, DiagramContext context) {
    ContextItemElement root = new ContextItemElement(ROOT, semantic);
    context.add(root);
    getComputedDiagramContext(semantic, context, false);

    Collection<DSemanticDecorator> elements = new ArrayList<DSemanticDecorator>();
    for (ContextItemView view : root.getViews()) {
      elements.addAll(view.getViews().get(VIEWS));
    }
    return elements;
  }

  public void show(EObject semantic, DiagramContext context) {
    showWithResult(semantic, context);
  }

  /**
   * @param collection
   */
  protected void hide(Collection<DSemanticDecorator> collection, DiagramContext context) {
    for (DSemanticDecorator view : collection) {
      DDiagramElement element = ((DDiagramElement) view);
      if (element != null) {
        if (mustHide(element, context)) {

          if (hideInsteadOfRemoveView(element, context)) {
            getContent().deferredHide(element);

          } else {
            DiagramElementMapping diagramElementMapping = element.getDiagramElementMapping();
            if (diagramElementMapping instanceof EdgeMapping) {
              DiagramServices.getDiagramServices().removeEdgeView((DEdge) element);
            } else if (diagramElementMapping instanceof AbstractNodeMapping) {
              DiagramServices.getDiagramServices().removeAbstractDNodeView((AbstractDNode) element);
            }
          }
        }
      }
    }
  }

  /**
   * @param element
   * @param context
   * @return
   */
  protected boolean hideInsteadOfRemoveView(DDiagramElement element, DiagramContext context) {
    return false;
  }

  /**
   * Returns whether the semantic element must be displayed context can be used to retrieve why this semantic element
   * must be shown
   * 
   * @param originCouple
   * @return
   */
  protected boolean mustShow(ContextItemElement originCouple, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {
    return true;
  }

  /**
   * Returns whether the semantic element must be hidden context can be used to retrieve why this semantic element must
   * be hidden
   * 
   * @param semantic
   * @param context
   * @return
   */
  protected boolean mustHide(ContextItemElement originCouple, DiagramContext context) {
    return true;
  }

  /**
   * Returns whether the view must be hidden context can be used to retrieve why this semantic element must be hidden By
   * default, doesn't hide node with incoming or outgoing edge
   * 
   * @param semantic
   * @param context
   * @return
   */
  protected boolean mustHide(DDiagramElement view, DiagramContext context) {

    if (view.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      if (((EdgeTarget) view).getIncomingEdges().isEmpty()) {
        if (((EdgeTarget) view).getOutgoingEdges().isEmpty()) {
          return true;
        }
      }

      return false;
    }
    return true;
  }

  /**
   * Retrieve views of the given semantic element according to views of related objects.
   * 
   * @param semantic
   * @param context
   * @param relatedViews
   */
  protected Collection<DSemanticDecorator> get(EObject semantic, DiagramContext context, HashMapSet<String, EObject> relatedElements,
      HashMapSet<String, DSemanticDecorator> relatedViews) {

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    DiagramElementMapping mapping = getMapping(semantic, context, relatedViews);

    if (mapping != null) {
      if (mapping instanceof EdgeMapping) {
        Collection<DSemanticDecorator> sourceViews = relatedViews.get(SOURCE);
        Collection<DSemanticDecorator> targetViews = relatedViews.get(TARGET);
        for (DSemanticDecorator sourceView : sourceViews) {
          for (DSemanticDecorator targetView : targetViews) {
            result.addAll(getEdges(sourceView, targetView, semantic, getContent(), (EdgeMapping) mapping));
          }
        }
      } else if (mapping instanceof AbstractNodeMapping) {
        Collection<DSemanticDecorator> targetViews = relatedViews.get(CONTAINER);
        if (targetViews.size() == 0) {
          targetViews.addAll(retrieveDefaultContainer(semantic, context, targetViews));
        }
        for (DSemanticDecorator targetView : targetViews) {
          if (isValidSemanticView(targetView.getTarget(), targetView, context)) {
            for (DDiagramElement semanticView : getNodes(targetView, semantic, _content, (AbstractNodeMapping) mapping)) {
              if (isValidSemanticView(semantic, semanticView, context)) {
                result.add(semanticView);
              }
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns whether the semanticView is valid according to its container view
   * 
   * @param semantic
   * @param semanticView
   * @param targetView
   * @param context
   * @return
   */
  protected boolean isValidNodeView(DDiagramElement semanticView, DSemanticDecorator containerView) {
    // A bordered node mapping for a semantic element must be directly contained in the containerView.
    DiagramElementMapping mapping = semanticView.getDiagramElementMapping();
    if (DiagramServices.getDiagramServices().isBorderedNodeMapping(mapping)) {
      if ((containerView != null) && containerView.equals(semanticView.eContainer())) {
        return true;
      }
      return false;
    }
    return true;
  }

  /**
   * Returns whether the edge is a valid edge view according to both source/target views
   * 
   * @param edge
   * @param sourceView
   * @param targetView
   * @return
   */
  protected boolean isValidEdgeView(DEdge edge, DSemanticDecorator sourceView, DSemanticDecorator targetView) {
    if (sourceView.equals(edge.getSourceNode()) && targetView.equals(edge.getTargetNode())) {
      return true;
    }
    return false;
  }

  /**
   * Returns whether the given view is a valid view for the show/hide or must be ignored
   * 
   * When a Function is displayed two times in a diagram, tool Show/Hide Functional Exchange on such function will
   * display two exchanges starting from both view of the Function. This method is used to filter views of the Function
   * to display only one exchange starting from selected view
   * 
   * @param semantic
   * @param semanticView
   * @return
   */
  protected boolean isValidSemanticView(EObject semantic, DSemanticDecorator semanticView, DiagramContext context) {
    return true;
  }

  /**
   * Show the given semantic element according to views of related objects.
   * 
   * @param semantic
   * @param context
   * @param relatedViews
   */
  protected Collection<DSemanticDecorator> show(EObject semantic, DiagramContext context, HashMapSet<String, EObject> relatedElements,
      HashMapSet<String, DSemanticDecorator> relatedViews) {

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    DiagramElementMapping mapping = getMapping(semantic, context, relatedViews);

    if (mapping != null) {
      if (mapping instanceof EdgeMapping) {
        Collection<? extends DSemanticDecorator> sourceViews = relatedViews.get(SOURCE);
        Collection<? extends DSemanticDecorator> targetViews = relatedViews.get(TARGET);
        for (DSemanticDecorator sourceView : sourceViews) {
          for (DSemanticDecorator targetView : targetViews) {
            result.addAll(showEdges(sourceView, targetView, semantic, getContent(), (EdgeMapping) mapping));
          }
        }
      } else if (mapping instanceof AbstractNodeMapping) {
        Collection<DSemanticDecorator> targetViews = relatedViews.get(CONTAINER);
        if (targetViews.isEmpty()) {
          targetViews.addAll(retrieveDefaultContainer(semantic, context, targetViews));
        }
        for (DSemanticDecorator targetView : targetViews) {
          if (isValidSemanticView(targetView.getTarget(), targetView, context)) {
            result.addAll(showNodes(targetView, semantic, getContent(), (AbstractNodeMapping) mapping));
          }
        }
      }
    }

    return result;
  }

  /**
   * For a semantic element, if no "container" has been found in relatedObjects, returns the defaults containerViews
   * used to create view of semantic elements
   * 
   * @param semantic
   * @param context
   * @param targetViews
   * @return
   */
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic, DiagramContext context, Collection<DSemanticDecorator> targetViews) {
    return Collections.emptyList();
  }

  /**
   * Retrieve existing nodes of the given semantic element, contained in the given containerView
   * 
   * @param targetView
   * @param semantic
   * @param content
   * @param mapping
   * @return
   */
  protected Collection<DDiagramElement> getNodes(DSemanticDecorator containerView, EObject semantic, DDiagramContents content, AbstractNodeMapping mapping) {
    Collection<DDiagramElement> edges = new LinkedList<DDiagramElement>();
    for (DDiagramElement view : content.getDiagramElements(semantic, mapping, containerView)) {
      if (isValidNodeView(view, containerView)) {
        edges.add(view);
      }
    }
    return edges;
  }

  /**
   * Retrieve existing edges of the given semantic element, between both source/target views
   * 
   * @param sourceView
   * @param targetView
   * @param semantic
   * @param content
   * @param mapping
   * @return
   */
  protected Collection<DDiagramElement> getEdges(DSemanticDecorator sourceView, DSemanticDecorator targetView, EObject semantic, DDiagramContents content,
      EdgeMapping mapping) {
    Collection<DDiagramElement> edges = new LinkedList<DDiagramElement>();
    for (DDiagramElement elementView : content.getDiagramElements(semantic, mapping)) {
      if (elementView instanceof DEdge) {
        DEdge edge = (DEdge) elementView;
        if ((sourceView != null) && (targetView != null) && (edge.getSourceNode() != null) && (edge.getTargetNode() != null)) {
          if (isValidEdgeView(edge, sourceView, targetView)) {
            edges.add(edge);
          }
        }
      }
    }
    return edges;
  }

  /**
   * Retrieve existing nodes of the given semantic element, contained in the given containerView and create a node if
   * not yet existing
   * 
   * @param targetView
   * @param semantic
   * @param content
   * @param mapping
   * @return
   */
  protected Collection<DDiagramElement> showNodes(DSemanticDecorator containerView, EObject semantic, DDiagramContents content, AbstractNodeMapping mapping) {
    Collection<DDiagramElement> nodes = getNodes(containerView, semantic, content, mapping);
    if (nodes.isEmpty()) {
      nodes = new ArrayList<DDiagramElement>();
    }
    if (nodes.isEmpty()) {
      DDiagram diagram = content.getDDiagram();
      if (mustShow(containerView, semantic, mapping)) {
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(mapping, semantic, (DragAndDropTarget) containerView, diagram);
        content.addView(node);
        nodes.add(node);
      }
    }
    return nodes;
  }

  /**
   * Returns whether a view of given semantic element must be created in the given containerView
   * 
   * @param containerView
   * @param semantic
   * @param mapping
   * @return
   */
  protected boolean mustShow(DSemanticDecorator containerView, EObject semantic, AbstractNodeMapping mapping) {
    if ((containerView instanceof DDiagram) && DiagramServices.getDiagramServices().isBorderedNodeMapping(mapping)) {
      return false;
    }
    if ((containerView instanceof DDiagram) && (mapping.eContainer() instanceof DiagramElementMapping)) {
      return false;
    }

    // Call mapping precondition to know if the Node must be shown.
    return DiagramServices.getDiagramServices().evaluateNodePrecondition(mapping, getContent().getDDiagram(), containerView, semantic);
  }

  /**
   * Returns whether a view of given semantic element must be created between both source/target views
   * 
   * @param source
   * @param target
   * @param exchange
   * @param edgeMapping
   * @return
   */
  protected boolean mustShow(DSemanticDecorator source, DSemanticDecorator target, EObject exchange, EdgeMapping edgeMapping) {
    // Call mapping precondition to know if the Edge must be shown.
    return DiagramServices.getDiagramServices().evaluateEdgePrecondition(edgeMapping, getContent().getDDiagram(), exchange, source, target);
  }

  /**
   * Retrieve existing edges of the given semantic element, between both source/target views Create a view if not exists
   * 
   * @param source
   * @param target
   * @param exchange
   * @param content
   * @return
   */
  protected Collection<DDiagramElement> showEdges(DSemanticDecorator source, DSemanticDecorator target, EObject exchange, DDiagramContents content,
      EdgeMapping edgeMapping) {
    Collection<DDiagramElement> nodes = getEdges(source, target, exchange, content, edgeMapping);
    if (nodes.isEmpty()) {
      nodes = new ArrayList<DDiagramElement>();
    }
    if (nodes.isEmpty()) {
      if (mustShow(source, target, exchange, edgeMapping)) {
        DEdge edge = DiagramServices.getDiagramServices().createEdge(edgeMapping, (EdgeTarget) source, (EdgeTarget) target, exchange);
        content.addView(edge);
        nodes.add(edge);
      }
    }
    return nodes;
  }

  protected DiagramElementMapping getMapping(EObject semantic, DiagramContext context, HashMapSet<String, DSemanticDecorator> relatedViews) {
    return null;
  }

  protected HashMapSet<String, EObject> getRelatedObjects(EObject semantic, DiagramContext context) {
    return new HashMapSet<String, EObject>();
  }
}
