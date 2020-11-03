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

import org.eclipse.swt.widgets.Composite;

/**
 */
public interface IRenderer {

  public static final String EXECUTION_EVENT__RENDERER_CONTEXT = "rendererContext";
  public static final String EXECUTION_EVENT__RENDERER = "renderer";

  public void render(Composite parent, IRendererContext context);

  public void dispose(IRendererContext context);

}
