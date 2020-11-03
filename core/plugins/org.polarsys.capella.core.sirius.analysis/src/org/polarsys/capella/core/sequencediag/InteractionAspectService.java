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
package org.polarsys.capella.core.sequencediag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;

/**
 * Services for interactions.
 */
public class InteractionAspectService {

  /**
   * Retrive all actors accessible for the any object used in common.odesign, context.odesign
   */
  public List<Component> getAllActors(final EObject any) {
    return ScenarioExt.getAllActors(any);
  }

  /**
   * Returns all components for the architecture that owns the given object. used in oa.odesign, sequences.odesign
   * @param any any object.
   * @param filter the parts we don't want to see.
   * @return all components for the architecture that owns the given object.
   */
  public Collection<Part> getAllComponents(final EObject any, final Collection<Part> filter) {
    return ScenarioExt.getAllComponents(any, filter);
  }

  /**
   * Returns all children of the given part.
   * @param part the parent part.
   * @param filter the parts we don't want to see.
   * @return
   */
  public Collection<Part> getChildren(final Part part, final Collection<Part> filter) {
    if (CsPackage.eINSTANCE.getComponent().isInstance(part.getAbstractType())) {
      final Component component = (Component) part.getAbstractType();
      Collection<Part> allChildren = filter(component.getOwnedFeatures(), Part.class);
      final Collection<Part> result = new LinkedList<Part>(allChildren);
      final Collection<Part> filteredChildren = new LinkedList<Part>(filter);
      filteredChildren.retainAll(result);
      result.removeAll(filter);

      for (final Part filteredChild : filteredChildren) {
        result.addAll(getChildren(filteredChild, filter));
      }
      return result;
    }
    return Collections.<Part> emptyList();
  }

  /**
   * Returns the modelling architecture that owns the given element.
   * @param ownedElement an element.
   * @return the modelling architecture that owns the given element.
   */
  public ModellingArchitecture findArchitecture(final EObject ownedElement) {
    ModellingArchitecture result = null;
    EObject current = ownedElement;
    while ((result == null) && (current != null)) {
      if (CapellacorePackage.eINSTANCE.getModellingArchitecture().isInstance(current)) {
        result = (ModellingArchitecture) current;
      }
      current = current.eContainer();
    }
    return result;
  }

  private static <T> Collection<T> filter(final Collection<?> collection, final Class<T> type) {
    final ArrayList<T> result = new ArrayList<T>(collection.size());

    for (final Object o : collection) {
      if (type.isInstance(o)) {
        result.add(type.cast(o));
      }
    }
    result.trimToSize();

    return result;
  }

  /**
   * Used in logical.odesign
   * @param context
   * @return
   */
  public TraceableElement getRefinedCapabilityRealization(final CapabilityRealization context) {
    EList<AbstractTrace> outgoingTraces = context.getOutgoingTraces();
    for (AbstractTrace abstractTrace : outgoingTraces) {
      if (abstractTrace instanceof RefinementLink) {
        TraceableElement targetElement = abstractTrace.getTargetElement();
        if (targetElement instanceof CapabilityRealization) {
          return targetElement;
        }
      }
    }
    return null;
  }

  /**
   * used in logical.odesign
   * @param context
   * @return
   */
  public TraceableElement getRefinedCapabality(final CapabilityRealization context) {
    EList<AbstractTrace> outgoingTraces = context.getOutgoingTraces();
    for (AbstractTrace abstractTrace : outgoingTraces) {
      if (abstractTrace instanceof RefinementLink) {
        TraceableElement targetElement = abstractTrace.getTargetElement();
        if (targetElement instanceof Capability) {
          return targetElement;
        } else if (targetElement instanceof CapabilityRealization) {
          return getRefinedCapabality((CapabilityRealization) targetElement);
        }
      }
    }
    return null;
  }

  /*
   * used in logical.odesign
   */
  public List<CapabilityRealization> getRefinedCapabilityRealizations(final CapabilityRealization context) {
    List<CapabilityRealization> result = new ArrayList<CapabilityRealization>(4);
    result.add(context);

    EList<AbstractTrace> outgoingTraces = context.getOutgoingTraces();
    for (AbstractTrace abstractTrace : outgoingTraces) {
      if (abstractTrace instanceof RefinementLink) {
        TraceableElement targetElement = abstractTrace.getTargetElement();
        if (targetElement instanceof Capability) {
          return result;
        } else if (targetElement instanceof CapabilityRealization) {
          result.addAll(getRefinedCapabilityRealizations((CapabilityRealization) targetElement));
          return result;
        }
      }
    }
    return null;
  }

