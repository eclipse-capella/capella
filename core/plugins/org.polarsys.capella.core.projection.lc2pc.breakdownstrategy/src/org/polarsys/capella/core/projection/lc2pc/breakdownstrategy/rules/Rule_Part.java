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
package org.polarsys.capella.core.projection.lc2pc.breakdownstrategy.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.projection.common.LcToPcProjectionUtils;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class Rule_Part extends org.polarsys.capella.core.projection.common.rules.cs.Rule_Part {

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      // Store BreakDown strategy projection flag on 'TransfoLink'
      breakDownStrategyFlag((Part) element_p, (Part) result_p);
    }
  }

  // Add BreakDown strategy projection flag on 'TransfoLink' between 'part'
  private void breakDownStrategyFlag(Part partSrc_p, Part partTgt_p) {
    boolean alreadyFlag = false;
    List<AbstractTrace> traceList = partSrc_p.getIncomingTraces();
    for (AbstractTrace trace : traceList) {
      if (trace instanceof GenericTrace) {
        GenericTrace genericTrace = (GenericTrace) trace;
        if (genericTrace.getSourceElement().equals(partTgt_p)) {
          // Check if flag is already put on link
          for (KeyValue keyValue : genericTrace.getKeyValuePairs()) {
            String key = keyValue.getKey();
            String value = keyValue.getValue();
            if (key.equals(LcToPcProjectionUtils.BREAK_DOWN_PROJECTION_STRATEGY) && value.equals(Boolean.TRUE.toString())) {
              alreadyFlag = true;
            }
          }
          if (!alreadyFlag) {
            KeyValue keyValue = CapellacoreFactory.eINSTANCE.createKeyValue();
            keyValue.setKey(LcToPcProjectionUtils.BREAK_DOWN_PROJECTION_STRATEGY);
            keyValue.setValue(Boolean.TRUE.toString());
            genericTrace.getKeyValuePairs().add(keyValue);
          }
        }
      }
    }
  }
}
