/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju.testcases;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;
import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class MergeSequenceLinksFilterTests extends DiagramObjectFilterTestCase {

  public static final String LA_FUNCTIONAL_EXCHANGE_1 = "5d8d7993-e00c-4c51-82a1-09b30a7c8975";
  public static final String LA_FUNCTIONAL_EXCHANGE_1_2 = "5d62112f-58ab-4279-85a8-3bf1b26ef4f3";
  public static final String LA_FUNCTIONAL_EXCHANGE_2 = "db7b13b1-17a9-45d4-bb65-b930b6cebb80";
  public static final String LA_FUNCTIONAL_EXCHANGE_3 = "2df4090d-ec59-40eb-990e-6d8a8f602630";

  public static final String LA_SEQUENCE_LINK_1 = "203057c4-3278-4c14-b69b-b4d6026b2311";
  public static final String LA_SEQUENCE_LINK_2 = "e85f715f-3297-44e0-99e4-7f87e3abd88e";
  public static final String LA_SEQUENCE_LINK_1_2 = "5d71ab6a-1aaf-4fc7-be05-b2dce7668970";
  public static final String LA_SEQUENCE_LINK_3 = "e3b41522-5311-415d-8a27-7cff1b126e1b";

  @Override
  protected String getTestProjectName() {
    return "HideSimplifiedLinksFilter";
  }

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_MERGE_ASSOCIATED_FE_AND_SL;
  }

  protected void postRunTest() {
    // check that expected filtered objects are actually filtered
    Set<String> links = new HashSet<>();
    Map<String, Integer> linksNotFiltered = new HashMap<String, Integer>();
    for (DDiagramElement elt : diagram.getDiagramElements()) {

      DDiagramElement objectToBeFiltered = diagramElement2ObjectID.get(((CapellaElement) elt.getTarget()).getId());

      if (objectToBeFiltered != null) {
        String objectID = ((CapellaElement) objectToBeFiltered.getTarget()).getId();
        boolean isFiltered = isFiltered(elt);
        if (objectToBeFiltered.getTarget() instanceof SequenceLink) {
          if (filteredObjetIDs.contains(objectID)) {
            /*
             * Case 1: SequenceLink with association: one SL remains in diagram while one (the association) should be filtered
             */
            if (!isFiltered)
              links.add(objectID);
            else if (links.contains(objectID))
              links.remove(objectID);
          } else {
            /*
             * Case 2: SequenceLinks without association should not be filtered
             * Case 3: Catch SL with association which were not checked by the user, should be given in filteredObjetIDs
             */
            // SequenceLinks without association should not be filtered
            Integer count = 0;
            if (linksNotFiltered.containsKey(objectID)) {
              count = linksNotFiltered.get(objectID);
            }
            linksNotFiltered.put(objectID, count + 1);
          }
        } else {
          if (!isFiltered && filteredObjetIDs.contains(objectID)) {
            assertTrue("Object " + objectID + " should be filtered by filter " + filterName + " in diagram "
                + diagramName + " of project " + projectTestName, false);
          } else if (isFiltered && !filteredObjetIDs.contains(objectID) && notFiltered.contains(elt)) {
            assertTrue("Object " + objectID + " should not be filtered by filter " + filterName + " in diagram "
                + diagramName + " of project " + projectTestName, false);
          }
        }
      }
    }
    for (String key : linksNotFiltered.keySet()) {
      if (linksNotFiltered.get(key) >= 2) {
        assertTrue("Object " + key + " should be filtered by filter " + filterName + " in diagram " + diagramName
            + " of project " + projectTestName, false);
      }
    }

    assertTrue(links.isEmpty());
  }
}
