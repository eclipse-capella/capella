/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.model.semantic.SimplifiedCapellaMetadata;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForClassItem;
import org.polarsys.capella.core.ui.search.searchfor.item.SearchForItem;
import org.polarsys.kitalpha.ad.viewpoint.coredomain.viewpoint.model.Viewpoint;

public class ClassContentProvider extends AbstractSearchForContentProvider {
  private boolean filterAbstract = true;
  private boolean filterNonSemantics = true;
  protected Map<String, Set<SearchForItem>> elements;

  public ClassContentProvider() {
    elements = new HashMap<>();
    elements.put(CapellaSearchConstants.ModelElements_Key, SearchForItemCache.getInstance().getCapellaClassItems());
    elements.put(CapellaSearchConstants.DiagramElements_Key, SearchForItemCache.getInstance().getDiagramItems());
    // load add-ons
    for (Viewpoint vp : SearchForItemCache.getInstance().getViewpoints()) {
      elements.put(vp.getName(), SearchForItemCache.getInstance().getAddonItems(vp));
    }
  }

  @Override
  public Object[] getElements(Object inputElement) {
    if (inputElement != null) {
      return elements.keySet().stream().filter(this::hasChildren).toArray();
    }
    return new Object[0];
  }

  @Override
  public Object[] getElements() {
    return new Object[0];
  }

  @Override
  public boolean hasChildren(Object element) {
    return getChildren(element).length > 0;
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof String && elements.keySet().contains(parentElement)) {
      return elements.get(parentElement).stream().filter(this::isDisplayed).collect(Collectors.toSet()).toArray();
    }
    return super.getChildren(parentElement);
  }

  @Override
  public Object getParent(Object element) {
    if (element instanceof EClass) {
      if (elements.get(CapellaSearchConstants.ModelElements_Key).contains(element)) {
        return CapellaSearchConstants.ModelElements_Key;
      }
      if (elements.get(CapellaSearchConstants.DiagramElements_Key).contains(element)) {
        return CapellaSearchConstants.DiagramElements_Key;
      }
    }
    return null;
  }

  public void setFilterAbstract(boolean filterAbstract) {
    this.filterAbstract = filterAbstract;
  }

  public void setFilterNonSemantic(boolean filterNonSemantics) {
    this.filterNonSemantics = filterNonSemantics;
  }

  // based on abstract and semantic checks, check if the element is displayed or not
  protected boolean isDisplayed(SearchForItem item) {
    if (item instanceof SearchForClassItem) {
      Object obj = ((SearchForClassItem) item).getObject();
      if (obj instanceof EClass) {
        EClass eclass = (EClass) obj;

        if (filterAbstract && eclass.isAbstract()) {
          return false;
        }

        if (filterNonSemantics && !SimplifiedCapellaMetadata.INSTANCE.isSemantic(eclass)) {
          return false;
        }

        return true;
      }
    }

    return false;
  }
}
