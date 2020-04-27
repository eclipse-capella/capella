/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.libraries.provider;

import java.util.ArrayList;
import java.util.Collection;



public abstract class AbstractLibraryProvider implements ILibraryProvider {

  private final Collection<ILibraryProviderListener> listeners = new ArrayList<ILibraryProviderListener>();

  @Override
  public void addListener(ILibraryProviderListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeListener(ILibraryProviderListener listener) {
    listeners.remove(listener);
  }

  public void notifyListeners(ILibraryProviderListener.LibraryProviderEvent event) {
    for (ILibraryProviderListener listener : listeners) {
      listener.onLibraryProviderChanged(event);
    }
  }
}
