/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.data.helpers.capellacore;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.InvolvedElement;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.NamingRule;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.AbstractPropertyValueHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.ConstraintHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.GeneralizableElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolvementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.InvolverElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamespaceHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.PropertyValueGroupHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.StructureHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypeHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TypedElementHelper;

public class CapellaCoreHelper implements IHelper {

	/**
	 * @see org.polarsys.capella.common.model.helpers.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EAnnotation)
	 */
  @Override
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
		Object ret = null;

		if (object instanceof TypedElement) {
			ret = TypedElementHelper.getInstance().doSwitch((TypedElement) object, feature);
		}
		else if (object instanceof Type) {
			ret = TypeHelper.getInstance().doSwitch((Type) object, feature);
		}
		else if (object instanceof GeneralizableElement) {
			ret = GeneralizableElementHelper.getInstance().doSwitch((GeneralizableElement) object, feature);
		}
		else if (object instanceof KeyValue) {
			ret = CapellaElementHelper.getInstance().doSwitch((KeyValue) object, feature);
		}
		else if (object instanceof Involvement) {
			ret = InvolvementHelper.getInstance().doSwitch((Involvement) object, feature);
		}
		else if (object instanceof InvolverElement) {
			ret = InvolverElementHelper.getInstance().doSwitch((InvolverElement) object, feature);
		}
		else if (object instanceof InvolvedElement) {
			ret = InvolvedElementHelper.getInstance().doSwitch((InvolvedElement) object, feature);
		}
		else if (object instanceof Relationship) {
			ret = RelationshipHelper.getInstance().doSwitch((Relationship) object, feature);
		}
		else if (object instanceof Structure) {
			ret = StructureHelper.getInstance().doSwitch((Structure) object, feature);
		}
		else if (object instanceof Trace) {
			ret = TraceHelper.getInstance().doSwitch((Trace) object, feature);
		}
    else if (object instanceof NamingRule) {
      ret = CapellaElementHelper.getInstance().doSwitch((NamingRule) object, feature);
    }
    else if (object instanceof AbstractPropertyValue) {
      ret = AbstractPropertyValueHelper.getInstance().doSwitch((AbstractPropertyValue) object, feature);
    }
    else if (object instanceof EnumerationPropertyType) {
      ret = NamedElementHelper.getInstance().doSwitch((EnumerationPropertyType) object, feature);
    }
    else if (object instanceof EnumerationPropertyLiteral) {
      ret = NamedElementHelper.getInstance().doSwitch((EnumerationPropertyLiteral) object, feature);
    }
    else if (object instanceof PropertyValueGroup) {
      ret = PropertyValueGroupHelper.getInstance().doSwitch((PropertyValueGroup) object, feature);
    }
    else if (object instanceof PropertyValuePkg) {
      ret = StructureHelper.getInstance().doSwitch((PropertyValuePkg) object, feature);
    }
    else if (object instanceof Constraint) {
      ret = ConstraintHelper.getInstance().doSwitch((Constraint) object, feature);
    }
    else if (object instanceof Namespace) {
      ret = NamespaceHelper.getInstance().doSwitch((Namespace) object, feature);
    }

		if (null != ret || feature.getUpperBound() == 1)
			return ret;

		throw new HelperNotFoundException();
	}

}
