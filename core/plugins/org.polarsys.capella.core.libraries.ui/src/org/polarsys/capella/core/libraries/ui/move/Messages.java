/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.ui.move;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.libraries.ui.move.messages"; //$NON-NLS-1$
  public static String MoveStagingView_backrefTooltip;
  public static String MoveStagingView_clearParentLabel;
  public static String MoveStagingView_collapseAllAction_text;
  public static String MoveStagingView_collapseAllAction_tooltip;
  public static String MoveStagingView_destinationSectionDescription;
  public static String MoveStagingView_destinationSectionTitle;
  public static String MoveStagingView_stageSectionDescription;
  public static String MoveStagingView_stageSectionTitle;
  public static String MoveStagingView_unstageLabel;
  public static String MoveStagingView_addRequiredElementsLabel;
  public static String MoveStagingView_addAllRequiredElementsLabel;
  public static String MoveStagingView_CancelStatusMessage;
  public static String MoveStagingView_expandAllAction_text;
  public static String MoveStagingView_expandAllAction_tooltip;
  public static String MoveStagingView_fail_dialog_message;
  public static String MoveStagingView_fail_dialog_title;
  public static String MoveStagingView_forced_move_message;
  public static String MoveStagingView_unstageActionTooltip;
  public static String ValidateExecuteListener_dialogCancelButton;
  public static String ValidateExecuteListener_dialogMessage;
  public static String ValidateExecuteListener_dialogProceedButton;
  public static String ValidateExecuteListener_dialogTitle;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
