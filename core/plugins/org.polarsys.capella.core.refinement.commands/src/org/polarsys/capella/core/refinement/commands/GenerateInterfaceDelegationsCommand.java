/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.commands;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.operations.LongRunningListenersRegistry;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.refinement.processor.InterfaceDelegationSCtoLC;

/**
 * Create interface links which was defined in the system analysis phase into the logical system
 */
public class GenerateInterfaceDelegationsCommand extends AbstractReadWriteCommand {

  /**
   * Capella elements to copy.
   */
  private ModelElement modelElement = null;

  /**
   * Constructor
   * @param modelElement
   */
  public GenerateInterfaceDelegationsCommand(ModelElement modelElement) {
    this.modelElement = modelElement;
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (modelElement instanceof LogicalComponent) {
    	  new InterfaceDelegationSCtoLC((LogicalComponent)modelElement).perform();      
      } else if (modelElement instanceof LogicalActor) {
    	  new InterfaceDelegationSCtoLC((LogicalActor)modelElement).perform();      
      } else if (modelElement instanceof LogicalActorPkg) {
    	  new InterfaceDelegationSCtoLC((LogicalActorPkg)modelElement).perform();
      } else if (modelElement instanceof System) {
        new InterfaceDelegationSCtoLC((System)modelElement).perform();
      } else if (modelElement instanceof Actor) {
          new InterfaceDelegationSCtoLC((Actor)modelElement).perform();
      } else if (modelElement instanceof ActorPkg) {
          new InterfaceDelegationSCtoLC((ActorPkg)modelElement).perform();          
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.command.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return "Generate Interface Delegations"; //$NON-NLS-1$
  }
}
