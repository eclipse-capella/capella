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

package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.action.CutAction;
import org.eclipse.jface.viewers.StructuredViewer;

import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCutCommand;

/**
 * The action allowing to cut Capella elements.
 */
public class CapellaCutAction extends CutAction {
  private StructuredViewer _viewer;

  /**
   * Constructs the Capella action allowing to cut Capella elements.
   * @param domain_p The editing domain.
   */
  public CapellaCutAction(EditingDomain domain_p, StructuredViewer viewer_p) {
    super(domain_p);
    _viewer = viewer_p;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.PasteAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<?> selection_p) {
    CapellaCopyAction.filterSelectedElements(selection_p);
    Collection<?> list = selection_p;
    return new CapellaCutCommand(EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"), domain, list, _viewer); //$NON-NLS-1$
  }
}
