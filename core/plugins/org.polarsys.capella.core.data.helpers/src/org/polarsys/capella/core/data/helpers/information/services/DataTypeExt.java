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
   * @param dataType_p The data type.
   * @return the realized information list.
   */
  public static List<CapellaElement> getRealizedInformation(DataType dataType_p) {
    List<CapellaElement> realizedInformation = new ArrayList<CapellaElement>();
    if (null != dataType_p) {
      for (InformationRealization realization : dataType_p.getOwnedInformationRealizations()) {
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
   * @param dataType_p The data type.
   * @param elt_p The realized information to add.
   */
  public static void addRealizedInformation(DataType dataType_p, TraceableElement elt_p) {
    if ((dataType_p != null) && (elt_p != null)) {
      InformationRealization realization = InformationFactory.eINSTANCE.createInformationRealization();
      dataType_p.getOwnedInformationRealizations().add(realization);
      realization.setTargetElement(elt_p);
    }
  }

  /**
   * This method removes a realized information.
   * @param classifier_p The data type.
   * @param superClassifier_p The realized information to remove.
   */
  public static void removeRealizedInformation(DataType dataType_p, TraceableElement elt_p) {
    if ((dataType_p != null) && (elt_p != null)) {
      InformationRealization realization = null;
      ListIterator<InformationRealization> it = dataType_p.getOwnedInformationRealizations().listIterator();
      while (it.hasNext()) {
        InformationRealization gen = it.next();
        if (gen.getTargetElement().equals(elt_p)) {
          realization = gen;
        }
      }
      if (realization != null) {
        dataType_p.getOwnedInformationRealizations().remove(realization);
        realization.destroy();
      }
    }
  }
}
