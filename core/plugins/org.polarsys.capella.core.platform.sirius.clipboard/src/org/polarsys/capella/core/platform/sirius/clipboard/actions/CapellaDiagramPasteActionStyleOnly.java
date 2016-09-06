/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
 * Paste action for Capella Diagrams: paste style but not layout
 */
public class CapellaDiagramPasteActionStyleOnly extends CapellaDiagramPasteAction {
  @Override
  public boolean mustPasteLayout() {
    return false;
  }
}
