/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.fa.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.ExchangeContainment;
import org.polarsys.capella.core.data.fa.ExchangeLink;
import org.polarsys.capella.core.data.fa.ExchangeSpecification;
import org.polarsys.capella.core.data.fa.ExchangeSpecificationRealization;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.common.data.helpers.activity.delegates.ActivityExchangeHelper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class ExchangeSpecificationHelper {
  private static ExchangeSpecificationHelper instance;

  private ExchangeSpecificationHelper() {
    // do nothing
  }

  public static ExchangeSpecificationHelper getInstance() {
    if (instance == null)
      instance = new ExchangeSpecificationHelper();
    return instance;
  }

  public Object doSwitch(ExchangeSpecification element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK)) {
      ret = getContainingLink(element);
    } else if (feature.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS)) {
      ret = getIncomingExchangeSpecificationRealizations(element);
    } else if (feature.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS)) {
      ret = getOutgoingExchangeSpecificationRealizations(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamedElementHelper.getInstance().doSwitch(element, feature);
    }
    if (ret == null) {
      ret = ActivityExchangeHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<ExchangeSpecificationRealization> getIncomingExchangeSpecificationRealizations(ExchangeSpecification element) {
    List<ExchangeSpecificationRealization> ret = new ArrayList<>();

    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof ExchangeSpecificationRealization) {
        ret.add((ExchangeSpecificationRealization) trace);
      }
    }

    return ret;
  }

  protected List<ExchangeSpecificationRealization> getOutgoingExchangeSpecificationRealizations(ExchangeSpecification element) {
    List<ExchangeSpecificationRealization> ret = new ArrayList<>();

    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof ExchangeSpecificationRealization) {
        ret.add((ExchangeSpecificationRealization) trace);
      }
    }

    return ret;
  }

  protected ExchangeLink getContainingLink(ExchangeSpecification element) {
    ExchangeContainment cont = element.getLink();
    if (null != cont)
      return cont.getLink();
    return null;
  }
}
