/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.capella;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMIException;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.common.data.core.gen.xmi.impl.CapellaXMLSaveImpl;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.core.data.migration.ContributoryMigrationRunnable;
import org.polarsys.capella.core.data.migration.MigrationHelpers;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.emde.xmi.SAXExtensionXMIHandler;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionLoadImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */
public class ModelMigrationRunnable extends ContributoryMigrationRunnable {

  public ModelMigrationRunnable(IFile file) {
    super(file);
  }

  @Override
  public String getName() {
    return Messages.MigrationAction_CapellaMigration;
  }

  protected boolean shallBeLoaded(URI uri) {
    return !CapellaResourceHelper.isAirdResource(uri);
  }

  @Override
  public XMLResource doCreateResource(URI uri, final MigrationContext context) {
    CapellamodellerResourceImpl result = new CapellamodellerResourceImpl(uri) {

      @Override
      protected XMIExtensionHelperImpl createXMLHelper() {
        return createCapellaXMLHelper(this);
      }

      @Override
      protected XMLLoad createXMLLoad() {
        return createCustomizedHandler(createXMLHelper(), context);
      }

      @Override
      protected XMLSave createXMLSave() {
        return createCustomizedSaveHandler(createXMLHelper(), context);
      }

    };
    return result;
  }

  public XMIExtensionHelperImpl createCapellaXMLHelper(XMLResource resource) {
    XMIExtensionHelperImpl result = new XMIExtensionHelperImpl(resource) {

      @Override
      public EClassifier getType(EFactory eFactory, String typeName) {
        EClassifier type = null;
        if (eFactory != null) {
          EPackage ePackage = eFactory.getEPackage();
          if (extendedMetaData != null) {
            type = extendedMetaData.getType(ePackage, typeName);
          }
          if (type == null) {
            EClass eClass = (EClass) ePackage.getEClassifier(typeName);
            if ((eClass == null) && (xmlMap != null)) {
              return xmlMap.getClassifier(ePackage.getNsURI(), typeName);
            }
            return eClass;
          }
        }
        return type;
      }

      @Override
      protected Object createFromString(EFactory eFactory, EDataType eDataType, String value) {
        if (value != null && eDataType.equals(ViewpointPackage.eINSTANCE.getResourceDescriptor())) {
          // ResourceDescriptor(String) constructor converts string into URI
          // That URI is used to get a relative URI
          URI resolvedURI = new ResourceDescriptor(value).getResourceURI().resolve(resourceURI);
          return new ResourceDescriptor(resolvedURI);
        }
        return super.createFromString(eFactory, eDataType, value);
      }
    };

    return result;
  }

