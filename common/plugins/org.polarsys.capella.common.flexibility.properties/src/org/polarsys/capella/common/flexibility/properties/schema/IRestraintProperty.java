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

/**
 * Describes a property associated to an element
 */
public interface IRestraintProperty extends IProperty {

  /**
   * Returns value of property
   */
  public Collection<Object> getChoiceValues(IPropertyContext context);

  public boolean isMany();

}
