/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.diagram.commands;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.diagram.commands.messages"; //$NON-NLS-1$
  public static String DiagramTransitionCommand_DescriptionCount;
  public static String DiagramTransitionCommand_Name;
  
  public static String DiagramTransitionRunnable_Name;
  public static String DiagramTransitionRunnable_Task_Init;
  public static String DiagramTransitionRunnable_Error_Source;
  public static String DiagramTransitionRunnable_Error_Target;
  public static String DiagramTransitionRunnable_Error_Session;
  public static String DiagramTransitionRunnable_Error_Diagram;
  public static String DiagramTransitionRunnable_Error_NoDiagram;
  public static String DiagramTransitionRunnable_Success;
  public static String DiagramTransitionRunnable_Error_Unsupported;
  public static String DiagramTransitionRunnable_Error_NoSemanticElt;
  public static String DiagramTransitionRunnable_Error_CannotCreate;
  public static String DiagramTransitionRunnable_25;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
