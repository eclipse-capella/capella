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

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchangeSpecification;
import org.polarsys.capella.common.data.modellingcore.AbstractRelationship;

public class FunctionalExchangeSpecificationHelper {
  private static FunctionalExchangeSpecificationHelper instance;

  private FunctionalExchangeSpecificationHelper() {
    // do nothing
  }

  public static FunctionalExchangeSpecificationHelper getInstance() {
    if (instance == null)
      instance = new FunctionalExchangeSpecificationHelper();
    return instance;
  }

  public Object doSwitch(FunctionalExchangeSpecification element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_EXCHANGE_SPECIFICATION__FUNCTIONAL_EXCHANGES)) {
      ret = getFunctionalExchanges(element);
    }

    // no helper found... searching in super classes...
    if (ret == null) {
      ret = ExchangeSpecificationHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<FunctionalExchange> getFunctionalExchanges(FunctionalExchangeSpecification element) {
    List<FunctionalExchange> ret = new ArrayList<>();

    for (AbstractRelationship item : element.getRealizations()) {
      if (item instanceof FunctionalExchange) {
        ret.add((FunctionalExchange) item);
      }
    }

    return ret;
  }
}
