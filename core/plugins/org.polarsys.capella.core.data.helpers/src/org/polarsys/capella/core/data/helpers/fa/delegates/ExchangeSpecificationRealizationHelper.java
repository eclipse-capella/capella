/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

  public Object doSwitch(ExchangeSpecificationRealization element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION_REALIZATION__REALIZING_EXCHANGE_SPECIFICATION)) {
      ret = getRealizingExchangeSpecification(element);
    } else if (feature.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION_REALIZATION__REALIZED_EXCHANGE_SPECIFICATION)) {
      ret = getRealizedExchangeSpecification(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = AllocationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected ExchangeSpecification getRealizingExchangeSpecification(ExchangeSpecificationRealization element) {
    TraceableElement ret = element.getSourceElement();
    if (ret instanceof ExchangeSpecification)
      return (ExchangeSpecification) ret;
    return null;
  }

  protected ExchangeSpecification getRealizedExchangeSpecification(ExchangeSpecificationRealization element) {
    TraceableElement ret = element.getTargetElement();
    if (ret instanceof ExchangeSpecification)
      return (ExchangeSpecification) ret;
    return null;
  }
}
