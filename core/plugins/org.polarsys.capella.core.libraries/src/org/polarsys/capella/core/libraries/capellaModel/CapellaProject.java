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
package org.polarsys.capella.core.libraries.capellaModel;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.common.libraries.IAbstractProject;

/**
 */
public class CapellaProject extends CapellaModel implements IAbstractProject {

  public CapellaProject(Project project_p) {
    super(project_p);
  }

  @Override
  public String toString() {
    return "CapellaProject " + super.toString();
  }

}
