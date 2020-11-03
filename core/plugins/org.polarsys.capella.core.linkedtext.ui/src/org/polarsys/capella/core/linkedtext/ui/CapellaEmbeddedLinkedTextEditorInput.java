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
package org.polarsys.capella.core.linkedtext.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.linkedtext.ui.DefaultLinkedTextResolver;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextDocument;
import org.polarsys.capella.common.linkedtext.ui.LinkedTextDocument.Resolver;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.helpers.ConstraintExt;

import com.google.common.base.Predicate;

public abstract class CapellaEmbeddedLinkedTextEditorInput implements LinkedTextDocument.Input {

  private final EObject documentBase;
  private ILabelProvider labelProvider;

  public CapellaEmbeddedLinkedTextEditorInput(EObject documentBase) {
    this.documentBase = documentBase;
  }

  private static final LinkedTextDocument.Resolver _resolver = new DefaultLinkedTextResolver(new Predicate<Resource>() {
    @Override
    public boolean apply(Resource res) {
      return CapellaResourceHelper.isCapellaResource(res);
    }
  });

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getDocumentBase() {
    return documentBase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILabelProvider getLabelProvider() {
    if (labelProvider == null && documentBase != null) {
      labelProvider = new MDEAdapterFactoryLabelProvider() {
        @Override
        public String getText(Object object) {
          if (object instanceof AbstractNamedElement) {
            String name = ((AbstractNamedElement) object).getName();
            if ((name != null) && (name.length() > 0)) {
              return name;
            }
            return "[Unnamed " + ((EObject) object).eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
          }
          return super.getText(object);
        }
      };
      ((AdapterFactoryLabelProvider) labelProvider).setFireLabelUpdateNotifications(true);        
    }
    return labelProvider;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Resolver getResolver() {
    return _resolver;
  }

  public void dispose() {
    if (labelProvider != null) {
      labelProvider.dispose();
    }
  }

  public static String getDefaultText(Constraint constraint) {
    return getDefaultText(constraint, constraint.getName());
  }

  public static String getDefaultText(Constraint contraint, String defaultName) {
    String result = defaultName;

    if ((result == null) || result.isEmpty()) {
      ValueSpecification vspec = contraint.getOwnedSpecification();

      if (vspec instanceof OpaqueExpression) {
        final OpaqueExpression exp = (OpaqueExpression) vspec;
        if ((!exp.getLanguages().isEmpty()) && (!exp.getBodies().isEmpty())) {
          if (ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT.equals(exp.getLanguages().get(0))) {
            CapellaEmbeddedLinkedTextEditorInput input = new CapellaEmbeddedLinkedTextEditorInput.Readonly(exp, exp.getBodies().get(0));
            result = LinkedTextDocument.load(input).get();
            input.dispose();
          } else {
            result = exp.getBodies().get(0);
          }
        }
      } else if (vspec instanceof AbstractExpressionValue) {
        result = EObjectLabelProviderHelper.getText(vspec);
      }
    }
    return result == null ? "" : result; //$NON-NLS-1$
  }

  /**
   * A read-only input, useful for one-way conversion of linked text to something else.
   */
  public static class Readonly extends CapellaEmbeddedLinkedTextEditorInput {
    final String text;

    public Readonly(EObject documentBase, String text) {
      super(documentBase);
      this.text = text;
    }

    @Override
    public String getText() {
      return text;
    }

    @Override
    public void setText(String linkedText) {
      throw new UnsupportedOperationException();
    }
  }
}