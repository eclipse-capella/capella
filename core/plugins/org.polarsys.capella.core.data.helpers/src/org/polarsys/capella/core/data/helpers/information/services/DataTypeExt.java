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

package org.polarsys.capella.core.data.helpers.information.services;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * DataType helpers
 * 
 */
public class DataTypeExt {

  /**
   * This method retrieves the realized information.
   * @param dataType The data type.
   * @return the realized information list.
   */
  public static List<CapellaElement> getRealizedInformation(DataType dataType) {
    List<CapellaElement> realizedInformation = new ArrayList<>();
    if (null != dataType) {
      for (InformationRealization realization : dataType.getOwnedInformationRealizations()) {
        TraceableElement elt = realization.getTargetElement();
        if (null != elt) {
          realizedInformation.add((CapellaElement) elt);
        }
      }
    }

    return realizedInformation;
  }

  /**
   * This method adds a realized information.
   * @param dataType The data type.
   * @param elt The realized information to add.
   */
  public static void addRealizedInformation(DataType dataType, TraceableElement elt) {
    if ((dataType != null) && (elt != null)) {
      InformationRealization realization = InformationFactory.eINSTANCE.createInformationRealization();
      dataType.getOwnedInformationRealizations().add(realization);
      realization.setTargetElement(elt);
    }
  }

  /**
   * This method removes a realized information.
   * @param dataType The data type.
   * @param elt The realized information to remove.
   */
  public static void removeRealizedInformation(DataType dataType, TraceableElement elt) {
    if ((dataType != null) && (elt != null)) {
      InformationRealization realization = null;
      ListIterator<InformationRealization> it = dataType.getOwnedInformationRealizations().listIterator();
      while (it.hasNext()) {
        InformationRealization gen = it.next();
        if (gen.getTargetElement().equals(elt)) {
          realization = gen;
        }
      }
      if (realization != null) {
        dataType.getOwnedInformationRealizations().remove(realization);
        realization.destroy();
      }
    }
  }
}
