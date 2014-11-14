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
package org.polarsys.capella.common.re.handlers.attributes;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.AbstractRule;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 *
 */
public class DefaultAttributeHandler implements IAttributeHandler {

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
   * {@inheritDoc}
   */
  @Override
  public boolean isUpdatable(EObject element_p, EStructuralFeature feature_p, IContext context_p) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EStructuralFeature> getNonUpdatableFeatures(EObject element_p, IContext context_p) {
    return Collections.emptyList();
  }

  public String getDefaultName(EObject object_p, IContext context_p, IPropertyContext pContext_p) {
    return EObjectLabelProviderHelper.getText(object_p);
  }

  HashSet<EObject> _suffixable = new HashSet<EObject>();

  HashSet<EObject> _msuffixable = new HashSet<EObject>();

  HashMap<EObject, String> _mNames = new HashMap<EObject, String>();

  public boolean hasCustomName(EObject object_p, IContext context_p) {
    return _mNames.containsKey(object_p);
  }

  public Collection<EObject> getCustoms(IContext context_p) {
    return _mNames.keySet();
  }

  public String getCustomName(EObject object_p, IContext context_p) {
    if (_mNames.containsKey(object_p)) {
      return _mNames.get(object_p);
    }
    return null;
  }

  public void setName(EObject object_p, String value_p, IContext context_p, IPropertyContext pContext_p) {
    _mNames.put(object_p, value_p);
  }

