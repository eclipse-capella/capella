/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;

public class FunctionalChainInvolvementFunctionHelper {
  private static FunctionalChainInvolvementFunctionHelper instance;

  private FunctionalChainInvolvementFunctionHelper() {
    // do nothing
  }

  public static FunctionalChainInvolvementFunctionHelper getInstance() {
    if (instance == null)
      instance = new FunctionalChainInvolvementFunctionHelper();
    return instance;
  }

  public Object doSwitch(FunctionalChainInvolvementFunction element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__INCOMING_INVOLVEMENT_LINKS)) {
      ret = getIncomingInvolvementLinks(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_FUNCTION__OUTGOING_INVOLVEMENT_LINKS)) {
      ret = getOutgoingInvolvementLinks(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = FunctionalChainInvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<FunctionalChainInvolvementLink> getIncomingInvolvementLinks(
      FunctionalChainInvolvementFunction element) {
    List<FunctionalChainInvolvementLink> ret = new ArrayList<>();
    for (EObject anInverseReference : EObjectExt.getReferencers(element,
        FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET)) {
      if (anInverseReference instanceof FunctionalChainInvolvementLink) {
        ret.add((FunctionalChainInvolvementLink) anInverseReference);
      }
    }
    return ret;
  }

  protected List<FunctionalChainInvolvementLink> getOutgoingInvolvementLinks(
      FunctionalChainInvolvementFunction element) {
    List<FunctionalChainInvolvementLink> ret = new ArrayList<>();
    for (EObject anInverseReference : EObjectExt.getReferencers(element,
        FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE)) {
      if (anInverseReference instanceof FunctionalChainInvolvementLink) {
        ret.add((FunctionalChainInvolvementLink) anInverseReference);
      }
    }
    return ret;
  }
}
