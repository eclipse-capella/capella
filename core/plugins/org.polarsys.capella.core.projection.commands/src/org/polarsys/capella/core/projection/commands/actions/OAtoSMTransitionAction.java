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
package org.polarsys.capella.core.projection.commands.actions;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.commands.OAtoSMTransitionCommand;
import org.polarsys.capella.common.tig.ef.command.ICommand;

/**
 */
public class OAtoSMTransitionAction extends AbstractTransitionAction {

  @Override
  protected ICommand createCommand(Collection<EObject> elements_p, IProgressMonitor progressMonitor_p) {
    return new OAtoSMTransitionCommand(elements_p, progressMonitor_p);
  }

}
