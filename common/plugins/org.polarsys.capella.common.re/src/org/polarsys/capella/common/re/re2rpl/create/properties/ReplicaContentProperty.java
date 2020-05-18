/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.location.LocationHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

import com.google.common.collect.LinkedHashMultimap;

/**
 *
 */
public class ReplicaContentProperty extends AbstractProperty implements ICompoundProperty, IModifiedProperty, IEditableProperty {

  private static String LINKS = "TEMPORARYLINKS";

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    CatalogElement target =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    IContext ctx = (IContext) context.getSource();

    if (ctx.get(LINKS) != null) {
      return ctx.get(LINKS);
    }
    Collection<CatalogElementLink> links = new LinkedHashSet<CatalogElementLink>();

    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));

    ReplicableElementHandlerHelper.getInstance(ctx).createInitialReplica(source, target, ctx);

    Collection<CatalogElementLink> toDelete = new HashSet<CatalogElementLink>();

    if (IReConstants.ENABLE_SUB_INSTANCIATION()) {
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(ctx).getAllElementsLinks(target)) {
        toDelete.add(link.getOrigin());
        links.add(link);
      }

    } else {
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(ctx).getElementsLinks(target)) {
        toDelete.add(link.getOrigin());
        links.add(link);
      }
    }

    LinkedHashMultimap<CatalogElement, CatalogElementLink> toCreate = LinkedHashMultimap.create();

    Collection<CatalogElement> usedSource = new ArrayList<CatalogElement>();
    usedSource.add(source);
    if (IReConstants.ENABLE_SUB_INSTANCIATION()) {
      usedSource.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllUsedReplicableElements(source));
    }

    Collection<CatalogElement> usedTarget = new ArrayList<CatalogElement>();
    usedTarget.add(target);
    if (IReConstants.ENABLE_SUB_INSTANCIATION()) {
      usedTarget.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllUsedReplicableElements(target));
    }

    for (CatalogElement element : usedSource) {
      for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(ctx).getElementsLinks(element)) {
        toCreate.put(element, link);
      }
    }

    for (CatalogElement element : toCreate.keySet()) {
      toCreate.get(element).removeAll(toDelete);

      CatalogElement targetElement = null;
      for (CatalogElement targetElementq : usedTarget) {
        if (targetElementq.getOrigin().equals(element)) {
          targetElement = targetElementq;
          break;
        }
      }

      Collection<CatalogElementLink> targetLinks =
          ReplicableElementHandlerHelper.getInstance(ctx).createTargetLinks(targetElement, toCreate.get(element), ctx);
      links.addAll(targetLinks);
    }

    ctx.put(LINKS, links);
    return links;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();

    Object useDefault = context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__PARENT_LOCATOR));
    boolean isUseDefault = !IReConstants.LOCATOR_OPTION_MANUAL.equals(useDefault);

    HashSet<CatalogElementLink> links = (HashSet<CatalogElementLink>) newValue;
    HashSet<CatalogElementLink> linksInvalid = new HashSet<CatalogElementLink>();

    for (CatalogElementLink link : links) {
      EObject currentLocation = LocationHandlerHelper.getInstance(ctx).getCurrentLocation(link, ctx);
      if (currentLocation != null) {
        continue;
      }

      EObject location = LocationHandlerHelper.getInstance(ctx).getLocation(link, link.getOrigin(), ctx);
      if (location != null) {
        continue;
      }

      if (isUseDefault) {
        EObject defaultLocation = LocationHandlerHelper.getInstance(ctx).getDefaultLocation(link, link.getOrigin(), ctx);
        if (defaultLocation != null) {
          continue;
        }
      }
      linksInvalid.add(link);
    }

    if (!linksInvalid.isEmpty()) {
      return new Status(IStatus.ERROR, IReConstants.PLUGIN_ID, "Some elements need to be stored in the model");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return Collection.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    //Nothing yet+
    IContext ctx = (IContext) context.getSource();

    HashSet<CatalogElementLink> links = (HashSet<CatalogElementLink>) context.getCurrentValue(this);
    Object useDefault = context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__PARENT_LOCATOR));
    boolean isUseDefault = !IReConstants.LOCATOR_OPTION_MANUAL.equals(useDefault);

    if (links != null) {

      for (CatalogElementLink link : links) {
        EObject currentLocation = LocationHandlerHelper.getInstance(ctx).getCurrentLocation(link, ctx);
        if (currentLocation != null) {
          continue;
        }

        EObject location = LocationHandlerHelper.getInstance(ctx).getLocation(link, link.getOrigin(), ctx);
        if (location != null) {
          LocationHandlerHelper.getInstance(ctx).setCurrentLocation(link, location, ctx);
          continue;
        }

        if (isUseDefault) {
          EObject defaultLocation = LocationHandlerHelper.getInstance(ctx).getDefaultLocation(link, link.getOrigin(), ctx);
          if (defaultLocation != null) {
            LocationHandlerHelper.getInstance(ctx).setCurrentLocation(link, defaultLocation, ctx);
            continue;
          }
        }
      }
    }

    CatalogElement replica =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    for (CatalogElementLink link : ReplicableElementHandlerHelper.getInstance(ctx).getAllElementsLinks(replica)) {
      if (ContextScopeHandlerHelper.getInstance(ctx).contains(IReConstants.CREATED_LINKS, link, ctx)) {
        ContextScopeHandlerHelper.getInstance(ctx).add(IReConstants.CREATED_LINKS_TO_KEEP, link, ctx);
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, IReConstants.PROPERTY__PARENT_LOCATOR,
                         IReConstants.PROPERTY__LOCATION_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET.equals(property.getId())
     || IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE.equals(property.getId())
     || IReConstants.PROPERTY__PARENT_LOCATOR.equals(property.getId()))
    {
      IContext ctx = (IContext) context.getSource();
      ctx.put(LINKS, null);
      ReplicableElementHandlerHelper.getInstance(ctx).cleanVirtualLinks(ctx);
      LocationHandlerHelper.getInstance(ctx).cleanLocations(ctx);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModified(IPropertyContext context) {
    return true;
  }

}
