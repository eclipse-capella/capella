/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultAttributeHandler implements IAttributeHandler {

  HashSet<EObject> suffixable = new HashSet<EObject>();

  HashSet<EObject> msuffixable = new HashSet<EObject>();

  HashMap<EObject, String> mNames = new HashMap<EObject, String>();

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

  public String getDefaultName(EObject object, IContext context, IPropertyContext pContext) {
    return EObjectLabelProviderHelper.getText(object);
  }

  public boolean hasCustomName(EObject object, IContext context) {
    return mNames.containsKey(object);
  }

  public Collection<EObject> getCustomNameElements(IContext context) {
    return mNames.keySet();
  }

  public String getCustomName(EObject object, IContext context) {
    if (mNames.containsKey(object)) {
      return mNames.get(object);
    }
    return null;
  }

  public void setCustomName(EObject object, String value, IContext context) {
    mNames.put(object, value);
  }

  public String getCurrentName(EObject object, IContext context, IPropertyContext pContext) {
    String value = "";
    if (mNames.containsKey(object)) {
      return mNames.get(object);
    }

    if (object instanceof CatalogElementLink) {
      CatalogElementLink catalogElementLink = (CatalogElementLink) object;
      CatalogElementLink origin = catalogElementLink.getOrigin();
      
      value += getDefaultName(catalogElementLink.getTarget(), context, pContext);

      // If the element is suffixed, we want to append the suffix of the origin element.
      if (origin != null && AttributesHandlerHelper.getInstance(context).isSuffixable(origin.getTarget(), context)) {
        IProperty property = pContext.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX);
        String suffix = (String) pContext.getCurrentValue(property);

        // If element name was ending by original suffix, we remove it before appending the new suffix
        String originalSuffix = ((CatalogElement) catalogElementLink.eContainer()).getSuffix();

        if (originalSuffix == null) {
          value += suffix;
        } else if (value.endsWith(originalSuffix)) {
          value = value.substring(0, value.length() - originalSuffix.length());
          value += suffix;
        } else if (ContextScopeHandlerHelper.getInstance(context).contains(IReConstants.CREATED_LINKS, catalogElementLink, context)) {
          value += suffix;
        }
        // If element name doesn't have the same name than the source name, we ignore it
        // String oriName = getDefaultName(((CatalogElementLink) object).getOrigin().getTarget(), context, pContext);
        // if (value.equals(oriName)) {
        // value += suffix;
        // }
      }

    } else if (object instanceof CatalogElement) {
      // If it's the target replica, we display the name of the property TARGET_NAME. Otherwise, we display the default name
      IProperty property = pContext.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
      Object replica = pContext.getCurrentValue(property);

      if (object.equals(replica)) {
        property = pContext.getProperties().getProperty(IReConstants.PROPERTY__TARGET_NAME);
        String replicaName = (String) pContext.getCurrentValue(property);
        value += replicaName;
      } else {
        value += getDefaultName(object, context, pContext);
      }

    } else {
      value += getDefaultName(object, context, pContext);
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSuffixable(EObject object, boolean value, IContext context) {
    if (object instanceof CatalogElement) {
      return;
    }
    if (!value) {
      suffixable.remove(object);
    } else {
      suffixable.add(object);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setManualSuffixable(EObject object, boolean value, IContext context) {
    if (object instanceof CatalogElement) {
      return;
    }
    msuffixable.add(object);
    if (!value) {
      suffixable.remove(object);
    } else {
      suffixable.add(object);
    }
  }

  public boolean isManualSuffixable(Object object, IContext context) {
    return msuffixable.contains(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixable(Object object, IContext context) {
    return suffixable.contains(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unsetCustomName(EObject element, String value, IContext iContext, IPropertyContext pContext) {
    if (mNames.containsKey(element)) {
      mNames.remove(element);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixableElement(Object object, IContext context) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EStructuralFeature getSuffixableFeature(EObject object, IContext context) {
    return object.eClass().getEStructuralFeature("name");
  }

}
