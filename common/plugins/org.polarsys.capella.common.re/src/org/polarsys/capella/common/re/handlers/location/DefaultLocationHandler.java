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
package org.polarsys.capella.common.re.handlers.location;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.IRuleAttachment;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

public class DefaultLocationHandler implements ILocationHandler {

  HashMap<EObject, EObject> _currentLocation = new HashMap<EObject, EObject>();

  HashMap<CatalogElementLink, EObject> _locations = new HashMap<CatalogElementLink, EObject>();

  HashMap<CatalogElementLink, EObject> _defaultLocations = new HashMap<CatalogElementLink, EObject>();

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCurrentLocation(CatalogElementLink object_p, IContext context_p) {
    if (_currentLocation.containsKey(object_p)) {
      return _currentLocation.get(object_p);
    }
    return null;
  }

  private boolean isInvalidResourceLocation(CatalogElementLink link, EObject location, IContext context) {

    if (location == null) {
      return true;
    }
    Collection<EObject> elements = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
    CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);

    Resource destinationResource = elements.iterator().next().eResource();

    // Retrieve if the given Link is from the Source or the Target
    boolean isSource = false;
    CatalogElement elementSource = link.getSource();
    if (elementSource == null) {
      return true;
    }

    if (elementSource.equals(sourceElement)) {
      isSource = true;
    } else if (elementSource.equals(targetElement)) {
      isSource = false;
    }

