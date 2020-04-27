/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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

/**
 *
 */
public class CapellaTraceabilityFormatDataKey extends CapellaDecoratorFormatDataKey {

  public CapellaTraceabilityFormatDataKey(AbstractCapellaFormatDataKey key, EObject semantic) {
    super(key);

    _semantic = semantic;

    if (parent instanceof CapellaDecoratorFormatDataKey) {
      for (Object decoration : ((CapellaDecoratorFormatDataKey) parent).getDecorations()) {
        if (decoration instanceof EObject) {
          EObject traced = retrieveLinkedEObject(((EObject) decoration));
          //By using this, we remove also mapping association
          if (traced != null) {
            addDecoration(traced);
          }
        }
      }
    }

  }

  @Override
  public EObject getSemantic() {
    return _semantic;
  }

  /**
   * Look for an {@link EObject} linked to given one by a
   * traceability/refinement link.
   */
  protected EObject retrieveLinkedEObject(EObject semantic) {
    EObject source = null;
    if ((semantic instanceof EClass) || (semantic instanceof ColorDescription)) {
      return semantic;

    } else if (semantic instanceof TraceableElement) {
      TraceableElement sourceOfTrace = (TraceableElement) semantic;

      for (AbstractTrace trace : sourceOfTrace.getOutgoingTraces()) {
        if (isValidTrace(trace)) {
          source = trace.getTargetElement();
        }
      }
    }
    if ((source == null) && (semantic instanceof Part)) {
      Part part = (Part) semantic;
      EObject obj = retrieveLinkedEObject(part.getAbstractType());
      if (obj instanceof Component) {
        Component linkedComponent = (Component) obj;
        for (Object ate : linkedComponent.getAbstractTypedElements()) {
          if (ate instanceof Part) {
            return (Part) ate;
          }
        }
      }
    }

    return source;
  }

  /**
   * @param trace
   * @return
   */
  private boolean isValidTrace(AbstractTrace trace) {
    if ((trace.getSourceElement() == null) || (trace.getTargetElement() == null)) {
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
