/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.topdown.handlers.traceability.config;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.handlers.traceability.LibraryTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class MergeTargetConfiguration extends org.polarsys.capella.core.transition.system.handlers.traceability.config.MergeTargetConfiguration {

  protected class TopDownTargetReconciliationTraceabilityHandler extends ReconciliationTraceabilityHandler {

    /**
     * @param identifier_p
     */
    public TopDownTargetReconciliationTraceabilityHandler(String identifier) {
      super(identifier);
    }

    @Override
    protected void initializeDataPkg(DataPkg source, DataPkg target, IContext context, LevelMappingTraceability map) {
      if (source.eClass().equals(target.eClass())) {
        addMapping(map, source, target, context);
      }
    }

    @Override
    protected void initializeComponent(Component source, Component target, IContext context, LevelMappingTraceability map) {
      super.initializeComponent(source, target, context, map);

      if ((source != null) && (target != null) && !source.eClass().equals(target.eClass())) {
        addMapping(map, ComponentExt.getDataPkg(source, false), ComponentExt.getDataPkg(target, false), context);
        addMapping(map, ComponentExt.getInterfacePkg(source, false), ComponentExt.getInterfacePkg(target, false), context);
      }

      // Reconciliation for InterfaceUse with no traceabilityLinks, or linked to a traced interface
      for (InterfaceUse sLink : source.getUsedInterfaceLinks()) {
        if (sLink.getUsedInterface() == null) {
          continue;
        }
        for (InterfaceUse tLink : target.getUsedInterfaceLinks()) {
          if (tLink.getUsedInterface() == null) {
            continue;
          }
          if (sLink.getUsedInterface().equals(tLink.getUsedInterface())
              || TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink.getUsedInterface(), context).contains(sLink.getUsedInterface())) {
            addMapping(map, sLink, tLink, context);
            break;
          }
        }
      }

      // Reconciliation for InterfaceImplementation with no traceabilityLinks, or linked to a traced interface
      for (InterfaceImplementation sLink : source.getImplementedInterfaceLinks()) {
        if (sLink.getImplementedInterface() == null) {
          continue;
        }
        for (InterfaceImplementation tLink : target.getImplementedInterfaceLinks()) {
          if (tLink.getImplementedInterface() == null) {
            continue;
          }
          if (sLink.getImplementedInterface().equals(tLink.getImplementedInterface())
              || TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink.getImplementedInterface(), context)
                  .contains(sLink.getImplementedInterface())) {
            addMapping(map, sLink, tLink, context);
            break;
          }
        }
      }

      // Reconciliation for CommunicationLink with no traceabilityLinks, or linked to a traced interface
      for (CommunicationLink sLink : source.getOwnedCommunicationLinks()) {
        if (sLink.getExchangeItem() == null) {
          continue;
        }
        for (CommunicationLink tLink : target.getOwnedCommunicationLinks()) {
          if (tLink.getExchangeItem() == null) {
            continue;
          }
          if (sLink.getExchangeItem().equals(tLink.getExchangeItem())
              || retrieveSourceElements(tLink.getExchangeItem(), context).contains(sLink.getExchangeItem())) {
            addMapping(map, sLink, tLink, context);
            break;
          }
        }
      }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeSystemEngineering(SystemEngineering source, SystemEngineering target, IContext context, LevelMappingTraceability map) {
      super.initializeSystemEngineering(source, target, context, map);
    }

    @Override
    protected void initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target, IContext context, LevelMappingTraceability map) {
      addMapping(map, BlockArchitectureExt.getFunctionPkg(source, true), BlockArchitectureExt.getFunctionPkg(target, true), context);
      addMapping(map, BlockArchitectureExt.getRootFunction(source, true), BlockArchitectureExt.getRootFunction(target, true), context);
      addMapping(map, BlockArchitectureExt.getDataPkg(source, true), BlockArchitectureExt.getDataPkg(target, true), context);
      addMapping(map, BlockArchitectureExt.getActorPkg(source, true), BlockArchitectureExt.getActorPkg(target, true), context);
      addMapping(map, BlockArchitectureExt.getContext(source, true), BlockArchitectureExt.getContext(target, true), context);

      if (!((target instanceof PhysicalArchitecture) && !(source instanceof PhysicalArchitecture))) {
        addMapping(map, BlockArchitectureExt.getInterfacePkg(source, true), BlockArchitectureExt.getInterfacePkg(target, true), context);
      }

      addMapping(map, BlockArchitectureExt.getRequirementsPkg(source, false), BlockArchitectureExt.getRequirementsPkg(target, false), context);
      addMapping(map, BlockArchitectureExt.getAbstractCapabilityPkg(source, true), BlockArchitectureExt.getAbstractCapabilityPkg(target, true), context);
      addMapping(map, BlockArchitectureExt.getFirstComponent(source, true), BlockArchitectureExt.getFirstComponent(target, true), context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      EObject source = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      addMappings(source, target, context);
    }

  }

  protected class TopDownTargetSIDTraceabilityHandler extends RealizationLinkTraceabilityHandler {

    /**
     * @param identifier
     */
    public TopDownTargetSIDTraceabilityHandler(String identifier) {
      super(identifier);
    }

    @Override
    protected boolean initTraceabilityMapping(EObject source, EObject object, IContext context) {
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
      return (architecture == null) || (getTargetArchitecture(source, context) == architecture);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      EObject source = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      initializeMappings(source, target, context);
    }
  }

  @Override
  protected void initHandlers(IContext context) {
    addHandler(context, new TopDownTargetReconciliationTraceabilityHandler(getIdentifier(context)));
    addHandler(context, new TopDownTargetSIDTraceabilityHandler(getIdentifier(context)));

    addHandler(context, new LibraryTraceabilityHandler());
  }

  protected BlockArchitecture getSourceArchitecture(SystemEngineering source, IContext context_p) {
    BlockArchitecture architecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(source);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject sourceSelection = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(sourceSelection);
    }
    return architecture;
  }

  /**
   * @param source_p
   * @param context
   * @return
   */
  protected BlockArchitecture getTargetArchitecture(EObject target, IContext context) {

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }

    EClass clazz = TransformationHandlerHelper.getInstance(context).getTargetType(architecture, context);
    for (BlockArchitecture archi : architecture.getAllocatingArchitectures()) {
      if (clazz.isInstance(archi)) {
        return archi;
      }
    }
    return architecture;
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
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForSourceElements(source, handler, context);

    if (result) {
      // We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler instanceof RealizationLinkTraceabilityHandler) {

        if (source instanceof SystemEngineering) {
          result = false;
        }
        if (source instanceof BlockArchitecture) {
          result = false;
        }

      }

      // We disable Reconciliation for StateMachine
      if (handler instanceof ReconciliationTraceabilityHandler) {
        if (source instanceof StateMachine) {
          result = false;
        }
        if (source instanceof Region) {
          result = false;
        }
      }
    }

    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    return result;
  }

}
