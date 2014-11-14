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
import org.polarsys.capella.core.refinement.processor.CommunicationLinkDelegationSCtoLC;

/**
 * Create interface links which was defined in the system analysis phase into the logical system
 */
public class GenerateCommunicationLinkDelegationsCommand extends AbstractReadWriteCommand {

  /**
   * Capella elements to copy.
   */
  private ModelElement _modelElement = null;

  /**
   * Constructor
   * @param modelElement_p
   */
  public GenerateCommunicationLinkDelegationsCommand(ModelElement modelElement_p) {
    _modelElement = modelElement_p;
  }

  /**
   * @see org.polarsys.capella.common.command.ICommand#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  public void run() {
    // Send long running operation events.
    // Operation is starting.
    LongRunningListenersRegistry.getInstance().operationStarting(getClass());
    try {
      if (_modelElement instanceof LogicalComponent) {
    	  new CommunicationLinkDelegationSCtoLC((LogicalComponent)_modelElement).perform();      
      } else if (_modelElement instanceof LogicalActor) {
    	  new CommunicationLinkDelegationSCtoLC((LogicalActor)_modelElement).perform();      
      } else if (_modelElement instanceof LogicalActorPkg) {
    	  new CommunicationLinkDelegationSCtoLC((LogicalActorPkg)_modelElement).perform();
      } else if (_modelElement instanceof System) {
        new CommunicationLinkDelegationSCtoLC((System)_modelElement).perform();
      } else if (_modelElement instanceof Actor) {
          new CommunicationLinkDelegationSCtoLC((Actor)_modelElement).perform();
      } else if (_modelElement instanceof ActorPkg) {
          new CommunicationLinkDelegationSCtoLC((ActorPkg)_modelElement).perform();          
      }
    } finally {
      // Send long running operation events.
      // Operation has finished.
      LongRunningListenersRegistry.getInstance().operationEnded(getClass());
    }
  }

  /**
   * @see org.polarsys.capella.common.command.ICommand#getLabel()
   */
  @Override
  public String getName() {
    return "Generate Communication Link Delegations"; //$NON-NLS-1$
  }
}
