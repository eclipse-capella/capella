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
package org.polarsys.capella.common.re.handlers.replicable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.CompliancyDefinition;
import org.polarsys.capella.common.re.CompliancyDefinitionPkg;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.queries.CatalogElement_AllUsedElementLinks;
import org.polarsys.capella.common.re.queries.CatalogElement_AllUsedElements;
import org.polarsys.capella.common.re.queries.CatalogElement_UsedElementLinks;
import org.polarsys.capella.common.re.queries.CatalogElement_UsedElements;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandler implements IReplicableElementHandler {

  public static final String SOURCE = "REH_SOURCE";

  public static final String TARGET = "REH_TARGET";

  public static final String INITIAL_SOURCE = "REH_INITIAL_SOURCE";

  public static final String INITIAL_TARGET = "REH_INITIAL_TARGET";

  public static final String LINKS = "LINKS";

  public CatalogElement getSource(IContext context_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE));
  }

  public CatalogElement getTarget(IContext context_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET));
  }

  public void setSource(IContext context_p, CatalogElement element_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    context.setCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE), element_p);
  }

  public void setTarget(IContext context_p, CatalogElement element_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    context.setCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET), element_p);
  }

  public LinkedList<CatalogElement> getListSourcesVisited(IContext context_p) {
    if (!context_p.exists("getListSourcesVisited")) {
      context_p.put("getListSourcesVisited", new LinkedList<CatalogElement>());
    }
    return (LinkedList<CatalogElement>) context_p.get("getListSourcesVisited");
  }

  public LinkedList<CatalogElement> getListSources(IContext context_p) {
    if (!context_p.exists("getListSources")) {
      context_p.put("getListSources", new LinkedList<CatalogElement>());
    }
    return (LinkedList<CatalogElement>) context_p.get("getListSources");
  }

  public CatalogElement getInitialSource(IContext context_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
  }

  public CatalogElement getInitialTarget(IContext context_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
  }

  public void setInitialSource(IContext context_p, CatalogElement element_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    context.setCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE), element_p);
  }

  public void setInitialTarget(IContext context_p, CatalogElement element_p) {
    IPropertyContext context =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
            (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
    context.setCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET), element_p);
  }

  public Collection<CatalogElementLink> getLinks(IContext context_p) {
    if (!context_p.exists(LINKS)) {
      context_p.put(LINKS, new ArrayList<CatalogElementLink>());
    }
    return (Collection<CatalogElementLink>) context_p.get(LINKS);
  }

  public CatalogElementPkg getRootPackage(EObject object_p) {
    //TODO SHARED
    return ReFactory.eINSTANCE.createCatalogElementPkg();
  }

  /** Replicable elements helpers */

  /**
   * Returns elements used through owned CatalogElementLinks of the given object_p
   */
  public Collection<EObject> getElements(CatalogElement object_p) {
    return getElements(object_p, new QueryContext());
  }

  /**
   * Returns elements used through owned CatalogElementLinks of the given object_p
   */
  public Collection<EObject> getElements(CatalogElement object_p, IQueryContext context_p) {
    return QueryInterpretor.executeQuery(CatalogElement_UsedElements.class.getSimpleName(), object_p, context_p);
  }

  /**
   * Returns owned non-null-target CatalogElementLinks of the given object_p
   */
  public Collection<CatalogElementLink> getElementsLinks(CatalogElement object_p) {
    return getElementsLinks(object_p, new QueryContext());
  }

  /**
   * Returns owned non-null-target CatalogElementLinks of the given object_p
   */
  public Collection<CatalogElementLink> getElementsLinks(CatalogElement object_p, IQueryContext context_p) {
    return QueryInterpretor.executeQuery(CatalogElement_UsedElementLinks.class.getSimpleName(), object_p, context_p);
  }

  public Collection<CatalogElementLink> getAllElementsLinks(CatalogElement object_p) {
    return getAllElementsLinks(object_p, new QueryContext());
  }

  public Collection<CatalogElementLink> getAllElementsLinks(CatalogElement object_p, IQueryContext context_p) {
    return QueryInterpretor.executeQuery(CatalogElement_AllUsedElementLinks.class.getSimpleName(), object_p, context_p);
  }

  public Collection<EObject> getAllElements(CatalogElement object_p) {
    return getAllElements(object_p, new QueryContext());
  }

  public Collection<EObject> getAllElements(CatalogElement object_p, IQueryContext context_p) {
    return QueryInterpretor.executeQuery(CatalogElement_AllUsedElements.class.getSimpleName(), object_p, context_p);
  }

  public boolean hasOrigin(CatalogElement element_p) {
    return element_p.getOrigin() != null;
  }

  public boolean isReplica(CatalogElement source_p, CatalogElement supposedReplicad_p) {
    return (supposedReplicad_p.getOrigin() != null) && supposedReplicad_p.getOrigin().equals(source_p);
  }

  public CatalogElement createReplicableElement() {
    CatalogElement element = ReFactory.eINSTANCE.createCatalogElement();
    element.setKind(CatalogElementKind.REC);
    return element;
  }

  public CatalogElement createReplica() {
    CatalogElement element = ReFactory.eINSTANCE.createCatalogElement();
    element.setKind(CatalogElementKind.RPL);
    return element;
  }

  public CatalogElementLink addLink(IContext context_p, ITraceabilityHandler handler_p, CatalogElement element_p, EObject value_p, EObject oppositeValue_p) {

    for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context_p).getAllElementsLinks(element_p)) {
      if (value_p.equals(link.getTarget())) {
        return null;
      }
      if ((oppositeValue_p != null) && oppositeValue_p.equals(link.getTarget())) {
        if (ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.VIRTUAL_LINKS, link, context_p)) {
          link.setTarget(value_p);
          link.setSource(element_p);
          ContextScopeHandlerHelper.getInstance(context_p).remove(IReConstants.VIRTUAL_LINKS, link, context_p);
        }
        return link;
      }
    }

    CatalogElementLink link = ReFactory.eINSTANCE.createCatalogElementLink();
    link.setSource(element_p);
    link.setTarget(value_p);
    handler_p.attachTraceability(link, value_p, context_p);
    getLinks(context_p).add(link);
    element_p.getOwnedLinks().add(link);
    return link;
  }

  public boolean isIndirectlyReferencedBy(EObject element_p, CatalogElement re_p, IContext context_p) {
    LinkedList<CatalogElement> elements = new LinkedList<CatalogElement>();
    LinkedList<CatalogElement> visited = new LinkedList<CatalogElement>();

    elements.addAll(getIndirectlyReplicableElements(context_p, (Collection) Collections.singleton(element_p)));

    while (!(elements.isEmpty())) {
      CatalogElement element = elements.removeFirst();

      if (!visited.contains(element)) {
        visited.add(element);

        if ((element != null) && element.equals(re_p)) {
          return true;
        }
        elements.addAll(getLinkingReplicableElements(context_p, (Collection) Collections.singleton(element)));
      }
    }

    return false;
  }

  public Collection<CatalogElement> getSelectedReplicableElements(IContext context_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    for (Object item : selection) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getIndirectlySelectedReplicableElements(IContext context_p) {

    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    return getIndirectlyReplicableElements(context_p, selection);
  }

  public Collection<CatalogElement> getUppestReplicableElement(IContext context_p, Collection<Object> sources_p) {
    Collection<CatalogElement> result = new ArrayList<CatalogElement>();
    for (Object item : sources_p) {
      if (item instanceof CatalogElement) {
        result.add((CatalogElement) item);
      }
    }
    result.removeAll(getLinkingReplicableElements(context_p, sources_p));
    return result;
  }

  public Collection<CatalogElement> getIndirectlyReplicableElementsForCommand(IContext context_p, Collection<Object> sources_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    for (Object item : sources_p) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }

    for (Object item : sources_p) {
      if (!(item instanceof CatalogElement)) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink) {
            if (((CatalogElementLink) link).getSource() != null) {
              elements.add(((CatalogElementLink) link).getSource());
            }
          }
        }
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getIndirectlyReplicableElements(IContext context_p, Collection<Object> sources_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    if (sources_p == null) {
      return elements;
    }
    for (Object item : sources_p) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }

    for (Object item : sources_p) {
      if (item instanceof EObject) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink) {
            if ((context_p != null) && ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.CREATED_LINKS, link, context_p)) {
              continue;
            }

            if ((((CatalogElementLink) link).getSource() != null) && (((CatalogElementLink) link).eContainer() != null)) {
              elements.add(((CatalogElementLink) link).getSource());
            }
          }
        }
      }
    }
    return elements;
  }

  /**
   * Retrieve replicable elements which references given source through a GatheringLink
   * @param context_p
   * @param sources_p
   * @return
   */
  public Collection<CatalogElement> getLinkingReplicableElements(IContext context_p, Collection<Object> sources_p) {
    ArrayList<CatalogElement> elements = new ArrayList<CatalogElement>();
    for (Object item : sources_p) {
      if (item instanceof EObject) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink) {
            if (ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.CREATED_LINKS, link, context_p)) {
              continue;
            }

            if (((CatalogElementLink) link).getSource() != null) {
              elements.add(((CatalogElementLink) link).getSource());
            }
          }
        }
      }
    }
    return elements;
  }

  /**
   * @param context_p
   * @return
   */
  public Collection<Object> getAllDefinedReplicableElements(IContext context_p) {
    ArrayList<Object> elements = new ArrayList<Object>();
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);

    if (!selection.isEmpty()) {
      CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context_p).getRootPackage((EObject) selection.iterator().next());
      elements.addAll(getAllOwnedCatalogElements(pkg));
    }
    return elements;
  }

  public Collection<Object> getAllDefinedRecReplicableElements(IContext context_p) {
    ArrayList<Object> elements = new ArrayList<Object>();
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);

    if (!selection.isEmpty()) {
      CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context_p).getRootPackage((EObject) selection.iterator().next());
      for (CatalogElement element : getAllOwnedCatalogElements(pkg)) {
        if (element.getKind() == CatalogElementKind.REC) {
          elements.add(element);
        }
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getAllOwnedCatalogElements(CatalogElement element_p) {
    Collection<CatalogElement> elements = new ArrayList<CatalogElement>();
    if (element_p != null) {
      for (CatalogElement ownedElement : element_p.getOwnedElements()) {
        elements.add(ownedElement);
        elements.addAll(getAllOwnedCatalogElements(ownedElement));
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getAllOwnedCatalogElements(CatalogElementPkg element_p) {
    Collection<CatalogElement> elements = new ArrayList<CatalogElement>();
    for (CatalogElement ownedElement : element_p.getOwnedElements()) {
      elements.add(ownedElement);
      elements.addAll(getAllOwnedCatalogElements(ownedElement));
    }
    for (CatalogElementPkg ownedElementPkg : element_p.getOwnedElementPkgs()) {
      elements.addAll(getAllOwnedCatalogElements(ownedElementPkg));
    }
    return elements;
  }

  public String getInitialReplicaName(IContext context_p, CatalogElementPkg pkg_p) {
    return ("RPL" + (pkg_p == null ? "" : pkg_p.getOwnedElements().size() + 1));
  }

  public String getInitialReplicableElementName(IContext context_p, CatalogElementPkg pkg_p) {
    return ("REC" + (pkg_p == null ? "" : pkg_p.getOwnedElements().size() + 1));
  }

  public String getString(IContext context_p, Collection<Object> elements_p) {
    String result = "";
    Iterator<Object> itObject = elements_p.iterator();
    while (itObject.hasNext()) {
      Object object = itObject.next();
      if (object instanceof EObject) {
        //TODO SHARED
        result += EObjectLabelProviderHelper.getText((EObject) object);
      }
      if (itObject.hasNext()) {
        result += "; ";
      }
    }
    if (result.length() > 30) {
      result = result.substring(0, 27) + "...";
    }
    return result;
  }

  /**
   * @param esource_p
   * @return
   */
  public Collection<CatalogElement> getUsedReplicableElements(CatalogElement esource_p) {
    LinkedList<CatalogElement> toVisit = new LinkedList<CatalogElement>();
    if (esource_p != null) {

      for (CatalogElementLink link : esource_p.getOwnedLinks()) {
        if ((link != null) && (link.getTarget() != null) && (link.getTarget() instanceof CatalogElement)) {
          toVisit.addLast((CatalogElement) link.getTarget());
        }
      }
    }
    return toVisit;

  }

  /**
   * @param source_p
   */
  public Collection<CatalogElement> getAllUsedReplicableElements(CatalogElement source_p) {
    LinkedList<CatalogElement> toVisit = new LinkedList<CatalogElement>();
    LinkedList<CatalogElement> visited = new LinkedList<CatalogElement>();
    toVisit.add(source_p);

    while (!toVisit.isEmpty()) {
      CatalogElement visitedRE = toVisit.removeFirst();
      if (!visited.contains(visitedRE)) {
        visited.add(visitedRE);
        if (visitedRE != null) {
          for (CatalogElementLink link : visitedRE.getOwnedLinks()) {
            if ((link != null) && (link.getTarget() != null) && (link.getTarget() instanceof CatalogElement)) {
              toVisit.addLast((CatalogElement) link.getTarget());
            }
          }
        }
      }
    }
    return visited;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * @param value_p
   * @param context_p
   * @return
   */
  public boolean isUnmodifiableElement(EObject value_p, IContext context_p) {
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

    //Unmodifiable element if linked to internal replicable elements of source / target
    Collection<CatalogElement> elements =
        ReplicableElementHandlerHelper.getInstance(context_p).getIndirectlyReplicableElements(context_p, (Collection) Collections.singletonList(value_p));

    elements.retainAll(ReplicableElementHandlerHelper.getInstance(context_p).getAllUsedReplicableElements(source));
    elements.remove(source);
    elements.remove(value_p);
    if (!elements.isEmpty()) {
      return true;
    }

    elements =
        ReplicableElementHandlerHelper.getInstance(context_p).getIndirectlyReplicableElements(context_p, (Collection) Collections.singletonList(value_p));

    elements.retainAll(ReplicableElementHandlerHelper.getInstance(context_p).getAllUsedReplicableElements(target));
    elements.remove(target);
    elements.remove(value_p);
    if (!elements.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * @param value_p
   * @return
   */
  public Collection<Object> getAllDefinedCatalogElementPkgs(IContext context_p) {
    ArrayList<Object> elements = new ArrayList<Object>();
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    return Collections.emptyList();
  }

  /**
   * @param location_p
   * @param context_p
   * @return
   */
  public boolean isDefaultLocation(EObject location_p, IContext context_p) {
    Collection<Object> selection = (Collection<Object>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    EObject defaultLocation = ReplicableElementHandlerHelper.getInstance(context_p).getRootPackage((EObject) selection.iterator().next());
    return defaultLocation.equals(location_p) || ((EObjectExt.isContainedBy(location_p, defaultLocation)));
  }

  /**
   * @param context_p
   */
  public void cleanVirtualLinks(IContext context_p) {
    Collection<EObject> links = ContextScopeHandlerHelper.getInstance(context_p).getCollection(IReConstants.CREATED_LINKS, context_p);
    Collection<EObject> toRemove = new LinkedList<EObject>();
    for (EObject object : links) {
      if (!ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.CREATED_LINKS_TO_KEEP, object, context_p)) {
        toRemove.add(object);
      }
    }

    //Remove also invalid links in the target REC/RPL (elements can have been be removed)
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);
    if (target != null) {
      for (CatalogElementLink link : target.getOwnedLinks()) {
        if ((link != null) && !(toRemove.contains(link))) {
          if (link.getTarget() == null) {
            toRemove.add(link);

          } else if (link.getTarget().eContainer() == null) {
            toRemove.add(link);
          }
        }
      }
    }

    if (!toRemove.isEmpty()) {
      for (EObject link : toRemove) {
        if (link instanceof CatalogElementLink) {
          ((CatalogElementLink) link).setTarget(null);
          ((CatalogElementLink) link).setSource(null);
        }
      }
      AttachmentHelper.getInstance(context_p).removeElements(toRemove, context_p);
    }

    links.clear();
    links = ContextScopeHandlerHelper.getInstance(context_p).getCollection(IReConstants.VIRTUAL_LINKS, context_p);
    links.removeAll(toRemove);
  }

  /**
   * @param context_p
   */
  public Collection<CompliancyDefinition> getAllDefinedCompliancy(EObject location_p) {
    return Collections.emptyList();
  }

  /**
   * @param sourceProperty_p
   */
  public void createDefaultCompliancies(EObject source_p) {
    CatalogElementPkg pkg = getRootPackage(source_p);
    if (pkg instanceof RecCatalog) {
      CompliancyDefinitionPkg definitions = ((RecCatalog) pkg).getOwnedCompliancyDefinitionPkg();
      if (definitions == null) {
        definitions = ReFactory.eINSTANCE.createCompliancyDefinitionPkg();
        definitions.setName("Compliancy Definitions");
        ((RecCatalog) pkg).setOwnedCompliancyDefinitionPkg(definitions);
      }

      if (definitions.getOwnedDefinitions().isEmpty()) {
        CompliancyDefinition definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName("BLACK_BOX");
        definitions.getOwnedDefinitions().add(definition);

        definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName("CONSTRAINT_REUSE");
        definitions.getOwnedDefinitions().add(definition);

        definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName("INHERITANCY_REUSE");
        definitions.getOwnedDefinitions().add(definition);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable_p, Collection<CatalogElementLink> setLinks_p, IContext context_p) {
    HashSet<CatalogElementLink> newLinks = new HashSet<CatalogElementLink>();

    for (CatalogElementLink link : setLinks_p) {
      CatalogElementLink link2 = ReFactory.eINSTANCE.createCatalogElementLink();
      link2.setTarget(link.getTarget());
      link2.setSource(replicable_p);
      link2.setOrigin(link);
      replicable_p.getOwnedLinks().add(link2);
      newLinks.add(link2);

      ContextScopeHandlerHelper.getInstance(context_p).add(IReConstants.CREATED_LINKS, link2, context_p);
      ContextScopeHandlerHelper.getInstance(context_p).add(IReConstants.VIRTUAL_LINKS, link2, context_p);
    }
    return newLinks;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CatalogElementLink getOppositeLink(CatalogElementLink link_p, IContext context) {
    CatalogElement element = link_p.getSource();

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
    if (element.equals(source)) {
      element = target;
    } else {
      element = source;
    }

    Collection<CatalogElementLink> usedLinks = ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element, new QueryContext());
    for (CatalogElementLink usedLink : usedLinks) {
      if ((usedLink != null) && (usedLink.getOrigin() != null) && usedLink.getOrigin().equals(link_p)) {
        return usedLink;
      }
      if ((link_p != null) && (link_p.getOrigin() != null) && link_p.getOrigin().equals(usedLink)) {
        return usedLink;
      }
    }

    return null;
  }

  public CatalogElement createInitialReplica(CatalogElement source_p, CatalogElement target_p, IContext context_p) {
    return createInitialReplica(source_p, target_p, context_p, true);
  }

  public CatalogElement createInitialReplica(CatalogElement source_p, CatalogElement target_p, IContext context_p, boolean children_p) {

    CatalogElement element = target_p;
    if (element == null) {
      element = ReplicableElementHandlerHelper.getInstance(context_p).createReplica();
    }

    if (source_p != null) {
      String namea = source_p.getName();
      if (namea.startsWith("REC")) {
        namea = "RPL" + namea.substring(3);
      }
      element.setName(namea);
      element.setOrigin(source_p);

      if (IReConstants.ENABLE_SUB_INSTANCIATION()) {
        //create internal links
        for (CatalogElementLink link : source_p.getOwnedLinks()) {
          if ((link != null) && (link.getTarget() != null) && (link.getTarget() instanceof CatalogElement)) {
            CatalogElement replica = null;
            boolean found = false;
            for (CatalogElementLink linkReplica : element.getOwnedLinks()) {
              if ((linkReplica != null) && (linkReplica.getOrigin() != null) && linkReplica.getOrigin().equals(link)) {
                found = true;
                replica = (CatalogElement) linkReplica.getTarget();
                break;
              }
            }

            replica = createInitialReplica((CatalogElement) link.getTarget(), replica, context_p, IReConstants.ENABLE_SUB_INSTANCIATION());

            if (!found) {
              CatalogElementLink linkCopy = ReFactory.eINSTANCE.createCatalogElementLink();
              linkCopy.setSource(element);
              linkCopy.setTarget(replica);
              linkCopy.setOrigin(link);
              element.getOwnedLinks().add(linkCopy);
              element.getOwnedElements().add(replica);
              ContextScopeHandlerHelper.getInstance(context_p).add(IReConstants.CREATED_LINKS, linkCopy, context_p);
            }
          }
        }
      }
    }

    return element;
  }

  /**
   * @param element_p
   * @param context_p
   */
  public void addDeletableElement(EObject element_p, IContext context_p) {
    if (!context_p.exists(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)) {
      context_p.put(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE, new HashSet<EObject>());
    }

    ((Collection) context_p.get(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)).add(element_p);
  }

  public Collection<EObject> getDeletableElements(IContext context_p) {
    if (!context_p.exists(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)) {
      return Collections.emptyList();
    }
    return ((Collection) context_p.get(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE));
  }

}
