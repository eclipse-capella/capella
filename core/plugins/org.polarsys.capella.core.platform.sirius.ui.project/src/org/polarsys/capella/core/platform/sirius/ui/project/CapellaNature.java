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
package org.polarsys.capella.core.platform.sirius.ui.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * The Capella nature.
 */
public class CapellaNature extends ModelingProject {
  /**
   * The Capella nature identifier.
   */
  public static final String ID = CapellaResourceHelper.CAPELLA_PROJECT_NATURE;

  /**
   * Get the corresponding Modeling project.
   * @param project The original project
   * @return an optional ModelingProject (none if this project is not a modeling project).
   */
  @Deprecated
  public static Option<CapellaNature> asCapellaProject(IProject project) {
    IProjectNature nature = null;

    if (project != null) {
      try {
        nature = project.getNature(ID);
      } catch (CoreException e) {
        /* does nothing */
      }
    }

    if (nature instanceof CapellaNature) {
      return Options.newSome((CapellaNature) nature);
    }

    return Options.newNone();
  }
}
