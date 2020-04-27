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

public enum XDFBInsertRemoveTools {

  INSERT_REMOVE_FUNCTION_PORT(IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTION_PORT),
  INSERT_REMOVE_FUNCTIONS(IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONS),
  INSERT_REMOVE_FUNCTIONAL_EXCHANGES(IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONAL_EXCHANGES),
  INSERT_REMOVE_FUNCTIONAL_CHAINS(IToolNameConstants.TOOL_INSERT_REMOVE_FUNCTIONAL_CHAINS),
  SWITCH_FUNCTIONAL_EXCHANGE_CATEGORIES(IToolNameConstants.TOOL_SWITCH_FUNCTIONAL_EXCHANGE_CATEGORIES),
  INSERT_REMOVE_CONSTRAINTS(IToolNameConstants.TOOL_INSERT_REMOVE_CONSTRAINTS),
  INSERT_REMOVE_PROPERTY_VALUES(IToolNameConstants.TOOL_INSERT_REMOVE_PV),
  INSERT_REMOVE_PROPERTY_VALUE_GROUPS(IToolNameConstants.TOOL_INSERT_REMOVE_PVG),
  INSERT_REMOVE_EXCHANGE_CATEGORIES(IToolNameConstants.TOOL_INSERT_REMOVE_EXCHANGE_CATEGORIES);
  
  private String toolName;
  
  XDFBInsertRemoveTools(String toolName) {
    this.toolName = toolName;
  }
  
  public String getToolName() {
    return this.toolName;
  }
}
