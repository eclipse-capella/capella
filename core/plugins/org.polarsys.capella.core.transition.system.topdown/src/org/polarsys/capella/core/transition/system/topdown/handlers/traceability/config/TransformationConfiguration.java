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
package org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.LibraryTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationConfiguration extends org.polarsys.capella.core.transition.system.handlers.traceability.config.TransformationConfiguration {

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new ReconciliationTraceabilityHandler(getIdentifier(fContext_p)) {

      @Override
      protected void initializeDataPkg(DataPkg source_p, DataPkg target_p, IContext context_p, LevelMappingTraceability map_p) {
        if (source_p.eClass().equals(target_p.eClass())) {
          addMapping(map_p, source_p, target_p, context_p);
        }
      }

      @Override
      protected void initializeComponent(Component source_p, Component target_p, IContext context_p, LevelMappingTraceability map_p) {
        //Nothing here, we don't want to play with matching though stateMachines size while transformation
      }

      @Override
      protected void initializeBlockArchitecture(BlockArchitecture source_p, BlockArchitecture target_p, IContext context_p, LevelMappingTraceability map_p) {
        addMapping(map_p, BlockArchitectureExt.getFunctionPkg(source_p, true), BlockArchitectureExt.getFunctionPkg(target_p, true), context_p);
        addMapping(map_p, BlockArchitectureExt.getRootFunction(source_p, true), BlockArchitectureExt.getRootFunction(target_p, true), context_p);
        addMapping(map_p, BlockArchitectureExt.getDataPkg(source_p, true), BlockArchitectureExt.getDataPkg(target_p, true), context_p);
        addMapping(map_p, BlockArchitectureExt.getActorPkg(source_p, true), BlockArchitectureExt.getActorPkg(target_p, true), context_p);
        addMapping(map_p, BlockArchitectureExt.getContext(source_p, true), BlockArchitectureExt.getContext(target_p, true), context_p);

        if (!((target_p instanceof PhysicalArchitecture) && !(source_p instanceof PhysicalArchitecture))) {
          addMapping(map_p, BlockArchitectureExt.getInterfacePkg(source_p, true), BlockArchitectureExt.getInterfacePkg(target_p, true), context_p);
        }
        addMapping(map_p, BlockArchitectureExt.getRequirementsPkg(source_p, false), BlockArchitectureExt.getRequirementsPkg(target_p, false), context_p);
        addMapping(map_p, BlockArchitectureExt.getAbstractCapabilityPkg(source_p, true), BlockArchitectureExt.getAbstractCapabilityPkg(target_p, true),
            context_p);
        addMapping(map_p, BlockArchitectureExt.getFirstComponent(source_p, true), BlockArchitectureExt.getFirstComponent(target_p, true), context_p);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context_p) {
        super.initializeRootMappings(context_p);
        EObject source = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        addMappings(source, target, context_p);
      }

    });

    addHandler(fContext_p, new RealizationLinkTraceabilityHandler(getIdentifier(fContext_p)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context_p) {
        super.initializeRootMappings(context_p);
        EObject source = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        initializeMappings(source, target, context_p);
      }

    });

    addHandler(fContext_p, new LibraryTraceabilityHandler());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source_p, EObject target_p, ITraceabilityHandler handler_p, IContext context_p) {
    if (LibraryTraceabilityHandler.isLibraryElement(source_p, context_p)) {
      return handler_p instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForAttachment(source_p, target_p, handler_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    if (LibraryTraceabilityHandler.isLibraryElement(source_p, context_p)) {
      return handler_p instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForTracedElements(source_p, handler_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    if (LibraryTraceabilityHandler.isLibraryElement(source_p, context_p)) {
      return handler_p instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForSourceElements(source_p, handler_p, context_p);
  }

}
