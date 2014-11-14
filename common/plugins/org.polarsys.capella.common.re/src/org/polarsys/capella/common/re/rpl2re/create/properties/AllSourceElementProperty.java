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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class AllSourceElementProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    Collection result = (Collection) context.get("ALL_SCOPE_ELEMENTS_PROPERTY");

    if (result == null) {
      result = new ArrayList<EObject>();

      Collection scope = (Collection) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

      if ((scope != null) && (scope.size() > 0)) {
        for (Object item : scope) {
          if (item instanceof CatalogElement) {
            result.addAll(ReplicableElementHandlerHelper.getInstance(context).getAllElements((CatalogElement) item));

            result.add(item);
          } else {
            result.add(item);
          }
        }
        context.put("ALL_SCOPE_ELEMENTS_PROPERTY", toType(result, context_p));
      }

    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if ((newValue_p instanceof Collection) && ((Collection) newValue_p).isEmpty()) {
      return new Status(IStatus.ERROR, getId(), "Scope should not be empty");
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__SCOPE };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();

    if (IReConstants.PROPERTY__SCOPE.equals(property_p.getId())) {
      context.put("ALL_SCOPE_ELEMENTS_PROPERTY", null);
    }
    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET.equals(property_p.getId())) {
      context.put("ALL_SCOPE_ELEMENTS_PROPERTY", null);
    }
  }

}
