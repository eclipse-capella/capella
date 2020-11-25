/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.capability;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An entity rule that transforms an Entity into the System or an Actor according to the corresponding element that has
 * been transitioned beforehand in the target model
 */
public class EntityRule extends org.polarsys.capella.core.transition.system.topdown.rules.oa.EntityRule {
  @Override
  protected boolean transformAsRootComponent(EObject object, IContext context) {
    if (TopDownTransformationHelper.getInstance(context).isTargetASystem(object, context)) {
      return true;
    }
    return false;
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    EObject result = super.transformDirectElement(element, context);
    if (element instanceof Entity && result instanceof SystemComponent) {
      SystemComponent systemComponent = (SystemComponent) result;
      if (!TopDownTransformationHelper.getInstance(context).isTargetASystem(element, context)) {
        systemComponent.setActor(true);
      }
    }
    return result;
  }
}
