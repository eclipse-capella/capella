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
package org.polarsys.capella.core.transition.system.rules.cs;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
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
import org.polarsys.capella.common.data.modellingcore.InformationsExchanger;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ComponentRule extends AbstractCapellaElementRule {

  public ComponentRule() {
    super();
    registerUpdatedAttribute(PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__KIND);
    registerUpdatedAttribute(PaPackage.Literals.ABSTRACT_PHYSICAL_COMPONENT__NATURE);
    registerUpdatedAttribute(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT);
  }

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {

    if (element_p.eContainer() instanceof BlockArchitecture) {
      EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
      BlockArchitecture target =
          (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE);
      return BlockArchitectureExt.getFirstComponent(target);
    }

    EObject result = super.transformDirectElement(element_p, context_p);
    if (result instanceof PhysicalComponent) {
      ((PhysicalComponent) result).setNature(PhysicalComponentNature.BEHAVIOR);
    }
    return result;
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);

    if (root.equals(element_p.eContainer())) {
      return target;
    }
    if (result_p instanceof AbstractActor) {
      return BlockArchitectureExt.getActorPkg(target, true);
    }
    return BlockArchitectureExt.getFirstComponent(target, true);
  }

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.COMPONENT;
  }

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    super.retrieveContainer(element_p, result_p, context_p);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    retrieveComponentGoDeep(source_p, result_p, context_p);
  }

  protected void retrieveComponentGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {

    Component element = (Component) source_p;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {

      retrieveComponentAllocations(source_p, result_p, context_p);
      retrieveRepresentingPartitions(source_p, result_p, context_p);
      result_p.addAll(element.getUsedInterfaceLinks());
      result_p.addAll(element.getImplementedInterfaceLinks());

      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getRepresentingPartitions(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getUsedInterfaceLinks(), context_p);
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getImplementedInterfaceLinks(), context_p);

      if (element instanceof InformationsExchanger) {
        InformationsExchanger info = (InformationsExchanger) element;
        result_p.addAll(info.getInformationFlows());
        handler.addAll(ITransitionConstants.SOURCE_SCOPE, info.getInformationFlows(), context_p);

      }
      // Retrieve component ports
      result_p.addAll(ComponentExt.getOwnedComponentPort(element));
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, ComponentExt.getOwnedComponentPort(element), context_p);

      // Retrieve physical ports
      result_p.addAll(ComponentExt.getOwnedPhysicalPort(element));
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, ComponentExt.getOwnedPhysicalPort(element), context_p);

      for (Generalization generalization : element.getSuperGeneralizations()) {
        result_p.add(generalization);
      }

    }

    // Add all involving involvements
    if (element instanceof InvolvedElement) {
      for (Involvement involvement : ((InvolvedElement) element).getInvolvingInvolvements()) {
        InvolverElement invo = involvement.getInvolver();
        if ((invo != null) && ((invo instanceof AbstractCapability) || (invo instanceof PhysicalPath))) {
          result_p.add(involvement);
        }
      }
    }

  }

  protected void retrieveRepresentingPartitions(EObject source_p, List<EObject> result_p, IContext context_p) {
    Component element = (Component) source_p;
    result_p.addAll(element.getRepresentingPartitions());
  }

  /**
   * @param source_p
   * @param result_p
   * @param context_p
   */
  protected void retrieveComponentAllocations(EObject source_p, List<EObject> result_p, IContext context_p) {
    Component element = (Component) source_p;

    IContextScopeHandler handler = ContextScopeHandlerHelper.getInstance(context_p);
    result_p.addAll(element.getFunctionalAllocations());

    if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
      handler.addAll(ITransitionConstants.SOURCE_SCOPE, element.getFunctionalAllocations(), context_p);
    }

    if (element instanceof Entity) {
      for (RoleAllocation ra : ((Entity) element).getRoleAllocations()) {
        Role role = ra.getRole();
        for (ActivityAllocation aa : role.getActivityAllocations()) {
          result_p.add(aa);
          if (handler.contains(ITransitionConstants.SOURCE_SCOPE, element, context_p)) {
            handler.add(ITransitionConstants.SOURCE_SCOPE, aa, context_p);
          }
        }
      }
    }
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    EClass targetType = getTargetType(element_p, context_p);

    if (container_p instanceof EntityPkg) {
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES;

      } else if (OaPackage.Literals.ENTITY_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS;
      }

    } else if (container_p instanceof Entity) {
      if (OaPackage.Literals.ENTITY.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY__OWNED_ENTITIES;
      }

    } else if (container_p instanceof SystemAnalysis) {
      if (CtxPackage.Literals.SYSTEM.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_SYSTEM;

      } else if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG;

      }

    } else if (container_p instanceof ActorPkg) {
      if (CtxPackage.Literals.ACTOR.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.ACTOR_PKG__OWNED_ACTORS;

      } else if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.ACTOR_PKG__OWNED_ACTOR_PKGS;

      }

    } else if (container_p instanceof org.polarsys.capella.core.data.ctx.System) {

    } else if (container_p instanceof LogicalArchitecture) {
      if (LaPackage.Literals.LOGICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT;

      } else if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG;

      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG;

      }

    } else if (container_p instanceof LogicalActorPkg) {
      if (LaPackage.Literals.LOGICAL_ACTOR.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS;
      } else if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS;
      }

    } else if (container_p instanceof LogicalComponentPkg) {
      if (LaPackage.Literals.LOGICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENTS;
      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof LogicalComponent) {
      if (LaPackage.Literals.LOGICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS;
      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof PhysicalArchitecture) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT;

      } else if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG;

      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG;

      }

    } else if (container_p instanceof PhysicalActorPkg) {
      if (PaPackage.Literals.PHYSICAL_ACTOR.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTORS;
      } else if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS;
      }

    } else if (container_p instanceof PhysicalComponentPkg) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_COMPONENTS;
      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof PhysicalComponent) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENTS;
      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof Part) {
      return CsPackage.Literals.PART__OWNED_ABSTRACT_TYPE;
    }
    return element_p.eContainingFeature();
  }
}
