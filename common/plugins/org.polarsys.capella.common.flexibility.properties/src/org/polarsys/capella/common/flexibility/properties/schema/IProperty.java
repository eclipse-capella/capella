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

import java.util.List;

import org.eclipse.core.runtime.IStatus;

/**
 * Describes a property
 */
public interface IProperty {

  public String getId();

  public String getName();

  public String getDescription();

  public Object getValue(IPropertyContext context_p);

  public IStatus validate(Object newValue, IPropertyContext context_p);

  public Object getType();

  public Object toType(Object value_p, IPropertyContext context_p);

  public List<IPropertyOption> getOptions();

  public boolean isEnabled(IPropertyContext context_p);

  public String getParameter(String key_p);

}
