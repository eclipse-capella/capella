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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.core.libraries.nature.LibraryNature;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.platform.sirius.ui.project.operations.ProjectSessionCreationHelper;

public class LibrarySessionCreationHelper extends ProjectSessionCreationHelper {

  public LibrarySessionCreationHelper(boolean epbsSelected_p, boolean opaSelected_p) {
    super(epbsSelected_p, opaSelected_p);
  }

  public LibrarySessionCreationHelper(boolean epbsSelected_p, boolean opaSelected_p, ProjectApproach projectApproach_p) {
    super(epbsSelected_p, opaSelected_p, projectApproach_p);
  }

  @Override
  protected ICommand createInitialElementsCommand(Resource resource_p, String projectName_p, IProgressMonitor monitor_p) {
    return new CreateCapellaLibraryCommand(resource_p, projectName_p, getProjectApproach());
  }

  @Override
  protected void setNatureProject(IProject project_p, IProjectDescription description_p) {
    // Add the project nature.
    if (!description_p.hasNature(LibraryNature.ID)) {
      String[] newNatures = new String[] { LibraryNature.ID };
      description_p.setNatureIds(newNatures);
    }
  }

}
