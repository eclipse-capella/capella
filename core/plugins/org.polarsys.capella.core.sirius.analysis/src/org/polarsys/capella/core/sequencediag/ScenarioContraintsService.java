/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sequencediag;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvedElement;
import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OperationalCapability;

/**
 * This class checks that the creation of a message doesn't violate one or more constraints.
 */
public class ScenarioContraintsService {

  public static EObject traceScenario(EObject object, EObject o1) {
    return object;
  }

  public static EObject traceScenario2(EObject object, EObject o1, EObject o2) {
    EventEnd e1 = (EventEnd) o1;
    EventEnd e2 = (EventEnd) o2;
    if (object instanceof Execution) {
      Execution e = (Execution) object;
      System.out.print("moving execution " + e.getName()); //$NON-NLS-1$
    } else if (object instanceof SequenceMessage) {
      SequenceMessage msg = (SequenceMessage) object;
      System.out.print("moving message " + msg.getName()); //$NON-NLS-1$
    } else if (object instanceof InteractionState) {
      InteractionState is = (InteractionState) object;
      System.out.print("moving interaction state " + is.getName()); //$NON-NLS-1$
    }
    System.out.print(" after "); //$NON-NLS-1$
    display(e1);
    System.out.print(" and "); //$NON-NLS-1$
    display(e2);
    System.out.println();
    return object;
  }

  private static void display(EventEnd e) {
    if (e == null)
      System.out.print("*NULL*"); //$NON-NLS-1$
    if (e instanceof SingleEventEnd) {
      displaySingle((SingleEventEnd) e);
    }
    if (e instanceof CompoundEventEnd) {
      displayCompound((CompoundEventEnd) e);
    }
  }

  private static void displaySingle(SingleEventEnd see) {
    System.out.print(" (" + see.eClass().getName() + ") " + ((InteractionFragment) see.getSemanticEnd()).getName()); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private static void displayCompound(CompoundEventEnd cee) {
    System.out.print(" (" + cee.eClass().getName() + ") " + ((InteractionFragment) cee.getSemanticEnd()).getName()); //$NON-NLS-1$ //$NON-NLS-2$
    System.out.print(" [ "); //$NON-NLS-1$
    for (EventEnd ee : cee.getEventEnds()) {
      display(ee);
    }
    System.out.print(" ] "); //$NON-NLS-1$
  }

  /**
   * CHeck if the component (actor, system, logical or physicalComponent) is involved into the capability containing the
   * scenario taken into parameters used in common.odesign
   */
  public static Scenario ensureCapabilityInvolvment(Scenario scenario, InvolvedElement component) {
    AbstractCapability capability = (AbstractCapability) scenario.eContainer();
    for (Involvement involv : capability.getInvolvedInvolvements()) {
      if (involv.getInvolved() == component)
        return scenario;
    }

    // if we are here, the involvement wasn't found, so we will have to create it.
    Involvement result = null;
    if (component instanceof SystemComponent) { // System
      result = CtxFactory.eINSTANCE.createCapabilityInvolvement();
      ((Capability) capability).getOwnedCapabilityInvolvements().add((CapabilityInvolvement) result);
    } else if (component instanceof Entity) {
      result = OaFactory.eINSTANCE.createEntityOperationalCapabilityInvolvement();
      ((OperationalCapability) capability).getOwnedEntityOperationalCapabilityInvolvements().add((EntityOperationalCapabilityInvolvement) result);
    } else if (component instanceof AbstractFunction) {
      result = InteractionFactory.eINSTANCE.createAbstractFunctionAbstractCapabilityInvolvement();
      capability.getOwnedAbstractFunctionAbstractCapabilityInvolvements().add((AbstractFunctionAbstractCapabilityInvolvement) result);
    } else if (component instanceof CapabilityRealizationInvolvedElement) {
      result = CapellacommonFactory.eINSTANCE.createCapabilityRealizationInvolvement();
      ((CapabilityRealization) capability).getOwnedCapabilityRealizationInvolvements().add((CapabilityRealizationInvolvement) result);
    }
    if (result != null) {
      result.setInvolved(component);
    }
    return scenario;
  }

  public void reorderInstanceRole(EObject context, EObject irToMove, EObject predecessor) {
    InstanceRole ir = (InstanceRole) irToMove;
    InstanceRole pred = (InstanceRole) predecessor;
    Scenario scenario = (Scenario) ir.eContainer();

    scenario.getOwnedInstanceRoles().remove(ir);
    if (predecessor == null) {
      scenario.getOwnedInstanceRoles().add(0, ir);
    } else {
      int pos = scenario.getOwnedInstanceRoles().indexOf(pred);
      scenario.getOwnedInstanceRoles().add(pos + 1, ir);
    }
  }
}
