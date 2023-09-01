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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ISchemaConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.config.ExtendedTraceabilityConfiguration;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.handlers.traceability.LibraryTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.RealizationLinkTraceabilityHandler;
import org.polarsys.capella.core.transition.system.handlers.traceability.ReconciliationTraceabilityHandler;
import org.polarsys.capella.core.transition.system.helpers.ContextHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class MergeTargetConfiguration extends ExtendedTraceabilityConfiguration {

  @Override
  protected String getExtensionIdentifier(IContext context) {
    return ISchemaConstants.TARGET_TRACEABILITY_CONFIGURATION;
  }

  protected class TopDownReconciliationTraceabilityHandler extends ReconciliationTraceabilityHandler {

    public TopDownReconciliationTraceabilityHandler(String identifier) {
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

      addInterfaceUseReconciliation(source, target, context, map);
      addInterfaceImplementationReconciliation(source, target, context, map);
      addCommunicationLinkReconciliation(source, target, context, map);
    }

    /**
     * Reconciliation for CommunicationLink with no traceabilityLinks, or linked to a traced exchangeItem
     */
    protected void addCommunicationLinkReconciliation(Component source, Component target, IContext context,
        LevelMappingTraceability map) {

      Collection<CommunicationLink> unmatchedLinks = new ArrayList<CommunicationLink>(1);

      for (CommunicationLink sLink : source.getOwnedCommunicationLinks()) {
        boolean match = false;
        if (sLink.getExchangeItem() == null) {
          continue;
        }
        if (!retrieveTracedElementsByRealization(sLink, context).isEmpty()) {
          continue;
        }
        for (CommunicationLink tLink : target.getOwnedCommunicationLinks()) {
          if (tLink.getExchangeItem() == null) {
            continue;
          }
          if (!TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink, context).isEmpty()) {
            continue;
          }
          if (!(sLink.getExchangeItem().equals(tLink.getExchangeItem())
              || TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink.getExchangeItem(), context)
                  .contains(sLink.getExchangeItem()))) {
            continue;
          }
          if (tLink.getKind() == sLink.getKind()) {
            addMapping(map, sLink, tLink, context);
            match = true;
            break;
          }
        }
        if (!match) {
          unmatchedLinks.add(sLink);
        }
      }

      for (CommunicationLink sLink : unmatchedLinks) {
        if (sLink.getExchangeItem() == null) {
          continue;
        }

        for (CommunicationLink tLink : target.getOwnedCommunicationLinks()) {
          if (tLink.getExchangeItem() == null) {
            continue;
          }
          if (!TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink, context).isEmpty()) {
            continue;
          }
          if (!(sLink.getExchangeItem().equals(tLink.getExchangeItem())
              || TraceabilityHandlerHelper.getInstance(context).retrieveSourceElements(tLink.getExchangeItem(), context)
                  .contains(sLink.getExchangeItem()))) {
            continue;
          }
          addMapping(map, sLink, tLink, context);
          break;
        }
      }
    }

    /**
     * Reconciliation for InterfaceUse with no traceabilityLinks, or linked to a traced interface
     */
    protected void addInterfaceUseReconciliation(Component source, Component target, IContext context,
        LevelMappingTraceability map) {
      for (InterfaceUse sLink : source.getUsedInterfaceLinks()) {
        if (sLink.getUsedInterface() == null) {
          continue;
        }
        for (InterfaceUse tLink : target.getUsedInterfaceLinks()) {
          if (tLink.getUsedInterface() == null) {
            continue;
          }
          if (sLink.getUsedInterface().equals(tLink.getUsedInterface())
              || TraceabilityHandlerHelper.getInstance(context)
                  .retrieveSourceElements(tLink.getUsedInterface(), context).contains(sLink.getUsedInterface())) {
            addMapping(map, sLink, tLink, context);
            break;
          }
        }
      }
    }

    /**
     * Reconciliation for InterfaceImplementation with no traceabilityLinks, or linked to a traced interface
     */
    protected void addInterfaceImplementationReconciliation(Component source, Component target, IContext context,
        LevelMappingTraceability map) {
      for (InterfaceImplementation sLink : source.getImplementedInterfaceLinks()) {
        if (sLink.getImplementedInterface() == null) {
          continue;
        }
        for (InterfaceImplementation tLink : target.getImplementedInterfaceLinks()) {
          if (tLink.getImplementedInterface() == null) {
            continue;
          }
          if (sLink.getImplementedInterface().equals(tLink.getImplementedInterface()) || TraceabilityHandlerHelper
              .getInstance(context).retrieveSourceElements(tLink.getImplementedInterface(), context)
              .contains(sLink.getImplementedInterface())) {
            addMapping(map, sLink, tLink, context);
            break;
          }
        }
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
    protected void initializeMappings(EObject source, IContext context, LevelMappingTraceability map) {
      super.initializeMappings(source, context, map);

      if (source instanceof ComponentFunctionalAllocation) {
        addComponentFunctionalReconciliation((ComponentFunctionalAllocation) source, context, map);
      }
    }

    protected void addComponentFunctionalReconciliation(ComponentFunctionalAllocation source, IContext context,
        LevelMappingTraceability map) {
      AbstractFunction function = source.getFunction();
      if (function != null) {
        ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context);
        // if isBackward, its bottom to up (source is physical allocation, and we look for logical allocation),
        // otherwise, its from top to bottom
        Collection<EObject> sFunctions = isBackward(map.key) ? handler.retrieveSourceElements(function, context)
            : handler.retrieveTracedElements(function, context);
        if (sFunctions.size() == 1) {
          EObject sFunction = sFunctions.iterator().next();
          if (sFunction instanceof AbstractFunction) {
            List<ComponentFunctionalAllocation> allocations = ((AbstractFunction) sFunction)
                .getComponentFunctionalAllocations();
            if (allocations.size() == 1) {
              addMapping(map, allocations.get(0), source, context);
            }
          }
        }
      }
    }

    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      addMappings(ContextHelper.getSourceProject(context), ContextHelper.getTargetProject(context), context);
      addMappings(ContextHelper.getSourceEngineering(context), getTargetEngineering(context), context);
    }
  }

  public EObject getTargetEngineering(IContext context) {
    // In topdown transition, like TargetProject is the same than SourceProject, the SystemEngineering is the same than
    // the source
    return ContextHelper.getSourceEngineering(context);
  }

  protected class TopDownRealizationTraceabilityHandler extends RealizationLinkTraceabilityHandler {

    public TopDownRealizationTraceabilityHandler(String identifier) {
      super(identifier);
    }

    @Override
    protected boolean initTraceabilityMapping(EObject source, EObject object, IContext context) {
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
      return (architecture == null) || (getTargetArchitecture(source, context) == architecture);
    }

    @Override
    protected void initializeRootMappings(IContext context) {
      super.initializeRootMappings(context);
      initializeMappings(ContextHelper.getSourceProject(context), ContextHelper.getTargetProject(context), context);
    }
  }

  @Override
  protected void initHandlers(IContext context) {
    addHandler(context, new TopDownReconciliationTraceabilityHandler(getIdentifier(context)));
    addHandler(context, new TopDownRealizationTraceabilityHandler(getIdentifier(context)));
    addHandler(context, new LibraryTraceabilityHandler());
  }

  public Collection<EObject> retrieveTracedElementsByRealization(EObject object, IContext context) {
    ITraceabilityHandler handler = getDefinedHandler(context, RealizationLinkTraceabilityHandler.class);
    if (handler != null) {
      return handler.retrieveTracedElements(object, context);
    }
    return Collections.emptyList();
  }

  @Override
  public boolean useHandlerForTracedElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForTracedElements(source, handler, context);

    if (result) {
      // We disable RealizationLinks for root elements
      if (handler instanceof RealizationLinkTraceabilityHandler) {
        result = !(source instanceof Project || source instanceof SystemEngineering
            || source instanceof BlockArchitecture);
      }
    }

    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    return result;
  }

  @Override
  public boolean useHandlerForSourceElements(EObject source, ITraceabilityHandler handler, IContext context) {
    boolean result = super.useHandlerForSourceElements(source, handler, context);

    if (result) {
      // We disable RealizationLinks for root elements
      if (handler instanceof RealizationLinkTraceabilityHandler) {
        result = !(source instanceof Project || source instanceof SystemEngineering
            || source instanceof BlockArchitecture);
      }

      // We disable Reconciliation for StateMachine
      if (handler instanceof ReconciliationTraceabilityHandler) {
        result = !(source instanceof StateMachine || source instanceof Region);
      }
    }

    if (LibraryTraceabilityHandler.isLibraryElement(source, context)) {
      return handler instanceof LibraryTraceabilityHandler;
    }

    return result;
  }

  @Override
  public boolean useHandlerForAttachment(EObject source, EObject target, ITraceabilityHandler handler,
      IContext context) {

    // We disable Reconciliation for attachment
    if (handler instanceof ReconciliationTraceabilityHandler) {
      return false;
    }

    return super.useHandlerForAttachment(source, target, handler, context);
  }

  public BlockArchitecture getTargetArchitecture(EObject target, IContext context) {

    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    Collection<EObject> selection = (Collection<EObject>) context.get(ITransitionConstants.TRANSITION_SOURCES);
    if (!selection.isEmpty()) {
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
}
