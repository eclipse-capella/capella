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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

/**
 */
public class EIAllocationModelHelpers {

  /**
   * Get list of transitioned Elements
   */
  public static List<ModelElement> getTransitionedElements(ModelElement element_p) {
    return getTransitionedElements(Collections.singletonList(element_p));
  }

  /**
   * Get list of transitioned Elements
   */
  public static List<ModelElement> getTransitionedElements(List<ModelElement> elements_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    for (ModelElement elt : elements_p) {
      if (elt instanceof AbstractFunction) {
        for (AbstractTrace trace : ((AbstractFunction) elt).getIncomingTraces()) {
          if (trace instanceof FunctionRealization) {
            AbstractFunction fct = ((FunctionRealization) trace).getAllocatingFunction();
            if (null != fct) {
              result.add(fct);
            }
          }
        }
      } else if (elt instanceof FunctionPort) {
        for (AbstractTrace trace : ((FunctionPort) elt).getIncomingTraces()) {
          if (trace instanceof PortRealization) {
            Port port = ((PortRealization) trace).getRealizingPort();
            if (null != port) {
              result.add(port);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * Get list of transitioner Elements
   */
  public static List<ModelElement> getTransitionerElements(ModelElement element_p) {
    return getTransitionerElements(Collections.singletonList(element_p));
  }

  /**
   * Get list of transitioner Elements
   */
  public static List<ModelElement> getTransitionerElements(List<ModelElement> elements_p) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    for (ModelElement elt : elements_p) {
      if (elt instanceof AbstractFunction) {
        for (AbstractTrace trace : ((AbstractFunction) elt).getOutgoingTraces()) {
          if (trace instanceof FunctionRealization) {
            AbstractFunction fct = ((FunctionRealization) trace).getAllocatedFunction();
            if (null != fct) {
              result.add(fct);
            }
          }
        }
      } else if (elt instanceof FunctionPort) {
        for (AbstractTrace trace : ((FunctionPort) elt).getOutgoingTraces()) {
          if (trace instanceof PortRealization) {
            Port port = ((PortRealization) trace).getRealizedPort();
            if (null != port) {
              result.add(port);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * @param exchangeItem_p
   */
  public static List<EObject> getOwners(ExchangeItem exchangeItem_p) {
    return EObjectExt.getReferencers(exchangeItem_p, Arrays.asList(
      new EReference[] { FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS, FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS }));
  }

  /**
   * @param selection_p
   */
  public static void handleDeletion(Collection<?> selection_p) {
    CapellaDeleteCommand cmd = new CapellaDeleteCommand(MDEAdapterFactory.getExecutionManager(), filterFunctionsAndPorts(selection_p), false, false, false);
    if (cmd.canExecute()) {
      cmd.execute();
    }
  }

  /**
   * @param selection_p
   */
  public static Collection<?> filterFunctionsAndPorts(Collection<?> selection_p) {
    Set<EObject> result = new HashSet<EObject>();
    for (Object obj : selection_p) {
      if ((obj instanceof AbstractFunction) || (obj instanceof FunctionPort)) {
        result.add((EObject) obj);
      }
    }
    return result;
  }

  /**
   * @param sepFunctionPort_p
   * @param tepFunctionPort_p
   * @param exchangeItem_p
   */
  public static void handleAllocation(FunctionPort sepFunctionPort_p, FunctionPort tepFunctionPort_p, ExchangeItem exchangeItem_p) {
    if ((null != tepFunctionPort_p) && (null != exchangeItem_p)) {
      if (tepFunctionPort_p instanceof FunctionInputPort) {
        List<ExchangeItem> incomingEI = ((FunctionInputPort) tepFunctionPort_p).getIncomingExchangeItems();
        if (!incomingEI.contains(exchangeItem_p)) {
          incomingEI.add(exchangeItem_p);
        }
      } else if (tepFunctionPort_p instanceof FunctionOutputPort) {
        List<ExchangeItem> outgoingEI = ((FunctionOutputPort) tepFunctionPort_p).getOutgoingExchangeItems();
        if (!outgoingEI.contains(exchangeItem_p)) {
          outgoingEI.add(exchangeItem_p);
        }
      }
      handleAllocation(tepFunctionPort_p, sepFunctionPort_p);
    }
  }

  /**
   * @param source_p
   * @param target_p
   */
  public static void handleAllocation(AbstractFunction source_p, AbstractFunction target_p) {
    boolean alreadyRealized = false;
    for (FunctionRealization rlz : source_p.getOutFunctionRealizations()) {
      AbstractFunction fct = rlz.getAllocatedFunction();
      if (target_p.equals(fct)) {
        alreadyRealized = true;
        break;
      }
    }
    if (!alreadyRealized) {
      FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
      rlz.setSourceElement(source_p);
      rlz.setTargetElement(target_p);
      source_p.getOwnedFunctionRealizations().add(rlz);
    }
  }

  /**
   * @param source_p
   * @param target_p
   */
  public static void handleAllocation(FunctionPort source_p, FunctionPort target_p) {
    boolean alreadyRealized = false;
    if (((source_p instanceof FunctionInputPort) && (target_p instanceof FunctionInputPort))
      || ((source_p instanceof FunctionOutputPort) && (target_p instanceof FunctionOutputPort)))
    {
      for (PortRealization rlz : source_p.getOutgoingPortRealizations()) {
        Port port = rlz.getRealizedPort();
        if (target_p.equals(port)) {
          alreadyRealized = true;
          break;
        }
      }
      if (!alreadyRealized) {
        PortRealization rlz = InformationFactory.eINSTANCE.createPortRealization();
        rlz.setSourceElement(source_p);
        rlz.setTargetElement(target_p);
        source_p.getOwnedPortRealizations().add(rlz);
      }
    } else {
      //
    }
  }

  /**
   * @param elements_p
   * @return
   */
  public static boolean isSameType(List<EObject> elements_p) {
    if ((null != elements_p) && !elements_p.isEmpty()) {
      EClass cls = elements_p.get(0).eClass();
      for (EObject elt : elements_p) {
        if (!elt.eClass().equals(cls)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * @param element_p
   * @return
   */
  public static boolean isSupportedType(Object element_p) {
    return !(element_p instanceof ExchangeItem);
  }

  /**
   * @param element1_p
   * @param element2_p
   * @return
   */
  public static boolean isCompatibleType(Object element1_p, Object element2_p) {
    if ((element1_p instanceof AbstractFunction) && (element2_p instanceof AbstractFunction)) {
      return true;
    } else if ((element1_p instanceof FunctionPort) && (element2_p instanceof FunctionPort)) {
      return isSamePortType((FunctionPort) element1_p, (FunctionPort) element2_p);
    }
    return false;
  }

  /**
   * @param element1_p
   * @param element2_p
   * @return
   */
  private static boolean isSamePortType(FunctionPort element1_p, FunctionPort element2_p) {
    if (((element1_p instanceof FunctionInputPort) && (element2_p instanceof FunctionInputPort))
      ||((element1_p instanceof FunctionOutputPort) && (element2_p instanceof FunctionOutputPort)))
    {
      return true;
    }
    return false;
  }

  /**
   * @param elements_p
   * @return
   */
  public static boolean isValidTypeForDeletion(List<EObject> elements_p) {
    if ((null != elements_p) && !elements_p.isEmpty()) {
      for (EObject elt : elements_p) {
        if (!(elt instanceof AbstractFunction) && !(elt instanceof FunctionPort)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * @param exchangeItem_p
   * @param port_p
   * @return
   */
  public static boolean isDelegated(ExchangeItem exchangeItem_p, FunctionPort port_p) {
    boolean isDelegated = false;
    for (PortRealization rlz : port_p.getIncomingPortRealizations()) {
      Port srcPort = rlz.getRealizingPort();
      if ((srcPort instanceof FunctionInputPort) && ((FunctionInputPort) srcPort).getIncomingExchangeItems().contains(exchangeItem_p)) {
        isDelegated = true;
      } else if ((srcPort instanceof FunctionOutputPort) && ((FunctionOutputPort) srcPort).getOutgoingExchangeItems().contains(exchangeItem_p)) {
        isDelegated = true;
      }
    }
    return isDelegated;
  }

  /**
   * @param exchangeItem_p
   * @param port_p
   * @return
   */
  public static boolean isDelegator(ExchangeItem exchangeItem_p, FunctionPort port_p) {
    boolean isDelegated = false;
    for (PortRealization rlz : port_p.getOutgoingPortRealizations()) {
      Port dstPort = rlz.getRealizedPort();
      if ((dstPort instanceof FunctionInputPort) && ((FunctionInputPort) dstPort).getIncomingExchangeItems().contains(exchangeItem_p)) {
        isDelegated = true;
      } else if ((dstPort instanceof FunctionOutputPort) && ((FunctionOutputPort) dstPort).getOutgoingExchangeItems().contains(exchangeItem_p)) {
        isDelegated = true;
      }
    }
    return isDelegated;
  }
}
