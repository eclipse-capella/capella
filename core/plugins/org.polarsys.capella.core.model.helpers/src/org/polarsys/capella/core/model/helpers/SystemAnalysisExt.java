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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 * ComponentArchitecture helpers
 */
public class SystemAnalysisExt {

  /**
   * Gets all the components contained in a component architecture
   * @param componentArchitecture_p the parent component architecture
   * @return list of components
   */
  static public List<Component> getComponentsFromComponentArchitecture(ComponentArchitecture componentArchitecture_p) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : componentArchitecture_p.eContents()) {
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
      if (functionPkg != null && functionPkg instanceof SystemFunctionPkg) {
        list = getAllFunctionsFromFunctionPkg((SystemFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture arch_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch_p) {
      FunctionPkg functionPkg = arch_p.getOwnedFunctionPkg();
      if (functionPkg != null && functionPkg instanceof SystemFunctionPkg) {
        list = getAllFunctionsFromAbstractFunctionPkg((SystemFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunctionPkg(SystemFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg_p) {
      EList<SystemFunction> ownedSystemFunctions = sysFunPkg_p.getOwnedSystemFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedSystemFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (SystemFunctionPkg ownedSysFunPkg : sysFunPkg_p.getOwnedSystemFunctionPkgs()) {
        list.addAll(getAllFunctionsFromFunctionPkg(ownedSysFunPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromAbstractFunctionPkg(SystemFunctionPkg sysFunPkg_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg_p) {
      EList<SystemFunction> ownedSystemFunctions = sysFunPkg_p.getOwnedSystemFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedSystemFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromAbstractFunction(function));
      }
      // owned function of (subPkg of sysFunPkg_p) SystemFunctionPkg
      for (SystemFunctionPkg ownedSysFunPkg : sysFunPkg_p.getOwnedSystemFunctionPkgs()) {
        list.addAll(getAllFunctionsFromAbstractFunctionPkg(ownedSysFunPkg));
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

  static public List<AbstractFunction> getAllFunctionsFromAbstractFunction(AbstractFunction fun_p) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != fun_p) {
      EList<AbstractFunction> ownedSystemFunctions = fun_p.getOwnedFunctions();
      for (AbstractFunction abstractFunction : ownedSystemFunctions) {
        list.add(abstractFunction);
      }
      // owned function of function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromAbstractFunction(function));
      }
    }

    return list;
  }

  /**
   * Retrieve the data pkg from the given architecture
   */
  public static MissionPkg getMissionPkg(SystemAnalysis architecture_p) {
    if (architecture_p.getOwnedMissionPkg() == null) {
      // to externalize when constants in skeleton will be into helpers.
      MissionPkg pkg = CtxFactory.eINSTANCE.createMissionPkg("Missions"); //$NON-NLS-1$
      architecture_p.setOwnedMissionPkg(pkg);
    }
    return architecture_p.getOwnedMissionPkg();
  }
}
