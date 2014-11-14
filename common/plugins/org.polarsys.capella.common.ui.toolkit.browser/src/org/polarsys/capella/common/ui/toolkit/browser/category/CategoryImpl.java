/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
  /**
   * Qualified Name of type for which the category is enabled.
   */
  protected String qualifiedName = null;
  
  /**
   * This list contains ids of sub categories of this category.
   */
  protected List<String> subCategoriesIds = null;
  
  /**
   * Query associated to this category.
   */
  protected Object categoryQuery = null;
  
  /**
   * Item Queries associated to the upcoming content of this category.
   */
  protected List<Object> itemQueries = null;
  
  /**
   * Category ID.
   */
  protected String id = null;
  
  /**
   * Category Name.
   */
  protected String name = null;
  
  /**
   * If true, means category is a direct child of the root element.
   */
  protected boolean isTopLevel = false;

  /**
   * Item Queries of the category.
   */
  protected HashSet<Object> itemQueriesHashSet = null;

  /**
   * Constructor.
   */
  public CategoryImpl() {
    subCategoriesIds = new ArrayList<String>(0);
    itemQueries = new ArrayList<Object>(0);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#isAvailableForType(java.lang.Object)
   */
  public boolean isAvailableForType(Object element_p) {
    return TypeHelper.getInstance().isInstanceOf(element_p, qualifiedName);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setId(java.lang.String)
   */
  public void setId(String id_p) {
    id = id_p;
  }

  /**
   * Add an id to the sub categories id list.
   * @param id_p
   */
  public void addSubCategoryId(String id_p) {
    if (id_p != null && !id_p.isEmpty())
      subCategoriesIds.add(id_p);
  }

  /**
   * Add a category
   */
  public void setQuery(Object query_p) {
    categoryQuery = query_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setTypeFullyQualifiedName(java.lang.String)
   */
  public void setTypeFullyQualifiedName(String element_p) {
    qualifiedName = element_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#getSubCategoryIds()
   */
  public List<String> getSubCategoryIds() {
    return subCategoriesIds;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#compute()
   */
  public List<Object> compute(Object currentElement_p) {
    return QueryAdapter.getInstance().compute(currentElement_p, categoryQuery);
  }

  /**
   * @param name_p the name to set
   */
  public void setName(String name_p) {
    name = name_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setIsTopLevel(boolean)
   */
  public void setIsTopLevel(boolean isTopLevel_p) {
    // if redefined by different extensions, return (A or... Z)
    isTopLevel |= isTopLevel_p;
  }

  /**
   * Return true if Category is root category.
   * @return
   */
  public boolean isTopLevel() {
    return isTopLevel;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#getName()
   */
  public String getName() {
    return name;
  }

  /**
   * @return the itemQueries
   */
  public List<Object> getItemQueries() {
    return itemQueries;
  }

  /**
   * @param itemQueries_p the itemQueries to set
   */
  public void addItemQuery(Object query) {
    if (query != null)
      itemQueries.add(query);
  }

}
