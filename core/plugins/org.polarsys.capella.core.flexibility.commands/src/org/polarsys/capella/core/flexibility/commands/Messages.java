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
package org.polarsys.capella.core.flexibility.commands;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.flexibility.commands.messages"; //$NON-NLS-1$

  public static String DefaultCategories_RenameElement;
  public static String DefaultCategories_RetrieveData;
  public static String DefaultCategories_TestGeneration;
  public static String DefaultCategories_MoveElement;
  public static String DefaultCategories_Diagram;
  public static String DefaultCategories_Information;
  public static String DefaultCategories_Tools;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
