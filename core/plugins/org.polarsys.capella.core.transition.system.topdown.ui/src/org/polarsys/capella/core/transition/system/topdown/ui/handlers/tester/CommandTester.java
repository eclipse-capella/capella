/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.ui.handlers.tester;

import org.eclipse.core.expressions.PropertyTester;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.transition.system.topdown.commands.TransitionCommandHelper;

public class CommandTester extends PropertyTester {

  @Override
  public boolean test(Object object, String propertyName, Object[] params, Object testedValue) {
    if (propertyName.equals("transitionMode")) { //$NON-NLS-1$
      ModelElement element = ModelAdaptation.adaptToCapella(object);

      if ((element != null) && (testedValue instanceof String)) {
        String value = (String) testedValue;

        switch (value) {
        case "transitionFunctional":
          return TransitionCommandHelper.getInstance().isFunctionalTransitionAvailable(element);
        case "transitionInterface":
          return TransitionCommandHelper.getInstance().isInterfaceTransitionAvailable(element);
        case "transitionStateMachine":
          return TransitionCommandHelper.getInstance().isStateMachineTransitionAvailable(element);
        case "transitionData":
          return TransitionCommandHelper.getInstance().isDataTransitionAvailable(element);
        case "transitionPropertyValue":
          return TransitionCommandHelper.getInstance().isPropertyValueTransitionAvailable(element);
        case "transitionExchangeItem":
          return TransitionCommandHelper.getInstance().isExchangeItemTransitionAvailable(element);
        case "transitionActor":
          return TransitionCommandHelper.getInstance().isActorTransitionAvailable(element);
        case "transitionSystem":
          return TransitionCommandHelper.getInstance().isSystemTransitionAvailable(element);
        case "transitionLC2PC":
          return TransitionCommandHelper.getInstance().isLC2PCTransitionAvailable(element);
        case "transitionOE2Actor":
          return TransitionCommandHelper.getInstance().isOE2ActorTransitionAvailable(element);
        case "transitionOE2System":
          return TransitionCommandHelper.getInstance().isOE2SystemTransitionAvailable(element);
        case "transitionCapability":
          return TransitionCommandHelper.getInstance().isCapabilityTransitionAvailable(element);
        case "transitionOC2SM":
          return TransitionCommandHelper.getInstance().isOC2SMTransitionAvailable(element);
        case "transitionOA2SC":
          return TransitionCommandHelper.getInstance().isOA2SCTransitionAvailable(element);
        case "transitionOA2SM":
          return TransitionCommandHelper.getInstance().isOA2SMTransitionAvailable(element);
        case "transitionPC2CI":
          return TransitionCommandHelper.getInstance().isPC2CITransitionAvailable(element);
        default:
          break;
        }
      }
    }

    return false;
  }
}
