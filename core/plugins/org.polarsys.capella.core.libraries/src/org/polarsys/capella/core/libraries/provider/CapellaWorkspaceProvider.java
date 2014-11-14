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
package org.polarsys.capella.core.libraries.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
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
import org.polarsys.capella.common.libraries.provider.ILibraryProviderListener.ILibraryProviderEvent;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.model.ModelIdentifier;
import org.polarsys.capella.core.libraries.utils.IFileRequestor;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class provides IModelIdentifiers/IAbstractModel for all Capella model files in the current workspace
 * 
 * When accessing the method getAvailableModels, it retrieve a ModelIdentifier based on the root-project-id of the file.
 * When accessing the method getModelDefinition, it retrieve a IAbstractModel: 
 * 
 * getModelDefinition mechanism:
 * When the wanted resource is not loaded in the given editing domain, we don't load it in the given domain but access to it via 
 * another editing domain, the workspaceDomain. On this domain, we will load only the root project and its ModelInformation/LibraryReferences. 
 * In that way, when accessing via IAbstractModel.getReferences(), we will have necessary informations to know which references have the model without loading all the resource.
 * 
 * This assumption is ok since : 
 * 1- when the user click on ManageReference or other library's action, the selected resource is loaded in the given domain_p, so the modelDefinition of the selection is always in the given model
 * 2- there is no manipulation of internal EObject / domain of a ModelDefinition otherwise than through getProject(ted) method, which will retrieve here the resource in the given ted.
 *
 */
public class CapellaWorkspaceProvider extends AbstractCapellaProvider implements IResourceChangeListener, IResourceDeltaVisitor {

  TransactionalEditingDomain workspaceDomain = CapellaWorkspaceProvider.createEditingDomain();

  Collection<IModelIdentifier> _modelIds = null;

  HashMap<IModelIdentifier, IModel> _models = new HashMap<IModelIdentifier, IModel>();

  /**
   * Returns a transient editing domain with lazy capella resources
   */
  public static TransactionalEditingDomain createEditingDomain() {
    ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
    TransactionalEditingDomain domain = manager.getEditingDomain();

    domain.setID(CapellaWorkspaceProvider.class.getCanonicalName());
    domain.getResourceSet().setResourceFactoryRegistry(new ResourceFactoryRegistryImpl() {
      @Override
      protected Factory delegatedGetFactory(URI uri_p, String contentTypeIdentifier_p) {
        return new Resource.Factory() {

          @Override
          public Resource createResource(URI uri_p) {
            return new XMIResourceImpl(uri_p) {

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
            _modelIds.add(identifier);
          }
        }
      }
    }
    return _modelIds;
  }

  @Override
  public IModel getModelDefinition(IModelIdentifier identifier_p, TransactionalEditingDomain domain_p) {
    if (identifier_p instanceof ModelIdentifier) {
      //we use the modelDefinition from the given editing domain only if the resource is loaded in the resourceSet, otherwise, we use the default model definition
      //(with a modelDefinition from the domain instead of the default model definition, we ensure that references are at the latest version, 
      //even if resources are not saved)
      if (domain_p != null) {
        Resource resource = getResource(domain_p, ((ModelIdentifier) identifier_p).getUri(), false);
        if ((resource != null) && isHandled(((ModelIdentifier) identifier_p).getUri()) && resource.getErrors().isEmpty()) {
          return createModel((ModelIdentifier) identifier_p, domain_p);
        }
      }
      //if no modelDefinition are found, we retrieve the one from the workspace
      if (!_models.containsKey(identifier_p)) {
        Resource resource = getResource(workspaceDomain, ((ModelIdentifier) identifier_p).getUri(), true);
        if ((resource != null) && isHandled(((ModelIdentifier) identifier_p).getUri()) && resource.getErrors().isEmpty()) {
          IModel model = createModel((ModelIdentifier) identifier_p, workspaceDomain);
          if (model != null) {
            _models.put(identifier_p, model);
          }
        }
      }
      return _models.get(identifier_p);
    }
    return null;
  }

  protected Collection<IModelIdentifier> getModels(IProject project_p) {
    List<IModelIdentifier> models = new ArrayList<IModelIdentifier>();

    if (project_p.isOpen()) {
      // for all semantic models in the eclipse project, ...
      List<IFile> files = new IFileRequestor().search(project_p, CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION, false);
      for (IFile mmFile : files) {
        models.addAll(getIdentifiers(mmFile));
      }
    }
    return models;
  }

  protected Collection<IModelIdentifier> getIdentifiers(IFile mmFile_p) {
    URI uri = EcoreUtil2.getURI(mmFile_p);
    Resource resource = getResource(workspaceDomain, uri, true);
    IModelIdentifier identifier = createModelIdentifier(resource);
    if (identifier == null) {
      return Collections.emptyList();
    }
    return Collections.singletonList((IModelIdentifier) createModelIdentifier(resource));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resourceChanged(IResourceChangeEvent event_p) {
    IResourceDelta delta = event_p.getDelta();
    try {
      if (delta != null) {
        delta.accept(this);
      }
    } catch (CoreException exception_p) {
      //an exception occurs, we remove the cache since we don't really know what happened
      clear(delta);
    }
  }

  protected boolean clear(IResourceDelta delta) {
    _modelIds = null;
    _models.clear();
    workspaceDomain.getResourceSet().getResources().clear();
    notifyListeners(new ILibraryProviderEvent());
    return true;
  }

  @Override
  public boolean visit(IResourceDelta delta) {
    IResource res = delta.getResource();

    if (res instanceof IWorkspaceRoot) {
      return clear(delta);

    } else if (res instanceof IProject) {
      return clear(delta);

    } else if (res instanceof IFile) {
      if (CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION.equals(res.getFileExtension())) {
        return clear(delta);
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IModelIdentifier getModelIdentifier(URI semanticUri_p) {
    if (isHandled(semanticUri_p)) {
      Resource resource = getResource(workspaceDomain, semanticUri_p, true);
      if ((resource != null) && resource.getErrors().isEmpty()) {
        if (CapellaResourceHelper.isCapellaResource(semanticUri_p)) {
          return CapellaModel.createModelIdentifier(resource);
        }
      }
    }
    return null;
  }

  @Override
  protected boolean isHandled(URI uri_p) {
    return (uri_p.isPlatformResource() || uri_p.isPlatformPlugin()) && CapellaResourceHelper.isCapellaResource(uri_p);
  }

}
