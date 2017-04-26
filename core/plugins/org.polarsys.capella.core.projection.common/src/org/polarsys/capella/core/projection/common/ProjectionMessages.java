/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.projection.common.projectionMessages"; //$NON-NLS-1$

  public static String RetrieveElement;
  public static String RetrieveElementNullElement;
  public static String SubFunctionAlreadyTransitioned;

  public static String ContainerNotTransitioned;
  public static String BothBoundNotTransitioned;
  public static String TargetBoundNotTransitioned;
  public static String SourceBoundNotTransitioned;
  public static String ElementTransitionedAndAttached;
  public static String ElementTransitioned;
  public static String ElementNotTransitioned;
  public static String ElementMoved;
  public static String ElementAttached;

  public static String SourceBoundHasBeenChanged;
  public static String TargetBoundHasBeenChanged;

  public static String OutOfScope;
  public static String SourceNull;
  public static String TargetNull;
  public static String IsFinal;

  public static String InvolvedElementNotTransitioned;

  public static String ComponentWasTransitionedToPackage;
  public static String TypeNotTransitioned;
  public static String TypeTransitionedToPackage;
  public static String ComponentAlreadyTransitionedToComponent;

  public static String ConnectionMovedToBetterParent;
  public static String ContainerTransitionedToPackage;

  public static String ElementTransitionedToExistingElement;

  public static String EndNotTransitioned;
  public static String EventNotTransitioned;
  public static String RelatedFunctionalExchangeConveyNoExchangeItem;
  public static String RelatedConnectionConveyNoExchangeItem;
  public static String RelatedFunctionalExchangeIsNotExternal;

  public static String SuperNull;
  public static String SubNull;
  public static String Null;

  public static String Transition_UI_Cancel;
  public static String Activity_Transformation;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, ProjectionMessages.class);
  }

  private ProjectionMessages() {
    //Nothing to do
  }
}
