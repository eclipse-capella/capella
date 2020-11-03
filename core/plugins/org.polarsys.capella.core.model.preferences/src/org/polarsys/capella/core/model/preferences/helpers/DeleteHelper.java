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
package org.polarsys.capella.core.model.preferences.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.ConstraintDuration;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.model.handler.command.PreDeleteHandler;
import org.polarsys.capella.core.model.handler.command.PreDeleteStructureCommand;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

/**
 * Helper to compute all deleted semantic elements from a collection of semantic elements to delete applying business delete rules.
 * @deprecated use org.polarsys.capella.core.platform.sirius.ui.commands.IDeleteHelper
 */
class DeleteHelper {

  /**
   * Add elements to delete for {@link Association}.
   * @param elementsToDelete
   */
  private static void addElementsForAssociation(Collection<Object> elementsToDelete) {
    List<Object> elementToAdd = new ArrayList<Object>(0);
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof Association) {
        elementToAdd.addAll(((Association) elementToDelete).getNavigableMembers());
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete.addAll(elementToAdd);
    }
  }

  /**
   * Add elements to delete for {@link FunctionalChainInvolvement}.
   * @param elementsToDelete
   */
  private static void addElementsForFunctionalChainInvolvement(Collection<Object> elementsToDelete) {
    List<Object> elementToAdd = new ArrayList<Object>(0);
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof FunctionalChainInvolvement) {
        FunctionalChainInvolvement fcInvolvement = (FunctionalChainInvolvement) elementToDelete;
        InvolvedElement involved = fcInvolvement.getInvolved();
        if ((null != involved) && (involved instanceof AbstractFunction)) {
          // Add next involvements.
          elementToAdd.addAll(fcInvolvement.getNextFunctionalChainInvolvements());
          // Add previous involvements.
          elementToAdd.addAll(fcInvolvement.getPreviousFunctionalChainInvolvements());
        }
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete.addAll(elementToAdd);
    }
  }

  /**
   * Add elements to delete for {@link PhysicalPathInvolvement}.
   * @param elementsToDelete
   */
  private static void addElementsForPhysicalPathInvolvement(Collection<Object> elementsToDelete) {
    List<Object> elementToAdd = new ArrayList<Object>(0);
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement pathInvolvement = (PhysicalPathInvolvement) elementToDelete;
        AbstractPathInvolvedElement involved = pathInvolvement.getInvolvedElement();
        if ((null != involved) && (involved instanceof Part)) {
          // Add next involvements.
          elementToAdd.addAll(pathInvolvement.getNextInvolvements());
          // Add previous involvements.
          elementToAdd.addAll(pathInvolvement.getPreviousInvolvements());
        }
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete.addAll(elementToAdd);
    }
  }

  /**
   * Add elements that are pointed (referenced by end-user selection.<br>
   * Default RemoveCommand only deletes elements of containment relationships.
   * @param elementsToDelete
   */
  public static void addInAdditionSomeReferencedElemensInDelete(Collection<Object> elementsToDelete) {
    // Special case for Physical Path Involvements (we add previous and next involvements if the involved element is a Part).
    addElementsForPhysicalPathInvolvement(elementsToDelete);

    // special case for state and modes and abstractFunction: sequence diagram deletion
    addElementsForAbstractState (elementsToDelete);
    addElementsForAbstractFunction (elementsToDelete);

    // Special case for functional chain involvements (we add previous and next involvements if the involved element is an AbstractFunction).
    addElementsForFunctionalChainInvolvement(elementsToDelete);
    // Special case for association, we add navigable members i.e properties that are not owned by the association but targeted elements.
    addElementsForAssociation(elementsToDelete);
    // Get all elements for a scenario.
    globalizeElementsForScenario(elementsToDelete);
    // Special case for property values.
    addPendingPropertyValues(elementsToDelete);
    // Special case for property value groups.
    addPendingPropertyValueGroups(elementsToDelete);
    
    
    
  }

  private static void addElementsForAbstractFunction(
		Collection<Object> elementsToDelete) {
	  List<EObject> elementsToAdd = new ArrayList<EObject>();
	    for (Object elementToDelete : elementsToDelete) {
	        if (elementToDelete instanceof AbstractFunction) {
	        	elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION));
	        }
	      }
	    elementsToDelete.addAll(elementsToAdd);
	
}

