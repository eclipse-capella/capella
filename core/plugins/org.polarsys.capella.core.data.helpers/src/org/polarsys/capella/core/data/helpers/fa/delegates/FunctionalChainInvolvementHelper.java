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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;

public class FunctionalChainInvolvementHelper {
  private static FunctionalChainInvolvementHelper instance;

  private FunctionalChainInvolvementHelper() {
    // do nothing
  }

  public static FunctionalChainInvolvementHelper getInstance() {
    if (instance == null)
      instance = new FunctionalChainInvolvementHelper();
    return instance;
  }

  public Object doSwitch(FunctionalChainInvolvement element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__PREVIOUS_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getPreviousFunctionalChainInvolvements(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS)) {
      ret = getNextFunctionalChainInvolvements(element);
    } else if (feature.equals(FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__INVOLVED_ELEMENT)) {
      ret = getInvolvedElement(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = InvolvementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<FunctionalChainInvolvement> getPreviousFunctionalChainInvolvements(
      FunctionalChainInvolvement element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<>();
    if (element instanceof FunctionalChainInvolvementLink) {
      ret.add(((FunctionalChainInvolvementLink) element).getSource());
    } else if (element instanceof FunctionalChainInvolvementFunction) {
      for (EObject anInverseReference : EObjectExt.getReferencers(element,
          FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__TARGET)) {
        if (anInverseReference instanceof FunctionalChainInvolvementLink) {
          ret.add((FunctionalChainInvolvementLink) anInverseReference);
        }
      }
    }
    return ret;
  }

  protected List<FunctionalChainInvolvement> getNextFunctionalChainInvolvements(FunctionalChainInvolvement element) {
    List<FunctionalChainInvolvement> ret = new ArrayList<>();
    if (element instanceof FunctionalChainInvolvementLink) {
      ret.add(((FunctionalChainInvolvementLink) element).getTarget());
    } else if (element instanceof FunctionalChainInvolvementFunction) {
      for (EObject anInverseReference : EObjectExt.getReferencers(element,
          FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT_LINK__SOURCE)) {
        if (anInverseReference instanceof FunctionalChainInvolvementLink) {
          ret.add((FunctionalChainInvolvementLink) anInverseReference);
        }
      }
    }
    return ret;
  }

  protected InvolvedElement getInvolvedElement(FunctionalChainInvolvement element) {
    return element.getInvolved();
  }
}
