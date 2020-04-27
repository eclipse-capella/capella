/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.polarsys.capella.common.model.label.LabelRetriever;

public class LinkedTextCompletionProposal implements ICompletionProposal {

    private final String _displayString;
    private final IContextInformation _contextInformation;
    private final String _additionalProposalInfo;
    private final Object _target;
    private final int _startOffset;
    private final int _endOffset;
    private final ILabelProvider _labelProvider;

    /**
     * Creates a new linked text completion proposal.
     *
     * @param target the target object for the hyperlink
     * @param endOffset the offset of the text to be replaced
     * @param contextInformation the context information associated with this proposal
     * @param additionalProposalInfo the additional information associated with this proposal
     */
    public LinkedTextCompletionProposal(ILabelProvider labelProvider, Object target, int startOffset, int endOffset, IContextInformation contextInformation, String additionalProposalInfo) {
      _labelProvider = labelProvider;
      _contextInformation = contextInformation;
      _additionalProposalInfo = additionalProposalInfo;
      _target = target;
      _startOffset = startOffset;
      _endOffset = endOffset;
      if (_target instanceof EObject){
        _displayString = LabelRetriever.getFullLabel((EObject) _target);
      } else {
        _displayString = null;
      }
    }

    /*
     * @see ICompletionProposal#apply(IDocument)
     */
    @Override
    public void apply(IDocument document) {
       try {
        document.replace(_startOffset, _endOffset - _startOffset, ""); //$NON-NLS-1$
        ((LinkedTextDocument) document).insertHyperlink(LinkedTextHyperlink.create(_startOffset, _labelProvider.getText(_target).length(), _target));
      } catch (BadLocationException exception) {
        exception.printStackTrace();
      }
    }

    /*
     * @see ICompletionProposal#getSelection(IDocument)
     */
    @Override
    public Point getSelection(IDocument document) {
      return new Point(_startOffset + _labelProvider.getText(_target).length(), 0);
    }

    /*
     * @see ICompletionProposal#getContextInformation()
     */
    @Override
    public IContextInformation getContextInformation() {
      return _contextInformation;
    }

    /*
     * @see ICompletionProposal#getImage()
     */
    @Override
    public Image getImage() {
      return _labelProvider.getImage(_target);
    }

    /*
     * @see ICompletionProposal#getDisplayString()
     */
    @Override
    public String getDisplayString() {
      return _displayString != null ? _displayString : _labelProvider.getText(_target);
    }

    /*
     * @see ICompletionProposal#getAdditionalProposalInfo()
     */
    @Override
    public String getAdditionalProposalInfo() {
      return _additionalProposalInfo;
    }

}
