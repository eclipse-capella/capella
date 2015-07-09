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
package org.polarsys.capella.core.data.helpers.information.services;

import org.polarsys.capella.core.data.information.ElementKind;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.ParameterDirection;

/**
 */
public class ExchangeItemElementExt {

  /**
   * @param element_p
   */
  public static void changeExchangeItemElementKind(ExchangeItemElement element_p, ExchangeMechanism mechanism) {
    if (mechanism == ExchangeMechanism.OPERATION) {
      if (element_p.getKind() != ElementKind.MEMBER) {
        element_p.setKind(ElementKind.MEMBER);
      }
    } else {
      if (element_p.getKind() != ElementKind.TYPE) {
        element_p.setKind(ElementKind.TYPE);
        element_p.setDirection(ParameterDirection.UNSET);

      }
      if (!element_p.getDirection().equals(ParameterDirection.UNSET)) {
        element_p.setDirection(ParameterDirection.UNSET);
      }
    }
  }

  /**
   * @param element_p
   */
  public static void changeExchangeItemElementKind(ExchangeItemElement element_p) {
    if (element_p.getKind() == ElementKind.TYPE) {
      element_p.setDirection(ParameterDirection.UNSET);
    }
  }

}
