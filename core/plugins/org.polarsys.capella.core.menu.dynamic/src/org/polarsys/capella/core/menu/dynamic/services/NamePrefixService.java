/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.menu.dynamic.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.menu.dynamic.util.INamePrefixService;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.ActorExt;

public class NamePrefixService implements INamePrefixService {

  private Map<EClass, Function<EObject, String>> prefixes;

  public NamePrefixService() {
    prefixes = new HashMap<>();

    prefixes.put(OaPackage.Literals.ENTITY,
        o -> ActorExt.isActorHuman(o) ? CustomNamePrefixes.OPERATIONAL_ACTOR : CustomNamePrefixes.OPERATIONAL_ENTITY);

    prefixes.put(CtxPackage.Literals.SYSTEM_COMPONENT,
        o -> ActorExt.isActorHuman(o) ? CustomNamePrefixes.SYSTEM_ACTOR : CustomNamePrefixes.SYSTEM_COMPONENT);

    prefixes.put(LaPackage.Literals.LOGICAL_COMPONENT,
        o -> ActorExt.isActorHuman(o) ? CustomNamePrefixes.LOGICAL_ACTOR : CustomNamePrefixes.LOGICAL_COMPONENT);

    prefixes.put(PaPackage.Literals.PHYSICAL_COMPONENT,
        o -> ActorExt.isActorHuman(o) ? CustomNamePrefixes.PHYSICAL_ACTOR : CustomNamePrefixes.PHYSICAL_COMPONENT);
  }

  @Override
  public String getPrefix(EObject object) {
    EClass objectClass = object.eClass();
    Function<EObject, String> customNamePrefixFunction = prefixes.get(objectClass);

    if (customNamePrefixFunction != null) {
      return customNamePrefixFunction.apply(object);
    }

    return objectClass.getName();
  }

}
