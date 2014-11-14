/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.helpers.cs.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;

/**
 */
public class PhysicalLinkExt {

  public static Collection<PhysicalLinkEnd> getRelatedPhysicalLinkEnds(Port element_p) {
    HashSet<PhysicalLinkEnd> result = new HashSet<PhysicalLinkEnd>();
    List<EReference> refs = new ArrayList<EReference>();
    refs.add(CsPackage.Literals.PHYSICAL_LINK_END__PORT);

    for (Object objectRef : EObjectExt.getReferencers(element_p, refs)) {
      result.add((PhysicalLinkEnd) objectRef);
    }

    return result;
  }

  public static Collection<PhysicalLinkEnd> getRelatedPhysicalLinkEnds(Part element_p) {
    HashSet<PhysicalLinkEnd> result = new HashSet<PhysicalLinkEnd>();
    List<EReference> refs = new ArrayList<EReference>();
    refs.add(CsPackage.Literals.PHYSICAL_LINK_END__PART);

    for (Object objectRef : EObjectExt.getReferencers(element_p, refs)) {
      result.add((PhysicalLinkEnd) objectRef);
    }

    return result;
  }

  /**
   * Returns all PhysicalLink involving the port
   * @param element_p
   * @return
   */
  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(PhysicalPort element_p) {
    HashSet<PhysicalLink> result = new HashSet<PhysicalLink>();
    result.addAll(element_p.getInvolvedLinks());

    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(element_p)) {
      result.addAll(end.getInvolvedLinks());
    }

    return result;
  }

  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(Component element_p) {
    HashSet<PhysicalLink> result = new HashSet<PhysicalLink>();

    for (Feature port : element_p.getOwnedFeatures()) {
      if (port instanceof PhysicalPort) {
        result.addAll(PhysicalLinkExt.getAllRelatedPhysicalLinks((PhysicalPort) port));
      }
    }

    return result;
  }

  /**
   * Retrieve the helper part.componentExchanges returns all component exchanges directly connected to part, or by a component exchange end.
   * @param part_p
   * @return
   */
  public static final List<PhysicalLink> getPhysicalLinks(Part part_p) {
    List<PhysicalLink> result = new ArrayList<PhysicalLink>();
    for (AbstractInformationFlow flow : part_p.getInformationFlows()) {
      if (flow instanceof PhysicalLink) {
        result.add((PhysicalLink) flow);
      }
    }
    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(part_p)) {
      result.addAll(end.getInvolvedLinks());
    }
    return result;
  }

  /**
   * Returns all PhysicalLink involving the part. (include physical link linked through physical Ports)
   * @param element_p
   * @return
   */
  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(Part element_p) {
    HashSet<PhysicalLink> result = new HashSet<PhysicalLink>();

    if (element_p.getAbstractType() instanceof Component) {
      Component component = ((Component) element_p.getAbstractType());
      result.addAll(getAllRelatedPhysicalLinks(component));
    }

    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(element_p)) {
      result.addAll(end.getInvolvedLinks());
    }

    return result;
  }

  public static EObject getSource(PhysicalLink link_p) {
    if (link_p.getLinkEnds().size() > 0) {
      return link_p.getLinkEnds().get(0);
    }
    return null;
  }

  public static EObject getTarget(PhysicalLink link_p) {
    if (link_p.getLinkEnds().size() > 1) {
      return link_p.getLinkEnds().get(1);
    }
    return null;
  }

  public static Port getSourcePort(PhysicalLink link_p) {
    EObject source = getSource(link_p);
    if (source instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }
    return null;
  }

  public static Port getTargetPort(PhysicalLink link_p) {
    EObject target = getTarget(link_p);
    if (target instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }
    return null;
  }

  public static Part getSourcePart(PhysicalLink link_p) {
    EObject source = getSource(link_p);
    if (source instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) source).getPart();
    } else if (source instanceof Port) {
      PartitionableElement container = (PartitionableElement) source.eContainer();
      for (Partition aPartition : container.getRepresentingPartitions()) {
        return ((Part) aPartition);
      }
    }
    return null;
  }

  /**
   * Returns source Parts of the physical link If physical link is related to one part, returns a singleton of related part. If physical link is related to a
   * component, returns representing parts of the component
   */
  public static Collection<Part> getSourceParts(PhysicalLink link_p) {
    Part part = getSourcePart(link_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(link_p);
    if (sourceComponent != null) {
      ArrayList<Part> result = new ArrayList<Part>();
      for (Partition partition : sourceComponent.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.add((Part) partition);
        }
      }
      return result;
    }
    return Collections.emptyList();
  }

  public static Part getTargetPart(PhysicalLink link_p) {
    EObject target = getTarget(link_p);
    if (target instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) target).getPart();
    } else if (target instanceof Port) {
      PartitionableElement container = (PartitionableElement) target.eContainer();
      for (Partition aPartition : container.getRepresentingPartitions()) {
        return ((Part) aPartition);
      }
    }
    return null;
  }

  /**
   * Returns target Parts of the physical link If physical link is related to one part, returns a singleton of related part. If physical link is related to a
   * component, returns representing parts of the component
   */
  public static Collection<Part> getTargetParts(PhysicalLink link_p) {
    Part part = getTargetPart(link_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(link_p);
    if (targetComponent != null) {
      ArrayList<Part> result = new ArrayList<Part>();
      for (Partition partition : targetComponent.getRepresentingPartitions()) {
        if (partition instanceof Part) {
          result.add((Part) partition);
        }
      }
      return result;
    }
    return Collections.emptyList();
  }

  public static Component getSourceComponent(PhysicalLink link_p) {
    Port sourcePort = getSourcePort(link_p);
    if (null != sourcePort) {
      EObject eContainer = sourcePort.eContainer();
      if ((eContainer != null) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    }
    return null;
  }

  public static Component getTargetComponent(PhysicalLink link_p) {
    Port sourcePort = getTargetPort(link_p);
    if (null != sourcePort) {
      EObject eContainer = sourcePort.eContainer();
      if ((eContainer != null) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    }
    return null;
  }

  /**
   * @param link1_p
   * @param link2_p
   * @return the common Part of link1_p and link2_p
   */
  public static Part getCommonPart(PhysicalLink link1_p, PhysicalLink link2_p) {
    Part sourceLink1Part = getSourcePart(link1_p);
    Part targetLink1Part = getTargetPart(link1_p);
    Part sourceLink2Part = getSourcePart(link2_p);
    Part targetLink2Part = getTargetPart(link2_p);
    if (sourceLink1Part.equals(sourceLink2Part) || sourceLink1Part.equals(targetLink2Part)) {
      return sourceLink1Part;
    }
    if (targetLink1Part.equals(sourceLink2Part) || targetLink1Part.equals(targetLink2Part)) {
      return targetLink1Part;
    }
    return null;
  }

  /**
   * @param end_p
   * @return
   */
  public static PhysicalPort getRelatedPort(AbstractPhysicalLinkEnd end_p) {
    if (end_p instanceof PhysicalPort) {
      return (PhysicalPort) end_p;
    } else if (end_p instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) end_p).getPort();
    }
    return null;
  }

}
