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

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class RefinementCommandTester extends PropertyTester {
  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
    if (propertyName_p.equals("refinementMode") || propertyName_p.equals("graphicalRefinementMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);
      if (element != null && testedValue_p instanceof String) {
        String value = (String) testedValue_p;
        if (value.equals("propagateInterfacesSAtoLA")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailablePropagateInterfacesSAtoLA(element);
        } else if (value.equals("propagateInterfacesLCtoPC")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailablePropagateInterfacesLCtoPC(element);
        } else if (value.equals("propagateInterfacesPCtoCI")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailablePropagateInterfacesPCtoCI(element);
        } else if (value.equals("synchronizeInterfacesLA")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailableSynchronizeInterfacesLA(element);
        } else if (value.equals("synchronizeInterfacesPC")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailableSynchronizeInterfacesPC(element);
        } else if (value.equals("synchronizeInterfacesCI")) { //$NON-NLS-1$
          return RefinementCommandHelper.isAvailableSynchronizeInterfacesCI(element);
        }
      }
    }
    return false;
  }
}
