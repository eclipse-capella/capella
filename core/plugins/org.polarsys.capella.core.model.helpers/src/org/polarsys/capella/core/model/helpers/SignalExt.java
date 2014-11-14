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
   * @param signal_p
   * @return
   */
  public static DataPkg getOwnerSignalPkg(Signal signal_p) {
    if (null != signal_p) {
      if (signal_p.eContainer() instanceof Signal) {
        return getRootOwnerDataPkg((Signal) signal_p.eContainer());
      } else if (signal_p.eContainer() instanceof DataPkg) {
        return (DataPkg) signal_p.eContainer();
      }
    }
    return null;
  }

  /**
   * This method returns the root data package of the current signal
   * @param signal_p
   * @return
   */
  public static DataPkg getRootOwnerDataPkg(Signal signal_p) {
    if (null != signal_p) {
      if (signal_p.eContainer() instanceof Signal) {
        return getRootOwnerDataPkg((Signal) signal_p.eContainer());
      } else if (signal_p.eContainer() instanceof DataPkg) {
        return DataPkgExt.getRootDataPkg((DataPkg) signal_p.eContainer());
      }
    }
    return null;
  }

  /**
   * @see #getSignalDependencies(Signal)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getSignalDependencies2(Signal signal_p) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result = new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    // properties
    for (Property aProperty : signal_p.getProperties()) {
      // type of properties
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aProperty.getType());
    }
    // superSignals
    for (Generalization aGeneralization : signal_p.getSuperGeneralizations()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aGeneralization.getSuper());
    }

    return result;
  }

  /**
   * @param signal_p
   * @return all dependent packages of signal_p
   */
  public static Collection<AbstractDependenciesPkg> getSignalDependencies(Signal signal_p) {
    return getSignalDependencies2(signal_p).keySet();
  }

}
