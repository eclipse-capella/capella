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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
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
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;

public class PortRealizationPropagationCommand extends AbstractFixCommand {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return Messages.PropagatePortRealizations;
  }

  /**
   * @param modelElement
   */
  public PortRealizationPropagationCommand(Collection<ModelElement> selection) {
    this(selection, new NullProgressMonitor());
  }

  /**
   * @param modelElement
   * @param progressMonitor
   */
  public PortRealizationPropagationCommand(Collection<ModelElement> selection, IProgressMonitor progressMonitor) {
    super(selection, progressMonitor);
  }

  /**
   * Returns a list of model elements on which a transition should be applied
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
    } else if (modelElement instanceof AbstractFunction) {
      Collection<?> result = FunctionExt.getAllOwnedFunctionalExchanges((AbstractFunction) modelElement);
      return (Collection<ModelElement>) result;
    } else if (modelElement instanceof ComponentExchange) {
      return Collections.singleton(modelElement);
    } else if (modelElement instanceof Component) {
      Collection<?> result = ComponentExt.getAllOwnedComponentExchanges((Component) modelElement);
      return (Collection<ModelElement>) result;
    } else if (modelElement instanceof Part) {
      return retrieveModelElements(((Part) modelElement).getAbstractType());
    }
    return Collections.singleton(modelElement);
  }

  @Override
  protected boolean process(ModelElement element) {
    boolean attachementAdded = false;
    if (element instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) element;
      List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(fe, FaPackage.Literals.FUNCTIONAL_EXCHANGE);
      ActivityNode sourceCurrent = fe.getSource();
      if (sourceCurrent instanceof Port) {
        for (CapellaElement previous : previousPhaseElements) {
          FunctionalExchange previousExchange = (FunctionalExchange) previous;
          ActivityNode sourcePrevious = previousExchange.getSource();
          attachementAdded |= attachIfNeeded((Port) sourceCurrent, sourcePrevious);
        }
        detachIfUnused((Port) sourceCurrent);
      }

      ActivityNode targetCurrent = fe.getTarget();
      if (targetCurrent instanceof Port) {
        for (CapellaElement previous : previousPhaseElements) {
          FunctionalExchange previousExchange = (FunctionalExchange) previous;
          ActivityNode targetPrevious = previousExchange.getTarget();
          attachementAdded |= attachIfNeeded((Port) targetCurrent, targetPrevious);
        }
        detachIfUnused((Port) targetCurrent);
      }
    } else if (element instanceof ComponentExchange) {
      ComponentExchange ce = (ComponentExchange) element;
      List<CapellaElement> previousPhaseElements = RefinementLinkExt.getRelatedTargetElements(ce, FaPackage.Literals.COMPONENT_EXCHANGE);

      EObject sourceCurrent = ComponentExchangeExt.getSourcePort(ce);
      if (sourceCurrent instanceof Port) {
        for (CapellaElement previous : previousPhaseElements) {
          ComponentExchange previousExchange = (ComponentExchange) previous;
          Port sourcePrevious = ComponentExchangeExt.getSourcePort(previousExchange);
          attachementAdded |= attachIfNeeded((Port) sourceCurrent, sourcePrevious);
        }
        detachIfUnused((Port) sourceCurrent);
      }

      EObject targetCurrent = ComponentExchangeExt.getTargetPort(ce);
      if (targetCurrent instanceof Port) {
        for (CapellaElement previous : previousPhaseElements) {
          ComponentExchange previousExchange = (ComponentExchange) previous;
          Port targetPrevious = ComponentExchangeExt.getTargetPort(previousExchange);
          attachementAdded |= attachIfNeeded((Port) targetCurrent, targetPrevious);
        }
        detachIfUnused((Port) targetCurrent);
      }
    }
    return attachementAdded;
  }

  /**
   * @param current
   * @param previous
   */
  private boolean attachIfNeeded(Port current, EObject previous) {
    if (previous instanceof Port) {
      if (!RefinementLinkExt.isLinkedTo(current, (TraceableElement) previous)) {
        attach(current, (TraceableElement) previous);
        return true;
      }
    }
    return false;
  }

  /**
   * @param current
   * @param sourcePrevious
   */
  private void attach(Port current, TraceableElement previous) {
    PortRealization realization = InformationFactory.eINSTANCE.createPortRealization();
    realization.setSourceElement(current);
    realization.setTargetElement(previous);
    current.getOwnedPortRealizations().add(realization);
    String message = "Port realization " + realization.getLabel() + " has been propagated from source element "
        + current.getLabel() + " to target element " + previous.getLabel();
    EmbeddedMessage eMessage = new EmbeddedMessage(message, logger.getName(),
        Arrays.asList(realization, current, previous));
    logger.info(eMessage);
  }

  /**
   * @param current
   */
  private void detachIfUnused(Port current) {
    if (current != null) {
      List<CapellaElement> realizedExch = new ArrayList<>();
      if (current instanceof FunctionOutputPort) {
        for (ActivityEdge edge : ((FunctionOutputPort) current).getOutgoing()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements((CapellaElement) edge, FaPackage.Literals.FUNCTIONAL_EXCHANGE));
        }
      } else if (current instanceof FunctionInputPort) {
        for (ActivityEdge edge : ((FunctionInputPort) current).getIncoming()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements((CapellaElement) edge, FaPackage.Literals.FUNCTIONAL_EXCHANGE));
        }
      } else if (current instanceof ComponentPort) {
        for (ComponentExchange exch : ((ComponentPort) current).getComponentExchanges()) {
          realizedExch.addAll(RefinementLinkExt.getRelatedTargetElements(exch, FaPackage.Literals.COMPONENT_EXCHANGE));
        }
      }

      for (PortRealization rlz : current.getOutgoingPortRealizations()) {
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
          detach(current, port);
        }
      }
    }
  }

  /**
   * @param currentPort
   * @param realizedPort
   */
  private void detach(Port currentPort, Port realizedPort) {
    if ((null != currentPort) && (null != realizedPort)) {
      PortRealization rlzToDelete = null;
      for (PortRealization rlz : currentPort.getOutgoingPortRealizations()) {
        if (realizedPort.equals(rlz.getRealizedPort())) {
          rlzToDelete = rlz;
        }
      }
      if (null != rlzToDelete) {
        currentPort.getOwnedPortRealizations().remove(rlzToDelete);
      }
    }
  }
}
