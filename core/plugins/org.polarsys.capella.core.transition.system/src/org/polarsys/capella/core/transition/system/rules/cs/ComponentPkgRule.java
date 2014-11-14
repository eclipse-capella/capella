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

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalComponentPkg;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.EClassSelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public abstract class ComponentPkgRule extends AbstractCapellaElementRule {

  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof BlockArchitecture)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if (container != null) {
      EObject parent = container;
      while (parent != null) {

        ISelectionContext sContext =
            SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION,
                element_p, result_p);

        EObject targetContainer =
            TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p,
                new EClassSelectionContext(sContext, CsPackage.Literals.COMPONENT));
        if (targetContainer == null) {
          targetContainer = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(parent, context_p, sContext);
        }
        if (targetContainer != null) {
          return targetContainer;
        }
        parent = parent.eContainer();
      }
    }

    return super.getBestContainer(element_p, result_p, context_p);
  }

  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    BlockArchitecture target =
        (BlockArchitecture) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p, CsPackage.Literals.BLOCK_ARCHITECTURE,
            element_p, result_p);

    if (result_p instanceof ActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    } else if (result_p instanceof LogicalActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    } else if (result_p instanceof PhysicalActorPkg) {
      return BlockArchitectureExt.getActorPkg(target);
    }

    return target;
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    if (ContextScopeHandlerHelper.getInstance(context_p).contains(ITransitionConstants.SOURCE_SCOPE, source_p, context_p)) {

      if (source_p instanceof EntityPkg) {
        EntityPkg sourceElement = (EntityPkg) source_p;
        result_p.addAll(sourceElement.getOwnedEntityPkgs());
        result_p.addAll(sourceElement.getOwnedEntities());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedEntityPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedEntities(), context_p);

      } else if (source_p instanceof ActorPkg) {
        ActorPkg sourceElement = (ActorPkg) source_p;
        result_p.addAll(sourceElement.getOwnedActorPkgs());
        result_p.addAll(sourceElement.getOwnedActors());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedActorPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedActors(), context_p);

      } else if (source_p instanceof LogicalActorPkg) {
        LogicalActorPkg sourceElement = (LogicalActorPkg) source_p;
        result_p.addAll(sourceElement.getOwnedLogicalActorPkgs());
        result_p.addAll(sourceElement.getOwnedLogicalActors());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedLogicalActorPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedLogicalActors(), context_p);

      } else if (source_p instanceof LogicalComponentPkg) {
        LogicalComponentPkg sourceElement = (LogicalComponentPkg) source_p;
        result_p.addAll(sourceElement.getOwnedLogicalComponentPkgs());
        result_p.addAll(sourceElement.getOwnedLogicalComponents());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedLogicalComponentPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedLogicalComponents(), context_p);

      } else if (source_p instanceof PhysicalActorPkg) {
        PhysicalActorPkg sourceElement = (PhysicalActorPkg) source_p;
        result_p.addAll(sourceElement.getOwnedPhysicalActorPkgs());
        result_p.addAll(sourceElement.getOwnedPhysicalActors());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedPhysicalActorPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedPhysicalActors(), context_p);

      } else if (source_p instanceof PhysicalComponentPkg) {
        PhysicalComponentPkg sourceElement = (PhysicalComponentPkg) source_p;
        result_p.addAll(sourceElement.getOwnedPhysicalComponentPkgs());
        result_p.addAll(sourceElement.getOwnedComponents());
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedPhysicalComponentPkgs(), context_p);
        ContextScopeHandlerHelper.getInstance(context_p).addAll(ITransitionConstants.SOURCE_SCOPE, sourceElement.getOwnedComponents(), context_p);

      }

    }
  }

  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    EClass targetType = getTargetType(element_p, context_p);

    if (container_p instanceof EntityPkg) {
      if (OaPackage.Literals.ENTITY_PKG.isSuperTypeOf(targetType)) {
        return OaPackage.Literals.ENTITY_PKG__OWNED_ENTITY_PKGS;
      }

    } else if (container_p instanceof Entity) {
      //Nothing

    } else if (container_p instanceof SystemAnalysis) {
      if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.SYSTEM_ANALYSIS__OWNED_ACTOR_PKG;

      }

    } else if (container_p instanceof ActorPkg) {
      if (CtxPackage.Literals.ACTOR_PKG.isSuperTypeOf(targetType)) {
        return CtxPackage.Literals.ACTOR_PKG__OWNED_ACTOR_PKGS;

      }

    } else if (container_p instanceof org.polarsys.capella.core.data.ctx.System) {
      //Nothing

    } else if (container_p instanceof LogicalArchitecture) {
      if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_ACTOR_PKG;

      } else if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ARCHITECTURE__OWNED_LOGICAL_COMPONENT_PKG;

      }

    } else if (container_p instanceof LogicalActorPkg) {
      if (LaPackage.Literals.LOGICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS;
      }

    } else if (container_p instanceof LogicalComponentPkg) {
      if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT_PKG__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof LogicalComponent) {
      if (LaPackage.Literals.LOGICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof PhysicalArchitecture) {
      if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_ACTOR_PKG;

      } else if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ARCHITECTURE__OWNED_PHYSICAL_COMPONENT_PKG;

      }

    } else if (container_p instanceof PhysicalActorPkg) {
      if (PaPackage.Literals.PHYSICAL_ACTOR_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_ACTOR_PKG__OWNED_PHYSICAL_ACTOR_PKGS;
      }

    } else if (container_p instanceof PhysicalComponentPkg) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT_PKG__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    } else if (container_p instanceof PhysicalComponent) {
      if (PaPackage.Literals.PHYSICAL_COMPONENT_PKG.isSuperTypeOf(targetType)) {
        return PaPackage.Literals.PHYSICAL_COMPONENT__OWNED_PHYSICAL_COMPONENT_PKGS;
      }

    }
    return element_p.eContainingFeature();
  }
}
