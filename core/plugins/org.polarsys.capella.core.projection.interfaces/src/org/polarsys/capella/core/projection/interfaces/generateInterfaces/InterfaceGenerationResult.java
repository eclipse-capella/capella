/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.projection.interfaces.InterfaceGenerationPreferences;

public class InterfaceGenerationResult {

  private final InterfaceGenerationPreferences prefs;
  private final Collection<InterfaceInfo> infos = new LinkedHashSet<InterfaceInfo>();

  public InterfaceGenerationResult(InterfaceGenerationPreferences prefs){
    this.prefs = prefs;
  }
  
  /**
   * Returns the updated interfaces. It is not necessary to call
   * apply() before.
   */
  public Collection<Interface> getUpdatedInterfaces(){
    Collection<Interface> result = new ArrayList<Interface>();
    for (InterfaceInfo i : infos) {
      Interface existing = i.getInterface(false);
      if (existing != null){
        result.add(existing);
      }
    }
    return result;
  }
  
  /**
   * Returns the exchange items that are removed from the given updated interface.
   */
  public Collection<ExchangeItem> getRemovedExchangeItems(Interface i){
    for (InterfaceInfo inf : infos){
      if (inf.getInterface(false) == i){        
        return ExchangeItemUpdater.getRemovedExchangeItems(i, inf.getExchangeItems(prefs));
      }
    }
    throw new IllegalArgumentException("Interface is not part of this generation result");
  }

  /**
   * Returns the exchange items that are added to the given updated interface.
   */
  public Collection<ExchangeItem> getAddedExchangeItems(Interface i){
    for (InterfaceInfo inf : infos) {
      if (i == inf.getInterface(false)){
        return ExchangeItemUpdater.getAddedExchangeItems(i, inf.getExchangeItems(prefs));
      }
    }
    throw new IllegalArgumentException("Interface is not part of this generation result");
  }

  public Collection<InterfaceInfo> getInterfaceInfos(){
    return infos;
  }

  public InterfaceInfo getInterfaceInfo(Interface iface){
    for (InterfaceInfo info : getInterfaceInfos()){
      if (info.getInterface(false) == iface){
        return info;
      }
    }
    throw new IllegalArgumentException("Interface is not part of this generation result");
  }
}
