/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  public static List<Part> getReferencerParts(Component component) {
    List<Part> partSet = new ArrayList<Part>();
    for (EObject referencer : EObjectExt.getReferencers(component, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE)) {
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
  public static AbstractEventOperation getDelegatedOperation(CapellaElement srcElt, CapellaElement tgtElt, AbstractEventOperation currentOp) {
    AbstractEventOperation finalOp = null;
    LAYERSNAME srcLayer = CapellaLayerCheckingExt.getLayersName(srcElt);
    LAYERSNAME tgtLayer = CapellaLayerCheckingExt.getLayersName(tgtElt);

    if (srcLayer.equals(LAYERSNAME.CTX) && tgtLayer.equals(LAYERSNAME.LA)) {
      // 'Ctx To La' Operation refinement case : Search a delegate Operation existing in LA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp, true)) {
        if (CapellaLayerCheckingExt.isInLogicalLayer(traceElt))
          finalOp = traceElt;
      }
    } else if (srcLayer.equals(LAYERSNAME.LA) && tgtLayer.equals(LAYERSNAME.LA)) {
      // 'La To La' Operation refinement case : Search a delegate Operation existing in LA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp, true)) {
        if (CapellaLayerCheckingExt.isInLogicalLayer(traceElt))
          finalOp = traceElt;
      }
    } else if (srcLayer.equals(LAYERSNAME.LA) && tgtLayer.equals(LAYERSNAME.PA)) {
      // 'La To Pa' Operation refinement case : Search a delegate Operation existing in PA Layer
      for (AbstractEventOperation traceElt : getDelegatedAndRefinedOperations(currentOp, true)) {
        if (CapellaLayerCheckingExt.isInPhysicalLayer(traceElt))
          finalOp = traceElt;
      }
    }

    if (finalOp != null)
      return finalOp;

    return currentOp;
  }

  public static List<AbstractEventOperation> getDelegatedAndRefinedOperations(AbstractEventOperation currentOp, boolean reverse) {
    List<AbstractEventOperation> opsDelegated = new ArrayList<AbstractEventOperation>();

    opsDelegated.addAll(getDelegatedOperations(currentOp, reverse));
    opsDelegated.addAll(getRefinedOperations(currentOp, reverse));

    return opsDelegated;
  }

  protected static List<AbstractEventOperation> getDelegatedOperations(AbstractEventOperation currentOp, boolean reverse) {
    return getRelatedElement(currentOp, CapellacommonPackage.Literals.TRANSFO_LINK, reverse);
  }

  protected static List<AbstractEventOperation> getRefinedOperations(AbstractEventOperation currentOp, boolean reverse) {
    return getRelatedElement(currentOp, InteractionPackage.Literals.REFINEMENT_LINK, reverse);
  }

  protected static List<AbstractEventOperation> getRelatedElement(TraceableElement src, EClass linkMC, boolean reverse) {
    List<AbstractEventOperation> opsDelegated = new ArrayList<AbstractEventOperation>();
    for (TraceableElement traceElt : getRelatedElement(src, InformationPackage.Literals.ABSTRACT_EVENT_OPERATION, linkMC, reverse)) {
      opsDelegated.add((AbstractEventOperation) traceElt);
    }
    return opsDelegated;
  }

  protected static List<? extends TraceableElement> getRelatedElement(TraceableElement src, EClass tgtMC, EClass linkMC, boolean reverse) {
    List<TraceableElement> traceableElts = new ArrayList<TraceableElement>();
    for (AbstractTrace abstractTrace : reverse ? src.getIncomingTraces() : src.getOutgoingTraces()) {
      if (abstractTrace.eClass() == linkMC) {
        TraceableElement traceableElt = reverse ? abstractTrace.getSourceElement() : abstractTrace.getTargetElement();
        if (traceableElt != null && (tgtMC.equals(traceableElt.eClass()) || tgtMC.isSuperTypeOf(traceableElt.eClass())))
          if (!traceableElts.contains(traceableElt))
            traceableElts.add(traceableElt);
      }
    }
    return traceableElts;
  }
}
