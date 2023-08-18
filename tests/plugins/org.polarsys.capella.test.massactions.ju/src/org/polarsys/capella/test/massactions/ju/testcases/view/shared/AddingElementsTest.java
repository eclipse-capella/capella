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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.IRowDataProvider;
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
public class AddingElementsTest extends AbstractCapellaMATestCase {

  @Override
  public void performTest() throws Exception {

    MEView editionView = ViewHelper.getActiveMEView();
    MVView visualizationView = ViewHelper.getActiveMVView();

    testAddElementsToView(editionView);
    testAddElementsToView(visualizationView);
  }

  /**
   * This method tests the addition of elements for a view. It ensures that all of the elements are only present one
   * time, and that for elements of the same type the table configuration does not change.
   * 
   * @param view
   *          the view that is tested.
   */
  public void testAddElementsToView(MAView view) {

    List<EObject> data = new ArrayList<>();

    int initialColumnCount = 0;

    // add a first SF element
    data.add(getObject(SF_ENTERTAIN_WITH_IFE_SYSTEM));

    // initialization
    view.dataChanged(data);
    IMAGridLayer gridLayer = LayerHelper.extractGridLayer(view);
    IMABodyLayer bodyLayer = gridLayer.getBodyLayer();
    IMAColumnPropertyAccessor columnPropertyAccessor = bodyLayer.getColumnPropertyAccessor();
    IRowDataProvider<EObject> bodyDataProvider = bodyLayer.getBodyDataProvider();

    // test that a column configuration is present
    initialColumnCount = columnPropertyAccessor.getColumnCount();
    assertTrue(initialColumnCount > 0);

    // test that only one element is currently visible
    assertEquals(1, bodyDataProvider.getRowCount());

    // add the same element and test that the table config did not change
    data.add(getObject(SF_ENTERTAIN_WITH_IFE_SYSTEM));
    view.dataChanged(data);

    assertEquals(initialColumnCount, columnPropertyAccessor.getColumnCount());
    assertEquals(1, bodyDataProvider.getRowCount());

    // add an element of a different type and test that the table config
    // changed
    data.add(getObject(CAT_DISPLAYED_IMPOSED_VIDEO_DATA));
    view.dataChanged(data);

    assertTrue(columnPropertyAccessor.getColumnCount() < initialColumnCount);
    assertEquals(2, bodyDataProvider.getRowCount());

    // reset the views for future tests
    ViewHelper.resetViews(view);
  }

}
