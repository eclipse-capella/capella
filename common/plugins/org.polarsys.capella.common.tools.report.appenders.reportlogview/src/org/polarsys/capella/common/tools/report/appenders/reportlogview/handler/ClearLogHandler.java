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

package org.polarsys.capella.common.tools.report.appenders.reportlogview.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Simple handler to clean the Information view
 *
 */
public class ClearLogHandler extends AbstractViewHandler {
  /**
   * {@inheritDoc}
   */
  public Object execute(ExecutionEvent event) throws ExecutionException {
    getView(event).clear();
    return null;
  }

}
