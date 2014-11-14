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
package org.polarsys.capella.core.sirius.ui.editor;

import java.lang.ref.WeakReference;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
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

  /**
   * Constructor.<br>
   * This constructor is used to restore a Capella dashboard editor.
   * @param memento
   * @throws Exception
   */
  CapellaDashboardEditorInput(IMemento memento_p) throws Exception {
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
  protected void loadState(IMemento memento_p) throws Exception {
    // Get from the memento the diagram file associated to session to restore.
    String firstAnalysisFile = memento_p.getString(FIRST_ANALYSIS_FILE_TAG);
    IFile diagramFile = FileHelper.getPlatformFile(firstAnalysisFile);
    Session session = SessionHelper.getSession(diagramFile);

    // Don't open session if already opened (bad performance)
    if (null == session) {
      // Instantiate the action responsible for opening a session.
      OpenSessionAction openSessionAction = new OpenSessionAction();
      // Disable to open the capella dashboard as we are already restoring a Capella Dashboard editor input i.e a CapellaDashboard editor.
      openSessionAction.setOpenCapellaDashboard(false);
      openSessionAction.setRunInProgressService(false);
      openSessionAction.selectionChanged(new StructuredSelection(diagramFile));
      // Open the session.
      openSessionAction.run();
      session = SessionHelper.getSession(diagramFile);
    }

    // Get the session.
    _sessionReference = new WeakReference<Session>(session);
    session = null;

    if (null != _sessionReference) {
      _capellaProjectReference = new WeakReference<Project>(SessionHelper.getCapellaProject(_sessionReference.get()));
    } else {
      throw new Exception("Failed to instantiate the session for " + firstAnalysisFile); //$NON-NLS-1$
    }
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
    CapellaDashboardEditorInput result = null;
    try {
      result = new CapellaDashboardEditorInput(memento_p);
    } catch (Exception exception_p) {
      StringBuilder loggerMessage = new StringBuilder("Error while trying to restore a CapellaDashboardEditorInput - "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    }
    return result;
  }
}
