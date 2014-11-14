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
package org.polarsys.capella.core.linkedtext.ui.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextDocument;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextHyperlink;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.linkedtext.ui.CapellaLinkedTextConstants;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Validates linked text in opaque expressions for dangling (unresolvable) 
 * hyperlinks
 */
public class OpaqueExpressionHyperlinkCheck extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    OpaqueExpression oe = (OpaqueExpression) ctx_p.getTarget();
    for (int i = 0; i < oe.getLanguages().size(); i++) {
      if (CapellaLinkedTextConstants.OPAQUE_EXPRESSION_LINKED_TEXT.equals(oe.getLanguages().get(i))) {
        if (hasDanglingReferences(oe, oe.getBodies().get(i))) {
          return ctx_p.createFailureStatus(oe);
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

  /**
   * @param string_p
   * @return
   */
  private boolean hasDanglingReferences(OpaqueExpression documentBase_p, final String body_p) {

    CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput.Readonly(documentBase_p, body_p);
    try {
      LinkedTextDocument doc = LinkedTextDocument.load(input);
      for (LinkedTextHyperlink hl : doc.getHyperlinks()) {
        Object o = hl.getTarget();
        if (o == null) {
          return true;
        }
      }

    } finally {
      input.dispose();
    }

    return false;

  }
}