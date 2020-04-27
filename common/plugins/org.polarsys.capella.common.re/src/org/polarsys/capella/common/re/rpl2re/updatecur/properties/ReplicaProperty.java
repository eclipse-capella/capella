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
package org.polarsys.capella.common.re.rpl2re.updatecur.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaProperty extends AbstractProperty implements IEditableProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    try {

      IContext ctx = (IContext) context.getSource();
      CatalogElement replica = (CatalogElement) ctx.get("RPL");

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
    CatalogElement element = (CatalogElement) ctx.get("RPL");
    if (element != null) {

      if (value instanceof String) {
        element.setName((String) value);
        return element;
      }
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
    return Status.OK_STATUS;
  }

}
