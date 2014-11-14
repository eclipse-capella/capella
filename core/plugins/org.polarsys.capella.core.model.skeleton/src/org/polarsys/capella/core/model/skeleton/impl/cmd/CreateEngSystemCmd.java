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

package org.polarsys.capella.core.model.skeleton.impl.cmd;

import org.polarsys.capella.core.data.capellamodeller.CapellamodellerFactory;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.skeleton.Messages;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * The business command allowing to create the system engineering root element, <b>ONLY</b>.
 */
public class CreateEngSystemCmd extends AbstractReadWriteCommand {
  // The engineering name.
  private String _systemEngName;
  // The system engineering.
  private SystemEngineering _systemEng;
  // The capella project.
  private Project _project;

  /**
   * Constructs a system engineering element.
   * @param project_p The Capella project.
   * @param systemName_p The engineering system name.
   */
  public CreateEngSystemCmd(Project project_p, String systemName_p) {
    // Check the engineering name.
    if (null == systemName_p) {
      // TODO to change to logger ??
      throw new IllegalArgumentException("The system engineering name parameter cannot be null !"); //$NON-NLS-1$
    }
    
    // Check the Capella project.
    if (null == project_p) {
      // TODO to change to logger ??
      throw new IllegalArgumentException("The Capella project parameter cannot be null !"); //$NON-NLS-1$
    }

    _project = project_p;
    _systemEngName = systemName_p;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractReadWriteCommand#run()
   */
  public void run() {
    // 1 - Builds the system engineering with the specified name.
    _systemEng = CapellamodellerFactory.eINSTANCE.createSystemEngineering(_systemEngName);

    // Attaches the model to its parent project.
    _project.getOwnedModelRoots().add(_systemEng);
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractCommand#getName()
   */
  @Override
  public String getName() {
    return Messages.getString("capella.sys_eng.create.cmd"); //$NON-NLS-1$
  }

  /**
   * Gets the new system engineering.
   * @return The new system engineering.
   */
  public SystemEngineering getSystemEngineering() {
    return _systemEng;
  }
}
