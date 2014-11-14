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

import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class AbstractExchangeItemPkgExt {
  
  public static List<AbstractExchangeItem> getAllAbstractExchangeItems(AbstractExchangeItemPkg pkg_p) {
    List<AbstractExchangeItem> list = new ArrayList<AbstractExchangeItem>();
    if (null != pkg_p) {
      // retrieve from current package
      list.addAll(pkg_p.getOwnedExchangeItems());
      // retrieve from all subPkgs
      if (pkg_p instanceof DataPkg){
        for (DataPkg subPkg : ((DataPkg)pkg_p).getOwnedDataPkgs()) {
          list.addAll(getAllAbstractExchangeItems(subPkg));
        } 
      }
      if (pkg_p instanceof InterfacePkg){
        for (InterfacePkg subPkg : ((InterfacePkg)pkg_p).getOwnedInterfacePkgs()) {
          list.addAll(getAllAbstractExchangeItems(subPkg));
        } 
      } 
    }
    return list;
 }
}
