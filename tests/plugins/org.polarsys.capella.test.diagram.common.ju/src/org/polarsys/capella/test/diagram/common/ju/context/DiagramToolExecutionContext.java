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
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;

/**
 *
 */
public class DiagramToolExecutionContext extends DiagramOpenExecutionContext {

  /**
   * Tool to test.
   */
  protected String _toolName;

  /**
   * @param session
   * @param diagram
   * @param toolName
   */
  public DiagramToolExecutionContext(Session session, DDiagram diagram, String toolName) {
    super(session, diagram);
    _toolName = toolName;
  }

  public String getToolName() {
    return _toolName;
  }
}
