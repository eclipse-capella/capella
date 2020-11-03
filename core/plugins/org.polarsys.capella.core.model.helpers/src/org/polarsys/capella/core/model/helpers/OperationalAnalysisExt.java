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

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RolePkg;

/**
 * ComponentArchitecture helpers
 */
public class OperationalAnalysisExt {

  /**
   * Gets all the components contained in a component architecture
   * @param blockArchitecture the parent component architecture
   * @return list of components
   */
  static public List<Component> getComponentsFromBlockArchitecture(BlockArchitecture blockArchitecture) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : blockArchitecture.eContents()) {
      if (obj instanceof Component) {
        list.add((Component) obj);
      } else if (obj instanceof Structure) {
        for (Object content : ((Structure) obj).eContents()) {
          if (content instanceof Component) {
            list.add((Component) content);
            list.addAll(ComponentExt.getComponentsFromComponent((Component) content));
          }
        }
      }
    }
    return list;
  }


  public static List<Entity> getAllEntity(EntityPkg ownedEntityPkg) {
    List<Entity> list = new ArrayList<Entity>(1);
    if (null != ownedEntityPkg) {
      // get all entities from root entity package
      Set<EObject> scSet = EObjectExt.getAll(ownedEntityPkg, OaPackage.Literals.ENTITY);
      for (EObject object : scSet) {
        list.add((Entity) object);
      }
    }
    return list;
  }

  public static List<Entity> getAllEntity(OperationalAnalysis arch) {
    List<Entity> list = new ArrayList<Entity>(1);
    if (null != arch) {
      EntityPkg ownedEntityPkg = arch.getOwnedEntityPkg();
      if (null != ownedEntityPkg) {
        // get all entities from root entity package
        Set<EObject> scSet = EObjectExt.getAll(ownedEntityPkg, OaPackage.Literals.ENTITY);
        for (EObject object : scSet) {
          list.add((Entity) object);
        }
      }
    }

    return list;
  }

  public static List<Role> getAllRoles(OperationalAnalysis arch) {
    List<Role> list = new ArrayList<Role>();
    if (null != arch) {
      for (RolePkg aRolePkg : getAllRolePkgs(arch.getOwnedRolePkg())) {
        list.addAll(aRolePkg.getOwnedRoles());
      }
    }
    return list;
  }

  public static List<RolePkg> getAllRolePkgs(RolePkg rolePkg) {
    List<RolePkg> list = new ArrayList<RolePkg>();
    if (rolePkg != null) {
      list.add(rolePkg);
      for (RolePkg aRolePkg : rolePkg.getOwnedRolePkgs()) {
        list.addAll(getAllRolePkgs(aRolePkg));
      }
    }
    return list;
  }

  /**
   * Return all the communication mean from OperationalContext and EntityPkg
   * @param arch
   * @return
   */
  public static List<CommunicationMean> getAllCommunicationMeans(OperationalAnalysis arch) {
    List<CommunicationMean> list = new ArrayList<CommunicationMean>();
    if (null != arch) {
      // get all communication means from root entity package
      EntityPkg ownedEntityPkg = arch.getOwnedEntityPkg();
      if (null != ownedEntityPkg) {
        for (EObject object : EObjectExt.getAll(ownedEntityPkg, OaPackage.Literals.COMMUNICATION_MEAN)) {
          list.add((CommunicationMean) object);
        }
      }
    }
    return list;
  }

  public static List<OperationalCapability> getAllOperationalCapabilities(OperationalAnalysis arch) {
    List<OperationalCapability> list = new ArrayList<OperationalCapability>(1);
    if (null != arch) {
      // get all entities from root entity package
      AbstractCapabilityPkg ownedAbstractCapabilityPkg = arch.getOwnedAbstractCapabilityPkg();
      if (null != ownedAbstractCapabilityPkg) {
        Set<EObject> scSet = EObjectExt.getAll(ownedAbstractCapabilityPkg, OaPackage.Literals.OPERATIONAL_CAPABILITY);
        for (EObject object : scSet) {
          list.add((OperationalCapability) object);
        }
      }
    }
    return list;
  }

}
