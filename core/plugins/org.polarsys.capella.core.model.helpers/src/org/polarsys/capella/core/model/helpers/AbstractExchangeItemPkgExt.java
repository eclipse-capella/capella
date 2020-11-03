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

import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class AbstractExchangeItemPkgExt {
  
  public static List<AbstractExchangeItem> getAllAbstractExchangeItems(AbstractExchangeItemPkg pkg) {
    List<AbstractExchangeItem> list = new ArrayList<AbstractExchangeItem>();
    if (null != pkg) {
      // retrieve from current package
      list.addAll(pkg.getOwnedExchangeItems());
      // retrieve from all subPkgs
      if (pkg instanceof DataPkg){
        for (DataPkg subPkg : ((DataPkg)pkg).getOwnedDataPkgs()) {
          list.addAll(getAllAbstractExchangeItems(subPkg));
        } 
      }
      if (pkg instanceof InterfacePkg){
        for (InterfacePkg subPkg : ((InterfacePkg)pkg).getOwnedInterfacePkgs()) {
          list.addAll(getAllAbstractExchangeItems(subPkg));
        } 
      } 
    }
    return list;
 }
}
