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
package org.polarsys.capella.core.re.ui.handlers;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import org.polarsys.capella.core.re.handlers.UpdateDefHandler;
import org.polarsys.capella.core.re.ui.commands.UpdateDefUiCommand;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 */
public class UpdateDefUiHandler extends UpdateDefHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected ICommand createCommand(Collection<Object> selection_p, IProgressMonitor progressMonitor_p) {
    return new UpdateDefUiCommand(selection_p, progressMonitor_p) {

    };
  }

  @Override
  public Object resolveSemanticObject(Object object_p) {
    Object semantic = super.resolveSemanticObject(object_p);
    if (semantic != null) {
      if (semantic instanceof DSemanticDecorator) {
        Object adapter = ((DSemanticDecorator) semantic).getTarget();
        if (adapter instanceof EObject) {
          semantic = adapter;
        }
      }
    }
    return semantic;
  }

}
