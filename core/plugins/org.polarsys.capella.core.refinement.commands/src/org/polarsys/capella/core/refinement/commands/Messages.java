/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.commands;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.refinement.commands.messages"; //$NON-NLS-1$

  public static String MultiInstanceRoleExtension_message;
  public static String MultiInstanceRoleExtension_title;
  public static String MultiInstanceRoleExtension_logmsg;
  public static String MultiInstanceRoleExtension_logmsg_confirm;

  private Messages() {
  }

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

}
