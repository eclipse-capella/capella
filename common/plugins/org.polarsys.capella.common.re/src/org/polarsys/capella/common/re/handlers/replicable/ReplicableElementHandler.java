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
package org.polarsys.capella.common.re.handlers.replicable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
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
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementHandler implements IReplicableElementHandler {

  public static final String SOURCE = "REH_SOURCE";

  public static final String TARGET = "REH_TARGET";

  public static final String INITIAL_SOURCE = "REH_INITIAL_SOURCE";

  public static final String INITIAL_TARGET = "REH_INITIAL_TARGET";

  public static final String LINKS = "LINKS";

  public static final String COMPLIANCY_BLACK_BOX_NAME = "BLACK_BOX"; //$NON-NLS-1$
  public static final String COMPLIANCY_CONSTRAINT_REUSE_NAME = "CONSTRAINT_REUSE"; //$NON-NLS-1$
  public static final String COMPLIANCY_INHERITANCY_REUSE_NAME = "INHERITANCY_REUSE"; //$NON-NLS-1$

  public CatalogElement getSource(IContext context) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE));
  }

  public CatalogElement getTarget(IContext context) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET));
  }

  public void setSource(IContext context, CatalogElement element) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    ctx.setCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_SOURCE), element);
  }

  public void setTarget(IContext context, CatalogElement element) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    ctx.setCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__CURRENT_TARGET), element);
  }

  public LinkedList<CatalogElement> getListSourcesVisited(IContext context) {
    if (!context.exists("getListSourcesVisited")) {
      context.put("getListSourcesVisited", new LinkedList<CatalogElement>());
    }
    return (LinkedList<CatalogElement>) context.get("getListSourcesVisited");
  }

  public LinkedList<CatalogElement> getListSources(IContext context) {
    if (!context.exists("getListSources")) {
      context.put("getListSources", new LinkedList<CatalogElement>());
    }
    return (LinkedList<CatalogElement>) context.get("getListSources");
  }

  public CatalogElement getInitialSource(IContext context) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
  }

  public CatalogElement getInitialTarget(IContext context) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    return (CatalogElement) ctx.getCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
  }

  public void setInitialSource(IContext context, CatalogElement element) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    ctx.setCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE), element);
  }

  public void setInitialTarget(IContext context, CatalogElement element) {
    IPropertyContext ctx =
        ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context,
            (String) context.get(ITransitionConstants.OPTIONS_SCOPE));
    ctx.setCurrentValue(ctx.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET), element);
  }

  public Collection<CatalogElementLink> getLinks(IContext context) {
    if (!context.exists(LINKS)) {
      context.put(LINKS, new ArrayList<CatalogElementLink>());
    }
    return (Collection<CatalogElementLink>) context.get(LINKS);
  }

  public CatalogElementPkg getRootPackage(EObject object) {
    //TODO SHARED
    return ReFactory.eINSTANCE.createCatalogElementPkg();
  }

  /** Replicable elements helpers */

  /**
   * Returns elements used through owned CatalogElementLinks of the given object
   */
  public Collection<EObject> getElements(CatalogElement object) {
    return getElements(object, new QueryContext());
  }

  /**
   * Returns elements used through owned CatalogElementLinks of the given object
   */
  public Collection<EObject> getElements(CatalogElement object, IQueryContext context) {
    return QueryInterpretor.executeQuery(CatalogElement_UsedElements.class.getSimpleName(), object, context);
  }

  /**
   * Returns owned non-null-target CatalogElementLinks of the given object
   */
  public Collection<CatalogElementLink> getElementsLinks(CatalogElement object) {
    return getElementsLinks(object, new QueryContext());
  }

  /**
   * Returns owned non-null-target CatalogElementLinks of the given object
   */
  public Collection<CatalogElementLink> getElementsLinks(CatalogElement object, IQueryContext context) {
    return QueryInterpretor.executeQuery(CatalogElement_UsedElementLinks.class.getSimpleName(), object, context);
  }

  public Collection<CatalogElementLink> getAllElementsLinks(CatalogElement object) {
    return getAllElementsLinks(object, new QueryContext());
  }

  public Collection<CatalogElementLink> getAllElementsLinks(CatalogElement object, IQueryContext context) {
    return QueryInterpretor.executeQuery(CatalogElement_AllUsedElementLinks.class.getSimpleName(), object, context);
  }

  public Collection<EObject> getAllElements(CatalogElement object) {
    return getAllElements(object, new QueryContext());
  }

  public Collection<EObject> getAllElements(CatalogElement object, IQueryContext context) {
    return QueryInterpretor.executeQuery(CatalogElement_AllUsedElements.class.getSimpleName(), object, context);
  }

  public boolean hasOrigin(CatalogElement element) {
    return element.getOrigin() != null;
  }

  public boolean isReplica(CatalogElement source, CatalogElement supposedReplicad) {
    return (supposedReplicad.getOrigin() != null) && supposedReplicad.getOrigin().equals(source);
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

  public CatalogElementLink addLink(IContext context, CatalogElement element, EObject value, EObject oppositeValue) {

    for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element)) {
      if (value.equals(link.getTarget())) {
        return null;
      }
      if ((oppositeValue != null) && oppositeValue.equals(link.getTarget())) {
        if (ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.VIRTUAL_LINKS, link, context)) {
          link.setTarget(value);
          link.setSource(element);
          ContextScopeHandlerHelper.getInstance(context).remove(IReConstants.VIRTUAL_LINKS, link, context);
        }
        return link;
      }
    }

    CatalogElementLink link = ReFactory.eINSTANCE.createCatalogElementLink();
    link.setSource(element);
    link.setTarget(value);
    getLinks(context).add(link);
    element.getOwnedLinks().add(link);
    return link;
  }

  public boolean isIndirectlyReferencedBy(EObject element, CatalogElement re, IContext context) {
    LinkedList<CatalogElement> elements = new LinkedList<CatalogElement>();
    LinkedList<CatalogElement> visited = new LinkedList<CatalogElement>();

    elements.addAll(getIndirectlyReplicableElements(context, (Collection) Collections.singleton(element)));

    while (!(elements.isEmpty())) {
      CatalogElement elt = elements.removeFirst();

      if (!visited.contains(elt)) {
        visited.add(elt);

        if ((elt != null) && elt.equals(re)) {
          return true;
        }
        elements.addAll(getLinkingReplicableElements(context, (Collection) Collections.singleton(elt)));
      }
    }

    return false;
  }

  public Collection<CatalogElement> getSelectedReplicableElements(IContext context) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    for (Object item : selection) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getIndirectlySelectedReplicableElements(IContext context) {

    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    return getIndirectlyReplicableElements(context, selection);
  }

  public Collection<CatalogElement> getUppestReplicableElement(IContext context, Collection<?> sources) {
    Collection<CatalogElement> result = new ArrayList<>();
    for (Object item : sources) {
      if (item instanceof CatalogElement) {
        result.add((CatalogElement) item);
      }
    }
    result.removeAll(getLinkingReplicableElements(context, sources));
    return result;
  }

  public Collection<CatalogElement> getIndirectlyReplicableElementsForCommand(IContext context, Collection<?> sources) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    for (Object item : sources) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }

    for (Object item : sources) {
      if (item != null && !(item instanceof CatalogElement)) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item,
            RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink && ((CatalogElementLink) link).getSource() != null) {
            elements.add(((CatalogElementLink) link).getSource());
          }
        }
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getIndirectlyReplicableElements(IContext context, Collection<?> sources) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    if (sources == null) {
      return elements;
    }
    for (Object item : sources) {
      if (item instanceof CatalogElement) {
        elements.add((CatalogElement) item);
      }
    }

    for (Object item : sources) {
      if (item instanceof EObject) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink) {
            if ((context != null) && ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
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
   * @param context
   * @param sources
   * @return
   */
  public Collection<CatalogElement> getLinkingReplicableElements(IContext context, Collection<?> sources) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    for (Object item : sources) {
      if (item instanceof EObject) {
        Collection<EObject> links = EObjectExt.getReferencers((EObject) item, RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
        for (EObject link : links) {
          if (link instanceof CatalogElementLink) {
            if (ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
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
   * @param context
   * @return
   */
  public Collection<CatalogElement> getAllDefinedReplicableElements(IContext context) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);

    if (!selection.isEmpty()) {
      CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context).getRootPackage((EObject) selection.iterator().next());
      elements.addAll(getAllOwnedCatalogElements(pkg));
    }
    return elements;
  }

  public Collection<CatalogElement> getAllDefinedRecReplicableElements(IContext context) {
    ArrayList<CatalogElement> elements = new ArrayList<>();
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);

    if (!selection.isEmpty()) {
      CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context).getRootPackage((EObject) selection.iterator().next());
      for (CatalogElement element : getAllOwnedCatalogElements(pkg)) {
        if (element.getKind() == CatalogElementKind.REC) {
          elements.add(element);
        }
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getAllOwnedCatalogElements(CatalogElement element) {
    Collection<CatalogElement> elements = new ArrayList<>();
    if (element != null) {
      for (CatalogElement ownedElement : element.getOwnedElements()) {
        elements.add(ownedElement);
        elements.addAll(getAllOwnedCatalogElements(ownedElement));
      }
    }
    return elements;
  }

  public Collection<CatalogElement> getAllOwnedCatalogElements(CatalogElementPkg element) {
    Collection<CatalogElement> elements = new ArrayList<>();
    for (CatalogElement ownedElement : element.getOwnedElements()) {
      elements.add(ownedElement);
      elements.addAll(getAllOwnedCatalogElements(ownedElement));
    }
    for (CatalogElementPkg ownedElementPkg : element.getOwnedElementPkgs()) {
      elements.addAll(getAllOwnedCatalogElements(ownedElementPkg));
    }
    return elements;
  }

  public String getInitialReplicaName(IContext context, CatalogElementPkg pkg) {
    return ("RPL" + (pkg == null ? "" : pkg.getOwnedElements().size() + 1));
  }

  public String getInitialReplicableElementName(IContext context, CatalogElementPkg pkg) {
    return ("REC" + (pkg == null ? "" : pkg.getOwnedElements().size() + 1));
  }

  public String getString(IContext context, Collection<?> elements) {
    String result = "";
    Iterator<?> itObject = elements.iterator();
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
   * @param esource
   * @return
   */
  public Collection<CatalogElement> getUsedReplicableElements(CatalogElement esource) {
    LinkedList<CatalogElement> toVisit = new LinkedList<>();
    if (esource != null) {

      for (CatalogElementLink link : esource.getOwnedLinks()) {
        if ((link != null) && (link.getTarget() != null) && (link.getTarget() instanceof CatalogElement)) {
          toVisit.addLast((CatalogElement) link.getTarget());
        }
      }
    }
    return toVisit;

  }

  /**
   * @param source
   */
  public Collection<CatalogElement> getAllUsedReplicableElements(CatalogElement source) {
    LinkedList<CatalogElement> toVisit = new LinkedList<>();
    LinkedList<CatalogElement> visited = new LinkedList<>();
    toVisit.add(source);

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
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * @param value
   * @param context
   * @return
   */
  public boolean isUnmodifiableElement(EObject value, IContext context) {
    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    //Unmodifiable element if linked to internal replicable elements of source / target
    Collection<CatalogElement> elements =
        ReplicableElementHandlerHelper.getInstance(context).getIndirectlyReplicableElements(context, (Collection) Collections.singletonList(value));

    elements.retainAll(ReplicableElementHandlerHelper.getInstance(context).getAllUsedReplicableElements(source));
    elements.remove(source);
    elements.remove(value);
    if (!elements.isEmpty()) {
      return true;
    }

    elements =
        ReplicableElementHandlerHelper.getInstance(context).getIndirectlyReplicableElements(context, (Collection) Collections.singletonList(value));

    elements.retainAll(ReplicableElementHandlerHelper.getInstance(context).getAllUsedReplicableElements(target));
    elements.remove(target);
    elements.remove(value);
    if (!elements.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * @param value
   * @return
   */
  public Collection<Object> getAllDefinedCatalogElementPkgs(IContext context) {
    ArrayList<Object> elements = new ArrayList<>();
    Collection<Object> selection = (Collection<Object>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    return Collections.emptyList();
  }

  /**
   * @param location
   * @param context
   * @return
   */
  public boolean isDefaultLocation(EObject location, IContext context) {
    Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    EObject defaultLocation = ReplicableElementHandlerHelper.getInstance(context).getRootPackage((EObject) selection.iterator().next());
    return defaultLocation.equals(location) || ((EObjectExt.isContainedBy(location, defaultLocation)));
  }

  /**
   * @param context
   */
  public void cleanVirtualLinks(IContext context) {
    Collection<EObject> createdLinks = ContextScopeHandlerHelper.getInstance(context).getCollection(IReConstants.CREATED_LINKS, context);
    Collection<EObject> toRemove = new HashSet<>();
    for (EObject object : createdLinks) {
      if (!ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS_TO_KEEP, object, context)) {
        toRemove.add(object);
      }
    }
    
    //Remove also invalid links in the target REC/RPL (elements can have been be removed)
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
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

    //Remove also all remaining virtual links that have not been merged (see org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandler.addLink)
    Collection<EObject> virtualLinks = ContextScopeHandlerHelper.getInstance(context).getCollection(IReConstants.VIRTUAL_LINKS, context);
    toRemove.addAll(virtualLinks);

    if (!toRemove.isEmpty()) {
      for (EObject link : toRemove) {
        if (link instanceof CatalogElementLink) {
          ((CatalogElementLink) link).setTarget(null);
          ((CatalogElementLink) link).setSource(null);
        }
      }
      AttachmentHelper.getInstance(context).removeElements(toRemove, context);
    }

    createdLinks.clear();
    virtualLinks.clear();
  }

  /**
   * @param context
   */
  public Collection<CompliancyDefinition> getAllDefinedCompliancy(EObject location) {
    return Collections.emptyList();
  }

  /**
   * @param sourceProperty
   */
  public void createDefaultCompliancies(EObject source) {
    CatalogElementPkg pkg = getRootPackage(source);
    if (pkg instanceof RecCatalog) {
      CompliancyDefinitionPkg definitions = ((RecCatalog) pkg).getOwnedCompliancyDefinitionPkg();
      if (definitions == null) {
        definitions = ReFactory.eINSTANCE.createCompliancyDefinitionPkg();
        definitions.setName("Compliancy Definitions");
        ((RecCatalog) pkg).setOwnedCompliancyDefinitionPkg(definitions);
      }

      if (definitions.getOwnedDefinitions().isEmpty()) {
        CompliancyDefinition definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName(COMPLIANCY_BLACK_BOX_NAME);
        definitions.getOwnedDefinitions().add(definition);

        definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName(COMPLIANCY_CONSTRAINT_REUSE_NAME);
        definitions.getOwnedDefinitions().add(definition);

        definition = ReFactory.eINSTANCE.createCompliancyDefinition();
        definition.setName(COMPLIANCY_INHERITANCY_REUSE_NAME);
        definitions.getOwnedDefinitions().add(definition);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable, Collection<CatalogElementLink> setLinks, IContext context) {
    HashSet<CatalogElementLink> newLinks = new LinkedHashSet<>();

    for (CatalogElementLink link : setLinks) {
      CatalogElementLink link2 = ReFactory.eINSTANCE.createCatalogElementLink();
      link2.setTarget(link.getTarget());
      link2.setSource(replicable);
      link2.setOrigin(link);
      replicable.getOwnedLinks().add(link2);
      newLinks.add(link2);

      ContextScopeHandlerHelper.getInstance(context).add(IReConstants.CREATED_LINKS, link2, context);
      ContextScopeHandlerHelper.getInstance(context).add(IReConstants.VIRTUAL_LINKS, link2, context);
    }
    return newLinks;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CatalogElementLink getOppositeLink(CatalogElementLink link, IContext context) {
    CatalogElement element = link.getSource();

    CatalogElement source = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement target = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
    if (element.equals(source)) {
      element = target;
    } else {
      element = source;
    }

    Collection<CatalogElementLink> usedLinks = ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element, new QueryContext());
    for (CatalogElementLink usedLink : usedLinks) {
      if ((usedLink != null) && (usedLink.getOrigin() != null) && usedLink.getOrigin().equals(link)) {
        return usedLink;
      }
      if ((link != null) && (link.getOrigin() != null) && link.getOrigin().equals(usedLink)) {
        return usedLink;
      }
    }

    return null;
  }

  public CatalogElement createInitialReplica(CatalogElement source, CatalogElement target, IContext context) {
    return createInitialReplica(source, target, context, true);
  }

  public CatalogElement createInitialReplica(CatalogElement source, CatalogElement target, IContext context, boolean children) {

    CatalogElement element = target;
    if (element == null) {
      element = ReplicableElementHandlerHelper.getInstance(context).createReplica();
    }

    if (source != null && !source.eIsProxy()) {
      String namea = source.getName();
      if (namea.startsWith("REC")) {
        namea = "RPL" + namea.substring(3);
      }
      element.setName(namea);
      element.setOrigin(source);

      if (IReConstants.ENABLE_SUB_INSTANCIATION()) {
        //create internal links
        for (CatalogElementLink link : source.getOwnedLinks()) {
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

            replica = createInitialReplica((CatalogElement) link.getTarget(), replica, context, IReConstants.ENABLE_SUB_INSTANCIATION());

            if (!found) {
              CatalogElementLink linkCopy = ReFactory.eINSTANCE.createCatalogElementLink();
              linkCopy.setSource(element);
              linkCopy.setTarget(replica);
              linkCopy.setOrigin(link);
              element.getOwnedLinks().add(linkCopy);
              element.getOwnedElements().add(replica);
              ContextScopeHandlerHelper.getInstance(context).add(IReConstants.CREATED_LINKS, linkCopy, context);
            }
          }
        }
      }
    }

    return element;
  }

  /**
   * @param element
   * @param context
   */
  public void addDeletableElement(EObject element, IContext context) {
    if (!context.exists(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)) {
      context.put(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE, new HashSet<EObject>());
    }

    ((Collection) context.get(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)).add(element);
  }

  public Collection<EObject> getDeletableElements(IContext context) {
    if (!context.exists(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE)) {
      return Collections.emptyList();
    }
    return ((Collection) context.get(IReConstants.ADDITIONAL_ELEMENTS_TO_DELETE));
  }

  public boolean isFromSource(IContext context, CatalogElementLink link) {

    // Retrieve if the given Link is from the Source or the Target
    CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getInitialSource(context);
    CatalogElement elementSource = link.getSource();
    boolean isLinkFromSource = false;
    if (elementSource.equals(sourceElement)) {
      isLinkFromSource = true;
    }
    
    return isLinkFromSource;
  }

}
