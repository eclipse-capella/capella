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
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.options.IPropertyHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.rules.AbstractRule;
import org.polarsys.kitalpha.transposer.rules.handler.api.IRulesHandler;
import org.polarsys.kitalpha.transposer.rules.handler.exceptions.possibilities.MappingPossibilityResolutionException;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;

/**
 *
 */
public class DefaultAttributeHandler implements IAttributeHandler {

  HashSet<EObject> _suffixable = new HashSet<EObject>();

  HashSet<EObject> _msuffixable = new HashSet<EObject>();

  HashMap<EObject, String> _mNames = new HashMap<EObject, String>();

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

  public String getDefaultName(EObject object_p, IContext context_p, IPropertyContext pContext_p) {
    return EObjectLabelProviderHelper.getText(object_p);
  }

  public boolean hasCustomName(EObject object_p, IContext context_p) {
    return _mNames.containsKey(object_p);
  }

  public Collection<EObject> getCustomNameElements(IContext context_p) {
    return _mNames.keySet();
  }

  public String getCustomName(EObject object_p, IContext context_p) {
    if (_mNames.containsKey(object_p)) {
      return _mNames.get(object_p);
    }
    return null;
  }

  public void setCustomName(EObject object_p, String value_p, IContext context_p) {
    _mNames.put(object_p, value_p);
  }

  public String getCurrentName(EObject object_p, IContext context_p, IPropertyContext pContext_p) {
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

      if (ContextScopeHandlerHelper.getInstance(context_p).contains(IReConstants.CREATED_LINKS, object_p, context_p)) {

        if (AttributesHandlerHelper.getInstance(context_p).isSuffixable(((CatalogElementLink) object_p).getOrigin().getTarget(), context_p)) {
          IProperty property = pContext_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX);
          String suffix = (String) pContext_p.getCurrentValue(property);
          value += suffix;
        }
      }

    } else if (object_p instanceof CatalogElement) {
      IProperty property = pContext_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET);
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void unsetCustomName(EObject element_p, String value_p, IContext iContext_p, IPropertyContext pContext_p) {
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

}
