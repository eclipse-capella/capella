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
package org.polarsys.capella.core.transition.system.handlers.traceability;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReconciliationTraceabilityHandler extends LevelBasedTraceabilityHandler {

  public ReconciliationTraceabilityHandler(String identifier_p) {
    super(identifier_p);
  }

  @Override
  protected boolean isLevelElement(EObject object_p, IContext context_p) {
    return !(object_p instanceof Project);
  }

  @Override
  protected void initializeMapping(EObject source_p, EObject target_p, IContext context_p, LevelMappingTraceability map_p) {

    if ((source_p instanceof SystemEngineering) && (target_p instanceof SystemEngineering)) {
      initializeSystemEngineering((SystemEngineering) source_p, (SystemEngineering) target_p, context_p, map_p);
    }

    if ((source_p instanceof BlockArchitecture) && (target_p instanceof BlockArchitecture)) {
      initializeBlockArchitecture((BlockArchitecture) source_p, (BlockArchitecture) target_p, context_p, map_p);
    }

    if ((source_p instanceof Component) && (target_p instanceof Component)) {
      initializeComponent((Component) source_p, (Component) target_p, context_p, map_p);
    }

    if ((source_p instanceof FunctionPkg) && (target_p instanceof FunctionPkg)) {
      initializeFunctionPkg((FunctionPkg) source_p, (FunctionPkg) target_p, context_p, map_p);
    }

    if ((source_p instanceof DataPkg) && (target_p instanceof DataPkg)) {
      initializeDataPkg((DataPkg) source_p, (DataPkg) target_p, context_p, map_p);
    }

    if ((source_p instanceof TraceableElement) && (target_p instanceof TraceableElement)) {
      initializeTraceableElement((TraceableElement) source_p, (TraceableElement) target_p, context_p, map_p);
    }

  }

  protected void initializeDataPkg(DataPkg source_p, DataPkg target_p, IContext context_p, LevelMappingTraceability map_p) {
    initializeDataType(source_p, target_p, context_p, map_p);
  }

  protected void initializeDataType(EObject source_p, EObject target_p, IContext context_p, LevelMappingTraceability map_p) {
    if (source_p.eClass().equals(target_p.eClass())) {
      addMapping(map_p, source_p, target_p, context_p);

      for (EReference reference : source_p.eClass().getEAllReferences()) {
        if (reference.isContainment()) {
          if (!reference.isMany()) {
            Object sourceValue = source_p.eGet(reference);
            Object targetValue = target_p.eGet(reference);
            if ((sourceValue != null) && (targetValue != null) && (sourceValue instanceof EObject) && (targetValue instanceof EObject)) {
              if (((EObject) sourceValue).eClass().equals(((EObject) targetValue).eClass())) {
                initializeDataType((EObject) sourceValue, (EObject) targetValue, context_p, map_p);
              }
            }

          } else if (reference.isMany()) {
            Object sourceValue = source_p.eGet(reference);
            Object targetValue = target_p.eGet(reference);
            if ((sourceValue != null) && (targetValue != null) && (sourceValue instanceof EList) && (targetValue instanceof EList)) {
              EList<?> sourceList = (EList<?>) sourceValue;
              EList<?> targetList = (EList<?>) targetValue;
              for (int i = 0; i < sourceList.size(); i++) {
                sourceValue = sourceList.get(i);
                if ((sourceValue != null) && (sourceValue instanceof EObject)) {
                  EObject sourceObject = (EObject) sourceValue;
                  //We try to find a element with same type and same name if any 
                  for (int j = 0; j < targetList.size(); j++) {
                    Object targetObject = targetList.get(j);
                    if ((targetObject != null) && (targetObject instanceof EObject) && ((EObject) targetObject).eClass().equals(sourceObject.eClass())) {
                      if ((targetObject instanceof AbstractNamedElement) && (sourceObject instanceof AbstractNamedElement)) {
                        AbstractNamedElement sourceNamed = (AbstractNamedElement) sourceObject;
                        AbstractNamedElement targetNamed = (AbstractNamedElement) targetObject;
                        if (((sourceNamed.getName() == null) && (targetNamed.getName() == null))
                            || ((sourceNamed.getName() != null) && sourceNamed.getName().equals(targetNamed.getName()))) {
                          initializeDataType(sourceObject, (EObject) targetObject, context_p, map_p);
                          break;
                        }
                      } else {
                        initializeDataType(sourceObject, (EObject) targetObject, context_p, map_p);
                        break;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * @param source_p
   * @param target_p
   * @param context_p
   * @param map_p
   */
  protected void initializeTraceableElement(TraceableElement source_p, TraceableElement target_p, IContext context_p, LevelMappingTraceability map_p) {
    ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context_p);

    for (AbstractTrace sourceTrace : source_p.getIncomingTraces()) {
      for (AbstractTrace targetTrace : target_p.getIncomingTraces()) {
        if ((sourceTrace.eClass() == targetTrace.eClass()) && (sourceTrace instanceof Allocation) && (targetTrace instanceof Allocation)) {
          if ((sourceTrace.getSourceElement() != null) && (targetTrace.getSourceElement() != null)) {
            if ((sourceTrace.getTargetElement() != null) && (targetTrace.getTargetElement() != null)) {
              if (handler.retrieveTracedElements(sourceTrace.getSourceElement(), context_p).contains(targetTrace.getSourceElement())) {
                if (handler.retrieveTracedElements(sourceTrace.getTargetElement(), context_p).contains(targetTrace.getTargetElement())) {
                  addMapping(map_p, sourceTrace, targetTrace, context_p);
                }
              }
            }
          }
        }
      }
    }

    for (AbstractTrace sourceTrace : source_p.getOutgoingTraces()) {
      for (AbstractTrace targetTrace : target_p.getOutgoingTraces()) {
        if ((sourceTrace.eClass() == targetTrace.eClass()) && (sourceTrace instanceof Allocation) && (targetTrace instanceof Allocation)) {
          if ((sourceTrace.getSourceElement() != null) && (targetTrace.getSourceElement() != null)) {
            if ((sourceTrace.getTargetElement() != null) && (targetTrace.getTargetElement() != null)) {
              if (handler.retrieveTracedElements(sourceTrace.getSourceElement(), context_p).contains(targetTrace.getSourceElement())) {
                if (handler.retrieveTracedElements(sourceTrace.getTargetElement(), context_p).contains(targetTrace.getTargetElement())) {
                  addMapping(map_p, sourceTrace, targetTrace, context_p);
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * @param source_p
   * @param target_p
   * @param context_p
   * @param map_p
   */
  protected void initializeFunctionPkg(FunctionPkg source_p, FunctionPkg target_p, IContext context_p, LevelMappingTraceability map_p) {
    //Nothing yet
  }

  /**
   * @param source_p
   * @param target_p
   * @param context_p
   * @param map_p
   */
  protected void initializeComponent(Component source_p, Component target_p, IContext context_p, LevelMappingTraceability map_p) {
    ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context_p);

    // Perform a map with parts if there is only one part with the same type
    for (Partition sourcePartition : source_p.getOwnedPartitions()) {
      if (sourcePartition.getType() != null) {
        Partition targetPart = null;
        for (Partition targetPartition : target_p.getOwnedPartitions()) {
          if (targetPartition.getType() != null) {
            if (handler.retrieveTracedElements(sourcePartition.getType(), context_p).contains(targetPartition.getType())) {
              if (targetPart != null) {
                targetPart = null;
              } else {
                targetPart = targetPartition;
              }
            }
          }
        }
        if (targetPart != null) {
          addMapping(map_p, sourcePartition, targetPart, context_p);
        }
      }
    }

    if (source_p.getRepresentingPartitions().size() == 1) {
      if (target_p.getRepresentingPartitions().size() == 1) {
        Partition sourcePartition = source_p.getRepresentingPartitions().get(0);
        Partition targetPartition = target_p.getRepresentingPartitions().get(0);
        addMapping(map_p, sourcePartition, targetPartition, context_p);
      }
    }
  }

  /**
   * @param sourceRoot_p
   * @param targetRoot_p
   * @param context_p
   * @param map_p
   */
  protected void initializeSystemEngineering(SystemEngineering source_p, SystemEngineering target_p, IContext context_p, LevelMappingTraceability map_p) {
    for (ModellingArchitecture archi : source_p.getOwnedArchitectures()) {
      for (ModellingArchitecture archi2 : target_p.getOwnedArchitectures()) {
        if (archi2.eClass() == archi.eClass()) {
          addMapping(map_p, archi, archi2, context_p);
        }
      }
    }
  }

  /**
   * @param sourceRoot_p
   * @param targetRoot_p
   * @param context_p
   * @param map_p
   */
  protected void initializeBlockArchitecture(BlockArchitecture source_p, BlockArchitecture target_p, IContext context_p, LevelMappingTraceability map_p) {
    addMapping(map_p, BlockArchitectureExt.getFunctionPkg(source_p), BlockArchitectureExt.getFunctionPkg(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getRootFunction(source_p), BlockArchitectureExt.getRootFunction(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getDataPkg(source_p), BlockArchitectureExt.getDataPkg(target_p), context_p);

    addMapping(map_p, BlockArchitectureExt.getActorPkg(source_p), BlockArchitectureExt.getActorPkg(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getContext(source_p), BlockArchitectureExt.getContext(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getInterfacePkg(source_p), BlockArchitectureExt.getInterfacePkg(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getRequirementsPkg(source_p), BlockArchitectureExt.getRequirementsPkg(target_p), context_p);
    addMapping(map_p, BlockArchitectureExt.getAbstractCapabilityPkg(source_p), BlockArchitectureExt.getAbstractCapabilityPkg(target_p), context_p);
  }

}
