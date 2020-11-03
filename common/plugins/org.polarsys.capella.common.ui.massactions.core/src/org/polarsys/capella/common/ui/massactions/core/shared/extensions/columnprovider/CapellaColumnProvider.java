/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.extensions.columnprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.ui.massactions.core.shared.column.ManyRefColumn;
import org.polarsys.capella.common.ui.massactions.core.shared.column.SingleRefColumn;
import org.polarsys.kitalpha.massactions.core.column.IMAColumn;
import org.polarsys.kitalpha.massactions.core.column.primitive.MAPrimitiveColumn;
import org.polarsys.kitalpha.massactions.core.extensions.columnprovider.AbstractMAColumnProvider;
import org.polarsys.kitalpha.massactions.core.helper.ColumnProviderHelper;
import org.polarsys.kitalpha.massactions.core.helper.container.PossibleFeature;

/**
 * The default Capella column provider.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaColumnProvider extends AbstractMAColumnProvider {

  @Override
  public List<IMAColumn> getColumnValues(Collection<PossibleFeature> commonPossibleFeatures, Collection<EObject> data) {

    List<IMAColumn> columns = new ArrayList<>();

    for (PossibleFeature possibleFeature : commonPossibleFeatures) {

      EStructuralFeature feature = possibleFeature.getFeature();
      MAPrimitiveColumn column = null;

      if (ColumnProviderHelper.isReferenceType(feature)) {

        if (feature.isMany()) {
          column = new ManyRefColumn();
        } else {
          column = new SingleRefColumn();
        }

        column.setPossibleFeature(possibleFeature);
        column.setBodyLayer(bodyLayer);
        columns.add(column);
      }
    }

    return columns;
  }

}
