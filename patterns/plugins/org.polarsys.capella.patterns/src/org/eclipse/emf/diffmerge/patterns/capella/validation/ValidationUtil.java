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
package org.eclipse.emf.diffmerge.patterns.capella.validation;

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;


/**
 * Utility class providing a few basic services related to model validation.
 */
public class ValidationUtil {
  
  /**
   * Return the set of messages which are contained within the given
   * Diagnostic, inclusive
   * @see ValidationUtil#buildDiagnosticMessages(Diagnostic, boolean)
   * @return a non-null, potentially empty array
   */
  public static String[] buildDiagnosticMessages(Diagnostic root_p) {
    return buildDiagnosticMessages(root_p, true);
  }
  
  /**
   * Return the set of messages which are contained within the given
   * Diagnostic
   * @param includeSource_p whether the message of the given Diagnostic
   *        must be included
   * @return a non-null, potentially empty array
   */
  public static String[] buildDiagnosticMessages(Diagnostic root_p,
      boolean includeSource_p) {
    List<Diagnostic> diags = root_p.getChildren();
    String[] result = new String[diags.size()];
    int i = 0;
    for(Diagnostic diag : diags) {
      result[i] = !includeSource_p? diag.getMessage():
        diag.getSource() + ": " + diag.getMessage(); //$NON-NLS-1$
      i++;
    }
    return result;
  }
  
  /**
   * Return a single String message describing the whole content of the
   * given Diagnostic
   * @return a non-null String
   */
  public static String buildSingleDiagnosticMessage(Diagnostic root_p) {
    String[] messages = buildDiagnosticMessages(root_p, false);
    StringBuilder builder = new StringBuilder();
    for(String msg : messages) {
      builder.append(msg);
      builder.append(" - "); //$NON-NLS-1$
    }
    return builder.toString();
  }
  
}
