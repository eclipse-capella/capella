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

package org.polarsys.capella.core.data.helpers.interaction.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class ScenarioExt {
  /**
   * @param scenario
   * @return
   */
  public static List<MessageEnd> getOwnedMessagesEnds(Scenario scenario) {
    List<MessageEnd> ownedMsgEnd = new ArrayList<>();

    for (InteractionFragment abs : scenario.getOwnedInteractionFragments()) {
      if (abs instanceof MessageEnd) {
        ownedMsgEnd.add((MessageEnd)abs);
      }
    }
    
    return ownedMsgEnd;
  }

  /**
   * This method retrieves all the receipt events from the given Scenario.
   * 
   * @param currentElement
   * @return List<EventReceiptOperation>
   */
  public static List<EventReceiptOperation> getOwnedEventReceiptOperation(Scenario currentElement) {
    List<EventReceiptOperation> result = new ArrayList<>();

    for (Event evt : currentElement.getOwnedEvents()) {
      if (evt instanceof EventReceiptOperation) {
        result.add((EventReceiptOperation) evt);
      }
    }

    return result;
  }

  /**
   * This method retrieves all the sent events from the given Scenario.
   * 
   * @param currentElement
   * @return List<EventSentOperation>
   */
  public static List<EventSentOperation> getOwnedEventSentOperation(Scenario currentElement) {
    List<EventSentOperation> result = new ArrayList<>();

    for (Event evt : currentElement.getOwnedEvents()) {
      if (evt instanceof EventSentOperation) {
        result.add((EventSentOperation) evt);
      }
    }

    return result;
  }
  
  /**
   * Return the a list of features containing objects with {@link MergeLink}
   * on {@link Scenario}.
   * @return see below.
   */
  public static List<EStructuralFeature> getElementOfInterestOnScenario() {
    List<EStructuralFeature> features = new ArrayList<>();
    
    features.add(InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES);
    features.add(InteractionPackage.Literals.SCENARIO__OWNED_TIME_LAPSES);
    features.add(InteractionPackage.Literals.SCENARIO__OWNED_MESSAGES);
    features.add(InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS);
    features.add(InteractionPackage.Literals.SCENARIO__OWNED_EVENTS);
    
    return features;    
  }

  /**
   * Check and return is a {@link TraceableElement} has at least
   * one outgoing {@link Trace} of a given type.  
   * @param te the target {@link TraceableElement}
   * @param TraceType the type of {@link Trace} to check
   * @return the first {@link Trace} found, <code>null</code> otherwise
   */
  public static TraceableElement hasLinkOftype(TraceableElement te, EClass traceType) {
    
    List<TraceableElement> list = hasLinkOftype(te, traceType,
      ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT
    );
    
    return ( list.isEmpty() ? null: list.get(0) );
  }

  /**
   * Return list of target {@link TraceableElement} linked to/from a given {@link TraceableElement}
   * @param te the target {@link TraceableElement}
   * @param TraceType the type of {@link Trace} to check
   * @return a {@link List} containing the matching {@link Trace}s found, an empty one otherwise
   */
  @SuppressWarnings("unchecked")
  public static List<TraceableElement> hasLinkOftype(TraceableElement te, EClass traceType, EStructuralFeature feature1,
      EStructuralFeature feature2) {

    List<TraceableElement> result = new ArrayList<>();

    for (EObject at : (Collection<EObject>) te.eGet(feature1)) {
      if (at.eClass() == traceType) {
        TraceableElement traceableElement = (TraceableElement) ((AbstractTrace) at).eGet(feature2);
        if (traceableElement != null) {
          result.add(traceableElement);
        }
      }
    }

    return result;
  }
}
