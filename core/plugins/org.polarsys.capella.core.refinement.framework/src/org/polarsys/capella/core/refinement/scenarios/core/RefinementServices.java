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
package org.polarsys.capella.core.refinement.scenarios.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt.LAYERSNAME;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class RefinementServices {

  /**
   * 
   */
  public static List<Part> getReferencerParts(Component component_p) {
    List<Part> partSet = new ArrayList<Part>();
    for (EObject referencer : EObjectExt.getReferencers(component_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
      if (referencer instanceof Part) {
        partSet.add((Part) referencer);
      }
    }
    return partSet;
  }

  /**
   * @returns the current operation if it's not delegated, delegated operation otherwise
   *          (according to the refinement direction: CTX to La / LA to LA / LA to PA)
   */
  public static AbstractEventOperation getDelegatedOperation(CapellaElement srcElt_p, CapellaElement tgtElt_p, AbstractEventOperation currentOp_p) {
    AbstractEventOperation finalOp = null;
    LAYERSNAME srcLayer = CapellaLayerCheckingExt.getLayersName(srcElt_p);
    LAYERSNAME tgtLayer = CapellaLayerCheckingExt.getLayersName(tgtElt_p);

    if (srcLayer.equals(LAYERSNAME.CTX) && tgtLayer.equals(LAYERSNAME.LA)) {
      // 'Ctx To La' Operation refinement case : Search a delegate Operation existing in LA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp_p, true)) {
        if (CapellaLayerCheckingExt.isInLogicalLayer(traceElt))
          finalOp = traceElt;
      }
    } else if (srcLayer.equals(LAYERSNAME.LA) && tgtLayer.equals(LAYERSNAME.LA)) {
      // 'La To La' Operation refinement case : Search a delegate Operation existing in LA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp_p, true)) {
        if (CapellaLayerCheckingExt.isInLogicalLayer(traceElt))
          finalOp = traceElt;
      }
    } else if (srcLayer.equals(LAYERSNAME.LA) && tgtLayer.equals(LAYERSNAME.PA)) {
      // 'La To Pa' Operation refinement case : Search a delegate Operation existing in PA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp_p, true)) {
        if (CapellaLayerCheckingExt.isInPhysicalLayer(traceElt))
          finalOp = traceElt;
      }
    }

    if (finalOp != null)
      return finalOp;

    return currentOp_p;
  }

  public static List<AbstractEventOperation> getDelegatedAndRefinedOperations(AbstractEventOperation currentOp_p, boolean reverse_p) {
    List<AbstractEventOperation> opsDelegated = new ArrayList<AbstractEventOperation>();

    opsDelegated.addAll(getDelegatedOperations(currentOp_p, reverse_p));
    opsDelegated.addAll(getRefinedOperations(currentOp_p, reverse_p));

    return opsDelegated;
  }

  protected static List<AbstractEventOperation> getDelegatedOperations(AbstractEventOperation currentOp_p, boolean reverse_p) {
    return getRelatedElement(currentOp_p, CapellacommonPackage.Literals.TRANSFO_LINK, reverse_p);
  }

  protected static List<AbstractEventOperation> getRefinedOperations(AbstractEventOperation currentOp_p, boolean reverse_p) {
    return getRelatedElement(currentOp_p, InteractionPackage.Literals.REFINEMENT_LINK, reverse_p);
  }

  protected static List<AbstractEventOperation> getRelatedElement(TraceableElement src_p, EClass linkMC_p, boolean reverse_p) {
    List<AbstractEventOperation> opsDelegated = new ArrayList<AbstractEventOperation>();
    for (TraceableElement traceElt : getRelatedElement(src_p, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION, linkMC_p, reverse_p)) {
      opsDelegated.add((AbstractEventOperation) traceElt);
    }
    return opsDelegated;
  }

  protected static List<? extends TraceableElement> getRelatedElement(TraceableElement src_p, EClass tgtMC_p, EClass linkMC_p, boolean reverse_p) {
    List<TraceableElement> traceableElts = new ArrayList<TraceableElement>();
    for (AbstractTrace abstractTrace : reverse_p ? src_p.getIncomingTraces() : src_p.getOutgoingTraces()) {
      if (abstractTrace.eClass() == linkMC_p) {
        TraceableElement traceableElt = reverse_p ? abstractTrace.getSourceElement() : abstractTrace.getTargetElement();
        if (traceableElt != null && (tgtMC_p.equals(traceableElt.eClass()) || tgtMC_p.isSuperTypeOf(traceableElt.eClass())))
          if (!traceableElts.contains(traceableElt))
            traceableElts.add(traceableElt);
      }
    }
    return traceableElts;
  }
}
