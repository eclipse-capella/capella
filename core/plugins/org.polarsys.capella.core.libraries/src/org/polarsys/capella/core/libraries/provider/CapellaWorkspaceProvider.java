/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.IModelIdentifier;
import org.polarsys.capella.common.libraries.provider.ILibraryProviderListener.LibraryProviderEvent;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.model.ModelIdentifier;
import org.polarsys.capella.core.libraries.utils.IFileRequestor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class provides IModelIdentifiers/IAbstractModel for all Capella model files in the current workspace<br>
 * <br>
 * When accessing the method getAvailableModels, it retrieve a ModelIdentifier based on the root-project-id of the
 * file.<br>
 * When accessing the method getModelDefinition, it retrieve a IAbstractModel: <br>
 * getModelDefinition mechanism:<br>
 * When the wanted resource is not loaded in the given editing domain, we don't load it in the given domain but access
 * to it via<br>
 * another editing domain, the workspaceDomain. On this domain, we will load only the root project and its
 * ModelInformation/LibraryReferences.<br>
 * In that way, when accessing via IAbstractModel.getReferences(), we will have necessary informations to know which
 * references have the model without loading all the resource.<br>
 * <br>
 * This assumption is ok since :<br>
 * 1- when the user click on ManageReference or other library's action, the selected resource is loaded in the given
 * domain_p, so the modelDefinition of the selection is always in the given model<br>
 * 2- there is no manipulation of internal EObject / domain of a ModelDefinition otherwise than through getProject(ted)
 * method, which will retrieve here the resource in the given ted.
 */
