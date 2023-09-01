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
package org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.LibraryTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.core.transition.system.helpers.ContextHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationConfiguration extends ExtendedTraceabilityConfiguration {

  @Override
  protected String getExtensionIdentifier(IContext context) {
    return ISchemaConstants.TRANSFORMATION_TRACEABILITY_CONFIGURATION;
  }

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
      protected void initializeComponent(Component source, Component target, IContext context,
          LevelMappingTraceability map) {
        // Nothing here, we don't want to play with matching though stateMachines size while transformation
      }

      @Override
      protected void initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target, IContext context,
          LevelMappingTraceability map) {
        addMapping(map, BlockArchitectureExt.getFunctionPkg(source, false),
            BlockArchitectureExt.getFunctionPkg(target, false), context);
        addMapping(map, BlockArchitectureExt.getRootFunction(source, false),
            BlockArchitectureExt.getRootFunction(target, false), context);
        addMapping(map, BlockArchitectureExt.getDataPkg(source, false), BlockArchitectureExt.getDataPkg(target, false),
            context);
        addMapping(map, BlockArchitectureExt.getComponentPkg(source, false),
            BlockArchitectureExt.getComponentPkg(target, false), context);

        if (!((target instanceof PhysicalArchitecture) && !(source instanceof PhysicalArchitecture))) {
          addMapping(map, BlockArchitectureExt.getInterfacePkg(source, false),
              BlockArchitectureExt.getInterfacePkg(target, false), context);
        }
        addMapping(map, BlockArchitectureExt.getAbstractCapabilityPkg(source, false),
            BlockArchitectureExt.getAbstractCapabilityPkg(target, false), context);
        addMapping(map, source.getSystem(), target.getSystem(), context);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        addMappings(ContextHelper.getSourceProject(context), ContextHelper.getTransformedProject(context), context);
        addMappings(ContextHelper.getSourceEngineering(context), ContextHelper.getTransformedEngineering(context),
            context);
      }

    });

    addHandler(fContext, new RealizationLinkTraceabilityHandler(getIdentifier(fContext)) {

      /**
       * {@inheritDoc}
       */
      @Override
      protected void initializeRootMappings(IContext context) {
        super.initializeRootMappings(context);
        initializeMappings(ContextHelper.getSourceProject(context), ContextHelper.getTransformedProject(context),
            context);
      }

    });

    addHandler(fContext, new LibraryTraceabilityHandler());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler,
      IContext context) {
    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    boolean result = super.useHandlerForAttachment(source, target, handler, context);
    if (result) {
      // We disable Reconciliation for attachment
      if (handler instanceof ReconciliationTraceabilityHandler) {
        result = false;
      }
    }
    return result;
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
