/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractDeleteCommandResolver;

public class I_26_Resolver extends AbstractDeleteCommandResolver {

  @Override
  public Object getElementToDelete(Object obj) {

    return getDuplicateAbstractTraces((AbstractTrace) obj);
  }

  private List<AbstractTrace> getDuplicateAbstractTraces(AbstractTrace source) {

    List<AbstractTrace> duplicates = new ArrayList<>();

    ECrossReferenceAdapter adapter = ECrossReferenceAdapter.getCrossReferenceAdapter(source);
    if (adapter != null && source.getSourceElement() != null && source.getTargetElement() != null) {

      for (EStructuralFeature.Setting setting : adapter.getInverseReferences(source.getSourceElement())) {

        if (setting.getEObject().eClass() == source.eClass()
            && setting.getEStructuralFeature() == ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT) {

          AbstractTrace otherTrace = (AbstractTrace) setting.getEObject();
          if (otherTrace.getTargetElement().equals(source.getTargetElement())
              && otherTrace.getSourceElement().equals(source.getSourceElement())) {

            duplicates.add(otherTrace);
          }
        }
      }
    }

    // Remove the first element, because we need to keep the original Trace
    if (duplicates.size() > 1) {

      duplicates.remove(0);
    }

    return duplicates;
  }

}