private static void addElementsForAbstractState(
		Collection<Object> elementsToDelete) {
	List<EObject> elementsToAdd = new ArrayList<EObject>();
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof AbstractState) {
    	  elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete, InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE));
      }
    }
    elementsToDelete.addAll(elementsToAdd);
}

/**
   * Add pending property values in addition to their elements if necessary.
   * @param elementsToDelete
   */
  private static void addPendingPropertyValues(Collection<Object> elementsToDelete) {
    // This algorithm is specified by Loic Petit.
    Set<Object> propertyValuesToAddToDeletedElements = new HashSet<Object>(0);
    // Loop over elements to delete.
    for (Object object : elementsToDelete) {
      if (object instanceof CapellaElement) {
        // Get applied property values.
        List<AbstractPropertyValue> appliedPropertyValues = ((CapellaElement) object).getAppliedPropertyValues();
        // Loop over property values to collect only the ones which have only current capella element as involvedElements.
        for (AbstractPropertyValue propertyValue : appliedPropertyValues) {
          // Is it a pending property value ? i.e valueElements must contain only current object and involvedElements must be empty.
          List<CapellaElement> valuedElements = propertyValue.getValuedElements();
          if ((propertyValue!= object) && propertyValue.getInvolvedElements().isEmpty() && (valuedElements.size() == 1 && valuedElements.contains(object))) {
            // That's it !
            ArrayList<Object> propertyValues = new ArrayList<Object>(1);
            propertyValues.add(propertyValue);
            // Recurse algorithm on this pending property value to remove other pending property values linked to this one.
            addPendingPropertyValues(propertyValues);
            propertyValuesToAddToDeletedElements.addAll(propertyValues);
          }
        }
      }
    }
    if (!propertyValuesToAddToDeletedElements.isEmpty()) {
      elementsToDelete.addAll(propertyValuesToAddToDeletedElements);
    }
  }

  /**
   * Add pending property value groups in addition to their elements if necessary.
   * @param elementsToDelete
   */
  private static void addPendingPropertyValueGroups(Collection<Object> elementsToDelete) {
    // This algorithm is specified by Loic Petit.
    Set<Object> propertyValueGroupsToAddToDeletedElements = new HashSet<Object>(0);
    // Loop over elements to delete.
    for (Object object : elementsToDelete) {
      if (object instanceof CapellaElement) {
        // Get applied property values.
        List<PropertyValueGroup> appliedPropertyValueGroups = ((CapellaElement) object).getAppliedPropertyValueGroups();
        // Loop over property value groups to collect only the ones which have only current capella element as involvedElements.
        for (PropertyValueGroup propertyValueGroup : appliedPropertyValueGroups) {
          // StackOverflow deleting property value applied on itself
          if (!elementsToDelete.contains(propertyValueGroup)) {
            // Is it a pending property value group ? i.e valueElements must contain only current object and involvedElements must be empty.
            List<CapellaElement> valuedElements = propertyValueGroup.getValuedElements();
            if (valuedElements.size() == 1 && valuedElements.contains(object)) {
              // That's it !
              ArrayList<Object> propertyValueGroups = new ArrayList<Object>(1);
              propertyValueGroups.add(propertyValueGroup);
              // Recurse algorithm on this pending property value to remove other pending property values linked to this one.
              addPendingPropertyValueGroups(propertyValueGroups);
              propertyValueGroupsToAddToDeletedElements.addAll(propertyValueGroups);
            }
          }
        }
      }
    }
    if (!propertyValueGroupsToAddToDeletedElements.isEmpty()) {
      elementsToDelete.addAll(propertyValueGroupsToAddToDeletedElements);
    }
  }

  @SuppressWarnings("unchecked")
  public static boolean computeAllDeletedElementsFor(Collection<?> elementsToDelete, HashSet<EObject> deletedElements,
      TransactionalEditingDomain editingDomain) {
    // Get a new handler.
    PreDeleteHandler handler = new PreDeleteHandler();
    /*
     * Tweak the selection to add components related to selected parts. Be careful, this code is also mandatory in the execute method. Indeed, From diagrams
     * editor, confirmDeletion is not called at command execution. Hence, we must duplicate this code to make sure the input selection is well filled in. From
     * diagrams editor, this command is called twice : one only to get a deleted elements preview (through static method) and after end-user confirmation, a new
     * command (without confirmation) is run to really delete elements.
     */
    boolean preferenceDeleteParts = CapellaModelPreferencesPlugin.getDefault().isDeletePartsAllowed();
    Collection<Object> eltsToDelete = (Collection<Object>) elementsToDelete;
    eltsToDelete.addAll(getAdditionalElementsForParts(eltsToDelete, preferenceDeleteParts));

    // Add additional elements in elements to delete.
    addInAdditionSomeReferencedElemensInDelete((Collection<Object>) elementsToDelete);

    // Call predeletion command.
    Command preDeletion = new PreDeleteStructureCommand(editingDomain, elementsToDelete, handler);
    if (preDeletion.canExecute()) {
      preDeletion.execute();
    }
    boolean modelModified = handler.notifications.size() > 0;
    for (Notification notification : handler.notifications) {
      Object notifier = notification.getNotifier();
      if (notifier instanceof EObject) {
        // The model is modified, this is likely the user will want to know about that.
        modelModified = true;
        // Get old value (ie removed one).
        Object oldValue = notification.getOldValue();
        int notificationType = notification.getEventType();
        switch (notificationType) {
          // Set case.
          // Handle it as a remove, as long as there is a null new value (and a not null old one, but that part is tested within the remove case
          // directly).
          case Notification.SET:
            if (null != notification.getNewValue()) {
              break;
            }
            //$FALL-THROUGH$
          case Notification.REMOVE:
            if (oldValue instanceof EObject) {
              boolean handleNotification = false;
              try {
                EReference feature = EReference.class.cast(notification.getFeature());
                handleNotification = feature.isContainment();
              } catch (ClassCastException cce) {
                // Could not tell feature, add notification whatever it might be.
                handleNotification = true;
              }
              if (handleNotification) {
                // Add the deleted element.
                EObject deletedObject = (EObject) oldValue;
                deletedElements.add(deletedObject);
                // Filter out children of non Capella model elements as DRepresentation for instance.
                if (deletedObject instanceof ModelElement) {
                  // Add the deleted element subtree.
                  TreeIterator<EObject> allChildrenOfDeletedObject = deletedObject.eAllContents();
                  while (allChildrenOfDeletedObject.hasNext()) {
                    EObject child = allChildrenOfDeletedObject.next();
                    deletedElements.add(child);
                  }
                }
              }
            }
          break;
          default:
          break;
        }
      }
    }
    handler.dispose();
    return modelModified;
  }

  public static Collection<Object> getAdditionalElementsForParts(Collection<Object> elementsToDelete, boolean preferenceDeleteParts) {
    Collection<Object> result = new ArrayList<Object>(0);

    // Remove type if we are singleton driven or if preference is enabled
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof Part) {
        boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) elementToDelete));
        if (!allowMultiplePart || preferenceDeleteParts) {
          AbstractType type = ((Part) elementToDelete).getAbstractType();
          if ((type != null) && !elementsToDelete.contains(type) && !result.contains(type)) {
            result.add(type);
          }
        }
      }
    }

    return result;
  }

  /**
   * return the other side of the sequence message, the message and the related event
   * @param interactionFragment
   * @return
   */
  private static Collection<? extends EObject> getAllObjectsFromAbstractEnd(InteractionFragment interactionFragment) {
    List<EObject> objectsToDelete = new ArrayList<EObject>();
    List<Event> eventsToDelete = new ArrayList<Event>();
    if (interactionFragment instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) interactionFragment;
      SequenceMessage message = messageEnd.getMessage();
      objectsToDelete.add(messageEnd);
      if (message != null) {
        objectsToDelete.add(message);
        if (message.getSendingEnd() != null && !objectsToDelete.contains(message.getSendingEnd()))
          objectsToDelete.add(message.getSendingEnd());
        if (message.getReceivingEnd() != null && !objectsToDelete.contains(message.getReceivingEnd()))
          objectsToDelete.add(message.getReceivingEnd());
      }
    } else if (interactionFragment instanceof ExecutionEnd) {
      ExecutionEnd execEnd = (ExecutionEnd) interactionFragment;
      objectsToDelete.add(execEnd);
    } else if (interactionFragment instanceof FragmentEnd) {
      objectsToDelete.add(interactionFragment);
    } else if (interactionFragment instanceof InteractionState){
    	objectsToDelete.add(interactionFragment);
    	// new interaction states and stateFragment
    }

    for (EObject eObject : objectsToDelete) {
      if (eObject instanceof AbstractEnd) {
        AbstractEnd ae = (AbstractEnd) eObject;
        if (ae.getEvent() != null)
          eventsToDelete.add(ae.getEvent());
      }
    }

    objectsToDelete.addAll(eventsToDelete);
    return objectsToDelete;
  }

  /**
   * @param sourceObject
   * @return
   */
  private static Collection<?> getExecutionFromScenarioElement(Collection<?> elementsToDelete) {
    Collection<EObject> result = new ArrayList<EObject>(elementsToDelete.size());
    for (Object sourceObject : elementsToDelete) {
      TimeLapse exec = null;
      Scenario s = (Scenario) ((EObject) sourceObject).eContainer();

      if (sourceObject instanceof AbstractEnd) {
        for (TimeLapse exec2 : s.getOwnedTimeLapses()) {
          if (exec2.getStart() == sourceObject)
            exec = exec2;
          if (exec2.getFinish() == sourceObject)
            exec = exec2;
        }
      }
      
      if (sourceObject instanceof ConstraintDuration) {
    	  result.add((EObject) sourceObject);
      }
    	  

      if (sourceObject instanceof SequenceMessage) {
        SequenceMessage sm = (SequenceMessage) sourceObject;
        // looking for an execution from a sequence message.
        List<AbstractEnd> messageEnds = new ArrayList<AbstractEnd>(2);
        messageEnds.add(sm.getSendingEnd());
        messageEnds.add(sm.getReceivingEnd());
        for (TimeLapse exec2 : s.getOwnedTimeLapses()) {
          if (messageEnds.contains(exec2.getStart()))
            exec = exec2;
          if (messageEnds.contains(exec2.getFinish()))
            exec = exec2;
        }
        // create and delete message are not connected to execution.
        // so we must manage them manually
        if (sm.getKind() == MessageKind.CREATE || sm.getKind() == MessageKind.DELETE || sm.getSendingEnd() == null || sm.getReceivingEnd() == null) {
          result.add(sm);
        }
      }

      if (sourceObject instanceof TimeLapse) {
        exec = (TimeLapse) sourceObject;
        result.add(exec);
      }
      if (sourceObject instanceof InstanceRole) {
        InstanceRole ir = (InstanceRole) sourceObject;
        if (!result.contains(ir))
          result.add(ir);
        for (TimeLapse exec2 : s.getOwnedTimeLapses()) {
          if ((exec2 instanceof Execution) && (((Execution) exec2).getCovered() == ir))
            result.add(exec2);
          if ((exec2 instanceof StateFragment) && (((StateFragment) exec2).getStart().getCoveredInstanceRoles().contains(ir)))
              result.add(exec2);
        }

        // there is also the case of messages starting from this instanceRole
        for (AbstractEnd ae : ir.getAbstractEnds()) {
          if (ae instanceof MessageEnd) {
            MessageEnd me = (MessageEnd) ae;
            MessageEnd end = me.getMessage().getReceivingEnd();
            for (TimeLapse exec2 : s.getOwnedTimeLapses()) {
              if (exec2.getStart() == end) {
                if (!result.contains(exec2))
                  result.add(exec2);
              }
            }
          }
        }
      }
    }
    return result;
  }

  private static void globalizeElementsForScenario(Collection<Object> elementsToDelete) {
    Collection<Object> eltsToDelete = elementsToDelete;
    Collection<Object> sequenceElements = new ArrayList<Object>();
    Collection<Object> nonSequenceElements = new ArrayList<Object>();
    for (Object object : eltsToDelete) {
      if (isSequenceDiagramObject(object))
    	  sequenceElements.add(object);
      else 
    	  nonSequenceElements.add(object);
    }

    if (sequenceElements.size() != 0) {
      // propagate elementsToDelete to every elements impacted
      Collection<?> executions = getExecutionFromScenarioElement(sequenceElements);
      eltsToDelete = new ArrayList<Object>();
      for (Object object : executions) {
        if (object instanceof TimeLapse) {
          TimeLapse exec = (TimeLapse) object;
          for (Object object2 : propagageSequenceDeletion(exec)) {
            eltsToDelete.add(object2);
          }
        }
        if (object instanceof ConstraintDuration)
        	eltsToDelete.add(object);
        if (object instanceof InstanceRole)
          eltsToDelete.add(object);
        if (object instanceof SequenceMessage) {
          SequenceMessage sm = (SequenceMessage) object;
          if (sm.getSendingEnd() != null) {
            eltsToDelete.add(sm.getSendingEnd());
            eltsToDelete.add(sm.getSendingEnd().getEvent());
          }

          if (sm.getReceivingEnd() != null) {
            eltsToDelete.add(sm.getReceivingEnd());
            eltsToDelete.add(sm.getReceivingEnd().getEvent());
          }
          eltsToDelete.add(sm);
          if (sm.getKind() == MessageKind.CREATE || sm.getKind() == MessageKind.DELETE) {
            eltsToDelete.add(sm.getSendingEnd().getEvent());
            eltsToDelete.add(sm.getReceivingEnd().getEvent());
          }
        }
        if (object instanceof CombinedFragment) {
          CombinedFragment cf = (CombinedFragment) object;
          eltsToDelete.addAll(cf.getReferencedOperands());
        }
      }
    }
    
    // adding ConstraintDuration pointing to a deleted element
    List<ConstraintDuration> durationsToDelete = new ArrayList<ConstraintDuration>();
    for (Object object : eltsToDelete) {
		if (object instanceof InteractionFragment) {
			InteractionFragment if_ = (InteractionFragment) object;
			if (if_.eContainer() instanceof Scenario) {
				Scenario scenario = (Scenario) if_.eContainer();
				for (ConstraintDuration duration : scenario.getOwnedConstraintDurations()) {
					if (duration.getStart().equals(if_) || duration.getFinish().equals(if_)) 
						durationsToDelete.add (duration);
				}
			}			
		}
	}
    eltsToDelete.addAll(durationsToDelete);

    // cleaning up the content by removing duplicatas
    Collection<Object> result = new ArrayList<Object>(eltsToDelete.size());

    // Reassign resulting collection.
    elementsToDelete.clear();
    elementsToDelete.addAll(nonSequenceElements);
    for (Object object : eltsToDelete) {
        if (!elementsToDelete.contains(object) && object != null) {
        	elementsToDelete.add(object);
        }
	}
    elementsToDelete.addAll(result);
    
  }

  /**
   * @param elementsToDelete
   * @return
   */
  private static boolean isSequenceDiagramObject(Object elementsToDelete) {
    if (elementsToDelete instanceof InteractionState)
      return false; // no propagation in this case.
    if (elementsToDelete instanceof InteractionOperand)
      return false; // TODO
    if (elementsToDelete instanceof InteractionFragment)
      return true;
    if (elementsToDelete instanceof SequenceMessage)
      return true;
    if (elementsToDelete instanceof TimeLapse)
      return true;
    if (elementsToDelete instanceof InstanceRole)
      return true;
    if (elementsToDelete instanceof ConstraintDuration)
        return true;

    return false;
  }

  private static List<?> propagageSequenceDeletion(TimeLapse exec) {
    InteractionFragment start = exec.getStart();
    InteractionFragment finish = exec.getFinish();

    List<EObject> objectsToDelete = new ArrayList<EObject>();

    objectsToDelete.add(exec);
    objectsToDelete.addAll(getAllObjectsFromAbstractEnd(start));
    objectsToDelete.addAll(getAllObjectsFromAbstractEnd(finish));
    return objectsToDelete;
  }
}
