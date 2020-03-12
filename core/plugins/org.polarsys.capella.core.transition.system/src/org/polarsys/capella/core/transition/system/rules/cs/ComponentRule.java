/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemComponentPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.IContextScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ComponentRule extends AbstractCapellaElementRule {

  public ComponentRule() {
    super();
    registerUpdatedAttribute(CsPackage.Literals.COMPONENT__ACTOR);
    registerUpdatedAttribute(CsPackage.Literals.COMPONENT__HUMAN);
    registerUpdatedAttribute(PaPackage.Literals.PHYSICAL_COMPONENT__KIND);
    registerUpdatedAttribute(PaPackage.Literals.PHYSICAL_COMPONENT__NATURE);
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT);
  }

  @Override
  protected EObject transformDirectElement(EObject element, IContext context) {
    if (BlockArchitectureExt.isRootComponent((Component) element)) {
      EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
      BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
          .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE);
      return BlockArchitectureExt.getOrCreateSystem(target);
    }

    return super.transformDirectElement(element, context);
  }

  @Override
  protected void updateElement(EObject logicalElement, EObject physicalElement, IContext context) {
    super.updateElement(logicalElement, physicalElement, context);

    if (logicalElement instanceof LogicalComponent && physicalElement instanceof PhysicalComponent) {
      LogicalComponent logicalComponent = (LogicalComponent) logicalElement;
      PhysicalComponent physicalComponent = (PhysicalComponent) physicalElement;

      if (!BlockArchitectureExt.isRootComponent(physicalComponent)) {
        PhysicalComponentNature nature = getComputedNature(context, logicalComponent);

        physicalComponent.setNature(nature);
      }
    }
  }

  @Override
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    BlockArchitecture target = (BlockArchitecture) TransformationHandlerHelper.getInstance(context)
        .getBestTracedElement(root, context, CsPackage.Literals.BLOCK_ARCHITECTURE, element, result);
    if (result instanceof Entity || ComponentExt.isActor(result)) {
      return BlockArchitectureExt.getComponentPkg(target, true);
    }
    return BlockArchitectureExt.getFirstComponent(target, true);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.COMPONENT;
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    retrieveComponentGoDeep(source, result, context);
  }

  protected void retrieveComponentGoDeep(EObject source, List<EObject> result, IContext context) {

    Component element = (Component) source;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {

      retrieveComponentAllocations(source, result, context);
      retrieveRepresentingPartitions(source, result, context);
      result.addAll(element.getUsedInterfaceLinks());
      result.addAll(element.getImplementedInterfaceLinks());

      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getRepresentingParts(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getUsedInterfaceLinks(), context);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getImplementedInterfaceLinks(), context);

      if (element instanceof InformationsExchanger) {
        InformationsExchanger info = (InformationsExchanger) element;
        result.addAll(info.getInformationFlows());
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, info.getInformationFlows(), context);

      }
      // Retrieve component ports
      result.addAll(ComponentExt.getOwnedComponentPort(element));
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, ComponentExt.getOwnedComponentPort(element), context);

      // Retrieve physical ports
      result.addAll(ComponentExt.getOwnedPhysicalPort(element));
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, ComponentExt.getOwnedPhysicalPort(element), context);

      for (Generalization generalization : element.getSuperGeneralizations()) {
        result.add(generalization);
      }

    }

    // Add all involving involvements
    if (element instanceof InvolvedElement) {
      for (Involvement involvement : ((InvolvedElement) element).getInvolvingInvolvements()) {
        InvolverElement invo = involvement.getInvolver();
        if ((invo != null) && ((invo instanceof AbstractCapability) || (invo instanceof PhysicalPath))) {
          result.add(involvement);
        }
      }
    }

  }

  protected void retrieveRepresentingPartitions(EObject source, List<EObject> result, IContext context) {
    Component element = (Component) source;
    result.addAll(element.getRepresentingParts());
  }

  /**
   * @param source
   * @param result
   * @param context
   */
  protected void retrieveComponentAllocations(EObject source, List<EObject> result, IContext context) {
    Component element = (Component) source;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context);
    result.addAll(element.getFunctionalAllocations());

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getFunctionalAllocations(), context);
    }

    if (element instanceof Entity) {
      for (RoleAllocation ra : ((Entity) element).getRoleAllocations()) {
        Role role = ra.getRole();
        for (ActivityAllocation aa : role.getActivityAllocations()) {
          result.add(aa);
          if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context)) {
            handler.add(ITransitionConstants.SOURCE_SCOPE, aa, context);
          }
        }
      }
    }
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container,
      IContext context) {
    EClass targetType = getTargetType(element, context);

    if (container instanceof EntityPkg) {
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;

      } else if (OaPackage.Literals.ENTITY_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS;
      }

    } else if (container instanceof Entity) {
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY__OWNED_ENTITIES;
      }

    } else if (container instanceof SystemAnalysis) {
      if (CtxPackage.Literals.SYSTEM_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM_COMPONENT_PKG;

      }

    } else if (container instanceof SystemComponentPkg) {
      if (CtxPackage.Literals.SYSTEM_COMPONENT.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENTS;

      } else if (CtxPackage.Literals.SYSTEM_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_COMPONENT_PKG__OWNED_SYSTEM_COMPONENT_PKGS;

      }

    } else if (container instanceof SystemComponent) {
      if (CtxPackage.Literals.SYSTEM_COMPONENT.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENTS;

      } else if (CtxPackage.Literals.SYSTEM_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_COMPONENT__OWNED_SYSTEM_COMPONENT_PKGS;

      }

    } else if (container instanceof LogicalArchitecture) {
      if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG;
      }

    } else if (container instanceof LogicalComponentPkg) {
      if (LaPackage.Literals.LOGICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof LogicalComponent) {
      if (LaPackage.Literals.LOGICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS;
      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof PhysicalArchitecture) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG;

      }

    } else if (container instanceof PhysicalComponentPkg) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENTS;
      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof PhysicalComponent) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container instanceof Part) {
      return CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE;
    }
    return element.eContainingFeature();
  }

  PhysicalComponentNature getComputedNature(IContext context, LogicalComponent logicalComponent) {
    Map<LogicalComponent, PhysicalComponentNature> natureMap = getNatureMap(context);

    PhysicalComponentNature nature = natureMap.get(logicalComponent);

    if (nature == null) {
      Collection<Component> subComponents = ComponentExt.getAllSubUsedComponents(logicalComponent);
      nature = computeNature(subComponents);
      cacheNature(natureMap, Arrays.asList(logicalComponent), nature);
      cacheNature(natureMap, subComponents, nature);
    }

    return nature;
  }

  @SuppressWarnings("unchecked")
  private Map<LogicalComponent, PhysicalComponentNature> getNatureMap(IContext context) {
    Map<LogicalComponent, PhysicalComponentNature> natureMap = (Map<LogicalComponent, PhysicalComponentNature>) context
        .get(ITransitionConstants.LC_TO_PC_COMPUTED_NATURE);

    if (natureMap == null) {
      natureMap = new HashMap<>();
      context.put(ITransitionConstants.LC_TO_PC_COMPUTED_NATURE, natureMap);
    }

    return natureMap;
  }

  private PhysicalComponentNature computeNature(Collection<Component> subComponents) {
    boolean allSubComponentsActors = subComponents.stream().allMatch(Component::isActor);

    return allSubComponentsActors ? PhysicalComponentNature.NODE : PhysicalComponentNature.BEHAVIOR;
  }

  private void cacheNature(Map<LogicalComponent, PhysicalComponentNature> natureMap, Collection<Component> components,
      PhysicalComponentNature nature) {

    components.stream() //
        .filter(LogicalComponent.class::isInstance) //
        .map(LogicalComponent.class::cast) //
        .forEach(component -> natureMap.put(component, nature));
  }

}
