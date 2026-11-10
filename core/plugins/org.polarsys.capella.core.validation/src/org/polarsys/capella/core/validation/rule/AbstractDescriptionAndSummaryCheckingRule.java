/*******************************************************************************
 * Copyright (c) 2013, 2024 Thales LAS France SAS.
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
package org.polarsys.capella.core.validation.rule;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;

/**
 * This class contains the execution logic of all naming rules based on the use of regular expressions.
 */
public abstract class AbstractDescriptionAndSummaryCheckingRule extends AbstractValidationRule {

  /**
   * Verify that the rule is applicable to the given validated element
   * 
   * @param eObj
   *          the validated model element @SuppressWarnings("unused") because param is here for the sub-classes to check
   */
  protected boolean isApplicable(EObject eObj) {
    return true;
  }

  /**
   * Get a flag specifying whether the description (when true) or the summary (when false) must be checked
   */
  protected abstract boolean checkDescription();

  @Override
  public IStatus validate(IValidationContext ctx) {

    EObject eObj = ctx.getTarget();

    if (!isApplicable(eObj)) {
      return ctx.createSuccessStatus();
    }

    if (eObj instanceof CapellaElement) {

      NamedElement namedElt = (NamedElement) eObj;
      CapellaElement melodyElt = (CapellaElement) eObj;
      String name = namedElt.getName();
      String toBeChecked;

      if (checkDescription()) {
        toBeChecked = melodyElt.getDescription();
      } else {
        toBeChecked = melodyElt.getSummary();
      }

      if (toBeChecked == null || toBeChecked.trim().isEmpty() || this.startsWithValidHTMLTag(toBeChecked) == false) {
        return ctx.createFailureStatus(new Object[] { eObj.eClass().getName(), name });
      }
    }

    return ctx.createSuccessStatus();
  }

  // texts beginning with < are incorrect because interpreted by
  // MelodyAdvance as HTML tag, except valid HTML tags of course!
  private boolean startsWithValidHTMLTag(String text) {
    // HTML tags are case insensitive.
    String toBeChecked = text.trim().toLowerCase();

    if (!toBeChecked.startsWith("<")) {
      return true;
    }

    boolean isValidHTMLTag = false;

    if (toBeChecked.startsWith("<br>")) {
      isValidHTMLTag = true;
    } else if (toBeChecked.startsWith("<p")) {
      isValidHTMLTag = true;
    } else if (toBeChecked.startsWith("<div>")) {
      isValidHTMLTag = true;
    }

    return isValidHTMLTag;
  }

}
