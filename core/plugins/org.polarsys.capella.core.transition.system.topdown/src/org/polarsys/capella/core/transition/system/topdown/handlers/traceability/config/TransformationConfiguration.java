/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
  protected void initHandlers(IContext fContext) {
    addHandler(fContext, new ReconciliationTraceabilityHandler(getIdentifier(fContext)) {

      @Override
      protected void initializeDataPkg(DataPkg source, DataPkg target, IContext context, LevelMappingTraceability map) {
        if (source.eClass().equals(target.eClass())) {
          addMapping(map, source, target, context);
        }
      }

      @Override
      protected void initializeComponent(Component source, Component target, IContext context, LevelMappingTraceability map) {
        //Nothing here, we don't want to play with matching though stateMachines size while transformation
      }

      @Override
      protected void initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target, IContext context, LevelMappingTraceability map) {
        addMapping(map, BlockArchitectureExt.getFunctionPkg(source, false), BlockArchitectureExt.getFunctionPkg(target, false), context);
        addMapping(map, BlockArchitectureExt.getRootFunction(source, false), BlockArchitectureExt.getRootFunction(target, false), context);
        addMapping(map, BlockArchitectureExt.getDataPkg(source, false), BlockArchitectureExt.getDataPkg(target, false), context);
        addMapping(map, BlockArchitectureExt.getActorPkg(source, false), BlockArchitectureExt.getActorPkg(target, false), context);
        addMapping(map, BlockArchitectureExt.getContext(source, false), BlockArchitectureExt.getContext(target, false), context);

        if (!((target instanceof PhysicalArchitecture) && !(source instanceof PhysicalArchitecture))) {
          addMapping(map, BlockArchitectureExt.getInterfacePkg(source, false), BlockArchitectureExt.getInterfacePkg(target, false), context);
        }
        addMapping(map, BlockArchitectureExt.getRequirementsPkg(source, false), BlockArchitectureExt.getRequirementsPkg(target, false), context);
        addMapping(map, BlockArchitectureExt.getAbstractCapabilityPkg(source, false), BlockArchitectureExt.getAbstractCapabilityPkg(target, false),
            context);
        addMapping(map, BlockArchitectureExt.getFirstComponent(source, false), BlockArchitectureExt.getFirstComponent(target, false), context);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        EObject source = (EObject) context.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        addMappings(source, target, context);
      }

    });

    addHandler(fContext, new RealizationLinkTraceabilityHandler(getIdentifier(fContext)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        EObject source = (EObject) context.get(ITransitionConstants.TRANSFORMATION_SOURCE_ROOT);
        EObject target = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
        initializeMappings(source, target, context);
      }

    });

    addHandler(fContext, new LibraryTraceabilityHandler());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler, IContext context) {
    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForAttachment(source, target, handler, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {
    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForTracedElements(source, handler, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }
    return super.useHandlerForSourceElements(source, handler, context);
  }

}
