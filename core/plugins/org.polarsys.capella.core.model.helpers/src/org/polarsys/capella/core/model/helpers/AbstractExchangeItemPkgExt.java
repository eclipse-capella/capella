/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;

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
