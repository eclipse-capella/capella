/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.information.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralClassHelper;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.InformationRealization;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

public class ClassHelper {

	private static ClassHelper instance;

  /**
   *
   */
	private ClassHelper() {
    // do nothing
	}

  /**
   *
   */
	public static ClassHelper getInstance(){
		if(instance == null)
			instance = new ClassHelper();
		return instance;
	}

  /**
   *
   */
	public Object doSwitch(Class element, EStructuralFeature feature) {
		Object ret = null;

		if (feature.equals(InformationPackage.Literals.CLASS__REALIZED_CLASSES)) {
      ret = getRealizedClasses(element);
    } else if (feature.equals(InformationPackage.Literals.CLASS__REALIZING_CLASSES)) {
      ret = getRealizingClasses(element);
    }

		// no helper found... searching in super classes...
		if(null == ret) {
			ret = GeneralClassHelper.getInstance().doSwitch(element, feature);
		}

		return ret;
	}

  /**
   *
   */
  protected List<Class> getRealizedClasses(Class element) {
    List <Class> ret = new ArrayList <>();
    for (AbstractTrace trace : element.getOutgoingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getTargetElement();
        if (elt instanceof Class) {
          ret.add((Class) elt);
        }
      }
    }
    return ret;
  }

  /**
   *
   */
  protected List<Class> getRealizingClasses(Class element) {
    List <Class> ret = new ArrayList <>();
    for (AbstractTrace trace : element.getIncomingTraces()) {
      if (trace instanceof InformationRealization) {
        TraceableElement elt = trace.getSourceElement();
        if (elt instanceof Class) {
          ret.add((Class) elt);
        }
      }
    }
    return ret;
  }
}
