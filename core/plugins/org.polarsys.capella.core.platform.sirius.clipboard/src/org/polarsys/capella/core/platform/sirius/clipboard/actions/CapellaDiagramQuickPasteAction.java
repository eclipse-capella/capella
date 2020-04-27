/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
 * Quick Paste action for Capella Diagrams: skip refresh
 */
public class CapellaDiagramQuickPasteAction extends CapellaDiagramPasteAction {

  /**
   * Default constructor
   */
  public CapellaDiagramQuickPasteAction() {
    super();
  }

  /**
   * @see org.polarsys.capella.core.platform.sirius.clipboard.actions.CapellaDiagramPasteAction#mustRefresh()
   */
  @Override
  protected boolean mustRefresh() {
    return false;
  }
}
