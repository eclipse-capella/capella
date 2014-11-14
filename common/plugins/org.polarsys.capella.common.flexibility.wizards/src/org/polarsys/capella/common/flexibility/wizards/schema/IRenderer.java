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
package org.polarsys.capella.common.flexibility.wizards.schema;

import org.eclipse.swt.widgets.Composite;

/**
 */
public interface IRenderer {

  public static final String EXECUTION_EVENT__RENDERER_CONTEXT = "rendererContext";
  public static final String EXECUTION_EVENT__RENDERER = "renderer";

  public void render(Composite parent_p, IRendererContext context_p);

  public void dispose(IRendererContext context_p);

}