  protected XMLSave createCustomizedSaveHandler(XMIExtensionHelperImpl xmiExtensionHelperImpl,
      MigrationContext context) {
    return new CapellaXMLSaveImpl(xmiExtensionHelperImpl) {

      /**
       * Unlike outside migration where we don't have any extendedMetaData loaded, while migration we use ecore2ecore to
       * perform some migration. In some cases, where EClass are moved from one ecore to another, some
       * EStructuralFeature are considered as Unknown while loading, whereas they are present (but migrated/moved) In
       * that case, loading method SAXExtensionXMIHandler.validateCreateObjectFromFactory will create an AnyType for
       * such feature filled with eProxyUri not yet resolved. At saving time, default implementation of
       * org.polarsys.kitalpha.emde.xmi.XMIExtensionSaveImpl will save into xml: feature='AnyType.getMixed(feature)' in
       * addition of feature='eGet(feature)' which lead to errors in the xml. intead of saving 2 time the feature, we
       * merge it to one, prioritizing eGet(feature) values.
       */
      @Override
      @SuppressWarnings("unchecked")
      protected void saveEObjectMany(EObject o, EStructuralFeature f) {

        // Retrieve values from o.eGet(f)
        InternalEList<? extends EObject> values = (InternalEList<? extends EObject>) helper.getValue(o, f);

        // Retrieve values from AnyTypes, and merge them with original
        // values
        Object extendedValues = getValues(o, f);
        if ((extendedValues != null) && (extendedValues instanceof InternalEList)) {
          InternalEList<?> listExtendedValues = (InternalEList<? extends EObject>) extendedValues;
          if (!listExtendedValues.isEmpty()) {
            InternalEList<EObject> result = new BasicInternalEList<>(EObject.class);
            HashMap<String, EObject> uriFragment = new HashMap<String, EObject>();

            // Add all values
            for (EObject object : (InternalEList<? extends EObject>) values) {
              if (object != null) {
                result.add(object);
                String id = EcoreUtil.getID(object);
                if (object.eIsProxy() && (((InternalEObject) object).eProxyURI() != null)) {
                  id = ((InternalEObject) object).eProxyURI().fragment();
                }
                uriFragment.put(id, object);
              }
            }

            // Add all extendedValues if not found in the list
            for (EObject object : (InternalEList<? extends EObject>) extendedValues) {
              if (object != null) {
                String id = EcoreUtil.getID(object);
                if (object.eIsProxy() && (((InternalEObject) object).eProxyURI() != null)) {
                  id = ((InternalEObject) object).eProxyURI().fragment();
                }
                if (!uriFragment.containsKey(id)) {
                  result.add(object);
                }
              }
            }
            values = result;
          }
        }

        processSaveEObjectMany(values, o, f);
      }
    };
  }

