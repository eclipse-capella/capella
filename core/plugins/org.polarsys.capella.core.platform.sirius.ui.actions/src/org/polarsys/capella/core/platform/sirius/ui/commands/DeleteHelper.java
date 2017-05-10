/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractState;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateEvent;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkCategory;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocator;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.TimeLapse;
import org.polarsys.capella.core.model.handler.command.IDeleteHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.platform.sirius.ui.preferences.IDeletePreferences;

/**
 * This class should not be defined into UI plugin !! (but it can't be defined into model.handler too)
 */
public class DeleteHelper implements IDeleteHelper {

  /**
   * Add elements to delete for {@link Association}.
   * 
   * @param elementsToDelete_p
   */
  protected void addElementsForAssociation(Set<? super EObject> elementsToDelete_p) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof Association) {
        elementToAdd.addAll(((Association) elementToDelete).getNavigableMembers());
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete_p.addAll(elementToAdd);
    }
  }

  /**
   * Add elements to delete for {@link FunctionalChainInvolvement}.
   * 
   * @param elementsToDelete_p
   */
  protected void addElementsForFunctionalChainInvolvement(Set<? super EObject> elementsToDelete_p) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof FunctionalChainInvolvement) {
        FunctionalChainInvolvement fcInvolvement = (FunctionalChainInvolvement) elementToDelete;
        InvolvedElement involved = fcInvolvement.getInvolved();
        if (null != involved) {
          if ((involved instanceof AbstractFunction) || (involved instanceof FunctionalChain)) {
            // Add next involvements.
            elementToAdd.addAll(fcInvolvement.getNextFunctionalChainInvolvements());
            // Add previous involvements.
            elementToAdd.addAll(fcInvolvement.getPreviousFunctionalChainInvolvements());
          }
        }
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete_p.addAll(elementToAdd);
    }
  }

  /**
   * Add elements to delete for {@link PhysicalPathInvolvement}.
   * 
   * @param elementsToDelete_p
   */
  protected void addElementsForPhysicalPathInvolvement(Set<? super EObject> elementsToDelete_p) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof PhysicalPathInvolvement) {
        PhysicalPathInvolvement pathInvolvement = (PhysicalPathInvolvement) elementToDelete;
        AbstractPathInvolvedElement involved = pathInvolvement.getInvolvedElement();
        if (null != involved) {
          if ((involved instanceof Part) || (involved instanceof PhysicalPath)) {
            // Add next involvements.
            elementToAdd.addAll(pathInvolvement.getNextInvolvements());
            // Add previous involvements.
            elementToAdd.addAll(pathInvolvement.getPreviousInvolvements());
          }
        }
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete_p.addAll(elementToAdd);
    }
  }

  protected void addElementsForAbstractFunction(Set<? super EObject> elementsToDelete_p) {
    List<EObject> elementsToAdd = new ArrayList<EObject>();
    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof AbstractFunction) {
        elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete,
            InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION));
      }
    }
    elementsToDelete_p.addAll(elementsToAdd);
  }

  protected void addElementsForAbstractState(Set<? super EObject> elementsToDelete_p) {
    List<EObject> elementsToAdd = new ArrayList<EObject>();
    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof AbstractState) {
        elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete,
            InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE));
      }
    }
    elementsToDelete_p.addAll(elementsToAdd);
  }

  /**
   * Add pending property values in addition to their elements if necessary.
   * 
   * @param elementsToDelete_p
   */
  protected void addPendingPropertyValues(Set<? super EObject> elementsToDelete_p) {
    // This algorithm is specified by Loic Petit.
    Set<EObject> propertyValuesToAddToDeletedElements = new HashSet<EObject>(0);
    // Loop over elements to delete.
    for (Object object : elementsToDelete_p) {
      if (object instanceof CapellaElement) {
        // Get applied property values.
        List<AbstractPropertyValue> appliedPropertyValues = ((CapellaElement) object).getAppliedPropertyValues();
        // Loop over property values to collect only the ones which have only current capella element as
        // involvedElements.
        for (AbstractPropertyValue propertyValue : appliedPropertyValues) {
          // Is it a pending property value ? i.e valueElements must contain only current object and involvedElements
          // must be empty.
          List<CapellaElement> valuedElements = propertyValue.getValuedElements();
          if ((propertyValue != object) && propertyValue.getInvolvedElements().isEmpty()
              && ((valuedElements.size() == 1) && valuedElements.contains(object))) {
            // That's it !
            Set<EObject> propertyValues = new HashSet<EObject>();
            propertyValues.add(propertyValue);
            // Recurse algorithm on this pending property value to remove other pending property values linked to this
            // one.
            addPendingPropertyValues(propertyValues);
            propertyValuesToAddToDeletedElements.addAll(propertyValues);
          }
        }
      }
    }
    if (!propertyValuesToAddToDeletedElements.isEmpty()) {
      elementsToDelete_p.addAll(propertyValuesToAddToDeletedElements);
    }
  }

  /**
   * Add pending property value groups in addition to their elements if necessary.
   * 
   * @param elementsToDelete_p
   */
  protected void addPendingPropertyValueGroups(Set<? super EObject> elementsToDelete_p) {
    // This algorithm is specified by Loic Petit.
    Set<EObject> propertyValueGroupsToAddToDeletedElements = new HashSet<EObject>(0);
    // Loop over elements to delete.
    for (Object object : elementsToDelete_p) {
      if (object instanceof CapellaElement) {
        // Get applied property values.
        List<PropertyValueGroup> appliedPropertyValueGroups = ((CapellaElement) object).getAppliedPropertyValueGroups();
        // Loop over property value groups to collect only the ones which have only current capella element as
        // involvedElements.
        for (PropertyValueGroup propertyValueGroup : appliedPropertyValueGroups) {
          // StackOverflow deleting property value applied on itself
          if (!elementsToDelete_p.contains(propertyValueGroup)) {
            // Is it a pending property value group ? i.e valueElements must contain only current object and
            // involvedElements must be empty.
            List<CapellaElement> valuedElements = propertyValueGroup.getValuedElements();
            if ((valuedElements.size() == 1) && valuedElements.contains(object)) {
              // That's it !
              Set<EObject> propertyValueGroups = new HashSet<EObject>();
              propertyValueGroups.add(propertyValueGroup);
              // Recurse algorithm on this pending property value to remove other pending property values linked to this
              // one.
              addPendingPropertyValueGroups(propertyValueGroups);
              propertyValueGroupsToAddToDeletedElements.addAll(propertyValueGroups);
            }
          }
        }
      }
    }
    if (!propertyValueGroupsToAddToDeletedElements.isEmpty()) {
      elementsToDelete_p.addAll(propertyValueGroupsToAddToDeletedElements);
    }
  }

  /**
   * @param elementsToDelete_p
   */
  protected void getAdditionalElementsForParts(Set<? super AbstractType> elementsToDelete_p) {
    Set<AbstractType> result = new HashSet<AbstractType>(0);
    for (Object elementToDelete : elementsToDelete_p) {
      if ((elementToDelete instanceof Part) && shouldDeleteTypeOf((Part) elementToDelete)) {
        AbstractType type = ((Part) elementToDelete).getAbstractType();
        if (type != null) {
          result.add(type);
        }
      }
    }
    elementsToDelete_p.addAll(result);
  }

  protected void addElementsForScenario(Collection<Object> elementsToDelete_p) {

    Collection<EObject> elementsToDelete = Collections.emptyList();
    elementsToDelete = new ArrayList<EObject>();

    for (Object object : elementsToDelete_p) {

      if (object instanceof TimeLapse) {
        TimeLapse exec = (TimeLapse) object;
        elementsToDelete.add(exec.getStart());
        elementsToDelete.add(exec.getFinish());

        if (object instanceof CombinedFragment) {
          CombinedFragment cf = (CombinedFragment) object;
          elementsToDelete.addAll(cf.getReferencedOperands());
        }

      } else if (object instanceof InstanceRole) {
        elementsToDelete
            .addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.EXECUTION__COVERED));

        for (EObject fragment : EObjectExt.getReferencers((EObject) object,
            InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES)) {
          if (((InteractionFragment) fragment).getCoveredInstanceRoles().size() == 1) {
            elementsToDelete.add(fragment);
          }
        }

      } else if (object instanceof SequenceMessage) {
        elementsToDelete.add(((SequenceMessage) object).getSendingEnd());
        elementsToDelete.add(((SequenceMessage) object).getReceivingEnd());
      }

      if (object instanceof InteractionFragment) {
        elementsToDelete
            .addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.TIME_LAPSE__START));
        elementsToDelete
            .addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.TIME_LAPSE__FINISH));

        if (object instanceof AbstractEnd) {
          elementsToDelete.add(((AbstractEnd) object).getEvent());
        }
        if (object instanceof MessageEnd) {

          elementsToDelete.add(((MessageEnd) object).getMessage());
        }

        elementsToDelete.addAll(
            EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.CONSTRAINT_DURATION__START));
        elementsToDelete.addAll(
            EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.CONSTRAINT_DURATION__FINISH));
      }

    }

    for (EObject object : elementsToDelete) {
      if (object != null) {
        elementsToDelete_p.add(object);
      }
    }

  }

  /**
   * Should we delete the type of the argument Part if that Part is deleted. {@inheritDoc}
   */
  protected boolean shouldDeleteTypeOf(Part deletedPart_p) {
    boolean allowMultiplePart = TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(deletedPart_p));
    return (!allowMultiplePart || IDeletePreferences.INSTANCE.isDeletingPartType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<?> getExpandedSelection(Collection<?> selection_p) {

    Set<Object> expandedSelection = new HashSet<Object>(selection_p);
    getAdditionalElementsForParts(expandedSelection);

    // Special case for Physical Path Involvements (we add previous and next involvements if the involved element is a
    // Part).
    addElementsForPhysicalPathInvolvement(expandedSelection);

    // special case for state and modes and abstractFunction: sequence diagram deletion
    addElementsForAbstractState(expandedSelection);
    addElementsForAbstractFunction(expandedSelection);

    // Special case for functional chain involvements (we add previous and next involvements if the involved element is
    // an AbstractFunction).
    addElementsForFunctionalChainInvolvement(expandedSelection);
    // Special case for association, we add navigable members i.e properties that are not owned by the association but
    // targeted elements.
    addElementsForAssociation(expandedSelection);
    // Get all elements for a scenario.
    addElementsForScenario(expandedSelection);
    // Special case for property values.
    addPendingPropertyValues(expandedSelection);
    // Special case for property value groups.
    addPendingPropertyValueGroups(expandedSelection);

    if (CapellaModelPreferencesPlugin.getDefault().isSynchronizationOfComponentPortToFunctionPortAllowed()) {
      addElementsForComponentExchangeFunctionalExchangeAllocation(expandedSelection);
    }
    if (CapellaModelPreferencesPlugin.getDefault()
        .isSynchronizationOfPhysicalPortToComponentPortOnPhysicalLinkAllowed()) {
      addElementsForComponentExchangeAllocationFromPL(expandedSelection);
    }
    if (CapellaModelPreferencesPlugin.getDefault()
        .isSynchronizationOfPhysicalPortToComponentPortOnPhysicalPathAllowed()) {
      addElementsForComponentExchangeAllocationFromPP(expandedSelection);
    }

    return expandedSelection;
  }

  /**
   * {@inheritDoc}
   */
  protected void addElementsForComponentExchangeAllocationFromPL(Set<? super EObject> elementsToDelete_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof ComponentExchangeAllocation) {
        ComponentExchange exchange = ((ComponentExchangeAllocation) elementToDelete).getComponentExchangeAllocated();
        ComponentExchangeAllocator allocator = ((ComponentExchangeAllocation) elementToDelete)
            .getComponentExchangeAllocator();
        if (allocator instanceof PhysicalLink) {
          elementsToAddToDeletion.addAll(
              PhysicalLinkExt.evaluateImpactsOfUnsynchronizeAllocations((PhysicalLink) allocator, exchange, true));
        }
      } else if (elementToDelete instanceof PhysicalLink) {
        elementsToAddToDeletion.addAll(getRelatedElements((PhysicalLink) elementToDelete));
      } else if (elementToDelete instanceof PhysicalPort) {
        for (PhysicalLink link : ((PhysicalPort) elementToDelete).getInvolvedLinks()) {
          elementsToAddToDeletion.addAll(getRelatedElements(link));
        }
      }
    }

    if (!elementsToAddToDeletion.isEmpty()) {
      elementsToDelete_p.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void addElementsForComponentExchangeAllocationFromPP(Set<? super EObject> elementsToDelete_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof ComponentExchangeAllocation) {
        ComponentExchange exchange = ((ComponentExchangeAllocation) elementToDelete).getComponentExchangeAllocated();
        ComponentExchangeAllocator allocator = ((ComponentExchangeAllocation) elementToDelete)
            .getComponentExchangeAllocator();
        if (allocator instanceof PhysicalPath) {
          elementsToAddToDeletion.addAll(
              PhysicalPathExt.evaluateImpactsOfUnsynchronizeAllocations((PhysicalPath) allocator, exchange, true));
        }
      } else if (elementToDelete instanceof PhysicalPath) {
        elementsToAddToDeletion.addAll(getRelatedElements((PhysicalPath) elementToDelete));
      }
    }

    if (!elementsToAddToDeletion.isEmpty()) {
      elementsToDelete_p.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void addElementsForComponentExchangeFunctionalExchangeAllocation(Set<? super EObject> elementsToDelete_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete_p) {
      if (elementToDelete instanceof ComponentExchangeFunctionalExchangeAllocation) {
        ComponentExchange cptExchange = ((ComponentExchangeFunctionalExchangeAllocation) elementToDelete)
            .getAllocatingComponentExchange();
        FunctionalExchange fctExchange = ((ComponentExchangeFunctionalExchangeAllocation) elementToDelete)
            .getAllocatedFunctionalExchange();
        elementsToAddToDeletion
            .addAll(ComponentExchangeExt.evaluateImpactsOfUnsynchronizeAllocations(cptExchange, fctExchange, true));
      } else if (elementToDelete instanceof ComponentExchange) {
        elementsToAddToDeletion.addAll(getRelatedElements((ComponentExchange) elementToDelete));
      } else if (elementToDelete instanceof ComponentPort) {
        for (ComponentExchange exchange : ((ComponentPort) elementToDelete).getComponentExchanges()) {
          elementsToAddToDeletion.addAll(getRelatedElements(exchange));
        }
      }
    }

    if (!elementsToAddToDeletion.isEmpty()) {
      elementsToDelete_p.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * @param link_p
   */
  private Set<EObject> getRelatedElements(PhysicalLink link_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeAllocation allocation : link_p.getOwnedComponentExchangeAllocations()) {
      ComponentExchange cpntExch = allocation.getComponentExchangeAllocated();
      elementsToAddToDeletion.addAll(PhysicalLinkExt.evaluateImpactsOfUnsynchronizeAllocations(link_p, cpntExch, true));
    }
    return elementsToAddToDeletion;
  }

  /**
   * @param path_p
   */
  private Set<EObject> getRelatedElements(PhysicalPath path_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeAllocation allocation : path_p.getOwnedComponentExchangeAllocations()) {
      ComponentExchange cpntExch = allocation.getComponentExchangeAllocated();
      elementsToAddToDeletion.addAll(PhysicalPathExt.evaluateImpactsOfUnsynchronizeAllocations(path_p, cpntExch, true));
    }
    return elementsToAddToDeletion;
  }

  /**
   * @param exchange_p
   */
  private Set<EObject> getRelatedElements(ComponentExchange exchange_p) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange_p
        .getOwnedComponentExchangeFunctionalExchangeAllocations()) {
      FunctionalExchange fctExchange = allocation.getAllocatedFunctionalExchange();
      elementsToAddToDeletion
          .addAll(ComponentExchangeExt.evaluateImpactsOfUnsynchronizeAllocations(exchange_p, fctExchange, true));

      for (PhysicalLink link : exchange_p.getAllocatorPhysicalLinks()) {
        elementsToAddToDeletion
            .addAll(PhysicalLinkExt.evaluateImpactsOfUnsynchronizeAllocations(link, exchange_p, true));
      }
    }
    return elementsToAddToDeletion;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteSemanticStructure(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    if (CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(feature_p)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES.equals(feature_p)) {
      // Don't remove objects that reference a PropertyValueGroup or a PropertyValue we are deleting.
      return false;

    } else if (sourceObject_p instanceof AbstractRelationship) {
      // This is a capella link.
      if (isDeletableLink((AbstractRelationship) sourceObject_p, linkObject_p, feature_p)) {
        // Delete it, along with connected structure.
        return true;
      }

    } else if (isPendingElement(sourceObject_p, linkObject_p, feature_p)) { // Finally, deals with pending elements.
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteElement(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    if (sourceObject_p instanceof AbstractTrace) { // Then deals with trace.
      if (isDeletableTrace((AbstractTrace) sourceObject_p, linkObject_p, feature_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Should this association be deleted ?<br>
   * An association is a pending link if it is attached to one side at most.
   * 
   * @param association_p
   * @return
   */
  protected boolean isDeletableAssociation(Association association_p, EObject member_p) {
    EList<Property> ownedMembers = association_p.getOwnedMembers();
    // The second part of the test should be optional, since this was called by a property deletion query.
    // But it's easier to understand that the supposedly deleted member leads to a pending association.
    return (2 >= ownedMembers.size()) && ownedMembers.contains(member_p);
  }

  /**
   * Is specified relation standing for a link that is to be deleted ?
   * 
   * @param linkedObject_p
   * @param link_p
   * @param feature_p
   * @return <code>true</code> if relation is a link that should be deleted, <code>false</code> if it is not a link, or
   *         should be kept untouched.
   */
  protected boolean isDeletableLink(AbstractRelationship link_p, EObject linkedObject_p, EStructuralFeature feature_p) {
    // In most cases, an abstract relationship is a link that is to be destroyed, along with its attached semantic
    // structure.
    boolean result = true;
    boolean testTraceFeature = false;
    if (linkedObject_p instanceof PropertyValueGroup) {
      // Do not delete referenced values when deleting a property value group.
      result = false;
    } else if (link_p instanceof FunctionalExchange) {
      if ((linkedObject_p instanceof AbstractEventOperation) || (linkedObject_p instanceof ExchangeItem)
          || (linkedObject_p instanceof FunctionalChainInvolvement) || (linkedObject_p instanceof ExchangeCategory)
          || (linkedObject_p instanceof PhysicalLink)) {
        result = false;
      } else {
        // Test for exchange realizations, that are referencing links that should not be destroyed.
        testTraceFeature = (linkedObject_p instanceof FunctionalExchangeRealization)
            || (linkedObject_p instanceof ComponentExchangeFunctionalExchangeAllocation);
      }
    } else if ((linkedObject_p instanceof AbstractFunction) || (linkedObject_p instanceof StateEvent)) {
      // [DELETE] Deleting referenced trigger or effect (e.g. a function) deletes the related state transition
      if (CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT.equals(feature_p)) {
        return false;
      } else if (CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS.equals(feature_p)) {
        return false;
      }

    } else if (link_p instanceof ComponentExchange) {
      if ((linkedObject_p instanceof AbstractEventOperation) || (linkedObject_p instanceof ExchangeItem)
          || (linkedObject_p instanceof ComponentExchangeCategory)) {
        result = false;
      } else {
        // Test for exchange realizations, that are referencing links that should not be destroyed.
        testTraceFeature = (linkedObject_p instanceof ComponentExchangeRealization)
            || (linkedObject_p instanceof ComponentExchangeFunctionalExchangeAllocation)
            || (linkedObject_p instanceof ComponentExchangeAllocation);
      }
    } else if (link_p instanceof FunctionalChainInvolvement) {
      if (FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__EXCHANGED_ITEMS.equals(feature_p)) {
        return false;
      }
      InvolvedElement involved = ((Involvement) link_p).getInvolved();
      result = (involved == null) || (involved instanceof FunctionalExchange);

    } else if (link_p instanceof PhysicalPathInvolvement) {
      AbstractPathInvolvedElement involved = ((PhysicalPathInvolvement) link_p).getInvolvedElement();
      result = (involved == null) || (involved instanceof PhysicalLink);

    } else if (linkedObject_p instanceof TransfoLink) { // Test transformation link.
      // Test involved feature.
      testTraceFeature = true;

    }

    // Test feature against traceable element incoming or outgoing traces.
    if (testTraceFeature) {
      result = !(ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES.equals(feature_p)
          || ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES.equals(feature_p));
    }
    return result;
  }

  /**
   * Should specified trace be spared by deletion ?
   * 
   * @param trace_p
   * @param referencingElement_p
   * @param referencingFeature_p
   * @return <code>true</code> if so, <code>false</code> otherwise.
   */
  protected boolean isDeletableTrace(AbstractTrace trace_p, EObject referencingElement_p,
      EStructuralFeature referencingFeature_p) {
    // In most cases, traces are to be removed.
    boolean result = true;
    // Deal transformation link with care.
    // They point to a trace that should not be deleted.
    // Just make sure this as to do with the incoming or outgoing trace stuff too.
    if ((referencingElement_p instanceof TransfoLink)
        && (ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES.equals(referencingFeature_p)
            || ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES.equals(referencingFeature_p))) {
      result = false;
    }
    return result;
  }

  /**
   * Is specified element a pending one, that should be removed too ?
   * 
   * @param sourceObject_p
   * @param linkedObject_p
   * @param feature_p
   * @return <code>true</code> if so, <code>false</code> to ignore element.
   */
  protected boolean isPendingElement(EObject sourceObject_p, EObject linkedObject_p, EStructuralFeature feature_p) {

    boolean result = false;
    if (sourceObject_p instanceof Part) {
      // Remove type if we are singleton driven or if preference is enabled
      boolean shouldDelete = (!TriStateBoolean.False
          .equals(CapellaProjectHelper.isSingletonComponentsDriven(sourceObject_p)))
          || IDeletePreferences.INSTANCE.isDeletingPartType();
      result = shouldDelete && (linkedObject_p instanceof Component)
          && ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE.equals(feature_p);

    } else if (sourceObject_p instanceof PropertyValueGroup) {
      // Is property value group pending ?
      result = isPendingPropertyValueGroup((PropertyValueGroup) sourceObject_p, linkedObject_p, feature_p);
    } else if (sourceObject_p instanceof ComponentExchangeEnd) {
      // TODO : check this is enough.
      // When deleting a ComponentPort we want to also delete ConnectionEnd that reference it + the Connection itself.
      result = true;
    } else if (linkedObject_p instanceof ExchangeItem) {
      // When deleting an ExchangeItem we want to also delete CommunicationLink that reference it.
      result = sourceObject_p instanceof CommunicationLink;

    } else if (sourceObject_p instanceof PhysicalLink) {
      result = !(linkedObject_p instanceof PhysicalLinkCategory);

    } else if (sourceObject_p instanceof PhysicalLinkEnd) {
      result = true;
    }

    return result;
  }

  /**
   * Is specified {@link PropertyValueGroup} a pending one ?
   * 
   * @param group_p
   * @param referencingElement_p
   * @param feature_p
   * @return
   */
  protected boolean isPendingPropertyValueGroup(PropertyValueGroup group_p, EObject referencingElement_p,
      EStructuralFeature feature_p) {
    HashSet<EObject> storedValuedElements = new HashSet<EObject>(group_p.getValuedElements());
    storedValuedElements.addAll(group_p.getAppliedPropertyValueGroups());
    storedValuedElements.remove(referencingElement_p);
    return storedValuedElements.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> getAdditionalElements(EObject sourceObject_p, EObject linkObject_p,
      EStructuralFeature feature_p) {
    if (sourceObject_p instanceof Property) {
      // Do not remove the property directly, instead, remove its parent, if applicable.
      EObject container = sourceObject_p.eContainer();
      if ((container instanceof Association) && isDeletableAssociation((Association) container, sourceObject_p)) {
        // The association is no longer needed, remove it.
        // This will also destroy contained properties (including current one).
        return Collections.singleton(container);
      }
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<Command> getAdditionalCommands(EObject sourceObject_p, EObject linkObject_p,
      EStructuralFeature feature_p) {
    // Nothing here
    return null;
  }

  @Deprecated
  protected Collection<? extends EObject> getAllObjectsFromAbstractEnd(InteractionFragment interactionFragment_p) {
    return Collections.emptyList();
  }

  @Deprecated
  protected Collection<? extends EObject> getExecutionFromScenarioElement(Collection<?> elementsToDelete_p) {
    return Collections.emptyList();
  }

  @Deprecated
  protected void globalizeElementsForScenario(Set<? super EObject> elementsToDelete_p) {
    // Nothing here
  }

  @Deprecated
  protected boolean isSequenceDiagramObject(Object elementsToDelete_p) {
    return false;
  }

  @Deprecated
  protected List<? extends EObject> propagageSequenceDeletion(TimeLapse exec_p) {
    return Collections.emptyList();
  }
}
