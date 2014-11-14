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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public class ExchangeItemAllocationOnPortsCommand extends AbstractFixCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return Messages.PropagateEIOnPorts;
  }

  /**
   * @param modelElement_p
   */
  public ExchangeItemAllocationOnPortsCommand(Collection<ModelElement> selection_p) {
    this(selection_p, new NullProgressMonitor());
  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public ExchangeItemAllocationOnPortsCommand(Collection<ModelElement> selection_p, IProgressMonitor progressMonitor_p) {
    super(selection_p, progressMonitor_p);
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * @param modelElement_p
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Collection<ModelElement> retrieveModelElements(ModelElement modelElement_p) {
    if (modelElement_p instanceof BlockArchitecture) {
      Collection<?> result = FunctionalExt.getAllFunctionalExchanges((BlockArchitecture) modelElement_p);
      return (Collection<ModelElement>) result;

    } else if (modelElement_p instanceof FunctionalExchange) {
      return Collections.singleton(modelElement_p);

    } else if (modelElement_p instanceof FunctionPort) {
      return Collections.singleton(modelElement_p);

    } else if (modelElement_p instanceof AbstractFunction) {
      Collection<?> result = FunctionExt.getAllOwnedFunctionalExchanges((AbstractFunction) modelElement_p);
      return (Collection<ModelElement>) result;
    }

    return Collections.singleton(modelElement_p);
  }

  @Override
  protected void process(ModelElement element_p) {
    Collection<ExchangeItem> flows;

    if (element_p instanceof FunctionInputPort) {
      FunctionInputPort fp = (FunctionInputPort) element_p;
      processPort(fp, getLinkedItems(fp));

    } else if (element_p instanceof FunctionOutputPort) {
      FunctionOutputPort fp = (FunctionOutputPort) element_p;
      processPort(fp, getLinkedItems(fp));

    } else if (element_p instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) element_p;
      flows = ((FunctionalExchange) element_p).getExchangedItems();

      if (fe.getSource() != null) {
        if (fe.getSource() instanceof FunctionOutputPort) {
          processPort((FunctionOutputPort) fe.getSource(), flows);
        }
      }

      if (fe.getTarget() != null) {
        if (fe.getTarget() instanceof FunctionInputPort) {
          processPort((FunctionInputPort) fe.getTarget(), flows);
        }
      }
    }
  }

  /**
   * @param fp_p
   * @return
   */
  private Collection<ExchangeItem> getLinkedItems(ActivityNode fp_p) {
    List<ExchangeItem> items = new ArrayList<ExchangeItem>();

    for (ActivityEdge edge : fp_p.getIncoming()) {
      if (edge instanceof FunctionalExchange) {
        items.addAll(((FunctionalExchange) edge).getExchangedItems());
      }
    }
    for (ActivityEdge edge : fp_p.getOutgoing()) {
      if (edge instanceof FunctionalExchange) {
        items.addAll(((FunctionalExchange) edge).getExchangedItems());
      }
    }

    return items;
  }

  private void processPort(FunctionOutputPort source_p, Collection<ExchangeItem> flows_p) {
    for (ExchangeItem ei : flows_p) {
      if (!source_p.getOutgoingExchangeItems().contains(ei)) {
        source_p.getOutgoingExchangeItems().add(ei);
      }
    }
    List<ExchangeItem> lst = new ArrayList<ExchangeItem>(source_p.getOutgoingExchangeItems());
    for (ExchangeItem ei : lst) {
      if (!flows_p.contains(ei)) {
        source_p.getOutgoingExchangeItems().remove(ei);
      }
    }
  }

  private void processPort(FunctionInputPort source_p, Collection<ExchangeItem> flows_p) {
    for (ExchangeItem ei : flows_p) {
      if (!source_p.getIncomingExchangeItems().contains(ei)) {
        source_p.getIncomingExchangeItems().add(ei);
      }
    }
    List<ExchangeItem> lst = new ArrayList<ExchangeItem>(source_p.getIncomingExchangeItems());
    for (ExchangeItem ei : lst) {
      if (!flows_p.contains(ei)) {
        source_p.getIncomingExchangeItems().remove(ei);
      }
    }
  }
}
