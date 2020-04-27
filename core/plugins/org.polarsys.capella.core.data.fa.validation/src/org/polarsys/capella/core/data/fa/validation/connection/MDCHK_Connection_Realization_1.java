/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.validation.connection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 * This check insures that a Component Exchange is realized by at least one Component Exchange.
 */
public class MDCHK_Connection_Realization_1 extends AbstractValidationRule {
  /**
   * "unknown level" string
   */
  private static final String UNKNOWN_LEVEL = "unknown level"; //$NON-NLS-1$

  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // This rule does not applies to physical links
      if (eObj instanceof ComponentExchange) {
        ComponentExchange exch = (ComponentExchange) eObj;
        if (CapellaLayerCheckingExt.isInPhysicalLayer(exch)) {
          return ctx.createSuccessStatus();
        }
        for (AbstractTrace trace : exch.getIncomingTraces()) {
          if ((trace instanceof ComponentExchangeRealization) && (trace.getSourceElement() instanceof ComponentExchange)) {
            return ctx.createSuccessStatus();
          }
        }
        // Strings storing the item levels
        String itemLevel = UNKNOWN_LEVEL, otherItemLevel = UNKNOWN_LEVEL;
        // Gets the item architecture level
        BlockArchitecture architectureLevel = SystemEngineeringExt.getRootBlockArchitecture(exch);
        if (null != architectureLevel) {
          itemLevel = architectureLevel.getName();
          SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(architectureLevel);
          // Then get the "should be there" other item architecture level
          if (systemEngineering != null) {
            AbstractNamedElement elem = null;
            if (architectureLevel instanceof SystemAnalysis) {
              elem = SystemEngineeringExt.getOwnedLogicalArchitecture(systemEngineering);
            } else if (architectureLevel instanceof LogicalArchitecture) {
              elem = SystemEngineeringExt.getOwnedPhysicalArchitecture(systemEngineering);
            }
            if (elem != null) {
              otherItemLevel = elem.getName();
            }
          }

        }
        return ctx.createFailureStatus(new Object[] { exch.getName(), itemLevel, otherItemLevel });
      }
    }
    return ctx.createSuccessStatus();
  }

}
