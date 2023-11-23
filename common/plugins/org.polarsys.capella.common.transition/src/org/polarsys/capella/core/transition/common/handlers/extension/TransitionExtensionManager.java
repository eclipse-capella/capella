/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.extension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeRetriever;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

import com.google.common.collect.SetMultimap;

public class TransitionExtensionManager {

  public static TransitionExtensionManager eINSTANCE = new TransitionExtensionManager();
  String EXTENSION_POINT_ID = "org.polarsys.capella.core.transition.extension";
  String EXTENSION_POINT_CLASS = "transitionExtension";

  Set<ITransitionExtension> extensions;
  
  Set<IScopeFilter> additionalFilters;
  Set<IScopeRetriever> additionalRetrievers;

  private TransitionExtensionManager() {
    initializeTransitionExtensions();
  }

  private Set<ITransitionExtension> initializeTransitionExtensions() {
    if (extensions == null) {
      extensions = new HashSet<ITransitionExtension>();
      IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

      for (IConfigurationElement configurationElement : extensionRegistry
          .getConfigurationElementsFor(EXTENSION_POINT_ID)) {
        try {
          ITransitionExtension extension = (ITransitionExtension) configurationElement
              .createExecutableExtension(EXTENSION_POINT_CLASS);
          extensions.add(extension);
        } catch (CoreException e) {
          e.printStackTrace();
        }
      }

    }
    return extensions;
  }

  public boolean isActive(ITransitionExtension extension, ResourceSet rSet) {
    ViewpointManager vpManager = ViewpointManager.getInstance(rSet);
    return vpManager.isActive(extension.getViewpointID());
  }

  public Set<IScopeFilter> getAdditionalScopeFilters(IContext context) {
    Set<IScopeFilter> filters = new HashSet<IScopeFilter>();
    for (ITransitionExtension extension : extensions) {
      if (isActive(extension, getResourceSetFromContext(context))) {
        filters.addAll(extension.getScopeFilters());
      }
    }
    return filters;
  }

  public Set<IScopeRetriever> getAdditionalScopeRetrievers(IContext context) {
    Set<IScopeRetriever> retrievers = new HashSet<IScopeRetriever>();
    for (ITransitionExtension extension : extensions) {
      if (isActive(extension, getResourceSetFromContext(context))) {
        retrievers.addAll(extension.getScopeRetrievers());
      }
    }
    return retrievers;
  }

  public Set<Entry<EObject, EObject>> initializeBlockArchitecture(BlockArchitecture source,
      BlockArchitecture target, boolean createTarget) {

    Set<Entry<EObject, EObject>> additionalBlockArchitectureMappings = new HashSet<Entry<EObject, EObject>>();

    for (ITransitionExtension extension : extensions) {
      if (isActive(extension, target.eResource().getResourceSet())) {
        Set<Entry<EObject, EObject>> additionalMappings = extension.initializeBlockArchitecture(source, target,
            createTarget);
        for (Entry<EObject, EObject> mapping : additionalMappings) {
          additionalBlockArchitectureMappings.add(mapping);
        }
      }
    }
    return additionalBlockArchitectureMappings;
  }

  public String getAdditionalMatchKey(EObject element_p, ITreeDataScope<EObject> scope_p) {
    for (ITransitionExtension extension : extensions) {
      if (isActive(extension, element_p.eResource().getResourceSet())) {
        String matchKey = extension.getAdditionalMatchKey(element_p, scope_p);
        if (matchKey != null) {
          return matchKey;
        }
      }
    }
    return null;
  }

  private ResourceSet getResourceSetFromContext(IContext ctx) {
    ResourceSet rSet = null;
    Collection<Object> selection = (Collection) ctx.get(ITransitionConstants.TRANSITION_SELECTION);
    for (Object selected : selection) {
      if (selected instanceof EObject) {
        return ((EObject) selected).eResource().getResourceSet();
      }
    }
    return rSet;
  }

  public void addAdditionnalTraceabilityMappings(SetMultimap<EObject, EObject> map, PhysicalArchitecture sourcePA,
      BlockArchitecture targetBlockArchitecture) {
    
    for (ITransitionExtension extension : extensions) {
      if (isActive(extension, sourcePA.eResource().getResourceSet())) {
        extension.addAdditionnalTraceabilityMappings(map, sourcePA, targetBlockArchitecture);
      }
    }
  }

}
