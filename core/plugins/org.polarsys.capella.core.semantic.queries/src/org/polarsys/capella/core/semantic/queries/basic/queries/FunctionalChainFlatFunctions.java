/*******************************************************************************
 * This program is the Confidential and Proprietary product of Thales Global   *
 * Services. Any unauthorized use, reproduction or transfer of this program is *
 * strictly prohibited.                                                        *
 *                                                                             *
 * Copyright (c) 2022 Thales Global Services. All Rights Reserved.             *
 * *****************************************************************************/
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.model.helpers.FunctionalChainExt;

public class FunctionalChainFlatFunctions implements IQuery {

  @Override
  public List<Object> compute(Object object) {
    return new ArrayList(FunctionalChainExt.getFlatFunctions((FunctionalChain) object));
  }

}
