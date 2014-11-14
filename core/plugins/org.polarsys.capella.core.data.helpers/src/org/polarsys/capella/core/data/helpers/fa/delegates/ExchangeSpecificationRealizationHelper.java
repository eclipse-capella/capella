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
package org.polarsys.capella.core.data.helpers.fa.delegates;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AllocationHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ExchangeSpecificationRealizationHelper {
  private static ExchangeSpecificationRealizationHelper instance;

  private ExchangeSpecificationRealizationHelper() {
    // do nothing
  }

  public static ExchangeSpecificationRealizationHelper getInstance() {
    if (instance == null)
      instance = new ExchangeSpecificationRealizationHelper();
    return instance;
  }

  public Object doSwitch(ExchangeSpecificationRealization element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION)) {
      ret = getRealizingExchangeSpecification(element_p);
    } else if (feature_p.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION)) {
      ret = getRealizedExchangeSpecification(element_p);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AllocationHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected ExchangeSpecification getRealizingExchangeSpecification(ExchangeSpecificationRealization element_p) {
    TraceableElement ret = element_p.getSourceElement();
    if (null != ret && ret instanceof ExchangeSpecification)
      return (ExchangeSpecification) ret;
    return null;
  }

  protected ExchangeSpecification getRealizedExchangeSpecification(ExchangeSpecificationRealization element_p) {
    TraceableElement ret = element_p.getTargetElement();
    if (null != ret && ret instanceof ExchangeSpecification)
      return (ExchangeSpecification) ret;
    return null;
  }
}
