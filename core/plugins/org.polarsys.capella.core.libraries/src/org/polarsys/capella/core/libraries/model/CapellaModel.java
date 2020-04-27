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
package org.polarsys.capella.core.libraries.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.Activator;
import org.polarsys.capella.core.model.handler.command.BasicCapellaDeleteCommand;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.LibraryHelper;

public class CapellaModel extends AbstractCapellaModel implements IModel.Edit {

  private static final String ACTIVE_STATE_PREF_ID = "LIBRARY_ACTIVE_STATE_INFORMATIONS"; //$NON-NLS-1$

  public CapellaModel(ModelIdentifier identifier, TransactionalEditingDomain domain) {
    super(identifier, domain);
  }

  @Override
  public Collection<IModelIdentifier> getReferences() {
    Collection<IModelIdentifier> models = new ArrayList<IModelIdentifier>();

    Resource resource = getResource(_domain, uriSemanticFile);
    ModelInformation information = CapellaLibraryExt.getModelInformation(resource, false);

    if (information != null) {
      for (LibraryReference reference : information.getOwnedReferences()) {
        ModelInformation library = reference.getLibrary();
        if ((library != null) && library.eIsProxy()) {
          ((InternalEObject) reference).eResolveProxy((InternalEObject) library);
        }

        ModelIdentifier identifier = null;
        if (library != null) {
          if (!(library.eIsProxy())) {
            identifier = createModelIdentifier(library.eResource());
          } else {
            identifier = createModelIdentifier(((InternalEObject) library).eProxyURI().fragment(), ((InternalEObject) library).eProxyURI().trimFragment(), true);
          }
        }

        if (identifier != null) {
          models.add(identifier);
        }
      }
    }

    return models;
  }

  @Override
  public Collection<IModel> getAvailableReferences() {
    Collection<IModel> models = new ArrayList<IModel>();
    for (IModelIdentifier reference : getReferences()) {
      IModel model = ILibraryManager.INSTANCE.getModel(_domain, reference);
      if (model != null) {
        models.add(model);
      }
    }
    return models;
  }

  protected static ModelIdentifier createModelIdentifier(String identifier, URI uri) {
    return new ModelIdentifier(identifier, uri);
  }
  
  protected static ModelIdentifier createModelIdentifier(String identifier, URI uri, boolean isProxy) {
    return new ModelIdentifier(identifier, uri, isProxy);
  }

  public static ModelIdentifier createModelIdentifier(Resource resource) {
    Project project = CapellaLibraryExt.getProject(resource);
    if (project != null) {
      Resource rootResource = project.eResource();
      ModelInformation information = CapellaLibraryExt.getModelInformation(rootResource, false);
      String identifier = CapellaLibraryExt.getIdentifier(information);
      return createModelIdentifier(identifier, rootResource.getURI(), false);
    }
    return null;
  }

  public static IModel createModel(ModelIdentifier identifier, TransactionalEditingDomain domain) {
    if (identifier == null) {
      return null;
    }
    return new CapellaModel(identifier, domain);
  }

