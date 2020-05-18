/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.gen.edit.decorators;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.gen.edit.decorators.messages"; //$NON-NLS-1$

  public static String CreationMenuLabel_OperationalActor;
  public static String CreationMenuLabel_SystemActor;
  public static String CreationMenuLabel_LogicalActor;
  public static String CreationMenuLabel_PhysicalActor;

  public static String CreationMenuLabel_Entity;
  public static String CreationMenuLabel_SystemComponent;
  public static String CreationMenuLabel_LogicalComponent;
  public static String CreationMenuLabel_PhysicalComponent_Node;
  public static String CreationMenuLabel_PhysicalComponent_Behaviour;

  public static String CreationMenuLabel_SystemComponentPkg;

  public static String CreationMenuLabel_ConfigurationItem;
  public static String CreationMenuLabel_ConfigurationItemPkg;

  public static String ActorRealization_Label;
  public static String ComponentRealization_Label;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
