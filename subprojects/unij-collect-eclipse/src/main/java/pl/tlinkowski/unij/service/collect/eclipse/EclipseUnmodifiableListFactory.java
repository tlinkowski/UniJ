/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.tlinkowski.unij.service.collect.eclipse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.collector.Collectors2;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableListFactory;

/**
 * Implementation of {@link UnmodifiableListFactory} that returns Eclipse's {@link ImmutableList}s.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 30)
@AutoService(UnmodifiableListFactory.class)
public final class EclipseUnmodifiableListFactory implements UnmodifiableListFactory {

  //region COLLECTOR
  @Override
  public <E> Collector<E, ?, List<E>> collector() {
    return Collectors.collectingAndThen(Collectors2.toImmutableList(), ImmutableList::castToList);
  }
  //endregion

  //region COPY OF
  @Override
  public <E> List<E> copyOf(Collection<? extends E> coll) {
    return Lists.immutable.<E>ofAll(coll).castToList();
  }
  //endregion

  //region OF
  @Override
  public <E> List<E> of() {
    return Lists.immutable.<E>of().castToList();
  }

  @Override
  public <E> List<E> of(E e1) {
    return Lists.immutable.of(e1).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2) {
    return Lists.immutable.of(e1, e2).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3) {
    return Lists.immutable.of(e1, e2, e3).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4) {
    return Lists.immutable.of(e1, e2, e3, e4).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
    return Lists.immutable.of(e1, e2, e3, e4, e5).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return Lists.immutable.of(e1, e2, e3, e4, e5, e6).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return Lists.immutable.of(e1, e2, e3, e4, e5, e6, e7).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return Lists.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8).castToList();
  }

  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return Lists.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8, e9).castToList();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return Lists.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10).castToList();
  }

  @SafeVarargs
  @Override
  public final <E> List<E> of(E... elements) {
    return Lists.immutable.of(elements).castToList();
  }
  //endregion
}
