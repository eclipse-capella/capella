/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.fragmentation.ju.messages;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.test.fragmentation.ju.Activator;

/**
 * Messages externalization for this plugin
 */
public class FragmentationMessages extends NLS {

  private static final String BUNDLE_NAME = Activator.PLUGIN_ID + ".messages.fragmentationmessages"; //$NON-NLS-1$

  public static String abstractFragmentationTest_EObjectNotSetted;
  public static String abstractFragmentationTest_UnsupportedKindOfdRepresentation;
  public static String abstractFragmentationTest_wrongAirdresourceAfterOps;

  public static String abstractFragmentTest_DefaultDescription;
  public static String abstractFragmentTest_DefaultReadableName;

  public static String abstractFragmentTest_isAlreadyFragmented;
  public static String abstractFragmentTest_hasNotItsOwnResource;
  public static String abstractFragmentTest_hasNotSameNumbersOfchildrenAfterFragmentation;
  public static String abstractFragmentationTest_dRepresentationToMoveDoesNotMatch;
  public static String abstractFragmentTest_someReferencesLost;

  public static String abstractUnfragmentTest_DefaultDescription;
  public static String abstractUnfragmentTest_DefaultReadableName;

  public static String abstractUnfragmentTest_isNotFragmented;
  public static String abstractunfragmentTest_hasItsOwnResource;

  public static String fragmentUtils_resourceDoesNotMatch;
  public static String fragmentUtils_numberOfRefDoesNotMatch;

  public static String openFragmentSession;
  public static String nullSession;

  public static String checkModelIntegrityTest;
  public static String duplicatedModelElements;

  public static String checkLoadedFragmentsNumberTest;
  public static String wrongFragmentsNumber;

  public static String checkFragmentTablesDiagramsNumberTest;
  public static String wrongTablesDiagramsNumber;
  public static String errorInGetFile;

  public static String checkFragmentModelElementsNumberTest;
  public static String wrongModelElementsNumber;

  public static String fragmentObject;
  public static String unfragmentObject;
  public static String saveAndClose;
  public static String save;
  public static String close;
  public static String retrieveRepresentationElementsNumber;
  public static String openAndCheckAllSessionRepresentations;
  public static String resourceStatusNotSync;
  public static String wrongNumberOfDiagramElements;
  public static String wrongNumberOfColumns;
  public static String wrongNumberOfLines;

  public static String SF11airdName;
  public static String SF11m2Name;
  public static String SF12airdName;
  public static String SF12m2Name;

  public static String fragmentsFolder;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, FragmentationMessages.class);
  }

  /**
   * Constructor.
   */
  private FragmentationMessages() {
    // Do nothing.
  }
}
