/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.misc.ExtensionClassDescriptor;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.IBrowserContentProvider;

/**
 * Category Registry. Responsibility of initialize and cache all categories on loading. Gathering categories in session.
 */
public class CategoryRegistry {

  private static final String CONTENT_PROVIDER_CATEGORY = Messages.getString("CategoryRegistry.ContentProvider"); //$NON-NLS-1$

  protected static CategoryRegistry categoryRegistry;

  protected List<ICategory> categoriesCache;

  /**
   * CategoryMap<Category Id, Category>
   */
  protected HashMap<String, ICategory> currentElementRegistry;
  protected HashMap<String, ICategory> diagramElementRegistry;
  protected HashMap<String, ICategory> referencedElementRegistry;
  protected HashMap<String, ICategory> referencingElementRegistry;
  protected HashMap<String, ICategory> relatedElementsRegistry;

  private Collection<ExtensionClassDescriptor> availableForTypeClassDescriptors;

  private CategoryRegistry() {
    this.categoriesCache = new ArrayList<>();

    initRegistry();
  }

  /**
   * Returns the available categories for the current element, that can be displayed in the view.
   * 
   * @param viewerId
   *          the view id.
   * @param currentElement
   *          the current element.
   * @return the available categories for the current element, that can be displayed in the view.
   */
  public Set<ICategory> gatherCategories(String viewerId, EObject currentElement) {
    HashMap<String, ICategory> elementRegistry = getRegistry(viewerId);
    Set<ICategory> categories = new HashSet<>();
    Set<Entry<String, ICategory>> entrySet = elementRegistry.entrySet();
    for (Entry<String, ICategory> entry : entrySet) {
      ICategory category = entry.getValue();
      // top level category and type matching
      if (category.isTopLevel() && category.isAvailableForType(currentElement)) {
        categories.add(category);
      }
    }
    removeOverriddenCategories(categories, currentElement);
    return categories;
  }

  /**
   * Remove categories overridden by at least one another category in the same input set of categories
   * 
   * @param categories
   */
  private void removeOverriddenCategories(Set<ICategory> categories, EObject current) {
    Set<ICategory> overriddenCategories = categories.stream()
        .filter(x -> categories.stream().anyMatch(y -> y != x && y.overrides(x, current))).collect(Collectors.toSet());
    categories.removeAll(overriddenCategories);
  }

  /**
   * Returns all the available categories for the current element.
   * 
   * @param currentElement
   *          the current element.
   * @return all the available categories for the current element.
   */
  public Set<ICategory> gatherCategories(EObject currentElement) {

    Set<ICategory> referencedCategories = gatherCategories(IBrowserContentProvider.ID_REFERENCED_CP, currentElement);
    Set<ICategory> referencingCategories = gatherCategories(IBrowserContentProvider.ID_REFERENCING_CP, currentElement);
    Set<ICategory> currentCategories = gatherCategories(IBrowserContentProvider.ID_CURRENT_CP, currentElement);

    Set<ICategory> categories = new HashSet<>();
    categories.addAll(referencedCategories);
    categories.addAll(referencingCategories);
    categories.addAll(currentCategories);

    return categories;
  }

  /**
   * Returns the available sub-categories of the current category and current element, that can be displayed in the
   * view.
   * 
   * @param viewerId
   *          the viewer id.
   * @param currentElement
   *          the current element.
   * @param category
   *          the category.
   * @return the available sub-categories of the current category and current element, that can be displayed in the
   *         view.
   */
  public List<ICategory> gatherSubCategories(String viewerId, EObject currentElement, ICategory category) {
    HashMap<String, ICategory> elementRegistry = getRegistry(viewerId);
    List<ICategory> subCategories = new ArrayList<ICategory>();
    for (String subCategoryId : category.getSubCategoryIds()) {
      ICategory subCategory = elementRegistry.get(subCategoryId);
      if ((subCategory != null) && subCategory.isAvailableForType(currentElement)) {
        subCategories.add(subCategory);
      }
    }
    return subCategories;
  }

