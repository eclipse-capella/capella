/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.ui.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.ef.command.ICommand;

public class DeleteReplicaPreserveRelatedElementsUiHandler extends org.polarsys.capella.core.re.handlers.DeleteReplicaPreserveRelatedElementsHandler {

	/**
	   * {@inheritDoc}
	   */
	  @Override
	  protected ICommand createCommand(Collection<?> selection, IProgressMonitor progressMonitor) {
	    return new org.polarsys.capella.core.re.commands.DeleteReplicaPreserveRelatedElementsCommand(selection, progressMonitor) {
	      protected boolean isHeadless() {
          return false;
        }
	    };
	  }

	  @Override
	  public EObject resolveSemanticObject(Object object) {
	    EObject semantic = super.resolveSemanticObject(object);
	    if (semantic != null) {
	      if (semantic instanceof DSemanticDecorator) {
	        Object adapter = ((DSemanticDecorator) semantic).getTarget();
	        if (adapter instanceof EObject) {
	          semantic = (EObject) adapter;
	        }
	      }
	    }
	    return semantic;
	  }
}
