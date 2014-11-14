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
package org.polarsys.capella.core.libraries.commands.internal;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 */

public class AddModelInformationToProjectCommand extends AbstractReadWriteCommand {

  private Project project;
  private ModelInformation result;

  public AddModelInformationToProjectCommand(Project project_p) {
    project = project_p;
  }

  @Override
  public void run() {
    result = LibrariesFactory.eINSTANCE.createModelInformation();
    project.getOwnedExtensions().add(result);
  }

  public ModelInformation getResult() {
    return result;
  }
}
