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
package org.polarsys.capella.core.ui.properties;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;
import org.polarsys.capella.common.platform.sirius.ted.DataNotifier;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 *
 */
public class CapellalEditingDomainListenerForPropertySections extends ResourceSetListenerImpl implements IEditingDomainListener {

  /**
   * Unique instance of the data listener.
   */
  private static CapellaDataListenerForPropertySections _dataListenerForPropertySections;

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#createdEditingDomain(EditingDomain)
   */
  @Override
  public void createdEditingDomain(EditingDomain editingDomain) {
    DataNotifier dataNotifier = ((SemanticEditingDomain) editingDomain).getDataNotifier();
    if (null != dataNotifier) {
      dataNotifier.addAdapter(ModelElement.class, getCapellaDataListenerForPropertySections());
      dataNotifier.addAdapter(DRepresentation.class, getCapellaDataListenerForPropertySections());
    }
  }

  /**
   * @see org.polarsys.capella.common.ef.domain.IEditingDomainListener#disposedEditingDomain(EditingDomain)
   */
  @Override
  public void disposedEditingDomain(EditingDomain editingDomain) {
    DataNotifier dataNotifier = ((SemanticEditingDomain) editingDomain).getDataNotifier();
    if (null != dataNotifier) {
      dataNotifier.remove(getCapellaDataListenerForPropertySections());
    }
  }

  /**
   * Getter for the unique instance of the data listener
   */
  public static CapellaDataListenerForPropertySections getCapellaDataListenerForPropertySections() {
    if (null == _dataListenerForPropertySections) {
      _dataListenerForPropertySections = new CapellaDataListenerForPropertySections();
    }
    return _dataListenerForPropertySections;
  }
}
