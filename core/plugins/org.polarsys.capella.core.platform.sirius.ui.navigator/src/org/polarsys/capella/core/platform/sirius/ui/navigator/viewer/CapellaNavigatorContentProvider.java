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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.api.navigator.GroupingContentProvider;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionWrapperContentProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.toolkit.provider.GroupedAdapterFactoryContentProvider;
import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.preferences.ICapellaNavigatorPreferences;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella navigator content provider.
 */
public class CapellaNavigatorContentProvider extends GroupedAdapterFactoryContentProvider implements IResourceChangeListener, IAdaptable {
  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Keep synchronized with the plugin.xml extension declaration.
   */
  public static final String CONTENT_EXTENSION_ID = "capella.project.explorer.content"; //$NON-NLS-1$
  /**
   * No Child constant.
   */
  private static final Object[] NO_CHILD = new Object[0];

  /**
   * Should content notifications be taken into account ? <code>true</code> if so, <code>false</code> to ignore them.
   */
  private volatile boolean _allowContentNotifications;
  /**
   * Session content provider.
   */
  private ITreeContentProvider _sessionContentProvider;

  /**
   * The saveable provider.
   */
  private CapellaSaveablesProvider _saveablesProvider;

  class CustomizedSessionWrapperContentProvider extends SessionWrapperContentProvider {
    /**
     * Constructor.
     * @param wrapped_p
     */
    public CustomizedSessionWrapperContentProvider(ITreeContentProvider wrapped_p) {
      super(wrapped_p);
    }

    /**
     * Return children of the given element.<br>
     * A specific behavior is implemented for ViewpointItem: RepresentationDescriptionItems which contain no diagram are removed from the children list.<br>
     * Why don't use a filter ? Model Content view handles that case with a filter. The disadvantage of the filter is : an expandable icon is displayed because
     * hasChildren is based on doGetChildren().length. Thus, after clicking on the expandable nothing is displayed, this is a weird behavior for the end-user.
     */
    @Override
    public Object[] getChildren(Object parentElement_p) {
      Object[] children = super.getChildren(parentElement_p);
      // Special behavior for ViewpointItems: don't return RepresentationDescriptionItems which contain no diagram.
      if (parentElement_p instanceof ViewpointItem) {
        ArrayList<Object> selectedChildren = new ArrayList<Object>(0);
        // Only RepresentationDescriptionItem with children are considered.
        for (Object child : children) {
          if ((child instanceof RepresentationDescriptionItem) && !((RepresentationDescriptionItem) child).getChildren().isEmpty()) {
            selectedChildren.add(child);
          }
        }
        children = selectedChildren.toArray();
      }
      return children;
    }
  }

  /**
   * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
   */
  @Override
  @SuppressWarnings("rawtypes")
  public Object getAdapter(Class clazz_p) {
    if (SaveablesProvider.class == clazz_p) {
      return _saveablesProvider;
    }
    return null;
  }

