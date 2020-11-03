/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
