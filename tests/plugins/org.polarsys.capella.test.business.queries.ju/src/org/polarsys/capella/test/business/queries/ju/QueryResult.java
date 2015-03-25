/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.business.queries.ju;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * @author Erwan Brottier
 */
public class QueryResult {

	private static final String EXCEPTION_KEYWORD = "EXCEPTION";
	private static final String MULTIPLE_COMMENTS_BEGIN_KEYWORD = "/*";
	private static final String MULTIPLE_COMMENTS_END_KEYWORD = "*/";
	private static final String SINGLE_COMMENTS_KEYWORD = "//";
  
  protected String queryIdentifier;
  protected String inputId;
  protected List<String> resultIds;

  protected QueryResult() {
    resultIds = new ArrayList<String>();
  }

  public QueryResult(String queryIdentifier_p, String inputId_p, List<String> resultIds_p) {
    queryIdentifier = queryIdentifier_p;
    inputId = inputId_p;
    resultIds = resultIds_p;
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
   * Notice that parameter elements equals null means that the query raises an exception when called when the gievn input. 
   */
  public static QueryResult createQueryResult(String queryIdentifier_p, EObject input, List<? extends EObject> elements) {
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
    	b.append("resultIds = {" + '\n'); //$NON-NLS-1$
    	for (int i = 0; i < resultIds.size(); i++)
    		b.append("    " + resultIds.get(i) + '\n'); //$NON-NLS-1$    	
    	b.append("}" + '\n'); //$NON-NLS-1$
    } else {
    	b.append(EXCEPTION_KEYWORD + '\n'); //$NON-NLS-1$
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
			buffer.append(queryResult.toString()+'\n');
		return buffer.toString();
  }
 
  private static void cleanStringList(List<String> lines) {
  	while (lines.size() > 0 && StringUtils.trim(lines.get(0)).length() == 0) {
  		lines.remove(0);
  	} 
  }
  
  /** Return a query result or null if the given set of lines is empty.
   * @precond the given list must be modifiable. */
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
  				} else if (line.startsWith(EXCEPTION_KEYWORD)) { //$NON-NLS-1$
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
  	} else {
  		return null;
  	}
  }
  
  public boolean equals(QueryResult queryResult) {
  	return queryResult.queryIdentifier.equals(queryIdentifier) 
  			&& queryResult.inputId.equals(inputId) 
  			&&	(
  					queryResult.resultIds == null && resultIds == null
  					|| queryResult.resultIds != null && resultIds != null && new LinkedHashSet<String>(queryResult.resultIds).equals(new LinkedHashSet<String>(resultIds))
  			);
  }
}
