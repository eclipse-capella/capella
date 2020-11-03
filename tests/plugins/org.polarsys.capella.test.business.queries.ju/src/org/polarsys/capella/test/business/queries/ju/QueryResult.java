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
package org.polarsys.capella.test.business.queries.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * @author Erwan Brottier
 */
public class QueryResult {

  private static final String EXCEPTION_KEYWORD = "EXCEPTION";

  protected String queryIdentifier;
  protected String inputId;
  protected List<String> resultIds;

  protected Optional<IBusinessQuery> query = null;
  
  protected QueryResult() {
    resultIds = new ArrayList<String>();
  }

  public QueryResult(String queryIdentifier_p, String inputId_p, List<String> resultIds_p) {
    queryIdentifier = queryIdentifier_p;
    inputId = inputId_p;
    resultIds = resultIds_p;
  }

  public IBusinessQuery getBusinessQuery() {
    if (query == null) {
      IBusinessQuery businessQuery = BQTestHelpers.instanciateBQ(FrameworkUtil.getBundle(getClass()), getQuery());
      query = Optional.ofNullable(businessQuery);
    }
    return query.isPresent() ? query.get() : null;
  }

  public String getQuery() {
    return queryIdentifier.split("-")[0];
  }

  public String getQueryKind() {
    return queryIdentifier.split("-")[1];
  }

  public String getQueryIdentifier() {
    return queryIdentifier;
  }

  public String getInputId() {
    return inputId;
  }

  public List<String> getResultIds() {
    return resultIds;
  }

  /**
   * @return a query result with the given parameters.<br>
   *         Notice that parameter elements equals null means that the query raises an exception when called when the
   *         gievn input.
   */
  public static QueryResult createQueryResult(String queryIdentifier_p, EObject input,
      List<? extends EObject> elements) {
    String inputId = getObjectId(input);
    if (inputId == null) {
      return null;
    }
    List<String> ids = null;
    if (elements != null) {
      ids = new ArrayList<String>();
      for (EObject object : elements) {
        String id = getObjectId(object);
        if (id == null) {
          ids = null;
          // TODO log something
          return null;
        }
        ids.add(id);
      }
    }
    QueryResult queryResult = new QueryResult(queryIdentifier_p, inputId, ids);
    return queryResult;
  }

  public static String getObjectId(EObject object) {
    return IdManager.getInstance().getId(object);
  }

  @Override
  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append("queryIdentifier = " + queryIdentifier + '\n'); //$NON-NLS-1$
    b.append("inputId = " + inputId + '\n'); //$NON-NLS-1$
    if (resultIds != null) {
      ArrayList<String> ids = new ArrayList<String>(resultIds);
      Collections.sort(ids);
      b.append("resultIds = {" + '\n'); //$NON-NLS-1$
      for (String id : ids) {
        b.append("    " + id + '\n'); //$NON-NLS-1$
      }
      b.append("}" + '\n'); //$NON-NLS-1$
    } else {
      b.append(EXCEPTION_KEYWORD + '\n'); // $NON-NLS-1$
    }
    return b.toString();
  }

  public enum State {
    IDLE, RESULT_ID, CLEAN, END
  }

  public static List<QueryResult> deserialize(String serializedData) {
    List<String> lines = Arrays.asList(serializedData.split("\r\n|\r|\n")); //$NON-NLS-1$
    lines = new ArrayList<String>(lines);
    List<QueryResult> queryResults = new ArrayList<QueryResult>();
    while (lines.size() > 0) {
      QueryResult queryResult = parseOneQueryResult(lines);
      if (queryResult != null) {
        queryResults.add(queryResult);
      }
    }
    return queryResults;
  }

  public static String serialize(List<QueryResult> queryResults) {
    StringBuilder buffer = new StringBuilder();
    for (QueryResult queryResult : queryResults)
      buffer.append(queryResult.toString() + '\n');
    return buffer.toString();
  }

  private static void cleanStringList(List<String> lines) {
    while (lines.size() > 0 && StringUtils.trim(lines.get(0)).length() == 0) {
      lines.remove(0);
    }
  }

  /**
   * Return a query result or null if the given set of lines is empty.
   * 
   * @precond the given list must be modifiable.
   */
  private static QueryResult parseOneQueryResult(List<String> lines) {
    cleanStringList(lines);
    if (lines.size() > 0) {
      QueryResult current = new QueryResult();
      State state = State.IDLE;
      while ((lines.size() > 0) && (state != State.END)) {
        String line = lines.get(0);
        switch (state) {
        case IDLE: {
          if (line.startsWith("queryIdentifier")) { //$NON-NLS-1$
            current.queryIdentifier = StringUtils.trim(StringUtils.split(line, "=")[1]); //$NON-NLS-1$
          } else if (line.startsWith("inputId")) { //$NON-NLS-1$
            current.inputId = StringUtils.trim(StringUtils.split(line, "=")[1]); //$NON-NLS-1$
          } else if (line.startsWith("resultIds")) { //$NON-NLS-1$
            state = State.RESULT_ID;
          } else if (line.startsWith(EXCEPTION_KEYWORD)) { // $NON-NLS-1$
            current.resultIds = null;
            state = State.CLEAN;
          }
          lines.remove(0);
          break;
        }
        case RESULT_ID: {
          if (StringUtils.trim(line).startsWith("}")) { //$NON-NLS-1$
            state = State.CLEAN;
          } else {
            current.resultIds.add(StringUtils.trim(line));
          }
          lines.remove(0);
          break;
        }
        case CLEAN: {
          if (StringUtils.trim(line).length() > 0) {
            state = State.END;
          } else {
            lines.remove(0);
          }
          break;
        }
        default:
          throw new InternalError();
        }
      }
      return current;
    }
    return null;
  }

  public boolean equals(QueryResult queryResult) {
    return queryResult.queryIdentifier.equals(queryIdentifier) && queryResult.inputId.equals(inputId)
        && (queryResult.resultIds == null && resultIds == null || queryResult.resultIds != null && resultIds != null
            && new LinkedHashSet<String>(queryResult.resultIds).equals(new LinkedHashSet<String>(resultIds)));
  }

  public Collection<ResultItem> getResults(Session session) {
    IScope capellaSemanticResourceScope = new SemanticResourcesScope(
        session.getTransactionalEditingDomain().getResourceSet());

    ArrayList<ResultItem> result = new ArrayList<>();
    IBusinessQuery query = getBusinessQuery();
    EObject input = (EObject) IdManager.getInstance().getEObject(getInputId(), capellaSemanticResourceScope);

    if (input != null && query != null) {
      ArrayList<EObject> queryResult = new ArrayList<>();
      if (getQueryKind().equals(BQTestConstants.GET_AVAILABLE_METHOD_NAME)) {
        queryResult.addAll(query.getAvailableElements(input));
      } else {
        queryResult.addAll(query.getCurrentElements(input, false));
      }

      for (String exp : getResultIds()) {
        EObject expected = (EObject) IdManager.getInstance().getEObject(exp, capellaSemanticResourceScope);
        if (queryResult.contains(expected)) {
          result.add(new ResultItem(this, expected, exp, ResultItem.Kind.OK));
        } else if (!queryResult.contains(expected)) {
          result.add(new ResultItem(this, expected, exp, ResultItem.Kind.MISSING));
        }
      }

      for (EObject current : queryResult) {
        String id = EcoreUtil.getID(current);
        if (!getResultIds().contains(id)) {
          result.add(new ResultItem(this, current, id, ResultItem.Kind.ADDED));
        }
      }
    }
    return result;
  }
}
