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
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE, IReConstants.PROPERTY__LOCATION_SOURCE };
  }

  @Override
  public boolean isEnabled(IPropertyContext context_p) {
    CatalogElement source =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE));
    return source != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    if (_currentName == null) {
      IContext context = (IContext) context_p.getSource();
      CatalogElement source =
          (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE));
      if (source != null) {
        CatalogElementPkg location =
            (CatalogElementPkg) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_SOURCE));
        if ((source.getName() != null) && !(source.getName().isEmpty())) {
          _currentName = source.getName();
        } else {
          _currentName = ReplicableElementHandlerHelper.getInstance(context).getInitialReplicaName(context, location);
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
    IContext context = (IContext) context_p.getSource();
    CatalogElement source =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE));
    if (source != null) {

      if ((newValue_p == null) || ((newValue_p instanceof String) && ((String) newValue_p).isEmpty())) {
        isValid = false;
      }
      if (!isValid) {
        return new Status(IStatus.WARNING, IReConstants.PLUGIN_ID, "Name should not be empty");
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
  public Object toType(Object value_p, IPropertyContext context_p) {
    if (value_p == null) {
      return null;
    }
    return value_p.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    CatalogElement element =
        (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE));
    if (element != null) {
      element.setName((String) context_p.getCurrentValue(this));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    if (IReConstants.PROPERTY__LOCATION_SOURCE.equals(property_p.getId())) {
      _currentName = null;
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
