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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;

/**
 * Category Registry. Responsibility of initialize and cache all categories on
 * loading. Gathering categories in session.
 */
public class CategoryRegistry {
	/**
	 * Singleton.
	 */
	protected static CategoryRegistry _categoryRegistry = null;
	private static final String CONTENT_PROVIDER_CATEGORY = Messages.getString("CategoryRegistry.ContentProvider"); //$NON-NLS-1$
	/**
	 * Cache of category
	 */
	protected List<ICategory> categoriesCache = new ArrayList<ICategory>(0);
	/**
	 * CategoryMap<Category Id, Category>
	 */
	protected HashMap<String, ICategory> currentElementRegistry = null;
	protected HashMap<String, ICategory> diagramElementRegistry = null;

	protected HashMap<String, ICategory> referencedElementRegistry = null;

	protected HashMap<String, ICategory> referencingElementRegistry = null;

	/**
	 * Singleton constructor.
	 */
	private CategoryRegistry() {
		initRegister();
	}

	/**
	 * @param viewerId
	 * @param currentElement
	 * @return
	 */
	public Set<ICategory> gatherCategories(String viewerId, EObject currentElement) {
		Set<ICategory> result = gatherCategoriesInternal(currentElement, getRegistry(viewerId));
		return result;
	}

	/**
	 * @param currentElement
	 * @param viewerId
	 * @param elementRegistry
	 * @return
	 */
	private Set<ICategory> gatherCategoriesInternal(Object element, HashMap<String, ICategory> elementRegistry) {
		Set<ICategory> categories = new HashSet<ICategory>(0);
		Set<Entry<String, ICategory>> entrySet = elementRegistry.entrySet();
		for (Entry<String, ICategory> entry : entrySet) {
			ICategory category = entry.getValue();
			// type matching.
			if (category.isAvailableForType(element)) {
				// top level category.
				if (category.isTopLevel()) {
					categories.add(category);
				}
			}
		}
		return categories;
	}

	/**
	 * @param viewerId
	 * @param category
	 * @return
	 */
	public List<ICategory> gatherSubCategories(String viewerId, EObject currentElement, ICategory category) {
		List<ICategory> result = gatherSubCategoriesInternal(currentElement, category, getRegistry(viewerId));
		return result;
	}

	/**
	 * @param category
	 * @param elementRegistry
	 * @param categories
	 * @param category
	 */
	private List<ICategory> gatherSubCategoriesInternal(Object element, ICategory category,
			HashMap<String, ICategory> elementRegistry) {
		List<ICategory> subCategories = new ArrayList<ICategory>(0);
		for (String subCategoryId : category.getSubCategoryIds()) {
			ICategory subCategory = elementRegistry.get(subCategoryId);
			if ((subCategory != null) && subCategory.isAvailableForType(element)) {
				subCategories.add(subCategory);
			}
		}
		return subCategories;
	}

