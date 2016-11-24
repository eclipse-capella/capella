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
package org.polarsys.capella.core.refinement.processor.actorprojection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.actors.ctx2la.TransformActorsCtx2La;
import org.polarsys.capella.core.projection.actors.la2pa.TransformActorsLa2Pa;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class ActorProjectionProcessor implements IProcessor {

  private ModelElement context = null;
  private NamedElement target = null; // Refinement target (can be either a 'ComponentArchitecture', or a 'LogicalComponent')

  public void execute(IProgressMonitor progressMonitor) throws ProcessorException {

    if (((CapellaLayerCheckingExt.isInContextLayer((CapellaElement) context) || (context instanceof SystemAnalysis))
         && CapellaLayerCheckingExt.isInLogicalLayer(target) || (target instanceof LogicalArchitecture))
        || ((CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) context) || (context instanceof LogicalArchitecture))
            && CapellaLayerCheckingExt.isInPhysicalLayer(target) || (target instanceof PhysicalArchitecture))) {
      Scenario scenario = (Scenario) context;
      List<AbstractActor> listActor = new ArrayList<AbstractActor>();
      for (InteractionFragment interactionFragment : scenario.getOwnedInteractionFragments()) {
        if (interactionFragment instanceof AbstractEnd) {
          AbstractEnd abstractEnd = (AbstractEnd) interactionFragment;

          // Retrieve Actor manipulated
          InstanceRole instRole = abstractEnd.getCovered();
          if (instRole != null) {
            AbstractInstance cpntInst = instRole.getRepresentedInstance();
            if (cpntInst != null) {
              AbstractNamedElement cpnt = cpntInst.getType();
              if (cpnt instanceof AbstractActor && !listActor.contains(cpnt))
                listActor.add((AbstractActor) cpnt);
            }
          }
        }
      }
      performActorProjection(listActor);
    }
  }

  /*
   * Perform the Actor projection 
   * (Interfaces Used/Implemented by the current Actor are also projected)
   */
  private void performActorProjection(List<AbstractActor> listActor) {
    if (target instanceof LogicalArchitecture) {
      for (AbstractActor actor : listActor) {
        TransformActorsCtx2La transfActor = new TransformActorsCtx2La();
        transfActor.setContext(actor);
        transfActor.execute();
      }
    } else if (target instanceof PhysicalArchitecture) {
      for (AbstractActor actor : listActor) {
        TransformActorsLa2Pa transfActor = new TransformActorsLa2Pa();
        transfActor.setContext(actor);
        transfActor.execute();
      }
    }
  }

  public Object getResult() {
    return null;
  }

  public void setContext(ModelElement context) {
    this.context = context;
  }

  public void setContext(List<ModelElement> context) {
    if ((context != null) && (context.size() > 0)) {
      setContext(context.get(0));
    }

  }

  public void setTarget(NamedElement target) {
    if ((target instanceof ComponentArchitecture) || (target instanceof LogicalComponent)) {
      this.target = target;
    }

  }

  public Object getName() {
    return "actor and interface projection"; //$NON-NLS-1$
  }
}
