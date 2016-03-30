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

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;

/**
 *
 */
public interface IPropertyRenderer extends IRenderer {

  public void initialize(IProperty property, IRendererContext rendererContext);

  public void changeValue(IProperty property, IRendererContext context, Object newValue);

  public void updatedValue(IProperty property, IRendererContext context, Object newValue);

}
