package org.polarsys.capella.test.framework.tools;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.kitalpha.massactions.core.column.AbstractMAColumn;
import org.polarsys.kitalpha.massactions.core.column.IMAColumn;
import org.polarsys.kitalpha.massactions.core.extensionpoint.columnprovider.IMAColumnProvider;
import org.polarsys.kitalpha.massactions.core.helper.container.PossibleFeature;
import org.polarsys.kitalpha.massactions.core.table.layer.body.IMABodyLayer;

/**
 * This class contributes a new column on Mass Visualization to generate easily model constants
 */
public class MassColumnsConstantModel implements IMAColumnProvider {

  private Collection<IMAColumn> column = Collections.singletonList(new AbstractMAColumn() {

    @Override
    public String getName() {
      return "[TEST] Constants";
    }

    @Override
    public Object getDataValue(EObject rowObject) {
      String constant = EObjectLabelProviderHelper.getText(rowObject).replaceAll(" ", "_").toUpperCase();
      String id = rowObject.eResource().getURIFragment(rowObject);
      return NLS.bind("public static final String {0} = \"{1}\"; //$NON-NLS-1$", constant, id);
    }

    @Override
    public void setDataValue(EObject arg0, Object arg1) {
      //Nothing here
    }

    @Override
    public void dataChanged(Collection<EObject> arg0) {
      //Nothing here
    }
  });

  @Override
  public Collection<IMAColumn> getColumnValues(Collection<PossibleFeature> arg0, Collection<EObject> arg1) {
    return column;
  }

  @Override
  public void setBodyLayer(IMABodyLayer arg0) {
    // Nothing
  }

}
