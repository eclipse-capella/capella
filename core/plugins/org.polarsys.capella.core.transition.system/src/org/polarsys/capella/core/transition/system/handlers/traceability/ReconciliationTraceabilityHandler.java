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

package org.polarsys.capella.core.transition.system.handlers.traceability;

import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.core.data.capellacore.Allocation;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.LevelBasedTraceabilityHandler;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ReconciliationTraceabilityHandler extends LevelBasedTraceabilityHandler {

  public ReconciliationTraceabilityHandler(String identifier) {
    super(identifier);
  }

  @Override
  protected boolean isLevelElement(EObject object, IContext context) {
    return true;
  }

  @Override
  protected void initializeMapping(EObject source, EObject target, IContext context, LevelMappingTraceability map) {

    if ((source instanceof SystemEngineering) && (target instanceof SystemEngineering)) {
      initializeSystemEngineering((SystemEngineering) source, (SystemEngineering) target, context, map);
    }

    if ((source instanceof BlockArchitecture) && (target instanceof BlockArchitecture)) {
      initializeBlockArchitecture((BlockArchitecture) source, (BlockArchitecture) target, context, map);
    }

    if ((source instanceof Component) && (target instanceof Component)) {
      initializeComponent((Component) source, (Component) target, context, map);
    }
    
    if ((source instanceof ComponentPkg) && (target instanceof ComponentPkg)) {
      initializeComponentPkg((ComponentPkg) source, (ComponentPkg) target, context, map);
    }

    if ((source instanceof FunctionPkg) && (target instanceof FunctionPkg)) {
      initializeFunctionPkg((FunctionPkg) source, (FunctionPkg) target, context, map);
    }

    if ((source instanceof DataPkg) && (target instanceof DataPkg)) {
      initializeDataPkg((DataPkg) source, (DataPkg) target, context, map);
    }

    if ((source instanceof TraceableElement) && (target instanceof TraceableElement)) {
      initializeTraceableElement((TraceableElement) source, (TraceableElement) target, context, map);
    }

  }

  /**
   * Map the Data package and its Predefined Types package if possible
   * @param source
   * @param target
   * @param context
   * @param map
   */
  protected void initializeDataPkg(DataPkg source, DataPkg target, IContext context, LevelMappingTraceability map) {
    BlockArchitecture sourceBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    BlockArchitecture targetBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
    if (sourceBlockArchitecture != null && targetBlockArchitecture != null) {
      DataPkg sourceDataPkg = BlockArchitectureExt.getDataPkg(sourceBlockArchitecture);
      DataPkg targetDataPkg = BlockArchitectureExt.getDataPkg(targetBlockArchitecture);
      if (source == sourceDataPkg && target == targetDataPkg) {
        addMapping(map, sourceDataPkg, targetDataPkg, context);
        Optional<DataPkg> sourcePredefinedTypes = sourceDataPkg.getOwnedDataPkgs().stream()
            .filter(pkg -> NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name.equals(pkg.getName()))
            .findFirst();
        Optional<DataPkg> targetPredefinedTypes = targetDataPkg.getOwnedDataPkgs().stream()
            .filter(pkg -> NamingConstants.PredefinedTypesCmd_predefinedDataTypePkg_name.equals(pkg.getName()))
            .findFirst();
        if (sourcePredefinedTypes.isPresent() && targetPredefinedTypes.isPresent()) {
          initializeDataType(sourcePredefinedTypes.get(), targetPredefinedTypes.get(), context, map);
        }
      }
    }
  }

  protected void initializeDataType(EObject source, EObject target, IContext context, LevelMappingTraceability map) {
    if (source.eClass().equals(target.eClass())) {
      addMapping(map, source, target, context);

      for (EReference reference : source.eClass().getEAllReferences()) {
        if (reference.isContainment()) {
          if (!reference.isMany()) {
            Object sourceValue = source.eGet(reference);
            Object targetValue = target.eGet(reference);
            if ((sourceValue != null) && (targetValue != null) && (sourceValue instanceof EObject) && (targetValue instanceof EObject)) {
              if (((EObject) sourceValue).eClass().equals(((EObject) targetValue).eClass())) {
                initializeDataType((EObject) sourceValue, (EObject) targetValue, context, map);
              }
            }

          } else if (reference.isMany()) {
            Object sourceValue = source.eGet(reference);
            Object targetValue = target.eGet(reference);
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
                          initializeDataType(sourceObject, (EObject) targetObject, context, map);
                          break;
                        }
                      } else {
                        initializeDataType(sourceObject, (EObject) targetObject, context, map);
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
   * @param source
   * @param target
   * @param context
   * @param map
   */
  protected void initializeTraceableElement(TraceableElement source, TraceableElement target, IContext context, LevelMappingTraceability map) {
    ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context);

    for (AbstractTrace sourceTrace : source.getIncomingTraces()) {
      for (AbstractTrace targetTrace : target.getIncomingTraces()) {
        if ((sourceTrace.eClass() == targetTrace.eClass()) && (sourceTrace instanceof Allocation) && (targetTrace instanceof Allocation)) {
          if ((sourceTrace.getSourceElement() != null) && (targetTrace.getSourceElement() != null)) {
            if ((sourceTrace.getTargetElement() != null) && (targetTrace.getTargetElement() != null)) {
              if (handler.retrieveTracedElements(sourceTrace.getSourceElement(), context).contains(targetTrace.getSourceElement())) {
                if (handler.retrieveTracedElements(sourceTrace.getTargetElement(), context).contains(targetTrace.getTargetElement())) {
                  addMapping(map, sourceTrace, targetTrace, context);
                }
              }
            }
          }
        }
      }
    }

    for (AbstractTrace sourceTrace : source.getOutgoingTraces()) {
      for (AbstractTrace targetTrace : target.getOutgoingTraces()) {
        if ((sourceTrace.eClass() == targetTrace.eClass()) && (sourceTrace instanceof Allocation) && (targetTrace instanceof Allocation)) {
          if ((sourceTrace.getSourceElement() != null) && (targetTrace.getSourceElement() != null)) {
            if ((sourceTrace.getTargetElement() != null) && (targetTrace.getTargetElement() != null)) {
              if (handler.retrieveTracedElements(sourceTrace.getSourceElement(), context).contains(targetTrace.getSourceElement())) {
                if (handler.retrieveTracedElements(sourceTrace.getTargetElement(), context).contains(targetTrace.getTargetElement())) {
                  addMapping(map, sourceTrace, targetTrace, context);
                }
              }
            }
          }
        }
      }
    }
  }

  /**
   * @param source
   * @param target
   * @param context
   * @param map
   */
  protected void initializeFunctionPkg(FunctionPkg source, FunctionPkg target, IContext context, LevelMappingTraceability map) {
    //Nothing yet
  }

  /**
   * @param source
   * @param target
   * @param context
   * @param map
   */
  protected void initializeComponent(Component source, Component target, IContext context, LevelMappingTraceability map) {
    ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context);

    // Perform a map with parts if there is only one part with the same type
    for (Part sourcePartition : source.getContainedParts()) {
      if (sourcePartition.getType() != null) {
        Part targetPart = null;
        for (Part targetPartition : target.getContainedParts()) {
          if (targetPartition.getType() != null) {
            if (handler.retrieveTracedElements(sourcePartition.getType(), context).contains(targetPartition.getType())) {
              if (targetPart != null) {
                targetPart = null;
              } else {
                targetPart = targetPartition;
              }
            }
          }
        }
        if (targetPart != null) {
          addMapping(map, sourcePartition, targetPart, context);
        }
      }
    }

    if (source.getRepresentingParts().size() == 1) {
      if (target.getRepresentingParts().size() == 1) {
        Part sourcePartition = source.getRepresentingParts().get(0);
        Part targetPartition = target.getRepresentingParts().get(0);
        addMapping(map, sourcePartition, targetPartition, context);
      }
    }
  }
  
  protected void initializeComponentPkg(ComponentPkg source, ComponentPkg target, IContext context,
      LevelMappingTraceability map) {
    ITraceabilityHandler handler = TraceabilityHandlerHelper.getInstance(context);

    // Perform a map with parts if there is only one part with the same type
    for (Part sourcePart : source.getOwnedParts()) {
      if (sourcePart.getType() != null) {
        for (Part targetPart : target.getOwnedParts()) {
          if (targetPart.getType() != null) {
            if (handler.retrieveTracedElements(sourcePart.getType(), context).contains(targetPart.getType())) {
              addMapping(map, sourcePart, targetPart, context);
              break;
            }
          }
        }
      }
    }
  }

  /**
   * @param sourceRoot_p
   * @param targetRoot_p
   * @param context
   * @param map
   */
  protected void initializeSystemEngineering(SystemEngineering source, SystemEngineering target, IContext context, LevelMappingTraceability map) {
    for (ModellingArchitecture archi : source.getOwnedArchitectures()) {
      for (ModellingArchitecture archi2 : target.getOwnedArchitectures()) {
        if (archi2.eClass() == archi.eClass()) {
          addMapping(map, archi, archi2, context);
        }
      }
    }
  }

  /**
   * @param sourceRoot_p
   * @param targetRoot_p
   * @param context
   * @param map
   */
  protected void initializeBlockArchitecture(BlockArchitecture source, BlockArchitecture target, IContext context, LevelMappingTraceability map) {
    addMapping(map, BlockArchitectureExt.getFunctionPkg(source, false), BlockArchitectureExt.getFunctionPkg(target, false), context);
    addMapping(map, BlockArchitectureExt.getRootFunction(source, false), BlockArchitectureExt.getRootFunction(target, false), context);
    addMapping(map, BlockArchitectureExt.getDataPkg(source, false), BlockArchitectureExt.getDataPkg(target, false), context);

    addMapping(map, BlockArchitectureExt.getActorPkg(source, false), BlockArchitectureExt.getActorPkg(target, false), context);
    addMapping(map, BlockArchitectureExt.getContext(source, false), BlockArchitectureExt.getContext(target, false), context);
    addMapping(map, BlockArchitectureExt.getInterfacePkg(source, false), BlockArchitectureExt.getInterfacePkg(target, false), context);
    addMapping(map, BlockArchitectureExt.getAbstractCapabilityPkg(source, false), BlockArchitectureExt.getAbstractCapabilityPkg(target, false), context);
  }

}
