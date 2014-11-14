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
package org.polarsys.capella.core.preferences.transferer;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.polarsys.capella.core.commands.preferences.model.CategoryPreferencesManager;

/**
 */
public class PreferencesProjectFilter extends ViewerFilter {

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("restriction")
  @Override
  public boolean select(Viewer viewer, Object parentElement, Object element) {
    if (element instanceof IProject) {
      return isSuportedProject((IProject) element);
    }
    return false;

  }

  /**
   * @param element
   * @return
   */
  private boolean isSuportedProject(IProject element) {
    Set<String> projectsIdetnifients = CategoryPreferencesManager.getInstance().getProjectsNaturesIds();
    IProjectNature projectNature = null;

    try {

      for (String currentID : projectsIdetnifients) {
        if (element.isOpen() && (element.getNature(currentID) != null)) {
          projectNature = element.getNature(currentID);
          break;
        }
      }

      return (projectNature != null) && (projectNature.getProject() != null) && projectNature.getProject().isOpen();

    } catch (CoreException exception_p) {
      exception_p.printStackTrace();
    }

    return false;
  }

}
