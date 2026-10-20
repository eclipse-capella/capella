/*****************************************************************************
 * Copyright (c) 2026 Obeo.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation.
 *
 *****************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IActivityManager;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/**
 * Services for traceability diagrams.
 *
 * @author nicolas.peransin@obeo.fr
 */
public class TraceabilityServices {

  private static final String GLOBAL_ACTIVITITY_ID = "org.polarsys.capella.activities.traceability";

  /**
   * Returns true if Traceability capability is enabled in Platform Activities.
   * 
   * @param self stub for service
   * @return 
   */
  public static boolean isTraceabilityInPlatformActivitiesEnabled(EObject self) {
    IActivityManager asm = PlatformUI.getWorkbench().getActivitySupport().getActivityManager();
    IActivity traceabilityActivity = asm.getActivity(GLOBAL_ACTIVITITY_ID);
    return traceabilityActivity == null // Activity is defined in org.polarsys.capella.core.platform.sirius.ui.perspective
        || traceabilityActivity.isEnabled();
  }

  /**
   * Returns the traces from traceable elements.
   *
   * @param container
   *     diagram
   * @return list of traces
   */
  public static List<AbstractTrace> getTracedTracesFromTraceables(DDiagram container) {
    return container.getDiagramElements()
        .stream()
        .filter(AbstractDNode.class::isInstance)
        .map(DSemanticDecorator::getTarget)
        .filter(it -> it instanceof PropertyValueGroup
            || it instanceof AbstractPropertyValue
            || it instanceof Constraint)
        .flatMap(it -> ((NamedElement) it).getOutgoingTraces().stream())
        .collect(Collectors.toList());
  }

  /**
   * Collects child elements to display from a container.
   *
   * @param element
   *     selected item
   * @return list of elements
   */
  public static List<? extends NamedElement> getTracedElementContent(NamedElement element) {
    if (element instanceof PropertyValueGroup) {
      return collectAllPvGroups((PropertyValueGroup) element, new ArrayList<>());
    }
    return List.of(element);
  }

  /**
   * Returns for Hierarchical diagram the node of containing block.
   *
   * @param element
   *     to display
   * @param anchor
   *     selected target
   * @return view
   */
  public static AbstractDNode getTracedOwningBlockView(
      NamedElement element, DSemanticDecorator anchor) {
    if (anchor == null) {
      return null;
    }
    if (anchor instanceof DDiagramElement anchorView) {
      return getTracedOwningBlockView(element, (DSemanticDiagram) anchorView.getParentDiagram());
    }
    if (!(anchor instanceof DSemanticDiagram)) {
      return null;
    }
    DSemanticDiagram diagram = (DSemanticDiagram) anchor;

    ModellingArchitecture expectedBlock = CapellaServices
        .getAncestor(element, ModellingArchitecture.class);
    return diagram.getContainers().stream()
        .filter(it -> it.getTarget() == expectedBlock)
        .findFirst()
        .orElse(null);
  }

  private static List<PropertyValueGroup> collectAllPvGroups(
      PropertyValueGroup group, List<PropertyValueGroup> result) {
    if (!result.contains(group)) {
      result.add(group);
      for (PropertyValueGroup child : group.getAppliedPropertyValueGroups()) {
        collectAllPvGroups(child, result);
      }
    }
    return result;
  }

  /**
   * Returns the elements attached to a displayed traceable element.
   *
   * @param source
   *     displayed element
   * @return attached element
   */
  public static List<? extends ModelElement> getTracedElementTargets(NamedElement source) {
    if (source instanceof PropertyValueGroup) {
      return filterParent(source, ((PropertyValueGroup) source).getValuedElements());
    }
    if (source instanceof AbstractPropertyValue) {
      return filterParent(source, ((AbstractPropertyValue) source).getValuedElements());
    }
    if (source instanceof Constraint) {
      return ((Constraint) source).getConstrainedElements();
    }
    return List.of();
  }

  private static List<? extends ModelElement> filterParent(
      NamedElement source, List<? extends ModelElement> values) {
    EObject container = source.eContainer();

    // PV and PVG are displayed, so they are filtered from target.
    if (!(container instanceof PropertyValueGroup
        || container instanceof AbstractPropertyValue)) {
      // No need to filter.
      return values;
    }

    return values.stream()
        .filter(value -> value != container)
        .collect(Collectors.toList());
  }

  /**
   * Returns related elements for provided element.
   * <p>
   * if flag {@code recursive} is set, 
   * </p>
   * 
   * @param element an end of traces
   * @param recursive search for all related elements
   * @return a list of related elements
   */
  public static List<NamedElement> getTracedRelatedElements(
      NamedElement element, boolean recursive) {
    List<NamedElement> directTraces = getDirectRelatedElements(element);
    if (!recursive) {
      return directTraces;
    }

    List<NamedElement> result = new ArrayList<>();
    for (NamedElement trace : directTraces) {
      collectTracedElements(trace, result);
    }

    return result;
  }

  private static List<NamedElement> getDirectRelatedElements(NamedElement element) {
    List<NamedElement> result = Stream.concat(
        element.getIncomingTraces().stream().map(AbstractTrace::getSourceElement),
        element.getOutgoingTraces().stream().map(AbstractTrace::getTargetElement))
        .filter(NamedElement.class::isInstance)
        .map(NamedElement.class::cast)
        .collect(Collectors.toList());

    if (element instanceof PropertyValueGroup) {
      PropertyValueGroup group = (PropertyValueGroup) element;
      for (AbstractPropertyValue child : group.getOwnedPropertyValues()) {
        List<NamedElement> childrenRelations = getDirectRelatedElements(child);
        if (!childrenRelations.isEmpty()) {
          result.add(child);
          result.addAll(childrenRelations);
        }
      }

      for (PropertyValueGroup child : group.getOwnedPropertyValueGroups()) {
        // Recursive loop is impossible as ownedPropertyValueGroups is
        // containment.
        List<NamedElement> childrenRelations = getDirectRelatedElements(child);
        if (!childrenRelations.isEmpty()) {
          result.add(child);
          result.addAll(childrenRelations);
        }
      }
    }

    return result;
  }

  private static void collectTracedElements(NamedElement element, List<NamedElement> result) {
    if (result.contains(element)) {
      return; // skip collect: avoid recursive loop.
    }
    result.add(element);

    List<NamedElement> tracedElements = getDirectRelatedElements(element);
    for (NamedElement trace : tracedElements) {
      collectTracedElements(trace, result);
    }
  }

  /**
   * Returns all Traceable Elements candidates for Traceability diagram.
   *
   * @param context
   *     current selection
   * @return {@link NamedElement}s
   */
  public static List<NamedElement> getAllTraceableCandidates(Namespace context) {
    List<NamedElement> result = new ArrayList<>();

    SystemEngineering root = CapellaServices.getRootSystemEngineering(context);
    for (ModellingArchitecture block : root.getOwnedArchitectures()) {
      Iterator<EObject> i = block.eAllContents();
      while (i.hasNext()) {
        EObject value = i.next();
        if (value instanceof Constraint
            || value instanceof PropertyValueGroup
            || value instanceof AbstractPropertyValue) {
          result.add((NamedElement) value);
        }
      }
    }
    return result;
  }

}
