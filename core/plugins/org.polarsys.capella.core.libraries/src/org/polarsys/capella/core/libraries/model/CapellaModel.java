/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.Activator;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

public class CapellaModel extends AbstractCapellaModel implements IModel.Edit {

  private static final String ACTIVE_STATE_PREF_ID = "LIBRARY_ACTIVE_STATE_INFORMATIONS"; //$NON-NLS-1$

  public CapellaModel(ModelIdentifier identifier_p, TransactionalEditingDomain domain_p) {
    super(identifier_p, domain_p);
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
            identifier = createModelIdentifier(((InternalEObject) library).eProxyURI().fragment(), ((InternalEObject) library).eProxyURI().trimFragment());
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

  protected static ModelIdentifier createModelIdentifier(String identifier, URI uri_p) {
    return new ModelIdentifier(identifier, uri_p);
  }

  public static ModelIdentifier createModelIdentifier(Resource resource_p) {
    Project project = CapellaLibraryExt.getProject(resource_p);
    if (project != null) {
      Resource rootResource = project.eResource();
      ModelInformation information = CapellaLibraryExt.getModelInformation(rootResource, false);
      String identifier = CapellaLibraryExt.getIdentifier(information);
      return createModelIdentifier(identifier, rootResource.getURI());
    }
    return null;
  }

  public static IModel createModel(ModelIdentifier identifier_p, TransactionalEditingDomain domain) {
    if (identifier_p == null) {
      return null;
    }
    return new CapellaModel(identifier_p, domain);
  }

  @Override
  public void addReference(final IModel referencedLibrary_p) {
    // Enable proxy resolution before adding the referenced library
    CrossReferencerHelper.enableResolveProxy(_domain);

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(referencedLibrary_p, true);

        // if reference is already made, we don't add another reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
            return;
          }
        }

        // otherwise, we add a reference
        LibraryReference result = LibrariesFactory.eINSTANCE.createLibraryReference();
        result.setLibrary(target);
        source.getOwnedReferences().add(result);
        result.setAccessPolicy(getDefaultNewAccess(referencedLibrary_p));

        // Sirius session requires the semantic target resource to be added to the <semanticResources> reference
        // we can't use session.addSemanticResources unload the resource if already loaded.......
        // new AddSemanticResourceCommand(session, ((CapellaModel) referencedLibrary_p).uriSemanticFile, new
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
  public void removeReference(final IModel referencedLibrary_p) {
    // Enable proxy resolution before removing the referenced library
    CrossReferencerHelper.enableResolveProxy(_domain);

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(referencedLibrary_p, true);

        LibraryReference toDelete = null;

        // if reference is made, we remove the reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if (reference.getLibrary() != null) {
            if (reference.getLibrary().equals(target)) {
              toDelete = reference;
              break;
            } else if (reference.getLibrary().eIsProxy() && referencedLibrary_p.getIdentifier().getId()
                .equals(((InternalEObject) reference.getLibrary()).eProxyURI().fragment())) {
              toDelete = reference;
              break;
            }
          }
        }

        if (toDelete != null) {

          // session.removeSemanticResources unload the resource and depending ones (so the root model resource too)
          Resource toRemove = toDelete.getLibrary().eResource();
          Session session = SessionManager.INSTANCE.getSession(source);
          if (session instanceof DAnalysisSessionImpl) {
            for (final DAnalysis analysis : ((DAnalysisSessionImpl) session).allAnalyses()) {
              analysis.getSemanticResources()
                  .remove(new ResourceDescriptor(toDelete.getLibrary().eResource().getURI()));
            }
          }

          new CapellaDeleteCommand(ExecutionManagerRegistry.getInstance().getExecutionManager(_domain),
              Collections.singleton(toDelete), false, false, false).execute();
          if (toRemove != null) {
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
   * @param source_p
   */
  protected void notifyLibraryChange(ModelInformation source_p) {

    // TODO a workaround to update the views of the ModelInformation, even if we don't really touch it.

    // we should add an event based mechanism on library state changes, instead of being based on EMF underlayer, but still better than :

    // ResourceSetSync sync = ResourceSetSync.getOrInstallResourceSetSync(domain_p);
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
    // public int getFeatureID(Class<?> expectedClass_p) {
    // return LibrariesPackage.MODEL_INFORMATION__OWNED_REFERENCES;
    // }
    // };
    // source.eNotify(notification); //this doesn't update the view since no transaction change is triggered.

    LibraryReference reference = LibrariesFactory.eINSTANCE.createLibraryReference();
    source_p.getOwnedReferences().add(reference);
    source_p.getOwnedReferences().remove(reference);
  }

  @Override
  public void setAccess(final IModel library_p, final AccessPolicy currentAccessPolicy_p) {

    ExecutionManagerRegistry.getInstance().getExecutionManager(_domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        ModelInformation source = getModelInformation(CapellaModel.this, true);
        ModelInformation target = getModelInformation(library_p, true);

        LibraryReference result = null;

        // if reference is already made, we don't add another reference
        for (LibraryReference reference : source.getOwnedReferences()) {
          if ((reference.getLibrary() != null) && reference.getLibrary().equals(target)) {
            result = reference;
          }
        }

        if (result != null) {
          result.setAccessPolicy(currentAccessPolicy_p);
        }

        notifyLibraryChange(source);
      }
    });

  }

  @Override
  public boolean canReference(IModel referencedLibrary_p) {
    return (referencedLibrary_p instanceof CapellaModel) && ((CapellaModel) referencedLibrary_p).isLibrary();
  }

  @Override
  public boolean isActive(IModel library) {
    Preferences activeLibraryPrefInfos = new ConfigurationScope().getNode(Activator.PLUGIN_ID).node(ACTIVE_STATE_PREF_ID);
    Preferences libraryContextPref = activeLibraryPrefInfos.node(this.getIdentifier().getName());
    return libraryContextPref.getBoolean(library.getIdentifier().getName(), getDefaultActiveState(library));
  }

  @Override
  public void setActive(IModel library, boolean activeState) {
    final Preferences activeLibraryPrefInfos = new ConfigurationScope().getNode(Activator.PLUGIN_ID).node(ACTIVE_STATE_PREF_ID);
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
  public AccessPolicy getDefaultNewAccess(IModel referencedLibrary_p) {
    //when a library can be added as a reference, it will have a readonly access policy
    return AccessPolicy.READ_ONLY;
  }

}
