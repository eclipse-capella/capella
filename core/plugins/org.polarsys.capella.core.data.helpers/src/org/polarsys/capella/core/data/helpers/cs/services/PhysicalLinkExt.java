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
package org.polarsys.capella.core.data.helpers.cs.services;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.information.Port;
/**
 */
public class PhysicalLinkExt {
  

  public static Collection<PhysicalLinkEnd> getRelatedPhysicalLinkEnds(Port element) {
    HashSet<PhysicalLinkEnd> result = new HashSet<>();
    List<EReference> refs = new ArrayList<>();
    refs.add(CsPackage.Literals.PHYSICAL_LINK_END__PORT);

    for (Object objectRef : EObjectExt.getReferencers(element, refs)) {
      result.add((PhysicalLinkEnd) objectRef);
    }

    return result;
  }

  public static Collection<PhysicalLinkEnd> getRelatedPhysicalLinkEnds(Part element) {
    HashSet<PhysicalLinkEnd> result = new HashSet<>();
    List<EReference> refs = new ArrayList<>();
    refs.add(CsPackage.Literals.PHYSICAL_LINK_END__PART);

    for (Object objectRef : EObjectExt.getReferencers(element, refs)) {
      result.add((PhysicalLinkEnd) objectRef);
    }

    return result;
  }

  /**
   * Returns all PhysicalLink involving the port
   * 
   * @param element
   * @return
   */
  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(PhysicalPort element) {
    HashSet<PhysicalLink> result = new HashSet<>();
    result.addAll(element.getInvolvedLinks());

    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(element)) {
      result.addAll(end.getInvolvedLinks());
    }

    return result;
  }

  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(Component element) {
    HashSet<PhysicalLink> result = new HashSet<>();

    for (PhysicalPort port : element.getContainedPhysicalPorts()) {
      result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, (PhysicalPort) port));
    }

    return result;
  }

  /**
   * Retrieve the helper part.componentExchanges returns all component exchanges directly connected to part, or by a
   * component exchange end.
   * 
   * @param part
   * @return
   */
  public static final List<PhysicalLink> getPhysicalLinks(Part part) {
    List<PhysicalLink> result = new ArrayList<>();
    for (AbstractInformationFlow flow : part.getInformationFlows()) {
      if (flow instanceof PhysicalLink) {
        result.add((PhysicalLink) flow);
      }
    }
    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(part)) {
      result.addAll(end.getInvolvedLinks());
    }
    return result;
  }

  /**
   * Returns all PhysicalLink involving the part. (include physical link linked through physical Ports)
   * 
   * @param element
   * @return
   */
  public static Collection<PhysicalLink> getAllRelatedPhysicalLinks(Part element) {
    HashSet<PhysicalLink> result = new HashSet<>();

    if (element.getAbstractType() instanceof Component) {
      Component component = ((Component) element.getAbstractType());
      result.addAll(getCache(PhysicalLinkExt::getAllRelatedPhysicalLinks, component));
    }

    for (PhysicalLinkEnd end : getRelatedPhysicalLinkEnds(element)) {
      result.addAll(end.getInvolvedLinks());
    }

    return result;
  }

  public static EObject getSource(PhysicalLink link) {
    if (!link.getLinkEnds().isEmpty()) {
      return link.getLinkEnds().get(0);
    }
    return null;
  }

  public static EObject getTarget(PhysicalLink link) {
    if (link.getLinkEnds().size() > 1) {
      return link.getLinkEnds().get(1);
    }
    return null;
  }

  public static Port getSourcePort(PhysicalLink link) {
    EObject source = getSource(link);
    if (source instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }
    return null;
  }

  public static Port getTargetPort(PhysicalLink link) {
    EObject target = getTarget(link);
    if (target instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }
    return null;
  }

  public static Part getSourcePart(PhysicalLink link) {
    EObject source = getSource(link);
    if (source instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) source).getPart();
    } else if (source instanceof Port) {
      EObject eContainer = source.eContainer();
      if (eContainer instanceof Component) {
        for (Part part : ((Component) eContainer).getRepresentingParts()) {
          return part;
        }
      }
    }
    return null;
  }

  /**
   * Returns source Parts of the physical link If physical link is related to one part, returns a singleton of related
   * part. If physical link is related to a component, returns representing parts of the component
   */
  public static Collection<Part> getSourceParts(PhysicalLink link) {
    Part part = getSourcePart(link);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(link);
    if (sourceComponent != null) {
      ArrayList<Part> result = new ArrayList<>();
      for (Part aPart : sourceComponent.getRepresentingParts()) {
        result.add(aPart);
      }
      return result;
    }
    return Collections.emptyList();
  }

  public static Part getTargetPart(PhysicalLink link) {
    EObject target = getTarget(link);
    if (target instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) target).getPart();
    } else if (target instanceof Port) {
      EObject eContainer = target.eContainer();
      if (eContainer instanceof Component) {
        for (Part part : ((Component) eContainer).getRepresentingParts()) {
          return part;
        }
      }
    }
    return null;
  }

  /**
   * Returns target Parts of the physical link If physical link is related to one part, returns a singleton of related
   * part. If physical link is related to a component, returns representing parts of the component
   */
  public static Collection<Part> getTargetParts(PhysicalLink link) {
    Part part = getTargetPart(link);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(link);
    if (targetComponent != null) {
      ArrayList<Part> result = new ArrayList<>();
      for (Part aPart : targetComponent.getRepresentingParts()) {
        result.add(aPart);
      }
      return result;
    }
    return Collections.emptyList();
  }

  public static Component getSourceComponent(PhysicalLink link) {
    Port sourcePort = getSourcePort(link);
    if (null != sourcePort) {
      EObject eContainer = sourcePort.eContainer();
      if (eContainer instanceof Component) {
        return (Component) eContainer;
      }
    }
    return null;
  }

  public static Component getTargetComponent(PhysicalLink link) {
    Port sourcePort = getTargetPort(link);
    if (null != sourcePort) {
      EObject eContainer = sourcePort.eContainer();
      if (eContainer instanceof Component) {
        return (Component) eContainer;
      }
    }
    return null;
  }

  /**
   * @param link1
   * @param link2
   * @return the common Part of link1 and link2
   */
  public static Part getCommonPart(PhysicalLink link1, PhysicalLink link2) {
    Part sourceLink1Part = getSourcePart(link1);
    Part targetLink1Part = getTargetPart(link1);
    Part sourceLink2Part = getSourcePart(link2);
    Part targetLink2Part = getTargetPart(link2);
    if (sourceLink1Part.equals(sourceLink2Part) || sourceLink1Part.equals(targetLink2Part)) {
      return sourceLink1Part;
    }
    if (targetLink1Part.equals(sourceLink2Part) || targetLink1Part.equals(targetLink2Part)) {
      return targetLink1Part;
    }
    return null;
  }

  /**
   * @param end
   * @return
   */
  public static PhysicalPort getRelatedPort(AbstractPhysicalLinkEnd end) {
    if (end instanceof PhysicalPort) {
      return (PhysicalPort) end;
    } else if (end instanceof PhysicalLinkEnd) {
      return ((PhysicalLinkEnd) end).getPort();
    }
    return null;
  }
}
