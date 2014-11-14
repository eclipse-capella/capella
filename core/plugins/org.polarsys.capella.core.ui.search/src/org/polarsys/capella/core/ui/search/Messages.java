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
package org.polarsys.capella.core.ui.search;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.search.messages"; //$NON-NLS-1$
  public static String FindAndReplaceDialog_1;
  public static String FindAndReplaceDialog_no_matching;
  public static String FindAndReplaceDialog_preview;
  public static String FindAndReplaceDialog_impacted_find_string;
  public static String FindAndReplaceDialog_dialogMessage;
  public static String ImpactAnalysisAction_ShowInCapellaExplorer_Title;
  public static String selectInSemanticBrowser;
  public static String FindAndReplaceDialog_compute_impact;
  public static String FindAndReplaceDialog_title;
  public static String FindAndReplaceDialog_impacted_names;
  public static String FindAndReplaceDialog_impacted_summaries;
  public static String FindAndReplaceDialog_impacted_descriptions;
  public static String FindAndReplaceDialog_replace_all;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //
  }
}
