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
  public Object getValue(IPropertyContext context_p) {

    if (_currentName == null) {

      CatalogElement element =
          (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));
      String suffix = (String) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX));

      IContext context = (IContext) context_p.getSource();
      if (AttributesHandlerHelper.getInstance(context).hasCustomName(element, context)) {
        _currentName = AttributesHandlerHelper.getInstance(context).getCurrentName(element, context, context_p);

      } else {
        _lastSuffix = suffix;
        if (element == null) {
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
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    boolean isValid = true;

    if ((newValue_p == null) || ((newValue_p instanceof String) && ((String) newValue_p).isEmpty())) {
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
  public Object toType(Object value_p, IPropertyContext context_p) {
    CatalogElement element =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    IContext context = (IContext) context_p.getSource();
    AttributesHandlerHelper.getInstance(context).setCustomName(element, (String) value_p, context);
    return value_p.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    CatalogElement element =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    IContext context = (IContext) context_p.getSource();
    AttributesHandlerHelper.getInstance(context).setCustomName(element, (String) context_p.getCurrentValue(this), context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    String value = (String) context_p.getCurrentValue(this);
    IContext context = (IContext) context_p.getSource();

    CatalogElement element =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
    if (AttributesHandlerHelper.getInstance(context).hasCustomName(element, context)) {
      value = AttributesHandlerHelper.getInstance(context).getCurrentName(element, context, context_p);
    }
    if ((value == null) || value.isEmpty()) {
      _currentName = null;

    } else {
      if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE.equals(property_p.getId())) {
        if ((_lastSourceName != null) && value.startsWith(_lastSourceName)) {
          _currentName = null;
        } else {
          _currentName = value;
        }

      } else if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX.equals(property_p.getId())) {
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
  public boolean isModified(IPropertyContext context_p) {
    return true;
  }

}
