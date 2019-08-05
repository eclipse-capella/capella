/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.projection.interfaces.generateInterfaces.messages"; //$NON-NLS-1$
  public static String PortReferenceUpdater_add_provided_interface;
  public static String PortReferenceUpdater_add_required_interface;
  public static String PortReferenceUpdater_remove_provided_interface;
  public static String PortReferenceUpdater_remove_required_interface;

  public static String generateInterfaces_label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
