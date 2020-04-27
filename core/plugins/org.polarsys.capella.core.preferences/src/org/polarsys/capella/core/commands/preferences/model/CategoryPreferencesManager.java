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
package org.polarsys.capella.core.commands.preferences.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.core.runtime.IConfigurationElement;

import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesMessages;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;
import org.polarsys.capella.core.commands.preferences.util.UserProfileDescriptor;
import org.polarsys.capella.core.commands.preferences.util.XmlPreferencesConfig;

/**
 * <p>
 * Central point by which clients discover the available
 * {@link CategoryPreferences categories} and {@link IItemDescriptor items}
 * in the system.  The <code>CategoryManager</code> is responsible for
 * loading and maintaining all <code>Category</code> instances.
 * </p>
 * <p>
 * This class is intended to be used by clients of the validation framework.
 * </p>
 * 
 */
public class CategoryPreferencesManager { 
	
	private Set<String> projectsNaturesId ;
	

	/*
	 * 
	 */
	static final String DEFAULT_CATEGORY_NAME = CustomPreferencesMessages.category_default_name;
	/*
	 * 
	 */
	static final String DEFAULT_CATEGORY_DESCRIPTION = CustomPreferencesMessages.category_default_desc;
	/*
	 * 
	 */
	private static CategoryPreferencesManager INSTANCE ;
	/*
	 * 
	 */
	private final CategoryPreferences globalCategory = CategoryPreferences.GLOBAL_NAMESPACE;

	
	
	/**
	 * I cannot be instantiated by clients.
	 */
	private CategoryPreferencesManager() {
		super();
		projectsNaturesId = new HashSet<String>(0);
	}

