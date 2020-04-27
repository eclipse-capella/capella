/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A helper interface that contains business logic which selects
 * model elements for the CapellaDeleteCommand.
 * 
 * Note: Unfortunately, not ALL business rules that control deletion of model elements
 *       are captured via this interface. Additional rules are defined under:
 *       
 *       org.polarsys.capella.core.model.handler.command.DeleteStructureCommand
 * 
 */
public interface IDeleteHelper {

  /**
   * The default IDeleteHelper that should be used in all delete operations.
   */
  public static IDeleteHelper DEFAULT = new CompoundDeleteHelper();

  /**
   * If the elements in the argument should be deleted, which
   * other elements must also be deleted according to business
   * rules? The resulting set will usually at least contain all
   * elements in the argument, unless it contains
   * elements that can/should not be deleted for whatever reason.
   * 
   * @param selection_p
   */
  public Collection<?> getExpandedSelection(Collection<?> selection_p);

  /**
   * Return whether sourceObject_p should be deleted through a DeleteStructureCommand when linked through feature_p to the linkObject_p
   * @param linkObject_p
   * @param sourceObject_p
   * @param feature_p
   * @return
   */
  public boolean isDeleteSemanticStructure(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p);

  /**
   * Return whether sourceObject_p should be deleted through a DeleteCommand when linked through feature_p to the linkObject_p
   * @param linkObject_p
   * @param sourceObject_p
   * @param feature_p
   * @return
   */
  public boolean isDeleteElement(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p);

  /**
   * Return all elements to be deleted through a DeleteStructureCommand when sourceObject_p is linked through feature_p to the linkObject_p
   * @param linkObject_p
   * @param sourceObject_p
   * @param feature_p
   * @return
   */
  public Collection<EObject> getAdditionalElements(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p);

  /**
   * Retrieve additional commands to be performed when sourceObject_p is linked through feature_p to the linkObject_p
   * (this can be useful when removing sourceObject_p linked to linkObject_p lead to change an attribute to the linkObject_p)
   * 
   * @param sourceObject_p
   * @param linkObject_p
   * @param feature_p
   * @return
   */
  public Collection<Command> getAdditionalCommands(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p);

}
