package org.polarsys.capella.core.sirius.ui.queries;

import org.osgi.service.component.annotations.Component;
import org.polarsys.capella.common.ui.IEditableQuery;

@Component
public class NameQuery3 implements IEditableQuery{

  @Override
  public String setName(String value) {
    return NameQuery3.class.getSimpleName();
  }

}
