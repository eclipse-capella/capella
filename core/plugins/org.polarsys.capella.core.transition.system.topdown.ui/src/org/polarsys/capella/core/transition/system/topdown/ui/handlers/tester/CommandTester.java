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
        case "transitionFunctional": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isFunctionalTransitionAvailable(element);
        case "transitionInterface": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isInterfaceTransitionAvailable(element);
        case "transitionStateMachine": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isStateMachineTransitionAvailable(element);
        case "transitionData": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isDataTransitionAvailable(element);
        case "transitionPropertyValue": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isPropertyValueTransitionAvailable(element);
        case "transitionExchangeItem": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isExchangeItemTransitionAvailable(element);
        case "transitionActor": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isActorTransitionAvailable(element);
        case "transitionSystem": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isSystemTransitionAvailable(element);
        case "transitionLC2PC": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isLC2PCTransitionAvailable(element);
        case "transitionOE2Actor": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isOE2ActorTransitionAvailable(element);
        case "transitionOE2System": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isOE2SystemTransitionAvailable(element);
        case "transitionCapability": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isCapabilityTransitionAvailable(element);
        case "transitionOC2SM": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isOC2SMTransitionAvailable(element);
        case "transitionOA2SC": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isOA2SCTransitionAvailable(element);
        case "transitionOA2SM": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isOA2SMTransitionAvailable(element);
        case "transitionPC2CI": //$NON-NLS-1$
          return TransitionCommandHelper.getInstance().isPC2CITransitionAvailable(element);
        default:
          break;
        }
      }
    }

    return false;
  }
}
