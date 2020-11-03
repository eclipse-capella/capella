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

package org.polarsys.capella.core.data.helpers.information.services;

import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.ParameterDirection;

/**
 */
public class ExchangeItemElementExt {

  /**
   * @param element
   */
  public static void changeExchangeItemElementKind(ExchangeItemElement element, ExchangeMechanism mechanism) {
    if (mechanism == ExchangeMechanism.OPERATION) {
      if (element.getKind() != ElementKind.MEMBER) {
        element.setKind(ElementKind.MEMBER);
        element.setDirection(ParameterDirection.IN);
      }
    } else {
      if (element.getKind() != ElementKind.TYPE) {
        element.setKind(ElementKind.TYPE);
        element.setDirection(ParameterDirection.UNSET);
      }
      if (!element.getDirection().equals(ParameterDirection.UNSET)) {
        element.setDirection(ParameterDirection.UNSET);
      }
    }
  }

  /**
   * @param element
   */
  public static void changeExchangeItemElementDirection(ExchangeItemElement element, ExchangeMechanism mechanism) {
    if (element.getKind() == ElementKind.TYPE) {
      element.setDirection(ParameterDirection.UNSET);
    } 
    if (element.getKind() == ElementKind.MEMBER && mechanism == ExchangeMechanism.OPERATION) {
        element.setDirection(ParameterDirection.IN);
      } 
  }

}
