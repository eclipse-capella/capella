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
  private PreDeleteHandler _handler;

  /**
   * Constructor.
   * @param editingDomain_p
   * @param elements_p
   * @param deleteParts_p
   * @param handler The resulting notifications chain, faking the delete behavior, plus shared data.
   */
  public PreDeleteStructureCommand(EditingDomain editingDomain_p, Collection<?> elements_p, boolean deleteParts_p, PreDeleteHandler handler_p) {
    super(editingDomain_p, elements_p, deleteParts_p);
    _handler = handler_p;
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteCommand#doPrepare()
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void doPrepare() {
    // Fake deleting representations according to semantic elements.
    Collection<? extends EObject> allContainedRepresentationsFor = RepresentationHelper.getAllRepresentationsTargetedBy(_elementsToDelete);
    append(new PreRemoveCommand((Collection<EObject>) allContainedRepresentationsFor, _handler));
    append(new PreRemoveCommand((Collection<EObject>) _elementsToDelete, _handler));
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#deletePointingReference(org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected void deletePointingReference(EObject referencingEObject_p, EStructuralFeature feature_p, EObject referenceToDelete_p) {
    if (feature_p.isMany()) {
      _handler._notifications.add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject_p, Notification.REMOVE, referenceToDelete_p,
          feature_p));
    } else {
      _handler._notifications
          .add(PreRemoveCommand.createNotification((InternalEObject) referencingEObject_p, Notification.SET, referenceToDelete_p, feature_p));
    }
    // Delete specific semantic structure, if any.
    deleteSemanticStructure(referenceToDelete_p, referencingEObject_p, feature_p);
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#doDeleteElement(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Command doDeleteElement(EObject sourceObject_p) {
    return new PreDeleteCommand(_editingDomain, Collections.singletonList(sourceObject_p), _handler);
  }

  /**
   * @see org.polarsys.capella.core.model.handler.command.DeleteStructureCommand#doDeleteStructure(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected Command doDeleteStructure(EObject sourceObject_p) {
    return new PreDeleteStructureCommand(_editingDomain, Collections.singletonList(sourceObject_p), _deleteParts, _handler);
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
