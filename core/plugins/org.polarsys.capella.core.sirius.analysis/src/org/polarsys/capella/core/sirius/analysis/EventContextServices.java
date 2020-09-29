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
package org.polarsys.capella.core.sirius.analysis;

import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getExecutionElementToContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getExecutionElementToSingleContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getInstanceRoleToEventContextCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getInteractionCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getStateElementToContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.getStateElementToSingleContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.putExecutionElementToContainerCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.putInstanceRoleToEventContextCache;
import static org.polarsys.capella.core.sirius.analysis.refresh.extension.InteractionRefreshExtension.putStateElementToContainerCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.AbstractFragment;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;

public class EventContextServices {

  public static Collection<EObject> getDirectEvents(EObject element, InstanceRole instanceRole) {
    Collection<EObject> directEvents = getExecutionElementToContainerCache(element);
    if (directEvents == null) {
      // compute EventContext structure from InstanceRole if needed and put it in cache
      List<EventContext> eventContexts = getInstanceRoleToEventContextCache(instanceRole);
      if (eventContexts == null) {
        eventContexts = computeInstanceRoleEventContextStructure(instanceRole);
        putInstanceRoleToEventContextCache(instanceRole, eventContexts);
      }

      // compute result from EventContext structure
      Set<EObject> result = new LinkedHashSet<EObject>();
      List<EventContext> structure = eventContexts;

      // AbstractEnd case
      if (element instanceof AbstractEnd) {
        // get container from cache
        // if not present, compute it from EventContext structure
        Optional<EventContext> endEventContext = structure.stream()
            .filter(eventContext -> element.equals(eventContext.getElement())).findFirst();
        if (endEventContext.isPresent() && endEventContext.get().getParent() instanceof CapellaElement) {
          putExecutionElementToContainerCache(element, endEventContext.get().getParent());
        }
      } else {
        List<EObject> temp = structure.stream()
            .filter(eventContext -> element.equals(eventContext.getParent())
                && eventContext.getElement() instanceof Execution)
            .map(eventContext -> eventContext.getElement()).filter(event -> event != element).distinct()
            .collect(Collectors.toList());
        result.addAll(temp);
        putExecutionElementToContainerCache(element, temp);
      }
      directEvents = getExecutionElementToContainerCache(element);
    }
    return directEvents;
  }

  public static Collection<EObject> getStateDirectEvents(EObject element, InstanceRole instanceRole) {
    Collection<EObject> directEvents = getStateElementToContainerCache(element);
    if (directEvents == null) {
      // compute EventContext structure from InstanceRole if needed and put it in cache
      List<EventContext> eventContexts = getInstanceRoleToEventContextCache(instanceRole);
      if (eventContexts == null) {
        eventContexts = computeInstanceRoleEventContextStructure(instanceRole);
        putInstanceRoleToEventContextCache(instanceRole, eventContexts);
      }

      // compute result from EventContext structure
      Set<EObject> result = new LinkedHashSet<EObject>();
      List<EventContext> structure = eventContexts;

      List<EObject> temp = structure.stream()
          .filter(eventContext -> element.equals(eventContext.getParent())
              && eventContext.getElement() instanceof StateFragment)
          .map(eventContext -> eventContext.getElement()).filter(event -> event != element).distinct()
          .collect(Collectors.toList());
      result.addAll(temp);
      putStateElementToContainerCache(element, temp);
      directEvents = getStateElementToContainerCache(element);
    }
    return directEvents;
  }

