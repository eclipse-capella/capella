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
package org.polarsys.capella.core.transition.system.topdown.ui.actions.tester;

import org.eclipse.core.expressions.PropertyTester;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class CommandTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  @Override
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {

    if (propertyName_p.equals("transitionMode")) { //$NON-NLS-1$ 
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);

      if ((element != null) && (testedValue_p instanceof String)) {
        String value = (String) testedValue_p;

        if (value.startsWith("transition")) { //$NON-NLS-1$
          value = value.substring(10);

          if (value.equals("Functional")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isFunctionalTransitionAvailable(element);

          } else if (value.equals("Interface")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isInterfaceTransitionAvailable(element);

          } else if (value.equals("StateMachine")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isStateMachineTransitionAvailable(element);

          } else if (value.equals("Data")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isDataTransitionAvailable(element);

          } else if (value.equals("PropertyValue")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isPropertyValueTransitionAvailable(element);

          } else if (value.equals("ExchangeItem")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isExchangeItemTransitionAvailable(element);

          } else if (value.equals("Actor")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isActorTransitionAvailable(element);

          } else if (value.equals("System")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isSystemTransitionAvailable(element);

          } else if (value.equals("LC2PC")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isLC2PCTransitionAvailable(element);

          } else if (value.equals("OE2Actor")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isOE2ActorTransitionAvailable(element);

          } else if (value.equals("OE2System")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isOE2SystemTransitionAvailable(element);

          } else if (value.equals("Capability")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isCapabilityTransitionAvailable(element);

          } else if (value.equals("OC2SM")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isOC2SMTransitionAvailable(element);

          } else if (value.equals("OA2SC")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isOA2SCTransitionAvailable(element);

          } else if (value.equals("OA2SM")) { //$NON-NLS-1$
            return TransitionCommandHelper.getInstance().isOA2SMTransitionAvailable(element);

          }

        }
      }
    }

    return false;
  }
}
