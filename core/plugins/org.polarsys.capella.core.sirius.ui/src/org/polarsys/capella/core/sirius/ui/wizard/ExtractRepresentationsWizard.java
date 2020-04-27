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
package org.polarsys.capella.core.sirius.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * This wizard asks the user for which representations he wants to externalize and create the new .aird files.
 */
public class ExtractRepresentationsWizard extends Wizard {
  // Log4j reference logger.
  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  private TransactionalEditingDomain domain;
  private DAnalysisSession session;
  private List<DRepresentation> representations;
  private AirdFileCreationWizardPage diagramModelFilePage;
  private Resource pickedResource;

  /**
   * Constructor.
   * @param session origin session.
   * @param domain the editing domain
   * @param movableRepresentations the DView instance
   */
  public ExtractRepresentationsWizard(DAnalysisSession session, TransactionalEditingDomain domain, List<DRepresentation> movableRepresentations) {
    this.domain = domain;
    this.session = session;
    this.representations = movableRepresentations;
  }

  /**
   * Initialize the wizard.
   * @param workbench the workbench
   * @param selection the selection
   */
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    setWindowTitle("Extract views"); //$NON-NLS-1$
    setNeedsProgressMonitor(true);
  }

  /**
   * Gets the editing domain of the current wizard.
   * @return The editing domain.
   */
  protected TransactionalEditingDomain getDomain() {
    return domain;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {

    closeRepresentations(representations);

    IRunnableWithProgress op = new DiagramFileCreationOperation();
    boolean errorCatch = false;
    errorCatch = createAIRDFile(op, errorCatch);
    if (errorCatch) {
      return false;
    }

    DAnalysis slaveAnalysis = prepareNewAnalysis();
    moveRepresentations(slaveAnalysis);

    return true;
  }

  /**
   * @param slaveAnalysis
   */
  private void moveRepresentations(final DAnalysis slaveAnalysis) {
    IRunnableWithProgress moveReps = new IRunnableWithProgress() {
      public void run(IProgressMonitor monitor) {
        getDomain().getCommandStack().execute(new RecordingCommand(getDomain()) {

          @Override
          protected void doExecute() {
            doMoveRepresentation(slaveAnalysis);
          }

          @Override
          public boolean canUndo() {
            /*
             * We don't want to let people undo that !
             */
            return false;
          }
        });
      }
    };
    try {
      getContainer().run(false, true, moveReps);
    } catch (final InterruptedException ex) {
      logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (InvocationTargetException ex) {
      logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.UI));
    }
  }

  /**
   * @param op
   * @return
   */
  private boolean createAIRDFile(IRunnableWithProgress op, boolean error) {
    boolean errorCatch = error;
    try {
      getContainer().run(false, true, op);
    } catch (InterruptedException e) {
      errorCatch = true;
      // return false;
    } catch (InvocationTargetException e) {
      if (e.getTargetException() instanceof CoreException) {
        CoreException targetException = (CoreException) e.getTargetException();
        ErrorDialog.openError(getContainer().getShell(), "Error creating resource", null, targetException.getStatus()); //$NON-NLS-1$
      } else {
        SiriusTransPlugin.getPlugin().error("Error creating aird session data", e.getTargetException()); //$NON-NLS-1$
      }
      // return false;
      errorCatch = true;
    }
    return errorCatch;
  }

  /**
   * @return
   */
  private DAnalysis prepareNewAnalysis() {
    final DAnalysis slaveAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();

    domain.getCommandStack().execute(new RecordingCommand(domain) {

      @Override
      protected void doExecute() {
        doPrepareNewAnalysis(slaveAnalysis);
      }

      @Override
      public boolean canUndo() {
        return false;
      }

    });
    return slaveAnalysis;
  }

  private void closeRepresentations(List<DRepresentation> diagrams) {
    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
    if (uiSession != null) {
      for (DRepresentation representation : diagrams) {
        closeOpenedEditor(uiSession, representation);
      }
    }
  }

  /**
   * @param uiSession
   * @param representation
   */
  private void closeOpenedEditor(IEditingSession uiSession, DRepresentation representation) {
    IEditorPart editor = uiSession.getEditor(representation);
    if (editor != null) {
      editor.getEditorSite().getPage().closeEditor(editor, false);
    }
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  @Override
  public void addPages() {
    List<String> extensions = new ArrayList<String>();
    extensions.add(SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
    diagramModelFilePage = new AirdFileCreationWizardPage("DiagramModelFile", new StructuredSelection(), CapellaResourceHelper.AIRD_FILE_EXTENSION); //$NON-NLS-1$
    addPage(diagramModelFilePage);
  }

  /**
   * return the newly created resource.
   * @return the newly created resource.
   */
  public Resource getCreatedResource() {
    return pickedResource;
  }

  /**
   * @param slaveAnalysis
   */
  protected void doMoveRepresentation(final DAnalysis slaveAnalysis) {
    session.addReferencedAnalysis(slaveAnalysis);
    for (Resource resource : session.getSemanticResources()) {
      if (!resource.getContents().isEmpty()) {
        slaveAnalysis.getSemanticResources().add(new ResourceDescriptor(resource.getURI()));
      }
    }
    for (DRepresentation representation : representations) {
      DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
      if (representationDescriptor != null) {
        session.moveRepresentation(slaveAnalysis, representationDescriptor);
      }
    }
  }

  /**
   * 
   */
  protected void doCreateResource() {
    pickedResource = TransactionHelper.getEditingDomain(session).getResourceSet().createResource(diagramModelFilePage.getURI());
  }

  /**
   * @param slaveAnalysis
   */
  protected void doPrepareNewAnalysis(final DAnalysis slaveAnalysis) {
    pickedResource.getContents().add(slaveAnalysis);
  }

  private class DiagramFileCreationOperation extends WorkspaceModifyOperation {

    public DiagramFileCreationOperation() {
      super(null);
    }

    @Override
    protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
      doCreateResource();
    }
  }
}
