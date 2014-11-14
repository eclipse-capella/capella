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
package org.polarsys.capella.core.re.handlers.traceability;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.merge.ExtendedComparison;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.MatchConfiguration;
import org.polarsys.capella.common.re.handlers.traceability.MatchTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class CapellaMatchConfiguration extends MatchConfiguration {

  public CapellaMatchConfiguration() {
  }

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new MatchTraceabilityHandler((ExtendedComparison) fContext_p.get(ITransitionConstants.MERGE_COMPARISON), getIdentifier(fContext_p)) {

      @Override
      public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
        Collection<EObject> parent = super.retrieveTracedElements(source_p, context_p);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }

        Collection<EObject> elements = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
        CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
        CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);

        Resource destinationResource = elements.iterator().next().eResource();
        Resource sourceResource = (sourceElement == null) || (sourceElement.eResource() == null) ? destinationResource : sourceElement.eResource();
        Resource targetResource = (targetElement == null) || (targetElement.eResource() == null) ? destinationResource : targetElement.eResource();

        if ((sourceElement != null) && (targetElement != null) && (sourceResource != targetResource)) {
          SystemEngineering sourceSE = (SystemEngineering) EcoreUtil2.getFirstContainer(source_p, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
          SystemEngineering destRE = (SystemEngineering) ((Project) destinationResource.getContents().get(0)).getOwnedModelRoots().get(0);
          if (source_p instanceof BlockArchitecture) {
            for (ModellingArchitecture root : destRE.getOwnedArchitectures()) {
              if (source_p.eClass().isInstance(root)) {
                return Collections.singletonList((EObject) root);
              }
            }
          }
        }
        return Collections.singletonList(source_p);
      }

      @Override
      public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
        Collection<EObject> parent = super.retrieveSourceElements(source_p, context_p);
        if ((parent != null) && !(parent.isEmpty())) {
          return parent;
        }

        Collection<EObject> elements = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
        CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context_p).getSource(context_p);
        CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context_p).getTarget(context_p);
        Resource destinationResource = null;
        destinationResource = elements.iterator().next().eResource();

        if ((sourceElement != null) && (targetElement != null) && (sourceElement.eResource() != destinationResource)) {
          SystemEngineering destRE = (SystemEngineering) EcoreUtil2.getFirstContainer(source_p, CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
          SystemEngineering sourceRE = (SystemEngineering) ((Project) destinationResource.getContents().get(0)).getOwnedModelRoots().get(0);
          if (source_p instanceof BlockArchitecture) {
            for (ModellingArchitecture root : destRE.getOwnedArchitectures()) {
              if (source_p.eClass().isInstance(root)) {
                return Collections.singletonList((EObject) root);
              }
            }
          }
        }

        return Collections.singletonList(source_p);
      }

    });

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context_p) {
    return "MATCH";
  }

}
