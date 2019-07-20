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
package org.polarsys.capella.core.sequencediag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ActorCapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentAllocation;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.SystemComponent;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.SystemActorRealization;
import org.polarsys.capella.core.data.pa.LogicalActorRealization;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
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
  public List<AbstractActor> getAllActors(final EObject any) {
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
   * used in common.odesign, sequences.odesign
   * @param any current object
   * @param filter the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public Collection<Part> getAvailableParts(final EObject any, final Collection<Part> filter) {
    Collection<Part> result = new ArrayList<Part>();
    EObject component = CapellaServices.getService().getAncestor(any, CsPackage.Literals.COMPONENT); //$NON-NLS-1$

    if (component != null) {
      Component comp = (Component) component;
      getOwnedPart(result, comp, filter);
    } else {
      EObject arch = CapellaServices.getService().getAncestor(any, CsPackage.Literals.BLOCK_ARCHITECTURE); //$NON-NLS-1$
      BlockArchitecture architecture = (BlockArchitecture) arch;
      Component rootComponent = null;
      if (arch != null) {
        if (architecture instanceof LogicalArchitecture) {
          rootComponent = ((LogicalArchitecture) architecture).getOwnedLogicalComponent();
          if (rootComponent != null) {
            getOwnedPart(result, rootComponent, filter);
            if (!rootComponent.getRepresentingPartitions().isEmpty()) {
              result.add(((Part) rootComponent.getRepresentingPartitions().get(0)));
            }
            return result;
          }
        } else if (architecture instanceof PhysicalArchitecture) {
          rootComponent = ((PhysicalArchitecture) architecture).getOwnedPhysicalComponent();

        } else if (architecture instanceof EPBSArchitecture) {
          rootComponent = ((EPBSArchitecture) architecture).getOwnedConfigurationItem();
        }
        if (rootComponent != null) {
          getOwnedPart(result, rootComponent, filter);
        }
      }
    }

    return result;
  }

  /**
   * used in common.odesign
   * @param any current object
   * @param filter the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public Collection<Part> getAllAvailableParts(final EObject any, final Collection<Part> filter) {
    return ScenarioExt.getAllAvailableParts(any, filter);
  }

  /**
   * used in common.odesign
   * @param any current object
   * @param filter the parts we don't want to see.
   * @return the owned part of the first ownerComponent or rootComponent
   */
  public Collection<Part> getAllAvailablePartsIncludingSystem(final EObject any, final Collection<Part> filter) {
    return ScenarioExt.getAllAvailablePartsIncludingSystem(any, filter);
  }

  /**
   * @param result owned part of rootComponent
   * @param rootComponent component
   * @param filter the parts we don't want to see.
   */
  private void getOwnedPart(Collection<Part> result, Component rootComponent, Collection<Part> filter) {
    EList<Partition> ownedPartitions = rootComponent.getOwnedPartitions();
    for (Partition partition : ownedPartitions) {
      if (partition instanceof Part) {
        if (!filter.contains(partition)) {
          result.add((Part) partition);
          // recursion, in the case physical;
          AbstractType type = partition.getAbstractType();
          if (type instanceof PhysicalComponent) {
            PhysicalComponent pc = (PhysicalComponent) type;
            getOwnedPart(result, pc, filter);
          }

        }
      }
    }
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
  public List<AbstractActor> getAllRefinedActors(final CapabilityRealization context) {
    List<AbstractActor> result = new ArrayList<AbstractActor>();

    List<CapabilityRealization> capabilities = getRefinedCapabilityRealizations(context);
    if(capabilities != null){
        for (CapabilityRealization capabilityRealization : capabilities) {
        	      result.addAll(capabilityRealization.getParticipatingActors());
        }    	
    }

    Capability capa = (Capability) getRefinedCapabality(context);
    if(capa != null){
        result.addAll(capa.getParticipatingActors());
    }
    
    return result;
  }

  /**
   * used in logical.odesign
   * @param context
   * @return
   */
  public List<ActorCapabilityInvolvement> getRefinedInvolvedActors(final CapabilityRealization context) {
    EList<AbstractTrace> outgoingTraces = context.getOutgoingTraces();
    for (AbstractTrace abstractTrace : outgoingTraces) {
      if (abstractTrace instanceof RefinementLink) {
        TraceableElement targetElement = abstractTrace.getTargetElement();
        if (targetElement instanceof Capability) {
          Capability cap = (Capability) targetElement;
          return cap.getInvolvedActors();
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
  public List<SystemComponent> getOwnedSystemComponents(final SystemComponent context) {
    List<SystemComponent> list = new ArrayList<SystemComponent>();

    ModellingArchitecture architecture = SystemEngineeringExt.findArchitecture(context);
    if (architecture != null) {
      if (((architecture instanceof LogicalArchitecture) && (context instanceof LogicalComponent))
          || ((architecture instanceof PhysicalArchitecture) && (context instanceof PhysicalComponent))) {

        for (Component component : ComponentExt.getSubDefinedComponents(context)) {
          if (component instanceof SystemComponent) {
            list.add((SystemComponent) component);
          }
        }
      }
    }

    return list;
  }

  /**
   * Used in logical.odesign
   */
  public List<ActorCapabilityRealizationInvolvement> getInvolvedActorsForCapabilityRealizations(CapabilityRealization context) {
    List<ActorCapabilityRealizationInvolvement> result = new ArrayList<ActorCapabilityRealizationInvolvement>();

    List<CapabilityRealization> capabilities = getRefinedCapabilityRealizations(context);
    if(capabilities != null){
        for (CapabilityRealization capabilityRealization : capabilities) {
            result.addAll(capabilityRealization.getOwnedActorCapabilityRealizations());
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
  public List<ComponentAllocation> getActorsAllocationLinks(final CapabilityRealization context) {
    List<ComponentAllocation> result = new ArrayList<ComponentAllocation>();
    List<AbstractActor> actors = getAllRefinedActors(context);
    for (AbstractActor abstractActor : actors) {
      if (abstractActor instanceof PhysicalActor) {
        PhysicalActor pa = (PhysicalActor) abstractActor;
        for (LogicalActorRealization lar : pa.getOwnedLogicalActorRealizations()) {
          if (actors.contains(lar.getAllocatedComponent())) {
            result.add(lar);
          }
        }
      } else if (abstractActor instanceof LogicalActor) {
        LogicalActor la = (LogicalActor) abstractActor;
        for (SystemActorRealization sar : la.getOwnedSystemActorRealizations()) {
          if (actors.contains(sar.getAllocatedComponent())) {
            result.add(sar);
          }
        }

      }
    }
    return result;
  }

}
