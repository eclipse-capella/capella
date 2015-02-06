/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 */
public class HelperMessages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.framework.helpers.messages"; //$NON-NLS-1$

  public static String invalidInteger;
  public static String wrongEObjectType;
  public static String wrongEObjectTypeWithInheritance;

  //
  // DiagramHelper Messages
  //
  public static String failedIsSynchronized;
  public static String failedIsUnSynchronized;
  public static String noContextualElement;
  public static String wrongNumberOfContextualElement;
  public static String wrongContextualElement;
  //
  // FilterOnDiagramHelper Messages
  //
  public static String mappingEmpty;
  public static String elementNotHiddenPropertly;
  public static String elementShouldBeVisible;
  public static String evaluationExceptionForExpression;
  public static String emptyFilters;
  public static String elementNotCollapsedPropertly;
  public static String filterNotFound;
  public static String filterHideTestDesc;
  public static String filterShowTestDesc;
  public static String expectedAmountNotFound;

  public static String failToOpenDDiagram;
  public static String diagramNotContainedInSession;

  //
  // SiriusElementHelper Messages
  //
  public static String multiEdgeTargetsError;
  public static String noEdgeDetected;
  // EdgeTarget comparison messages
  public static String edgeTargetComparisonFalse;
  public static String wrongElementName;

  //
  // DiagramStyleHelper Messages
  //
  public static String wrongLabelSize;
  public static String wrongLabelFormat;
  public static String wrongBackgroundColor;
  public static String wrongForegroundColor;
  public static String wrongLabelColor;
  public static String wrongBorderColor;
  public static String wrongColor;
  public static String noInstanceOfWorkspaceImageSpec;
  public static String wrongStrokeColor;
  public static String wrongEdgeSize;
  public static String wrongLineStyle;
  public static String wrongBendpoints;
  public static String wrongNoteFontName;
  public static String wrongNoteTextAlignment;
  public static String noNoteTextAlignment;
  public static String wrongNoteLineStyle;
  public static String noNoteLineStyle;
  public static String wrongConnectorStyleRouting;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, HelperMessages.class);
  }

  /**
   * Constructor.
   */
  private HelperMessages() {
    // Do nothing.
  }
}
