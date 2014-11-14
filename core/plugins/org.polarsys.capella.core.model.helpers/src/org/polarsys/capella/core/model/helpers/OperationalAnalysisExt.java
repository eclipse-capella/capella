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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalContext;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RolePkg;

/**
 * ComponentArchitecture helpers
 */
public class OperationalAnalysisExt {

  /**
   * Gets all the components contained in a component architecture
   * @param blockArchitecture_p the parent component architecture
   * @return list of components
   */
  static public List<Component> getComponentsFromBlockArchitecture(BlockArchitecture blockArchitecture_p) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : blockArchitecture_p.eContents()) {
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

  static public List<AbstractFunction> getAllFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if ((functionPkg != null) && (functionPkg instanceof OperationalActivityPkg)) {
        list = getAllFunctionsFromOperationalActivityPkg((OperationalActivityPkg) functionPkg);
      }
    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromOperationalActivityPkg(OperationalActivityPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != sysFunPkg_p) {
      EList<OperationalActivity> ownedOperationalActivities = sysFunPkg_p.getOwnedOperationalActivities();
      // owned function of SystemFunctionPkg
      list.addAll(ownedOperationalActivities);
      // owned function of Function
      for (AbstractFunction function : ownedOperationalActivities) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (OperationalActivityPkg ownedOperActiPkg : sysFunPkg_p.getOwnedOperationalActivityPkgs()) {
        list.addAll(getAllFunctionsFromOperationalActivityPkg(ownedOperActiPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
    }

    return list;
  }

  public static List<Entity> getAllEntity(EntityPkg ownedEntityPkg_p) {
    List<Entity> list = new ArrayList<Entity>(1);
    if (null != ownedEntityPkg_p) {
      // get all entities from root entity package
      Set<EObject> scSet = EObjectExt.getAll(ownedEntityPkg_p, OaPackage.Literals.ENTITY);
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

  public static List<Role> getAllRoles(OperationalAnalysis arch_p) {
    List<Role> list = new ArrayList<Role>();
    if (null != arch_p) {
      for (RolePkg aRolePkg : getAllRolePkgs(arch_p.getOwnedRolePkg())) {
        list.addAll(aRolePkg.getOwnedRoles());
      }
    }
    return list;
  }

  public static List<RolePkg> getAllRolePkgs(RolePkg rolePkg_p) {
    List<RolePkg> list = new ArrayList<RolePkg>();
    if (rolePkg_p != null) {
      list.add(rolePkg_p);
      for (RolePkg aRolePkg : rolePkg_p.getOwnedRolePkgs()) {
        list.addAll(getAllRolePkgs(aRolePkg));
      }
    }
    return list;
  }

  /**
   * Return all the communication mean from OperationalContext and EntityPkg
   * @param arch_p
   * @return
   */
  public static List<CommunicationMean> getAllCommunicationMeans(OperationalAnalysis arch_p) {
    List<CommunicationMean> list = new ArrayList<CommunicationMean>();
    if (null != arch_p) {
      // get all communication means from root entity package
      EntityPkg ownedEntityPkg = arch_p.getOwnedEntityPkg();
      if (null != ownedEntityPkg) {
        for (EObject object : EObjectExt.getAll(ownedEntityPkg, OaPackage.Literals.COMMUNICATION_MEAN)) {
          list.add((CommunicationMean) object);
        }
      }
      // get all communication means from operational context
      OperationalContext context = arch_p.getOwnedOperationalContext();
      if (null != context) {
        for (EObject object : EObjectExt.getAll(context, OaPackage.Literals.COMMUNICATION_MEAN)) {
          list.add((CommunicationMean) object);
        }
      }
    }

    return list;
  }

  public static List<OperationalCapability> getAllOperationalCapabilities(OperationalAnalysis arch_p) {
    List<OperationalCapability> list = new ArrayList<OperationalCapability>(1);
    if (null != arch_p) {
      // get all entities from root entity package
      AbstractCapabilityPkg ownedAbstractCapabilityPkg = arch_p.getOwnedAbstractCapabilityPkg();
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
