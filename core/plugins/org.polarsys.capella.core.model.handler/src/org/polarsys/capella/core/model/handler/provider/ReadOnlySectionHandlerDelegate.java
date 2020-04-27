/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.handler.provider;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

public class ReadOnlySectionHandlerDelegate implements IReadOnlySectionHandler {
  
  private Collection<IReadOnlySectionHandler> readOnlySectionHandlers;
  
  protected ReadOnlySectionHandlerDelegate(){
    readOnlySectionHandlers = new HashSet<>();
  }

  @Override
  public void register(EObject semanticElement, IReadOnlyListener listener) {
    for(IReadOnlySectionHandler handler : readOnlySectionHandlers){
      handler.register(semanticElement, listener);
    }
  }

  @Override
  public void unregister(EObject semanticElement, IReadOnlyListener listener) {
    for(IReadOnlySectionHandler handler : readOnlySectionHandlers){
      handler.unregister(semanticElement, listener);
    }
  }

  @Override
  public boolean isLockedByOthers(EObject semanticElement) {
    for(IReadOnlySectionHandler handler : readOnlySectionHandlers){
      if(handler.isLockedByOthers(semanticElement)){
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isControllable(EObject semanticElement) {
    for(IReadOnlySectionHandler handler : readOnlySectionHandlers){
      if(!handler.isControllable(semanticElement)){
        return false;
      }
    }
    return true;
  }
  
  public boolean addHandler(IReadOnlySectionHandler handler){
    return readOnlySectionHandlers.add(handler);
  }
  
  public boolean removeHandler(IReadOnlySectionHandler handler){
    return readOnlySectionHandlers.remove(handler);
  }
}