	protected HashMap<String, ICategory> getRegistry(String viewerId) {
		if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_CURRENT_CP)) {
			return currentElementRegistry;
		} else if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCED_CP)) {
			return referencedElementRegistry;
		} else if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCING_CP)) {
			return referencingElementRegistry;
		} else {// ID_DIAGRAM_CP Statement.
			return diagramElementRegistry;
		}
	}

	/**
	 * Initialize Register. One shot initialization. Spread the categories among
	 * right register.
	 */
	private void initRegister() {
		currentElementRegistry = new HashMap<String, ICategory>(0);
		referencedElementRegistry = new HashMap<String, ICategory>(0);
		referencingElementRegistry = new HashMap<String, ICategory>(0);
		diagramElementRegistry = new HashMap<String, ICategory>(0);

		IConfigurationElement[] categories = org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper
				.getConfigurationElements(BrowserActivator.PLUGIN_ID, CONTENT_PROVIDER_CATEGORY);

		for (IConfigurationElement categoryConfigurationElement : categories) {
			// Create simple instance of category
			ICategory category = new CategoryImpl();

			/**
			 * Fill category
			 */
			// Retrieve id attribute.
			String categoryId = categoryConfigurationElement
					.getAttribute(org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_ID);
			category.setId(categoryId);

			// Retrieve name attribute.
			String categoryName = categoryConfigurationElement
					.getAttribute(org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_NAME);
			category.setName(categoryName);

			// Retrieve isTopLevel attribute.
			String isTopLevelCategory = categoryConfigurationElement
					.getAttribute(Messages.getString("CategoryRegistry.TopLevel")); //$NON-NLS-1$
			if ((isTopLevelCategory != null) && (isTopLevelCategory != "")) { //$NON-NLS-1$
				category.setIsTopLevel(Boolean.parseBoolean(isTopLevelCategory));
			} else {
				category.setIsTopLevel(false);
			}

			// Retrieve sub categories ids.
			IConfigurationElement[] subCategoriesNodes = categoryConfigurationElement
					.getChildren(Messages.getString("CategoryRegistry.SubCategory")); //$NON-NLS-1$
			if (subCategoriesNodes.length > 0) {
				IConfigurationElement[] subCategoryConfigurationElements = subCategoriesNodes[0]
						.getChildren(Messages.getString("CategoryRegistry.Category")); //$NON-NLS-1$
				for (IConfigurationElement subCategoryConfigurationElement : subCategoryConfigurationElements) {
					// retrieve the id attribute and add it in the category.
					category.addSubCategoryId(subCategoryConfigurationElement.getAttribute(
							org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_ID));
				}
			}

			// Retrieve item queries
			IConfigurationElement[] itemQueriesNode = categoryConfigurationElement
					.getChildren(Messages.getString("ItemQueries")); //$NON-NLS-1$
			if (itemQueriesNode.length > 0) {
				IConfigurationElement[] itemQueries = itemQueriesNode[0].getChildren(Messages.getString("basicQuery")); //$NON-NLS-1$
				List<IConfigurationElement> basicQueryCollection = Arrays.asList(itemQueries);
				List<IConfigurationElement> navigationQueryCollection = Arrays
						.asList(itemQueriesNode[0].getChildren(Messages.getString("CategoryRegistry.NavigationQuery"))); //$NON-NLS-1$
				basicQueryCollection.addAll(navigationQueryCollection);
				itemQueries = (IConfigurationElement[]) basicQueryCollection.toArray();
				for (IConfigurationElement itemQueryConfigurationElement : itemQueries) {
					Object queryInstance = org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper
							.createInstance(itemQueryConfigurationElement,
									org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
					category.addItemQuery(queryInstance);
				}
			}

			// Retrieve category queries
			IConfigurationElement[] queryConfigurationElements = categoryConfigurationElement
					.getChildren(Messages.getString("CategoryRegistry.CategoryQuery")); //$NON-NLS-1$
			if (queryConfigurationElements.length > 0) {
				Object query = null;
				IConfigurationElement queryConfigurationElement = queryConfigurationElements[0];
				IConfigurationElement[] categoryQueries = queryConfigurationElement
						.getChildren(Messages.getString("basicQuery")); //$NON-NLS-1$
				if (categoryQueries.length == 0) {
					categoryQueries = queryConfigurationElement
							.getChildren(Messages.getString("CategoryRegistry.NavigationQuery")); //$NON-NLS-1$
				}

				if (categoryQueries.length > 0) {
					query = org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.createInstance(
							categoryQueries[0],
							org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
				}
				category.setQuery(query);
			}

			// Retrieve type.
			IConfigurationElement[] typeConfigurationElement = categoryConfigurationElement
					.getChildren(Messages.getString("CategoryRegistry.AvailableForType")); //$NON-NLS-1$
			if (typeConfigurationElement.length > 0) {
				String qualifiedClassName = typeConfigurationElement[0]
						.getAttribute(org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
				category.setTypeFullyQualifiedName(qualifiedClassName);
			}

			// Retrieve target browser id : a category is specific to a browser.
			IConfigurationElement[] targetBrowserIdNode = categoryConfigurationElement
					.getChildren(Messages.getString("CategoryRegistry.TargetBrowserId")); //$NON-NLS-1$
			if (targetBrowserIdNode.length > 0) {
				IConfigurationElement browserIdConfigurationElement = targetBrowserIdNode[0];
				// Register the category in the proper cache.
				String id = browserIdConfigurationElement
						.getAttribute(org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_ID);
				if (id.equalsIgnoreCase(IBrowserContentProvider.ID_CURRENT_CP)) {
					// index categories by ids.
					currentElementRegistry.put(categoryId, category);
				} else if (id.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCING_CP)) {
					referencingElementRegistry.put(categoryId, category);
				} else if (id.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCED_CP)) {
					referencedElementRegistry.put(categoryId, category);
				} else {
					// ID_DIAGRAM_CP Statement.
					diagramElementRegistry.put(categoryId, category);
				}
			}
		}
	}

	/**
	 * From a category identifier, request the registry to find the matching
	 * category object if exists
	 * 
	 * @param categoryId
	 *            the category identifier
	 * @return the category object if found, null otherwise
	 */
	public ICategory getCategory(String categoryId) {
		ICategory category = null;

		// There are three maps in the semantic browser. Each corresponds to a
		// particular view. Since the categories are sorted by view, we need to
		// check in all maps where our category is.
		if (getRegistry(IBrowserContentProvider.ID_REFERENCED_CP).containsKey(categoryId)) {
			category = getRegistry(IBrowserContentProvider.ID_REFERENCED_CP).get(categoryId);

		} else if (getRegistry(IBrowserContentProvider.ID_REFERENCING_CP).containsKey(categoryId)) {
			category = getRegistry(IBrowserContentProvider.ID_REFERENCING_CP).get(categoryId);

		} else if (getRegistry(IBrowserContentProvider.ID_CURRENT_CP).containsKey(categoryId)) {
			category = getRegistry(IBrowserContentProvider.ID_CURRENT_CP).get(categoryId);
		}

		return category;
	}

	/**
	 * Singleton accessor.
	 * 
	 * @return
	 */
	public static CategoryRegistry getInstance() {
		if (_categoryRegistry == null) {
			_categoryRegistry = new CategoryRegistry();
		}
		return _categoryRegistry;
	}
}
