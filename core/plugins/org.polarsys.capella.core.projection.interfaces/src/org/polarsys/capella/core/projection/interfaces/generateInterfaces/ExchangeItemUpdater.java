/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.InterfaceExt;

class ExchangeItemUpdater {

  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);

  /**
   * Updates the interface so that after this method completes all exchange items in the argument collection
   * are allocated to the interface. 
   * @param iface
   * @param expected
   * @return the exchange items that were newly allocated to the interface
   */
  static Collection<ExchangeItem> updateAddMissing(Interface iface, Collection<ExchangeItem> expected){
    Collection<ExchangeItem> result = getAddedExchangeItems(iface, expected); 
    for (ExchangeItem item : result) {
      ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem(iface, item);
      allocation.setName(item.getName());
      if (logger.isInfoEnabled()){
        logger.info(new EmbeddedMessage(
            NLS.bind("Element ''{0}'' has been allocated into ''{1}''", //$NON-NLS-1$
                new Object[] {EObjectLabelProviderHelper.getText(item), 
                    EObjectLabelProviderHelper.getText(iface)}), logger.getName(), new Object[] {item, iface}));
      }
    }
    return result;
  }

  /**
   * Returns all exchange items from expected that are not already allocated to the interface.
   */
  static Collection<ExchangeItem> getAddedExchangeItems(Interface i, Collection<ExchangeItem> expected) {
    return getAdded(expected, i.getExchangeItems());
  }

  /**
   * Returns all exchange items from the interface that are not present in the argument collection.
   */
  static Collection<ExchangeItem> getRemovedExchangeItems(Interface i, Collection<ExchangeItem> expected) {
    return getRemoved(expected, i.getExchangeItems());
  }

  
  static void propagateExchangeItemsToFunctionPorts(FunctionalExchange fe){

    for (ExchangeItem ei : fe.getExchangedItems()) {
      if (fe.getSourceFunctionOutputPort().getOutgoingExchangeItems().add(ei)){
        if (logger.isInfoEnabled()){
          logger.info(new EmbeddedMessage(
              NLS.bind("Exchange Item ''{0}'' is propagated to source port ''{1}''", //$NON-NLS-1$
                  new Object[] {EObjectLabelProviderHelper.getText(ei),
                      EObjectLabelProviderHelper.getText(fe.getSourceFunctionOutputPort())}), logger.getName(), new Object[] {ei, fe.getSourceFunctionOutputPort()}));
        }
      }

      if (fe.getTargetFunctionInputPort().getIncomingExchangeItems().add(ei)){
        if (logger.isInfoEnabled()){
          logger.info(new EmbeddedMessage(
              NLS.bind("Exchange Item ''{0}'' is propagated to target port ''{1}''", //$NON-NLS-1$
                  new Object[] {EObjectLabelProviderHelper.getText(ei),
                      EObjectLabelProviderHelper.getText(fe.getTargetFunctionInputPort())}), logger.getName(), new Object[] {ei, fe.getTargetFunctionInputPort()}));
        }
      }
    }
  }

  // returns all exchange items that are allocated on at least one of the exchanges but not on the interface
  private static Collection<ExchangeItem> getAdded(Collection<ExchangeItem> expected, Collection<ExchangeItem> actual){
    Collection<ExchangeItem> result = new LinkedHashSet<ExchangeItem>(expected);
    result.removeAll(actual);
    return result;
  }

  // returns all exchange items that are allocated on the interface but not on any exchange
  private static Collection<ExchangeItem> getRemoved(Collection<ExchangeItem> expected, Collection<ExchangeItem> actual) {
    Collection<ExchangeItem> result = new LinkedHashSet<ExchangeItem>(actual);
    result.removeAll(expected);
    return result;
  }

}