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
package org.polarsys.capella.core.refinement.commands;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class RefinementCommandHelper {

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailableSynchronizeInterfacesLA(ModelElement object) {
    return ((object instanceof LogicalComponent) && ComponentExt.isComponentRoot(object))
        || (object instanceof Part && ((Part) object).getAbstractType() instanceof LogicalComponent && ComponentExt.isComponentRoot(((Part) object).getAbstractType()));
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailableSynchronizeInterfacesPC(ModelElement object) {
    return ((object instanceof PhysicalComponent) && ComponentExt.isLeaf((Component) object));
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailableSynchronizeInterfacesCI(ModelElement object) {
    return ((object instanceof ConfigurationItem) && ComponentExt.isLeaf((Component) object));
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailablePropagateInterfacesSAtoLA(ModelElement object) {
    return (object instanceof System)
        || (object instanceof Part && ((Part) object).getAbstractType() instanceof System);
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailablePropagateInterfacesLCtoPC(ModelElement object) {
    return ((object instanceof LogicalComponent) && ComponentExt.isLeaf((Component) object));
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isAvailablePropagateInterfacesPCtoCI(ModelElement object) {
    return ((object instanceof PhysicalComponent) && ComponentExt.isLeaf((Component) object));
  }
}
