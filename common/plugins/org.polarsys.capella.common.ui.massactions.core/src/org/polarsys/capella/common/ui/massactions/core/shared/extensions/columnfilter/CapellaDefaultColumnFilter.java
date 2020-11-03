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
package org.polarsys.capella.common.ui.massactions.core.shared.extensions.columnfilter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ui.massactions.core.shared.column.ManyRefColumn;
import org.polarsys.capella.common.ui.massactions.core.shared.column.SingleRefColumn;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.CommonElementsHelper;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.kitalpha.massactions.core.column.IMAColumn;
import org.polarsys.kitalpha.massactions.core.column.primitive.MAPrimitiveColumn;
import org.polarsys.kitalpha.massactions.core.extensions.columnfilter.AbstractMAColumnFilter;

/**
 * A Capella edition column filter.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaDefaultColumnFilter extends AbstractMAColumnFilter {

  protected Set<String> hiddenColumnsId;
  protected boolean configured;

  public CapellaDefaultColumnFilter() {
    super();
    this.hiddenColumnsId = new HashSet<>();
    this.configured = false;
  }

  protected void configure() {
    // hide id
    hiddenColumnsId.add(ModellingcorePackage.Literals.MODEL_ELEMENT__ID.getName());

    // hide sid
    hiddenColumnsId.add(ModellingcorePackage.Literals.MODEL_ELEMENT__SID.getName());

    // sirius
    hiddenColumnsId.add(ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REP_PATH.getName());
  }

  @Override
  public boolean shouldHide(IMAColumn column) {

    // lazy initialization
    if (!configured) {
      configure();
      configured = true;
    }

    if (column instanceof MAPrimitiveColumn) {
      MAPrimitiveColumn primitiveColumn = (MAPrimitiveColumn) column;
      EStructuralFeature feature = primitiveColumn.getPossibleFeature().getFeature();

      if (isDerived(feature) || isNotChangeable(feature) || isContainment(feature)
          || hasNullBQ(primitiveColumn, feature)) {
        return true;
      }
    }

    return hiddenColumnsId.contains(column.getId());
  }

  protected boolean isDerived(EStructuralFeature feature) {
    return feature.isDerived();
  }

  protected boolean isNotChangeable(EStructuralFeature feature) {
    return !feature.isChangeable();
  }

  protected boolean isContainment(EStructuralFeature feature) {
    return (feature instanceof EReference && ((EReference) feature).isContainment());
  }

  protected boolean hasNullBQ(MAPrimitiveColumn primitiveColumn, EStructuralFeature feature) {

    if (primitiveColumn instanceof SingleRefColumn || primitiveColumn instanceof ManyRefColumn) {
      IBusinessQuery businessQuery = CommonElementsHelper.getBusinessQuery(feature);
      return businessQuery == null;
    }

    return false;
  }
}