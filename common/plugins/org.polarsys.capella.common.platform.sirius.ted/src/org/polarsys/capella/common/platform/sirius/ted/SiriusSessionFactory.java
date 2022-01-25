/*******************************************************************************
 * Copyright (c) 2008, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *  Thales - new method 'getDerivedSemanticResources' (see class comment)
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.session.SessionFactoryImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Specific SessionFactory to override {@link DAnalysisSessionImpl#getSemanticResources()}.
 */
public class SiriusSessionFactory extends SessionFactoryImpl implements SessionFactory {
  /*
   * FIXME: This method is overridden so that the resource set is NOT configured with
   * ResourceSetImpl.MappedResourceLocator. This can make the resource set end up with duplicated resources when the
   * session is closed (see Bug 1755)
   */
  @Override
  protected void configureDomain(TransactionalEditingDomain transactionalEditingDomain, URI sessionResourceUri) {
  }

  @Override
  protected Session createSession(DAnalysis analysis, final TransactionalEditingDomain transactionalEditingDomain) {
    return new DAnalysisSessionImpl(analysis) {
      @Override
      public Collection<Resource> getSemanticResources() {
        Collection<Resource> semanticResources = new ArrayList<Resource>(super.getSemanticResources());
        semanticResources.addAll(new DerivedResourcesHelper()
            .getDerivedSemanticResources(this.getTransactionalEditingDomain(), semanticResources));
        return Collections.unmodifiableCollection(semanticResources);
      }
    };
  }

  @Override
  protected void checkResource(URI sessionResourceURI, ResourceSet resourceSet) {
    new SessionMetadataHelper().checkMetadata(sessionResourceURI, resourceSet);
  }

  @Override
  protected void createAdditionalResources(Collection<Resource> additionalResources,
      TransactionalEditingDomain transactionalEditingDomain, URI sessionResourceURI, IProgressMonitor monitor) {
    Resource metadataResource = new SessionMetadataHelper().createMetadataResource(transactionalEditingDomain,
        sessionResourceURI, monitor);
    if (metadataResource != null) {
      additionalResources.add(metadataResource);
    }
  }

  @Override
  protected void completeSessionCreation(Session session, Collection<Resource> additionalResources,
      IProgressMonitor monitor) {
    if (additionalResources.size() == 1) {
      new SessionMetadataHelper().registerMetadataResource(additionalResources.iterator().next(), session, monitor);
    }
  }

  public static class DerivedResourcesHelper {
    /**
     * Adds new derived semantic resources that shall not be present in aird model
     * 
     * @param editingDomain
     *          the editing domain
     * @param nonDerivedSemanticResources
     *          the resources already present in aird model
     * @return all the derived semantic resources
     */
    public Collection<Resource> getDerivedSemanticResources(TransactionalEditingDomain editingDomain,
        Collection<Resource> nonDerivedSemanticResources) {
      Collection<Resource> derivedSemanticResources = new ArrayList<Resource>();

      if (editingDomain != null) {
        for (IDerivedSemanticResourceProvider provider : getAllDerivedSemanticResourceProviders()) {
          for (Resource resource : provider.getDerivedSemanticResources(editingDomain)) {
            if (!nonDerivedSemanticResources.contains(resource) && !derivedSemanticResources.contains(resource)) {
              derivedSemanticResources.add(resource);
            }
          }
        }
      }
      return derivedSemanticResources;
    }

    private static List<IDerivedSemanticResourceProvider> _derivedSemanticResourceProviders;

    private static final String DERIVED_SEMANTIC_RESOURCE_PROVIDER_EXTENSION_ID = "derivedSemanticResourceProvider"; //$NON-NLS-1$

    /**
     * 
     */
    protected List<IDerivedSemanticResourceProvider> getAllDerivedSemanticResourceProviders() {
      if (null == _derivedSemanticResourceProviders) {
        _derivedSemanticResourceProviders = new ArrayList<IDerivedSemanticResourceProvider>();
        List<IConfigurationElement> BQProvider = Arrays.asList(ExtensionPointHelper.getConfigurationElements(
            PlatformSiriusTedActivator.getDefault().getBundle().getSymbolicName(), DERIVED_SEMANTIC_RESOURCE_PROVIDER_EXTENSION_ID));
        for (IConfigurationElement configurationElement : BQProvider) {
          IDerivedSemanticResourceProvider contrib = (IDerivedSemanticResourceProvider) ExtensionPointHelper
              .createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          if (contrib != null) {
            _derivedSemanticResourceProviders.add(contrib);
          }
        }
      }
      return _derivedSemanticResourceProviders;
    }
  }

  public static class SessionMetadataHelper {

    static Boolean loaded = Boolean.FALSE;

    static IMetadataProvider provider = null;

    public IMetadataProvider getProvider() {

      if (loaded == Boolean.FALSE) {
        for (IConfigurationElement configurationElement : ExtensionPointHelper
            .getConfigurationElements(PlatformSiriusTedActivator.getDefault().getBundle().getSymbolicName(), "metadataProvider")) {
          IMetadataProvider contrib = (IMetadataProvider) ExtensionPointHelper.createInstance(configurationElement,
              ExtensionPointHelper.ATT_CLASS);
          if (contrib != null) {
            provider = contrib;
          }
        }
        loaded = Boolean.TRUE;
      }

      return provider;
    }

    public void checkMetadata(URI sessionResourceURI, ResourceSet set) {
      IMetadataProvider provider = getProvider();
      if (provider != null) {
        IStatus status = provider.checkMetadata(sessionResourceURI, set);
        if (!status.isOK()) {
          throw new MetadataException(status);
        }
      }
    }

    public void registerMetadataResource(Resource resource, final Session session, final IProgressMonitor monitor) {
      IMetadataProvider provider = getProvider();
      if (provider != null) {
        provider.registerMetadataResource(resource, session, monitor);
      }
    }

    /**
     * Creates the AFM metadata resource</br>
     * (This resource will only be created when the aird resource does not exist yet, if the aird resource already
     * exists then a model migration shall be done)
     * 
     * @param domain
     * @param resourceURI
     * @param monitor
     * @return the created resource (may be null if the resource does not belong to a Capella project)
     */
    public Resource createMetadataResource(TransactionalEditingDomain domain, URI resourceURI,
        IProgressMonitor monitor) {
      IMetadataProvider provider = getProvider();
      if (provider != null) {
        return provider.createMetadataResource(domain, resourceURI, monitor);
      }
      return null;
    }

    /**
     * @deprecated use CapellaResourceHelper instead
     */
    @Deprecated
    protected static boolean isCapellaProject(URI uri) {
      try {
        if (uri.isPlatformResource()) {
          IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
          IProject project = file.getProject();
          return project.hasNature("org.polarsys.capella.project.nature")
              || project.hasNature("org.polarsys.capella.library.nature");
        }

      } catch (Exception ex) {
        // This is not a valid local project
      }

      return false;
    }
  }
}
