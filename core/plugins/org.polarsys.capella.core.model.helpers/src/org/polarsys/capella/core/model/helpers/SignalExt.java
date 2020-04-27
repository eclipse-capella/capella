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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Generalization;

/**
 * Signal helpers
 */
public class SignalExt {

  /**
   * This method returns the root data package of the current signal
   * @param signal
   * @return
   */
  public static DataPkg getOwnerSignalPkg(Signal signal) {
    if (null != signal) {
      if (signal.eContainer() instanceof Signal) {
        return getRootOwnerDataPkg((Signal) signal.eContainer());
      } else if (signal.eContainer() instanceof DataPkg) {
        return (DataPkg) signal.eContainer();
      }
    }
    return null;
  }

  /**
   * This method returns the root data package of the current signal
   * @param signal
   * @return
   */
  public static DataPkg getRootOwnerDataPkg(Signal signal) {
    if (null != signal) {
      if (signal.eContainer() instanceof Signal) {
        return getRootOwnerDataPkg((Signal) signal.eContainer());
      } else if (signal.eContainer() instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) signal.eContainer());
      }
    }
    return null;
  }

  /**
   * @see #getSignalDependencies(Signal)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getSignalDependencies2(Signal signal) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    // properties
    for (Property aProperty : signal.getProperties()) {
      // type of properties
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aProperty.getType());
    }
    // superSignals
    for (Generalization aGeneralization : signal.getSuperGeneralizations()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aGeneralization.getSuper());
    }

    return result;
  }

  /**
   * @param signal
   * @return all dependent packages of signal
   */
  public static Collection<AbstractDependenciesPkg> getSignalDependencies(Signal signal) {
    return getSignalDependencies2(signal).keySet();
  }

}
