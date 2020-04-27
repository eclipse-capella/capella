/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  private String systemEngName;
  // The system engineering.
  private SystemEngineering systemEng;
  // The capella project.
  private Project project;

  /**
   * Constructs a system engineering element.
   * @param prj The Capella project.
   * @param systemName The engineering system name.
   */
  public CreateEngSystemCmd(Project prj, String systemName) {
    // Check the engineering name.
    if (null == systemName) {
      // TODO to change to logger ??
      throw new IllegalArgumentException("The system engineering name parameter cannot be null !"); //$NON-NLS-1$
    }
    
    // Check the Capella project.
    if (null == prj) {
      // TODO to change to logger ??
      throw new IllegalArgumentException("The Capella project parameter cannot be null !"); //$NON-NLS-1$
    }

    project = prj;
    systemEngName = systemName;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.AbstractReadWriteCommand#run()
   */
  public void run() {
    // 1 - Builds the system engineering with the specified name.
    systemEng = CapellamodellerFactory.eINSTANCE.createSystemEngineering(systemEngName);

    // Attaches the model to its parent project.
    project.getOwnedModelRoots().add(systemEng);
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
    return systemEng;
  }
}
