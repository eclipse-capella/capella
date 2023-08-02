/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.fragmentation.ju.messages;

public class FragmentationMessages {
  public static String abstractFragmentationTest_EObjectNotSetted = "The target EObject has not been set (yet?)";
  public static String abstractFragmentationTest_UnsupportedKindOfdRepresentation = "The DRepresentation ''{0}'' is not a DSemanticDecorator one. Please modify the test.";
  public static String abstractFragmentationTest_wrongAirdresourceAfterOps = "The DRepresentation ''{0}'' has not been moved at all or to the good AIRD resource.";
  public static String abstractFragmentTest_isAlreadyFragmented = "The element ''{0}'' is already fragmented.";
  public static String abstractFragmentTest_hasNotItsOwnResource = "The element ''{0}'' has just been fragmented but it has not its own eResource attribute set.";
  public static String abstractFragmentTest_hasNotSameNumbersOfchildrenAfterFragmentation = "The fragmentation of the element ''{0}'' change the number of its proper children";
  public static String abstractFragmentationTest_dRepresentationToMoveDoesNotMatch = "The DRepresentation ''{0}'' is not linked (directly or indirectly) to the object to fragment.  ";
  public static String abstractUnfragmentTest_isNotFragmented = "The element ''{0}'' is not fragmented.";
  public static String abstractunfragmentTest_hasItsOwnResource = " The element ''{0}'' has just been unfragmented but it has its own eResource attribute set.";
  public static String fragmentUtils_resourceDoesNotMatch = "The Object ''{0}'' is contained into the resource ''{1}'' instead of ''{2}''.";
  public static String fragmentUtils_numberOfRefDoesNotMatch = "The number of references to ''{0}'' into the resourceSet does not match.";
  public static String nullSession = "The tested session is null";
  public static String duplicatedModelElements = "There are duplicated elements in the model: {0}";
  public static String wrongFragmentsNumber = "{0} fragments number is {1} whereas {2} are expected.";
  public static String wrongTablesDiagramsNumber = "{0} fragment has {1} tables and diagrams whereas {2} are expected";
  public static String errorInGetFile = "cannot get the file at the following path: {0}";
  public static String wrongModelElementsNumber = "{0} fragment has {1} model elements in semantic model elements whereas {2} are expected";
  public static String resourceStatusNotSync = "The resource {0} is in {1}. That makes the session in dirty.";
  public static String wrongNumberOfDiagramElements = "{0} diagram has {1} elements whereas {2} are expected";
  public static String wrongNumberOfColumns = "{0} table has {1} columns whereas {2} are expected";
  public static String wrongNumberOfLines = "{0} table has {1} lines whereas {2} are expected";
  
}
