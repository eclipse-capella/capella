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
package org.polarsys.capella.common.ui.massactions.core.shared.helper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.nattable.data.IRowDataProvider;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQInput;
import org.polarsys.capella.common.ui.massactions.core.shared.helper.container.CommonBQResult;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;

/**
 * A common elements helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class CommonElementsHelper {

  private CommonElementsHelper() {
    // Exists only to defeat instantiation.
  }

  /**
   * Returns the associated IBusinessQuery to the current selected cell.
   * 
   * @param cell
   *          the current selected cell
   * @param featureMap
   *          the existing feature map used to retrieve the EstructuralFeature associated with the current cell value
   * @param bodyDataProvider
   *          the existing bodyDataProvider used to retrieve the current row object
   * @return the associated IBusinessQuery to the current selected cell
   */
  private static IBusinessQuery getBusinessQuery(ILayerCell cell, Map<String, List<EStructuralFeature>> featureMap,
      IRowDataProvider<EObject> bodyDataProvider) {

    int rowIndex = cell.getRowIndex();
    int columnIndex = cell.getColumnIndex();

    EObject rowObject = bodyDataProvider.getRowObject(rowIndex);
    EStructuralFeature feature = featureMap.get(rowObject.eClass().getName()).get(columnIndex);

    // class of the containing object
    return getBusinessQuery(feature);
  }

  /**
   * Returns the associated IBusinessQuery for the current structural feature.
   * 
   * @param feature
   *          current structural feature
   * @return the associated IBusinessQuery for the current structural feature.
   */
  public static IBusinessQuery getBusinessQuery(EStructuralFeature feature) {
    return BusinessQueriesProvider.getInstance().getContribution(feature.getEContainingClass(), feature);
  }

  /**
   * For a list of currently selected cells, provides all the common Business Query result elements, for which the value
   * of the current cells might be replaced with.
   * 
   * @param currentCells
   *          the current analyzed cells
   * @param featureMap
   *          the existing feature map used to retrieve the EstructuralFeature associated with the current cell value
   * @param bodyDataProvider
   *          the existing bodyDataProvider used to retrieve the current row object
   * @return the common Business Query result elements, for which the value of the current cells might be replaced with.
   */
  public static CommonBQResult getAllCommonBusinessQueryResults(List<ILayerCell> currentCells,
      Map<String, List<EStructuralFeature>> featureMap, IRowDataProvider<EObject> bodyDataProvider) {

    if (currentCells.isEmpty()) {
      return CommonBQResult.emptyResult();
    }

    ILayerCell sampleCell = currentCells.get(0);
    IBusinessQuery sampleCellQuery = getBusinessQuery(sampleCell, featureMap, bodyDataProvider);

    // no change possible if no elements
    if (sampleCellQuery == null) {
      return CommonBQResult.emptyResult();
    }

    EObject rowObject = bodyDataProvider.getRowObject(sampleCell.getRowIndex());

    Set<EObject> commonAvailableElements = new HashSet<>(sampleCellQuery.getAvailableElements(rowObject));
    Set<EObject> commonCurrentElements = new HashSet<>(sampleCellQuery.getCurrentElements(rowObject, false));

    // if more than one elements are selected, add the current elements also
    // in order to correctly compute all the common elements
    if (currentCells.size() > 1) {
      commonAvailableElements.addAll(commonCurrentElements);
    }

    for (int i = 1; i < currentCells.size(); i++) {

      sampleCell = currentCells.get(i);
      sampleCellQuery = getBusinessQuery(sampleCell, featureMap, bodyDataProvider);

      // return if no available elements
      if (sampleCellQuery == null) {
        return CommonBQResult.emptyResult();
      }

      rowObject = bodyDataProvider.getRowObject(sampleCell.getRowIndex());

      List<EObject> availableElements = sampleCellQuery.getAvailableElements(rowObject);
      List<EObject> currentElements = sampleCellQuery.getCurrentElements(rowObject, false);

      availableElements.addAll(currentElements);
      commonAvailableElements.retainAll(availableElements);
      commonCurrentElements.retainAll(currentElements);
    }

    return new CommonBQResult(commonAvailableElements, commonCurrentElements);
  }

  /**
   * Extracts the common business query result from a list of common business query inputs.
   * 
   * @param inputElements
   *          the business query inputs.
   * @return the common business query result.
   */
  public static CommonBQResult getAllCommonBusinessQueryResults(List<CommonBQInput> inputElements) {

    if (inputElements.isEmpty()) {
      return CommonBQResult.emptyResult();
    }

    CommonBQInput sampleInput = inputElements.get(0);
    EStructuralFeature sampleFeature = sampleInput.getColumnFeature();
    IBusinessQuery sampleQuery = getBusinessQuery(sampleFeature);

    // no change possible if no elements
    if (sampleQuery == null) {
      return CommonBQResult.emptyResult();
    }

    EObject sampleRowObject = sampleInput.getRowObject();

    Set<EObject> commonAvailableElements = new HashSet<>(sampleQuery.getAvailableElements(sampleRowObject));
    Set<EObject> commonCurrentElements = new HashSet<>(sampleQuery.getCurrentElements(sampleRowObject, false));

    // if more than one elements are selected, add the current elements also
    // in order to correctly compute all the common elements
    if (inputElements.size() > 1) {
      commonAvailableElements.addAll(commonCurrentElements);
    }

    for (int i = 1; i < inputElements.size(); i++) {

      sampleInput = inputElements.get(i);
      sampleFeature = sampleInput.getColumnFeature();
      sampleQuery = getBusinessQuery(sampleFeature);

      // return if no available elements
      if (sampleQuery == null) {
        return CommonBQResult.emptyResult();
      }

      sampleRowObject = sampleInput.getRowObject();

      List<EObject> availableElements = sampleQuery.getAvailableElements(sampleRowObject);
      List<EObject> currentElements = sampleQuery.getCurrentElements(sampleRowObject, false);

      availableElements.addAll(currentElements);
      commonAvailableElements.retainAll(availableElements);
      commonCurrentElements.retainAll(currentElements);
    }

    return new CommonBQResult(commonAvailableElements, commonCurrentElements);
  }

}
