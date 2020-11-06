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

package org.polarsys.capella.core.model.helpers.listeners;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 *
 */
public class CapellaModelEditingDomainListener extends ResourceSetListenerImpl implements IEditingDomainListener {

  // The data listeners
  // private CapellaModelDataListenerForDatas _dataListenerForDatas;
  private CapellaModelDataListenerForAbstractStates _dataListenerForAbstractStates;
  private CapellaModelDataListenerForSequenceMessages _dataListenerForSequenceMessages;
  private CapellaModelDataListenerForPartsAndComponents _dataListenerForPartsAndComponents;
  private CapellaModelDataListenerForInstanceRole dataListenerForInstanceRole;
  private CapellaModelDataListenerForExchangeItemsAndCommunicationLinks _dataListenerForExchangeItemsAndCommunicationLinks;

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#createdEditingDomain(EditingDomain)
   */
  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    loadDataListeners((SemanticEditingDomain) editingDomain);
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#disposedEditingDomain(EditingDomain)
   */
  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    // Do nothing
  }

  /**
   * load/unload the data listeners
   */
  private void loadDataListeners(SemanticEditingDomain editingDomain) {
    loadDataListenerForDatas(editingDomain);
    loadDataListenerForSequenceMessages(editingDomain);
    loadDataListenerForExchangeItemsAndCommunicationLinks(editingDomain);
    loadDataListenerForPartsAndComponents(editingDomain);
    loadDataListenerForInstanceRoles(editingDomain);
    loadDataListenerForAbstractStates(editingDomain);
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForDatas(SemanticEditingDomain editingDomain) {
    // FIXME OCD_MA_O1 to be reactivated
    // if (_dataListenerForDatas == null) {
    // _dataListenerForDatas = new CapellaModelDataListenerForDatas();
    // MDEAdapterFactory.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForDatas);
    // }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForAbstractStates(SemanticEditingDomain editingDomain) {
    if (_dataListenerForAbstractStates == null) {
      if (editingDomain.getDataNotifier() != null) {
        _dataListenerForAbstractStates = new CapellaModelDataListenerForAbstractStates();
        editingDomain.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForAbstractStates);
      }
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForSequenceMessages(SemanticEditingDomain editingDomain) {
    if (_dataListenerForSequenceMessages == null) {
      if (editingDomain.getDataNotifier() != null) {
        _dataListenerForSequenceMessages = new CapellaModelDataListenerForSequenceMessages();
        editingDomain.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForSequenceMessages);
      }
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForPartsAndComponents(SemanticEditingDomain editingDomain) {
    if (_dataListenerForPartsAndComponents == null) {
      if (editingDomain.getDataNotifier() != null) {
        _dataListenerForPartsAndComponents = new CapellaModelDataListenerForPartsAndComponents();
        editingDomain.getDataNotifier().addAdapter(AbstractNamedElement.class, _dataListenerForPartsAndComponents);
      }
    }
  }

  private void loadDataListenerForInstanceRoles(SemanticEditingDomain editingDomain) {
    if (dataListenerForInstanceRole == null && editingDomain.getDataNotifier() != null) {
      dataListenerForInstanceRole = new CapellaModelDataListenerForInstanceRole();
      editingDomain.getDataNotifier().addAdapter(AbstractNamedElement.class, dataListenerForInstanceRole);
    }
  }

  /**
   * loads the data listener
   */
  private void loadDataListenerForExchangeItemsAndCommunicationLinks(SemanticEditingDomain editingDomain) {
    if (_dataListenerForExchangeItemsAndCommunicationLinks == null) {
      if (editingDomain.getDataNotifier() != null) {
        _dataListenerForExchangeItemsAndCommunicationLinks = new CapellaModelDataListenerForExchangeItemsAndCommunicationLinks();
        editingDomain.getDataNotifier().addAdapter(AbstractNamedElement.class,
            _dataListenerForExchangeItemsAndCommunicationLinks);
      }
    }
  }
}
