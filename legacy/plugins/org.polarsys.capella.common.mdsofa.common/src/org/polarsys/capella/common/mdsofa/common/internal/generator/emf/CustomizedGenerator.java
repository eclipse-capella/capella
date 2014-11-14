/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.internal.generator.emf;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;

/**
 * Customized EMF generator. This generator only uses the provided generator adapter factory if any.<br>
 * If not provided, extension-point mechanism is used to retrieve all generator adapter factories.
 */
public class CustomizedGenerator extends Generator {
  /**
   * Java generator adapter factory.
   */
  private GeneratorAdapterFactory _generatorAdapterFactory;

  /**
   * Constructor.
   * @param generatorAdapterFactory_p customized generator that overrides EMF default one.
   */
  public CustomizedGenerator(GeneratorAdapterFactory generatorAdapterFactory_p) {
    _generatorAdapterFactory = generatorAdapterFactory_p;
    if (null != _generatorAdapterFactory) {
      _generatorAdapterFactory.setGenerator(this);
    }
  }

  /**
   * @see org.eclipse.emf.codegen.ecore.generator.Generator#getAdapterFactories(java.lang.Object)
   */
  @Override
  protected Collection<GeneratorAdapterFactory> getAdapterFactories(Object object_p) {
    Collection<GeneratorAdapterFactory> result = null;
    // If a customized generator is provided, use this one instead.
    if (null != _generatorAdapterFactory) {
      result = new ArrayList<GeneratorAdapterFactory>(1);
      result.add(_generatorAdapterFactory);
    } else {
      // None is provided, let's use the ones returned by EMF.
      result = super.getAdapterFactories(object_p);
    }
    return result;
  }
}
