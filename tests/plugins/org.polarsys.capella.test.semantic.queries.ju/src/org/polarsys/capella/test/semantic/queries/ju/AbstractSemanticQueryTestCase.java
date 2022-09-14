/*******************************************************************************
 * Copyright (c) 2016, 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.query.QueryAdapter;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;

/**
 * The purpose of this class is to provide a little framework for easing the test of semantic browser-related queries.
 * 
 * Each query under test shall:
 * <ul>
 * <li>first generate a list of identifiers for the test model the query will used on</li>
 * <li>extend this previously generated class</li>
 * <li>add unimplemented method from this class to provide the category identifier of the query (see in the contribution
 * of the extension point) and the semantic browser view the query is related to (see also in the contribution of the
 * extension point)</li>
 * </ul>
 * 
 * Then, the test can be run using run configuration and JUnit plugin test.
 */
public abstract class AbstractSemanticQueryTestCase extends BasicTestCase {
  /**
   * To be implemented by sub classes.
   * 
   * @return the identifier of the category of the query to test.
   */
  protected abstract String getQueryCategoryIdentifier();

  /**
   * Tests a query.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQuery(String sourceId, String... expectedResult) {
    testQuery(sourceId, Arrays.asList(expectedResult));
  }

  /**
   * Tests a query.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQuery(String sourceId, Collection<String> expectedResult) {
    // First we get the eobject on which to test the query
    IModel model = getTestModel(getRequiredTestModels().iterator().next());
    EObject testObject = EObjectHelper.getObject(model, sourceId);

    Collection<EObject> expectedObjects = EObjectHelper.getObjects(model, expectedResult);

    // Find the category related to the query
    ICategory category = getCategory(getQueryCategoryIdentifier());

    // Category cannot be found in the registry
    if (category == null) {
      assertTrue(NLS.bind("Query {0} doesn't exist", getQueryCategoryIdentifier()), false);
    }

    // Query is incompatible with the type of the object
    if (!category.isAvailableForType(testObject)) {
      assertTrue(NLS.bind("Query {0} is not applicable for {1}", getQueryCategoryIdentifier(),
          EObjectLabelProviderHelper.getText(testObject)), false);
    }

    // Query has been found! We execute it.
    List<Object> objResult = category.compute(testObject);

    // Since we want to check independent of order, we copy all of
    // the elements to Sets and use equals on the resulting Sets:
    Set<Object> set1 = new HashSet<Object>(objResult);
    Set<Object> set2 = new HashSet<Object>(expectedObjects);

    // Final test
    assertTrue(set1.equals(set2));
  }

  /**
   * Tests a query including potential sub queries.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQueryIncludingItemQueries(String sourceId, String... expectedResult) {
    testQueryIncludingItemQueries(sourceId, Arrays.asList(expectedResult));
  }

  /**
   * Tests a query including potential item queries.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQueryIncludingItemQueries(String sourceId, Collection<String> expectedResult) {
    // First we get the eobject on which to test the query
    IModel model = getTestModel(getRequiredTestModels().iterator().next());
    EObject testObject = EObjectHelper.getObject(model, sourceId);

    Collection<EObject> expectedObjects = EObjectHelper.getObjects(model, expectedResult);

    // Find the category related to the query
    ICategory category = getCategory(getQueryCategoryIdentifier());

    // Category cannot be found in the registry
    if (category == null) {
      assertTrue(NLS.bind("Query {0} doesn't exist", getQueryCategoryIdentifier()), false);
    }

    // Query is incompatible with the type of the object
    if (!category.isAvailableForType(testObject)) {
      assertTrue(NLS.bind("Query {0} is not applicable for {1}", getQueryCategoryIdentifier(),
          EObjectLabelProviderHelper.getText(testObject)), false);
    }

    // Query has been found! We execute it.
    List<Object> objResult = category.compute(testObject);

    List<Object> itemQueriesResult = new ArrayList<>();
    // Add item query content
    for (Object query : category.getItemQueries()) {
      for (Object obj : objResult) {
        List<Object> itemQueryResult = QueryAdapter.getInstance().compute(obj, query);
        itemQueriesResult.addAll(itemQueryResult);
      }
    }

    objResult.addAll(itemQueriesResult);
    // Since we want to check independent of order, we copy all of
    // the elements to Sets and use equals on the resulting Sets:
    Set<Object> set1 = new HashSet<Object>(objResult);
    Set<Object> set2 = new HashSet<Object>(expectedObjects);

    // Final test
    assertTrue(set1.equals(set2));
  }

  /**
   * Tests only sub queries of a query.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQueryOnlyItemQueries(String sourceId, String... expectedResult) {
    testQueryOnlyItemQueries(sourceId, Arrays.asList(expectedResult));
  }

  /**
   * Tests only sub queries of a query.
   * 
   * @param sourceId
   *          the element to execute the query on
   * @param expectedResult
   *          the expected result
   */
  protected void testQueryOnlyItemQueries(String sourceId, Collection<String> expectedResult) {
    // First we get the eobject on which to test the query
    IModel model = getTestModel(getRequiredTestModels().iterator().next());
    EObject testObject = EObjectHelper.getObject(model, sourceId);

    Collection<EObject> expectedObjects = EObjectHelper.getObjects(model, expectedResult);

    // Find the category related to the query
    ICategory category = getCategory(getQueryCategoryIdentifier());

    // Category cannot be found in the registry
    if (category == null) {
      assertTrue(NLS.bind("Query {0} doesn't exist", getQueryCategoryIdentifier()), false);
    }

    // Query is incompatible with the type of the object
    if (!category.isAvailableForType(testObject)) {
      assertTrue(NLS.bind("Query {0} is not applicable for {1}", getQueryCategoryIdentifier(),
          EObjectLabelProviderHelper.getText(testObject)), false);
    }

    // Query has been found! We execute it.
    List<Object> objResult = category.compute(testObject);

    List<Object> itemQueriesResult = new ArrayList<>();
    // Add item query content
    for (Object query : category.getItemQueries()) {
      for (Object obj : objResult) {
        List<Object> itemQueryResult = QueryAdapter.getInstance().compute(obj, query);
        itemQueriesResult.addAll(itemQueryResult);
      }
    }
    // Remove the query result
    objResult.clear();
    // Add subqueries result
    objResult.addAll(itemQueriesResult);
    // Since we want to check independent of order, we copy all of
    // the elements to Sets and use equals on the resulting Sets:
    Set<Object> set1 = new HashSet<Object>(objResult);
    Set<Object> set2 = new HashSet<Object>(expectedObjects);

    // Final test
    assertTrue(set1.equals(set2));
  }

