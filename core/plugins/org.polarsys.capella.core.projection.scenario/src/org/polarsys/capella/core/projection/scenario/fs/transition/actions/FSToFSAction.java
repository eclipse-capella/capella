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
package org.polarsys.capella.core.projection.scenario.fs.transition.actions;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.projection.common.AbstractTransitionAction;
import org.polarsys.capella.core.projection.scenario.fs.transition.commands.FStoFSCommand;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 *
 */
public class FSToFSAction extends AbstractTransitionAction {

  @Override
  protected ICommand createCommand(Collection<EObject> elements_p, IProgressMonitor progressMonitor_p) {
    return new FStoFSCommand(elements_p, progressMonitor_p);
  }

}
