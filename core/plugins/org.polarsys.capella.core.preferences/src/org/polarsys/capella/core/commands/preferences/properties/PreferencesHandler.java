/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.properties;

import static org.eclipse.ui.internal.handlers.LegacyHandlerService.LEGACY_H_ID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.commands.ExpressionContext;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.menus.UIElement;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.preferences.Activator;

/**
 */
public class PreferencesHandler extends AbstractHandler implements IElementUpdater {

  private static HashMap<Command, PreferencesHandler> instances = new HashMap<Command, PreferencesHandler>();

  public static PreferencesHandler getInstance(Command command) {
    if (!instances.containsKey(command)) {
      instances.put(command, new PreferencesHandler(command));
    }
    return instances.get(command);
  }

  private static Map<String, List<IHandlerActivation>> pluginHandlers = new HashMap<String, List<IHandlerActivation>>(
      0);

  /*
   *  
   */
  private Command currentCommand;

  private static IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench()
      .getService(IHandlerService.class);

  /*
   * 
   */
  private boolean isEnabledCommand;

  private void registerHandlerActivation(IHandlerActivation activation) {
    if (!pluginHandlers.containsKey(activation.getCommandId())) {
      pluginHandlers.put(activation.getCommandId(), new ArrayList<IHandlerActivation>());
    }
    pluginHandlers.get(activation.getCommandId()).add(activation);
  }

  private void registerHandlerActivations(List<IHandlerActivation> activations) {
    for (IHandlerActivation activation : activations) {
      registerHandlerActivation(activation);
    }
  }

  protected IEclipsePreferences getPreferences() {
    return InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID);
  }

  private List<IHandlerActivation> getRegisteredHandlerActivations(IHandlerService service, String commandId) {
    if (!pluginHandlers.containsKey(commandId)) {
      return Collections.emptyList();
    }
    return pluginHandlers.get(commandId);
  }

  private PreferencesHandler(Command command) {
    this.currentCommand = command;
    registerHandlerActivations(getHandlerActivations(handlerService, currentCommand.getId()));
  }

  /**
   * According to the preference, we enable or disable the command. Underlying this, we enable the preferenceHandler if
   * command is disabled. Otherwise, we enable original handler(s)
   */
  private void activateHandler(Command command) {
    boolean isCommandEnabled = isEnabled();
    boolean isPreferenceRegistered = false;

    // We enable the preferenceHandler if command is disabled. Otherwise, we enable original handler(s)
    for (IHandlerActivation activation : getRegisteredHandlerActivations(handlerService, currentCommand.getId())) {
      boolean isPreferenceHandler = activation.getHandler().equals(this);
      if (isPreferenceHandler) {
        if (isCommandEnabled) {
          if (getHandlerActivations(handlerService, currentCommand.getId()).contains(activation)) {
            // We desactivate only if it's here. (performance issue otherwise)
            handlerService.deactivateHandler(activation);
          }
        } else {
          handlerService.activateHandler(activation);
        }
        isPreferenceRegistered = true;

      } else {
        if (isCommandEnabled) {
          handlerService.activateHandler(activation);
        } else {
          handlerService.deactivateHandler(activation);
        }
      }
    }

    if (!isPreferenceRegistered && !isCommandEnabled) {
      registerHandlerActivation(getPreferenceHandlerActivation());
    }

  }

  @SuppressWarnings("restriction")
  private IEclipseContext getEclipseContext(IEvaluationContext evalContext) {
    if (evalContext instanceof ExpressionContext) {
      return ((ExpressionContext) evalContext).eclipseContext;
    }
    return evalContext != null ? getEclipseContext(evalContext.getParent()) : null;
  }

  private List<IHandlerActivation> getHandlerActivations(IHandlerService service, String commandId) {
    IEclipseContext eclipseContext = getEclipseContext(service.getCurrentState());
    List<IHandlerActivation> handlerActivations = (List<IHandlerActivation>) eclipseContext
        .getLocal(LEGACY_H_ID + commandId);
    return handlerActivations;
  }

  @Override
  public boolean isEnabled() {
    IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(currentCommand.getId());
    boolean result = commandDescriptor == null ? true
        : getPreferences().getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());
    return result;
  }

  /**
   * 
   */
  private IHandlerActivation getPreferenceHandlerActivation() {
    IHandlerActivation handlerActivation = handlerService.activateHandler(currentCommand.getId(), this,
        new Expression() {

          @Override
          public EvaluationResult evaluate(IEvaluationContext context_p) throws CoreException {
            Object selection = context_p.getVariable("selection");
            boolean result = true;
            if ((selection != null) && (selection instanceof ISelection)) {
              IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance()
                  .getDescriptor(currentCommand.getId());
              result = commandDescriptor == null ? true
                  : getPreferences().getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());
            }

            IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance()
                .getDescriptor(currentCommand.getId());
            result = commandDescriptor == null ? true
                : getPreferences().getBoolean(currentCommand.getId(), commandDescriptor.isEnabledByDefault());

            return EvaluationResult.valueOf(result);
          }

          @Override
          public void collectExpressionInfo(final ExpressionInfo info) {
            info.markDefaultVariableAccessed();
          }
        });
    return handlerActivation;
  }

  /**
   * {@inheritDoc}
   */

  public Object execute(ExecutionEvent event) throws ExecutionException {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateElement(UIElement element, Map parameters) {
    if (!isEnabledCommand) {
      element.setIcon(Activator.getImageDescriptor("preference.gif"));
      element.setHoverIcon(Activator.getImageDescriptor("preference.gif"));
      element.setTooltip("this element is disabled from preferences");
    }
  }

  public static void initializePreferenceCommands() {
    ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
    for (Command command : commandService.getDefinedCommands()) {
      IItemDescriptor commandDescriptor = PreferencesItemsRegistry.getInstance().getDescriptor(command.getId());
      if ((commandDescriptor != null) && command.isDefined()) {
        PreferencesHandler.getInstance(command).activateHandler(command);
        commandService.refreshElements(command.getId(), null);

      } else if (command.getId().equals("org.eclipse.ui.file.export")) {
        ExportPreferencesHandler.getInstance(command);
      }
    }
  }

}
