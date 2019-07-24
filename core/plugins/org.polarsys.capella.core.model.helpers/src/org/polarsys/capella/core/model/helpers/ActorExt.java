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
package org.polarsys.capella.core.model.helpers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

public class ActorExt {

  private ActorExt() {
    // prevent instantiation
  }

  public static Entity createOperationalActor() {
    Entity operationalActor = OaFactory.eINSTANCE.createEntity();
    operationalActor.setActor(true);
    operationalActor.setHuman(true);

    return operationalActor;
  }

  public static SystemComponent createSystemActor() {
    SystemComponent systemActor = CtxFactory.eINSTANCE.createSystemComponent();
    systemActor.setActor(true);
    systemActor.setHuman(true);

    return systemActor;
  }

  public static LogicalComponent createLogicalActor() {
    LogicalComponent logicalActor = LaFactory.eINSTANCE.createLogicalComponent();
    logicalActor.setActor(true);
    logicalActor.setHuman(true);

    return logicalActor;
  }

  public static PhysicalComponent createPhysicalActor() {
    PhysicalComponent physicalActor = PaFactory.eINSTANCE.createPhysicalComponent();

    physicalActor.setActor(true);
    physicalActor.setHuman(true);

    return physicalActor;
  }

  public static boolean canContainSubOperationalActor(Entity container) {
    return !container.isHuman();
  }

  public static boolean canContainSubSystemActor(SystemComponent container) {
    return container.isActor();
  }

  public static boolean canContainSubLogicalActor(LogicalComponent container) {
    return container.isActor();
  }

  public static boolean canContainSubPhysicalActor(PhysicalComponent container) {
    return container.isActor() || !container.isHuman();
  }

  public static boolean canContainSubActor(Component container) {
    if (container instanceof Entity) {
      return canContainSubOperationalActor((Entity) container);

    } else if (container instanceof SystemComponent) {
      return canContainSubSystemActor((SystemComponent) container);

    } else if (container instanceof LogicalComponent) {
      return canContainSubLogicalActor((LogicalComponent) container);

    } else if (container instanceof PhysicalComponent) {
      return canContainSubPhysicalActor((PhysicalComponent) container);

    }
    return false;
  }

  public static boolean isActorHuman(Component component) {
    return component.isActor() && component.isHuman();
  }

  public static boolean isActorHuman(EObject object) {
    if (object instanceof Component) {
      return isActorHuman((Component) object);
    }

    return false;
  }
}
