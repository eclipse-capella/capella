/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * A command stack listener used to retrieve elements created or relevant from the last command triggered on a command
 * stack This listener triggers an event on the registered ICommandStackSelectionProvider
 */
public class NavigatorCommandStackListener implements CommandStackListener {
  // Field is never null
  WeakReference<Command> mostRecent = new WeakReference<>(null);

  ICommandStackSelectionProvider callback;

  public NavigatorCommandStackListener(ICommandStackSelectionProvider callback) {
    this.callback = callback;
  }

  @Override
  public void commandStackChanged(final EventObject event) {

    // Get the most recent command.
    final Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();

    // We also get notified on rollbacks, so better verify if we've handled this command before
    // Not sure if this is the best solution.
    if (mostRecent.get() != mostRecentCommand) {
      mostRecent = new WeakReference<>(mostRecentCommand);
    } else {
      return;
    }

    if (isRelevant(mostRecentCommand)) {

      // Try to select the affected objects.
      Collection<?> affectedObjects = mostRecentCommand.getAffectedObjects();
      // Select and reveal objects.
      if ((null != affectedObjects) && !affectedObjects.isEmpty()) {
        Object selectedElement = affectedObjects.iterator().next();
        // Take into account the first contained element, we don't want to select the other ones.
        // For instance, when creating a property for a class, we don't want to select its min & max cards.
        if (selectedElement != null) {
          selectionChanged(new StructuredSelection(selectedElement));
        }
      }
    }
  }

  protected void selectionChanged(ISelection selection) {
    this.callback.commandStackSelectionChanged(selection);
  }

  /**
   * Handle Delete commands.
   * 
   * @param affectedObjects
   * @deprecated
   */
  @Deprecated
  protected ISelection handleDeleteCommand(Collection<?> affectedObjects) {
    List<EObject> elementsToSelect = new ArrayList<>(0);
    for (Object affectedObject : affectedObjects) {
      if (affectedObject instanceof EObject) {
        EObject parent = null;
        try {
          parent = ((EObject) affectedObject).eContainer();
        } catch (Exception exception) {
          // With CDO when closing the Sirius session, a late event to select a CDO object can occur.
          // If the underlying CDO transaction is closed, we can't call eContainer(). in that case don't select
          // something.
        }
        if (null != parent) {
          elementsToSelect.add(parent);
        }
      }
    }
    return new StructuredSelection(elementsToSelect);
  }

  /**
   * Default implementations filters out all recording commands.
   * 
   * @param mostRecentCommand
   * @return
   */
  protected boolean shouldSelectAndReveal(RecordingCommand mostRecentCommand) {
    return false;
  }

  /**
   * Should handle most recent command to select and reveal elements in current common viewer.<br>
   * Default implementation filters out :
   * <ul>
   * <li><code>SetCommand</code> performed with new value of String kind.</li>
   * <li><code>CompoundCommand</code> containing SetCommand matching previous case.</li>
   * <li><code>RecordingCommand</code> see {@link #shouldSelectAndReveal(RecordingCommand)}.
   * </ul>
   * 
   * @param mostRecentCommand
   * @return <code>true</code> means selecteAndReveal needed.
   */
  protected boolean isRelevant(Command mostRecentCommand) {
    // Precondition
    if (null == mostRecentCommand) {
      return false;
    }
    boolean shouldHandleMostRecentCommand = true;
    if (mostRecentCommand instanceof SetCommand) {
      SetCommand setCommand = (SetCommand) mostRecentCommand;
      // Filter out eSet with String value.
      shouldHandleMostRecentCommand = !(setCommand.getValue() instanceof String);
    } else if (mostRecentCommand instanceof CompoundCommand) {
      CompoundCommand compoundCommand = (CompoundCommand) mostRecentCommand;
      // Loop over contained commands.
      for (Command command : compoundCommand.getCommandList()) {
        shouldHandleMostRecentCommand = isRelevant(command);
        if (!shouldHandleMostRecentCommand) {
          break;
        }
      }
    } else if (mostRecentCommand instanceof RecordingCommand) {
      shouldHandleMostRecentCommand = shouldSelectAndReveal((RecordingCommand) mostRecentCommand);
    }
    return shouldHandleMostRecentCommand;
  }

  /**
   * @param editingDomain
   */
  public void registerCommandStackListener(SemanticEditingDomain editingDomain) {
    editingDomain.getCommandStack().addCommandStackListener(this);
  }

  /**
   * @param editingDomain
   */
  public void unregisterCommandStackListener(SemanticEditingDomain editingDomain) {
    if (null != editingDomain) {
      CommandStack commandStack = editingDomain.getCommandStack();
      if (null != commandStack) {
        commandStack.removeCommandStackListener(this);
      }
    }
  }
}
