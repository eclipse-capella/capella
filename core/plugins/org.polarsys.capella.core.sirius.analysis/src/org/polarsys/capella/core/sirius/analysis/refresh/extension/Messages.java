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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.analysis.refresh.extension.messages"; //$NON-NLS-1$
  public static String RefreshExtension_ErrorOnUpdateFunctionalChainStyle;
  public static String RefreshExtension_ErrorOnUpdatePhysicalPathStyle;
  public static String RefreshExtension_ErrorOnCommitDeferredActions;
  public static String RefreshExtension_ErrorOnContextualElements;
  public static String RefreshExtension_ErrorOnReordering;
  public static String RefreshExtension_ErrorOnUpdateComponentCategories;
  public static String RefreshExtension_ErrorOnUpdatePhysicalCategories;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
