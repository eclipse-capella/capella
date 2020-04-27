/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.browser.category;

import java.util.List;


/**
 * Represents a category of elements in the tree viewer dedicated to semantic browsing.
 */
public interface ICategory {
	/**
	 * Return a set of elements resulting from a query.
	 * @param currentElement 
	 * @return
	 */
	public List<Object> compute(Object currentElement);
	
	/**
	 * Id setter.
	 * @param id 
	 */
	public void setId(String id);
	
	/**
	 * Add Sub category id.
	 * @param id 
	 */
	public void addSubCategoryId(String id);
	
	/**
	 * Set Query object. Could be a basic query or a navigation query.
	 * @param query 
	 */
	public void setQuery(Object query);
	
	/**
	 * 
	 * @param element 
	 */
	public void setTypeFullyQualifiedName(String element);

  /**
   * @param currentElement 
   * @return true if 
   */
  public boolean isAvailableForType(Object currentElement);
  
  /**
   * SubCategory Ids getter.
   * @return
   */
  public List<String> getSubCategoryIds();
  
  /**
   * Name setter.
   * @param categoryName 
   */
  public void setName(String categoryName);
  
  /**
   * Name getter.
   * @return
   */
  public String getName();
  
  /**
   * IsTopLevel setter.
   * @param isTopLevel 
   */
  public void setIsTopLevel(boolean isTopLevel);
  
  /**
   * Return true if Category is top level category. 
   * Means the category is a direct child of an element (which is not a category).
   * @return
   */
  public boolean isTopLevel();
  
  /**
   * usedInShowRelated setter.
   * @param usedInShowRelated 
   */
  public void setIsUsedInShowRelated(boolean usedInShowRelated);
  
  /**
   * @return true if the Category is used in the Show Related Elements menu.
   */
  public boolean isUsedInShowRelated();
  
  
  /**
   * Get item queries.
   * @return
   */
  public List<Object> getItemQueries();
  
  /**
   * Set item queries.
   * @param queries  set of queries.
   */
  public void addItemQuery(Object queries);
  
  /**
   * @return the category identifier.
   */
  public String getCategoryId();
}
