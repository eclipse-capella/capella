package org.polarsys.capella.core.sirius.ui.queries;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.ui.IEditableQuery;

@Component
public class NameQuery implements IEditableQuery, IQuery {

  @Override
  public String setName(String value) {
    return NameQuery.class.getSimpleName();
  }

  @Override
  public List<Object> compute(Object object) {
    return Arrays.asList(object);
  }

}
