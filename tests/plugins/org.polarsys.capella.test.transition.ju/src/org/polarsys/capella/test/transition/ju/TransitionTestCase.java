/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.transition.ju;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.interaction.InteractionFragment;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.framework.api.BasicCommandTestCase;

/**
 * This class is a generic test case for Transitions tests.<br>
 * To use it, create a test case that inherits from this class and implement abstract methods, that are<br>
 * (see method documentation for more details) :<br>
 * <br>
 * - performTest()<br>
 * - getRequiredTestModels()<br>
 */
public abstract class TransitionTestCase extends BasicCommandTestCase {

  IPropertyContext _propertiesContext = null;

  protected void setPreferenceValue(String id, Object value) {
    if (_propertiesContext == null) {
      IProperties properties = new PropertiesLoader().getProperties(ITopDownConstants.OPTIONS_SCOPE__PREFERENCES);
      _propertiesContext = new PropertyContext(properties);
    }

    IProperty property = _propertiesContext.getProperties().getProperty(id);
    if (property == null) {
      assertFalse("Property not found : " + id, true);
    }
    _propertiesContext.setCurrentValue(property, value);
    _propertiesContext.write(property);
  }

  protected List<EObject> getAllocatedElements(EObject object) {
    List<EObject> result = new ArrayList<EObject>();
    if (object instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) object).getOutgoingTraces()) {
        result.add(trace.getTargetElement());
      }
    }
    return result;
  }

  protected List<EObject> getAllocatingElements(EObject object) {
    List<EObject> result = new ArrayList<EObject>();
    if (object instanceof TraceableElement) {
      for (AbstractTrace trace : ((TraceableElement) object).getIncomingTraces()) {
        result.add(trace.getSourceElement());
      }
    }
    return result;
  }

  protected EObject getAllocatingElement(EObject object) {
    return mustBeMonoTransitioned(object);
  }

  protected void mustBeOwnedBy(EObject object, EObject container) {
    assertTrue(object != null);
    assertTrue(object.eContainer().equals(container));
  }

  protected EObject mustBeMonoTransitioned(String id) {
    EObject element = getObject(id);
    return mustBeMultiTransitioned(element, 1).get(0);
  }

  protected EObject mustBeMonoTransitioned(EObject element) {
    return mustBeMultiTransitioned(element, 1).get(0);
  }
  
  protected void mustBeNamed(EObject result, String name) {
    assertTrue(((AbstractNamedElement)result).getName().equals(name));
  }

  protected List<EObject> mustBeMultiTransitioned(String id, int nb) {
    EObject element = getObject(id);
    return mustBeMultiTransitioned(element, nb);
  }

  protected List<EObject> mustBeMultiTransitioned(EObject element, int nb) {
    assertNotNull(NLS.bind(Messages.NullElement, EObjectLabelProviderHelper.getText(element)));
    List<EObject> a4t = getAllocatingElements(element);
    assertTrue(NLS.bind("Should be equals to ''{0}''", "" + nb), a4t.size() == nb); //$NON-NLS-1$ //$NON-NLS-2$
    return a4t;
  }

  protected EObject mustBeLinkedTo(String sourceId, String targetId, EStructuralFeature feature) {
    EObject source = getObject(sourceId);
    EObject target = getObject(targetId);
    return mustBeLinkedTo(source, target, feature);
  }

  protected EObject mustBeLinkedTo(EObject source, EObject target, EStructuralFeature feature) {
    if (feature.isMany()) {
      assertTrue(
          NLS.bind("''{0}'' should be linked to ''{1}'' by ''{2}''",
              new Object[] { EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), feature.getName() }),
          ((EList) source.eGet(feature)).contains(target));
    } else {
      assertTrue(
          NLS.bind("''{0}'' should be linked to ''{1}'' by ''{2}''",
              new Object[] { EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), feature.getName() }),
          target.equals(source.eGet(feature)));
    }

    return source;
  }

  protected EObject mustNotBeLinkedTo(String sourceId, String targetId, EStructuralFeature feature) {
    EObject source = getObject(sourceId);
    EObject target = getObject(targetId);
    return mustNotBeLinkedTo(source, target, feature);
  }

  protected EObject mustNotBeLinkedTo(EObject source, EObject target, EStructuralFeature feature) {
    if (feature.isMany()) {
      assertFalse(
          NLS.bind("''{0}'' should be linked to ''{1}'' by ''{2}''",
              new Object[] { EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), feature.getName() }),
          ((EList) source.eGet(feature)).contains(target));
    } else {
      assertFalse(
          NLS.bind("''{0}'' should be linked to ''{1}'' by ''{2}''",
              new Object[] { EObjectLabelProviderHelper.getText(source), EObjectLabelProviderHelper.getText(target), feature.getName() }),
          target.equals(source.eGet(feature)));
    }

    return source;
  }

  public EObject mustBeTransitionedAndLinkedTo(String id, String id2, EStructuralFeature feature) {
    EObject source = getObject(id);
    EObject tSource = getAllocatingElement(source);
    EObject target = getObject(id2);
    testReferenceLinked(tSource, target, feature);
    return source;
  }

  public EObject mustBeTransitionedAndLinkedToTransitioned(String id, String id2, EStructuralFeature feature) {
    EObject source = getObject(id);
    EObject tSource = getAllocatingElement(source);
    EObject target = getObject(id2);
    EObject tTarget = getAllocatingElement(target);
    testReferenceLinked(tSource, tTarget, feature);
    return source;
  }

  public EObject mustBeTransitionedAndNotLinkedTo(String id, String id2, EStructuralFeature feature) {
    EObject source = getObject(id);
    EObject tSource = getAllocatingElement(source);

    EObject target = getObject(id2);

    testReferenceNotLinked(tSource, target, feature);
    return source;
  }

  public EObject mustBeTransitionedAndNotLinkedToTransitioned(String id, String id2, EStructuralFeature feature) {
    EObject source = getObject(id);
    EObject tSource = getAllocatingElement(source);

    EObject target = getObject(id2);
    EObject tTarget = getAllocatingElement(target);

    testReferenceNotLinked(tSource, tTarget, feature);
    return source;
  }

  public void testReferenceLinked(EObject source, EObject target, EStructuralFeature feature) {

    if (feature.isMany()) {
      assertTrue(((EList) source.eGet(feature)).contains(target));
    } else {
      assertTrue(source.eGet(feature).equals(target));
    }

  }

  public void testReferenceNotLinked(EObject source, EObject target, EStructuralFeature feature) {
    if (target == null) {
      if (feature.isMany()) {
        assertTrue((source.eGet(feature) == null) || !((EList) source.eGet(feature)).contains(null));
      } else {
        assertTrue(source.eGet(feature) == null);
      }

    } else {
      if (feature.isMany()) {
        assertTrue(!source.eGet(feature).equals(target) && !((EList) source.eGet(feature)).contains(target));
      } else {
        assertTrue(!source.eGet(feature).equals(target));
      }
    }
  }

  protected <T extends EObject> T mustBeTransitioned(String id) {
    EObject a4 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a4);
    assertNotNull(NLS.bind(Messages.NullElement, name), a4);
    T a4t = (T) getAllocatingElement(a4);
    String namet = name + "t"; //$NON-NLS-1$
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, namet), a4t);
    assertNotNull(NLS.bind(Messages.ShouldBeContainedBy, name), a4t.eContainer());
    return a4t;
  }

  protected EObject mustBeTransitionedIndirecltyContainedBy(String id, EObject container) {
    EObject a4 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a4);
    assertNotNull(NLS.bind(Messages.NullElement, name), a4);
    EObject a4t = getAllocatingElement(a4);
    String namet = name + "t"; //$NON-NLS-1$
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, namet), a4t);
    String containerName = (container instanceof AbstractNamedElement ? ((AbstractNamedElement) container).getName() : container.eClass().getName());
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, namet, containerName), EcoreUtil2.isOrIsContainedBy(a4t, container));
    return a4t;
  }

  protected EObject mustBeTransitioned(String id, EObject container) {
    EObject a4 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a4);
    assertNotNull(NLS.bind(Messages.NullElement, name), a4);
    EObject a4t = getAllocatingElement(a4);
    String namet = name + "t"; //$NON-NLS-1$
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, namet), a4t);
    String containerName = (container instanceof AbstractNamedElement ? ((AbstractNamedElement) container).getName() : container.eClass().getName());
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, namet, containerName), a4t.eContainer() == container);
    return a4t;
  }

  protected EObject mustBeTransitionedAndReference(String id, EObject container) {
    EObject a4 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a4);
    assertNotNull(NLS.bind(Messages.NullElement, name), a4);
    EObject obj = mustBeTransitionedTo(id, a4.eClass(), container);

    EReference[] references = new EReference[0];

    if (obj instanceof InteractionFragment) {
      references = new EReference[] { InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES };
    }

    for (EReference reference : references) {
      Object sourceRef = a4.eGet(reference);
      Object targetRef = obj.eGet(reference);
      if (sourceRef == null) {
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, reference.getName(), sourceRef), targetRef == null);

      } else if ((sourceRef instanceof List<?>) && (targetRef instanceof List<?>)) {
        assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, reference.getName(), ICommonConstants.EMPTY_STRING + ((List<?>) sourceRef).size()),
            ((List<?>) sourceRef).size() == ((List<?>) targetRef).size());

      } else {
        assertFalse(NLS.bind(Messages.ShouldBeEqualsTo, reference.getName(), sourceRef), sourceRef == null);
      }
    }
    return obj;
  }

  protected EObject mustBeTransitionedTo(String id, EClass clazz) {
    EObject lc1 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(lc1);
    assertNotNull(NLS.bind(Messages.NullElement, name), lc1);
    String namet = name + "t"; //$NON-NLS-1$
    for (EObject a4t : getAllocatingElements(lc1)) {
      if (clazz.isInstance(a4t)) {
        assertTrue(NLS.bind(Messages.ShouldBeInstanceof, namet), true);
        return a4t;
      }
    }
    assertTrue(NLS.bind(Messages.ShouldBeInstanceof, namet), false);
    return null;
  }

  protected EObject mustBeTransitionedTo(String id, EClass clazz, EObject container) {
    EObject lc1 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(lc1);
    assertNotNull(NLS.bind(Messages.NullElement, name), lc1);
    String namet = name + "t"; //$NON-NLS-1$
    for (EObject a4t : getAllocatingElements(lc1)) {
      if (clazz.isInstance(a4t)) {
        assertTrue(NLS.bind(Messages.ShouldBeInstanceof, namet), true);

        String containerName = (container instanceof AbstractNamedElement ? ((AbstractNamedElement) container).getName() : container.eClass().getName());
        if (EcoreUtil2.isOrIsContainedBy(a4t, container)) {
          assertTrue(NLS.bind(Messages.ShouldBeContainedBy, namet, containerName), true);
        }

        return a4t;
      }
    }

    assertTrue(NLS.bind(Messages.ShouldBeInstanceof, namet), false);
    return null;
  }

  /**
   * @param lA__LC1
   * @return
   */
  protected <T extends EObject> T shouldExist(String id) {
    T i2 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(i2);
    assertNotNull(NLS.bind(Messages.NullElement, name), i2);
    return i2;
  }

  protected <T extends EObject> T shouldNotBeTransitioned(String id) {
    T a5 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a5);
    assertNotNull(NLS.bind(Messages.NullElement, name), a5);
    EObject a5t = getAllocatingElement(a5);
    String namet = name + "t"; //$NON-NLS-1$
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, namet), a5t);
    return a5;
  }

  protected boolean shouldNotBeTransitioned(String id, EClass clazz) {
    EObject a5 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(a5);
    assertNotNull(NLS.bind(Messages.NullElement, name), a5);
    String namet = name + "t"; //$NON-NLS-1$
    for (EObject a5t : getAllocatingElements(a5)) {
      if (clazz.isInstance(a5t)) {
        assertTrue(NLS.bind(Messages.ShouldNotBeTransitioned, namet), false);
        return false;
      }
    }
    assertTrue(NLS.bind(Messages.ShouldNotBeTransitioned, namet), true);
    return true;
  }

  protected EObject shouldNotBeTransitionedTo(String id, EObject container) {
    EObject lc1 = getObject(id);
    String name = EObjectLabelProviderHelper.getText(lc1);
    assertNotNull(NLS.bind(Messages.NullElement, name), lc1);
    String namet = name + "t"; //$NON-NLS-1$
    for (EObject a4t : getAllocatingElements(lc1)) {
      String containerName = (container instanceof AbstractNamedElement ? ((AbstractNamedElement) container).getName() : container.eClass().getName());
      assertTrue(NLS.bind(Messages.ShouldNotBeTransitionedInto, namet, containerName), !EcoreUtil2.isOrIsContainedBy(a4t, container));
    }

    return null;
  }
  

}
