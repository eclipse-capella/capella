/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.tiger.messages"; //$NON-NLS-1$
  public static String TigerRelationshipHelper_FeatureNonApplicable;
  public static String TigerRelationshipHelper_CannotAttachNull;
  public static String TigerRelationshipHelper_ContainedBy;
  public static String TigerRelationshipHelper_ReferencedBy;
  public static String TigerRelationshipHelper_ShouldBeReferencedBy;
  public static String TigerRelationshipHelper_ShouldBeContainedBy;
  public static String TigerRelationshipHelper_UpdateAttribute;
  public static String TransfoEngine_ErrorWhileTransformation;
  public static String TransfoEngine_ErrorWhileTransformationDetailled;
  public static String TransfoEngine_ErrorWhileTransformationPostExecute;
  public static String TigerRelationshipHelper_FeatureDerivedOrNonChangeable;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing here
  }
}
