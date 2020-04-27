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
package org.polarsys.capella.core.ui.toolkit.actions.move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;

/**
 * Base class to implement a move action for selected Capella Elements.<br>
 * Selected elements must have the same type and the same parent.
 */
public abstract class AbstractMoveAction extends AbstractCommandActionHandler {

  /**
   * Constructor.
   * 
   * @param text
   */
  protected AbstractMoveAction(String text) {
    super(text);
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean updateSelection(IStructuredSelection selection) {
    boolean result = true;
    if (!selection.isEmpty()) {
      Iterator<?> iterator = selection.iterator();
      EObject parent = null;
      while (iterator.hasNext() && result) {
        Object selectedObject = iterator.next();
        if (!(CapellaResourceHelper.isSemanticElement(selectedObject))) {
          result = false;
        } else {
          EObject selectedElement = (EObject) selectedObject;
          // Selected elements must have the same parent i.e container.
          if ((null != parent) && !selectedElement.eContainer().equals(parent)) {
            result = false;
          } else {
            parent = selectedElement.eContainer();
            // The parent must be not null to be able to move its children.
            result = (null != parent) ? true : false;
          }
        }
      }
    } else {
      result = false;
    }
    if (result) {
      // Take into account created command criteria.
      result = super.updateSelection(selection);

    } else {
      // If selection is invalid, free current command to avoid memory leak
      setCommand(createUnexecutableCommand());
    }
    return result;
  }

  /**
   * Capella move command.<br>
   * This class is intended to be run only by {@link AbstractMoveAction}.<br>
   * Selected elements must have the same type and the same parent.
   */
  protected class CapellaMoveCommand extends CompoundCommand {

    /**
     * This is the collection of objects to be moved.
     */
    private List<EObject> _collection;

    /**
     * The way the move has to be performed.
     */
    private boolean _isMovingUp;

    /**
     * @return the sortedElementToMove
     */
    protected List<EObject> getSortedElementsToMove() {
      // Sort selected elements by their index in the containment feature.
      Collections.sort(_collection, new Comparator<Object>() {
        /**
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @SuppressWarnings("unchecked")
        public int compare(Object o1, Object o2) {
          int result = 0;
          if ((o1 instanceof EObject) && (o2 instanceof EObject)) {
            EObject object1 = (EObject) o1;
            EObject object2 = (EObject) o2;
            // Get the container of 1st object. 2nd object has the same one, this condition has been checked earlier.
            EObject container = object1.eContainer();
            // Get the containing feature.
            EReference containmentFeature = object1.eContainmentFeature();
            // Get children.
            List<EObject> children = (List<EObject>) container.eGet(containmentFeature);
            // Get indexes
            int index1 = children.indexOf(object1);
            int index2 = children.indexOf(object2);
            // Compare indexes.
            if (index1 > index2) {
              result = 1;
            } else if (index2 > index1) {
              result = -1;
            }
          }
          return result;
        }
      });
      return _collection;
    }

    /**
     * Constructor.
     * 
     * @param label
     * @param collection
     * @param isMovingUp
     */
    public CapellaMoveCommand(String label, Collection<EObject> collection, boolean isMovingUp) {
      super(MERGE_COMMAND_ALL, label, Messages.AbstractMoveAction_MoveCommand_Description);
      _collection = new ArrayList<EObject>(collection);
      _isMovingUp = isMovingUp;
    }

    /**
     * Override this method to postpone nested commands creation.
     * 
     * @see org.eclipse.emf.common.command.CompoundCommand#prepare()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected boolean prepare() {
      boolean result = true;
      // Iterate over selected elements to check all conditions are fitted to be able to run the command.
      for (Iterator<?> iterator = _collection.iterator(); iterator.hasNext() && result;) {
        EObject object = (EObject) iterator.next();
        EObject container = object.eContainer();
        // Get the containing feature.
        EReference containmentFeature = object.eContainmentFeature();
        // To move elements the containment feature must have more than one value possible.
        if (!containmentFeature.isMany()) {
          result = false;
          break;
        }
        // Get the current index for current object.
        List<EObject> children = (List<EObject>) container.eGet(containmentFeature);
        int index = children.indexOf(object);
        // Handle border index.
        if ((index == 0) && _isMovingUp) {
          // Move up an object at the top index is impossible.
          result = false;
          break;
        }
        if ((index == (children.size() - 1)) && !_isMovingUp) {
          // Move down an object at the last index is impossible.
          result = false;
          break;
        }
      }
      return result;
    }

    /**
     * Override this method because nested move commands are instantiated just before their execution.<br>
     * That ensures all indexes are up-to-date when a move command is run.
     * 
     * @see org.eclipse.emf.common.command.CompoundCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
      List<EObject> moveable = getSortedElementsToMove();
      EditingDomain domain = TransactionHelper.getEditingDomain(moveable);

      for (Object name : moveable) {
        EObject object = (EObject) name;
        EObject container = object.eContainer();
        // Get the containing feature.
        EReference containmentFeature = object.eContainmentFeature();
        // Get the current index for current object.
        List<EObject> children = (List<EObject>) container.eGet(containmentFeature);
        int index = children.indexOf(object);
        // Compute the new position.
        index += _isMovingUp ? -1 : 1;
        // Append and execute immediately the command.
        appendAndExecute(MoveCommand.create(domain, container, containmentFeature, object, index));
      }
    }
  }
}
