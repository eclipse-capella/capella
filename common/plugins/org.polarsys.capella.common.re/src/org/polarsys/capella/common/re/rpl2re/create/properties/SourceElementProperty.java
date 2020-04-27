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

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class SourceElementProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    Collection result = (Collection) ctx.get("SCOPE_ELEMENTS_PROPERTY");

    if (result == null) {
      result = new HashSet<EObject>();
      Collection<Object> selection = (Collection<Object>) ctx.get(ITransitionConstants.TRANSITION_SOURCES);
      if ((selection != null) && (selection.size() > 0)) {
        result = DependenciesHandlerHelper.getInstance(ctx).getScopeElements((Collection) selection, (Collection) selection, ctx);
        ctx.put("SCOPE_ELEMENTS_PROPERTY", toType(result, context));
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

    IContext ctx = (IContext) context.getSource();

    Collection<EObject> toRemove = new HashSet<EObject>();
    Collection<EObject> elementsToAdd = new HashSet<EObject>();

    if (value instanceof Collection) {

      for (Object object : (Collection) value) {
        if (object instanceof CatalogElement) {
          toRemove.addAll(ReplicableElementHandlerHelper.getInstance(ctx).getAllElements((CatalogElement) object));
        }
      }
      ((Collection) value).removeAll(toRemove);

      elementsToAdd = DependenciesHandlerHelper.getInstance(ctx).getComplementaryScopeElements((Collection) value, (Collection) value, ctx);

      ((Collection) value).clear();
      ((Collection) value).addAll(elementsToAdd);
    }

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
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();

    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET.equals(property.getId())) {
      ctx.put("SCOPE_ELEMENTS_PROPERTY", null);
    }
  }

}
