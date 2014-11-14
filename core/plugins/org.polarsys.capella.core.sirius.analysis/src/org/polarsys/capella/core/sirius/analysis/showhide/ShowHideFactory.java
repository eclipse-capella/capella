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
package org.polarsys.capella.core.sirius.analysis.showhide;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;

/**
 */
public class ShowHideFactory {

  public static AbstractShowHide createShowHide(String eClass_p, EObject target_p, DDiagramContents content_p) {
    if (target_p instanceof CapabilityPkg) {
      if (eClass_p.equals(CtxPackage.Literals.ACTOR.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.ACTOR, CtxPackage.Literals.ACTOR_PKG__OWNED_ACTORS, content_p);
      }
      if (eClass_p.equals(CtxPackage.Literals.CAPABILITY.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.CAPABILITY, CtxPackage.Literals.CAPABILITY_PKG__OWNED_CAPABILITIES, content_p);
      }
      if (eClass_p.equals(CtxPackage.Literals.MISSION.getName())) {
        return GenericShowHide.createGenericShowHide(CtxPackage.Literals.MISSION, CtxPackage.Literals.MISSION_PKG__OWNED_MISSIONS, content_p);
      }
    }

    return null;
  }
}
