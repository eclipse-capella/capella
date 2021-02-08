/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * A cache of icons for DEdge representing Physical Link or Functional Exchange in case of there are multiple Physcial
 * Paths or Functional Chains displayed. This cache is only reset on the beforeRefresh of diagram but not in postRefresh
 * because on diagram opening, icons are initialized after diagram refresh (see method
 * {@link org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl#initializeGraphicalViewer
 * initializeGraphicalViewer})
 */
public class DEdgeIconCache {

  private static DEdgeIconCache instance;

  public static DEdgeIconCache getInstance() {
    if (instance == null) {
      instance = new DEdgeIconCache();
    }
    return instance;
  }

  private Map<DEdge, Image> edge2Icon = new HashMap<>();

  private DEdgeIconCache() {
  }

  public Image setIcon(DEdge plEdge, List<RGBValues> colors) {
    return edge2Icon.computeIfAbsent(plEdge, k -> PieIconCache.getInstance().getIcon(colors));
  }
  
  public Image getIcon(DEdge plEdge) {
    return edge2Icon.get(plEdge);
  }

  public void reset() {
    edge2Icon.clear();
  }
  
  public void removeIcon(DEdge dEdge) {
    edge2Icon.remove(dEdge);
  }
}
