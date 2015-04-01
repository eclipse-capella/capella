/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 *
 */
public abstract class AbstractCommonToolWrapper extends AbstractToolWrapper {

  /** The command factory */
  protected final IDiagramCommandFactory _diagramCommandFactory;
  
  /**
   * Constructor
   * @param tool_p
   * @param commandFactory_p
   */
  public AbstractCommonToolWrapper(AbstractToolDescription tool_p, IDiagramCommandFactory commandFactory_p) {
    super(tool_p);
    
    _diagramCommandFactory = commandFactory_p;
  }



}
