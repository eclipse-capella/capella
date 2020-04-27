/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public Object getValue(IPropertyContext context);

  public IStatus validate(Object newValue, IPropertyContext context);

  public Object getType();

  public Object toType(Object value, IPropertyContext context);

  public List<IPropertyOption> getOptions();

  public boolean isEnabled(IPropertyContext context);

  public String getParameter(String key);

}
