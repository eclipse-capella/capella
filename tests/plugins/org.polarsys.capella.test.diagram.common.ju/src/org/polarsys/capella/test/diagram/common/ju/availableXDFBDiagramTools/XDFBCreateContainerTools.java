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
package org.polarsys.capella.test.diagram.common.ju.availableXDFBDiagramTools;

import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;

public enum XDFBCreateContainerTools {

  CREATE_FUNCTION(IToolNameConstants.TOOL_CREATE_FUNCTION),
  CREATE_ACTOR_FUNCTION(IToolNameConstants.TOOL_CREATE_ACTOR_FUNCTION),
  CREATE_FUNCTION_DUPLICATE(IToolNameConstants.TOOL_CREATE_FUNCTION_DUPLICATE),
  CREATE_FUNCTION_GATHER(IToolNameConstants.TOOL_CREATE_FUNCTION_GATHER),
  CREATE_FUNCTION_ROUTE(IToolNameConstants.TOOL_CREATE_FUNCTION_ROUTE),
  CREATE_FUNCTION_SELECT(IToolNameConstants.TOOL_CREATE_FUNCTION_SELECT),
  CREATE_FUNCTION_SPLIT(IToolNameConstants.TOOL_CREATE_FUNCTION_SPLIT);
  
  private String toolName;
  
  XDFBCreateContainerTools(String toolName) {
    this.toolName = toolName;
  }
  
  public String getToolName() {
    return this.toolName;
  }
}
