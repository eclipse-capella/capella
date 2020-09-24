/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.contributor;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;

/**
 *
 */
public abstract class AbstractMigrationContributor {

  public abstract String getKind();

  public Collection<IResource> getMigrableFiles(Collection<IResource> resources) {
    return resources.stream().filter(this::isValidResource).collect(Collectors.toList());
  }

  public abstract boolean isValidResource(IResource member);

  public abstract AbstractMigrationRunnable getRunnable(IFile file);

}
