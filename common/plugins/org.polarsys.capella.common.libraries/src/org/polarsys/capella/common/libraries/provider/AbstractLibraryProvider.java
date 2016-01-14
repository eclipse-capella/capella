/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
