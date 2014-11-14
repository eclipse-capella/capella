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
package org.polarsys.capella.core.model.helpers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 *
 */
public class ComponentPortAllocationExt {

  /**
   * Return source port of the connection (mono Part mode)
   * @param connection_p
   * @return Part
   */
  public static Port getSourcePort(ComponentPortAllocation connection_p) {
    TraceableElement source = connection_p.getSourceElement();
    if (source instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }

    return null;
  }

  /**
   * Return target port of the connection (mono Part mode)
   * @param connection_p
   * @return
   */
  public static Port getTargetPort(ComponentPortAllocation connection_p) {
    TraceableElement target = connection_p.getTargetElement();
    if (target instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) target).getPort();
    } else if (target instanceof Port) {
      return (Port) target;
    }

    return null;
  }

  /**
   * Returns source Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected source component)
   * @param connection_p
   * @return
   */
  @Deprecated
  public static Part getSourcePart(ComponentPortAllocation connection_p) {
    TraceableElement source = connection_p.getSourceElement();
    if (source instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) source).getPart();
    } else if (source instanceof Part) {
      return (Part) source;
    }

    return null;
  }

  /**
   * Returns source Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<Part> getSourceParts(ComponentPortAllocation connection_p) {
    Part part = getSourcePart(connection_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(connection_p);
    if (sourceComponent != null) {
      return ComponentExt.getRepresentingParts(sourceComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Return target Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected target component)
   * @param connection_p
   * @return
   */
  @Deprecated
  public static Part getTargetPart(ComponentPortAllocation connection_p) {
    TraceableElement target = connection_p.getTargetElement();
    if (target instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) target).getPart();
    } else if (target instanceof Part) {
      return (Part) target;
    }
    return null;
  }

  /**
   * Returns target Parts of the connection If component exchange is related to one part, returns a singleton of related part. If component exchange is related
   * to a component, returns representing parts of the component
   */
  public static Collection<Part> getTargetParts(ComponentPortAllocation connection_p) {
    Part part = getTargetPart(connection_p);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(connection_p);
    if (targetComponent != null) {
      return ComponentExt.getRepresentingParts(targetComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange_p
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(ComponentPortAllocation exchange_p) {
    return attachTo(exchange_p, getDefaultContainer(exchange_p));
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange_p
   * @param container_p
   * @return
   */
  public static boolean attachTo(ComponentPortAllocation exchange_p, PhysicalPort container_p) {
    if ((container_p != null) && !container_p.equals(exchange_p.eContainer())) {
      (container_p).getOwnedComponentPortAllocations().add(exchange_p);
      return true;
    }
    return false;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param exchange_p
   * @return a not null element
   */
  public static PhysicalPort getDefaultContainer(ComponentPortAllocation exchange_p) {
    return (PhysicalPort) getSourcePort(exchange_p);
  }

  /**
   * Return best source element of the Connection
   * @param connection_p
   * @return ModelElement
   */
  public static Component getSourceComponent(ComponentPortAllocation connection_p) {
    TraceableElement source = connection_p.getSourceElement();
    // connection end
    if (source instanceof ComponentPortAllocationEnd) {
      Partition part = ((ComponentPortAllocationEnd) source).getPart();
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // part
    else if (source instanceof Part) {
      Part part = (Part) source;
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // component port
    else if (source instanceof ComponentPort) {
      EObject eContainer = source.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    }// physcialPort (kind delegation connection)
    else if (source instanceof PhysicalPort) {
      EObject eContainer = source.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    } else if (source instanceof Component) {
      Component eContainer = (Component) source;
      if ((null != eContainer)) {
        return eContainer;
      }
    }

    return null;
  }

  /**
   * Return best target element of the Connection
   * @param connection_p
   * @return ModelElement
   */
  public static Component getTargetComponent(ComponentPortAllocation connection_p) {
    TraceableElement target = connection_p.getTargetElement();
    // connection end
    if (target instanceof ComponentPortAllocationEnd) {
      Partition part = ((ComponentPortAllocationEnd) target).getPart();
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // part
    else if (target instanceof Part) {
      Part part = (Part) target;
      if (null != part) {
        AbstractType abstractType = part.getType();
        if ((null != abstractType) && (abstractType instanceof Component)) {
          return (Component) abstractType;
        }
      }
    }
    // component port
    else if (target instanceof ComponentPort) {
      EObject eContainer = target.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }

    }// physcialPort (kind delegation connection)
    else if (target instanceof PhysicalPort) {
      EObject eContainer = target.eContainer();
      if ((null != eContainer) && (eContainer instanceof Component)) {
        return (Component) eContainer;
      }
    } else if (target instanceof Component) {
      Component eContainer = (Component) target;
      if ((null != eContainer)) {
        return eContainer;
      }
    }

    return null;
  }

}
