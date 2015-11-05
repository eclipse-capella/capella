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
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
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
     * @param wrapped
     */
    public CustomizedSessionWrapperContentProvider(ITreeContentProvider wrapped) {
      super(wrapped);
    }

    /**
     * Return children of the given element.<br>
     * A specific behavior is implemented for ViewpointItem: RepresentationDescriptionItems which contain no diagram are removed from the children list.<br>
     * Why don't use a filter ? Model Content view handles that case with a filter. The disadvantage of the filter is : an expandable icon is displayed because
     * hasChildren is based on doGetChildren().length. Thus, after clicking on the expandable nothing is displayed, this is a weird behavior for the end-user.
     */
    @Override
    public Object[] getChildren(Object parentElement) {
      Object[] children = super.getChildren(parentElement);
      // Special behavior for ViewpointItems: don't return
      // RepresentationDescriptionItems which contain no diagram.
      if (parentElement instanceof ViewpointItem) {
        ArrayList<Object> selectedChildren = new ArrayList<Object>(0);
        // Only RepresentationDescriptionItem with children are
        // considered.
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
  public Object getAdapter(Class clazz) {
    if (SaveablesProvider.class == clazz) {
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
    ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.PRE_DELETE); // PRE_CLOSE is
                                                                                                     // handled by the
    // CloseAction.

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
    ActiveSessionManager.getInstance().enableContentNotifications();
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
  public Object getParent(Object element) {
    Object parent = null;
    if (element instanceof IResource) {
      // Handle Eclipse resources parenting.
      IResource resource = (IResource) element;
      parent = resource.getParent();

    } else if (element instanceof DAnalysisSession) {
      // Handle Session even if a session is not displayed because
      // saveables are based on sessions.
      parent = SessionHelper.getFirstAnalysisFile((DAnalysisSession) element);

    } else if (element instanceof DSemanticDecorator) {
      // Handle diagram parenting.
      DSemanticDecorator representation = (DSemanticDecorator) element;
      parent = representation.getTarget();

    } else if (CapellaResourceHelper.isCapellaResource(element)) {
      // Handle resource parenting.
      // Get the session for this semantic resource.
      Session session = SessionManager.INSTANCE.getSession((Resource) element);
      // Parent for a semantic resource is the parent of its session as
      // the session node is no longer displayed in the viewer.
      parent = getParent(session);
    } else if ((element instanceof Project) || ((element instanceof SystemEngineering) && !isCapellaProjectDisplayed())) {
      // In the CapellaProjectExplorer, parent of a Project/Library or of the SystemEngineering is actually the .aird file (depending of preferences).
      Resource mmResource = ((EObject) element).eResource();
      parent = getParent(mmResource);
    } else if ((element instanceof EObject) && (((EObject) element).eContainer() instanceof Component)) {
      EObject eObject = (EObject) element;
      Component component = (Component) eObject.eContainer();
      if (isImplicitView(component)) {
        if (component.eContainingFeature().equals(CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE)) {
          return getParent(component);
        }
      }
      parent = _sessionContentProvider.getParent(element);
    } else {
      // Handle other cases.
      parent = _sessionContentProvider.getParent(element);
    }

    return parent;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
   */
  @Override
  public Object[] getElements(Object inputElement) {
    return getChildren(inputElement);
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#hasChildren(java.lang.Object)
   */
  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object element) {
    Object[] result = NO_CHILD;
    try {

      if (element instanceof IProject) {
        // IProjects are top level elements in the tree.
        result = getIProjectChildren((IProject) element);

      } else if (element instanceof IFile) {
        // Handle AIRD file case.
        IFile file = (IFile) element;
        if (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(file.getFileExtension())) {
          result = getAirdFileChildren(file);
        }

      } else if (element instanceof Session) {
        Session session = (Session) element;
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        IModel referencingModel = ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());

        if (referencingModel != null) {

          LinkedList<Object> rootProject = new LinkedList<Object>();
          LinkedList<Object> libraries = new LinkedList<Object>();
          LinkedList<Object> others = new LinkedList<Object>();
          HashSet<Resource> resourcesDone = new HashSet<Resource>();

          // Add the main model to the top
          if (referencingModel instanceof CapellaModel) {
            Project object = ((CapellaModel) referencingModel).getProject(domain);
            if (object != null) {
              rootProject.add(object);
            }
            resourcesDone.add(object.eResource());
          }

          // Add referenced libraries
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
          for (Object child : _sessionContentProvider.getChildren(element)) {

            if ((child instanceof Resource) && !resourcesDone.contains(child) && (!((Resource) child).getContents().isEmpty())) {

              // Don't handle semantic fragments as theirs
              // contents are displayed as children of model
              // elements.
              if (CapellaResourceHelper.isCapellaResource(child)) {

                if (!CapellaResourceHelper.isCapellaFragment(((Resource) child).getURI())) {
                  // add any referenced resources which is not
                  // a compatible library (it may happen)
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
              // we want to add others elements at the end
              // (viewpoint package for instance)
              others.addLast(child);
            }
          }

          LinkedList<Object> resultList = new LinkedList<Object>();
          resultList.addAll(rootProject);
          resultList.addAll(libraries);
          resultList.addAll(others);

          return resultList.toArray();

        }
        return _sessionContentProvider.getChildren(element);

      } else if ((element instanceof Part) && isImplicitView(element) && (((Part) element).getOwnedAbstractType() != null)) {
        ArrayList<Object> merged = new ArrayList<Object>();
        merged.addAll(Arrays.asList(_sessionContentProvider.getChildren(element)));
        merged.addAll(Arrays.asList(getChildren(((Part) element).getOwnedAbstractType())));
        merged.remove(((Part) element).getOwnedAbstractType());
        return merged.toArray();

      } else {
        // Other cases are delegated to the session content provider.
        result = _sessionContentProvider.getChildren(element);
      }
    } catch (Exception e) {
      Logger.getLogger(IReportManagerDefaultComponents.UI).error("Error when retrieving children of " + element, e); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * @param part
   * @return
   */
  protected boolean isImplicitView(Object part) {
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
   * @param contentChild
   * @return
   */
  private boolean isCapellaProjectDisplayed(EObject contentChild) {
    if (contentChild instanceof Project) {
      return AbstractPreferencesInitializer.getBoolean(ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT, contentChild);

    }
    return isCapellaProjectDisplayed();
  }

  /**
   * Get aird file children.
   * @param file
   * @return
   */
  protected Object[] getAirdFileChildren(IFile file) {
    Object[] result = NO_CHILD;
    Session session = SessionHelper.getSession(file);

    // We got the session, let' get its children except the semantic
    // resource, because we skip the Session item in the tree.
    if (null != session) {

      List<Object> retainedChildren = new ArrayList<Object>(0);
      Object[] children = getChildren(session);

      for (Object object : children) {

        if (object instanceof Project) {
          Project project = (Project) object;

          boolean isCapellaProjectHidden = !isCapellaProjectDisplayed(project);

          if (isCapellaProjectHidden) {
            // Adapt the project to force its item provider to be
            // instantiated.
            // Without that, no refresh events are sent because no
            // item provider exists.
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
   * @param project
   * @return a not <code>null</code> array is returned.
   */
  protected Object[] getIProjectChildren(IProject project) {
    Object[] result = NO_CHILD;
    // Needed to make the Filter Tree operational.
    try {
      if (project.isOpen()) {
        result = project.members();
      }
    } catch (CoreException exception) {
      StringBuilder loggerMessage = new StringBuilder("CapellaNavigatorContentProvider.getChildren(..) _ "); //$NON-NLS-1$
      loggerMessage.append(exception.getMessage());
      __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception);
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
  public void resourceChanged(IResourceChangeEvent event) {
    switch (event.getType()) {
      case IResourceChangeEvent.PRE_DELETE: {
        if (null != event.getResource()) {
          IProject project = (IProject) event.getResource();
          // We are not called in the UI thread. The code below handles
          // that case.
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
  public void notifyChanged(Notification notification) {

    // At the moment representation notifications are ignored to avoid
    // massive UI refreshes.
    // Keep only representation notifications with DSemanticDiagram as
    // notifier to correctly refresh tree items when a diagram is renamed.
    Object notifier = notification.getNotifier();
    boolean shouldNotify = true;
    if ((notifier instanceof DRepresentation) && !(notifier instanceof DSemanticDiagram)) {
      shouldNotify = false;
    }

    if (shouldNotify) {
      Notification localNotification = notification;

      if (notifier instanceof EObject) {
        TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((EObject) notifier);
        if (domain != null) {
          domain.addResourceSetListener(getListener());

          // Precondition.
          // If notifications are disabled, don't do anything here.
          if (!ActiveSessionManager.getInstance().isEnabledContentNotifications(domain)) {
            return;
          }
        }

      }

      if (notifier instanceof ModelInformation) {
        // forward the notification on resource for library references
        // update.
        Session session = SessionManager.INSTANCE.getSession((ModelInformation) notifier);
        if (session != null) {
          Resource sessionResource = session.getSessionResource();
          if (sessionResource != null) {
            IFile file = EcoreUtil2.getFile(session.getSessionResource());
            if (file != null) {
              localNotification = new ViewerNotification(localNotification, file);
            }
          }
        }
      }

      if ((notifier instanceof Project) && !isCapellaProjectDisplayed()) {
        // Capella Project is not refresh, forward the notification on
        // Capella Project parent.
        localNotification = new ViewerNotification(localNotification, ((EObject) notifier).eContainer());
      }
      if (((notifier instanceof Component) && (((EObject) notifier).eContainer() instanceof Part)) && isImplicitView(notifier)) {
        // Capella Project is not refresh, forward the notification on
        // parent part.
        localNotification = new ViewerNotification(localNotification, ((EObject) notifier).eContainer());
      }

      super.notifyChanged(localNotification);

      // Search for additional updates, indeed elements that reference an
      // element, whose name changed, might be refreshed too.
      if (ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.equals(localNotification.getFeature())) {
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

}
