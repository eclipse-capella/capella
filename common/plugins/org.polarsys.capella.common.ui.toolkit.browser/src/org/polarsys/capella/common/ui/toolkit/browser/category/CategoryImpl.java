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
  public boolean isAvailableForType(Object element) {
    return TypeHelper.getInstance().isInstanceOf(element, qualifiedName);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setId(java.lang.String)
   */
  public void setId(String pId) {
    id = pId;
  }

  /**
   * Add an id to the sub categories id list.
   * @param id 
   */
  public void addSubCategoryId(String id) {
    if (id != null && !id.isEmpty())
      subCategoriesIds.add(id);
  }

  /**
   * Add a category
   */
  public void setQuery(Object query) {
    categoryQuery = query;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setTypeFullyQualifiedName(java.lang.String)
   */
  public void setTypeFullyQualifiedName(String element) {
    qualifiedName = element;
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
  public List<Object> compute(Object currentElement) {
    return QueryAdapter.getInstance().compute(currentElement, categoryQuery);
  }

  /**
   * @param name  the name to set
   */
  public void setName(String pName) {
    name = pName;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.category.ICategory#setIsTopLevel(boolean)
   */
  public void setIsTopLevel(boolean isTopLevel) {
    // if redefined by different extensions, return (A or... Z)
    isTopLevel |= isTopLevel;
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
   * @param itemQueries  the itemQueries to set
   */
  public void addItemQuery(Object query) {
    if (query != null)
      itemQueries.add(query);
  }

  /**
   * @return the category identifier
   */
  @Override
  public String getCategoryId() {
	return this.id;
  }
}
