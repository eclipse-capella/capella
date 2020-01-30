/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.browser.category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.polarsys.capella.common.ui.toolkit.browser.internal.TypeHelper;
import org.polarsys.capella.common.ui.toolkit.browser.query.QueryAdapter;

/**
 * Category implementation. POJO representing an entry in extension content.provider.category
 */
public class CategoryImpl implements ICategory {

  protected String id;

  protected String name;

  /**
   * Qualified Name of type for which the category is enabled.
   */
  protected String typeQualifiedName;

  protected List<String> subCategoriesIds;

  protected Object categoryQuery;
  
  protected HashSet<Object> itemQueriesHashSet;

  /**
   * Item Queries associated to the upcoming content of this category.
   */
  protected List<Object> itemQueries;

  /**
   * If true, means category is a direct child of the root element.
   */
  protected boolean isTopLevel;

  /**
   * If true, means the category is used in the 'Show Related Elements' menu.
   */
  protected boolean isUsedInShowRelated;  

  public CategoryImpl() {
    subCategoriesIds = new ArrayList<>();
    itemQueries = new ArrayList<>();
  }

  @Override
  public boolean isAvailableForType(Object toTestTypeQualifiedName) {
    return TypeHelper.getInstance().isInstanceOf(toTestTypeQualifiedName, typeQualifiedName);
  }

  @Override
  public void setId(String pId) {
    this.id = pId;
  }

  @Override
  public void addSubCategoryId(String pId) {
    if (pId != null && !pId.isEmpty())
      subCategoriesIds.add(pId);
  }

  @Override
  public void setQuery(Object query) {
    categoryQuery = query;
  }

  @Override
  public void setTypeFullyQualifiedName(String typeQualifiedName) {
    this.typeQualifiedName = typeQualifiedName;
  }

  @Override
  public List<String> getSubCategoryIds() {
    return subCategoriesIds;
  }

  @Override
  public List<Object> compute(Object currentElement) {
    return QueryAdapter.getInstance().compute(currentElement, categoryQuery);
  }

  @Override
  public void setName(String pName) {
    name = pName;
  }

  @Override
  public void setIsTopLevel(boolean isTopLevel) {
    // if redefined by different extensions, return (A or... Z)
    this.isTopLevel |= isTopLevel;
  }

  @Override
  public boolean isTopLevel() {
    return isTopLevel;
  }

  @Override
  public void setIsUsedInShowRelated(boolean usedInShowRelated) {
    this.isUsedInShowRelated = usedInShowRelated;
  }

  @Override
  public boolean isUsedInShowRelated() {
    return isUsedInShowRelated;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Object> getItemQueries() {
    return itemQueries;
  }

  @Override
  public void addItemQuery(Object query) {
    if (query != null)
      itemQueries.add(query);
  }

  @Override
  public String getCategoryId() {
    return this.id;
  }

}
