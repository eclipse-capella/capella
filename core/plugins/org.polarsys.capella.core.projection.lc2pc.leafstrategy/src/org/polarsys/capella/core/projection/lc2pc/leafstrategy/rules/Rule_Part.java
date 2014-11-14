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
package org.polarsys.capella.core.projection.lc2pc.leafstrategy.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.LcToPcProjectionUtils;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class Rule_Part extends org.polarsys.capella.core.projection.common.rules.cs.Rule_Part {

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    if (isFirstAttach(element_p, result_p, context_p)) {
      // Store Leaf strategy projection flag on 'TransfoLink'
      leafStrategyFlag((Part) element_p, (Part) result_p);
    }
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    EObject container = element_p.eContainer();
    if (!(container instanceof ComponentContext)) {
      return BlockArchitectureExt.getFirstComponent(architecture);
    }
    return null;
  }

  // Add Leaf strategy projection flag on 'TransfoLink' between 'part'
  private void leafStrategyFlag(Part partSrc_p, Part partTgt_p) {
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
            if (key.equals(LcToPcProjectionUtils.LEAF_PROJECTION_STRATEGY) && value.equals(Boolean.TRUE.toString())) {
              alreadyFlag = true;
            }
          }
          if (!alreadyFlag) {
            KeyValue keyValue = CapellacoreFactory.eINSTANCE.createKeyValue();
            keyValue.setKey(LcToPcProjectionUtils.LEAF_PROJECTION_STRATEGY);
            keyValue.setValue(Boolean.TRUE.toString());
            genericTrace.getKeyValuePairs().add(keyValue);
          }
        }
      }
    }
  }

}