	/**
	 * Obtains the singleton instance of this class.
	 * 
	 * @return the category manager instance
	 */
	public static CategoryPreferencesManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CategoryPreferencesManager();
		}
		return INSTANCE;
	}
	
	/**
	 * The top-level categories.
	 * 
	 * @return an unmodifiable set of {@link CategoryPreferences}s, sorted by their
	 *     names
	 */
	public SortedSet<CategoryPreferences> getFirstCategoriesLevel() {
		return globalCategory.getChildren();
	}
	
	/**
	 * Retrieves the default category which contains all items that are
	 * not explicitly categorized.
	 * 
	 * @return the default category
	 */
	public CategoryPreferences getDefaultCategory() {
		return CategoryPreferences.DEFAULT_CATEGORY;
	}

	/**
	 * Obtains the category that has the specified absolute <code>path</code>.
	 * If this category does not yet exist, it is implicitly created.
	 * 
	 * @param path the absolute path of the category
	 * @return the specified category (never <code>null</code>)
	 */
	public CategoryPreferences getCategory(String path) {
		return globalCategory.getDescendent(path, true);
	}

	/**
	 * Obtains the category that has the specified <code>path</code> relative
	 * to the specified <code>parent</code> category.  If this category does
	 * not yet exist, it is implicitly created.
	 * 
	 * @param parent the parent category, or <code>null</code> to indicate that
	 *    the path is absolute
	 * @param path the path relative to the <code>parent</code>, or the absolute
	 *    path if <code>parent == null</code>
	 * @return the specified category (never <code>null</code>)
	 */
	public CategoryPreferences getCategory(CategoryPreferences parent, String path) {
		if (parent == null) {
			return globalCategory.getDescendent(path, true);
		}
		return parent.getDescendent(path, true);
	}

	/**
	 * Finds the category that has the specified absolute <code>path</code>.
	 * Unlike the {@link #getCategory(String)} method, this method will not
	 * Implicitly create the sought-after category.
	 * 
	 * @param path the absolute path of the category
	 * @return the specified category or <code>null</code> if it is not found
	 */
	public CategoryPreferences findCategory(String path) {
		return globalCategory.getDescendent(path, false);
	}

	/**
	 * Finds the category that has the specified <code>path</code> relative
	 * to the specified <code>parent</code> category.  Unlike the
	 * {@link #getCategory(CategoryPreferences, String)} method, this method will not
	 * Implicitly create the sought-after category.
	 * 
	 * @param parent the parent category, or <code>null</code> to indicate that
	 *    the path is absolute
	 * @param path the path relative to the <code>parent</code>, or the absolute
	 *    path if <code>parent == null</code>
	 * @return the specified category or <code>null</code> if it is not found
	 */
	public CategoryPreferences findCategory(CategoryPreferences parent, String path) {
		if (parent == null) {
			return globalCategory.getDescendent(path, false);
		}
		return parent.getDescendent(path, false);
	}
	
	/**
	 * Removes the specified <code>category</code> from the category manager.
	 * <p>
	 * <b>Use extreme caution</b> when invoking this method.  This method
	 * recursively removes all descendant categories and their constraint from
	 * the category manager.  The items will still operate as they did
	 * previously, but the user will not see them in the UI or be able to
	 * control their enablement.  In general, you should only remove categories
	 * that you have added and whose items you control.
	 * </p>
	 * 
	 * @param category the category to remove
	 * 
	 * @see #removeCategory(String)
	 */
	public void removeCategory(CategoryPreferences category) {
		// we cannot remove the root category
		if (category.getParent() == null) {
			throw new IllegalArgumentException();
		}
		
		// first, recursively remove the child categories
		Collection<CategoryPreferences> children = category.getChildren();
		CategoryPreferences[] childrenArray = children.toArray(new CategoryPreferences[children.size()]);
		for (CategoryPreferences child : childrenArray) {
			removeCategory(child);
		}
		
		// purge the items from this category, so that they know they
		//   are no longer in it
		Collection<IItemDescriptor> items = category.getItems();
		IItemDescriptor[] itemsArray = items.toArray(
			new IItemDescriptor[items.size()]);
		for (IItemDescriptor constraint : itemsArray) {
			category.removeItem(constraint);
		}
		
		// finally, remove the category from its parent
		category.getParent().removeChild(category.getId());
	}
	
	/**
	 * Removes the specified category from the category manager.
	 * <p>
	 * <b>Use extreme caution</b> when invoking this method.  This method
	 * recursively removes all descendant categories and their constraint from
	 * the category manager.  The items will still operate as they did
	 * previously, but the user will not see them in the UI or be able to
	 * control their enablement.  In general, you should only remove categories
	 * that you have added and whose items you control.
	 * </p>
	 * 
	 * @param path the ID {@link CategoryPreferences#getPath() path} of the category to
	 *        remove
	 * 
	 * @see #removeCategory(CategoryPreferences)
	 */
	public void removeCategory(String path) {
		CategoryPreferences category = findCategory(path);
		
		if (category != null) {
			removeCategory(category);
		}
	}

	/**
	 * Initializes the default category which is the category that includes
	 * all items that are not explicitly members of any other category.
	 */
	public void initDefaultCategory() {
		CategoryPreferences.DEFAULT_CATEGORY.setName(DEFAULT_CATEGORY_NAME);
		CategoryPreferences.DEFAULT_CATEGORY.setDescription(DEFAULT_CATEGORY_DESCRIPTION);
	}
	


	
	/**
	 * Loads identifiers of the specified <code>projects</code> nature.
	 * @param parent
	 * @param element
	 */
	public void loadProjectsNature(CategoryPreferences parent, IConfigurationElement element) {
		String id = element.getAttribute(XmlPreferencesConfig.PROJECT_NATURE_ID);
		if (id!=null && !id.isEmpty()) {
			projectsNaturesId.add(id);
		}
		
	  }

	
	/**
	 * Loads subcategories of the specified <code>parent</code> category.
	 * @param parent
	 * @param element
	 */
	public void loadCategories(CategoryPreferences parent, IConfigurationElement element) {
		String description = element.getAttribute(XmlPreferencesConfig.ITEM_DESCRIPTION);
		String name = element.getAttribute(XmlPreferencesConfig.ITEM_NAME);
		
		if ((name != null) && (name.length() > 0)) {
			CategoryPreferences category = getCategory(parent, name);
			category.setName(name);
			category.setDescription(description);
			IConfigurationElement[] subcategories = element.getChildren(XmlPreferencesConfig.ELEMENT_CATEGORY);
			for (IConfigurationElement subCategory : subcategories) {
				// recursively load the child categories, if any
				loadCategories(category, subCategory);
			}
			//Finally resolve items of current category
			try {
				XmlPreferencesConfig.loadCategoryItems(element, category);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} 
	}

	
	/**
	 * Loads User profile of the specified <code>parent</code> preference scope.
	 * @param parent
	 * @param element
	 */
	public void loadUserProfile() {
		new UserProfileDescriptor(XmlPreferencesConfig.USER_PROFILE_MODE_ID , XmlPreferencesConfig.USER_PROFILE_MODE_NAME) ;
	}

	
	
	/**
	 * Obtains all of the mandatory categories.
	 *  
	 * @return the mandatory categories
	 */
	public Collection<CategoryPreferences> getMandatoryCategories() {
		Collection<CategoryPreferences> result = new java.util.ArrayList<CategoryPreferences>();
		
		globalCategory.getMandatoryCategories(result);
		
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Set<String> getProjectsNaturesIds() {
		return projectsNaturesId;
	}
}