  /**
   * Returns all the related elements categories for the current element.
   * @param currentElement the current element.
   * @return all the related elements categories for the current element.
   */
  public List<ICategory> gatherRelatedElementsCategories(EObject currentElement) {

    return relatedElementsRegistry.values().stream() //
        .filter(category -> category.isTopLevel() && category.isAvailableForType(currentElement)) //
        .collect(Collectors.toList());
  }

  /**
   * Returns the appropriate category registry, for the current viewerId.
   * @param viewerId the viewer id.
   * @return the appropriate category registry, for the current viewerId.
   */
  protected HashMap<String, ICategory> getRegistry(String viewerId) {
    if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_CURRENT_CP)) {
      return currentElementRegistry;
    } else if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCED_CP)) {
      return referencedElementRegistry;
    } else if (viewerId.equalsIgnoreCase(IBrowserContentProvider.ID_REFERENCING_CP)) {
      return referencingElementRegistry;
    } else {
      return diagramElementRegistry;
    }
  }

  /**
   * From a category identifier, request the registry to find the matching category object if exists
   * 
   * @param categoryId
   *          the category identifier
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
   * Initialize Register. One shot initialization. Spread the categories among right register.
   */
  private void initRegistry() {
    currentElementRegistry = new HashMap<>();
    referencedElementRegistry = new HashMap<>();
    referencingElementRegistry = new HashMap<>();
    diagramElementRegistry = new HashMap<>();
    relatedElementsRegistry = new HashMap<>();
    availableForTypeClassDescriptors = new HashSet<>();

    IConfigurationElement[] categories = org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper
        .getConfigurationElements(FrameworkUtil.getBundle(BrowserActivator.class).getSymbolicName(), CONTENT_PROVIDER_CATEGORY);

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
      if (isTopLevelCategory != null) {
        category.setIsTopLevel(Boolean.parseBoolean(isTopLevelCategory));
      } else {
        category.setIsTopLevel(false);
      }

      // Retrieve isusedInShowRelated attribute.
      String usedInShowRelated = categoryConfigurationElement
          .getAttribute(Messages.getString("CategoryRegistry.UsedInShowRelated")); //$NON-NLS-1$

      if (usedInShowRelated != null) {
        boolean isUsedInShowRelated = Boolean.parseBoolean(usedInShowRelated);
        category.setIsUsedInShowRelated(isUsedInShowRelated);

        if (isUsedInShowRelated) {
          relatedElementsRegistry.put(categoryId, category);
        }
      } else {
        category.setIsUsedInShowRelated(false);
      }

      // Retrieve sub categories ids.
      IConfigurationElement[] subCategoriesNodes = categoryConfigurationElement
          .getChildren(Messages.getString("CategoryRegistry.SubCategory")); //$NON-NLS-1$
      if (subCategoriesNodes.length > 0) {
        IConfigurationElement[] subCategoryConfigurationElements = subCategoriesNodes[0]
            .getChildren(Messages.getString("CategoryRegistry.Category")); //$NON-NLS-1$
        for (IConfigurationElement subCategoryConfigurationElement : subCategoryConfigurationElements) {
          // retrieve the id attribute and add it in the category.
          category.addSubCategoryId(subCategoryConfigurationElement
              .getAttribute(org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_ID));
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
        basicQueryCollection.forEach(itemQueryConfigurationElement -> {
          Object queryInstance = org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.createInstance(
              itemQueryConfigurationElement,
              org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
          category.addItemQuery(queryInstance);
        });
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
              categoryQueries[0], org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
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
        availableForTypeClassDescriptors.add(new ExtensionClassDescriptor(typeConfigurationElement[0]));
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
   * This method is added for testing purpose.
   */
  public Collection<ExtensionClassDescriptor> getAvailableForTypeClassDescriptors() {
    return availableForTypeClassDescriptors;
  }

  public static CategoryRegistry getInstance() {
    if (categoryRegistry == null) {
      categoryRegistry = new CategoryRegistry();
    }
    return categoryRegistry;
  }
}
