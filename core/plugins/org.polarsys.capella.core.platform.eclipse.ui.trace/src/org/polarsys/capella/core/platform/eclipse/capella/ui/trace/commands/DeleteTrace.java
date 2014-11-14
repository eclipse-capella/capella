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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.apache.log4j.Logger;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.requirement.RequirementsTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.common.tig.ef.command.AbstractReadWriteCommand;

/**
 *
 */
public class DeleteTrace extends AbstractReadWriteCommand {

  /**
   * Log4j reference logger.
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe("User Interface"); //$NON-NLS-1$
  
  private Trace _traceToDelete;
  
  /**
   * 
   */
  public DeleteTrace(Trace traceToDelete_p) {
    _traceToDelete=traceToDelete_p;
  }
  
  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#execute()
   */
  public void run() {
    if((_traceToDelete instanceof GenericTrace)||(_traceToDelete instanceof RequirementsTrace)){
      //remove links
      _traceToDelete.setSourceElement(null);
      _traceToDelete.setTargetElement(null);
      //delete trace
      _traceToDelete.destroy();
    }
    else{
      __logger.info(Messages.getString("DeleteTrace.0")); //$NON-NLS-1$
    }
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#getName()
   */
  @Override
  public String getName() {
    return "DeleteTrace"; //$NON-NLS-1$
  }
}
