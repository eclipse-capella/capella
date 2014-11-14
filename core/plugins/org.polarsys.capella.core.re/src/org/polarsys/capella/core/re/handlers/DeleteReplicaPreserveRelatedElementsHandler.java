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
package org.polarsys.capella.core.re.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.handlers.AbstractReHandler;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.re.commands.DeleteReplicaPreserveRelatedElementsCommand;
import org.polarsys.capella.core.transition.common.context.TransitionContext;

/**
 *  
 *
 */
public class DeleteReplicaPreserveRelatedElementsHandler extends AbstractReHandler {


	/**
	   * {@inheritDoc}
	   */
	  @Override
	  public void setEnabled(Object evaluationContext_p) {
	    super.setEnabled(evaluationContext_p);

	    Collection<CatalogElement> elements =
	        ReplicableElementHandlerHelper.getInstance(TransitionContext.EMPTY_CONTEXT).getIndirectlyReplicableElementsForCommand(TransitionContext.EMPTY_CONTEXT,
	            getInitialSelection(evaluationContext_p));

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
	  protected ICommand createCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
	    return new DeleteReplicaPreserveRelatedElementsCommand(selection_p, progressMonitor_p);
	  }
}
