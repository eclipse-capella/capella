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
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class PortRealizationPropagationCommand extends AbstractFixCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return Messages.PropagatePortRealizations;
  }

  /**
   * @param modelElement_p
   */
  public PortRealizationPropagationCommand(Collection<ModelElement> selection_p) {
    this(selection_p, new NullProgressMonitor());
  }

  /**
   * @param modelElement_p
   * @param progressMonitor_p
   */
  public PortRealizationPropagationCommand(Collection<ModelElement> selection_p, IProgressMonitor progressMonitor_p) {
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
    } else if (modelElement_p instanceof AbstractFunction) {
      Collection<?> result = FunctionExt.getAllOwnedFunctionalExchanges((AbstractFunction) modelElement_p);
      return (Collection<ModelElement>) result;
    } else if (modelElement_p instanceof ComponentExchange) {
      return Collections.singleton(modelElement_p);
    } else if (modelElement_p instanceof Component) {
      Collection<?> result = ComponentExt.getAllOwnedComponentExchanges((Component) modelElement_p);
      return (Collection<ModelElement>) result;
    } else if (modelElement_p instanceof Part) {
      return retrieveModelElements(((Part) modelElement_p).getAbstractType());
    }
    return Collections.singleton(modelElement_p);
  }

  @Override
  protected void process(ModelElement element_p) {
    if (element_p instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) element_p;
      List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(fe, FaPackage.Literals.FUNCTIONAL_EXCHANGE);

      ActivityNode sourceCurrent = fe.getSource();
      if ((sourceCurrent != null) && (sourceCurrent instanceof Port)) {
        for (CapellaElement previous : previousPhaseElements) {
          FunctionalExchange previousExchange = (FunctionalExchange) previous;
          ActivityNode sourcePrevious = previousExchange.getSource();
          attachIfNeeded((Port) sourceCurrent, sourcePrevious);
        }
        detachIfUnused((Port) sourceCurrent);
      }

      ActivityNode targetCurrent = fe.getTarget();
      if ((targetCurrent != null) && (targetCurrent instanceof Port)) {
        for (CapellaElement previous : previousPhaseElements) {
          FunctionalExchange previousExchange = (FunctionalExchange) previous;
          ActivityNode targetPrevious = previousExchange.getTarget();
          attachIfNeeded((Port) targetCurrent, targetPrevious);
        }
        detachIfUnused((Port) targetCurrent);
      }
    } else if (element_p instanceof ComponentExchange) {
      ComponentExchange ce = (ComponentExchange) element_p;
      List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(ce, FaPackage.Literals.COMPONENT_EXCHANGE);

      EObject sourceCurrent = ComponentExchangeExt.getSourcePort(ce);
      if ((sourceCurrent != null) && (sourceCurrent instanceof Port)) {
        for (CapellaElement previous : previousPhaseElements) {
          ComponentExchange previousExchange = (ComponentExchange) previous;
          Port sourcePrevious = ComponentExchangeExt.getSourcePort(previousExchange);
          attachIfNeeded((Port) sourceCurrent, sourcePrevious);
        }
        detachIfUnused((Port) sourceCurrent);
      }

      EObject targetCurrent = ComponentExchangeExt.getTargetPort(ce);
      if ((targetCurrent != null) && (targetCurrent instanceof Port)) {
        for (CapellaElement previous : previousPhaseElements) {
          ComponentExchange previousExchange = (ComponentExchange) previous;
          Port targetPrevious = ComponentExchangeExt.getTargetPort(previousExchange);
          attachIfNeeded((Port) targetCurrent, targetPrevious);
        }
        detachIfUnused((Port) targetCurrent);
      }
    }
  }

  /**
   * @param current_p
   * @param previous_p
   */
  private void attachIfNeeded(Port current_p, EObject previous_p) {
    if ((previous_p != null) && (previous_p instanceof Port)) {
      if (!RefinementLinkExt.isLinkedTo(current_p, (TraceableElement) previous_p)) {
        attach(current_p, (TraceableElement) previous_p);
      }
    }
  }

  /**
   * @param current_p
   * @param sourcePrevious_p
   */
  private void attach(Port current_p, TraceableElement previous_p) {
    PortRealization realization = InformationFactory.eINSTANCE.createPortRealization();
    realization.setSourceElement(current_p);
    realization.setTargetElement(previous_p);
    current_p.getOwnedPortRealizations().add(realization);
  }

  /**
   * @param current_p
   */
  private void detachIfUnused(Port current_p) {
    if (current_p != null) {
      List<CapellaElement> realizedExch = new ArrayList<CapellaElement>();
      if (current_p instanceof FunctionOutputPort) {
        for (ActivityEdge edge : ((FunctionOutputPort) current_p).getOutgoing()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements((CapellaElement) edge, FaPackage.Literals.FUNCTIONAL_EXCHANGE));
        }
      } else if (current_p instanceof FunctionInputPort) {
        for (ActivityEdge edge : ((FunctionInputPort) current_p).getIncoming()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements((CapellaElement) edge, FaPackage.Literals.FUNCTIONAL_EXCHANGE));
        }
      } else if (current_p instanceof ComponentPort) {
        for (ComponentExchange exch : ((ComponentPort) current_p).getComponentExchanges()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements(exch, FaPackage.Literals.COMPONENT_EXCHANGE));
        }
      }

      for (PortRealization rlz : current_p.getOutgoingPortRealizations()) {
        Port port = rlz.getRealizedPort();
        boolean detachMe = true;
        if (port instanceof FunctionOutputPort) {
          for (ActivityEdge edge : ((FunctionOutputPort) port).getOutgoing()) {
            if (realizedExch.contains(edge)) {
              detachMe = false;
            }
          }
        } else if (port instanceof FunctionInputPort) {
          for (ActivityEdge edge : ((FunctionInputPort) port).getIncoming()) {
            if (realizedExch.contains(edge)) {
              detachMe = false;
            }
          }
        } else if (port instanceof ComponentPort) {
          for (ComponentExchange exch : ((ComponentPort) port).getComponentExchanges()) {
            if (realizedExch.contains(exch)) {
              detachMe = false;
            }
          }
        }
        if (detachMe) {
          detach(current_p, port);
        }
      }
    }
  }

  /**
   * @param currentPort_p
   * @param realizedPort_p
   */
  private void detach(Port currentPort_p, Port realizedPort_p) {
    if ((null != currentPort_p) && (null != realizedPort_p)) {
      PortRealization rlzToDelete = null;
      for (PortRealization rlz : currentPort_p.getOutgoingPortRealizations()) {
        if (realizedPort_p.equals(rlz.getRealizedPort())) {
          rlzToDelete = rlz;
        }
      }
      if (null != rlzToDelete) {
        currentPort_p.getOwnedPortRealizations().remove(rlzToDelete);
      }
    }
  }
}
