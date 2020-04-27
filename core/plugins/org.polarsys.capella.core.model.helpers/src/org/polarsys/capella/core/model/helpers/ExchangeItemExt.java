/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 */
public class ExchangeItemExt {
  /**
   * @param context a Capella Element
   * @return all the exchange items contained in the current and previous architectures
   */
  public static Collection<AbstractExchangeItem> getAllExchangeItems(final EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS, context);
  }

  public static Object getEILabel(AbstractExchangeItem abstractExchangeItem1, boolean showExchangeItemsParameters) {
    ExchangeItem ei = (ExchangeItem) abstractExchangeItem1;
    StringBuilder result = new StringBuilder();
    if (ICommonConstants.EMPTY_STRING.equals(ei.getName()) || (ei.getName() == null)) {
      result.append("<undefined>"); //$NON-NLS-1$
    } else {
      result.append(ei.getName());
    }
    if (showExchangeItemsParameters) {
      result.append("("); //$NON-NLS-1$
      int indice = 0;
      for (ExchangeItemElement eie : ei.getOwnedElements()) {
        if (ICommonConstants.EMPTY_STRING.equals(eie.getName()) || (eie.getName() == null)) {
          result.append("<undefined>"); //$NON-NLS-1$
        } else {
          result.append(eie.getName());
        }
        AbstractType type = eie.getAbstractType();
        if (type != null) {
          result.append(" : "); //$NON-NLS-1$
          result.append(type.getName());
        } else {
          result.append(" : "); //$NON-NLS-1$
          result.append("<undefined>"); //$NON-NLS-1$
        }
        indice++;
        if (indice < ei.getOwnedElements().size()) {
          result.append(", "); //$NON-NLS-1$
        }
      }
      result.append(")"); //$NON-NLS-1$
    }
    return result.toString();
  }
}
