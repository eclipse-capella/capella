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
package org.polarsys.capella.core.sirius.ui.editor;

import java.lang.ref.WeakReference;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.sirius.ui.SiriusUIPlugin;
import org.polarsys.capella.core.sirius.ui.actions.OpenSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * Capella Dashboard editor input.
 */
public class CapellaDashboardEditorInput implements IEditorInput, IPersistableElement {
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  /**
   * First Analysis File tag.
   */
  private static final String FIRST_ANALYSIS_FILE_TAG = "firstAnalysisFile"; //$NON-NLS-1$
  /**
   * Session used in this editor input.
   */
  private WeakReference<Session> _sessionReference;
  /**
   * Capella project.
   */
  private WeakReference<Project> _capellaProjectReference;

  private IStatus _sessionStatus;

  /**
   * Constructor.<br>
   * This constructor is used to restore a Capella dashboard editor.
   * @param memento
   * @throws Exception
   */
  CapellaDashboardEditorInput(IMemento memento_p) {
    _sessionStatus = Status.OK_STATUS;
    loadState(memento_p);
  }

  /**
   * Constructor.
   * @param session_p
   * @param capellaProject_p
   */
  public CapellaDashboardEditorInput(Session session_p, Project capellaProject_p) {
    _sessionReference = new WeakReference<Session>(session_p);
    _capellaProjectReference = new WeakReference<Project>(capellaProject_p);
    _sessionStatus = Status.OK_STATUS;
  }

  /**
   * Dispose.
   */
  public void dispose() {
    _capellaProjectReference.clear();
    _capellaProjectReference = null;
    _sessionReference.clear();
    _sessionReference = null;
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj_p) {
    boolean result = this == obj_p;
    if (!result && (null != _sessionReference) && (obj_p instanceof CapellaDashboardEditorInput)) {
      Session session = _sessionReference.get();
      if (null != session) {
        WeakReference<Session> sessionReferenceToCompare = ((CapellaDashboardEditorInput) obj_p)._sessionReference;
        if (null != sessionReferenceToCompare) {
          result = session.equals(sessionReferenceToCompare.get());
        }
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.IEditorInput#exists()
   */
  public boolean exists() {
    boolean result = false;
    if (null != _sessionReference) {
      Session session = _sessionReference.get();
      result = (null != session) && session.isOpen();
    }
    return result;
  }

  /**
   * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
   */
  public Object getAdapter(Class adapter_p) {
    if ((null != _sessionReference) && (adapter_p == Session.class)) {
      return _sessionReference.get();
    }
    return null;
  }

  /**
   * @see org.eclipse.ui.IPersistableElement#getFactoryId()
   */
  public String getFactoryId() {
    return CapellaDashboardEditorInputFactory.ID;
  }

  /**
   * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
   */
  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  /**
   * Return the UI representation of current handled capella model.
   * @return
   */
  public String getCapellaModelUiName() {
    String result = ICommonConstants.EMPTY_STRING;
    if (null != _capellaProjectReference) {
      Project project = _capellaProjectReference.get();
      if (null != project) {
        result = EObjectLabelProviderHelper.getText(project);
      }
    }
    return result;
  }

  /**
   * Get the underlying capella project.
   * @return the capellaProject
   */
  public Project getCapellaProject() {
    Project result = null;
    if (null != _capellaProjectReference) {
      result = _capellaProjectReference.get();
    }
    return result;
  }

  /**
   * @see org.eclipse.ui.IEditorInput#getName()
   */
  public String getName() {
    return getCapellaModelUiName();
  }

  /**
   * @see org.eclipse.ui.IEditorInput#getPersistable()
   */
  public IPersistableElement getPersistable() {
    return this;
  }

  /**
   * Get the underlying session.
   * @return a not <code>null</code> instance.
   */
  public Session getSession() {
    return (Session) getAdapter(Session.class);
  }

  /**
   * @see org.eclipse.ui.IEditorInput#getToolTipText()
   */
  public String getToolTipText() {
    return getName();
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    int hashCode = super.hashCode();
    if (null != _sessionReference) {
      Session session = _sessionReference.get();
      hashCode = (null != session) ? session.hashCode() : hashCode;
    }
    return hashCode;
  }

  /**
   * Load this editor input from the memento.
   * @param memento_p
   * @throws Exception
   */
  protected void loadState(IMemento memento_p) {

    // Get from the memento the diagram file associated to session to restore.
    String firstAnalysisFile = memento_p.getString(FIRST_ANALYSIS_FILE_TAG);
    if (firstAnalysisFile == null) return;
    IFile diagramFile = FileHelper.getPlatformFile(firstAnalysisFile);

    try {
      Session session = SessionHelper.getSession(diagramFile);

      // Don't open session if already opened (bad performance)
      if (null == session) {
        // Instantiate the action responsible for opening a session.
        OpenSessionAction openSessionAction = new OpenSessionAction();
        // Disable to open the capella dashboard as we are already restoring a Capella Dashboard editor input i.e a CapellaDashboard editor.
        openSessionAction.setOpenActivityExplorer(false);
        openSessionAction.setRunInProgressService(false);
        openSessionAction.selectionChanged(new StructuredSelection(diagramFile));
        // Open the session.
        openSessionAction.run();

        if (openSessionAction.getFailedOpeningSessions().containsKey(diagramFile)) {
          IStatus status = openSessionAction.getFailedOpeningSessions().get(diagramFile);
          _sessionStatus =
              new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getBundle().getSymbolicName(), NLS.bind("Error while opening session {0} [{1}]", firstAnalysisFile,
                  status.getMessage()), status.getException());
        }

        session = SessionHelper.getSession(diagramFile);
      }

      // Get the session.
      _sessionReference = new WeakReference<Session>(session);

      if (null != _sessionReference.get()) {
        _capellaProjectReference = new WeakReference<Project>(SessionHelper.getCapellaProject(_sessionReference.get()));
      }

    } catch (Exception e) {
      _sessionStatus =
          new Status(IStatus.ERROR, SiriusUIPlugin.getDefault().getBundle().getSymbolicName(), NLS.bind("Error while opening session {0} [{1}]", firstAnalysisFile,
              e.getMessage()), e);
      __logger.error(new EmbeddedMessage(e.getMessage(), IReportManagerDefaultComponents.UI), e);
    }
  }

  /**
   * @return the sessionStatus
   */
  public IStatus getStatus() {
    return _sessionStatus;
  }

  /**
   * @see org.eclipse.ui.IPersistable#saveState(org.eclipse.ui.IMemento)
   */
  public void saveState(IMemento memento_p) {
    // Precondition.
    if ((null == _sessionReference) || (null == _sessionReference.get())) {
      return;
    }
    IFile firstAnalysisFile = SessionHelper.getFirstAnalysisFile((DAnalysisSession) _sessionReference.get());
    if (null != firstAnalysisFile) {
      memento_p.putString(FIRST_ANALYSIS_FILE_TAG, firstAnalysisFile.getFullPath().toString());
    }
  }

  /**
   * Instantiate a {@link CapellaDashboardEditorInput} from specified memento.
   * @param memento_p
   * @return
   */
  static CapellaDashboardEditorInput create(IMemento memento_p) {
    CapellaDashboardEditorInput result = new CapellaDashboardEditorInput(memento_p);
    return result;
  }
}
