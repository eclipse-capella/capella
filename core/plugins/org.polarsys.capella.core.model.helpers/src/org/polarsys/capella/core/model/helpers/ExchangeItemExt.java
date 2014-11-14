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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.debug.QueryDebugger;

/**
 */
public class ExchangeItemExt {
  /**
   * @param context a Capella Element
   * @return all the exchange items contained in the current and previous architectures
   */
  public static Collection<AbstractExchangeItem> getAllExchangeItems(final EObject context) {
    // OLD CODE
    Collection<AbstractExchangeItem> returnedItems = new ArrayList<AbstractExchangeItem>();
    for (BlockArchitecture aBlockArchitecture : BlockArchitectureExt.getRootAndPreviousBlockArchitectures(context)) {
      for (EObject anItem : EObjectExt.getAll(aBlockArchitecture, InformationPackage.Literals.EXCHANGE_ITEM)) {
        returnedItems.add((ExchangeItem) anItem);
      }
    }
    // NEW CODE
    returnedItems = (List) QueryDebugger.executeQueryWithInclusionDebug(QueryIdentifierConstants.GET_ALL_EXCHANGE_ITEMS, context, returnedItems);
    // END CODE REFACTOR
    return returnedItems;
  }

  public static Object getEILabel(AbstractExchangeItem ei_p, boolean showExchangeItemsParameters) {
    ExchangeItem ei = (ExchangeItem) ei_p;
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
