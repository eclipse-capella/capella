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
package org.polarsys.capella.common.re.rpl2re.create.properties;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SuffixesProperty extends org.polarsys.capella.common.re.properties.SuffixesProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    super.getValue(context_p);

    Collection<EObject> scope = (Collection) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));
    Collection<EObject> roots = new HashSet<EObject>();

    CatalogElement target =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    //For all new elements (not yet linked thorugh a CEL, if it is a root element, we add it automatically as suffixable
    IContext context = (IContext) context_p.getSource();
    if (target != null) {
      Collection<EObject> linkedElements = ReplicableElementHandlerHelper.getInstance(context).getElements(target);

      for (EObject object : scope) {
        if (object.eClass().getEStructuralFeature("name") == null) {
          continue;
        }

        if (!AttributesHandlerHelper.getInstance(context).isSuffixableElement(object, context)) {
          continue;
        }
        if (!linkedElements.contains(object) && (object.eContainer() != null) && !(scope.contains(object.eContainer()))) {
          roots.add(object);
        }
      }

      for (EObject root : roots) {
        if (!AttributesHandlerHelper.getInstance(context).isManualSuffixable(root, context)) {
          AttributesHandlerHelper.getInstance(context).setSuffixable(root, true, context);
        }
      }

      //Remove automatic suffixing on childs of all suffixable elements. (it can happen when a parent has been added into the scope while some child were suffixed)
      for (EObject object : scope) {
        if ((object != null) && !AttributesHandlerHelper.getInstance(context).isManualSuffixable(object, context)) {
          if (AttributesHandlerHelper.getInstance(context).isSuffixable(object, context)) {
            EObject parent = object.eContainer();
            while ((parent != null) && !roots.contains(parent)) {
              parent = parent.eContainer();
            }
            if (parent != null) {
              AttributesHandlerHelper.getInstance(context).setSuffixable(object, false, context);
            }
          }
        }
      }

    }

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__SCOPE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {

  }
}
