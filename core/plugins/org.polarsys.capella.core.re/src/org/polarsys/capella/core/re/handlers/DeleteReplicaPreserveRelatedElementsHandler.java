/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.re.commands.DeleteReplicaPreserveRelatedElementsCommand;
import org.polarsys.capella.core.transition.common.commands.CommandHandler;
import org.polarsys.capella.core.transition.common.context.TransitionContext;

/**
 *  
 *
 */
public class DeleteReplicaPreserveRelatedElementsHandler extends CommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext) {
    super.setEnabled(evaluationContext);

    Collection<CatalogElement> elements = ReplicableElementHandlerHelper.getInstance(TransitionContext.EMPTY_CONTEXT)
        .getIndirectlyReplicableElementsForCommand(TransitionContext.EMPTY_CONTEXT,
            getInitialSelection(evaluationContext));

    if (!elements.isEmpty()) {
      for (CatalogElement element : elements) {
        if ((element.getKind() == CatalogElementKind.REC_RPL) || (element.getKind() == CatalogElementKind.RPL)) {
          setBaseEnabled(true);
          return;
        }
      }
    }
    setBaseEnabled(false);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
    return new DeleteReplicaPreserveRelatedElementsCommand(selection, progressMonitor);
  }
}
