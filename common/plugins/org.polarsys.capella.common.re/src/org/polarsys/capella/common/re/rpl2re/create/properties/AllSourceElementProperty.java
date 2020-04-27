/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    Collection result = (Collection) ctx.get("ALL_SCOPE_ELEMENTS_PROPERTY");

    if (result == null) {
      result = new ArrayList<EObject>();

      Collection scope = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

      if ((scope != null) && (scope.size() > 0)) {
        for (Object item : scope) {
          if (item instanceof CatalogElement) {
            result.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllElements((CatalogElement) item));

            result.add(item);
          } else {
            result.add(item);
          }
        }
        ctx.put("ALL_SCOPE_ELEMENTS_PROPERTY", toType(result, context));
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
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if ((newValue instanceof Collection) && ((Collection) newValue).isEmpty()) {
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
  public void updatedValue(IProperty property, IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();

    if (IReConstants.PROPERTY__SCOPE.equals(property.getId())
     || IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET.equals(property.getId()))
    {
      ctx.put("ALL_SCOPE_ELEMENTS_PROPERTY", null);
    }
  }

}
