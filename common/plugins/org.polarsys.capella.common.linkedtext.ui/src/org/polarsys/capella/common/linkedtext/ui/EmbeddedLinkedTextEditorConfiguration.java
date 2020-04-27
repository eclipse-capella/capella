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

import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.themes.ITheme;
import org.eclipse.ui.themes.IThemeManager;

import com.google.common.base.Predicates;

public class EmbeddedLinkedTextEditorConfiguration extends SourceViewerConfiguration {
  
  /**
   * The theme key for the hyperlink color
   */
  public static final String HYPERLINK_COLOR = "org.polarsys.capella.common.linkedtext.ui.linkedTextHyperlinkColor"; //$NON-NLS-1$
  
  /**
   * The theme key for the text font
   */
  public static final String FONT = "org.polarsys.capella.common.linkedtext.ui.font"; //$NON-NLS-1$
  
  /**
   * {@inheritDoc}
   */
  @Override
  public IContentAssistant getContentAssistant(final ISourceViewer sourceViewer) {
    ContentAssistant ca = new ContentAssistant();
    final LinkedTextCompletionProcessor completionProcessor = new LinkedTextCompletionProcessor(getCompletionProcessorContentProvider());
    ca.setContentAssistProcessor(completionProcessor, IDocument.DEFAULT_CONTENT_TYPE);
    ca.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
    ca.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
    ca.addCompletionListener(completionProcessor);
    return ca;
  }

  /**
   * The content provider to use for content assist/hyperlink insertion.
   * Subclasses may override.
   */
  protected IStructuredContentProvider getCompletionProcessorContentProvider() {
    return new DefaultLinkedTextContentProvider(Predicates.<EObject>alwaysTrue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPresentationReconciler getPresentationReconciler(final ISourceViewer sourceViewer) {

    final PresentationReconciler reconciler = new PresentationReconciler();
    final IThemeManager manager = PlatformUI.getWorkbench().getThemeManager();
    ITheme theme = manager.getCurrentTheme();
    
    final AtomicReference<Color> hyperlinkColor = new AtomicReference<Color>(theme.getColorRegistry().get(HYPERLINK_COLOR));
    final AtomicReference<Font> font = new AtomicReference<Font>(theme.getFontRegistry().get(FONT));
    
    // FIXME removal/disposal??
    theme.addPropertyChangeListener(new IPropertyChangeListener() {
      
      @Override
      public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals(HYPERLINK_COLOR)){
          hyperlinkColor.set(manager.getCurrentTheme().getColorRegistry().get(HYPERLINK_COLOR));
          sourceViewer.invalidateTextPresentation();
        }
        else if (event.getProperty().equals(FONT)){
          font.set(manager.getCurrentTheme().getFontRegistry().get(FONT));
          sourceViewer.invalidateTextPresentation();
        }
      }
    });

    reconciler.setDamager(new IPresentationDamager() {
      @Override
      public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event, boolean documentPartitioningChanged) {
        return new Region(event.getOffset(), event.getText().length());
      }
      @Override
      public void setDocument(IDocument document) {
        /**/
      }
    }, IDocument.DEFAULT_CONTENT_TYPE);
    
    reconciler.setRepairer(new IPresentationRepairer() {
      
      private TextAttribute defaultTextAttribute = new TextAttribute(null);
      private IDocument doc;
      
      @Override
      public void setDocument(IDocument document) {
        doc = document;
      }

      @Override
      public void createPresentation(TextPresentation presentation, ITypedRegion damage) {

        // FIXME optimize? This always repairs the whole document range

        StyleRange normal = new StyleRange(0, doc.getLength(), defaultTextAttribute.getForeground(), defaultTextAttribute.getBackground());
        normal.font = font.get();
        presentation.mergeStyleRange(normal);
          
        for (LinkedTextHyperlink hl : ((LinkedTextDocument)doc).getHyperlinks()){
          StyleRange range = new StyleRange(hl.getOffset(), hl.getLength(), hyperlinkColor.get(), defaultTextAttribute.getBackground());
          if (hl.getTarget() == null){
            range.strikeout = true;
            range.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
          }
          presentation.mergeStyleRange(range);
        }
      }
    }, IDocument.DEFAULT_CONTENT_TYPE);
    return reconciler;
  }

}
