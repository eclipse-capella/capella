/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *  Thales - new method 'getDerivedSemanticResources' (see class comment)
 *******************************************************************************/
package org.polarsys.capella.common.platform.sirius.ted;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointURIConverter;
import org.eclipse.sirius.business.internal.resource.parser.AirDCrossReferenceAdapterImpl;
import org.eclipse.sirius.business.internal.session.SessionFactoryImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * This class is a fork of {@link SessionFactoryImpl}.<br>
 * The original class is final and cannot be override.<br>
 * The fork has been done to allow overriding {@link DAnalysisSessionImpl#getSemanticResources()}. 
 */
public class SiriusSessionFactory implements SessionFactory {

  /**
   * 
   */
  public SiriusSessionFactory() {
    // do nothing
  }

  /**
   * @see org.eclipse.sirius.business.api.session.factory.SessionFactory#createSession(org.eclipse.emf.common.util.URI, org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public Session createSession(URI sessionResourceURI, IProgressMonitor monitor) throws CoreException {
    final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(sessionResourceURI);
    final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(set);

    // Configure the resource set, its is done here and not before the
    // editing domain creation which could provide its own resource set.
    transactionalEditingDomain.getResourceSet().eAdapters().add(new AirDCrossReferenceAdapterImpl());
    if (Movida.isEnabled()) {
        transactionalEditingDomain.getResourceSet()
                .setURIConverter(new ViewpointURIConverter((ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance()));
    }

    // Create or load the session.
    boolean alreadyExistingResource = exists(sessionResourceURI, transactionalEditingDomain.getResourceSet());
    Session session = null;
    if (alreadyExistingResource) {
        session = loadSessionModelResource(sessionResourceURI, transactionalEditingDomain, monitor);
    } else {
        session = createSessionResource(sessionResourceURI, transactionalEditingDomain, monitor);
    }
    return session;
  }

  /**
   * Used to check if a Resource exists at this URI with EMF 2.3 since from
   * EMF 2.4 URIConverter.exists(URI) can be used.
   * 
   * @param sessionResourceURI
   *            the URI of the Resource to which checks existence
   * @param resourceSet
   * @return true if a Resource exists at this URI
   */
  private boolean exists(URI sessionResourceURI, ResourceSet resourceSet) {
      boolean exists = false;
      InputStream inputStream = null;
      try {
          inputStream = resourceSet.getURIConverter().createInputStream(sessionResourceURI);
          if (inputStream != null) {
              exists = true;
          }
      } catch (IOException e) {
          // Do nothing, we consider that the resource does not exist
      } finally {
          if (inputStream != null) {
              try {
                  inputStream.close();
              } catch (IOException e) {
                  // Do nothing, we consider that the resource does not exist
              }
          }
      }
      return exists;
  }

  private Session loadSessionModelResource(URI sessionResourceURI, TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
      ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
      // Make ResourceSet aware of resource loading with progress monitor
      ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));

      Session session = null;
      try {
          monitor.beginTask("Session loading", 4);
          // Get resource
          final Resource sessionModelResource = resourceSet.getResource(sessionResourceURI, true);
          if (sessionModelResource != null) {
              DAnalysis analysis = null;
              if (!sessionModelResource.getContents().isEmpty() && (sessionModelResource.getContents().get(0) instanceof DAnalysis)) {
                  analysis = (DAnalysis) sessionModelResource.getContents().get(0);
                  session = new DAnalysisSessionImpl(analysis) {
                    @Override
                    public Collection<Resource> getSemanticResources() {
                      Collection<Resource> semanticResources = new ArrayList<Resource>(super.getSemanticResources());
                      semanticResources.addAll(getDerivedSemanticResources(transactionalEditingDomain, semanticResources));
                      return Collections.unmodifiableCollection(semanticResources);
                    }
                  };
                  monitor.worked(2);
              } else {
                  session = createSessionResource(sessionResourceURI, transactionalEditingDomain, new SubProgressMonitor(monitor, 2));
              }
          }
      } catch (WrappedException e) {
          throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "Error while loading representations file", e));
      } finally {
          monitor.done();
          ResourceSetUtil.resetProgressMonitor(resourceSet);
      }
      return session;
  }

  private Session createSessionResource(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
      Session session = null;
      try {
          monitor.beginTask("Session creation", 2);
          Resource sessionModelResource = new ResourceSetImpl().createResource(sessionResourceURI);
          DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
          sessionModelResource.getContents().add(analysis);
          try {
              sessionModelResource.save(Collections.emptyMap());
          } catch (IOException e) {
              throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed", e));
          }
          monitor.worked(1);
          // Now load it from the TED
          sessionModelResource = transactionalEditingDomain.getResourceSet().getResource(sessionResourceURI, true);
          if (sessionModelResource.getContents().isEmpty()) {
              throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed: the resource content is empty."));
          }
          analysis = (DAnalysis) sessionModelResource.getContents().get(0);
          session = new DAnalysisSessionImpl(analysis) {
            @Override
            public Collection<Resource> getSemanticResources() {
              Collection<Resource> semanticResources = new ArrayList<Resource>(super.getSemanticResources());
              semanticResources.addAll(getDerivedSemanticResources(transactionalEditingDomain, semanticResources));
              return Collections.unmodifiableCollection(semanticResources);
            }
          };
          monitor.worked(1);
      } finally {
          monitor.done();
      }
      return session;
  }

  /**
   * Adds new derived semantic resources that shall not be present in aird model
   * 
   * @param editingDomain the editing domain
   * @param nonDerivedSemanticResources the resources already present in aird model
   * @return all the derived semantic resources
   */
  protected Collection<Resource> getDerivedSemanticResources(TransactionalEditingDomain editingDomain, Collection<Resource> nonDerivedSemanticResources) {
    Collection<Resource> derivedSemanticResources = new ArrayList<Resource>();
    
    for (IDerivedSemanticResourceProvider provider : getAllDerivedSemanticResourceProviders()) {
      for (Resource resource : provider.getDerivedSemanticResources(editingDomain)) {
        if (!nonDerivedSemanticResources.contains(resource) && !derivedSemanticResources.contains(resource)) {
          derivedSemanticResources.add(resource);
        }
      }
    }
    return derivedSemanticResources;
  }            

  private List<IDerivedSemanticResourceProvider> _derivedSemanticResourceProviders;
  private static final String DERIVED_SEMANTIC_RESOURCE_PROVIDER_EXTENSION_ID = "derivedSemanticResourceProvider"; //$NON-NLS-1$

  /**
   * 
   */
  protected List<IDerivedSemanticResourceProvider> getAllDerivedSemanticResourceProviders() {
    if (null == _derivedSemanticResourceProviders) {
      _derivedSemanticResourceProviders = new ArrayList<IDerivedSemanticResourceProvider>();
      List<IConfigurationElement> BQProvider =
          Arrays.asList(ExtensionPointHelper.getConfigurationElements(PlatformSiriusTedActivator.getDefault().getPluginId(), DERIVED_SEMANTIC_RESOURCE_PROVIDER_EXTENSION_ID));
      for (IConfigurationElement configurationElement : BQProvider) {
        IDerivedSemanticResourceProvider contrib = (IDerivedSemanticResourceProvider) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (contrib != null) {
          _derivedSemanticResourceProviders.add(contrib);
        }
      }
    }
    return _derivedSemanticResourceProviders;
  }

  /**
   * @see org.eclipse.sirius.business.api.session.factory.SessionFactory#createDefaultSession(org.eclipse.emf.common.util.URI)
   */
  @Override
  public Session createDefaultSession(URI sessionResourceURI) throws CoreException {
    return createSession(sessionResourceURI, new NullProgressMonitor());
  }
}
