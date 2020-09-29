/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.helpers.cache.Cache;
import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.sirius.analysis.EventContextServices.EventContext;

import com.google.common.collect.Lists;

public class InteractionRefreshExtension extends AbstractCacheAwareRefreshExtension {

  /**
   * Generic cache (basically for getCovered).
   */
  private static Cache interactionCache = new Cache();

  /**
   * Cache for ChildExecution -> ParentExecution and InteractionState -> ParentExecution.
   */
  private static Map<EObject, Collection<EObject>> stateElementToContainerCache = new ConcurrentHashMap<>();

  /**
   * Cache for StateFragment -> ParentExecution and StateFragment -> ParentInstanceRole.
   */
  private static Map<EObject, Collection<EObject>> executionElementToContainerCache = new ConcurrentHashMap<>();

  /**
   * Cache for CommunicationItem -> sending End.
   */
  private static Map<CommunicationItem, AbstractEnd> communicationItemToSendingEndCache = new ConcurrentHashMap<CommunicationItem, AbstractEnd>();

  /**
   * Cache for CommunicationItem -> receiving End.
   */
  private static Map<CommunicationItem, AbstractEnd> communicationItemToReceivingEndCache = new ConcurrentHashMap<CommunicationItem, AbstractEnd>();

  /**
   * Cache for InstanceRole -> EventContext structure.
   */
  private static Map<InstanceRole, List<EventContext>> instanceRoleToEventContextCache = new ConcurrentHashMap<InstanceRole, List<EventContext>>();

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#beforeRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void beforeRefresh(DDiagram diagram) {
    super.beforeRefresh(diagram);
  }

  /**
   * @see org.eclipse.sirius.business.api.refresh.IRefreshExtension#postRefresh(org.eclipse.sirius.DDiagram)
   */
  @Override
  public void postRefresh(DDiagram diagram) {
    interactionCache.clearCache();
    executionElementToContainerCache.clear();
    stateElementToContainerCache.clear();
    communicationItemToSendingEndCache.clear();
    communicationItemToReceivingEndCache.clear();
    instanceRoleToEventContextCache.clear();
    super.postRefresh(diagram);
  }

  /**
   * 
   * @param function
   * @param parameter
   * @return If enabled, return the cached result if any or apply the function to the given parameter and cache the
   *         result before returning it. //
   */
  public static <P, R> R getInteractionCache(Function<P, R> function, P parameter) {
    return interactionCache.get(function, parameter);
  }

  public static Collection<EObject> getExecutionElementToContainerCache(EObject capellaElement) {
    return executionElementToContainerCache.get(capellaElement);
  }

  public static Optional<EObject> getExecutionElementToSingleContainerCache(EObject capellaElement) {
    Collection<EObject> result = executionElementToContainerCache.get(capellaElement);
    if (result != null && !result.isEmpty()) {
      return Optional.of(result.iterator().next());
    }
    return Optional.empty();
  }

  public static Map<EObject, Collection<EObject>> getExecutionElementToContainerCache() {
    return executionElementToContainerCache;
  }

  public static void putExecutionElementToContainerCache(EObject element, Collection<EObject> container) {
    executionElementToContainerCache.put(element, container);
  }

  public static void putExecutionElementToContainerCache(EObject element, EObject container) {
    executionElementToContainerCache.put(element, Lists.newArrayList(container));
  }

  public static List<EventContext> getInstanceRoleToEventContextCache(InstanceRole instanceRole) {
    return instanceRoleToEventContextCache.get(instanceRole);
  }

  public static void putInstanceRoleToEventContextCache(InstanceRole instanceRole, List<EventContext> structure) {
    instanceRoleToEventContextCache.put(instanceRole, structure);
  }

  public static Collection<EObject> getStateElementToContainerCache(EObject capellaElement) {
    return stateElementToContainerCache.get(capellaElement);
  }

  public static void putStateElementToContainerCache(EObject element, Collection<EObject> container) {
    stateElementToContainerCache.put(element, container);
  }

  public static void putStateElementToContainerCache(EObject element, EObject container) {
    stateElementToContainerCache.put(element, Lists.newArrayList(container));
  }

  public static Optional<EObject> getStateElementToSingleContainerCache(EObject capellaElement) {
    Collection<EObject> result = stateElementToContainerCache.get(capellaElement);
    if (result != null && !result.isEmpty()) {
      return Optional.of(result.iterator().next());
    }
    return Optional.empty();
  }

  public static Map<EObject, Collection<EObject>> getStateElementToContainerCache() {
    return stateElementToContainerCache;
  }
}
