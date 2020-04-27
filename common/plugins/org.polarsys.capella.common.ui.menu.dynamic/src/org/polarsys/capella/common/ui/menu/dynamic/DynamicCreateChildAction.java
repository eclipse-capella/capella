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
package org.polarsys.capella.common.ui.menu.dynamic;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

/**
 * An extended child creation action that takes the Capella creation service into account.
 * 
 * @see org.polarsys.capella.common.menu.dynamic.CreationHelper
 */
public class DynamicCreateChildAction extends CreateChildAction {
  /**
   * Constructor.
   * 
   * @param editingDomain
   * @param selection
   * @param descriptor
   */
  public DynamicCreateChildAction(EditingDomain editingDomain, ISelection selection, Object descriptor) {
    super((IWorkbenchPart) null, selection, descriptor);
    this.editingDomain = editingDomain;
  }

  /**
   * @see org.eclipse.jface.action.Action#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    boolean selection = true;
    EObject owner = getOwner();
    EClass objectClass = ((EObject) ((CommandParameter) descriptor).getValue()).eClass();
    EStructuralFeature feature = getFeature();

    for (IMDEMenuItemContribution contribution : ActionContributionProvider.getInstance()
        .getAllActionContributions(objectClass)) {
      selection &= contribution.selectionContribution((ModelElement) owner, objectClass, feature);
      if (!selection)
        break;
    }
    return selection;
  }

  /**
   * Is this action executable (i.e underlying command is executable) ?
   * 
   * @return
   */
  public boolean isExecutable() {
    boolean result = false;
    // Precondition.
    if (null == command) {
      return result;
    }
    if (command instanceof CreateChildCommand) {
      CreateChildCommand childCommand = (CreateChildCommand) command;
      result = childCommand.getCommand() != UnexecutableCommand.INSTANCE;
    }
    return result;
  }

  /**
   * This executes the command.
   */
  @Override
  public void run() {
    final Command basicCreationCmd = command;
    CompoundCommand cmd = new CompoundCommand();

    // basic creation command
    cmd.append(basicCreationCmd);
    cmd.setLabel(basicCreationCmd.getLabel());
    cmd.setDescription(basicCreationCmd.getDescription());

    // additional stuff command
    Command additionalCommand = new CommandWrapper() {
      @SuppressWarnings("synthetic-access")
      @Override
      public Command createCommand() {
        // retrieve the created element
        Collection<?> collection = basicCreationCmd.getResult();
        if (collection.size() == 1) {
          Object createdElement = collection.iterator().next();
          if (createdElement instanceof AbstractNamedElement) {
            StrictCompoundCommand scc = CreationHelper.getAdditionnalCommand(editingDomain,
                (AbstractNamedElement) createdElement, getOwner(), (CommandParameter) descriptor, getFeature());
            if (scc != null) {
              return scc;
            }
          }
        }
        return new IdentityCommand();
      }
    };
    cmd.append(additionalCommand);

    editingDomain.getCommandStack().execute(cmd);
  }

  /**
   * This returns the owner object upon which the command will act.
   */
  protected EObject getOwner() {
    EObject owner = null;
    Command cmd = ((CreateChildCommand) command).getCommand();
    if (cmd instanceof AddCommand) {
      owner = ((AddCommand) cmd).getOwner();
    } else if (cmd instanceof SetCommand) {
      owner = ((SetCommand) cmd).getOwner();
    }
    return owner;
  }

  /**
   * This returns the feature of the owner object upon the command will act.
   */
  protected EStructuralFeature getFeature() {
    EStructuralFeature feature = null;
    Command cmd = ((CreateChildCommand) command).getCommand();
    if (cmd instanceof AddCommand) {
      feature = ((AddCommand) cmd).getFeature();
    } else if (cmd instanceof SetCommand) {
      feature = ((SetCommand) cmd).getFeature();
    }
    return feature;
  }
}
