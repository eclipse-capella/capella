/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.rpl2re.updatecur.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementProperty extends AbstractProperty implements IEditableProperty, IRestraintProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    try {

      IContext ctx = (IContext) context.getSource();
      CatalogElement replica = (CatalogElement) ctx.get("RE");

      if (replica == null) {

        Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);

        Collection<Object> selection = (Collection<Object>) ctx.get(ITransitionConstants.TRANSITION_SOURCES);
        if (selection.size() > 0) {
          Collection<CatalogElement> uppests =
              ReplicableElementHandlerHelper.getInstance(ctx).getUppestReplicableElement(ctx, (Collection) selectedElements);
          if (!uppests.isEmpty()) {
            replica = uppests.iterator().next();
          }
        }

        ctx.put("RE", replica);
      }

      return replica;
    } catch (Exception e) {

    }
    return null;

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
    CatalogElement element = (CatalogElement) ctx.get("RE");
    if (value instanceof String) {
      element.setName((String) value);
      return element;
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if (newValue instanceof String) {
      if (((String) newValue).length() == 0) {
        return new Status(IStatus.WARNING, getId(), "Should be not empty");
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<?> getChoiceValues(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    return ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedReplicableElements(ctx);
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
  public boolean isMany() {
    return false;
  }

}
