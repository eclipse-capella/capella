/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drop;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCopyToClipboardCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaPasteCommand;
import org.polarsys.capella.core.platform.sirius.ui.commands.PasteCommandHelper;

public class CapellaDragAndDropCommand extends DragAndDropCommand {
  public CapellaDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations, int operation,
      Collection<?> collection) {
    super(domain, owner, location, operations, operation, collection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean prepareDropMoveOn() {
    Collection<EObject> pastedElements = enrichPastedElements(owner, collection);
    dropCommand = new CompoundCommand();
    PasteCommandHelper.createPasteCommands(pastedElements, (CompoundCommand) dropCommand, (EObject) owner, null, domain,
        -1, true);
    dragCommand = RemoveCommand.create(domain, pastedElements);
    return dragCommand.canExecute() && dropCommand.canExecute();
  }

  /**
   * Prepare a drop copy on. We simply copy the selection to the clipboard, followed by an immediate paste. We also
   * clean the clipboard after the operation completes.
   *
   * TODO: There's no real need to occupy the editing domain clipboard here. We could just use a CopyCommand and use its
   * result for the drop. Unfortunately we're bound to the clipboard method because it incrusts does some semantic
   * tuning (see for example CapellaPasteCommand.prepareAssociationPaste, which I won't touch)
   */
  @Override
  protected boolean prepareDropCopyOn() {
    boolean result = false;
    dragCommand = new CapellaCopyToClipboardCommand(domain, collection, null);
    if (dragCommand.canExecute()) {

      dragCommand.execute();
      isDragCommandExecuted = true;

      dropCommand = new CommandWrapper(new CapellaPasteCommand(domain, owner, null, CommandParameter.NO_INDEX)) {
        @SuppressWarnings("synthetic-access")
        @Override
        public void execute() {
          super.execute();
          domain.getClipboard().clear();
          SharedCopyPasteElements.getInstance().clear();
        }
      };
      if (dropCommand.canExecute()) {
        result = true;
      } else {
        dragCommand.undo();
        isDragCommandExecuted = false;
      }
    }
    return result;
  }

  protected Collection<EObject> enrichPastedElements(Object owner, Collection<?> collection) {
    Set<EObject> pastedElements = new HashSet<>();
    pastedElements.addAll(
        collection.stream().filter(EObject.class::isInstance).map(EObject.class::cast).collect(Collectors.toList()));

    if (owner instanceof EObject
        && !TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((EObject) owner))) {
      // If a component is DnD, its part is also DnD
      List<Part> partsToMoveWithComponent = collection.stream().filter(Component.class::isInstance)
          .map(Component.class::cast).flatMap(component -> component.getRepresentingParts().stream())
          .filter(part -> !collection.contains(part)).collect(Collectors.toList());
      pastedElements.addAll(partsToMoveWithComponent);

      // If a part is DnD, its component is also DnD
      List<AbstractType> componentsToMoveWithPart = collection.stream().filter(Part.class::isInstance)
          .map(Part.class::cast).filter(part -> part.getAbstractType() != null).map(part -> part.getAbstractType())
          .filter(component -> !collection.contains(component)).collect(Collectors.toList());
      pastedElements.addAll(componentsToMoveWithPart);
    }
    return pastedElements;
  }
}
