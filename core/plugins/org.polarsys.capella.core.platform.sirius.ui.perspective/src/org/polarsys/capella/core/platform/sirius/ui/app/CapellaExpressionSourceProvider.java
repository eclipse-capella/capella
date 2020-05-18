/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.app;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.PlatformUI;

public class CapellaExpressionSourceProvider extends AbstractSourceProvider {
  
  @Override
  @SuppressWarnings("rawtypes")
  public Map getCurrentState() {
    Map<Object, Object> result = new HashMap<Object, Object>();
    result.put("org.polarsys.capella.core.platform.workbench", PlatformUI.getWorkbench());
    return result;
  }

  @Override
  public String[] getProvidedSourceNames() {
    return new String[] { "org.polarsys.capella.core.platform.workbench" };
  }

  @Override
  public void dispose() {
  }
}