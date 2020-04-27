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
package org.polarsys.capella.core.model.handler.command;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

/**
 * The very same command as {@link DeleteStructureCommand} but it does nothing to the model.<br>
 * Instead, it creates EMF notifications of what will be done, letting the user confirm the behavior.
 */
public class PreDeleteStructureCommand extends DeleteStructureCommand {
  /**
   * Pre-delete handler.
   */
  private PreDeleteHandler handler;

  /**
   * Constructor.
   * @param editingDomain
   * @param elements
   * @param handler The resulting notifications chain, faking the delete behavior, plus shared data.
   */
  public PreDeleteStructureCommand(EditingDomain editingDomain, Collection<?> elements, PreDeleteHandler handler) {
	  super(editingDomain, elements);
	  this.handler = handler;
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteCommand#doPrepare()
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doPrepare() {
    // Fake deleting representations according to semantic elements.
    Collection<? extends EObject> allContainedRepresentationsFor = RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(getElementsToDelete());
    append(new PreRemoveCommand((Collection<EObject>) allContainedRepresentationsFor, handler));
    append(new PreRemoveCommand((Collection<EObject>) getElementsToDelete(), handler));
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#deletePointingReference(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void deletePointingReference(EObject referencingEObject, EStructuralFeature feature, EObject referenceToDelete) {
    if (feature.isMany()) {
      handler.notifications.add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject, Notification.REMOVE, referenceToDelete,
          feature));
    } else {
      handler.notifications
          .add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject, Notification.SET, referenceToDelete, feature));
    }
    // Delete specific semantic structure, if any.
    deleteSemanticStructure(referenceToDelete, referencingEObject, feature);
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#doDeleteElement(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Command doDeleteElement(EObject sourceObject) {
    return new PreDeleteCommand(getEditingDomain(), Collections.singletonList(sourceObject), handler);
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#doDeleteStructure(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Command doDeleteStructure(EObject sourceObject) {
    return new PreDeleteStructureCommand(getEditingDomain(), Collections.singletonList(sourceObject), handler);
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#canUndo()
   */
  @Override
  public boolean canUndo() {
    return false;
  }

  @Override
  protected boolean runAdditionalCommands() {
    return false;
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#redo()
   */
  @Override
  public void redo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#undo()
   */
  @Override
  public void undo() {
    // Nothing to do, the command is neither undoable, nor redoable.
  }

}