  /**
   * Constructs the Capella navigator content provider.
   */
  public CapellaNavigatorContentProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());

    // Add in workspace resource changes notification mechanism.
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.PRE_DELETE); // PRE_CLOSE is handled by the CloseAction.

    // Instantiate a customized session content provider.
    CustomizedSessionWrapperContentProvider customizedSessionWrapperContentProvider =
        new CustomizedSessionWrapperContentProvider(new AdapterFactoryContentProvider(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory()));

    // Put it in a grouping content provider.
    SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, true);
    _sessionContentProvider = new GroupingContentProvider(customizedSessionWrapperContentProvider);

    // Create saveable provider.
    if (null == _saveablesProvider) {
      _saveablesProvider = new CapellaSaveablesProvider();
    }

    NavigatorEditingDomainDispatcher.registerNotifyChangedListener(this);

    // By default, take into account content notifications.
    enableContentNotifications();
  }

  /**
   * @return the sessionContentProvider
   */
  public ITreeContentProvider getSessionContentProvider() {
    return _sessionContentProvider;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element_p) {
    Object parent = null;
    if (element_p instanceof IResource) {
      // Handle Eclipse resources parenting.
      IResource resource = (IResource) element_p;
      parent = resource.getParent();

    } else if (element_p instanceof DAnalysisSession) {
      // Handle Session even if a session is not displayed because saveables are based on sessions.
      parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) element_p);

    } else if (element_p instanceof DSemanticDecorator) {
      // Handle diagram parenting.
      DSemanticDecorator representation = (DSemanticDecorator) element_p;
      parent = representation.getTarget();

    } else if (CapellaResourceHelper.isCapellaResource(element_p)) {
      // Handle resource parenting.
      // Get the session for this semantic resource.
      Session session = SessionManager.INSTANCE.getSession((Resource) element_p);
      // Parent for a semantic resource is the parent of its session as the session node is no longer displayed in the viewer.
      parent = getParent(session);

    } else if ((element_p instanceof EObject) && (((EObject) element_p).eContainer() instanceof Component)) {
      EObject element = (EObject) element_p;
      Component component = (Component) element.eContainer();
      if (isImplicitView(component)) {
        if (component.eContainingFeature().equals(CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE)) {
          return getParent(component);
        }
      }
      parent = _sessionContentProvider.getParent(element_p);
    } else {
      // Handle other cases.
      parent = _sessionContentProvider.getParent(element_p);
    }
    return parent;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement_p) {
    return getChildren(inputElement_p);
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#hasChildren(java.lang.Object)
   */
  @Override
  public boolean hasChildren(Object element_p) {
    return getChildren(element_p).length > 0;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object element_p) {
    Object[] result = NO_CHILD;
    try {

      if (element_p instanceof IProject) {
        // IProjects are top level elements in the tree.
        result = getIProjectChildren((IProject) element_p);

      } else if (element_p instanceof IFile) {
        // Handle AIRD file case.
        IFile file = (IFile) element_p;
        if (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(file.getFileExtension())) {
          result = getAirdFileChildren(file);
        }

      } else if (element_p instanceof Session) {
        Session session = (Session) element_p;
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        IModel referencingModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());

        if (referencingModel != null) {

          LinkedList<Object> rootProject = new LinkedList<Object>();
          LinkedList<Object> libraries = new LinkedList<Object>();
          LinkedList<Object> others = new LinkedList<Object>();
          HashSet<Resource> resourcesDone = new HashSet<Resource>();

          //Add the main model to the top
          if (referencingModel instanceof CapellaModel) {
            Project object = ((CapellaModel) referencingModel).getProject(domain);
            if (object != null) {
              rootProject.add(object);
            }
            resourcesDone.add(object.eResource());
          }

          //Add referenced libraries
          Collection<IModel> allReferenced = LibraryManagerExt.getAllReferences(referencingModel);
          for (IModel referenced : allReferenced) {
            if (referenced instanceof CapellaModel) {
              Project object = ((CapellaModel) referenced).getProject(domain);
              if (object != null) {
                resourcesDone.add(object.eResource());
                if (referencingModel.isActive(referenced)) {
                  libraries.add(object);
                }
              }
            }
          }

          // for any other children from sirius, we add it to the end
          for (Object child : _sessionContentProvider.getChildren(element_p)) {

            if ((child instanceof Resource) && !resourcesDone.contains(child) && (!((Resource) child).getContents().isEmpty())) {

              // Don't handle semantic fragments as theirs contents are displayed as children of model elements.
              if (CapellaResourceHelper.isCapellaResource(child)) {

                if (!CapellaResourceHelper.isCapellaFragment(((Resource) child).getURI())) {
                  //add any referenced resources which is not a compatible library (it may happen)
                  for (EObject object : ((Resource) child).getContents()) {
                    IModel referencedLibrary = ILibraryManager.INSTANCE.getModel(object);
                    if ((referencedLibrary == null)) {
                      others.addFirst(object);
                    }
                  }
                }
              } else {
                others.addFirst(((Resource) child).getContents());
              }

            } else {
              //we want to add others elements at the end (viewpoint package for instance)
              others.addLast(child);
            }
          }

          LinkedList<Object> resultList = new LinkedList<Object>();
          resultList.addAll(rootProject);
          resultList.addAll(libraries);
          resultList.addAll(others);

          return resultList.toArray(new Object[0]);

        }
        return _sessionContentProvider.getChildren(element_p);

      } else if ((element_p instanceof Part) && isImplicitView(element_p) && (((Part) element_p).getOwnedAbstractType() != null)) {
        ArrayList<Object> merged = new ArrayList<Object>();
        merged.addAll(Arrays.asList(_sessionContentProvider.getChildren(element_p)));
        merged.addAll(Arrays.asList(getChildren(((Part) element_p).getOwnedAbstractType())));
        merged.remove(((Part) element_p).getOwnedAbstractType());
        return merged.toArray();

      } else {
        // Other cases are delegated to the session content provider.
        result = _sessionContentProvider.getChildren(element_p);
      }
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.UI).error("Error when retrieving children of " + element_p, e); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * @param part_p
   * @return
   */
  protected boolean isImplicitView(Object part_p) {
    boolean explicit = AbstractPreferencesInitializer.getBoolean(ICapellaNavigatorPreferences.PREFERENCE_PART_EXPLICIT_VIEW, true);
    return !explicit;
  }

  /**
   * Answer whether or not capella project should be hidden.<br>
   * Answer is based on an end-user preference.
   * @return
   */
  protected boolean isCapellaProjectDisplayed() {
    return AbstractPreferencesInitializer.getBoolean(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT, false);
  }

  /**
   * @param contentChild_p
   * @return
   */
  private boolean isCapellaProjectDisplayed(EObject contentChild_p) {
    if (contentChild_p instanceof Project) {
      return AbstractPreferencesInitializer.getBoolean(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT, contentChild_p);

    }
    return isCapellaProjectDisplayed();
  }

  /**
   * Get aird file children.
   * @param file_p
   * @return
   */
  protected Object[] getAirdFileChildren(IFile file_p) {
    Object[] result = NO_CHILD;
    Session session = SessionHelper.getSession(file_p);

    // We got the session, let' get its children except the semantic resource, because we skip the Session item in the tree.
    if (null != session) {

      List<Object> retainedChildren = new ArrayList<Object>(0);
      Object[] children = getChildren(session);

      for (Object object : children) {

        if (object instanceof Project) {
          Project project = (Project) object;

          boolean isCapellaProjectHidden = !isCapellaProjectDisplayed();
          isCapellaProjectHidden = !isCapellaProjectDisplayed(project);

          if (isCapellaProjectHidden) {
            // Adapt the project to force its item provider to be instantiated.
            // Without that, no refresh events are sent because no item provider exists.
            adapterFactory.adapt(project, IEditingDomainItemProvider.class);
            retainedChildren.addAll(project.getOwnedModelRoots());

          } else {
            retainedChildren.add(project);
          }

        } else {
          retainedChildren.add(object);
        }
      }

      result = retainedChildren.toArray();
    }

    return result;
  }

  /**
   * Get the children of given {@link IProject}.
   * @param project_p
   * @return a not <code>null</code> array is returned.
   */
  protected Object[] getIProjectChildren(IProject project_p) {
    Object[] result = NO_CHILD;
    // Needed to make the Filter Tree operational.
    try {
      if (project_p.isOpen()) {
        result = project_p.members();
      }
    } catch (CoreException exception_p) {
      StringBuilder loggerMessage = new StringBuilder("CapellaNavigatorContentProvider.getChildren(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception_p.getMessage());
      __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#dispose()
   */
  @Override
  public void dispose() {
    NavigatorEditingDomainDispatcher.unregisterNotifyChangedListener(this);

    // Remove from workspace resource changes notification mechanism.
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);

    if (null != _sessionContentProvider) {
      _sessionContentProvider.dispose();
      _sessionContentProvider = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
   */
  @Override
  public void resourceChanged(IResourceChangeEvent event_p) {
    switch (event_p.getType()) {
      case IResourceChangeEvent.PRE_DELETE: {
        if (null != event_p.getResource()) {
          IProject project = (IProject) event_p.getResource();
          // We are not called in the UI thread. The code below handles that case.
          Object[] children = getChildren(project);
          for (Object object : children) {
            if (object instanceof Session) {
              Session session = (Session) object;
              IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
              boolean saveSession = false;
              if (SessionStatus.DIRTY.equals(session.getStatus())) {
                saveSession = (ISaveablePart2.YES == SWTUtil.showSaveDialog(session, "Session", true)); //$NON-NLS-1$
              }
              sessionUI.close(saveSession);
            }
          }
        }
        break;
      }
    }
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
   */
  @Override
  public void notifyChanged(Notification notification_p) {
    // Precondition.
    // If notifications are disabled, don't do anything here.
    if (!_allowContentNotifications) {
      return;
    }
    // At the moment representation notifications are ignored to avoid massive UI refreshes.
    // Keep only representation notifications with DSemanticDiagram as notifier to correctly refresh tree items when a diagram is renamed.
    Object notifier = notification_p.getNotifier();
    boolean shouldNotify = true;
    if ((notifier instanceof DRepresentation) && !(notifier instanceof DSemanticDiagram)) {
      shouldNotify = false;
    }

    if (shouldNotify) {
      Notification notification = notification_p;

      if (notifier instanceof EObject) {
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject) notifier);
        if (domain != null) {
          domain.addResourceSetListener(getListener());
        }
      }

      if (notifier instanceof ModelInformation) {
        // forward the notification on resource for library references update.
        Session session = SessionManager.INSTANCE.getSession((ModelInformation) notifier);
        if (session != null) {
          Resource sessionResource = session.getSessionResource();
          if (sessionResource != null) {
            IFile file = EcoreUtil2.getFile(session.getSessionResource());
            if (file != null) {
              notification = new ViewerNotification(notification, file);
            }
          }
        }
      }

      if ((notifier instanceof Project) && !isCapellaProjectDisplayed()) {
        // Capella Project is not refresh, forward the notification on Capella Project parent.
        notification = new ViewerNotification(notification, ((EObject) notifier).eContainer());
      }
      if (((notifier instanceof Component) && (((EObject) notifier).eContainer() instanceof Part)) && isImplicitView(notifier)) {
        // Capella Project is not refresh, forward the notification on parent part.
        notification = new ViewerNotification(notification, ((EObject) notifier).eContainer());
      }

      super.notifyChanged(notification);

      // Search for additional updates, indeed elements that reference an element, whose name changed, might be refreshed too.
      if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(notification.getFeature())) {
        SemanticEditingDomain editingDomain = (SemanticEditingDomain) TransactionHelper.getEditingDomain((EObject) notifier);
        if (null != editingDomain) {
          // Get the cross referencer.
          ECrossReferenceAdapter crossReferencer = editingDomain.getCrossReferencer();
          if (null != crossReferencer) {
            Collection<Setting> referencingObjectSettings = crossReferencer.getInverseReferences((EObject) notifier);
            // Loop over referencingObjectSettings.
            for (Setting setting : referencingObjectSettings) {
              super.addObject(setting.getEObject());
            }
          }
        }
      }
    }
  }

  /**
   * Disable content notifications.<br>
   * Method {@link #notifyChanged(Notification)} will no longer do anything.
   */
  public void disableContentNotifications(SemanticEditingDomain editingDomain) {
    _allowContentNotifications = false;
  }

  public void disableContentNotifications() {
    _allowContentNotifications = false;
  }

  /**
   * Re-enable content notifications.<br>
   * Method {@link #notifyChanged(Notification)} behaves as expected.
   */
  public void enableContentNotifications(SemanticEditingDomain editingDomain) {
    _allowContentNotifications = true;
  }

  public void enableContentNotifications() {
    _allowContentNotifications = true;
  }
}
