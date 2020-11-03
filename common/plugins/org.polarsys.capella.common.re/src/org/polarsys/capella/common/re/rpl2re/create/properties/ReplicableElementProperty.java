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
package org.polarsys.capella.common.re.rpl2re.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementProperty extends AbstractProperty implements IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    CatalogElement rootElement = (CatalogElement) ctx.get("RE");

    if (rootElement == null) {
      Collection<Object> selection = (Collection<Object>) ctx.get(ITransitionConstants.TRANSITION_SOURCES);
      Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);
      if (!selection.isEmpty()) {
    	  CatalogElement element = ReplicableElementHandlerHelper.getInstance(ctx).createReplicableElement();
          rootElement = element;
          ctx.put("RE", rootElement);
      }
    }

    return rootElement;
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
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {

  }
}
