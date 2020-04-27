/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.transition.common.context.TransitionContext;
import org.polarsys.capella.core.transition.diagram.handlers.DiagramDescriptionHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CommandTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
   */
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
    if (propertyName_p.equals("transitionMode") || propertyName_p.equals("graphicalTransitionMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      if (object_p instanceof DDiagram) {
        DDiagram diagram = (DDiagram) object_p;
        IContext context = new TransitionContext();

        if (DiagramDescriptionHelper.getService(context).handles(context, diagram.getDescription())) {
          if (DiagramDescriptionHelper.getService(context).covers(context, diagram.getDescription())) {
            DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(diagram);
            if (DiagramDescriptionHelper.getService(context).covers(context, descriptor)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
