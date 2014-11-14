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
package org.polarsys.capella.common.flexibility.properties.schema;

import java.util.Collection;

import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;

/**
 */
public interface IPropertyContext {

  public Object getSource();

  public Collection<Object> getSourceAsList(Class clazz);

  public Collection<Object> getSourceAsList();

  public void setSource(Object source_p);

  public IProperties getProperties();

  public Object getDefaultValue(IProperty property_p);

  public boolean isModified();

  public boolean isModified(IProperty property_p);

  public Object getCurrentValue(IProperty property_p);

  public void setCurrentValue(IProperty property_p, Object value_p);

  public void write(IProperty property_p);

  public void writeAll();

  public void registerListener(PropertyChangeListener listener_p);

  public void registerListener(PropertyChangeListener listener_p, IProperty property_p);

  public void setDefaults();

  public void unregisterListener(PropertyChangeListener listener_p);

}
