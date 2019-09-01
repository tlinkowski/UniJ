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
package pl.tlinkowski.unij.service.misc.jdk11;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

import com.google.auto.service.AutoService;

import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.misc.MiscellaneousApiProvider;

/**
 * Implementation of {@link MiscellaneousApiProvider} that delegates to original JDK 11 APIs.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 10)
@AutoService(MiscellaneousApiProvider.class)
public final class Jdk11MiscellaneousApiProvider implements MiscellaneousApiProvider {

  //region COLLECTORS
  @Override
  public <T, U, A, R> Collector<T, ?, R> flatMappingCollector(Function<? super T, ? extends Stream<? extends U>> mapper,
          Collector<? super U, A, R> downstream) {
    return Collectors.flatMapping(mapper, downstream);
  }

  @Override
  public <T, A, R> Collector<T, ?, R> filteringCollector(Predicate<? super T> predicate,
          Collector<? super T, A, R> downstream) {
    return Collectors.filtering(predicate, downstream);
  }
  //endregion
}
