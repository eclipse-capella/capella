/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: Cedric Brun (Obeo) <cedric.brun@obeo.fr> - Initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.internal.wizards.CreateSessionResourceWizard;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.polarsys.capella.common.helpers.EcoreUtil2;

/**
 * don't know if it is really used but we have to update as of Sirius 5.0 Fill the session when a drop event comes.
 */
public class ModelDropTargetAdapter extends ViewerDropAdapter {

  private static final String ERROR_CREATING_SESSION = "Error creating session"; //$NON-NLS-1$

  /**
   * Create a new {@link ModelDropTargetAdapter}.
   * @param viewer current viewer.
   */
  protected ModelDropTargetAdapter(Viewer viewer) {
    super(viewer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validateDrop(Object target, int operation, TransferData transferType) {
    return true;
  }

  /**
   * Manage drop of session & semantic model to the Model Content view. :
   * <ol>
   * <li>For session model : open the session if not already open</li>
   * <li>For semantic model whose at least one referencing session model is selected : do nothing because it will be used by the selected referencing sessions</li>
   * <li>For semantic model whose none referencing session model is selected : propose to create a new Session model & open it</li>
   * </ol>
   * @param data the Object to drop {@inheritDoc}
   */
  @Override
  public boolean performDrop(Object data) {
    try {
      return tryToCreateSession(data);
    } catch (final PartInitException e) {
      SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
    } catch (final IOException e) {
      SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
    } catch (final InvocationTargetException e) {
      SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
    } catch (final InterruptedException e) {
      SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
    }
    return false;
  }

  // CHECKSTYLE:OFF
  private boolean tryToCreateSession(Object data) throws IOException, PartInitException, InvocationTargetException, InterruptedException {
    boolean result = false;

    // CHECKSTYLE:ON
    TreeSelection currentTreeSelection = null;
    if (data instanceof TreeSelection) {
      currentTreeSelection = (TreeSelection) data;
      final Collection<URI> uris = getURIs(currentTreeSelection);
      final Collection<URI> sessionModelURIs = filterSessionURIs(uris);
      final Collection<URI> otherURIs = new ArrayList<URI>(uris);
      otherURIs.removeAll(sessionModelURIs);

      IRunnableWithProgress operation = new WorkspaceModifyOperation() {
        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
          monitor.beginTask("Model importation", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
          openSelectedSessionModelFiles(sessionModelURIs, monitor);
          if (!otherURIs.isEmpty()) {
            createSessionModelFromSemanticModels(otherURIs, monitor);
          }
          monitor.done();
        }
      };

      ProgressMonitorDialog dialog = new ProgressMonitorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
      try {
        dialog.run(false, false, operation);
      } catch (InvocationTargetException e) {
        SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
      } catch (InterruptedException e) {
        SiriusPlugin.getDefault().error(ERROR_CREATING_SESSION, e);
      }

    }
    return result;
  }

  private Collection<URI> getURIs(TreeSelection currentTreeSelection) {
    Collection<URI> result = new ArrayList<URI>(0);
    final Iterator<?> it = currentTreeSelection.iterator();
    while (it.hasNext()) {
      Object obj = it.next();
      if (obj instanceof IFile) {
        IFile file = (IFile) obj;
        result.add(EcoreUtil2.getURI(file));
      }
    }
    return result;
  }

  private Collection<URI> filterSessionURIs(Collection<URI> uris) {
    Collection<URI> sessionURIs = new ArrayList<URI>(0);
    for (URI uri : uris) {
      String fileExtension = uri.fileExtension();
      if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(fileExtension)) {
        sessionURIs.add(uri);
      }
    }
    return sessionURIs;
  }

  protected void openSelectedSessionModelFiles(Collection<URI> sessionModelURIs, IProgressMonitor monitor) {
    monitor.subTask("Open selected Session model"); //$NON-NLS-1$
    for (URI sessionModelURI : sessionModelURIs) {
      Session session = SessionManager.INSTANCE.getSession(sessionModelURI, monitor);
      monitor.worked(1);
      if (session == null) {
        monitor.subTask("Load session"); //$NON-NLS-1$
        session = SessionManager.INSTANCE.getSession(sessionModelURI, monitor);
        monitor.worked(1);
        session.save(new NullProgressMonitor());
        monitor.done();
      }
      if (!session.isOpen()) {
        monitor.subTask("Open session " + session.getID()); //$NON-NLS-1$
        session.open(new NullProgressMonitor());
        monitor.done();
      }
      ViewpointSelection.openViewpointsSelectionDialog(session);
      monitor.worked(1);
      final Collection<DRepresentation> startupCandidates = SessionHelper.findAllStartupCandidates(session);
      final Collection<DRepresentation> selection = SessionHelper.selectRepresentationsToOpen(null, startupCandidates);
      for (final DRepresentation repr : selection) {
        DialectUIManager.INSTANCE.openEditor(session, repr, monitor);
        monitor.worked(1);
      }
    }
    monitor.done();
  }

  protected void createSessionModelFromSemanticModels(Collection<URI> otherURIs, IProgressMonitor monitor) {
    monitor.subTask("Open selected Session model"); //$NON-NLS-1$
    for (URI semanticModelURI : otherURIs) {
      IPath path = new Path(semanticModelURI.toPlatformString(true));
      final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
      final CreateSessionResourceWizard wizard = new CreateSessionResourceWizard(new StructuredSelection(file));
      final WizardDialog dlg = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
      dlg.create();
      dlg.getShell().setText("Create a new Session"); //$NON-NLS-1$
      dlg.setTitle("Enter session file name"); //$NON-NLS-1$
      dlg.setBlockOnOpen(true);
      if (dlg.open() == Window.OK) {
        final Session session = wizard.getCreatedSession();
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticModelURI, monitor);
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
        ViewpointSelection.openViewpointsSelectionDialog(session);
        final Collection<DRepresentation> startupCandidates = SessionHelper.findAllStartupCandidates(session);
        final Collection<DRepresentation> selection = SessionHelper.selectRepresentationsToOpen(null, startupCandidates);
        for (final DRepresentation repr : selection) {
          DialectUIManager.INSTANCE.openEditor(session, repr, monitor);
        }
      }
    }
    monitor.done();
  }
}
