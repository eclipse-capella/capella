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
package org.polarsys.capella.common.re.rpl2re.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SourceNameProperty extends AbstractProperty implements ICompoundProperty, IEditableProperty, IModifiedProperty {

  String _currentName = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, IReConstants.PROPERTY__LOCATION_SOURCE };
  }

  @Override
  public boolean isEnabled(IPropertyContext context) {
    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
    return source != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    if (_currentName == null) {
      IContext ctx = (IContext) context.getSource();
      CatalogElement source =
          (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
      if (source != null) {
        CatalogElementPkg location =
            (CatalogElementPkg) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_SOURCE));
        if ((source.getName() != null) && !(source.getName().isEmpty())) {
          _currentName = source.getName();
        } else {
          _currentName = ReplicableElementHandlerHelper.getInstance(ctx).getInitialReplicaName(ctx, location);
        }
      }
    }
    return _currentName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    boolean isValid = true;
    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
    if (source != null) {

      if ((newValue == null) || ((newValue instanceof String) && ((String) newValue).isEmpty())) {
        isValid = false;
      }
      if (!isValid) {
        return new Status(IStatus.WARNING, IReConstants.getDefault().getBundle().getSymbolicName(), "Name should not be empty");
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return String.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    if (value == null) {
      return null;
    }
    return value.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    CatalogElement element =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
    if (element != null) {
      element.setName((String) context.getCurrentValue(this));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    if (IReConstants.PROPERTY__LOCATION_SOURCE.equals(property.getId())) {
      _currentName = null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModified(IPropertyContext context) {
    return true;
  }
}
