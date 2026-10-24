/*******************************************************************************
 * Copyright (c) 2014, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.data.fa.validation.ce;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * A FE between different components/actor should be allocated to a CE.
 */
public class FunctionalExchangeAllocatedToCe extends AbstractValidationRule {

  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) ctx.getTarget();

      if (fe.getAllocatingComponentExchanges().size() == 0) {
        // verify that the CE is between components/actor
        if (fe.getSource() instanceof FunctionOutputPort && fe.getTarget() instanceof FunctionInputPort) {
          final FunctionOutputPort outPort = (FunctionOutputPort) fe.getSource();
          final FunctionInputPort inPort = (FunctionInputPort) fe.getTarget();

          if (inPort.eContainer() instanceof AbstractFunction && outPort.eContainer() instanceof AbstractFunction) {
            final AbstractFunction lfSource = (AbstractFunction) outPort.eContainer();
            final AbstractFunction lfTarget = (AbstractFunction) inPort.eContainer();

            final Component source = this.findAllocatingComponentOrActor(lfSource);
            final Component target = this.findAllocatingComponentOrActor(lfTarget);

            final boolean sourceIsUserActor = this.isUserActor(source);
            final boolean targetIsUserActor = this.isUserActor(target);

            if (!sourceIsUserActor && !targetIsUserActor && source != target) {
              return ctx.createFailureStatus(new Object[] { fe.getName() });
            }
          }
        }
      }

    }

    return ctx.createSuccessStatus();
  }

  private boolean isUserActor(Component comp) {
    if (comp.isActor() && (comp.getName() != null) && (comp.getName().indexOf("User") != -1)) {
      return true;
    }

    return false;
  }

  private Component findAllocatingComponentOrActor(final AbstractFunction f) {
    // ////////////
    if (f instanceof SystemFunction) {
      final List<SystemComponent> listOfAllocatingSysComps = new ArrayList<SystemComponent>(
          ((SystemFunction) f).getAllocatingSystemComponents());
      listOfAllocatingSysComps.removeIf(x -> x.isActor());

      if (listOfAllocatingSysComps.size() == 1)
        return listOfAllocatingSysComps.get(0);
    }
    if (f instanceof LogicalFunction) {
      final List<LogicalComponent> listOfAllocatingLogComps = new ArrayList<LogicalComponent>(
          ((LogicalFunction) f).getAllocatingLogicalComponents());
      listOfAllocatingLogComps.removeIf(x -> x.isActor());

      if (listOfAllocatingLogComps.size() == 1)
        return listOfAllocatingLogComps.get(0);
    }
    if (f instanceof PhysicalFunction) {
      final List<PhysicalComponent> listOfAllocatingPhysComps = new ArrayList<PhysicalComponent>(
          ((PhysicalFunction) f).getAllocatingPhysicalComponents());
      listOfAllocatingPhysComps.removeIf(x -> x.isActor());

      if (listOfAllocatingPhysComps.size() == 1)
        return listOfAllocatingPhysComps.get(0);
    }

    // ////////////
    if (f instanceof SystemFunction) {
      final List<SystemComponent> listOfAllocatingSysComps = new ArrayList<SystemComponent>(
          ((SystemFunction) f).getAllocatingSystemComponents());
      listOfAllocatingSysComps.removeIf(x -> !(x.isActor()));

      if (listOfAllocatingSysComps.size() == 1)
        return listOfAllocatingSysComps.get(0);
    }
    if (f instanceof LogicalFunction) {
      final List<LogicalComponent> listOfAllocatingLogComps = new ArrayList<LogicalComponent>(
          ((LogicalFunction) f).getAllocatingLogicalComponents());
      listOfAllocatingLogComps.removeIf(x -> !(x.isActor()));

      if (listOfAllocatingLogComps.size() == 1)
        return listOfAllocatingLogComps.get(0);
    }
    if (f instanceof PhysicalFunction) {
      final List<PhysicalComponent> listOfAllocatingPhysComps = new ArrayList<PhysicalComponent>(
          ((PhysicalFunction) f).getAllocatingPhysicalComponents());
      listOfAllocatingPhysComps.removeIf(x -> !(x.isActor()));

      if (listOfAllocatingPhysComps.size() == 1)
        return listOfAllocatingPhysComps.get(0);
    }

    return null;
  }

}
