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

package org.polarsys.capella.common.linkedtext.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.viewers.IStructuredContentProvider;

public class LinkedTextCompletionProcessor implements IContentAssistProcessor, ICompletionListener {
 
  private final IStructuredContentProvider _contentProvider;
  
  private boolean sessionStarted = false;
  private int sessionStartOffset = 0;
  
  public LinkedTextCompletionProcessor(IStructuredContentProvider provider){
    _contentProvider = provider;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICompletionProposal[] computeCompletionProposals(final ITextViewer viewer, final int offset) {
    ICompletionProposal[] result = null;
    LinkedTextDocument doc = (LinkedTextDocument) viewer.getDocument();

    if (sessionStarted){
      sessionStarted = false;
      sessionStartOffset = offset;
    }

    try {
      if (sessionStartOffset <= offset){
        String prefix = viewer.getDocument().get(sessionStartOffset, offset - sessionStartOffset).toLowerCase();
        List<ICompletionProposal> res = new ArrayList<ICompletionProposal>();
        if (_contentProvider != null){
          for (Object element : _contentProvider.getElements(doc.getDocumentBase())){
            String text = doc.getLabelProvider().getText(element);
            if (text != null && text.length() > 0){
              if (prefix.length() > 0 && !text.toLowerCase().contains(prefix)){
                continue;
              }
              res.add(new LinkedTextCompletionProposal(doc.getLabelProvider(), element, sessionStartOffset, offset, null, null));
            }
          }
        }
        if (res.size() > 0 ){
          result = res.toArray(new ICompletionProposal[res.size()]);
        }
      }
    } catch (BadLocationException exception) {
      exception.printStackTrace();
      //FIXME
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public char[] getCompletionProposalAutoActivationCharacters() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public char[] getContextInformationAutoActivationCharacters() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getErrorMessage() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContextInformationValidator getContextInformationValidator() {
    return null;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void assistSessionStarted(ContentAssistEvent event) {
    sessionStarted = true;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void assistSessionEnded(ContentAssistEvent event) {
    sessionStarted = false;
    sessionStartOffset = 0;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(ICompletionProposal proposal, boolean smartToggle) {
    /**/
  }

}