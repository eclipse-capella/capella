/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.services;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.management.ToolFilter;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IIdentifier;

public class ActivityToolFilter implements ToolFilter {

  @Override
  public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
    IElementIdentifierService elementIdentifier = PlatformUI.getWorkbench().getService(IElementIdentifierService.class);
    String toolId = elementIdentifier.getIdentifier(diagram.getDescription(), tool);

    IActivityManager activityManager = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
    IIdentifier id = activityManager.getIdentifier(toolId);

    return !id.isEnabled();
  }
}
