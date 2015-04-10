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
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 *
 */
public class BasicExecutionContext {

  /**
   * Current session for semantic resource and diagram resource.
   */
  protected Session _session;

  /**
   * @param _session
   */
  public BasicExecutionContext(Session session) {
    _session = session;
  }

  public Session getSession() {
    return _session;
  }

  /**
   * Get the Capella Execution manager.
   * @return a not <code>null</code> execution manager.
   */
  public ExecutionManager getExecutionManager() {
    return TransactionHelper.getExecutionManager(_session);
  }
}
