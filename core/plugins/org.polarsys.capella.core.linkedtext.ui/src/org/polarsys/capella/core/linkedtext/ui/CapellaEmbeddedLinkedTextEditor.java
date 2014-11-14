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
package org.polarsys.capella.core.linkedtext.ui;

import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.ShowInContext;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.linkedtext.ui.DefaultLinkedTextContentProvider;
import org.polarsys.capella.common.linkedtext.ui.EmbeddedLinkedTextEditor;
import org.polarsys.capella.common.linkedtext.ui.EmbeddedLinkedTextEditorConfiguration;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextHyperlink;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextHyperlinkDetector;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.ICapellaModel;

import com.google.common.base.Predicate;

/**
 * An embedded LinkedTextEditor specialized for Capella. It enhances the common 
 * editor by providing a hyperlink detector that opens elements in the capella project explorer,
 * and by specializing the content assistant, proposing only AbstractNamedElements,
 * including those that are available through direct or indirect library references.
 */
public class CapellaEmbeddedLinkedTextEditor extends EmbeddedLinkedTextEditor {

  final static IHyperlinkDetector[] _hyperlinkDetectors = new IHyperlinkDetector[] {

      /*
       * This detector selects elements in project explorer when link is opened
       */
      new LinkedTextHyperlinkDetector() {
        @Override
         protected void appendLinksFor(final LinkedTextHyperlink hl_p, Collection<IHyperlink> links_p) {
           links_p.add(new IHyperlink() {
             final IRegion region = new Region(hl_p.getOffset(), hl_p.getLength());
             @Override
             public void open() {
               IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
               if (window != null){
                 IViewPart part = window.getActivePage().findView("capella.project.explorer"); //$NON-NLS-1$
                 if (part != null){
                   IShowInTarget showInTarget = (IShowInTarget) part.getAdapter(IShowInTarget.class);
                   showInTarget.show(new ShowInContext(null, new StructuredSelection(hl_p.getTarget())));
                 }
               }
             }
             @Override
             public String getTypeLabel() {
               return ""; //$NON-NLS-1$
             }
             @Override
             public String getHyperlinkText() {
               return ""; //$NON-NLS-1$
             }
             @Override
             public IRegion getHyperlinkRegion() {
               return region;
             }
           });
         }
      }
  };
  
  public CapellaEmbeddedLinkedTextEditor(Composite parent_p, int style_p) {
    super(parent_p, style_p);
  }

     /**
   * {@inheritDoc}
   */
  @Override
  protected SourceViewerConfiguration createSourceViewerConfiguration() {
    return new EmbeddedLinkedTextEditorConfiguration(){

      /**
       * {@inheritDoc}
       */
      @Override
      public IHyperlinkDetector[] getHyperlinkDetectors(ISourceViewer sourceViewer_p) {
        return _hyperlinkDetectors;
      }

      /**
       * For capella models, the content assistant also proposes elements from directly or indirectly referenced libraries.
       */
      @Override
      protected IStructuredContentProvider getCompletionProcessorContentProvider() {
        return new DefaultLinkedTextContentProvider(new Predicate<EObject>(){
          @Override
          public boolean apply(EObject arg0_p) {
            return arg0_p instanceof AbstractNamedElement;
          }
        }){
          @Override
          protected void fillAdditionalElements(EObject inputElement_p, Collection<EObject> result_p){
            IModel model = ILibraryManager.INSTANCE.getModel(inputElement_p);
            for (IModel lib : LibraryManagerExt.getAllActivesReferences(model)){
              if (lib instanceof ICapellaModel){
                Project root = ((ICapellaModel )lib).getProject(TransactionUtil.getEditingDomain(inputElement_p));
                for (TreeIterator<EObject> contents = root.eAllContents(); contents.hasNext();){
                  EObject next = contents.next();
                  if (getEObjectPredicate().apply(next)){
                    result_p.add(next);
                  }
                }
              }
            }
          }
        };
      }
    };
  }
}
