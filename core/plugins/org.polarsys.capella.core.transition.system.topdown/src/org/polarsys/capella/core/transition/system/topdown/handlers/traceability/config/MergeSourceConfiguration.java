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
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.system.handlers.traceability.LibraryTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.core.transition.system.helpers.ContextHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class MergeSourceConfiguration extends ExtendedTraceabilityConfiguration {

  @Override
  protected String getExtensionIdentifier(IContext context) {
    return ISchemaConstants.SOURCE_TRACEABILITY_CONFIGURATION;
  }

  protected class TopDownSourceReconciliationTraceabilityHandler extends ReconciliationTraceabilityHandler {

    public TopDownSourceReconciliationTraceabilityHandler(String identifier) {
      super(identifier);
    }

    @Override
    protected void initializeDataPkg(DataPkg source, DataPkg target, IContext context, LevelMappingTraceability map) {
      if (source.eClass().equals(target.eClass())) {
        addMapping(map, source, target, context);
      }
    }

    @Override
    protected void initializeComponent(Component source, Component target, IContext context,
        LevelMappingTraceability map) {
      super.initializeComponent(source, target, context, map);

      if ((source != null) && (target != null) && !source.eClass().equals(target.eClass())) {
        addMapping(map, ComponentExt.getDataPkg(source, false), ComponentExt.getDataPkg(target, false), context);
        addMapping(map, ComponentExt.getInterfacePkg(source, false), ComponentExt.getInterfacePkg(target, false),
            context);
      }
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

    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      addMappings(ContextHelper.getSourceProject(context), ContextHelper.getTransformedProject(context), context);
      addMappings(ContextHelper.getSourceEngineering(context), ContextHelper.getTransformedEngineering(context),
          context);
    }
  }

  protected class TopDownSourceSIDTraceabilityHandler extends RealizationLinkTraceabilityHandler {

    public TopDownSourceSIDTraceabilityHandler(String identifier) {
      super(identifier);
    }

    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      initializeMappings(ContextHelper.getSourceProject(context), ContextHelper.getTransformedProject(context),
          context);
    }

  }

  @Override
  protected void initHandlers(IContext fContext) {

    addHandler(fContext, new TopDownSourceReconciliationTraceabilityHandler(getIdentifier(fContext)));

    addHandler(fContext, new TopDownSourceSIDTraceabilityHandler(getIdentifier(fContext)));

    addHandler(fContext, new LibraryTraceabilityHandler());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {

    boolean result = super.useHandlerForSourceElements(source, handler, context);
    if (result) {
      // We disable Reconciliation for StateMachine
      if (handler instanceof ReconciliationTraceabilityHandler) {
        if (source instanceof StateMachine) {
          result = false;
        }
        if (source instanceof Region) {
          result = false;
        }
      }
      if (handler instanceof RealizationLinkTraceabilityHandler) {
        if (source instanceof SystemEngineering) {
          result = false;
        }
        if (source instanceof BlockArchitecture) {
          result = false;
        }
      }
    }

    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {

    boolean result = super.useHandlerForTracedElements(source, handler, context);
    if (result) {
      // We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler instanceof RealizationLinkTraceabilityHandler) {
        if (source instanceof SystemEngineering) {
          result = false;
        } else if (source instanceof BlockArchitecture) {
          result = false;
        }
      }
    }

    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    return result;
  }

  //
  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler,
      IContext context) {

    // We disable Reconciliation for attachment
    if (handler instanceof ReconciliationTraceabilityHandler) {
      return false;
    }

    return super.useHandlerForAttachment(source, target, handler, context);
  }

}
