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
package org.polarsys.capella.common.data.helpers.modellingcore.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectExt;

public class InformationsExchangerHelper {
  private static InformationsExchangerHelper instance;

  private InformationsExchangerHelper() {
  }

  public static InformationsExchangerHelper getInstance() {
    if (instance == null) {
      instance = new InformationsExchangerHelper();
    }
    return instance;
  }

  public Object doSwitch(InformationsExchanger element, EStructuralFeature feature) {
    Object ret = null;

    if (feature.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INCOMING_INFORMATION_FLOWS)) {
      ret = getIncomingInformationFlows(element);
    } else if (feature.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__OUTGOING_INFORMATION_FLOWS)) {
      ret = getOutgoingInformationFlows(element);
    } else if (feature.equals(ModellingcorePackage.Literals.INFORMATIONS_EXCHANGER__INFORMATION_FLOWS)) {
      ret = getInformationFlows(element);
    }

    // no helper found... searching in super classes...
    if (null == ret) {
      ret = ModelElementHelper.getInstance().doSwitch(element, feature);
    }

    return ret;
  }

  protected List<AbstractInformationFlow> getIncomingInformationFlows(InformationsExchanger element) {
    return EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET);
  }

  protected List<AbstractInformationFlow> getOutgoingInformationFlows(InformationsExchanger element) {
    return EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE);
  }

  /**
   * @param element
   * @return
   */
  protected List<AbstractInformationFlow> getInformationFlows(InformationsExchanger element) {
    List<AbstractInformationFlow> ret = new ArrayList<>();

    List<AbstractInformationFlow> sources = EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE);
    ret.addAll(sources);

    List<AbstractInformationFlow> targets = EObjectExt.getReferencers(element, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET);
    ret.addAll(targets);

    return ret;
  }
}
