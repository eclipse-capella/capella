package org.polarsys.capella.ext;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.polarsys.capella.common.helpers.query.IQuery;
import org.polarsys.capella.common.ui.IEditableQuery;

@Component
public class ExtQuery implements IQuery, IEditableQuery {

  @Override
  public List<Object> compute(Object object) {
    return Arrays.asList(object);
  }

  @Override
  public String setName(String value) {
    return "Ext";
  }

}
