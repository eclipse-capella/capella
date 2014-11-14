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
package org.polarsys.capella.common.re.ui.handlers.options;

import org.polarsys.capella.core.transition.common.ui.handlers.options.TransitionUIOptionsHandler;

/**
 *
 */
public class ReUIOptionHandler extends TransitionUIOptionsHandler {

  @Override
  protected String getTitle() {
    return "Replicable Elements Options";//$NON-NLS-1$
  }

  @Override
  protected String getDescription() {
    return "Select options";//$NON-NLS-1$
  }
}
