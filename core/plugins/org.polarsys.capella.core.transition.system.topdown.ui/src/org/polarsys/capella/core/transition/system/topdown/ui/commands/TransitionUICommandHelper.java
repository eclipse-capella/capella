/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.ui.commands;

import java.util.Collection;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.transition.system.topdown.ui.Activator;

public class TransitionUICommandHelper
    extends org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper {

  public static TransitionUICommandHelper getInstance() {
    return new TransitionUICommandHelper();
  }

  public void executeCommand(String id, Collection<? extends Object> list) {
    IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);

    Command command = commandService.getCommand(id);
    // Create a ParameterizedCommand with no parameter
    ParameterizedCommand parameterizedCommand = new ParameterizedCommand(command, null);
    EvaluationContext context = new EvaluationContext(null, list);

    try {
      handlerService.executeCommandInContext(parameterizedCommand, null, context);
    } catch (ExecutionException exception) {

    } catch (NotDefinedException exception) {

    } catch (NotEnabledException exception) {

    } catch (NotHandledException exception) {

    }
  }

  @Override
  public ICommand getActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_ACTOR;
      }
    };
  }

  @Override
  public ICommand getDataTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_DATA;
      }
    };
  }

  @Override
  public ICommand getExchangeItemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM;
      }
    };
  }

  @Override
  public ICommand getFunctionalTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_FUNCTIONAL;
      }
    };
  }

  @Override
  public ICommand getOCtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OC2SM;
      }
    };
  }

  @Override
  public ICommand getOAtoSCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SC;
      }
    };

  }

  @Override
  public ICommand getOAtoSMTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OA2SM;
      }
    };

  }

  @Override
  public ICommand getInterfaceTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE;
      }
    };
  }

  @Override
  public ICommand getLC2PCTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_LC2PC;
      }
    };
  }

  @Override
  public ICommand getStateMachineTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE;
      }
    };
  }

  @Override
  public ICommand getCapabilityTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_CAPABILITY;
      }
    };
  }

  @Override
  public ICommand getOE2SystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2SYSTEM;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  @Override
  public ICommand getOE2ActorTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_OE2ACTOR;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  @Override
  public ICommand getPropertyValueTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PROPERTYVALUE;
      }
    };
  }

  /**
   * @param selection
   * @param progressMonitor
   * @return
   */
  @Override
  public ICommand getSystemTransitionCommand(Collection<?> elements, IProgressMonitor monitor) {

    return new IntramodelTransitionCommand(elements, monitor) {

      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_SYSTEM;
      }
    };
  }

  public ICommand getPC2CITransitionCommand(Collection<?> elements, IProgressMonitor monitor) {
    return new IntramodelTransitionCommand(elements, monitor) {
      @Override
      protected String getTransitionKind() {
        return ITopDownConstants.TRANSITION_TOPDOWN_PC2CI;
      }

      @Override
      public void run() {
        if (!PreferenceHelper.getInstance().transitionPC2CIWhileScenarioTransition()) {
          String message = Messages.TransitionUICommandHelper_PC2CI_EnablePreference;
          MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getName(),
              message);
          LogExt.log(IReportManagerDefaultComponents.DEFAULT,
              new Status(IStatus.WARNING, Activator.getDefault().getBundle().getSymbolicName(), message));
        } else {
          super.run();
        }
      }
    };
  }
}
