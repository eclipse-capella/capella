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
package org.polarsys.capella.common.re.handlers.location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.LocationTraceabilityHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.IRuleAttachment;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;


public class DefaultLocationHandler implements ILocationHandler {

  HashMap<EObject, EObject> currentLocation = new HashMap<>();

  HashMap<CatalogElementLink, EObject> locations = new HashMap<>();

  HashMap<CatalogElementLink, EObject> defaultLocations = new HashMap<>();

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCurrentLocation(CatalogElementLink link, IContext context) {
    if (currentLocation.containsKey(link)) {
      return currentLocation.get(link);
    }
    return null;
  }

  private boolean isInvalidResourceLocation(CatalogElementLink link, EObject location, IContext context) {

    if (location == null) {
      return true;
    }

    CatalogElement elementSource = link.getSource();
    
    //If the link is not linked to a REC-RPL, it is invalid
    if (elementSource == null) {
      return true;
    }

    // Retrieve if the given Link is from the Source or the Target
    boolean isLinkFromSource = ReplicableElementHandlerHelper.getInstance(context).isFromSource(context, link);

    if (isLinkFromSource) {
      //If link is for source side, check that given location is on same project than source
      CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getInitialSource(context);
      Resource sourceResource = EcoreUtil.getRootContainer(sourceElement).eResource();
      Resource locationResource = EcoreUtil.getRootContainer(location).eResource();
      if (sourceResource != locationResource) {
        return true;
      }
      
    } else {
      //If link is for target side, check that given location is on same project than target
      CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);
      Resource targetResource = EcoreUtil.getRootContainer(targetElement).eResource();
      Resource locationResource = EcoreUtil.getRootContainer(location).eResource();
      if (targetResource != locationResource) {
        return true;
      }
    }
    
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCurrentLocation(CatalogElementLink link, EObject container, IContext context) {
    currentLocation.put(link, container);
  }

  protected CatalogElementLink getLink(CatalogElement element, EObject target, IContext context) {
    for (CatalogElementLink link : element.getOwnedLinks()) {
      if ((target != null) && target.equals(link.getTarget())) {
        return link;
      }
    }
    return null;
  }

