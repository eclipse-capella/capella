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

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
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
    public TopDownTargetReconciliationTraceabilityHandler(String identifier_p) {
      super(identifier_p);
    }

    @Override
    protected void initializeDataPkg(DataPkg source_p, DataPkg target_p, IContext context_p, LevelMappingTraceability map_p) {
      if (source_p.eClass().equals(target_p.eClass())) {
        addMapping(map_p, source_p, target_p, context_p);
      }
    }

    @Override
    protected void initializeComponent(Component source_p, Component target_p, IContext context_p, LevelMappingTraceability map_p) {
      super.initializeComponent(source_p, target_p, context_p, map_p);

      addMapping(map_p, ComponentExt.getDataPkg(source_p, false), ComponentExt.getDataPkg(target_p, false), context_p);
      addMapping(map_p, ComponentExt.getInterfacePkg(source_p, false), ComponentExt.getInterfacePkg(target_p, false), context_p);

      //Reconciliation for InterfaceUse with no traceabilityLinks, or linked to a traced interface
      for (InterfaceUse sLink : source_p.getUsedInterfaceLinks()) {
        if (sLink.getUsedInterface() == null) {
          continue;
        }
        for (InterfaceUse tLink : target_p.getUsedInterfaceLinks()) {
          if (tLink.getUsedInterface() == null) {
            continue;
          }
          if (sLink.getUsedInterface().equals(tLink.getUsedInterface())
              || TraceabilityHandlerHelper.getInstance(context_p).retrieveSourceElements(tLink.getUsedInterface(), context_p)
                  .contains(sLink.getUsedInterface())) {
            addMapping(map_p, sLink, tLink, context_p);
            break;
          }
        }
      }

      //Reconciliation for InterfaceImplementation with no traceabilityLinks, or linked to a traced interface
      for (InterfaceImplementation sLink : source_p.getImplementedInterfaceLinks()) {
        if (sLink.getImplementedInterface() == null) {
          continue;
        }
        for (InterfaceImplementation tLink : target_p.getImplementedInterfaceLinks()) {
          if (tLink.getImplementedInterface() == null) {
            continue;
          }
          if (sLink.getImplementedInterface().equals(tLink.getImplementedInterface())
              || TraceabilityHandlerHelper.getInstance(context_p).retrieveSourceElements(tLink.getImplementedInterface(), context_p)
                  .contains(sLink.getImplementedInterface())) {
            addMapping(map_p, sLink, tLink, context_p);
            break;
          }
        }
      }

      //Reconciliation for CommunicationLink with no traceabilityLinks, or linked to a traced interface
      for (CommunicationLink sLink : source_p.getOwnedCommunicationLinks()) {
        if (sLink.getExchangeItem() == null) {
          continue;
        }
        for (CommunicationLink tLink : target_p.getOwnedCommunicationLinks()) {
          if (tLink.getExchangeItem() == null) {
            continue;
          }
          if (tLink.getExchangeItem().equals(tLink.getExchangeItem())
              || retrieveSourceElements(tLink.getExchangeItem(), context_p).contains(sLink.getExchangeItem())) {
            addMapping(map_p, sLink, tLink, context_p);
            break;
          }
        }
      }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeSystemEngineering(SystemEngineering source_p, SystemEngineering target_p, IContext context_p, LevelMappingTraceability map_p) {
      super.initializeSystemEngineering(source_p, target_p, context_p, map_p);
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
      addMapping(map_p, BlockArchitectureExt.getAbstractCapabilityPkg(source_p, true), BlockArchitectureExt.getAbstractCapabilityPkg(target_p, true), context_p);
      addMapping(map_p, BlockArchitectureExt.getFirstComponent(source_p, true), BlockArchitectureExt.getFirstComponent(target_p, true), context_p);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context_p) {
      super.initializeRootMappings(context_p);
      EObject source = (EObject) context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context_p.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      addMappings(source, target, context_p);
    }

  }

  protected class TopDownTargetSIDTraceabilityHandler extends RealizationLinkTraceabilityHandler {

    /**
     * @param identifier_p
     */
    public TopDownTargetSIDTraceabilityHandler(String identifier_p) {
      super(identifier_p);
    }

    @Override
    protected boolean initTraceabilityMapping(EObject source_p, EObject object_p, IContext context_p) {
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object_p);
      return (architecture == null) || (getTargetArchitecture(source_p, context_p) == architecture);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initializeRootMappings(IContext context_p) {
      super.initializeRootMappings(context_p);
      EObject source = (EObject) context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      EObject target = (EObject) context_p.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      initializeMappings(source, target, context_p);
    }
  }

  @Override
  protected void initHandlers(IContext fContext_p) {
    addHandler(fContext_p, new TopDownTargetReconciliationTraceabilityHandler(getIdentifier(fContext_p)));
    addHandler(fContext_p, new TopDownTargetSIDTraceabilityHandler(getIdentifier(fContext_p)));
  }

  protected BlockArchitecture getSourceArchitecture(SystemEngineering source_p, IContext context_p) {
    BlockArchitecture architecture = SystemEngineeringExt.getOwnedPhysicalArchitecture(source_p);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }
    return architecture;
  }

  /**
   * @param source_p
   * @param context_p
   * @return
   */
  protected BlockArchitecture getTargetArchitecture(EObject target_p, IContext context_p) {

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target_p);
    Collection<EObject> selection = (Collection<EObject>) context_p.get(ITransitionConstants.TRANSITION_SOURCES);
    if (selection.size() > 0) {
      EObject source = (EObject) selection.toArray()[0];
      architecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    }

    EClass clazz = TransformationHandlerHelper.getInstance(context_p).getTargetType(architecture, context_p);
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
  public boolean useHandlerForTracedElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {

    boolean result = super.useHandlerForTracedElements(source_p, handler_p, context_p);
    if (result) {
      //We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler_p instanceof RealizationLinkTraceabilityHandler) {

        if (source_p instanceof SystemEngineering) {
          result = false;
        }
        if (source_p instanceof BlockArchitecture) {
          result = false;
        }

      }

    }

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean useHandlerForSourceElements(EObject source_p, ITraceabilityHandler handler_p, IContext context_p) {
    boolean result = super.useHandlerForSourceElements(source_p, handler_p, context_p);

    if (result) {
      //We disable RealizationLinks for SystemEngineering and BlockArchitecture
      if (handler_p instanceof RealizationLinkTraceabilityHandler) {

        if (source_p instanceof SystemEngineering) {
          result = false;
        }
        if (source_p instanceof BlockArchitecture) {
          result = false;
        }

      }

      //We disable Reconciliation for StateMachine
      if (handler_p instanceof ReconciliationTraceabilityHandler) {
        if (source_p instanceof StateMachine) {
          result = false;
        }
        if (source_p instanceof Region) {
          result = false;
        }
      }
    }

    return result;
  }

}
