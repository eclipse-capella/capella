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
package org.polarsys.capella.core.sirius.ui.copylayout.keyproviders;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.ui.copylayout.AbstractCapellaLayoutDataKey;
import org.polarsys.capella.core.sirius.ui.copylayout.CapellaTraceabilityLayoutDataKey;

/**
 * Retrieve for a TraceableElement a key of all its traced elements
 */
public class TraceabilityKeyProvider implements IKeyProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<LayoutDataKey> getKeys(LayoutDataKey key_p) {
    Collection<LayoutDataKey> keys = new ArrayList<LayoutDataKey>();

    if (key_p instanceof AbstractCapellaLayoutDataKey) {
      AbstractCapellaLayoutDataKey mKey = (AbstractCapellaLayoutDataKey) key_p;
      EObject semantic = mKey.getSemantic();

      if ((semantic instanceof EClass) || (semantic instanceof ColorDescription)) {
        keys.add(new CapellaTraceabilityLayoutDataKey(mKey, semantic));
      }

      if (semantic instanceof TraceableElement) {
        TraceableElement sourceOfTrace = (TraceableElement) semantic;

        for (AbstractTrace trace : sourceOfTrace.getOutgoingTraces()) {
          if (isValidTrace(trace)) {
            keys.add(new CapellaTraceabilityLayoutDataKey(mKey, trace.getTargetElement()));
          }
        }

      } else if (semantic instanceof Part) {
        keys.add(new CapellaTraceabilityLayoutDataKey(mKey, ((Part) semantic).getAbstractType()));

        for (AbstractTrace trace : ((TraceableElement) (((Part) semantic).getAbstractType())).getOutgoingTraces()) {
          if (isValidTrace(trace)) {
            keys.add(new CapellaTraceabilityLayoutDataKey(mKey, trace.getTargetElement()));
            for (Part part : ComponentExt.getRepresentingParts((Component) trace.getTargetElement())) {
              keys.add(new CapellaTraceabilityLayoutDataKey(mKey, part));
            }
          }
        }
      }

    }

    return keys;
  }

  /**
   * @param trace_p
   * @return
   */
  private boolean isValidTrace(AbstractTrace trace_p) {
    if ((trace_p.getSourceElement() == null) || (trace_p.getTargetElement() == null)) {
      return false;
    }
    if (trace_p.getSourceElement() instanceof Port) {
      return trace_p instanceof PortRealization;

    } else if (trace_p.getSourceElement() instanceof ComponentExchange) {
      return trace_p instanceof ComponentExchangeRealization;

    } else if (trace_p.getSourceElement() instanceof FunctionalExchange) {
      return trace_p instanceof FunctionalExchangeRealization;

    }

    return true;
  }

}
