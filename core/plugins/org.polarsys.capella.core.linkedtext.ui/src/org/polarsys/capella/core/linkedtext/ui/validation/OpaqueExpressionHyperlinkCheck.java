/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.linkedtext.ui.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextDocument;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextHyperlink;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.model.helpers.ConstraintExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * Validates linked text in opaque expressions for dangling (unresolvable) hyperlinks
 */
public class OpaqueExpressionHyperlinkCheck extends AbstractValidationRule {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    OpaqueExpression oe = (OpaqueExpression) ctx.getTarget();
    for (int i = 0; i < oe.getLanguages().size(); i++) {
      if (ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT.equals(oe.getLanguages().get(i))) {
        if (hasDanglingReferences(oe, oe.getBodies().get(i))) {
          return ctx.createFailureStatus(EObjectLabelProviderHelper.getText(oe));
        }
      }
    }
    return ctx.createSuccessStatus();
  }

  /**
   * @param documentBase
   * @param body
   * @return
   */
  private boolean hasDanglingReferences(OpaqueExpression documentBase, final String body) {

    CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput.Readonly(documentBase, body);
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