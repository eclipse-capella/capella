/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
  public static String MoveStagingView_clearParentActionTooltip;
  public static String MoveStagingView_destinationSectionDescription;
  public static String MoveStagingView_destinationSectionTitle;
  public static String MoveStagingView_stageSectionDescription;
  public static String MoveStagingView_stageSectionTitle;
  public static String MoveStagingView_unstageActionTooltip;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
