/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui.copyformat.keyproviders;

import static org.polarsys.capella.core.data.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeRealization;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeRealization;
import org.polarsys.capella.core.data.fa.impl.ComponentFunctionalAllocationImpl;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.ui.copyformat.AbstractCapellaFormatDataKey;
import org.polarsys.capella.core.sirius.ui.copyformat.CapellaTraceabilityFormatDataKey;

/**
 * Retrieve for a TraceableElement a key of all its traced elements
 */
public class TraceabilityKeyProvider implements IKeyProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<FormatDataKey> getKeys(FormatDataKey key) {
    Collection<FormatDataKey> keys = new ArrayList<FormatDataKey>();

    if (key instanceof AbstractCapellaFormatDataKey) {
      AbstractCapellaFormatDataKey mKey = (AbstractCapellaFormatDataKey) key;
      EObject semantic = mKey.getSemantic();

      if ((semantic instanceof EClass) || (semantic instanceof ColorDescription)) {
        keys.add(new CapellaTraceabilityFormatDataKey(mKey, semantic));
      }

      if (semantic instanceof TraceableElement) {
        TraceableElement sourceOfTrace = (TraceableElement) semantic;

        for (AbstractTrace trace : sourceOfTrace.getOutgoingTraces()) {
          if (isValidTrace(trace)) {
            keys.add(new CapellaTraceabilityFormatDataKey(mKey, trace.getTargetElement()));
          }
        }
      }
      
      if (semantic instanceof Part) {
        keys.add(new CapellaTraceabilityFormatDataKey(mKey, ((Part) semantic).getAbstractType()));

        for (AbstractTrace trace : ((TraceableElement) (((Part) semantic).getAbstractType())).getOutgoingTraces()) {
          if (isValidTrace(trace)) {
            keys.add(new CapellaTraceabilityFormatDataKey(mKey, trace.getTargetElement()));
            for (Part part : getCache(ComponentExt::getRepresentingParts, (Component) trace.getTargetElement())) {
              keys.add(new CapellaTraceabilityFormatDataKey(mKey, part));
            }
          }
        }
      }

    }

    return keys;
  }

  /**
   * @param trace
   * @return
   */
  private boolean isValidTrace(AbstractTrace trace) {
    if ((trace.getSourceElement() == null) || (trace.getTargetElement() == null)) {
      return false;
    }
    if (trace instanceof ComponentFunctionalAllocation) {
      return false;
    }
    if (trace.getSourceElement() instanceof Port) {
      return trace instanceof PortRealization;

    } else if (trace.getSourceElement() instanceof ComponentExchange) {
      return trace instanceof ComponentExchangeRealization;

    } else if (trace.getSourceElement() instanceof FunctionalExchange) {
      return trace instanceof FunctionalExchangeRealization;

    }

    return true;
  }

}
