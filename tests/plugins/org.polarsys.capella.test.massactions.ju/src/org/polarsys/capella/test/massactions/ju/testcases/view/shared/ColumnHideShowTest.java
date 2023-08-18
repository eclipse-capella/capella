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
package org.polarsys.capella.test.massactions.ju.testcases.view.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.hideshow.command.ColumnHideCommand;
import org.eclipse.nebula.widgets.nattable.hideshow.command.MultiColumnShowCommand;
import org.polarsys.capella.test.massactions.ju.helpers.LayerHelper;
import org.polarsys.capella.test.massactions.ju.helpers.ViewHelper;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;
import org.polarsys.kitalpha.massactions.core.data.accessor.IMAColumnPropertyAccessor;
import org.polarsys.kitalpha.massactions.core.table.layer.body.IMABodyLayer;
import org.polarsys.kitalpha.massactions.core.table.layer.grid.IMAGridLayer;
import org.polarsys.kitalpha.massactions.edit.MEView;
import org.polarsys.kitalpha.massactions.shared.view.MAView;
import org.polarsys.kitalpha.massactions.visualize.MVView;

/**
 * 
 * @author Sandu Postaru
 *
 */
public class ColumnHideShowTest extends AbstractCapellaMATestCase {

  @Override
  public void performTest() throws Exception {

    MEView editionView = ViewHelper.getActiveMEView();
    MVView visualizationView = ViewHelper.getActiveMVView();

    testColumnHideShow(editionView);
    testColumnHideShow(visualizationView);
  }

  /**
   * This method tests the action of hiding columns. It ensures that the SAME EXACT hidden columns are preserved when a
   * new element of the same type as the existing ones is added to the view. And that ONLY THE COMMON hidden columns are
   * preserved when a new element of a new type is added.
   * 
   * @param view
   *          the view that is tested.
   */
  public void testColumnHideShow(MAView view) {

    List<EObject> data = new ArrayList<>();
    data.add(getObject(SF_ENTERTAIN_WITH_IFE_SYSTEM));
    view.dataChanged(data);

    // columns to be tested
    List<String> columnNamesToHide = Arrays.asList("name", "visibleInDoc", "ordered", "unique");

    // initialization
    IMAGridLayer gridLayer = LayerHelper.extractGridLayer(view);
    IMABodyLayer bodyLayer = gridLayer.getBodyLayer();
    IMAColumnPropertyAccessor columnPropertyAccessor = bodyLayer.getColumnPropertyAccessor();
    ColumnHideShowLayer columnHideShowLayer = bodyLayer.getColumnHideShowLayer();

    // the internal representation of hidden columns
    Set<Integer> beforeHiddenColumns = Collections.emptySet();
    Set<Integer> afterHiddenColumns = Collections.emptySet();

    // hide the columns to be tested
    for (String columnName : columnNamesToHide) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists
      if (columnIndex != -1) {
        int columnPosition = columnHideShowLayer.getColumnPositionByIndex(columnIndex);

        // column is not hidden
        assertFalse(columnHideShowLayer.isColumnIndexHidden(columnIndex));

        // hide the column
        columnHideShowLayer.doCommand(new ColumnHideCommand(columnHideShowLayer, columnPosition));

        // check that it's hidden
        assertTrue(columnHideShowLayer.isColumnIndexHidden(columnIndex));
      }
    }

    // add another system function and check that the same columns are
    // hidden
    beforeHiddenColumns = new HashSet<>(columnHideShowLayer.getHiddenColumnIndexes());
    data.add(getObject(SF_PERFORM_CABIN_MANAGEMENT_ACTIVITIES));
    view.dataChanged(data);
    afterHiddenColumns = new HashSet<>(columnHideShowLayer.getHiddenColumnIndexes());

    assertEquals(beforeHiddenColumns, afterHiddenColumns);

    // add a category and check that only the common hidden columns (name
    // and visibleInDoc) are hidden
    data.add(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA));
    view.dataChanged(data);

    beforeHiddenColumns = new HashSet<>(columnHideShowLayer.getHiddenColumnIndexes());

    // check that all the common columns are hidden
    for (String columnName : columnNamesToHide) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists then it should be hidden
      if (columnIndex != -1) {
        assertTrue(columnHideShowLayer.isColumnIndexHidden(columnIndex));
      }
    }

    // restore the hidden columns and test that they are hidden no more
    for (String columnName : columnNamesToHide) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists test that its hidden and restore it
      if (columnIndex != -1) {
        assertTrue(columnHideShowLayer.isColumnIndexHidden(columnIndex));
        columnHideShowLayer.doCommand(new MultiColumnShowCommand(Arrays.asList(columnIndex)));
        assertFalse(columnHideShowLayer.isColumnIndexHidden(columnIndex));
      }
    }

    // reset the view for the next test
    ViewHelper.resetViews(view);
  }
}