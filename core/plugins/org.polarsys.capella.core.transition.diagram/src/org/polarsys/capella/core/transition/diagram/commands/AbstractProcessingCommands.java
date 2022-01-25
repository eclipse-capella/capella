/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.utils.RunnableWithBooleanResult;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.diagram.Activator;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class AbstractProcessingCommands<T> extends AbstractProcessingRunnable<T> {

  private IContext context;

  public AbstractProcessingCommands(Collection<T> elements_p) {
    super(elements_p);
    context = new TransitionContext();
  }

  public IContext getContext() {
    return context;
  }

  protected ExecutionManager getExecutionManager(Session session_p) {
    return TransactionHelper.getExecutionManager(session_p);
  }

  protected IStatus runCommand(final Session session_p, final String name_p, final RunnableWithBooleanResult runnable_p) {
    final IStatus[] result = new IStatus[] { Status.OK_STATUS };
    try {
      getExecutionManager(session_p).execute(new AbstractReadWriteCommand() {

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
          return name_p;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void commandRolledBack() {
          result[0] = new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), getName());
        }

        public void run() {
          try {
            runnable_p.run();
            result[0] = runnable_p.getStatus();
          } catch (Exception exception_p) {
            String message = getName();
            if (exception_p.getMessage() != null) {
              message += ": " + exception_p.getMessage();
            }
            result[0] = new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), message, exception_p);
          }
        }
      });

    } catch (Exception exception_p) {
      String message = name_p;
      if (exception_p.getMessage() != null) {
        message += ": " + exception_p.getMessage();
      }
      result[0] = new Status(IStatus.ERROR, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), message, exception_p);
    }
    return result[0];
  }
}
