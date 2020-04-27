/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReplicaNameProperty extends AbstractProperty implements ICompoundProperty, IEditableProperty, IModifiedProperty {

  String _currentName = null;
  String _lastSourceName = null;
  String _lastSuffix = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX, };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {

    if (_currentName == null) {

      CatalogElement element =
          (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
      String suffix = (String) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX));

      IContext ctx = (IContext) context.getSource();
      if (AttributesHandlerHelper.getInstance(ctx).hasCustomName(element, ctx)) {
        _currentName = AttributesHandlerHelper.getInstance(ctx).getCurrentName(element, ctx, context);

      } else {
        _lastSuffix = suffix;
        if (element == null || element.eIsProxy()) {
          _currentName = suffix;
        } else {
          String name = element.getName();

          //Workaround, we rename a REC to RPL to avoid user confusion 
          if (name.startsWith("REC")) {
            name = "RPL" + name.substring(3);
          }
          _lastSourceName = name;
          _currentName = name + suffix;
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

    if ((newValue == null) || ((newValue instanceof String) && ((String) newValue).isEmpty())) {
      isValid = false;
    }
    if (!isValid) {
      return new Status(IStatus.WARNING, IReConstants.PLUGIN_ID, "Name should not be empty");
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
    CatalogElement element =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    IContext ctx = (IContext) context.getSource();
    AttributesHandlerHelper.getInstance(ctx).setCustomName(element, (String) value, ctx);
    return value.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    CatalogElement element =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    IContext ctx = (IContext) context.getSource();
    AttributesHandlerHelper.getInstance(ctx).setCustomName(element, (String) context.getCurrentValue(this), ctx);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    String value = (String) context.getCurrentValue(this);
    IContext ctx = (IContext) context.getSource();

    CatalogElement element =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    if (AttributesHandlerHelper.getInstance(ctx).hasCustomName(element, ctx)) {
      value = AttributesHandlerHelper.getInstance(ctx).getCurrentName(element, ctx, context);
    }
    if ((value == null) || value.isEmpty()) {
      _currentName = null;

    } else {
      if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE.equals(property.getId())) {
        if ((_lastSourceName != null) && value.startsWith(_lastSourceName)) {
          _currentName = null;
        } else {
          _currentName = value;
        }

      } else if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX.equals(property.getId())) {
        if ((_lastSuffix != null) && value.endsWith(_lastSuffix)) {
          if ((_lastSourceName != null) && value.startsWith(_lastSourceName)) {
            _currentName = null;
          } else {
            _currentName = value;
          }
        } else {
          _currentName = value;
        }
      }
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
