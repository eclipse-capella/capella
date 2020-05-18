/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.tools;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.kitalpha.massactions.core.column.AbstractMAColumn;
import org.polarsys.kitalpha.massactions.core.column.IMAColumn;
import org.polarsys.kitalpha.massactions.core.extensionpoint.columnprovider.IMAColumnProvider;
import org.polarsys.kitalpha.massactions.core.helper.container.PossibleFeature;
import org.polarsys.kitalpha.massactions.core.table.layer.body.IMABodyLayer;

/**
 * This class contributes a new column on Mass Visualization to generate easily model constants
 */
public class MassColumnsConstantModel implements IMAColumnProvider {

  private Collection<IMAColumn> column = Arrays.asList(new AbstractMAColumn() {

    @Override
    public String getName() {
      return "[TEST] Constants";
    }

    @Override
    public Object getDataValue(EObject rowObject) {
      String constant = EObjectLabelProviderHelper.getText(rowObject).replaceAll(" ", "_").replaceAll("[\\[\\]]", "").toUpperCase();
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
  }, 
      
      new AbstractMAColumn() {

    @Override
    public String getName() {
      return "[TEST] Identifiable";
    }

    @Override
    public Object getDataValue(EObject rowObject) {
      
      String constant = EObjectLabelProviderHelper.getText(rowObject);
      if (rowObject.eContainer() instanceof BlockArchitecture) {
        constant=BlockArchitectureExt.getBlockArchitectureType((BlockArchitecture)rowObject.eContainer()).name()+" "+constant;
      }
      constant = constant.replaceAll("[\\[\\]]", "");
      constant = constant.replaceAll("[ ]", "_");
      constant = constant.toUpperCase();
      
      String id = rowObject.eResource().getURIFragment(rowObject);
      return NLS.bind("public @Identifier(id=\"{0}\") {1} {2};", new String[] {id, rowObject.eClass().getName(), constant});
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
