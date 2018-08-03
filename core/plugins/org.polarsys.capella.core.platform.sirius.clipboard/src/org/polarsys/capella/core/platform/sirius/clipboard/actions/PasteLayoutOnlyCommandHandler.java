/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
