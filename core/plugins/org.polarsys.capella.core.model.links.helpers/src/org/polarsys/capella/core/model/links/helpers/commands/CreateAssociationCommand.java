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
package org.polarsys.capella.core.model.links.helpers.commands;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.sirius.analysis.InformationServices;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 */
public class CreateAssociationCommand extends AbstractCreateLinksCommand {
  protected Association _createdAssociation;

  public CreateAssociationCommand() {
    super("Association", LinkStyle.LINE_SOLID_WITH_FILLED_ARROW);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCreatedLinkObject() {
    return _createdAssociation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean prepare() {
    return true;
  }

  /**
   * {@inheritDoc}<br>
   * This method is inspired from the Association creation procedure of the common.odesign file.
   */
  @Override
  public void execute() {
    AbstractType source = (AbstractType) getSource();
    AbstractType target = (AbstractType) getTarget();
    // Find the common ancestor of source and target.
    EObject commonAncestor = CapellaServices.getService().getCommonAncestor(source, target);
    // Find the container for the association to create.
    EObject container = commonAncestor;
    while ((null != container) && !InformationPackage.Literals.ASSOCIATION_PKG.isInstance(commonAncestor)) {
      container = container.eContainer();
    }
    if (container != null && InformationPackage.Literals.ASSOCIATION_PKG.isInstance(container)) {
      _createdAssociation = InformationFactory.eINSTANCE.createAssociation();
      // Generate association name.
      // TODO see if there is a common name generator for MA.
      String name = ((AssociationPkg) container).getName() + "Association" + (((AssociationPkg) container).getOwnedAssociations().size() + 1);
      _createdAssociation.setName(name);

      Property sourceProperty;
      if (InformationPackage.Literals.UNION.isInstance(source)) {
        sourceProperty = InformationFactory.eINSTANCE.createUnionProperty();
      } else {
        sourceProperty = InformationFactory.eINSTANCE.createProperty();
      }
      sourceProperty.setAbstractType(target);
      sourceProperty.setAggregationKind(AggregationKind.ASSOCIATION);
      sourceProperty.setName(InformationServices.getService().convertToUpperFirst(null, target.getName()));
      LiteralNumericValue sourceMinCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      sourceMinCard.setValue("1");
      sourceProperty.setOwnedMinCard(sourceMinCard);
      LiteralNumericValue sourceMaxCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      sourceMaxCard.setValue("1");
      sourceProperty.setOwnedMaxCard(sourceMaxCard);

      Property targetProperty = InformationFactory.eINSTANCE.createProperty();
      targetProperty.setAbstractType(source);
      targetProperty.setAggregationKind(AggregationKind.ASSOCIATION);
      targetProperty.setName(InformationServices.getService().convertToUpperFirst(null, source.getName()));
      LiteralNumericValue targetMinCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      targetMinCard.setValue("1");
      targetProperty.setOwnedMinCard(targetMinCard);
      LiteralNumericValue targetMaxCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
      targetMaxCard.setValue("1");
      targetProperty.setOwnedMaxCard(targetMaxCard);

      _createdAssociation.getOwnedMembers().add(sourceProperty);
      _createdAssociation.getOwnedMembers().add(targetProperty);
      _createdAssociation.getNavigableMembers().add(sourceProperty);

      // Association is navigable (source->target).
      ((Classifier) source).getOwnedFeatures().add(sourceProperty);

      ((AssociationPkg) container).getOwnedAssociations().add(_createdAssociation);
    }

  }
}
