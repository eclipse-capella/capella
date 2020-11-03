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
package org.polarsys.capella.core.af.integration;

import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Guiu
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.af.integration.messages"; //$NON-NLS-1$
  public static String MetadataUpdateContributor_Name;
  public static String MetadataCreationContributor_Name;
  public static String ViewpointMigrationContributor_Name;
  public static String NoMetadataException_Message;
  public static String WrongCapellaVersionException_Message;
  public static String WrongCapellaVersionException_DetailedMessage;

  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
