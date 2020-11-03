/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.handlers.traceability;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.handlers.traceability.LocationTraceabilityConfiguration;
import org.polarsys.capella.common.re.handlers.traceability.LocationTraceabilityHandler;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * As IRuleAttachment.retrieveDefaultContainer implementation are based on BlockArchitecture.
 * For instance, to retrieve default containers like root FunctionPkg, root CapabilityPkg, 
 * this class ensure to retrieve the correct BlockArchitecture in the expected model.
 * 
 * We need to ensure that the returned BlockArchitecture is the one located in the same model project 
 * than the REC or RPL we are looking while getDefaultLocation().
 */
public class CapellaLocationTraceabilityConfiguration extends LocationTraceabilityConfiguration {

  @Override
  protected void initHandlers(IContext context) {
    super.initHandlers(context);

    addHandler(context, new LevelBasedTraceabilityHandler(getIdentifier(context)) {

      @Override
      public Collection<EObject> retrieveTracedElements(EObject source, IContext iContext) {

        if (source instanceof BlockArchitecture) {
          if (isSourceKind()) {
            // Retrieve if the given Link is from the Source or the Target
            CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context)
                .getInitialSource(context);
            Resource resource = EcoreUtil.getRootContainer(sourceElement).eResource();
            Project project = ProjectExt.getProject(resource);
            BlockArchitecture targetArchitecture = BlockArchitectureExt.getBlockArchitecture(source.eClass(), project);
            if (targetArchitecture != null) {
              return Collections.singletonList(targetArchitecture);
            }

          } else {
            // Retrieve if the given Link is from the Source or the Target
            CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context)
                .getInitialTarget(context);
            Resource resource = EcoreUtil.getRootContainer(targetElement).eResource();
            Project project = ProjectExt.getProject(resource);
            BlockArchitecture targetArchitecture = BlockArchitectureExt.getBlockArchitecture(source.eClass(), project);
            if (targetArchitecture != null) {
              return Collections.singletonList(targetArchitecture);
            }
          }

        }

        return Collections.emptyList();
      }

      @Override
      public Collection<EObject> retrieveSourceElements(EObject source, IContext context_p) {

        if (source instanceof BlockArchitecture) {
          if (isSourceKind()) {
            // Retrieve if the given Link is from the Source or the Target
            CatalogElement sourceElement = ReplicableElementHandlerHelper.getInstance(context)
                .getInitialSource(context);
            Resource resource = EcoreUtil.getRootContainer(sourceElement).eResource();
            Project project = ProjectExt.getProject(resource);
            BlockArchitecture targetArchitecture = BlockArchitectureExt.getBlockArchitecture(source.eClass(), project);
            if (targetArchitecture != null) {
              return Collections.singletonList(targetArchitecture);
            }

          } else {
            // Retrieve if the given Link is from the Source or the Target
            CatalogElement targetElement = ReplicableElementHandlerHelper.getInstance(context)
                .getInitialTarget(context);
            Resource resource = EcoreUtil.getRootContainer(targetElement).eResource();
            Project project = ProjectExt.getProject(resource);
            BlockArchitecture targetArchitecture = BlockArchitectureExt.getBlockArchitecture(source.eClass(), project);
            if (targetArchitecture != null) {
              return Collections.singletonList(targetArchitecture);
            }
          }
        }
        return Collections.emptyList();
      }

    });

  }

  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    if (source instanceof BlockArchitecture && handler instanceof LocationTraceabilityHandler) {
      return false;
    }
    return super.useHandlerForSourceElements(source, handler, context);
  }

  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {
    if (source instanceof BlockArchitecture && handler instanceof LocationTraceabilityHandler) {
      return false;
    }
    return super.useHandlerForTracedElements(source, handler, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExtensionIdentifier(IContext context_p) {
    return "MATCH";
  }

}
