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
package org.polarsys.capella.core.libraries.ui.wizard.newLibrary;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

/**
 *
 */
public class CreateCapellaLibraryCommand extends CreateCapellaProjectCmd {

  public CreateCapellaLibraryCommand(Resource resource_p, String name_p, ProjectApproach approach_p) {
    super(resource_p, name_p, approach_p);
  }

  @Override
  protected Project createProject(String name_p) {
    return CapellamodellerFactory.eINSTANCE.createLibrary(name_p);
  }
}
