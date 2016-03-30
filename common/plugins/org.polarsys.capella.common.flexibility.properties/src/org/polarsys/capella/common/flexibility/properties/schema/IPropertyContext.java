/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public void setSource(Object source);

  public IProperties getProperties();

  public Object getDefaultValue(IProperty property);

  public boolean isModified();

  public boolean isModified(IProperty property);

  public Object getCurrentValue(IProperty property);

  public void setCurrentValue(IProperty property, Object value);

  public void write(IProperty property);

  public void writeAll();

  public void registerListener(PropertyChangeListener listener);

  public void registerListener(PropertyChangeListener listener, IProperty property);

  public void setDefaults();

  public void unregisterListener(PropertyChangeListener listener);

}
