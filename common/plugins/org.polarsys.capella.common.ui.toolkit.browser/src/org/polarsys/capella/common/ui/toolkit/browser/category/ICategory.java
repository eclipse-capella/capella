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

import java.util.List;


/**
 * Represents a category of elements in the tree viewer dedicated to semantic browsing.
 */
public interface ICategory {
	/**
	 * Return a set of elements resulting from a query.
	 * @param currentElement_p
	 * @return
	 */
	public List<Object> compute(Object currentElement_p);
	
	/**
	 * Id setter.
	 * @param id_p
	 */
	public void setId(String id_p);
	
	/**
	 * Add Sub category id.
	 * @param id_p
	 */
	public void addSubCategoryId(String id_p);
	
	/**
	 * Set Query object. Could be a basic query or a navigation query.
	 * @param query_p
	 */
	public void setQuery(Object query_p);
	
	/**
	 * 
	 * @param element_p
	 */
	public void setTypeFullyQualifiedName(String element_p);

  /**
   * @param currentElement_p
   * @return true if 
   */
  public boolean isAvailableForType(Object currentElement_p);
  
  /**
   * SubCategory Ids getter.
   * @return
   */
  public List<String> getSubCategoryIds();
  
  /**
   * Name setter.
   * @param categoryName_p
   */
  public void setName(String categoryName_p);
  
  /**
   * Name getter.
   * @return
   */
  public String getName();
  
  /**
   * IsTopLevel setter.
   * @param isTopLevel_p
   */
  public void setIsTopLevel(boolean isTopLevel_p);
  
  /**
   * Return true if Category is top level category. 
   * Means the category is a direct child of an element (which is not a category).
   * @return
   */
  public boolean isTopLevel();
  
  /**
   * Get item queries.
   * @return
   */
  public List<Object> getItemQueries();
  
  /**
   * Set item queries.
   * @param queries_p set of queries.
   */
  public void addItemQuery(Object queries_p);
}
