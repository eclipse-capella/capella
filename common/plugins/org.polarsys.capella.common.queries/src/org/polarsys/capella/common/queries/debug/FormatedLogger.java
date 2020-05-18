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
package org.polarsys.capella.common.queries.debug;

/**
 */
public class FormatedLogger {

  private static String tab = "  "; //$NON-NLS-1$
  private static int indentStep = 0;
  private static boolean indentMustBeDone = false;

  public static void addTextLn(Object text, Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      indent(debugGroup);
      System.out.print(text);
      carriageReturn(debugGroup);
    }
  }

  public static void addText(Object text, Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      indent(debugGroup);
      System.out.print(text);
    }
  }

  public static void carriageReturn(Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      System.out.println();
      indentMustBeDone = true;
    }
  }

  private static void indent(Integer debugGroup) {
    if (indentMustBeDone) {
      for (int i = 0; i < indentStep; i++) {
        System.out.print(tab);
      }
      indentMustBeDone = false;
    }
  }

  public static void incIndent(Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      indentStep++;
    }
  }

  public static void incIndentLn(Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      incIndent(debugGroup);
      carriageReturn(debugGroup);
    }
  }

  public static void decIndent(Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      indentStep--;
    }
  }

  public static void decIndentLn(Integer debugGroup) {
    if (Log.ACTIVE_DEBUG_GROUPS.contains(debugGroup)) {
      decIndent(debugGroup);
      carriageReturn(debugGroup);
    }
  }

}
