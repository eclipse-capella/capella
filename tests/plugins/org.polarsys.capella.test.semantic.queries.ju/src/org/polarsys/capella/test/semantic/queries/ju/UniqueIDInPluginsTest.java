/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.semantic.queries.ju;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.category.Messages;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class UniqueIDInPluginsTest extends BasicTestCase {

  private static final String CONTENT_PROVIDER_CATEGORY = Messages.getString("CategoryRegistry.ContentProvider"); //$NON-NLS-1$

  /**
   * Check that the id is unique for all the CONTENT_PROVIDER_CATEGORY contribution
   */
  @Override
  public void test() throws Exception {

    List<String> listofCategoryId = new ArrayList<String>();
    List<String> listofSubCategoryId = new ArrayList<String>();

    IConfigurationElement[] categories = ExtensionPointHelper.getConfigurationElements(BrowserActivator.getDefault().getBundle().getSymbolicName(),
        CONTENT_PROVIDER_CATEGORY);

    for (IConfigurationElement categoryConfigurationElement : categories) {

      /**
       * Fill category
       */
      // Retrieve id attribute.
      String categoryId = categoryConfigurationElement.getAttribute(ExtensionPointHelper.ATT_ID);
      listofCategoryId.add(categoryId);

      // Retrieve sub categories id
      IConfigurationElement[] subCategoriesNodes = categoryConfigurationElement
          .getChildren(Messages.getString("CategoryRegistry.SubCategory")); //$NON-NLS-1$
      if (subCategoriesNodes.length > 0) {
        IConfigurationElement[] subCategoryConfigurationElements = subCategoriesNodes[0]
            .getChildren(Messages.getString("CategoryRegistry.Category")); //$NON-NLS-1$
        for (IConfigurationElement subCategoryConfigurationElement : subCategoryConfigurationElements) {
          // retrieve the id attribute and add it in the category.
          listofSubCategoryId.add(subCategoryConfigurationElement.getAttribute(ExtensionPointHelper.ATT_ID));
        }
      }
    }

    // Find Duplicate Element in the listofCategoryId
    checkDuplicatedElementInList(listofCategoryId);

    // Find Duplicate Element in the listofSubCategoryId
    checkDuplicatedElementInList(listofSubCategoryId);
  }

  /**
   * Check if there's duplicated element in a given list
   * 
   * @param list
   */
  private void checkDuplicatedElementInList(List<String> list) {
    Set<String> set = new HashSet<String>(list);
    boolean duplicatedDetected = hasDuplicatedElement(list, set);
    if (duplicatedDetected) {
      for (String element : set) {
        // remove the non duplicate element from listofCategoryId
        list.remove(element);
      }
    }
    assertFalse(MessageFormat.format(org.polarsys.capella.test.semantic.queries.ju.Messages.duplicateIDDetected,
        list.toString()), duplicatedDetected);
  }

  /**
   * Check that there's no duplicate element in the list
   * 
   * @param list
   *          a list with potential duplicate element
   * @param set
   *          a set from the list
   * @return true if there's duplicate element in the list
   */
  private boolean hasDuplicatedElement(List<String> list, Set<String> set) {
    return set.size() < list.size();
  }

}
