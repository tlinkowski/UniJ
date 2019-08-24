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
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.collector.Collectors2;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableSetFactory;

/**
 * Implementation of {@link UnmodifiableSetFactory} that returns Eclipse's {@link ImmutableSet}.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 30)
@AutoService(UnmodifiableSetFactory.class)
public final class EclipseUnmodifiableSetFactory implements UnmodifiableSetFactory {

  //region COLLECTOR
  @Override
  public <E> Collector<E, ?, Set<E>> collector() {
    return Collectors.collectingAndThen(Collectors2.toImmutableSet(), ImmutableSet::castToSet);
  }
  //endregion

  //region COPY OF
  @Override
  public <E> Set<E> copyOf(Collection<? extends E> coll) {
    return Sets.immutable.<E>ofAll(coll).castToSet();
  }
  //endregion

  //region OF
  @Override
  public <E> Set<E> of() {
    return Sets.immutable.<E>of().castToSet();
  }

  @Override
  public <E> Set<E> of(E e1) {
    return Sets.immutable.of(e1).castToSet();
  }

  @Override
  public <E> Set<E> of(E e1, E e2) {
    return Sets.immutable.of(e1, e2).castToSet();
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3) {
    return Sets.immutable.of(e1, e2, e3).castToSet();
  }

  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4) {
    return Sets.immutable.of(e1, e2, e3, e4).castToSet();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) {
    return Sets.immutable.of(e1, e2, e3, e4, e5).castToSet();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
    return Sets.immutable.of(e1, e2, e3, e4, e5, e6).castToSet();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
    return Sets.immutable.of(e1, e2, e3, e4, e5, e6, e7).castToSet();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
    return Sets.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8).castToSet();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
    return Sets.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8, e9).castToSet();
  }

  @SuppressWarnings({"unchecked", "PMD.ExcessiveParameterList"})
  @Override
  public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
    return Sets.immutable.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10).castToSet();
  }

  @SafeVarargs
  @Override
  public final <E> Set<E> of(E... elements) {
    return Sets.immutable.of(elements).castToSet();
  }
  //endregion
}