  @Override
  public void addReference(final IModel referencedLibrary) {
    // Enable proxy resolution before adding the referenced library
    CrossReferencerHelper.enableResolveProxy(_domain);

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(referencedLibrary, true);

        // if reference is already made, we don't add another reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
            return;
          }
        }

        // manage afm before adding the new semantic resource: a listener will be call at this moment and it needs up-to-date metadata
        if (referencedLibrary instanceof CapellaModel)
        	LibraryHelper.add(_domain.getResourceSet(), getUriSemanticFile(), ((CapellaModel)referencedLibrary).getUriSemanticFile());

        // otherwise, we add a reference
        LibraryReference result = LibrariesFactory.eINSTANCE.createLibraryReference();
        result.setLibrary(target);
        source.getOwnedReferences().add(result);
        result.setAccessPolicy(getDefaultNewAccess(referencedLibrary));

        // Sirius session requires the semantic target resource to be added to the <semanticResources> reference
        // we can't use session.addSemanticResources unload the resource if already loaded.......
        // new AddSemanticResourceCommand(session, ((CapellaModel) referencedLibrary).uriSemanticFile, new
        // NullProgressMonitor()).execute();

        Resource toAdd = target.eResource();
        Session session = SessionManager.INSTANCE.getSession(source);
        if (session instanceof DAnalysisSessionImpl) {
          for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
            analysis.getSemanticResources().add(new ResourceDescriptor(toAdd.getURI()));
          }
        }
        // we ensure that sirius crossreferencer is correctly registered on it (just in case, like the
        // AddSemanticResourceCommand did)
        if (!toAdd.eAdapters().contains(session.getSemanticCrossReferencer())) {
          toAdd.eAdapters().add(session.getSemanticCrossReferencer());
        }

        notifyLibraryChange(source);
      }
    });

    // Disable proxy resolution after adding the referenced library
    CrossReferencerHelper.disableResolveProxy(_domain);
  }

  @Override
  public void removeReference(final IModel referencedLibrary) {
    // Enable proxy resolution before removing the referenced library
    CrossReferencerHelper.enableResolveProxy(_domain);

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(referencedLibrary, true);

        LibraryReference toDelete = null;

        // manage afm before removing the new semantic resource: a listener will be call at this moment and it needs up-to-date metadata
        if (referencedLibrary instanceof CapellaModel)
        	LibraryHelper.remove(_domain.getResourceSet(), getUriSemanticFile(), ((CapellaModel)referencedLibrary).getUriSemanticFile());
        
        // if reference is made, we remove the reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if (reference.getLibrary() != null) {
            if (reference.getLibrary().equals(target)) {
              toDelete = reference;
              break;
            } else if (reference.getLibrary().eIsProxy() && referencedLibrary.getIdentifier().getId()
                .equals(((InternalEObject) reference.getLibrary()).eProxyURI().fragment())) {
              toDelete = reference;
              break;
            }
          }
        }

        if (toDelete != null) {
          Resource toRemove = null;
          if (toDelete.getLibrary().eIsProxy()) {
            // Find the resource based solely on resource URI, not ModelInformation
            URI resourceURI = ((InternalEObject) toDelete.getLibrary()).eProxyURI().trimFragment();
            Optional<Resource> foundResource = _domain.getResourceSet().getResources().stream()
                .filter(res -> res.getURI().equals(resourceURI)).findFirst();
            if (foundResource.isPresent()) {
              toRemove = foundResource.get();
            }
          } else {
            toRemove = toDelete.getLibrary().eResource();
          }
          if (toRemove != null) {
            Session session = SessionManager.INSTANCE.getSession(source);
            if (session instanceof DAnalysisSessionImpl) {
              for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
                analysis.getSemanticResources().remove(new ResourceDescriptor(toRemove.getURI()));
              }
            }

            new BasicCapellaDeleteCommand(ExecutionManagerRegistry.getInstance().getExecutionManager(_domain),
                Collections.singleton(toDelete), false, false, false).execute();
            toRemove.unload();
            toRemove.eAdapters().removeAll(toRemove.eAdapters());
            toRemove.getResourceSet().getResources().remove(toRemove);
          }
        }

        notifyLibraryChange(source);
      }

    });

    // Disable proxy resolution after removing the referenced library
    CrossReferencerHelper.disableResolveProxy(_domain);
  }

  /**
   * @param source
   */
  protected void notifyLibraryChange(ModelInformation source) {

    // TODO a workaround to update the views of the ModelInformation, even if we don't really touch it.

    // we should add an event based mechanism on library state changes, instead of being based on EMF underlayer, but still better than :

    // ResourceSetSync sync = ResourceSetSync.getOrInstallResourceSetSync(domain);
    // Session session = SessionManager.INSTANCE.getSession(source);
    // sync.statusChanged(session.getSessionResource(), ResourceSetSync.ResourceStatus.SYNC, ResourceSetSync.ResourceStatus.UNKNOWN); // (UNKNOWN leads to
    // inconsistencies)

    // Notification notification = new NotificationImpl(Notification.SET, null, null) {
    // @Override
    // public Object getNotifier() {
    // return source;
    // }
    //
    // @Override
    // public int getFeatureID(Class<?> expectedClass) {
    // return LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES;
    // }
    // };
    // source.eNotify(notification); //this doesn't update the view since no transaction change is triggered.

    LibraryReference reference = LibrariesFactory.eINSTANCE.createLibraryReference();
    source.getOwnedReferences().add(reference);
    source.getOwnedReferences().remove(reference);
  }

  @Override
  public void setAccess(final IModel library, final AccessPolicy currentAccessPolicy) {

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(library, true);

        LibraryReference result = null;

        // if reference is already made, we don't add another reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
            result = reference;
          }
        }

        if (result != null) {
          result.setAccessPolicy(currentAccessPolicy);
        }

        notifyLibraryChange(source);
      }
    });

  }

  @Override
  public boolean canReference(IModel referencedLibrary) {
    return (referencedLibrary instanceof CapellaModel) && ((CapellaModel) referencedLibrary).isLibrary();
  }

  @Override
  public boolean isActive(IModel library) {
    Preferences activeLibraryPrefInfos = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID).node(ACTIVE_STATE_PREF_ID);
    Preferences libraryContextPref = activeLibraryPrefInfos.node(this.getIdentifier().getName());
    return libraryContextPref.getBoolean(library.getIdentifier().getName(), getDefaultActiveState(library));
  }

  @Override
  public void setActive(IModel library, boolean activeState) {
    final Preferences activeLibraryPrefInfos = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID).node(ACTIVE_STATE_PREF_ID);
    Preferences libraryContextPref = activeLibraryPrefInfos.node(this.getIdentifier().getName());
    libraryContextPref.putBoolean(library.getIdentifier().getName(), activeState);

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        try {
          activeLibraryPrefInfos.flush();

        } catch (BackingStoreException e) {
          e.printStackTrace();
        }

        final ModelInformation source = getModelInformation(CapellaModel.this, true);

        notifyLibraryChange(source);
      }
    });

  }

  @Override
  public AccessPolicy getDefaultNewAccess(IModel referencedLibrary) {
    //When a library can be added as a reference, it will have a read-only access policy
    return AccessPolicy.READ_ONLY;
  }
}
