/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper.table;

import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;

/**
 *
 */
public abstract class AbstractTableToolWrapper extends AbstractToolWrapper {

  /** The command factory */
  protected final ITableCommandFactory _tableCommandFactory;
  
  /**
   * Constructor
   * @param tool_p
   * @param commandFactory_p
   */
  public AbstractTableToolWrapper(AbstractToolDescription tool_p, ITableCommandFactory commandFactory_p) {
    super(tool_p);
    
    _tableCommandFactory = commandFactory_p;
  }



}
