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
  
  public LinkedTextCompletionProcessor(IStructuredContentProvider provider_p){
    _contentProvider = provider_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICompletionProposal[] computeCompletionProposals(final ITextViewer viewer_p, final int offset_p) {
    ICompletionProposal[] result = null;
    LinkedTextDocument doc = (LinkedTextDocument) viewer_p.getDocument();

    if (sessionStarted){
      sessionStarted = false;
      sessionStartOffset = offset_p;
    }

    try {
      if (sessionStartOffset <= offset_p){
        String prefix = viewer_p.getDocument().get(sessionStartOffset, offset_p - sessionStartOffset).toLowerCase();
        List<ICompletionProposal> res = new ArrayList<ICompletionProposal>();
        if (_contentProvider != null){
          for (Object element : _contentProvider.getElements(doc.getDocumentBase())){
            String text = doc.getLabelProvider().getText(element);
            if (text != null && text.length() > 0){
              if (prefix.length() > 0 && !text.toLowerCase().contains(prefix)){
                continue;
              }
              res.add(new LinkedTextCompletionProposal(doc.getLabelProvider(), element, sessionStartOffset, offset_p, null, null));
            }
          }
        }
        if (res.size() > 0 ){
          result = res.toArray(new ICompletionProposal[res.size()]);
        }
      }
    } catch (BadLocationException exception_p) {
      exception_p.printStackTrace();
      //FIXME
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContextInformation[] computeContextInformation(ITextViewer viewer_p, int offset_p) {
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
  public void assistSessionStarted(ContentAssistEvent event_p) {
    sessionStarted = true;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void assistSessionEnded(ContentAssistEvent event_p) {
    sessionStarted = false;
    sessionStartOffset = 0;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void selectionChanged(ICompletionProposal proposal_p, boolean smartToggle_p) {
    /**/
  }

}