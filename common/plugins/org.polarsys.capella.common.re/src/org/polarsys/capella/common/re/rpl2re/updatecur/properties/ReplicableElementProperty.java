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
  public Object getValue(IPropertyContext context_p) {
    try {

      IContext context = (IContext) context_p.getSource();
      CatalogElement replica = (CatalogElement) context.get("RE");

      if (replica == null) {

        Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);

        Collection<Object> selection = (Collection<Object>) context.get(ITransitionConstants.TRANSITION_SOURCES);
        if (selection.size() > 0) {
          Collection<CatalogElement> uppests =
              ReplicableElementHandlerHelper.getInstance(context).getUppestReplicableElement(context, (Collection) selectedElements);
          if (!uppests.isEmpty()) {
            replica = uppests.iterator().next();
          }
        }

        context.put("RE", replica);
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
  public Object toType(Object value_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement element = (CatalogElement) context.get("RE");
    if (value_p instanceof String) {
      element.setName((String) value_p);
      return element;
    }
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    if (newValue_p instanceof String) {
      if (((String) newValue_p).length() == 0) {
        return new Status(IStatus.WARNING, getId(), "Should be not empty");
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Object> getChoiceValues(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    return ReplicableElementHandlerHelper.getInstance(context).getAllDefinedReplicableElements(context);
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
  public boolean isMany() {
    return false;
  }

}
