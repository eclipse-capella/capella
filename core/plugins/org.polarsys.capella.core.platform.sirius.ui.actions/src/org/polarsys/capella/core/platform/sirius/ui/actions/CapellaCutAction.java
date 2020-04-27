/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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


package org.polarsys.capella.core.platform.sirius.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.viewers.StructuredViewer;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaCutCommand;
import org.polarsys.capella.core.ui.toolkit.AbstractCommandActionHandler;

/**
 * The action allowing to cut Capella elements.
 */
public class CapellaCutAction extends AbstractCommandActionHandler {

  private StructuredViewer _viewer;

  /**
   * Constructs the Capella action allowing to cut Capella elements.
   * @param domain The editing domain.
   */
  public CapellaCutAction(StructuredViewer viewer) {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"));
    _viewer = viewer;
  }

  /**
   * @see org.eclipse.emf.edit.ui.action.PasteAction#createCommand(java.util.Collection)
   */
  @Override
  public Command createCommand(Collection<Object> selection) {
    CapellaCopyAction.filterSelectedElements(selection);
    Collection<?> list = selection;
    return new CapellaCutCommand(EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"), list, _viewer); //$NON-NLS-1$
  }

}
