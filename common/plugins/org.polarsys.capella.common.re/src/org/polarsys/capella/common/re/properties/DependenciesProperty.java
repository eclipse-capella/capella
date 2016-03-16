/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DependenciesProperty extends AbstractProperty implements ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {

    IContext ctx = (IContext) context.getSource();
    if (!ctx.exists(IReConstants.PROPERTY__DEPENDENCIES) || (ctx.get(IReConstants.PROPERTY__DEPENDENCIES) == null)) {
      Collection<EObject> scopeElements = (Collection) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

      if (scopeElements == null) {
        ctx.put(IReConstants.PROPERTY__DEPENDENCIES, Collections.emptyList());
      } else {
        Object values = DependenciesHandlerHelper.getInstance(ctx).getDependencies(scopeElements, scopeElements, ctx);
        ctx.put(IReConstants.PROPERTY__DEPENDENCIES, new HashSet<Object>((Collection) values));
      }
    }

    return ctx.get(IReConstants.PROPERTY__DEPENDENCIES);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if (newValue instanceof Collection) {
      if (!((Collection) newValue).isEmpty()) {
        return new Status(IStatus.WARNING, getId(), "Some elements have missing dependencies");
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
    return new String[] { IReConstants.PROPERTY__SCOPE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    ctx.put(IReConstants.PROPERTY__DEPENDENCIES, null);
  }
}
