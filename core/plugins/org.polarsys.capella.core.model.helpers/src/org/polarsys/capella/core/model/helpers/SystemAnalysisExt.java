/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param componentArchitecture the parent component architecture
   * @return list of components
   */
  static public List<Component> getComponentsFromComponentArchitecture(ComponentArchitecture componentArchitecture) {
    List<Component> list = new ArrayList<Component>();
    for (Object obj : componentArchitecture.eContents()) {
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

  static public List<AbstractFunction> getAllFunctions(BlockArchitecture arch) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch) {
      FunctionPkg functionPkg = arch.getOwnedFunctionPkg();
      if (functionPkg != null && functionPkg instanceof SystemFunctionPkg) {
        list = getAllFunctionsFromFunctionPkg((SystemFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllAbstractFunctions(BlockArchitecture arch) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);
    if (null != arch) {
      FunctionPkg functionPkg = arch.getOwnedFunctionPkg();
      if (functionPkg != null && functionPkg instanceof SystemFunctionPkg) {
        list = getAllFunctionsFromAbstractFunctionPkg((SystemFunctionPkg) functionPkg);
      }

    }
    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunctionPkg(SystemFunctionPkg sysFunPkg) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg) {
      EList<SystemFunction> ownedSystemFunctions = sysFunPkg.getOwnedSystemFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedSystemFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromFunction(function));
      }
      // owned function of (subPkg of sysFunPkg) SystemFunctionPkg
      for (SystemFunctionPkg ownedSysFunPkg : sysFunPkg.getOwnedSystemFunctionPkgs()) {
        list.addAll(getAllFunctionsFromFunctionPkg(ownedSysFunPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromAbstractFunctionPkg(SystemFunctionPkg sysFunPkg) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != sysFunPkg) {
      EList<SystemFunction> ownedSystemFunctions = sysFunPkg.getOwnedSystemFunctions();
      // owned function of SystemFunctionPkg
      list.addAll(ownedSystemFunctions);
      // owned function of Function
      for (AbstractFunction function : ownedSystemFunctions) {
        list.addAll(getAllFunctionsFromAbstractFunction(function));
      }
      // owned function of (subPkg of sysFunPkg) SystemFunctionPkg
      for (SystemFunctionPkg ownedSysFunPkg : sysFunPkg.getOwnedSystemFunctionPkgs()) {
        list.addAll(getAllFunctionsFromAbstractFunctionPkg(ownedSysFunPkg));
      }
    }

    return list;
  }

  static public List<AbstractFunction> getAllFunctionsFromFunction(AbstractFunction fun) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != fun) {

      EList<AbstractFunction> ownedSystemFunctions = fun.getOwnedFunctions();
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

  static public List<AbstractFunction> getAllFunctionsFromAbstractFunction(AbstractFunction fun) {
    List<AbstractFunction> list = new ArrayList<AbstractFunction>(1);

    if (null != fun) {
      EList<AbstractFunction> ownedSystemFunctions = fun.getOwnedFunctions();
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
  public static MissionPkg getMissionPkg(SystemAnalysis architecture) {
    if (architecture.getOwnedMissionPkg() == null) {
      // to externalize when constants in skeleton will be into helpers.
      MissionPkg pkg = CtxFactory.eINSTANCE.createMissionPkg("Missions"); //$NON-NLS-1$
      architecture.setOwnedMissionPkg(pkg);
    }
    return architecture.getOwnedMissionPkg();
  }
}
