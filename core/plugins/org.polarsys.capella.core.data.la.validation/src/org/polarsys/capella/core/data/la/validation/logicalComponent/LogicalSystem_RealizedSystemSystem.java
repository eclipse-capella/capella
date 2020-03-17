/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.la.validation.logicalComponent;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * TC_DC_12 - This rule ensures that Root Logical Component always realizes a Root System Component.
 */
public class LogicalSystem_RealizedSystemSystem extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof LogicalComponent && !ComponentExt.isActor(eObj)) {
        LogicalComponent component = (LogicalComponent) eObj;
        if (component.equals(BlockArchitectureExt.getRootBlockArchitecture(component).getSystem())) {
          if (component.getRealizedSystemComponents().isEmpty()) {
            BlockArchitecture previousArchitectures = BlockArchitectureExt
                .getPreviousBlockArchitecture(BlockArchitectureExt.getRootBlockArchitecture(component));
            Component previousRootComponent = previousArchitectures.getSystem();
            String previousRootComponentName = NLS.bind("Root {0}", CtxPackage.Literals.SYSTEM_COMPONENT.getName());
            if (previousRootComponent != null) {
              previousRootComponentName = NLS.bind("Root \"{0}\"{1}", previousRootComponent.getName(),
                  EObjectLabelProviderHelper.getMetaclassLabel(previousRootComponent, true));
            }

            return ctx.createFailureStatus(NLS.bind("Root \"{0}\"{1}", component.getName(),
                EObjectLabelProviderHelper.getMetaclassLabel(component, true)), previousRootComponentName);
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
