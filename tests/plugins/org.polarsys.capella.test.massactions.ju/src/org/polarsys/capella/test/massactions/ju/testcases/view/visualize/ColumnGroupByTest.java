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
package org.polarsys.capella.test.massactions.ju.testcases.view.visualize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.extension.glazedlists.groupBy.GroupByHeaderLayer;
import org.eclipse.nebula.widgets.nattable.extension.glazedlists.groupBy.GroupByModel;
import org.eclipse.nebula.widgets.nattable.extension.glazedlists.groupBy.command.GroupByColumnIndexCommand;
import org.polarsys.capella.test.massactions.ju.helpers.LayerHelper;
import org.polarsys.capella.test.massactions.ju.helpers.ViewHelper;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;
import org.polarsys.kitalpha.massactions.core.data.accessor.IMAColumnPropertyAccessor;
import org.polarsys.kitalpha.massactions.shared.view.MAView;
import org.polarsys.kitalpha.massactions.visualize.MVView;
import org.polarsys.kitalpha.massactions.visualize.table.layer.groupby.IMVGroupByLayer;

/**
 * 
 * @author Sandu Postaru
 *
 */
public class ColumnGroupByTest extends AbstractCapellaMATestCase {

  @Override
  public void performTest() throws Exception {
    MVView visualizationView = ViewHelper.getActiveMVView();

    testGroupBy(visualizationView);
  }

  /**
   * This method tests the action of grouping columns. It ensures that the SAME EXACT grouped columns are preserved when
   * a new element of the same type as the existing ones is added to the view. And that ONLY THE COMMON grouped columns
   * are preserved when a new element of a new type is added.
   * 
   * @param view
   *          the view that is tested.
   */
  public void testGroupBy(MAView view) {

    List<EObject> data = new ArrayList<>();
    data.add(getObject(SF_ENTERTAIN_WITH_IFE_SYSTEM));
    view.dataChanged(data);

    // columns to be tested
    List<String> columnNamesToGroup = Arrays.asList("name", "visibleInDoc", "ordered", "unique");
    // shuffle the names
    Collections.shuffle(columnNamesToGroup);

    // initialization
    IMVGroupByLayer groupByLayer = LayerHelper.extractGroupByLayer(view);
    IMAColumnPropertyAccessor columnPropertyAccessor = groupByLayer.getGridLayer().getBodyLayer()
        .getColumnPropertyAccessor();
    GroupByHeaderLayer groupByHeaderLayer = groupByLayer.getGroupByHeaderLayer();
    GroupByModel groupByModel = groupByHeaderLayer.getGroupByModel();

    // the internal representation grouped columns
    Collection<Integer> beforeGroupedColumns = Collections.emptySet();
    Collection<Integer> afterGroupedColumns = Collections.emptySet();

    // group each column one by one
    for (String columnName : columnNamesToGroup) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists
      if (columnIndex != -1) {

        beforeGroupedColumns = new ArrayList<>(groupByModel.getGroupByColumnIndexes());
        // column is not grouped
        assertFalse(beforeGroupedColumns.contains(columnIndex));

        // group
        groupByHeaderLayer.doCommand(new GroupByColumnIndexCommand(columnIndex));

        afterGroupedColumns = new ArrayList<>(groupByModel.getGroupByColumnIndexes());
        // check that it's grouped
        assertTrue(afterGroupedColumns.contains(columnIndex));
      }
    }

    // add another system function and check that the same columns are
    // grouped
    beforeGroupedColumns = new ArrayList<>(groupByModel.getGroupByColumnIndexes());

    data.add(getObject(SF_PERFORM_CABIN_MANAGEMENT_ACTIVITIES));
    view.dataChanged(data);

    afterGroupedColumns = new ArrayList<>(groupByModel.getGroupByColumnIndexes());

    assertFalse(beforeGroupedColumns.isEmpty());
    assertFalse(afterGroupedColumns.isEmpty());
    assertEquals(beforeGroupedColumns, afterGroupedColumns);

    // add a category and check that only the common column (name,
    // visibleInDoc) are grouped
    data.add(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA));
    view.dataChanged(data);

    // check that only the common column (name, visibleInDoc) are grouped
    for (String columnName : columnNamesToGroup) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists
      if (columnIndex != -1) {
        afterGroupedColumns = new ArrayList<>(groupByModel.getGroupByColumnIndexes());

        // check that it's grouped
        assertTrue(afterGroupedColumns.contains(columnIndex));
        System.err.println(columnName);
      }
    }

    // reset the view
    ViewHelper.resetViews(view);
    assertTrue(data.remove(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA)));
    view.dataChanged(data);

    // re-initialization
    // get new references since the view has been reset
    groupByLayer = LayerHelper.extractGroupByLayer(view);
    columnPropertyAccessor = groupByLayer.getGridLayer().getBodyLayer().getColumnPropertyAccessor();
    groupByHeaderLayer = groupByLayer.getGroupByHeaderLayer();
    groupByModel = groupByHeaderLayer.getGroupByModel();

    // group the NOT common column "unique"
    int columnIndex = columnPropertyAccessor.getColumnIndex("unique");

    // check that not grouped
    beforeGroupedColumns = groupByModel.getGroupByColumnIndexes();
    assertFalse(beforeGroupedColumns.contains(columnIndex));

    // group
    groupByHeaderLayer.doCommand(new GroupByColumnIndexCommand(columnIndex));

    // check that grouped
    afterGroupedColumns = groupByModel.getGroupByColumnIndexes();
    assertTrue(afterGroupedColumns.contains(columnIndex));

    // add a new CAT
    data.add(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA));
    view.dataChanged(data);

    // check that not grouped anymore, since the column does not exist
    assertEquals(-1, columnPropertyAccessor.getColumnIndex("unique"));
    afterGroupedColumns = groupByModel.getGroupByColumnIndexes();
    assertTrue(afterGroupedColumns.isEmpty());

    // reset the view for the next test
    ViewHelper.resetViews(view);
  }
}