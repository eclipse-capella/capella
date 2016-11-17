/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    private String key;

    private Object value;

    ContextItemVariable(String key_p, Object value_p) {
      this.key = key_p;
      this.value = value_p;
    }

    /**
     * @param value_p
     *          the value to set
     */
    public void setValue(Object value_p) {
      value = value_p;
    }

    /**
     * @return the key
     */
    public String getKey() {
      return key;
    }

    /**
     * @return the value
     */
    public Object getValue() {
      return value;
    }

    @Override
    public String toString() {
      String result = value instanceof AbstractNamedElement ? ((AbstractNamedElement) value).getName() : value.toString();
      return "Var(" + key + "=" + result + ")";
    }
  }

  protected class ContextItemEnd implements ContextItem {

  }

  protected class ContextItemElement implements ContextItem {
    private String key;

    private EObject value;
    private ContextItemView ancestor;
    private ArrayList<ContextItemView> views = null;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    ContextItemElement(String key_p, EObject value_p) {
      this.key = key_p;
      this.value = value_p;

      ContextItemView initialView = new ContextItemView(this);
      addView(initialView);

      initialView.getViews().putAll(INITIAL_VIEWS, (Collection) getContent().getDiagramElements(value_p));
      initialView.getViews().putAll(VIEWS, (Collection) getContent().getDiagramElements(value_p));
    }

    ContextItemElement(String key_p, EObject value_p, ContextItemView ancestor_p) {
      this(key_p, value_p);
      this.ancestor = ancestor_p;
    }

    /**
     * @param value_p
     *          the value to set
     */
    public void setValue(EObject value_p) {
      value = value_p;
    }

    /**
     * @return the key
     */
    public ContextItemView getAncestor() {
      return ancestor;
    }

    /**
     * @return the key
     */
    public String getKey() {
      return key;
    }

    /**
     * @return the value
     */
    public EObject getValue() {
      return value;
    }

    @Override
    public String toString() {
      String result = value instanceof AbstractNamedElement ? ((AbstractNamedElement) value).getName() : value.toString();
      return "Element(" + key + "=" + result + ")";
    }

    public ArrayList<ContextItemView> getViews() {
      if (views == null) {
        views = new ArrayList<AbstractShowHide.ContextItemView>();
      }
      return views;
    }

    /**
     * @param contextItemView_p
     */
    public void addView(ContextItemView contextItemView_p) {
      getViews().add(contextItemView_p);
    }
  }

  protected class ContextItemView implements ContextItem {
    ContextItemElement element;
    HashMapSet<String, DSemanticDecorator> map;

    /**
     * @param element_p
     */
    public ContextItemView(ContextItemElement element_p) {
      super();
      element = element_p;
      element.addView(this);
      map = new HashMapSet<String, DSemanticDecorator>();
    }

    /**
     * @return
     */
    public HashMapSet<String, DSemanticDecorator> getViews() {
      return map;
    }

    /**
     * @return the element
     */
    public ContextItemElement getElement() {
      return element;
    }

    @Override
    public String toString() {
      return "View(" + element.toString() + ")";
    }

  }

  public class DiagramContext extends LinkedList<ContextItem> {
    private static final long serialVersionUID = 6026544019176965896L;

    public DiagramContext() {
      super();
    }

    public DiagramContext(DiagramContext context_p) {
      super();
      addAll(context_p);
    }

    public void setElement(String key_p, EObject value) {
      ContextItem entry = getLast(key_p);
      if (entry == null) {
        add(new ContextItemElement(key_p, value));
      } else {
        ((ContextItemElement) entry).setValue(value);
      }
    }

    public void setVariable(String key_p, Object value) {
      ContextItemVariable entry = getLastVariable(key_p);
      if (entry == null) {
        add(new ContextItemVariable(key_p, value));
      } else {
        (entry).setValue(value);
      }
    }

    public void unsetVariable(String key_p) {
      ContextItemVariable entry = getLastVariable(key_p);
      if (entry != null) {
        remove(entry);
      }
    }

    public ContextItemVariable getLastVariable(String key_p) {
      ContextItemVariable value = null;
      Iterator<? extends ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemVariable) && ((ContextItemVariable) next).getKey().equals(key_p)) {
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

    public ContextItemElement getLast(String key_p) {
      ContextItemElement value = null;
      Iterator<? extends ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemElement) && ((ContextItemElement) next).getKey().equals(key_p)) {
          value = (ContextItemElement) next;
        }
      }
      return value;
    }

    public void removeLast(String key_p) {
      ContextItem value = null;
      Iterator<ContextItem> list = this.iterator();
      while (list.hasNext()) {
        ContextItem next = list.next();
        if ((next instanceof ContextItemElement) && ((ContextItemElement) next).getKey().equals(key_p)) {
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

  public AbstractShowHide(DDiagramContents content_p) {
    this._content = content_p;
  }

  protected ContextItemView getNextContextItemView(DiagramContext context, ContextItemElement skip_p) {
    for (ContextItem obj : context) {
      if (obj instanceof ContextItemView) {
        if (((ContextItemView) obj).getElement() != skip_p) {
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
  protected DiagramContext getComputedDiagramContext(EObject semantic_p, DiagramContext context_p, boolean readOnly_p) {
    DiagramContext contextToVisit = new DiagramContext();
    DiagramContext contextToHide = new DiagramContext();

    ContextItemEnd endContext = new ContextItemEnd();

    contextToVisit.add(context_p.getLast());

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
            Collection<DSemanticDecorator> v = get(element.getValue(), context_p, null, view.getViews());
            if (!v.isEmpty()) {
              view.getViews().putAll(VIEWS, v);
            }
          }
        }

        Collection<DSemanticDecorator> v = null;
        if (readOnly_p) {
          v = itemView.getViews().get(VIEWS);

        } else if (!mustShow(element, context_p, itemView.getViews())) {
          v = itemView.getViews().get(VIEWS);

        } else if (bypassRelatedElements(element, context_p)) {
          v = itemView.getViews().get(VIEWS);

        } else {
          v = show(element.getValue(), context_p, null, itemView.getViews());
          itemView.getViews().putAll(VIEWS, v);
        }

        if (nextCouple != null) {
          nextCouple.getViews().putAll(element.getKey(), v);
        }
        if (mustHide(element, context_p)) {
          contextToHide.addFirst(couple);
        }

      } else if (couple instanceof ContextItemEnd) {
        context_p.removeLast();

      } else if (couple instanceof ContextItemElement) {
        ContextItemElement element = (ContextItemElement) couple;
        EObject semantic = element.getValue();

        contextToVisit.addFirst(endContext);

        if (bypassRelatedElements(element, context_p) && !readOnly_p) {
          contextToVisit.addFirst(new ContextItemView(element));

        } else {
          HashMapSet<String, EObject> relatedElements = getRelatedObjects(semantic, context_p);

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
            context_p.add(item);
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
   * @param context_p
   * @param semantic_p
   * @return
   */
  protected boolean bypassRelatedElements(ContextItemElement originCouple_p, DiagramContext context_p) {
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
   * @param semantic_p
   * @param context_p
   */
  public void hide(EObject semantic_p, DiagramContext context_p) {
    context_p.add(new ContextItemElement(ROOT, semantic_p));
    DiagramContext context = getComputedDiagramContext(semantic_p, context_p, true);

    // Hide all elements
    for (ContextItem couple : context) {
      ContextItem cA = couple;
      if (cA instanceof ContextItemView) {
        ContextItemView itemView = (ContextItemView) cA;
        ContextItemElement element = itemView.getElement();
        context_p.add(element);
        HashMapSet<String, DSemanticDecorator> itemViewGetViews = itemView.getViews();
        hide(get(element.getValue(), context_p, null, itemViewGetViews), context_p);
        context_p.removeLast();
      }
    }
  }

  /**
   * Hide the given semantic element
   * 
   * @param semantic_p
   * @param context_p
   */
  public Collection<DSemanticDecorator> get(EObject semantic_p, DiagramContext context_p) {
    context_p.add(new ContextItemElement(ROOT, semantic_p));
    DiagramContext context = getComputedDiagramContext(semantic_p, context_p, true);

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();

    for (ContextItem couple : context) {
      ContextItem cA = couple;
      if (cA instanceof ContextItemView) {
        ContextItemView itemView = (ContextItemView) cA;
        ContextItemElement element = itemView.getElement();
        context_p.add(element);
        result.addAll(get(element.getValue(), context_p, null, itemView.getViews()));
        context_p.removeLast();
      }
    }

    return result;
  }

  /**
   * Show the given semantic element
   * 
   * @param semantic_p
   * @param context_p
   */
  public Collection<DSemanticDecorator> showWithResult(EObject semantic_p, DiagramContext context_p) {
    ContextItemElement root = new ContextItemElement(ROOT, semantic_p);
    context_p.add(root);
    getComputedDiagramContext(semantic_p, context_p, false);

    Collection<DSemanticDecorator> elements = new ArrayList<DSemanticDecorator>();
    for (ContextItemView view : root.getViews()) {
      elements.addAll(view.getViews().get(VIEWS));
    }
    return elements;
  }

  public void show(EObject semantic_p, DiagramContext context_p) {
    showWithResult(semantic_p, context_p);
  }

  /**
   * @param collection_p
   */
  protected void hide(Collection<DSemanticDecorator> collection_p, DiagramContext context_p) {
    for (DSemanticDecorator view : collection_p) {
      DDiagramElement element = ((DDiagramElement) view);
      if (element != null) {
        if (mustHide(element, context_p)) {

          if (hideInsteadOfRemoveView(element, context_p)) {
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
   * @param element_p
   * @param context_p
   * @return
   */
  protected boolean hideInsteadOfRemoveView(DDiagramElement element_p, DiagramContext context_p) {
    return false;
  }

  /**
   * Returns whether the semantic element must be displayed context can be used to retrieve why this semantic element
   * must be shown
   * 
   * @param originCouple_p
   * @return
   */
  protected boolean mustShow(ContextItemElement originCouple_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    return true;
  }

  /**
   * Returns whether the semantic element must be hidden context can be used to retrieve why this semantic element must
   * be hidden
   * 
   * @param semantic_p
   * @param context_p
   * @return
   */
  protected boolean mustHide(ContextItemElement originCouple_p, DiagramContext context_p) {
    return true;
  }

  /**
   * Returns whether the view must be hidden context can be used to retrieve why this semantic element must be hidden By
   * default, doesn't hide node with incoming or outgoing edge
   * 
   * @param semantic_p
   * @param context_p
   * @return
   */
  protected boolean mustHide(DDiagramElement view_p, DiagramContext context_p) {

    if (view_p.getDiagramElementMapping() instanceof AbstractNodeMapping) {
      if (((EdgeTarget) view_p).getIncomingEdges().isEmpty()) {
        if (((EdgeTarget) view_p).getOutgoingEdges().isEmpty()) {
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
   * @param semantic_p
   * @param context_p
   * @param relatedViews_p
   */
  protected Collection<DSemanticDecorator> get(EObject semantic_p, DiagramContext context_p, HashMapSet<String, EObject> relatedElements,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    DiagramElementMapping mapping = getMapping(semantic_p, context_p, relatedViews_p);

    if (mapping != null) {
      if (mapping instanceof EdgeMapping) {
        Collection<DSemanticDecorator> sourceViews = relatedViews_p.get(SOURCE);
        Collection<DSemanticDecorator> targetViews = relatedViews_p.get(TARGET);
        for (DSemanticDecorator sourceView : sourceViews) {
          for (DSemanticDecorator targetView : targetViews) {
            result.addAll(getEdges(sourceView, targetView, semantic_p, getContent(), (EdgeMapping) mapping));
          }
        }
      } else if (mapping instanceof AbstractNodeMapping) {
        Collection<DSemanticDecorator> targetViews = relatedViews_p.get(CONTAINER);
        if (targetViews.size() == 0) {
          targetViews.addAll(retrieveDefaultContainer(semantic_p, context_p, targetViews));
        }
        for (DSemanticDecorator targetView : targetViews) {
          if (isValidSemanticView(targetView.getTarget(), targetView, context_p)) {
            for (DDiagramElement semanticView : getNodes(targetView, semantic_p, _content, (AbstractNodeMapping) mapping)) {
              if (isValidSemanticView(semantic_p, semanticView, context_p)) {
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
   * @param semantic_p
   * @param semanticView_p
   * @param targetView_p
   * @param context_p
   * @return
   */
  protected boolean isValidNodeView(DDiagramElement semanticView_p, DSemanticDecorator containerView_p) {
    // A bordered node mapping for a semantic element must be directly contained in the containerView.
    DiagramElementMapping mapping = semanticView_p.getDiagramElementMapping();
    if (DiagramServices.getDiagramServices().isBorderedNodeMapping(mapping)) {
      if ((containerView_p != null) && containerView_p.equals(semanticView_p.eContainer())) {
        return true;
      }
      return false;
    }
    return true;
  }

  /**
   * Returns whether the edge is a valid edge view according to both source/target views
   * 
   * @param edge_p
   * @param sourceView_p
   * @param targetView_p
   * @return
   */
  protected boolean isValidEdgeView(DEdge edge_p, DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p) {
    if (sourceView_p.equals(edge_p.getSourceNode()) && targetView_p.equals(edge_p.getTargetNode())) {
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
   * @param semantic_p
   * @param semanticView_p
   * @return
   */
  protected boolean isValidSemanticView(EObject semantic_p, DSemanticDecorator semanticView_p, DiagramContext context_p) {
    return true;
  }

  /**
   * Show the given semantic element according to views of related objects.
   * 
   * @param semantic_p
   * @param context_p
   * @param relatedViews_p
   */
  protected Collection<DSemanticDecorator> show(EObject semantic_p, DiagramContext context_p, HashMapSet<String, EObject> relatedElements,
      HashMapSet<String, DSemanticDecorator> relatedViews_p) {

    Collection<DSemanticDecorator> result = new ArrayList<DSemanticDecorator>();
    DiagramElementMapping mapping = getMapping(semantic_p, context_p, relatedViews_p);

    if (mapping != null) {
      if (mapping instanceof EdgeMapping) {
        Collection<? extends DSemanticDecorator> sourceViews = relatedViews_p.get(SOURCE);
        Collection<? extends DSemanticDecorator> targetViews = relatedViews_p.get(TARGET);
        for (DSemanticDecorator sourceView : sourceViews) {
          for (DSemanticDecorator targetView : targetViews) {
            result.addAll(showEdges(sourceView, targetView, semantic_p, getContent(), (EdgeMapping) mapping));
          }
        }
      } else if (mapping instanceof AbstractNodeMapping) {
        Collection<DSemanticDecorator> targetViews = relatedViews_p.get(CONTAINER);
        if (targetViews.isEmpty()) {
          targetViews.addAll(retrieveDefaultContainer(semantic_p, context_p, targetViews));
        }
        for (DSemanticDecorator targetView : targetViews) {
          if (isValidSemanticView(targetView.getTarget(), targetView, context_p)) {
            result.addAll(showNodes(targetView, semantic_p, getContent(), (AbstractNodeMapping) mapping));
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
   * @param semantic_p
   * @param context_p
   * @param targetViews_p
   * @return
   */
  protected Collection<DSemanticDecorator> retrieveDefaultContainer(EObject semantic_p, DiagramContext context_p, Collection<DSemanticDecorator> targetViews_p) {
    return Collections.emptyList();
  }

  /**
   * Retrieve existing nodes of the given semantic element, contained in the given containerView
   * 
   * @param targetView_p
   * @param semantic_p
   * @param content_p
   * @param mapping_p
   * @return
   */
  protected Collection<DDiagramElement> getNodes(DSemanticDecorator containerView_p, EObject semantic_p, DDiagramContents content_p, AbstractNodeMapping mapping_p) {
    Collection<DDiagramElement> edges = new LinkedList<DDiagramElement>();
    for (DDiagramElement view : content_p.getDiagramElements(semantic_p, mapping_p, containerView_p)) {
      if (isValidNodeView(view, containerView_p)) {
        edges.add(view);
      }
    }
    return edges;
  }

  /**
   * Retrieve existing edges of the given semantic element, between both source/target views
   * 
   * @param sourceView_p
   * @param targetView_p
   * @param semantic_p
   * @param content_p
   * @param mapping_p
   * @return
   */
  protected Collection<DDiagramElement> getEdges(DSemanticDecorator sourceView_p, DSemanticDecorator targetView_p, EObject semantic_p, DDiagramContents content_p,
      EdgeMapping mapping_p) {
    Collection<DDiagramElement> edges = new LinkedList<DDiagramElement>();
    for (DDiagramElement elementView : content_p.getDiagramElements(semantic_p, mapping_p)) {
      if (elementView instanceof DEdge) {
        DEdge edge = (DEdge) elementView;
        if ((sourceView_p != null) && (targetView_p != null) && (edge.getSourceNode() != null) && (edge.getTargetNode() != null)) {
          if (isValidEdgeView(edge, sourceView_p, targetView_p)) {
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
   * @param targetView_p
   * @param semantic_p
   * @param content_p
   * @param mapping_p
   * @return
   */
  protected Collection<DDiagramElement> showNodes(DSemanticDecorator containerView_p, EObject semantic_p, DDiagramContents content_p, AbstractNodeMapping mapping_p) {
    Collection<DDiagramElement> nodes = getNodes(containerView_p, semantic_p, content_p, mapping_p);
    if (nodes.isEmpty()) {
      nodes = new ArrayList<DDiagramElement>();
    }
    if (nodes.isEmpty()) {
      DDiagram diagram = content_p.getDDiagram();
      if (mustShow(containerView_p, semantic_p, mapping_p)) {
        AbstractDNode node = DiagramServices.getDiagramServices().createAbstractDNode(mapping_p, semantic_p, (DragAndDropTarget) containerView_p, diagram);
        content_p.addView(node);
        nodes.add(node);
      }
    }
    return nodes;
  }

  /**
   * Returns whether a view of given semantic element must be created in the given containerView
   * 
   * @param containerView_p
   * @param semantic_p
   * @param mapping_p
   * @return
   */
  protected boolean mustShow(DSemanticDecorator containerView_p, EObject semantic_p, AbstractNodeMapping mapping_p) {
    if ((containerView_p instanceof DDiagram) && DiagramServices.getDiagramServices().isBorderedNodeMapping(mapping_p)) {
      return false;
    }
    if ((containerView_p instanceof DDiagram) && (mapping_p.eContainer() instanceof DiagramElementMapping)) {
      return false;
    }

    return true;
  }

  /**
   * Returns whether a view of given semantic element must be created between both source/target views
   * 
   * @param source_p
   * @param target_p
   * @param exchange_p
   * @param edgeMapping_p
   * @return
   */
  protected boolean mustShow(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, EdgeMapping edgeMapping_p) {
    return true;
  }

  /**
   * Retrieve existing edges of the given semantic element, between both source/target views Create a view if not exists
   * 
   * @param source_p
   * @param target_p
   * @param exchange_p
   * @param content_p
   * @return
   */
  protected Collection<DDiagramElement> showEdges(DSemanticDecorator source_p, DSemanticDecorator target_p, EObject exchange_p, DDiagramContents content_p,
      EdgeMapping edgeMapping_p) {
    Collection<DDiagramElement> nodes = getEdges(source_p, target_p, exchange_p, content_p, edgeMapping_p);
    if (nodes.isEmpty()) {
      nodes = new ArrayList<DDiagramElement>();
    }
    if (nodes.isEmpty()) {
      if (mustShow(source_p, target_p, exchange_p, edgeMapping_p)) {
        DEdge edge = DiagramServices.getDiagramServices().createEdge(edgeMapping_p, (EdgeTarget) source_p, (EdgeTarget) target_p, exchange_p);
        content_p.addView(edge);
        nodes.add(edge);
      }
    }
    return nodes;
  }

  protected DiagramElementMapping getMapping(EObject semantic_p, DiagramContext context_p, HashMapSet<String, DSemanticDecorator> relatedViews_p) {
    return null;
  }

  protected HashMapSet<String, EObject> getRelatedObjects(EObject semantic_p, DiagramContext context_p) {
    return new HashMapSet<String, EObject>();
  }

}
