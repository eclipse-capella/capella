/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.commands;

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class RefinementCommandTester extends PropertyTester {
  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object, String propertyName, Object[] params, Object testedValue) {
    if (propertyName.equals("refinementMode") || propertyName.equals("graphicalRefinementMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      ModelElement element = ModelAdaptation.adaptToCapella(object);
      if (element != null && testedValue instanceof String) {
        String value = (String) testedValue;
        if (value.equals("instanceOfSystem")) { //$NON-NLS-1$
          return (element instanceof System)
              || (element instanceof Part && ((Part) element).getAbstractType() instanceof System);
        } else if (value.equals("instanceOfLogicalComponent")) { //$NON-NLS-1$
            return ((element instanceof LogicalComponent) && ComponentExt.isComponentRoot(element))
                || (element instanceof Part && ((Part) element).getAbstractType() instanceof LogicalComponent && ComponentExt.isComponentRoot(((Part) element).getAbstractType()));
        } else if (value.equals("instanceOfSystemActorOrSystemActorPck")) { //$NON-NLS-1$
          return (element instanceof ActorPkg)
              || (element instanceof Actor)
              || (element instanceof Part && ((Part) element).getAbstractType() instanceof System);
        } else if (value.equals("instanceOfLogicalActorOrLogicalActorPck")) { //$NON-NLS-1$
            return (element instanceof LogicalActorPkg)
                || (element instanceof LogicalActor)
                || (element instanceof Part && ((Part) element).getAbstractType() instanceof LogicalComponent);          
        } else if (value.equals("propagateInterfacesLCtoPC")) { //$NON-NLS-1$
          return ((element instanceof LogicalComponent) && ComponentExt.isLeaf((Component) element));
        } else if (value.equals("propagateInterfacesPCtoCI")) { //$NON-NLS-1$
          return ((element instanceof PhysicalComponent) && ComponentExt.isLeaf((Component) element));
        } else if (value.equals("synchronizeInterfacesPC")) { //$NON-NLS-1$
          return ((element instanceof PhysicalComponent) && ComponentExt.isLeaf((Component) element));
        } else if (value.equals("synchronizeInterfacesCI")) { //$NON-NLS-1$
          return ((element instanceof ConfigurationItem) && ComponentExt.isLeaf((Component) element));
        }
      }
    }
    return false;
  }
}