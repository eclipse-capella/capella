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
package org.polarsys.capella.core.dashboard.editor;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener2;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.CapellaOverviewDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.EpbsArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.LogicalArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.OperationalAnalysisDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.PhysicalArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.SystemAnalysisDashboardPage;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.sirius.ui.editor.CapellaDashboardEditorInput;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 * Base class to implement Capella dashboard.
 */
public class CapellaDashboardEditor extends SharedHeaderFormEditor implements SessionManagerListener2, ITabbedPropertySheetPageContributor {
  /**
   * Logger.
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Editor ID.
   */
  public static final String ID = "org.polarsys.capella.core.dashboard.editor.capellaDashboardEditor"; //$NON-NLS-1$
  /**
   * Part listener to detect editor activation.
   */
  private IPartListener _partListener;
  /**
   * Property Sheet page.
   */
  private CapellaTabbedPropertySheetPage _propertySheetPage;

  /**
   * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
   */
  @Override
  protected void addPages() {
    try {
      addPage(createOverviewDashboardPage());

      IFormPage page = createDocumentationPage();
      if (null != page) {
    	addPage(page);
      }

      // Add Operational Analysis if needed.
      if (null != ModelQueryHelper.getOperationalAnalysis(getEditorInput().getCapellaProject())) {
        addPage(createOperationalAnalysisDashboardPage());
      }
      addPage(createSystemAnalysisDashboardPage());
      addPage(createLogicalArchitectureDashboardPage());
      addPage(createPhysicalArchitectureDashboardPage());
      // Add EPBS Architecture. if needed.
      if (null != ModelQueryHelper.getEPBSArchitecture(getEditorInput().getCapellaProject())) {
        addPage(createEpbsArchitectureDashboardPage());
      }
    } catch (PartInitException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("CapellaDashboardEditor.addPages(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    }
    // Add a control listener to force reflow
    getContainer().addControlListener(new ControlListener() {
      public void controlMoved(ControlEvent e_p) {
        // Do nothing.
      }

      public void controlResized(ControlEvent e_p) {
        IFormPage activePageInstance = CapellaDashboardEditor.this.getActivePageInstance();
        IManagedForm managedForm = activePageInstance.getManagedForm();
        managedForm.reflow(true);
      }
    });
    // Refresh dirty state when the part is activated : open time for instance.
    getHeaderForm().dirtyStateChanged();
  }

  /**
   * Create a documentation page.
   * @return a not <code>null</code> instance.
   */
  protected IFormPage createDocumentationPage() {
    //return new CapellaDocumentationDashboardPage(this);
	List<IConfigurationElement> providers = Arrays.asList(ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.core.dashboard", "documentationDashboardPageProvider"));
    for (IConfigurationElement configurationElement : providers) {
      IDashboardPageProvider provider = (IDashboardPageProvider) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      return provider.provideDashboardPage(this);
	}
    return null;
  }

  /**
   * Create the EPBS Architecture dashboard page.
   * @return a not <code>null</code> instance.
   */
  protected EpbsArchitectureDashboardPage createEpbsArchitectureDashboardPage() {
    return new EpbsArchitectureDashboardPage(this);
  }

  /**
   * Create the Logical Architecture dashboard page.
   * @return a not <code>null</code> instance.
   */
  protected LogicalArchitectureDashboardPage createLogicalArchitectureDashboardPage() {
    return new LogicalArchitectureDashboardPage(this);
  }

  /**
   * Create the Operational Analysis dashboard page.
   * @return a not <code>null</code> instance.
   */
  protected OperationalAnalysisDashboardPage createOperationalAnalysisDashboardPage() {
    return new OperationalAnalysisDashboardPage(this);
  }

  /**
   * @return
   */
  protected IFormPage createOverviewDashboardPage() {
    return new CapellaOverviewDashboardPage(this);
  }

  /**
   * Create the Physical Architecture dashboard page.
   * @return
   */
  protected PhysicalArchitectureDashboardPage createPhysicalArchitectureDashboardPage() {
    return new PhysicalArchitectureDashboardPage(this);
  }

  /**
   * Create the System Analysis dashboard page.
   * @return
   */
  protected SystemAnalysisDashboardPage createSystemAnalysisDashboardPage() {
    return new SystemAnalysisDashboardPage(this);
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormEditor#dispose()
   */
  @Override
  public void dispose() {
    // Dispose the property sheet page.
    if (null != _propertySheetPage) {
      _propertySheetPage.dispose();
      _propertySheetPage = null;
    }
    // Unregister Sirius session listener.
    unregisterSessionListener();
    // Remove part listener.
    if (null != _partListener) {
      getEditorSite().getPage().removePartListener(_partListener);
      _partListener = null;
    }
    super.dispose();

    if (null != getEditorInput()) {
      getEditorInput().dispose();
    }
  }

  /**
   * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public void doSave(IProgressMonitor monitor_p) {
    if (isDirty()) {
      getEditorInput().getSession().save(monitor_p);
    }
  }

  /**
   * @see org.eclipse.ui.part.EditorPart#doSaveAs()
   */
  @Override
  public void doSaveAs() {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter_p) {
    if (IPropertySheetPage.class.equals(adapter_p)) {
      return getOrCreatePropertySheetPage();
    }
    return super.getAdapter(adapter_p);
  }

  /**
   * {@inheritDoc}
   */
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  /**
   * @see org.eclipse.ui.part.EditorPart#getEditorInput()
   */
  @Override
  public CapellaDashboardEditorInput getEditorInput() {
    return (CapellaDashboardEditorInput) super.getEditorInput();
  }

  /**
   * Get or create (first call) the property sheet page.
   */
  private IPropertySheetPage getOrCreatePropertySheetPage() {
    if ((null == _propertySheetPage)) {
      _propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void dispose() {
          super.dispose();
          _propertySheetPage = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void init(IPageSite pageSite_p) {
          super.init(pageSite_p);
          pageSite_p.setSelectionProvider(CapellaDashboardEditor.this.getEditorSite().getSelectionProvider());
        }
      };
    }
    return _propertySheetPage;
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#getPartName()
   */
  @Override
  public String getPartName() {
    return getEditorInput().getName() + Messages.CapellaDashboardEditor_Title_Suffix;
  }

  /**
   * Get property sheet page accessor.
   * @return <code>null</code> if the property sheet view is not displayed.
   */
  public TabbedPropertySheetPage getPropertySheetPage() {
    return _propertySheetPage;
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
   */
  @Override
  public void init(IEditorSite site_p, IEditorInput input_p) throws PartInitException {
    if (null == ((CapellaDashboardEditorInput) input_p).getCapellaProject()) {
      throw new PartInitException(
          new Status(IStatus.WARNING, CapellaDashboardActivator.getDefault().getPluginId(), Messages.CapellaDashboardEditor_Error_Message));
    }
    super.init(site_p, input_p);
    // Register as session listener.
    SessionManager.INSTANCE.addSessionsListener(this);
    // PArt listener to detect when this editor is activated.
    _partListener = new IPartListener() {
      /**
       * {@inheritDoc}
       */
      public void partActivated(IWorkbenchPart part_p) {
        if (CapellaDashboardEditor.this == part_p) {
          IFormPage activePageInstance = CapellaDashboardEditor.this.getActivePageInstance();
          // Make sure action bars
          if (activePageInstance instanceof AbstractCapellaArchitectureDashboardPage) {
            ((AbstractCapellaArchitectureDashboardPage) activePageInstance).updateActionBars();
          }
        }
      }

      /**
       * {@inheritDoc}
       */

      public void partBroughtToTop(IWorkbenchPart part_p) {
        // Do nothing.
      }

      /**
       * {@inheritDoc}
       */

      public void partClosed(IWorkbenchPart part_p) {
        // Do nothing.
      }

      /**
       * {@inheritDoc}
       */

      public void partDeactivated(IWorkbenchPart part_p) {
        // Do nothing.
      }

      /**
       * {@inheritDoc}
       */
      public void partOpened(IWorkbenchPart part_p) {
        // Do nothing.
      }
    };
    getEditorSite().getPage().addPartListener(_partListener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDirty() {
    Session session = getEditorInput().getSession();
    if (null != session) {
      return SessionStatus.DIRTY.equals(session.getStatus());
    }
    return false;
  }

  /**
   * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
   */
  @Override
  public boolean isSaveAsAllowed() {
    // Not applicable in this editor.
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSaveOnCloseNeeded() {
    // See with SBo, we don't want to save on close.
    return false;
  }

  /**
   * Mark given page as dirty.
   * @param page_p
   */
  private void markArchitecturePageAsDirty(AbstractCapellaArchitectureDashboardPage page_p) {
    if (null != page_p) {
      page_p.markDiagramViewerAsDirty();
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener2#notify(org.eclipse.sirius.business.api.session.Session, int)
   */
  public void notify(Session session_p, int notification_p) {
    // Filter on event for other sessions.
    if (!session_p.equals(getEditorInput().getSession())) {
      return;
    }
    Runnable runnable = null;
    switch (notification_p) {
      case SessionListener.CLOSING: /* Closing event is used to have a chance to persist the editor input at workbench shutdown */
        runnable = new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          public void run() {
            // Close this editor.
            close(false);
          }
        };
      break;
      case SessionListener.REPRESENTATION_CHANGE:
        runnable = new Runnable() {
          /**
           * @see java.lang.Runnable#run()
           */
          @SuppressWarnings("synthetic-access")
          public void run() {
            // Handle pages to mark them as dirty.
            markArchitecturePageAsDirty((AbstractCapellaArchitectureDashboardPage) findPage(OperationalAnalysisDashboardPage.PAGE_ID));
            markArchitecturePageAsDirty((AbstractCapellaArchitectureDashboardPage) findPage(SystemAnalysisDashboardPage.PAGE_ID));
            markArchitecturePageAsDirty((AbstractCapellaArchitectureDashboardPage) findPage(LogicalArchitectureDashboardPage.PAGE_ID));
            markArchitecturePageAsDirty((AbstractCapellaArchitectureDashboardPage) findPage(PhysicalArchitectureDashboardPage.PAGE_ID));
            markArchitecturePageAsDirty((AbstractCapellaArchitectureDashboardPage) findPage(EpbsArchitectureDashboardPage.PAGE_ID));
            IManagedForm headerForm = getHeaderForm();
            if (null != headerForm) {
              headerForm.dirtyStateChanged();
            }
          }
        };
      break;
      case SessionListener.DIRTY:
      case SessionListener.SYNC:
      case SessionListener.SEMANTIC_CHANGE: // Listening to changes to mark the dashboard editor dirty hence saveable.
        runnable = new Runnable() {
          /**
           * {@inheritDoc}
           */
          public void run() {
            IManagedForm headerForm = getHeaderForm();
            if (null != headerForm) {
              headerForm.dirtyStateChanged();
            }
          }
        };
      break;
    }
    if (null != runnable) {
      getEditorSite().getShell().getDisplay().asyncExec(runnable);
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyAddSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyAddSession(Session newSession_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyRemoveSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyRemoveSession(Session removedSession_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notifyUpdatedSession(org.eclipse.sirius.business.api.session.Session)
   */
  public void notifyUpdatedSession(Session updatedSession_p) {
    // Fake a representation change
    notify(updatedSession_p, SessionListener.REPRESENTATION_CHANGE);
  }

  /**
   * Unregister the session listener and set the session to <code>null</code>.
   */
  private void unregisterSessionListener() {
    CapellaDashboardEditorInput editorInput = getEditorInput();
    if (null != editorInput) {
      Session session = editorInput.getSession();
      if (null != session) {
        // Remove the session listener.
        SessionManager.INSTANCE.removeSessionsListener(this);
        session = null;
      }
    }
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointDeselected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointDeselected(Viewpoint deselectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.sirius.business.api.session.SessionManagerListener#viewpointSelected(org.eclipse.sirius.description.Viewpoint)
   */
  public void viewpointSelected(Viewpoint selectedViewpoint_p) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getTitleImage() {
    ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
    Image decoratedImage = decorator.decorateImage(super.getTitleImage(), getEditorInput().getSession());

    return decoratedImage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setTitleImage(Image titleImage_p) {

    super.setTitleImage(titleImage_p);
  }
}
