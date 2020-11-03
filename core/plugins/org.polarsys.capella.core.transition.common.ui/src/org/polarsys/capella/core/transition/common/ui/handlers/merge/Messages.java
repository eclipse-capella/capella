/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import org.eclipse.osgi.util.NLS;


@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.common.ui.handlers.merge.messages"; //$NON-NLS-1$
  public static String ComparisonViewer_MergeAllOnLeftTooltip;
  public static String ComparisonViewer_MergeAllOnRightTooltip;
  public static String MergeUIDifferencesHandler_ApplyAllChanges;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing needed
  }
}
