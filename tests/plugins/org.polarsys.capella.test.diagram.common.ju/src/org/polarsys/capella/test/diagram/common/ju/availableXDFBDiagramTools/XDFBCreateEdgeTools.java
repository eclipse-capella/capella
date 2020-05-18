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

public enum XDFBCreateEdgeTools {

  CREATE_FUNCTIONAL_EXCHANGE(IToolNameConstants.TOOL_CREATE_FUNCTIONAL_EXCHANGE),
  CREATE_CONSTRAINT_LINK(IToolNameConstants.TOOL_CREATE_CONSTRAINT_ELEMENT);
  
  private String toolName;
  
  XDFBCreateEdgeTools(String toolName) {
    this.toolName = toolName;
  }
  
  public String getToolName() {
    return this.toolName;
  }
}
