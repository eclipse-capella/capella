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
package org.polarsys.capella.core.data.helpers.fa.services;

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

  /**
   * Get All the ExchangeCategories from FunctionPkg
   * @param functionPkg_p
   * @return list of ExchageCategories
   */
  public static List<ExchangeCategory> getAllExchangeCategories(FunctionPkg functionPkg_p) {
    List<ExchangeCategory> list = new ArrayList<ExchangeCategory>(1);

    for (FunctionPkg pkg : getAllFunctionPkgs(functionPkg_p)) {
      list.addAll(pkg.getOwnedCategories());
    }

    return list;
  }

  /**
   * @param arch_p
   * @return all functionPkgs contained in arch_p
   */
  public static List<FunctionPkg> getAllFunctionPkgs(BlockArchitecture arch_p) {
    return getAllFunctionPkgs(arch_p.getOwnedFunctionPkg());
  }

  /**
   * @param functionPkg_p
   * @return all contained FunctionPkgs
   */
  public static List<FunctionPkg> getOwnedFunctionPkgs(FunctionPkg functionPkg_p) {
    List<FunctionPkg> containedFunctionPkgs = new ArrayList<FunctionPkg>();
    if (functionPkg_p instanceof OperationalActivityPkg) {
      containedFunctionPkgs.addAll(((OperationalActivityPkg) functionPkg_p).getOwnedOperationalActivityPkgs());
    }
    if (functionPkg_p instanceof SystemFunctionPkg) {
      containedFunctionPkgs.addAll(((SystemFunctionPkg) functionPkg_p).getOwnedSystemFunctionPkgs());
    }
    if (functionPkg_p instanceof LogicalFunctionPkg) {
      containedFunctionPkgs.addAll(((LogicalFunctionPkg) functionPkg_p).getOwnedLogicalFunctionPkgs());
    }
    if (functionPkg_p instanceof PhysicalFunctionPkg) {
      containedFunctionPkgs.addAll(((PhysicalFunctionPkg) functionPkg_p).getOwnedPhysicalFunctionPkgs());
    }
    return containedFunctionPkgs;
  }

  /**
   * @param functionPkg_p
   * @return all functionPkgs contained recursively in functionPkg_p
   */
  public static List<FunctionPkg> getAllFunctionPkgs(FunctionPkg functionPkg_p) {
    List<FunctionPkg> returnedList = new ArrayList<FunctionPkg>();
    if (functionPkg_p == null) {
      return returnedList;
    }
    returnedList.add(functionPkg_p);

    for (AbstractFunction aFunction : getOwnedFunctions(functionPkg_p)) {
      returnedList.addAll(FunctionExt.getAllFunctionPkgs(aFunction));
    }

    for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(functionPkg_p)) {
      returnedList.addAll(getAllFunctionPkgs(aFunctionPkg));
    }
    return returnedList;
  }

  /**
   * Returns functions owned by the function or owned function pkg
   * @return
   */
  public static Collection<AbstractFunction> getFirstLevelAbstractFunctions(FunctionPkg container_p) {
    Collection<AbstractFunction> result = new ArrayList<AbstractFunction>();

    result.addAll(FunctionPkgExt.getOwnedFunctions(container_p));
    for (FunctionPkg pkg : FunctionPkgExt.getOwnedFunctionPkgs(container_p)) {
      result.addAll(getFirstLevelAbstractFunctions(pkg));
    }
    return result;
  }

  /**
   * @param functionPkg_p
   * @return functions contained in functionPkg_p
   */
  public static List<AbstractFunction> getOwnedFunctions(FunctionPkg functionPkg_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();
    if (functionPkg_p instanceof OperationalActivityPkg) {
      returnedList.addAll(((OperationalActivityPkg) functionPkg_p).getOwnedOperationalActivities());
    }
    if (functionPkg_p instanceof SystemFunctionPkg) {
      returnedList.addAll(((SystemFunctionPkg) functionPkg_p).getOwnedSystemFunctions());
    }
    if (functionPkg_p instanceof LogicalFunctionPkg) {
      returnedList.addAll(((LogicalFunctionPkg) functionPkg_p).getOwnedLogicalFunctions());
    }
    if (functionPkg_p instanceof PhysicalFunctionPkg) {
      returnedList.addAll(((PhysicalFunctionPkg) functionPkg_p).getOwnedPhysicalFunctions());
    }
    return returnedList;
  }

  /**
   * @param blockArchitecture_p
   * @return all abstractFunctions in blockArchitecture_p
   */
  public static List<AbstractFunction> getAllAbstractFunctions(FunctionPkg functionPkg_p) {
    List<AbstractFunction> returnedList = new ArrayList<AbstractFunction>();

    if (functionPkg_p != null) {
      for (AbstractFunction aFunction : getOwnedFunctions(functionPkg_p)) {
        returnedList.addAll(FunctionExt.getAllAbstractFunctions(aFunction));
      }

      for (FunctionPkg aFunctionPkg : getOwnedFunctionPkgs(functionPkg_p)) {
        returnedList.addAll(getAllAbstractFunctions(aFunctionPkg));
      }
    }

    return returnedList;
  }
}