  protected void testQueryValue(String sourceId, String... expectedResult) {
    testQuery(sourceId, Arrays.asList(expectedResult));
  }

  protected void testQueryValue(String sourceId, Collection<String> expectedValues) {
    // First we get the eobject on which to test the query
    IModel model = getTestModel(getRequiredTestModels().iterator().next());
    EObject testObject = EObjectHelper.getObject(model, sourceId);

    // Find the category related to the query
    ICategory category = getCategory(getQueryCategoryIdentifier());

    // Category cannot be found in the registry
    if (category == null) {
      assertTrue(NLS.bind("Query {0} doesn't exist", getQueryCategoryIdentifier()), false);
    }

    // Query is incompatible with the type of the object
    if (!category.isAvailableForType(testObject)) {
      assertTrue(NLS.bind("Query {0} is not applicable for {1}", getQueryCategoryIdentifier(),
          EObjectLabelProviderHelper.getText(testObject)), false);
    }

    // Query has been found! We execute it.
    List<Object> objResult = category.compute(testObject);

    // Since we want to check independent of order, we copy all of
    // the elements to Sets and use equals on the resulting Sets:
    Set<Object> set1 = new HashSet<Object>(objResult);
    Set<Object> set2 = new HashSet<Object>(expectedValues);

    // Final test
    assertTrue(set1.equals(set2));
  }

  /**
   * Query the category registry to find the category related to the query under test.
   * 
   * @param categoryId
   *          a category identifier
   * @return the category object if found, null otherwise
   */
  protected ICategory getCategory(String categoryId) {
    return CategoryRegistry.getInstance().getCategory(categoryId);
  }
}
