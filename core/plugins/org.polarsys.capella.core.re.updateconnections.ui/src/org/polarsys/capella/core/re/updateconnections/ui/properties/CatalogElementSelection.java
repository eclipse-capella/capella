/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.core.re.updateconnections.ui.UpdateConnectionsUIActivator;

/**
 * Select one of all available catalog elements.
 */
public class CatalogElementSelection extends AbstractProperty implements IRestraintProperty, IEditableProperty {

  private Collection<? extends CatalogElement> availableCatalogElements;

  /**
   * Set the available catalog elements. The first element of the collections iterator will be used as the default
   * element.
   * 
   * @param availableCatalogElements
   */
  public void setAvailableCatalogElements(Collection<? extends CatalogElement> availableCatalogElements) {
    this.availableCatalogElements = availableCatalogElements;
  }

  @Override
  public Object getType() {
    return CatalogElement.class;
  }

  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  @Override
  public Collection<?> getChoiceValues(IPropertyContext context) {
    return availableCatalogElements;
  }

  @Override
  public boolean isMany() {
    return false;
  }

  @Override
  public Object getValue(IPropertyContext context) {
    if (availableCatalogElements != null && availableCatalogElements.size() > 0) {
      return availableCatalogElements.iterator().next();
    }
    return null;
  }

  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    EObject value = (EObject)context.getCurrentValue(this);
    for (IProperty property : context.getProperties().getAllItems()) {
      if (property instanceof CatalogElementSelection && !this.equals(property)) {
        if (value.equals(context.getCurrentValue(property))) {
          return new Status(IStatus.ERROR, UpdateConnectionsUIActivator.PLUGIN_ID, "Selected RPLs must be different");
        }
      }
    }
   return super.validate(newValue, context);
  }

  @Override
  public void setValue(IPropertyContext context) {
    // not used, but must be implemented or wizard won't update value
  }
}