  /**
   * used in logical.odesign
   */
  public List<Component> getAllRefinedActors(final CapabilityRealization context) {
    List<Component> result = new ArrayList<>();

    List<CapabilityRealization> capabilities = getRefinedCapabilityRealizations(context);
    if (capabilities != null) {
      for (CapabilityRealization capabilityRealization : capabilities) {
        result.addAll(capabilityRealization.getInvolvedComponents().stream().filter(e -> ComponentExt.isActor(e))
            .map(Component.class::cast).collect(Collectors.toList()));
      }
    }

    Capability capa = (Capability) getRefinedCapabality(context);
    if (capa != null) {
      result.addAll(capa.getInvolvedSystemComponents().stream().filter(e -> ComponentExt.isActor(e))
          .collect(Collectors.toList()));
    }
    
    return result;
  }

  /**
   * used in logical.odesign
   * @param context
   * @return
   */
  public List<CapabilityInvolvement> getRefinedInvolvedActors(final CapabilityRealization context) {
    EList<AbstractTrace> outgoingTraces = context.getOutgoingTraces();
    for (AbstractTrace abstractTrace : outgoingTraces) {
      if (abstractTrace instanceof RefinementLink) {
        TraceableElement targetElement = abstractTrace.getTargetElement();
        if (targetElement instanceof Capability) {
          Capability cap = (Capability) targetElement;
          return cap.getOwnedCapabilityInvolvements().stream().filter(inv -> ComponentExt.isActor(inv.getInvolved()))
              .collect(Collectors.toList());
        } else if (targetElement instanceof CapabilityRealization) {
          return getRefinedInvolvedActors((CapabilityRealization) targetElement);
        }
      }
    }
    return null;
  }

  /**
   * Used in logical.odesign
   * @param context
   * @return
   */
  public List<Component> getOwnedComponents(final Component context) {
    List<Component> list = new ArrayList<>();

    ModellingArchitecture architecture = SystemEngineeringExt.findArchitecture(context);
    if (architecture != null) {
      if (((architecture instanceof LogicalArchitecture) && (context instanceof LogicalComponent))
          || ((architecture instanceof PhysicalArchitecture) && (context instanceof PhysicalComponent))) {

        for (Component component : ComponentExt.getSubDefinedComponents(context)) {
          if (component instanceof Component) {
            list.add((Component) component);
          }
        }
      }
    }

    return list;
  }

  /**
   * Used in logical.odesign
   */
  public List<CapabilityRealizationInvolvement> getInvolvedActorsForCapabilityRealizations(
      CapabilityRealization context) {
    List<CapabilityRealizationInvolvement> result = new ArrayList<CapabilityRealizationInvolvement>();

    List<CapabilityRealization> capabilities = getRefinedCapabilityRealizations(context);
    if (capabilities != null) {
      for (CapabilityRealization capabilityRealization : capabilities) {
        result.addAll(capabilityRealization.getOwnedCapabilityRealizationInvolvements());
      }
    }
    return result;
  }

  /**
   * used in logical.odesign
   */
  public List<RefinementLink> getRefinementLinksChain(final CapabilityRealization context) {
    List<RefinementLink> result = new ArrayList<RefinementLink>(4);

    AbstractCapability current = context;
    while (current instanceof CapabilityRealization) {
      EList<AbstractTrace> outgoingTraces = current.getOutgoingTraces();
      if (outgoingTraces.isEmpty()) {
        return result;
      }
      for (AbstractTrace trace : outgoingTraces) {
        if (trace instanceof RefinementLink) {
          RefinementLink refinementLink = (RefinementLink) trace;
          result.add(refinementLink);
          TraceableElement targetElement = refinementLink.getTargetElement();
          if ((null != targetElement) && (targetElement instanceof AbstractCapability)) {
            current = (AbstractCapability) targetElement;
          }
        }
      }
      if (current.equals(context)) {
        return result;
      }
    }
    return result;
  }

  /**
   * Used in logical.odesign
   * @param context
   * @return
   */
  public List<ComponentRealization> getActorsAllocationLinks(final CapabilityRealization context) {
    List<ComponentRealization> result = new ArrayList<ComponentRealization>();
    List<Component> actors = getAllRefinedActors(context);
    for (Component actor : actors) {
      for (ComponentRealization lar : actor.getOwnedComponentRealizations()) {
        if (actors.contains(lar.getRealizedComponent())) {
          result.add(lar);
        }
      }
    }
    return result;
  }

}
