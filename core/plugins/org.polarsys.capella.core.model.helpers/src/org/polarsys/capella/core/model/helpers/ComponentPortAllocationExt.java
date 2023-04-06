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
package org.polarsys.capella.core.model.helpers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.ComponentPortAllocation;
import org.polarsys.capella.core.data.fa.ComponentPortAllocationEnd;
import org.polarsys.capella.core.data.information.Port;

/**
 *
 */
public class ComponentPortAllocationExt {

	/**
	 * Constructor
	 */
  protected ComponentPortAllocationExt() {
	}

  /**
   * Return source port of the connection (mono Part mode)
   * @param connection
   * @return Part
   */
  public static Port getSourcePort(ComponentPortAllocation connection) {
    TraceableElement source = connection.getSourceElement();
    if (source instanceof ComponentPortAllocationEnd) {
      return ((ComponentPortAllocationEnd) source).getPort();
    } else if (source instanceof Port) {
      return (Port) source;
    }

    return null;
  }

  /**
   * Return target port of the connection (mono Part mode)
   * @param connection
   * @return
   */
  public static Port getTargetPort(ComponentPortAllocation connection) {
    TraceableElement target = connection.getTargetElement();
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
   * @param connection
   * @return
   */
  @Deprecated
  public static Part getSourcePart(ComponentPortAllocation connection) {
    TraceableElement source = connection.getSourceElement();
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
  public static Collection<Part> getSourceParts(ComponentPortAllocation connection) {
    Part part = getSourcePart(connection);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component sourceComponent = getSourceComponent(connection);
    if (sourceComponent != null) {
      return getCache(ComponentExt::getRepresentingParts, sourceComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Return target Part of the connection (MultiPart mode) if component exchange is not linked to a part, returns null. (don't returns the first representing
   * partition of connected target component)
   * @param connection
   * @return
   */
  @Deprecated
  public static Part getTargetPart(ComponentPortAllocation connection) {
    TraceableElement target = connection.getTargetElement();
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
  public static Collection<Part> getTargetParts(ComponentPortAllocation connection) {
    Part part = getTargetPart(connection);
    if (part != null) {
      return Collections.singletonList(part);
    }
    Component targetComponent = getTargetComponent(connection);
    if (targetComponent != null) {
      return getCache(ComponentExt::getRepresentingParts, targetComponent);
    }
    return Collections.emptyList();
  }

  /**
   * Move the given component exchange to common ancestor
   * @param exchange
   * @return whether the component exchange has been moved
   */
  public static boolean attachToDefaultContainer(ComponentPortAllocation exchange) {
    return attachTo(exchange, getDefaultContainer(exchange));
  }

  /**
   * Attach the given component exchange to the given abstract functional block
   * @param exchange
   * @param container
   * @return
   */
  public static boolean attachTo(ComponentPortAllocation exchange, PhysicalPort container) {
    if ((container != null) && !container.equals(exchange.eContainer())) {
      (container).getOwnedComponentPortAllocations().add(exchange);
      return true;
    }
    return false;
  }

  /**
   * The best container is the common ancestor of source/target parts. if no parts, we use common ancestor of components (which can happen in OA or if user has
   * deleted parts)
   * @param exchange
   * @return a not null element
   */
  public static PhysicalPort getDefaultContainer(ComponentPortAllocation exchange) {
    return (PhysicalPort) getSourcePort(exchange);
  }

  /**
   * Return best source element of the Connection
   * @param connection
   * @return ModelElement
   */
  public static Component getSourceComponent(ComponentPortAllocation connection) {
    TraceableElement source = connection.getSourceElement();
    // connection end
    if (source instanceof ComponentPortAllocationEnd) {
      Part part = ((ComponentPortAllocationEnd) source).getPart();
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
      AbstractType abstractType = part.getType();
      if ((null != abstractType) && (abstractType instanceof Component)) {
        return (Component) abstractType;
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
      return (Component) source;
    }
    return null;
  }

  /**
   * Return best target element of the Connection
   * @param connection
   * @return ModelElement
   */
  public static Component getTargetComponent(ComponentPortAllocation connection) {
    TraceableElement target = connection.getTargetElement();
    // connection end
    if (target instanceof ComponentPortAllocationEnd) {
      Part part = ((ComponentPortAllocationEnd) target).getPart();
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
      AbstractType abstractType = part.getType();
      if ((null != abstractType) && (abstractType instanceof Component)) {
        return (Component) abstractType;
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
       return (Component) target;
    }

    return null;
  }

}
