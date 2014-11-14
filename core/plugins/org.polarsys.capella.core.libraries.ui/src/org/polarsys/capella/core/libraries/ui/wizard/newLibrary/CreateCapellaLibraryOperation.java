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
package org.polarsys.capella.core.libraries.ui.wizard.newLibrary;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.CreateCapellaProjectOperation;

/**
 *
 */
public class CreateCapellaLibraryOperation extends CreateCapellaProjectOperation {

  public CreateCapellaLibraryOperation(IProject parent_p, String projectName_p, ProjectApproach projectApproach_p) {
    super(parent_p, projectName_p, projectApproach_p);
  }

  @Override
  protected CreateCapellaProjectCmd createCreationCommand(IProgressMonitor monitor_p, TransactionalEditingDomain domain_p) {
    return new CreateCapellaLibraryCommand(getParent(), getProjectName(), domain_p, getProjectApproach());
  }

}
