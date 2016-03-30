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

package org.polarsys.capella.common.re.properties;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public abstract class AbstractReProperty extends AbstractProperty {

  protected IContext getContext(IPropertyContext context) {
    return (IContext) context.getSource();
  }

  protected String getUniqueIdentifier() {
    return getClass().getSimpleName() + "__" + getId();
  }

}
