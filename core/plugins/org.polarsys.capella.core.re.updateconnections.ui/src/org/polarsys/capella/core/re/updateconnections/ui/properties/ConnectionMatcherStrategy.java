/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.core.re.updateconnections.ui.ConnectionMatcher;

public class ConnectionMatcherStrategy extends AbstractProperty implements IRestraintProperty, IEditableProperty {

  private Collection<ConnectionMatcher> availableConnectionMatchers;

  public void setAvailableConnectionMatchers(Collection<ConnectionMatcher> available) {
    this.availableConnectionMatchers = available;
  }

  @Override
  public Object getValue(IPropertyContext context) {
    if (availableConnectionMatchers != null && availableConnectionMatchers.size() > 0) {
      return availableConnectionMatchers.iterator().next();
    }
    return null;
  }

  @Override
  public Object getType() {
    return ConnectionMatcher.class;
  }

  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  @Override
  public void setValue(IPropertyContext context) {
    // not used, but must be implemented or wizard won't update value
  }

  @Override
  public Collection<?> getChoiceValues(IPropertyContext context) {
    return availableConnectionMatchers;
  }

  @Override
  public boolean isMany() {
    return false;
  }

}
