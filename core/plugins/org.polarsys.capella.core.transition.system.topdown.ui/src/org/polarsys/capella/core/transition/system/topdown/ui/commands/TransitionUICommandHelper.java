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
package org.polarsys.capella.core.transition.system.topdown.ui.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.common.ef.command.ICommand;

/**
 */
public class TransitionUICommandHelper extends org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper {

  public static TransitionUICommandHelper getInstance() {
    return new TransitionUICommandHelper();
  }

  @Override
  public ICommand getActorTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_ACTOR;
      }
    };
  }

  @Override
  public ICommand getDataTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_DATA;
      }
    };
  }

  @Override
  public ICommand getExchangeItemTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM;
      }
    };
  }

  @Override
  public ICommand getFunctionalTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL;
      }
    };
  }

  @Override
  public ICommand getOCtoSMTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OC2SM;
      }
    };
  }

  @Override
  public ICommand getOAtoSCTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SC;
      }
    };

  }

  @Override
  public ICommand getOAtoSMTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SM;
      }
    };

  }

  @Override
  public ICommand getInterfaceTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE;
      }
    };
  }

  @Override
  public ICommand getLC2PCTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_LC2PC;
      }
    };
  }

  @Override
  public ICommand getStateMachineTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {
    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE;
      }
    };
  }

  @Override
  public ICommand getCapabilityTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY;
      }
    };
  }

  @Override
  public ICommand getOE2SystemTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM;
      }
    };
  }

  /**
   * @param selection_p
   * @param progressMonitor_p
   * @return
   */
  @Override
  public ICommand getOE2ActorTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR;
      }
    };
  }

  /**
   * @param selection_p
   * @param progressMonitor_p
   * @return
   */
  @Override
  public ICommand getPropertyValueTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE;
      }
    };
  }

  /**
   * @param selection_p
   * @param progressMonitor_p
   * @return
   */
  @Override
  public ICommand getSystemTransitionCommand(Collection<Object> elements_p, IProgressMonitor monitor_p) {

    return new IntramodelTransitionCommand(elements_p, monitor_p) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM;
      }
    };
  }

  //TODO not yet implemented !

}
