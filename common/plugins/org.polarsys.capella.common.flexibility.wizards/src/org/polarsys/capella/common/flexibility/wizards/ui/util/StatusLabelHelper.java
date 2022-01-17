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
package org.polarsys.capella.common.flexibility.wizards.ui.util;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

public class StatusLabelHelper {
    
  StatusLabelHelper() {}

  /**
   * This method allows to set an image to the given SWT label according to the status
   */
  public static void updateImage(IStatus status, Label label) {
    if (!label.isDisposed() && status != null) {
      if (status.isOK()) {
        label.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(StatusLabelHelper.class).getSymbolicName(), "full/etool16/empty.gif").createImage());

      } else if (status.matches(IStatus.INFO)) {
        label.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(StatusLabelHelper.class).getSymbolicName(), "full/etool16/info_tsk.gif").createImage());

      } else if (status.matches(IStatus.WARNING)) {
        label.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(StatusLabelHelper.class).getSymbolicName(), "full/etool16/warn_tsk.gif").createImage());

      } else if (status.matches(IStatus.ERROR)) {
        label.setImage(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(StatusLabelHelper.class).getSymbolicName(), "full/etool16/error_tsk.gif").createImage());
      }
    }
  }

  /**
   * This method allows to set the tooltip to the given SWT label according to the status
   * @param okMessage : if the status is OK, set or not the message of the status
   */
  public static void updateTooltip(IStatus status, Label label, boolean okMessage) {
    if (!label.isDisposed() && status != null) {
      String message = status.getMessage();
      if (status.isOK() && !okMessage) {
        message = ICommonConstants.EMPTY_STRING;
      }
      label.setToolTipText(message);
    }
  }
}
