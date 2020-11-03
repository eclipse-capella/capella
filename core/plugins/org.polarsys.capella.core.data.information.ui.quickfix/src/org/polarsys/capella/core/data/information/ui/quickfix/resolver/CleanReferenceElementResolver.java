/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.text.BadLocationException;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextDocument;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextHyperlink;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.linkedtext.ui.CapellaEmbeddedLinkedTextEditorInput;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class CleanReferenceElementResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {

    final EObject value = getModelElements(marker).get(0);
    if (value instanceof OpaqueExpression) {
      OpaqueExpression opExpression = (OpaqueExpression) value;
      cleanDeletedHyperLink(opExpression);
      try {
        marker.delete();
      } catch (CoreException e) {
        e.printStackTrace();
      }
    }
  }

  public void cleanDeletedHyperLink(OpaqueExpression opExpression) {

    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(opExpression);
    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        EList<String> listBody = opExpression.getBodies();
        for (int i = 0; i < listBody.size(); i++) {
          String body = listBody.get(i);
          // Get all the content of text editor in the body of opaque Expression
          CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput.Readonly(opExpression,
              body);
          try {
            // Load the linked text of text editor
            LinkedTextDocument doc = LinkedTextDocument.load(input);
            for (LinkedTextHyperlink hl : doc.getHyperlinks()) {
              Object o = hl.getTarget();
              if (o == null) {
                try {
                  // Delete the text corresponding at the link to delete
                  doc.replace(hl.offset, hl.length, "");
                  // Delete the physical link with model
                  hl.delete();
                } catch (BadLocationException e) {
                  e.printStackTrace();
                }
              }
            }
            // Save the new content of body text editor
            String content = doc.saveToRaw();
            int position = i;
            // Implement write operations on opaqueExpression,
            opExpression.getBodies().set(position, content);
          } finally {
            input.dispose();
          }
        }
      }
    });

  }
}