    // Retrieve if the selected Resource is for the Source or the Target
    boolean isSelectionForSource = false;
    String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
      isSelectionForSource = true;

    } else if (IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {
      isSelectionForSource = true;
    } // when creating a RPL, user can select the REC instead of a real location..., isSelectionForSource should be true in that case

    // Change the resource according to selection
    Resource sourceElementResource;
    Resource targetElementResource;
    Resource sourceResource = (sourceElement == null) || ((sourceElementResource = sourceElement.eResource()) == null) ? destinationResource : sourceElementResource;
    Resource targetResource = (targetElement == null) || ((targetElementResource = targetElement.eResource()) == null) ? destinationResource : targetElementResource;
    if (isSelectionForSource) {
      sourceResource = destinationResource;
    } else {
      targetResource = destinationResource;
    }

    // If REC and RPL are not located in the same resource, we exclude all containers not located in the targetElement's resource.
    if (isSource && (sourceResource != location.eResource())) {
      return true;
    } else if (!isSource && (targetResource != location.eResource())) {
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCurrentLocation(CatalogElementLink object_p, EObject container_p, IContext context_p) {
    _currentLocation.put(object_p, container_p);
  }

  protected CatalogElementLink getLink(CatalogElement element, EObject target, IContext context_p) {
    for (CatalogElementLink link : element.getOwnedLinks()) {
      if ((target != null) && target.equals(link.getTarget())) {
        return link;
      }
    }
    return null;
  }

  protected CatalogElementLink getAllLink(CatalogElement element, EObject target, IContext context_p) {
    Collection<CatalogElementLink> links = ReplicableElementHandlerHelper.getInstance(context_p).getAllElementsLinks(element);
    for (CatalogElementLink link : links) {
      if ((target != null) && target.equals(link.getTarget())) {
        return link;
      }
    }
    return null;
  }

  /**
   * @param target_p
   * @return
   */
  protected CatalogElementLink getLinkContainer(CatalogElementLink link_p, IContext context_p) {
    EObject target = link_p.getTarget();
    return getAllLink(link_p.getSource(), target.eContainer(), context_p);
  }

  protected CatalogElementLink getLinkRelatedElement(CatalogElementLink link_p, IContext context_p) {
    return null;
  }

  public void cleanLocations(IContext context_p) {
    _currentLocation.clear();
    _locations.clear();
    _defaultLocations.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getLocation(CatalogElementLink object_p, CatalogElementLink origin_p, IContext context_p) {

    CatalogElementLink link = object_p;
    EObject target = link.getTarget();

    if (target == null) {
      return null;
    }

    if (_locations.containsKey(link)) {
      return _locations.get(link);
    }

    // Retrieve link.getTarget().eContainer()<-elementLink
    CatalogElementLink container = getLinkContainer(link, context_p);
    if (container != null) {
      _locations.put(link, container);
      return container;
    }

    // Customization for Melody, we put component into their part
    CatalogElementLink relatedElement = getLinkRelatedElement(link, context_p);
    if (relatedElement != null) {
      _locations.put(link, relatedElement);
      return relatedElement;
    }

    if (((target instanceof CatalogElement)) || !ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.CREATED_LINKS, object_p, context_p)) {
      if (!isInvalidResourceLocation(link, object_p.getTarget().eContainer(), context_p)) {
        _locations.put(link, object_p.getTarget().eContainer());
        return object_p.getTarget().eContainer();
      }
    }

    // For all elements added in REC but not yet in RPL
    // we add it into the link.getTarget().eContainer()<-elementLink
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context_p).getInitialTarget(context_p);
    for (CatalogElementLink linkA : ReplicableElementHandlerHelper.getInstance(context_p).getAllElementsLinks(element)) {
      if (((linkA.getOrigin() != null) && (linkA.getOrigin().getTarget() != null)) && (link.getTarget() != null)
          && linkA.getOrigin().getTarget().equals(link.getTarget().eContainer())) {
        _locations.put(link, linkA);
        return linkA;
      }
    }

    _locations.put(link, null);
    return null;
  }

  /**
   * @param targetContainer_p
   * @param target_p
   * @return
   */
  protected boolean isValidContainement(EObject targetContainer_p, EObject target_p) {
    return true;
  }

  /**
   * Returns whether the selected element can be store the link_p target
   * @param target_p
   * @param eObject_p
   * @return
   */
  protected boolean isInitialSelectionValidContainer(EObject selection_p, CatalogElementLink link_p, IContext context_p) {
    if (context_p.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
      String value = (String) context_p.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
        if (link_p.getSource().equals(ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p))) {
          // We are adding an element in the REC according to the RPL, so the user has selected an element from the RPL, we don't add the new REC element in the
          // RPL
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @param element_p
   * @return
   */
  protected Collection<EObject> getRelatedElements(EObject element_p) {
    return Collections.singleton(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getDefaultLocation(CatalogElementLink source_p, CatalogElementLink origin_p, IContext context_p) {
    CatalogElementLink linkSource = source_p;
    if ((linkSource == null) || (linkSource.getTarget() == null)) {
      return null;
    }

    if (_defaultLocations.containsKey(source_p)) {
      return _defaultLocations.get(source_p);
    }

    // Retrieve default containers
    Collection<EObject> elementsContainers = retrieveDefaultContainers(source_p, origin_p, context_p);

    // Now we are looking for a feature according to related rule and if we can add element to one of the retrieve possible container, we return it
    IRuleAttachment arule = getRule(linkSource.getTarget(), context_p);

    for (EObject targetContainer : elementsContainers) {

      // If REC and RPL are not located in the same resource, we exclude all containers not located in the targetElement's resource.
      if (isInvalidResourceLocation(linkSource, targetContainer, context_p)) {
        continue;
      }
      if (!isValidContainement(targetContainer, linkSource.getTarget())) {
        continue;
      }

      EStructuralFeature sourceFeature = linkSource.getTarget().eContainingFeature();
      EStructuralFeature targetFeature = sourceFeature;
      if (arule != null) {
        // If we have a rule, we use with priority rule information
        targetFeature = getFeature(arule, linkSource.getTarget(), linkSource.getTarget(), targetContainer, context_p);
        if (((targetFeature == null) || (targetFeature == sourceFeature)) && (linkSource.getOrigin() != null) && (linkSource.getOrigin().getTarget() != null)) {
          targetFeature = getFeature(arule, linkSource.getOrigin().getTarget(), linkSource.getTarget(), targetContainer, context_p);
        }
      }

      if (targetFeature != null) {
        if (AttachmentHelper.getInstance(context_p).isApplicable(targetContainer.eClass(), targetFeature)) {
          _defaultLocations.put(source_p, targetContainer);
          return targetContainer;
        }
      }
    }

    try {
      CatalogElementLink source = linkSource.getOrigin();
      EObject other = TraceabilityHandlerHelper.getInstance(context_p).retrieveSourceElements(linkSource.getTarget(), context_p).iterator().next();

      if (arule != null) {
        EObject result = arule.retrieveDefaultContainer(source == null ? other : source.getTarget(), linkSource.getTarget(), context_p);

        if (result != null) {
          if (!isValidContainement(result, linkSource.getTarget())) {
            _defaultLocations.put(source_p, null);
            return null;
          }
          // If REC and RPL are not located in the same resource, we exclude all containers not located in the destination's resource.
          if (isInvalidResourceLocation(linkSource, result, context_p)) {
            _defaultLocations.put(source_p, null);
            return null;
          }
        }

        _defaultLocations.put(source_p, result);
        return result;
      }

    } catch (Exception exception_p) {
      exception_p.printStackTrace();
    }

    _defaultLocations.put(source_p, null);
    return null;
  }

  /**
   * @param source_p
   * @param target_p
   * @param currentLocation_p
   * @return
   */
  public EStructuralFeature getFeature(EObject source_p, EObject target_p, EObject currentLocation_p, IContext context_p) {
    if (source_p == null) {
      return null;
    }
    IRuleAttachment arule = getRule(source_p, context_p);
    return getFeature(arule, source_p, target_p, currentLocation_p, context_p);
  }

  protected EStructuralFeature getFeature(IRuleAttachment rule, EObject source_p, EObject target_p, EObject currentLocation_p, IContext context_p) {
    if (source_p == null) {
      return null;
    }
    if (rule != null) {
      EStructuralFeature targetFeature = rule.retrieveTargetContainementFeature(source_p, target_p, currentLocation_p, context_p);
      if (targetFeature != null) {
        if (AttachmentHelper.getInstance(context_p).isApplicable(currentLocation_p.eClass(), targetFeature)) {
          return targetFeature;
        }
      }
    }
    return source_p.eContainingFeature();
  }

  /**
   * @param target_p
   * @param source_p
   * @param context_p
   * @return
   */
  protected Collection<EObject> retrieveDefaultContainers(CatalogElementLink target_p, CatalogElementLink source_p, IContext context_p) {

    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context_p).getInitialTarget(context_p);
    CatalogElementLink linkSource = target_p;

    Collection<EObject> elementsContainers = new LinkedHashSet<EObject>(); // order is important!

    CatalogElement recUsingSource = null;
    CatalogElement recUsingTarget = null;

    // For a link stored in catalogElement used "somewhere", if the TARGET catalogElement is used in replica of "somewhere", we can
    // find a container for the source which is source_p.target.eContainer<-replicaOfSomewhere
    for (CatalogElement referencingElement : ReplicableElementHandlerHelper.getInstance(context_p).getLinkingReplicableElements(context_p,
        Collections.singletonList((Object) linkSource.getSource()))) {
      for (CatalogElement referencingElement2 : ReplicableElementHandlerHelper.getInstance(context_p).getLinkingReplicableElements(context_p,
          Collections.singletonList((Object) element.getOrigin()))) {
        if (referencingElement.getOrigin().equals(referencingElement2)) {
          recUsingSource = referencingElement2;
          recUsingTarget = referencingElement;
          break;
        }
      }
    }
    CatalogElementLink linkToLocate = linkSource;
    if ((recUsingSource != null) && (recUsingTarget != null)) {
      for (CatalogElementLink linke : (Collection<CatalogElementLink>) (Collection) ReplicableElementHandlerHelper.getInstance(context_p).getElementsLinks(
          recUsingSource)) {
        if (((linkToLocate.getTarget() != null) && (linkToLocate.getTarget().eContainer() != null))
            && linkToLocate.getTarget().eContainer().equals(linke.getTarget())) {
          for (CatalogElementLink link : recUsingTarget.getOwnedLinks()) {
            if ((link != null) && ((link.getOrigin() != null) & link.getOrigin().equals(linke))) {
              elementsContainers.add(link.getTarget());
            }
          }
        }
      }
    }

    // We look in the selection if there is a compatible element to store the linkSource (the rack when instanciating the card)
    Collection<EObject> elements = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (elements != null) {
      for (EObject ppp : elements) {

        for (EObject item : getRelatedElements(ppp)) {
          if (isInvalidResourceLocation(linkSource, item, context_p)) {
            continue;
          }
          // if the selection is a REC, we add the container of the type (thus in the REC) of the RPL element as a possible location
          if (item instanceof CatalogElement) {
              String value = (String) context_p.get(IReConstants.COMMAND__CURRENT_VALUE);
              // specific case when parameters are reversed
              if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
              	elementsContainers.add(source_p.getTarget().eContainer());                            	
              }
              // nominal case
              else {
              	elementsContainers.add(target_p.getOrigin().getTarget().eContainer());              
              }
          } else {
            if (isInitialSelectionValidContainer(item, linkSource, context_p)) {
              elementsContainers.add(item);
            }
          }
        }
      }
    }

    // We look for a Link of a brother of linkSource (same eContainer) in the target, and then, we retrieve its container
    Collection<CatalogElementLink> links = ReplicableElementHandlerHelper.getInstance(context_p).getElementsLinks(element);

    for (CatalogElementLink linkA : links) {
      if ((linkA == null) || ((linkA.getOrigin() == null) || (linkA.getOrigin().getTarget() == null)) || (linkA.getOrigin().getTarget().eContainer() == null)) {
        continue; // invalid link
      }
      if ((linkSource == null) || ((linkSource.getOrigin() == null) || (linkSource.getOrigin().getTarget() == null))) {
        continue; // invalid link
      }
      if ((linkA.equals(linkSource))) {
        continue; // not a brother
      }
      if (!linkA.getOrigin().getTarget().eContainer().equals(linkSource.getOrigin().getTarget().eContainer())) {
        continue; // not a brother
      }

      elementsContainers.add(linkA.getTarget().eContainer()); // retrieve the container
    }

    // We also add for a CatalogElementLink to a CatalogElement, link.getSource().eContainer()
    String scope = (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext context = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p, scope);

    if (linkSource.getTarget() instanceof CatalogElement) {
      CatalogElement source = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
      if ((source != null) && source.equals(linkSource.getSource())) {
        IProperty property = context.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_SOURCE);
        if (property != null) {
          Object value = context.getCurrentValue(property);
          if ((value != null) && (value instanceof EObject)) {
            elementsContainers.add((EObject) context.getCurrentValue(property));
          }
        }
      }
      CatalogElement target = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);
      if ((target != null) && target.equals(linkSource.getSource())) {

        IProperty property = context.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
        if (property != null) {
          Object value = context.getCurrentValue(property);
          if ((value != null) && (value instanceof EObject)) {
            elementsContainers.add((EObject) context.getCurrentValue(property));
          }
        }
      }
    }

    return elementsContainers;
  }

  /**
   * Returns the rule for the given target_p element
   */
  public IRuleAttachment getRule(EObject target_p, IContext context_p) {

    IRuleAttachment arule = null;
    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);
    try {
      if (ruleHandler != null) {
        MappingPossibility mapping = ruleHandler.getApplicablePossibility(target_p);
        if (mapping != null) {
          IRule<?> rule = ruleHandler.getApplicablePossibility(target_p).getCompleteRule();
          if ((rule != null) && (rule instanceof IRuleAttachment)) {
            arule = (IRuleAttachment) rule;
          }
        }
      }
    } catch (MappingPossibilityResolutionException exception_p) {
      // Nothing to report
    }

    return arule;
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
    cleanLocations(context_p);
    return Status.OK_STATUS;
  }

}
