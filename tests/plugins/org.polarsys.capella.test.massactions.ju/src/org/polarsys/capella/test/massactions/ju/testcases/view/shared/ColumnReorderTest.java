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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.reorder.command.ColumnReorderEndCommand;
import org.eclipse.nebula.widgets.nattable.reorder.command.ColumnReorderStartCommand;
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
public class ColumnReorderTest extends AbstractCapellaMATestCase {

  @Override
  public void performTest() throws Exception {
    MEView editionView = ViewHelper.getActiveMEView();
    MVView visualizationView = ViewHelper.getActiveMVView();

    testColumnReorder(editionView);
    testColumnReorder(visualizationView);
  }

  /**
   * This method tests the action of reordering columns. It ensures that the SAME EXACT reordered columns are preserved
   * when a new element of the same type as the existing ones is added to the view. And that the reordered columns are
   * reset a new element of a new type is added.
   * 
   * @param view
   *          the view that is tested.
   */
  private void testColumnReorder(MAView view) {

    List<EObject> data = new ArrayList<>();
    data.add(getObject(SF_ENTERTAIN_WITH_IFE_SYSTEM));
    view.dataChanged(data);

    // columns to be tested
    List<String> columnNamesToReorder = Arrays.asList("name", "visibleInDoc", "ordered", "unique");

    // initialization
    IMAGridLayer gridLayer = LayerHelper.extractGridLayer(view);
    IMABodyLayer bodyLayer = gridLayer.getBodyLayer();
    IMAColumnPropertyAccessor columnPropertyAccessor = bodyLayer.getColumnPropertyAccessor();
    ColumnHideShowLayer columnHideShowLayer = bodyLayer.getColumnHideShowLayer();
    ColumnReorderLayer columnReorderLayer = bodyLayer.getColumnReorderLayer();

    // the internal representation of reordered columns
    List<Integer> beforeIndexOrder = Collections.emptyList();
    List<Integer> afterIndexOrder = Collections.emptyList();

    List<Integer> expectedIndexOrder = IntStream.range(0, columnPropertyAccessor.getColumnCount()).boxed()
        .collect(Collectors.toList());
    beforeIndexOrder = new ArrayList<>(columnReorderLayer.getColumnIndexOrder());
    assertEquals(expectedIndexOrder, beforeIndexOrder);

    for (String columnName : columnNamesToReorder) {
      int columnIndex = columnPropertyAccessor.getColumnIndex(columnName);

      // column exists
      if (columnIndex != -1) {
        int columnPosition = columnHideShowLayer.getColumnPositionByIndex(columnIndex);
        // reorder at start
        int newColumnPosition = 0;
        // reorder the column
        columnReorderLayer.doCommand(new ColumnReorderStartCommand(columnReorderLayer, columnPosition));
        // always at the end
        columnReorderLayer.doCommand(new ColumnReorderEndCommand(columnReorderLayer, newColumnPosition));
      }
    }

    afterIndexOrder = new ArrayList<>(columnReorderLayer.getColumnIndexOrder());
    assertFalse(afterIndexOrder.isEmpty());

    // check that the reorder has been performed
    assertNotSame(afterIndexOrder, beforeIndexOrder);

    // add a SF and check that we have the same order
    beforeIndexOrder = new ArrayList<>(columnReorderLayer.getColumnIndexOrder());
    data.add(getObject(SF_PERFORM_CABIN_MANAGEMENT_ACTIVITIES));
    view.dataChanged(data);
    afterIndexOrder = new ArrayList<>(columnReorderLayer.getColumnIndexOrder());

    assertEquals(beforeIndexOrder, afterIndexOrder);

    // save current reordered elements order
    beforeIndexOrder = new ArrayList<>(afterIndexOrder);
    
    // add a CAT and check that the order is kept
    data.add(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA));
    view.dataChanged(data);

    final List<Integer> finalAfterIndexOrder = new ArrayList<>(columnReorderLayer.getColumnIndexOrder());
    
    // we expect the new indexOrder to be a subset of the old indexOrder with the elements in the same order
    List<Integer> collected = beforeIndexOrder.stream().filter(index -> finalAfterIndexOrder.contains(index)).collect(Collectors.toList());
    assertEquals(collected, finalAfterIndexOrder);
    
    ViewHelper.resetViews(view);
  }

}
