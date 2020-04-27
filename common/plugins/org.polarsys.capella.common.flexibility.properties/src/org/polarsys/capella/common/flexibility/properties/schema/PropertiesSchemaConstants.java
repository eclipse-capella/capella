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
package org.polarsys.capella.common.flexibility.properties.schema;

import org.eclipse.osgi.util.NLS;

/**
 */
public class PropertiesSchemaConstants extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.flexibility.properties.schema.messages"; //$NON-NLS-1$

  @Deprecated
  public static String PropertiesSchema_ARGUMENT;
  @Deprecated
  public static String PropertiesSchema_ARGUMENT__ID;
  @Deprecated
  public static String PropertiesSchema_ARGUMENT__VALUE;
  public static String PropertiesSchema_PARAMETER;
  public static String PropertiesSchema_PARAMETER__ID;
  public static String PropertiesSchema_PARAMETER__VALUE;
  public static String PropertiesSchema_GROUP;
  public static String PropertiesSchema_GROUP__ID;
  public static String PropertiesSchema_GROUP__NAME;
  public static String PropertiesSchema_GROUP__PARENT;
  @Deprecated
  public static String PropertiesSchema_GROUP__PARENT_ID;
  @Deprecated
  public static String PropertiesSchema_ID;
  public static String PropertiesSchema_SCHEMA_ID;

  public static String PropertiesSchema_OPTION;
  public static String PropertiesSchema_OPTION__ENABLED;
  public static String PropertiesSchema_OPTION__ID;
  public static String PropertiesSchema_OPTION__NAME;
  public static String PropertiesSchema_OPTION__DESCRIPTION;
  public static String PropertiesSchema_OPTION__VALUE;
  public static String PropertiesSchema_PROPERTIES;
  public static String PropertiesSchema_PROPERTIES__ID;
  public static String PropertiesSchema_INHERITANCE;
  public static String PropertiesSchema_INHERITANCE__PROPERTIES;
  @Deprecated
  public static String PropertiesSchema_INHERITANCE__PROPERTIESID;
  public static String PropertiesSchema_PROPERTY;
  public static String PropertiesSchema_PROPERTY__CLASS;
  public static String PropertiesSchema_PROPERTY__ENABLED;
  public static String PropertiesSchema_PROPERTY__GROUP;
  @Deprecated
  public static String PropertiesSchema_PROPERTY__GROUP_ID;

  public static String PropertiesSchema_PROPERTY__ID;
  public static String PropertiesSchema_PROPERTY__NAME;
  public static String PropertiesSchema_PROPERTY__DESCRIPTION;

  /** Define preference scope qualifier used with IScopeContext */
  public static String PropertiesSchema_PROPERTY_PREFERENCE__SCOPE;

  /** Allows to define another ID than IProperty.getId() to be retrieved from preferences */
  public static String PropertiesSchema_PROPERTY_PREFERENCE__PREFERENCE_ID;

  /** Default value if preference is not found (should be equals to PreferenceInitializer value) */
  public static String PropertiesSchema_PROPERTY_PREFERENCE__DEFAULT;

  public static String PropertiesSchema_ESTRUCTURAL_FEATURE_PROPERTY__EFEATURE;
  public static String PropertiesSchema_STRING_PROPERTY__EMPTY_IS_VALID;

  public static String PropertiesSchema_ESTRUCTURAL_FEATURE_PROPERTY__ECLASS;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, PropertiesSchemaConstants.class);
  }

  private PropertiesSchemaConstants() {
    // nothing here
  }
}
