/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.delete;

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
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
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
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;
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
import org.polarsys.capella.core.model.helpers.SequenceLinkEndExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;

/**
 * 
 */
public class DeleteHelper implements IDeleteHelper {

  /**
   * Add elements to delete for {@link Association}.
   * 
   * @param elementsToDelete
   */
  protected void addElementsForAssociation(Set<? super EObject> elementsToDelete) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
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
   * 
   * @param elementsToDelete
   */
  protected void addElementsForFunctionalChainInvolvement(Set<? super EObject> elementsToDelete) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof FunctionalChainInvolvement) {
        FunctionalChainInvolvement fcInvolvement = (FunctionalChainInvolvement) elementToDelete;
        InvolvedElement involved = fcInvolvement.getInvolved();
        if (null != involved) {
          if ((elementToDelete instanceof FunctionalChainInvolvementFunction && involved instanceof AbstractFunction)
              || elementToDelete instanceof FunctionalChainReference) {
            // Add next involvements.
            elementToAdd.addAll(fcInvolvement.getNextFunctionalChainInvolvements());
            // Add previous involvements.
            elementToAdd.addAll(fcInvolvement.getPreviousFunctionalChainInvolvements());
          }
        }
      }
    }
    if (!elementToAdd.isEmpty()) {
      elementsToDelete.addAll(elementToAdd);
    }
  }

  /**
   * Add elements to delete for {@link PhysicalPathInvolvement}.
   * 
   * @param elementsToDelete
   */
  protected void addElementsForPhysicalPathInvolvement(Set<? super EObject> elementsToDelete) {
    List<EObject> elementToAdd = new ArrayList<EObject>(0);
    for (Object elementToDelete : elementsToDelete) {
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
    elementsToDelete.addAll(elementToAdd);
  }

  /**
   * Add elements to delete for {@link SequenceLinkEnd}.
   * 
   * @param elementsToDelete
   */
  protected void addElementsForSequenceLinkEnd(Set<? super EObject> elementsToDelete) {
    List<EObject> elementToAdd = new ArrayList<>();
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof SequenceLinkEnd) {
        SequenceLinkEnd sequenceLinkEnd = (SequenceLinkEnd) elementToDelete;
        elementToAdd.addAll(SequenceLinkEndExt.getIncomingSequenceLinks(sequenceLinkEnd));
        elementToAdd.addAll(SequenceLinkEndExt.getOutgoingSequenceLinks(sequenceLinkEnd));
      }
    }
    elementsToDelete.addAll(elementToAdd);
  }

  protected void addElementsForAbstractFunction(Set<? super EObject> elementsToDelete) {
    List<EObject> elementsToAdd = new ArrayList<EObject>();
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof AbstractFunction) {
        elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete,
            InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_FUNCTION));
      }
    }
    elementsToDelete.addAll(elementsToAdd);
  }

  protected void addElementsForAbstractState(Set<? super EObject> elementsToDelete) {
    List<EObject> elementsToAdd = new ArrayList<EObject>();
    for (Object elementToDelete : elementsToDelete) {
      if (elementToDelete instanceof AbstractState) {
        elementsToAdd.addAll(EObjectExt.getReferencers((EObject) elementToDelete,
            InteractionPackage.Literals.STATE_FRAGMENT__RELATED_ABSTRACT_STATE));
      }
    }
    elementsToDelete.addAll(elementsToAdd);
  }

  /**
   * @param elementsToDelete
   */
  protected void getAdditionalElementsForParts(Set<? super AbstractType> elementsToDelete) {
    Set<AbstractType> result = new HashSet<AbstractType>(0);
    for (Object elementToDelete : elementsToDelete) {
      if ((elementToDelete instanceof Part) && shouldDeleteTypeOf((Part) elementToDelete)) {
        AbstractType type = ((Part) elementToDelete).getAbstractType();
        if (type != null) {
          result.add(type);
        }
      }
    }
    elementsToDelete.addAll(result);
  }

  protected void addElementsForScenario(Collection<Object> elementsToDelete) {

    Collection<EObject> result = Collections.emptyList();
    result = new ArrayList<EObject>();

    for (Object object : elementsToDelete) {

      if (object instanceof TimeLapse) {
        TimeLapse exec = (TimeLapse) object;
        result.add(exec.getStart());
        result.add(exec.getFinish());

        if (object instanceof CombinedFragment) {
          CombinedFragment cf = (CombinedFragment) object;
          result.addAll(cf.getReferencedOperands());
        }

      } else if (object instanceof InstanceRole) {
        result.addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.EXECUTION__COVERED));

        for (EObject fragment : EObjectExt.getReferencers((EObject) object,
            InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES)) {
          if (((InteractionFragment) fragment).getCoveredInstanceRoles().size() == 1) {
            result.add(fragment);
          }
        }

      } else if (object instanceof SequenceMessage) {
        result.add(((SequenceMessage) object).getSendingEnd());
        result.add(((SequenceMessage) object).getReceivingEnd());
      }

      if (object instanceof InteractionFragment) {
        result.addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.TIME_LAPSE__START));
        result.addAll(EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.TIME_LAPSE__FINISH));

        if (object instanceof AbstractEnd) {
          result.add(((AbstractEnd) object).getEvent());
        }
        if (object instanceof MessageEnd) {

          result.add(((MessageEnd) object).getMessage());
        }

        result.addAll(
            EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.CONSTRAINT_DURATION__START));
        result.addAll(
            EObjectExt.getReferencers((EObject) object, InteractionPackage.Literals.CONSTRAINT_DURATION__FINISH));
      }

    }

    for (EObject object : result) {
      if (object != null) {
        elementsToDelete.add(object);
      }
    }

  }

  /**
   * Should we delete the type of the argument Part if that Part is deleted. {@inheritDoc}
   */
  protected boolean shouldDeleteTypeOf(Part deletedPart) {
    boolean allowMultiplePart = TriStateBoolean.True
        .equals(CapellaProjectHelper.isReusableComponentsDriven(deletedPart));
    return (!allowMultiplePart || IDeletePreferences.INSTANCE.isDeletingPartType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<?> getExpandedSelection(Collection<?> selection) {

    Set<Object> expandedSelection = new HashSet<Object>(selection);
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
    // Special case for Sequence Link Ends.
    addElementsForSequenceLinkEnd(expandedSelection);

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
  protected void addElementsForComponentExchangeAllocationFromPL(Set<? super EObject> elementsToDelete) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete) {
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
      elementsToDelete.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void addElementsForComponentExchangeAllocationFromPP(Set<? super EObject> elementsToDelete) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete) {
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
      elementsToDelete.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * {@inheritDoc}
   */
  protected void addElementsForComponentExchangeFunctionalExchangeAllocation(Set<? super EObject> elementsToDelete) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();

    for (Object elementToDelete : elementsToDelete) {
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
      elementsToDelete.addAll(elementsToAddToDeletion);
    }
  }

  /**
   * @param link
   */
  private Set<EObject> getRelatedElements(PhysicalLink link) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeAllocation allocation : link.getOwnedComponentExchangeAllocations()) {
      ComponentExchange cpntExch = allocation.getComponentExchangeAllocated();
      elementsToAddToDeletion.addAll(PhysicalLinkExt.evaluateImpactsOfUnsynchronizeAllocations(link, cpntExch, true));
    }
    return elementsToAddToDeletion;
  }

  /**
   * @param path
   */
  private Set<EObject> getRelatedElements(PhysicalPath path) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeAllocation allocation : path.getOwnedComponentExchangeAllocations()) {
      ComponentExchange cpntExch = allocation.getComponentExchangeAllocated();
      elementsToAddToDeletion.addAll(PhysicalPathExt.evaluateImpactsOfUnsynchronizeAllocations(path, cpntExch, true));
    }
    return elementsToAddToDeletion;
  }

  /**
   * @param exchange
   */
  private Set<EObject> getRelatedElements(ComponentExchange exchange) {
    Set<EObject> elementsToAddToDeletion = new HashSet<EObject>();
    for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange
        .getOwnedComponentExchangeFunctionalExchangeAllocations()) {
      FunctionalExchange fctExchange = allocation.getAllocatedFunctionalExchange();
      elementsToAddToDeletion
          .addAll(ComponentExchangeExt.evaluateImpactsOfUnsynchronizeAllocations(exchange, fctExchange, true));

      for (PhysicalLink link : exchange.getAllocatorPhysicalLinks()) {
        elementsToAddToDeletion.addAll(PhysicalLinkExt.evaluateImpactsOfUnsynchronizeAllocations(link, exchange, true));
      }
    }
    return elementsToAddToDeletion;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteSemanticStructure(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    if (CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(feature)
        || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES.equals(feature)) {
      // Don't remove objects that reference a PropertyValueGroup or a PropertyValue we are deleting.
      return false;

    } else if (sourceObject instanceof AbstractRelationship) {
      // This is a capella link.
      if (isDeletableLink((AbstractRelationship) sourceObject, linkObject, feature)) {
        // Delete it, along with connected structure.
        return true;
      }

    } else if (isPendingElement(sourceObject, linkObject, feature)) { // Finally, deals with pending elements.
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDeleteElement(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    if (sourceObject instanceof AbstractTrace) { // Then deals with trace.
      if (isDeletableTrace((AbstractTrace) sourceObject, linkObject, feature)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Should this association be deleted ?<br>
   * An association is a pending link if it is attached to one side at most.
   * 
   * @param association
   * @return
   */
  protected boolean isDeletableAssociation(Association association, EObject member) {
    EList<Property> ownedMembers = association.getOwnedMembers();
    // The second part of the test should be optional, since this was called by a property deletion query.
    // But it's easier to understand that the supposedly deleted member leads to a pending association.
    return (2 >= ownedMembers.size()) && ownedMembers.contains(member);
  }

  /**
   * Is specified relation standing for a link that is to be deleted ?
   * 
   * @param linkedObject
   * @param link
   * @param feature
   * @return <code>true</code> if relation is a link that should be deleted, <code>false</code> if it is not a link, or
   *         should be kept untouched.
   */
  protected boolean isDeletableLink(AbstractRelationship link, EObject linkedObject, EStructuralFeature feature) {
    // In most cases, an abstract relationship is a link that is to be destroyed, along with its attached semantic
    // structure.
    boolean result = true;
    boolean testTraceFeature = false;
    if (linkedObject instanceof PropertyValueGroup) {
      // Do not delete referenced values when deleting a property value group.
      result = false;
    } else if (link instanceof FunctionalExchange) {
      if ((linkedObject instanceof AbstractEventOperation) || (linkedObject instanceof ExchangeItem)
          || (linkedObject instanceof FunctionalChainInvolvement) || (linkedObject instanceof ExchangeCategory)
          || (linkedObject instanceof PhysicalLink)) {
        result = false;
      } else {
        // Test for exchange realizations, that are referencing links that should not be destroyed.
        testTraceFeature = (linkedObject instanceof FunctionalExchangeRealization)
            || (linkedObject instanceof ComponentExchangeFunctionalExchangeAllocation);
      }
    } else if ((linkedObject instanceof AbstractFunction) || (linkedObject instanceof StateEvent)) {
      // [DELETE] Deleting referenced trigger or effect (e.g. a function) deletes the related state transition
      if (CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT.equals(feature)) {
        return false;
      } else if (CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS.equals(feature)) {
        return false;
      }

    } else if (link instanceof ComponentExchange) {
      if ((linkedObject instanceof AbstractEventOperation) || (linkedObject instanceof ExchangeItem)
          || (linkedObject instanceof ComponentExchangeCategory)) {
        result = false;
      } else {
        // Test for exchange realizations, that are referencing links that should not be destroyed.
        testTraceFeature = (linkedObject instanceof ComponentExchangeRealization)
            || (linkedObject instanceof ComponentExchangeFunctionalExchangeAllocation)
            || (linkedObject instanceof ComponentExchangeAllocation);
      }
    } else if (link instanceof FunctionalChainInvolvementLink) {
      result = !FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__EXCHANGED_ITEMS.equals(feature);

    } else if (link instanceof PhysicalPathInvolvement) {
      AbstractPathInvolvedElement involved = ((PhysicalPathInvolvement) link).getInvolvedElement();
      result = (involved == null) || (involved instanceof PhysicalLink);

    } else if (linkedObject instanceof TransfoLink) { // Test transformation link.
      // Test involved feature.
      testTraceFeature = true;

    }

    // Test feature against traceable element incoming or outgoing traces.
    if (testTraceFeature) {
      result = !(ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES.equals(feature)
          || ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES.equals(feature));
    }

    if (CapellacommonPackage.Literals.STATE_TRANSITION__GUARD.equals(feature)) {
      result = false;
    }

    return result;
  }

  /**
   * Should specified trace be spared by deletion ?
   * 
   * @param trace
   * @param referencingElement
   * @param referencingFeature
   * @return <code>true</code> if so, <code>false</code> otherwise.
   */
  protected boolean isDeletableTrace(AbstractTrace trace, EObject referencingElement,
      EStructuralFeature referencingFeature) {
    // In most cases, traces are to be removed.
    boolean result = true;
    // Deal transformation link with care.
    // They point to a trace that should not be deleted.
    // Just make sure this as to do with the incoming or outgoing trace stuff too.
    if ((referencingElement instanceof TransfoLink)
        && (ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES.equals(referencingFeature)
            || ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES.equals(referencingFeature))) {
      result = false;
    }
    return result;
  }

  /**
   * Is specified element a pending one, that should be removed too ?
   * 
   * @param sourceObject
   * @param linkedObject
   * @param feature
   * @return <code>true</code> if so, <code>false</code> to ignore element.
   */
  protected boolean isPendingElement(EObject sourceObject, EObject linkedObject, EStructuralFeature feature) {

    boolean result = false;
    if (sourceObject instanceof Part) {
      // Remove type if we are singleton driven or if preference is enabled
      boolean shouldDelete = (!TriStateBoolean.False
          .equals(CapellaProjectHelper.isSingletonComponentsDriven(sourceObject)))
          || IDeletePreferences.INSTANCE.isDeletingPartType();
      result = shouldDelete && (linkedObject instanceof Component)
          && ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE.equals(feature);

    } else if (sourceObject instanceof PropertyValueGroup) {
      // Is property value group pending ?
      result = isPendingPropertyValueGroup((PropertyValueGroup) sourceObject, linkedObject, feature);
    } else if (sourceObject instanceof ComponentExchangeEnd) {
      // TODO : check this is enough.
      // When deleting a ComponentPort we want to also delete ConnectionEnd that reference it + the Connection itself.
      result = true;
    } else if (linkedObject instanceof ExchangeItem) {
      // When deleting an ExchangeItem we want to also delete CommunicationLink that reference it.
      result = sourceObject instanceof CommunicationLink;

    } else if (sourceObject instanceof PhysicalLink) {
      result = !(linkedObject instanceof PhysicalLinkCategory);

    } else if (sourceObject instanceof PhysicalLinkEnd) {
      result = true;

    } else if (linkedObject instanceof FunctionalChainReference
        && (FaPackage.Literals.REFERENCE_HIERARCHY_CONTEXT__TARGET_REFERENCE_HIERARCHY.equals(feature)
            || FaPackage.Literals.REFERENCE_HIERARCHY_CONTEXT__SOURCE_REFERENCE_HIERARCHY.equals(feature))) {
      result = true;
    }

    return result;
  }

  /**
   * Is specified {@link PropertyValueGroup} a pending one ?
   * 
   * @param group
   * @param referencingElement
   * @param feature
   * @return
   */
  protected boolean isPendingPropertyValueGroup(PropertyValueGroup group, EObject referencingElement,
      EStructuralFeature feature) {
    HashSet<EObject> storedValuedElements = new HashSet<EObject>(group.getValuedElements());
    storedValuedElements.addAll(group.getAppliedPropertyValueGroups());
    storedValuedElements.remove(referencingElement);
    return storedValuedElements.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> getAdditionalElements(EObject sourceObject, EObject linkObject,
      EStructuralFeature feature) {
    if (sourceObject instanceof Property) {
      // Do not remove the property directly, instead, remove its parent, if applicable.
      EObject container = sourceObject.eContainer();
      if ((container instanceof Association) && isDeletableAssociation((Association) container, sourceObject)) {
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
  public Collection<Command> getAdditionalCommands(EObject sourceObject, EObject linkObject,
      EStructuralFeature feature) {
    // Nothing here
    return null;
  }

  @Deprecated
  protected Collection<? extends EObject> getAllObjectsFromAbstractEnd(InteractionFragment interactionFragment) {
    return Collections.emptyList();
  }

  @Deprecated
  protected Collection<? extends EObject> getExecutionFromScenarioElement(Collection<?> elementsToDelete) {
    return Collections.emptyList();
  }

  @Deprecated
  protected void globalizeElementsForScenario(Set<? super EObject> elementsToDelete) {
    // Nothing here
  }

  @Deprecated
  protected boolean isSequenceDiagramObject(Object elementsToDelete) {
    return false;
  }

  @Deprecated
  protected List<? extends EObject> propagageSequenceDeletion(TimeLapse exec) {
    return Collections.emptyList();
  }
}
