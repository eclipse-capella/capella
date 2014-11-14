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

  public Object doSwitch(ExchangeSpecification element_p, EStructuralFeature feature_p) {
    Object ret = null;

    if (feature_p.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__CONTAINING_LINK)) {
      ret = getContainingLink(element_p);
    } else if (feature_p.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__INCOMING_EXCHANGE_SPECIFICATION_REALIZATIONS)) {
      ret = getIncomingExchangeSpecificationRealizations(element_p);
    } else if (feature_p.equals(FaPackage.Literals.EXCHANGE_SPECIFICATION__OUTGOING_EXCHANGE_SPECIFICATION_REALIZATIONS)) {
      ret = getOutgoingExchangeSpecificationRealizations(element_p);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = NamedElementHelper.getInstance().doSwitch(element_p, feature_p);
    }
    if (ret == null) {
      ret = ActivityExchangeHelper.getInstance().doSwitch(element_p, feature_p);
    }

    return ret;
  }

  protected List<ExchangeSpecificationRealization> getIncomingExchangeSpecificationRealizations(ExchangeSpecification element_p) {
    List<ExchangeSpecificationRealization> ret = new ArrayList<ExchangeSpecificationRealization>();

    for (AbstractTrace trace : element_p.getIncomingTraces()) {
      if (trace instanceof ExchangeSpecificationRealization) {
        ret.add((ExchangeSpecificationRealization) trace);
      }
    }

    return ret;
  }

  protected List<ExchangeSpecificationRealization> getOutgoingExchangeSpecificationRealizations(ExchangeSpecification element_p) {
    List<ExchangeSpecificationRealization> ret = new ArrayList<ExchangeSpecificationRealization>();

    for (AbstractTrace trace : element_p.getOutgoingTraces()) {
      if (trace instanceof ExchangeSpecificationRealization) {
        ret.add((ExchangeSpecificationRealization) trace);
      }
    }

    return ret;
  }

  protected ExchangeLink getContainingLink(ExchangeSpecification element_p) {
    ExchangeContainment cont = element_p.getLink();
    if (null != cont)
      return cont.getLink();
    return null;
  }
}
