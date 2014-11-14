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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

/**
 *
 */
public class CreateCapellaLibraryCommand extends CreateCapellaProjectCmd {

  public CreateCapellaLibraryCommand(IProject eclipseProject_p, String name_p, TransactionalEditingDomain domain_p, ProjectApproach approach_p) {
    super(eclipseProject_p, name_p, domain_p, approach_p);
  }

  @Override
  protected Project createProject(String name_p) {
    return CapellamodellerFactory.eINSTANCE.createLibrary(name_p);
  }
}
