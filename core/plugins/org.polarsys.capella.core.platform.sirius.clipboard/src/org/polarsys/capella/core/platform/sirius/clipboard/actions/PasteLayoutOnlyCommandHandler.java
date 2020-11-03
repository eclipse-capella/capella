/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.actions;

/**
 * A command handler for Capella Diagram Paste
 */
public class PasteLayoutOnlyCommandHandler extends AbstractCopyPasteCommandHandler {
  
  /**
   * @see org.polarsys.capella.core.platform.sirius.clipboard.actions.AbstractCopyPasteCommandHandler#getAction()
   */
  @Override
  protected AbstractCopyPasteAction getAction() {
    return new CapellaDiagramPasteActionLayoutOnly();
  }
}
