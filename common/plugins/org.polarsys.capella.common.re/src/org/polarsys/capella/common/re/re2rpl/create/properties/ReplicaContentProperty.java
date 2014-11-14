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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;
import java.util.HashSet;

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
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicaContentProperty extends AbstractProperty implements ICompoundProperty, IModifiedProperty, IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    CatalogElement replicable =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__TARGET));

    IContext context = (IContext) context_p.getSource();

    if (context.get("TEMPORARYLINKS") != null) {
      return context.get("TEMPORARYLINKS");
    }

    HashSet<CatalogElement> visited = new HashSet<CatalogElement>();
    HashSet<CatalogElementLink> setLinks = new HashSet<CatalogElementLink>();
    HashSet<CatalogElementLink> setLinksToDelete = new HashSet<CatalogElementLink>();

    //Retrieve all links not being origin of another link => OK
    CatalogElement toSee = replicable;
    while (toSee != null) {
      if (visited.contains(toSee)) {
        continue;
      }

      visited.add(toSee);
      Collection<CatalogElementLink> links = ReplicableElementHandlerHelper.getInstance(context).getElementsLinks(toSee);
      for (CatalogElementLink link : links) {
        setLinks.add(link);
        if (link.getOrigin() != null) {
          setLinksToDelete.add(link.getOrigin());
        }
      }
      toSee = toSee.getOrigin();
    }
    setLinks.removeAll(setLinksToDelete);
    Collection<CatalogElementLink> targetLinks = AttributesHandlerHelper.getInstance(context).createTargetLinks(replicable, setLinks, context);
    context.put("TEMPORARYLINKS", targetLinks);
    return targetLinks;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    System.out.println(0);
    IContext context = (IContext) context_p.getSource();

    Object useDefault = context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__USE_DEFAULT_LOCATION));
    boolean isUseDefault = !(Boolean.FALSE.equals(useDefault));

    HashSet<CatalogElementLink> links = (HashSet<CatalogElementLink>) newValue_p;
    System.out.println(0);
    HashSet<CatalogElementLink> linksInvalid = new HashSet<CatalogElementLink>();

    for (CatalogElementLink link : links) {
      EObject currentLocation = AttributesHandlerHelper.getInstance(context).getCurrentLocation(link, context);
      if (currentLocation != null) {
        continue;
      }

      EObject location = AttributesHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
      if (location != null) {
        continue;
      }

      if (isUseDefault) {
        EObject defaultLocation = AttributesHandlerHelper.getInstance(context).getDefaultLocation(link, link.getOrigin(), context);
        if (defaultLocation != null) {
          continue;
        }
      }
      linksInvalid.add(link);
    }

    if (!linksInvalid.isEmpty()) {
      return new Status(IStatus.WARNING, IReConstants.PLUGIN_ID, "Some elements need to be stored in the model");

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
  public Object toType(Object value_p, IPropertyContext context_p) {
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    //Nothing yet+
    IContext context = (IContext) context_p.getSource();

    HashSet<CatalogElementLink> links = (HashSet) context_p.getCurrentValue(this);
    Object useDefault = context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__USE_DEFAULT_LOCATION));
    boolean isUseDefault = !(Boolean.FALSE.equals(useDefault));

    if (links != null) {

      for (CatalogElementLink link : links) {
        EObject currentLocation = AttributesHandlerHelper.getInstance(context).getCurrentLocation(link, context);
        if (currentLocation != null) {
          continue;
        }

        EObject location = AttributesHandlerHelper.getInstance(context).getLocation(link, link.getOrigin(), context);
        if (location != null) {
          AttributesHandlerHelper.getInstance(context).setCurrentLocation(link, location, context);
          continue;
        }

        if (isUseDefault) {
          EObject defaultLocation = AttributesHandlerHelper.getInstance(context).getDefaultLocation(link, link.getOrigin(), context);
          if (defaultLocation != null) {
            AttributesHandlerHelper.getInstance(context).setCurrentLocation(link, defaultLocation, context);
            continue;
          }
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE, IReConstants.PROPERTY__USE_DEFAULT_LOCATION,
                         IReConstants.PROPERTY__LOCATION_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE.equals(property_p.getId())) {
      IContext context = (IContext) context_p.getSource();
      context.put("TEMPORARYLINKS", null);
      ReplicableElementHandlerHelper.getInstance(context).cleanVirtualLinks(context);
    }
    //Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModified(IPropertyContext context_p) {
    return true;
  }

}