  /**
   * Creates a customized handler to manage the migration of simple references to derived references
   * 
   * @param xmlHelper
   * @return
   */
  protected XMLLoad createCustomizedHandler(final XMIExtensionHelperImpl xmlHelper, final MigrationContext context) {
    return new XMIExtensionLoadImpl(xmlHelper) {

      @Override
      protected DefaultHandler makeDefaultHandler() {
        return new SAXExtensionXMIHandler(resource, helper, options) {
          @Override
          protected EStructuralFeature getFeature(EObject object, String prefix, String name, boolean isElement) {
            EStructuralFeature feature = MigrationHelpers.getInstance().getFeature(object, prefix, name, isElement);
            if (feature != null) {
              return feature;
            }
            return super.getFeature(object, prefix, name, isElement);
          }

          /*
           * (non-Javadoc)
           * 
           * @see org.eclipse.emf.ecore.xmi.impl.XMIHandler#startElement(java.lang.String, java.lang.String,
           * java.lang.String, org.xml.sax.Attributes)
           */
          @Override
          public void startElement(String uri, String localName, String name, Attributes attributes)
              throws SAXException {
            super.startElement(uri, localName, name, attributes);
            MigrationHelpers.getInstance().createdXMLHelper(resource, xmlHelper);
          }

          @Override
          protected void handleProxy(InternalEObject proxy, String inputUriLiteral) {

            String uriLiteral = inputUriLiteral;
            String migratedURI = MigrationHelpers.getInstance().getHandleProxy(proxy, inputUriLiteral, resource,
                xmlHelper, context);
            if (migratedURI != null) {
              uriLiteral = migratedURI;
            }

            super.handleProxy(proxy, uriLiteral);

            objects.peek();
          }

          @Override
          public void error(XMIException e) {

            IStatus status = MigrationHelpers.getInstance().handleError(e, resource, context);

            if ((status == null) || (status.getSeverity() == IStatus.ERROR)) {
              super.error(e);
            } else if (status.getSeverity() == IStatus.WARNING) {
              warning(e);
            }

          }

          /*
           * (non-Javadoc)
           * 
           * @see org.eclipse.emf.ecore.xmi.impl.XMLHandler#handleXMLNSAttribute(java.lang.String, java.lang.String)
           */
          @Override
          protected void handleXMLNSAttribute(String attrib, String value) {

            String prefix = attrib;

            String migratedPrefix = MigrationHelpers.getInstance().getNSPrefix(attrib, context);
            if (migratedPrefix != null) {
              prefix = migratedPrefix;
            }

            String NSURI = value;
            String migratedNSURI = MigrationHelpers.getInstance().getNSURI(attrib, value, context);
            if (migratedNSURI != null) {
              NSURI = migratedNSURI;
            }

            super.handleXMLNSAttribute(prefix, NSURI);
          }

          /**
           * @see org.eclipse.emf.ecore.xmi.impl.XMLHandler#handleForwardReferences(boolean)
           */
          @Override
          protected void handleForwardReferences(boolean isEndDocument) {
            for (Iterator<SingleReference> i = forwardSingleReferences.iterator(); i.hasNext();) {
              SingleReference ref = i.next();
              EStructuralFeature feature = ref.getFeature();
              if (isDerivedFeature(feature)) {
                EObject obj = xmlResource.getEObject((String) ref.getValue());
                if ((obj == null) && isEndDocument) {
                  i.remove();
                }
              }
            }
            for (Iterator<ManyReference> i = forwardManyReferences.iterator(); i.hasNext();) {
              ManyReference ref = i.next();
              EStructuralFeature feature = ref.getFeature();
              if (isDerivedFeature(feature)) {
                boolean failure = false;
                Object[] values = ref.getValues();
                for (Object value : values) {
                  EObject obj = xmlResource.getEObject((String) value);
                  if ((obj == null) && isEndDocument) {
                    failure = true;
                  }
                }
                if (failure) {
                  i.remove();
                }
              }
            }

            super.handleForwardReferences(isEndDocument);
          }

          private boolean isDerivedFeature(EStructuralFeature feature) {
            return feature.isDerived();
          }

          @Override
          protected void handleUnknownFeature(String prefix, String name, boolean isElement, EObject peekObject,
              String value) {

            boolean result = MigrationHelpers.getInstance().ignoreUnknownFeature(prefix, name, isElement, peekObject,
                value, resource, context);
            if (result) {
              return;
            }

            String featureName = MigrationHelpers.getInstance().getFeatureName(prefix, name, isElement, peekObject,
                value, resource, context);
            if (featureName != null) {
              setAttribValue(peekObject, featureName, value);
            }
            super.handleUnknownFeature(prefix, name, isElement, peekObject, value);

          }

          @Override
          public void endElement(String uri, String localName, String name) {

            super.endElement(uri, localName, name);
            MigrationHelpers.getInstance().endElement(objects.peekEObject(), attribs, uri, localName, name, resource,
                context);

          }

          /**
           * Set the given feature of the given object to the given value.
           */
          @Override
          protected void setFeatureValue(EObject peekObject, EStructuralFeature inputFeature, Object inputValue,
              int position) {
            EStructuralFeature feature = inputFeature;

            boolean result = MigrationHelpers.getInstance().ignoreSetFeatureValue(peekObject, inputFeature, inputValue,
                position, resource, context);
            if (result) {
              return;
            }

            EStructuralFeature migratedFeature = MigrationHelpers.getInstance().getFeature(peekObject, inputFeature,
                resource, context);
            if (migratedFeature != null) {
              feature = migratedFeature;
            }

            Object value = inputValue;
            Object migratedValue = MigrationHelpers.getInstance().getValue(peekObject, inputFeature, value, position,
                resource, context);
            if (migratedValue != null) {
              value = migratedValue;
            }

            // if (value != null) {
            super.setFeatureValue(peekObject, feature, value, position);
            // }

          }

          /**
           * Set the values for the given multi-valued forward reference.
           */
          @Override
          protected void setFeatureValues(ManyReference reference) {
            EObject peekObject = reference.getObject();
            EStructuralFeature inputFeature = reference.getFeature();
            EStructuralFeature feature = inputFeature;

            if ((reference.getPositions() != null) && (reference.getValues() != null)
                && (reference.getPositions().length == reference.getValues().length)) {
              for (int i = 0; i < reference.getPositions().length; i++) {

                EStructuralFeature migratedFeature = MigrationHelpers.getInstance().getFeature(peekObject, inputFeature,
                    resource, context);
                if (migratedFeature != null) {
                  feature = migratedFeature;
                }

                Object value = reference.getValues()[i];
                Object migratedValue = MigrationHelpers.getInstance().getValue(peekObject, inputFeature, value,
                    reference.getPositions()[i], resource, context);
                if (migratedValue != null) {
                  value = migratedValue;
                }

                setFeatureValue(peekObject, feature, value, reference.getPositions()[i]);
              }
            }
          }

          /**
           * @see org.eclipse.emf.ecore.xmi.impl.XMLHandler#setValueFromId(org.eclipse.emf.ecore.EObject,
           *      org.eclipse.emf.ecore.EReference, java.lang.String)
           */
          @Override
          protected void setValueFromId(EObject object, EReference eReference, String ids) {
            MigrationHelpers.getInstance().setValueFromId(object, eReference, ids);
            super.setValueFromId(object, eReference, ids);
          }

          /**
           * Use here to perform additional modifications to ecore2ecore migration. According to old type typeName and
           * containing feature, it is possible to modify the migrated object result (for instance, set a kind in the
           * result)
           */
          private void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature) {

            MigrationHelpers.getInstance().updateElement(peekObject, typeName, result, feature, resource, context);

          }

          @Override
          protected EObject validateCreateObjectFromFactory(EObject peekObject, EFactory factory, String typeName,
              EObject newObject, EStructuralFeature feature) {
            EObject result = null;

            // On move of Eclass between two Ecore with ecore2ecore,
            // a new object which will be setted in the model
            // couldn't be created, so use default method to create it.
            if (newObject == null) {
              boolean oldUseNewMethods = useNewMethods;
              EStructuralFeature oldContextFeature = contextFeature;
              useNewMethods = true;
              contextFeature = null;
              result = super.validateCreateObjectFromFactory(peekObject, factory, typeName, newObject, feature);
              useNewMethods = oldUseNewMethods;
              contextFeature = oldContextFeature;
            } else {
              result = super.validateCreateObjectFromFactory(peekObject, factory, typeName, newObject, feature);
            }

            updateElement(peekObject, typeName, result, feature);
            return result;
          }

          @Override
          protected EObject validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject,
              boolean top) {
            EObject result = super.validateCreateObjectFromFactory(factory, typeName, newObject, top);
            updateElement(null, typeName, result, null);
            return result;
          }

          @Override
          protected EObject validateCreateObjectFromFactory(EFactory factory, String typeName, EObject newObject,
              EStructuralFeature feature) {
            EObject result = super.validateCreateObjectFromFactory(factory, typeName, newObject, feature);
            updateElement(null, typeName, result, feature);
            return result;
          }

          /**
           * {@inheritDoc}
           */
          @Override
          protected EObject createObjectFromTypeName(EObject peekObject, String typeQName, EStructuralFeature feature) {

            String qName = typeQName;

            String migratedQName = MigrationHelpers.getInstance().getQName(peekObject, typeQName, feature, resource,
                helper, context);
            if (migratedQName != null) {
              qName = migratedQName;
            }

            EObject eObject = super.createObjectFromTypeName(peekObject, qName, feature);
            MigrationHelpers.getInstance().updateCreatedObject(peekObject, eObject, typeQName, feature, resource,
                helper, context);

            return eObject;
          }

          /**
           * {@inheritDoc}
           */
          @Override
          protected EFactory getFactoryForPrefix(String prefix) {

            EFactory migratedEFactory = MigrationHelpers.getInstance().getEFactory(prefix, resource, context);
            if (migratedEFactory != null) {
              return migratedEFactory;
            }

            return super.getFactoryForPrefix(prefix);
          }

        };
      }
    };
  }
}
