/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.action;

import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.command.AbstractNonDirtyingCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.tig.ef.command.ICommand;
import org.polarsys.capella.common.tig.ef.registry.ExecutionManagerRegistry;

/**
 * Base class to implement an action which executes an {@link ICommand} against a model through <b>Capella Transactional Editing Domain</b>.
 * <p>
 * {@link AbstractReadWriteCommand}, {@link AbstractReadOnlyCommand}, {@link AbstractNonDirtyingCommand}
 */
public abstract class AbstractCapellaModelAction extends AbstractModelAction {
  /**
   * Capella edition provider id.
   */
  private static final String EDITING_DOMAIN_PROVIDER_ID = "org.polarsys.capella.common.platform.sirius.tig.EditingDomainProvider"; //$NON-NLS-1$

  /**
   * {@inheritDoc}
   */
  @Override
  protected ExecutionManager getExecutionManager() {
    return ExecutionManagerRegistry.getInstance().getExecutionManager(EDITING_DOMAIN_PROVIDER_ID);
  }
}
