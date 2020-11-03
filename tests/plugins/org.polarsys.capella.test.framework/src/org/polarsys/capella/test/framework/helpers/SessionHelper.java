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
package org.polarsys.capella.test.framework.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.input.ActivityExplorerEditorInput;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.core.libraries.model.AbstractCapellaModel;

/**
 *
 */
public class SessionHelper {

  public static void saveSession(EObject context) {
    saveSession(context, new NullProgressMonitor());
  }

  public static void saveSession(AbstractCapellaModel context) {
    saveSession(context, new NullProgressMonitor());
  }

  public static void saveSession(AbstractCapellaModel model, IProgressMonitor monitor) {
    saveSession(model.getProject(model.getEditingDomain()), monitor);
  }

  public static void saveSession(Session session) {
    session.save(new NullProgressMonitor());
  }

	public static void saveSession(EObject context, IProgressMonitor monitor) {
	  SessionManager.INSTANCE.getSession(context).save(monitor);
	}

  /**
   * Close all editors connected with this Session
   * @param session : the target session
   * @param save : editors should be saved?
   */
  public static void closeEditors(final Session session, final boolean save) {
    // FIXME CloseEditorsCommand has changed. Please adapt the code
    Assert.isNotNull(session);

    IWorkbenchPage iwp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

    // Editor references to close.
    List<IEditorReference> editorReferencesToClose = new ArrayList<IEditorReference>(0);

    for (IEditorReference editorReference : iwp.getEditorReferences()) {
      IEditorInput editorReferenceInput;
      try {
        editorReferenceInput = editorReference.getEditorInput();
        if (session instanceof DAnalysisSession) { // should be, just in case of
          // Get the analysis resources.
          Collection<Resource> analysisResources = getAllAirdResources(session);
          // Loop over theses ones to match URIs.
          for (Resource resource : analysisResources) {
            URI uri = resource.getURI();
            URI trimFragment = getTrimFragment(editorReferenceInput);
            if ((uri != null) && uri.equals(trimFragment)) {
              editorReferencesToClose.add(editorReference);
              break;
            }
          }
        }
      } catch (PartInitException e) {
        // Do nothing
      }
    }

    if (!editorReferencesToClose.isEmpty()) {
      iwp.closeEditors(editorReferencesToClose.toArray(new IEditorReference[editorReferencesToClose.size()]), save);
    }
    GuiActions.flushASyncGuiThread();
  }

  /**
   * Get all aird resources contained in specified session.
   * @param session
   * @return a not <code>null</code> collection.
   */
  public static Collection<Resource> getAllAirdResources(Session session) {
    Collection<Resource> allAnalysisResources = new HashSet<Resource>(session.getReferencedSessionResources());
    allAnalysisResources.add(session.getSessionResource());
    return allAnalysisResources;
  }

  private static URI getTrimFragment(IEditorInput editorReferenceInput) {
    URI trimFragment;
    if(editorReferenceInput instanceof ActivityExplorerEditorInput){
      trimFragment = ((ActivityExplorerEditorInput)editorReferenceInput).getSession().getSessionResource().getURI().trimFragment();
    }else{
      trimFragment = ((URIEditorInput) editorReferenceInput).getURI().trimFragment();
    }
    return trimFragment;
  }
}
