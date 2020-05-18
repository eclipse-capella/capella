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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move;

import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;

/**
 * Move down selected elements.<br>
 * Selected elements must have the same type and the same parent.
 */
public class MoveDownAction extends org.polarsys.capella.core.ui.toolkit.actions.move.MoveDownAction {
  /**
   * Constructor.
   * @param text_p
   */
  public MoveDownAction() {
    super();
    setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_ENABLED_MOVE_DOWN));
    setDisabledImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IMG_DISABLED_MOVE_DOWN));
    setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.moveDown"); //$NON-NLS-1$
  }
}