  protected CatalogElementLink getAllLink(CatalogElement element, EObject target, IContext context) {
    Collection<CatalogElementLink> links = ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element);
    for (CatalogElementLink link : links) {
      if ((target != null) && target.equals(link.getTarget())) {
        return link;
      }
    }
    return null;
  }

  /**
   * @param link
   * @param context
   * @return
   */
  protected CatalogElementLink getLinkContainer(CatalogElementLink link, IContext context) {
    EObject target = link.getTarget();
    return getAllLink(link.getSource(), target.eContainer(), context);
  }

  protected CatalogElementLink getLinkRelatedElement(CatalogElementLink link, IContext context) {
    return null;
  }

  @Override
  public void cleanLocations(IContext context) {
    currentLocation.clear();
    locations.clear();
    defaultLocations.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getLocation(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context) {

    EObject target = link.getTarget();

    if (target == null) {
      return null;
    }

    if (locations.containsKey(link)) {
      return locations.get(link);
    }

    // Retrieve link.getTarget().eContainer()<-elementLink
    CatalogElementLink container = getLinkContainer(link, context);
    if (container != null) {
      locations.put(link, container);
      return container;
    }

    // Customization for Melody, we put component into their part
    CatalogElementLink relatedElement = getLinkRelatedElement(link, context);
    if (relatedElement != null) {
      locations.put(link, relatedElement);
      return relatedElement;
    }

    if (((target instanceof CatalogElement)) || !ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, link, context)) {
      if (!isInvalidResourceLocation(link, link.getTarget().eContainer(), context)) {
        locations.put(link, link.getTarget().eContainer());
        return link.getTarget().eContainer();
      }
    }

    // For all elements added in REC but not yet in RPL
    // we add it into the link.getTarget().eContainer()<-elementLink
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);
    for (CatalogElementLink linkA : ReplicableElementHandlerHelper.getInstance(context).getAllElementsLinks(element)) {
      if (((linkA.getOrigin() != null) && (linkA.getOrigin().getTarget() != null)) && (link.getTarget() != null)
          && linkA.getOrigin().getTarget().equals(link.getTarget().eContainer())) {
        locations.put(link, linkA);
        return linkA;
      }
    }

    locations.put(link, null);
    return null;
  }

  /**
   * @param targetContainer
   * @param target
   * @return
   */
  protected boolean isValidContainement(EObject targetContainer, EObject target) {
    return true;
  }

  /**
   * Returns whether the selected element can be store the link target
   * @param selection
   * @param link
   * @param context
   * @return
   */
  protected boolean isInitialSelectionValidContainer(EObject selection, CatalogElementLink link, IContext context) {
    if (context.exists(IReConstants.COMMAND__CURRENT_VALUE)) {
      String value = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
        if (link.getSource().equals(ReplicableElementHandlerHelper.getInstance(context).getTarget(context))) {
          // We are adding an element in the REC according to the RPL, so the user has selected an element from the RPL, we don't add the new REC element in the
          // RPL
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @param element
   * @return
   */
  protected Collection<EObject> getRelatedElements(EObject element) {
    return Collections.singleton(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getDefaultLocation(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context) {
    if ((link == null) || (link.getTarget() == null)) {
      return null;
    }

    if (defaultLocations.containsKey(link)) {
      return defaultLocations.get(link);
    }

    // Now we are looking for a feature according to related rule and if we can add element to one of the retrieve possible container, we return it
    IRuleAttachment arule = getRule(link.getTarget(), context);

    for (Iterator<EObject> elementsContainers = retrieveDefaultContainers(link, oppositeLink, context); elementsContainers.hasNext();) {

      EObject targetContainer = elementsContainers.next();

      // If REC and RPL are not located in the same resource, we exclude all containers not located in the targetElement's resource.
      if (isInvalidResourceLocation(link, targetContainer, context)) {
        continue;
      }
      if (!isValidContainement(targetContainer, link.getTarget())) {
        continue;
      }

      EStructuralFeature sourceFeature = link.getTarget().eContainingFeature();
      EStructuralFeature targetFeature = sourceFeature;
      if (arule != null) {
        // If we have a rule, we use with priority rule information
        targetFeature = getFeature(arule, link.getTarget(), link.getTarget(), targetContainer, context);
        if (((targetFeature == null) || (targetFeature == sourceFeature)) && (link.getOrigin() != null) && (link.getOrigin().getTarget() != null)) {
          targetFeature = getFeature(arule, link.getOrigin().getTarget(), link.getTarget(), targetContainer, context);
        }
      }

      if (targetFeature != null && AttachmentHelper.getInstance(context).isApplicable(targetContainer.eClass(), targetFeature)) {
          defaultLocations.put(link, targetContainer);
          return targetContainer;
      }
    }
    
    EObject defaultContainer = retrieveDefaultContainerFromRule(arule, link, oppositeLink, context);
    defaultLocations.put(link, defaultContainer);
    return defaultContainer;
    
  }

  private EObject retrieveDefaultContainerFromRule(IRuleAttachment arule, CatalogElementLink link,
      CatalogElementLink oppositeLink, IContext context) {

    boolean isLinkFromSource = ReplicableElementHandlerHelper.getInstance(context).isFromSource(context, link);
    
    try {
      if (arule != null) {
        LocationTraceabilityHandler.update(isLinkFromSource, context);
        EObject result = arule.retrieveDefaultContainer(oppositeLink.getTarget(), link.getTarget(), context);

        if (result != null) {
          if (!isValidContainement(result, link.getTarget())) {
            return null;
          }
          // If REC and RPL are not located in the same resource, we exclude all containers not located in the destination's resource.
          if (isInvalidResourceLocation(link, result, context)) {
            return null;
          }
        }

        return result;
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return null;
  }

  /**
   * @param source
   * @param target
   * @param currentLocation
   * @return
   */
  @Override
  public EStructuralFeature getFeature(EObject source, EObject target, EObject currentLocation, IContext context) {
    if (source == null) {
      return null;
    }
    IRuleAttachment arule = getRule(source, context);
    return getFeature(arule, source, target, currentLocation, context);
  }

  protected EStructuralFeature getFeature(IRuleAttachment rule, EObject source, EObject target, EObject currentLocation, IContext context) {
    if (source == null) {
      return null;
    }
    if (rule != null) {
      EStructuralFeature targetFeature = rule.retrieveTargetContainementFeature(source, target, currentLocation, context);
      if (targetFeature != null && AttachmentHelper.getInstance(context).isApplicable(currentLocation.eClass(), targetFeature)) {
        return targetFeature;
      }
    }
    return source.eContainingFeature();
  }


  protected Iterator<EObject> retrieveDefaultContainers(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context) {

    Collection<Object> elementsContainers = new LinkedHashSet<>(); // order is important!

    retrieveDefaultContainersForComposite(link, elementsContainers, context);

    String parentLocator = getParentLocator(context);
    if (parentLocator == null || IReConstants.LOCATOR_OPTION_DEFAULT.equals(parentLocator) || IReConstants.LOCATOR_OPTION_MANUAL.equals(parentLocator)) {
      retrieveDefaultContainersFromSelection(link, oppositeLink, elementsContainers, context);
      retrieveDefaultContainersFromSiblingLinks(link, elementsContainers, false, context);

    } else if (IReConstants.LOCATOR_OPTION_SPECIFIC_PACKAGES.equals(parentLocator)){

      String command = (String) context.get(IReConstants.COMMAND__CURRENT_VALUE);
      if (IReConstants.COMMAND__CREATE_A_REPLICA_FROM_REPLICABLE.equals(command)) {
        retrieveDefaultSpecificPackageContainer(link, oppositeLink, elementsContainers, context);
        retrieveDefaultContainersFromSelection(link, oppositeLink, elementsContainers, context);
        retrieveDefaultContainersFromSiblingLinks(link, elementsContainers, true, context);
      }

      if (IReConstants.COMMAND__UPDATE_A_REPLICA_FROM_REPLICABLE.equals(command)) {
        retrieveDefaultContainersFromSiblingLinks(link, elementsContainers, true, context);
        retrieveDefaultSpecificPackageContainer(link, oppositeLink, elementsContainers, context);
        retrieveDefaultContainersFromSelection(link, oppositeLink, elementsContainers, context);
      }

    }

    retrieveDefaultContainersFromLocationSourceProperty(oppositeLink, elementsContainers, context);

    return retrieveDefaultContainersIterator(elementsContainers);
  }

  private Iterator<EObject> retrieveDefaultContainersIterator(Collection<?> containersAndSuppliers){

    final Iterator<?> nested = containersAndSuppliers.iterator();
    Iterator<EObject> result = new Iterator<EObject>() {
      @Override
      public boolean hasNext() {
        return nested.hasNext();
      }

      @Override
      public EObject next() {
        Object next = nested.next();
        if (next instanceof EObject) {
          return (EObject) next;
        }
        if (next instanceof Supplier<?>) {
          return ((Supplier<EObject>) next).get();
        }
        return null;
      }

      @Override
      public void remove() {
        nested.remove();
      }
    };
    return result;
  }


  private void retrieveDefaultSpecificPackageContainer(CatalogElementLink link, CatalogElementLink oppositeLink, Collection<? super Supplier<EObject>> elementsContainers, IContext context) {
    Supplier<EObject> pkgSupplier = getSpecificPackage(link, oppositeLink, context);
    if (pkgSupplier != null) {
      elementsContainers.add(pkgSupplier);
    }
  }

  private String getParentLocator(IContext context) {
    String result = null;
    String scope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context, scope);
    IProperty parentLocatorProperty = propertyContext.getProperties().getProperty(IReConstants.PROPERTY__PARENT_LOCATOR);
    if (parentLocatorProperty != null) { // null during "Update REC from selected RPL"
      result = (String) propertyContext.getCurrentValue(parentLocatorProperty);
    }
    return result;
  }

  /**
   * Finds a supplier that can provide a specific package for the given link's target element. This default
   * implementation returns null, subclasses are expected to override.
   *
   * @param link the link for whose target element a package supplier is needed
   * @param oppositeLink the opposing link
   * @param context the transition context
   * @return
   */
  protected Supplier<EObject> getSpecificPackage(CatalogElementLink link, CatalogElementLink oppositeLink, IContext context) {
    return null;
  }

  // We also add for a CatalogElementLink to a CatalogElement, link.getSource().eContainer()
  private void retrieveDefaultContainersFromLocationSourceProperty(CatalogElementLink link, Collection<? super EObject> elementsContainers,
      IContext context) {
    String scope = (String) context.get(ITransitionConstants.OPTIONS_SCOPE);
    IPropertyContext propertyContext = ((IPropertyHandler) OptionsHandlerHelper.getInstance(context)).getPropertyContext(context, scope);

    if (link.getTarget() instanceof CatalogElement) {
      CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context).getSource(context);
      if ((sourceElement != null) && sourceElement.equals(link.getSource())) {
        IProperty property = propertyContext.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_SOURCE);
        if (property != null) {
          Object value = propertyContext.getCurrentValue(property);
          if (value instanceof EObject) {
            elementsContainers.add((EObject) propertyContext.getCurrentValue(property));
          }
        }
      }
      CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context).getTarget(context);
      if ((targetElement != null) && targetElement.equals(link.getSource())) {

        IProperty property = propertyContext.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET);
        if (property != null) {
          Object value = propertyContext.getCurrentValue(property);
          if (value instanceof EObject) {
            elementsContainers.add((EObject) propertyContext.getCurrentValue(property));
          }
        }
      }
    }
  }

  // We look for a Link of a brother of linkSource (same eContainer) in the target, and then, we retrieve its container,
  // optionally preferring those that have the same target element type
  private void retrieveDefaultContainersFromSiblingLinks(CatalogElementLink link, Collection<? super EObject> elementsContainers,
      boolean preferSameType, IContext context) {
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);
    Collection<CatalogElementLink> links = ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(element);

    Collection<EObject> siblings = new ArrayList<>();

    for (CatalogElementLink linkA : links) {
      if ((linkA == null) || ((linkA.getOrigin() == null) || (linkA.getOrigin().getTarget() == null)) || (linkA.getOrigin().getTarget().eContainer() == null)) {
        continue; // invalid link
      }
      if ((link == null) || ((link.getOrigin() == null) || (link.getOrigin().getTarget() == null))) {
        continue; // invalid link
      }
      if ((linkA.equals(link))) {
        continue; // not a brother
      }
      if (!linkA.getOrigin().getTarget().eContainer().equals(link.getOrigin().getTarget().eContainer())) {
        continue; // not a brother
      }
      siblings.add(linkA.getTarget());
    }


    if (preferSameType) {
      for (Iterator<EObject> it = siblings.iterator(); it.hasNext(); ) {
        EObject next = it.next();
        if (link != null && next.eClass() == link.getTarget().eClass()) {
          it.remove();
          elementsContainers.add(next.eContainer());
        }
      }
    }

    for (EObject e : siblings) {
      elementsContainers.add(e.eContainer());
    }

  }


  // We look in the selection if there is a compatible element to store the linkSource (the rack when instanciating the card)
  private void retrieveDefaultContainersFromSelection(CatalogElementLink link, CatalogElementLink oppositeLink,
      Collection<? super EObject> elementsContainers, IContext context) {
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection != null) {
      for (EObject selectedItem : selection) {
        for (EObject item : getRelatedElements(selectedItem)) {
          if (isInvalidResourceLocation(link, item, context)) {
            continue;
          }
          // if the selection is a REC, we add the container of the type (thus in the REC) of the RPL element as a possible location
          if (item instanceof CatalogElement) {
              // specific case when parameters are reversed
              if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(context.get(IReConstants.COMMAND__CURRENT_VALUE))) {
              	elementsContainers.add(oppositeLink.getTarget().eContainer());
              } else {
                // nominal case
              	elementsContainers.add(link.getOrigin().getTarget().eContainer());
              }
              
          } else {
            if (isInitialSelectionValidContainer(item, link, context)) {
              elementsContainers.add(item);
            }
          }
        }
      }
    }
  }

  private void retrieveDefaultContainersForComposite(CatalogElementLink link, Collection<? super EObject> elementsContainers,
      IContext context) {
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context).getInitialTarget(context);

    CatalogElement recUsingSource = null;
    CatalogElement recUsingTarget = null;

    // For a link stored in catalogElement used "somewhere", if the TARGET catalogElement is used in replica of "somewhere", we can
    // find a container for the source which is source.target.eContainer<-replicaOfSomewhere
    for (CatalogElement referencingElement : ReplicableElementHandlerHelper.getInstance(context).getLinkingReplicableElements(context,
        Collections.singletonList((Object) link.getSource()))) {
      for (CatalogElement referencingElement2 : ReplicableElementHandlerHelper.getInstance(context).getLinkingReplicableElements(context,
          Collections.singletonList((Object) element.getOrigin()))) {
        if (referencingElement.getOrigin().equals(referencingElement2)) {
          recUsingSource = referencingElement2;
          recUsingTarget = referencingElement;
          break;
        }
      }
    }
    if ((recUsingSource != null) && (recUsingTarget != null)) {
      for (CatalogElementLink sLink : ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(recUsingSource)) {
        if (((link.getTarget() != null) && (link.getTarget().eContainer() != null))
            && link.getTarget().eContainer().equals(sLink.getTarget())) {
          for (CatalogElementLink tLink : recUsingTarget.getOwnedLinks()) {
            if ((tLink != null) && ((tLink.getOrigin() != null) && tLink.getOrigin().equals(sLink))) {
              elementsContainers.add(tLink.getTarget());
            }
          }
        }
      }
    }

  }

  /**
   * Returns the rule for the given target element
   */
  public IRuleAttachment getRule(EObject target, IContext context) {

    IRuleAttachment arule = null;
    IRulesHandler ruleHandler = (IRulesHandler) context.get(ITransitionConstants.RULES_HANDLER);
    try {
      if (ruleHandler != null) {
        MappingPossibility mapping = ruleHandler.getApplicablePossibility(target);
        if (mapping != null) {
          IRule<?> rule = ruleHandler.getApplicablePossibility(target).getCompleteRule();
          if (rule instanceof IRuleAttachment) {
            arule = (IRuleAttachment) rule;
          }
        }
      }
    } catch (MappingPossibilityResolutionException exception) {
      // Nothing to report
    }
    return arule;
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
    cleanLocations(context);
    return Status.OK_STATUS;
  }
}
