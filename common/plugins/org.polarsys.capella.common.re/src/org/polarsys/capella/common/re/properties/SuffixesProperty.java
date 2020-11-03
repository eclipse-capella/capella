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
package org.polarsys.capella.common.re.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SuffixesProperty extends AbstractProperty implements IModifiedProperty, IEditableProperty, ICompoundProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context1) {
    CatalogElement target =
        (CatalogElement) context1.getCurrentValue(context1.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    IContext context = (IContext) context1.getSource();
    if (target != null) {
      for (CatalogElementLink link : target.getOwnedLinks()) {
        if (link.getTarget() != null) {
          if (!AttributesHandlerHelper.getInstance(context).isManualSuffixable(link.getTarget(), context)) {
            AttributesHandlerHelper.getInstance(context).setSuffixable(link.getTarget(), link.isSuffixed(), context);
          }
        }
      }
    }

    CatalogElement source =
        (CatalogElement) context1.getCurrentValue(context1.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
    if (source != null) {

      for (CatalogElementLink link : source.getOwnedLinks()) {
        if (link.getTarget() != null) {
          if (!AttributesHandlerHelper.getInstance(context).isManualSuffixable(link.getTarget(), context)) {
            AttributesHandlerHelper.getInstance(context).setSuffixable(link.getTarget(), link.isSuffixed(), context);
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
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return Object.class;
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
  public boolean isModified(IPropertyContext context) {
    return true;
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

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {

  }

}
