package org.polarsys.capella.core.sirius.ui.queries;

import org.osgi.service.component.annotations.Component;
import org.polarsys.capella.common.ui.IEditableQuery;

@Component
public class NameQuery2 implements IEditableQuery{

  @Override
  public String setName(String value) {
    return NameQuery2.class.getSimpleName();
  }

}
