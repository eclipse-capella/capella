/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.analysis.messages"; //$NON-NLS-1$
  public static String ABServices_UnallocatedFunctions;
  public static String ABServices_UnallocatedRoles;
  public static String Diagram_TID_Implemented;
  public static String Diagram_TID_Provided;
  public static String Diagram_TID_Required;
  public static String Diagram_TID_Used;
  public static String InformationServices_to;
  public static String InformationServices_Undefined;
  public static String InformationServices_PackageDependency_Title;
  public static String InformationServices_PackageDependency_Message;
  public static String CapellaServices_NavigateOpen;
  public static String CapellaServices_NavigateUnnamed;
  public static String SequenceDiagramServices_0;
  public static String SequenceDiagramServices_1;
  public static String SequenceDiagramServices_2;
  public static String SequenceDiagramServices_3;
  public static String OpenRelatedDiagram_CreateNew;
  public static String OpenRelatedDiagram_Message;
  public static String OpenRelatedDiagram_NewName;
  public static String CreateRepresentationLabel;
  public static String FunctionalChainServices_12;
  public static String FunctionalChainServices_13;
  public static String FunctionalChainServices_14;
  public static String FunctionalChainServices_15;
  public static String InformationServices_Suffix_Ordered;
  public static String InformationServices_Suffix_NonUnique;
  public static String InformationServices_Suffix_NonComposite;
  public static String TitleBlockServices_DefaultCellName;
  public static String TitleBlockServices_DisableFiltersDialog_Title;
  public static String TitleBlockServices_DisableFiltersDialog_Message;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //nothing to do
  }
}
