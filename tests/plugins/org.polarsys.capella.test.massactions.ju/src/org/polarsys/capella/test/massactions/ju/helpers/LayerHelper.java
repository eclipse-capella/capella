/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.helpers;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.polarsys.kitalpha.massactions.core.table.IMATable;
import org.polarsys.kitalpha.massactions.core.table.layer.grid.IMAGridLayer;
import org.polarsys.kitalpha.massactions.edit.table.METable;
import org.polarsys.kitalpha.massactions.shared.view.MAView;
import org.polarsys.kitalpha.massactions.visualize.table.MVTable;
import org.polarsys.kitalpha.massactions.visualize.table.layer.groupby.IMVGroupByLayer;

/**
 * A layer helper, providing some utility methods.
 * 
 * @author Sandu Postaru
 *
 */
public class LayerHelper {

  private LayerHelper() {
    // Exists only to defeat instantiation.
  }

  public static NatTable extractNatTable(MAView view) {
    return view.getTable().getNatTable();
  }

  public static IMAGridLayer extractGridLayer(MAView view) {
    IMATable maTable = view.getTable();
    ILayer baseLayer = maTable.getNatTable().getLayer();

    if (maTable instanceof METable) {
      return (IMAGridLayer) baseLayer;
    } else if (maTable instanceof MVTable) {
      IMVGroupByLayer groupByLayer = (IMVGroupByLayer) baseLayer;
      return groupByLayer.getGridLayer();
    } else {
      throw new IllegalArgumentException("Illegal view class " + view.getClass());
    }
  }

  public static IMVGroupByLayer extractGroupByLayer(MAView view) {
    IMATable maTable = view.getTable();
    ILayer baseLayer = maTable.getNatTable().getLayer();

    if (maTable instanceof MVTable) {
      return (IMVGroupByLayer) baseLayer;
    }

    throw new IllegalArgumentException("Illegal view class " + view.getClass());
  }
}
