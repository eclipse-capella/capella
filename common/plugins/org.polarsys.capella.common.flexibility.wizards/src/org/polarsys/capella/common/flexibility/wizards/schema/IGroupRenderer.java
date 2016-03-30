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

package org.polarsys.capella.common.flexibility.wizards.schema;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public interface IGroupRenderer extends IRenderer {

  public void updatedValue(IPropertyGroup propertyGroup, IRendererContext context);

}
