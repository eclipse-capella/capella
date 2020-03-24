/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common;

import org.eclipse.osgi.util.NLS;

/**
 */
public class ProjectionMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.projection.common.messages"; //$NON-NLS-1$

  public static String RetrieveElement;
  public static String RetrieveElementNullElement;

  public static String ElementTransitioned;
  public static String ElementNotTransitioned;
  public static String ElementMoved;

  public static String EndNotTransitioned;
  public static String EventNotTransitioned;
  public static String RelatedFunctionalExchangeConveyNoExchangeItem;
  public static String RelatedComponentExchangeConveyNoExchangeItem;
  public static String RelatedFunctionalExchangeConveyNoInterface;
  public static String RelatedComponentExchangeConveyNoInterface;

  public static String Null;

  public static String Transition_UI_Cancel;
  public static String Activity_Transformation;

  public static String transition_title;
  public static String transition_processing;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, ProjectionMessages.class);
  }

  private ProjectionMessages() {
    //Nothing to do
  }
}
