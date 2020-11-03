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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.system.handlers.merge.messages"; //$NON-NLS-1$
  public static String AttributeNameValueFromSource;
  public static String AttributeNameValueFromSource_Description;
  public static String AttributeSummaryValueFromSource;
  public static String AttributeSummaryValueFromSource_Description;
  public static String AttributeDescriptionValueFromSource;
  public static String AttributeDescriptionValueFromSource_Description;
  public static String EObjectCategoryFilter;
  public static String EObjectCategoryFilter_Description;
  public static String ManyToOneCategoryFilter;
  public static String ManyToOneCategoryFilter_Description;
  public static String OneToManyCategoryFilter;
  public static String OneToManyCategoryFilter_Description;
  public static String RootCategoryFilter;
  public static String RootCategoryFilter_Description;
  public static String PartPropagationCategoryFilter;
  public static String PartPropagationCategoryFilter_Description;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
