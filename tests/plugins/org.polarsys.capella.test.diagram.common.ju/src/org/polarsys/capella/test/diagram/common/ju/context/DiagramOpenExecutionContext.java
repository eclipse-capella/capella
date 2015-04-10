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
public class DiagramOpenExecutionContext extends BasicExecutionContext {

  /**
   * Diagram to test.
   */
  protected DDiagram _diagram;

  /**
   * @param session
   * @param diagram
   */
  public DiagramOpenExecutionContext(Session session, DDiagram diagram) {
    super(session);
    _diagram = diagram;
  }

  public DDiagram getDiagram() {
    return _diagram;
  }
}
