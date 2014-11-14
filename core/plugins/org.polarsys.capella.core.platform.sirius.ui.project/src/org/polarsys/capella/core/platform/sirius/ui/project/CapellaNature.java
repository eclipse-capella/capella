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
package org.polarsys.capella.core.platform.sirius.ui.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;

/**
 * The Capella nature.
 */
public class CapellaNature extends ModelingProject {
  /**
   * The Capella nature identifier.
   */
  public static final String ID = "org.polarsys.capella.project.nature"; //$NON-NLS-1$

  /**
   * Get the corresponding Modeling project.
   * @param project The original project
   * @return an optional ModelingProject (none if this project is not a modeling project).
   */
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
