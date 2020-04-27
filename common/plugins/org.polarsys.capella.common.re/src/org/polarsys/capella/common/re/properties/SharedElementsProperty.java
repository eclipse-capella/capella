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
package org.polarsys.capella.common.re.properties;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SharedElementsProperty extends AbstractProperty implements ICompoundProperty, IRestraintProperty, IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {

    IContext ctx = (IContext) context.getSource();
    if (!ctx.exists(IReConstants.PROPERTY__SHARED_ELEMENTS) || (ctx.get(IReConstants.PROPERTY__SHARED_ELEMENTS) == null)) {
      Collection<EObject> scopeElements = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

      if (scopeElements == null) {
        ctx.put(IReConstants.PROPERTY__SHARED_ELEMENTS, Collections.emptyList());
      } else {
        Object values = DependenciesHandlerHelper.getInstance(ctx).getSharedElements(scopeElements, scopeElements, ctx);
        ctx.put(IReConstants.PROPERTY__SHARED_ELEMENTS, new HashSet<Object>((Collection) values));
      }
    }

    return ctx.get(IReConstants.PROPERTY__SHARED_ELEMENTS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context) {

    IContext ctx = (IContext) context.getSource();
    Collection<EObject> scopeElements = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

    if (scopeElements == null) {
      return Collections.emptyList();
    }
    Object values = DependenciesHandlerHelper.getInstance(ctx).getSharedElements(scopeElements, scopeElements, ctx);
    return new HashSet<Object>((Collection) values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {

    if (newValue instanceof Collection) {
      if (!((Collection) newValue).isEmpty()) {
        return new Status(IStatus.INFO, getId(), "REC have references to external elements");
      }
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
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__SCOPE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, IReConstants.PROPERTY__LOCATION_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    ctx.put(IReConstants.PROPERTY__SHARED_ELEMENTS, null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    //Nothing here
  }
}
