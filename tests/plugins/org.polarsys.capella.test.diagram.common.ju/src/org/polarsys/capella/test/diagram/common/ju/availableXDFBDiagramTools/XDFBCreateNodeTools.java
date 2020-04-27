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

import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;;

public enum XDFBCreateNodeTools {

  CREATE_INPUT_PORT(IToolNameConstants.TOOL_CREATE_INPUT_PORT),
  CREATE_OUTPUT_PORT(IToolNameConstants.TOOL_CREATE_OUTPUT_PORT),
  CREATE_CONSTRAINT(IToolNameConstants.TOOL_CREATE_CONSTRAINT);
  
  private String toolName;
  
  XDFBCreateNodeTools(String toolName) {
    this.toolName = toolName;
  }
  
  public String getToolName() {
    return this.toolName;
  }
}
