/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.schema;

import org.eclipse.osgi.util.NLS;

/**
 */
public class WizardsSchemaConstants extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.flexibility.wizards.schema.messages"; //$NON-NLS-1$

  @Deprecated
  public static String PropertiesSchema_ID;
  public static String PropertiesSchema_SCHEMA_ID;

  @Deprecated
  public static String PropertiesSchema_RENDERER;
  @Deprecated
  public static String PropertiesSchema_RENDERER_CLASS;
  @Deprecated
  public static String PropertiesSchema_RENDERER_ID;

  @Deprecated
  public static String PropertiesSchema_RENDERERBINDING;
  @Deprecated
  public static String PropertiesSchema_RENDERERBINDING_RENDERERID;
  @Deprecated
  public static String PropertiesSchema_RENDERERBINDING_PROPERTYID;
  @Deprecated
  public static String PropertiesSchema_RENDERERBINDING_PROPERTIESID;

  public static String PropertiesSchema_BINDINGS;
  public static String PropertiesSchema_BINDINGS__PROPERTIES;

  public static String PropertiesSchema_PROPERTY_BINDING;
  public static String PropertiesSchema_PROPERTY_BINDING__PROPERTY;
  public static String PropertiesSchema_PROPERTY_BINDING__RENDERER;

  public static String PropertiesSchema_GROUP_BINDING;
  public static String PropertiesSchema_GROUP_BINDING__GROUP;
  public static String PropertiesSchema_GROUP_BINDING__RENDERER;

  public static String PropertiesSchema_PROPERTY_RENDERER;
  public static String PropertiesSchema_PROPERTY_RENDERER__CLASS;
  public static String PropertiesSchema_PROPERTY_RENDERER__ID;

  public static String PropertiesSchema_GROUP_RENDERER;
  public static String PropertiesSchema_GROUP_RENDERER__CLASS;
  public static String PropertiesSchema_GROUP_RENDERER__ID;

  public static String PropertiesSchema_PARAMETER;
  public static String PropertiesSchema_PARAMETER__ID;
  public static String PropertiesSchema_PARAMETER__VALUE;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, WizardsSchemaConstants.class);
  }

  private WizardsSchemaConstants() {
    // nothing here
  }
}
