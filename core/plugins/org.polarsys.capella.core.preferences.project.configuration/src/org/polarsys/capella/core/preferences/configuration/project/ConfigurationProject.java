/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.preferences.configuration.project;

import org.eclipse.core.resources.IProject;

/**
 */
public class ConfigurationProject {

  /*
   * 
   */
  private IProject project;

  /*
   * 
   */
  private Boolean isSelected;

  /**
   * @param project
   * @param isSelected
   */
  public ConfigurationProject(IProject project, Boolean isSelected) {
    this.project = project;
    this.isSelected = isSelected;
  }

  public IProject getProject() {
    return project;
  }

  public void setProject(IProject project_p) {
    project = project_p;
  }

  public Boolean getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(Boolean isSelected_p) {
    isSelected = isSelected_p;
  }

}
