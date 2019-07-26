package org.polarsys.capella.test.diagram.filters.ju.lab;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HidePortDelegationsForLAB extends FiltersForLAB {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LAB_HIDE_PORT_DELEGATIONS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { PORT_DELEGATION_ID });
  }

}
