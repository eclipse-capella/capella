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
package org.polarsys.capella.core.refinement.merge.update;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.refinement.merge.utils.MergeNavigator;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Update Merged scenario
 */
public class UpdateMergedScenario {

  protected Scenario _old;
  protected Scenario _new;

  protected MergeNavigator _navigator;
  
  /**
   * Constructor
   * @param sc the initial scenario
   */
  public UpdateMergedScenario(Scenario sc) {

    _old = sc;
    _navigator = new MergeNavigator(sc);

  }

  /**
   * The update Link operation main entry.
   * @param newScenario the {@link Scenario} to update
   */
  public void update(Scenario newScenario) {

    _new = newScenario;

    AbstractTrace trace = hasLinkToNextLayer();

    if (null != trace) {
      performUpdate(trace);
    }

    return;
  }

  /**
   * Perform the update Link operation.
   * @param trace the trace between the old merged scenario and the physical one.
   */
  protected void performUpdate(AbstractTrace trace) {

    Scenario physSc = (Scenario) trace.getSourceElement();

    //
    // First of all, let's update this Main Link
    //
    trace.setTargetElement(_new);

    //
    // Now, let's check other trace
    //

    TraceableElement eltOnOldSc = null;
    TraceableElement eltOnLogLayer = null;
    TraceableElement eltOnNewSc = null;

    List<AbstractTrace> traceToDelete = new ArrayList<AbstractTrace>();
    
    for (AbstractTrace at : physSc.getOwnedTraces()) {

      boolean isEvent = InteractionPackage.Literals.EVENT.isSuperTypeOf(at.getTargetElement().eClass());
      if (!isEvent) { // Event should be ok...
        eltOnOldSc = at.getTargetElement();
        // Let's obtain outgoing MergeLinks
        for (AbstractTrace att : eltOnOldSc.getOutgoingTraces()) {
          // and follow the incoming MergeLinks to the new merged Scenario
          if (att.eClass() == InteractionPackage.Literals.MERGE_LINK) {
            eltOnLogLayer = att.getTargetElement();
            if (null != eltOnLogLayer) {
              for (AbstractTrace attt : eltOnLogLayer.getIncomingTraces()) {
                eltOnNewSc = attt.getSourceElement();
                if (
                    null != eltOnNewSc && 
                    eltOnNewSc.eContainer() == _new
                ) {
                  at.setTargetElement(eltOnNewSc);
                }
              }
            } else {
              // this element does not exist anymore.
              // Thus the Refinement Link between Logical and Physical 
              // must be deleted
              traceToDelete.add(at);
            }
          }
        }
      }
    }

    //Let's remove all deprecated traces between physical and logical layer     
    if ( !traceToDelete.isEmpty() ) {
      physSc.getOwnedTraces().removeAll(traceToDelete);
    }
    
    return;
  }

  /**
   * check is the old Scenario is linked to the next layer and return the corresponding link.
   * @return the {@link AbstractTrace} if true, <code>null</code> otherwise.
   */
  protected AbstractTrace hasLinkToNextLayer() {

    AbstractTrace result = null;

    TraceableElement te = null;
    for (AbstractTrace trace : _old.getIncomingTraces()) {
      te = trace.getSourceElement();
      if (_navigator.isContainedIntoTheNextLayer(te)) {
        result = trace;
        break;
      }
    }

    return result;
  }

}