  public String getName(EObject object_p, IContext context_p, IPropertyContext pContext_p) {
    String value = "";
    if (_mNames.containsKey(object_p)) {
      return _mNames.get(object_p);
    }

    if (object_p instanceof CatalogElementLink) {
      if (!(((CatalogElementLink) object_p).getTarget() instanceof CatalogElement)) {
        value += getDefaultName(((CatalogElementLink) object_p).getTarget(), context_p, pContext_p);

      } else {
        value += getDefaultName(((CatalogElementLink) object_p).getTarget(), context_p, pContext_p);
      }

      if (ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.VIRTUAL_LINKS, object_p, context_p)) {

        if (AttributesHandlerHelper.getInstance(context_p).isSuffixable(((CatalogElementLink) object_p).getOrigin().getTarget(), context_p)) {
          IProperty property = pContext_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX);
          String suffix = (String) pContext_p.getCurrentValue(property);
          value += suffix;
        }
      }

    } else if (object_p instanceof CatalogElement) {
      IProperty property = pContext_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET);
      Object replica = pContext_p.getCurrentValue(property);

      if (object_p.equals(replica)) {
        property = pContext_p.getProperties().getProperty("targetName");
        String suffix = (String) pContext_p.getCurrentValue(property);
        value += suffix;
      } else {
        value += getDefaultName(object_p, context_p, pContext_p);
      }

    } else {
      value += getDefaultName(object_p, context_p, pContext_p);
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSuffixable(EObject object_p, boolean value_p, IContext context_p) {
    if (object_p instanceof CatalogElement) {
      return;
    }
    if (!value_p) {
      _suffixable.remove(object_p);
    } else {
      _suffixable.add(object_p);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setManualSuffixable(EObject object_p, boolean value_p, IContext context_p) {
    if (object_p instanceof CatalogElement) {
      return;
    }
    _msuffixable.add(object_p);
    if (!value_p) {
      _suffixable.remove(object_p);
    } else {
      _suffixable.add(object_p);
    }
  }

  public boolean isManualSuffixable(Object object_p, IContext context_p) {
    return _msuffixable.contains(object_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixable(Object object_p, IContext context_p) {
    return _suffixable.contains(object_p);
  }

  HashMap<EObject, EObject> _parents = new HashMap<EObject, EObject>();

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCurrentLocation(CatalogElementLink object_p, IContext context_p) {
    if (_parents.containsKey(object_p)) {
      return _parents.get(object_p);
    }

    return null;
  }

  private boolean isInvalidResourceLocation(CatalogElementLink link_p, EObject location, IContext context_p) {

    if (location == null) {
      return true;
    }
    Collection<EObject> elements = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
    CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

    Resource destinationResource = elements.iterator().next().eResource();

    //Retrieve if the given Link is from the Source or the Target
    boolean isSource = false;
    CatalogElement elementSource = link_p.getSource();
    if (elementSource.equals(sourceElement)) {
      isSource = true;
    } else if (elementSource.equals(targetElement)) {
      isSource = false;
    }

    //Retrieve if the selected Resource is for the Source or the Target
    boolean isSelectionForSource = false;
    String value = (String) context_p.get(IReConstants.COMMAND__CURRENT_VALUE);
    if (IReConstants.COMMAND__UPDATE_DEFINITION_REPLICA_FROM_REPLICA.equals(value)) {
      isSelectionForSource = true;

    } else if (IReConstants.COMMAND__CREATE_REPLICABLE_ELEMENT.equals(value)) {
      isSelectionForSource = true;
    } //when creating a RPL, user can select the REC instead of a real location..., isSelectionForSource should be true in that case

    //Change the resource according to selection
    Resource sourceResource = (sourceElement == null) || (sourceElement.eResource() == null) ? destinationResource : sourceElement.eResource();
    Resource targetResource = (targetElement == null) || (targetElement.eResource() == null) ? destinationResource : targetElement.eResource();
    if (isSelectionForSource) {
      sourceResource = destinationResource;
    } else {
      targetResource = destinationResource;
    }

    //If REC and RPL are not located in the same resource, we exclude all containers not located in the targetElement's resource.
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
    _parents.put(object_p, container_p);
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

    //Retrieve link.getTarget().eContainer()<-elementLink
    CatalogElementLink container = getLinkContainer(link, context_p);
    if (container != null) {
      return container;
    }

    //Customization for Capella, we put component into their part
    CatalogElementLink relatedElement = getLinkRelatedElement(link, context_p);
    if (relatedElement != null) {
      return relatedElement;
    }

    if (!ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.VIRTUAL_LINKS, object_p, context_p)) {
      if (!isInvalidResourceLocation(link, object_p.getTarget().eContainer(), context_p)) {
        return object_p.getTarget().eContainer();
      }
    }

    //For all elements added in REC but not yet in RPL
    //we add it into the link.getTarget().eContainer()<-elementLink
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);
    for (CatalogElementLink linkA : ReplicableElementHandlerHelper.getInstance(context_p).getAllElementsLinks(element)) {
      if (((linkA.getOrigin() != null) && (linkA.getOrigin().getTarget() != null)) && (link.getTarget() != null)
          && linkA.getOrigin().getTarget().equals(link.getTarget().eContainer())) {
        return linkA;
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getDefaultLocation(CatalogElementLink source_p, CatalogElementLink origin_p, IContext context_p) {
    CatalogElement element = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);
    CatalogElementLink linkSource = source_p;

    if ((linkSource == null) || (linkSource.getTarget() == null) || (linkSource.getSource() == null)) {
      return null;
    }

    //Retrieve container from the selection
    Collection<EObject> elementsContainers = new LinkedHashSet<EObject>(); //order is important!

    CatalogElement recUsingSource = null;
    CatalogElement recUsingTarget = null;

    //For a link stored in catalogElement used "somewhere", if the TARGET catalogElement is used in replica of "somewhere", we can
    //find a container for the source which is source_p.target.eContainer<-replicaOfSomewhere
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

    //We look in the selection if there is a compatible element to store the linkSource (the rack when instanciating the card)
    Collection<EObject> elements = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (elements != null) {

      for (EObject ppp : elements) {

        for (EObject item : getRelatedElements(ppp)) {
          if (isInvalidResourceLocation(linkSource, item, context_p)) {
            continue;
          }
          if (item instanceof CatalogElement) {
            //to allow creation of a card into a rack.
            //if user select "update rpl from rec" selecting target rpl, we should not add elements to elements of rpl !
            if (!(item.equals(element))) {
              elementsContainers.addAll(ReplicableElementHandlerHelper.getInstance(context_p).getAllElements((CatalogElement) item));
            }
          } else {
            if (isInitialSelectionValidContainer(item, linkSource, context_p)) {
              elementsContainers.add(item);
            }
          }
        }

      }
    }

    //We look for a Link of a brother of linkSource (same eContainer) in the target, and then, we retrieve its container
    for (CatalogElementLink linkA : ReplicableElementHandlerHelper.getInstance(context_p).getElementsLinks(element)) {

      if ((linkA == null) || ((linkA.getOrigin() == null) || (linkA.getOrigin().getTarget() == null)) || (linkA.getOrigin().getTarget().eContainer() == null)) {
        continue; //invalid link
      }
      if ((linkSource == null) || ((linkSource.getOrigin() == null) || (linkSource.getOrigin().getTarget() == null))) {
        continue; //invalid link
      }
      if ((linkA.equals(linkSource))) {
        continue; //not a brother
      }
      if (!linkA.getOrigin().getTarget().eContainer().equals(linkSource.getOrigin().getTarget().eContainer())) {
        continue; //not a brother
      }

      for (CatalogElementLink linkB : ReplicableElementHandlerHelper.getInstance(context_p).getElementsLinks(element)) {
        if ((linkB == null) || (linkB.getTarget() == null) || (linkB.getTarget().eContainer() == null)) {
          continue; //invalid link
        }
        if ((linkB.getOrigin() != null) && linkB.getOrigin().equals(linkA.getOrigin())) {
          elementsContainers.add(linkB.getTarget().eContainer()); //retrieve the container
        }
      }
    }

    //We also add for a CatalogElementLink to a CatalogElement, link.getSource().eContainer()
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

    //Now we are looking for a feature according to related rule and if we can add element to one of the retrieve possible container, we return it
    AbstractRule arule = getRule(linkSource.getTarget(), context_p);
    if (arule != null) {

      for (EObject targetContainer : elementsContainers) {

        //If REC and RPL are not located in the same resource, we exclude all containers not located in the targetElement's resource.
        if (isInvalidResourceLocation(linkSource, targetContainer, context_p)) {
          continue;
        }

        if (!isValidContainement(targetContainer, linkSource.getTarget())) {
          continue;
        }

        EStructuralFeature targetFeature = arule._getTargetContainementFeature(linkSource.getTarget(), linkSource.getTarget(), targetContainer, context_p);
        if ((targetFeature == null) && (linkSource.getOrigin() != null) && (linkSource.getOrigin().getTarget() != null)) {
          targetFeature = arule._getTargetContainementFeature(linkSource.getOrigin().getTarget(), linkSource.getTarget(), targetContainer, context_p);
        }
        if (targetFeature != null) {
          if (AttachmentHelper.getInstance(context_p).isApplicable(targetContainer.eClass(), targetFeature)) {
            return targetContainer;
          }
        }
      }

      try {
        CatalogElementLink source = linkSource.getOrigin();
        EObject other = TraceabilityHandlerHelper.getInstance(context_p).retrieveSourceElements(linkSource.getTarget(), context_p).iterator().next();
        EObject result = arule._getDefaultContainer(source == null ? other : source.getTarget(), linkSource.getTarget(), context_p);

        if (result != null) {
          if (!isValidContainement(result, linkSource.getTarget())) {
            int i = 0;
            return null;
          }
          //If REC and RPL are not located in the same resource, we exclude all containers not located in the destination's resource.
          if (isInvalidResourceLocation(linkSource, result, context_p)) {
            return null;
          }
        }

        return result;

      } catch (Exception exception_p) {
        exception_p.printStackTrace();
      }
    }

    return null;
  }

  /**
   * Returns the rule for the given target_p element
   */
  protected AbstractRule getRule(EObject target_p, IContext context_p) {

    AbstractRule arule = null;
    IRulesHandler ruleHandler = (IRulesHandler) context_p.get(ITransitionConstants.RULES_HANDLER);
    try {
      if (ruleHandler != null) {
        MappingPossibility mapping = ruleHandler.getApplicablePossibility(target_p);
        if (mapping != null) {
          IRule<?> rule = ruleHandler.getApplicablePossibility(target_p).getCompleteRule();
          if ((rule != null) && (rule instanceof AbstractRule)) {
            arule = (AbstractRule) rule;
          }
        }
      }
    } catch (MappingPossibilityResolutionException exception_p) {
      //Nothing to report
    }

    return arule;
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
   * @param element_p
   * @return
   */
  protected Collection<EObject> getRelatedElements(EObject element_p) {
    return Collections.singleton(element_p);
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
          //We are adding an element in the REC according to the RPL, so the user has selected an element from the RPL, we don't add the new REC element in the RPL
          return false;
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<CatalogElementLink> createTargetLinks(CatalogElement replicable_p, HashSet<CatalogElementLink> setLinks_p, IContext context_p) {
    HashSet<CatalogElementLink> newLinks = new HashSet<CatalogElementLink>();

    for (CatalogElementLink link : setLinks_p) {
      if (link.getOrigin() != replicable_p) {
        boolean added = false;
        for (CatalogElementLink link2 : replicable_p.getOwnedLinks()) {
          if ((link2.getTarget() != null) && link2.getTarget().equals(link.getTarget())) {
            newLinks.add(link2);
            added = true;
          }
        }

        if (!added) {
          CatalogElementLink link2 = ReFactory.eINSTANCE.createCatalogElementLink();
          link2.setSource(replicable_p);
          link2.setTarget(link.getTarget());
          link2.setOrigin(link);
          newLinks.add(link2);
          ContextScopeHandlerHelper.getInstance(context_p).add(IReConstants.VIRTUAL_LINKS, link2, context_p);
        }
      }
    }

    replicable_p.getOwnedLinks().addAll(newLinks);

    return newLinks;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unsetName(EObject element_p, String value_p, IContext iContext_p, IPropertyContext pContext_p) {
    if (_mNames.containsKey(element_p)) {
      _mNames.remove(element_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixableElement(Object object_p, IContext context_p) {
    return true;
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

}
