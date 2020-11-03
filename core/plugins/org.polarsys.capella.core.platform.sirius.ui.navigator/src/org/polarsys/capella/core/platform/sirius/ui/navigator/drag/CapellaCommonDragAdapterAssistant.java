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
package org.polarsys.capella.core.platform.sirius.ui.navigator.drag;

import org.apache.log4j.Logger;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.navigator.CommonDragAdapterAssistant;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;

/**
 * The Capella common drag adapter assistant.
 */
public class CapellaCommonDragAdapterAssistant extends CommonDragAdapterAssistant {
  /**
   * Log4j reference logger.
   */
  protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  // The supported transfers list.
  private static final Transfer[] SUPPORTED_TRANSFERS = new Transfer[] { LocalSelectionTransfer.getTransfer() };

  /**
   * Constructs the Capella common drag adapter assistant.
   */
  public CapellaCommonDragAdapterAssistant() {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.navigator.CommonDragAdapterAssistant#getSupportedTransferTypes()
   */
  @Override
  public Transfer[] getSupportedTransferTypes() {
    return SUPPORTED_TRANSFERS;
  }

  /**
   * @see org.eclipse.ui.navigator.CommonDragAdapterAssistant#setDragData(org.eclipse.swt.dnd.DragSourceEvent, org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  public boolean setDragData(DragSourceEvent event_p, IStructuredSelection selection) {
    if (LocalSelectionTransfer.getTransfer().isSupportedType(event_p.dataType)) {
      logger.debug(new EmbeddedMessage("Drag activated from " + event_p.getSource(), IReportManagerDefaultComponents.UI)); //$NON-NLS-1$
      event_p.data = selection;
      return true;
    }
    return false;
  }
}
