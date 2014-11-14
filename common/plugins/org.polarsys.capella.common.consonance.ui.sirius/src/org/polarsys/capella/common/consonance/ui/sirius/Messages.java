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
package org.polarsys.capella.common.consonance.ui.sirius;

import org.eclipse.osgi.util.NLS;

/**
 * Message management.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.consonance.ui.sirius.messages"; //$NON-NLS-1$
  public static String SiriusComparisonFactory_Label;
  public static String SiriusComparisonMethod_UndoRedoWarning;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
  
  /**
   * Constructor
   */
  private Messages() {
    // Nothing needed
  }
}
