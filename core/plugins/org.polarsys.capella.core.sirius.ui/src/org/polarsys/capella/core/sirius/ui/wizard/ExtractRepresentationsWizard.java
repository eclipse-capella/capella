/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  private TransactionalEditingDomain _domain;
  private DAnalysisSession _session;
  private List<DRepresentation> _representations;
  private AirdFileCreationWizardPage _diagramModelFilePage;
  private Resource _pickedResource;

  /**
   * Constructor.
   * @param session_p origin session.
   * @param domain_p the editing domain
   * @param movableRepresentations_p the DView instance
   */
  public ExtractRepresentationsWizard(DAnalysisSession session_p, TransactionalEditingDomain domain_p, List<DRepresentation> movableRepresentations_p) {
    _domain = domain_p;
    _session = session_p;
    _representations = movableRepresentations_p;
  }

  /**
   * Initialize the wizard.
   * @param workbench_p the workbench
   * @param selection_p the selection
   */
  public void init(IWorkbench workbench_p, IStructuredSelection selection_p) {
    setWindowTitle("Extract views"); //$NON-NLS-1$
    setNeedsProgressMonitor(true);
  }

  /**
   * Gets the editing domain of the current wizard.
   * @return The editing domain.
   */
  protected TransactionalEditingDomain getDomain() {
    return _domain;
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {

    closeRepresentations(_representations);

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
   * @param slaveAnalysis_p
   */
  private void moveRepresentations(final DAnalysis slaveAnalysis_p) {
    IRunnableWithProgress moveReps = new IRunnableWithProgress() {
      public void run(IProgressMonitor monitor_p) {
        getDomain().getCommandStack().execute(new RecordingCommand(getDomain()) {

          @Override
          protected void doExecute() {
            doMoveRepresentation(slaveAnalysis_p);
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
    } catch (final InterruptedException ex_p) {
      __logger.debug(new EmbeddedMessage(ex_p.getMessage(), IReportManagerDefaultComponents.UI));
    } catch (InvocationTargetException ex_p) {
      __logger.debug(new EmbeddedMessage(ex_p.getMessage(), IReportManagerDefaultComponents.UI));
    }
  }

  /**
   * @param op_p
   * @return
   */
  private boolean createAIRDFile(IRunnableWithProgress op_p, boolean error_p) {
    boolean errorCatch = error_p;
    try {
      getContainer().run(false, true, op_p);
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

    _domain.getCommandStack().execute(new RecordingCommand(_domain) {

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

  private void closeRepresentations(List<DRepresentation> diagrams_p) {
    IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(_session);
    if (uiSession != null) {
      for (DRepresentation representation : diagrams_p) {
        closeOpenedEditor(uiSession, representation);
      }
    }
  }

  /**
   * @param uiSession_p
   * @param representation_p
   */
  private void closeOpenedEditor(IEditingSession uiSession_p, DRepresentation representation_p) {
    IEditorPart editor = uiSession_p.getEditor(representation_p);
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
    _diagramModelFilePage = new AirdFileCreationWizardPage("DiagramModelFile", new StructuredSelection(), CapellaResourceHelper.AIRD_FILE_EXTENSION); //$NON-NLS-1$
    addPage(_diagramModelFilePage);
  }

  /**
   * return the newly created resource.
   * @return the newly created resource.
   */
  public Resource getCreatedResource() {
    return _pickedResource;
  }

  /**
   * @param slaveAnalysis_p
   */
  protected void doMoveRepresentation(final DAnalysis slaveAnalysis_p) {
    _session.addReferencedAnalysis(slaveAnalysis_p);
    for (Resource resource : _session.getSemanticResources()) {
      if (!resource.getContents().isEmpty()) {
        slaveAnalysis_p.getSemanticResources().add(new ResourceDescriptor(resource.getURI()));
      }
    }
    for (DRepresentation representation : _representations) {
      _session.moveRepresentation(slaveAnalysis_p, representation);
    }
  }

  /**
   * 
   */
  protected void doCreateResource() {
    _pickedResource = TransactionHelper.getEditingDomain(_session).getResourceSet().createResource(_diagramModelFilePage.getURI());
  }

  /**
   * @param slaveAnalysis
   */
  protected void doPrepareNewAnalysis(final DAnalysis slaveAnalysis) {
    _pickedResource.getContents().add(slaveAnalysis);
  }

  private class DiagramFileCreationOperation extends WorkspaceModifyOperation {

    public DiagramFileCreationOperation() {
      super(null);
    }

    @Override
    protected void execute(IProgressMonitor monitor_p) throws CoreException, InterruptedException {
      doCreateResource();
    }
  }
}
