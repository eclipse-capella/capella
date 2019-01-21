/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.manager.LibraryManager;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

/**
 * Ensure that Components and Parts are always moved together across projects.
 * @deprecated will be removed in 1.4.0
 */
@Deprecated
public class PartComponentMoveListener extends CapellaContainmentTreeListener {

  @Override
  public boolean isPrecommitOnly() {
    return true;
  }

  @Override
  protected Command handleContainmentTreeChange(Map<EObject, Notification> deleted, Map<EObject, Notification> moved) {
    CompoundCommand c = new CompoundCommand();
    for (EObject m : moved.keySet()) {
      Object notifier = moved.get(m).getNotifier();
      boolean isSingletonComponents = true;
      if (notifier instanceof EObject && CapellaProjectHelper.isSingletonComponentsDriven((EObject)notifier) !=  TriStateBoolean.True) {
        isSingletonComponents = false;
      }
      if (isSingletonComponents && isCrossProjectMove(m, (EObject) moved.get(m).getNotifier())) { // Our notification filter ensures that this cast is safe
        handleMoved(m, moved.get(m), c);
      }
    }
    return c;
  }

  private void handleMoved(EObject moved, Notification notif, CompoundCommand c) {
    if (moved instanceof Component) {
      handleComponentMove((Component) moved, notif, c);
    } else if (moved instanceof Part) {
      handlePartMove((Part) moved, notif, c);
    }

    for (EObject e : moved.eContents()) {
      handleMoved(e, notif, c);
    }
  }

  private void handlePartMove(Part movedPart, Notification notif, CompoundCommand c) {

    if (movedPart.getAbstractType() instanceof Component && EcoreUtil.getRootContainer(movedPart) != EcoreUtil.getRootContainer(movedPart.getAbstractType())) {

      AbstractType t = movedPart.getAbstractType();
      EObject newParent = null;

      if (t instanceof OperationalActor || t instanceof Entity) {
        OperationalAnalysis oa = (OperationalAnalysis) EcoreUtil2.getFirstContainer(movedPart, OaPackage.Literals.OPERATIONAL_ANALYSIS);
        if (oa != null) {
          newParent = oa.getOwnedEntityPkg();
        }

      } else if (t instanceof Actor) {

        SystemAnalysis sa = (SystemAnalysis) EcoreUtil2.getFirstContainer(movedPart, CtxPackage.Literals.SYSTEM_ANALYSIS);
        if (sa != null) {
          newParent = sa.getOwnedActorPkg();
        }

      } else if (t instanceof LogicalActor) {

        LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(movedPart, LaPackage.Literals.LOGICAL_ARCHITECTURE);
        if (la != null) {
          newParent = la.getOwnedLogicalActorPkg();
        }

      } else if (t instanceof LogicalComponent) {
        EObject parentCandidate = movedPart.eContainer();
        while (parentCandidate != null) {
          if (parentCandidate instanceof LogicalComponent) {
            newParent = parentCandidate;
            break;
          }
          parentCandidate = parentCandidate.eContainer();
        }
        if (newParent == null) {
          LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(movedPart, LaPackage.Literals.LOGICAL_ARCHITECTURE);
          if (la != null) {
            newParent = la.getOwnedLogicalComponent();
          }
        }
      }

      else if (t instanceof PhysicalActor) {
        PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(movedPart, PaPackage.Literals.PHYSICAL_ACTOR);
        if (pa != null) {
          newParent = pa.getOwnedPhysicalActorPkg();
        }
      } else if (t instanceof PhysicalComponent) {
        newParent = EcoreUtil2.getFirstContainer(movedPart, PaPackage.Literals.PHYSICAL_COMPONENT);
        if (newParent == null) {
          PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(movedPart, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
          if (pa != null) {
            newParent = pa.getOwnedPhysicalComponent();
          }
        }
      } else if (t instanceof ConfigurationItem) {
        newParent = EcoreUtil2.getFirstContainer(movedPart, EpbsPackage.Literals.CONFIGURATION_ITEM);
        if (newParent == null) {
          EPBSArchitecture epbs = (EPBSArchitecture) EcoreUtil2.getFirstContainer(movedPart, EpbsPackage.Literals.EPBS_ARCHITECTURE);
          if (epbs != null) {
            newParent = epbs.getOwnedConfigurationItem();
          }
        }
      }


      if (newParent != null) {
        c.append(AddCommand.create(getTarget(), newParent, null, Collections.singleton(t)));
      }

    }
  }

  private void handleComponentMove(Component movedComponent, Notification notif, CompoundCommand c) {
    Collection<Partition> toMove = new ArrayList<Partition>();
    for (Partition e : movedComponent.getRepresentingPartitions()) {
      if (EcoreUtil.getRootContainer(e) != EcoreUtil.getRootContainer(movedComponent)) {
        toMove.add(e);
      }
    }
    if (toMove.size() > 0) {

      EObject newParent = null;

      if (movedComponent instanceof OperationalActor || movedComponent instanceof Entity) {
        OperationalAnalysis oa = (OperationalAnalysis) EcoreUtil2.getFirstContainer(movedComponent, OaPackage.Literals.OPERATIONAL_ANALYSIS);
        if (oa != null) {
          newParent = oa.getOwnedOperationalContext();
        }
      } else if (movedComponent instanceof Actor) {
        SystemAnalysis sa = (SystemAnalysis) EcoreUtil2.getFirstContainer(movedComponent, CtxPackage.Literals.SYSTEM_ANALYSIS);
        if (sa != null) {
          newParent = sa.getOwnedSystemContext();
        }
      } else if (movedComponent instanceof LogicalActor) {
        LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(movedComponent, LaPackage.Literals.LOGICAL_ARCHITECTURE);
        if (la != null) {
          newParent = la.getOwnedLogicalContext();
        }
      } else if (movedComponent instanceof PhysicalActor) {
        PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(movedComponent, PaPackage.Literals.PHYSICAL_ARCHITECTURE);
        if (pa != null) {
          newParent = pa.getOwnedPhysicalContext();
        }
      } else {
        EObject parent = movedComponent.eContainer();
        while (parent != null) {
          if (parent instanceof PartitionableElement) {
            newParent = parent;
            break;
          }
          parent = parent.eContainer();
        }
      }

      if (newParent != null) {
        c.append(AddCommand.create(getTarget(), newParent, null, toMove));
      }

    }
  }

  private boolean isCrossProjectMove(EObject moved, EObject oldParent) {
    return LibraryManager.INSTANCE.getModel(moved) != LibraryManager.INSTANCE.getModel(oldParent);
  }

}
