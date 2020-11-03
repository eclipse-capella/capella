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
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.TransactionHelper;
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

/**
 */
public class EIAllocationModelHelpers {

  /**
   * Get list of transitioned Elements
   */
  public static List<ModelElement> getTransitionedElements(ModelElement element) {
    return getTransitionedElements(Collections.singletonList(element));
  }

  /**
   * Get list of transitioned Elements
   */
  public static List<ModelElement> getTransitionedElements(List<ModelElement> elements) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    for (ModelElement elt : elements) {
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
  public static List<ModelElement> getTransitionerElements(ModelElement element) {
    return getTransitionerElements(Collections.singletonList(element));
  }

  /**
   * Get list of transitioner Elements
   */
  public static List<ModelElement> getTransitionerElements(List<ModelElement> elements) {
    List<ModelElement> result = new ArrayList<ModelElement>();
    for (ModelElement elt : elements) {
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
   * @param exchangeItem
   */
  public static List<EObject> getOwners(ExchangeItem exchangeItem) {
    return EObjectExt.getReferencers(exchangeItem, Arrays.asList(
      new EReference[] { FaPackage.Literals.FUNCTION_INPUT_PORT__INCOMING_EXCHANGE_ITEMS, FaPackage.Literals.FUNCTION_OUTPUT_PORT__OUTGOING_EXCHANGE_ITEMS }));
  }

  /**
   * @param selection
   */
  public static void handleDeletion(Collection<?> selection) {
	Collection<EObject> elts = filterFunctionsAndPorts(selection);
    CapellaDeleteCommand cmd = new CapellaDeleteCommand(TransactionHelper.getExecutionManager(elts), elts, false, false, false);
    if (cmd.canExecute()) {
      cmd.execute();
    }
  }

  /**
   * @param selection
   */
  public static Collection<EObject> filterFunctionsAndPorts(Collection<?> selection) {
    Set<EObject> result = new HashSet<EObject>();
    for (Object obj : selection) {
      if ((obj instanceof AbstractFunction) || (obj instanceof FunctionPort)) {
        result.add((EObject) obj);
      }
    }
    return result;
  }

  /**
   * @param sepFunctionPort
   * @param tepFunctionPort
   * @param exchangeItem
   */
  public static void handleAllocation(FunctionPort sepFunctionPort, FunctionPort tepFunctionPort, ExchangeItem exchangeItem) {
    if ((null != tepFunctionPort) && (null != exchangeItem)) {
      if (tepFunctionPort instanceof FunctionInputPort) {
        List<ExchangeItem> incomingEI = ((FunctionInputPort) tepFunctionPort).getIncomingExchangeItems();
        if (!incomingEI.contains(exchangeItem)) {
          incomingEI.add(exchangeItem);
        }
      } else if (tepFunctionPort instanceof FunctionOutputPort) {
        List<ExchangeItem> outgoingEI = ((FunctionOutputPort) tepFunctionPort).getOutgoingExchangeItems();
        if (!outgoingEI.contains(exchangeItem)) {
          outgoingEI.add(exchangeItem);
        }
      }
      handleAllocation(tepFunctionPort, sepFunctionPort);
    }
  }

  /**
   * @param source
   * @param target
   */
  public static void handleAllocation(AbstractFunction source, AbstractFunction target) {
    boolean alreadyRealized = false;
    for (FunctionRealization rlz : source.getOutFunctionRealizations()) {
      AbstractFunction fct = rlz.getAllocatedFunction();
      if (target.equals(fct)) {
        alreadyRealized = true;
        break;
      }
    }
    if (!alreadyRealized) {
      FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
      rlz.setSourceElement(source);
      rlz.setTargetElement(target);
      source.getOwnedFunctionRealizations().add(rlz);
    }
  }

  /**
   * @param source
   * @param target
   */
  public static void handleAllocation(FunctionPort source, FunctionPort target) {
    boolean alreadyRealized = false;
    if (((source instanceof FunctionInputPort) && (target instanceof FunctionInputPort))
      || ((source instanceof FunctionOutputPort) && (target instanceof FunctionOutputPort)))
    {
      for (PortRealization rlz : source.getOutgoingPortRealizations()) {
        Port port = rlz.getRealizedPort();
        if (target.equals(port)) {
          alreadyRealized = true;
          break;
        }
      }
      if (!alreadyRealized) {
        PortRealization rlz = InformationFactory.eINSTANCE.createPortRealization();
        rlz.setSourceElement(source);
        rlz.setTargetElement(target);
        source.getOwnedPortRealizations().add(rlz);
      }
    } else {
      //
    }
  }

  /**
   * @param elements
   * @return
   */
  public static boolean isSameType(List<EObject> elements) {
    if ((null != elements) && !elements.isEmpty()) {
      EClass cls = elements.get(0).eClass();
      for (EObject elt : elements) {
        if (!elt.eClass().equals(cls)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * @param element
   * @return
   */
  public static boolean isSupportedType(Object element) {
    return !(element instanceof ExchangeItem);
  }

  /**
   * @param element1
   * @param element2
   * @return
   */
  public static boolean isCompatibleType(Object element1, Object element2) {
    if ((element1 instanceof AbstractFunction) && (element2 instanceof AbstractFunction)) {
      return true;
    } else if ((element1 instanceof FunctionPort) && (element2 instanceof FunctionPort)) {
      return isSamePortType((FunctionPort) element1, (FunctionPort) element2);
    }
    return false;
  }

  /**
   * @param element1
   * @param element2
   * @return
   */
  private static boolean isSamePortType(FunctionPort element1, FunctionPort element2) {
    if (((element1 instanceof FunctionInputPort) && (element2 instanceof FunctionInputPort))
      ||((element1 instanceof FunctionOutputPort) && (element2 instanceof FunctionOutputPort)))
    {
      return true;
    }
    return false;
  }

  /**
   * @param elements
   * @return
   */
  public static boolean isValidTypeForDeletion(List<EObject> elements) {
    if ((null != elements) && !elements.isEmpty()) {
      for (EObject elt : elements) {
        if (!(elt instanceof AbstractFunction) && !(elt instanceof FunctionPort)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * @param exchangeItem
   * @param port
   * @return
   */
  public static boolean isDelegated(ExchangeItem exchangeItem, FunctionPort port) {
    boolean isDelegated = false;
    for (PortRealization rlz : port.getIncomingPortRealizations()) {
      Port srcPort = rlz.getRealizingPort();
      if ((srcPort instanceof FunctionInputPort) && ((FunctionInputPort) srcPort).getIncomingExchangeItems().contains(exchangeItem)) {
        isDelegated = true;
      } else if ((srcPort instanceof FunctionOutputPort) && ((FunctionOutputPort) srcPort).getOutgoingExchangeItems().contains(exchangeItem)) {
        isDelegated = true;
      }
    }
    return isDelegated;
  }

  /**
   * @param exchangeItem
   * @param port
   * @return
   */
  public static boolean isDelegator(ExchangeItem exchangeItem, FunctionPort port) {
    boolean isDelegated = false;
    for (PortRealization rlz : port.getOutgoingPortRealizations()) {
      Port dstPort = rlz.getRealizedPort();
      if ((dstPort instanceof FunctionInputPort) && ((FunctionInputPort) dstPort).getIncomingExchangeItems().contains(exchangeItem)) {
        isDelegated = true;
      } else if ((dstPort instanceof FunctionOutputPort) && ((FunctionOutputPort) dstPort).getOutgoingExchangeItems().contains(exchangeItem)) {
        isDelegated = true;
      }
    }
    return isDelegated;
  }
}
