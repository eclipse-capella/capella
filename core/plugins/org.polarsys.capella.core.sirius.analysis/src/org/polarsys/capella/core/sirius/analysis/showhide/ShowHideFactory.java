/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.showhide;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;

/**
 */
public class ShowHideFactory {

  public static AbstractShowHide createShowHide(String eClass, EObject target, DDiagramContents content) {
    if (isSupportedTarget(target)) {
      if (eClass.equals(CtxPackage.Literals.ACTOR.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.ACTOR,
            CtxPackage.Literals.ACTOR_PKG__OWNED_ACTORS, content);
      }
      if (eClass.equals(CtxPackage.Literals.CAPABILITY.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.CAPABILITY,
            CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES, content);
      }
      if (eClass.equals(CtxPackage.Literals.MISSION.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.MISSION,
            CtxPackage.Literals.MISSION_PKG__OWNED_MISSIONS, content);
      }
      if (eClass.equals(OaPackage.Literals.OPERATIONAL_ACTOR.getName())) {
        return GenericShowHide.createGenericShowHide(OaPackage.Literals.OPERATIONAL_ACTOR,
            OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES, content, true);
      }
      if (eClass.equals(OaPackage.Literals.OPERATIONAL_CAPABILITY.getName())) {
        return GenericShowHide.createGenericShowHide(OaPackage.Literals.OPERATIONAL_CAPABILITY,
            OaPackage.Literals.OPERATIONAL_CAPABILITY_PKG__OWNED_OPERATIONAL_CAPABILITIES, content);
      }
      if (eClass.equals(OaPackage.Literals.ENTITY.getName())) {
        return GenericShowHide.createGenericShowHide(OaPackage.Literals.ENTITY,
            OaPackage.Literals.ENTITY_PKG__OWNED_ENTITIES, content, true);
      }
      if (eClass.equals(LaPackage.Literals.LOGICAL_ACTOR.getName())) {
        return GenericShowHide.createGenericShowHide(LaPackage.Literals.LOGICAL_ACTOR,
            LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS, content, true);
      }
      if (eClass.equals(LaPackage.Literals.CAPABILITY_REALIZATION.getName())) {
        return GenericShowHide.createGenericShowHide(LaPackage.Literals.CAPABILITY_REALIZATION,
            LaPackage.Literals.CAPABILITY_REALIZATION_PKG__OWNED_CAPABILITY_REALIZATIONS, content, true);
      }
      if (eClass.equals(LaPackage.Literals.LOGICAL_COMPONENT.getName())) {
        return GenericShowHide.createGenericShowHide(LaPackage.Literals.LOGICAL_COMPONENT,
            LaPackage.Literals.LOGICAL_COMPONENT__OWNED_LOGICAL_COMPONENTS, content, true);
      }
    }
    return null;
  }

  private static boolean isSupportedTarget(EObject target) {
    return target instanceof CapabilityPkg || target instanceof MissionPkg || target instanceof Capability
        || target instanceof Mission || target instanceof OperationalCapability
        || target instanceof OperationalCapabilityPkg || target instanceof CapabilityRealizationPkg 
        || target instanceof LogicalComponent;
  }
}
