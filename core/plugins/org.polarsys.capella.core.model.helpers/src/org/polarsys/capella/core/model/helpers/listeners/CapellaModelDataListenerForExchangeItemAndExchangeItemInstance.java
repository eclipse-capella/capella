/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.listeners;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.model.utils.NamingHelper;

/**
 * 
 * This listener will synchronize the name of ExchangeItem and its ExchangeItemInstance if and only if a single typed
 * ExchangeItemInstance exists.
 * 
 */
public class CapellaModelDataListenerForExchangeItemAndExchangeItemInstance extends CapellaModelDataListener {
  @Override
  public void notifyChanged(Notification notification) {

    if (filterNotification(notification)) {
      return;
    }

    if (notification.getEventType() != Notification.SET) {
      return;
    }

    EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
    if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(feature)) {
      String newName = notification.getNewStringValue();
      Object notifier = notification.getNotifier();

      if (notifier instanceof ExchangeItem) {
        ExchangeItem exchangeItem = (ExchangeItem) notifier;
        List<ExchangeItemInstance> exchangeItemInstances = ExchangeItemExt.getTypedExchangeItemInstances(exchangeItem);

        if (exchangeItemInstances.size() == 1) {
          ExchangeItemInstance exchangeItemInstance = exchangeItemInstances.get(0);
          NamingHelper.synchronizeName(exchangeItemInstance, newName);
        }

      } else if (notifier instanceof ExchangeItemInstance) {
        ExchangeItemInstance exchangeItemInstance = (ExchangeItemInstance) notifier;
        AbstractType abstractType = exchangeItemInstance.getAbstractType();
        if (abstractType instanceof ExchangeItem) {
          ExchangeItem exchangeItem = (ExchangeItem) abstractType;
          List<ExchangeItemInstance> exchangeItemInstances = ExchangeItemExt
              .getTypedExchangeItemInstances(exchangeItem);

          if (exchangeItemInstances.size() == 1) {
            NamingHelper.synchronizeName(exchangeItem, newName);
          }

        }

      }

    }
  }
}
