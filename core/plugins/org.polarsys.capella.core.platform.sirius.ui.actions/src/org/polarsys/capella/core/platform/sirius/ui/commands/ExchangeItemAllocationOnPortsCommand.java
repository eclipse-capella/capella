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

package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.ExchangeItem;

public class ExchangeItemAllocationOnPortsCommand extends AbstractFixCommand {

  // should exchange items that are only present on the port be removed
  public static enum Mode {
    PROPAGATE, SYNCHRONIZE
  }

  private final Mode mode;

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return Messages.PropagateEIOnPorts;
  }

  /**
   * @param modelElement
   */
  public ExchangeItemAllocationOnPortsCommand(Collection<ModelElement> selection) {
    this(selection, new NullProgressMonitor());
  }

  /**
   * @param modelElement
   * @param progressMonitor
   */
  public ExchangeItemAllocationOnPortsCommand(Collection<ModelElement> selection, IProgressMonitor progressMonitor) {
    this(selection, progressMonitor, Mode.SYNCHRONIZE);
  }

  /**
   * @param selection
   * @param progressMonitor
   * @param removeExtraEIs
   */
  public ExchangeItemAllocationOnPortsCommand(Collection<ModelElement> selection, IProgressMonitor progressMonitor,
      Mode mode) {
    super(selection, progressMonitor);
    this.mode = mode;
  }

  /**
   * Returns a list of model elements on which a transition should be applied
   * 
   * @param modelElement
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Collection<ModelElement> retrieveModelElements(ModelElement modelElement) {
    if (modelElement instanceof BlockArchitecture) {
      Collection<?> result = FunctionalExt.getAllFunctionalExchanges((BlockArchitecture) modelElement);
      return (Collection<ModelElement>) result;

    } else if (modelElement instanceof FunctionalExchange) {
      return Collections.singleton(modelElement);

    } else if (modelElement instanceof FunctionPort) {
      return Collections.singleton(modelElement);

    } else if (modelElement instanceof AbstractFunction) {
      Collection<?> result = FunctionExt.getAllOwnedFunctionalExchanges((AbstractFunction) modelElement);
      return (Collection<ModelElement>) result;
    }

    return Collections.singleton(modelElement);
  }

  @Override
  protected boolean process(ModelElement element) {
    boolean portProcessed = false;

    if (element instanceof FunctionInputPort) {
      FunctionInputPort fp = (FunctionInputPort) element;
      portProcessed |= processPort(fp, getLinkedItems(fp));

    } else if (element instanceof FunctionOutputPort) {
      FunctionOutputPort fp = (FunctionOutputPort) element;
      portProcessed |= processPort(fp, getLinkedItems(fp));

    } else if (element instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) element;
      Collection<ExchangeItem> flows = ((FunctionalExchange) element).getExchangedItems();

      if (fe.getSource() instanceof FunctionOutputPort) {
        portProcessed |= processPort((FunctionOutputPort) fe.getSource(), flows);
      }
      if (fe.getTarget() instanceof FunctionInputPort) {
        portProcessed |= processPort((FunctionInputPort) fe.getTarget(), flows);
      }
    }
    return portProcessed;
  }

  /**
   * @param fp
   * @return
   */
  private Collection<ExchangeItem> getLinkedItems(ActivityNode fp) {
    List<ExchangeItem> items = new ArrayList<>();

    for (ActivityEdge edge : fp.getIncoming()) {
      if (edge instanceof FunctionalExchange) {
        items.addAll(((FunctionalExchange) edge).getExchangedItems());
      }
    }
    for (ActivityEdge edge : fp.getOutgoing()) {
      if (edge instanceof FunctionalExchange) {
        items.addAll(((FunctionalExchange) edge).getExchangedItems());
      }
    }

    return items;
  }

  private boolean processPort(FunctionOutputPort source, Collection<ExchangeItem> flows) {
    boolean portProcessed = false;
    for (ExchangeItem ei : flows) {
      if (!source.getOutgoingExchangeItems().contains(ei)) {
        source.getOutgoingExchangeItems().add(ei);
        portProcessed = true;
      }
    }
    if (mode == Mode.SYNCHRONIZE) {
      List<ExchangeItem> lst = new ArrayList<>(source.getOutgoingExchangeItems());
      for (ExchangeItem ei : lst) {
        if (!flows.contains(ei)) {
          source.getOutgoingExchangeItems().remove(ei);
        }
      }
    }
    return portProcessed;
  }

  private boolean processPort(FunctionInputPort source, Collection<ExchangeItem> flows) {
    boolean portProcessed = false;
    for (ExchangeItem ei : flows) {
      if (!source.getIncomingExchangeItems().contains(ei)) {
        source.getIncomingExchangeItems().add(ei);
        portProcessed = true;
      }
    }
    if (mode == Mode.SYNCHRONIZE) {
      List<ExchangeItem> lst = new ArrayList<>(source.getIncomingExchangeItems());
      for (ExchangeItem ei : lst) {
        if (!flows.contains(ei)) {
          source.getIncomingExchangeItems().remove(ei);
        }
      }
    }
    return portProcessed;
  }
}
