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
package org.polarsys.capella.core.platform.sirius.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 *
 */
public class ActionPropertyTester extends PropertyTester {

  /**
   * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
   *      java.lang.Object)
   */
  public boolean test(Object object_p, String propertyName_p, Object[] params_p, Object testedValue_p) {
    boolean result = false;
    if (propertyName_p.equals("actionMode") || propertyName_p.equals("graphicalActionMode")) { //$NON-NLS-1$ //$NON-NLS-2$
      // getting the capella element
      ModelElement element = ModelAdaptation.adaptToCapella(object_p);
      if ((element != null) && (element instanceof CapellaElement)) {
        String actionName = (String) testedValue_p;
        
        if ("propagationEIOnPorts".equals(actionName)) { //$NON-NLS-1$
          return isPropagationEIOnPorts(element);
        }
        if ("synchronizeEIOnPorts".equals(actionName)) { //$NON-NLS-1$
          return isSynchronizeEIOnPorts(element);
        }
        if ("propagationPortRealizationsFromFE".equals(actionName)) { //$NON-NLS-1$
          return isPropagationPortRealizationsFromFE(element);
        }
        if ("propagationPortRealizationsFromCE".equals(actionName)) { //$NON-NLS-1$
          return isPropagationPortRealizationsFromCE(element);
        }
        if ("convertClassPrimitive".equals(actionName)) { //$NON-NLS-1$
          return isConvertPrimitive(element);
        }
        if ("transformLiteralNumericValue".equals(actionName)) { //$NON-NLS-1$
          return element instanceof LiteralNumericValue;
        }
        if ("traceManager".equals(actionName)) { //$NON-NLS-1$
          return isTraceManager(element);
        }
        if ("allocationManagement".equals(actionName)) { //$NON-NLS-1$
          return isAllocationManagement(element);
        }
        if ("copyPath".equals(actionName)) { //$NON-NLS-1$
          return isCopyPath(element);
        }
      }
    }
    return result;
  }

  private boolean isCopyPath(ModelElement element) {
    return true;
  }

  private boolean isAllocationManagement(ModelElement element) {
    return true;
  }

  private boolean isTraceManager(ModelElement element) {
    return true;
  }

  private boolean isConvertPrimitive(ModelElement element) {
    if (element instanceof org.polarsys.capella.core.data.information.Class) {
      org.polarsys.capella.core.data.information.Class cl = (org.polarsys.capella.core.data.information.Class) element;
      return cl.isIsPrimitive();
    }
    return false;
  }

  /**
   * @param element_p
   * @return
   */
  private boolean isPropagationPortRealizationsFromCE(ModelElement element_p) {
    ModelElement element = element_p;

    boolean result = false;

    if (element instanceof ComponentExchange) {
      result = true;
    }

    if (element instanceof Part) {
      element = ((Part) element).getAbstractType();
    }

    if (element instanceof Component) {
      if (!(element instanceof ConfigurationItem)) {
        result = true;
      }
    }

    if (result) {
      result = !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element_p);
    }
    return result;
  }

  /**
   * @param element_p
   * @return
   */
  private boolean isPropagationPortRealizationsFromFE(ModelElement element_p) {
    boolean result = false;

    result = ((element_p instanceof AbstractFunction) || (element_p instanceof FunctionalExchange));
    result = (result && !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element_p));

    return result;
  }

  /**
   * @param element_p
   * @return
   */
  private boolean isPropagationEIOnPorts(ModelElement element_p) {
    boolean result = element_p instanceof CapellaElement;

    result = ((element_p instanceof AbstractFunction) || (element_p instanceof FunctionalExchange));
    result = (result && !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element_p));

    return result;
  }

  private boolean isSynchronizeEIOnPorts(ModelElement element) {
    boolean result = element instanceof FunctionalExchange;
    result = (result && !CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer((CapellaElement) element));
    return result;
  }
}
