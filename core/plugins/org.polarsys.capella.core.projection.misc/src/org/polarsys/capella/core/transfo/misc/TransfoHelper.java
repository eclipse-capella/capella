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
package org.polarsys.capella.core.transfo.misc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class TransfoHelper {

  public static TraceableElement getReconciliation(TraceableElement src_p, EClass tgtMC_p, EClass linkMC_p) {
    for (AbstractTrace abstractTrace : src_p.getIncomingTraces()) {
      if (abstractTrace.eClass() == linkMC_p) {
        TraceableElement traceableElt = abstractTrace.getSourceElement();
        if (((traceableElt != null) && (traceableElt.eClass() == tgtMC_p)) || ((traceableElt != null) && tgtMC_p.isSuperTypeOf(traceableElt.eClass()))) {
          return traceableElt;
        }
      }
    }
    return null;
  }

  public static List<TraceableElement> getAllReconciliation(TraceableElement src_p, EClass tgtMC_p, EClass linkMC_p) {
    List<TraceableElement> listTraceableElt = new ArrayList<TraceableElement>();
    for (AbstractTrace abstractTrace : src_p.getIncomingTraces()) {
      if (abstractTrace.eClass() == linkMC_p) {
        TraceableElement traceableElt = abstractTrace.getSourceElement();

        if ((traceableElt != null) && ((traceableElt.eClass() == tgtMC_p) || tgtMC_p.isSuperTypeOf(traceableElt.eClass()))) {
          if (!listTraceableElt.contains(traceableElt)) {
            listTraceableElt.add(traceableElt);
          }
        }
      }
    }
    return listTraceableElt;
  }

}
