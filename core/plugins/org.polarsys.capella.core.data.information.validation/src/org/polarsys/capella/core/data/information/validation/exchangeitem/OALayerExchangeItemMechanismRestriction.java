/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.validation.exchangeitem;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

/**
 * Physical Layer ExchangeItem mechanism can not be UNSET Applay's same for other layer ExchangeItem if referred by any element contained in Physical Layer
 */
public class OALayerExchangeItemMechanismRestriction extends AbstractExchangeItemMechanismRestriction {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isInCurrentLayer(CapellaElement element) {
    return CapellaLayerCheckingExt.isAOrInOperationalAnalysisLayer(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkIfReferedByOtherLayer() {
    return false;
  }

}
