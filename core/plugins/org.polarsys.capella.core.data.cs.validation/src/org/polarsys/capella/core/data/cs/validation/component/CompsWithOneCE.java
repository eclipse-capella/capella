/*******************************************************************************
 * Copyright (c) 2014, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.cs.validation.component;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalNode;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A component/actor should be connected by at least one CE.
 */
public class CompsWithOneCE extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (((ctx.getTarget() instanceof Component) && (((Component) ctx.getTarget()).isActor()))
        || ctx.getTarget() instanceof SystemComponent || ctx.getTarget() instanceof LogicalComponent
        || this.isPcButNotNode(ctx.getTarget())) {
      Classifier cl = (Classifier) ctx.getTarget();

      boolean atLeastOnePortFound = false;
      for (Feature feature : cl.getOwnedFeatures()) {
        if (feature instanceof ComponentPort) {
          ComponentPort p = (ComponentPort) feature;
          if (p.getComponentExchanges().size() > 0) {
            atLeastOnePortFound = true;
            break;
          }
        }
      }
      if (!atLeastOnePortFound)
        return ctx.createFailureStatus(new Object[] { cl.getName() });
    }

    return ctx.createSuccessStatus();
  }

  private boolean isPcButNotNode(EObject obj) {
    if (obj instanceof PhysicalNode) {
      return false;
    }
    if (obj instanceof PhysicalComponent) {
      PhysicalComponent comp = (PhysicalComponent) obj;
      if (comp.getNature() == PhysicalComponentNature.NODE) {
        return false;
      }
      return true;
    }

    return false;
  }
}