  public static List<EventContext> computeInstanceRoleEventContextStructure(InstanceRole instanceRole) {
    if (instanceRole == null || !(instanceRole.eContainer() instanceof Scenario)) {
      return Collections.emptyList();
    }
    Scenario scenario = SequenceDiagramServices.getScenario(instanceRole);

    // initialize ancestors structure
    Stack<EObject> ancestors = new Stack<EObject>();
    ancestors.push(instanceRole);
    List<EventContext> result = new ArrayList<EventContext>();

    // cache Execution.start -> Execution and Execution.finish -> Execution
    Stream<TimeLapse> timeLapses = scenario.getOwnedTimeLapses().stream()
        .filter(tl -> !(tl instanceof AbstractFragment));
    // scan timelapse only one time
    if (getExecutionElementToContainerCache().isEmpty() && getStateElementToContainerCache().isEmpty()) {
      timeLapses.forEach(e -> {
        if (e.getStart() instanceof MessageEnd) {
          putExecutionElementToContainerCache(e.getStart(), e);
        }
        if (e.getFinish() instanceof MessageEnd) {
          putExecutionElementToContainerCache(e.getFinish(), e);
        }
        if (e.getStart() instanceof InteractionState) {
          putStateElementToContainerCache(e.getStart(), e);
        }
        if (e.getFinish() instanceof InteractionState) {
          putStateElementToContainerCache(e.getFinish(), e);
        }
      });
    }

    // compute execution and end structure
    Stream<InteractionFragment> ends = scenario.getOwnedInteractionFragments().stream()
        .filter(frag -> frag instanceof AbstractEnd || frag instanceof InteractionState);
    ends.forEachOrdered(end -> {
      InstanceRole covered = null;
      if (end instanceof AbstractEnd) {
        covered = getInteractionCache(AbstractEnd::getCovered, (AbstractEnd) end);
      } else if (end instanceof InteractionState) {
        covered = getInteractionCache(InteractionState::getCovered, (InteractionState) end);
      }
      if (covered != null && covered.equals(instanceRole)) {

        // Interaction State
        if (end instanceof InteractionState) {
          Optional<EObject> container = getStateElementToSingleContainerCache(end);
          if (container.isPresent() && container.get() instanceof StateFragment
              && ((StateFragment) container.get()).getStart() == end) {
            result.add(new EventContext(ancestors.peek(), container.get(), true, ancestors.size() + 1));
          }

          if (container.isPresent() && container.get() instanceof StateFragment
              && ((StateFragment) container.get()).getFinish() == end) {
            result.add(new EventContext(ancestors.peek(), container.get(), false, ancestors.size() + 1));
          }
          if (!container.isPresent()) {
            result.add(new EventContext(ancestors.peek(), end, true, ancestors.size() + 1));
          }
        }

        // Execution End Start
        if (isStartingExecutionEnd(end)) {
          ExecutionEnd start = (ExecutionEnd) end;
          Execution exec = start.getExecution();
          result.add(new EventContext(ancestors.peek(), exec, true, ancestors.size() + 1));
          ancestors.push(exec);
        }

        // Message End
        if (end instanceof MessageEnd) {
          Optional<EObject> container = getExecutionElementToSingleContainerCache(end);
          if (container.isPresent() && container.get() instanceof Execution
              && ((Execution) container.get()).getStart() == end) {
            result.add(new EventContext(ancestors.peek(), container.get(), true, ancestors.size() + 1));
            ancestors.push(container.get());
          }

          if (container.isPresent() && container.get() instanceof Execution
              && ((Execution) container.get()).getFinish() == end) {
            ancestors.pop();
            result.add(new EventContext(ancestors.peek(), container.get(), false, ancestors.size() + 1));
          }
          if (!container.isPresent()) {
            result.add(new EventContext(ancestors.peek(), end, true, ancestors.size() + 1));
          }
        }

        // Execution End Finish
        if (isFinishingExecutionEnd(end)) {
          ExecutionEnd finish = (ExecutionEnd) end;
          Execution exec = finish.getExecution();
          ancestors.pop();
          result.add(new EventContext(ancestors.peek(), exec, false, ancestors.size() + 1));
        }

      }
    });
    return result;
  }

  public static boolean isStartingExecutionEnd(InteractionFragment end) {
    if (end instanceof ExecutionEnd) {
      ExecutionEnd ee = (ExecutionEnd) end;
      return ee.getExecution() != null && ee.getExecution().getStart() == end;
    }
    return false;
  }

  public static boolean isFinishingExecutionEnd(InteractionFragment end) {
    if (end instanceof ExecutionEnd) {
      ExecutionEnd ee = (ExecutionEnd) end;
      return ee.getExecution() != null && ee.getExecution().getFinish() == end;
    }
    return false;
  }

  public static boolean isFinishingMessageEnd(AbstractEnd end) {
    if (end instanceof MessageEnd) {
      Optional<EObject> container = getExecutionElementToSingleContainerCache(end);
      return container.isPresent() && container.get() instanceof Execution
          && ((Execution) container.get()).getFinish() == end;
    }
    return false;
  }

  /**
   * Helper class to keep track of who "contains" who depending on the interleaving of the start/finish ends.
   * 
   * @author pcdavid
   */
  public static final class EventContext {
    private final EObject parent;

    private final boolean start;

    private final EObject element;

    private final int level;

    public EventContext(EObject parent, EObject element, boolean start, int level) {
      this.parent = parent;
      this.element = element;
      this.level = level;
      this.start = start;
    }

    public boolean isStart() {
      return start;
    }

    public EObject getParent() {
      return parent;
    }

    public EObject getElement() {
      return element;
    }

    public int getLevel() {
      return level;
    }

    @Override
    public String toString() {
      return String.format("%02d\t%s\t%s", getLevel(), element, parent);
    }
  }

}