public class CapellaWorkspaceProvider extends AbstractCapellaProvider
    implements IResourceChangeListener, IResourceDeltaVisitor {

  protected final TransactionalEditingDomain workspaceDomain = CapellaWorkspaceProvider.createEditingDomain();

  protected Collection<IModelIdentifier> _modelIds = null;

  protected final Map<IModelIdentifier, IModel> _models = new HashMap<IModelIdentifier, IModel>();

  /**
   * Returns a transient editing domain with lazy capella resources
   */
  public static TransactionalEditingDomain createEditingDomain() {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    TransactionalEditingDomain domain = manager.getEditingDomain();

    domain.setID(getWorkspaceEditingDomainID());
    domain.getResourceSet().setResourceFactoryRegistry(new ResourceFactoryRegistryImpl() {
      @Override
      protected Factory delegatedGetFactory(URI uri, String contentTypeIdentifier) {
        return new Resource.Factory() {

          @Override
          public Resource createResource(URI resourceUri) {
            return new XMIResourceImpl(resourceUri) {

              @Override
              protected XMLHelper createXMLHelper() {
                return new XMLHelperImpl();
              }

              @Override
              protected XMLLoad createXMLLoad() {
                return new XMILoadImpl(createXMLHelper()) {

                  @Override
                  protected DefaultHandler makeDefaultHandler() {
                    return new LibrarySAXXMIHandler(resource, helper, options);
                  }

                };
              }
            };
          }
        };
      }
    });
    return domain;
  }

  /**
   * Constructor.
   */
  public CapellaWorkspaceProvider() {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    workspace.addResourceChangeListener(this);
  }

  @Override
  public Collection<IModelIdentifier> getAvailableModels() {
    if (_modelIds == null) {
      _modelIds = new ArrayList<IModelIdentifier>();
      for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
        if (project.isOpen()) {
          for (IModelIdentifier identifier : getModels(project)) {
            // FIXME in some cases (not systematic), it seems that '_modelIds' can be cleared by another thread...
            // needs a deeper analysis to understand why
            if (_modelIds != null) {
              _modelIds.add(identifier);
            }
          }
        }
      }
    }
    return _modelIds;
  }

  public Collection<IModelIdentifier> getAvailableModels(TransactionalEditingDomain domain) {
      if (isLocalProject(domain)) {
          return getAvailableModels();
      }
      return Collections.EMPTY_LIST;
  }

  @Override
  public IModel getModelDefinition(IModelIdentifier identifier, TransactionalEditingDomain domain) {
    // Preconditions
    if (!(identifier instanceof ModelIdentifier)) {
      return null;
    }
    URI modelURI = ((ModelIdentifier) identifier).getUri();
    if (!isHandled(modelURI)) {
      return null;
    }
    // we use the modelDefinition from the given editing domain only if the resource is loaded in the resourceSet,
    // otherwise, we use the default model
    // definition (with a modelDefinition from the domain instead of the default model definition, we ensure that
    // references are at the latest version,
    // even if resources are not saved)
    if (domain != null) {
      Resource resource = getResource(domain, modelURI, false);
      if ((resource != null) && resource.getErrors().isEmpty()) {
        return createModel((ModelIdentifier) identifier, domain);
      }
    }
    // if no modelDefinition are found, we retrieve the one from the workspace
    if (!_models.containsKey(identifier)) {
      Resource resource = getResource(workspaceDomain, modelURI, true);
      if ((resource != null) && resource.getErrors().isEmpty()) {
        IModel model = createModel((ModelIdentifier) identifier, workspaceDomain);
        if (model != null) {
          _models.put(identifier, model);
        }
      }
    }
    return _models.get(identifier);

  }

  protected Collection<IModelIdentifier> getModels(IProject project) {
    // Precondition
    if (!project.isOpen()) {
      return Collections.emptyList();
    }
    // for all semantic models in the eclipse project, ...
    List<IFile> files = new IFileRequestor().search(project, CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, false);
    List<IModelIdentifier> models = new ArrayList<IModelIdentifier>(files.size());
    for (IFile mmFile : files) {
      models.addAll(getIdentifiers(mmFile));
    }

    return models;
  }

  protected Collection<IModelIdentifier> getIdentifiers(IFile mmFile) {
    URI uri = EcoreUtil2.getURI(mmFile);
    Resource resource = getResource(workspaceDomain, uri, true);
    IModelIdentifier identifier = createModelIdentifier(resource);
    if (identifier == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList((IModelIdentifier) createModelIdentifier(resource));
  }

  /**
   * Listen on changes on {@link CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION} files (i.e. a library/project is
   * now available or no more available) </br>
   * {@inheritDoc}
   */
  @Override
  public void resourceChanged(IResourceChangeEvent event) {

    IResourceDelta delta = event.getDelta();
    try {
      if (delta != null) {
        delta.accept(this);
      }
    } catch (CoreException coreException) {
      // an exception occurs, we remove the cache since we don't really know what happened
      clear(delta);
    }
  }

  protected boolean clear(IResourceDelta delta) {
    _modelIds = null;
    _models.clear();

    workspaceDomain.getResourceSet().getResources().clear();
    notifyListeners(new LibraryProviderEvent());
    return true;
  }

  @Override
  public boolean visit(IResourceDelta delta) {
    IResource resource = delta.getResource();

    switch (resource.getType()) {
    case IResource.ROOT:
    case IResource.PROJECT:
    case IResource.FOLDER:
      return true;
    case IResource.FILE:
      if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(resource.getFileExtension())) {
        clear(delta);
      }
      return false;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IModelIdentifier getModelIdentifier(URI semanticUri) {
    if (isHandled(semanticUri)) {
      Resource resource = getResource(workspaceDomain, semanticUri, true);
      if ((resource != null) && resource.getErrors().isEmpty()) {
        if (CapellaResourceHelper.isCapellaResource(semanticUri)) {
          return CapellaModel.createModelIdentifier(resource);
        }
      }
    }
    return null;
  }

  /**
   * Is the given editing domain the workspace editing domain?
   */
  public boolean isWorkspaceDomain(TransactionalEditingDomain domain) {
    return getWorkspaceEditingDomainID().equals(domain.getID());
  }

  private static String getWorkspaceEditingDomainID() {
    return CapellaWorkspaceProvider.class.getCanonicalName();
  }

  @Override
  protected boolean isHandled(URI uri) {
    // Handle only local, semantic URIs
    return (uri.isPlatformResource() || uri.isPlatformPlugin()) && CapellaResourceHelper.isCapellaResource(uri);
  }

}
