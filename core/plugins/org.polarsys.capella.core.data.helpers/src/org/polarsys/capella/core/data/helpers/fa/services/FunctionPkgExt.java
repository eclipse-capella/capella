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

package org.polarsys.capella.core.data.helpers.fa.services;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemFunctionPkg;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FunctionPkg;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.core.data.pa.PhysicalFunctionPkg;

/**
 */
public class FunctionPkgExt {
  
  private FunctionPkgExt() {
    // To hide the implicit public one
  }

  /**
   * Get All the ExchangeCategories from FunctionPkg
   * @param functionPkg
   * @return list of ExchageCategories
   */
  public static List<ExchangeCategory> getAllExchangeCategories(FunctionPkg functionPkg) {
    List<ExchangeCategory> list = new ArrayList<>(1);

    for (FunctionPkg pkg : getAllFunctionPkgs(functionPkg)) {
      list.addAll(pkg.getOwnedCategories());
    }

    return list;
  }

  /**
   * @param arch
   * @return all functionPkgs contained in arch
   */
  public static List<FunctionPkg> getAllFunctionPkgs(BlockArchitecture arch) {
    return getAllFunctionPkgs(arch.getOwnedFunctionPkg());
  }

  /**
   * @param functionPkg
   * @return all contained FunctionPkgs
   */
  public static List<FunctionPkg> getOwnedFunctionPkgs(FunctionPkg functionPkg) {
    List<FunctionPkg> containedFunctionPkgs = new ArrayList<>();
    if (functionPkg instanceof OperationalActivityPkg) {
      containedFunctionPkgs.addAll(((OperationalActivityPkg) functionPkg).getOwnedOperationalActivityPkgs());
    }
    if (functionPkg instanceof SystemFunctionPkg) {
      containedFunctionPkgs.addAll(((SystemFunctionPkg) functionPkg).getOwnedSystemFunctionPkgs());
    }
    if (functionPkg instanceof LogicalFunctionPkg) {
      containedFunctionPkgs.addAll(((LogicalFunctionPkg) functionPkg).getOwnedLogicalFunctionPkgs());
    }
    if (functionPkg instanceof PhysicalFunctionPkg) {
      containedFunctionPkgs.addAll(((PhysicalFunctionPkg) functionPkg).getOwnedPhysicalFunctionPkgs());
    }
    return containedFunctionPkgs;
  }

  /**
   * @param functionPkg
   * @return all functionPkgs contained recursively in functionPkg
   */
  public static List<FunctionPkg> getAllFunctionPkgs(FunctionPkg functionPkg) {
    List<FunctionPkg> returnedList = new ArrayList<>();
    if (functionPkg == null) {
      return returnedList;
    }
    returnedList.add(functionPkg);

    for (AbstractFunction aFunction : getOwnedFunctions(functionPkg)) {
      returnedList.addAll(getCache(FunctionExt::getAllFunctionPkgs, aFunction));
    }

    for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(functionPkg)) {
      returnedList.addAll(getAllFunctionPkgs(aFunctionPkg));
    }
    return returnedList;
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * @return
   */
  public static Collection<AbstractFunction> getFirstLevelAbstractFunctions(FunctionPkg container) {
    Collection<AbstractFunction> result = new ArrayList<>();

    result.addAll(FunctionPkgExt.getOwnedFunctions(container));
    for (FunctionPkg pkg : FunctionPkgExt.getOwnedFunctionPkgs(container)) {
      result.addAll(getFirstLevelAbstractFunctions(pkg));
    }
    return result;
  }

  /**
   * @param functionPkg
   * @return functions contained in functionPkg
   */
  public static List<AbstractFunction> getOwnedFunctions(FunctionPkg functionPkg) {
    List<AbstractFunction> returnedList = new ArrayList<>();
    if (functionPkg instanceof OperationalActivityPkg) {
      returnedList.addAll(((OperationalActivityPkg) functionPkg).getOwnedOperationalActivities());
    }
    if (functionPkg instanceof SystemFunctionPkg) {
      returnedList.addAll(((SystemFunctionPkg) functionPkg).getOwnedSystemFunctions());
    }
    if (functionPkg instanceof LogicalFunctionPkg) {
      returnedList.addAll(((LogicalFunctionPkg) functionPkg).getOwnedLogicalFunctions());
    }
    if (functionPkg instanceof PhysicalFunctionPkg) {
      returnedList.addAll(((PhysicalFunctionPkg) functionPkg).getOwnedPhysicalFunctions());
    }
    return returnedList;
  }

  /**
   * @param functionPkg
   * @return all abstractFunctions in blockArchitecture
   */
  public static List<AbstractFunction> getAllAbstractFunctions(FunctionPkg functionPkg) {
    List<AbstractFunction> returnedList = new ArrayList<>();

    if (functionPkg != null) {
      for (AbstractFunction aFunction : getOwnedFunctions(functionPkg)) {
        returnedList.addAll(getCache(FunctionExt::getAllAbstractFunctions, aFunction));
      }

      for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(functionPkg)) {
        returnedList.addAll(getCache(FunctionPkgExt::getAllAbstractFunctions, aFunctionPkg));
      }
    }

    return returnedList;
  }
}
